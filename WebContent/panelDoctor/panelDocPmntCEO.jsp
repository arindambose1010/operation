<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"  %> 
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Panel Doctor Payments</title>
    <script src="js/jquery-1.9.1.js"></script>
    <%@ include file="/common/include.jsp"%> 
    <%-- <%@ include file="/common/includeCalendar.jsp"%> --%> 
<link href="css/select2.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/themes/<%=themeColour%>/accountstyle.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/select2.min.js"></script>

<style>

.inline{
border-radius:20px !important;
padding:0px  0px 0px  30px !important;
}
.tbheaderInLine{
background-color: #FFFFFF !important;
padding:10px !important;}

.noPadding{
padding:0px !important;
}

.leftMargin{
border-left:#ffffff solid 1px !important;}

.tbheader1 {
background-color: #1e4b89c2 !important;
padding:6px !important;
align:center !important;
}

.tbcellBorder{
border:#ffffff solid 1px !important;
background: none;
}

.inlineBut{
padding:3px !important;
background-color:#3B9C9C !important;
border-color:#3B9C9 !important; 
}
.inlineBut:hover{
background-color:#0B4C5F !important;
}

body {
  font-family: sans-serif;
    color: #767676;
}
.sizeChange {
    font-size: 15px;
}
.centerProgress{
width:50%;
align:center;
padding-top:20%;
padding-left:40%;
background-color:none;
border-radius:10%;
}
.modal-dialog{
width:800px;
}
.icheck .single-row {
    display: inline-block;
}
.ajeoBackground {
	background-color: #d8f5e2;
}
.bnksntBackGround {
	background-color: #FFE9CA;
}
.sntupdBackground {
	background-color: #e7e6f1;
}
</style>

<script type="text/javascript">
$(document).ready(function() {
	 let x = document.querySelectorAll(".myDIV");
     for (var i = 0, len = x.length; i < len; i++) {
    	 if(x[i].innerHTML != "--NA--"){
         let num = Number(x[i].innerHTML)
                   .toLocaleString('en-IN');
         x[i].innerHTML = num;
     }
     }
});


var caseCount=0;
function getAllUsers()
	{
		var allDepts=document.getElementById("allDepts").value;
		if(allDepts==null||allDepts==""||allDepts==-1)
			{
				document.getElementById("allUsers").options.length=0;
				document.getElementById("allUsers").options[0]=new Option("-SELECT-","-1");
				
				$('#allUsers').select2("val","-1");
			}
		if(allDepts!=null && allDepts!="" && allDepts!=-1)
			{
				if(allDepts!='hie')
					url='panelDocPay.do?actionFlag=getAllUsers&deptId='+allDepts;	
				else
					url='panelDocPay.do?actionFlag=getAllUsersforHie&deptId='+allDepts;
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
								else
									{
										document.getElementById("allUsers").options.length=0;
										document.getElementById("allUsers").options[0]=new Option("-SELECT-","-1");
										document.getElementById("allUsers").options[1]=new Option("-NO USERS FOUND-","-1");
									}
								if(result2.length>0)
									{
										document.getElementById("allUsers").options.length=0;
										document.getElementById("allUsers").options[0]=new Option("-SELECT-","-1");
										
										for(var i=0;i<result2.length;i++)
											{
												var finalResult=result2[i].split("~");
												if(finalResult[0]!=null&&finalResult[1]!=null)
												{
													document.getElementById("allUsers").options[i+1]=new Option(finalResult[1],finalResult[0]);
												}
											}
										if(result2.length==1)
											{
												if(result2=='')
													{
														document.getElementById("allUsers").options.length=0;
														document.getElementById("allUsers").options[0]=new Option("-SELECT-","-1");
														document.getElementById("allUsers").options[1]=new Option("-NO USERS FOUND-","-1");
													}
											}
									}
								else
									{
										document.getElementById("allUsers").options.length=0;
										document.getElementById("allUsers").options[0]=new Option("-SELECT-","-1");
										document.getElementById("allUsers").options[1]=new Option("-NO USERS FOUND-","-1");
									}
							}
					}
					xmlhttp.open("POST", url, true);
					xmlhttp.send(null);
			}
		
		$(".sentBack4-ceo").select2(); 
	}

function getDepts()
	{
		document.getElementById("allUsers").options.length=0;
		document.getElementById("allUsers").options[0]=new Option("-SELECT-","-1");
		url='panelDocPay.do?actionFlag=getDepts';
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
													document.getElementById("allDepts").options[i+1]=new Option(finalResult[1],finalResult[0]);
												}
										}
									$(".sentBack3-ceo").select2(); 
									$(".sentBack4-ceo").select2();
								}
						}
				}
			xmlhttp.open("POST", url, true);
			xmlhttp.send(null);
	}


$(document).ready(function() {

	
	$(function(){
		 
		$(document).on( 'scroll', function(){
	 
			if ($(window).scrollTop() > 100) {
				$('.scroll-top-wrapper').addClass('show');
			} else {
				$('.scroll-top-wrapper').removeClass('show');
			}
		});
	 
		$('.scroll-top-wrapper').on('click', scrollToTop);
	});
	 
	function scrollToTop() {
		verticalOffset = typeof(verticalOffset) != 'undefined' ? verticalOffset : 0;
		element = $('body');
		offset = element.offset();
		offsetTop = offset.top;
		$('html, body').animate({scrollTop: offsetTop}, 500, 'linear');
	}
	
	
	
	/* $('#searchText1').keyup(function(evt)
			{
				searchTable($(this).val(),'panelDocPayDtls');
			});
	$('#searchText1').keypress(function(evt)
	{
		 evt = evt || window.event;
          if (evt.keyCode == 13) {
               return false;
            }
		
	}); */
	
	
});











var checkedValue="[";
var checkedWID="[";
var amount= parseFloat("0");


function partial(func /*, 0..n args */) {
	 var args = new Array();
	 for (var i = 1; i < arguments.length; i++) { args.push(arguments[i]); }
	  return function() {
	    var allArguments = args.concat(Array.prototype.slice.call(arguments));
	    return func.apply(this, allArguments);
	  };
}
function fn_loadingImage()
{
		$(function () { $('#progressBar').modal({
			backdrop : 'static',
			keyboard : false,
			show: true
		   });});
}
function goToHomePage(arg)
{
	fn_loadingImage();
	
	 if(document.panelDocPayForm.statusCeo.value == "3")
		 {
		 	document.forms[0].action = "panelDocPay.do?actionFlag=viewBankRejctdCasesCeo&SingleRow="+arg;
		 }
	 else if(document.panelDocPayForm.statusCeo.value == "4")
		 {
	 		document.forms[0].action = "panelDocPay.do?actionFlag=getPanelDocPaymentRecrds&sentBack=Y&SingleRow="+arg;
		 }
	 else 
		 {
	 		document.forms[0].action = "panelDocPay.do?actionFlag=getPanelDocPaymentRecrds&SingleRow="+arg;
		 }	
		
	document.forms[0].submit();
}
function focusBox(arg)
{	
aField = arg;
setTimeout("aField.focus()", 0);  

}

