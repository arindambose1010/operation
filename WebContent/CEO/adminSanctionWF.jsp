<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>Admin Sanction Workflow</title> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

  <script src="js/jquery-1.9.1.js"></script>
   <%@ include file="/common/include.jsp"%>
  <%@ include file="/common/includeCalendar.jsp"%>
  <%@ include file="/common/editableComboBox.jsp"%>  
<script src="js/jquery.msgBox.js" type="text/javascript"></script>

<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css"    media="screen"> 

<style>
.ui-autocomplete-input {
    width: 17em;
}
input[type="text"] {
   height:100%;
}
.mainDiv{

width:92%;
float:right;
padding-right:3%;

}
#remarksDiv{
width:98%;
height:540px;
overflow:auto;
background:white;
pitch-range: 
}
</style>

<script>

//function for a scrollable div
/* (function($){
	   
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


*/
var date1 = new Date();
var m1 = date.getMonth(), d1 = date.getDate(), y1 = date.getFullYear();

$(function() {
	$( "#date" ).datepicker({
		defaultDate: "+1w",
		changeMonth: true,
		changeYear: true,
		showOn: "both", 
        buttonImage: "common/images/calend.gif", 
        dateFormat: "dd/mm/yy",
        buttonText: "Calendar",
        buttonImageOnly: true ,
		numberOfMonths: 1,
		minDate: date1,
		onSelect: function ()
	    {
			this.focus();
	    }
		});
	$( "#sanctionOrderDate" ).datepicker({
		defaultDate: "+1w",
		changeMonth: true,
		changeYear: true,
		showOn: "both", 
        buttonImage: "common/images/calend.gif", 
        dateFormat: "dd/mm/yy",
        buttonText: "Calendar",
        buttonImageOnly: true ,
		numberOfMonths: 1,
		minDate: date1,
		onSelect: function ()
	    {
			this.focus();
	    }
		});
	$( "#purchaseDate" ).datepicker({
		defaultDate: "+1w",
		changeMonth: true,
		changeYear: true,
		showOn: "both", 
        buttonImage: "common/images/calend.gif", 
        dateFormat: "dd/mm/yy",
        buttonText: "Calendar",
        buttonImageOnly: true ,
		numberOfMonths: 1,
		minDate: date1,
		onSelect: function ()
	    {
			this.focus();
	    }
		});
	});








