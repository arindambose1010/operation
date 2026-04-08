<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net"  %>
<%@ include file="/common/include.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/jquery-1.9.1.js"></script>
  <%@ include file="/common/includeCalendar.jsp"%>
  <%@ include file="/common/editableComboBox.jsp"%>  
<script src="/<%=context%>/js/jquery.msgBox.js" type="text/javascript"></script>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css"    media="screen"> 
<title>View Sanction Status</title>
<style>
.ui-autocomplete-input {
    width: 17em;
}
#reqStatus-input{
 width: 200px;
}

input[type="text"] {
   height:100%;
}
#displayblock{
 display: none;
 height: 300px;
 overflow: hidden;
}
</style>
<script>var date = new Date();
var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();
$(function() {
$( "#reqRaisedDtFrm" ).datepicker({
	changeMonth: true,
	changeYear: true,
	showOn: "both", 
   	buttonImage: "images/calend.gif", 
    buttonText: "Calendar",
    buttonImageOnly: true ,
    dateFormat: "dd-mm-yy",
	yearRange: '2012:' + new Date().getFullYear()
	 ,
	onClose: function( selectedDate ) {
		$( "#reqRaisedDtTo" ).datepicker( "option", "minDate", selectedDate );
		},maxDate: new Date() 
});
$( "#reqRaisedDtTo" ).datepicker({
	changeMonth: true,
	changeYear: true,
	showOn: "both", 
    buttonImage: "images/calend.gif", 
    buttonText: "Calendar",
    dateFormat: "dd-mm-yy",
    buttonImageOnly: true ,
	yearRange: '2012:' + new Date().getFullYear() 
	 ,
	onClose: function( selectedDate ) {
		
		if(selectedDate == "" || selectedDate == null){
		$( "#reqRaisedDtFrm" ).datepicker( "option", "maxDate",  new Date() );
		}
		else
		$( "#reqRaisedDtFrm" ).datepicker( "option", "maxDate", selectedDate);
	} ,maxDate: new Date()
});
});

function eraser(arg1)
{ 
     document.getElementById(arg1).value="";   
}
function resetPage(){
	document.getElementById('sanctionId').value="";
	 document.getElementById('reqRaisedDtFrm').value="";
	 document.getElementById('reqRaisedDtTo').value="";
	 $('#reqStatus-input').val(''); document.getElementById('reqStatus').value="";
	 
}

function addTooltip(id)
{
var numOptions = document.getElementById(id).options.length;
for ( var i = 0; i < numOptions; i++)
	document.getElementById(id).options[i].title = document
					.getElementById(id).options[i].text;		

}
function fn_loadImage()
{
	document.getElementById('processImagetable').style.display="";
}
function fn_removeLoadingImage()
{   
	parent.fn_removeLoadingImage();
	document.getElementById('processImagetable').style.display="none";
}

function viewSancStatus(sanctionId){
	fn_loadImage();
	document.forms[0].action ='/<%=context%>/adminSanction.do?actionFlag=viewDetailsById&sanctionId='+sanctionId+'&printFlag=no';
	document.forms[0].submit();
}

