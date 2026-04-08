<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ include file="/common/include.jsp"%>
<html>
<fmt:setLocale value='${langID}'/> 
<fmt:bundle basename="ApplicationResources">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script LANGUAGE="JavaScript" SRC="scripts/PreauthScripts.js"></script>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script src="js/jquery-1.9.1.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>  
<%@ include file="/common/editableComboBox.jsp"%>  
<%@ include file="/common/includePatientDetails.jsp"%>  
<script src="js/jquery.msgBox.js" type="text/javascript"></script>

<title>Cases Search</title>
<style> #ui-id-4{ width:15%; }#ui-id-6,#ui-id-3,#ui-id-1,#ui-id-2,#ui-id-5 { width:35%; }
  .custom-combobox-input {    margin: 0;    padding: 0.3em; background:#fff;border:1px solid #e6e6e6; }
 </style>
<script type="text/javascript">
var searchType='${caseSearchType}';
var errSearchType='${errSearchType}';
var denErrSearchType='${denErrSearchType}';
var disSearchType='${disSearchType}';
var module='${module}';
var caseSearchURl='';
function fn_loadImage()
{
	document.getElementById('processImagetable').style.display="";
}
function fn_removeLoadingImage()  
{
	document.getElementById('processImagetable').style.display="none";  
}
function fn_caseApproval(caseId,arg,ipOpType)
{ 
	if(parent.parent.globalURl==''){
		 caseSearchURl="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errDentalSearchType='+denErrSearchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value;
		 parent.setGlobalUrl(caseSearchURl);
	}
	fn_loadImage();
	//parent.fn_resizePage2(470);
	document.forms[0].action='/<%=context%>/casesApprovalAction.do?actionFlag=getCaseDtls&CaseId='+caseId+'&status='+arg+'&errDentalSearchType='+denErrSearchType+'&caseSearchType='+searchType+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value+'&ipOpType='+ipOpType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module;	
	document.forms[0].submit(); 
}
function fn_caseApprovalAjax(caseId,arg,ipOpType)
{	
	fn_loadImage();
	if(parent.parent.globalURl==''){
		 caseSearchURl="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errDentalSearchType='+denErrSearchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value;
		 parent.setGlobalUrl(caseSearchURl);
	}
	//parent.fn_resizePage2(470);
	var flag = null;
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
	
	
	 url = '/<%=context%>/casesSearchAction.do?actionFlag=setViewFlag&CaseId='+caseId+'&flag=Y';
	 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {

		    	document.forms[0].action='/<%=context%>/casesApprovalAction.do?actionFlag=getCaseDtls&CaseId='+caseId+'&status='+arg+'&errDentalSearchType='+denErrSearchType+'&caseSearchType='+searchType+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value+'&ipOpType='+ipOpType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module;	
		    	document.forms[0].submit();    	
		    	
		    }}
	 xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
		
	
}

function showPagination(num)
{
	document.forms[0].showPage.value=num; 
	caseSearch();
	}
function showinSetsOf(num)
	{
	document.forms[0].rowsPerPage.value=num; 
	document.forms[0].showPage.value='1'; 
	caseSearch();
	}
function caseSearch()
	{
		document.forms[0].action="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errDentalSearchType='+denErrSearchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module;
         document.forms[0].submit();
	}
