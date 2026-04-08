<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/include.jsp"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>View Flagged Cases</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">

<link rel="stylesheet" href="css/jquery.ui.core.css">
<link rel="stylesheet" href="css/jquery.ui.datepicker.css">
<link rel="stylesheet" href="css/jquery.ui.theme.css">
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">

<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/jquery.ui.core.js"></script> 
<script src="js/jquery.ui.widget.js"></script>
<script src="js/jquery.ui.datepicker.js"></script>
<script src="js/DateTimePicker.js"></script>
	<style>
	body{font-size:1.3em !important;}
input:disabled, select:disabled{
background-color:#ddd;
}
	</style>
<script>

$(function() 
{
	var date = new Date();
	var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();
	
	$( "#fromDt" ).datepicker(
		{
			changeMonth: true,
			changeYear: true,
			numberOfMonths: 1,
			showOn: "both", 
    		buttonImage: "images/calend.gif", 
		    buttonText: "Calendar",
			    buttonImageOnly: true,
			    maxDate: new Date(y, m, d), 
			yearRange: '2000:' + new Date().getFullYear()
		});
	$( "#toDt" ).datepicker(
			{
				changeMonth: true,
				changeYear: true,
				numberOfMonths: 1,
				showOn: "both", 
	    		buttonImage: "images/calend.gif", 
			    buttonText: "Calendar",
				    buttonImageOnly: true,
				    maxDate: new Date(y, m, d), 
				yearRange: '2000:' + new Date().getFullYear()
			});

});
function focusBox(id)
{
	aField=id;
	setTimeout("aField.focus()",0);
}
function fn_resetFields()
{
	var con =confirm("Do you want to Reset all the fields ?");
	if(con==true)
		{
			document.getElementById("caseId1").value="";
			document.getElementById("cardNo").value="";
			document.getElementById("patState").value="-1";
			document.getElementById("patStateType").value="-1";
			document.getElementById("patDist").options.length=0;
			document.getElementById("patDist").options[0]=new Option("Select","-1");
			document.getElementById("fromDt").value="";
			document.getElementById("hospState").value="-1";
			document.getElementById("hospDist").options.length=0;
			document.getElementById("hospDist").options[0]=new Option("Select","-1");
			document.getElementById("hospType").value="-1";
			document.getElementById("hospName").options.length=0;
			document.getElementById("hospName").options[0]=new Option("Select","-1");
			document.getElementById("mainCatName").options.length=0;
			document.getElementById("mainCatName").options[0]=new Option("Select","-1");
			document.getElementById("catName").options.length=0;
			document.getElementById("catName").options[0]=new Option("Select","-1");
			document.getElementById("procName").options.length=0;
			document.getElementById("procName").options[0]=new Option("Select","-1");
		}	
}
function viewPreviousPages(pageNo,noOfPages,selectedPage,end_index)
{
	var pageDisplays='';
	pageDisplays=pageDisplays+'<ul class="pagination">';
	var pageNoLim=pageNo;
	var minPageNo=pageNo-10;
	var i=0;
	if(minPageNo>1)
	{
		//pageDisplays=pageDisplays+'<a href="javascript:viewPreviousPages('+minPageNo+','+noOfPages+','+selectedPage+','+end_index+')">Previous</a>&nbsp;&nbsp;';
		pageDisplays=pageDisplays+'<li> <span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages('+minPageNo+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer;"></span></li>';
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
			//pageDisplays=pageDisplays+'<b>'+i+'</b>&nbsp;';
			pageDisplays=pageDisplays+' <li class="active"><a href="javascript:fn_pagination('+i+','+end_index+')">'+i+'</a></li>';
		}
		else
		{
			//pageDisplays=pageDisplays+'<a href="javascript:fn_pagination('+i+','+end_index+')"><b>'+i+'</b></a>&nbsp;&nbsp;';
			pageDisplays=pageDisplays+' <li><a href="javascript:fn_pagination('+i+','+end_index+')">'+i+'</a></li>';
		}
		
	}
	//pageDisplays=pageDisplays+'<a href="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+','+end_index+')">Next</a>';
	pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer;"></span></li></ul>';
	document.getElementById("pageNoDisplay").innerHTML=pageDisplays;
}
function viewNextPages(pageNo,noOfPages,selectedPage,end_index)
{
	var pageDisplays='';
	var pageNoLim=pageNo+10;
	var i=0;
	pageDisplays=pageDisplays+'<ul class="pagination">';
	if(pageNoLim>noOfPages)
	{
		pageNoLim=noOfPages+1;
	}
	//pageDisplays=pageDisplays+'<a href="javascript:viewPreviousPages('+pageNo+','+noOfPages+','+selectedPage+','+end_index+')">Previous</a>&nbsp;&nbsp;';
	pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages('+pageNo+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer;"></span></li>';
	for(i=pageNo;i<pageNoLim;i++)
	{
		if(selectedPage==i)
		{
			//pageDisplays=pageDisplays+'<b>'+i+'</b>&nbsp;';
			pageDisplays=pageDisplays+'<li class="active"><a href=javascript:fn_pagination('+i+','+end_index+')">'+i+'</a></li>';
		}
		else
		{
			//pageDisplays=pageDisplays+'<a href="javascript:fn_pagination('+i+','+end_index+')"><b>'+i+'</b></a>&nbsp;&nbsp;';
			pageDisplays=pageDisplays+'<li> <a href="javascript:fn_pagination('+i+','+end_index+')">'+i+'</a></li>';
		}
		
	}
	if(i<noOfPages)
	{
		//pageDisplays=pageDisplays+'<a href="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+','+end_index+')">Next</a>';
		pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer;"></span></li></ul>';
	}
	document.getElementById("pageNoDisplay").innerHTML=pageDisplays;
}

function enableStateType(){
	document.getElementById("patStateType").value="-1";
	document.getElementById("patDist").value="-1";
	
	if(document.getElementById("patState").value!=null && document.getElementById("patState").value!="-1"){
		if(document.getElementById("patState").value=="S35"){
			document.getElementById("patStateType").disabled=false;
			document.getElementById("patDist").disabled=true;
		}
		else{
			document.getElementById("patStateType").disabled=true;
			document.getElementById("patStateType").value="-1";
			getDistrictsNew();			
		}
		
	}
}
function getDistricts(id)
	{
		document.getElementById("patDist").disabled=false;
		document.getElementById("hospDist").options.length=0;
		document.getElementById("hospDist").options[0]=new Option("Select","-1");
		document.getElementById("hospType").value="-1";
		document.getElementById("hospName").options.length=0;
		document.getElementById("hospName").options[0]=new Option("Select","-1");
		var stateId=document.getElementById(id).value;
		if(stateId!=null&&stateId!=""&&stateId!=-1)
			{
				url='/<%=context%>/flaggingAction.do?actionFlag=getDistricts&stateId='+stateId;
				var xmlhttp;
					if(window.XMLHttpRequest)
						{
							xmlhttp=new XMLHttpRequest();
						}
					else if(window.ActiveXObject)
						{
							xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
						}
					else{alert("Your browser does not support XMLHttp");}
					
					xmlhttp.onreadystatechange=function()
					{
						if(xmlhttp.readyState==4)
							{
								var result=xmlhttp.responseText;
								var result1=result.replace("*","");
								if(result1!=null)
									{
										result1 =result1.replace("[","");
										result1 =result1.replace("]","");
										var result2=result1.split(", @");
									}
								if(result2.length>0)
									{
										for(var i=0;i<result2.length;i++)
											{
												var finalResult=result2[i].split("~");
												if(finalResult[0]!=null&&finalResult[1]!=null)
												{
													if(id=="patState")
													document.getElementById("patDist").options[i+1]=new Option(finalResult[1],finalResult[0]);
													if(id=="hospState")
													document.getElementById("hospDist").options[i+1]=new Option(finalResult[1],finalResult[0]);
												}
											}
									}
							}
					}
					xmlhttp.open("POST", url, true);
					xmlhttp.send(null);
			}
		if(stateId==null||stateId==""||stateId==-1)
			{
				if(id=="patState")
					{
						document.getElementById("patDist").options.length=0;
						document.getElementById("patDist").options[0]=new Option("Select","-1");
					}
				else if(id=="hospState")
				{
					document.getElementById("hospDist").options.length=0;
					document.getElementById("hospDist").options[0]=new Option("Select","-1");
					document.getElementById("hospType").value="-1";
					document.getElementById("hospName").options.length=0;
					document.getElementById("hospName").options[0]=new Option("Select","-1");
				}
			}
	}
function getDistrictsNew()
{
	document.getElementById("patDist").disabled=false;
	var stateId=document.getElementById("patState").value;
	var patStateType=document.getElementById("patStateType").value;
	if(stateId!=null&&stateId!=""&&stateId!=-1)
		{
			url='flaggingAction.do?actionFlag=getDistrictsNew&stateId='+stateId+'&patStateType='+patStateType;
			var xmlhttp;
				if(window.XMLHttpRequest)
					{
						xmlhttp=new XMLHttpRequest();
					}
				else if(window.ActiveXObject)
					{
						xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
					}
				else{alert("Your browser does not support XMLHttp");}
				
				xmlhttp.onreadystatechange=function()
				{
					if(xmlhttp.readyState==4)
						{
							var result=xmlhttp.responseText;
							var result1=result.replace("*","");
							if(result1!=null)
								{
									result1 =result1.replace("[","");
									result1 =result1.replace("]","");
									var result2=result1.split(", @");
								}
							if(result2.length>0)
								{
								document.getElementById("patDist").options.length=0;
								document.getElementById("patDist").options[0]=new Option("---select---","-1");
									for(var i=0;i<result2.length;i++)
										{
											var finalResult=result2[i].split("~");
											if(finalResult[0]!=null&&finalResult[1]!=null)
											{
												
												document.getElementById("patDist").options[i+1]=new Option(finalResult[1],finalResult[0]);
												
											}
										}
								}
						}
				}
				xmlhttp.open("POST", url, true);
				xmlhttp.send(null);
		}
	if(stateId==null||stateId==""||stateId==-1)
		{
			if(id=="patState")
				{
					document.getElementById("patDist").options.length=0;
					document.getElementById("patDist").options[0]=new Option("Select","-1");
				}
			else if(id=="hospState")
			{
				document.getElementById("hospDist").options.length=0;
				document.getElementById("hospDist").options[0]=new Option("Select","-1");
				document.getElementById("hospType").value="-1";
				document.getElementById("hospName").options.length=0;
				document.getElementById("hospName").options[0]=new Option("Select","-1");
			}
		}
}
function getHospitals(id)
{
	var stateId=document.getElementById("hospState").value;
	var distId=document.getElementById("hospDist").value;
	var hospType=document.getElementById(id).value;
	var error=0;
	/* if(stateId==null||stateId==""||stateId=="-1")
		{
			error=1;
			alert("Please select any state and district before selecting Hospital Type");
			return false;
		} 
	if(distId==null||distId==""||distId=="-1")
		{
			error=1;
			alert("Please select any district before selecting Hospital Type");
			return false;
		}*/
	if(error==0&&hospType!=null&&hospType!="")
		{
			url='/<%=context%>/flaggingAction.do?actionFlag=getHospitals&stateId='+stateId+'&distId='+distId+'&hospType='+hospType;
			var xmlhttp;
				if(window.XMLHttpRequest)
					{
						xmlhttp=new XMLHttpRequest();
					}
				else if(window.ActiveXObject)
					{
						xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
					}
				else{alert("Your browser does not support XMLHttp");}
				
				xmlhttp.onreadystatechange=function()
				{
					if(xmlhttp.readyState==4)
						{
							var result=xmlhttp.responseText;
							var result1=result.replace("*","");
							if(result1!=null)
								{
									result1 =result1.replace("[","");
									result1 =result1.replace("]","");
									var result2=result1.split(", @");
								}
							if(result2.length>0)
								{
									for(var i=0;i<result2.length;i++)
										{
											var finalResult=result2[i].split("~");
											if(finalResult[0]!=null&&finalResult[1]!=null)
											{
												document.getElementById("hospName").options[i+1]=new Option(finalResult[1],finalResult[0]);
											}
										}
								}
						}
				}
				xmlhttp.open("POST", url, true);
				xmlhttp.send(null);
		}
	if(hospType==null||hospType==""||hospType!="-1")
		{
			if(id=="hospState")
				{
					document.getElementById("hospName").options.length=0;
					document.getElementById("hospName").options[0]=new Option("Select","-1");
				}
		}
}
function chkHospType(id)
	{
		var distId=document.getElementById(id).value;
		if(distId==null||distId==""||distId=="-1")
			{
				document.getElementById("hospType").options.length=0;
				document.getElementById("hospType").options[0]=new Option("Select","-1");
				
				document.getElementById("hospName").options.length=0;
				document.getElementById("hospName").options[0]=new Option("Select","-1");
			}
		else
			{
				document.getElementById("hospType").options[1]=new Option("Corporate","C");
				document.getElementById("hospType").options[2]=new Option("Government","G");
			}
	}
function fn_chkSpl(id)
	{
		if(id=="caseId1")
		{
			var specialCharName=/^[a-zA-Z0-9]*$/;
			var caseId=document.getElementById(id).value;
			if(caseId.length>0)
				{
					if(caseId.charAt[0]=="")
						{
							alert("Case Number cannot start with a Space");
							focusBox(document.getElementById(id));
							return false;
						}
					if(!specialCharName.test(caseId))
						{
							alert("Case Number cannot have Special Characters and Spaces");
							focusBox(document.getElementById(id));
							return false;
						}
				}
		}	
		
		if(id=="cardNo")
		{
			var specialCharName=/^[a-zA-Z0-9\/]*$/;
			var cardNo=document.getElementById(id).value;
			if(cardNo.length>0)
				{
					if(cardNo.charAt[0]=="")
						{
							alert("Card Number cannot start with a Space");
							focusBox(document.getElementById(id));
							return false;
						}
					if(!specialCharName.test(cardNo))
						{
							alert("Card Number cannot have Special Characters and Spaces");
							focusBox(document.getElementById(id));
							return false;
						}
				}
		}
			
	}
function fetchCaseDetails(caseId)
{
	document.forms[0].action="/Operations/flaggingAction.do?actionFlag=getFlagPage&caseIdS="+caseId+"&flaggedCasesForApproval=${flaggedCasesForApproval}&newFlag=${newFlag}";
	document.forms[0].method="post";
	document.forms[0].submit();
}
function fn_pagination(pageId,end_index)
{
	document.forms[0].action="./flaggingAction.do?actionFlag=viewFlaggedCases&pageId="+pageId+"&end_index="+end_index+"&flaggedCasesForApproval=${flaggedCasesForApproval}&newFlag=${newFlag}";
	document.forms[0].method="post";
	document.forms[0].submit();
}
function fn_fetchFlaggedCases()
{
	var caseNo=document.getElementById("caseId1").value;
	if(caseNo!=null&&caseNo!="")
		{
			var ret=fn_chkSpl("caseId1");
			if(ret==false)
				return false;
			caseNo.toUpperCase();
			document.getElementById("caseId1").value=caseNo;
		}	
	var cardNo=document.getElementById("cardNo").value;
	if(cardNo!=null&&cardNo!="")
		{
			var ret=fn_chkSpl("cardNo");
			if(ret==false)
				return false;
			caseNo.toUpperCase();
			document.getElementById("cardNo").value=cardNo;
		}
	
	if( document.getElementById("caseId1").value== ""    && document.getElementById("cardNo").value== ""     &&
	    document.getElementById("patStateType").value=="-1" && document.getElementById("patDist").value== "-1"  &&
	    document.getElementById("fromDt").value==""     && document.getElementById("hospState").value=="-1" &&
	    document.getElementById("hospDist").value=="-1" && document.getElementById("hospType").value=="-1"  &&
	    document.getElementById("hospName").value=="-1"	&& document.getElementById("mainCatName").value=="-1"	&& 
	    document.getElementById("catName").value=="-1"	&& document.getElementById("procName").value=="-1")
		{
			alert("Please enter any Search Criteria to Search");
			return false;
		}
	if(document.getElementById("patStateType").value!="-1" && document.getElementById("patDist").value=="-1")
		{
		alert("Please select district to Search");
		return false;
		}
	var con =confirm("Do you want to proceed ?");
	if(con==true)
		{
			document.forms[0].action="./flaggingAction.do?actionFlag=viewFlaggedCases&flaggedCasesForApproval=${flaggedCasesForApproval}&newFlag=${newFlag}&pageId=${pageId}";
			document.forms[0].method="post";
			document.forms[0].submit();
		}	
}
function fn_removeDate()
	{
		document.getElementById("fromDt").value="";
	}
function exportToCSV()
{
	fn_blockScreen();
	var id=document.getElementById("csvImg");
	var con =confirm("Do you want to download all the records in a CSV file?");
	if(con==true)
		{
			id.onclick=null;
			document.forms[0].action="./flaggingAction.do?actionFlag=viewFlaggedCases&flaggedCasesForApproval=${flaggedCasesForApproval}&csvFlag=Y&newFlag=${newFlag}";
			document.forms[0].method="post";
			document.forms[0].submit();
		}	
}

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
	}, 60000);
	});	
}
this.$('#progressBar').modal({
  backdrop: 'static',
  show: false
});
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
function getTitles(styleId)
	{
		 var numOptions = document.getElementById(styleId).options.length; 
		
	for (var i = 0; i < numOptions; i++)
		 document.getElementById(styleId).options[i].title = document.getElementById(styleId).options[i].text;
	}
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
</script>
</head>
<body>
	<form name="flaggingForm">
	<div class="panel-group" id="accordion">
		<div class="panel panel-default">
    		<div class="tbheader">
    			<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" title="Click to search" style="color:#fff; display:block;">
    				<span class="glyphicon glyphicon-plus"></span>
							<b>Search Flagged Cases</b>
				</a>
			</div>	
			  <div id="collapseOne" class="panel-collapse collapse"> 
      		    <div class="panel-body">
				  <table width="99%" style="margin:auto">
					<tr>
						<td class="labelheading1 tbcellCss"  width="12%">
															Case No:
						</td>
						<td class="tbcellBorder" width="12%">
								<html:text name="flaggingForm" property="caseId1" styleId="caseId1" onblur="javascript:fn_chkSpl(id)" maxlength="12" title="Please enter Case Number"></html:text>
						</td>
						<td class="labelheading1 tbcellCss" width="12%">
															Card No:
						</td>
						<td class="tbcellBorder" width="12%">
								<html:text name="flaggingForm" property="cardNo" styleId="cardNo" onblur="javascript:fn_chkSpl(id)" maxlength="15" title="Please enter Card Number"></html:text>
						</td>
						<td class="labelheading1 tbcellCss" width="12%">
															Patient State:
						</td>
						<td class="tbcellBorder" width="14%">
								<html:select name="flaggingForm" property="patState" styleId="patState" title="Please select Patient State" onchange="javascript:enableStateType()"> 
								<html:option value="-1">Select</html:option>
								<html:option value="S17">Andhra Pradesh</html:option>
								<html:option value="S35">Telangana</html:option>
								<html:option value="both">Both</html:option>
								</html:select>
						</td>
						
						<td class="labelheading1 tbcellCss" width="12%">
															District Type:
						</td>  
						<td class="tbcellBorder" width="14%">
								<html:select name="flaggingForm" property="patStateType" styleId="patStateType" onchange="javascript:getDistrictsNew()" title="Please select Patient District Type"> 
									<html:option value="-1">Select</html:option>
									<html:option value="O">Old Districts</html:option>
									<html:option value="N">New Districts</html:option>
								</html:select>
						</td>
					</tr>
					<tr>	
						<td class="labelheading1 tbcellCss" width="12%">
															Patient District:
						</td>  
						<td class="tbcellBorder" width="14%">
								<html:select name="flaggingForm" property="patDist" styleId="patDist" title="Please select Patient District">
								<html:option value="-1">Select</html:option>
								<html:options property="ID" collection="patDistList" labelProperty="dist" ></html:options>
								</html:select>
						</td>
				
						<td class="labelheading1 tbcellCss" width="12%">
															Hospital State:
						</td>
						<td class="tbcellBorder" width="14%">
								<html:select name="flaggingForm" property="hospState" styleId="hospState" onchange="javascript:getDistricts(id)" title="Please select Patient State">
								<html:option value="-1">Select</html:option>
								<html:option value="S17">Andhra Pradesh</html:option>
								<html:option value="S35">Telangana</html:option>
								<html:option value="both">Both</html:option>
								</html:select>
						</td>
						<td class="labelheading1 tbcellCss" width="12%">
														   Hospital District:
						</td>
						<td class="tbcellBorder" width="12">
								<html:select  name="flaggingForm" property="hospDist" styleId="hospDist" onchange="javascript:chkHospType(id)" title="Please Select Hospital District">
								<html:option value="-1">Select</html:option>
								<html:options property="ID" collection="hospDistList" labelProperty="dist" ></html:options>
								</html:select>
						</td>
						<td class="labelheading1 tbcellCss" width="12%">
															Hospital Type:
						</td>
						<td class="tbcellBorder" width="14%">
								<html:select name="flaggingForm" property="hospType" styleId="hospType" onchange="javascript:getHospitals(id)" title="Please select Hospital Type">
								<html:option value="-1">Select</html:option>
								<html:options property="ID" collection="hospTypeList" labelProperty="ID1" ></html:options>
								</html:select>
						</td>
					</tr>
					<tr>
						<td width="16%" class="labelheading1 tbcellCss">
														   Hospital Name:
						</td>
						<td class="tbcellBorder" width="16%">
								<html:select name="flaggingForm" property="hospName" styleId="hospName"  title="Please Select Hospital Name" style="width:200px;">
								<html:option value="-1">Select</html:option>
								<html:options property="ID" collection="hospNameList" labelProperty="hospName" ></html:options>
								</html:select>
						</td>
	<td width="16%" class="labelheading1 tbcellCss"><b>Category</b></td>
		<td width="16%" class="tbcellBorder"><html:select  name="flaggingForm" property="mainCatName" styleId="mainCatName"  style="width:200px;" onmousemove="javascript:getTitles('mainCatName')" onchange="javascript:fn_getICDCatList()" title="Please select Category">
			<option  value="-1">----Select----</option>
			<html:options collection="catList" property="ID" labelProperty="VALUE"  />
			</html:select>
		</td>
		<td width="16%" class="labelheading1 tbcellCss"><b>ICD Category Name</b></td>
		<td width="16%" class="tbcellBorder"><html:select  name="flaggingForm" property="catName" styleId="catName"  style="width:150px;" onmousemove="javascript:getTitles('catName')" onchange="javascript:fn_getProcedures()" title="Please select ICD Category name"><!-- onclick="javascript:fn_getProcedures()" -->
			<option  value="-1">----Select----</option>
			<html:options collection="icdCatList" property="ICDCODE" labelProperty="ICDNAME"  />
			</html:select>
		</td>
			<td width="16%" class="labelheading1 tbcellCss"><b>Procedure Name</b></td>
		<td width="16%" class="tbcellBorder"><html:select  name="flaggingForm" property="procName" styleId="procName"  style="width:150px;" onmousemove="javascript:getTitles('procName')"  title="Please select Procedure name">
			<option  value="-1">----Select----</option>
			<html:options collection="procList" property="ICDCODE" labelProperty="ICDNAME"  />
			</html:select>
		</td>					
		</tr>
		<tr>
		<td class="labelheading1 tbcellCss" width="12%">
															Flagged Date:
						</td>
						<td class="tbcellBorder">
								<html:text style="width:124px" name="flaggingForm" property="fromDt" styleId="fromDt" readonly="true" maxlength="12" title="Please Select From Date"></html:text>
								<img id="eraseImage" src="images/eraser.gif" style="cursor:pointer;" alt="Erase" title="Click to Erase Date" onclick="javascript:fn_removeDate()" ></img>
						</td>
					</tr>
				</table>	
						<table width="99%">
							<tr>
								<td colspan="6" align="center">
									<button type="button" class="but" name="sub" id="sub" tabindex="0" style="Width:5em;height:2em" onclick="javascript:fn_fetchFlaggedCases()" title="Hit to fetch the flagged cases">Submit</button>
									<button type="button" class="but" name="resetFields" id="resetFields" tabindex="0" style="Width:5em;height:2em" onclick="javascript:fn_resetFields()" title="Hit to reset all the fields">Reset</button>
								</td>
							</tr>
						</table>
		</div>
		</div>				
		</div>
		</div>				
		<logic:present name="flaggingForm" property="lstFlaggedCases">
	
	<!-- <table width="100%" border="0" align="center"> 
	<tr>    -->
	<div  class="leftonePag" style="float:left;width:40%;">
		<ul class="pagination" style="min-width:100%;">
		<li class="lispacing" style="padding-right:10%">Total No of records found:${noOfRecords}</li>
		<li class="lispacing" >Displaying records from ${start_index+1} to ${endresults}</li>
	</ul>
	</div>	
	<!-- <td align="center" colspan="1" id="pageNoDisplay"  style="width:25%">col-xs-12 col-sm-12 col-md-9 col-lg-3 -->
	<div id="pageNoDisplay" class="centeronePag " style="float:left;">
	<ul class="pagination">
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
		<%-- <a href="javascript:viewPreviousPages(<%=minVal%>,<%=noOfPages%>,<%=selectedPage%>,<%=end_index%>)">Prev</a>&nbsp; --%>
			<li><a href="javascript:viewPreviousPages(<%=minVal%>,<%=noOfPages%>,<%=selectedPage%>,<%=end_index%>)"><span class="glyphicon glyphicon-backward" style="cursor: pointer;"></span></a></li>
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
			<%-- <b><%=pageNo%></b> --%>
			<li class="active"><span><%=pageNo%></span></li> 
			<%
		}
		else
		{
			%>
			<%-- <a href="javascript:fn_pagination(<%=pageNo%>,<%=end_index%>)"><b><%=pageNo%></b></a>&nbsp; --%>
			<li><a href="javascript:fn_pagination(<%=pageNo%>,<%=end_index%>)"><%=pageNo%></a></li>
			<%
		}
}
if(pageNo<noOfPages)
	{
		%>
		<%-- <a href="javascript:viewNextPages(<%=pageNo+1%>,<%=noOfPages%>,<%=selectedPage%>,<%=end_index%>)">Next</a> --%>
		<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages(<%=pageNo+1%>,<%=noOfPages%>,<%=selectedPage%>,<%=end_index%>)" style="cursor: pointer;"></span></li>
		<%
	}
		%>   
		</ul>
		</div>
		<div class="rightonePag " style="float:right;" style="margin:0px 0px 0px 0px">
		<ul class="pagination">
		<!-- <td width="25%"> --><li class="lispacing">Show results in sets of </li><c:if test="${end_index ne 10}"><li><a href="javascript:fn_pagination(1,10)">10</a></li></c:if>
												<c:if test="${end_index eq 10}"><li class="active"><span>10</span></li></c:if>	
												<c:if test="${end_index ne 20}"><li><a href="javascript:fn_pagination(1,20)">20</a></li></c:if>
												<c:if test="${end_index eq 20}"><li class="active"><span>20</span></li></c:if>
												<c:if test="${end_index ne 50}"><li><a href="javascript:fn_pagination(1,50)">50</a></li></c:if>
												<c:if test="${end_index eq 50}"><li class="active"><span>50</span></li></c:if>
												<c:if test="${end_index ne 100}"><li><a href="javascript:fn_pagination(1,100)">100</a></li></c:if>
												<c:if test="${end_index eq 100}"><li class="active"><span>100</span></li></c:if><!-- </td> -->
												
	<!-- </table> -->
	</ul>
	</div>
	<div id="csvDiv" style="clear:both;float:right;margin-right:25px;">
			<img id="csvImg" src="images/csv1.png"  onmouseover="this.src='images/csv2.png'" onmouseout="this.src='images/csv1.png'" onclick="javascript:exportToCSV()"/>
	</div>
	<!-- <div id="container" class="table-responsive"> -->
				<table  id="flaggedCases" class=".table table-responsive" style="width:100%;margin:auto">
					<tr>
						<td class="tbheader1" style="width:10%;">
							Case No
						</td>
						<td class="tbheader1" style="width:10%;">
							Flag ID
						</td>
						<td class="tbheader1" style="width:10%;">
							Flag Status
						</td>
						 <td class="tbheader1" style="width:10%;">
							Card No		
						</td> 
						<td class="tbheader1" style="width:10%;">
							Flagged Date and Time 
						</td>
						<td class="tbheader1" style="width:10%;">
							Patient District
						</td>
						<td class="tbheader1" style="width:10%;">
							Hospital District
						</td>
						<td class="tbheader1" style="width:10%;">
							Hospital Type
						</td>
						<td class="tbheader1" style="width:20%;">
							Hospital Name
						</td>
					</tr>
					<logic:iterate name="flaggingForm" property="lstFlaggedCases" id="flaggedCases">
						<tr>
							<td class="tbcellBorder" align="center">
								<a href="javascript:fetchCaseDetails('<bean:write name="flaggedCases" property="caseId"/>')"><bean:write name="flaggedCases" property="caseId"/></a>
							</td>
							<td class="tbcellBorder" align="center">
								<bean:write name="flaggedCases" property="flagId"/>
							</td>
							<td class="tbcellBorder" align="center">
								<bean:write name="flaggedCases" property="currentStatus"/>
							</td>
							<td class="tbcellBorder" align="center">
								<bean:write name="flaggedCases" property="cardNo"/>
							</td> 
							 <td class="tbcellBorder" align="center">
								<bean:write name="flaggedCases" property="crtDt"/>
							</td> 
							<td class="tbcellBorder" align="center">
								<bean:write name="flaggedCases" property="patDist"/>
							</td>
							<td class="tbcellBorder" align="center">
								<bean:write name="flaggedCases" property="hospDist"/>
							</td>
							<td class="tbcellBorder" align="center">
							<%-- <bean:write name="flaggedCases" property="hospType"/> --%>
							<%-- <c:choose>
								<c:when test="${flaggedCases.hospType eq 'C'}"> --%>
								<%-- </c:when> --%>
								<%-- <c:when test="${flaggedCases.hospType eq 'G'}"> --%>
								<%-- </c:when> --%>
							<%-- </c:choose> --%>
							<logic:equal name="flaggedCases" property="hospType" value="C">
							Corporate
							</logic:equal>
							<logic:equal name="flaggedCases" property="hospType" value="G">
							Government									
							</logic:equal>

							</td>
							<td class="tbcellBorder" align="center">
								<bean:write name="flaggedCases" property="hospName"/>
							</td>
						</tr>
					</logic:iterate>	
				</table>
	<!-- </div>	 -->	
		</logic:present>
				<logic:equal name="flaggingForm" property="flagged" value="false">
						<br><br><br>
						
						<table  style="padding-top:0px;" width="100%">
							<tr>
								<td align="center"><b><font size="2">No Records Found</font></b></td>
							</tr>
						</table>
				</logic:equal>
				
				<div class="modal fade"   id="progressBar" >
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
<%-- <html:hidden name="flaggingForm" property="patState" styleId="patState" value="S35"/> --%>
	</form>
</body>
</html>