function searchSancReq(){
	
	var sanctionId=document.getElementById('sanctionId').value;
	var strtdate=document.getElementById('reqRaisedDtFrm').value;
	var endDate=document.getElementById('reqRaisedDtTo').value;
	 var reqStatus= document.getElementById('reqStatus').value;
	
	if((sanctionId==null ||sanctionId=="")&&(strtdate==null ||strtdate=="")&&(endDate=="" || endDate==null)&&(reqStatus=="")){

		jqueryAlertMsgTop("Alert","Please select any search criteria");
		  return;
	}
	 
	if(((strtdate==null ||strtdate=="") && !(endDate=="" || endDate==null)) || (!(strtdate==null ||strtdate=="") && (endDate=="" || endDate==null)))
	{
		jqueryAlertMsgTop("Alert","Please select from date and to date");
	  return;
	}
	fn_loadImage();
	document.forms[0].action = '/<%=context%>/adminSanction.do?actionFlag=searchSancStatus&sanctionId='+sanctionId+'&reqStatus='+reqStatus+'&strtdate='+strtdate+'&endDate='+endDate;
	document.forms[0].submit();
}
function partial(func /*, 0..n args */) {
	var args = new Array();
	for ( var i = 1; i < arguments.length; i++) {
		args.push(arguments[i]);
	}
	return function() {
		var allArguments = args.concat(Array.prototype.slice
				.call(arguments));
		return func.apply(this, allArguments);
	};
}
function focusBox(arg) {

	aField = arg;
	setTimeout("aField.focus()", 0);

}
function focusFieldView(el)
{
var offset = $(el).offset();
var top = offset.top;
focusBox(el);
}
function chkSpecialChars(str){
	
	var dot=/^[\/]+$/;
var number=/^[0-9]+$/;
var pattern=/^[a-zA-Z0-9 \/]+$/;
	   var value= document.getElementById(str).value;
	   if (value != ""){ 
		   if(value.match(dot))
		   {
				var fr=partial(focusBox, document.getElementById(str));
				jqueryAlertMsgTop("Alert","Sanction ID cannot contain only Special character / ",fr);
			    document.getElementById(str).value="";
			return false;
		   }
		   
		   if(value.match(number))
		   {
			   var fr=partial(focusBox, document.getElementById(str));
				 jqueryAlertMsgTop("Alert","Please provide proper sanctionId, it cannot contain only numbers",fr);
				document.getElementById(str).value="";
				return false;
		   }
		   if(!value.match(pattern))
			   {
			   var fr=partial(focusBox, document.getElementById(str));
				jqueryAlertMsgTop("Alert","Sanction ID can accept only alpha numerical values and special character like / ",fr);
				document.getElementById(str).value="";
				return false;
			   
			   }
	   }
	   
return false;

}


</script>

</head>
<body onload="fn_removeLoadingImage();">
<html:form action="/adminSanction.do" method="POST" enctype="multipart/form-data">
<table id="Search" align="center"  width="90%" cellpadding="0" cellspacing="0" class="tb" style="padding-top:0px;margin:0px auto;">
						<tr class="HeaderRow">
                                  <td width="90%" class="tbheader"   align="left"><b>Admin Sanction Search</b></td>
					</tr>	
						<tr><td>
						<table id="table2" align="center"  width="100%" cellpadding="0" cellspacing="0"  class="tb"  >
						<tr class="NormalRow">
						<td></td>
						</tr>
						<tr class="NormalRow">
						<td></td>
						
						 <td style="padding-left:5px" class="labelheading1 tbcellCss">Sanction ID:</td>
						<td class="tbcellBorder"><html:text property="sanctionId"  name="adminSanctionForm" styleId ="sanctionId" title="Enter Sanction Id" style="width:220px" onchange="chkSpecialChars('sanctionId')"/></td> 
						<td style="padding-left:5px" class="labelheading1 tbcellCss">Status</td>
						<td class="tbcellBorder"><html:select property="reqStatus" name="adminSanctionForm" title="Sanction Request Status" onmouseover="addTooltip('reqStatus')" styleId="reqStatus">
							<option value="">--------Select---------</option>
							<logic:equal value="Initiate" name="adminSanctionForm" property="statusFlag"><html:option value="I">Initiated</html:option></logic:equal>
						<html:option value="A">Approved</html:option>
						<html:option value="P">In Process</html:option>
						<html:option value="R">Rejected</html:option>
						<html:option value="RA">Recommended for Approval to CEO</html:option>
						</html:select>
					   </td>
						
						
						</tr>
						<tr class="NormalRow">
						<td></td>
					    <td style="padding-left:5px" class="labelheading1 tbcellCss">Request Raised Date:FROM</td>
						<td class="tbcellBorder">
						<html:text style="width:160px" property="reqRaisedDtFrm" styleId="reqRaisedDtFrm" title="Request Raised Date:FROM" name="adminSanctionForm"  readonly="true"  />		 
						<img id="eraImg"  title="Click here to erase" src="/<%=context%>/images/erase37.jpg" alt="click to clear" onclick="javascript:eraser('reqRaisedDtFrm');" >
					    </td>
					   <td style="padding-left:5px" class="labelheading1 tbcellCss">Request Raised Date:TO</td>
						<td class="tbcellBorder">
						<html:text style="width:160px" property="reqRaisedDtTo" styleId="reqRaisedDtTo" name="adminSanctionForm" title="Request Raised Date:TO" readonly="true"  />		 
						<img id="eraImg"  title="Click here to erase" src="/<%=context%>/images/erase37.jpg" alt="click to clear" onclick="javascript:eraser('reqRaisedDtTo');" >
					   </td>
                       </tr>
                       <tr class="NormalRow">
                       <td></td>
                         
                       </tr>
                      
                      <tr></tr>
                      <tr></tr>
                       <tr class="NormalRow">
                                   <td colspan="9" align="center"><button class="but"  id="search" name="search" tabindex="0" type="button"  title="Search"   onClick="searchSancReq();">Search</button>
                                  <button class="but"  id="reset" name="reset" tabindex="0" type="button"  title="Reset"   onClick="resetPage();">Reset</button></td>   
                        </tr>
						
					</table>
					</td>
					</tr>
					</table>
					
					
					<br>
					
				<table align="center" width="90%" cellpadding="0" cellspacing="0"  style="padding-top:0px;margin:0px auto;">
						<tr class="HeaderRow">
                                  <td width="100%" class="tbcellCss" align="center"><b>ADMIN SANCTION REQUESTS</b></td>
					</tr>	
					</table>