function exportToExcel()
{
	document.forms[0].action="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&excelFlag=Y&caseSearchType="+searchType+'&errDentalSearchType='+denErrSearchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module;
    document.forms[0].submit();
}
function exportToCSV()
{
	document.forms[0].action="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&excelFlag=Y&csvFlag=Y&caseSearchType="+searchType+'&errDentalSearchType='+denErrSearchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module;
    document.forms[0].submit();
}
function fn_caseSearch()
{
	var msg ='';
	var fr ='';
	var patientName = document.forms[0].patName.value;
	if(errSearchType=='Y'){
		
		if(document.forms[0].errStatusId.value=='' &&
				document.forms[0].caseNo.value=='' &&
				document.forms[0].patName.value=='' &&
				document.getElementById("fromDate").value=='' &&
				document.getElementById("toDate").value==='' &&
				document.forms[0].wapNo.value=='' && 
				document.forms[0].mainCatName.value=='' &&
				document.forms[0].catName.value=='' &&
				document.forms[0].procName.value=='' &&
				document.forms[0].telephonicId.value=='' && 
				(document.forms[0].schemeType && document.forms[0].schemeType.value==''))
					{
					jqueryAlertMsg('Cases Search','Please select any search criteria');
					return;
					}
				else if ((document.getElementById("fromDate").value=='' && document.getElementById("toDate").value!='' )||(document.getElementById("fromDate").value!='' && document.getElementById("toDate").value===''))
					{
					jqueryAlertMsg('Cases Search','Please select from date and to date');
					return;
					}
				else
					{
				 document.forms[0].showPage.value ="1";
				 document.forms[0].action="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errDentalSearchType='+denErrSearchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module;
				 caseSearchURl="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errSearchType='+errSearchType+'&errDentalSearchType='+denErrSearchType+'&disSearchType='+disSearchType+'&module='+module
				 +'&errStatusId='+document.forms[0].errStatusId.value+'&caseNo='+document.forms[0].caseNo.value+'&patName='+document.forms[0].patName.value+
				 '&fromDate='+document.getElementById("fromDate").value+'&toDate='+document.getElementById("toDate").value+'&wapNo='+document.forms[0].wapNo.value+'&mainCatName='+document.forms[0].mainCatName.value+
				 '&catName='+document.forms[0].catName.value+'&procName='+document.forms[0].procName.value+'&telephonicId='+document.forms[0].telephonicId.value+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value;
				 parent.setGlobalUrl(caseSearchURl);
			     document.forms[0].submit();
					
					}
	}
	else if(document.forms[0].casesForApprovalFlag.value=='Y'){	
	if(document.forms[0].claimId.value=='' &&
	document.forms[0].caseNo.value=='' &&
	document.forms[0].patName.value=='' &&
	document.getElementById("fromDate").value=='' &&
	document.getElementById("toDate").value==='' &&
	document.forms[0].wapNo.value=='' && 
	document.forms[0].mainCatName.value=='' &&
	document.forms[0].catName.value=='' &&
	document.forms[0].procName.value=='' &&
	document.forms[0].telephonicId.value=='' && 
	(document.forms[0].schemeType && document.forms[0].schemeType.value==''))
		{
		jqueryAlertMsg('Cases Search','Please select any search criteria');
		return;
		}
	else if ((document.getElementById("fromDate").value=='' && document.getElementById("toDate").value!='' )||(document.getElementById("fromDate").value!='' && document.getElementById("toDate").value===''))
		{
		jqueryAlertMsg('Cases Search','Please select from date and to date');
		return;
		}
	else
		{
		
	 document.forms[0].showPage.value ="1";
	 document.forms[0].action="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errDentalSearchType='+denErrSearchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module;
	 caseSearchURl="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errDentalSearchType='+denErrSearchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module
	 +'&claimId='+document.forms[0].claimId.value+'&caseNo='+document.forms[0].caseNo.value+'&patName='+patientName+
	 '&fromDate='+document.getElementById("fromDate").value+'&toDate='+document.getElementById("toDate").value+'&wapNo='+document.forms[0].wapNo.value+'&mainCatName='+document.forms[0].mainCatName.value+
	 '&catName='+document.forms[0].catName.value+'&procName='+document.forms[0].procName.value+'&telephonicId='+document.forms[0].telephonicId.value+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value;
	 parent.setGlobalUrl(caseSearchURl);
     document.forms[0].submit();
		
		}
	}
	else{
		if(document.forms[0].errStatusId.value=='' && document.forms[0].claimId.value=='' &&
				document.forms[0].caseNo.value=='' &&
				document.forms[0].patName.value=='' &&
				document.getElementById("fromDate").value=='' &&
				document.getElementById("toDate").value==='' &&
				document.forms[0].wapNo.value=='' && document.forms[0].hospId.value=='' 
				&& document.forms[0].mainCatName.value=='' && document.forms[0].catName.value==''
				&& document.forms[0].procName.value=='' &&
				document.forms[0].telephonicId.value=='')
					{
			     if(document.forms[0].showScheme.value=='show'){
			    	 if(document.forms[0].schemeType.value==''){
			    		 jqueryAlertMsg('Cases Search','Please select any search criteria');
						 return;
				    }
			    	 else{
			    		 document.forms[0].showPage.value ="1";
						 document.forms[0].action="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errDentalSearchType='+denErrSearchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module;
						 caseSearchURl="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&errDentalSearchType='+denErrSearchType+'&module='+module
						 +'&claimId='+document.forms[0].claimId.value+'&errStatusId='+document.forms[0].errStatusId.value+'&caseNo='+document.forms[0].caseNo.value+'&patName='+document.forms[0].patName.value
						 +'&hospId='+document.forms[0].hospId.value+'&fromDate='+document.getElementById("fromDate").value+'&toDate='+document.getElementById("toDate").value+'&wapNo='+document.forms[0].wapNo.value+'&mainCatName='+document.forms[0].mainCatName.value+
						 '&catName='+document.forms[0].catName.value+'&procName='+document.forms[0].procName.value+'&telephonicId='+document.forms[0].telephonicId.value+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value+'&schemeType='+document.forms[0].schemeType.value;
						 parent.setGlobalUrl(caseSearchURl);
					     document.forms[0].submit();}
				}else{
					jqueryAlertMsg('Cases Search','Please select any search criteria');
					return;
				}
			        
					}
				else if ((document.getElementById("fromDate").value=='' && document.getElementById("toDate").value!='' )||(document.getElementById("fromDate").value!='' && document.getElementById("toDate").value===''))
					{
					jqueryAlertMsg('Cases Search','Please select from date and to date');
					return;
					}
				else
					{
				document.forms[0].showPage.value ="1";
				 document.forms[0].action="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errDentalSearchType='+denErrSearchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module;
				 caseSearchURl="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errDentalSearchType='+denErrSearchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module
				 +'&claimId='+document.forms[0].claimId.value+'&errStatusId='+document.forms[0].errStatusId.value+'&caseNo='+document.forms[0].caseNo.value+'&patName='+document.forms[0].patName.value
				 +'&hospId='+document.forms[0].hospId.value+'&fromDate='+document.getElementById("fromDate").value+'&toDate='+document.getElementById("toDate").value+'&wapNo='+document.forms[0].wapNo.value+'&mainCatName='+document.forms[0].mainCatName.value+
				 '&catName='+document.forms[0].catName.value+'&procName='+document.forms[0].procName.value+'&telephonicId='+document.forms[0].telephonicId.value+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value;
				 if(document.forms[0].showScheme.value=='show'){
					 caseSearchURl= caseSearchURl+'&schemeType='+document.forms[0].schemeType.value;  
				}
				 parent.setGlobalUrl(caseSearchURl);
			     document.forms[0].submit();
					
					}
		}
}


	function fn_reset()
	{  
		
	if(document.forms[0].hospId !=null)
	{ document.forms[0].hospId.value=''; $('#hospId-input').val(''); }
	if(document.forms[0].schemeType)
	{ 
	document.forms[0].schemeType.value='CD201'; $('#schemeType-input').val('Andhra'); 
	
	}
	
	document.forms[0].mainCatName.value='';	
	if(document.forms[0].mainCatName.value!=null)
		$('#mainCatName-input').val('');
	document.forms[0].catName.value='';	
	if(document.forms[0].catName.value!=null)
		$('#catName-input').val('');
	document.forms[0].procName.value='';
	if(document.forms[0].procName.value!=null)
		$('#procName-input').val('');	   
		
	if(errSearchType=='Y'){
	document.forms[0].errStatusId.value='';
	if(document.forms[0].errStatusId.value!=null)
		$('#errStatusId-input').val('');
	}
	else if(document.forms[0].casesForApprovalFlag.value=='Y')	
		 { 
		 document.forms[0].claimId.value='';
		 if(document.forms[0].claimId.value!=null)
				$('#claimId-input').val('');
		 }
	else {
		document.forms[0].claimId.value='';
		if(document.forms[0].claimId.value!=null)
			$('#claimId-input').val('');
		document.forms[0].errStatusId.value='';
		if(document.forms[0].errStatusId.value!=null)
			$('#errStatusId-input').val('');
		}
		
	document.forms[0].caseNo.value='';		
	document.forms[0].patName.value='';	
	document.getElementById("fromDate").value="";
	document.getElementById("toDate").value="";
	document.forms[0].wapNo.value='';
	document.forms[0].telephonicId.value='';	
	 //document.forms[0].action="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=N&caseSearchType="+searchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module;
     //document.forms[0].submit();
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
	function getTitles(styleId)
	{
		 var numOptions = document.getElementById(styleId).options.length; 
		
	for (var i = 0; i < numOptions; i++)
		 document.getElementById(styleId).options[i].title = document.getElementById(styleId).options[i].text;
	}

	function checkBlankSpaces(arg ,id)
	{
		var name =  eval("document.forms[0]."+arg);    
		var textval = name.value;
		var tbLen = textval.replace(/\s+/g,'').replace(/\s+$/g,'') ;
	    if(tbLen.length == 0)
	    	{
	    	var fr = partial(focusNClear,name);
   		   jqueryAlertMsg('Cases Search check',"Blank spaces are not allowed in "+id,fr);
	    	}
	}
	function printprfForm(caseId, caseStatus,patientId, pendingFlag,ipDt)
	{	
		if(ipDt==null || caseStatus=='CD34'|| (ipDt!=null && (pendingFlag==null || pendingFlag=='') && caseStatus=='CD2'))
			jqueryAlertMsg('Cases Search','Preauthorization has not been updated for this case');
		else
		{
			var url="/<%=context%>/preauthDetails.do?actionFlag=preauthDetailsEhf&printFlag=Y&caseId="+caseId+"&patientId="+patientId;	
			childWindow= window.open(url, '_blank','toolbar=no,resizable=yes,scrollbars=yes,width=900, height=700, top=50,left=50');
		}
	}
function generateCasePrint(caseId)
{
	var url="./preauthDetails.do?actionFlag=casePrintForm&caseId="+caseId;
	childWindow= window.open(url, '_blank','toolbar=no,resize=yes,scrollbars=yes,width=900, height=700, top=50,left=50');
}

//Ajax calls
function fn_getICDCatList()
{
	if(document.forms[0].mainCatName.value == null || document.forms[0].mainCatName.value =='' || document.forms[0].mainCatName.value=='-1' )
	{
		if(document.forms[0].mainCatName.value=='-1' )
		{
			document.forms[0].procName.options.length=0;
			document.forms[0].procName.options[0]=new Option("--select--","-1");
			document.forms[0].procName.options.length=0;
			document.forms[0].procName.options[0]=new Option("--select--","-1");
		}
		return ;
	}
	
	
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
	 url = '/<%=context%>/preauthDetails.do?actionFlag=getIcdCatList&catId='+document.forms[0].mainCatName.value+'&callType=Ajax';
	 if(document.forms[0].hospId)
		url=url+'&hospId='+document.forms[0].hospId.value;
	 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {	
		    	    var resultArray=xmlhttp.responseText;
			        var resultArray = resultArray.split("*");
			    	if(resultArray[0]=="SessionExpired"){
			    		//alert("Session has been expired");
			    		// parent.sessionExpireyClose();
			    		 var fr = partial(parent.sessionExpireyClose);
			    		 jqueryInfoMsg('Session details',"Session has been expired",fr);
			    		}
			    		else
			    		{
			        if(resultArray[0]!=null)
			        {	
			            resultArray1 = resultArray[0].replace("[","");
			            resultArray1 = resultArray1.replace("]","");            
			            var catList = resultArray1.split(", @"); 
			            
			                       
			          //  var serviceList = resultArray.split(", @"); 
			            
			        }
			        if(resultArray[1]!=null)
			        {	
			            resultArray2 = resultArray[1].replace("[","");
			            resultArray2 = resultArray2.replace("@]","");            
	                	var doctorList = resultArray2.split("@,"); 
			           // alert(doctorList);
			                       
			          //  var serviceList = resultArray.split(", @"); 
			            
			        }
					
		            if(catList.length>0)
		            {   
						if(document.forms[0].catName.options!=null){  
							document.forms[0].catName.options.length=0;
							document.forms[0].catName.options[0]=new Option("--select--","-1");
							document.forms[0].catName.options.length=0;
							document.forms[0].catName.options[0]=new Option("--select--","-1");
							
						}
						
						 for(var i = 0; i<catList.length;i++)
			                {	
			                    var arr=catList[i].split("~");
			                     if(arr[1]!=null && arr[0]!=null)
			                     {
			                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
			                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
			                         document.forms[0].catName.options[i+1] =new Option(val1,val2);
							   }
			                     else
				                    {
				                    	document.forms[0].catName.options[0]=new Option("--select--","-1");
										
				                    }
			                }
						
		            }
		            // get treating doctor list

		            
		    }}}
	 xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
}
// get Procedures