function validateMaxlength(input,e)
{
	var fieldValue=input.value;
	var code;
    if (!e) var e = window.event;
    if (e.keyCode) code = e.keyCode;
    else if (e.which) code = e.which; 
	if(fieldValue.trim().length>3000)
		{
			input.value=fieldValue.trim().substr(0,3000);
			jqueryAlertMsg('Maxlength Validation','Exceeded maximum limits of 3000 words.');	
	
			if(code==8 || code==46 || code==37 || code==38 || code==39 || code==40)
				//Key Code check for backspace,delete,Arrow Left, Arrow Up,Arrow Right,Arrow Down
				{
				e.returnValue=true;
				}
			else
				{
				e.returnValue=false;
			 	}
		}
	
}

function resetList(arg)
{
	var obj =  arg;
	if (obj == null) return;
	if (obj.options == null) return;
	while (obj.options.length > 1) {
		obj.remove(1);
	}
}





function popitup(url) {
	
	newwindow=window.open(url,'PanelDoctor','width=1000, height=600, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes,menubar=no,toolbar=no');	
	if (window.focus) {newwindow.focus();}
	return false;
}


function popUp(docId,docName,wId)
{
	var scheme="";
	var pmntStatusValue=document.panelDocPayForm.paymentStatus;
	var pmntStatusLabel=pmntStatusValue.options[pmntStatusValue.selectedIndex].text;
	if(document.panelDocPayForm.userType.value=="CD203")
		scheme=document.panelDocPayForm.scheme.value;
	popitup("panelDocPay.do?actionFlag=getAllCasesStatus&docId="+docId+"&docName="+docName+"&dispType=PAO&pmntType="+pmntStatusLabel+"&scheme="+scheme+"&wId="+wId);
	/* document.forms[0].action="panelDocPay.do?actionFlag=getAllCasesStatusCeo&docId="+docId+"&docName="+docName+"&dispType=PAO&pmntType="+pmntStatusLabel+"&scheme="+scheme+"&wId="+wId;
	document.forms[0].method="post";
	fn_loadingImage();
	document.forms[0].submit(); */
	
}

function fn_pmntType()
{
	var pmntStatusValue=document.panelDocPayForm.paymentStatus;
	var pmntStatusLabel=pmntStatusValue.options[pmntStatusValue.selectedIndex].text;
	//var indexSpace=pmntStatusLabel.lastIndexOf(" ");
	//var action=pmntStatusLabel.substring(indexSpace+1);
	document.panelDocPayForm.actionSelect.value=pmntStatusLabel;
	getCases();
}
<%-- function Reset()
	{
		fn_loadImage();
		document.forms[0].action='/<%=context%>/claimPayments.do?actionFlag=getPanelDocPaymentRecrds';
		document.forms[0].method="post";
		document.forms[0].submit();
	} --%>

function fn_addCheckValues(arg,amt,wId,count){
	if(document.getElementById("checkAll")!=null)
		{
			if(document.getElementById("checkAll").checked==true)
				{
					document.getElementById("checkAll").checked=false;
				}
		}
	var aInputs = document.getElementsByName('individualPayment');
	//aInputs[0].checked=false;
	if(!checkedWID.match(wId))
		{
			checkedValue = checkedValue+'~'+arg;
			checkedWID=checkedWID+'~'+wId;
			amount=amount+parseFloat(amt);
			caseCount++;
			document.getElementById("row"+count).style.backgroundColor="#8adecb";
		}
	else
		{
			checkedValue = checkedValue.replace('~'+arg, '');
			checkedWID = checkedWID.replace('~'+wId, '');
			amount = amount-parseInt(amt);
			caseCount--;
			document.getElementById("row"+count).style.backgroundColor="";
		}
	/* var elements=document.getElementsByName('individualPayment');
	for (i=0;i<elements.length;i++)
		{
			if(elements[i].type=="checkbox")
				{
					if(elements[i].checked==true)
						{
							var spltValue=elements[i].value.split('@');
							var currStat=spltValue[3];
							if(currStat=='WF_BNK_PMNT_SENT')
								document.getElementById("row"+(i+1)).style.backgroundColor="#FFE9CA";
							else if(currStat=='WF_PAO_KEPT_PENDING_UPDATED')
								document.getElementById("row"+(i+1)).style.backgroundColor="#D8C5F1";
							else
								document.getElementById("row"+(i+1)).style.backgroundColor="#CAFFDC";
						}
					else
						document.getElementById("row"+(i+1)).style.backgroundColor="#f1f2f7";
			     }
		}  */
		document.getElementById("caseCnt").innerHTML="<b><font color=red>"+caseCount+"</font></b>";
		document.getElementById("totAmt").innerHTML="<b><font color=red>"+amount+"</font></b>";

	 document.panelDocPayForm.amount.value=amount;
	
}

