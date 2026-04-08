<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/common/include.jsp"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"  %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script LANGUAGE="JavaScript" SRC="scripts/PreauthScripts.js"></script>
	<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
	<script src="js/jquery-1.9.1.min.js"></script>
	<%@ include file="/common/editableComboBox.jsp"%> 
	<%@ include file="/common/includeCalendar.jsp"%>  
	<%@ include file="/common/includeScrollbar.jsp"%>
	<script src="js/jquery.msgBox.js" type="text/javascript"></script>
	<title>View Death Cases</title>
	<style> #ui-id-4{ width:15%; }#ui-id-6,#ui-id-3,#ui-id-1,#ui-id-2,#ui-id-5 { width:35%; }
  .custom-combobox-input {    margin: 0;    padding: 0.3em; background:#fff;border:1px solid #e6e6e6; }
 </style>
</head>
<script>

function fn_pagination(pageId,end_index)
{
	
			document.forms[0].action="./casesSearchAction.do?actionFlag=viewDeathCases&pageId="+pageId+"&end_index="+end_index;
			document.forms[0].method="post";
			document.forms[0].submit();
}


function viewPreviousPages(pageNo,noOfPages,selectedPage,end_index)
{
	var pageDisplays='';
	var pageNoLim=pageNo;
	var minPageNo=pageNo-10;
	var i=0;
	if(minPageNo>1)
	{
		pageDisplays=pageDisplays+'<a href="javascript:viewPreviousPages('+minPageNo+','+noOfPages+','+selectedPage+','+end_index+')">Previous</a>&nbsp;&nbsp;';
	}
	/* else
	{
		minPageNo=minPageNo+1;
	} */
	if(minPageNo==0)
		{
			minPageNo=1;
		}
	for(i=minPageNo;i<pageNoLim;i++)
	{
		if(selectedPage==i)
		{
			pageDisplays=pageDisplays+'<b>'+i+'</b>&nbsp;';
		}
		else
		{
			pageDisplays=pageDisplays+'<a href="javascript:fn_pagination('+i+','+end_index+')"><b>'+i+'</b></a>&nbsp;&nbsp;';
		}
		
	}
	pageDisplays=pageDisplays+'<a href="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+','+end_index+')">Next</a>';
	document.getElementById("pageNoDisplay").innerHTML=pageDisplays;
}
function viewNextPages(pageNo,noOfPages,selectedPage,end_index)
{
	var pageDisplays='';
	var pageNoLim=pageNo+10;
	var i=0;
	if(pageNoLim>noOfPages)
	{
		pageNoLim=noOfPages+1;
	}
	pageDisplays=pageDisplays+'<a href="javascript:viewPreviousPages('+pageNo+','+noOfPages+','+selectedPage+','+end_index+')">Previous</a>&nbsp;&nbsp;';
	for(i=pageNo;i<pageNoLim;i++)
	{
		if(selectedPage==i)
		{
			pageDisplays=pageDisplays+'<b>'+i+'</b>&nbsp;';
		}
		else
		{
			pageDisplays=pageDisplays+'<a href="javascript:fn_pagination('+i+','+end_index+')"><b>'+i+'</b></a>&nbsp;&nbsp;';
		}
		
	}
	if(i<noOfPages)
	{
		pageDisplays=pageDisplays+'<a href="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+','+end_index+')">Next</a>';
	}
	document.getElementById("pageNoDisplay").innerHTML=pageDisplays;
}



	var searchType='${caseSearchType}';
	function checkBlankSpaces(arg ,id)
	{
		var name =  eval("document.forms[0]."+arg);    
		var textval = name.value;
		var tbLen = textval.replace(/\s+/g,'').replace(/\s+$/g,'') ;
	    if(tbLen.length == 0)
	    	{
	    	var fr = partial(focusNClear,name);
   		   jqueryAlertMsg('Death Cases Search check',"Blank spaces are not allowed in "+id,fr);
	    	}
	}
	
	function getTitles(styleId)
	{
		 var numOptions = document.getElementById(styleId).options.length; 
		
		for (var i = 0; i < numOptions; i++)
		 document.getElementById(styleId).options[i].title = document.getElementById(styleId).options[i].text;
	}
	function trim (str) 
	{ 
		return str.replace (/^\s+|\s+$/g, '');
	}
	function validateDate(arg1,input)
	{
		var entered = input.value;
		entered = entered.split("-");
		var byr = parseInt(entered[2]); 
		if(isNaN(byr))
		{
			input.value="";
			alert("Please Select "+arg1);
		}
		else
		{
		var bmth = parseInt(entered[1],10); 
		var bdy = parseInt(entered[0],10);
		var DoB=""+(bmth)+"/"+ bdy +"/"+ byr;
		DoB=Date.parse(DoB);
		var today= new Date();
		var nowmonth = today.getMonth();
		var nowday = today.getDate();
		var nowyear = today.getFullYear();
		var DoC=""+(nowmonth+1)+"/"+ nowday +"/"+ nowyear;
		DoC=Date.parse(DoC);
		if(DoB>DoC)
			{
			input.value="";
			alert(arg1+" should not be greater than Today's Date");
			}
		}
	}
	function fn_resetData()
	{
		if(document.forms[0].claimId.value!=null)
				$('#claimId-input').val('');
		document.forms[0].caseNo.value='';
		document.forms[0].wapNo.value='';
		document.forms[0].patName.value='';
		document.forms[0].fromDate.value='';
		document.forms[0].toDate.value='';
		if(document.forms[0].showScheme.value=='show')
		{
			document.forms[0].schemeType.value='';
		}
	}
	function fn_Search()
	{	
		if(trim(document.forms[0].claimId.value)=='' &&
			trim(document.forms[0].caseNo.value)=='' &&
				trim(document.forms[0].wapNo.value)=='' &&
					trim(document.forms[0].patName.value)=='' &&
						trim(document.forms[0].fromDate.value)=='' &&
							trim(document.forms[0].toDate.value)=='')
		{	
			if(document.forms[0].showScheme.value=='show')
			{
				if(document.forms[0].schemeType.value=='')
				{
					jqueryAlertMsg('Death Cases Search','Please select any search criteria');
					return;
				}
			}
			else
			{
				jqueryAlertMsg('Death Cases Search','Please select any search criteria');
				return;
			}
		}
		else if ((document.getElementById("fromDate").value=='' && document.getElementById("toDate").value!='' )||(document.getElementById("fromDate").value!='' && document.getElementById("toDate").value===''))
		{	
			jqueryAlertMsg('Death Cases Search','Please select both from date and to date');
			return;
		}
			
			document.forms[0].action="/Operations/casesSearchAction.do?actionFlag=viewDeathCases&searchFlag=Y";
			caseSearchURl= '/Operations/casesSearchAction.do?actionFlag=viewDeathCases&searchFlag=Y&claimId='+trim(document.forms[0].claimId.value)+
			'&caseNo='+trim(document.forms[0].caseNo.value)+'&wapNo='+trim(document.forms[0].wapNo.value)+
			'&fromDate='+trim(document.forms[0].fromDate.value)+'&toDate='+trim(document.forms[0].toDate.value)+
			'&patName='+trim(document.forms[0].patName.value);
			if(document.forms[0].showScheme.value=='show')
			{
				caseSearchURl= caseSearchURl+'&schemeType='+document.forms[0].schemeType.value;  
			}
			//parent.setGlobalUrl(caseSearchURl);
			document.forms[0].submit();
		
	}
	function fn_caseApproval(caseId,arg,ipOpType)
	{ 
		if(parent.parent.globalURl==''){
			 caseSearchURl="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType;
			 parent.setGlobalUrl(caseSearchURl);
		}
		//parent.fn_resizePage2(470);
		document.forms[0].action='/Operations/casesApprovalAction.do?actionFlag=getCaseDtls&casesForApproval=N&CaseId='+caseId+'&status='+arg+'&caseSearchType='+searchType+'&ipOpType='+ipOpType;	
		document.forms[0].submit(); 
	}
	