function fn_getProcedures()
{
	if(document.forms[0].catName.value == null || document.forms[0].catName.value =='' || document.forms[0].catName.value=='-1' )
		{
		return ;
		}
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
	 url = '/<%=context%>/preauthDetails.do?actionFlag=getProcList&IcdcatId='+document.forms[0].catName.value+'&asriCode='+document.forms[0].mainCatName.value+'&callType=Ajax';
	 if(document.forms[0].hospId)
		url=url+'&hospId='+document.forms[0].hospId.value;
	 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {	
		    	 var resultArray=xmlhttp.responseText;
			        var resultArray = resultArray.split("*");
			        if(resultArray[0]=="SessionExpired"){
				    	var fr = partial(parent.sessionExpireyClose);
			    		 jqueryInfoMsg('Session details',"Session has been expired",fr);
				    	}
				    	else
				    	{
			        if(resultArray[0]!=null)
			        {	
			            resultArray1 = resultArray[0].replace("[","");
			            resultArray1 = resultArray1.replace("]","");            
			            var procList = resultArray1.split(", @"); 
			        }
					
		            if(procList.length>0)
		            {   
						if(document.forms[0].procName.options!=null){  
							document.forms[0].procName.options.length=0;
							document.forms[0].procName.options[0]=new Option("--select--","-1");
						}
						 for(var i = 0; i<procList.length;i++)
			                {	
			                    var arr=procList[i].split("~");
			                     if(arr[1]!=null && arr[0]!=null)
			                     {
			                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
			                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
			                         document.forms[0].procName.options[i+1] =new Option(val1,val2);
							   }
			                     else
				                    {
				                    	document.forms[0].procName.options[0]=new Option("--select--","-1");
										
				                    }
			                }
		            }
		    	
		    }
		}// end of if
		}
	 xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
}


