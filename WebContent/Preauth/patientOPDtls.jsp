 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.ResourceBundle,java.util.ArrayList,java.util.HashMap" isErrorPage="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ include file="/common/include.jsp"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Referral Route</title>  
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen"> 
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script LANGUAGE="JavaScript" type="text/javascript" SRC="/<%=context%>/Preauth/maximizeScreen.js"></script>
<script type="text/javascript" src="js/patientscripts.js"></script>
<script LANGUAGE="JavaScript" SRC="scripts/PreauthScripts.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"> 
<%-- <%@ include file="/common/includeScrollbar.jsp"%> --%>

<%@ include file="/common/includePatientDetails.jsp"%>
<c:if test="${resMsg != null }">
<script>
jqueryInfoMsg('Result','${resMsg}');
</script>
</c:if>
<script type="text/javascript">
var scc='';
/* sai krishna:#CR8566:for dialysis cycles view */
var speciality='${speciality}';
function fn_maxmizeTop()
{
	parent.fn_maxmizeTop();
}
function fn_maxmizeRight(){
	parent.fn_maxmizeRight();
}

var ViewType = '${viewType}';
var caseFlag = parent.caseApprovalFlag;
var caseId = '${caseId}';
function viewTelephonicInfo(telephonicId){
	window.open('/Operations/preauthDetails.do?actionFlag=ViewTelephonicDtls&telephonicId='+telephonicId,'','scrollbars=1,left=20,top=20,width=1000,height=650');
}
function CasesView()  
{	
     var url='/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=ipTab&CaseId=${caseId}&flag=N&casesForApproval='+parent.caseApprovalFlag+'&errSearchType='+parent.errSearchType+'&disSearchType='+parent.disSearchType+'&module='+parent.module;
	 document.forms[0].action=url;
	 document.forms[0].target="_parent";
     document.forms[0].submit();	
}
function fn_getClinicData(vType,message)
{
//fn_changeColors(id);
//	var url="/Operations/clinicalNotesAction.do?actionFlag=clinicalNotes&caseId=${PatientDtls.PreauthVO.caseId}&CaseStat=${PatientDtls.PreauthVO.caseStatusId}&notesType="+vType+"&RestrictFlag=${RestrictFlag}";   
	var url="/Operations/clinicalNotesAction.do?actionFlag=clinicalNotes&caseId=${caseId}&notesType="+vType+"&caseApprovalFlag="+parent.caseApprovalFlag+'&errSearchType='+parent.errSearchType+'&ClinicalCount='+message+'&disSearchType='+parent.disSearchType+'&module='+parent.module;
   document.forms[0].action=url;   
   document.forms[0].target="bottomFrame";
   document.forms[0].submit();
}

function showBiometricAttendence()
{
	var url="/ASRI/preauthAction.do?actionFlag=getPatientBiometric&CaseId=${caseId}&viewType=View";
	showBiometricAtt = window.open(url,"biometricAtt",'width=750,height=200,resizable=yes,top=100,left=0,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=yes');
}
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
function fn_PastData()
{
   //var url="/Operations/casesApprovalAction.do?actionFlag=getPastHistory&caseId=${caseId}&cardNo=${patCommonDtls.CARDNO}";   
   var url="/Operations/empPenCaseSearch.do?actionFlag=caseSearch&employeeNo=${patCommonDtls.EMPLOYEENO}&fromPastHistory=Y";
   document.forms[0].action=url;   
   document.forms[0].target="bottomFrame";
   document.forms[0].submit();	
}
function fn_attachments()
{
	var url="/Operations/attachmentAction.do?actionFlag=onload&UpdType=ehfPreauth&caseId=${caseId}&caseAttachmentFlag=Y&caseApprovalFlag="+parent.caseApprovalFlag+'&errSearchType='+parent.errSearchType+'&disSearchType='+parent.disSearchType+'&module='+parent.module;      
	   document.forms[0].action=url;   
	   document.forms[0].target="bottomFrame";
	   document.forms[0].submit();	
}
function fn_OnlineCaseSheet(caseSheetFlag)
{
	var url="/Operations/preauthDetails.do?actionFlag=getOnlineCaseSheet&caseId=${caseId}&patientId=${patCommonDtls.PATID}&caseSheetFlag="+caseSheetFlag;      
	   document.forms[0].action=url;   
	   document.forms[0].target="bottomFrame";
	   document.forms[0].submit();	
}
function fn_getIP()
{	
	if(parent.ipRefershFlag != null && parent.ipRefershFlag !='' && parent.ipRefershFlag=='Y')
		{
		return;
		}
	else
		{
   var url="/Operations/patCommonDtls.htm?actionFlag=getPatDetails&CaseId=${patCommonDtls.CASEID}&PatientID=${patCommonDtls.PATID}";   
   document.forms[0].action=url;   
   document.forms[0].target="bottomFrame";
   document.forms[0].submit();
		}
}
function fn_flagging()
{
	url="/Operations/flaggingAction.do?actionFlag=getFlagPage&caseId=${caseId}";
	document.forms[0].action=url;
	document.forms[0].target="bottomFrame";
	document.forms[0].submit();
}

function fn_getFraudCr()
{
  //fn_changeColors(id);
   var url="/Operations/patCommonDtls.htm?actionFlag=viewCMAremarks&CaseId=${patCommonDtls.CASEID}";   
   document.forms[0].action=url;   
   document.forms[0].target="bottomFrame";
   document.forms[0].submit();    
}
//sai:#CR8566:function call to get dialysis cycles data.
function viewDialysisCycles(){
	var showdialysis = 0;
	var caseId = '${patCommonDtls.CASEID}';
	var url="/<%=context%>/preauthDetails.do?actionFlag=viewDialysisCycles&CaseId="+caseId;
    window.open(url,"dialysisAtt",'width=1000,height=450,resizable=yes,top=80,left=150,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=yes');
}
function fn_preauthDetails()
{	
	 var url1="/<%=context%>/preauthDetails.do?actionFlag=preauthDetailsEhf&caseId=${caseId}&patientId=${patCommonDtls.PATID}&caseApprovalFlag="+parent.caseApprovalFlag+"&patientAge=${patCommonDtls.AGEYEARS}";
	   document.forms[0].action=url1;   
	   document.forms[0].target="bottomFrame";
	   document.forms[0].submit();
	}
