<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ include file="/common/include.jsp"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css"    media="screen"> 
<link href="css/themes/<%=themeColour%>/accountstyle.css" rel="stylesheet" type="text/css" />
<script src="bootstrap/js/respond.min.js"></script>
<script src="js/jquery-1.9.1.min.js"></script>
 <script src="/ChangeMgmt/cmsScripts.js" type=text/javascript></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootbox.js"></script>
 <script src="js/jquery.msgBox.js" type="text/javascript"></script>
 <%@ include file="/common/includeScrollbar.jsp"%>
<title>Change Request Details</title>
<style>
body {
	font-size: 1.2em !important;
	font-family:Arial;
}

.
.pagination {
	display: none;
}

.bottom-margin {
	margin: 0px 0px 3px 0px !important;
}

.marginTop {
	margin-top: 3px
}

.but {
	border-radius: 16px;
}

.text-active,.active .text {
	display: none !important;
}

.mainDiv {
	width: 96%;
	float: right;
	padding-right: 8%;
}

.active .text-active {
	display: inline-block !important;
}

.centerone {
	padding-left: 5%;
}

.rightone {
	padding-right: 3%;
}
</style>
 <style type="text/css">
 
 
 
 *
 {
 
 font-size:13px;
 }
    .bottom-margin {
    margin: 0px 0px 3px 0px !important;
    
    
}
	#remarksDiv{
width:100%;
height:540px;
overflow:auto;
background:white;
}
</style>

<title>Change Management System-Only View Request </title>
<script language="javascript" type="text/javascript">

(function($){
	   
	$(window).load(function(){
		$("#remarksDiv").mCustomScrollbar({
			theme:"dark",
			advanced:{  
		        updateOnBrowserResize:true,   
		        updateOnContentResize:true 
		      },
		      callbacks:{
				onScroll:function(){ 
					$(".output .content-position").text(mcs.top);	
					elemJqueryScrollTop= mcs.top ;
					elemJqueryScrollTop =(-elemJqueryScrollTop);
				}
			}  
			});
		
		$("#remarksDiv").hover(function(){
			$(document).data({"keyboard-input":"enabled"});
			$(this).addClass("keyboard-input");
		},function(){
			$(document).data({"keyboard-input":"disabled"});
			$(this).removeClass("keyboard-input");
		});
		$(document).keydown(function(e){
			if($(this).data("keyboard-input")==="enabled"){
				var activeElem=$(".keyboard-input"),
					activeElemPos=Math.abs($(".keyboard-input .mCSB_container").position().top),
					pixelsToScroll=60;
				if(e.which===38){ //scroll up
					e.preventDefault();
					if(pixelsToScroll>activeElemPos){
						activeElem.mCustomScrollbar("scrollTo","top");
					}else{
						activeElem.mCustomScrollbar("scrollTo",(activeElemPos-pixelsToScroll),{scrollInertia:400,scrollEasing:"easeOutCirc"});
					}
				}else if(e.which===40){ //scroll down
					e.preventDefault();
					activeElem.mCustomScrollbar("scrollTo",(activeElemPos+pixelsToScroll),{scrollInertia:400,scrollEasing:"easeOutCirc"});
				}
			}
		});
	});
})(jQuery); 

isLarge = false;
function expandOrCollapse(){
	    $("#remarksDiv").animate({height:(isLarge ? '0.1em' : '10em')});
	   isLarge = !isLarge; 
	}
function fn_saveRow(lStrCrId,button)
{
	var vMenu = $('#menu').val();
	var cnfmMsg=confirm("Do you want to "+button);
	if(cnfmMsg){
		document.forms[0].action = "ceoWorklist.do?flag=requestApprove&btnType="+button+"&crIdList="+lStrCrId+"&menu="+vMenu;
		document.forms[0].submit();	
		document.forms[0].approveBtn.disabled = true;	
		document.forms[0].rejectBtn.disabled = true;	
	}
}
function fn_PendingAprvl()
{
	
	 document.forms[0].action = "ceoWorklist.do?flag=pendingApprvls";
	 document.forms[0].submit();	
	
}

