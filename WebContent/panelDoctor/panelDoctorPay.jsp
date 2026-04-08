<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
 <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"  %> 
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

       
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Panel Doctor Payments</title>
 <script src="js/jquery-1.9.1.js"></script>
    <%@ include file="/common/include.jsp"%> 
    <%-- <%@ include file="/common/includeCalendar.jsp"%> --%> 
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/themes/<%=themeColour%>/accountstyle.css" rel="stylesheet" type="text/css" />
<link href="css/select2.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css">
<link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.js"></script>
<script src="bootstrap/js/bootbox.min.js"></script>
<script src="js/select2.min.js"></script>
<script type="text/javascript">
var jsonDataLst;
var checkedValue="[";
var widChecked="[";
var amount= parseFloat("0");

$(document).ready(function (){
	$('.selectClass').select2();
	$("#monthDatePicker").datepicker({
				viewMode: 'months',
				minViewMode: 'months',
				endDate:new Date(),
		    	format: 'mm-yyyy',
		    	todayHighlight:true,
		    	autoclose:true,
		    	clearBtn:true
			});
});

function partial(func /*, 0..n args */) {
	 var args = new Array();
	 for (var i = 1; i < arguments.length; i++) { args.push(arguments[i]); }
	  return function() {
	    var allArguments = args.concat(Array.prototype.slice.call(arguments));
	    return func.apply(this, allArguments);
	  };
}

function focusBox(arg)
{	
aField = arg; 
setTimeout("aField.focus()", 0);  

}