// end of procedures

</script>
</head>

<body onload="javascript:fn_removeLoadingImage();">

<html:form  method="post"  action="/casesSearchAction.do" > 
<div id="viewDtlsID" > 
	<div id="popupRaiseComplaint" style="position: absolute; width: 825px; height:300px; overflow:hidden;display:none" >
    <a id="popupRaiseCloseComplaint" onclick="disablePopup('#popupRaiseComplaint');">X</a>
    <iframe  id="complaintFrame" width="100%" height="100%" frameborder="no" scrolling="no" > </iframe>
    </div> 
    </div>
    <div id="backgroundPopup" onclick="disablePopup('#popupRaiseComplaint');"></div>	
<table border="0" width="95%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;" >
<tr><td>

	<table  border="0" width="100%" >
	<c:if test="${disSearchType eq 'Y'}">
	<tr align="center">
	<td   align="center"  class="tbheader" ><b>Cases For Discussion</b></td></tr>
	</c:if>
	<c:if test="${errSearchType ne 'Y' && disSearchType ne 'Y'}">
	<logic:equal value="Y" property="casesForApprovalFlag" name="casesSearchFormClaims">
	<tr align="center">
	<c:if test="${module eq 'claim'}">
	<td   align="center"  class="tbheader" ><b>Claim Cases For Approval </b></td>
	</c:if>
	<c:if test="${module eq 'preauth'}">
	<td   align="center"  class="tbheader" ><b>Preauth Cases For Approval </b></td>
	</c:if>
	</tr>
	</logic:equal>
	<logic:notEqual value="Y" property="casesForApprovalFlag" name="casesSearchFormClaims"><tr align="center">
	<td   align="center"  class="tbheader" ><b> <fmt:message key="label.caseSearch.caseSearchTitle" /> </b></td></tr>
	</logic:notEqual>
	</c:if>
	<c:if test="${errSearchType eq 'Y' && disSearchType ne 'Y'}">
	<tr align="center">
	<td  align="center"  class="tbheader" ><b>Erroneous Cases For Approval </b></td></tr>
	</c:if>
	</table>