function fn_crRqstAction(arg){
	
	var a="${crId}";
	document.forms[0].action = "ceoWorklist.do?flag=requestApprove&btnType="+arg+"&crIdList="+a;
	
		document.forms[0].submit();	
		
	
	
	
}

	
function fn_viewAttach(crIdView,lineItemNo)
{
    var url="ceoWorklist.do?flag=viewAttachment&crIdView="+crIdView+"&lineItemNo="+lineItemNo;
    window.open(url,"new","toolbar=no,resizable=no,scrollbar=yes,menubar=no,location=no,height=250,width=600,left=50,right=50,top=200,bottom=200");
}
</script>
</head>
<body>
<html:form action="/ceoWorklist.do" method="POST">
<logic:empty name="ceoApprovalsForm" property="msg">
<div class="container mainDiv" style="width:91%;">
    <table class="tbheader" style="width:100%;margin:0 auto;">   
    <tbody>
   <tr>
   <td align="center" style="height:2em;width:90%">
						<b>Change Requests</b>
						</td>
	 <td align="right" style="display:block">
	 <a id="backLink" class="tbcellCss" href="#" onclick="javascript:fn_PendingAprvl();" style="font-weight: bold; color:black; text-decoration:none; width:10%;">
	 Back</a></td></tr></tbody></table>
    
         
         <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group tbcellCss" >

        <label class="labelheading1">Department:&nbsp;</label>

    <bean:write property="sourceDeptName" name="ceoApprovalsForm"/>            
        </div>
         
         
           <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group  tbcellCss" >

        <label class="labelheading1">CR Type :&nbsp;</label>

     <bean:write name="ceoApprovalsForm" property="crType"/>         
        </div>
         
         
             <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group tbcellCss" >

        <label class="labelheading1">CR ID :&nbsp;</label>

     <bean:write name="ceoApprovalsForm" property="crId"/>   
        </div>
         
         
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group  tbcellCss" >

        <label class="labelheading1">CR Title :&nbsp;</label>

    
            <bean:write name="ceoApprovalsForm" property="crTitle"/> 
        </div>
         
         
                   <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group  tbcellCss" >

        <label class="labelheading1">CR Description :&nbsp;</label>

    
            <bean:write name="ceoApprovalsForm" property="crDesc"/> 
        </div>
         
		 
         
      
  <table class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
  <tr>
  <td class="tbheader" colspan="2"><b>Attachments</b></td></tr>
   <c:if test="${showFiles eq 'Y'}">
 
  <tr><td width="80%" class="labelheading1 tbcellCss" align="center">
   <c:forEach var="fileList" items="${attachVOList}">
 <a href="#" onclick="javascript:fn_viewAttach('${fileList.crId}','${fileList.lineItemNo}')" id='${fileList.attachFileName}'><span title="Click to view attachment" class="labelheading1" style="font-size: 1.25em;">${fileList.attachFileName}</span></a>
 <span>&nbsp;&nbsp;</span>
   </c:forEach> 
 </td>
 </tr>
 
  </c:if> 
  <c:if test="${showFiles eq 'N'}">
  <tr><td align="center"><b>No attachments found</b></td></tr>
  </c:if>   
  </table>	
        
              
   		  
        <logic:equal value="Y" name="ceoApprovalsForm" property="saveRemarksFlag">
        <td colspan="6">
            <table border=0 align="center"  width="100%"> 
             <tr>
                <TD class="labelheading1 tbcellCss" style="text-align: left" width="18%"><b>Remarks&nbsp;</b><span style="clor:red">*</span></TD>
                <TD class="tbcellBorder" style="text-align: left;" width="79%" height="14">
                  <html:textarea rows="2" cols="80"  name="ceoApprovalsForm" property="remarks" onblur="fn_maxlength(this,3000,\'remarks\')"/>
                </TD>       
             </tr>
            </table>
            </td>
        </logic:equal>
    
       

     <!-- For Sign Off Remarks-->       
   <tr><td>
    
   <div id="remarksDiv"  style="overflow:auto;width:100%; height:185px;" > 
        <table style="margin:0 auto;width:90%" border="0" cellspacing="0" cellpadding="0" align ="center" >
        <logic:present name="ceoApprovalsForm" property="signOffActionRemarkList">
                <bean:size id="size2" name="ceoApprovalsForm" property="signOffActionRemarkList"/>
                <logic:greaterThan value="0" name="size2">
                <tr align="center">
                    <td colspan="4"  class="labelheading1 tbcellCss">Sign Off Remark Details</td>
                </tr>
                <tr><td>                
                    <table border=0 align="center" width="100%" cellSpacing="1" cellPadding="1">
                        <tr  align="center">
                        <td class=tbheader1>SNo</td>
                        <td class=tbheader1>Employee Code</td>
                        <td class=tbheader1>Action Taken By</td>
                        <td class=tbheader1>Designation</td>
                        <td class=tbheader1>Phone Number</td>
                        <td class=tbheader1>Remarks Given</td>
                        <td class=tbheader1>Action Taken Time</td>
                        <td class=tbheader1>Action Taken</td>
                        <td class=tbheader1>View Attachment(s)</td>
                        </tr>
                    <logic:iterate id="result" name="ceoApprovalsForm" property="signOffActionRemarkList" indexId="sno">
                        <tr  align="center">
                        <td  class="tbcellCss">&nbsp;<%=sno+1%></td>
                        <td  class="tbcellCss">&nbsp;<bean:write name="result" property="EMPCODE"/></td>
                        <td  class="tbcellCss">&nbsp;<bean:write name="result" property="EMP_FULL_NAME"/></td>
                        <td  class="tbcellCss">&nbsp;<bean:write name="result" property="DSGN_NAME"/></td>
                        <td class=tbcellCss>&nbsp;<bean:write name="result" property="PHONE_NUMBER"/></td>                        
                        <td class=tbcellCss>&nbsp;<bean:write name="result" property="CR_REMARKS"/></td>
                        <td class=tbcellCss>&nbsp;<bean:write name="result" property="REMARKS_ENTRYTIME"/></td>
                        <td class=tbcellCss>&nbsp;<bean:write name="result" property="CR_ACTION_TAKEN"/></td>
                        <logic:equal value="Y" name="result" property="ATTACH_EXISTS">
                            <td class=tbcellCss>&nbsp;
                                <a href="#" title="Please click here to view attachment..." onclick="javascript:viewRemarkWiseAttach('<bean:write property="CR_ID" name="result" />','<bean:write property="LINEITEMNO" name="result" />')" > View Attachments 
                                </a>
                            </td>
                        </logic:equal>
                        <logic:notEqual value="Y" name="result" property="ATTACH_EXISTS">
                            <td class=tbcellCss>&nbsp;-NA-</td>
                        </logic:notEqual>
                        </tr>
                    </logic:iterate>
                  </table>                 
                  </td></tr>
                </logic:greaterThan>
            </logic:present>   
            <tr><td>&nbsp;</td></tr>
