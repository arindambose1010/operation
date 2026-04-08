 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/common/include.jsp"%>
<html>
	<fmt:setLocale value='en_US'/> 
		<fmt:bundle basename="ApplicationResources">
			<head>
				<title>Case Sheet</title>
				
				<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
				<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
				<script src="js/jquery-1.9.1.min.js"></script>
				<%@ include file="/common/includeCalendar.jsp"%>
				<%@ include file="/common/includeScrollbar.jsp"%>
				<c:if test="${fromPastHistory eq 'Y'}">
					<%@ include file="/common/includePatientDetails.jsp"%>
				</c:if>
				<script src="js/jquery.msgBox.js" type="text/javascript"></script>
				
				  
				<style type="text/css">	
				a.disabled:link, a.disabled:visited {color:grey;}
				a.visited{color: red;}
#imageID {
top: 100px;
}

				</style>
				<script>
                var length=0;
				var newwindow;
					function paginFunction(res)  
					{
						document.forms[0].action='/<%=context%>/empPenCaseSearch.do?actionFlag=searchFormPagin&employeeNo=${employeeNo}&fromPastHistory=${fromPastHistory}&paginStatus='+res;
						document.forms[0].submit();
					}

					function CalenderWindowOpen(val,x,y)
					{

					//parameter val contains the name of the textbox
					var newWindow;

					var urlstring = '/Operations/Common/Calendar.jsp?requestSent='+val;
					var urlstyle = 'height=200,width=280,toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=no,top='+x+',left='+y;
					newWindow = window.open(urlstring,'Calendar',urlstyle);
					}
					 function search()
					{
						 var caseId=document.getElementById('caseNo').value;
						 var caseStatus="-1";
						 if(document.getElementById('caseStatus'))
							caseStatus= document.getElementById('caseStatus').value;
						 var fromDate=document.getElementById('fromDate').value;
						 var toDate=document.getElementById('toDate').value;
						 
						 if((fromDate=="" && toDate!="") || (fromDate!="" && toDate==""))
							 {
							 jqueryAlertMsg("Alert","Please Select Both From Date and To Date");return;
							 
							 }
						 						 
					if(caseId=="" && caseStatus=="-1" && fromDate=="" && toDate=="")	
					{
						jqueryAlertMsg("Alert","Please Enter the Search Criteria ");return;
						}	
						
						document.forms[0].action='/<%=context%>/empPenCaseSearch.do?actionFlag=caseSearchCriteria&employeeNo=${employeeNo}&fromPastHistory=${fromPastHistory}';
						document.forms[0].submit();
					  } 
					 
					 
					 function resetData()
					 {
						document.getElementById('caseNo').value="";
						if(document.getElementById('caseStatus'))
							document.getElementById('caseStatus').value="-1";
						document.getElementById('fromDate').value="";
						 toDate=document.getElementById('toDate').value="";
						 
					 }
 function popitup(url) {

		newwindow=window.open(url,'CaseFeedbackForm','width=700, height=500, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes,menubar=no,toolbar=no');	
          
 		if (window.focus) {newwindow.focus();
		}
		 return false;
	}
	
	function fn_verifyFeedback()
	{
		if('${fromPastHistory}'!='Y')
		{
			var tdCaseno=new Array();
			  for(i=0;i<length;i++)
			  {
				  
			  tdCaseno[i]= document.getElementById("td["+i+"]").innerHTML;
			  }
			  var caseIds = tdCaseno.join(";");
			 
			  
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
		   alert("Your browser does not support XMLHTTP!");
		  }

				var url="feedback.do?flg=verify&caseNo="+caseIds;
				  xmlhttp.open("Post",url,true);
				  xmlhttp.send(null);
						  
				  
		  xmlhttp.onreadystatechange=function()
		  {
		  if(xmlhttp.readyState==4)
		  {
			  
			  var resultArray=xmlhttp.responseText;
			 if(resultArray =="SessionExpired")
			{
				alert("Session has been expired");
				parent.location.href="indexOpenAM.jsp";
			}
			else
			{  
			if(resultArray!=null)
		   {	
			
			var result=resultArray.split("*");
			var result1=new Array();
			result1=result[0].split(";");
			for(j=0;j<result1.length;j++)
				{
			if(result1[j]=="true")
				{
				  document.getElementById("td1["+(j)+"]").style.color="red";
				} 
				}
			}
		  }
		  }
		  }
		}            

	}
 


 
 function fn_getFeedback(caseNo,caseId,patientId,patName,input)
 {
	
	if(input.style.color=="red")
		{
          alert("Feedback already Submitted");
		}
	else
		{
		popitup("feedback.do?flg=feedback&caseNo="+caseNo+"&caseId="+caseId+"&patientId="+patientId+"&patName="+patName);
		input.setAttribute("onclick","javascript:void(0)");
		input.className='disabled';
		}	
	  
} 
						function validateBackSpace(e)
						{
							var code;
							if (!e) var e = window.event;
							if (e.keyCode) code = e.keyCode;
							else if (e.which) code = e.which;  
							if( code== 8 )
							{
							  e.returnValue=false;
							 }
						}
						function validateDate(arg1,input)
						{
							var entered = input.value;
							entered = entered.split("-");
							var byr = parseInt(entered[2]); 
							/* if(isNaN(byr))
							{
								input.value="";
								jqueryAlertMsg("Alert",Please Select "+arg1);
							}
							else
							{ */
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
							//}
						}
						function getDiagnosisDetails(caseStatus,caseId,hospName,regisDate,patientId,caseNo)
						{
							if(caseStatus!=null && caseStatus!='' && caseStatus=='CD34')
							{
								jqueryAlertMsg("Alert","Cannot show the case details as case is in Case Drafted status.");
								return;
							}
							else
							{
								var url;
								var pastHistory='${fromPastHistory}';
								if(caseStatus!=null && caseStatus!='' && caseStatus=='CD2')
								{
									if(pastHistory!=null && pastHistory!='' && pastHistory=='Y')
										url="/<%=context%>/preauthDetails.do?actionFlag=getOnlineCaseSheet&caseId="+caseId+"&patientId="+patientId+"&caseSheetFlag=Y&closeBut=Y";     
									else
										url="/<%=context%>/clinicalNotesAction.do?actionFlag=printDischargeForm&caseId="+caseId+"&decFlag=N";	
								}
								else
									url="/<%=context%>/patientDetails.do?actionFlag=casePrintForm&patientId="+patientId+"&caseId="+caseNo+"&decFlag=N";
									//var url='/<%=context%>/empPenCaseSearch.do?actionFlag=getDiagnosisDetails&patientId='+patientId+'&caseNo='+caseNo+'&hospName='+hospName+'&regisDate='+regisDate+'&caseId='+caseId+'&caseStatus='+caseStatus;
								window.open(url,"case_details",'toolbar=no,resizable=yes,scrollbars=yes,menubar=no,location=no,height=700,width=900,left=10,top=15');
							}
							
						}

					function fnMonthDiff(FromDate,ToDate)
					{
						var FromDateVal;
						var ToDateVal;            
						var k = FromDate.indexOf("-");
						var t = FromDate.indexOf("-",3);   
						FromDateVal = FromDate.substr(k+1,t-k-1)+"/"+FromDate.substr(0,k)+"/"+FromDate.substr(t+1,t-1); 

						var fromYear = parseInt(FromDate.substr(t+1,t-1));			
						var fromMon = Number(FromDate.substr(k+1,t-k-1));
						var fromDt=Number(FromDate.substr(0,k));	
						k = ToDate.indexOf("-");
						t = ToDate.indexOf("-",3);
						ToDateVal = ToDate.substr(k+1,t-k-1) +"/"+ToDate.substr(0,k)+"/"+ToDate.substr(t+1,t-1);

						var toYear = Number(ToDate.substr(t+1,t-1));
						var toMon = Number(ToDate.substr(k+1,t-k-1));
						var toDt=Number(ToDate.substr(0,k));
						if(toYear == fromYear)
						{				
							if((toDt - fromDt >=0 && toMon - fromMon <= 2) || (toDt - fromDt <0 && toMon - fromMon <= 3))
							{
								return true;
							}
							else 
							{   
								alert('Can not Select more than 3 months difference');
								return false;
							}
						}
						else if(toYear > fromYear)
						{
							if((toDt - fromDt >=0 &&  fromMon - toMon >=10) || (toDt - fromDt <0 && fromMon - toMon >= 9))
							{
								return true;
							}
							else 
							{  
								alert('Can not Select more than 3 months difference');
								return false;
							}
						}
						else
						{
							alert('Please Select valid From and To Dates');
							return false;
						}
					}
					function fn_maxmizeTop()
					{
					parent.fn_maxmizeTop();
					/*	var url='/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=ipTab&CaseId=${caseId}&flag=N&casesForApproval='+parent.parent.caseApprovalFlag+'&errSearchType='+parent.parent.errSearchType+'&disSearchType='+parent.parent.disSearchType+'&module='+parent.parent.module;
						document.forms[0].action=url;
						document.forms[0].target="_parent";
					    document.forms[0].submit();*/
						}
					function fn_maxmizeRight(){
						parent.fn_maxmizeRight();
					}
					function fn_viewAttachments(caseId)
					{
						attachmentWin= window.open("/Operations/attachmentAction.do?actionFlag=onload&UpdType=ehfPreauth&caseId="+caseId+"&caseAttachmentFlag=Y&openWin=Y", 'window1','toolbar=no,resize=no,scrollbars=yes,width=800, height=600, top=100,left=50');
					}
					</script>
			</head>
			<body onload="fn_verifyFeedback()">
				<html:form action="/empPenCaseSearch.do" method="post" enctype="multipart/form-data">