</td></tr>
<tr><td>
	<table width="100%" class="tb">
	<tr><td>
	<table width="100%" >
	<tr>
		<c:if test="${disSearchType eq 'Y'}">
		<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="label.caseSearch.claimStatus" /></b></td>
		<td width="16%" class="tbcellBorder"><html:select  name="casesSearchFormClaims" property="claimId" styleId="claimId" style="width:200px; " onmousemove="javascript:getTitles('claimId')" title="Please select claim status">
		<option  value="">----Select----</option>
		<option  value="CD119">Claim Kept for Discussion by CTD</option>
		<option  value="CD150">Claim Kept for Discussion by CH</option>
		</html:select></td>
		</c:if>
		<c:if test="${errSearchType ne 'Y' && disSearchType ne 'Y' }">
		<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="label.caseSearch.claimStatus" /></b></td>
		<td width="16%" class="tbcellBorder"><html:select  name="casesSearchFormClaims" property="claimId" styleId="claimId" style="width:200px; " onmousemove="javascript:getTitles('claimId')" title="Please select claim status">
		<option  value="">----Select----</option>
		<html:options collection="StatusList" property="ID" labelProperty="VALUE"  />
		</html:select></td>
		</c:if>
		<c:if test="${errSearchType eq 'Y' && disSearchType ne 'Y'}">
		<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="label.caseSearch.ErrClaimStatus"/></b></td>
		<td width="16%" class="tbcellBorder"><html:select  name="casesSearchFormClaims" property="errStatusId" styleId="errStatusId"  style="width:200px;" onmousemove="javascript:getTitles('errStatusId')" title="Please select erroneous status">
		<option  value="">----Select----</option>
		<html:options collection="ErrStatusList" property="ID" labelProperty="VALUE"  />
		</html:select></td>
		</c:if>
		<!-- <td width="24%" class="Label"><fmt:message key="label.caseSearch.category" /></td><td width="24%" class="Label"><fmt:message key="label.caseSearch.errStatus" /></td> -->
		<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="label.caseSearch.caseNo" /></b></td>
		<td width="16%" class="tbcellBorder"><html:text name="casesSearchFormClaims" property="caseNo" style="width:110px;" maxlength="50" title="Please enter complete case number Eg: If case number is CASE/EHS100/1234 ,Please enter 1234 as case number" onchange="javascript:checkBlankSpaces('caseNo','case number');" /></td>
		<td  width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="label.caseSearch.wapNo" /></b></td>
		<td width="16%" class="tbcellBorder"><html:text style="width:110px;" name="casesSearchFormClaims" property="wapNo" title="Enter Card Number" onchange="javascript:checkBlankSpaces('wapNo','card number');"/></td>
	</tr>
	
	<tr>
		<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="label.caseSearch.patientName" /></b></td>
		<td width="16%" class="tbcellBorder"><html:text name="casesSearchFormClaims" style="width:90%" property="patName" styleId="patName" maxlength="50" title="Enter Patient Name" onchange="javascript:chkOnlyAlpha('patName','patient Name');"/></td>
		<td width="16%" class="labelheading1 tbcellCss"><b>Registered From Date</b></td>
		<td width="16%" class="tbcellBorder"><html:text name="casesSearchFormClaims" property="fromDate" styleId="fromDate" title="Enter From Date" onchange="validateDate('From Date',this)"  onkeydown="validateBackSpace(event)" readonly="true" style="width:110px"/><!-- <img border='0' src='images/calend.gif' alt="Calendar" onClick="CalenderWindowOpen('fromDate','250','480')" title="Calendar"></img> --></td>
		<td width="16%" class="labelheading1 tbcellCss"><b>Registered To Date</b></td>
		<td width="16%" class="tbcellBorder"><html:text name="casesSearchFormClaims" property="toDate" styleId="toDate" title="Enter To Date" onchange="validateDate('To Date',this)"  onkeydown="validateBackSpace(event)" readonly="true" style="width:110px"/><!-- <img border='0' src='images/calend.gif' alt="Calendar" onClick="CalenderWindowOpen('toDate','250','740')" title="Calendar"></img> --></td>
	</tr>
	<tr>
		<td width="16%" class="labelheading1 tbcellCss"><b>Category</b></td>
		<td width="16%" class="tbcellBorder"><html:select  name="casesSearchFormClaims" property="mainCatName" styleId="mainCatName"  style="width:200px;" onmousemove="javascript:getTitles('mainCatName')" onchange="javascript:fn_getICDCatList()" title="Please select Category">
			<option  value="">----Select----</option>
			<html:options collection="catList" property="ID" labelProperty="VALUE"  />
			</html:select>
		</td>
		<td width="16%" class="labelheading1 tbcellCss"><b>ICD Category Name</b></td>
		<td width="16%" class="tbcellBorder"><html:select  name="casesSearchFormClaims" property="catName" styleId="catName"  style="width:150px;" onmousemove="javascript:getTitles('catName')" onchange="javascript:fn_getProcedures()" title="Please select ICD Category name">
			<option  value="">----Select----</option>
			<html:options collection="icdCatList" property="ICDCODE" labelProperty="ICDNAME"  />
			</html:select>
		</td>
		<td width="16%" class="labelheading1 tbcellCss"><b>Procedure Name</b></td>
		<td width="16%" class="tbcellBorder"><html:select  name="casesSearchFormClaims" property="procName" styleId="procName"  style="width:150px;" onmousemove="javascript:getTitles('procName')" title="Please select Procedure name">
			<option  value="">----Select----</option>
			<html:options collection="procList" property="ICDCODE" labelProperty="ICDNAME"  />
			</html:select>
		</td>
	</tr>
	<logic:equal value="N" property="casesForApprovalFlag" name="casesSearchFormClaims">
	<tr>	 
		<c:if test="${errSearchType ne 'Y'}">	
				<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="label.caseSearch.ErrClaimStatus"/></b></td>
				<td width="16%" class="tbcellBorder"><html:select  name="casesSearchFormClaims" property="errStatusId" styleId="errStatusId"  style="width:150px;" onmousemove="javascript:getTitles('errStatusId')" title="Please select erroneous status">
					<option  value="">----Select----</option>
					<html:options collection="ErrStatusList" property="ID" labelProperty="VALUE"  />
					</html:select>
				</td>
				<td width="16%" class="labelheading1 tbcellCss"><b>Hospitals</b></td>
				<td width="16%" class="tbcellBorder"><html:select  name="casesSearchFormClaims" property="hospId" styleId="hospId"  style="width:150px;" onmousemove="javascript:getTitles('hospId')" title="Please select erroneous status">
				<option  value="">----Select----</option>
				<html:options collection="HospList" property="ID" labelProperty="VALUE"  />
				</html:select></td></c:if>
				<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="label.tele.telephonicId" /></b></td>
		        <td width="16%" class="tbcellBorder"><html:text name="casesSearchFormClaims" property="telephonicId" style="width:90%" maxlength="50" title="Please enter Telephonic Intimation Id" onchange="javascript:checkBlankSpaces('telephonicId','Telephonic Id');" /></td>	
	</tr>
	<tr>
	 <logic:equal name="casesSearchFormClaims"  property="showScheme" value="show">
	    <td width="16%" class="labelheading1 tbcellCss"><b>Scheme</b></td>
		<td width="16%" class="tbcellBorder"><html:select property="schemeType"  styleId="schemeType"  name="casesSearchFormClaims" style="width:150px;" onmousemove="javascript:getTitles('schemeType')" title="Please select Scheme">
						<html:option value="CD201">Andhra</html:option>
						<html:option value="CD202">Telangana</html:option> 
						<html:option value="CD203">Both</html:option>
						</html:select></td>
						</logic:equal>
	</tr>
	</logic:equal>
	<logic:equal value="Y" property="casesForApprovalFlag" name="casesSearchFormClaims">
	<tr>	 
				<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="label.tele.telephonicId" /></b></td>
		        <td width="16%" class="tbcellBorder"><html:text name="casesSearchFormClaims" property="telephonicId" style="width:90%" maxlength="50" title="Please enter Telephonic Intimation Id" onchange="javascript:checkBlankSpaces('telephonicId','Telephonic Id');" /></td>
		        <logic:equal name="casesSearchFormClaims"  property="showScheme" value="show">
	    <td width="16%" class="labelheading1 tbcellCss"><b>Scheme</b></td>
		<td width="16%" class="tbcellBorder"><html:select property="schemeType" name="casesSearchFormClaims" style="width:150px;" onmousemove="javascript:getTitles('schemeType')" title="Please select Scheme">
						<html:option value="CD201">Andhra</html:option>
						<html:option value="CD202">Telangana</html:option> 
						<html:option value="CD203">Both</html:option> 
						</html:select></td>
						</logic:equal>	
	</tr>
	</logic:equal>
	<tr><td>&nbsp;</td>
	</tr>	
	<tr><td align="center" colspan="6">
	<button class="but"   type="button" name="Search" value="Search" onclick="javascript:fn_caseSearch()"  >Search</button>
	<button class="but"   type="button" name="Reset" value="Reset" onclick="javascript:fn_reset()"  >Reset</button>
	</td>
	</tr></table></td></tr>
	<!-- <tr><td width="4%"></td>
	<td>Case No</td><td>Claim No</td><td>Patient Name</td>
	</tr>
	<tr><td width="4%"></td>
	<td><html:text name="casesSearchFormClaims" property="caseNo" /></td>
	<td><html:text name="casesSearchFormClaims" property="claimNo" /></td>
	<td><html:text name="casesSearchFormClaims" property="patName" /></td>
	</tr>
	<tr><td>&nbsp;</td></tr>-->
	</table>