<!--For Remarks-->
        
        <tr align="center">
            <td colspan="4"  class="tbheader">Remark Details</td>
        </tr>
        <tr><td>
        <logic:notPresent name="ceoApprovalsForm" property="otherActionRemarkList">
            <table width="40%" border="0" cellspacing="0" cellpadding="0" align ="center">
                 <tr><td colspan="3">&nbsp;</td> </tr>        
                 <tr><td colspan="3"><hr></td> </tr>        
                 <tr><td colspan="3">&nbsp;</td></tr>
                 <tr><td colspan="3"  align="center"><font class="resultmessage"><bean:message key="label.apps.noDocsMsg" /></font></td> </tr>     
                 <tr>  <td colspan="3">&nbsp;</td> </tr>
                 <tr>
                     <td colspan="3">&nbsp;</td>
                 </tr>
                 <tr>
                     <td colspan="3"><hr class="resultline"></td>
                 </tr>    
            </table>
        </logic:notPresent>
        <logic:present name="ceoApprovalsForm" property="otherActionRemarkList">
                <bean:size id="size1" name="ceoApprovalsForm" property="otherActionRemarkList"/>
                <logic:greaterThan value="0" name="size1">                                 
                    <table border=0 align="center" width="100%" cellSpacing="1" cellPadding="1">
                        <tr  align="center">
                        <td class=tbheader1>SNo</td>
                        <td class=tbheader1>Employee Code</td>
                        <td class=tbheader1>Action Taken By</td>
                        <td class=tbheader1>Designation</td>
                        <td class=tbheader1>Phone Number</td>
                        <td class=tbheader1>Remarks Given</td>
                        <td class=tbheader1>Action Taken Time</td>
                        <td class=tbheader1>Action Taken</td>
                        
                        </tr>
                    <logic:iterate id="result" name="ceoApprovalsForm" property="otherActionRemarkList" indexId="sno">
                        <tr align="center">
                        <td class=tbcellCss>&nbsp;<%=sno+1%></td>
                        <td class=tbcellCss>&nbsp;<bean:write name="result" property="EMPCODE"/></td>
                        <td class=tbcellCss>&nbsp;<bean:write name="result" property="EMP_FULL_NAME"/></td>
                        <td class=tbcellCss>&nbsp;<bean:write name="result" property="DSGN_NAME"/></td>
                        <td class=tbcellCss>&nbsp;<bean:write name="result" property="PHONE_NUMBER"/></td>                        
                        <td class=tbcellCss>&nbsp;<bean:write name="result" property="CR_REMARKS"/></td>
                        <td class=tbcellCss>&nbsp;<bean:write name="result" property="REMARKS_ENTRYTIME"/></td>
                        <td class=tbcellCss>&nbsp;<bean:write name="result" property="CR_ACTION_TAKEN"/></td>
                    </tr>
                    </logic:iterate>
                  </table>
                </logic:greaterThan>
            </logic:present>
        </td></tr>    
        
        
                            
            </table>
            </div>
            <div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12" align="center">
			<button class="btn but" id="approveBtn" type="button" value="Approve"  onClick="fn_crRqstAction('Approve')" ><span class="glyphicon glyphicon-ok"></span>&nbsp;Approve</button>
			<button class="btn but" id="rejectBtn" type="button" value="Reject"  onClick="fn_crRqstAction('Reject')" ><span class="glyphicon glyphicon-remove"></span>&nbsp;Reject</button>	
		</div>
            </td></tr></div></logic:empty>

<html:hidden name="ceoApprovalsForm" property="crId" styleId="crId"/>
<html:hidden name="ceoApprovalsForm" property="crAppName"/>
<html:hidden name="ceoApprovalsForm" property="crOrgName"/>
<html:hidden name="ceoApprovalsForm" property="crTitle"/>
<html:hidden name="ceoApprovalsForm" property="crReqTypeId"/>
<html:hidden name="ceoApprovalsForm" property="crType"/>
</html:form>
</body>