function fn_loadImage()
{
	document.getElementById('processImagetable').style.display="";

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

function goToHomePage()
{
	
	document.forms[0].action = "panelDocPay.do?actionFlag=panelDocPayHome&searchFlag=${searchFlag}";  
	document.forms[0].submit();
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

function fn_viewMonth()
{

	monthlab.style.visibility='visible';
	month.style.visibility='visible';
	/* fromdatelab.style.visibility='hidden';
	todatelab.style.visibility='hidden';
	fromdate.style.visibility='hidden';
	todate.style.visibility='hidden'; */
}

function fn_viewDate()
{
	
	monthlab.style.visibility='hidden';
	month.style.visibility='hidden';
	fromdatelab.style.visibility='visible';
	todatelab.style.visibility='visible';
	fromdate.style.visibility='visible';
	todate.style.visibility='visible';
}

function fn_hideAll()
{
	monthlab.style.visibility='hidden';
	month.style.visibility='hidden';
	/* fromdatelab.style.visibility='hidden';
	todatelab.style.visibility='hidden';
	fromdate.style.visibility='hidden';
	todate.style.visibility='hidden'; */
}

function fn_pmntType()
{
	var pmntType=document.panelDocPayForm.paymentStatus.value;
	if(pmntType=="00")
		{
		  var fr = partial(focusBox,document.panelDocPayForm.paymentStatus);
		  bootbox.alert("Please Select Payment status",function(){
			  	//focusBox(document.panelDocPayForm.paymentStatus);
			  	$('#paymentStatusId').select2("open");
			  });
		  
		  document.panelDocPayForm.dispType.value="00";
		  //fn_hideAll();
		  return false;
		}
	 else
		{
		var flag=document.panelDocPayForm.actionSelect.value;
				var pmntStatusValue=document.panelDocPayForm.paymentStatus;
				var pmntStatusLabel=pmntStatusValue.options[pmntStatusValue.selectedIndex].text;
				var indexSpace=pmntStatusLabel.lastIndexOf(" ");
				var action=pmntStatusLabel.substring(indexSpace+1);
				document.panelDocPayForm.actionSelect.value=action;
				
		if(!(flag == "" || flag == null))
     		{
			
			document.panelDocPayForm.dispType.value="00";
			document.panelDocPayForm.month.value="";
			/*fn_hideAll();
			 document.forms[0].action = "panelDocPay.do?actionFlag=panelDocPayHome&searchFlag=${searchFlag}";  
			document.forms[0].submit(); */
			}
		} 
}

function fn_viewType()
{
	
	var type=document.panelDocPayForm.dispType.value;
	var pmntType=document.panelDocPayForm.paymentStatus.value;
	if(pmntType=="00")
		{
		 var fr = partial(focusBox,document.panelDocPayForm.paymentStatus);
		  jqueryErrorMsg('Mandatory Field',"Please Select Payment status",fr);
		  document.panelDocPayForm.dispType.value="00";
		  fn_hideAll();
		  return false;
		}
	else{
	if(type == "01")
	{
		fn_viewMonth();
	
	}
	else if(type == "02")
		{
		fn_viewDate();
		}
	else if (type=="00")
		{
			monthlab.style.visibility='hidden';
			month.style.visibility='hidden';
			document.getElementById('monthPicker').value='';
		}
  }
}

function validateDate(arg1,input)
{
	
	var entered = input.value;
	entered = entered.split("-");
	var byr = parseInt(entered[2]); 
	
	if(isNaN(byr))
	{
		
		input.value="";
		jqueryShowContentMsg('Date Validation',"Please Select "+arg1);
		return false;
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
	var type=document.panelDocPayForm.dispType.value;
	
	
	if(document.panelDocPayForm.userType.value=="CD203")
	scheme=document.panelDocPayForm.scheme.value;
	
	if(type == "01" || type == "00")
	{
	popitup("panelDocPay.do?actionFlag=getAllCasesStatus&docId="+docId+"&docName="+docName+"&dispType="+type+"&month="+document.panelDocPayForm.month.value+"&pmntType="+pmntStatusLabel+"&scheme="+scheme+"&wId="+wId+"&searchFlag=${searchFlag}");
	
	}
	/* else if(type == "02")
	{
	popitup("panelDocPay.do?actionFlag=getAllCasesStatus&docId="+docId+"&docName="+docName+"&dispType="+type+"&fromDate="+document.panelDocPayForm.fromDate.value+"&toDate="+document.panelDocPayForm.toDate.value+"&pmntType="+pmntStatusLabel+"&wId="+wId);
		
	} */
}
function fn_allValidations()
{
	
	 if(document.panelDocPayForm.paymentStatus.value=="00")
	  {
		 
		 var fr = partial(focusBox,document.panelDocPayForm.paymentStatus);
	     //jqueryErrorMsg('Mandatory Field',"Please Select Payment Status",fr);
	     bootbox.alert('Please Select Payment Status',function(){
	    	 $('#paymentStatusId').select2("open");
	    	 //focusBox(document.getElementById('paymentStatusId').paymentStatus);
	     });
	     
	     return false;
	  }
	 if(document.panelDocPayForm.dispType.value  =="00" && document.panelDocPayForm.paymentStatus.value=="00" )
	  {
		 var fr = partial(focusBox,document.panelDocPayForm.dispType);
	  	 //jqueryErrorMsg('Mandatory Field',"Please Select View Type",fr);
	  	 bootbox.alert('Please Select View Type',function(){
	  		focusBox(document.panelDocPayForm.dispType);
	  	 });
	 	 return false;
	  }
	 if(document.panelDocPayForm.dispType.value=="01")
		 {
		 
		 if(document.panelDocPayForm.month.value=="" || document.panelDocPayForm.month.value==null)
		 {
		 var fr = partial(focusBox,document.panelDocPayForm.month);
		  //jqueryErrorMsg('Mandatory Field',"Please Select month",fr);
		  bootbox.alert('Please Select Month',function(){
		  		focusBox(document.panelDocPayForm.month);
		  	 });
		  return false;
		 }
		 }
	 /* else if(document.panelDocPayForm.dispType.value=="02")
		 {
		 
		 if(document.panelDocPayForm.fromDate.value=="" || document.panelDocPayForm.fromDate.value==null)
		 {
		 var fr = partial(focusBox,document.panelDocPayForm.fromDate);
		  jqueryErrorMsg('Mandatory Field',"Please Select from date",fr);
		  return false;
		 }
		
		 if(document.panelDocPayForm.toDate.value=="" || document.panelDocPayForm.toDate.value==null)
		 {
		 var fr = partial(focusBox,document.panelDocPayForm.toDate);
		  jqueryErrorMsg('Mandatory Field',"Please Select to date",fr);
		  return false;
		 }
		} */
	 
	 return true;
}

function disablingButtonsAndImgs(type)
{
	document.getElementById('getcases').disabled=true;
	document.getElementById('reset').disabled=true;
	if(type=="initiate")
	document.getElementById('actionInitiate').disabled=true;
	if(type=="appreject")
		{
			document.getElementById('actionSubmit').disabled=true;
			if(document.getElementById("actionSubmitRej")!=null)
				document.getElementById('actionSubmitRej').disabled=true;
		}
	else if(type=='sendBack')
		{
			document.getElementById('actionSubmitCEO').disabled=true;
			if(document.getElementById("actionSubmitRejCEO")!=null)
				document.getElementById('actionSubmitRejCEO').disabled=true;
		}
		
}

function getCases(){
	
	var pmntStatusValue=document.panelDocPayForm.paymentStatus;
	var pmntStatusLabel=pmntStatusValue.options[pmntStatusValue.selectedIndex].text;
	  if(fn_allValidations())
	  {document.getElementById('getcases').disabled=true;
		document.getElementById('reset').disabled=true;
		  fn_loadImage();
	 if(document.panelDocPayForm.actionSelect.value == "Rejected")
	 	{
			 document.forms[0].action = "panelDocPay.do?actionFlag=viewRejctdCases&pmntType="+pmntStatusLabel+"&searchFlag=${searchFlag}";  
	 	}
	 else if(document.panelDocPayForm.actionSelect.value == "CEO")
	 	{
		 	document.forms[0].action = "panelDocPay.do?actionFlag=getCases&type=CEOPending&pmntType="+pmntStatusLabel+"&searchFlag=${searchFlag}";
	 	}
	 else 
	 	{
	  		 document.forms[0].action = "panelDocPay.do?actionFlag=getCases&pmntType="+pmntStatusLabel+"&searchFlag=${searchFlag}";  
	    }
	 
  		 document.forms[0].submit();
		
	}
  
}
function Reset(){
	
	document.panelDocPayForm.paymentStatus.value="00";
	document.panelDocPayForm.dispType.value="00";
	fn_hideAll();
	document.forms[0].action = "panelDocPay.do?actionFlag=panelDocPayHome&searchFlag=${searchFlag}";  
	document.forms[0].submit();
}

function fn_addCheckValues(arg,amt,wId){
	var aInputs = document.getElementsByName('individualPayment');
	if(!checkedValue.match(arg))
		{
			checkedValue = checkedValue+'~'+arg;
			widChecked = widChecked+'~'+wId;
			amount=amount+parseFloat(amt);
		}
	else
		{
		checkedValue = checkedValue.replace('~'+arg, '');
		widChecked = widChecked.replace('~'+wId, '');
		amount = amount-parseFloat(amt);
		
		}
	 document.panelDocPayForm.amount.value=amount;
}

function checkAllBoxes(arg)
	{
		arg=document.getElementById(arg);
		var chckAmt=0;
		var chckDoc='';
		var spltArg;
		var wId='';
		 var aInputs = document.getElementsByName('individualPayment');
		    for (var i=0;i<aInputs.length;i++) 
		    	{
			        spltArg=aInputs[i].value.split('@');
	        		chckDoc=spltArg[0];
	        		chckAmt=spltArg[1];
	        		wId=spltArg[2];
	        		
		        	if(arg.checked && !aInputs[i].checked)
			    		{
			        		aInputs[i].checked = arg.checked;
			        		checkedValue = checkedValue+'~'+chckDoc;
			        		widChecked = widChecked+'~'+wId;
			        		amount=amount+parseFloat(chckAmt);
			    		}
			    	else if(!arg.checked && aInputs[i].checked)
			    		{
				    		aInputs[i].checked = arg.checked;
				    	    checkedValue = checkedValue.replace('~'+chckDoc, '');
				    	    widChecked = widChecked.replace('~'+wId, '');
				    		amount = amount-parseFloat(chckAmt);
			    		} 
	        		
		        	 document.panelDocPayForm.amount.value=amount;
		        }
		    if(arg.checked || !arg.checked)
	    		focusBox(document.getElementById("amount"));
	    
	}
function fn_submitOnConfirm(arg)
{
	var type=arg;
	fn_loadImage();
	var searchFlag='${searchFlag}';
	if(searchFlag==null ||(searchFlag!=null && searchFlag!='Y'))
		{
			if(type=='Initiate')
				{
					disablingButtonsAndImgs('initiate');
					document.forms[0].action = "panelDocPay.do?actionFlag=initiatePmnt&checkedDocValues="+checkedValue+"&wIdValues="+widChecked;  
				}
			else
				{
					var pmntStatusValue=document.panelDocPayForm.paymentStatus;
					var pmntStatusLabel=pmntStatusValue.options[pmntStatusValue.selectedIndex].text;
				 if(type=='Approve')
				 	{
						 disablingButtonsAndImgs('appreject');
				    	 document.forms[0].action = "panelDocPay.do?actionFlag=approvePmnt&checkedDocValues="+checkedValue+"&pmntType="+pmntStatusLabel+"&wIdValues="+widChecked; 
					}
				 else if(type=='Reject')
					{
						 disablingButtonsAndImgs('appreject');
						 document.forms[0].action = "panelDocPay.do?actionFlag=rejectPmnt&checkedDocValues="+checkedValue+"&pmntType="+pmntStatusLabel+"&wIdValues="+widChecked;
					}
				 else if(type=='sendBack' || type=='sendBackReject')
				 	{
					 	disablingButtonsAndImgs('sendBack');
					 	document.forms[0].action = "panelDocPay.do?actionFlag=approvePmnt&checkedDocValues="+checkedValue+"&pmntType="+pmntStatusLabel+"&sendBack="+type+"&wIdValues="+widChecked;
				 	}
				}
			document.forms[0].submit();
		}
	
}

function fn_Initiate(){
	var caseType='Initiate';
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
	
	if(checkFlag!=null && checkFlag=='Y'){
	
		
		 var fr = partial(fn_submitOnConfirm,caseType);
			jqueryConfirmMsg('Payment Confirmation','Do you want to proceed?',fr);
	
	}
	else{
		var fr = partial(focusBox,document.getElementsByName('individualPayment'));
		//jqueryErrorMsg('Panel Doctor Mandatory Field',"Please select atleast one payment",fr);
		bootbox.alert("Please select atleast one payment",function (){
			focusBox(document.getElementById('individualPayment'));
		});
		return;
	}
}

function fn_Submit(arg){
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
	
	if(checkFlag!=null && checkFlag=='Y'){
	var pmntStatusValue=document.panelDocPayForm.paymentStatus;
	var pmntStatusLabel=pmntStatusValue.options[pmntStatusValue.selectedIndex].text;
	
	if(caseType=='sendBack')
		{
			var specialCharName=/^[0-9a-zA-Z .,&()\/\n]*$/;
			var ceoRemarksDoc=document.getElementById("ceoRemarksDoc").value;
			if(ceoRemarksDoc==null||ceoRemarksDoc=='')
				{
					alert('Sent Back to CEO Remarks are mandatory');
					focusBox(document.getElementById("ceoRemarksDoc"));
					return false;
				}
			else
				{
				var remarks=document.getElementById("ceoRemarksDoc").value;
					if(remarks.length>0)
						{
							if(remarks.charAt(0)==" ")
								{
									alert("Remarks cannot start with a space");
									focusBox(document.getElementById("ceoRemarksDoc"));
									return false;
								}
							if(!specialCharName.test(remarks))
								{
									alert("Remarks cannot have special characters and numbers");
									focusBox(document.getElementById("ceoRemarksDoc"));
									return false;
								}
					var count=0;
							for(var i=0;i<remarks.length;i++)
								{
									if(remarks.charAt(i)==" ")
										{
											count++;
										}
								}
							if(count==remarks.length)
								{
									alert("Remarks cannot have only blanks");
									focusBox(document.getElementById("ceoRemarksDoc"));
									return false;
								}
						}
				
				}
		}
	 var fr = partial(fn_submitOnConfirm,caseType);
		jqueryConfirmMsg('Claim Payment Confirmation','Do you want to proceed?',fr);
	
	}
	else{
		var fr = partial(focusBox,document.getElementsByName('individualPayment'));
		if(caseType!='sendBack')
		{
			//jqueryErrorMsg('Panel Doctor Mandatory Field',"Please select atleast one case for payment",fr);
			bootbox.alert('Please select atleast one Payment to Send Back to CEO');
		}
		if(caseType=='sendBack')
			{
				//jqueryErrorMsg('Panel Doctor Mandatory Field',"Please select atleast one Payment to Send Back to CEO",fr);
				bootbox.alert("Please select atleast one Payment to Send Back to CEO");
			}
		return;
	}
}
function textCounter( id, fieldName, maxlimit )
	{
		var specialCharName=/^[0-9a-zA-Z .,&()\/\n]*$/;
		var remarks=document.getElementById(id).value;
		if(remarks.length>0)
			{
				if(remarks.charAt(0)==" ")
					{
						alert("Send Back Remarks cannot start with a space");
						focusBox(document.getElementById(id));
					}
				if(!specialCharName.test(remarks))
					{
						alert("Send Back Remarks cannot have special characters and numbers");
						focusBox(document.getElementById(id));
					}
		var count=0;
				for(var i=0;i<remarks.length;i++)
					{
						if(remarks.charAt(i)==" ")
							{
								count++;
							}
					}
				if(count==remarks.length)
					{
						alert("Remarks cannot have only blanks");
						focusBox(document.getElementById(id));
					}
			}
		
		if ( document.getElementById(id).value.length > maxlimit )
		{
			document.getElementById(id).value = document.getElementById(id).value.substring( 0, maxlimit );
		   alert(fieldName+' length cannot be more than '+ maxlimit+' characters');
		   var fieldValue1=document.getElementById(id).value;
		   fieldValue1.trim().substring(0,3999);
		   return false;
		 }
			return true;
	}


function checkDate(){
	var list='${list}';
/* 	 if((document.panelDocPayForm.paymentStatus.value!='00' && (document.panelDocPayForm.month.value=='' || document.panelDocPayForm.month.value==null))||list=='N')
		{
			$('.panel-collapse').addClass('in');
		}  */

	 if(document.panelDocPayForm.dispType.value=="01")
	 {
		 fn_viewMonth();
	 }	
	 else if(document.panelDocPayForm.dispType.value=="02")
	 {
		 fn_viewDate();
	 }	
}

function fn_generateFile()
{
	var pmntStatusValue=document.panelDocPayForm.paymentStatus;
	var pmntStatusLabel=pmntStatusValue.options[pmntStatusValue.selectedIndex].text;
	document.forms[0].action="panelDocPay.do?actionFlag=getPayClaimForCases&checkedDocValues="+checkedValue+"&pmntType="+pmntStatusLabel+"&wIdValues="+widChecked+"&searchFlag=${searchFlag}";
    document.forms[0].submit();
}
function fn_pagination(pageId,end_index)
{
	var pmntStatusValue=document.panelDocPayForm.paymentStatus;
	var pmntStatusLabel=pmntStatusValue.options[pmntStatusValue.selectedIndex].text;
	//document.forms[0].action="./panelDocPay.do?actionFlag=getCases&type=CEOPending&pageId="+pageId+"&end_index="+end_index+"&pmntType="+pmntStatusLabel+"&searchFlag=${searchFlag}";
	document.forms[0].action="./panelDocPay.do?actionFlag=getCases&pageId="+pageId+"&end_index="+end_index+"&pmntType="+pmntStatusLabel+"&searchFlag=${searchFlag}";
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
function loadRemarks(wId,docId)
	{
		var url='panelDocPay.do?actionFlag=getCEORemarks&wId='+wId+'&docId='+docId;
		var xmlhttp;
		if(window.XMLHttpRequest)
			xmlhttp=new XMLHttpRequest();
		else if(window.ActiveXObject)
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		xmlhttp.onreadystatechange=function()
			{
				if(xmlhttp.readyState==4)
					{
						var result=xmlhttp.responseText;
						
						var jsonData = JSON.parse(result);
						jsonDataLst=jsonData;
						if(jsonDataLst!=null)
							{
							var load = "<table>";
							load+="<tr><td class=\"tbheader1 leftMargin\" width=\"5%\">S.No</td>";
							load+="<td class=\"tbheader1 leftMargin\" width=\"20%\">Action</td>";
							load+="<td class=\"tbheader1 leftMargin\" width=\"25%\">Acted By</td>";
							load+="<td class=\"tbheader1 leftMargin\" width=\"20%\">Date and Time</td>";
							load+="<td class=\"tbheader1 leftMargin\" width=\"30%\">Remarks</td></tr>";
							var i=1;
							var str1='Sent Back by CEO';
							var str2='Sent Back Payment Updated';
							$.each(jsonData, function(key, val){
								load+="<tr>";
								load+="<td class=\"tbcellBorder\" width=\"5%\">"+i+"</td>";
								
								if(val.WRKFLWSETID=='WF_PAO_KEPT_PENDING')
									load+="<td class=\"tbcellBorder\" width=\"20%\">"+str1+"</td>";
								else if(val.WRKFLWSETID=='WF_PAO_KEPT_PENDING_UPDATED')
									load+="<td class=\"tbcellBorder\" width=\"20%\">"+str2+"</td>";
									
								load+="<td class=\"tbcellBorder\" width=\"25%\">"+val.EMPNAME+"</td>";
								load+="<td class=\"tbcellBorder\" width=\"20%\">"+val.lstUpdDt+"</td>";
								if(val.ceoRemarks==null||val.ceoRemarks=='')
									load+="<td class=\"tbcellBorder\" width=\"30%\">NA</td>";
								else
									load+="<td class=\"tbcellBorder\" width=\"30%\">"+val.ceoRemarks+"</td>";
								load+="</tr>";
								i=i+1;
									});
							load+="</table>";
							
							document.getElementById("loadDiv").innerHTML=load;
								$('#dialog-CEORem').modal({
									backdrop : 'static',
									keyboard : false,
									show : true
								},'show');
							}
					}
			};
			xmlhttp.open("post",url,true);
			xmlhttp.send(null);	
	}	 
</script>
</head>
<style>
body{font-size:1.3em !important;}
.btn-danger:hover{
background : #AF0000 !important;
}
.btn-success:hover{
background : #003D00 !important;
}
.btn-primary:hover{
background : #00315e !important;
}
.form-control
	{
		height:30px !important;
	}
</style>

<body  onload="checkDate();">
<center>
<html:form action="/panelDocPay.do" method="post" >
<html:hidden name="panelDocPayForm" property="result" />
<html:hidden name="panelDocPayForm" property="totalAmt" />
<html:hidden name="panelDocPayForm" property="actionSelect" />
<html:hidden name="panelDocPayForm" property="userType" />
<table width="95%" style="margin:0 auto"  ><tr><td>
<br> 
<%-- <table class="tbheader"  >
<tr><th><center><b>Panel Doctor Payment</b></center></th></tr>
</table> --%>

<div class="tbheader">
    			
    			
							<b><font size="2">Panel Doctor Payment</font></b>
				
</div>


<table class="contentTable">
<tr>
<td class="labelheading1 tbcellCss" width="15%"><b><i class="fa fa-cog"></i>&nbsp;Payment Status : &nbsp;</b></td>
<td class="tbcellBorder" style="width:25%;">
<html:select name="panelDocPayForm" property="paymentStatus"  onchange="javascript:fn_pmntType()" style="width:90%" styleId="paymentStatusId" styleClass="selectClass">
<html:option value="00" key="select">------Select------</html:option>
 <logic:notEmpty name="panelDocPayForm" property="paymentStatusList">
<html:options collection="PaymentStatusList" property="IDVAL" labelProperty="VALUE"/>
</logic:notEmpty>

</html:select>
</td>
<td class="labelheading1 tbcellCss" width="15%"><i class="fa fa-bars"></i>&nbsp;<b>View by:</b></td>
<td class="tbcellBorder" id="disptype" width="15%">
<html:select name="panelDocPayForm" style="width:90%" property="dispType" onchange="javascript:fn_viewType()" styleClass="selectClass">
<html:option value="00" key="select">-Select-</html:option>
<html:option value="01" key="Month">Month Wise</html:option>
<%-- <html:option value="02" key="Day">Date Wise</html:option> --%>
</html:select>
</td>
<logic:equal value="CD203" name="panelDocPayForm" property="userType" >
<td class="labelheading1 tbcellCss" width="10%"><b>Scheme : &nbsp;</b></td>
<td class="tbcellBorder" width="20%">
<html:select name="panelDocPayForm" property="scheme" styleClass="selectClass">
<html:option value="CD201" key="CD201">Andhra Pradesh</html:option>
<html:option value="CD202" key="CD202">Telengana</html:option>
 <%-- <logic:notEmpty name="panelDocPayForm" property="schemeList">
<html:options collection="schemeStatusList" property="IDVAL" labelProperty="VALUE"/>
</logic:notEmpty> --%>
</html:select>
</td>
</logic:equal>

<!-- </tr>
<tr> -->
<td class="labelheading1 tbcellCss" width="10%" id="monthlab" style="visibility:hidden"><i class="fa fa-calendar"></i>&nbsp;<b>Month:&nbsp;<font color="red">*</font></b></td>
<td class="tbcellBorder" width="20%" id="month" style="visibility:hidden">

	<div class="input-group date " id="monthDatePicker">
		<html:text style="width:104%" styleClass="form-control" styleId="monthPicker" name="panelDocPayForm"  property="month" readonly="true" >
		</html:text>
		<span class="input-group-addon">
			<i class="fa fa-calendar"></i> 
		</span>
	</div>

</td> 

</tr>
<%-- <tr>
<td style="WIDTH: 4em"></td>
<td style="WIDTH: 4em"></td>
<td class="labelheading1 tbcellCss" id="fromdatelab" style="visibility:hidden"><b>From Date:&nbsp;<font color="red">*</font></b></td>
<td class="tbcellBorder" id="fromdate" style="visibility:hidden"><html:text styleId="fromDate" name="panelDocPayForm"  property="fromDate" style="WIDTH: 110px"  readonly="true"   ></html:text>
</td> 
<td style="WIDTH: 4em"></td>
<td style="WIDTH: 4em"></td>
<td class="labelheading1 tbcellCss" id="todatelab" style="visibility:hidden"><b>To Date:&nbsp;<font color="red">*</font></b></td>
<td class="tbcellBorder" id="todate" style="visibility:hidden"><html:text styleId="toDate"   name="panelDocPayForm" property="toDate"  style="WIDTH: 110px" readonly="true" ></html:text>
</td>
</tr> --%>

</table>

<table class="contentTable">
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr>
<td style="WIDTH: 4em"></td>
<td style="WIDTH: 25em"></td><td><button class="but btn btn-primary"  type="button" id="getcases" onclick="javascript:getCases()">Get Cases</button>
</td>
<td style="WIDTH: 2em"></td><td><button class="but btn btn-primary"  type="button" id="reset" onclick="javascript:Reset()">Reset</button>
</tr>
</table>

</td></tr>
</table>

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
<table  width=95% align="center"><tr><td>
</td></tr>
<tr><td>
<table border="0" width="95%"  cellpadding="1" cellspacing="1" align="center">
<tr align="center">
<td>
No Records Found
</td>
</tr>
<tr><td>&nbsp;</td></tr>
<tr><td>&nbsp;</td></tr>
</table>
</td></tr></table>
</logic:equal>
<logic:equal value="List Found" name="panelDocPayForm" property="flag" >
 <logic:notEmpty name="panelDocPayForm"  property="panelDocList">
 <table width=95% align="center">
 	<tr>
 		<td width="20%" >
 			Total No of Records found:${noOfRecords}
 		</td>
 		<td width="25%" >
 			Displaying Records from ${start_index+1} to ${endresults}
 		</td>
 		<td  id="pageNoDisplay" width="30%" align="center">
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
		<li><a href="#"><span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages(<%=minVal%>,<%=noOfPages%>,<%=selectedPage%>,<%=end_index%>)" style="cursor: pointer;"></span></a></li>
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
 		</td>
 				<td width="25%"> 		<ul class="pagination">
	<li class="lispacing">Show results in:</li><c:if test="${end_index ne 10}"><li><a href="javascript:fn_pagination(1,10)">10</a></li></c:if>
												<c:if test="${end_index eq 10}"><li class="active"><span>10</span></li></c:if>	
												<c:if test="${end_index ne 20}"><li><a href="javascript:fn_pagination(1,20)">20</a></li></c:if>
												<c:if test="${end_index eq 20}"><li class="active"><span>20</span></li></c:if>
												<c:if test="${end_index ne 50}"><li><a href="javascript:fn_pagination(1,50)">50</a></li></c:if>
												<c:if test="${end_index eq 50}"><li class="active"><span>50</span></li></c:if>
												<c:if test="${end_index ne 100}"><li><a href="javascript:fn_pagination(1,100)">100</a></li></c:if>
												<c:if test="${end_index eq 100}"><li class="active"><span>100</span></li></c:if><!-- </td> -->
												
	</ul> </td> 
 	</tr>
 </table>
<table border='0' width=95% align="center"><tr><td>
 <!-- <table class="	tbheader1" border=0 >
<tr><th><b>Panel Doctor Payment </b></th></tr>
</table> --></td></tr>
</table>

 <table  width=95% align="center" >
 
 <tr><td>
<%-- <display:table  id="panelDocPayVO"  name="panelDocPayForm.panelDocList" pagesize="100" style="width:100%;align:center;border:2;rowspan:5" export="false" requestURI="/panelDocPay.do"  cellpadding="2" cellspacing="2" decorator="com.ahct.panelDoctor.util.PanelDocDecorator"> 
<display:column value="<%=i++ %>" title="S No"  style="text-align:center;width:3%"  />
<display:column   property="DOC_NAME" title="Panel Doctor" />
<display:column  property="claimAprvAmt" title="Claim Approved Amount" style="text-align:right;width:10%"/>
<display:column  property="claimRejAmt" title="Claim Rejected Amount" style="text-align:right;width:10%"/>
<display:column  property="claimPendAmt" title="Claim Pending Amount" style="text-align:right;width:10%"/>
<display:column  property="preauthAprvAmt" title="Preauth Approved Amount" style="text-align:right;width:10%"/>
<display:column  property="preauthRejAmt" title="Preauth Rejected Amount" style="text-align:right;width:10%"/>
<display:column  property="preauthPendAmt" title="Preauth Pending Amount" style="text-align:right;width:10%"/>
<display:column  property="totalPnldocAmt" title="Total Amount" style="text-align:right;width:10%"/>
<display:column property="ID" title="<input type='checkbox' name='checkId' id='check_id' title='select All' onclick='javascript:checkAll(this);'  />" media="html" style="text-align:center" >
  
</display:column>
</display:table> --%>

<table>
		<tr>
				<td class="tbheader1" >
					S.No
				</td>
				<td class="tbheader1" >
					Panel Doctor
				</td>
				<td class="tbheader1" >
					Month-Year  
				</td>
				<td class="tbheader1" >
					Claim Approved Amount  
				</td>
				<td class="tbheader1" >
					Claim Rejected Amount  
				</td>
				<td class="tbheader1" >
					Claim Pending Amount 
				</td>
				<td class="tbheader1" >
					Preauth Approved Amount
				</td>
				<td class="tbheader1" >
					Preauth Rejected Amount
				</td>
				<td class="tbheader1" >
					Preauth Pending Amount 
				</td>
				<td class="tbheader1" >
					Total Amount
				</td>
				<c:if test="${ceoRemarks eq 'show'}">
					<td class="tbheader1" >
						Remarks
					</td>
				</c:if>
				<c:if test="${searchFlag ne 'Y' }">
					<td class="tbheader1" >
						<html:checkbox name="panelDocPayForm" property="checkAll" styleId="checkAll"  onclick="checkAllBoxes(this.id)" title="Select All"></html:checkbox>
					</td>
				</c:if>		
		</tr>
		<%int i = 1;%>
		<logic:iterate name="panelDocPayForm" property="panelDocList" id="results">
		<tr>
			<td class="tbcellBorder" >
				<%=i++ %>
			</td>
			<td class="tbcellBorder" >
				<a href="javascript:popUp('${results.DOC_ID}','${results.DOC_NAME}','${results.wId}')"><font color="#FF4444"><bean:write name="results" property="firstName" />&nbsp;<bean:write name="results" property="DOC_NAME" /></font></a>
			</td>
			<td class="tbcellBorder" >
				<bean:write name="results" property="month"/>-<bean:write name="results" property="year"/>
			</td>
			<td class="tbcellBorder" >
				<bean:write name="results" property="claimAprvAmt" />
			</td>
			<td class="tbcellBorder" >
				<bean:write name="results" property="claimRejAmt" />
			</td>
			<td class="tbcellBorder" >
				<bean:write name="results" property="claimPendAmt" />
			</td>
			<td class="tbcellBorder" >
				<bean:write name="results" property="preauthAprvAmt" />
			</td>
			<td class="tbcellBorder" >
				<bean:write name="results" property="preauthRejAmt" />
			</td>
			<td class="tbcellBorder" >
				<bean:write name="results" property="preauthPendAmt" />
			</td>
			<td class="tbcellBorder" >
				<bean:write name="results" property="totalPnldocAmt" />
			</td>
			<c:if test="${ceoRemarks eq 'show'}">
					<td class="tbcellBorder" align="center">
						<a href="javascript:loadRemarks('${results.wId}','${results.DOC_ID}');" title="Click to see CEO Sent Back Payments Remarks"><span class="glyphicon glyphicon-new-window"></span></a>
					</td>
			</c:if>
			<c:if test="${searchFlag ne 'Y' }">
				<td class="tbcellBorder" >
					<html:checkbox name="panelDocPayForm" property="individualPayment" styleId="individualPayment"  onclick="fn_addCheckValues('${results.DOC_ID}','${results.totalPnldocAmt}','${results.wId}')" value="${results.DOC_ID}@${results.totalPnldocAmt}@${results.wId}" title="Click to select"></html:checkbox>
				</td>
			</c:if>	
		</tr>
		</logic:iterate>
</table>
<c:if test="${searchFlag ne 'Y' }">
<center>
<table border='0' width="50%" align="center">
<tr><td>&nbsp;</td></tr>

   
<tr>
<td width="20%">&nbsp;</td>
<td class="labelheading1 tbcellCss" align="center" style="WIDTH:30%"><b>Sum of amount : </b></td>
<td  class="labelheading1 tbcellCss Label5" style="WIDTH:30%" ><b><html:text name="panelDocPayForm"  property="amount" styleId="amount" readonly="true" size="14" /></b>
<td width="20%">&nbsp;</td>
</td>
</tr>
<tr><!-- <td>&nbsp;</td> --></tr>
<%-- <tr>
<td class="labelheading1 tbcellCss" ><b>Remarks : </b></td>
<td  class="labelheading1 tbcellCss Label5" style="WIDTH: 2em" ><b><html:textarea  name="panelDocPayForm"  property="remarks" style="WIDTH: 190px"  onkeypress="validateMaxlength(this,event);"> </html:textarea></b>
</td>
</tr> --%>
</table> 
</center>
<c:if test="${sentBackRemDiv eq 'show'}">
	<table>
		<tr>
			<td width="20%">&nbsp;</td>
			<td width="7%">Remarks :</td>
			<td align="left" width="53%">
				<html:textarea  property="ceoRemarksDoc" style="margin-top:10px;resize:none" styleId="ceoRemarksDoc" onchange="javascript:textCounter(this.id,'Sent Back to CEO Remarks',3000)"  name="panelDocPayForm" rows="2" cols="90" ></html:textarea>
			</td>
			<td width="20%">&nbsp;</td>
		</tr>
	</table>
</c:if>
<logic:equal value="Initiate" name="panelDocPayForm" property="action" >
<table class="contentTable">
<tr><td>&nbsp;</td></tr>
<tr>
<td style="WIDTH: 4em"></td>
<td style="WIDTH: 5em"></td>
<td style="WIDTH: 25em"></td><td ><button class="but btn btn-danger"  type="button" value="Initiate" id="actionInitiate" onclick="javascript:fn_Initiate()">Initiate</button></td>
</tr>
<tr><td>&nbsp;</td></tr>
<tr><td>&nbsp;</td></tr>
</table>
</logic:equal>
<logic:equal value="Approved" name="panelDocPayForm" property="action" >
<table class="contentTable">
<tr><td>&nbsp;</td></tr>
<tr>
<logic:equal value="Reject" name="panelDocPayForm" property="rejId" >
<td style="WIDTH: 4em"></td>
<td style="WIDTH: 25em"></td><td ><button class="but btn btn-success"  type="button" id="actionSubmit" onclick="javascript:fn_Submit('Approve')">Approve</button></td>
<td style="WIDTH: 2em"></td><td ><button class="but btn btn-danger"  type="button" id="actionSubmitRej" onclick="javascript:fn_Submit('Reject')">Reject</button></td>
</logic:equal>
<logic:notEqual value="Reject" name="panelDocPayForm" property="rejId" >
<td style="WIDTH: 10em"></td>
<td style="WIDTH: 25em"></td><td ><button class="but btn btn-danger"  type="button" id="actionSubmit" onclick="javascript:fn_Submit('Approve')">Approve</button></td>
</logic:notEqual>
</tr>
</table>
</logic:equal>
<table class="contentTable">
<tr>
<logic:equal value="SendBack" name="panelDocPayForm" property="sendBack" >
<logic:equal value="Rej" name="panelDocPayForm" property="action" >
<td style="WIDTH: 28em"></td>
</logic:equal>
<logic:notEqual value="Rej" name="panelDocPayForm" property="action" >
<td style="WIDTH: 31em"></td>
</logic:notEqual>
<td ><button class="but btn btn-success"  type="button" id="actionSubmitCEO" onclick="javascript:fn_Submit('sendBack')">Send Back To CEO</button></td>
<%-- <logic:equal value="Rej" name="panelDocPayForm" property="action" >
<td style="WIDTH: 2em"></td><td ><button class="but btn btn-danger"  type="button" id="actionSubmitRejCEO" onclick="javascript:fn_Submit('sendBackReject')">Reject</button></td>
</logic:equal> --%>
</logic:equal>
</tr>
</table>
</c:if>
</td></tr>
</table> 
</logic:notEmpty> 
</logic:equal>
<logic:equal value="Approve" name="panelDocPayForm" property="status">
<script>
jqueryInfoMsg('Approve',document.panelDocPayForm.result.value);
//jqueryInfoMsg('Approve',document.panelDocPayForm.result.value,getCases);
</script>
</logic:equal>
<logic:equal value="Reject" name="panelDocPayForm" property="status">
<script>
jqueryInfoMsg('Reject',document.panelDocPayForm.result.value,getCases);
</script>
</logic:equal>
<div class="modal fade" id="dialog-CEORem" style="height:60% !important;" > 
  <div class="modal-dialog" > 
    <div class="modal-content" >
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h2 class="modal-title" >Sent Back Remarks</h2>
      </div>
      <div class="modal-body" style="overflow-y:scroll;align:center" id="remarksCeoDiv">
			<div id="loadDiv"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
     </div>
   </div>
</div>      
</html:form>
</center>
</body>
</html>