</script>
<body>
<html:form  method="post"  action="/casesSearchAction.do" > 
<table width="980px" border="0" align="center">
	<tr align="center">
		<td align="center" class="tbheader"><b> VIEW DEATH CASES </b></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="center">
			<table class="tb" align="center" width="95%">
				<tr>
					<td class="labelheading1 tbcellCss" width="120px">
						<b>Claim Status</b>
					</td>
					<td class="tbcellBorder" width="150px">
						<html:select  name="casesSearchFormClaims" property="claimId" styleId="claimId" style="width:150px; " onmousemove="javascript:getTitles('claimId')" title="Please select claim status">
						<option  value="">----Select----</option>
						<html:options collection="StatusList" property="ID" labelProperty="VALUE"  />
						</html:select>
					</td>
					<td class="labelheading1 tbcellCss" width="120px">
						<b>Case No</b>
					</td>
					<td class="tbcellBorder" width="150px">
						<html:text name="casesSearchFormClaims" property="caseNo" style="width:120px;" maxlength="50" title="Please enter complete case number Eg: If case number is CASE/EHS100/1234 ,Please enter 1234 as case number" onchange="javascript:checkBlankSpaces('caseNo','case number');" />
					</td>
					<td class="labelheading1 tbcellCss" width="120px">
						<b>Card No</b>
					</td>
					<td class="tbcellBorder" width="150px">
						<html:text name="casesSearchFormClaims" property="wapNo" style="width:120px;" title="Enter Card Number" onchange="javascript:checkBlankSpaces('wapNo','card number');"/>
					</td>
				</tr>
				<tr>
					<td class="labelheading1 tbcellCss" width="120px">
						<b>Patient Name</b>
					</td>
					<td class="tbcellBorder"  width="150px">
						<html:text name="casesSearchFormClaims" style="width:120px" property="patName" styleId="patName" maxlength="50" title="Enter Patient Name" onchange="javascript:chkOnlyAlpha('patName','patient Name');"/>
					</td>
					<td class="labelheading1 tbcellCss" width="120px">
						<b>Death Date From</b>
					</td>
					<td class="tbcellBorder" width="150px">
						<html:text name="casesSearchFormClaims" property="fromDate" styleId="fromDate" style="width:110px;" title="Enter From Date" onchange="validateDate('From Date',this)"  onkeydown="validateBackSpace(event)" readonly="true" />
					</td>
					<td class="labelheading1 tbcellCss" width="120px">
						<b>Death Date To</b>
					</td>
					<td class="tbcellBorder" width="150px">
						<html:text name="casesSearchFormClaims" property="toDate" styleId="toDate" style="width:110px;" title="Enter To Date" onchange="validateDate('To Date',this)"  onkeydown="validateBackSpace(event)" readonly="true" />
					</td>
				</tr>
				<logic:equal name="casesSearchFormClaims"  property="showScheme" value="show">
					<tr>
						<td class="labelheading1 tbcellCss" width="120px">
							<b>Scheme</b>
						</td>
						<td class="tbcellBorder">
							<html:select property="schemeType" name="casesSearchFormClaims" style="width:150px;" onmousemove="javascript:getTitles('schemeType')" title="Please select Scheme">
								<html:option value="CD203">Both</html:option> 
								<html:option value="CD201">Andhra</html:option>
								<html:option value="CD202">Telangana</html:option> 
							</html:select>
						</td>
						<td colspan="4">&nbsp;</td>
					</tr>
				</logic:equal>
				<tr>
					<td>&nbsp;</td>
				</tr>	
				<tr>
					<td align="center" colspan="6">
						<button class="but"   type="button" name="Search" value="Search" onclick="javascript:fn_Search()"  >Search</button>
						<button class="but"   type="button" name="Reset" value="Reset" onclick="javascript:fn_resetData()"  >Reset</button>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	</table>
	<c:if test="${noOfRecords ne 0}">
	<table width="980px" border="0" align="center">
	<tr>
		 <!-- <td> -->
		<%-- 	<display:table name="${deathCasesList}" id="rowId" pagesize="10" style="width:100%;align:center;" export="false" requestURI="/casesSearchAction.do?actionFlag=viewDeathCases" cellpadding="1" cellspacing="1"> --%>
	<td width="25%">Total No of records found:${noOfRecords}</td>	 
	<td width="25%">Displaying records from ${start_index+1} to ${endresults}</td>
	<td align="center" colspan="1" id="pageNoDisplay"  style="width:25%">