</td></tr>

</table>
<bean:size id="lstCaseSearchSize" name="casesSearchFormClaims"  property="lstCaseSearch"/>
<logic:greaterThan value="0" name="lstCaseSearchSize">
<c:if test="${errSearchType ne 'Y'}">
<table width="95%" border="0" align="center" style="padding-top:0px;margin:0px auto;">
<tr><td>&nbsp;</td></tr>
<tr>
<td>
<img src="images/button_reddishPink.png" width="12" height="12"></img>
 &nbsp;&nbsp;NAM Preauth Forwarded , MEDCO Preauth updated
</td>
<td>
<img src="images/grey_button.png" width="12" height="12"></img>
 &nbsp;&nbsp;NAM Preauth Forwarded (6 hours or more)
</td>
<td>
<img src="images/green_button.png" width="12" height="12"></img>
 &nbsp;&nbsp;PTD Preauth pending (6 hours or more)
</td>
</tr>

</table>
</c:if>
</logic:greaterThan>

<table  border="0" width="95%"  cellpadding="0" cellspacing="0" align="center" style="padding-top:0px;margin:0px auto;">
<br></br>
<logic:greaterThan value="0" name="lstCaseSearchSize">
<tr>
<td width="10%">Showing Results</td>
<td width="10%"><bean:write name="casesSearchFormClaims" property="startIndex" />  - <bean:write name="casesSearchFormClaims" property="endIndex" /> 
of <bean:write name="casesSearchFormClaims" property="totalRows" /> </td>
<td  width="7%">Show Page</td>
<td width="30%">
<c:forEach items="${pages}" var="page" >
<c:if test="${liPageNo eq page}" >
<c:out value="${page}" /> &nbsp;
</c:if>
<c:if test="${liPageNo ne page}">
<c:if test="${page lt 11}" >
<a href="javascript:showPagination('<c:out value="${page}" />')"> <c:out value="${page}" /></a> &nbsp;
</c:if>
</c:if>
</c:forEach>
<logic:notEmpty name="casesSearchFormClaims" property="prev">
<img src="images/left2.png" onclick="javascript:showPagination('<bean:write name="casesSearchFormClaims" property="prev" />')"/>
</logic:notEmpty>
<logic:notEmpty name="casesSearchFormClaims" property="next">
<img src="images/right2.png" onclick="javascript:showPagination('<bean:write name="casesSearchFormClaims" property="next" />')"/>
</logic:notEmpty>