function fn_preauthDetails1()
{	
	
	var xmlhttp;
	var url;
	if (window.XMLHttpRequest)
	{
	 xmlhttp=new XMLHttpRequest();
	}
	else if (window.ActiveXObject)
	{		
	 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	else
	{
	 alert("Your Browser Does Not Support XMLHTTP!");
	}
	 url = '/<%=context%>/preauthDetails.do?actionFlag=checkClinicalNotes&caseId=${caseId}&callType=Ajax';
	 xmlhttp.onreadystatechange=function()
	{
	    if(xmlhttp.readyState==4)
	    {	
	    	
	    	 var resultArray=xmlhttp.responseText;
		        var resultArray = resultArray.split("*");
		        if(resultArray[0]=="SessionExpired"){
		    		//alert("Session has been expired");
		    		//parent.sessionExpireyClose();
		    		 var fr = partial(parent.sessionExpireyClose);
		    		 jqueryInfoMsg('Session details',"Session has been expired",fr);
		    		}
		    		else
		    		{
		        if(resultArray[0]!=null)
		        {
		        	 if('${viewType}' != null && '${viewType}' =='medco' && parent.caseApprovalFlag =='Y')
		        		 {
		        		 if(resultArray[0] =='success')
			        	   {
		        			 var url1="/<%=context%>/preauthDetails.do?actionFlag=preauthDetailsEhf&caseId=${caseId}&patientId=${patCommonDtls.PATID}&caseApprovalFlag="+parent.caseApprovalFlag+"&patientAge=${patCommonDtls.AGEYEARS}";  
		        			 document.forms[0].action=url1;   
		  		    	     document.forms[0].target="bottomFrame";
		  		    	     document.forms[0].submit();
			        	   }
		        		 else
		        			 {
		        			 var fr = partial(fn_navigateClinical);
		        			  jqueryAlertMsg('Mandatory check',resultArray[0],fr);
		        			 }
		        		 }
		        	 else
		        		 {
		        		 var url1="/<%=context%>/preauthDetails.do?actionFlag=preauthDetailsEhf&caseId=${caseId}&patientId=${patCommonDtls.PATID}&caseApprovalFlag="+parent.caseApprovalFlag+"&patientAge=${patCommonDtls.AGEYEARS}";
		        		   document.forms[0].action=url1;   
				    	   document.forms[0].target="bottomFrame";
				    	   document.forms[0].submit();
		        		 }
		          
		          	   
		        }
	    }// end of if
	    	
	    }}
	 
	 xmlhttp.open("Post",url,true);
	 xmlhttp.send(null);  	
}
function fn_navigateClinical(message)
{
	 parent.leftFrame.fn_highlight('clinicalNotes');
	 fn_getClinicData('PRE',message);
	}
function fn_UploadFile()
{
	var url ="/<%=context%>/patCommonDtls.htm?actionFlag=uploadFile&CaseId=${patCommonDtls.CASEID}&Status=${patCommonDtls.STATUS}&patId=${patCommonDtls.PATID}";
	 document.forms[0].action=url;   
	 document.forms[0].target="topFrame";
	 document.forms[0].submit();	   
	}
function fn_claim()//076
{
 //alert("CaseId="+'${patCommonDtls.CASEID}'+"&UserRole="+'${UserRole}'+"&CaseStat="+'${patCommonDtls.CASESTAT}'+"&payee="+'${PatientDtls.PreauthVO.payee}'+"&ProcessFlag="+'${PatientDtls.PreauthVO.processFlag}'+"&Status="+'${status}'+"&ErrCase="+'${ErrCase}'+"&TdsPmtType="+'${TdsPmtType}'+"&ErrAprvFlag="+'${ErrAprv}'+"&ErrStatus="+'${PatientDtls.PreauthVO.errStatus}'+"&PendUpdFlag="+'${PendUpdFlag}'+"&StatusFlag="+'${StatusFlag}'+"&DeductorType="+'${PatientDtls.PreauthVO.deductorType}'+"&RationCard="+'${ratCard}'+"&Phase="+'${phase}'+"&PhaseRenewal="+'${PatientDtls.PreauthVO.renewal}'+"&hospType="+'${PatientDtls.PreauthVO.hospType}'+"&RestrictFlag="+'${RestrictFlag}');

var url="/Operations/ClaimsFlow.do?actionFlag=Claims&CaseId=${patCommonDtls.CASEID}&UserRole=${UserRole}&CaseStat=${patCommonDtls.CASESTAT}&payee=${PatientDtls.PreauthVO.payee}&ProcessFlag=${PatientDtls.PreauthVO.processFlag}&Status=${status}&ErrCase=${ErrCase}&TdsPmtType=${TdsPmtType}&ErrAprvFlag=${ErrAprv}&ErrStatus=${PatientDtls.PreauthVO.errStatus}&PendUpdFlag=${PendUpdFlag}&StatusFlag=${StatusFlag}&DeductorType=${PatientDtls.PreauthVO.deductorType}&RationCard=${ratCard}&Phase=${phase}&PhaseRenewal=${PatientDtls.PreauthVO.renewal}&hospType=${PatientDtls.PreauthVO.hospType}&RestrictFlag=${RestrictFlag}&caseApprovalFlag="+parent.caseApprovalFlag;;	 
	// var url="/Operations/ClaimsFlow.do?actionFlag=Claims&CaseId=1329543";
		 //&CaseId=${patCommonDtls.CASEID}&UserRole=${UserRole}&CaseStat=${patCommonDtls.CASESTAT}&payee=${PatientDtls.PreauthVO.payee}&ProcessFlag=${PatientDtls.PreauthVO.processFlag}&Status=${status}&ErrCase=${ErrCase}&TdsPmtType=${TdsPmtType}&ErrAprvFlag=${ErrAprv}&ErrStatus=${PatientDtls.PreauthVO.errStatus}&PendUpdFlag=${PendUpdFlag}&StatusFlag=${StatusFlag}&DeductorType=${PatientDtls.PreauthVO.deductorType}&RationCard=${ratCard}&Phase=${phase}&PhaseRenewal=${PatientDtls.PreauthVO.renewal}&hospType=${PatientDtls.PreauthVO.hospType}&RestrictFlag=${RestrictFlag}";
	   document.forms[0].action=url;   
	   document.forms[0].target="bottomFrame";
	   document.forms[0].submit(); 
		
		//document.getElementById('dataFrame').src ="/Operations/ClaimsFlow.do?actionFlag=Claims&CaseId=${patCommonDtls.CASEID}&UserRole=${UserRole}&CaseStat=${patCommonDtls.CASESTAT}&payee=${PatientDtls.PreauthVO.payee}&ProcessFlag=${PatientDtls.PreauthVO.processFlag}&Status=${status}&ErrCase=${ErrCase}&TdsPmtType=${TdsPmtType}&ErrAprvFlag=${ErrAprv}&ErrStatus=${PatientDtls.PreauthVO.errStatus}&PendUpdFlag=${PendUpdFlag}&StatusFlag=${StatusFlag}&DeductorType=${PatientDtls.PreauthVO.deductorType}&RationCard=${ratCard}&Phase=${phase}&PhaseRenewal=${PatientDtls.PreauthVO.renewal}&hospType=${PatientDtls.PreauthVO.hospType}&RestrictFlag=${RestrictFlag}";
}
function showCMS()
{
	var caseScheme="${caseScheme}";
	
   var url="https://ehf.telangana.gov.in/CMS/login.htm?flag=showChangeMgmt&caseId=${patCommonDtls.CASEID}&caseNo=${patCommonDtls.CASENO}&caseScheme="+caseScheme;
	   //var url="http://172.25.147.51:8080/CMS/changeMgmtAction.do?flag=showChangeMgmt&caseId=${patCommonDtls.CASEID}&caseNo=${patCommonDtls.CASENO}&caseScheme="+caseScheme;
   var child= window.open( url,'ChangeManagement','width=800, height=600, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes,fullscreen=YES');
   if (window.focus) {child.focus();
	}
  
}
function fn_removeLoadingImage()  
{
	document.getElementById('processImagetable').style.display="none";  
}
function showHealthCard(cardNo){

	var xmlhttp;
    var url;
	   if (window.XMLHttpRequest)
  		{
   		xmlhttp=new XMLHttpRequest();
  		}
  		else if (window.ActiveXObject)
  		{		
   		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  		}
 		 else
 		 {
 			jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
  		 }   
  		 url = "./preauthDetails.do?actionFlag=getHealthCardDetails&cardNo="+cardNo;  
  		 xmlhttp.onreadystatechange=function()
  		 {
      		if(xmlhttp.readyState==4)
      		{
      		
   	   		var resultArray=xmlhttp.responseText;
   	   		resultArray = resultArray.replace("*","");
          		if(resultArray.trim()=="SessionExpired*")
          		{
          			jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
          		}
          		else
          		{
          			if(resultArray!=null && resultArray.length>0){
          			var jsonData=JSON.parse(resultArray);
          			document.getElementById("cardNoSpan").innerHTML='${patCommonDtls.CARDNO}';
          			document.getElementById("nameSpan").innerHTML=jsonData.cardDetails[0].NAME;
          			document.getElementById("genderSpan").innerHTML=jsonData.cardDetails[0].EGENDER;
          			document.getElementById("dobSpan").innerHTML=jsonData.cardDetails[0].BENDOB;
          			document.getElementById("relationSpan").innerHTML=jsonData.cardDetails[0].RELATION;
          			document.getElementById("addressSpan").innerHTML=jsonData.cardDetails[0].ADDRESS;
          			document.getElementById("empNameSpan").innerHTML=jsonData.cardDetails[0].EMPNAME;
          			document.getElementById("empIdSpan").innerHTML=jsonData.cardDetails[0].EMPID;
          			document.getElementById("aadhaarSpan").innerHTML=jsonData.cardDetails[0].AADHARID;
          			document.getElementById("ddoDistSpan").innerHTML=jsonData.cardDetails[0].DDO;
          			document.getElementById("empNameLabelSpan").innerHTML=jsonData.cardDetails[0].CARDTYPE;
          			document.getElementById("empIdLabelSpan").innerHTML=jsonData.cardDetails[0].CARDTYPE;
          			if(jsonData.cardDetails[0].CARDTYPE=="Journalist"){
          				$(".ehsScheme").css("display","none");
          				$(".jrnlstScheme").css("display","");
          			document.getElementById("accNoSpan").innerHTML=jsonData.cardDetails[0].ACCRIDNO;
          			document.getElementById("mobileNoSpan").innerHTML=jsonData.cardDetails[0].MOBILENO;
          			document.getElementById("emailSpan").innerHTML=jsonData.cardDetails[0].EMAILID;
          			document.getElementById("idTypeSpan").innerHTML=jsonData.cardDetails[0].IDTYPE;
          			document.getElementById("idNumberSpan").innerHTML=jsonData.cardDetails[0].AADHARID;
          			
          			}
          			else{
          				$(".jrnlstScheme").css("display","none");
          				$(".ehsScheme").css("display","");
          			}
          			$('#healthCardModal').modal({
          				backdrop : 'static',
          				keyboard : false,
          				show : true
          			},'show');
          			}
          			else
          				alert("No Records Found");
        		}
      		}
  		 }
  		
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	
}
</script>
<style type="text/css">
#imageID {
top: 151px;
}
.modal-body
{
overflow-x:hidden;
}
.modal-dialog{
width:600px;
}
.bootbox .modal-dialog .modal-content {top:30% !important;}
.modal-header{background:#447fcf;color:#fff;}
</style>
</head>
<body>
<form name="AdmnNotes" method=post enctype="multipart/form-data" modelAttribute="uploadItem" id="form1">
<table border="0" width="100%" >
<tr class="tbheader">
<td id="topSlide" width="5%">
<img id="menuImage" src="images/rightLeftArrow.jpg" title="Maximize/Minimize" style=cursor:hand; width="25" height="25" alt="Hide Menu" align="top" onclick="javascript:fn_maxmizeRight()" ></img>
</td>
<td  colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Patient Details</strong></td>
<td id="menuSlide" width="5%">  
<img id="topImage" src="images/back.jpg" width="30" height="20" style=cursor:hand; title="Back" alt="Back" align="top" onclick="javascript:fn_maxmizeTop()" ></img>
</td>
</tr></table>
<!-- Patient Details  -->
<table width="100%" onload="javascript:fn_getIP();fn_removeLoadingImage();">
<tr><td width="100%" valign="top">

 <table width="83%" border="0" align="left" cellpadding="0" cellspacing="4">
 <tr><td width="10%" class="labelheading1 tbcellCss" ><b>Name  </b></td><td width="20%" class="tbcellBorder" colspan="3">${patCommonDtls.PATIENTNAME} ,    ${patCommonDtls.GENDER} ,    ${patCommonDtls.AGE}</td>
 	<c:if test="${patCommonDtls.telephonicId != null && patCommonDtls.telephonicId !='' }">
	 <td width="20%" class="labelheading1 tbcellCss"><b>Telephonic ID </b></td>
 <td class="tbcellBorder"><a href="javascript:viewTelephonicInfo('${patCommonDtls.telephonicId}')">${patCommonDtls.telephonicId}</a>  <!--<button class="but"   type="button" id="Back" value='Back' onclick = 'CasesView()' >Back</button>--> </td>
 </c:if>
 <c:if test="${patCommonDtls.telephonicId == null || patCommonDtls.telephonicId =='' }">
  <c:if test="${hideBackButton != 'Y'}">
				<td colspan="1">	 <!--  <button class="but"   type="button" id="Back" value='Back' onclick = 'CasesView()' >Back</button>--></td>
	</c:if>		
</c:if>

 </tr>
 <tr><td width="10%" class="labelheading1 tbcellCss"><b>Card No  </b></td><td width="20%" class="tbcellBorder">${patCommonDtls.CARDNO}</td><td width="10%" rowspan="1" class="labelheading1 tbcellCss"><b>Case Status </b></td><td width="34%" rowspan="1" class="tbcellBorder"> <b>${patCommonDtls.STATUS}</b></td><td width="8%" class="labelheading1 tbcellCss"><b>District&nbsp;:</b></td><td width="15%" class="tbcellBorder">${patCommonDtls.DISTRICT}</td></tr>
 <tr><td width="10%" class="labelheading1 tbcellCss"><b>Case No  </b></td><td width="20%" class="tbcellBorder">${patCommonDtls.CASENO}</td><td width="10%" class="labelheading1 tbcellCss"><b>Slab Type </b></td><td width="34%" class="tbcellBorder"> ${patCommonDtls.slabType}</td><td width="8%" class="labelheading1 tbcellCss"><b>Mandal</b></td><td width="15%" class="tbcellBorder">${patCommonDtls.MANDAL}</td></tr>
 <tr><td width="10%" class="labelheading1 tbcellCss"><b>IP No  </b></td><td width="20%" class="tbcellBorder">${patCommonDtls.IPNO}</td><td width="10%" class="labelheading1 tbcellCss"><b>NWH Name </b></td>
 
 <c:if test="${checkNABH eq 'Y'}">
 <td width="34%" class="tbcellBorder" style="color: blue;" title="NABH Hospital"><b>${patCommonDtls.HOSPNAME}</b></td>
 </c:if>
 <c:if test="${checkNABH eq 'N'}">
<td width="34%" class="tbcellBorder">${patCommonDtls.HOSPNAME}</td>
 </c:if>
 <td width="8%" class="labelheading1 tbcellCss"><b>Village</b></td><td width="15%" class="tbcellBorder">${patCommonDtls.VILLAGE}</td></tr>
 <tr><td width="10%" class="labelheading1 tbcellCss"><b>Claim No  </b></td><td width="20%" class="tbcellBorder">${patCommonDtls.CLAIMNO}</td><td width="10%" class="labelheading1 tbcellCss"><b>IP Reg Date </b></td><td width="34%" class="tbcellBorder">${patCommonDtls.date}</td><td width="8%" class="labelheading1 tbcellCss"><b>Contact No</b></td><td width="15%" class="tbcellBorder">${patCommonDtls.CONTACT}</td></tr>
 <c:if test="${PatientOpList.relationName ne null }"><c:if test="${PatientOpList.relationName eq 'New Born Baby'}"><tr><td width="10%" class="labelheading1 tbcellCss"><b>Patient Type</b></td><td width="20%" class="tbcellBorder"><b>${PatientOpList.relationName}</b></td></tr></c:if></c:if>
 <!-- <tr><td class="labelheading1 tbcellCss"><b>Case Status &nbsp;:</b></td><td colspan="2" class="tbcellBorder"><b>${patCommonDtls.STATUS}</b></td>
 <td></td><td></td><td colspan="1" align="center">
					<c:if test="${hideBackButton != 'Y'}">
					<button class="but"   type="button" id="Back" value='Back' onclick = 'CasesView()' >Back</button>
					</c:if>
					&nbsp;
				</td></tr>-->
 
  
 </table>
 <fieldset style="border:0;">
 <table width="17%" border="0" align="center">
	<tr height="20">
		<td>
			<c:if test="${fromCMS ne 'Y'}">
				<button class="but" style="font-size:12px; width:110px"  type="button" id="raiseCms" value="raiseCms" onclick ="javascript:showCMS();" >
					RAISE CMS
				</button>
			</c:if>
			<c:if test="${fromCMS eq 'Y'}">
				&nbsp;
			</c:if>
		</td>
	</tr>
 <tr>
 <td align="center" width="100%" >
 <c:choose>
 <c:when test="${patOnBedPic != null && patOnBedPic != 'N'}" >
	<img src="common/showDocument.jsp" width="110" height="90" alt="NO DATA" align="center" id="patImage" 
  onmouseover="javascript:resizePatImage('patImage','140','110')" onmouseout="resizePatImage('patImage','110','90')" />
 </c:when>
  <c:otherwise>
 
<img src="images/photonot.gif" width="110" height="90" alt="NO DATA" align="center" 
/>
  <%-- <form:input  path="fileData" type="file" cssStyle="width:120px" id="Attachment" size='2'  />
  <button class="but"   type="button" onclick="javascript:Validate('Attachment')" value="Upload" > Uplaod </button> --%>   
  
  </c:otherwise>
 </c:choose>
 </td></tr>
 <c:if test='${loginUserRole != "medco" && loginUserRole != "mithra"}'>
 <tr>
 <td>
 <button class="but" style="font-size:12px; width:110px"  type="button" id="viewHealthCard" value="viewHealthCard" data-toggle="modal" onclick ="javascript:showHealthCard('${patCommonDtls.CARDNO}');" >
					View Health Card
</button>
 </td>
 </tr>
 </c:if>
 </table>
 </fieldset>
 <!-- sai:#CR8566:Adding the new table to give onclick to view the dialysis cycles. -->
 <table width="17%" border="0" align="center">
 <c:if test="${speciality eq 'N18.6.A' && DialysisCnt ne '0'}">
<tr>
 <td width="20%" class="tbcellBorder" colspan="3" class="blink"><a href="javascript:void(0);" onclick="viewDialysisCycles()">View Dialysis Cycles</a></td>
 </tr>
 </c:if>
</table>
 </td></tr>
</table>
<!-- Patient Details  -->

<table border="0" width="100%" >
<tr class="tbheader">
<td  colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Registration Details</strong></td>
</tr></table>
<table border="0" width="100%" >
<tr>
<!--<td  class="labelheading1 tbcellCss"><b>Card Issue Date</b></td>
<td  align="left" class="tbcellBorder">${PatientOpList.cardIssueDt}</td>-->
<td  class="labelheading1 tbcellCss"><b>Occupation</b></td>
<td  align="left" class="tbcellBorder">${PatientOpList.occName}</td>
<td   class="labelheading1 tbcellCss"><b>Relationship With family head</b></td>
<td  align="left" class="tbcellBorder">${PatientOpList.relationName}</td>
</tr>
<tr>
<td colspan="2" class="tbheader"><center><b>Card Address</b></center></td>
<td colspan="2" class="tbheader"><center><b>Communication Address</b></center></td>
</tr>
<tr>
<td  width="20%" class="labelheading1 tbcellCss">
	<b>House No</b>
</td>
<td width="30%" class="tbcellBorder">
	${PatientOpList.cardHNo}
</td>

<td width="20%"  class="labelheading1 tbcellCss">
	<b>House No</b>
</td>
<td width="30%" class="tbcellBorder">
	${PatientOpList.houseNo}
</td>
</tr>
<tr>
<td  class="labelheading1 tbcellCss">
	<b>Street</b>
</td>
<td  class="tbcellBorder">
	${PatientOpList.cardStreet}
</td>
<td   class="labelheading1 tbcellCss">
	<b>Street</b>
</td>
<td  class="tbcellBorder">
		${PatientOpList.street}
</td>
</tr>
<tr>
<!-- <td>
	<b>Hamlet:</b>
</td>
<td>&nbsp;</td> -->
<td  class="labelheading1 tbcellCss">
	<b>Village</b>
</td>
<td class="tbcellBorder">
		${PatientOpList.cardVillage}
</td> 
<!-- <td>
	<b>Hamlet:</b>
</td>
<td>&nbsp;</td> -->
<td  class="labelheading1 tbcellCss">
	<b>Village</b>
</td>
<td class="tbcellBorder">
	${PatientOpList.village}
</td>
</tr>
<tr>
<td  class="labelheading1 tbcellCss">
	<b>Mandal</b>
</td>
<td  class="tbcellBorder">
	${PatientOpList.cardMandal}
</td>
 <td  class="labelheading1 tbcellCss">
	<b>Mandal</b>
</td>
<td class="tbcellBorder">
${PatientOpList.mandal}
</td>
</tr>
<tr>
<td  class="labelheading1 tbcellCss">
	<b>District</b>
</td>
<td class="tbcellBorder">
		${PatientOpList.cardDistrict}
</td>

<td  class="labelheading1 tbcellCss">
	<b>District</b>
</td>
<td class="tbcellBorder">
		${PatientOpList.district}
</td>
</tr>
<tr><td class="tbheader" colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Medical Details</b></td></tr>
<tr>
<td class="labelheading1 tbcellCss"><b>Complaint Type</b></td>
<td  class="tbcellBorder">${PatientOpList.complaintType}</td>
<td class="labelheading1 tbcellCss"><b>Patient Complaint</b></td>
<td  class="tbcellBorder">${PatientOpList.patComplaint}</td></tr>
<tr>
<td class="labelheading1 tbcellCss"><b>History of Present Illness</b></td>
<td  class="tbcellBorder">${PatientOpList.presentIllness}</td>
<c:if test="${telephonicRemks != null && telephonicRemks !='' }" >
<td class="labelheading1 tbcellCss"><b>Telephonic Intimation Remarks</b></td>
<td  class="tbcellBorder">${telephonicRemks}</td>
</c:if>
</tr>
</table>  
  <!-- IP Details -->

<!-- past history -->
<table width="100%">
<tr><td class="tbheader">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>History of Past Illness</b></td></tr>
</table>
<table border="0" width="100%" align=center>
<tr><td>
<c:set var="loopCount1" value="0" />
<c:forEach  items="${pastHistoryList}" varStatus="loop">								
<c:set value="${pastHistoryList[loop.index].ID}" var="sample"></c:set>	
<c:forTokens var="tokenName" items="${PatientOpList.pastIllness}" delims="~" varStatus="status" begin="0">
<c:choose>
<c:when test="${tokenName == sample}">
<c:set var="loopCount1" value="${loopCount1 + 1}" /> 
<c:if test="${(loopCount1 % 4) eq 1}">
	<tr>
	</c:if>		
<td class="tbcellBorder" >
<c:out value="${pastHistoryList[loop.index].VALUE}"/>	
	<c:if test="${tokenName == 'PH11' }">
	${PatientOpList.pastIllenesOthr}
   <%-- <input type="text" id="${pastHistoryList[loop.index].ID}" name="${PatientOpList.pastIllenesOthr}" value="${PatientOpList.pastIllenesOthr}" disabled="disabled"/> --%>
   </c:if>
   <c:if test="${tokenName == 'PH8' }">
	( ${PatientOpList.pastIllenesCancers})
   </c:if>
   <c:if test="${tokenName == 'PH10' }">
	( ${PatientOpList.pastIllenesSurg})
   </c:if>
   <c:if test="${tokenName == 'PH12' }">
	( ${PatientOpList.pastIllenesEndDis})
   </c:if>
   </td>
</c:when>
</c:choose>  
</c:forTokens>     	
</c:forEach>
</td></tr>
<c:if test="${fn:length(PatientOpList.pastIllness) eq 0}">
<tr><td align="center">History of past illness not found</td></tr>
</c:if>
</table>

<!-- Personal history -->
<table width="100%">
<tr><td class="tbheader">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Personal History</b></td></tr>
</table>

<table border="0" width="100%" >
<c:set  value="${PatientOpList.lstPerHis}" var="perHisList"></c:set>

<c:set var="loopCount" value="0" /> 
<c:forEach  items="${PatientOpList.lstPersonalHistory}" varStatus="loop">	
<c:set var="loopCount" value="${loopCount + 1}" />
<tr>
<c:if test="${(loopCount % 2) eq 1}">
	<tr>
	</c:if>	
		<td width="20%" class="labelheading1 tbcellCss">
<c:set value="${PatientOpList.lstPersonalHistory[loop.index].lstSub}" var="sample"></c:set>
<b>&nbsp;&nbsp;${PatientOpList.lstPersonalHistory[loop.index].VALUE} </b>
</td>
<c:forEach items="${sample}" varStatus="loop1">
<td  class="tbcellBorder">
<c:set value="${sample[loop1.index].ID}" var="subLstId"></c:set>
<c:if test="${subLstId!='PR6.2' && subLstId!='PR5.2'}">
&nbsp;&nbsp;${sample[loop1.index].VALUE}&nbsp;
</c:if>
<c:if test="${fn:contains(perHisList,sample[loop1.index].ID) }" >
<c:if test="${subLstId=='PR6.2'}">
No
</c:if>
<c:if test="${subLstId=='PR5.2'}">
No
</c:if>
  <input type="checkbox" value="${sample[loop1.index].VALUE}" disabled="disabled" checked="checked"/>  &nbsp;
<c:if test="${subLstId=='PR6.1'}">
<c:set var="test" value="${PatientOpList.test}" scope="page" />
<td  class="tbcellBorder"> &nbsp;&nbsp;No <input type="checkbox" value="${sample[loop1.index].VALUE}" disabled="disabled"/>&nbsp;</td>

      <table width="100%" border="0" align="center"><tr><td class="labelheading1 tbcellCss" nowrap="nowrap" width="20%">
	     &nbsp;a.<b>Alcohol  </b></td><td class="tbcellBorder" nowrap="nowrap"><input type="radio" name="alcohol" id="alcohol" value="Regular" disabled="disabled"/> &nbsp;Regular&nbsp;</td>
	     <td class="tbcellBorder" > <input type="radio" name="alcohol" id="alcohol" value="Occasional" disabled="disabled"/> &nbsp;Occasional&nbsp;</td>
	     <td class="tbcellBorder"> <input type="radio" name="alcohol" id="alcohol" value="Teetotaler" disabled="disabled"/> &nbsp;Teetotaler&nbsp; </td></tr>
	    	<tr><td class="labelheading1 tbcellCss"> &nbsp;b.<b>Tobacco  </b></td><td class="tbcellBorder"><input type="radio" name="tobacco" id="tobacco" value="Snuff" onclick="displayPackYears(this)" disabled="disabled"/> &nbsp;Snuff</td>
	      <td class="tbcellBorder"><input type="radio" name="tobacco" id="tobacco" value="Chewable" onclick="displayPackYears(this)" disabled="disabled"/> &nbsp;Chewable&nbsp;</td>
	     <td class="tbcellBorder" > <input type="radio" name="tobacco" id="tobacco" value="Smoking" onclick="displayPackYears(this)" disabled="disabled"/> &nbsp;Smoking&nbsp;
	      <span id="smokingTd" style="display:none" >
	      &nbsp;Pack  <input  class="tbcellBorder" type="text" name="packNo" id="packNo" style="width:40px" maxlength="3" title="Smoking Pack No" disabled="disabled"/>
	       &nbsp;Years  <input class="tbcellBorder" type="text" name="smokeYears" id="smokeYears" style="width:40px" maxlength="3" title="Smoking Years" disabled="disabled"/>
	     </span>
	     </td>
	      </tr>
	      <tr><td class="labelheading1 tbcellCss"> &nbsp;c.<b>Drug Use  </b></td><td class="tbcellBorder"><input type="radio" name="drugUse" id="drugUse" value="Yes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
	      <td class="tbcellBorder"><input type="radio" name="drugUse" id="drugUse" value="No" disabled="disabled"/> &nbsp;No&nbsp;</td></tr>
	      <tr><td class="labelheading1 tbcellCss"> &nbsp;d.<b>Betel nut  </b></td><td class="tbcellBorder"><input type="radio" name="betelNut" id="betelNut" value="Yes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
	     <td class="tbcellBorder"> <input type="radio" name="betelNut" id="betelNut" value="No" disabled="disabled"/> &nbsp;No&nbsp;</td></tr>
	      <tr><td class="labelheading1 tbcellCss"> &nbsp;e.<b>Betel leaf  </b></td><td class="tbcellBorder"><input type="radio" name="betelLeaf" id="betelLeaf" value="Yes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
	      <td class="tbcellBorder"><input type="radio" name="betelLeaf" id="betelLeaf" value="No" disabled="disabled"/> &nbsp;No&nbsp;</td></tr></table>      
</c:if>
<c:if test="${subLstId=='PR5.1'}">
<c:set var="testKnown" value="${PatientOpList.testKnown}" scope="page" />
<td  class="tbcellBorder"> &nbsp;&nbsp; No
<input type="checkbox" value="${sample[loop1.index].VALUE}" disabled="disabled"/>&nbsp;</td>

      <table width="100%" border="0" align="center"><tr><td class="labelheading1 tbcellCss" nowrap="nowrap" width="20%">
	     &nbsp;a.<b>Allergic to Medicine </b></td><td class="tbcellBorder" nowrap="nowrap"><input type="radio" name="AllMed" id="AllMed" value="AllMedYes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
	     <td class="tbcellBorder" > <input type="radio" name="AllMed" id="AllMed" value="AllMedNo" disabled="disabled"/> &nbsp;No&nbsp;</td><td  class="tbcellBorder">
	    	<div id="AllMedDiv" style="display:none">Specify:<input type="text" name="AllMedRemrk" id="AllMedRemrk" maxlength="4000"  title="Remark" style="width:70%" disabled="disabled"/></div>
			</td></tr>
			<tr><td class="labelheading1 tbcellCss" nowrap="nowrap" width="20%">
	     &nbsp;a.<b>Allergic to Substance other than medicine </b></td><td class="tbcellBorder" nowrap="nowrap"><input type="radio" name="AllSub" id="AllSub" value="AllSubYes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
	     <td class="tbcellBorder" > <input type="radio" name="AllSub" id="AllSub" value="AllSubNo" disabled="disabled"/> &nbsp;No&nbsp;</td><td  class="tbcellBorder">
	    	<div id="AllSubDiv" style="display:none">Specify:<input type="text" name="AllSubRemrk" id="AllSubRemrk" maxlength="4000" title="Remark" style="width:70%" disabled="disabled"/></div>
			</td></tr></table>			
			</c:if>
</c:if>
<c:if test="${!fn:contains(perHisList,sample[loop1.index].ID) }" >
<c:if test="${subLstId!='PR6.2'}">
<!-- <input type="checkbox" value="${sample[loop1.index].VALUE}" disabled="disabled"/>&nbsp; -->
<c:if test="${subLstId=='PR6.1'}">
<c:if test="${!fn:contains(perHisList,'PR6.2') }" >
No<input type="checkbox" value="${sample[loop1.index].VALUE}" disabled="disabled"/>&nbsp;
</c:if>
</c:if>
</c:if>
</c:if>
<c:if test="${!fn:contains(perHisList,sample[loop1.index].ID) }" >
<c:if test="${subLstId!='PR5.2'}">
<!--<input type="checkbox" value="${sample[loop1.index].VALUE}" disabled="disabled"/>&nbsp; -->
<c:if test="${subLstId=='PR5.1'}">
<c:if test="${!fn:contains(perHisList,'PR5.2') }" >
No<input type="checkbox" value="${sample[loop1.index].VALUE}" disabled="disabled"/>&nbsp;
</c:if>
</c:if>
</c:if>
</c:if>
</td>
</c:forEach>
</tr>
</c:forEach>
<!--  <tr>
<c:forEach  items="${PatientOpList.lstPersonalHistory}" varStatus="loop">	
<c:set value="${PatientOpList.lstPersonalHistory[loop.index].VALUE}" var="sample"></c:set>	
<c:forTokens var="tokenName" items="${sample}" delims="^" varStatus="status" begin="0">
<c:forTokens var="tokenName1" items="${tokenName}" delims="~" varStatus="status" begin="0" >
&nbsp;&nbsp;<c:out value="${tokenName1}"/>
&nbsp;&nbsp;<input type="checkbox" value="${tokenName1}"/>
</c:forTokens>
</c:forTokens>
<br>
</c:forEach>
</tr>-->
</table>

<!-- Family History -->
<table width="100%">
<tr><td class="tbheader">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Family History</b></td></tr>
</table> 
<table border="0" width="100%" align=center >
<tr><td align="center">
<c:set var="loopCount2" value="0" />
<c:forEach  items="${familyHistoryList}" varStatus="loop">								
<c:set value="${familyHistoryList[loop.index].ID}" var="sample"></c:set>	
<c:forTokens var="tokenName" items="${PatientOpList.familyHis}" delims="~" varStatus="status" begin="0">
<c:choose>
<c:when test="${tokenName == sample}">	
<c:set var="loopCount2" value="${loopCount2 + 1}" /> 
<c:if test="${(loopCount2 % 4) eq 1}">
	<tr>
	</c:if>		
<td class="tbcellBorder" >
<c:out value="${familyHistoryList[loop.index].VALUE}"/>
<c:if test="${tokenName == 'FH11' }">
${PatientOpList.familyHistoryOthr}
</c:if>
</c:when>
</c:choose>  
</c:forTokens>     	
</c:forEach>
</td></tr>
<tr><td align="center">
<c:if test="${fn:length(PatientOpList.familyHis) eq 0 }">
Family history not found
</c:if>
</td></tr>
</table>

<!-- General Examination Findings -->
<table width="100%">
<tr><td class="tbheader">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>General Examination Findings</b></td></tr>
</table>
 
 <table border="0" width="100%" align=center>
 <tr>
 <td class="labelheading1 tbcellCss" width="20%"><b>Height</b></td>
 <td  class="tbcellBorder" width="30%">${PatientOpList.height}</td>
 <td class="labelheading1 tbcellCss" width="20%"><b>Weight</b></td>
 <td  class="tbcellBorder" width="30%">${PatientOpList.weight}</td>
</tr>
<tr>
<td class="labelheading1 tbcellCss" width="20%"> <b>BMI</b></td>
 <td  class="tbcellBorder" width="30%">${PatientOpList.bmi}</td>
  <td class="labelheading1 tbcellCss" width="10%"><b>Pallor</b></td>
 <td  class="tbcellBorder">
 <c:choose>
	 <c:when test="${PatientOpList.pallor=='Y'}">
	  <input type="checkbox" name="PallorChkBox" value="Y" checked="checked" disabled="disabled">Yes
	 <input type="checkbox" name="PallorChkBox" value="N" disabled="disabled">No
	 </c:when>
	  <c:when test="${PatientOpList.pallor=='N'}">
	  <input type="checkbox" name="PallorChkBox" value="Y" disabled="disabled">Yes
	 <input type="checkbox" name="PallorChkBox" value="N" checked="checked" disabled="disabled">No
	 </c:when>
	 <c:otherwise>
	  <input type="checkbox" name="PallorChkBox" value="Y" disabled="disabled">Yes
	 <input type="checkbox" name="PallorChkBox" value="N" disabled="disabled">No
	 </c:otherwise>
 </c:choose>
 </td>
</tr>
<tr> 
 <td class="labelheading1 tbcellCss" width="20%"><b>Cyanosis</b></td>
 <td  class="tbcellBorder" width="30%">
  <c:choose>
	 <c:when test="${PatientOpList.cyanosis=='Y'}">
	 <input type="checkbox" name="CyanosisChkBox" value="Y" checked="checked" disabled="disabled">Yes
	 <input type="checkbox" name="CyanosisChkBox" value="N" disabled="disabled"> No
	 </c:when>
	 <c:when test="${PatientOpList.cyanosis=='N'}">
	 <input type="checkbox" name="CyanosisChkBox" value="Y" disabled="disabled">Yes
	 <input type="checkbox" name="CyanosisChkBox" value="N" checked="checked" disabled="disabled">No
	 </c:when>
	 <c:otherwise>
	 <input type="checkbox" name="CyanosisChkBox" value="Y" disabled="disabled">Yes
	 <input type="checkbox" name="CyanosisChkBox" value="N" disabled="disabled">No
	 </c:otherwise>
 </c:choose>
 </td>
 <td class="labelheading1 tbcellCss" width="20%"><b>Clubbing of Fingers/Toes</b></td>
  <td  class="tbcellBorder" width="30%">
  <c:choose>
	  <c:when test="${PatientOpList.clubbingOfFingers=='Y'}">
	 <input type="checkbox" name="ClubbingChkBox" value="Y" checked="checked" disabled="disabled">Yes
	 <input type="checkbox" name="ClubbingChkBox" value="N" disabled="disabled" >No
	 </c:when>
	   <c:when test="${PatientOpList.clubbingOfFingers=='N'}">
	 <input type="checkbox" name="ClubbingChkBox" value="Y" disabled="disabled">Yes
	 <input type="checkbox" name="ClubbingChkBox" value="N" checked="checked" disabled="disabled">No
	 </c:when>
	 <c:otherwise>
	 <input type="checkbox" name="ClubbingChkBox" value="Y" disabled="disabled">Yes
	 <input type="checkbox" name="ClubbingChkBox" value="N" disabled="disabled">No
	 </c:otherwise>
 </c:choose>
 </td>
</tr>
<tr>
 <td class="labelheading1 tbcellCss" width="20%"><b>Lymphadenopathy</b></td>
 <td  class="tbcellBorder" width="30%">
  <c:choose>
	  <c:when test="${PatientOpList.lymphadenopathy=='Y'}">
	 <input type="checkbox" name="LymphadenopathyChkBox" value="Y" checked="checked" disabled="disabled">Yes
	 <input type="checkbox" name="LymphadenopathyChkBox" value="N" disabled="disabled">No
	 </c:when>
	 <c:when test="${PatientOpList.lymphadenopathy=='N'}">
	 <input type="checkbox" name="LymphadenopathyChkBox" value="Y" disabled="disabled">Yes
	 <input type="checkbox" name="LymphadenopathyChkBox" value="N" checked="checked" disabled="disabled">No
	 </c:when>
	 <c:otherwise>
	 <input type="checkbox" name="LymphadenopathyChkBox" value="Y" disabled="disabled">Yes
	 <input type="checkbox" name="LymphadenopathyChkBox" value="N" disabled="disabled">No
	 </c:otherwise>
 </c:choose>
 </td>
 <td class="labelheading1 tbcellCss" width="20%"><b>Edema of Feet</b> </td>
  <td  class="tbcellBorder" width="30%">
  <c:choose>
	  <c:when test="${PatientOpList.edema=='Y'}">
	 <input type="checkbox" name="EdemaChkBox" value="Y" checked="checked" disabled="disabled">Yes
	 <input type="checkbox" name="EdemaChkBox" value="N" disabled="disabled">No
	 </c:when>
	 <c:when test="${PatientOpList.edema=='N'}">
	 <input type="checkbox" name="EdemaChkBox" value="Y" disabled="disabled">Yes
	 <input type="checkbox" name="EdemaChkBox" value="N" checked="checked" disabled="disabled">No
	 </c:when>
	 <c:otherwise>
	 <input type="checkbox" name="EdemaChkBox" value="Y" disabled="disabled">Yes
	 <input type="checkbox" name="EdemaChkBox" value="N" disabled="disabled">No
	 </c:otherwise>
 </c:choose>
 </td>
  </tr>
 <tr>
 <td class="labelheading1 tbcellCss" width="12%"><b>Malnutrition</b></td>
 <td  class="tbcellBorder">
 <c:choose>
	 <c:when  test="${PatientOpList.malNutrition=='Y'}">
	 <input type="checkbox" name="MalnutritionChkBox" value="Y" checked="checked" disabled="disabled">Yes
	 <input type="checkbox" name="MalnutritionChkBox" value="N" disabled="disabled">No
	 </c:when>
	 <c:when test="${PatientOpList.malNutrition=='N'}">
	 <input type="checkbox" name="MalnutritionChkBox" value="Y" disabled="disabled">Yes
	 <input type="checkbox" name="MalnutritionChkBox" value="N" checked="checked" disabled="disabled">No
	 </c:when>
	 <c:otherwise>
	  <input type="checkbox" name="MalnutritionChkBox" value="Y" disabled="disabled">Yes
	 <input type="checkbox" name="MalnutritionChkBox" value="N" disabled="disabled">No
	 </c:otherwise>
 </c:choose>
 </td>
 <td class="labelheading1 tbcellCss" width="20%"><b>Dehydration</b></td>
 <td  class="tbcellBorder">
 <c:choose>
	 <c:when  test="${PatientOpList.dehydration=='Y'}">
		 <input type="checkbox" name="DehydrationChkBox" value="Y" checked="checked" disabled="disabled">Yes (${ PatientOpList.dehydrationType})
		 <input type="checkbox" name="DehydrationChkBox" value="N" disabled="disabled">No
		
	 </c:when>
	 
	 <c:when test="${PatientOpList.dehydration=='N'}">
	 <input type="checkbox" name="DehydrationChkBox" value="Y" disabled="disabled">Yes
	 <input type="checkbox" name="DehydrationChkBox" value="N" checked="checked" disabled="disabled">No
	 </c:when>
	 <c:otherwise>
	  <input type="checkbox" name="DehydrationChkBox" value="Y" disabled="disabled">Yes
	 <input type="checkbox" name="DehydrationChkBox" value="N" disabled="disabled">No
	 </c:otherwise>
 </c:choose>
 </td>
 </tr>
<tr>
 <td class="labelheading1 tbcellCss" width="12%"><b>Temperature</b> </td>
 <td  class="tbcellBorder">${PatientOpList.temperature}</td> 

 <td class="labelheading1 tbcellCss" width="12%"><b>Pulse Rate per Minute</b></td>
 <td  class="tbcellBorder">${PatientOpList.pulseRate}</td> 
 </tr>
 <tr>
 <td class="labelheading1 tbcellCss" width="10%"><b>Respiration Rate</b></td>
 <td  class="tbcellBorder">${PatientOpList.respirationRate}</td>

 <td class="labelheading1 tbcellCss" width="12%"><b>BP Lt. Arm</b> </td>
 <td  class="tbcellBorder">${PatientOpList.bpLmt}</td>
  </tr>
<tr>
 <td class="labelheading1 tbcellCss" width="12%"><b>BP Rt. Arm</b></td>
 <td  class="tbcellBorder">${PatientOpList.bpRmt}</td>
</tr>
 
 <!-- Systematic Examination Findings-->
<tr><td class="tbheader" colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Systematic Examination Findings</b></td></tr>

 <tr><td colspan="4">
<table width="100%" border="0">
 <tr>  
      	<td width="5%" class="labelheading1 tbcellCss"><b>S.No</b></td>        
        <td width="35%" class="labelheading1 tbcellCss"><b>Main Symptom Name</b></td>   
       	<td width="30%" class="labelheading1 tbcellCss"><b>Sub Symptom Name</b></td>
        <td width="30%" class="labelheading1 tbcellCss"><b>Symptom Name</b></td>
 </tr>
 <%int i=1; %>
<c:forEach items="${symptomsList}" var="element"> 
  <tr>
    <td width="5%" class="tbcellBorder"><%=i++ %></td>
    <td width="35%" class="tbcellBorder">${element.ID}</td>
    <td width="30%" class="tbcellBorder">${element.SUBID}</td>
    <td width="30%" class="tbcellBorder">${element.VALUE}</td>
  </tr>
</c:forEach>  
</table>
</td></tr>
<!-- 
<tr>
<td class="labelheading1 tbcellCss" width="10%"><b>Main Symptom Name</b></td>
<td  class="tbcellBorder">${PatientOpList.mainSymName}</td>
<td class="labelheading1 tbcellCss" width="12%"><b>Main Symptom Code</b></td>
<td  class="tbcellBorder">${PatientOpList.mainSymCode}</td>
</tr> 
<tr>
<td class="labelheading1 tbcellCss" width="12%"><b>Symptom Name</b></td>
<td  class="tbcellBorder">${PatientOpList.symName}</td>
<td class="labelheading1 tbcellCss" width="10%"><b>Symptom Code</b></td>
<td  class="tbcellBorder">${PatientOpList.symCode}</td>
</tr>-->
<tr>
<td class="labelheading1 tbcellCss" width="12%"><b>Investigations</b></td>
<td  class="tbcellBorder">${PatientOpList.investRemarks}</td>
<td class="labelheading1 tbcellCss" width="12%"><b>Patient Diagnosed By</b></td>
<td  class="tbcellBorder">${PatientOpList.docType}</td>
</tr>
<tr>
<td class="labelheading1 tbcellCss" width="10%"><b>Doctor Name</b></td>
<td  class="tbcellBorder">${PatientOpList.docName}</td> 
<!--<td class="labelheading1 tbcellCss" width="12%"><b>Registration No.</b></td>
<td  class="tbcellBorder">${PatientOpList.docReg}</td>
</tr><tr>
<td class="labelheading1 tbcellCss" width="12%"><b>Qualification</b></td>
<td  class="tbcellBorder">${PatientOpList.docQual}</td>
<td class="labelheading1 tbcellCss" width="10%"><b>Mobile No.</b></td>
<td  class="tbcellBorder">${PatientOpList.docMobNo}</td> 
</tr>
<tr>-->
<td class="labelheading1 tbcellCss" width="12%"><b>Patient Type</b></td>
<td  class="tbcellBorder">${PatientOpList.patientType}</td> 
</tr>
<tr>
	<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" width="15%">
		<b>Medico Legal case</b>
	</td>
	<td colspan="1" align="left" class="tbcellBorder print_cell" width="10%">
	<c:if test="${PatientOpList.legalCaseCheck eq 'Y'}">
		Yes
	</c:if>
	<c:if test="${PatientOpList.legalCaseCheck eq 'N'}">
		No					</c:if>
	</td>
	<c:if test="${PatientOpList.legalCaseCheck eq 'Y'}">
		<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" width="10%">
			<b>Case No</b>
		</td>
		<td colspan="1" align="left" class="tbcellBorder print_cell" width="20%">
			${PatientOpList.legalCaseNo}
		</td>
		</tr>
		<tr>	
		<td colspan="1" align="left" class="labelheading1 tbcellCss print_cell" width="20%">
			<b>Police Station</b>
		</td>
		<td colspan="1" align="left" class="tbcellBorder print_cell" width="25%">
			${PatientOpList.policeStatName}
		</td>
		<td colspan="2">&nbsp;</td>
	</c:if>
</tr>
</table>

<div class="modal fade col-lg-12 col-md-12 col-sm-12 col-xs-12" id="healthCardModal" style="">
<div class="modal-dialog" id="modal-1gx">
 <div class="modal-content" align="center">
 <div class="modal-header" style="height: 30px;color:#fff;">
<label>Patient Health Card</label>
</div>
 <div class="modal-body" style="height: 410px;font-family:Times New Roman; font-size:3.1mm; font-weight:normal;">
<table>
<tr valign="top" >
<td  style="border:2px solid gray;background-color: white;width:255px;">
<table align="center" style="padding-top: 2px; background-color: white;width:100%" >
<tr><td colspan="3" style="width:100%" align="center"><img src="images/TG_logo.png" width="17%" ></td></tr>
<tr>
				<td width="17%"></td>
				<td nowrap="nowrap" align="left" width="40%" style="text-align: center;"><b><span style="font-size:10px;">Government of Telangana</span><br>
					<span class="ehsScheme" style="display:none;">Employees Health Scheme</span>
					<span class="jrnlstScheme" style="display:none;">Working & Retired Journalist Health Scheme</span>
					</b></td>
					<td width="15%"></td>
					
					<tr align="center">
				<td colspan="3"><b><u>HEALTH CARD</u></b></td>
					<tr align="center" >
				<td colspan="3"><b><span  id="cardNoSpan"></span></b></td>
			</tr>	
			
</table>
<table style="padding-left: 10px;  background-color: white; width: 245px;auto;">
			<tr>
				<td style="word-break:break-all;" colspan="3"><b><span  id="nameSpan"></span></b>,&nbsp;
			<b>
<span  id="genderSpan">Male</span>
</b></td>
</tr>
<tr align="left" style="height: 10px;padding-top: 5px;">

				<td valign="top" colspan="4" ><b>DOB &nbsp;: <span  id="dobSpan"></span>,&nbsp;<span id="relationSpan"></span></b></td>
</tr>

<tr align="center">
<td colspan="4"><br>
 <c:choose>
 <c:when test="${patOnBedPic != null && patOnBedPic != 'N'}" >
	<img src="common/showDocument.jsp" width="110" height="90" alt="NO DATA" align="center" id="patImage" 
  onmouseover="javascript:resizePatImage('patImage','140','110')" onmouseout="resizePatImage('patImage','110','90')" />
 </c:when>
  <c:otherwise>
 
<img src="images/photonot.gif" width="110" height="90" alt="NO DATA" align="center"/> 
  
  </c:otherwise>
 </c:choose>

</td>
</tr>
</table>
<table align="center" style="padding-left: 10px; background-color: white; width: 245px;auto;break-word;">
			<tr align="left" style="height: 70px" valign="top" >
			<td style="word-break:break-all;"> <b>Address :</b>  <span id="addressSpan"></span>
</td>
</tr>
<tr align="left"  >
<td style="padding-top: 10px" style="word-break:break-all;"> 
<b><span id="empNameLabelSpan"></span> Name : </b><span id="empNameSpan"></span>
</td>
</tr>

<tr  align="left">
<td style="padding-bottom: 4px"><b>
<b><span id="empIdLabelSpan"></span> ID No : </b><span id="empIdSpan"></span>
</td>




<tr align="left" class="ehsScheme" style="display:none;"><td valign="top" align="left" ><b>Aadhar ID/Aadhar Enrollment ID :</b></td></tr>
<tr class="ehsScheme" style="display:none;"><td><span id="aadhaarSpan"></span>&nbsp;</td></tr>
<tr class="jrnlstScheme" style="display:none;"><td><b>Designation :</b> Journalist</td></tr>
<tr class="jrnlstScheme" style="display:none;"><td><b>Organisation :</b> Press</td></tr>
</table>
</td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td class="jrnlstScheme" style="border:2px solid gray;background-color: white;width:255px; padding-bottom: 8px">
<table align="center" class="jrnlstScheme" style="padding-top: 2px; width:100%;height:60%;display:none;" >
	<tr><td align="left">Valid till 31<span style="vertical-align: super;">st</span> Dec, <script type="text/javascript">var year = new Date();document.write(year.getFullYear());</script></td></tr>
	<tr><td align="left"><b>ID Type &nbsp; :</b>&nbsp;&nbsp;<span id="idTypeSpan"></span></td> </tr>
	<tr><td align="left"><b>ID Number &nbsp; :</b>&nbsp;&nbsp;<span id="idNumberSpan"></span></td> </tr>
	<tr><td align="left"><b>Accreditation No &nbsp; :</b>&nbsp;&nbsp;<span id="accNoSpan"></span></td> </tr>
	<tr><td align="left"><b>Mobile No  &nbsp;:</b>&nbsp;&nbsp;<span id="mobileNoSpan"></span></td> </tr>
	<tr><td align="left"><b>E-mail  &nbsp;&nbsp;:</b>&nbsp;&nbsp;<span id="emailSpan" style="text-transform: lowercase;"></span></td> </tr>
</table>
<table align="center" style="padding-top: 220px;background-color: white;  width: 245px;">
<tr style="height: 80%" ><td colspan="2"></td></tr>			
<!-- <tr class="ehsScheme" style="display:none;"><td valign="top" nowrap="nowrap" align="left" ><b>Issued By  </b><br>Collector & District Magistrate,<br><span id="ddoDistSpan"></span>&nbsp;District</td></tr> -->
<tr><td align="left">Issued By : </td></tr>
<tr><td align="left"><b>Commissioner, Information & Public Relation,</b></td> </tr>
<tr><td align="left"><b>Govt. of Telangana,Hyderabad.</b></td> </tr>
<tr style="padding-bottom: 55px" ><!--<td valign="top" nowrap="nowrap" align="left" ><b><fmt:message key="label.EHF.Card.valid"/>&nbsp; : &nbsp;05-03-2014&nbsp;<fmt:message key="label.EHF.Card.Till"/></b></td>--></tr>
</table>
</td>

<td class="ehsScheme" style="border:2px solid gray;background-color: white;width:255px; padding-bottom: 8px" valign="bottom" >

<table align="center" 
			style="padding-top: 15px; background-color: white;  width: 235px;">
<tr style="height: 80%" ><td colspan="2"></td></tr>			
<tr class="ehsScheme" style="display:none;"><td valign="top" nowrap="nowrap" align="left" ><b>Issued By  </b><br>Collector & District Magistrate,<br><span id="ddoDistSpan"></span>&nbsp;District</td></tr>
<tr style="padding-bottom: 55px" ><!--<td valign="top" nowrap="nowrap" align="left" ><b><fmt:message key="label.EHF.Card.valid"/>&nbsp; : &nbsp;05-03-2014&nbsp;<fmt:message key="label.EHF.Card.Till"/></b></td>--></tr>
		
		</table>
</td>


</tr>
</table>


 </div>
  <div class="modal-footer">
 <button type="button" class="btn btn-warning" data-dismiss="modal" onclick="">Close</button>
 </div>
 </div></div></div>
<script type="text/javascript">
var addition='${test}';var additionKnown='${testKnown}';
if(document.getElementById("habitsTd") != null)
document.getElementById("habitsTd").style.display='block';
addition=addition.replace("PR6.1(","");
additionKnown=additionKnown.replace("PR5.1,","");
var additionList = addition.split(",");
var addKnownList = additionKnown.split(",");
if(addKnownList.length>0){
	for(var i = 0; i<addKnownList.length;i++)
    {	    
		var addtn = addKnownList[i].split("$");
		if(addtn[0]=='AllMed'){
			var spitedY = addtn[1].split("(");	
			if(spitedY[0]=='AllMedYes'){
				
				document.forms[0].AllMed[0].checked='checked';
				document.getElementById("AllMedDiv").style.display='block';
				var valueY = addtn[1].split("@");
				document.getElementById("AllMedRemrk").value=valueY[1];
			}
			else if(addtn[1]=='AllMedNo'){
				document.forms[0].AllMed[1].checked='checked';
		}
	   }
		if(addtn[0]=='AllSub'){
			var spitedY = addtn[1].split("(");	
			if(spitedY[0]=='AllSubYes'){
				
				document.forms[0].AllSub[0].checked='checked';
				document.getElementById("AllSubDiv").style.display='block';
				var valueY = addtn[1].split("@");
				document.getElementById("AllSubRemrk").value=valueY[1];
			}
			else if(addtn[1]=='AllSubNo'){
				document.forms[0].AllSub[1].checked='checked';
		}
	   }
}
}
if(additionList.length>0)
{
	for(var i = 0; i<additionList.length;i++)
    {	
	    var addtn = additionList[i].split("$");
	    if(addtn[0]=='Alcohol')
	    	{if(addtn[1]=='Regular')
	    		document.forms[0].alcohol[0].checked='checked';
	    	else if (addtn[1]=='Occasional')
	    		document.forms[0].alcohol[1].checked='checked';
	    	else if (addtn[1]=='Teetotaler')
	    		document.forms[0].alcohol[2].checked='checked';
	    	}
	    else if(addtn[0]=='Tobacco')
		    {
	    	var tabacoLst = addtn[1].split("(");
	    	
	    	if(tabacoLst[0]=='Snuff')
	    		document.forms[0].tobacco[0].checked='checked';
	    	else if (tabacoLst[0]=='Chewable')
	    		document.forms[0].tobacco[1].checked='checked';
	    	else if (tabacoLst[0]=='Smoking')
		    	{
	    		document.forms[0].tobacco[2].checked='checked';
	    		tabacoLst[1] = tabacoLst[1].replace(")","");
	    		
	    		document.getElementById("smokingTd").style.display='block';
	    		
	    		var smokSub = tabacoLst[1].split("*");
	    	
	    		if(smokSub.length>0)
		    		{
                       for(var j=0;j<smokSub.length;j++){
                    	   
                    	  var smokeVal= smokSub[j].split("@");
                    	  
                    	  if(smokeVal[0]=='PackNo')
                    		  document.forms[0].packNo.value=smokeVal[1];
                    	  else
                    		  document.forms[0].smokeYears.value=smokeVal[1];
                           } 
		    		}
		    	}
             }
	    else if(addtn[0]=='DrugUse')
		    {
              if(addtn[1]=='Yes')
            	  document.forms[0].drugUse[0].checked='checked';
              else  if(addtn[1]=='No')
            	  document.forms[0].drugUse[1].checked='checked';
            }
	    else if(addtn[0]=='BetelNut')
	    {
	    	if(addtn[1]=='Yes')
          	  document.forms[0].betelNut[0].checked='checked';
            else  if(addtn[1]=='No')
          	  document.forms[0].betelNut[1].checked='checked';
	    }
	    else if(addtn[0]=='BetelLeaf')
	    {
	    	addtn[1] = addtn[1].replace(")","");
	    	if(addtn[1]=='Yes')
          	  document.forms[0].betelLeaf[0].checked='checked';
            else  if(addtn[1]=='No')
          	  document.forms[0].betelLeaf[1].checked='checked';
	    }
    }
}
</script>

<!--Start of PatientDetails -->

<!-- End of Grievance -->
<input type=hidden name=requestType>
<input type=hidden name=actionVal>
<input type=hidden name=invId>
</form>
</body>
</html>