function checkAllBoxes(arg){
	var chckAmt=0;
	var chckDoc='';
	var wIDs='';
	var prevWF='';
	var spltArg;
	arg=document.getElementById(arg);
	 var aInputs = document.getElementsByName('individualPayment');
	    for (var i=0;i<aInputs.length;i++) {
		        spltArg=aInputs[i].value.split('@');
        		chckDoc=spltArg[0];
        		chckAmt=spltArg[1];
        		wIDs=spltArg[2];
        		prevWF=spltArg[3];
	        	if(arg.checked && !aInputs[i].checked &&
	        			document.getElementById('row'+(i+1)+'').style.display!='none')
	    		{
	        		aInputs[i].checked = arg.checked;
	        		checkedValue = checkedValue+'~'+chckDoc;
	        		checkedWID = checkedWID+'~'+wIDs;
	        		amount=amount+parseFloat(chckAmt);
	        		caseCount++;
	    			document.getElementById("row"+(i+1)).style.backgroundColor="#8adecb";
	    			//document.getElementById("row"+(i+1)).style.backgroundColor="#CAFFDC";
	    			/* 
	    			if(prevWF=='WF_BNK_PMNT_SENT')
						document.getElementById("row"+(i+1)).style.backgroundColor="#FFE9CA";
					else if(prevWF=='WF_PAO_KEPT_PENDING_UPDATED')
						document.getElementById("row"+(i+1)).style.backgroundColor="#D8C5F1";
					else
						document.getElementById("row"+(i+1)).style.backgroundColor="#CAFFDC"; */
	    		}
	    	else if(!arg.checked && aInputs[i].checked)
	    		{
		    		aInputs[i].checked = arg.checked;
		    	    checkedValue = checkedValue.replace('~'+chckDoc, '');
		    	    checkedWID = checkedWID.replace('~'+wIDs, '');
		    		amount = amount-parseFloat(chckAmt);
					//document.getElementById("row"+(i+1)).style.backgroundColor="#f1f2f7";
					caseCount--;
					document.getElementById("row"+(i+1)).style.backgroundColor="";
	    		} 
        		
	        	 document.panelDocPayForm.amount.value=amount;
	        	 
	        	/*  if(arg.checked || !arg.checked)
	 	    		focusBox(document.getElementById("paySent1")); */
	        }
	    
	     if(document.getElementById("checkAll").checked)
		{
			 document.getElementById('actionSubmit').focus();
		}  
	    document.getElementById("caseCnt").innerHTML="<b><font color=red>"+caseCount+"</font></b>";
		document.getElementById("totAmt").innerHTML="<b><font color=red>"+amount+"</font></b>";
}
function selectDeselect()
	{
		var aInputs = document.getElementsByName('individualPayment');
		var count=0;
		for (var i=0;i<aInputs.length;i++) 
			{
				if(aInputs.checked)
					count++;
			}
		if(count!=aInputs.length)
			document.getElementById("checkAll").checked=false;
	}
function fn_loadImage()
{
	document.getElementById('processImagetable').style.display="";
}

function getCases(){
	fn_loadingImage();
	 if(document.panelDocPayForm.actionSelect.value == "Accounts Claim Head Rejected")
		 {
			 document.forms[0].action = "panelDocPay.do?actionFlag=viewRejctdCasesCEO";  
		 }
	 else if(document.panelDocPayForm.actionSelect.value == "Bank Rejected Payments")
		 {
			 document.forms[0].action = "panelDocPay.do?actionFlag=viewBankRejctdCasesCeo";  
		 }
	 else if(document.panelDocPayForm.actionSelect.value == "Sent Back Payments")
		 {
			 document.forms[0].action = "panelDocPay.do?actionFlag=getPanelDocPaymentRecrds&sentBack=Y";  
		 }
	else 
		 {
  			 document.forms[0].action = "panelDocPay.do?actionFlag=getPanelDocPaymentRecrds";  
		 }
	 
  		 document.forms[0].submit();
		
	
}


function fn_submitOnConfirm(arg)
	{
	
	 var transAmt=parseInt(0);
		$('#panelDocPayDtls tr').each(function(){
			 var amountRow=$(this).find(".pnlDocPayment").html(); 
			 if(amountRow!=null && amountRow !='')
				 {
				 	if($(this).find(":checkbox").prop('checked'))
				 		{
				 	transAmt=transAmt+parseInt(amountRow);
				 		}
				 }
		  });

		var r = confirm("Transaction Amount : "+transAmt+"\n Do you want to continue ?");
			  if (r == true) {
				
				 var type=arg;
					 document.forms[0].action="panelDocPay.do?actionFlag=getPayClaimForCasesCeo&checkedDocValues="+checkedValue+"&action="+type+"&checkedWID="+checkedWID;
					fn_loadingImage();
				    document.forms[0].submit();
				
			  } else 
			  {
				  document.getElementById("actionSubmit").disabled=false;
			      return false;
			  } 
		
	}
function fn_submitOnConfirmSentBack(arg)
	{
		var type=arg;
		document.forms[0].action="panelDocPay.do?actionFlag=getPayClaimForCasesCeo&checkedDocValues="+checkedValue+"&action="+type+"&checkedWID="+checkedWID;
		fn_loadingImage();
	    document.forms[0].submit();
	}	
function fn_generateFile(arg)
{
	var caseType=arg;
	
	var checkFlag = 'N';
	 var aInputs = document.getElementsByName('individualPayment');
	for (var i = 0; i < aInputs.length; i++) {
	  if(aInputs[i].checked)
		  {checkFlag='Y';
		 
		  break;}
	  else{
		  checkFlag='N';
		 
	  }
	}
	if(document.getElementById("paySent1").checked==true)
		{
			if(checkFlag!=null && checkFlag=='Y')
				{
					document.getElementById("actionSubmit").disabled=true;
					fn_submitOnConfirm(caseType);
				}
			else
				{
					//alert("Please select atleast one Panel Doctor for payment");
					//focusBox(document.getElementsByName('individualPayment'));
					//window.scrollTo(0, 0);
					document.getElementById("noSelect1").style.display="";
					document.getElementById("noSelect2").style.display="none";
					document.getElementById("noSelect3").style.display="none";
					document.getElementById("noSelect4").style.display="none";
					document.getElementById("noSelect5").style.display="none";

					return;
				}
		}
	else if(document.getElementById("paySent2").checked==true)
		{
			if(checkFlag!=null && checkFlag=='Y')
				{
					var value=document.getElementById("ceoRemarks").value;
					if(document.getElementById("allDepts").value=='' || document.getElementById("allDepts").value=='-1'
							|| document.getElementById("allUsers").value==''||document.getElementById("allUsers").value=='-1')
						{
							if(document.getElementById("allDepts").value=='' || document.getElementById("allDepts").value=='-1')
								{
									document.getElementById("noSelect1").style.display="none";
									document.getElementById("noSelect2").style.display="none";
									document.getElementById("noSelect3").style.display="";
									document.getElementById("noSelect4").style.display="none";
									document.getElementById("noSelect5").style.display="none";
									
									$('#allDepts').select2('open');
								}
							else if(document.getElementById("allUsers").value=='' || document.getElementById("allUsers").value=='-1')
								{
									document.getElementById("noSelect1").style.display="none";
									document.getElementById("noSelect2").style.display="none";
									document.getElementById("noSelect3").style.display="none";
									document.getElementById("noSelect4").style.display="";
									document.getElementById("noSelect5").style.display="none";
									
									$('#allUsers').select2('open');
								}
						}
					else if(value.length>3000)
						{
							document.getElementById("noSelect1").style.display="none";
							document.getElementById("noSelect2").style.display="none";
							document.getElementById("noSelect3").style.display="none";
							document.getElementById("noSelect4").style.display="none";
							document.getElementById("noSelect5").style.display="";
							focusBox(document.getElementById("ceoRemarks"));
							return false;
						}
					else
						{
							document.getElementById("actionSubmit").disabled=true;
							fn_submitOnConfirmSentBack('sendBack');
						}	
				}
			else
				{
					//window.scrollTo(0, 0);
					document.getElementById("noSelect1").style.display="none";
					document.getElementById("noSelect2").style.display="";
					document.getElementById("noSelect3").style.display="none";
					document.getElementById("noSelect4").style.display="none";
					document.getElementById("noSelect5").style.display="none";
					
					return;
				}
		}
	else if(document.getElementById("paySent1").checked==false
			&& document.getElementById("paySent2").checked==false)
		{
			alert("Please select either Pay Now or Send Back to Proceed");
			focusBox(document.getElementById("paySent1"));
			return false;
		}
}


