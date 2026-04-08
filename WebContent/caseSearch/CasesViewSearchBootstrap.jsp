<!DOCTYPE html>
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

<title>Cases Search</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<style type="text/css">.centerone{padding-left:6%;}</style>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="scripts/PreauthScripts.js"></script>
<script src="js/jquery.msgBox.js"></script>
<script src="bootstrap/js/bootbox.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>  
<%@ include file="/common/editableComboBox.jsp"%>  
<%@ include file="/common/includePatientDetails.jsp"%>  
<style> #ui-id-4{ width:15%; }#ui-id-6,#ui-id-3,#ui-id-1,#ui-id-2,#ui-id-5 { width:35%; }
  .custom-combobox-input {    margin: 0;    padding: 0.3em; background:#fff;border:1px solid #e6e6e6; }
body{font-size:1.2em !important;}
</style>
<script>
var searchType='${caseSearchType}';
var errSearchType='${errSearchType}';
var denErrSearchType='${denErrSearchType}';
var disSearchType='${disSearchType}';
var module='${module}';
var patientScheme="${patientScheme}";
var diaPendFlg="${diaPendFlg}";
var caseSearchURl='';
function focusBox(id)
{
	aField=id;
	setTimeout("aField.focus()",0);
}
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

	fn_loadImage();
	if(parent.parent.globalURl==''){
		 caseSearchURl="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errDentalSearchType='+denErrSearchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&diaPendFlg='+diaPendFlg+'&module='+module+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value;
		 parent.setGlobalUrl(caseSearchURl);
	}
	//parent.fn_resizePage2(470);
	document.forms[0].action='/<%=context%>/casesApprovalAction.do?actionFlag=getCaseDtls&CaseId='+caseId+'&status='+arg+'&caseSearchType='+searchType+'&errDentalSearchType='+denErrSearchType+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value+'&ipOpType='+ipOpType+'&diaPendFlg='+diaPendFlg+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module;	
	document.forms[0].submit(); 
}
function fn_caseApprovalAjax(caseId,arg,ipOpType)
{	

	fn_loadImage();
	if(parent.parent.globalURl==''){
		 caseSearchURl="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errSearchType='+errSearchType+'&errDentalSearchType='+denErrSearchType+'&disSearchType='+disSearchType+'&diaPendFlg='+diaPendFlg+'&module='+module+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value;
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

		    	document.forms[0].action='/<%=context%>/casesApprovalAction.do?actionFlag=getCaseDtls&CaseId='+caseId+'&status='+arg+'&caseSearchType='+searchType+'&errDentalSearchType='+denErrSearchType+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value+'&ipOpType='+ipOpType+'&diaPendFlg='+diaPendFlg+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module;	
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

function viewPreviousPages(pageNo,noOfPages,selectedPage)
{
	var pageDisplays='';
	var pageNoLim=pageNo;
	var minPageNo=pageNo-10;
	var action='link';
	var i=0;
	pageDisplays=pageDisplays+'<ul class="pagination">';
	if(minPageNo>0)
	{
		pageDisplays=pageDisplays+'<li> <span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages('+minPageNo+','+noOfPages+','+selectedPage+')" style="cursor: pointer;"></span></li>';
	}
	else
	{
		minPageNo=minPageNo+1;
	}
	for(i=minPageNo;i<pageNoLim;i++)
	{
		if(selectedPage==i)
		{
			pageDisplays=pageDisplays+' <li class="active"><a href="javascript:showPagination('+i+')">'+i+'</a></li>';
		}
		else
		{
			pageDisplays=pageDisplays+' <li><a href="javascript:showPagination('+i+')">'+i+'</a></li>';
		}
		
	}
	pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+')" style="cursor: pointer;"></span></li></ul>';
	document.getElementById("pageNoDisplay").innerHTML=pageDisplays;
}
function viewNextPages(pageNo,noOfPages,selectedPage)
{
	var pageDisplays='';
	var pageNoLim=pageNo+10;
	var action='link';
	var i=0;
	pageDisplays=pageDisplays+'<ul class="pagination">';
	if(pageNoLim>noOfPages)
	{
		pageNoLim=noOfPages+1;
	}
	
	pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages('+pageNo+','+noOfPages+','+selectedPage+')" style="cursor: pointer;"></span></li>';

	for(i=pageNo;i<pageNoLim;i++)
	{
		if(selectedPage==i)
		{
			pageDisplays=pageDisplays+'<li class="active"><a href="javascript:showPagination('+i+')">'+i+'</a></li>';
		}
		else
		{
			pageDisplays=pageDisplays+'<li> <a href="javascript:showPagination('+i+')">'+i+'</a></li>';
		}
		
	}
	if(i<noOfPages)
	{
		pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+')" style="cursor: pointer;"></span></li></ul>';
		//pageDisplays=pageDisplays+'<a href="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+')">Next</a>';
		
	}
	document.getElementById("pageNoDisplay").innerHTML=pageDisplays;
}




function showinSetsOf(num)
	{
	document.forms[0].rowsPerPage.value=num; 
	document.forms[0].showPage.value='1'; 
	caseSearch();
	}
function caseSearch()
	{

		document.forms[0].action="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errDentalSearchType='+denErrSearchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value+'&diaPendFlg='+diaPendFlg+'&patientScheme='+patientScheme;

         document.forms[0].submit();
	}
function exportToExcel()
	{
		fn_blockScreen();
		
		document.forms[0].action="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&excelFlag=Y&caseSearchType="+searchType+'&errDentalSearchType='+denErrSearchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module+'&diaPendFlg='+diaPendFlg+'&patientScheme='+patientScheme;
		
	    document.forms[0].submit();
	}
function fn_alreadyClicked()
	{
		bootbox.alert("CSV Download has already been requested once.Please check the respective CSV File in Downloads Tab ");
		return false;
	}
function fn_alreadyDownloaded()
	{
		bootbox.alert("CSV File has already been downloaded once.");
		return false;
	}
function exportToCSV()
	{
		fn_blockScreen();
		var id=document.getElementById("csvImg");
		id.onclick=fn_alreadyClicked;
		var totalRows='${casesSearchFormClaims.totalRows}';
		var ajaxFlag='N';
		if(totalRows!=null)
			{
				if(Number(totalRows)>5000)
					{
						ajaxFlag='Y';			
					}
			}
		if(ajaxFlag=='Y')
			{
				var url="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&excelFlag=Y&csvFlag=Y&caseSearchType="+searchType+'&errDentalSearchType='+denErrSearchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module+'&patientScheme='+patientScheme+'&ajaxFlag=Y'+'&claimId='+document.forms[0].claimId.value;
			
				var xmlhttp;
				if(window.XMLHttpRequest)
					xmlhttp=new XMLHttpRequest();
				else if(window.ActiveXObject)
					xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
				else 
					alert('Your browser does not Support XMLHttp(Ajax)');
				
				xmlhttp.onreadystatechange=function()
					{
						if(xmlhttp.readyState==4)
							{
							     var result=xmlhttp.responseText;
								 var $modal = $('#progressBar'),
								 $bar = $modal.find('.progress-bar progress-bar-striped active');
								 $bar.removeClass('animate');
								 $modal.modal('hide');
								 bootbox.alert(result);
							}
					}
				xmlhttp.open("post",url,true);
				xmlhttp.send(null);
			}
		else
			{
				fn_blockScreen();
				var id=document.getElementById("csvImg");
				id.onclick=fn_alreadyDownloaded;
				document.forms[0].action="/Operations/casesSearchAction.do?actionFlag=casesSearchCSV&searchFlag=Y&excelFlag=Y&csvFlag=Y&caseSearchType="+searchType+'&errDentalSearchType='+denErrSearchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module+'&patientScheme='+patientScheme+'&ajaxFlag=N';
			    document.forms[0].submit();
			}
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



				 document.forms[0].action="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&diaPendFlg='+diaPendFlg+'&module='+module+'&patientScheme='+patientScheme;
				 caseSearchURl="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&errDentalSearchType='+denErrSearchType+'&module='+module
				 +'&errStatusId='+document.forms[0].errStatusId.value+'&caseNo='+document.forms[0].caseNo.value+'&patName='+document.forms[0].patName.value+
				 '&fromDate='+document.getElementById("fromDate").value+'&toDate='+document.getElementById("toDate").value+'&wapNo='+document.forms[0].wapNo.value+'&mainCatName='+document.forms[0].mainCatName.value+
				 '&catName='+document.forms[0].catName.value+'&procName='+document.forms[0].procName.value+'&telephonicId='+document.forms[0].telephonicId.value+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value+'&patientScheme='+patientScheme;

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
	 document.forms[0].action="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errSearchType='+errSearchType+'&errDentalSearchType='+denErrSearchType+'&diaPendFlg='+diaPendFlg+'&disSearchType='+disSearchType+'&module='+module+'&patientScheme='+patientScheme;
	 caseSearchURl="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module
	 +'&claimId='+document.forms[0].claimId.value+'&caseNo='+document.forms[0].caseNo.value+'&patName='+patientName+
	 '&fromDate='+document.getElementById("fromDate").value+'&toDate='+document.getElementById("toDate").value+'&wapNo='+document.forms[0].wapNo.value+'&mainCatName='+document.forms[0].mainCatName.value+
	 '&catName='+document.forms[0].catName.value+'&procName='+document.forms[0].procName.value+'&telephonicId='+document.forms[0].telephonicId.value+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value+'&patientScheme='+patientScheme;
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
						 document.forms[0].action="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&module='+module+'&diaPendFlg='+diaPendFlg+'&patientScheme='+patientScheme;
						 caseSearchURl="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errDentalSearchType='+denErrSearchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&diaPendFlg='+diaPendFlg+'&module='+module
						 +'&claimId='+document.forms[0].claimId.value+'&errStatusId='+document.forms[0].errStatusId.value+'&caseNo='+document.forms[0].caseNo.value+'&patName='+document.forms[0].patName.value
						 +'&hospId='+document.forms[0].hospId.value+'&fromDate='+document.getElementById("fromDate").value+'&toDate='+document.getElementById("toDate").value+'&wapNo='+document.forms[0].wapNo.value+'&mainCatName='+document.forms[0].mainCatName.value+
						 '&catName='+document.forms[0].catName.value+'&procName='+document.forms[0].procName.value+'&telephonicId='+document.forms[0].telephonicId.value+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value+'&schemeType='+document.forms[0].schemeType.value+'&patientScheme='+patientScheme;
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
				 document.forms[0].action="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errSearchType='+errSearchType+'&diaPendFlg='+diaPendFlg+'&errDentalSearchType='+denErrSearchType+'&disSearchType='+disSearchType+'&module='+module+'&patientScheme='+patientScheme;
				 caseSearchURl="/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&searchFlag=Y&caseSearchType="+searchType+'&errSearchType='+errSearchType+'&disSearchType='+disSearchType+'&diaPendFlg='+diaPendFlg+'&module='+module
				 +'&claimId='+document.forms[0].claimId.value+'&errStatusId='+document.forms[0].errStatusId.value+'&caseNo='+document.forms[0].caseNo.value+'&patName='+document.forms[0].patName.value
				 +'&hospId='+document.forms[0].hospId.value+'&fromDate='+document.getElementById("fromDate").value+'&toDate='+document.getElementById("toDate").value+'&wapNo='+document.forms[0].wapNo.value+'&mainCatName='+document.forms[0].mainCatName.value+
				 '&catName='+document.forms[0].catName.value+'&procName='+document.forms[0].procName.value+'&telephonicId='+document.forms[0].telephonicId.value+'&casesForApproval='+document.forms[0].casesForApprovalFlag.value+'&patientScheme='+patientScheme;
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
	 url = '/<%=context%>/preauthDetails.do?actionFlag=getProcList&IcdcatId='+document.forms[0].catName.value+'&asriCode='+document.forms[0].mainCatName.value+'&callType=Ajax+';
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


var casesSelected=[];
var cases;
function fn_checkAll()
{

	var elements=document.getElementsByClassName("selectableCheckbox");
    casesSelected=[];
	var count=0;
	var count2=1;
	 var countTemp=0;
    count=elements.length;
	for (i=1;i<elements.length;i++)
	{
                 if(document.getElementById("checkAll").checked==true)
                    {
	        			  elements[i].checked=true;
							if(count>count2)
	        		        casesSelected[i]=elements[i].value;
							countTemp++;

						    if(countTemp==1)
							{
								cases=casesSelected[i];
							}
							else
							{
								cases=cases+"~"+casesSelected[i];
								
							}
	        		      
        			}
                else
				    {
				    	  elements[i].checked=false;
					      casesSelected[i]=null;
					      cases=null;
					      
				    }
				    count2++;
        		}
			
 
//alert(cases);
if(document.getElementById("checkAll").checked==true)
{
document.getElementById("verifyBut").focus();
}	
}

/* function checkOrUncheckCase()
{
var elements=document.getElementsByTagName("input");
	
	var count=0;
	var count2=0;
    casesSelected=[];

	for (i=0;i<elements.length;i++)
	{
		
	if(elements[i].type=="checkbox" && elements[i].checked==true)
	{
		
			casesSelected[i]=elements[i].value;

			count++;

		    if(count==1)
			{
				cases=casesSelected[i];
			}
			else
			{
				cases=cases+"~"+casesSelected[i];
			}
		
	
     }
    
	
	}

	//alert(cases);
	//document.forms[0].caseSelected.value=casesSelected;
	//alert(document.forms[0].caseSelected.value);

	
} */
//vijay
 function checkOrUncheckCase()
{
var elements=document.getElementsByTagName("input");
	var count=0;
	var count2=0;
	var otherElements=0;
    casesSelected=[];
    var checkMissCasesSelected= "N";
	var NoofRecords = '${lStrRowsperpage}';
	var totalRecords = '${casesSearchFormClaims.totalRows}';
	var iterator = "";
	var count = 0;
	var checkMissCasesSelected= "N";
	if(NoofRecords == 'ALL'){
		iterator = totalRecords;
	}
	else if(parseInt(NoofRecords)>parseInt(totalRecords)){
		iterator = totalRecords;
	}
	else{
		iterator = NoofRecords;
	}
	for(var k=1;k<=iterator;k++)
	{
		if(document.getElementById('CheckBox'+k) != null)
		{
			if(document.getElementById("CheckBox"+k).checked)
			{
				if(checkMissCasesSelected=='Y'){
					bootbox.alert("Please select checkboxes in FIFO order");
				 	$("#checkAll").each(function() {
						$(this).css("background-color", "");
					}); 
					$('input:checkbox').prop('checked', false);
					return false;
				}
				
			} 
			else
			{
				checkMissCasesSelected="Y";
				
			}
		}
	}
	
	for (i=0;i<elements.length;i++)
	{
		
	if(elements[i].type=="checkbox" && elements[i].checked==true)
	{
		
			casesSelected[i]=elements[i].value;

			count++;

		    if(count==1)
			{
				cases=casesSelected[i];
			}
			else
			{
				cases=cases+"~"+casesSelected[i];
			}
		
	
     }
    
	
	}

	//alert(cases);
	document.forms[0].caseSelected.value=casesSelected;
	//alert(document.forms[0].caseSelected.value);

}



function fn_buttonClicked(buttonId,confirmMsg)
{	
	
	//disableDiv(document.getElementById('buttonBlock'));
	var errFlag='${errSearchType}';
		var count=0;
		var module='${module}';
		var patientScheme='${patientScheme}';
		if(casesSelected!=null)
		var length=casesSelected.length;
var IDSel;
if(length!=null)
{
for(i=0;i<length;i++)
{
if(casesSelected[i]!=null && casesSelected[i]!="")
{
	IDSel=casesSelected[i];
	count++;
}
}
}

		if(count==0)
{
			alert("No cases are Selected.please select and try Again");
return false;
}
	

	if(module!='cochlearAco')
		{
		if(confirm("Do You Wish to Verify Selected Cases ?"))
		{
			document.getElementById('verifyBut').disabled=true;	
			fn_loadImage();
        document.forms[0].action="/Operations/casesSearchAction.do?actionFlag=ClaimsBulkApproval&acoFlag=Y&module=claim&errFlag="+errFlag+"&actionDone="+buttonId+"&cases="+cases+"&patientScheme="+patientScheme;
    	
		    document.forms[0].submit();
		}
		else
		{
return false;
		}
		}
	if(module=='cochlearAco')
		{
		if(confirm("Do You Wish to Verify Selected Cases ?"))
		{
			document.getElementById('verifyBut').disabled=true;	
			fn_loadImage();
        document.forms[0].action="/Operations/casesSearchAction.do?actionFlag=ClaimsBulkApproval&acoFlag=Y&module=cochlearAco&errFlag="+errFlag+"&actionDone="+buttonId+"&cases="+cases+"&patientScheme="+patientScheme;
    	
		    document.forms[0].submit();
		}
		else
		{
return false;
		}
		
		}
}

// end of procedures
	function showStatus()
	{
		
		
		
		var pagNo ='${liPageNo}';
		
		if(pagNo>1){
			 document.getElementById("verifyBut").disabled = true; 
			 /* document.getElemenyById('verifyBut').style.display='none'; */
		}
	}
</script>
</head>

<body onload="fn_removeLoadingImage();showStatus();">
<html:form  method="post"  action="/casesSearchAction.do" > 
<!-- Modal for patient details  -->  

<c:if test="${saveMsg ne null && saveMsg ne '' }">
<script>
alert('${saveMsg}');
</script>
</c:if>
<div class="modal fade" id="viewDtlsID"> 
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-body">
      	<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <iframe  id="complaintFrame" width="100%" height="300px" frameborder="no" scrolling="yes" > </iframe>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content --> 
  </div><!-- /.modal-dialog --> 
</div><!-- /.modal --> 

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
<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="tbheader">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" title="Click to search" style="color:#fff; display:block;">
         <span class="glyphicon glyphicon-plus"></span>
         	<c:if test="${disSearchType eq 'Y'}">
			<span><b>Cases For Discussion</b></span>
			</c:if>
			<c:if test="${errSearchType ne 'Y' && disSearchType ne 'Y' && denErrSearchType ne 'Y'}">
			<logic:equal value="Y" property="casesForApprovalFlag" name="casesSearchFormClaims">
			<c:if test="${module eq 'claim'}">
			<span><b>Claim Cases For Approval</b></span>
			</c:if>
			<c:if test="${module eq 'dentalErr'}">
			<span><b>Dental Erroneous Claim Cases For Approval</b></span>
			</c:if>
			<c:if test="${module eq 'claimJournal'}">
			<span><b>Journalists Claim Cases For Approval</b></span>
			</c:if>
			<c:if test="${module eq 'preauth' && diaFlg ne 'Y' && diaPendFlg ne 'Y'}">
			<span><b>Preauth Cases For Approval</b></span>
			</c:if>
			<c:if test="${module eq 'preauth' && diaFlg eq 'Y'}">
			<span><b>Dialysis Cases For Approval</b></span>
			</c:if>
			<c:if test="${module eq 'preauth' && diaPendFlg eq 'Y'}">
			<span><b>Dialysis Pending Cases</b></span>
			</c:if>
			<c:if test="${module eq 'cochlearAco'}">
			<span><b>Cochlear Cases For Approval(Claims)</b></span>
			</c:if>
			<c:if test="${module eq 'preauthJournal'}">
			<span><b>Journalists Preauth Cases For Approval</b></span>
			</c:if>
			</logic:equal>
			<logic:notEqual value="Y" property="casesForApprovalFlag" name="casesSearchFormClaims">
			<span><b><fmt:message key="label.caseSearch.caseSearchTitle" /></b></span>
			</logic:notEqual>
			</c:if>
			 <c:if test="${errSearchType eq 'Y' && disSearchType ne 'Y'}">
			
			<c:if test="${patientScheme eq 'CD501'}">
			<span><b>Erroneous Cases For Approval</b></span>
			</c:if>
			
			<c:if test="${patientScheme eq 'CD502'}">
			<span><b>Journalists Erroneous Cases For Approval</b></span>
			</c:if>
			
			</c:if> 
			
			<c:if test="${denErrSearchType eq 'Y' && disSearchType ne 'Y'}">
			
			<c:if test="${patientScheme eq 'CD501'}">
			<span><b>Erroneous Dental Cases For Approval</b></span>
			</c:if>
			
			<c:if test="${patientScheme eq 'CD502'}">
			<span><b>Journalists Erroneous Dental Cases For Approval</b></span>
			</c:if>
			
			</c:if>
        </a>
    </div>
    <div id="collapseOne" class="panel-collapse collapse"> 
      <div class="panel-body">
    <table border="0" width="95%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;" >
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
		<c:if test="${errSearchType ne 'Y' && disSearchType ne 'Y' && module ne 'cochlearAco' }">
		<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="label.caseSearch.claimStatus" /></b></td>
		<td width="16%" class="tbcellBorder"><html:select  name="casesSearchFormClaims" property="claimId" styleId="claimId" style="width:200px; " onmousemove="javascript:getTitles('claimId')" title="Please select claim status">
		<option  value="">----Select----</option>
		<html:options collection="StatusList" property="ID" labelProperty="VALUE"  />
		</html:select></td>
		</c:if>
		<c:if test="${module eq 'cochlearAco'}">
		<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="label.caseSearch.claimStatus" /></b></td>
		<td width="16%" class="tbcellBorder"><html:select  name="casesSearchFormClaims" property="claimId" styleId="claimId" style="width:200px; " onmousemove="javascript:getTitles('claimId')" disabled="true"  title="Please select claim status">
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
		<td width="16%" class="tbcellBorder"><html:text name="casesSearchFormClaims" property="caseNo" styleId="caseNo" style="width:110px;" maxlength="50" title="Please enter complete case number Eg: If case number is CASE/EHS100/1234 ,Please enter 1234 as case number" onchange="javascript:checkBlankSpaces('caseNo','case number');" /></td>
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
		<td width="16%" class="tbcellBorder"><html:select  name="casesSearchFormClaims" property="catName" styleId="catName"  style="width:150px;" onmousemove="javascript:getTitles('catName')" onchange="javascript:fn_getProcedures()" title="Please select ICD Category name"><!-- onclick="javascript:fn_getProcedures()" -->
			<option  value="">----Select----</option>
			<html:options collection="icdCatList" property="ICDCODE" labelProperty="ICDNAME"  />
			</html:select>
		</td>
		<td width="16%" class="labelheading1 tbcellCss"><b>Procedure Name</b></td>
		<td width="16%" class="tbcellBorder"><html:select  name="casesSearchFormClaims" property="procName" styleId="procName"  style="width:150px;" onmousemove="javascript:getTitles('procName')"  title="Please select Procedure name">
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
		<td width="16%" class="tbcellBorder"><html:select property="schemeType"  styleId="schemeType" onchange="javascript:fn_getProcedures()" name="casesSearchFormClaims" style="width:150px;" onmousemove="javascript:getTitles('schemeType')" title="Please select Scheme">
						<html:option value="CD201">Andhra</html:option>
						<html:option value="CD202">Telangana</html:option> 
						<html:option value="CD203">Both</html:option>
						</html:select></td>
						</logic:equal>
	</tr>
	</logic:equal>
	<logic:equal value="Y" property="casesForApprovalFlag" name="casesSearchFormClaims">
	<tr>	 
				<logic:equal name="casesSearchFormClaims" property="showHospital" value="show">
					<td width="16%" class="labelheading1 tbcellCss"><b>Hospitals</b></td>
					<td width="16%" class="tbcellBorder">
						<html:select  name="casesSearchFormClaims" property="hospId" styleId="hospId"  style="width:150px;" onmousemove="javascript:getTitles('hospId')" title="Please select Hospital">
							<option  value="">----Select----</option>
							<html:options collection="HospList" property="ID" labelProperty="VALUE"  />
						</html:select>
					</td>
				</logic:equal>	
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
      </div>
    </div>
  </div>
</div>
<div class="clearfix"></div>
<bean:size id="lstCaseSearchSize" name="casesSearchFormClaims"  property="lstCaseSearch"/>
<logic:greaterThan value="0" name="lstCaseSearchSize">
<c:if test="${errSearchType ne 'Y'}">
<table width="95%" border="0" align="center" style="padding-top:0px;margin:0px auto;">
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

<div  class="leftone">
<ul class="pagination">
<logic:greaterThan value="0" name="lstCaseSearchSize">
<li class="lispacing">Showing Results</li>
<li class="lispacing"><bean:write name="casesSearchFormClaims" property="startIndex" />  - <bean:write name="casesSearchFormClaims" property="endIndex" /> 
of <bean:write name="casesSearchFormClaims" property="totalRows" /> </li>
</ul></div>

<div id="pageNoDisplay" class="centerone">

<ul class="pagination"> 


<%
int noOfPages = ((Integer) request.getAttribute("liTotalPages")).intValue();
int selectedPage = ((Integer) request.getAttribute("liPageNo")).intValue();
int a=selectedPage/10;
int pageNo=0;
int minVal=(a*10);
int maxVal=minVal+10;
if(maxVal>noOfPages)
{
	maxVal=noOfPages+1;
}
if(minVal>=10)
	{
		%>
		
		<li><a href="#"><span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages(<%=minVal%>,<%=noOfPages%>,<%=selectedPage%>)" style="cursor: pointer;"></span></a></li>
		
		<%
	}
	else
	{
		minVal=minVal+1;
	}
for(int i=minVal;i<maxVal;i++)
{
	pageNo=i;
		if(selectedPage==pageNo)
		{
			%>
		<li class="active"><span><%=pageNo%></span></li> 
			<%
		}
		else
		{
			%>
			<li><a href="javascript:showPagination(<%=pageNo%>)"><%=pageNo%></a></li>
			<%
		}
}
if(pageNo<noOfPages)
	{
		%>
		
		<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages(<%=pageNo+1%>,<%=noOfPages%>,<%=selectedPage%>)" style="cursor: pointer;"></span></li>
		
		<%
	}
%>









</ul></div>
<div class="rightone">
<ul class="pagination">
<li class="lispacing">Show in sets of</li>
<c:set var="ListNoSet" value="10,20,50,100,1000"/>  
<c:forEach var="set" items="${ListNoSet}"  >
<c:if test="${lStrRowsperpage eq set }" >
<li class="active"><span><c:out value="${set}" /></span></li> 
</c:if>
<c:if test="${lStrRowsperpage ne set }" >
<li><a href="javascript:showinSetsOf('<c:out value="${set}" />')"> <c:out value="${set}" /></a></li>
</c:if>
</c:forEach>
</logic:greaterThan>
</ul></div>

<logic:greaterThan value="0" name="lstCaseSearchSize">
<div id="csvDiv" style="clear:both;float:right;margin-right:25px;">   
<img id="csvImg" src="images/csv1.png"  onmouseover="this.src='images/csv2.png'" onmouseout="this.src='images/csv1.png'" onclick="javascript:exportToCSV()"/>
</div>
<table  width="98%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:0px auto;" class="tb">
	<tr>
	 
	 <c:if test="${acoFlag eq 'Y' && casesForApprovalFlag eq 'Y'}">
     <th class="tbheader1" width="5%"  >
      <label><input type="checkbox" id="checkAll"  class="selectableCheckbox"   title="click here to select all Cases" onclick="javascript:fn_checkAll();"  /></label>
    </th></c:if>
    
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
	<c:set var="count" value="1"></c:set>
    <logic:iterate id="result" name="casesSearchFormClaims"  property="lstCaseSearch" indexId="index">
	<tr  class="border${result.colorFlag}" >
	
	
	 <c:if test="${acoFlag eq 'Y'  && casesForApprovalFlag eq 'Y'}">
       <td class="tbcellBorder" width="5%" align="center">
      <label><input type="checkbox" id="CheckBox${count}" class="selectableCheckbox" value="<bean:write name="result" property="caseId"/>" onclick="javascript:checkOrUncheckCase();"></input></label>
    </td>
      </c:if> 
	<%--  <td class="tbcellBorder" width="5%">
      <label><input type="checkbox" id="CheckBox${count}" Class="selectableCheckbox" value="<bean:write name="result" property="caseId"/>" onchange="javascript:checkOrUncheckCase('<bean:write name="result" property="caseId"/>;"></label>
    </td> --%>
	 
	<logic:equal value="Y" property="casesForApprovalFlag" name="casesSearchFormClaims">
	<td class="tbcellBorder" width="12%" style="word-wrap:break-word;padding:3px;" nowrap="nowrap">
    <a id="patDtlsImage" style=cursor:pointer; title="Click to View Case Details" align="top" data-toggle="modal" href="#viewDtlsID" onclick="javascript:fn_getPatFullDetails('<bean:write name="result" property="caseId" />')" ><span class="glyphicon glyphicon-folder-open"></span></a>
    <a href="javascript:fn_caseApprovalAjax('<bean:write name="result" property="caseId" />','','<bean:write name="result" property="patIpOp" />');"><bean:write name="result" property="caseNo" /></a>
    <c:if test="${result.flagged eq 'Y'}">
    <img id="redFlag" src="./images/RedFlag.png" style="cursor:pointer;" title="Flagged"></img>
    </c:if>
    <c:if test="${result.flagged eq 'N'}">
    <img id="greenFlag" src="./images/GreenFlag.png" style="cursor:pointer;" title="DeFlagged" ></img>
    </c:if>
	<c:if test="${result.viewFlag eq 'Y' && is_medco_mithra ne 'Y'}">
	<img src="images/lock1.png" height="18" width="18" alt="this case is view by other" title="this case is viewed by other"/>
	</c:if>
	<c:if test="${result.teleStatus ne '' && result.teleStatus ne null}">
	<span class="glyphicon glyphicon-earphone" title="This is a Telephonic Registered case"></span>
	</c:if>
	<c:if test="${result.grievanceFlag ne '' && result.grievanceFlag ne null}">
	<img src="images/InspectionAttachmentFlag.gif" height="18" width="18" alt="Grievance has been raised against this case" title="Grievance has been raised against this case"/>
	</c:if>		
	</td>
	</logic:equal>
	<logic:notEqual value="Y" property="casesForApprovalFlag" name="casesSearchFormClaims">
	<td class="tbcellBorder" width="12%" style="word-wrap:break-word;padding:3px;" nowrap="nowrap" >
	
	<a id="patDtlsImage" style=cursor:pointer; title="Click to View Case Details" align="top" data-toggle="modal" href="#viewDtlsID" onclick="javascript:fn_getPatFullDetails('<bean:write name="result" property="caseId" />')" ><span class="glyphicon glyphicon-folder-open"></span></a>
    <a href="javascript:fn_caseApproval('<bean:write name="result" property="caseId" />','','<bean:write name="result" property="patIpOp" />');"><bean:write name="result" property="caseNo" /></a>
	<c:if test="${result.teleStatus ne '' && result.teleStatus ne null}">
	<span class="glyphicon glyphicon-earphone" title="This is a Telephonic Registered case"></span>
	</c:if>
	<c:if test="${result.grievanceFlag ne '' && result.grievanceFlag ne null}">
	<img src="images/InspectionAttachmentFlag.gif" height="18" width="18" alt="Grievance has been raised against this case" title="Grievance has been raised against this case"/>
	</c:if> 
	    <c:if test="${result.flagged eq 'Y'}">
    <img id="redFlag" src="./images/RedFlag.png" style="cursor:pointer;" ></img>
    </c:if>
    <c:if test="${result.flagged eq 'N'}">
    <img id="greenFlag" src="./images/GreenFlag.png" style="cursor:pointer;"  ></img>
    </c:if>
	</td> 
	</logic:notEqual>
	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="claimNo" /></td>
	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="patientName" /></td>
	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="result" property="wapNo" /></td>
	<td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;">
	<span style="padding:2px 0 3px 2px; background:<bean:write name="result" property="excelFlag" />">
	<c:if test="${result.patIpOp=='IP' || result.patIpOp=='IPD' }"><bean:write name="result" property="claimStatus"/></c:if>
	<c:if test="${result.patIpOp=='DOP' && result.claimStatus=='IP case Registered'}">DOP Case Registered</c:if>
	<c:if test="${result.patIpOp=='IPM' && result.claimStatus =='IP case Registered'}">IP Medical Case Registered</c:if> 
	<c:if test="${result.patIpOp=='IPM' && result.claimStatus !='IP case Registered'}"><bean:write name="result" property="claimStatus"/></c:if> 
	<c:if test="${result.patIpOp=='DOP' && result.claimStatus!='IP case Registered'}">
	<bean:write name="result" property="claimStatus"/></c:if>
	</span></td>
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
	<c:if test="${result.patIpOp=='IP' or result.patIpOp=='DOP' or result.patIpOp=='IPM' or result.patIpOp=='IPD' }">
		<td class="tbcellBorder" width="4%"><a href="javascript: generateCasePrint('<bean:write name="result" property="caseNo" />')"><span class="glyphicon glyphicon-print"></span></a></td>
		<td class="tbcellBorder" width="4%"><a href="javascript: printprfForm('<bean:write name="result" property="caseId" />','<bean:write name="result" property="caseStatusId" />','<bean:write name="result" property="patientId" />','<bean:write name="result" property="pendingFlag" />','<bean:write name="result" property="inpatientCaseSubDt" />')"><span class="glyphicon glyphicon-print"></span></a></td>
	</c:if>
		
	
	
	<%-- <logic:notEqual value="IP" name="result" property="patIpOp" >
		<td width="3%">&nbsp;</td>
	</logic:notEqual> --%>
	<c:set var="count" value="${count+1}"></c:set>
	</tr>
</logic:iterate>


<c:if test="${acoFlag eq 'Y'  && casesForApprovalFlag eq 'Y'}">
<tr>
	<td colspan="10"  align="center">
	<div id="buttonDiv">
<button class="but btn btn-danger"   id="verifyBut" type="button"  value="Verify"  name="CD73" onclick="javascript:fn_buttonClicked('CD73');">Verify</button>
</div>
</td></tr></c:if>

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
<html:hidden property="rowsPerPage"  name="casesSearchFormClaims"/>
<html:hidden property="startIndex" name="casesSearchFormClaims" />
<html:hidden property="showPage" name="casesSearchFormClaims" />
<html:hidden property="totalRows" name="casesSearchFormClaims" />
<html:hidden property="casesForApprovalFlag" name="casesSearchFormClaims" />
<html:hidden property="showScheme" name="casesSearchFormClaims" />


<div class="modal fade"   id="progressBar" style="position: absolute;top:40%;">
<div class="modal-dialog modal-lg">
 
      <div class="modal-body">
 
 <div class="centerProgress">
  <div class="progress">
    <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%">
      <span>Your file is being downloaded.Please Wait for few Minutes</span>
    </div>
  </div>
</div>
</div></div></div>




<div class="modal fade"   id="progressBar" style="position: absolute;top:40%;">
<div class="modal-dialog modal-lg">
 
      <div class="modal-body">
 
 <div class="centerProgress">
  <div class="progress">
    <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%">
      <span>Your file is being downloaded.Please Wait for few Minutes</span>
    </div>
  </div>
</div>
</div></div></div>


</html:form>
<script type="text/javascript">

function fn_blockScreen()
	{
		$(function () { 
			 var $modal = $('#progressBar'),
		    $bar = $modal.find('.progress-bar progress-bar-striped active');
		
		$modal.modal('show');
		$bar.addClass('animate');
		
		setTimeout(function() {
		  $bar.removeClass('animate');
		  $modal.modal('hide');
		}, 30000);
		});	
	}
this.$('#progressBar').modal({
	  backdrop: 'static',
	  show: false
	});	
	
function fn_getPatFullDetails(caseIdd){
	var url='/Operations/patCommonDtls.htm?actionFlag=getCaseDetails&CaseId='+caseIdd;
    document.getElementById('complaintFrame').src=url;
	//centerPopup("#popupRaiseComplaint");
	//loadPopup("#popupRaiseComplaint");
	//document.getElementById('popupRaiseComplaint').style.top=elemJqueryScrollTop+"px" ;	
	//document.getElementById('popupRaiseComplaint').style.left="120px";
	//document.getElementById('popupRaiseComplaint').style.right="0px";
}
	parent.fn_removeLoadingImage();
</script>
</body>
</fmt:bundle>
</html>