<table id="sancWorkflow" width="90%" style="padding-top: 0px; margin: 0px auto;">
<tr>
<td >

<logic:present name="adminSanctionForm"  property="adminSancList">
<bean:size id="size" name="adminSanctionForm" property="adminSancList"/>
<logic:greaterThan name="size" value="0">

<%int i = 1;%> 
  <display:table style="padding-left:20px"   name="adminSanctionForm.adminSancList" id="rowId" requestURI="/adminSanction.do"  pagesize="5" export="false">
                <display:caption>
                <display:setProperty name="export.export name.include_header">true</display:setProperty>
				<display:setProperty name="basic.show.header">false</display:setProperty>
							<thead>
								<tr>
									<td class="tbheader1" style="width:10px"><b>S.No</b></td>
									<td class="tbheader1" style="width:100px"><b>Sanction ID</b></td>
									<td class="tbheader1" style="width:250px"><b>Status</b></td>
									<td class="tbheader1" style="width:100px"><b>Amount</b></td>
									<td class="tbheader1" style="width:160px"><b>Created By</b></td>
									<td class="tbheader1" style="width:160px"><b>Created Date</b></td>
									<td class="tbheader1" style="width:160px"><b>Last Updated By</b></td>
									<td class="tbheader1" style="width:160px"><b>Last Updated Date</b></td>
									</tr>
									</thead>
                </display:caption>
                <display:column  class="tbcellCss"  value="<%=i++ %>" title="S.No" style="text-align:center;"/>
                 <display:column  class="tbcellCss" title="Sanction ID"  style="text-align:center;"><a href="javascript:viewSancStatus('${rowId.sanctionId}');" title="Click to View Admin Sanction Request"><c:out value="${rowId.sanctionId}"/></a>
               </display:column>
                <display:column class="tbcellCss"  title="Status"  property="status" style="text-align:center;"/>
                <display:column class="tbcellCss" title="Amount" property="sanctionAmount" style="text-align:center;"/>
                <display:column  class="tbcellCss"  title="Created By" property="crtUsr" style="text-align:center;"/>
               <display:column  class="tbcellCss"  title="Created Date" property="crtDt" style="text-align:center;"/>
               <display:column  class="tbcellCss"  title="Last Updated By" property="lstUpdUsr" style="text-align:center;"/>
               <display:column  class="tbcellCss"  title="Last Updated Date" property="lstUpdDt" style="text-align:center;"/>
              
               
            </display:table>

</logic:greaterThan>
 <logic:equal name="size" value="0">
 <table width="100%" >
 <tr>
 <td class="tbcell" width="90%" align="center"><b>No Records Found</b></td>
 </tr>

 </table>
 </logic:equal>
</logic:present>
</td>
</tr>
</table>	
					
				<div id="processImagetable" style="top:45%;z-index:50;position:absolute;left:45%;display:none">
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
</html:form>
</body>
</html>