</td>

<td width="10%">
Show in sets of
</td>
<td width="17%">
<c:set var="ListNoSet" value="10,20,50,100,1000"/> 
<c:forEach var="set" items="${ListNoSet}"  >
<c:if test="${lStrRowsperpage eq set }" >
${set} &nbsp;
</c:if>
<c:if test="${lStrRowsperpage ne set }" >
<a href="javascript:showinSetsOf('<c:out value="${set}" />')"> <c:out value="${set}" /> </a> &nbsp;
</c:if>
</c:forEach>
</td>
</logic:greaterThan>
<td width="16%" align="right">
<!--<img src="images/excel1.png"  onmouseover="this.src='images/excel2.png'" onmouseout="this.src='images/excel1.png'" onclick="javascript:exportToExcel()"/> -->
<img src="images/csv1.png"  onmouseover="this.src='images/csv2.png'" onmouseout="this.src='images/csv1.png'" onclick="javascript:exportToCSV()"/>
</td>
</tr>
</table>
<logic:greaterThan value="0" name="lstCaseSearchSize">
<table  width="98%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;" class="tb">
	<tr>
	<th class="tbheader1" width="12%" valign="top"><fmt:message key="label.caseSearch.caseNo" /></th>
	<th class="tbheader1" width="10%" valign="top"><fmt:message key='label.caseSearch.claimNo' /></th>
	<th class="tbheader1" width="15%" valign="top"><fmt:message key="label.caseSearch.patientName" /></th>
	<th class="tbheader1" width="10%" valign="top"><fmt:message key="label.caseSearch.wapNo" /></th>
	<th class="tbheader1" width="10%" valign="top"><fmt:message key="label.caseSearch.claimStatus" /></th>
	<th class="tbheader1" width="10%" valign="top">Hospital Name</th>