&nbsp;&nbsp;&nbsp;
<%
int noOfPages = ((Integer) request.getAttribute("lastPage")).intValue();
int selectedPage = ((Integer) request.getAttribute("pageId")).intValue();
int end_index=((Integer) request.getAttribute("end_index")).intValue();
int pageNo=0;
int a=selectedPage/10;
int minVal=(a*10);
if(selectedPage%10==0)
	{
		minVal=minVal-10;
	}
int maxVal=minVal+10;
if(maxVal>noOfPages)
{
	maxVal=noOfPages;
}
if(minVal>=10&&minVal!=noOfPages)
	{
		minVal=minVal+1;
		%>
		<a href="javascript:viewPreviousPages(<%=minVal%>,<%=noOfPages%>,<%=selectedPage%>,<%=end_index%>)">Prev</a>&nbsp;
		<%
	}
	else
	{
		minVal=minVal+1;
	}
for(int i=minVal;i<=maxVal;i++)
{
	pageNo=i;
		if(selectedPage==pageNo)
		{
			%>
			<b><%=pageNo%></b>
			<%
		}
		else
		{
			%>
			<a href="javascript:fn_pagination(<%=pageNo%>,<%=end_index%>)"><b><%=pageNo%></b></a>&nbsp;
			<%
		}
}
if(pageNo<noOfPages)
	{
		%>
		<a href="javascript:viewNextPages(<%=pageNo+1%>,<%=noOfPages%>,<%=selectedPage%>,<%=end_index%>)">Next</a>
		<%
	}
		%>
		</td>
		<td width="25%">Show results in sets of <c:if test="${end_index ne 10}"><a href="javascript:fn_pagination(1,10)">10</a></c:if>
												<c:if test="${end_index eq 10}">10</c:if>	
												<c:if test="${end_index ne 20}"><a href="javascript:fn_pagination(1,20)">20</a></c:if>
												<c:if test="${end_index eq 20}">20</c:if>
												<c:if test="${end_index ne 50}"><a href="javascript:fn_pagination(1,50)">50</a></c:if>
												<c:if test="${end_index eq 50}">50</c:if>
												<c:if test="${end_index ne 100}"><a href="javascript:fn_pagination(1,100)">100</a></c:if>
												<c:if test="${end_index eq 100}">100</c:if></td>
	</tr>	
	</table>
	</c:if>
	<c:if test="${noOfRecords ne 0}">
	<table width="100%" border="0" align="center">
				<%-- <display:caption>
				<display:setProperty name="basic.show.header">false</display:setProperty> 
					<thead>--%>
						<tr>
							<td align="center" class="tbheader1" style="width:50px"><b>Case Number</b></td>
							<td align="center" class="tbheader1" style="width:50px"><b>Claim Number</b></td>
							<td align="center" class="tbheader1" style="width:50px"><b>Patient Name</b></td>
							<td align="center" class="tbheader1" style="width:50px"><b>Card Number</b></td>
							<td align="center" class="tbheader1" style="width:50px"><b>Claim Status</b></td>
							<td align="center" class="tbheader1" style="width:50px"><b>Hospital Name</b></td>
							<td align="center" class="tbheader1" style="width:50px"><b>Date of Registration</b></td>
							<td align="center" class="tbheader1" style="width:50px"><b>Death Date</b></td>
						</tr>
				<%--	</thead>
				</display:caption>
				 <display:column class="tbcellBorder" title="Case Number" >
					<a href="#" onclick="fn_caseApproval('${rowId.caseId}','','${rowId.patIpOp}')">
						<c:out value="${rowId.caseNo}"/>
					</a>
				</display:column> 
				<display:column class="tbcellBorder" title="Case Number" property="claimNo"/>
				<display:column class="tbcellBorder" title="Patient Name" property="patientName"/>
				<display:column class="tbcellBorder" title="Card Number" property="wapNo"/>
				<display:column class="tbcellBorder" title="Claim Status" property="claimStatus"/>
				<display:column class="tbcellBorder" title="HOSPITAL NAME" property="hospName"/>
				<display:column class="tbcellBorder" title="Date of Registration" property="caseBlockedUsr"/>
				<display:column class="tbcellBorder" title="Death Date" property="caseForDissFlg"/> 
				</display:table> --%>
				<logic:iterate id="deathCasesList" name="deathCasesList"> 
				<tr>
				<td class="tbcellBorder"><a href="#" onclick="fn_caseApproval('${deathCasesList.caseId}','','${deathCasesList.patIpOp}')">
						<c:out value="${deathCasesList.caseNo}"/></a></td>
				<td class="tbcellBorder"><bean:write name="deathCasesList" property="claimNo"/>	</td>
				<td class="tbcellBorder"><bean:write name="deathCasesList" property="patientName"/></td>
				<td class="tbcellBorder"><bean:write name="deathCasesList" property="wapNo"/></td>
				<td class="tbcellBorder"><bean:write name="deathCasesList" property="claimStatus"/></td>
				<td class="tbcellBorder"><bean:write name="deathCasesList" property="hospName"/></td>
				<td class="tbcellBorder"><bean:write name="deathCasesList" property="caseBlockedUsr"/></td>
				<td class="tbcellBorder"><bean:write name="deathCasesList" property="caseForDissFlg"/></td>
				</tr>
				</logic:iterate>
</table>
</c:if>
<c:if test="${noOfRecords eq 0}">
<div style="text-align:center">
<b>No Results Found.</b>
</div>
</c:if>
<html:hidden property="showScheme" name="casesSearchFormClaims" />
</html:form>
</body>
</html>