function fn_pagination(pageId,end_index)
{
	 if(document.panelDocPayForm.actionSelect.value == "Accounts Claim Head Rejected")
	 {
		 document.forms[0].action = "panelDocPay.do?actionFlag=viewRejctdCasesCEO&pageId="+pageId+"&end_index="+end_index;  
	 }
	 else if(document.panelDocPayForm.actionSelect.value == "Bank Rejected Payments")
	 {
		 document.forms[0].action = "panelDocPay.do?actionFlag=viewBankRejctdCasesCeo&pageId="+pageId+"&end_index="+end_index;  
	 }
	 else
	 {
	 
 		 document.forms[0].action = "panelDocPay.do?actionFlag=getPanelDocPaymentRecrds&pageId="+pageId+"&end_index="+end_index;  
	 }
	 fn_loadingImage();
	document.forms[0].method="post";
	document.forms[0].submit();
}

function viewPreviousPages(pageNo,noOfPages,selectedPage,end_index)
	{
		var pageDisplays='';
		pageDisplays=pageDisplays+'<ul>';
		var pageNoLim=pageNo;
		var minPageNo=pageNo-10;
		if(minPageNo>1)
			{
				pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages('+minPageNo+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer;"></span></li>';
			}
		for(var i=minPageNo;i<pageNoLim;i++)
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
		pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer;"></span></li></ul>';
		document.getElementById("pageNoDisplay").innerHTML=pageDisplays;
	}
function viewNextPages(pageNo,noOfPages,selectedPage,end_index)
	{
		var pageDisplays='';
		pageDisplays=pageDisplays+'<ul>';
		var pageNoLim=pageNo+10;
		if(pageNoLim>noOfPages)
			{
				pageNoLim=noOfPages;
			}
		
		pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages('+pageNo+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer;"></span></li>';
		for(var i=pageNo;i<=pageNoLim;i++)
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
		if(pageNoLim<noOfPages)
			{
				pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer;"></span></li></ul>';
			}
		document.getElementById("pageNoDisplay").innerHTML=pageDisplays;
	}

</script>
</head>
<style>
body{font-size:1.2em !important;
}
</style>

<body  onload="fn_removeLoadingImage();">
<center>
<html:form action="/panelDocPay.do" method="post" styleId="panelDocPayForm">
<html:hidden name="panelDocPayForm" property="result" />
<html:hidden name="panelDocPayForm" property="totalAmt" />
<html:hidden name="panelDocPayForm" property="actionSelect" />
<html:hidden name="panelDocPayForm" property="userType" />
<html:hidden name="panelDocPayForm" property="statusCeo" />
<div class="container">
<div class="tbheader"  class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-top:20px">
		<font color="white" size="3"><b>Panel Doctor Payments</b></font>
</div>
<br>

<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding">
		
			<font color="#428bca" ><b>Payment Status : &nbsp;</b></font>
	
			<html:select name="panelDocPayForm" styleClass="paymentStatus-ceo" property="paymentStatus" styleId="paymentStatus" style="WIDTH: 300px" onchange="javascript:fn_pmntType()">
			 	<logic:notEmpty name="panelDocPayForm" property="paymentStatusList">
					<html:options collection="PaymentStatusList" property="IDVAL" labelProperty="VALUE"/>
				</logic:notEmpty>
			</html:select>
<!-- 		
  <div  style="color:#1fb5ad;font-weight:bold;float:right;margin-right: 70px;">
		<input type="text" class="form-control search inline" placeholder="Search" id="searchText1" data-placement="top" data-toggle="tooltip" data-original-title="Enter text to Search" onchange="javascript:selectDeselect();">
 </div> -->		
</div> 

<c:if test="${singleRowShow ne null}">
<div class=" col-lg-8 col-md-8 col-sm-8 col-xs-12" style="padding:0px; !important;margin-top:8px;" align="center">
	<div class="alert alert-success fade in" id="msgAlert" style="padding:3px; !important;padding:3px;text-align:left">
                                <button data-dismiss="alert" class="close close-sm" type="button">
                                    <i class="fa fa-times sizeChange"></i>
                                </button>
                                <strong> <b>${singleRowShow}</b> </strong>
    </div>
</div>
<div class=" col-lg-3 col-md-3 col-sm-3 col-xs-3">&nbsp;</div>                         	
</c:if>

 <div id="processImagetable" style="top:60%;width:90%;position:absolute;z-index:60;height:100%;display:none">
   <table border="0" align="center" width="90%" style="height:400" >
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

<logic:equal value="List Not Found" name="panelDocPayForm" property="flag">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="center">
		<br><br><br><br>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-exclamation-triangle"></i>&nbsp;&nbsp;No Records Found
	</div>	
</logic:equal>
<logic:equal value="List Found" name="panelDocPayForm" property="flag" >
 <logic:notEmpty name="panelDocPayForm"  property="panelDocList"> 

<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding" style="margin-top:15px">
<%-- <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6 noPadding " align="left" >
	Total Records:${noOfRecords}
</div> --%>
 <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6 noPadding" align="left" >
 	Displaying Records from ${start_index+1} - ${endresults} of ${noOfRecords}
 </div>
 <div class="col-lg-4 col-md-4 col-sm-4 col-xs-6 noPadding" id="pageNoDisplay" align="left" >	
	<ul class="pagination noPadding">
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
		<li class="noPadding"><a href="#"><span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages(<%=minVal%>,<%=noOfPages%>,<%=selectedPage%>,<%=end_index%>)" style="cursor: pointer;"></span></a></li>
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
		<li class="active noPadding"><span><%=pageNo%></span></li> 
		<%
	}
	else
	{
		%>
		<%-- <a href="javascript:fn_pagination(<%=pageNo%>,<%=end_index%>)"><b><%=pageNo%></b></a>&nbsp; --%>
		<li class="noPadding"><a href="javascript:fn_pagination(<%=pageNo%>,<%=end_index%>)"><%=pageNo%></a></li>
		<%
	}
}
if(pageNo<noOfPages)
	{
		%>
		<%-- <a href="javascript:viewNextPages(<%=pageNo+1%>,<%=noOfPages%>,<%=selectedPage%>,<%=end_index%>)">Next</a> --%>
		<li class="noPadding"><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages(<%=pageNo+1%>,<%=noOfPages%>,<%=selectedPage%>,<%=end_index%>)" style="cursor: pointer;"></span></li>
		<%
	}