<!-- 	<th class="tbheader1" width="10%" valign="top">Hospital Name</th> -->
	
	<th class="tbheader1" width="10%" valign="top"><fmt:message key="label.caseSearch.regdate" /></th>
	<th class="tbheader1" width="7%" valign="top"><fmt:message key="label.caseSearch.claimAmt" /></th>
	<c:if test="${errSearchType eq 'Y'}">
	<th class="tbheader1" width="10%" valign="top"><fmt:message key="label.caseSearch.ErrClaimStatus"/></th>
	</c:if>
	<c:if test="${errSearchType ne 'Y'}">
	<logic:notEqual value="Y" property="casesForApprovalFlag" name="casesSearchFormClaims">
	<th class="tbheader1" width="10%" valign="top"><fmt:message key="label.caseSearch.ErrClaimStatus"/></th>
	</logic:notEqual></c:if>
	<th class="tbheader1" width="4%" valign="top">Case Form</th>
	<th class="tbheader1" width="4%" valign="top">PRF Form</th>
	</tr>
    <logic:iterate id="result" name="casesSearchFormClaims"  property="lstCaseSearch" indexId="index">
	<tr  class="border${result.colorFlag}" > 
	<logic:equal value="Y" property="casesForApprovalFlag" name="casesSearchFormClaims">
	<td class="tbcellBorder" width="12%" style="word-wrap:break-word;padding:3px;" nowrap="nowrap">
    <img id="patDtlsImage" src="images/file1.png" height="15" width="15" style=cursor:pointer; title="Click to View Case Details" alt="Case Details" align="top" onclick="javascript:fn_getPatFullDetails('<bean:write name="result" property="caseId" />')" ></img>
    <a href="javascript:fn_caseApprovalAjax('<bean:write name="result" property="caseId" />','','<bean:write name="result" property="patIpOp" />');"><bean:write name="result" property="caseNo" /></a>
	<c:if test="${result.viewFlag eq 'Y' && is_medco_mithra ne 'Y'}">
	<img src="images/lock1.png" height="18" width="18" alt="this case is view by other" title="this case is viewed by other"/>
	</c:if>
	<c:if test="${result.teleStatus ne '' && result.teleStatus ne null}">
	<img src="images/telephone.png" height="23" width="23" alt="This is a Telephonic Registered case" title="This is a Telephonic Registered case"/>
	</c:if>
	<c:if test="${result.grievanceFlag ne '' && result.grievanceFlag ne null}">
	<img src="images/InspectionAttachmentFlag.gif" height="18" width="18" alt="Grievance has been raised against this case" title="Grievance has been raised against this case"/>
	</c:if>		
	</td>
	</logic:equal>
	<logic:notEqual value="Y" property="casesForApprovalFlag" name="casesSearchFormClaims">
	<td class="tbcellBorder" width="12%" style="word-wrap:break-word;padding:3px;" nowrap="nowrap" >
	
	 <img id="patDtlsImage" src="images/file1.png" height="15" width="15" style=cursor:pointer; title="Click to View Case Details" alt="Case Details" align="top" onclick="javascript:fn_getPatFullDetails('<bean:write name="result" property="caseId" />')" ></img>
    <a href="javascript:fn_caseApproval('<bean:write name="result" property="caseId" />','','<bean:write name="result" property="patIpOp" />');"><bean:write name="result" property="caseNo" /></a>
	<c:if test="${result.teleStatus ne '' && result.teleStatus ne null}">
	<img src="images/telephone.png" height="23" width="23" alt="This is a Telephonic Registered case" title="This is a Telephonic Registered case"/>
	</c:if>
	<c:if test="${result.grievanceFlag ne '' && result.grievanceFlag ne null}">
	<img src="images/InspectionAttachmentFlag.gif" height="18" width="18" alt="Grievance has been raised against this case" title="Grievance has been raised against this case"/>
	</c:if> 
	</td>
	</logic:notEqual>
	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="claimNo" /></td>
	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="patientName" /></td>
	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="wapNo" /></td>
	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="claimStatus" /></td>
     <td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="hospName" /></td> 
	
	
	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="inpatientCaseSubDt" /></td>
	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="claimAmt" /></td>
	<c:if test="${errSearchType eq 'Y'}">
	<td  class="tbcellBorder" width="13%" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="errStatus" /></td>
	</c:if>
	<c:if test="${errSearchType ne 'Y'}">
	<logic:notEqual value="Y" property="casesForApprovalFlag" name="casesSearchFormClaims">
	<td class="tbcellBorder" width="13%" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="errStatus" /></td>
	</logic:notEqual></c:if>
	<logic:equal value="IP" name="result" property="patIpOp" >
		<td class="tbcellBorder" width="4%"><a href="javascript: generateCasePrint('<bean:write name="result" property="caseNo" />')"><img src="images/print.gif" height="18" width="18" border="0" alt="Case Generated Print Form" title="Case Generated Print Form"/></a></td>
		<td class="tbcellBorder" width="4%"><a href="javascript: printprfForm('<bean:write name="result" property="caseId" />','<bean:write name="result" property="caseStatusId" />','<bean:write name="result" property="patientId" />','<bean:write name="result" property="pendingFlag" />','<bean:write name="result" property="inpatientCaseSubDt" />')"><img src="images/print.gif" height="18" width="18" border="0" alt="Print PRF Form" title="Print PRF Form"/></a></td>
	</logic:equal>
	<logic:notEqual value="IP" name="result" property="patIpOp" >
		<td width="3%">&nbsp;</td>
	</logic:notEqual>
	</tr>
</logic:iterate>
</table>

</logic:greaterThan>
<logic:equal value="0" name="lstCaseSearchSize">
<table width="50%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:1px auto;" class="tb">
<tr >
<td align="center" height="50">
<b>No results found</b>
</td>
</tr>
</table>
</logic:equal>
<div id="processImagetable" style="top:50%;width:100%;position:absolute;z-index:60;height:100%">
   <table border="0" align="center" width="100%" style="height:400" >
      <tr>
         <td>
           <div id="processImage" align="center">
             <img src="images/Progress.gif" width="100"
                     height="100" border="0"></img>
            </div>
          </td>
        </tr>
     </table>
</div>
<html:hidden property="rowsPerPage"  name="casesSearchFormClaims"/>
<html:hidden property="startIndex" name="casesSearchFormClaims" />
<html:hidden property="showPage" name="casesSearchFormClaims" />
<html:hidden property="totalRows" name="casesSearchFormClaims" />
<html:hidden property="casesForApprovalFlag" name="casesSearchFormClaims" />
<html:hidden property="showScheme" name="casesSearchFormClaims" />
</html:form>
<script type="text/javascript">
function fn_getPatFullDetails(caseIdd){
	var url='/Operations/patCommonDtls.htm?actionFlag=getCaseDetails&CaseId='+caseIdd;
    document.getElementById('complaintFrame').src=url;
	centerPopup("#popupRaiseComplaint");
	loadPopup("#popupRaiseComplaint");
	document.getElementById('popupRaiseComplaint').style.top=document.documentElement.scrollTop+"px" ;	
	document.getElementById('popupRaiseComplaint').style.left="120px";
	//document.getElementById('popupRaiseComplaint').style.right="0px";
}
</script>
</body>
</fmt:bundle>
</html>