function addTooltip(id) {
	var numOptions = document.getElementById(id).options.length;
	for ( var i = 0; i < numOptions; i++)
		document.getElementById(id).options[i].title = document
				.getElementById(id).options[i].text;

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
function fn_single()
    {
	document.getElementById('vendorTR').style.display = "";
	}
function fn_Multiple()
{
document.getElementById('vendorTR').style.display = "none";
}


function saveAdminSancDetails(buttonValue){
	
	var status= fn_validate(buttonValue);
	if(status==true)
		{
		  fn_loadImage();
	      document.forms[0].action = '/<%=context%>/adminSanction.do?actionFlag=saveAdminSancDetails&buttonValue='+buttonValue;
	      document.forms[0].submit();
	      document.forms[0].saveBtn.disabled = true;	
		}
}
function fn_viewAtach(mode,uploadType)
{
	var left = (screen.width/2)-450;
	  var top = (screen.height/2)-200;
	  var sanctionId=document.getElementById("sanctionId").value;
	Url="addViewAttach.do?actionVal=getAccountsAtachments&TransId="+sanctionId+"&mode="+mode+"&uploadType="+uploadType+"&isAdvancePaymentAttach=N";
	window.open(Url,"new",'toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, copyhistory=no, width=900, height=400, top='+top+',left='+left);
}


function fn_showAlert()
{
	
	var successflag="${successMsg}";
		 if(successflag=="success")
		{
		 var sanctionId=document.getElementById("sanctionId").value;
		jqueryAlertMsg("Alert","Saved details successfully with sanction id: "+sanctionId+" , please attach required attachments and enter remarks to submit the data. ");
		}
		 
		 var status = "${alertMsg}"; 
		 
			if( status== "absent") {
			}
			else
				{
				var sanctionId=document.getElementById("sanctionId").value;
				var fr=partial(goBack);
				jqueryAlertMsgTop('Alert',sanctionId+" "+status+ "  Successfully",fr);
				}
			 
		 
	var vendorType="";
	 for (var i=0; i<document.forms[0].elements.length; i++)
		{	
		
			var type = document.forms[0].elements[i].type;
			var id=document.forms[0].elements[i].id;
			if (type=="radio" && id=="vendorType")
			{	
				 
				if(document.forms[0].elements[i].checked)
				{	
					vendorType=document.forms[0].elements[i].value;
				}
			}
		}
	 
	 if(vendorType=="S")
		 {
		   document.getElementById('vendorTR').style.display = "";
		   $("#vendorCode-input").val($('#vendorName option:selected').val());
			document.getElementById("vName").value=$('#vendorName option:selected').text();
		 }
	 
	 
	 if("${btnRecAppr}"=="recApprove")
		 {
		 var months =new Array();
		    months=['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
		    var date = new Date();
		    document.getElementById('dispMonth').innerHTML = months[date.getMonth()] + ' ' + date.getFullYear();
		 }
		
	 
	 var status1 = "${butDisabled}";
	 if(status1!="")
		 {
		 $("#purchaseDate").datepicker('disable');
		 $("#sanctionOrderDate").datepicker('disable');
		 $("#date").datepicker('disable');
		 
		 }
		
	 parent.fn_removeLoadingImage();
	 	
	}


function fn_validate(buttonValue)
{
	var i = $('.addRemoveTrClass').length;
	var specList="";
	var costList="";
    var totalCost=0;
    
	var j=0;
	for(var k=1;k<=i;k++)
	 {
	 if(k==1)
		 {
		 costList=document.getElementById("cost"+k).value;
		 totalCost=parseInt(costList);
		 j=k;
		 }
	 else
		 {
		 costList=costList+"~"+document.getElementById("cost"+k).value;
          totalCost=totalCost+parseInt(document.getElementById("cost"+k).value);
          j=k;
		 }
	 }
	 if(totalCost>parseInt(document.getElementById("sanctionAmount").value))
     {
		 var fr=partial(focusFieldView,document.getElementById("cost"+j));
        jqueryAlertMsgTop("Alert","Sum of costs specified must be less than or equal to given sanction amount",fr);
        document.getElementById("cost"+j).value=""; 
        return false;
     
	   }
	 if(document.getElementById("issuingAuthority").value=="" || document.getElementById("issuingAuthority").value==null)
          {
		 var fr=partial(focusFieldView,document.getElementById("issuingAuthority-input"));
         jqueryAlertMsgTop("Alert","Please select Issuing Authority",fr);
		     return false;
	      }
 
	 if(document.getElementById("date").value=="")
     {
		 var fr=partial(focusFieldView,document.getElementById("date"));
      jqueryAlertMsgTop("Alert","Please select Date",fr);
		     return false;
	    }

	 if(document.getElementById("subject").value=="" )
     {
		 var fr=partial(focusFieldView,document.getElementById("subject"));
      jqueryAlertMsgTop("Alert","Please enter Subject",fr);
		     return false;
	   }

	 if(document.getElementById("deptName").value=="" )
     {
		 var fr=partial(focusFieldView,document.getElementById("deptName-input"));
      jqueryAlertMsgTop("Alert","Please select department",fr);
		     return false;
	   }

	 if(document.getElementById("reference").value=="" )
     {
		 var fr=partial(focusFieldView,document.getElementById("reference"));
      jqueryAlertMsgTop("Alert","Please enter reference details",fr);
		     return false;
	   }

	 if(document.getElementById("scheme").value=="" )
     {
		 var fr=partial(focusFieldView,document.getElementById("scheme-input"));
      jqueryAlertMsgTop("Alert","Please select Scheme",fr);
		     return false;
	   }

	 if(document.getElementById("sanctionAmount").value=="" )
     {
		 var fr=partial(focusFieldView,document.getElementById("sanctionAmount"));
      jqueryAlertMsgTop("Alert","Please enter sanction amount",fr);
		     return false;
	   }
	 
	 if(document.getElementById("detailedHead").value=="" )
     {
		 var fr=partial(focusFieldView,document.getElementById("detailedHead-input"));
      jqueryAlertMsgTop("Alert","Please select detailed head",fr);
		     return false;
	   }

	 if(document.getElementById("sourceOfBudget").value=="" )
     {
		 var fr=partial(focusFieldView,document.getElementById("sourceOfBudget"));
      jqueryAlertMsgTop("Alert","Please select source of budget",fr);
		     return false;
	   }


	 if(document.getElementById("inspectionAuthority").value=="" )
     {
		 var fr=partial(focusFieldView,document.getElementById("inspectionAuthority"));
      jqueryAlertMsgTop("Alert","Please enter inspection authority details",fr);
		     return false;
	   }

	 if(document.getElementById("executingAuthority").value=="" )
     {
		 var fr=partial(focusFieldView,document.getElementById("executingAuthority"));
      jqueryAlertMsgTop("Alert","Please enter executing authority details",fr);
		     return false;
	   }

	 if(document.forms[0].vendorType[0].checked == false &&  document.forms[0].vendorType[1].checked == false && document.forms[0].vendorType[2].checked==false){
		 var fr=partial(focusFieldView,document.forms[0].vendorType[0]);
    	 jqueryAlertMsgTop("Alert","Please select vendor type",fr);
	     return false;
    }

	 if(document.forms[0].vendorType[0].checked == true)
		 {
		 if(document.getElementById("vendorName").value=="" )
	     {
			 var fr=partial(focusFieldView,document.getElementById("vendorName-input"));
	         jqueryAlertMsgTop("Alert","Please select vendor name",fr);
			     return false;
		   }
           
		 }

	 if(document.getElementById("toBeIssuedOn").value=="" )
     {
		 var fr=partial(focusFieldView,document.getElementById("toBeIssuedOn"));
         jqueryAlertMsgTop("Alert","Please enter issue details",fr);
		     return false;
	   }

	 if(document.getElementById("typoOfSanction").value=="" )
     {
		 var fr=partial(focusFieldView,document.getElementById("typoOfSanction-input"));
         jqueryAlertMsgTop("Alert","Please select sanction type",fr);
		     return false;
	   }

	 for(var k=1;k<=i;k++)
	 {

		 if(document.getElementById("specification"+k).value=="")
			{
			 var fr=partial(focusFieldView,document.getElementById("specification"+k));
	         jqueryAlertMsgTop("Alert","Please enter the specification",fr);
			     return false;
			} 

		 if(document.getElementById("cost"+k).value=="")
			{
			 var fr=partial(focusFieldView,document.getElementById("cost"+k));
	         jqueryAlertMsgTop("Alert","Please enter cost",fr);
			     return false;
			} 
	 }
	   for(var k=1;k<=i;k++)
		 {
		     if(k==1)
		        specList=document.getElementById("specification"+k).value;
		     else
			 specList=specList+"~"+document.getElementById("specification"+k).value;
		 }
	      document.getElementById("specification").value=specList;
	
	  for(var k=1;k<=i;k++)
	   {
	       if(k==1)
		   {
	           costList=document.getElementById("cost"+k).value;
		    }
	       else
		   {
		       costList=costList+"~"+document.getElementById("cost"+k).value;
		   }
	  }

	      document.getElementById("cost").value=costList;
          var status="";
	      if(buttonValue=="Submit" || buttonValue=="Update")
		  {
	    	  
	    	  if(document.getElementById("adminSancRemarks").value=="")
		      {
	    	  var fr=partial(focusFieldView,document.getElementById("adminSancRemarks"));
	          jqueryAlertMsgTop("Alert","Please enter the remarks",fr);
	 		     return false;
		      }
	    	  else
	    		  {
	    		   status=validateRemarks("adminSancRemarks");
	    		  }
	    	if(status==true)	 
	          return checkAttach(buttonValue);
	    
		  }	
	      else
	    	  {
	    	  return true;
	    	  }
	}
	
	


function checkAttach(buttonValue)
{

	var xmlhttp;
	var url;
	var sId=document.getElementById("sanctionId").value;
	url= 'adminSanction.do?actionFlag=checkAttachments&sanctionId='+sId;
	
	
	if (window.XMLHttpRequest){
		 xmlhttp=new XMLHttpRequest();
		}
		else if (window.ActiveXObject){		
		 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		else{
		 alert("Your Browser Does Not Support XMLHTTP!");
		}
	
	
	xmlhttp.onreadystatechange=function(){
	    if(xmlhttp.readyState==4){
		    
	        var resultArray=xmlhttp.responseText;
	        if(resultArray!= null){
	        	
	        	 resultArray2 = resultArray.replace("[","");
		         resultArray2 = resultArray2.replace("]","");  
		        
	        	if(resultArray2=="no")
	        	{
	        	jqueryAlertMsgTop("Alert","No attachments found for the given sanction id.Please upload related attachments");
	        	    return false;
	        	}
	        	else 
	        		{
	        		if(resultArray2=="yes")
	        			{
	        			var fr=partial(onSuccessSubmit,buttonValue);
	        			if(buttonValue=="Submit")
	        		 	jqueryConfirmMsg("Confirm","Are you sure you want to Submit details?",fr);
	        			else
	        				{
	        				jqueryConfirmMsg("Confirm","Are you sure you want to Update details?",fr);
	        				}
	        				
	        			}
	        		}
	        	}
	    }			
	}
	xmlhttp.open("Post",url,false);
	xmlhttp.send(null);
	
}

function onSuccessSubmit(buttonValue)
{
	fn_loadImage();
	 document.forms[0].action = '/<%=context%>/adminSanction.do?actionFlag=saveAdminSancDetails&buttonValue='+buttonValue;
     document.forms[0].submit();
     if(buttonValue=="Submit")
     document.forms[0].submitBtn.disabled = true;
     if(buttonValue=="Update")
    	 document.forms[0].updateBtn.disabled = true;
}


function fn_loadImage()
{
  document.getElementById('processImagetable').style.display="";
}

function fn_removeLoadingImage()
 {   
   document.getElementById('processImagetable').style.display="none";
 }
 
function submitAdminSancDetails(buttonValue)
{
	 var valid = submitValidation();
	 if(valid == true)
		{ 
		 onSuccessSave(buttonValue);
		}

}

function onSuccessSave(buttonValue){
	 fn_loadImage();
		 var sanctionId=document.getElementById("sanctionId").value;		
		 document.forms[0].action = '/<%=context%>/adminSanction.do?actionFlag=submitAdminSancDetails&sanctionId='+sanctionId+'&buttonValue='+buttonValue;
			document.forms[0].submit();
			document.forms[0].approveBtn.disabled = true;
				document.forms[0].pendingBtn.disabled = true;
				document.forms[0].rejectBtn.disabled = true;
			
		}
	
function submitValidation(){
	
	 var adminSancRemarks =document.getElementById('adminSancRemarks').value;
	 var status="";
	if(adminSancRemarks==null||adminSancRemarks=="")
	   {
		 var fr=partial(focusFieldView,document.getElementById('adminSancRemarks'));
		 jqueryAlertMsg("Alert","Please enter remarks ",fr);
		 document.getElementById('adminSancRemarks').value="";
	   return false;
	   }
	else
		{
	     status=validateRemarks("adminSancRemarks");
		}
	if(status==true)	
	return true;
	else
		return false;
	
}


function validateRemarks(arg) { 
	
	var value = document.getElementById(arg).value;
	var len = value.length; 
	var var_length = value.length;
	var str = /\W/g;
	var space=/\s/g;
	var pattern=/^[A-Z a-z 0-9.,()?&]+$/; 	
	var chars="*|\":<>[]{}`\';!^_*+@$#%~";
	var allowedSpl =".,()?&/";
	
	if (value != ""){ 
	  if(value.charCodeAt(0) == 32) 	{ 	
			var fr=partial(focusFieldView, document.getElementById(arg));
			jqueryAlertMsgTop("Alert","Remarks cannot start with white space",fr);
			document.getElementById(arg).value=""; 	return false;  	} 	
		
	
		else if  (len > 3000) 	{ 	
			var fr=partial(focusFieldView, document.getElementById(arg));
			jqueryAlertMsgTop("Alert","Remarks cannot be more than 3000 characters",fr);
				
			return false;  
			} 

	  for (var i = 0; i < var_length; i++) 
	    {
	        if(chars.indexOf(value.charAt(i))!=-1)
	        {
	        	var fr=partial(focusFieldView, document.getElementById(arg));
	        	jqueryAlertMsgTop("Alert","Remarks cannot have Special Characters except . , () / & ? ",fr);
				return false;  
				
	            
	        }
	        else {
	        	
	        	 if(allowedSpl.indexOf(value.charAt(i))!=-1)
	 	        {
	        		 if(i<var_length-1){
	        		 if(allowedSpl.indexOf(value.charAt(i+1))!=-1)
	        			 {	var fr=partial(focusFieldView, document.getElementById(arg));
	        			 jqueryAlertMsgTop("Alert","Remarks cannot have Consecutive Special Characters",fr);
	     				return false;  
		        			
	        			 }
	        		 }
	 	        }
	        }
	       
	       
	       
	    }

	  document.getElementById(arg).value = document.getElementById(arg).value.replace(/  +/g, ' ');
	
		
	return true;
	}	
}
isLarge = false;
function expandOrCollapse(){
	    $("#remarksDiv").animate({height:(isLarge ? '0.1em' : '10em')});
	   isLarge = !isLarge; 
	}
	
function goBack(){
	
	if("${backTo}"=="track")
		parent.fn_sancStatus(); 
	else
 	parent.fn_sancWorkFlow(); 
 	}

function viewAttachmnt(arg)
{
	TransIdNum=document.getElementById("sanctionId").value;
	document.forms[0].action = '/<%=context%>/adminSanction.do?actionFlag=viewSelectAttach&TransId='+TransIdNum+'&fileName='+arg.id;
 document.forms[0].submit(); 
	}
</script>
</head>
<body onload="fn_showAlert();" >
 <form name = "adminSanctionForm" id="adminSanctionForm" method="post" action="/adminSanction.do" enctype="multipart/form-data">

<br>
<div class="mainDiv">
<logic:equal name="adminSanctionForm"  property="showBack" value="show">
 <table  class="tbheader" style="width:90%;margin:0 auto;">
 <tr>
<td  style="height:2em;width:80%" align="left"><b>&nbsp;&nbsp;&nbsp;&nbsp;Admin Sanction Proceeding Form </b></td>	

<td style="${showBack}" align="right">
 <a class="tbcellCss" id="backLink" style="font-weight: bold; color:black; text-decoration:none;width:10%" onclick="goBack();" href="#">Back</a> 
</td>				

</tr>
</table>
</logic:equal>

<logic:notEqual name="adminSanctionForm"  property="showBack" value="show">


<table  class="tbheader" style="width:90%;margin:0 auto;">
 <tr>
<td  style="height:2em" align="center"><b>&nbsp;&nbsp;&nbsp;&nbsp;Admin Sanction Proceeding Form </b></td>				
</tr>
</table>

</logic:notEqual>

 <br>
<table id="adminSancTable1" style="width:90%;margin:0 auto;" class="tb">
<logic:notEmpty name="adminSanctionForm" property="sanctionId">
<tr>
<td  width="100%" class="labelheading1 tbcellCss" align="center"><b>Admin Sanction ID : <bean:write name="adminSanctionForm" property="sanctionId"></bean:write></b>
 <html:hidden  property="sanctionId" styleId ="sanctionId" name="adminSanctionForm"/>		
</td>
</tr>
</logic:notEmpty>
</table>
<table id="adminSancTable" style="width:90%;margin:0 auto;" class="tb">


<logic:notEmpty name="adminSanctionForm" property="amtSanc">
<tr style="height:2em" >
<td colspan="4" align="center">
 <span style="color:#ff0000"> Sanctioned Amount by Executive officer(Admin) in the month of <span id="dispMonth"></span> is Rs. <bean:write name="adminSanctionForm" property="amtSanc"/> 
 </span> 
 </td>
 </tr>
 </logic:notEmpty>


<tr>

	<td   width="20% " class="labelheading1 tbcellCss">Issuing Authority <span style="color:#ff0000">*</span></td>
		<td width="30%" class="tbcellBorder">
			<html:select disabled="${disabled}" property="issuingAuthority" tabindex="0" name="adminSanctionForm" style="width:19em" styleId="issuingAuthority"  onmouseover="addTooltip('issuingAuthority');"  title="Issuing Authorities List"  onchange="fn_getIssuingAuthNames()">
                                       <option value="">--------Select---------</option>
                                       <html:option value="CEO">Chief Executive Officer</html:option>
                                       <html:option value="EO_ADMIN">Executive Officer (Admin)</html:option>
                                        </html:select>
											</td>
<td  width="20% " class="labelheading1 tbcellCss">Issuing Authority Name</td>
<td  width="30%" class="tbcellBorder">

<html:select  property="issuingAuthorityName" name="adminSanctionForm" styleId="issuingAuthorityName" disabled="${disabled}" onmouseover="addTooltip('issuingAuthorityName');" title="Issuing Authority Name">
						<option value="">--------Select---------</option>
						 <html:options collection="issuingAuthNamesList" labelProperty="VALUE" property="ID"/>
						</html:select> 
 </td>

</tr> 
<tr>
<td  width="20%" class="labelheading1 tbcellCss">Date<span style="color: #ff0000"> *</span></td>
<td  width="30%" class="tbcellBorder"> <html:text readonly='true' style="width:15em" property="date" styleId ="date" name="adminSanctionForm"  disabled="${disabled}"  title="Date" tabindex="0"/>
<img id="eraImg"  title="Click here to erase" src="/<%=context%>/images/erase37.jpg" alt="click to clear"  onclick="javascript:eraser('date');" >
</td>

<td  width="20%" class="labelheading1 tbcellCss">Department<span style="color: #ff0000"> *</span></td>
<td  width="30%" class="tbcellBorder"><html:select  property="deptName" name="adminSanctionForm" styleId="deptName" disabled="${disabled}" onmouseover="addTooltip('deptName');" title="Select Department">
						<option value="">--------Select---------</option>
						 <html:options collection="deptNamesList" labelProperty="VALUE" property="ID"/>
						</html:select>
						</td>


					

</tr>
<tr>

<td  width="20% " class="labelheading1 tbcellCss">Subject<span style="color: #ff0000"> *</span></td>
<td  width="30%" class="tbcellBorder"> <html:textarea readonly="${readonly}" property="subject" styleId ="subject"  disabled="${disabled}" name="adminSanctionForm"  onblur="javascript:chkSpecialChars('subject')" rows="4" cols="47"  title="Subject" tabindex="0"/></td>

<td  width="20%" class="labelheading1 tbcellCss">Reference<span style="color: #ff0000"> *</span></td>
<td  width="30%" class="tbcellBorder"> <html:textarea readonly="${readonly}" disabled="${disabled}" property="reference"  styleId ="reference" name="adminSanctionForm" onkeyup="validate_text1(reference)"  rows="4" cols="47"  title="Reference" tabindex="0" /></td>
</tr>


<tr class="border_bottom">
<td  width="20%" class="labelheading1 tbcellCss">Scheme<span style="color: #ff0000"> *</span></td>
<td width="30%" class="tbcellBorder">
<c:if test="${ userState eq 'CD203' }" >				
				 <html:select  property="scheme" styleId="scheme" name="adminSanctionForm" onchange="fn_getDetailedHeads()" disabled="${disabled}">
						  <html:option value="" key="select" >-------Select-------</html:option>
                          <html:options collection="listSchemes" property="ID" labelProperty="VALUE" />
						</html:select>
				
				</c:if>
				<c:if test="${ userState eq 'CD201' }" >
				<html:text value="EHF-AP" style="width:19em" name="adminSanctionForm" property="scheme" styleId="scheme" readonly='true' disabled="${disabled}"/>
				</c:if>
				<c:if test="${ userState eq 'CD202' }" >
				<html:text value="EHF-TG" style="width:19em" name="adminSanctionForm" property="scheme" styleId="scheme" readonly='true' disabled="${disabled}"/>
				</c:if>
				</td>

<td  width="20%" class="labelheading1 tbcellCss">Sanction Amount<span style="color: #ff0000"> *</span></td>
<td  width="30%" class="tbcellBorder"> <html:text readonly="${readonly}" disabled="${disabled}" style="width:19em" property="sanctionAmount" onchange="validate_cost()" styleId ="sanctionAmount" name="adminSanctionForm"  maxlength="15" title="Sanction Amount" tabindex="0"/></td>
</tr>
       
<tr>
<td  width="20%" class="labelheading1 tbcellCss">Detailed Head<span style="color: #ff0000"> *</span></td>
<td  width="30%" class="tbcellBorder"><html:select  disabled="${disabled}"  property="detailedHead" name="adminSanctionForm" styleId="detailedHead" onmouseover="addTooltip('detailedHead');" title="Select Detailed Head" onchange="fn_getHeadDetails()">
						<option value="">--------Select---------</option>
						 <html:options collection="detailedHeadsList" labelProperty="VALUE" property="ID"/>
						</html:select>
						</td>
<td  width="20% " class="labelheading1 tbcellCss">Name of head of account<span style="color: #ff0000"> *</span></td>
<td  width="30%" class="tbcellBorder"> <html:text readonly='true' disabled="${disabled}" style="width:19em" property="detailedHeadName" styleId ="detailedHeadName" name="adminSanctionForm"  maxlength="50" title="Detailed Head Name" tabindex="0"/></td>
					

</tr>
<tr>
<td  width="20% " class="labelheading1 tbcellCss">Sub Head<span style="color: #ff0000"> *</span></td>
<td  width="30%" class="tbcellBorder"> <html:text readonly='true' disabled="${disabled}" style="width:19em" property="subHead" styleId ="subHead" name="adminSanctionForm"  maxlength="50" title="Sub Head" tabindex="0"/></td>
					
<td  width="20% " class="labelheading1 tbcellCss">Name of Sub Head<span style="color: #ff0000"> *</span></td>
<td  width="30%" class="tbcellBorder"> <html:text readonly='true' disabled="${disabled}" style="width:19em" property="subHeadName" styleId ="subHeadName" name="adminSanctionForm"  maxlength="50" title="Sub Head Name" tabindex="0"/></td>
					

</tr>
<tr>
<td  width="20% " class="labelheading1 tbcellCss">Major Head<span style="color: #ff0000"> *</span></td>
<td  width="30%" class="tbcellBorder"> <html:text readonly='true' disabled="${disabled}" style="width:19em" property="majorHead" styleId ="majorHead" name="adminSanctionForm"  maxlength="50" title="majorHead" tabindex="0"/></td>
					
<td  width="20% " class="labelheading1 tbcellCss">Name of Major Head<span style="color: #ff0000"> *</span></td>
<td  width="30%" class="tbcellBorder"> <html:text readonly='true' disabled="${disabled}" style="width:19em" property="majorHeadName" styleId ="majorHeadName" name="adminSanctionForm"  maxlength="50" title="Major Head Name" tabindex="0"/></td>
					

</tr>


<tr>
<td  width="20%" class="labelheading1 tbcellCss">Source of Budget<span style="color: #ff0000"> *</span></td>
<td  width="30%" class="tbcellBorder"><html:select  property="sourceOfBudget" name="adminSanctionForm" styleId="sourceOfBudget" disabled="${disabled}" onmouseover="addTooltip('sourceOfBudget');" title="Select Source of Budget" onchange="fn_getBudgetSourceCode()">
						<option value="">--------Select---------</option>
						 <html:options collection="sourceOfBudgetList" labelProperty="VALUE" property="ID"/>
						</html:select>
						</td>
						<html:hidden property="sourceName"  name="adminSanctionForm" styleId="sourceName" ></html:hidden>
<td  width="20% " class="labelheading1 tbcellCss">Budget Source Code<span style="color: #ff0000"> *</span></td>
<td  width="30%" class="tbcellBorder"> <html:text readonly='true' style="width:19em" property="sourceCode" styleId ="sourceCode" disabled="${disabled}" name="adminSanctionForm"  maxlength="50" title="Budget Source Code" tabindex="0"/></td>
					

</tr>

<tr>
<td  width="20%" class="labelheading1 tbcellCss">Sanction order Validity date</td>
<td  width="30%" class="tbcellBorder"> <html:text readonly='true' disabled="${disabled}" style="width:15em" property="sanctionOrderDate" styleId ="sanctionOrderDate" name="adminSanctionForm"   title="Sanction Order Validity Date" tabindex="0"/>
<img id="eraImg"  title="Click here to erase" src="/<%=context%>/images/erase37.jpg" alt="click to clear" onclick="javascript:eraser('sanctionOrderDate');" >
</td>
<td  width="20%" class="labelheading1 tbcellCss">Purchase Validity Date</td>
<td  width="30%" class="tbcellBorder"> <html:text readonly='true' disabled="${disabled}" style="width:15em" property="purchaseDate" styleId ="purchaseDate" name="adminSanctionForm"   title="Purchase Validity Date" tabindex="0"/>
<img id="eraImg"  title="Click here to erase" src="/<%=context%>/images/erase37.jpg" alt="click to clear" onclick="javascript:eraser('purchaseDate');" >
</td>					

</tr>
<tr>
<td  width="20% " class="labelheading1 tbcellCss">Inspection Authority<span style="color: #ff0000"> *</span></td>
<td  width="30%" class="tbcellBorder"> <html:text  style="width:19em" disabled="${disabled}" property="inspectionAuthority" styleId ="inspectionAuthority" name="adminSanctionForm" onkeyup="validate_text(inspectionAuthority)"  maxlength="100" title="Inspection Authority" tabindex="0"/></td>
					
<td  width="20% " class="labelheading1 tbcellCss">Executing Authority<span style="color: #ff0000"> *</span></td>
<td  width="30%" class="tbcellBorder"> <html:text  style="width:19em" disabled="${disabled}" property="executingAuthority" styleId ="executingAuthority" name="adminSanctionForm"  maxlength="100" onkeyup="validate_text(executingAuthority)" title="Executing Authority" tabindex="0"/></td>
					

</tr>

<tr>
									<td width="20% " class="labelheading1 tbcellCss">Vendor Type<span style='color:red;'>*</span></td>
									<td class="tbcellBorder" colspan = "4">
										<html:radio  property = "vendorType" styleId="vendorType" disabled="${disabled}" value = "S" name="adminSanctionForm" title = "Single Vendor"  onclick="fn_single()"/> 
										<label for = "radio1" style="cursor:pointer;">Single </label>
										&nbsp;&nbsp;&nbsp;
										<html:radio property = "vendorType" styleId="vendorType" disabled="${disabled}" value = "M" name="adminSanctionForm" title = "Multiple Vendors Vendor"  onclick="fn_Multiple()"/> 
										<label for = "radio2" style="cursor:pointer;">Multiple</label>
										&nbsp;&nbsp;&nbsp;
										<html:radio property = "vendorType" styleId="vendorType" disabled="${disabled}" value = "N" name="adminSanctionForm"  title = "Not Applicable" onclick="fn_Multiple()"/> 
										<label for = "radio3" style="cursor:pointer;">Not Applicable</label>
									</td>
									
								</tr>
<tr style="display:none;" id="vendorTR">
<td  width="20%" class="labelheading1 tbcellCss">Vendor Name<span style="color: #ff0000"> *</span></td>
<td  width="30%" class="tbcellBorder"><html:select  property="vendorName" disabled="${disabled}" name="adminSanctionForm" styleId="vendorName" onmouseover="addTooltip('vendorName');" title="Select Vendor Name"  style="width:17em" onchange="getVendorCode();">
						<option value="">--------Select---------</option>
						 <html:options collection="vendorNamesList" labelProperty="VALUE" property="ID"/>
						</html:select>
						<html:hidden property="vName"  name="adminSanctionForm" styleId="vName" ></html:hidden>

						</td>
<td  width="20% " class="labelheading1 tbcellCss">Vendor Code<span style="color: #ff0000"> *</span></td>
				<td  width="30%" class="tbcellBorder"><html:select disabled="${disabled}"  property="vendorCode" name="adminSanctionForm" styleId="vendorCode" onmouseover="addTooltip('vendorCode');" onchange="getVendorName();"  title="Select Vendor Code">
						<option value="">--------Select---------</option>
						 <html:options collection="vendorNamesList" labelProperty="ID" property="ID"/>
						</html:select>
						</td>	

</tr>	

<tr>
<td  width="20% " class="labelheading1 tbcellCss">To be Issued on<span style="color: #ff0000"> *</span></td>
<td  width="30%" class="tbcellBorder"> <html:text  style="width:19em" property="toBeIssuedOn" disabled="${disabled}" styleId ="toBeIssuedOn" name="adminSanctionForm"   maxlength="50" title="To be Issued on" tabindex="0" onblur="chkSpecialChars('toBeIssuedOn')"/></td>
					
<td  width="20% " class="labelheading1 tbcellCss">Type of Sanction<span style="color: #ff0000"> *</span></td>
				<td  width="30%" class="tbcellBorder"><html:select  property="typoOfSanction" disabled="${disabled}" name="adminSanctionForm" styleId="typoOfSanction" onmouseover="addTooltip('typoOfSanction');" title="Select Type of Sanction">
						<option value="">--------Select---------</option>
						 <html:option value="Items">Items</html:option>
						  <html:option value="Supply">Supply</html:option>
						   <html:option value="Work">Work</html:option>
						    <html:option value="Service">Service</html:option>
						</html:select>
						</td>						
</tr>
<logic:notEqual property="specFlag" value="Y" name="adminSanctionForm">
<tr class="addRemoveTrClass">
<td  width="20% " class="labelheading1 tbcellCss">Specification<span style="color: #ff0000"> *</span></td>

<td  width="30%" class="tbcellBorder"> 
<input type='text'  style='width:19em' ${butDisabled} id ='specification1' name='specification1' maxlength='100' title='Specification' onkeyup="validate_text(specification1)" tabindex='0'/>
</td>
					
<td  width="20% " class="labelheading1 tbcellCss">Cost<span style="color: #ff0000"> *</span></td>
<td  width="30%" class="tbcellBorder"> 
<input type='text' style='width:19em' ${butDisabled} id ='cost1' name='cost1'  maxlength='15'  onkeyup="validate_number(this)"  onchange="validate_cost(cost1)" title='Cost' tabindex='0'/>
</td>
				
</tr>	
</logic:notEqual>	
<logic:equal property="specFlag" value="Y" name="adminSanctionForm">
<%
System.out.println("Before For");

String specString =(String)request.getAttribute("specString");
String costString = (String)request.getAttribute("costString");

String[] specList=specString.split("~");
String[] costList=costString.split("~");

for(int sci=1;sci<=specList.length;sci++){
																						
String sp = specList[sci-1];
String cs = costList[sci-1];

if(sp==null)break;%>
	<tr class='addRemoveTrClass'>
<td  width="20% " class="labelheading1 tbcellCss">Specification<span style="color: #ff0000"> *</span></td>

<td  width="30%" class="tbcellBorder"> 
<input type='text'  style='width:19em' id ='specification<%=sci%>' ${butDisabled} name='specification<%=sci%>' value='<%=sp%>' onkeyup="validate_text(specification<%=sci%>)" maxlength='100' title='Specification' tabindex='0'/>
</td>
					
<td  width="20% " class="labelheading1 tbcellCss">Cost<span style="color: #ff0000"> *</span></td>
<td  width="30%" class="tbcellBorder"> 
<input type='text' style='width:19em' id ='cost<%=sci%>' name='cost<%=sci%>' ${butDisabled}  maxlength='15' value='<%=cs%>' onkeyup="validate_number(cost<%=sci%>)" onchange="validate_cost()" title='Cost' tabindex='0'/>
</td>
					

</tr>		
<%}%>		
</logic:equal>
<tr id="addRowTrId">
				<td colspan='5' style='text-align:right;padding-bottom:0px;'>
				
               <input type="button" onclick='addMoreCost()' ${butDisabled} title='Click to add more Specification and Cost.' id="addrows" class="but" value="Add">
               <input type="button" onclick='removeCost()' ${butDisabled} class="but" title='Click to remove last Specification and Cost.' value="Remove"/>
				</td></tr>
				<tr><td>&nbsp;</td></tr>																		
       </table>  
       
  <table width="75%"  cellSpacing="1" cellPadding="1" style="text-align:center;margin:0 auto;">
  <tr>
  <td class="tbcellCss" colspan="2"><b>Existing Attachments</b></td></tr>
   <c:if test="${showFiles eq 'Y'}">
  <c:forEach var="fileList" items="${fileNames}">
  <tr><td width="80%" class="labelheading1 tbcellCss" align="center">
 <a href="#" onclick="viewAttachmnt(this)" id='${fileList.VALUE}'><span title="Click to view attachment" class="labelheading1" style="font-size: 1.25em;">${fileList.VALUE}</span></a>
 </td>
 </tr>
  </c:forEach>  
  </c:if> 
  <c:if test="${showFiles ne 'Y'}">
  <tr><td><b>No attachments found</b></td></tr>
  </c:if>   
  </table>	
		
<table  width="90%" align="center" style="padding-left:1em;margin:0 auto;">
<tr>
<td >
<logic:present name="adminSanctionForm"  property="remarksList">
<bean:size id="size" name="adminSanctionForm" property="remarksList"/>
<logic:greaterThan name="size" value="0">
   <table class="tbheader"  style="width:90%;margin:0 auto;">
            <tr >
            <td align="left">
<img id="menuImage" src="/<%=context%>/images/updownArrow.jpg" title="Maximize/Minimize" style=cursor:hand; width="25" height="25" alt="Hide Menu" align="top" onclick="javascript:expandOrCollapse()" ></img>
</td>
<td  align="center" width="90%" ><b>Previous Remarks</b>
</td>
</tr>
</table>     
<table width="90%" align="center"  style="padding-left:2em;margin:0 auto;" >
<tr>
<td>
 <div id="remarksDiv"  style="height:10em;overflow:auto;width:950px;margin:0 auto;" > 
<table width="90%"   style="padding-left:1em;margin:0 auto;" border="0">

<tr>
	
<td class="tbcellBorder"  style="width:3em;" align="left" ><b>SNo</b></td> 
<td  class="tbcellBorder"  style="width:10em;" align="left"><b>Date</b></td> 
<td  class="tbcellBorder"  style="width:15em;" align="left"><b>Updated&nbsp;By</b></td> 
<td  class="tbcellBorder"  style="width:16em;" align="left"><b>Status</b></td> 
<td class="tbcellBorder"  style="width:30em;" align="left" ><b>Remarks</b></td> 
</tr>
	<logic:iterate id="data" name="adminSanctionForm" property="remarksList" indexId="index">
	
<c:choose>
     <c:when test='${(index+1)%2 eq 0}'>
      <c:set var="rowColor" value="text_class" scope="page"/>
    </c:when>
    <c:otherwise>
      <c:set var="rowColor" value="text_class1" scope="page"/>
    </c:otherwise>
  </c:choose>

	<tr class="${rowColor}">	
	
	<td class="tbcellBorder" align="left" style="width:3em;" >${index + 1} </td>
	<td  class="tbcellBorder" align="left" style="width:10em;" ><bean:write name="data" property="remarksDt"/></td>
	<td class="tbcellBorder" align="left" style="width:15em;" ><bean:write name="data" property="updatedBy"/></td>
	<td class="tbcellBorder" align="left" style="width:15em;" ><bean:write name="data" property="action"/></td>
	<td class="tbcellBorder" align="left" style="width:28em;" ><bean:write name="data" property='remarks'  />
	</td>
	</tr>
	
</logic:iterate>
</table>

 </div> 
</td>
</tr>

</table>

</logic:greaterThan>

  <logic:equal name="size" value="0">
  <table  style="width:90%;margin:0 auto;">
 <tr>
 <td  align="center"><b>No Previous Remarks Found</b></td>
 </tr>

 </table>
 </logic:equal>  
</logic:present>


</table>

<table  style="${showRemarksTextArea}"  align="center" border="0"  style="padding-left:10em;margin:0 auto;">
<tr><td style="padding-left:5em;" >	
			Remarks on <script language="JavaScript">
			HoldDate=new Date();
			document.write(HoldDate.getDate() + "/" + (HoldDate.getMonth()+1) + "/" + HoldDate.getFullYear());
        </script> :
			<html:textarea title="Remarks for Admin Sanction"  name="adminSanctionForm" styleId="adminSancRemarks" property="adminSancRemarks" rows="5" cols="120"  style="height: 80px; width: 850px" onkeypress="validateRemarks('adminSancRemarks')"></html:textarea>
			</td></tr>
			</table>
		
		
		
		<table align="center" border="0" style="padding-top:0px;margin:0px auto;" id="printForm">
		<tr><td><br></td></tr>
<tr>
<td> <button class="but" id="printBtn" style="${showPrint}" name="printBtn" tabindex="0" type="button"  value="print" title="Print form" onClick="printPage();">Print</button>
</td>
</tr>
</table>
		
		 
		
			
<table align="center" border="0" style="padding-top:0px;margin:0px auto;" id="submitTable">
<tr>
<td> <button class="but" style="${showSave}" id="saveBtn" name="saveBtn" tabindex="0" type="button"  value="${btnLbl}" title="Save" onClick="saveAdminSancDetails('${btnLbl}');">Save</button></td>
<td> <button class="but" style="${showSubmit}" id="submitBtn" name="submitBtn" tabindex="0" type="button"  value="${submit}" title="Submit" onClick="saveAdminSancDetails('${submit}');">Submit</button>
</td>
</tr>
</table>
<table width="100%" align="center" border="0" style="padding-top:0px;margin:0px auto;">
		<tr>
<td align="center">
 <button class="but"  style="${showUpdateBtn}"  id="updateBtn" name="updateBtn" tabindex="0" type="button"  title="${btnLbl}"  value="${btnLbl}" onClick="saveAdminSancDetails('${btnLbl}')">${btnLbl}</button>
<button class="but"   style="${showRecApprBtn}" id="recApproveBtn" name="recApproveBtn"  tabindex="0" type="button"  title="Recommend for Approval"  value="${btnRecAppr}" onClick="submitAdminSancDetails('${btnRecAppr}')"> Recommend for Approval</button>
&nbsp;<button class="but" style="${showActionBtn}" id="approveBtn" name="approveBtn" tabindex="0" type="button"  title="Approve"  value="${btnAppr}" onClick="submitAdminSancDetails('${btnAppr}')"> Approve</button>
&nbsp;<button class="but" style="${showActionBtn1}" id="pendingBtn" name="pendingBtn" tabindex="0" type="button"  title="Pending"  value="${btnPen}" onClick="submitAdminSancDetails('${btnPen}')">Pending</button>
&nbsp;<button class="but" style="${showActionBtn1}" id="rejectBtn" name="rejectBtn" tabindex="0" type="button"  title="Reject"  value="${btnRej}" onClick="submitAdminSancDetails('${btnRej}')">Reject</button></td>
</tr>
<tr><td>&nbsp;</td></tr>
</table>
	<html:hidden property="specification" styleId="specification" name="adminSanctionForm"></html:hidden>
	<html:hidden property="cost" styleId="cost" name="adminSanctionForm"></html:hidden>		
					
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
     </div>    				
</form>
<c:if test="${ disabled eq 'true' }" >
<script>
var elements = document.getElementById("adminSanctionForm").elements;
if(elements!=null)
	{
for (i=0; i<elements.length; i++){
	if(elements[i]!=null){
	if(document.getElementById(elements[i].id).type=="text")
	document.getElementById(elements[i].id).setAttribute("title",document.getElementById(elements[i].id).value);
	}
  }
	}

</script>
	</c:if>
</body>

<script>

var trLStr = "<tr class='addRemoveTrClass'> <td  width='20%' class='labelheading1 tbcellCss'>Specification<span style='color: #ff0000'> *</span></td>  <td  width='30%' class='tbcellBorder'> <input type='text' ${butDisabled}  style='width:19em' id ='specificationXXXXX' name='specificationXXXXX'  maxlength='100' title='Specification' onkeyup='validate_text(specificationXXXXX)' tabindex='0'/></td>"			
	+ "<td  width='20% ' class='labelheading1 tbcellCss'>Cost<span style='color: #ff0000'> *</span></td>"
	+ "<td  width='30%' class='tbcellBorder'> <input type='text' style='width:19em' id ='costXXXXX' name='costXXXXX' onkeyup='validate_number(costXXXXX)' onchange='validate_cost()' ${butDisabled}  maxlength='15' title='Cost' tabindex='0'/></td> </tr>";

	function addMoreCost(){
	var i = $('.addRemoveTrClass').length + 1;
	
	if(i>10){
		jqueryAlertMsgTop("Alert","Maximum of 10 specifications and costs can be added.");
		return;
	}
	$('#addRowTrId').before(trLStr.replace(/XXXXX/g, i));
		$("#cost"+i).bind("contextmenu",function(e){
	     e.preventDefault();
	     
	  });
}
function removeCost(){
	var i = $('.addRemoveTrClass').length;
	if(i!=1)
	$('.addRemoveTrClass :last').remove();
}

function getVendorCode()
{
	$("#vendorCode-input").val($('#vendorName option:selected').val());
	document.getElementById("vName").value=$('#vendorName option:selected').text();
	
}
function getVendorName()
{
	val2=$('#vendorCode option:selected').val();
	var select = document.getElementById("vendorName");
    for(var i = 0;i < select.options.length;i++){
        if(select.options[i].value == val2 )
        {
            document.getElementById("vendorName-input").value=document.getElementById("vendorName").options[i].text;   
        }
  }

	

}
function fn_getBudgetSourceCode()
{
	document.getElementById('sourceCode').value=($('#sourceOfBudget option:selected').val());
	document.getElementById("sourceName").value=$('#sourceOfBudget option:selected').text();
}

$(function(){setInterval(function(){$('[blink]').fadeOut(100, function(){$(this).fadeIn(100, function(){$(this).fadeOut(100, function(){$(this).fadeIn(100, function(){$(this).fadeOut(100, function(){$(this).fadeIn(100);});});});});});}, 4000);});

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


function focusFieldView(el)
{
var offset = $(el).offset();
var top = offset.top;
focusBox(el);
}

function focusBox(arg) {

	aField = arg;
	setTimeout("aField.focus()", 0);

}

$(document).ready(function() {
    $("#sanctionAmount").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
             // Allow: Ctrl+A
            (e.keyCode == 65 && e.ctrlKey === true) || 
             // Allow: home, end, left, right
            (e.keyCode >= 35 && e.keyCode <= 39)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
});

function validate_text(ele)
{
	var str = ele.value;
	if(str.search(/[^ A-Za-z.,]/) > -1)
	{
		ele.value = str.replace(/[^ A-Za-z.,]+/, '');
	}
	if(ele.value.charAt(0) == ' ' || ele.value.charAt(0) == '.' || ele.value.charAt(0) == ',')
	{
		ele.value = str.replace(/[^A-Za-z]+/, '');
	}
	return;
}
function validate_text1(ele)
{
	var str = ele.value;
	if(str.search(/[^ A-Za-z.,0-9\/]/) > -1)
	{
		ele.value = str.replace(/[^ A-Za-z.,0-9\/]+/, '');
	}
	if(ele.value.charAt(0) == ' ' || ele.value.charAt(0) == '.' || ele.value.charAt(0) == ',' || ele.value.charAt(0) == '/' )
	{
		ele.value = str.replace(/[^A-Za-z0-9]+/, '');
	}
	return;
}
function validate_number(ele)
{
	var val = ele.value;
	if(val.search(/[^0-9]/) > -1){
		ele.value = val.replace(/[^0-9]+/, '');
	}
	if(ele.value.charAt(0) == 0){
		ele.value = ele.value.replace(/^0+/, '');
	}
	return true;
}

function validate_cost()
{
	var i = $('.addRemoveTrClass').length;
	var totalCost=0;
	var costList;
	var j=0;
	for(var k=1;k<=i;k++)
	 {
	 if(k==1)
		 {
		 costList=document.getElementById("cost"+k).value;
		 totalCost=parseInt(costList);
		 j=k;
		 }
	 else
		 {
		 costList=costList+"~"+document.getElementById("cost"+k).value;
          totalCost=totalCost+parseInt(document.getElementById("cost"+k).value);
          j=k;
		 }
	 }
	 if(totalCost>parseInt(document.getElementById("sanctionAmount").value))
     {
		 var fr=partial(focusFieldView,document.getElementById("cost"+j));
        jqueryAlertMsgTop("Alert","Sum of costs specified must be less than or equal to given sanction amount",fr);
        document.getElementById("cost"+j).value=""; 
        return false;
     
	   }
	 return true;
}


function chkSpecialChars(str){
	
	var dot=/^[.!@#$%^&*()~|+,]+$/;
var number=/^[0-9]+$/;
var pattern=/^[a-zA-Z0-9 , . \/ ? () \!]+$/;
	   var count=0;
	   var value= document.getElementById(str).value;
	   if (value != ""){ 
		   if(value.match(dot))
		   {
			
			
			if(str=="toBeIssuedOn")
				{
				var fr=partial(focusFieldView, document.getElementById(str));
				jqueryAlertMsgTop("Alert","To be issued on cannot contain only Special characters",fr);
				}
			
			else
				{
				var fr=partial(focusBox, document.getElementById(str));
				jqueryAlertMsgTop("Alert","Subject cannot contain only Special characters",fr);
				}
				
			document.getElementById(str).value="";
			return false;
		   }
		   
		   if(value.match(number))
		   {
			   var fr=partial(focusBox, document.getElementById(str));
			   if(str=="toBeIssuedOn")
				{
				jqueryAlertMsgTop("Alert","To be issued on cannot contain only numbers",fr);
				}
			   else
				   jqueryAlertMsgTop("Alert","Please provide proper subject, it cannot contain only numbers",fr);
				document.getElementById(str).value="";
				return false;
		   }
		   if(!value.match(pattern))
			   {
			   var fr=partial(focusBox, document.getElementById(str));
			   if(str=="toBeIssuedOn")
				jqueryAlertMsgTop("Alert","To be issued on can accept only alpha numerical values and special characters like , . / ? () \!",fr);
			   else
				   jqueryAlertMsgTop("Alert","Subject can accept only alpha numerical values and special characters like , . / ? () \!",fr);
				document.getElementById(str).value="";
				return false;
			   
			   }
	   }
	   
return false;

}

$(document).ready(function(){
    $("#sanctionAmount").bind("contextmenu",function(e){
     e.preventDefault();
  });
});

$(document).ready(function(){

    $("#cost1").bind("contextmenu",function(e){
     e.preventDefault();
     
  });
		
});


function printPage(){

	var sanctionId=document.getElementById("sanctionId").value;
	var url = "adminSanction.do?actionFlag=viewDetailsById&sanctionId="+sanctionId+"&printFlag=yes";	
     window.open(url,"SanctionForm",'width=1150,height=600,top=100,left=0,status=no,menubar=no,scrollbars=yes,resizable=yes');

}
function fn_loadImage() {
	document.getElementById('processImagetable').style.display = "";
}
function fn_removeLoadingImage() {
	document.getElementById('processImagetable').style.display = "none";
}
function eraser(arg1) {
	var verifyFlag=document.getElementById(arg1).disabled;
	if(verifyFlag==true)
		{
		   return false;
		}
	else
		{
		document.getElementById(arg1).value = "";
		}
	
}

function fn_getDetailedHeads()
{
	var xmlhttp;
	var url;
	var scheme=document.getElementById('scheme').value;
	url= '/<%=context%>/adminSanction.do?actionFlag=getDetailedHeads&scheme='+scheme;
	if (window.XMLHttpRequest){
	 xmlhttp=new XMLHttpRequest();
	}
	else if (window.ActiveXObject){		
	 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	else{
	 alert("Your Browser Does Not Support XMLHTTP!");
	}
	xmlhttp.onreadystatechange=function(){
	    if(xmlhttp.readyState==4){	
	    	document.forms[0].detailedHead.options[0]=new Option('-------- Select --------',"");
	        var resultArray=xmlhttp.responseText;
	        if(resultArray!= null){
	        	 resultArray2 = resultArray.replace("[","");
		         resultArray2 = resultArray2.replace("]","");            
		         var addList = resultArray2.split(", @");
		         if(addList.length>0){   
		                for(var i = 0; i<addList.length;i++){	
		                    var arr=addList[i].split("~");
		                     if(arr[1]!=null && arr[0]!=null){
		                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
		                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
		                     	 document.forms[0].detailedHead.options[i+1] =new Option(val1,val2);       
						   } 
		                }
		            }
		        	 
	        	}
	        
	    }			
	}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);

	document.getElementById('detailedHead').options.length=0;
	$('#detailedHead-input').val('');
	document.getElementById('detailedHeadName').value="";
	document.getElementById('subHead').value="";
	document.getElementById('subHeadName').value="";
	document.getElementById('majorHead').value="";
	document.getElementById('majorHeadName').value="";
	
	
	}

function getVendorsSchemewise(scheme)
{
	document.getElementById('vendorName').options.length=0;
	$('#vendorName-input').val('');
	document.getElementById('vendorCode').options.length=0;
	$('#vendorCode-input').val('');

	var state="";
	if(scheme=='SC13')
		 state='CD201';
		else if (scheme=='SC14')
			 state='CD202';

 	url= '/<%=context%>/adminSanction.do?actionFlag=getVendorsSchemewise&state='+state;

	if (window.XMLHttpRequest){
	 xmlhttp=new XMLHttpRequest();
	}
	else if (window.ActiveXObject){		
	 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	else{
	 alert("Your Browser Does Not Support XMLHTTP!");
	}
	xmlhttp.onreadystatechange=function(){
	    if(xmlhttp.readyState==4){	
	    	document.forms[0].vendorName.options[0]=new Option('-------- Select --------',"");
	    	document.forms[0].vendorCode.options[0]=new Option('-------- Select --------',"");
		       
	    	var resultArray=xmlhttp.responseText;
	        if(resultArray!= null){
	        	 resultArray2 = resultArray.replace("[","");
		         resultArray2 = resultArray2.replace("]","");            
		         var addList = resultArray2.split(", @");
		         if(addList.length>0){   
		                for(var i = 0; i<addList.length;i++){	
		                    var arr=addList[i].split("~");
		                     if(arr[1]!=null && arr[0]!=null){
		                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
		                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
		                     	 document.forms[0].vendorName.options[i+1] =new Option(val1,val2);       
		                     	 document.forms[0].vendorCode.options[i+1] =new Option(val2,val2); 
		                     } 
		                }
		            }
	        	}
	    }			
	}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);

	
}


function fn_getHeadDetails()
	{
	var xmlhttp;
	var url;
	var detailedHead=document.getElementById('detailedHead').value;
	var scheme=document.getElementById('scheme').value;
	
	url= '/<%=context%>/adminSanction.do?actionFlag=getHeadDetails&scheme='+scheme+'&detailedHead='+detailedHead;
	if (window.XMLHttpRequest){
	 xmlhttp=new XMLHttpRequest();
	}
	else if (window.ActiveXObject){		
	 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	else{
	 alert("Your Browser Does Not Support XMLHTTP!");
	}
	xmlhttp.onreadystatechange=function(){
	    if(xmlhttp.readyState==4){
		    
	        var resultArray=xmlhttp.responseText;
	        if(resultArray!= null){
	        	 resultArray2 = resultArray.replace("[","");
		         resultArray2 = resultArray2.replace("]","");  
	        	var headDtls = resultArray2.split("~");
	        	document.getElementById('detailedHeadName').value=headDtls[0];
	        	document.getElementById('subHead').value=headDtls[1];
	        	document.getElementById('subHeadName').value=headDtls[2];
	        	document.getElementById('majorHead').value=headDtls[3];
	        	document.getElementById('majorHeadName').value=headDtls[4];
	        	}
	    }			
	}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);

	
	 


	}
function fn_getIssuingAuthNames()
{
	var xmlhttp;
	var url;
	var issuingAuthority=document.getElementById('issuingAuthority').value;
	document.getElementById('issuingAuthorityName').options.length=0;
	$('#issuingAuthorityName-input').val('');
	document.getElementById('issuingAuthorityName').value="";
	
	
	url= '/<%=context%>/adminSanction.do?actionFlag=getIssuingAuthName&issuingAuthority='+issuingAuthority;
	if (window.XMLHttpRequest){
	 xmlhttp=new XMLHttpRequest();
	}
	else if (window.ActiveXObject){		
	 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	else{
	 alert("Your Browser Does Not Support XMLHTTP!");
	}
	xmlhttp.onreadystatechange=function(){
	    if(xmlhttp.readyState==4){	
	    	document.forms[0].issuingAuthorityName.options[0]=new Option('-------- Select --------',"");
	        var resultArray=xmlhttp.responseText;
	        if(resultArray!= null){
	        	 resultArray2 = resultArray.replace("[","");
		         resultArray2 = resultArray2.replace("]","");            
		         var addList = resultArray2.split(", @");
		         if(addList.length>0){   
		                for(var i = 0; i<addList.length;i++){	
		                    var arr=addList[i].split("~");
		                     if(arr[1]!=null && arr[0]!=null){
		                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
		                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
		                     	 document.forms[0].issuingAuthorityName.options[i+1] =new Option(val1,val2);       
						   } 
		                }
		            }
	        	}
	    }			
	}
	xmlhttp.open("Post",url,false);
	xmlhttp.send(null);


}

</script>
</html>