%>
</ul>
 		</div>
 	<div class="col-lg-5 col-md-5 col-sm-5 col-xs-6 noPadding"  align="left" >	
 	<ul class="pagination ">
	<li class="lispacing ">Showing results in sets of &nbsp; </li><c:if test="${end_index ne 10}"><li><a href="javascript:fn_pagination(1,10)">10</a></li></c:if>
												<c:if test="${end_index eq 10}"><li class="active"><span>10</span></li></c:if>	
												<c:if test="${end_index ne 20}"><li><a href="javascript:fn_pagination(1,20)">20</a></li></c:if>
												<c:if test="${end_index eq 20}"><li class="active"><span>20</span></li></c:if>
												<c:if test="${end_index ne 50}"><li><a href="javascript:fn_pagination(1,50)">50</a></li></c:if>
												<c:if test="${end_index eq 50}"><li class="active"><span>50</span></li></c:if>
												<c:if test="${end_index ne 100}"><li><a href="javascript:fn_pagination(1,100)">100</a></li></c:if>
												<c:if test="${end_index eq 100}"><li class="active"><span>100</span></li></c:if><!-- </td> -->
												
	</ul> </div> 
 

</div>

<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin:5px">
	<div class="col-lg-4 col-md-4 col-sm-4 col-xs-6">
		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 noPadding" style="width:15px;height:15px;background:#d8f5e2;border:1px solid black;">
				&nbsp;
		</div>
		<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10 noPadding" style="text-align: left;">
			&nbsp;&nbsp;Accounts JEO Approved
		</div>	
	</div>
	<div class="col-lg-4 col-md-4 col-sm-4 col-xs-6">
		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 noPadding" style="width:15px;height:15px;background:#FFE9CA;border:1px solid black;">
			&nbsp;
		</div>
		<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10 noPadding" style="text-align: left;">
			&nbsp;&nbsp;Bank Rejected Payments
		</div>
	</div>
	<div class="col-lg-4 col-md-4 col-sm-4 col-xs-6">
		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 noPadding" style="width:15px;height:15px;background:#e7e6f1;border:1px solid black;">
			&nbsp;
		</div>
		<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10 noPadding" style="text-align: left;">
			&nbsp;&nbsp;Sent Back Payments
		</div>
	</div>
</div>

<div  class="col-lg-12 col-md-12 col-sm-12 col-xs-12 table " style="margin-top:15px;">
<bean:size  id="ltSize" name="panelDocPayForm"  property="panelDocList"/>
		<logic:greaterThan value="0" name="ltSize">
					 <table id="cntTbl" width="80%" align="center" cellspacing="5">
      		 <tr >
      		 <td  class="tbheader1 " width="33%" height="5%"><b>Total Number Of Pending Cases:</b></td>
      		 <td  class="tbcellBorder" id="totalCasesPending" width="17%"  height="5%"><b><font color="red">${ltSize}</font></b></td>
 
      		
      		  <td  class="tbheader1" width="33%" height="5%"><b>Total Amount that can be Approved :</b></td>
      		 <td  class="tbcellBorder"  width="17%" height="5%"><b><font color="red">Rs ${totalPendingAmt}/-</font></b></td>
 		 		 
      		 </tr> 
      		 
      		 </table>
      		 </logic:greaterThan>
      		 
      	
</div>
 <table  width="100%" align="center" >
 
 <tr><td>
<%-- <%int i = 1;%>
<display:table  id="panelDocPayVO"  name="panelDocPayForm.panelDocList" pagesize="100" style="width:100%;align:center;border:2;rowspan:5" export="false" requestURI="/panelDocPay.do"  cellpadding="2" cellspacing="2" decorator="com.ahct.panelDoctor.util.PanelDocDecorator">


<display:column value="<%=i++ %>" title="S No"  style="text-align:center;width:5%"  />

<display:column   property="DOC_NAME" title="Panel Doctor" style="width:15%"/>
<display:column   property="ACCOUNTNO" title="Account No" style="width:15%"/>
<display:column  property="CL_APP_AMT" title="Claim Approved Amount" style="text-align:right;width:11%"/>
<display:column  property="CL_REJ_AMT" title="Claim Rejected Amount" style="text-align:right;width:11%"/>
<display:column  property="CL_PEND_AMT" title="Claim Pending Amount" style="text-align:right;width:11%"/>
<display:column  property="PR_APP_AMT" title="Preauth Approved Amount" style="text-align:right;width:11%"/>
<display:column  property="PR_REJ_AMT" title="Preauth Rejected Amount" style="text-align:right;width:11%"/>
<display:column  property="PR_PEND_AMT" title="Preauth Pending Amount" style="text-align:right;width:11%"/>
<display:column  property="AMOUNT" title="Total Amount" style="text-align:right;width:11%"/>
<display:column property="ID" title="<input type='checkbox' name='checkId' id='check_id' title='select All' onclick='javascript:checkAll(this);' />" media="html" >
  
</display:column>
</display:table> --%>