<c:if test="${fromPastHistory eq 'Y'}">
				<div id="imageID" > 
<img id="patDtlsImage" src="images/patient_dtls.jpg"  style=cursor:hand; title="Click to View Patient Details" alt="Patient Details" align="top" onclick="javascript:fn_getPatDetails()" ></img>
<div id="popupRaiseComplaint" style="position: absolute; width: 825px; height:250px; overflow:hidden;display:none" >
   <a id="popupRaiseCloseComplaint">X</a>
   <iframe  id="complaintFrame" width="100%" height="100%" frameborder="no" scrolling="no" > </iframe>
   </div> 
</div>
</c:if>
<div id="backgroundPopup"></div>
					<table width="100%" >
						<tr class="tbheader">
							<c:if test="${fromPastHistory ne 'Y'}">
								<td align="center">
									<b>Employee/Pensioner Case Details</b>
								</td>
							</c:if>
							<c:if test="${fromPastHistory eq 'Y'}">
								<tr class="tbheader">
									<td width="3%">
										<img id="menuImage"
										src="images/rightLeftArrow.jpg" title="Maximize/Minimize"
										style="cursor: hand;" width="25" height="20" alt="Hide Menu"
										align="top" onclick="javascript:fn_maxmizeRight()"></img> 
									</td>
									
									<td><FONT size="2" style="STRONG"><b>Card Utilization Details</b></FONT></td>
									<td width="3%">
										<!-- <img id="topImage"
										src="images/updownArrow.jpg" width="30" height="20"
										style="cursor: hand;" title="Maximize/Minimize" alt="Maximize"
										align="top" onclick="javascript:fn_maxmizeTop()"></img>-->
										<img id="topImage" src="images/back.jpg" width="30" height="20" style=cursor:hand; title="Back" alt="Back" align="top" onclick="javascript:fn_maxmizeTop()" ></img>
									</td>
								</tr>
							</c:if>
						</tr>
					</table>
					<br>
					<table class="tb" align="center" style="padding-top: 0px; margin: 0px auto;">
						<tr>
							<td>
								<table align="center" cellpadding="15" cellspacing="15"  style="padding-top: 0px; margin: 0px auto;">
									<c:if test="${fromPastHistory ne 'Y'}">
										<tr>
											<td width="50%" class="labelheading1 tbcellCss">
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="label.EmpPenCaseSearch.caseId"/>
												<html:text styleId="caseNo" title="Enter Case Number" name="casesSearchForm" property="caseNo" maxlength="15"/> 
											</td>
 
											<td width="50%" class="labelheading1 tbcellCss">
												<fmt:message key="label.EmpPenCaseSearch.CaseStatus"/>

												<html:select styleId="caseStatus" name="casesSearchForm" title="Select Case Status" property="caseStatus" >
													<html:option value="-1"><fmt:message key="label.EmployeeOtherDetails.select"/></html:option>
													<html:option value="CD2">IP</html:option>
													<html:option value="CD5">OP</html:option>
												</html:select>
											</td>
										</tr>

										<tr>
											<td width="52%" class="labelheading1 tbcellCss" >From Date
												<span title="select From Date">
													<html:text name="casesSearchForm" property="fromDate" styleId="fromDate" title="Enter From Date" onblur="validateDate('From Date',this)"  onkeydown="validateBackSpace(event)" readonly="true"/>
												</span>
											</td>
											<td width="53%" class="labelheading1 tbcellCss" nowrap="true">To Date
											   <span title="select To Date">
													<html:text name="casesSearchForm" property="toDate" styleId="toDate" title="Enter To Date" onblur="validateDate('To Date',this)"  onkeydown="validateBackSpace(event)" readonly="true"/>
												</span>
											</td>
										</tr>
										<tr>
										<td colspan="2" align="center">
											<button class="but" type="button" onclick="search();" class="ActionButton" value="Submit" >Search</button>
											<button class="but" type="button" onclick="resetData();" class="ActionButton"  value="Reset"  >Reset</button>
										</td>
									</tr>
									</c:if>
									<c:if test="${fromPastHistory eq 'Y'}">
										<tr>
											<td class="labelheading1 tbcellCss">
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="label.EmpPenCaseSearch.caseId"/>
												<html:text styleId="caseNo" title="Enter Case Number" name="casesSearchForm" property="caseNo" maxlength="15"/> 
											</td>

											<td class="labelheading1 tbcellCss" >From Date
												<span title="select From Date">
													<html:text name="casesSearchForm" property="fromDate" styleId="fromDate" title="Enter From Date" onblur="validateDate('From Date',this)"  onkeydown="validateBackSpace(event)" readonly="true"/>
												</span>
											</td>
											<td class="labelheading1 tbcellCss" nowrap="true">To Date
											   <span title="select To Date">
													<html:text name="casesSearchForm" property="toDate" styleId="toDate" title="Enter To Date" onblur="validateDate('To Date',this)"  onkeydown="validateBackSpace(event)" readonly="true"/>
												</span>
											</td>
										</tr>
										<tr>
										<td colspan="3" align="center">
											<button class="but" type="button" onclick="search();" class="ActionButton" value="Submit" >Search</button>
											<button class="but" type="button" onclick="resetData();" class="ActionButton"  value="Reset"  >Reset</button>
										</td>
									</tr>
									</c:if>
									
								</table>
							</td>
						</tr>
					</table>
					<br>

					<logic:present name="casesSearchForm" property="lstCaseSearch">
						<c:choose>
							<c:when test="${fromPastHistory ne 'Y' || (fromPastHistory eq 'Y' && (listsize>1 || fromType eq 'criteria'))}">
								<table class="tb" cellpadding="1" cellspacing="1"  width="95%" style="padding-top:0px;margin:0px auto;">
									<bean:size id="size" name="casesSearchForm" property="lstCaseSearch"/>
									<logic:greaterThan name="size" value="0">
										<tr>
											<td align="right" colspan="9"  width="100%" >
												<logic:greaterThan name="casesSearchForm" property="curPage" value="10">
													<a  id="prev" href="javascript:paginFunction('prev')" >
														<font color="black">
															<b> <fmt:message key="label.EmployeeSearch.prev"/> </b>
														</font>
													</a>
												</logic:greaterThan>
												&nbsp;&nbsp;&nbsp;
												<bean:write name="casesSearchForm" property="pageStats" />
												&nbsp;&nbsp;
												
												<logic:notEqual name="casesSearchForm" property="curPage" value='${listsize}'>
													<a class="groovybutton" id="next"  href="javascript:paginFunction('next')" >
													  <font color="black">
														<b> <fmt:message key="label.EmployeeSearch.next"/> </b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													  </font>
													</a>
												</logic:notEqual>
											</td>
										</tr>
										
										<tr>
											<th class="tbheader1" width="12%"><fmt:message key="label.EmpPenCaseSearch.caseId"/></th>
											<th class="tbheader1" width="14%"><fmt:message key="label.EmpPenCaseSearch.patientName"/></th>
											<th class="tbheader1" width="18%"><fmt:message key="label.EmpPenCaseSearch.hospitalName"/></th>
											<th class="tbheader1" width="15%"><fmt:message key="label.EmpPenCaseSearch.CaseStatus"/></th>
											<th class="tbheader1" width="15%"><fmt:message key="label.tele.caseStatus"/></th>
											<th class="tbheader1" width="20%"><fmt:message key="label.EmployeeSearch.CaseRegisteredDate"/></th>
											<!-- Do not show feedback link in Past history tab -->
											<c:if test="${fromPastHistory ne 'Y'}">
												<th class="tbheader1" width="3%"><fmt:message key="label.EmpPenCaseSearch.CaseFeedback"/></th>
												<th class="tbheader1" width="2%"><fmt:message key="label.EmpPenCaseSearch.ViewAttach"/></th>
											</c:if>
											<c:if test="${fromPastHistory eq 'Y'}">
												<th class="tbheader1" width="3%">Preauth Amount</th>
												<th class="tbheader1" width="2%"><fmt:message key="label.caseSearch.claimAmt"/></th>
											</c:if>
											
										</tr>
										<!--To generate indexes for tr and td -->
										<%
										  String tdIndex1 = "";
										  String tdIndex="";
										%>
										<logic:iterate name="casesSearchForm" property="lstCaseSearch" id="data" indexId="index">
										<!-- Do not show current case in Past history tab -->
										<script> length=length+1;</script>
										 <%
											 tdIndex = "td["+index+"]"; 
											 tdIndex1="td1["+index+"]";
										 %>
										
										<c:choose>
											<c:when test="${fromPastHistory eq 'Y'}">
												<logic:notEqual name="data" property="catId" value="${caseId}">
														<tr>
															<td align="center" width="12%" class="tbcellBorder">
																<a href="javascript:getDiagnosisDetails('<bean:write name="data" property="caseStatusId"/>','<bean:write  name="data" property="catId"/>','<bean:write  name="data" property="hospName"/>','<bean:write  name="data" property="SUBMITTEDDATE"/>','<bean:write  name="data" property="patientId"/>','<bean:write  name="data" property="caseNo"/>')"><bean:write  name="data" property="caseNo"/></a>
															</td>
															<td align="center" width="14%" class="tbcellBorder">
																<bean:write  name="data" property="patName"/>
															</td>
															<td align="center" width="18%" class="tbcellBorder">
																<bean:write  name="data" property="hospName"/>
															</td>
															<td align="center" width="15%" class="tbcellBorder">
																<bean:write  name="data" property="caseStatus"/>
															</td>
															<td align="center" width="15%" class="tbcellBorder">
																<bean:write  name="data" property="issuestatus"/>
															</td>
															<td align="center" width="20%" class="tbcellBorder">
																<bean:write  name="data" property="SUBMITTEDDATE"/>
															</td>
															<td align="center" width="3%" class="tbcellBorder">
																<bean:write  name="data" property="preauthAppAmt"/>
															</td>
															<td align="center" width="2%" class="tbcellBorder">
																<bean:write  name="data" property="claimAmt"/>
															</td>
															
														</tr>
													</logic:notEqual>
											</c:when>
											<c:otherwise>
												<tr>
													<td align="center" width="12%" class="tbcellBorder" >
														<a id="<%=tdIndex%>" href="javascript:getDiagnosisDetails('<bean:write name="data" property="caseStatusId"/>','<bean:write  name="data" property="catId"/>','<bean:write  name="data" property="hospName"/>','<bean:write  name="data" property="SUBMITTEDDATE"/>','<bean:write  name="data" property="patientId"/>','<bean:write  name="data" property="caseNo"/>')"><bean:write  name="data" property="caseNo"/></a>
													</td>
													<td align="center" width="14%" class="tbcellBorder">
														<bean:write  name="data" property="patName"/>
													</td>
													<td align="center" width="18%" class="tbcellBorder">
														<bean:write  name="data" property="hospName"/>
													</td>
													<td align="center" width="15%" class="tbcellBorder">
														<bean:write  name="data" property="caseStatus"/>
													</td>
													<td align="center" width="15%" class="tbcellBorder">
															<bean:write  name="data" property="issuestatus"/>
													</td>
													<td align="center" width="20%" class="tbcellBorder">
														<bean:write  name="data" property="SUBMITTEDDATE"/>
													</td>
													<td align="center" width="3%" class="tbcellBorder" >
														<logic:equal name="data" property="caseStatusId" value="CD2">
															<a id="<%=tdIndex1%>" href="javascript:void(0)" onclick="javascript:fn_getFeedback('<bean:write  name="data" property="caseNo"/>','<bean:write  name="data" property="catId"/>','<bean:write  name="data" property="patientId"/>','<bean:write  name="data" property="patName"/>',this)">Feedback</a>
														</logic:equal>
														<logic:notEqual name="data" property="caseStatusId" value="CD2">
															Not Applicable
														</logic:notEqual>
													</td>
													<td align="center" width="2%" class="tbcellBorder">
														<logic:equal name="data" property="caseStatusId" value="CD2">
															<a href="javascript:fn_viewAttachments('<bean:write  name="data" property="catId"/>')"><img src="images/empnlmnt/ViewAttach.gif" title="View Attachments"></a>
														</logic:equal>
														<logic:notEqual name="data" property="caseStatusId" value="CD2">
															Not Applicable
														</logic:notEqual>
													</td>
												</tr>
											</c:otherwise>
										</c:choose>
										</logic:iterate>
									</logic:greaterThan>
								</table>
							</c:when>
							<c:otherwise>
								<br><br><br>
								<table  style="padding-top:0px;margin:0px auto;">
									<tr>
										<td align="center"><b><font size="2">No Records Found</font></b></td>
									</tr>
								</table>
							</c:otherwise>
						</c:choose>
					</logic:present>
					
					<html:hidden name="casesSearchForm" property="curPage" />
					<html:hidden name="casesSearchForm" property="fromType" />
					<html:hidden name="casesSearchForm" property="caseNo" />
					<html:hidden name="casesSearchForm" property="caseStatus" value="-1"/>
					<html:hidden name="casesSearchForm" property="fromDate" />
					<html:hidden name="casesSearchForm" property="toDate" />
					
					<!-- If no records are found -->
					<logic:equal name="casesSearchForm" property="flag" value="false">
						<br><br><br>
						
						<table  style="padding-top:0px;margin:0px auto;">
							<tr>
								<td align="center"><b><font size="2">No Records Found</font></b></td>
							</tr>
						</table>
					</logic:equal>
				</html:form>
			</body>
	</fmt:bundle>
</html>