<table class="col-lg-12 col-md-12 col-sm-12 col-xs-12 table" width="100%" id="panelDocPayDtls" >
	<thead>
		<tr>
				<th class="tbheader1 leftMargin" >
					<b>S.No</b>
				</th>
				<th class="tbheader1 leftMargin" >
					<html:checkbox name="panelDocPayForm" property="checkAll" styleId="checkAll"  onclick="checkAllBoxes(this.id)" title="Select All"></html:checkbox>
				</th>
				<th class="tbheader1 leftMargin" >
					<b>Panel Doctor</b>
				</th>
				<th class="tbheader1 leftMargin" >
					<b>Month - Year  </b>
				</th>
			
				<th class="tbheader1 leftMargin hidden-xs" >
					<b>Claim Approved Amount </b> 
				</th>
				<th class="tbheader1 leftMargin hidden-xs" >
					<b>Claim Rejected Amount  </b>
				</th>
				<th class="tbheader1 leftMargin hidden-xs" >
					<b>Claim Pending Amount </b>
				</th>
				<th class="tbheader1 leftMargin hidden-xs" >
					<b>Preauth Approved Amount</b>
				</th>
				<th class="tbheader1 leftMargin hidden-xs" >
					<b>Preauth Rejected Amount</b>
				</th>
				<th class="tbheader1 leftMargin hidden-xs" >
					<b>Preauth Pending Amount </b>
				</th>
				<th class="tbheader1 leftMargin" >
					<b>Total Amount</b>
				</th>
				<%-- <c:if test="${Action eq 'Y'}">
					<th class="tbheader1 leftMargin" >
						<b>Action</b>
					</th>
				</c:if> --%>
		</tr>
	</thead>		
		<%int i = 1;%>
		<c:set var="count" value="1"></c:set>
		<logic:iterate name="panelDocPayForm" property="panelDocList" id="results">
		<tr  id="row${count}" >
			<td class="tbcellBorder" >
				<%=i++ %>
			</td>
			<td class="tbcellBorder" align="center" >
				<html:checkbox name="panelDocPayForm" property="individualPayment" styleId="individualPayment"  onclick="fn_addCheckValues('${results.DOC_ID}','${results.TOTAL_PNLDOC_AMT}','${results.WID}','${count}')" value="${results.DOC_ID}@${results.TOTAL_PNLDOC_AMT}@${results.WID}@${results.PREVWRKFLWID}" title="Click to select"></html:checkbox>
			</td>
			<td class="tbcellBorder" >
				<a href="javascript:popUp('${results.DOC_ID}','${results.DOC_NAME}','${results.WID}')"><font color="#FF4444"><bean:write name="results" property="EMPNAME" />&nbsp;<bean:write name="results" property="DOC_NAME" /></font></a>
				<%-- <font color="#F00000"><bean:write name="results" property="DOC_NAME" /></font> --%>
			</td>
			<td class="tbcellBorder" >
		
			<c:choose>
			<c:when  test="${results.MONTH eq '01'}">
			JANUARY - <bean:write name="results" property="YEAR" />
			</c:when>
			
			
			<c:when  test="${results.MONTH eq '02'}">
			FEBRUARY - <bean:write name="results" property="YEAR" />
			</c:when>
			
			
			<c:when  test="${results.MONTH eq '03'}">
			MARCH - <bean:write name="results" property="YEAR" />
			</c:when>
				
			<c:when  test="${results.MONTH eq '04'}">
			APRIL - <bean:write name="results" property="YEAR" />
			</c:when>
			
			<c:when  test="${results.MONTH eq '05'}">
			MAY - <bean:write name="results" property="YEAR" />
			</c:when>
			
			
			<c:when  test="${results.MONTH eq '06'}">
			JUNE - <bean:write name="results" property="YEAR" />
			</c:when>
			
			
			<c:when  test="${results.MONTH eq '07'}">
			JULY - <bean:write name="results" property="YEAR" />
			</c:when>
			
			<c:when  test="${results.MONTH eq '08'}">
			AUGUST - <bean:write name="results" property="YEAR" />
			</c:when>
			
			<c:when  test="${results.MONTH eq '09'}">
			SEPTEMBER - <bean:write name="results" property="YEAR" />
			</c:when>
			
			
			<c:when  test="${results.MONTH eq '10'}">
			OCTOBER - <bean:write name="results" property="YEAR" />
			</c:when>
			
			
			<c:when  test="${results.MONTH eq '11'}">
			NOVEMBER - <bean:write name="results" property="YEAR" />
			</c:when>
			
			
			<c:when test="${results.MONTH eq '12'}">
			DECEMBER - <bean:write name="results" property="YEAR" />
			</c:when>
			
			<c:otherwise>
			
			<bean:write name="results" property="MONTH" /> - <bean:write name="results" property="YEAR" />
			</c:otherwise>
			</c:choose>
			</td>
			
			
			
			<td class="tbcellBorder hidden-xs" >
				<bean:write name="results" property="CLAIM_APRV_AMT" />
			</td>
			<td class="tbcellBorder hidden-xs" >
				<bean:write name="results" property="CLAIM_REJ_AMT" />
			</td>
			<td class="tbcellBorder hidden-xs" >
				<bean:write name="results" property="CLAIM_PEND_AMT" />
			</td>
			<td class="tbcellBorder hidden-xs" >
				<bean:write name="results" property="PREAUTH_APRV_AMT" />
			</td>
			<td class="tbcellBorder hidden-xs" >
				<bean:write name="results" property="PREAUTH_REJ_AMT" />
			</td>
			<td class="tbcellBorder hidden-xs" >
				<bean:write name="results" property="PREAUTH_PEND_AMT" />
			</td>
			<td class="tbcellBorder" title="Final Amount">
				<font color="blue"><i class="fa fa-inr"></i><span class="pnlDocPayment"><bean:write name="results" property="TOTAL_PNLDOC_AMT" /></span></font>
			</td>
			<%-- <c:if test="${Action eq 'Y'}">
				<td align="center" class=" tbcellBorder"  >
					<div style="display:inline-flex;">
					
					<a href="javascript:fn_SubmitSingleRow('Approve','<bean:write name="results" property="DOC_ID"/>','<bean:write name="results" property="WID"/>')" id="approveS"  data-toggle="class" title="Click to Pay" >
					<span class=" fa   fa-check text-success text-active"></span>
					</a>&nbsp;
				
					<a href="javascript:fn_SubmitSingleRow('<fmt:message key='EHF.Button.Reject' />','Reject','<bean:write name="results" property="DOC_ID"/>','<bean:write name="results" property="WID"/>')" id="RejectS"  data-toggle="class" title="Click to Reject" >
					<span class=" fa  fa-times text-danger text"></span>
					</a>&nbsp;
					</div>
				</td>
			</c:if> --%>
			
		</tr>
		<c:set var="count" value="${count+1}"></c:set>
		</logic:iterate>
</table>
</td></tr>
</table> 
<html:hidden name="panelDocPayForm" property="rejId"/>
<%-- <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="center">
	<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
		&nbsp;
	</div>
	<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
		<div align="left" >
			<b>Sum of amount : </b>
			<b><html:text name="panelDocPayForm" size="14" styleId="amount" property="amount" readonly="true" /></b>
		</div>
	</div>	
</div> --%>
<%-- <html:radio style="" name="panelDocPayForm"  property="paySent" styleId="paySent1" title="Click to Authorize Payments" value="payments" onclick="javascript:fn_changeSentBack(this.id)"/>&nbsp;Pay Now&nbsp;&nbsp;&nbsp;&nbsp;
					<html:radio name="panelDocPayForm"  property="paySent" styleId="paySent2" title="Click to Send Back Payments" value="sentBack" onclick="javascript:fn_changeSentBack(this.id)"/>&nbsp;Send To --%>
<div  class="col-lg-12 col-md-12 col-sm-12 col-xs-12 table " style="margin-top:15px;">
<bean:size  id="listSize" name="panelDocPayForm"  property="panelDocList"/>
		<logic:greaterThan value="0" name="listSize">
					 <table id="countTbl" width="80%" align="center" cellspacing="5">
      		 <tr >

      		 <td  class="tbheader1" width="33%"  height="5%"><b>Total Number Of Cases Selected :</b></td>
      		 <td  class="tbcellBorder" id="caseCnt" width="17%"  height="5%"><b><font color="red">0</font></b></td>   
      		
      		 <td  class="tbheader1 tbcellCss" width="33%" height="5%"><b>Total Amount Being Approved :</b></td>
      		 <td class="tbcellBorder" id="totAmt" width="17%" height="5%"><b><font color="red">Rs 0/-</font></b></td>   		 		 
      		 </tr> 
      		 
      		 </table>
      		 </logic:greaterThan>
      		 
      	
</div>
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin:5px;" >
	<logic:equal value="PayNow" name="panelDocPayForm" property="rejId" >
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding" style="display:inline-flex;" align="center">
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4 noPadding" >&nbsp;</div>
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 noPadding" style="display: none;" >
						<b>Total amount : </b>
						<b><html:text name="panelDocPayForm" size="14" style="margin-top:10px;" styleId="amount" property="amount" readonly="true" /></b>&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<div class="icheck col-lg-5 col-md-5 col-sm-5 col-xs-6 noPadding"  >
	               	  	<div class="flat-green single-row" style="width:45%">
	                         <div class="radio" >
	                             <input checked type="radio" id="paySent1"  name="paySent"  value="payments"  title="Click to Authorize Payments" ><b>Pay Now</b>
	                         </div>
	                     </div>
	                     <div class="flat-yellow single-row">
	                         <div class="radio" >
	                             <input  type="radio" id="paySent2"  name="paySent"  value="sentBack"  title="Click to Send Back Payments" ><b>Send To</b>
	                         </div>
	                     </div> 
                     </div> 
				</div>
	<%-- 			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding" id="sentBackDivmg" style="display:none;padding-left:15px !important;margin-top:15px;" align="center">
						 <html:select name="panelDocPayForm" property="sendBack" styleClass="sentBack-ceo" styleId="sendBack" style="margin-top:15px;width:60%" >
						 <html:option value="">-SELECT-</html:option>
							<c:if test="${sentBackListValues eq 'show'}">
								<html:options collection="sentBackList" property="ID" labelProperty="VALUE" ></html:options>
							</c:if>
						</html:select>
				</div> --%>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 noPadding" id="sentBackDiv1" style="display:none;padding-left:0px !important;margin-top:15px" align="center">
							&nbsp;Sections &nbsp;:&nbsp; <html:select name="panelDocPayForm" property="allDepts" onchange="javascript:getAllUsers()" styleClass="sentBack3-ceo" styleId="allDepts" style="margin-top:15px;" >
							 <html:option value="-1">-SELECT-</html:option>
							</html:select>
							&nbsp;Users &nbsp;:&nbsp; <html:select name="panelDocPayForm" property="allUsers" styleClass="sentBack4-ceo" styleId="allUsers" style="margin-top:15px;width:25%" >
							 <html:option value="-1">-SELECT-</html:option>
							</html:select>
				</div>
				<div class="input-group  col-lg-12 col-md-12 col-sm-12 col-xs-12 " id="sentBackDiv2" style="display:none;padding-top:10px;">
					<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 noPadding" style="margin-top:15px;"><span style="float:right;">Remarks :</span></div> 
					<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 noPadding custom-control" style="padding-left:15px !important;" >
					<html:textarea styleClass="form-control  " onchange="javascript:textCounter(this.id,'Length of Remarks cannot be more than ',3000)" property="ceoRemarks" style="float:left;" name="panelDocPayForm" rows="2" cols="80" styleId="ceoRemarks"></html:textarea>
					</div>
				</div>	
				<br>
				
	</logic:equal>
</div>

<div class=" col-lg-8 col-md-8 col-sm-8 col-xs-12" style="padding:0px; !important;margin-top:8px;" align="center">
  <div class="alert  alert-block alert-danger fade in" id="noSelect1" style="display:none;padding:3px;text-align:left" >
                                <strong>Please select atleast one Panel Doctor Payment to pay now.</strong>
                                <i class="fa fa-times sizeChange"  style="cursor:pointer;float:right;" onclick="fn_hide('noSelect1');"></i>
 </div>
</div>

<div class=" col-lg-8 col-md-8 col-sm-8 col-xs-12" style="padding:0px; !important;margin-top:8px;" align="center">
  <div class="alert  alert-block alert-danger fade in" id="balanceChk" style="display:none;padding:3px;text-align:left" >
                                <strong>Total amount is exceeded Remaining Balance. Payment can't proceed.</strong>
                                <i class="fa fa-times sizeChange"  style="cursor:pointer;float:right;" onclick="fn_hide('balanceChk');"></i>
 </div>
</div>

<div class=" col-lg-8 col-md-8 col-sm-8 col-xs-12" style="padding:0px; !important;margin-top:8px;" align="center"> 
   <div class="alert  alert-block alert-danger fade in" id="noSelect2" style="display:none;padding:3px;text-align:left">
                                <strong>Please select atleast one Panel Doctor Payment to Send Back to respective role.</strong>
                                <i class="fa fa-times sizeChange" style="cursor:pointer;float:right;" onclick="fn_hide('noSelect2');"></i>
   </div>
</div>  
<div class=" col-lg-8 col-md-8 col-sm-8 col-xs-12" style="padding:0px; !important;margin-top:8px;" align="center">
  <div class="alert  alert-block alert-danger fade in" id="noSelect3" style="display:none;padding:3px;text-align:left" >
                                <strong>Please select a Section to Send Back.</strong>
                                <i class="fa fa-times sizeChange" style="cursor:pointer;float:right;" onclick="fn_hide('noSelect3');"></i>
 </div>
</div>
<div class=" col-lg-8 col-md-8 col-sm-8 col-xs-12" style="padding:0px; !important;margin-top:8px;" align="center">
  <div class="alert  alert-block alert-danger fade in" id="noSelect4" style="display:none;padding:3px;text-align:left" >
                                <strong>Please select any User to Send Back.</strong>
                                <i class="fa fa-times sizeChange" style="cursor:pointer;float:right;" onclick="fn_hide('noSelect4');"></i>
 </div>
</div>
<div class=" col-lg-8 col-md-8 col-sm-8 col-xs-12" style="padding:0px; !important;margin-top:8px;" align="center">
  <div class="alert  alert-block alert-danger fade in" id="noSelect5" style="display:none;padding:3px;text-align:left" >
                                <strong>Length of Remarks cannot be more than 3000 characters.</strong>
                                <i class="fa fa-times sizeChange" style="cursor:pointer;float:right;" onclick="fn_hide('noSelect5');"></i>
 </div>
</div>
<logic:equal value="PayNow" name="panelDocPayForm" property="rejId" >
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="" align="center">
	<button class="btn btn-round btn-primary" style="" type="button" id="actionSubmit" onclick="javascript:fn_generateFile('PayNow')">Submit</button><br><br><br>
</div>
</logic:equal>
</logic:notEmpty> 
</logic:equal>

<%-- <c:if test="${saveMsg ne null}">

<c:if test="${updatedCase ne null}">
	<script>
	var updatedCase='${updatedCase}';
		 goToHomePage(updatedCase);
	</script>
</c:if>
<c:if test="${updatedCase eq null}">
	<script>
	var updatedCase='${updatedCase}';
 	     goToHomePage(updatedCase);
	</script>
</c:if>	
</c:if> --%>
<c:if test="${failAccList ne null}">
<script>
//var failList='${failAccList}';
//failList=failList.replace(",", "  <br> ");
//jqueryInfoMsg('Panel Doctor Information','Some Claims Of Doctor(s) Were Not Processed Due To Unavailability of Bank Details:  <br>'+failList);
</script>
</c:if>
<div class="modal fade"   id="progressBar" >
<div class="modal-dialog modal-lg">
 
      <div class="modal-body">
 
 <div class="centerProgress" style="margin-right:45%;">
  <div class="progress">
    <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%">
      <span>Please Wait...</span>
    </div>
  </div>
</div>
</div></div></div>

 <div class="scroll-top-wrapper tooltips"  data-placement="top" data-toggle="tooltip" title="Go to Top" >
			<span class="scroll-top-inner" style="display:none">
				<i class="fa fa-2x fa-arrow-circle-up"></i>
			</span>
		</div> 
<script>

			$(".paymentStatus-ceo").select2();
			/* $(".sentBack-ceo").select2(); */

function fn_hide(arg)
	{
		document.getElementById(arg).style.display="none";
	}
function fn_SubmitSingleRow(action,docCheckID,checkWID)
	{
		var type=document.panelDocPayForm.rejId.value;
		fn_loadingImage();
		document.forms[0].action="panelDocPay.do?actionFlag=getPayClaimForCasesCeo&singleRow=Y&checkedDocValues="+docCheckID+"&action="+type+"&checkedWID="+checkWID;
	    document.forms[0].submit();
	}
function fn_changeSentBack(arg)
	{
	
	document.getElementById("noSelect1").style.display="none";
	document.getElementById("noSelect2").style.display="none";
		if(arg=='paySent1')
			{
				if(document.getElementById("paySent1").checked=true)
					{
						document.getElementById("ceoRemarks").value="";
						document.getElementById("sentBackDiv1").style.display="none";
						document.getElementById("sentBackDiv2").style.display="none";
						document.getElementById("actionSubmit").className="btn btn-round btn-primary";
					}
				
			}	
		else if(arg=='paySent2')
			{
				if(document.getElementById("paySent2").checked=true)
					{
						getDepts();
						document.getElementById("ceoRemarks").value="";
						document.getElementById("sentBackDiv1").style.display="";
						document.getElementById("sentBackDiv2").style.display="";
						document.getElementById("actionSubmit").className="btn btn-round btn-warning";
						focusBox(document.getElementById("actionSubmit"));
					}
			}	
			
		}
$('#paySent1').on('change', function() {
	
	document.getElementById("noSelect1").style.display="none";
	document.getElementById("noSelect2").style.display="none";
	document.getElementById("ceoRemarks").value="";
	document.getElementById("sentBackDiv1").style.display="none";
	document.getElementById("sentBackDiv2").style.display="none";
	document.getElementById("actionSubmit").className="btn btn-round btn-primary";
	
	});
	
$('#paySent2').on('change', function() {
	getDepts();
	focusBox(document.getElementById("actionSubmit"));
	document.getElementById("noSelect1").style.display="none";
	document.getElementById("noSelect2").style.display="none";
	document.getElementById("ceoRemarks").value="";
	document.getElementById("sentBackDiv1").style.display="";
	document.getElementById("sentBackDiv2").style.display="";
	document.getElementById("actionSubmit").className="btn btn-round btn-warning";
	//window.scrollTo(0, $(window).height());
	 $("html, body").animate({ scrollTop: $(document).height() }, 1000);
	});
	
		
function fn_removeLoadingImage()
	{   
		document.getElementById('progressBar').style.display='none';
		applyColors();
	}

function applyColors()
	{
		var elements=document.getElementsByName('individualPayment');
		for (i=0;i<elements.length;i++)
			{
				if(elements[i].type=="checkbox")
					{
						var spltValue=elements[i].value.split('@');
						var currStat=spltValue[3];
						if(currStat=='WF_BNK_PMNT_SENT')
							document.getElementById("row"+(i+1)).className="bnksntBackGround";
						else if(currStat=='WF_PAO_KEPT_PENDING_UPDATED')
							document.getElementById("row"+(i+1)).className="sntupdBackground";
						else
							document.getElementById("row"+(i+1)).className="ajeoBackground";
				     }
			}
	}

	
function textCounter(id,arg,maxLength)
	{
		var value=document.getElementById(id).value;
		if(value.length>maxLength)
			{
				document.getElementById("noSelect1").style.display="none";
				document.getElementById("noSelect2").style.display="none";
				document.getElementById("noSelect3").style.display="none";
				document.getElementById("noSelect4").style.display="none";
				document.getElementById("noSelect5").style.display="";
				focusBox(document.getElementById("ceoRemarks"));
				return false;
			}
	}
	
</script>
</html:form>
</center>
</body>
</html>