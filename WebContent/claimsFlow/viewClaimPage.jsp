<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/common/include.jsp"%>
<html:html>  
<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Claims">  
<title>Claims Flow Home</title>   
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<LINK href="./css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>  
<script LANGUAGE="JavaScript" type="text/javascript"   SRC="/<%=context%>/Preauth/maximizeScreen.js"></script> 
<%@ include file="/common/includePatientDetails.jsp"%>
<script src="bootstrap/js/bootbox.min.js"></script>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<style>body{font-size:12px !important}
.bootbox .modal-dialog .modal-content {top:30% !important;}</style>
<style type="text/css">
#imageID {
top: 45px;
}
body{font-size:12px !important}
</style>
<script type="text/javascript">
String.prototype.trim = function () {
    return this.replace(/^\s*/, "").replace(/\s*$/, "");
}
//jquery alerts functions
var dentalSurg='${dentalSurg}';

var newCpd='${newCpd}';
function partial(func /*, 0..n args */) {
	 var args = new Array();
	 for (var i = 1; i < arguments.length; i++) { args.push(arguments[i]); }
	  return function() {
	    var allArguments = args.concat(Array.prototype.slice.call(arguments));
	    return func.apply(this, allArguments);
	  };
}

function fn_openAtachment(filepath)
	{  
	    var url = "/<%=context%>/attachmentAction.do?actionFlag=viewAttachment&docSeqId="+filepath;
	    childWindow= window.open(url,"",'width=950,height=600,resizable=yes,top=100,left=50,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
	}
function fn_openAtachmentMedical(filepath)
{  
    var url = "attachmentAction.do?actionFlag=viewAttachment&medicalFlg=Y&docSeqId="+filepath;
    childWindow= window.open(url,"",'width=1150,height=600,resizable=yes,top=100,left=0,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
}	
function fn_maxmizeTop()
{
parent.fn_maxmizeTop();
	/*var url='/Operations/casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=ipTab&CaseId=${caseId}&flag=N&casesForApproval='+parent.parent.caseApprovalFlag+'&errSearchType='+parent.parent.errSearchType+'&disSearchType='+parent.parent.disSearchType+'&module='+parent.parent.module;
	document.forms[0].action=url;
	document.forms[0].target="_parent";
    document.forms[0].submit();*/
	}
function fn_maxmizeRight(){
	parent.fn_maxmizeRight();
}
function validateSpaces(input,arg1)
{
	var a=input.value;
	var fr = partial(focusBox,input);
	if(a.trim()=="")
	{
	input.value="";
    jqueryErrorMsg('Text Validation',"Cannot accept only spaces in "+arg1,fr);
	return false;
	}
	if(a.charAt(0)==" ")
		{
		input.value="";
		jqueryErrorMsg('Text Validation',arg1+ " should not start with space",fr);
		return false;
		}
}
function validateSplKeyCodes(evt)      
{         
	var charCode = (evt.which) ? evt.which : evt.keyCode;       
			if (charCode>31&& (charCode<47 || charCode>58)&&(charCode<65 || charCode>90)
					&&(charCode<97 || charCode>122)&&(charCode!= 63 &&(charCode<39 || charCode>41)
							&&(charCode!=44)&&(charCode!=46)&&(charCode!=91)&&(charCode!=93)&&(charCode!=95)
							&&(charCode!=32)&&(charCode!=38)&&(charCode!=39)&&(charCode!=40)))	
			    return false; 	
				return true;  
} 
function maxLengthPress(field,maxChars,e)
{
	var code;
    if (!e) var e = window.event;
    if (e.keyCode) code = e.keyCode;
    else if (e.which) code = e.which; 
	if(field.value.length >= maxChars) 
	{
		if(code==8 || code==9 || code==46 || code==37 || code==38 || code==39 || code==40)
			//Key Code check for backspace,delete,Arrow Left, Arrow Up,Arrow Right,Arrow Down
			{
				e.returnValue=true;
				return true;
			}
		else
			{
				e.returnValue=false;
	        	return false;
		 	}
         }
}
//For validating maxlength onpaste event--For textarea fields
function maxLengthPaste(field,maxChars)
{
	var fr = partial(focusBox,field);
      event.returnValue=false;
      if(window.clipboardData)
    	  {
      		if((field.value.length +  window.clipboardData.getData("Text").length) > maxChars) 
			{
			//alert("Characters should not exceed 3000");
			jqueryErrorMsg('Max Length Validation',"Remarks length should not exceed " +maxChars ,fr);
        	return false;
        	}
      		event.returnValue=true;
    	  }
      if (event.clipboardData) 
      {  
    	if((field.value.length + event.clipboardData.getData('text/plain').length) > maxChars)
    		{
			//alert("Characters should not exceed 300");
			jqueryErrorMsg('Max Length Validation',"Remarks length should not exceed " +maxChars ,fr);
        	return false;
        	}
      		event.returnValue=true;
      }
}
function validateTodayDate(arg1,input){

	var fr = partial(focusBox,input);	
    var entered = input.value;
	entered = entered.split("-");
	var cmth = parseInt(entered[1],10); 
	var cdy = parseInt(entered[0],10);
	var cyr = parseInt(entered[2],10);
	if(isNaN(cyr))
	{
		input.value="";
		//alert("Please Select "+arg1);
		jqueryErrorMsg('Date Validation',"Please Select "+arg1,fr);
	}
	else
	{
	var billDate=""+(cmth)+"/"+ cdy +"/"+ cyr;
	billDate=Date.parse(billDate);
	var today= new Date();
	var nowmonth = today.getMonth();
	var nowday = today.getDate();
	var nowyear = today.getFullYear();
	var todayDate=""+(nowmonth+1)+"/"+ nowday +"/"+ nowyear;
	todayDate=Date.parse(todayDate);
	//if(billDate>=todayDate)
	//	{
		//input.value="";
		//alert(arg1+" should be less than Today's Date");
	//	}

var dischargeDate = document.forms[0].onlineDschrgeDate.value;
dischargeDate = dischargeDate.split("-");
var cmth1 = parseInt(dischargeDate[1],10); 
var cdy1 = parseInt(dischargeDate[0],10);
var cyr1 = parseInt(dischargeDate[2],10);
	var disDate = ""+(cmth1)+"/"+ cdy1 +"/"+ cyr1;
	disDate=Date.parse(disDate);
	if(billDate<disDate || billDate>todayDate)
	{
	input.value="";
	//alert(arg1+" should be between today's date and discharge Date("+document.forms[0].onlineDschrgeDate.value+")");
	jqueryErrorMsg('Date Validation',arg1+" should not be less than discharge Date("+document.forms[0].onlineDschrgeDate.value+")",fr);
	}
	}
}
function validateDate(arg1,input)
{
	var fr = partial(focusBox,input);	
	var entered = input.value;
	entered = entered.split("-");
	var cmth = parseInt(entered[1],10); 
	var cdy = parseInt(entered[0],10);
	var cyr = parseInt(entered[2],10);
	if(isNaN(cyr))
	{
		input.value="";
		//alert("Please Select "+arg1);
		jqueryErrorMsg('Date Validation',"Please Select "+arg1,fr);
	}
	else{

		var inputDate = input.value;var actualDate;
		inputDate = inputDate.split("-");
		var cmth1 = parseInt(inputDate[1],10); 
		var cdy1 = parseInt(inputDate[0],10);
		var cyr1 = parseInt(inputDate[2],10);
		var enteredDate = ""+(cmth1)+"/"+ cdy1 +"/"+ cyr1;
		enteredDate=Date.parse(enteredDate);
		
        if(arg1=='Admission Date'){
        actualDate = document.forms[0].onlineAdmDate.value;
        document.forms[0].admDtCheck[1].checked=true;
        document.getElementById("admDtCheck").value='N';
        }
        else if(arg1=='Surgery/Therapy Date'){
        actualDate = document.forms[0].onlineSurgDate.value;
        document.forms[0].surgDtCheck[1].checked=true;
        document.getElementById("surgDtCheck").value='N';
        }
        else if(arg1=='Discharge/Death Date'){
        actualDate = document.forms[0].onlineDschrgeDate.value;
        document.forms[0].dischargeDtCheck[1].checked=true;	
        document.getElementById("dischargeDtCheck").value='N';
        }
        
		actualDate = actualDate.split("-");
        var cmth1 = parseInt(actualDate[1],10); 
        var cdy1 = parseInt(actualDate[0],10);
        var cyr1 = parseInt(actualDate[2],10);
	    var actDate = ""+(cmth1)+"/"+ cdy1 +"/"+ cyr1;
	    actDate=Date.parse(actDate);

        if(actDate-enteredDate==0 && arg1=='Admission Date'){
        	document.forms[0].admDtCheck[0].checked=true;
        	document.getElementById("admDtCheck").value='Y';}
        
        if(actDate-enteredDate==0 && arg1=='Surgery/Therapy Date'){
            document.forms[0].surgDtCheck[0].checked=true;
            document.getElementById("surgDtCheck").value='Y';
        }
        
        if(actDate-enteredDate==0 && arg1=='Discharge/Death Date'){
        	document.forms[0].dischargeDtCheck[0].checked=true;
        	document.getElementById("dischargeDtCheck").value='Y';
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
function validateMaxlength(input,e)
{
	var fr = partial(focusBox,input);
	var fieldValue=input.value;
	var code;
    if (!e) var e = window.event;
    if (e.keyCode) code = e.keyCode;
    else if (e.which) code = e.which; 
	if(fieldValue.trim().length>=300)
		{
		input.value=fieldValue.trim().substr(0,300);
		jqueryErrorMsg('Maxlength Validation','Exceeded maximum limits of 300 words.',fr);		
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
function validateAlphaNumSpace(arg1,input)
{
	var a=input.value;
	var fr = partial(focusBox,input);
	var regAlphaNum=/^[0-9a-zA-Z ]+$/;
	if(a.trim().search(regAlphaNum)==-1)
		{
		if(a.trim().length==0){
			//alert("Only Spaces are not allowed in "+arg1);
			jqueryErrorMsg('Text Validation',"Only Spaces are not allowed in "+arg1,fr);
			input.value="";
			//input.focus();
			return false;
			
		}
		//alert("Only AlphaNumeric are allowed for "+arg1);
		jqueryErrorMsg('Text Validation',"Only AlphaNumeric are allowed for "+arg1,fr);
		//input.focus();
		return false;
		}
}
function validateNumber(arg1,input)
{
	var a=input.value;
	var regDigit=/^\d+$/ ;
	var fr = partial(focusBox,input);
	if(a.search(regDigit)==-1)
		{
		input.value="";
		jqueryErrorMsg('Text Validation',"Only Numbers are allowed for "+arg1,fr);
		//focusBox(input);
		//alert("Only Numbers are allowed for "+arg1);
		return false;
		}
}
function addToDedcution(input){
	//parseInt(document.getElementById("stay").value*1)+
	var sum = parseInt(document.getElementById("inputs").value*1)
	+parseInt(document.getElementById("profFee").value*1)+parseInt(document.getElementById("investBill").value*1);
		//var sum = parseInt(document.getElementById("deduction").value) + parseInt(input.value*1);
	if(parseInt(sum*1)>parseInt(document.getElementById("totalClaim").value*1))
		{
		//alert("Deductions cannot be more than Total claim");
		var fr = partial(focusBox,input);
		jqueryAlertMsg('Alert',"Deductions cannot be more than Total claim",fr);
		input.value="";
       	return false;
		}
	else{
	document.getElementById("deduction").value=sum;
	var finalAmt  = parseInt(document.getElementById("totalClaim").value*1) - parseInt(document.getElementById("deduction").value*1);
	document.getElementById("cpdAprAmt").value = finalAmt;
	}
	
}
function addToDedcutionOrg(input){
	//parseInt(document.getElementById("stay").value*1)+
	var sum = parseInt(document.getElementById("inputsOrg").value*1)
	+parseInt(document.getElementById("profFeeOrg").value*1)+parseInt(document.getElementById("investBillOrg").value*1);
		//var sum = parseInt(document.getElementById("deduction").value) + parseInt(input.value*1);
	if(parseInt(sum*1)>parseInt(document.getElementById("totalClaim").value*1))
		{
		//alert("Deductions cannot be more than Total claim");
		var fr = partial(focusBox,input);
		jqueryAlertMsg('Alert',"Deductions cannot be more than Total claim",fr);
		input.value="";
       	return false;
		}
	else{
	document.getElementById("deductionOrg").value=sum;
	var finalAmt  = parseInt(document.getElementById("totalClaim").value*1) - parseInt(document.getElementById("deductionOrg").value*1);
	document.getElementById("cpdAprAmtOrg").value = finalAmt;
	}
	
}
function calculateFinalAmt(inputRole){
	var nabhAmt=0,finalAmt=0;
	
	if(inputRole!=null && inputRole=='CH')
		{
			if(document.forms[0].schemeType.value!=null && document.forms[0].schemeType.value=='CD201' && 
					document.forms[0].nabhFlag.value!=null && document.forms[0].nabhFlag.value=='Y' && 
					document.forms[0].phaseId.value!=null && document.forms[0].phaseId.value=='0')
				{
					var chEntAprAmt = document.getElementById("chEntAprAmt").value;
					if(parseInt(chEntAprAmt*1)<=parseInt(200000*1))
						{
							nabhAmt = parseInt(chEntAprAmt*1)*0.25;
							//document.getElementById("chNabhAmt").value=Math.round(nabhAmt);
						
							//finalAmt = parseInt(chEntAprAmt*1) + parseFloat(nabhAmt);
							finalAmt = parseInt(chEntAprAmt*1);
							document.getElementById("chAprAmt").value=Math.round(finalAmt);
						}
					else
						{
							var chEntAprAmt = document.getElementById("chEntAprAmt").value;
						//	document.getElementById("chNabhAmt").value=Math.round(nabhAmt);
						//	finalAmt = parseInt(chEntAprAmt*1) + parseFloat(nabhAmt);
							finalAmt = parseInt(chEntAprAmt*1);
							document.getElementById("chAprAmt").value=Math.round(finalAmt);
						}
				}
			else
				{
					var chEntAprAmt = document.getElementById("chEntAprAmt").value;
				//	document.getElementById("chNabhAmt").value=Math.round(nabhAmt);
					//finalAmt = parseInt(chEntAprAmt*1) + parseFloat(nabhAmt);
					finalAmt = parseInt(chEntAprAmt*1);
					document.getElementById("chAprAmt").value=Math.round(finalAmt);
				}
		}
	else if(inputRole!=null && inputRole=='CTD')
		{
			if(document.forms[0].schemeType.value!=null && 
					document.forms[0].schemeType.value=='CD201' && document.forms[0].nabhFlag.value!=null && 
					document.forms[0].nabhFlag.value=='Y' && document.forms[0].phaseId.value!=null && 
					document.forms[0].phaseId.value=='0')
				{
					var chEntAprAmt = document.getElementById("ctdAprAmt").value;
					/* if(parseInt(chEntAprAmt*1)<=parseInt(200000*1)) */
						{
							nabhAmt = parseInt(chEntAprAmt*1)*0.25;
							if(document.getElementById("ctdNabhAmt")!=null)
								document.getElementById("ctdNabhAmt").value=Math.round(nabhAmt);
					
							finalAmt = parseInt(chEntAprAmt*1) + parseFloat(nabhAmt);
							if(document.getElementById("ctdFinAprAmt")!=null)
								document.getElementById("ctdFinAprAmt").value=Math.round(finalAmt);
						}
					/* else{
						var chEntAprAmt = document.getElementById("ctdAprAmt").value;
						document.getElementById("ctdNabhAmt").value=Math.round(nabhAmt);
						finalAmt = parseInt(chEntAprAmt*1) + parseFloat(nabhAmt);
						document.getElementById("ctdFinAprAmt").value=Math.round(finalAmt);
					} */
				}
			else
				{
					var chEntAprAmt = document.getElementById("ctdAprAmt").value;
					if(document.getElementById("ctdNabhAmt")!=null)
						document.getElementById("ctdNabhAmt").value=Math.round(nabhAmt);
					
					finalAmt = parseInt(chEntAprAmt*1) + parseFloat(nabhAmt);
					if(document.getElementById("ctdFinAprAmt")!=null)
						document.getElementById("ctdFinAprAmt").value=Math.round(finalAmt);
				}
		}
	else if(inputRole!=null && inputRole=='EOCOM')
		{
			if(document.forms[0].schemeType.value!=null && document.forms[0].schemeType.value=='CD201' 
					&& document.forms[0].nabhFlag.value!=null && document.forms[0].nabhFlag.value=='Y' 
					&& document.forms[0].phaseId.value!=null && document.forms[0].phaseId.value=='0')
				{
					var eoComEntAprAmt = document.getElementById("eoComEntAprAmt").value;
					nabhAmt = parseInt(eoComEntAprAmt*1)*0.25;
					if(document.getElementById("eoComNabhAmt")!=null)
						document.getElementById("eoComNabhAmt").value=Math.round(nabhAmt);
					
		            finalAmt = parseInt(eoComEntAprAmt*1) + parseFloat(nabhAmt);
		            if(document.getElementById("eoComAprAmt")!=null)
						document.getElementById("eoComAprAmt").value=Math.round(finalAmt);
				}
			else
				{
					var eoComEntAprAmt = document.getElementById("eoComEntAprAmt").value;
					if(document.getElementById("eoComNabhAmt")!=null)
						document.getElementById("eoComNabhAmt").value=Math.round(nabhAmt);
					
					finalAmt = parseInt(eoComEntAprAmt*1) + parseFloat(nabhAmt);
					if(document.getElementById("eoComAprAmt")!=null)
						document.getElementById("eoComAprAmt").value=Math.round(finalAmt);
				}
		}
	else if(inputRole!=null && inputRole=='MEDCO')
		{
		if(document.forms[0].schemeType.value!=null && document.forms[0].schemeType.value=='CD201' && document.forms[0].nabhFlag.value!=null && document.forms[0].nabhFlag.value=='Y' && document.forms[0].phaseId.value!=null && document.forms[0].phaseId.value=='0'){
		var medcoEntAmount =  document.getElementById("billAmt").value;
		nabhAmt = parseInt(medcoEntAmount*1)*0.25;
		document.getElementById("medcoNabhAmount").value=Math.round(nabhAmt);
		}else{
			document.getElementById("medcoNabhAmount").value="0";
		}
		}
}
function fn_afterMsg(caseStatus){
	var autoCaseID=parent.parent.caseId;
	var autoAction = parent.parent.autoActionFlag;
	var module=parent.parent.module;
	
	 if(module=="cochlearAco"){
	        if(autoAction!=null && autoAction=="OnloadCaseOpen") 
	        	parent.parent.parent.fn_cochlearCasesForApprovalClaims(); 
	        else
	        	parent.parent.parent.fn_cochlearCasesForApprovalClaims();
	       
					}
	
	if(caseStatus=='<fmt:message key="EHF.claim.Status.ClaimKeptDiscuCTD"/>' || caseStatus=='<fmt:message key="EHF.claim.Status.ClaimKeptDiscuCH"/>')
		parent.fn_getCaseForDisscussion();
	else if(caseStatus=='<fmt:message key="EHF.claim.Status.DissClearCTD"/>' || caseStatus=='<fmt:message key="EHF.claim.Status.DissClearCH"/>')
	{

	if(module=="claim"){
		        if(autoAction!=null && autoAction=="OnloadCaseOpen")
		        	{
		        	
		        	if(caseStatus=='CD758' || caseStatus=='CD759' || caseStatus=='CD760')
		        		{
		        		
		        		parent.parent.fn_dialysisClaimCasesForApproval();
		        		}
		        	else
		        		{
		        	parent.parent.fn_casesForApprovalClaimNew();  
		        		}
		        	}
		        else    
				parent.fn_getCaseForApproval();
						}
	else if(module=="claimJournal"){
        if(autoAction!=null && autoAction=="OnloadCaseOpen")
        	parent.parent.fn_JournalistcasesForApprovalClaimNew();  
        else    
		parent.parent.fn_JournalistcasesForApprovalClaim();
				}
	else if(module=="claimDental"){
        if(autoAction!=null && autoAction=="OnloadCaseOpen")
        	parent.parent.fn_dentalCasesForApprovalClaimNew();  
        else    
		parent.parent.fn_dentalCasesForApprovalClaim();
				}
	else if(module=="claimDentalJournal"){
        if(autoAction!=null && autoAction=="OnloadCaseOpen")
        	parent.parent.fn_JrnlstDentalCases();  
        else    
		parent.parent.fn_JournalistcasesForApprovalClaim();
				}
	}
	else{
		if(module=="claim"){
		
	        if(autoAction!=null && autoAction=="OnloadCaseOpen")
	        	{
	        	if(caseStatus=='CD758' || caseStatus=='CD759' || caseStatus=='CD760')
	        		{
	        		
	        		
	        		parent.parent.parent.fn_dialysisClaimCasesForApproval();
	        		}
	        	else
	        		{
	        	parent.parent.fn_casesForApprovalClaimNew();  
	        		}
	        	}
	        else  
	        	{
			parent.fn_getCaseForApproval();
	        	}
					}
		else if(module=="claimJournal"){
			
	        if(autoAction!=null && autoAction=="OnloadCaseOpen")
	        	parent.parent.fn_JournalistcasesForApprovalClaimNew();  
	        else    
			parent.parent.fn_JournalistcasesForApprovalClaim();
					}
		 else if(module=="claimOrg"){
				
		        if(autoAction!=null && autoAction=="OnloadCaseOpen")
		        	parent.parent.fn_casesForApprovalOrgClaim();  
		        else    
				parent.parent.fn_casesForApprovalClaimNew();
						}
		else if(module=="claimDental"){
			
	        if(autoAction!=null && autoAction=="OnloadCaseOpen")
	        	parent.parent.fn_dentalCasesForApprovalClaimNew();  
	        else    
			parent.parent.fn_dentalCasesForApprovalClaim();
					}
		else if(module=="claimDentalJournal"){
	        if(autoAction!=null && autoAction=="OnloadCaseOpen")
	        	parent.parent.fn_JrnlstDentalCases();  
	        else    
			parent.parent.fn_JournalistcasesForApprovalClaim();
					}
		}
}

function check_maxLength(id,remarkLength)
{
	var name = document.getElementById(id).value;
	 if(name != null && name !='' && name.length >= remarkLength)
 	{
 	alert("Remarks length should not exceed " +remarkLength );
 	var fr = partial(subStringRemarks,id,remarkLength);
		}
}
function checkRemarks(name,remarkLength,id)
{
	var msg = chkSpecailChars(name,remarkLength);
	
	if(msg !=null && msg !='')
	{
		var fr = partial(subStringRemarks,id,3000);
		//jqueryAlertMsg('Preauth mandatory check',msg ,fr);
		alert(msg);
		subStringRemarks(id,3000);
		return;
	}
}

function subStringRemarks(id,remarkLength)
{
	if(document.getElementById(id).value != null && document.getElementById(id).value !='')
	document.getElementById(id).value = document.getElementById(id).value.substr(0, remarkLength-1);
	}
function showRevertRoles()
{
var showRoles=document.getElementById("paySent2").checked;

if(showRoles){
	getAllUsers();
document.getElementById("revertRoles").style.display="";
document.getElementById("allUsers").style.display="";
 document.getElementById("revertRemarks").style.display="";
document.getElementById("eoComRemark").style.display ="none"; 

document.getElementById("buttonsNor").style.display ="none";
document.getElementById("sendtoButtons").style.display =""; 


}
}
function hideRevertRoles()
{
document.getElementById("revertRoles").style.display="none";
document.getElementById("allUsers").style.display="none";
document.getElementById("revertRemarks").style.display="none";
document.getElementById("eoComRemark").style.display =""; 
document.getElementById("buttonsNor").style.display =""; 
document.getElementById("sendtoButtons").style.display ="none"; 


}
function getAllUsers()
{ 
	var sendBackToPPD='Y';
	var caseId = '<bean:write name="claimsFlowForm" property="caseId" />';	
			document.getElementById("allUsers").options.length=0;
			document.getElementById("allUsers").options[0]=new Option("-SELECT-","-1");
			//$('#allUsers').select2("val","-1");
			url='ClaimsFlow.do?actionFlag=getAllPanelUsers&sendBackToPPD='+sendBackToPPD+'&caseId='+caseId;	
			
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
							var result1=result;
							var result2=null;
							if(result1!=null)
								{
									result1 =result1.replace("[","");
									result1 =result1.replace("]","");
									result2=result1.split(", @");
								}
							else
								{
									document.getElementById("allUsers").options.length=0;
									document.getElementById("allUsers").options[0]=new Option("-SELECT-","-1");
									document.getElementById("allUsers").options[1]=new Option("-NO USERS FOUND-","-1");
								}
							if(result2.length>0)
								{
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
				};
				xmlhttp.open("POST", url, true);
				xmlhttp.send(null);
}
</script>
</head>
<bean:define id="claimDtls" name="claimsFlowForm" property="claimInfo" />
<c:set var="buttons" value="${claimDtls.buttonList}"/>
<body onload="fn_onloadDisabled();">
<div id="middleFrame_content">
<form action="/ClaimsFlow.do" method="post" name="claimsFlowForm">
<c:if test="${saveMsg ne null}">
<script>
var fr = partial(fn_afterMsg,'${nextStat}');
jqueryInfoMsg('Claim Information','${saveMsg}',fr);
</script>
</c:if>
<html:hidden property="caseId" name="claimsFlowForm"/>
<html:hidden name="claimsFlowForm" property="caseUnits" styleId="caseUnits" />
<html:hidden name="claimsFlowForm" property="caseUnitsPar" styleId="caseUnitsPar" />
<html:hidden property="newBornBaby" styleId="newBornBaby" name="claimsFlowForm"/>
<html:hidden property="caseStatus" name="claimsFlowForm"/>
<html:hidden property="roleId" name="claimsFlowForm"/>
<html:hidden property="totalClaim" name="claimsFlowForm"/>
<html:hidden property="packageAmt" name="claimsFlowForm"/>
<html:hidden property="claimInitAmt" name="claimsFlowForm"/>
<html:hidden property="onlineDschrgeDate" name="claimsFlowForm"/>
<html:hidden property="onlineAdmDate" name="claimsFlowForm"/>
<html:hidden property="onlineSurgDate" name="claimsFlowForm"/>
<html:hidden property="dtTime" name="claimsFlowForm"/>
<html:hidden property="deduction" name="claimsFlowForm"/>
<html:hidden property="cpdAprAmt" name="claimsFlowForm"/>
<html:hidden property="deductionOrg" name="claimsFlowForm"/>
<html:hidden property="cpdAprAmtOrg" name="claimsFlowForm"/>
<html:hidden property="dispErrInitBlock" name="claimsFlowForm"/>
<html:hidden property="errClaimPaid" name="claimsFlowForm"/>
<html:hidden property="errAmount" name="claimsFlowForm"/>
<html:hidden property="errClaimStatus" name="claimsFlowForm"/>
<html:hidden property="showEo" name="claimsFlowForm"/>
<html:hidden property="showEoCom" name="claimsFlowForm"/>
<html:hidden property="schemeType" name="claimsFlowForm"/>
<html:hidden property="nabhFlag" name="claimsFlowForm"/>
<html:hidden property="phaseId" name="claimsFlowForm"/>
<br>
<logic:notEmpty name="claimsFlowForm" property="msg">
<table width="100%" border="0" align="center">
<tbody>
<tr>
<td class="labelheading1 tbcellCss" style="height:340px;text-align:center;vertical-align:center;" colspan="6">
<b><bean:write name="claimsFlowForm" property="msg"/></b>
</td>
</tr>
</tbody>
</table>
</logic:notEmpty>
<logic:empty name="claimsFlowForm" property="msg">
<!-- Modal for patient details  -->  
<div class="modal fade" id="viewDtlsID"> 
 <div class="modal-dialog" id="modal-lgx">
   <div class="modal-content">
      <div class="modal-body">
	  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	  <iframe  id="complaintFrame" width="100%" height="250px" frameborder="no" scrolling="yes" > </iframe>
	  </div>
	  <div class="modal-footer">
      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	  </div>
	  </div><!-- /.modal-content --> 
	</div><!-- /.modal-dialog --> 
</div><!-- /.modal --> 
<div id="imageID"> 
 <a id="patDtlsImage" href="#viewDtlsID" data-toggle="modal" style=cursor:hand; title="Click to View Patient Details" onclick="javascript:fn_getPatDetails()">
 <span class="glyphicon glyphicon-plus"></span><span class="glyphicon glyphicon-user"></span>
 <br>Patient Details
 </a>
</div>

<table width="95%" style="margin:0 auto">

<tr>
<td>
<table border="0" width="100%" >
<tr class="tbheader">
<td id="topSlide" width="5%">
<img id="menuImage" src="images/rightLeftArrow.jpg" title="Maximize/Minimize" style=cursor:hand; width="25" height="25" alt="Hide Menu" align="top" onclick="javascript:fn_maxmizeRight()" ></img>
</td>
<td  colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong><fmt:message key="EHF.title.ClaimDtls"/></strong></td>
<td id="menuSlide" width="5%"> 
<!--<img id="topImage" src="images/updownArrow.jpg" width="30" height="20" style=cursor:hand; title="Maximize/Minimize" alt="Maximize" align="top" onclick="javascript:fn_maxmizeTop()" ></img>-->
<img id="topImage" src="images/back.jpg" width="30" height="20" style=cursor:hand; title="Back" alt="Back" align="top" onclick="javascript:fn_maxmizeTop()" ></img>
</td>
</tr></table>

<table width="100%">
<tr>
<td width="50%" align="left" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.PreAuthDate"/>:&nbsp;&nbsp;<b><bean:write name="claimsFlowForm" property="preauthDate" /></b></td>
<td width="50%" align="left" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.PreAuthApprvDate"/>:&nbsp;&nbsp;<b><bean:write name="claimsFlowForm" property="preAuthApprvDate" /></b></td>
</tr>
<tr>
<td width="40%" align="left" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.SubmitDate"/>:&nbsp;&nbsp;<b><bean:write name="claimsFlowForm" property="dtTime" /></b></td>
<td width="50%" align="left" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.PackAmt"/>:&nbsp;&nbsp;<b><bean:write name="claimsFlowForm" property="packageAmt" /></b></td>


<!-- <img id="cal1" border='0' src='images/calend.gif'  alt="Calendar" onClick="CalenderWindowOpen('billDt','430','250')"></img></td> -->
</tr>
<logic:notEqual property="medicalSurgFlag" value="IPM" name="claimsFlowForm">
<tr>
<td width="40%" align="left" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.ConsumableAmt"/>:&nbsp;&nbsp;<b><bean:write name="claimsFlowForm" property="totalConsumableAmount" /></b></td>
<td width="50%" align="left" class="labelheading1 tbcellCss"></td>
</tr>
</logic:notEqual>
<logic:equal property="medicalSurgFlag" value="IPM" name="claimsFlowForm">
<tr>
<td width="40%" align="left" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.ConsumableAmt"/>:&nbsp;&nbsp;<b><bean:write name="claimsFlowForm" property="totalConsumableAmount" /></b></td>
<td width="50%" align="left" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DrugAmt"/>:&nbsp;&nbsp;<b><bean:write name="claimsFlowForm" property="totalDrugAmount" /></b></td>
</tr>
</logic:equal>
<tr>
<td width="50%" align="left" class="labelheading1 tbcellCss">Penalty Amount:&nbsp;&nbsp;<b><bean:write name="claimsFlowForm" property="penaltyAmount" /></b></td>
<td width="50%" align="left"  class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.ClaimAmt"/> &nbsp;&nbsp;<b><bean:write name="claimsFlowForm" property="claimInitAmt" /></b></td>

</tr>
<c:if test="${pendFlag ne 'Y'}">
<tr >
<td width="50%" align="left"  class="labelheading1 tbcellCss"><fmt:message key="EHF.Claims.billAmt"/><font color="red">*</font>:&nbsp;&nbsp;<html:text name="claimsFlowForm" property="billAmt" styleId="billAmt" maxlength="7" onblur="calculateFinalAmt('MEDCO')" onchange="validateNumber('Bill Amount',this)" title="Enter Bill Amount" style="width:50%"/></td>
<td width="50%" align="left"  class="labelheading1 tbcellCss"><fmt:message key="EHF.Claims.billDate"/><font color="red">*</font>:&nbsp;&nbsp;<b><html:text name="claimsFlowForm" property="billDt"  styleId="billDt" title="Enter Bill Date" onchange="validateTodayDate('Bill Date',this)" onkeydown="validateBackSpace(event)" readonly="true" style="width:50%"/></b></td>
<%-- <td width="30%" align="left" class="labelheading1 tbcellCss">NABH Amount :&nbsp;&nbsp;<b><html:text name="claimsFlowForm" property="medcoNabhAmount" styleId="medcoNabhAmount" maxlength="7"  title="NABH Amount" style="width:20%" disabled="true"/></b></td> --%>
</tr>
</c:if>
<c:if test="${pendFlag eq 'Y'}">
	<td width="50%" align="left"  class="labelheading1 tbcellCss"><fmt:message key="EHF.Claims.billAmt"/><font color="red">*</font>:&nbsp;&nbsp;<html:text name="claimsFlowForm" property="billAmt" styleId="billAmt" maxlength="7" onblur="calculateFinalAmt('MEDCO')" onchange="validateNumber('Bill Amount',this)" title="Enter Bill Amount" style="width:50%" readonly="true"/></td>
	<td width="50%" align="left"  class="labelheading1 tbcellCss"><fmt:message key="EHF.Claims.billDate"/><font color="red">*</font>:&nbsp;&nbsp;<b><html:text name="claimsFlowForm" property="billDt"  styleId="billDt" title="Enter Bill Date" onchange="validateTodayDate('Bill Date',this)" onkeydown="validateBackSpace(event)" style="width:50%" readonly="readonly"/></b></td>
</c:if>

<tr>
<td colspan="3"  class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer6"/><font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
<html:textarea   name="claimsFlowForm" property="medcoRemark"  styleId="medcoRemark" style="overflow-y:auto;WIDTH:80%;height:3em" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Medco Remarks')" title="Enter Remarks"/>
</td>
</tr>
<tr ><td colspan="4"><font color="red"><fmt:message key="EHF.Claim.MedcoNote"/> <br/><fmt:message key="EHF.Claim.NABH"/></font>
</td></tr>
</table>
<c:if test="${dentalSurg eq 'Y' }">
	<tr>
		<td>
			<!-- Treatment Protocol shown Only for Dental case -->
					 <logic:present name="claimsFlowForm" property="lstPreauthVO">
					 
					 
					<table width="100%">
						<tr>
							<td colspan="10" align="left" class="tbheader"><strong>Treatment Protocol 
									</strong>
							</td>
						</tr>
						<tr>
							<td width="10%" style="word-wrap:break-word;" class="labelheading1 tbcellCss"><b>Category Name</b></td>
							<td width="13%" class="labelheading1 tbcellCss"><b>ICD Category Name</b></td>
							<td width="17%" class="labelheading1 tbcellCss"><b>Procedure Name</b></td>
							<td width="5%" class="labelheading1 tbcellCss"><p id="unitsSelectID"><b>Preauth Approved Units</b></p></td>
							<!-- <td width="5%" class="labelheading1 tbcellCss" align="center"><p id="unitsSelectIDNew"><b><font color="red">Last User Updated Units*</font></b></p></td> -->
							<td width="5%" class="labelheading1 tbcellCss"><b>Medco Updated Units</b></td>
							<c:if test="${isMedcoFlg eq 'Y'}">
							<td width="5%" class="labelheading1 tbcellCss"><b>CTD Approved Units</b></td>
							 <td width="5%" class="labelheading1 tbcellCss"><b>CH Approved Units</b></td>
							</c:if>
							<c:if test="${ctdViewFlag eq 'Y'}"> 
							<td width="5%" class="labelheading1 tbcellCss"><b>CTD Approved Units</b></td>
							</c:if>
							<c:if test="${chViewFlag eq 'Y'}"> 
							<td width="5%" class="labelheading1 tbcellCss"><b>CTD Approved Units</b></td>
							<td width="5%" class="labelheading1 tbcellCss"><b>CH Approved Units</b></td>
							</c:if>
							<td width="15%" class="labelheading1 tbcellCss" ><b>Special Investigations</b> </td>
							<td width="12%" class="labelheading1 tbcellCss"><b><font color="red">Teeth No/Quadrant No*</font></b></td>
							<!--<td width="15%" class="labelheading1 tbcellCss"><b>Remarks</b></td>  -->
							<td width="10%" class="labelheading1 tbcellCss"><b>Treating Doctor name</b></td>
							<c:if test="${viewProcAmts eq 'Y'}"> 
								<td width="7%" class="labelheading1 tbcellCss"><b>Package Amount</b></td>
							</c:if>
						</tr>
						<bean:size id="caseList" name="claimsFlowForm" property="lstPreauthVO"/>
						<logic:greaterThan value="0" name="caseList">
						<logic:iterate id="results" name="claimsFlowForm" property="lstPreauthVO" indexId="index" >
						<tr id="splInvetsDataTable<%=index+1%>" style="display:true">
							<td  class="tbcellBorder" style="word-wrap:break-word;"><bean:write name="results" property="asriCatName" />(<bean:write name="results" property="catId" />)</td>
							<td  class="tbcellBorder" style="word-wrap:break-word;"><bean:write name="results" property="catName" />(<bean:write name="results" property="icdCatCode" />)</td>
							<td  class="tbcellBorder" style="word-wrap:break-word;"><bean:write name="results" property="procName" />(<bean:write name="results" property="icdProcCode" />)</td>
							<td align="center" class="labelheading1 tbcellCss" style="word-wrap:break-word;">
								<logic:notEqual name="results" property="opProcUnits" value="-1">
									<%-- <bean:write name="results" property="opProcUnits" /> --%>
									
										${results.opProcUnits}
								</logic:notEqual>
								<logic:equal name="results" property="opProcUnits" value="-1">
									-NA-
								</logic:equal>
							</td>
							
							<%-- <td align="center" class="labelheading1 tbcellCss" style="word-wrap:break-word;">
									${results.surgProcUnits}
							</td> --%>
							<td align="center" class="labelheading1 tbcellCss" style="word-wrap:break-word;">
							<logic:notEqual name="results" property="medcoProcUnits" value="-1">
								${results.medcoProcUnits}
							</logic:notEqual>
							<logic:equal name="results" property="medcoProcUnits" value="">
								${results.surgProcUnits}
							</logic:equal>
							</td>
							<!-- Added for Medco View -->
							<c:if test="${isMedcoFlg eq 'Y'}">
							<td align="center" class="labelheading1 tbcellCss" style="word-wrap:break-word;">
								<logic:notEqual name="results" property="ctdProcUnits" value="-1">${results.ctdProcUnits}</logic:notEqual>
								<logic:equal name="results" property="ctdProcUnits" value="">	-NA-</logic:equal>
							</td>
							<td align="center" class="labelheading1 tbcellCss" style="word-wrap:break-word;">
								<logic:notEqual name="results" property="chProcUnits" value="-1">${results.chProcUnits}</logic:notEqual>
								<logic:equal name="results" property="chProcUnits" value="">	-NA-</logic:equal>
							</td>
							
							</c:if>
							
							
							<!-- Added for CTD Approval -->
							
							 <c:if test="${ctdViewFlag eq 'Y'}"> 
							<td  class="tbcellBorder">
							<select style="width:100%" id="ctdProcUnits${index}" name="${results.seqNo}" title="Please Select The Number of Units to which treatment Done">
							<option value="-1">Select</option>
							<c:forEach begin="0" end="${results.opProcUnits}" varStatus="stats">
							<option value="${stats.index}">${stats.index}</option>
							</c:forEach>
							</select>
							</td>
							</c:if> 
							
							<!-- Added for CH Approval -->
							<c:if test="${chViewFlag eq 'Y'}">
							<td align="center" class="labelheading1 tbcellCss" style="word-wrap:break-word;">
							<logic:notEqual name="results" property="ctdProcUnits" value="-1">
								${results.ctdProcUnits}
							</logic:notEqual>
							<logic:equal name="results" property="ctdProcUnits" value="-1">
								-NA-
							</logic:equal>
							</td>
							<td  class="tbcellBorder">
							<select style="width:100%" id="chProcUnits${index}" name="${results.seqNo}" title="Please Select The Number of Units to which treatment Done">
							<option value="-1">Select</option>
							<c:forEach begin="0" end="${results.opProcUnits}" varStatus="stats">
							<option value="${stats.index}">${stats.index}</option>
							</c:forEach>
							</select>
							</td>
							</c:if>
							
							<bean:size id="splattachList" name="results" property="lstSplInvet" />
							<c:set var="splInvstCount" value="1" />
							<logic:greaterThan value="0" name="splattachList" >
								<td  class="tbcellBorder" style="word-wrap:break-word;">
									<logic:iterate id="results1" name="results" property="lstSplInvet" indexId="index1" >
									<a href="javascript:javascript:fn_openAtachment('<bean:write name="results1" property="filePath" />')" >
									<bean:write name="results1" property="filename" /></a>
									<c:if test="${fn:length(results.lstSplInvet) ne splInvstCount}">
									,
									</c:if>
									<c:set var="splInvstCount" value="${splInvstCount+1 }" />
									
									
									</logic:iterate>
								</td>
							</logic:greaterThan> 
							<logic:equal value="0" name="splattachList">
							<td  style="word-wrap:break-word;" class="tbcellBorder">&nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;    -- &nbsp;</td>
							</logic:equal>
							<!--Sneha: removed bean:write to get , wherever &#44; is present  -->
							<%-- <td   style="word-wrap:break-word;" class="tbcellBorder">&nbsp;&nbsp;${results.investRemarks}</td> --%>
							<td   style="word-wrap:break-word;" class="tbcellBorder">
								<textarea rows="3"  disabled="disabled" maxlength="3000" id="unitstxtArea${index}" name="unitstxtArea${results.seqNo}" >${results.toothedUnits}</textarea>
							</td>
							
							<td  class="tbcellBorder" style="word-wrap:break-word;"><bean:write name="results" property="docName" /></td> 
							<c:if test="${viewProcAmts eq 'Y'}">
							<td  class="tbcellBorder"  style="word-wrap:break-word;"> <bean:write name="results" property="amount" />
							
							 <%-- <input type="button" class="btn but" id="getPriceBtn${index}" value="Get Price" onclick="javascript:fn_getPkgAmnt('<bean:write name="results" property="catId" />','<bean:write name="results" property="icdCatCode" />','<bean:write name="results" property="icdProcCode" />','${index}','${results.seqNo}','<bean:write name="results" property="procName" />') "/> --%>  
							</td>
								
							</c:if>
						</tr>
						</logic:iterate>
						</logic:greaterThan>
							<c:if test="${totalPriceBtn eq 'Y'}">
						<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td align="center" colspan="2">
						<button class="but"  type="button"  value="Total Price" onclick="javascript:fn_partialsave();">Total Price</button>
						</td>
						</tr>
						</c:if>
					</table>	
					</logic:present> 
		</td>
	</tr>
</c:if>	
<c:set var="investList" value="${fn:length(claimsFlowForm.investDetails)}"/>
<logic:greaterThan value="0" name="investList">
	<tr><td>
		<table class="tbheader" >
			<tr><td align="center"><b>&nbsp;&nbsp;&nbsp;<fmt:message key="EHF.Title.CosumableDetails"/></b></td></tr>
		</table>
	</td></tr>
	<tr><td align="center">
		<table width="100%">
			<thead class="tbheader">
				<th> CONSUMABLE TYPE</th>
				<th> COSUMABLE NAME </th>
				<th> QUANTITY </th>				
				<c:if test="${UserRole ne 'GP2'}">
					<th> UNIT AMOUNT</th>
					<th> TOTAL AMOUNT</th>			
				</c:if>
				<th> ATTACHMENTS </th>	
			</thead>
			<logic:iterate name="claimsFlowForm" property="investDetails" id="investDtls">
				<tr>
					<td class="tbcellBorder"> <bean:write name="investDtls" property="WFTYPE" /></td>
					<td class="tbcellBorder"><bean:write name="investDtls" property="NAME"/></td>
					<td class="tbcellBorder"><bean:write name="investDtls" property="COUNT"/></td>
					<c:if test="${UserRole ne 'GP2'}">
						<td class="tbcellBorder"><bean:write name="investDtls" property="UNITAMOUNT"/></td>
						<td class="tbcellBorder"><bean:write name="investDtls" property="AMOUNT"/></td>
					</c:if>
					<logic:notEmpty  name="investDtls" property="ATTACH">
						<td class="tbcellBorder"><a href="javascript:fn_openAtachmentMedical('<bean:write name="investDtls" property="ATTACH" />');" >View</a></td>
					</logic:notEmpty>
					<logic:empty  name="investDtls" property="ATTACH">
					<td class="tbcellBorder"><b>No Records</b></td>
					</logic:empty>
				</tr>
			</logic:iterate>
		</table>
	</td></tr>
</logic:greaterThan>

<c:set var="drugList" value="${fn:length(claimsFlowForm.drugDetails)}"/>
<logic:greaterThan value="0" name="drugList">
	<tr><td>
		<table class="tbheader" >
			<tr><td align="center"><b>&nbsp;&nbsp;&nbsp;<fmt:message key="EHF.Title.DrugDetails"/></b></td></tr>
		</table>
	</td></tr>
	<tr><td align="center">
		<table width="100%">
			<tr>
								<td width="15%" class="labelheading1 tbcellCss"><b>Input type</b></td>
								<td width="15%" class="labelheading1 tbcellCss"><b>Code</b></td>
								<td width="15%" class="labelheading1 tbcellCss"><b>Amount</b></td>
								<td width="15%" class="labelheading1 tbcellCss"><b>Date</b></td>
								<td width="10%" class="labelheading1 tbcellCss"><b>Attachment</b></td>
		</tr>
			<logic:iterate name="claimsFlowForm" property="drugDetails" id="drugList">
				<tr>
								<td width="13%" class="tbcellBorder">Pharmacy</td>   
								<td width="13%" class="tbcellBorder">Drugs and Disposables</td>
								<td width="13%" class="tbcellBorder"><bean:write name="drugList" property="DRUGAMOUNT" /></td>
								<td width="13%" class="tbcellBorder"><bean:write name="drugList" property="CRTDT" /></td>
								<logic:notEmpty  name="drugList" property="ATTACHPATH">
								<td class="tbcellBorder"><a href="javascript:fn_openAtachmentMedical('<bean:write name="drugList" property="ATTACHPATH" />');" >View</a></td>
								</logic:notEmpty>
								<logic:empty  name="drugList" property="ATTACHPATH">
								<td class="tbcellBorder">No Records</td>
								</logic:empty>
				</tr>
			</logic:iterate>
		</table>
	</td></tr>
</logic:greaterThan>
<logic:equal value="Yes" name="claimsFlowForm" property="showCex">
<tr><td>
<table class="tbheader" >
<tr><td align="center"><b>&nbsp;&nbsp;&nbsp;<fmt:message key="EHF.Title.NonTech"/></b></td></tr>
</table></td></tr>
<tr><td>
<table width="100%">
<tr><td width="75%"  class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.NameCaseSheet"/>&nbsp;<font color="red">*</font></td>
<td  class="tbcellBorder"><html:radio name="claimsFlowForm" title="Yes" property="nameCheck" value="Y" styleId="nameCheck" /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="nameCheck" title="No" value="N" styleId="nameCheck" /><fmt:message key="EHF.Claims.No"/>
</td></tr>
<tr><td width="75%"  class="labelheading1 tbcellCss" ><fmt:message key="EHF.Claim.GenderCaseSheet"/>&nbsp;<font color="red">*</font></td><td class="tbcellBorder">
<html:radio name="claimsFlowForm" property="genderCheck" title="Yes" value="Y" styleId="genderCheck"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="genderCheck" title="No" value="N" styleId="genderCheck" /><fmt:message key="EHF.Claims.No"/>
</td></tr>
<tr><td width="75%"  class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.BenPhoto"/>&nbsp;<font color="red">*</font></td><td class="tbcellBorder">
<html:radio name="claimsFlowForm" property="benPhotoCheck" title="Yes" value="Y" styleId="benPhotoCheck"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="benPhotoCheck" title="No" value="N" styleId="benPhotoCheck" /><fmt:message key="EHF.Claims.No"/>
</td></tr></table>
<table width="100%">
<tr><td colspan="3" align="left"><b><fmt:message key="EHF.Claim.DateVerif"/></b></td></tr>
<tr><td colspan="3">
<table width="100%">
<tr><td width="25%" class="labelheading1 tbcellCss"></td><td width="25%" class="labelheading1 tbcellCss">Online</td><td width="25%" class="labelheading1 tbcellCss">Case Sheet</td><td width="25%"></td></tr>
<tr><td width="25%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.AdmDate"/>&nbsp;<font color="red">*</font></td><td width="25%" class="tbcellBorder"><b><bean:write name="claimsFlowForm" property="onlineAdmDate" /></b></td><td width="25%" class="tbcellBorder"><html:text name="claimsFlowForm" property="caseStAdmDt"  styleId="caseStAdmDt" title="Enter Admission Date" onchange="validateDate('Admission Date',this)" onkeydown="validateBackSpace(event)" readonly="true"/>
<!--<img id="cal2" border='0' src='images/calend.gif'  alt="Calendar" onClick="CalenderWindowOpen('caseStAdmDt','430','250')" ></img> --></td>
<td width="25%" class="tbcellBorder"><html:radio name="claimsFlowForm" property="admDtCheck" title="Yes" value="Y" styleId="admDtCheck" disabled="true" /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="admDtCheck" value="N" title="No" styleId="admDtCheck" disabled="true"/><fmt:message key="EHF.Claims.No"/></td></tr>
<tr><td width="25%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.SurgDate"/>&nbsp;<font color="red">*</font></td><td width="25%"  class="tbcellBorder"><b><bean:write name="claimsFlowForm" property="onlineSurgDate" /></b></td><td width="25%"  class="tbcellBorder"><html:text name="claimsFlowForm" property="caseStSurgDt"  styleId="caseStSurgDt" title="Enter Surgery/Therapy Date" onchange="validateDate('Surgery/Therapy Date',this)" onkeydown="validateBackSpace(event)" readonly="true"/>
<!-- <img  id="cal3" border='0' src='images/calend.gif' alt="Calendar" onClick="CalenderWindowOpen('caseStSurgDt','430','250')" ></img> --></td>
<td width="25%" class="tbcellBorder"><html:radio name="claimsFlowForm" property="surgDtCheck" value="Y" title="Yes" styleId="surgDtCheck"  disabled="true"/><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="surgDtCheck" value="N" title="No" styleId="surgDtCheck" disabled="true"/><fmt:message key="EHF.Claims.No"/></td></tr>
<tr><td width="25%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DisDate"/>&nbsp;<font color="red">*</font></td><td width="25%" class="tbcellBorder"><b><bean:write name="claimsFlowForm" property="onlineDschrgeDate" /></b></td><td width="25%" class="tbcellBorder"><html:text name="claimsFlowForm" property="caseStDischrgDt"  styleId="caseStDischrgDt" title="Enter Discharge/Death Date" onchange="validateDate('Discharge/Death Date',this)" onkeydown="validateBackSpace(event)" readonly="true"/>
<!-- <img id="cal4" border='0' src='images/calend.gif' alt="Calendar" onClick="CalenderWindowOpen('caseStDischrgDt','430','250')" ></img></td> -->
<td width="25%" class="tbcellBorder"><html:radio name="claimsFlowForm" property="dischargeDtCheck" value="Y" title="Yes" styleId="dischargeDtCheck" disabled="true"/><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="dischargeDtCheck" value="N" title="No" styleId="dischargeDtCheck" disabled="true"/><fmt:message key="EHF.Claims.No"/></td></tr>
</table>
</td></tr>
</table>
<table width="100%">
<tr><td colspan="3" align="left"><b><fmt:message key="EHF.Claim.DocuVerf"/></b></td></tr>
<tr><td colspan="3">
<table width="100%">
<tr><td width="75%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer1"/>&nbsp;<font color="red">*</font></td><td  class="tbcellBorder">
<html:radio name="claimsFlowForm" property="docVer1" value="Y" title="Yes" styleId="docVer1"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="docVer1" value="N" title="No" styleId="docVer1" /><fmt:message key="EHF.Claims.No"/>
</td></tr>
<tr><td width="75%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer2"/>&nbsp;<font color="red">*</font></td><td  class="tbcellBorder">
<html:radio name="claimsFlowForm" property="docVer2" value="Y" title="Yes" styleId="docVer2"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="docVer2" value="N" title="No" styleId="docVer2" /><fmt:message key="EHF.Claims.No"/>
</td></tr>
<!-- <tr><td width="75%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer3"/>&nbsp;<font color="red">*</font></td><td  class="tbcellBorder">
<html:radio name="claimsFlowForm" property="docVer3" value="Y" title="Yes" styleId="docVer3"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="docVer3" value="N" title="No" styleId="docVer3" /><fmt:message key="EHF.Claims.No"/>
</td></tr>
 -->
<tr><td width="75%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer4"/>&nbsp;<font color="red">*</font></td><td  class="tbcellBorder">
<html:radio name="claimsFlowForm" property="docVer4" value="Y" title="Yes" styleId="docVer4"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="docVer4" value="N" title="No" styleId="docVer4" /><fmt:message key="EHF.Claims.No"/>
</td></tr>
<tr><td width="75%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer5"/>&nbsp;<font color="red">*</font></td><td  class="tbcellBorder">
<html:radio name="claimsFlowForm" property="docVer5" value="Y" title="Yes" styleId="docVer5"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="docVer5" value="N" title="No" styleId="docVer5" /><fmt:message key="EHF.Claims.No"/>
</td></tr>
<tr>
<!-- <td colspan="2" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claims.FAprAmt"/>&nbsp;<font color="red">*</font>:&nbsp;&nbsp;
<html:text name="claimsFlowForm" property="cexAprAmt" styleId="cexAprAmt" maxlength="7" onchange="validateNumber('Final Approve Amount',this)" title="Enter Final Approve Amount"/></td>
 -->
</tr>

<tr ><td colspan="3" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer6"/>&nbsp;<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
<html:textarea name="claimsFlowForm" property="cexRemark"  styleId="cexRemark" style="overflow-y:auto;WIDTH:85%;height:3em"  onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Enter Remarks"/>
</td></tr>
</table>
</td></tr>
</table>
</td></tr>
</logic:equal>

<logic:equal value="Yes" name="claimsFlowForm" property="showCpd">
<tr><td>
<table class="tbheader">
<tr><td align="center"><b>&nbsp;&nbsp;&nbsp;<fmt:message key="EHF.Title.Tecnical"/></b></td></tr>
</table></td></tr>
<tr><td>
<table width="100%" style="table-layout: fixed;"  >
<tr><td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claim.techCheck1"/></b></td>
<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claim.techCheck2"/></b></td>
<!-- <td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claim.techCheck3"/></b></td> -->
<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claim.techCheck4"/></b></td>
<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claim.techCheck5"/></b></td>
<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claim.techCheck6"/></b></td>
</tr>
<tr><td width="16%"  class="tbcellBorder"><html:text name="claimsFlowForm" property="totalClaim" styleId="totalClaim" style="width:90%" maxlength="7" onchange="validateNumber('Total claim',this)" title="Enter Total claim" disabled="true"/></td>
<td width="16%"  class="tbcellBorder"><html:text name="claimsFlowForm" property="deduction" styleId="deduction" style="width:90%" maxlength="6" onchange="validateNumber('Deduction Recommended',this)" title="Enter Deduction Recommended" disabled="true"/></td>
<!-- <td width="16%"  class="tbcellBorder"><html:text name="claimsFlowForm" property="stay" styleId="stay" style="width:90%" maxlength="6"  onblur="addToDedcution(stay)" onchange="validateNumber('Stay',this)" title="Enter Stay"/></td>-->
<td width="16%"  class="tbcellBorder"><html:text name="claimsFlowForm" property="inputs" styleId="inputs" style="width:90%" maxlength="6" onblur="addToDedcution(inputs)" onchange="validateNumber('Inputs',this)" title="Enter Inputs"/></td>
<td width="16%"  class="tbcellBorder"><html:text name="claimsFlowForm" property="profFee" styleId="profFee" style="width:90%" maxlength="6" onblur="addToDedcution(profFee)" onchange="validateNumber('Professional fee bill',this)" title="Enter Professional fee bill"/></td>
<td width="16%"  class="tbcellBorder"><html:text name="claimsFlowForm" property="investBill" styleId="investBill" style="width:90%" maxlength="6" onchange="validateNumber('Investigation Bill',this)" onblur="addToDedcution(investBill)" title="Enter Investigation Bill"/></td>
</tr>
<tr><td width="16%">&nbsp;</td>
<td width="16%">&nbsp;</td>
<!-- <td width="16%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claim.StayRemark"/></b></td> -->
<td width="16%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claim.inputRemark"/></b></td>
<td width="16%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claim.ProfRemark"/></b></td>
<td width="16%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claim.InvestRemark"/></b></td>
</tr>
<tr><td width="16%">&nbsp;</td>
<td width="16%" style="word-wrap:break-word;">&nbsp;</td>
<!-- <td width="16%" style="word-wrap:break-word;" class="tbcellBorder"><html:textarea   name="claimsFlowForm" property="stayRemark"   styleId="stayRemark" style="WIDTH:90%;height:3em;overflow-y:auto" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Stay Remarks')" title="Enter Remarks if amount reduced"/></td>-->
<td width="16%" style="word-wrap:break-word;" class="tbcellBorder"><html:textarea   name="claimsFlowForm" property="inputsRmrk"   styleId="inputsRmrk" style="WIDTH:90%;height:3em;overflow-y:auto" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Input Remarks')" title="Enter Remarks if amount reduced"/></td>
<td width="16%" style="word-wrap:break-word;" class="tbcellBorder"><html:textarea   name="claimsFlowForm" property="profFeeRmrk"   styleId="profFeeRmrk" style="WIDTH:90%;height:3em;overflow-y:auto" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Professional Fee Remarks')" title="Enter Remarks  if amount reduced"/></td>
<td width="16%" style="word-wrap:break-word;" class="tbcellBorder"><html:textarea   name="claimsFlowForm" property="investBillRmrk"   styleId="investBillRmrk" style="WIDTH:90%;height:3em" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Invest Bill Remarks')" title="Enter Remarks if amount reduced"/></td>
</tr>
</table></td></tr>  
<tr><td>
<table width="100%"><tr>
<td width="30%" align="left"  class="labelheading1 tbcellCss"><fmt:message key="EHF.Claims.FAprAmt"/>&nbsp;<font color="red">*</font>:&nbsp;&nbsp;<html:text name="claimsFlowForm" property="cpdAprAmt" styleId="cpdAprAmt" disabled="true" maxlength="7" onchange="validateNumber('Final Approve Amount',this)" title="Enter Final Approve Amount"/></td>
</tr>
</table>
</td></tr>
<tr><td>
<table width="100%">
<tr><td width="75%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.techCheck7"/>&nbsp;<font color="red">*</font></td><td  class="tbcellBorder">
<html:radio name="claimsFlowForm" property="techCheck1" value="Y" title="Yes" styleId="techCheck1"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="techCheck1" value="N" title="No" styleId="techCheck1" /><fmt:message key="EHF.Claims.No"/>
</td></tr>
<tr><td width="75%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.techCheck8"/>&nbsp;<font color="red">*</font></td><td  class="tbcellBorder">
<html:radio name="claimsFlowForm" property="techCheck2" value="Y" title="Yes" styleId="techCheck2"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="techCheck2" value="N" title="No" styleId="techCheck2" /><fmt:message key="EHF.Claims.No"/>
</td></tr>
<tr><td width="75%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.techCheck9"/>&nbsp;<font color="red">*</font></td><td  class="tbcellBorder">
<html:radio name="claimsFlowForm" property="techCheck3" value="Y" title="Yes" styleId="techCheck3"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="techCheck3" value="N" title="No" styleId="techCheck3" /><fmt:message key="EHF.Claims.No"/>
</td></tr>
<tr><td width="75%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer3"/>&nbsp;<font color="red">*</font></td><td  class="tbcellBorder">
<html:radio name="claimsFlowForm" property="techCheck4" value="Y" title="Yes" styleId="techCheck4"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="techCheck4" value="N" title="No" styleId="techCheck4" /><fmt:message key="EHF.Claims.No"/>
</td></tr>
<tr><td colspan="3" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer6"/>&nbsp;<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
<html:textarea   name="claimsFlowForm"  property="claimPanelRemark"  styleId="claimPanelRemark" style="WIDTH:80%;height:3em;overflow-y:auto" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Enter Remarks"/>
</td></tr>
</table></td></tr>
</logic:equal>
<logic:equal value="Yes" name="claimsFlowForm" property="showCpdOrg">
<tr><td>
<table class="tbheader">
<tr><td align="center"><b>&nbsp;&nbsp;&nbsp;<fmt:message key="EHF.Title.Tecnical"/></b></td></tr>
</table></td></tr>
<tr><td>
<table width="100%" style="table-layout: fixed;"  >
<tr><td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claim.techCheck1"/></b></td>
<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claim.techCheck2"/></b></td>
<!-- <td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claim.techCheck3"/></b></td> -->
<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claim.techCheck4"/></b></td>
<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claim.techCheck5"/></b></td>
<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claim.techCheck6"/></b></td>
</tr>
<tr><td width="16%"  class="tbcellBorder"><html:text name="claimsFlowForm" property="totalClaim" styleId="totalClaim" style="width:90%" maxlength="7" onchange="validateNumber('Total claim',this)" title="Enter Total claim" disabled="true"/></td>
<td width="16%"  class="tbcellBorder"><html:text name="claimsFlowForm" property="deductionOrg" styleId="deductionOrg" style="width:90%" maxlength="7" onchange="validateNumber('Deduction Recommended',this)" title="Enter Deduction Recommended" disabled="true"/></td>
<!-- <td width="16%"  class="tbcellBorder"><html:text name="claimsFlowForm" property="stay" styleId="stay" style="width:90%" maxlength="7"  onblur="addToDedcution(stay)" onchange="validateNumber('Stay',this)" title="Enter Stay"/></td>-->
<td width="16%"  class="tbcellBorder"><html:text name="claimsFlowForm" property="inputsOrg" styleId="inputsOrg" style="width:90%" maxlength="7" onblur="addToDedcutionOrg(inputs)" onchange="validateNumber('Inputs',this)" title="Enter Inputs"/></td>
<td width="16%"  class="tbcellBorder"><html:text name="claimsFlowForm" property="profFeeOrg" styleId="profFeeOrg" style="width:90%" maxlength="7" onblur="addToDedcutionOrg(profFee)" onchange="validateNumber('Professional fee bill',this)" title="Enter Professional fee bill"/></td>
<td width="16%"  class="tbcellBorder"><html:text name="claimsFlowForm" property="investBillOrg" styleId="investBillOrg" style="width:90%" maxlength="7" onchange="validateNumber('Investigation Bill',this)" onblur="addToDedcutionOrg(investBill)" title="Enter Investigation Bill"/></td>
</tr>
<tr><td width="16%">&nbsp;</td>
<td width="16%">&nbsp;</td>
<!-- <td width="16%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claim.StayRemark"/></b></td> -->
<td width="16%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claim.inputRemark"/></b></td>
<td width="16%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claim.ProfRemark"/></b></td>
<td width="16%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Claim.InvestRemark"/></b></td>
</tr>
<tr><td width="16%">&nbsp;</td>
<td width="16%" style="word-wrap:break-word;">&nbsp;</td>
<!-- <td width="16%" style="word-wrap:break-word;" class="tbcellBorder"><html:textarea   name="claimsFlowForm" property="stayRemark"   styleId="stayRemark" style="WIDTH:90%;height:3em;overflow-y:auto" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Stay Remarks')" title="Enter Remarks if amount reduced"/></td>-->
<td width="16%" style="word-wrap:break-word;" class="tbcellBorder"><html:textarea   name="claimsFlowForm" property="inputsRmrkOrg"   styleId="inputsRmrkOrg" style="WIDTH:90%;height:3em;overflow-y:auto" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Input Remarks')" title="Enter Remarks if amount reduced"/></td>
<td width="16%" style="word-wrap:break-word;" class="tbcellBorder"><html:textarea   name="claimsFlowForm" property="profFeeRmrkOrg"   styleId="profFeeRmrkOrg" style="WIDTH:90%;height:3em;overflow-y:auto" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Professional Fee Remarks')" title="Enter Remarks  if amount reduced"/></td>
<td width="16%" style="word-wrap:break-word;" class="tbcellBorder"><html:textarea   name="claimsFlowForm" property="investBillRmrkOrg"   styleId="investBillRmrkOrg" style="WIDTH:90%;height:3em" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Invest Bill Remarks')" title="Enter Remarks if amount reduced"/></td>
</tr>
</table></td></tr>  
<tr><td>
<table width="100%"><tr>
<td width="30%" align="left"  class="labelheading1 tbcellCss"><fmt:message key="EHF.Claims.FAprAmt"/>&nbsp;<font color="red">*</font>:&nbsp;&nbsp;<html:text name="claimsFlowForm" property="cpdAprAmtOrg" styleId="cpdAprAmtOrg" disabled="true" maxlength="7" onchange="validateNumber('Final Approve Amount',this)" title="Enter Final Approve Amount"/></td>
</tr>
</table>
</td></tr>
<tr><td>
<table width="100%">
<tr><td width="75%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.techCheck7"/>&nbsp;<font color="red">*</font></td><td  class="tbcellBorder">
<html:radio name="claimsFlowForm" property="techCheckOrg1" value="Y" title="Yes" styleId="techCheckOrg1"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="techCheckOrg1" value="N" title="No" styleId="techCheckOrg1" /><fmt:message key="EHF.Claims.No"/>
</td></tr>
<tr><td width="75%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.techCheck8"/>&nbsp;<font color="red">*</font></td><td  class="tbcellBorder">
<html:radio name="claimsFlowForm" property="techCheckOrg2" value="Y" title="Yes" styleId="techCheckOrg2"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="techCheckOrg2" value="N" title="No" styleId="techCheckOrg2" /><fmt:message key="EHF.Claims.No"/>
</td></tr>
<tr><td width="75%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.techCheck9"/>&nbsp;<font color="red">*</font></td><td  class="tbcellBorder">
<html:radio name="claimsFlowForm" property="techCheckOrg3" value="Y" title="Yes" styleId="techCheckOrg3"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="techCheckOrg3" value="N" title="No" styleId="techCheckOrg3" /><fmt:message key="EHF.Claims.No"/>
</td></tr>
<tr><td width="75%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer3"/>&nbsp;<font color="red">*</font></td><td  class="tbcellBorder">
<html:radio name="claimsFlowForm" property="techCheckOrg4" value="Y" title="Yes" styleId="techCheckOrg4"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="techCheckOrg4" value="N" title="No" styleId="techCheckOrg4" /><fmt:message key="EHF.Claims.No"/>
</td></tr>
<tr><td colspan="3" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer6"/>&nbsp;<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
<html:textarea   name="claimsFlowForm"  property="claimPanelRemarkOrg"  styleId="claimPanelRemarkOrg" style="WIDTH:80%;height:3em;overflow-y:auto" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Enter Remarks"/>
</td></tr>
</table></td></tr>
</logic:equal>
<logic:equal value="Yes" name="claimsFlowForm" property="showCtd">
<tr><td>
<table class="tbheader">
<tr><td align="center"><b>&nbsp;&nbsp;&nbsp;<fmt:message key="EHF.Title.TrustDoc"/></b></td></tr>
</table></td></tr>
<tr><td>
<table width="100%"><tr>
<td width="30%" align="left"  class="labelheading1 tbcellCss"><fmt:message key="EHF.Claims.FAprAmt"/>&nbsp;<font color="red">*</font>:&nbsp;&nbsp;<html:text name="claimsFlowForm" property="ctdAprAmt" styleId="ctdAprAmt" maxlength="7" onblur="calculateFinalAmt('CTD')" onchange="validateNumber('Final Approve Amount',this)" title="Enter Final Approve Amount"/></td>
<logic:equal value="Yes" name="claimsFlowForm" property="cocFinal">
<td width="30%" align="left" class="labelheading1 tbcellCss">NABH Amount&nbsp;<font color="red">*</font>:&nbsp;&nbsp;<html:text name="claimsFlowForm" property="ctdNabhAmt" styleId="ctdNabhAmt" maxlength="7" title="NABH Amount" disabled="true"/></td>
<td width="30%" align="left" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claims.FAprAmt"/>&nbsp;<font color="red">*</font>:&nbsp;&nbsp;<html:text name="claimsFlowForm" property="ctdFinAprAmt" styleId="ctdFinAprAmt" maxlength="6" title="Final Approve Amount" disabled="true" style="width:45%" /></td>
</logic:equal>
</tr>
</table>
</td></tr>
<tr><td>
<table width="100%">
<tr><td width="75%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.trustDoc1"/>&nbsp;<font color="red">*</font></td><td  class="tbcellBorder">
<html:radio name="claimsFlowForm" property="trustDoc1" value="Y" title="Yes" styleId="trustDoc1"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="trustDoc1" value="N" title="No" styleId="trustDoc1" /><fmt:message key="EHF.Claims.No"/>
</td></tr>
<tr><td width="75%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.trustDoc2"/>&nbsp;<font color="red">*</font></td><td  class="tbcellBorder">
<html:radio name="claimsFlowForm" property="trustDoc2" value="Y" title="Yes" styleId="trustDoc2"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="trustDoc2" value="N" title="No" styleId="trustDoc2" /><fmt:message key="EHF.Claims.No"/>
</td></tr>
<tr><td width="75%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.trustDoc3"/>&nbsp;<font color="red">*</font></td><td  class="tbcellBorder">
<html:radio name="claimsFlowForm" property="trustDoc3" value="Y" title="Yes" styleId="trustDoc3"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="trustDoc3" value="N" title="No" styleId="trustDoc3" /><fmt:message key="EHF.Claims.No"/>
</td></tr>
<tr><td width="75%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer3"/>&nbsp;<font color="red">*</font></td><td  class="tbcellBorder">
<html:radio name="claimsFlowForm" property="trustDoc4" value="Y" title="Yes" styleId="trustDoc4"  /><fmt:message key="EHF.Claims.Yes"/>
<html:radio name="claimsFlowForm" property="trustDoc4" value="N" title="No" styleId="trustDoc4" /><fmt:message key="EHF.Claims.No"/>
</td></tr>
<tr><td colspan="3" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer6"/>&nbsp;<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
<html:textarea   name="claimsFlowForm" property="ctdRemark"  styleId="ctdRemark" style="overflow-y:auto;WIDTH:80%;height:3em" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Enter Remarks"/>
</table></td></tr></logic:equal>

<logic:equal value="Yes" name="claimsFlowForm" property="showCh">
<tr><td>
<table class="tbheader">
<tr><td align="center"><b>&nbsp;&nbsp;&nbsp;<fmt:message key="EHF.Title.ClaimHead"/></b></td></tr>
</table></td></tr>
<tr><td>
<table width="100%"><tr>
<td width="25%" align="left" class="labelheading1 tbcellCss">Approve Amount&nbsp;<font color="red">*</font>:&nbsp;&nbsp;<html:text name="claimsFlowForm" property="chEntAprAmt" styleId="chEntAprAmt"  onblur="calculateFinalAmt('CH')" maxlength="7" onchange="validateNumber('Approve Amount',this)" title="Enter Approve Amount"/></td>
<!-- <td width="25%" align="left" class="labelheading1 tbcellCss">NABH Amount&nbsp;<font color="red">*</font>:&nbsp;&nbsp;<html:text name="claimsFlowForm" property="chNabhAmt" styleId="chNabhAmt" maxlength="6" title="NABH Amount" disabled="true"/></td>-->
<td width="25%" align="left" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claims.FAprAmt"/>&nbsp;<font color="red">*</font>:&nbsp;&nbsp;<html:text name="claimsFlowForm" property="chAprAmt" styleId="chAprAmt" maxlength="6" title="Final Approve Amount" readonly="true" style="width:45%" /></td>
<td width="25%" align="left" class="labelheading1 tbcellCss">Penalty Amount:&nbsp;&nbsp;<html:text name="claimsFlowForm" property="penaltyAmount" styleId="penaltyAmount" maxlength="6" title="Penalty Amount" readonly="true" style="width:45%" /></td>
</tr>
</table>
</td></tr>
<tr><td>
<table width="100%">
<tr ><td width="100%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer6"/>&nbsp;<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
<html:textarea   name="claimsFlowForm" property="chRemark"  styleId="chRemark" style="overflow-y:auto;WIDTH:80%;height:3em" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Enter Remarks"/>
</td></tr></table></td></tr>
</logic:equal>
<c:if test="${claimsFlowForm.caseStatus ne 'CD1351'}">
<logic:equal value="Yes" name="claimsFlowForm" property="showEo">
<tr><td>
<table class="tbheader">
<tr><td align="center"><b>&nbsp;&nbsp;&nbsp;<fmt:message key="EHF.Claim.EOCheckList"/></b></td></tr>
</table></td></tr>
<tr><td>
<table width="100%"><tr>
<td width="30%" align="left" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claims.FAprAmt"/>&nbsp;<font color="red">*</font>:&nbsp;&nbsp;<html:text name="claimsFlowForm" property="eoAprAmt" styleId="eoAprAmt" maxlength="7" onchange="validateNumber('Final Approve Amount',this)" title="Enter Final Approve Amount"/></td>
</tr>
</table>
</td></tr>
<tr><td>
<table width="100%">
<tr ><td width="100%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer6"/>&nbsp;<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
<html:textarea name="claimsFlowForm" property="eoRemark" styleId="eoRemark" style="WIDTH:80%;height:3em;overflow-y:auto" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Enter Remarks"/>
</td></tr></table></td></tr>
</logic:equal>
</c:if>
<logic:equal value="Yes" name="claimsFlowForm" property="showEoCom">
<tr><td>
<table class="tbheader">
<tr><td align="center"><b>&nbsp;&nbsp;&nbsp;<fmt:message key="EHF.Claim.EOCommCheckList"/></b></td></tr>
</table></td></tr>
<tr><td>
<table width="100%"><tr>
<td width="30%" align="left" class="labelheading1 tbcellCss">Approve Amount&nbsp;<font color="red">*</font>:&nbsp;&nbsp;<html:text name="claimsFlowForm" property="eoComEntAprAmt" styleId="eoComEntAprAmt" onblur="calculateFinalAmt('EOCOM')" maxlength="7" onchange="validateNumber('Approve Amount',this)" title="Enter Approve Amount"/></td>
<!-- <td width="30%" align="left" class="labelheading1 tbcellCss">NABH Amount&nbsp;<font color="red">*</font>:&nbsp;&nbsp;<html:text name="claimsFlowForm" property="eoComNabhAmt" styleId="eoComNabhAmt" maxlength="6" title="NABH Amount" disabled="true"/></td>-->
<td width="30%" align="left" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claims.FAprAmt"/>&nbsp;<font color="red">*</font>:&nbsp;&nbsp;<html:text name="claimsFlowForm" property="eoComAprAmt" styleId="eoComAprAmt" maxlength="6" title="Final Approve Amount" disabled="true" style="width:45%" /></td>
</tr>
</table>
</td></tr>
<tr><td>
<table width="100%">
<tr ><td width="100%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer6"/>&nbsp;<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
<html:textarea name="claimsFlowForm" property="eoComRemark" styleId="eoComRemark" style="WIDTH:80%;height:3em;overflow-y:auto" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Enter Remarks"/>
</td></tr></table></td></tr>
</logic:equal>
<logic:equal value="Yes" name="claimsFlowForm" property="showAco">
<tr><td>
<table class="tbheader">
<tr><td align="center"><b>&nbsp;&nbsp;&nbsp;<fmt:message key="EHF.Title.Account"/></b></td></tr>
</table></td></tr>
<!-- <tr><td>
<table width="100%"><tr>
<td width="30%" align="left" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claims.FAprAmt"/>&nbsp;<font color="red">*</font>:&nbsp;&nbsp;<html:text name="claimsFlowForm" property="acoAprAmt" styleId="acoAprAmt" maxlength="7" onchange="validateNumber('Final Approve Amount',this)" title="Enter Final Approve Amount"/></td>
</tr>
</table>
</td></tr>
 -->
<tr><td>
<table width="100%">
<tr ><td width="100%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer6"/>&nbsp;<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
<html:textarea   name="claimsFlowForm" property="acoRemark"  styleId="acoRemark" style="WIDTH:80%;height:3em;overflow-y:auto" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Remarks')" title="Enter Remarks"/>
</td></tr></table></td></tr>
</logic:equal>
<tr><td colspan="5" align="center">&nbsp;</td></tr>	
<logic:equal value="Yes" name="claimsFlowForm" property="dispErrInitBlock">
<tr><td colspan="10">
<tr><td>
<table class="tbheader">
<tr><td align="center"><b>&nbsp;&nbsp;&nbsp;<fmt:message key="EHF.ErrClaims.Title"/></b></td></tr>
</table></td></tr>
<tr><td>
	<table width="100%">
	<tr ><td width="30%" align="left" class="labelheading1 tbcellCss"><fmt:message key="EHF.ErrClaims.claimPaid"/>:&nbsp;&nbsp;<b><bean:write name="claimsFlowForm" property="errClaimPaid" /></b></td>
    <td width="32%" align="left" class="labelheading1 tbcellCss"><fmt:message key="EHF.ErrClaims.Amt"/>:&nbsp;&nbsp;<b><bean:write name="claimsFlowForm" property="errAmount" /></b></td>
    <td width="38%" align="left" class="labelheading1 tbcellCss"><fmt:message key="EHF.ErrClaims.subDate"/>:&nbsp;&nbsp;<b><bean:write name="claimsFlowForm" property="errSubDate" /></b></td>
    </tr>
	<tr ><td colspan="4"  class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer6"/>&nbsp;<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <html:textarea   name="claimsFlowForm" property="errMedRemark"  styleId="errMedRemark" style="WIDTH:80%;height:3em;overflow-y:auto" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Medco Remarks')" title="Enter Remarks"/>
    </td></tr>
 </table>
</td></tr>
<logic:equal value="Yes" name="claimsFlowForm" property="showCtd1">
<tr><td colspan="10">
<tr><td>
<table class="tbheader">
<tr><td align="center"><b>&nbsp;&nbsp;&nbsp;<fmt:message key="EHF.ErrClaims.CTD"/></b></td></tr>
</table></td></tr>
<tr><td>
	<table width="100%">
	<tr><td align="left" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claims.FAprAmt"/>&nbsp;<font color="red">*</font>:&nbsp;&nbsp;<html:text name="claimsFlowForm" property="errAppAmount" styleId="errAppAmount" maxlength="7" onchange="validateNumber('Final Approve Amount',this)" title="Enter Final Approve Amount"/></td></tr>
	<tr ><td  class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer6"/>&nbsp;<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <html:textarea   name="claimsFlowForm" property="errCtdRemark"  styleId="errCtdRemark" style="WIDTH:80%;height:3em;overflow-y:auto" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Medco Remarks')" title="Enter Remarks"/>
    </td></tr>
 </table>
</td></tr>
</logic:equal>
<logic:equal value="Yes" name="claimsFlowForm" property="showCh1">
<tr><td colspan="10">
<tr><td>
<table class="tbheader">
<tr><td align="center"><b>&nbsp;&nbsp;&nbsp;<fmt:message key="EHF.ErrClaims.CH"/></b></td></tr>
</table></td></tr>
<tr><td>
	<table width="100%">
	<tr><td align="left" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claims.FAprAmt"/>&nbsp;<font color="red">*</font>:&nbsp;&nbsp;<html:text name="claimsFlowForm" property="errChAppAmount" styleId="errChAppAmount" maxlength="7" onchange="validateNumber('Final Approve Amount',this)" title="Enter Final Approve Amount"/></td></tr>
	<tr ><td  class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer6"/>&nbsp;<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <html:textarea   name="claimsFlowForm" property="errChRemark"  styleId="errChRemark" style="WIDTH:80%;height:3em;overflow-y:auto" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Medco Remarks')" title="Enter Remarks"/>
    </td></tr>
 </table>
</td></tr>
</logic:equal>

<logic:equal value="Yes" name="claimsFlowForm" property="showAco1">
<tr><td colspan="10">
<tr><td>
<table class="tbheader">
<tr><td align="center"><b>&nbsp;&nbsp;&nbsp;<fmt:message key="EHF.Title.Account"/></b></td></tr>
</table></td></tr>
<tr><td>
	<table width="100%">
	<!-- <tr><td align="left" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claims.FAprAmt"/>&nbsp;<font color="red">*</font>:&nbsp;&nbsp;<html:text name="claimsFlowForm" property="errAcoAprAmt" styleId="errAcoAprAmt" maxlength="7" onchange="validateNumber('Final Approve Amount',this)" title="Enter Final Approve Amount"/></td></tr> -->
	<tr ><td  class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer6"/>&nbsp;<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <html:textarea   name="claimsFlowForm" property="errAcoRemark"  styleId="errAcoRemark" style="WIDTH:80%;height:3em;overflow-y:auto" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Medco Remarks')" title="Enter Remarks"/>
    </td></tr>
 </table>
</td></tr>
</logic:equal>

</logic:equal>

	<tr>
	        <td >
	     <table width="100%">
<tr id='checkSendto' style="display:none" ><td width="75%"  class="labelheading1 tbcellCss"><b>Do you want to Send the case to Panel Doctor ?</b>&nbsp;<font color="red">*</font></td>
<td  class="tbcellBorder"><html:radio name="claimsFlowForm" title="Yes" property="sendtoPanelDoc" value="Y" styleId="paySent2" onchange="javascript:showRevertRoles();" /><fmt:message key="EHF.Claims.Yes"/>
                        <html:radio name="claimsFlowForm" property="sendtoPanelDoc" title="No" value="N" styleId="paySent2" onchange="javascript:hideRevertRoles();" /><fmt:message key="EHF.Claims.No" />
</td></tr>
	     <tr id="revertRoles" style="display:none">   

		  <td   class="labelheading1 tbcellCss">Panel Doctor Name &nbsp;<font color="red">*</font></td>				
	                  <td class="labelheading1 tbcellCss">    <html:select name="claimsFlowForm" property="allUsers"   styleId="allUsers" style="width:100%" >
							 <html:option value="-1">-SELECT-</html:option>
							</html:select>
	      </td>
	   
	    </tr>
	    <tr>

	    </table>
	   </td> 
	</tr>
	

	<tr>
	<td>
	<table width="100%" >
		    <tr id="revertRemarks" style="display:none">
		    
	       <td colspan="3"  class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer6"/><font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
          <html:textarea   name="claimsFlowForm" property="eoComSendRemarks"  styleId="eoComSendRemarks" style="overflow-y:auto;WIDTH:80%;height:3em" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'send back Remarks')" title="Enter Send Back"/>
	       </td>
	    </tr>
	    <tr id="sendtoButtons" style="display:none">
	<td colspan="6" align="center">	
   	<button class="but"   type="button"  value="SendBack" name="SendBack" onclick="javascript:fn_buttonClicked('CD72');">Send</button>
	<logic:equal value="Yes" name="claimsFlowForm" property="viewAttach">
	<button class="but"   type="button"  value="viewAttachment" name="viewAttachment" onclick="javascript:fn_attachments();">View Attachments</button>
	</logic:equal>
	</td>
	</tr>
	</table>
	</td>
	</tr>

	<c:if test="${fn:length(buttons) > 0}">
	<tr id="buttonVisibleId" style="display: none;">
	<td colspan="6" align="center" id="buttonsNor">
	<c:forEach var="EHFbutton" items="${buttons}" varStatus="status" >
		<button class="but" id="${EHFbutton.ID}"  type="button"  value="${EHFbutton.VALUE}" name="${EHFbutton.ID}" onclick="javascript:fn_buttonClicked('${EHFbutton.ID}');">${EHFbutton.VALUE}</button>
	</c:forEach>
	<logic:equal value="Yes" name="claimsFlowForm" property="addAttach">
	<button class="but"   type="button"  value="addAttachment" name="addAttachment" onclick="javascript:fn_attachments();">Add/View Attachments</button>
	</logic:equal>
	<logic:equal value="Yes" name="claimsFlowForm" property="viewAttach">
	<button class="but"   type="button"  value="viewAttachment" name="viewAttachment" onclick="javascript:fn_attachments();">View Attachments</button>
	</logic:equal>
	</td>
	</tr>
	</c:if>	
<tr><td colspan="5" align="center">&nbsp;</td></tr>
	
<logic:present name="claimsFlowForm" property="lstworkFlow">
<bean:size id="wrkList" name="claimsFlowForm" property="lstworkFlow"/>
<logic:greaterThan value="0" name="wrkList">
<tr><td colspan="2">
<table border="0" width="100%" style="table-layout: fixed;word-wrap:break-word;" cellpadding="1" cellspacing="1" align="center">
<tr  class="tbheader"  align="center"><td colspan="7">&nbsp;<b><fmt:message key="EHF.workFlow.title"/></b></td></tr>
</table>
<table border="0" width="100%" style="table-layout: fixed;word-wrap:break-word;" cellpadding="1" cellspacing="1" align="center"  id="testtable"  >
<tr ><td width="4%" class="labelheading1 tbcellCss"><fmt:message key="EHF.workFlow.sno"/></td>
<td width="12%" class="labelheading1 tbcellCss"><fmt:message key="EHF.workFlow.Dt"/></td>
<c:if test="${ viewClaimAuditNames eq 'Y' }">
	<td width="12%" class="labelheading1 tbcellCss"><fmt:message key="EHF.workFlow.name"/></td>
</c:if>			
<td width="12%" class="labelheading1 tbcellCss"><fmt:message key="EHF.workFlow.role"/></td>
<td width="30%" class="labelheading1 tbcellCss"><fmt:message key="EHF.workFlow.reamark"/></td>
<td width="15%" class="labelheading1 tbcellCss"><fmt:message key="EHF.workFlow.appAmt"/></td>
<td width="15%" class="labelheading1 tbcellCss"><fmt:message key="EHF.workFlow.action"/></td>	
</tr>



<logic:iterate id="results1" name="claimsFlowForm" property="lstworkFlow" indexId="index1" >
<tr>
<td  class="tbcellBorder"><%=index1+1%></td>
<td  class="tbcellBorder"><bean:write name="results1" property="auditDate" /></td>
<c:if test="${ viewClaimAuditNames eq 'Y' }">
	<td  class="tbcellBorder"><bean:write name="results1" property="auditName" /></td>
</c:if>	
<td  class="tbcellBorder"><bean:write name="results1" property="auditRole" /></td>
<td  class="tbcellBorder"><bean:write name="results1" property="cexRemark" /></td>
<td  class="tbcellBorder"><bean:write name="results1" property="COUNT" /></td>
<td  class="tbcellBorder"><bean:write name="results1" property="auditAction" /></td>
</tr>

</logic:iterate>

</table>
</td></tr>
</logic:greaterThan>
</logic:present>



	<c:if test="${sentBack eq true && caseApprovalFlag != 'N' && UserRole!='GP9'}">
	<tr><td><br><br></td></tr>
<tr>
<td>



<table width="100%">
<tr ><td width="100%" class="labelheading1 tbcellCss"><fmt:message key="EHF.Claim.DocVer6"/>&nbsp;<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
<html:textarea   name="claimsFlowForm" property="ceoRemark"  styleId="ceoRemarks" style="WIDTH:80%;height:3em;overflow-y:auto" onkeydown="check_maxLength('ceoRemarks',3000)" onchange="checkRemarks(this.value,3000,'ceoRemarks')" title="Enter Remarks"/>
</td></tr></table>

</td></tr>



			
	
	
	<tr><td align="center">
							<button class="but"   type="button"  id="approveBtn" onclick="javascript:fn_submitSentBackNxtLvl();" >Submit</button>
							
							</td>
						</tr>			
				
	</c:if>
	
</table>
</logic:empty>


<script type="text/javascript">
var roleId='${UserRole}';
var flag = null;
var lField = '';
var date = new Date();
var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();
var displayMsg='';
var disSearchType = parent.parent.disSearchType;
var showEO = document.claimsFlowForm.showEo.value;
var showEOCom = document.claimsFlowForm.showEoCom.value;
function fn_attachments()
{	
	var newBornBaby=document.getElementById("newBornBaby").value;
    var caseAppFlag = '${caseApprovalFlag}';
	var caseId = '<bean:write name="claimsFlowForm" property="caseId" />';
	var url = "/Operations/attachmentAction.do?actionFlag=onload&UpdType=ehfClaim&caseId="+caseId+"&caseAttachmentFlag=Y&PreauthFlag=N&openWin=Y&caseApprovalFlag="+caseAppFlag+"&newBornBaby="+newBornBaby;
    parent.parent.parent.claimAttachWindow = window.open(url, 'window1','toolbar=no,resize=no,scrollbars=yes,width=800, height=600, top=100,left=50');
}








function fn_submitSentBackNxtLvl()
{
	var caseId='${caseId}';
	var caseStatus='${caseStatus}';
	var sendBackFlag="${sentBack}";
	var remarks=document.getElementById("ceoRemarks").value;


	var xmlhttp;
    var url;
    check_maxLength('ceoRemarks',4000);
    if(remarks.length>=3000)
    {
  return false;
    }
    if(remarks==null || remarks=="")
    {
alert("Please Enter Remarks");
return false;
    }
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
 url = "/Operations/ClaimsFlow.do?actionFlag=updateSentBackCases&caseId="+caseId+"&caseStatus="+caseStatus+"&remarks="+remarks+"&sendBackFlag="+sendBackFlag+"&moduleType=claims";
 xmlhttp.onreadystatechange=function()
	{
	    if(xmlhttp.readyState==4)
	    {	
	    	 var resultArray=xmlhttp.responseText;
		        var resultArray = resultArray.split("*");
		        if(resultArray[0]=="SessionExpired"){
		    		alert("Session has been expired");
		    		 parent.sessionExpireyClose();
		    		// var fr = partial(parent.sessionExpireyClose);
		    		// jqueryInfoMsg('Session details',"Session has been expired",fr);
		    		}
		        else
		        {
                alert(resultArray[0]);
                fn_back();
		        }
	    }}
 document.getElementById('approveBtn').disabled=true;
 xmlhttp.open("Post",url,true);
	xmlhttp.send(null);	
}


function fn_back()
{
	parent.parent.parent.fn_pendingCases(); 
}








function fn_onloadDisabled(){
	
	var pendFlag = '${pendFlag}';
	if(pendFlag!=null && pendFlag == 'Y')
	{
	document.getElementById("billAmt").readonly=true;	
	document.getElementById("billDt").readonly=true;
	$("#billDt").datepicker("disable");
	}
	
	
if('${disableAll}'=='Yes'){	
	if(roleId==''){
		disableMedco();
		disableCEX();
		disableCPD();	
		disableCTD();
		disableCH();
		disableEO();
		disableEOCom();
		disableACO();
		if(document.forms[0].dispErrInitBlock.value=='Yes'){
			if(document.getElementById("errMedRemark")!=null)
    		   document.getElementById("errMedRemark").readOnly=true;document.getElementById("errMedRemark").style.background='#ECE9D8';document.getElementById('errMedRemark').style.color='#ACA899';
    		   if(document.getElementById("errCtdRemark")!=null)
    		   document.getElementById("errCtdRemark").readOnly=true;document.getElementById("errCtdRemark").style.background='#ECE9D8';document.getElementById('errCtdRemark').style.color='#ACA899';
    		   if(document.getElementById("errAppAmount")!=null)
     		   document.getElementById("errAppAmount").disabled=true;
     		  if(document.getElementById("errChAppAmount")!=null)
     		   document.getElementById("errChAppAmount").disabled=true;
     		   if(document.getElementById("errChRemark")!=null)
    		   document.getElementById("errChRemark").readOnly=true;document.getElementById("errChRemark").style.background='#ECE9D8';document.getElementById('errChRemark').style.color='#ACA899';
     		//  if(document.getElementById("errAcoAprAmt")!=null)
    		  // document.getElementById("errAcoAprAmt").disabled=true;
     		 if(document.getElementById("errAcoRemark")!=null)
    		   document.getElementById("errAcoRemark").readOnly=true;document.getElementById("errAcoRemark").style.background='#ECE9D8';document.getElementById('errAcoRemark').style.color='#ACA899';
            }
		
		}
	if(roleId=='<fmt:message key="EHF.Claims.Role.MEDCO"/>'){
               disableMedco();
               if(document.forms[0].dispErrInitBlock.value=='Yes'){
            	   disableCEX();
           		   disableCPD();	
           		   disableCTD();
           		   disableCH();
           		   disableEO();
           		   disableEOCom();
           		   disableACO();
           		   document.getElementById("errMedRemark").readOnly=true;document.getElementById("errMedRemark").style.background='#ECE9D8';document.getElementById('errMedRemark').style.color='#ACA899';
                   }
		}		
	if(roleId=='<fmt:message key="EHF.Claims.Role.CEX"/>'||roleId=='<fmt:message key="EHF.Claims.Role.COCCEX"/>'||roleId=='<fmt:message key="EHF.Claims.Role.ORGCEX"/>' ){
		disableMedco();
		disableCEX();
		}
	if(roleId=='<fmt:message key="EHF.Claims.Role.CPD"/>'||roleId=='<fmt:message key="EHF.Claims.Role.COCCMT"/>'||roleId=='<fmt:message key="EHF.Claims.Role.ORGCMT"/>'||roleId=='<fmt:message key="EHF.Claims.Role.HUBS"/>'){
		disableMedco();
		disableCEX();
		disableCPD();	
	}
	if(roleId=='<fmt:message key="EHF.Claims.Role.CTD"/>'||roleId=='<fmt:message key="EHF.Claims.Role.COCTD"/>'||roleId=='<fmt:message key="EHF.Claims.Role.ORGCTD"/>'){
		disableMedco();
		disableCEX();
		disableCPD();
		disableCTD();
		if(document.forms[0].dispErrInitBlock.value=='Yes'){
    		   disableCH();
    		   disableEO();
    		   disableEOCom();
    		   disableACO();
    		   document.getElementById("errMedRemark").readOnly=true; document.getElementById("errMedRemark").style.background='#ECE9D8';document.getElementById('errMedRemark').style.color='#ACA899';		 
            }	
	}
	if(roleId=='<fmt:message key="EHF.Claims.Role.CH"/>'){
		disableMedco();
		disableCEX();
		disableCPD();	
		disableCTD();
		disableCH();
		if(document.forms[0].dispErrInitBlock.value=='Yes'){
			disableEO();
			disableEOCom();
			disableACO();
 		   document.getElementById("errMedRemark").readOnly=true;document.getElementById("errMedRemark").style.background='#ECE9D8';document.getElementById('errMedRemark').style.color='#ACA899';
 		   document.getElementById("errCtdRemark").readOnly=true;document.getElementById("errCtdRemark").style.background='#ECE9D8';document.getElementById('errCtdRemark').style.color='#ACA899';
 		   document.getElementById("errAppAmount").disabled=true;
         }
	}
	if(roleId=='<fmt:message key="EHF.Claims.Role.EO"/>'){
		disableMedco();
		disableCEX();
		disableCPD();	
		disableCTD();
		disableCH();
		disableEO();
	}
	if(roleId=='<fmt:message key="EHF.Claims.Role.EOComm"/>'){
		disableMedco();
		disableCEX();
		disableCPD();	
		disableCTD();
		disableCH();
		disableEO();
		disableEOCom();
		if('${claimsFlowForm.caseStatus}'!="CD3516"){
		document.getElementById("checkSendto").style.display="";
		document.forms[0].paySent2[1].checked=true;
		}
	}
	if(roleId=='<fmt:message key="EHF.Claims.Role.ACO"/>'){
		disableMedco();
		disableCEX();
		disableCPD();	
		disableCTD();
		disableCH();
		disableEO();
		disableEOCom();
		disableACO();
		if(document.forms[0].dispErrInitBlock.value=='Yes'){
 		   document.getElementById("errMedRemark").readOnly=true;document.getElementById("errMedRemark").style.background='#ECE9D8';document.getElementById('errMedRemark').style.color='#ACA899';
 		   document.getElementById("errCtdRemark").readOnly=true;document.getElementById("errCtdRemark").style.background='#ECE9D8';document.getElementById('errCtdRemark').style.color='#ACA899';
 		   document.getElementById("errAppAmount").disabled=true;
 		   document.getElementById("errChAppAmount").disabled=true;
		   document.getElementById("errChRemark").readOnly=true;document.getElementById("errChRemark").style.background='#ECE9D8';document.getElementById('errChRemark').style.color='#ACA899';
         }
	}
}
else{
	if(roleId=='<fmt:message key="EHF.Claims.Role.MEDCO"/>'){
		
		 if(document.forms[0].dispErrInitBlock.value=='Yes'){
			 disableMedco();
      	   disableCEX();
     		   disableCPD();	
     		   disableCTD();
     		   disableCH();
     		   disableEO();
     		   disableEOCom();
     		   disableACO();
     		  // document.getElementById("errMedRemark").disabled=true;
             }
		}
	
	if(roleId=='<fmt:message key="EHF.Claims.Role.CEX"/>'||roleId=='<fmt:message key="EHF.Claims.Role.COCCEX"/>'||roleId=='<fmt:message key="EHF.Claims.Role.ORGCEX"/>'){
		disableMedco();
		 if(document.forms[0].dispErrInitBlock.value=='Yes'){
      	       disableCEX();
     		   disableCPD();	
     		   disableCTD();
     		   disableCH();
     		   disableEO();
     		   disableEOCom();
     		   disableACO();
     		  // document.getElementById("errMedRemark").disabled=true;
             }
		}
	if(newCpd!=null && newCpd=='N')
	{
if(roleId=='<fmt:message key="EHF.Claims.Role.CPD"/>'||roleId=='<fmt:message key="EHF.Claims.Role.COCCMT"/>'||roleId=='<fmt:message key="EHF.Claims.Role.ORGCMT"/>'||roleId=='<fmt:message key="EHF.Claims.Role.HUBS"/>'){
	
	disableMedco();
	disableCEX();
	
	if('${claimsFlowForm.caseStatus}'!=null&&'${claimsFlowForm.caseStatus}'=="CD3515"){
		disableEO();
		disableEOCom();
		disableCH();
		disableCTD();
		document.getElementById("claimPanelRemark").value="";
		
	}	
}
	}
	if(newCpd!=null && newCpd=='Y')
	{
if(roleId=='<fmt:message key="EHF.Claims.Role.CPD"/>'||roleId=='<fmt:message key="EHF.Claims.Role.COCCMT"/>'||roleId=='<fmt:message key="EHF.Claims.Role.ORGCMT"/>'||roleId=='<fmt:message key="EHF.Claims.Role.HUBS"/>'){
	
	disableMedco();
	disableCEX();
	disableCPDOrg();
	if('${claimsFlowForm.caseStatus}'!=null&&'${claimsFlowForm.caseStatus}'=="CD3515"){
		disableEO();
		disableEOCom();
		disableCH();
		disableCTD();
		document.getElementById("claimPanelRemark").value="";
		
	}	
}
	}
if(roleId=='<fmt:message key="EHF.Claims.Role.CTD"/>'||roleId=='<fmt:message key="EHF.Claims.Role.COCTD"/>'||roleId=='<fmt:message key="EHF.Claims.Role.ORGCTD"/>'){
	disableMedco();
	disableCEX();
	disableCPD();
	if(disSearchType=='Y')
		disableCTD();
	if(document.forms[0].dispErrInitBlock.value=='Yes'){
		   disableCTD();
		   disableCH();
		   disableEO();
		   disableEOCom();
		   disableACO();
		   document.getElementById("errMedRemark").readOnly=true;  document.getElementById("errMedRemark").style.background='#ECE9D8';  document.getElementById('errMedRemark').style.color='#ACA899';		 
     }	
}
if(roleId=='<fmt:message key="EHF.Claims.Role.CH"/>'){
	disableMedco();
	disableCEX();
	disableCPD();	
	disableCTD();
	if(disSearchType=='Y')
		disableCH();
	if(document.forms[0].dispErrInitBlock.value=='Yes'){
		   disableCH();
		   disableEO();
		   disableEOCom();
		   disableACO();
		   document.getElementById("errMedRemark").readOnly=true; document.getElementById("errMedRemark").style.background='#ECE9D8';document.getElementById('errMedRemark').style.color='#ACA899';
		   document.getElementById("errCtdRemark").readOnly=true;document.getElementById("errCtdRemark").style.background='#ECE9D8';document.getElementById('errCtdRemark').style.color='#ACA899';
 		   document.getElementById("errAppAmount").disabled=true;   		 
  }
}
if(roleId=='<fmt:message key="EHF.Claims.Role.EO"/>'){
	disableMedco();
	disableCEX();
	disableCPD();	
	disableCTD();
	disableCH();
}
if(roleId=='<fmt:message key="EHF.Claims.Role.EOComm"/>'){
	disableMedco();
	disableCEX();
	disableCPD();	
	disableCTD();
	disableCH();
	disableEO();
	if('${claimsFlowForm.caseStatus}'!="CD3516"){
	document.getElementById("checkSendto").style.display="";
	document.forms[0].paySent2[1].checked=true;
	}
}
if(roleId=='<fmt:message key="EHF.Claims.Role.ACO"/>'){
	disableMedco();
	disableCEX();
	disableCPD();	
	disableCTD();
	disableCH();
	disableEO();
	disableEOCom();
	if(document.forms[0].dispErrInitBlock.value=='Yes'){
		   disableACO();
		   document.getElementById("errMedRemark").readOnly=true; document.getElementById("errMedRemark").style.background='#ECE9D8';document.getElementById('errMedRemark').style.color='#ACA899';
		   document.getElementById("errCtdRemark").readOnly=true;document.getElementById("errCtdRemark").style.background='#ECE9D8';document.getElementById('errCtdRemark').style.color='#ACA899';
		   document.getElementById("errAppAmount").disabled=true; 
		   document.getElementById("errChAppAmount").disabled=true;
		   document.getElementById("errChRemark").readOnly=true; document.getElementById("errChRemark").style.background='#ECE9D8'; document.getElementById('errChRemark').style.color='#ACA899';		 
}
}
}
document.getElementById("revertRoles").style.display="none";
document.getElementById("allUsers").style.display="none";
document.getElementById("revertRemarks").style.display="none";
//document.getElementById("sendToBtn").style.display = "none";
 
// document.getElementById("buttonsNor").style.display =""; 
document.getElementById("sendtoButtons").style.display ="none"; 	
/* if(document.getElementById("billAmt")!=null){
	calculateFinalAmt('MEDCO');
} 
else{
	document.getElementById("medcoNabhAmount").value="0";
} */
}
function disableMedco(){
	if(document.getElementById("billAmt")!=null)
	document.getElementById("billAmt").disabled=true;
	if(document.getElementById("billDt")!=null){
	document.getElementById("billDt").disabled=true;
	$("#billDt").datepicker("disable"); 
	}
	if(document.getElementById("medcoRemark")!=null)
	{
		document.getElementById("medcoRemark").readOnly=true;document.getElementById("medcoRemark").style.background='#ECE9D8';document.getElementById('medcoRemark').style.color='#ACA899';
	}


}
function disableCH(){
	if(disSearchType!='Y' || roleId!='<fmt:message key="EHF.Claims.Role.CH"/>'){
	if(document.getElementById("chRemark")!=null)
		{
			document.getElementById('chRemark').readOnly=true;document.getElementById('chRemark').style.background='#ECE9D8';document.getElementById('chRemark').style.color='#ACA899';
		}
	}
	if(document.getElementById("chEntAprAmt")!=null)
	document.getElementById("chEntAprAmt").disabled=true;
}
function disableCTD(){
	if(document.forms[0].trustDoc1!=null){
	document.forms[0].trustDoc1[0].disabled=true;
	document.forms[0].trustDoc1[1].disabled=true;
	}
	if(document.forms[0].trustDoc2!=null){
	document.forms[0].trustDoc2[0].disabled=true;
	document.forms[0].trustDoc2[1].disabled=true;
	}
	if(document.forms[0].trustDoc3!=null){
	document.forms[0].trustDoc3[0].disabled=true;
	document.forms[0].trustDoc3[1].disabled=true;
	}
	if(document.forms[0].trustDoc4!=null){
		document.forms[0].trustDoc4[0].disabled=true;
		document.forms[0].trustDoc4[1].disabled=true;
		}
	if(disSearchType!='Y' || (roleId!='<fmt:message key="EHF.Claims.Role.CTD"/>' && roleId!=='<fmt:message key="EHF.Claims.Role.COCTD"/>' && roleId!=='<fmt:message key="EHF.Claims.Role.ORGCTD"/>' )){
	if(document.getElementById("ctdRemark")!=null)
	document.getElementById('ctdRemark').readOnly=true;document.getElementById('ctdRemark').style.background='#ECE9D8';document.getElementById('ctdRemark').style.color='#ACA899';
	}
	if(document.getElementById("ctdAprAmt")!=null)
	document.getElementById("ctdAprAmt").disabled=true;
}
function disableCEX(){
	if(document.forms[0].nameCheck!=null){
	document.forms[0].nameCheck[0].disabled=true;
	document.forms[0].nameCheck[1].disabled=true;
	}
	if(document.forms[0].genderCheck!=null){
	document.forms[0].genderCheck[0].disabled=true;
	document.forms[0].genderCheck[1].disabled=true;
	}
	if(document.forms[0].benPhotoCheck!=null){
	document.forms[0].benPhotoCheck[0].disabled=true;
	document.forms[0].benPhotoCheck[1].disabled=true;
	}
	if(document.forms[0].admDtCheck!=null){
	document.forms[0].admDtCheck[0].disabled=true;
	document.forms[0].admDtCheck[1].disabled=true;
	}
	if(document.forms[0].surgDtCheck!=null){
	document.forms[0].surgDtCheck[0].disabled=true;
	document.forms[0].surgDtCheck[1].disabled=true;
	}
	if(document.forms[0].dischargeDtCheck!=null){
	document.forms[0].dischargeDtCheck[0].disabled=true;
	document.forms[0].dischargeDtCheck[1].disabled=true;
	}
	if(document.getElementById("caseStAdmDt")!=null){
	document.getElementById("caseStAdmDt").disabled=true;
	$("#caseStAdmDt").datepicker("disable");
	}

   if(document.getElementById("caseStSurgDt")!=null){
	document.getElementById("caseStSurgDt").disabled=true;
	$("#caseStSurgDt").datepicker("disable");
	}
	if(document.getElementById("caseStDischrgDt")!=null){
	document.getElementById("caseStDischrgDt").disabled=true;
	$("#caseStDischrgDt").datepicker("disable");
	}
	if(document.forms[0].docVer1!=null){
	document.forms[0].docVer1[0].disabled=true;
	document.forms[0].docVer1[1].disabled=true;
	}
	if(document.forms[0].docVer2!=null){
	document.forms[0].docVer2[0].disabled=true;
	document.forms[0].docVer2[1].disabled=true;
	}
	/*if(document.forms[0].docVer3!=null){
	document.forms[0].docVer3[0].disabled=true;
	document.forms[0].docVer3[1].disabled=true;
	}*/
	if(document.forms[0].docVer4!=null){
	document.forms[0].docVer4[0].disabled=true;
	document.forms[0].docVer4[1].disabled=true;
	}
	if(document.forms[0].docVer5!=null){
	document.forms[0].docVer5[0].disabled=true;
	document.forms[0].docVer5[1].disabled=true;
	}
	if(document.getElementById("cexRemark")!=null)
	{
		document.getElementById('cexRemark').readOnly=true;document.getElementById('cexRemark').style.background='#ECE9D8';document.getElementById('cexRemark').style.color='#ACA899';
	}
	//document.getElementById('cexAprAmt').disabled=true;
	//document.getElementById('cal2').style.visibility='hidden';
	//document.getElementById('cal3').style.visibility='hidden';
	//document.getElementById('cal4').style.visibility='hidden';
	
	
}
function disableCPD(){
	if(document.getElementById("totalClaim")!=null)
	document.getElementById("totalClaim").disabled=true;
	if(document.getElementById("deduction")!=null)
	document.getElementById("deduction").disabled=true;
	if(document.getElementById("deductionOrg")!=null)
		document.getElementById("deductionOrg").disabled=true;
	//if(document.getElementById("stay")!=null)
	//document.getElementById("stay").disabled=true;
	if(document.getElementById("inputs")!=null)
	document.getElementById("inputs").disabled=true;
	if(document.getElementById("profFee")!=null)
	document.getElementById("profFee").disabled=true;
	if(document.getElementById("investBill")!=null)
	document.getElementById("investBill").disabled=true;
	if(document.getElementById("inputsOrg")!=null)
		document.getElementById("inputsOrg").disabled=true;
		if(document.getElementById("profFeeOrg")!=null)
		document.getElementById("profFeeOrg").disabled=true;
		if(document.getElementById("investBillOrg")!=null)
		document.getElementById("investBillOrg").disabled=true;
	//if(document.getElementById("stayRemark")!=null)
	//document.getElementById("stayRemark").readOnly=true;document.getElementById("stayRemark").style.background='#ECE9D8';document.getElementById('stayRemark').style.color='#ACA899';
	if(document.getElementById("inputsRmrk")!=null)
	{
		document.getElementById("inputsRmrk").readOnly=true;document.getElementById("inputsRmrk").style.background='#ECE9D8';document.getElementById('inputsRmrk').style.color='#ACA899';
	}
	if(document.getElementById("profFeeRmrk")!=null)
    {
		document.getElementById("profFeeRmrk").readOnly=true;document.getElementById("profFeeRmrk").style.background='#ECE9D8';document.getElementById('profFeeRmrk').style.color='#ACA899';
    }
	if(document.getElementById("investBillRmrk")!=null)
	{
		document.getElementById("investBillRmrk").readOnly=true;document.getElementById("investBillRmrk").style.background='#ECE9D8';document.getElementById('investBillRmrk').style.color='#ACA899';
	}
	if(document.getElementById("claimPanelRemark")!=null)
	{
		document.getElementById("claimPanelRemark").readOnly=true;document.getElementById("claimPanelRemark").style.background='#ECE9D8';document.getElementById('claimPanelRemark').style.color='#ACA899';
	}
	if(document.getElementById("inputsRmrkOrg")!=null)
	{
		document.getElementById("inputsRmrkOrg").readOnly=true;document.getElementById("inputsRmrkOrg").style.background='#ECE9D8';document.getElementById('inputsRmrkOrg').style.color='#ACA899';
	}
	if(document.getElementById("profFeeRmrkOrg")!=null)
    {
		document.getElementById("profFeeRmrkOrg").readOnly=true;document.getElementById("profFeeRmrkOrg").style.background='#ECE9D8';document.getElementById('profFeeRmrkOrg').style.color='#ACA899';
    }
	if(document.getElementById("investBillRmrkOrg")!=null)
	{
		document.getElementById("investBillRmrkOrg").readOnly=true;document.getElementById("investBillRmrkOrg").style.background='#ECE9D8';document.getElementById('investBillRmrkOrg').style.color='#ACA899';
	}
	if(document.getElementById("claimPanelRemarkOrg")!=null)
	{
		document.getElementById("claimPanelRemarkOrg").readOnly=true;document.getElementById("claimPanelRemarkOrg").style.background='#ECE9D8';document.getElementById('claimPanelRemarkOrg').style.color='#ACA899';
	}
	if(document.forms[0].techCheck1!=null){
	document.forms[0].techCheck1[0].disabled=true;
	document.forms[0].techCheck1[1].disabled=true;
	}
	if(document.forms[0].techCheck2!=null){
	document.forms[0].techCheck2[0].disabled=true;
	document.forms[0].techCheck2[1].disabled=true;
	}
	if(document.forms[0].techCheck3!=null){
	document.forms[0].techCheck3[0].disabled=true;
	document.forms[0].techCheck3[1].disabled=true;
	}
	if(document.forms[0].techCheck4!=null){
		document.forms[0].techCheck4[0].disabled=true;
		document.forms[0].techCheck4[1].disabled=true;
	}
	if(document.forms[0].techCheckOrg1!=null){
		document.forms[0].techCheckOrg1[0].disabled=true;
		document.forms[0].techCheckOrg1[1].disabled=true;
		}
		if(document.forms[0].techCheckOrg2!=null){
		document.forms[0].techCheckOrg2[0].disabled=true;
		document.forms[0].techCheckOrg2[1].disabled=true;
		}
		if(document.forms[0].techCheckOrg3!=null){
		document.forms[0].techCheckOrg3[0].disabled=true;
		document.forms[0].techCheckOrg3[1].disabled=true;
		}
		if(document.forms[0].techCheckOrg4!=null){
			document.forms[0].techCheckOrg4[0].disabled=true;
			document.forms[0].techCheckOrg4[1].disabled=true;
		}
		if(document.getElementById("cpdAprAmtOrg")!=null)
			document.getElementById("cpdAprAmtOrg").disabled=true;
	
	if(document.getElementById("cpdAprAmt")!=null)
	document.getElementById("cpdAprAmt").disabled=true;

}
function disableCPDOrg(){
	if(document.getElementById("totalClaim")!=null)
	document.getElementById("totalClaim").disabled=true;
	if(document.getElementById("deduction")!=null)
	document.getElementById("deduction").disabled=true;

	//if(document.getElementById("stay")!=null)
	//document.getElementById("stay").disabled=true;
	if(document.getElementById("inputs")!=null)
	document.getElementById("inputs").disabled=true;
	if(document.getElementById("profFee")!=null)
	document.getElementById("profFee").disabled=true;
	if(document.getElementById("investBill")!=null)
	document.getElementById("investBill").disabled=true;

	//if(document.getElementById("stayRemark")!=null)
	//document.getElementById("stayRemark").readOnly=true;document.getElementById("stayRemark").style.background='#ECE9D8';document.getElementById('stayRemark').style.color='#ACA899';
	if(document.getElementById("inputsRmrk")!=null)
	{
		document.getElementById("inputsRmrk").readOnly=true;document.getElementById("inputsRmrk").style.background='#ECE9D8';document.getElementById('inputsRmrk').style.color='#ACA899';
	}
	if(document.getElementById("profFeeRmrk")!=null)
    {
		document.getElementById("profFeeRmrk").readOnly=true;document.getElementById("profFeeRmrk").style.background='#ECE9D8';document.getElementById('profFeeRmrk').style.color='#ACA899';
    }
	if(document.getElementById("investBillRmrk")!=null)
	{
		document.getElementById("investBillRmrk").readOnly=true;document.getElementById("investBillRmrk").style.background='#ECE9D8';document.getElementById('investBillRmrk').style.color='#ACA899';
	}
	if(document.getElementById("claimPanelRemark")!=null)
	{
		document.getElementById("claimPanelRemark").readOnly=true;document.getElementById("claimPanelRemark").style.background='#ECE9D8';document.getElementById('claimPanelRemark').style.color='#ACA899';
	}
	
	if(document.forms[0].techCheck1!=null){
	document.forms[0].techCheck1[0].disabled=true;
	document.forms[0].techCheck1[1].disabled=true;
	}
	if(document.forms[0].techCheck2!=null){
	document.forms[0].techCheck2[0].disabled=true;
	document.forms[0].techCheck2[1].disabled=true;
	}
	if(document.forms[0].techCheck3!=null){
	document.forms[0].techCheck3[0].disabled=true;
	document.forms[0].techCheck3[1].disabled=true;
	}
	if(document.forms[0].techCheck4!=null){
		document.forms[0].techCheck4[0].disabled=true;
		document.forms[0].techCheck4[1].disabled=true;
	}
	
		
	
	if(document.getElementById("cpdAprAmt")!=null)
	document.getElementById("cpdAprAmt").disabled=true;

}
function disableEO(){	
if(showEO=='Yes'){
	if(document.getElementById("eoAprAmt")!=null)
	 document.getElementById('eoAprAmt').disabled=true;
	if(document.getElementById("eoRemark")!=null)
	{
		document.getElementById("eoRemark").readOnly=true;document.getElementById('eoRemark').style.background='#ECE9D8';document.getElementById('eoRemark').style.color='#ACA899';
	}
}}
function disableEOCom(){
	if(showEOCom=='Yes'){
	if(document.getElementById("eoComEntAprAmt")!=null)
		 document.getElementById('eoComEntAprAmt').disabled=true;
	if(document.getElementById("eoComRemark")!=null)
		 document.getElementById("eoComRemark").readOnly=true;document.getElementById('eoComRemark').style.background='#ECE9D8';document.getElementById('eoComRemark').style.color='#ACA899';
	}
}
function disableACO(){
	//if(document.getElementById("acoAprAmt")!=null)
	//document.getElementById('acoAprAmt').disabled=true;
	if(document.getElementById("acoRemark")!=null)
	{
		document.getElementById("acoRemark").readOnly=true;document.getElementById('acoRemark').style.background='#ECE9D8';document.getElementById('acoRemark').style.color='#ACA899';
	}
}

function fn_buttonClicked(buttonId){
	
   var errFlag = document.forms[0].dispErrInitBlock.value;
  
	var errMsg =''; 
	if(roleId=='<fmt:message key="EHF.Claims.Role.MEDCO"/>')
		errMsg= validateMedco(errFlag);
	if(roleId=='<fmt:message key="EHF.Claims.Role.CEX"/>')
		errMsg = validateNonTechCheckLst();
	if(roleId=='<fmt:message key="EHF.Claims.Role.COCCEX"/>')
		errMsg = validateNonTechCheckLst();
	if(roleId=='<fmt:message key="EHF.Claims.Role.ORGCEX"/>')
		errMsg = validateNonTechCheckLst();
	 if(newCpd!=null && newCpd=='N')
	 {
	if(roleId=='<fmt:message key="EHF.Claims.Role.CPD"/>'||roleId=='<fmt:message key="EHF.Claims.Role.COCCMT"/>' ||roleId=='<fmt:message key="EHF.Claims.Role.ORGCMT"/>')
		errMsg = validateTechCheckList();
	 }
	 if(newCpd!=null && newCpd=='Y')
	 {
if(roleId=='<fmt:message key="EHF.Claims.Role.CPD"/>'||roleId=='<fmt:message key="EHF.Claims.Role.COCCMT"/>' ||roleId=='<fmt:message key="EHF.Claims.Role.ORGCMT"/>')
	errMsg = validateTechCheckListOrg();
	 }
	if(roleId=='<fmt:message key="EHF.Claims.Role.CTD"/>'||roleId=='<fmt:message key="EHF.Claims.Role.COCTD"/>' ||roleId=='<fmt:message key="EHF.Claims.Role.ORGCTD"/>'){
          if(buttonId=='<fmt:message key="EHF.Button.DiscussionButton"/>' || buttonId=='<fmt:message key="EHF.Button.ClearButton"/>')
              {
        	  if(document.getElementById("ctdRemark").value==null || document.getElementById("ctdRemark").value==''||document.getElementById("ctdRemark").value.trim()==null || document.getElementById("ctdRemark").value.trim()=='')
        			{ lField=''; if(lField=='') lField="ctdRemark"; if(errMsg=='') errMsg=errMsg+"Please Enter Remarks <br>";}
              }else{
			errMsg = validateCTDList(errFlag);
              }
	}
	if(roleId=='<fmt:message key="EHF.Claims.Role.CH"/>'){
        if(buttonId=='<fmt:message key="EHF.Button.DiscussionButton"/>' || buttonId=='<fmt:message key="EHF.Button.ClearButton"/>')
        { 
  	  if(document.getElementById("chRemark").value==null || document.getElementById("chRemark").value==''||document.getElementById("chRemark").value.trim()==null || document.getElementById("chRemark").value.trim()=='')
  			{ lField=''; if(lField=='') lField="chRemark"; if(errMsg=='') errMsg=errMsg+"Please Enter Remarks <br>";}
        }else{
        	errMsg = validateCHList(errFlag,buttonId);
        }
}	
	if(roleId=='<fmt:message key="EHF.Claims.Role.EO"/>')
		errMsg= validateEO(errFlag);
	if(roleId=='<fmt:message key="EHF.Claims.Role.EOComm"/>')
		errMsg= validateEOCom(errFlag);	
	if(roleId=='<fmt:message key="EHF.Claims.Role.ACO"/>')
		errMsg = validateACOList(errFlag);	 
	
	    if (buttonId == '<fmt:message key="EHF.Button.Initiate"/>')
			displayMsg = '<fmt:message key="EHF.Button.Msg.Initiate"/>';
		if (buttonId == '<fmt:message key="EHF.Button.Forward"/>')
			displayMsg = '<fmt:message key="EHF.Button.Msg.Forward"/>';
		if (buttonId == '<fmt:message key="EHF.Button.Approve"/>')
			displayMsg = '<fmt:message key="EHF.Button.Msg.Approve"/>';
		if (buttonId == '<fmt:message key="EHF.Button.Reject"/>')
			displayMsg = '<fmt:message key="EHF.Button.Msg.Reject"/>';
		if (buttonId == '<fmt:message key="EHF.Button.Pending"/>')
			displayMsg = '<fmt:message key="EHF.Button.Msg.Pending"/>';

		if (buttonId == '<fmt:message key="EHF.Button.RecmdApp"/>')
			displayMsg = '<fmt:message key="EHF.Button.Msg.RecmdApp"/>';
		if (buttonId == '<fmt:message key="EHF.Button.RecmdRej"/>')
		    displayMsg = '<fmt:message key="EHF.Button.Msg.RecmdRej"/>';
		if (buttonId == '<fmt:message key="EHF.Button.RecmdPend"/>')
			displayMsg = '<fmt:message key="EHF.Button.Msg.RecmdPend"/>';
		if (buttonId == '<fmt:message key="EHF.Button.Save"/>')
			displayMsg = '<fmt:message key="EHF.Button.Msg.Save"/>';
		if (buttonId == '<fmt:message key="EHF.Button.Submit"/>')
			displayMsg = '<fmt:message key="EHF.Button.Msg.Submit"/>';
		if (buttonId == '<fmt:message key="EHF.Button.Verify"/>')
			displayMsg = '<fmt:message key="EHF.Button.Msg.Verify"/>';
		if (buttonId == '<fmt:message key="EHF.Button.initErro"/>')
			displayMsg = '<fmt:message key="EHF.Button.Msg.initErro"/>';
		if (buttonId == '<fmt:message key="EHF.Button.updateErro"/>')
			displayMsg = '<fmt:message key="EHF.Button.Msg.updateErr"/>';
		if (buttonId == '<fmt:message key="EHF.Button.update"/>')
			displayMsg = '<fmt:message key="EHF.Button.Msg.update"/>';
		if (buttonId == '<fmt:message key="EHF.Button.DiscussionButton"/>')
		    displayMsg = 'Do you want keep it for Discussion?';
		if (buttonId == '<fmt:message key="EHF.Button.ClearButton"/>')
		    displayMsg = 'Do you want to clear the case?';
		 						
		if (!errMsg == '') {
          
			var fr = partial(focusFieldView, lField);
			jqueryAlertMsg('Claim mandatory fields', errMsg, fr);
			return false;
		} else {
			
			var caseId = '<bean:write name="claimsFlowForm" property="caseId" />';
			var xmlhttp;
			var url;
			if (window.XMLHttpRequest) {
				xmlhttp = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			} else {
				jqueryAlertMsg('Browser Validation',
						"Your Browser Does Not Support XMLHTTP!");
			}

			url = '/<%=context%>/preauthDetails.do?actionFlag=checkMandatoryAttch&caseId='+caseId+'&attachType=ehfClaim&callType=Ajax';
		 xmlhttp.onreadystatechange=function()
			{ 
			    if(xmlhttp.readyState==4)
			    {	
			    	 var resultArray=xmlhttp.responseText;
				        var resultArray = resultArray.split("*");
				        if(resultArray[0]!=null)
				        {	  
					        
				        	if(resultArray[0]=="SessionExpired"){
					    		var fr = partial(parent.sessionExpireyClose());
					    		jqueryInfoMsg('Session Validation', "Session has been expired" ,fr);
					    		
					    		}
				    		else if(resultArray[0] =='success')
				        	   {
				    			
				    			if((roleId=='<fmt:message key="EHF.Claims.Role.CTD"/>' || roleId=='<fmt:message key="EHF.Claims.Role.CH"/>') 
				    					&& (buttonId=='CD24' || buttonId=='CD32' || buttonId=='CD73' ))
				    				{
				    					var billAmt=document.getElementById("billAmt").value;
				    					var apprvAmt;
				    					//Added by sravan for CTD Approve units in dental
				    					var subVal=null;
				    					var alertCont='';
				    					var count=0;
				    					if(dentalSurg!=null && dentalSurg=='Y' &&  roleId=='<fmt:message key="EHF.Claims.Role.CTD"/>' && buttonId=='CD32')
				    					{
				    						document.getElementById('caseUnits').value='';
				    						for(var i=0;i<100;i++)
				    							{
				    								
				    								if(document.getElementById('ctdProcUnits'+i)!=null)
				    									{
				    										if(document.getElementById('ctdProcUnits'+i).value=='-1')
				    											{
				    												alertCont='Please select Actual Treated Units';
				    												focusId='ctdProcUnits'+i;
				    											}
				    									}
				    								if(alertCont.length>0 && alertCont!='' && focusId!='' && focusId.length>0)
													{
														alert(alertCont);
														focusBox(document.getElementById(focusId));
														count++;
														return false;
													}
				    								
				    								
				    							}
			    						if(count==0)
			    						{	
		    								for(var i=0;i<100;i++)
		    									{
		    										if(document.getElementById('ctdProcUnits'+i)!=null)
		    											{
		    												if(subVal==null)
		    													subVal=document.getElementById('ctdProcUnits'+i).getAttribute("name")+'@'+document.getElementById('ctdProcUnits'+i).value;
		    												else
		    													subVal+='~'+document.getElementById('ctdProcUnits'+i).getAttribute("name")+'@'+document.getElementById('ctdProcUnits'+i).value;
		    												
		    											}
		    										else
		    										{
		    											break;
		    										}
		    									}
				    					}
		    								document.getElementById('caseUnits').value=subVal;	
				    					}
				    				
				    					if(dentalSurg!=null && dentalSurg=='Y' &&  roleId=='<fmt:message key="EHF.Claims.Role.CH"/>' && buttonId=='CD24')
				    					{
				    						document.getElementById('caseUnits').value='';
				    						for(var i=0;i<100;i++)
				    							{
				    							
				    								if(document.getElementById('chProcUnits'+i)!=null)
				    									{
				    										if(document.getElementById('chProcUnits'+i).value=='-1')
				    											{
				    												alertCont='Please select Actual Treated Units';
				    												focusId='chProcUnits'+i;
				    											}
				    									}
				    								if(alertCont.length>0 && alertCont!='' && focusId!='' && focusId.length>0)
													{
														alert(alertCont);
														focusBox(document.getElementById(focusId));
														count++;
														return false;
													}
				    								
				    								
				    							}
			    						if(count==0)
			    						{	
		    								for(var i=0;i<100;i++)
		    									{
		    										if(document.getElementById('chProcUnits'+i)!=null)
		    											{
		    												if(subVal==null)
		    													subVal=document.getElementById('chProcUnits'+i).getAttribute("name")+'@'+document.getElementById('chProcUnits'+i).value;
		    												else
		    													subVal+='~'+document.getElementById('chProcUnits'+i).getAttribute("name")+'@'+document.getElementById('chProcUnits'+i).value;
		    												
		    											}
		    										else
		    										{
		    											break;
		    										}
		    									}
				    					}
		    								document.getElementById('caseUnits').value=subVal;	
				    					}
				    					
				    					
				    					
				    					if(roleId=='<fmt:message key="EHF.Claims.Role.CTD"/>')
				    						{
					    						if(errFlag!=null && errFlag!='' && errFlag!=' ' && errFlag=='Yes')
			    									{
				    									apprvAmt=document.getElementById("errAppAmount").value;
				    									if('${claimsFlowForm.errAmount}'!=null)
				    										billAmt='${claimsFlowForm.errAmount}';
			    									}
					    						else
					    							apprvAmt=document.getElementById("ctdAprAmt").value;
				    						}
				    					if(roleId=='<fmt:message key="EHF.Claims.Role.CH"/>')
				    						{
				    							if(errFlag!=null && errFlag!='' && errFlag!=' ' && errFlag=='Yes')
				    								{
				    									apprvAmt=document.getElementById("errChAppAmount").value;
				    									if('${claimsFlowForm.errAmount}'!=null)
				    										billAmt='${claimsFlowForm.errAmount}';
				    								}	
				    							else
				    								apprvAmt=document.getElementById("chEntAprAmt").value;
				    						}
				    					
				    					if(Number(billAmt)>Number(apprvAmt))
				    						{
							    				if(confirm('You have opted to approve \u20B9 '+apprvAmt+' as the Claim Approved Amount , which is less than the Bill Amount \u20B9 '+billAmt+' for this Case.Do you still want to Continue ?'))
													{
								    					var fr = partial(claimSubmit,buttonId,errFlag);
											       		jqueryConfirmMsg('Claim Submission',displayMsg,fr);
													}
				    						}
				    					else
						    				{
							    				var fr = partial(claimSubmit,buttonId,errFlag);
									       		jqueryConfirmMsg('Claim Submission',displayMsg,fr);
						    				}
				    					
				    				}
				    			else
				    				{
					    				var fr = partial(claimSubmit,buttonId,errFlag);
							       		jqueryConfirmMsg('Claim Submission',displayMsg,fr);
				    				}
				    				
					       		  
				        	   }
				           else
				        	   {
				        	   jqueryAlertMsg('Claim mandatory check',resultArray[0]);			        	  
				        	  return;
				        	   }
				        } 
			    }
			}
		 xmlhttp.open("Post",url,true);
			xmlhttp.send(null);		
	}
}
function claimSubmit(buttonId,errFlag)
{   if(document.getElementById(buttonId)!=null)
	document.getElementById(buttonId).disabled = true;
	
	if('${pendFlag}'!=null && '${pendFlag}' == 'Y')
		{
			document.getElementById("billAmt").readonly=false;
			//document.getElementById("billDt").readonly=false;
			document.getElementById("billDt").disabled=false;
		}
	var totalClaim='';
	   var deduction ='';
	   var cpdAprAmt ='';	var chNabhAmt='';var chfAprAmt='';
	   var ctdNabhAmt=''; 
	   var ctdFinAprAmt='';
	   if(newCpd!=null && newCpd=='N')
	   {
	   if(roleId=='<fmt:message key="EHF.Claims.Role.CPD"/>'||roleId=='<fmt:message key="EHF.Claims.Role.COCCMT"/>' ||roleId=='<fmt:message key="EHF.Claims.Role.ORGCMT"/>' ||roleId=='<fmt:message key="EHF.Claims.Role.HUBS"/>'){  
		  totalClaim=document.getElementById("totalClaim").value;
		  deduction =  document.getElementById("deduction").value;
		  cpdAprAmt = document.getElementById("cpdAprAmt").value;
	   }
	   }
	   if(newCpd!=null && newCpd=='Y')
	   {
   if(roleId=='<fmt:message key="EHF.Claims.Role.CPD"/>'||roleId=='<fmt:message key="EHF.Claims.Role.COCCMT"/>' ||roleId=='<fmt:message key="EHF.Claims.Role.ORGCMT"/>' ||roleId=='<fmt:message key="EHF.Claims.Role.HUBS"/>'){  
	  totalClaim=document.getElementById("totalClaim").value;
	  deduction =  document.getElementById("deductionOrg").value;
	  cpdAprAmt = document.getElementById("cpdAprAmtOrg").value;
	  
   }
	   }
	   if(roleId=='<fmt:message key="EHF.Claims.Role.CH"/>'){  
		 //  chNabhAmt = document.getElementById("chNabhAmt").value;
		   chfAprAmt = document.getElementById("chAprAmt").value;
		   }
	   if(roleId=='<fmt:message key="EHF.Claims.Role.COCTD"/>' || roleId=='<fmt:message key="EHF.Claims.Role.ORGCTD"/>'){  
		   if(document.getElementById("ctdNabhAmt")!=null){ctdNabhAmt = document.getElementById("ctdNabhAmt").value;}
		   if(document.getElementById("ctdFinAprAmt")!=null){ctdFinAprAmt = document.getElementById("ctdFinAprAmt").value;}
		   }
	   var url ="/Operations/ClaimsFlow.do?actionFlag=saveClaimDtls&CaseId="+document.forms[0].caseId.value+"&actionDone="+buttonId+"&totalClaim="+totalClaim+"&errClaimFlag="+errFlag+"&deduction="+deduction+"&cpdAprAmt="+cpdAprAmt+"&chNabhAmt="+chNabhAmt+"&chfAprAmt="+chfAprAmt+"&ctdNabhAmt="+ctdNabhAmt+"&ctdFinAprAmt="+ctdFinAprAmt;
	   if(roleId=='<fmt:message key="EHF.Claims.Role.CEX"/>'||roleId=='<fmt:message key="EHF.Claims.Role.COCCEX"/>'){  
		   surgDtCheck=document.getElementById("surgDtCheck").value;
		   admDtCheck =  document.getElementById("admDtCheck").value;
		   dischargeDtCheck = document.getElementById("dischargeDtCheck").value;
		   url = url +"&admDtCheck="+admDtCheck+"&surgDtCheck="+surgDtCheck+"&dischargeDtCheck="+dischargeDtCheck;
		   }
	   if(roleId=='<fmt:message key="EHF.Claims.Role.EOComm"/>'){  
	   var eoComNabhAmt = 0;
		if(document.getElementById("eoComNabhAmt")!=null)	
		 eoComNabhAmt = document.getElementById("eoComNabhAmt").value;
		  
		 
		   var eoComfAprAmt = document.getElementById("eoComAprAmt").value;
		   url = url +"&eoComNabhAmt="+eoComNabhAmt+"&eoComfAprAmt="+eoComfAprAmt;
		   }		
		  document.forms[0].action=url;		    
		  document.forms[0].submit(); 
}
function validateMedco(errFlag)
{
	
	var errMsg='';
	lField='';
	var pendFlag = '${pendFlag}';
	if(pendFlag!=null && pendFlag != 'Y')
		{
	if(errFlag=="Yes"){
		if(document.getElementById("errMedRemark").value==null || document.getElementById("errMedRemark").value==''||document.getElementById("errMedRemark").value.trim()==null || document.getElementById("errMedRemark").value.trim()=='')
		{ 
			if(lField=='')
			lField="errMedRemark";
			if(errMsg=='')  errMsg=errMsg+"Please Enter Remarks <br>";}
		
		}else{
	if(document.getElementById("billAmt").value==null || document.getElementById("billAmt").value=='')
		
	{ 
		
		if(lField=='') lField="billAmt";
	if(errMsg=='') errMsg=errMsg+"Please Enter Bill Amount <br>";}
	else{
		if(document.getElementById("billAmt").value*1<"2"){
			if(lField=='') lField="billAmt"; 
			document.getElementById("billAmt").value="";
			if(errMsg=='')  errMsg=errMsg+"Bill Amount cannot be less than Rs.2 <br>";
		}
		var billAmt = document.getElementById("billAmt").value;
		var PackAmt = document.forms[0].packageAmt.value;
        var claiminitAmt = document.forms[0].claimInitAmt.value;
		
		if(parseInt(billAmt*1)>parseInt(claiminitAmt*1)){
			if(errMsg=='')  errMsg=errMsg+"Bill Amount Cannot be greater than Claim Amount. <br>";
			if(lField=='') lField="billAmt";
			}		
			}
	
		}
	if(document.getElementById("billDt").value==null || document.getElementById("billDt").value=='')
	{
		if(lField=='') lField="billDt";
		if(errMsg=='') errMsg=errMsg+"Please Select Bill Date <br>";
	}
		}
	
	if(document.getElementById("medcoRemark").value==null || document.getElementById("medcoRemark").value==''||document.getElementById("medcoRemark").value.trim()==null || document.getElementById("medcoRemark").value.trim()=='')
	{ if(lField=='') lField="medcoRemark";
	if(errMsg=='')  errMsg=errMsg+"Please Enter Remarks <br>";}

	
	return errMsg;
}

function validateNonTechCheckLst(){
	var errMsg='';lField='';
	if (!document.forms[0].nameCheck[0].checked && !document.forms[0].nameCheck[1].checked)
		{if(lField=='') lField="nameCheck"; if(errMsg=='')  errMsg=errMsg+"Please Select yes or no for First checklist <br>";}
	if (!document.forms[0].genderCheck[0].checked && !document.forms[0].genderCheck[1].checked)
	{if(lField=='') lField="genderCheck"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Second checklist <br>";}
	if (!document.forms[0].benPhotoCheck[0].checked && !document.forms[0].benPhotoCheck[1].checked)
	{if(lField=='') lField="benPhotoCheck"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Third checklist <br>";}
	if(document.getElementById("caseStAdmDt").value==null || document.getElementById("caseStAdmDt").value=='')
	{
	if(lField=='') lField="caseStAdmDt"; 
	if(errMsg=='') errMsg=errMsg+"Please Select Admission Date <br>";
	}
	
    if (!document.forms[0].admDtCheck[0].checked && !document.forms[0].admDtCheck[1].checked)
	{if(lField=='') lField="admDtCheck"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Admission Date <br>";}
	
	if(document.getElementById("caseStSurgDt").value==null || document.getElementById("caseStSurgDt").value=='')
	{
		if(lField=='') lField="caseStSurgDt";
		if(errMsg=='') errMsg=errMsg+"Please Select Surgery/Therapy  Date <br>";
	}
	if (!document.forms[0].surgDtCheck[0].checked && !document.forms[0].surgDtCheck[1].checked)
	{if(lField=='') lField="surgDtCheck"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Surgery/Therapy Date <br>";}
	
	if(document.getElementById("caseStDischrgDt").value==null || document.getElementById("caseStDischrgDt").value=='')
	{
		if(lField=='') lField="caseStDischrgDt";
		if(errMsg=='') errMsg=errMsg+"Please Select Discharge/Death Date <br>";
	}
	if (!document.forms[0].dischargeDtCheck[0].checked && !document.forms[0].dischargeDtCheck[1].checked)
	{if(lField=='') lField="dischargeDtCheck"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Discharge/Death Date <br>";}
	
	if (!document.forms[0].docVer1[0].checked && !document.forms[0].docVer1[1].checked)
	{if(lField=='') lField="docVer1"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Document verification First checklist <br>";}
	if (!document.forms[0].docVer2[0].checked && !document.forms[0].docVer2[1].checked)
	{if(lField=='') lField="docVer2"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Document verification Second checklist <br>";}
	/*if (!document.forms[0].docVer3[0].checked && !document.forms[0].docVer3[1].checked)
	{if(lField=='') lField="docVer3"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Document verification Third checklist <br>";}*/
	if (!document.forms[0].docVer4[0].checked && !document.forms[0].docVer4[1].checked)
	{if(lField=='') lField="docVer4"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Document verification Fourth checklist <br>";}
	if (!document.forms[0].docVer5[0].checked && !document.forms[0].docVer5[1].checked)
	{if(lField=='') lField="docVer5"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Document verification Fifth checklist <br>";}
	if(document.getElementById("cexRemark").value==null || document.getElementById("cexRemark").value==''||document.getElementById("cexRemark").value.trim()==null || document.getElementById("cexRemark").value.trim()=='')
	{
		if(lField=='') lField="cexRemark";
		if(errMsg=='') errMsg=errMsg+"Please Enter Remarks <br>";
	}
	return errMsg;
}
function validateTechCheckListOrg(){
	var errMsg='';lField='';
	
	
if(document.getElementById("inputsOrg").value==null || document.getElementById("inputsOrg").value==''){ 
	if(lField=='') lField="inputsOrg";
	if(errMsg=='') errMsg=errMsg+"Please Enter Inputs(atleast 0) <br>";
}
if(document.getElementById("profFeeOrg").value==null || document.getElementById("profFeeOrg").value==''){ 
	if(lField=='') lField="profFeeOrg";
	if(errMsg=='') errMsg=errMsg+"Please Enter Professional Fee Bill(atleast 0) <br>";
}



if(document.getElementById("investBillOrg").value==null || document.getElementById("investBillOrg").value==''){ 
	if(lField=='') lField="investBillOrg";
	if(errMsg=='') errMsg=errMsg+"Please Enter Investigation Bill(atleast 0) <br>";
}
/*
if(document.getElementById("stay").value*1 > 0 && (document.getElementById("stayRemark").value==null || document.getElementById("stayRemark").value=='')){ 
	if(lField=='') lField="stayRemark";
	if(errMsg=='') errMsg=errMsg+"Please Enter Stay Remarks<br>";
}*/

if(document.getElementById("inputsOrg").value*1 > 0 && (document.getElementById("inputsRmrkOrg").value==null || document.getElementById("inputsRmrkOrg").value=='')){ 
	if(lField=='') lField="inputsRmrkOrg";
	if(errMsg=='') errMsg=errMsg+"Please Enter inputs remarks <br>";
}
if(document.getElementById("profFeeOrg").value*1 > 0 && (document.getElementById("profFeeRmrkOrg").value==null || document.getElementById("profFeeRmrkOrg").value=='')){ 
	if(lField=='') lField="profFeeRmrkOrg";
	if(errMsg=='') errMsg=errMsg+"Please Enter Professional fee bill remarks<br>";
}

if(document.getElementById("investBillOrg").value*1 > 0 && (document.getElementById("investBillRmrkOrg").value==null || document.getElementById("investBillRmrkOrg").value=='')){ 
	if(lField=='') lField="investBillRmrkOrg";
	if(errMsg=='') errMsg=errMsg+"Please Enter investigation Bill remarks<br>";
}

//if(document.getElementById("cpdAprAmt").value==null || document.getElementById("cpdAprAmt").value=='')
//	errMsg=errMsg+"Please Enter Final Approve Amount <br>";
   if (!document.forms[0].techCheckOrg1[0].checked && !document.forms[0].techCheckOrg1[1].checked){
	   if(errMsg=='') errMsg=errMsg+"Please Select yes or no for First checklist <br>";
	if(lField=='') lField="techCheckOrg1";//if(lField=='') lField="totalClaim";
	   }
if (!document.forms[0].techCheckOrg2[0].checked && !document.forms[0].techCheckOrg2[1].checked){ 
	if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Second checklist <br>";
	if(lField=='') lField="techCheckOrg2";
}
if (!document.forms[0].techCheckOrg3[0].checked && !document.forms[0].techCheckOrg3[1].checked){ 
	if(lField=='') lField="techCheckOrg3"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Third checklist <br>";
}
if (!document.forms[0].techCheckOrg4[0].checked && !document.forms[0].techCheckOrg4[1].checked){ 
	if(lField=='') lField="techCheckOrg4"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Fourth checklist <br>";
}
if(document.getElementById("claimPanelRemarkOrg").value==null || document.getElementById("claimPanelRemarkOrg").value==''||document.getElementById("claimPanelRemarkOrg").value.trim()==null || document.getElementById("claimPanelRemarkOrg").value.trim()==''){ 
    if(lField=='') lField="claimPanelRemarkOrg";
    if(errMsg=='') errMsg=errMsg+"Please Enter Remarks <br>";
}

if(parseInt(document.getElementById("deductionOrg").value*1)>parseInt(document.getElementById("totalClaim").value*1)){ 
    if(lField=='') lField="deductionOrg";
    if(errMsg=='') errMsg=errMsg+"Deductions cannot be greater than Total claim Amount <br>";
}


if(parseInt(document.getElementById("cpdAprAmtOrg").value*1)>parseInt(document.getElementById("totalClaim").value*1)){ 
if(lField=='') {lField="cpdAprAmtOrg";document.getElementById("cpdAprAmtOrg").value='';
if(errMsg=='') errMsg=errMsg+"Final Approve Amount cannot be greater than Total claim Amount <br>";}
} 
    
	return errMsg;
}
function validateTechCheckList(){
	var errMsg='';lField='';
	
	if(document.getElementById("totalClaim").value==null || document.getElementById("totalClaim").value==''){ 

		if(lField=='') lField="totalClaim";
		if(errMsg=='') errMsg=errMsg+"Please Enter Total Claim (Rs.) <br>";
	}
	//if(document.getElementById("deduction").value==null || document.getElementById("deduction").value=='')
	//	errMsg=errMsg+"Please Enter Deduction Recommended (Rs.) <br>";
	/*if(document.getElementById("stay").value==null || document.getElementById("stay").value==''){ 
		if(lField=='') lField="stay";
		if(errMsg=='') errMsg=errMsg+"Please Enter Stay (atleast 0)<br>";
}*/
	if(document.getElementById("inputs").value==null || document.getElementById("inputs").value==''){ 
		if(lField=='') lField="inputs";
		if(errMsg=='') errMsg=errMsg+"Please Enter Inputs(atleast 0) <br>";
	}
	if(document.getElementById("profFee").value==null || document.getElementById("profFee").value==''){ 
		if(lField=='') lField="profFee";
		if(errMsg=='') errMsg=errMsg+"Please Enter Professional Fee Bill(atleast 0) <br>";
	}

	

	if(document.getElementById("investBill").value==null || document.getElementById("investBill").value==''){ 
		if(lField=='') lField="investBill";
		if(errMsg=='') errMsg=errMsg+"Please Enter Investigation Bill(atleast 0) <br>";
	}
	/*
	if(document.getElementById("stay").value*1 > 0 && (document.getElementById("stayRemark").value==null || document.getElementById("stayRemark").value=='')){ 
		if(lField=='') lField="stayRemark";
		if(errMsg=='') errMsg=errMsg+"Please Enter Stay Remarks<br>";
}*/

	if(document.getElementById("inputs").value*1 > 0 && (document.getElementById("inputsRmrk").value==null || document.getElementById("inputsRmrk").value=='')){ 
		if(lField=='') lField="inputsRmrk";
		if(errMsg=='') errMsg=errMsg+"Please Enter inputs remarks <br>";
}
	if(document.getElementById("profFee").value*1 > 0 && (document.getElementById("profFeeRmrk").value==null || document.getElementById("profFeeRmrk").value=='')){ 
		if(lField=='') lField="profFeeRmrk";
		if(errMsg=='') errMsg=errMsg+"Please Enter Professional fee bill remarks<br>";
}

	if(document.getElementById("investBill").value*1 > 0 && (document.getElementById("investBillRmrk").value==null || document.getElementById("investBillRmrk").value=='')){ 
		if(lField=='') lField="investBillRmrk";
		if(errMsg=='') errMsg=errMsg+"Please Enter investigation Bill remarks<br>";
}

	//if(document.getElementById("cpdAprAmt").value==null || document.getElementById("cpdAprAmt").value=='')
	//	errMsg=errMsg+"Please Enter Final Approve Amount <br>";
	   if (!document.forms[0].techCheck1[0].checked && !document.forms[0].techCheck1[1].checked){
		   if(errMsg=='') errMsg=errMsg+"Please Select yes or no for First checklist <br>";
		if(lField=='') lField="techCheck1";//if(lField=='') lField="totalClaim";
		   }
	if (!document.forms[0].techCheck2[0].checked && !document.forms[0].techCheck2[1].checked){ 
		if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Second checklist <br>";
		if(lField=='') lField="techCheck2";
	}
	if (!document.forms[0].techCheck3[0].checked && !document.forms[0].techCheck3[1].checked){ 
		if(lField=='') lField="techCheck3"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Third checklist <br>";
	}
	if (!document.forms[0].techCheck4[0].checked && !document.forms[0].techCheck4[1].checked){ 
		if(lField=='') lField="techCheck4"; if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Fourth checklist <br>";
	}
    if(document.getElementById("claimPanelRemark").value==null || document.getElementById("claimPanelRemark").value==''||document.getElementById("claimPanelRemark").value.trim()==null || document.getElementById("claimPanelRemark").value.trim()==''){ 
        if(lField=='') lField="claimPanelRemark";
        if(errMsg=='') errMsg=errMsg+"Please Enter Remarks <br>";
    }

    if(parseInt(document.getElementById("deduction").value*1)>parseInt(document.getElementById("totalClaim").value*1)){ 
        if(lField=='') lField="deduction";
        if(errMsg=='') errMsg=errMsg+"Deductions cannot be greater than Total claim Amount <br>";
    }

    
if(parseInt(document.getElementById("cpdAprAmt").value*1)>parseInt(document.getElementById("totalClaim").value*1)){ 
	if(lField=='') {lField="cpdAprAmt";document.getElementById("cpdAprAmt").value='';
	if(errMsg=='') errMsg=errMsg+"Final Approve Amount cannot be greater than Total claim Amount <br>";}
}   
    
	return errMsg;
}

function validateCTDList(errFlag){
	
	var errMsg='';lField='';
	if(errFlag=="Yes"){
		if(document.getElementById("errAppAmount").value==null || document.getElementById("errAppAmount").value=='')
			{if(lField=='') lField="errAppAmount";
			if(errMsg=='') errMsg=errMsg+"Please Enter Final Approve Amount <br>";
			}
		else
		{if(document.getElementById("errAppAmount").value*1<"2"){
			if(lField=='') lField="errAppAmount"; 
			document.getElementById("errAppAmount").value="";
			if(errMsg=='') errMsg=errMsg+"Final Approve Amount cannot be less than Rs.2 <br>";
		}}
		if(document.getElementById("errCtdRemark").value==null || document.getElementById("errCtdRemark").value==''||document.getElementById("errMedRemark").value.trim()==null || document.getElementById("errMedRemark").value.trim()=='')
		{if(lField=='') lField="errCtdRemark";
		if(errMsg=='') errMsg=errMsg+"Please Enter Remarks <br>";}

		if(parseInt(document.getElementById("errAppAmount").value*1)>parseInt(document.forms[0].errAmount.value*1))
		{if(lField=='') lField="errAppAmount";
		if(errMsg=='') errMsg=errMsg+"Final Approve Amount cannot be greater than Erroneous claim Amount <br>";
		}
		
		}else{
			
	if(document.getElementById("ctdAprAmt").value==null || document.getElementById("ctdAprAmt").value=='')
	{if(lField=='') lField="ctdAprAmt"; if(errMsg=='') errMsg=errMsg+"Please Enter Final Approve Amount <br>"; }
	else
		{if(document.getElementById("ctdAprAmt").value*1<"2"){
			if(lField=='') lField="ctdAprAmt"; 
			document.getElementById("ctdAprAmt").value="";
			if(errMsg=='') errMsg=errMsg+"Final Approve Amount cannot be less than Rs.2 <br>";
		}}
	if (!document.forms[0].trustDoc1[0].checked && !document.forms[0].trustDoc1[1].checked)
		{if(lField=='') lField="trustDoc1";if(errMsg=='') errMsg=errMsg+"Please Select yes or no for First checklist <br>";}
	if (!document.forms[0].trustDoc2[0].checked && !document.forms[0].trustDoc2[1].checked)
		{if(lField=='') lField="trustDoc2";if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Second checklist <br>";}
	if (!document.forms[0].trustDoc3[0].checked && !document.forms[0].trustDoc3[1].checked)
		{if(lField=='') lField="trustDoc3";if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Third checklist <br>";}
	if (!document.forms[0].trustDoc4[0].checked && !document.forms[0].trustDoc4[1].checked)
	{if(lField=='') lField="trustDoc4";if(errMsg=='') errMsg=errMsg+"Please Select yes or no for Fourth checklist <br>";}
	if(document.getElementById("ctdRemark").value==null || document.getElementById("ctdRemark").value==''||document.getElementById("ctdRemark").value.trim()==null || document.getElementById("ctdRemark").value.trim()=='')
		{if(lField=='') lField="ctdRemark"; if(errMsg=='') errMsg=errMsg+"Please Enter Remarks <br>"; }

    if(parseInt(document.getElementById("ctdAprAmt").value*1)>parseInt(document.getElementById("billAmt").value*1))
    {if(lField=='') lField="ctdAprAmt"; document.getElementById("ctdAprAmt").value='';if(errMsg=='') errMsg=errMsg+"Final Approve Amount cannot be greater than Bill Amount <br>";
    	if(document.getElementById("ctdNabhAmt")!=null)
    		document.getElementById("ctdNabhAmt").value=0;
    	if(document.getElementById("ctdFinAprAmt")!=null)
    		document.getElementById("ctdFinAprAmt").value=0;
    }

	}
	return errMsg;
}
function validateCHList(errFlag,buttonId){

	var errMsg=''; 
	lField=''; 
	if(errFlag=="Yes"){

		   if(document.getElementById("errChAppAmount").value==null || document.getElementById("errChAppAmount").value=='')
		   {if(lField=='') lField="errChAppAmount"; if(errMsg=='') errMsg=errMsg+"Please Enter Final Approve Amount <br>";}
		   else
			{if(document.getElementById("errChAppAmount").value*1<"2"){
				if(lField=='') lField="errChAppAmount"; 
				document.getElementById("errChAppAmount").value="";
				if(errMsg=='') errMsg=errMsg+"Final Approve Amount cannot be less than Rs.2 <br>";
			}} 
		if(document.getElementById("errChRemark").value==null || document.getElementById("errChRemark").value==''||document.getElementById("errChRemark").value.trim()==null || document.getElementById("errChRemark").value.trim()=='')
		{if(lField=='') lField="errChRemark"; if(errMsg=='') errMsg=errMsg+"Please Enter Remarks <br>"; }

		if(parseInt(document.getElementById("errChAppAmount").value*1)>parseInt(document.forms[0].errAmount.value*1))
		{if(lField=='') lField="errChAppAmount"; if(errMsg=='') errMsg=errMsg+"Final Approve Amount cannot be greater than Erroneous claim Amount <br>"; }
		
		
		}else{	
	if((buttonId != null /* && buttonId == 'CD24' */) && (document.getElementById("chEntAprAmt").value==null || document.getElementById("chEntAprAmt").value==''))
	{
		if(lField=='') lField="chEntAprAmt"; if(errMsg=='') errMsg=errMsg+"Please Enter Approve Amount <br>"; }
	else
	{
       /* if(buttonId=='<fmt:message key="EHF.Button.Approve"/>'){ */
		if(document.getElementById("chEntAprAmt").value*1<"2"){
		if(lField=='') lField="chEntAprAmt"; 
		document.getElementById("chEntAprAmt").value="";
		if(errMsg=='') errMsg=errMsg+"Final Approve Amount cannot be less than Rs.2 <br>";
		/* } */
	}}
	if(document.getElementById("chRemark").value==null || document.getElementById("chRemark").value==''||document.getElementById("chRemark").value.trim()==null || document.getElementById("chRemark").value.trim()=='')
	{if(lField=='') lField="chRemark"; if(errMsg=='') errMsg=errMsg+"Please Enter Remarks <br>";}

	
	if(parseInt(document.getElementById("chEntAprAmt").value*1)>parseInt(document.getElementById("billAmt").value*1))
	{if(lField=='') lField="chEntAprAmt"; if(errMsg=='') errMsg=errMsg+"Approve Amount cannot be greater than Bill Amount <br>"; }
		}
	
	return errMsg;
}
function validateEO(errFlag){
	var errMsg='';lField='';
    if(document.getElementById("eoAprAmt").value==null || document.getElementById("eoAprAmt").value=='')
	 {
    	if(lField=='') lField="eoAprAmt"; errMsg=errMsg+"Please Enter Final Approve Amount <br>"; 
	 }	
    if(parseInt(document.getElementById("eoAprAmt").value*1)>parseInt(document.getElementById("chAprAmt").value*1))
	{
    	if(lField=='') lField="eoAprAmt"; errMsg=errMsg+"Final Approve Amount cannot be greater than CH Approve Amount <br>"; 
    }
    
    if(document.getElementById("eoAprAmt").value*1<"2"){
		if(lField=='') lField="eoAprAmt"; 
		document.getElementById("eoAprAmt").value="";
		if(errMsg=='') errMsg=errMsg+"Final Approve Amount cannot be less than Rs.2 <br>";
	}
    
    
	if(document.getElementById("eoRemark").value==null || document.getElementById("eoRemark").value==''||document.getElementById("eoRemark").value.trim()==null || document.getElementById("eoRemark").value.trim()=='')
	{
		if(lField=='') lField="eoRemark"; if(errMsg=='') errMsg=errMsg+"Please Enter Remarks <br>";
	}	
	return errMsg;
}
function validateEOCom(errFlag){
	var errMsg='';lField='';
    	

	if(document.forms[0].paySent2[0].checked)
	{   if(document.getElementById("eoComEntAprAmt").value==null || document.getElementById("eoComEntAprAmt").value=='')
	 {if(lField=='') lField="eoComEntAprAmt"; errMsg=errMsg+"Please Enter Approve Amount <br>"; }	
	
	if(document.getElementById("eoComEntAprAmt").value*1<"2"){
			if(lField=='') lField="eoComEntAprAmt"; 
			document.getElementById("eoComEntAprAmt").value="";
			if(errMsg=='') errMsg=errMsg+"Final Approve Amount cannot be less than Rs.2 <br>";
		}
	    
	
	//CMS13242 CEO Changes option can be given to committee login to edit package cost not exceeding package cost
   // var claiminitAmt = document.forms[0].claimInitAmt.value;
    if(parseInt(document.getElementById("eoComEntAprAmt").value*1)>parseInt(document.getElementById("billAmt").value*1))
	{if(lField=='') lField="eoComEntAprAmt"; errMsg=errMsg+"Approve Amount cannot be greater than Claim Initiated Amount <br>"; }
		user=document.forms[0].allUsers.value;
		remarks=document.getElementById("eoComSendRemarks").value;
		if(remarks==null||remarks==''||remarks.trim()==null||remarks.trim()=='')
		{if(lField=='') lField="eoComSendRemarks"; if(errMsg=='') errMsg=errMsg+"Please Enter eo send back Remarks <br>";}
		if(user==null || user=="-1" )
		{if(lField=='') lField="allUsers";if(errMsg=='') errMsg=errMsg+"Please Select User"; }
	}else{
		if(document.getElementById("eoComEntAprAmt").value==null || document.getElementById("eoComEntAprAmt").value=='')
		 {if(lField=='') lField="eoComEntAprAmt"; errMsg=errMsg+"Please Enter Approve Amount <br>"; }
		
		if(document.getElementById("eoComEntAprAmt").value*1<"2"){
				if(lField=='') lField="eoComEntAprAmt"; 
				document.getElementById("eoComEntAprAmt").value="";
				if(errMsg=='') errMsg=errMsg+"Final Approve Amount cannot be less than Rs.2 <br>";
			}
		
	    //CMS13242 CEO Changes option can be given to committee login to edit package cost not exceeding package cost
	   // var claiminitAmt = document.forms[0].claimInitAmt.value;
	    if(parseInt(document.getElementById("eoComEntAprAmt").value*1)>parseInt(document.getElementById("billAmt").value*1))
		{if(lField=='') lField="eoComEntAprAmt"; errMsg=errMsg+"Approve Amount cannot be greater than Claim Initiated Amount <br>"; }
		if(document.getElementById("eoComRemark").value==null || document.getElementById("eoComRemark").value==''||document.getElementById("eoComRemark").value.trim()==null || document.getElementById("eoComRemark").value.trim()=='')
		{if(lField=='') lField="eoComRemark"; if(errMsg=='') errMsg=errMsg+"Please Enter Remarks <br>";}
	}
	return errMsg;
}
function validateACOList(errFlag){
	var errMsg='';lField='';
	if(errFlag=="Yes"){
		  // if(document.getElementById("errAcoAprAmt").value==null || document.getElementById("errAcoAprAmt").value=='')
		  // {if(lField=='') lField="errAcoAprAmt"; errMsg=errMsg+"Please Enter Final Approve Amount <br>";} 
		if(document.getElementById("errAcoRemark").value==null || document.getElementById("errAcoRemark").value==''||document.getElementById("errAcoRemark").value.trim()==null || document.getElementById("errAcoRemark").value.trim()=='')
		{if(lField=='') lField="errAcoRemark"; if(errMsg=='') errMsg=errMsg+"Please Enter Remarks <br>"; }

		//if(parseInt(document.getElementById("errAcoAprAmt").value*1)>parseInt(document.getElementById("chAprAmt").value*1))
		//{if(lField=='') lField="errAcoAprAmt"; errMsg=errMsg+"Final Approve Amount cannot be greater than Approval Amount Of CH <br>"; }
		
		
		}else{
	//if(document.getElementById("acoAprAmt").value==null || document.getElementById("acoAprAmt").value=='')
	//{if(lField=='') lField="acoAprAmt"; errMsg=errMsg+"Please Enter Final Approve Amount <br>"; }	
	if(document.getElementById("acoRemark").value==null || document.getElementById("acoRemark").value==''||document.getElementById("acoRemark").value.trim()==null || document.getElementById("acoRemark").value.trim()=='')
	{if(lField=='') lField="acoRemark"; if(errMsg=='') errMsg=errMsg+"Please Enter Remarks <br>";}

	
	//if(parseInt(document.getElementById("acoAprAmt").value*1)>parseInt(document.getElementById("chAprAmt").value*1))
	//{if(lField=='') lField="acoAprAmt"; errMsg=errMsg+"Final Approve Amount cannot be greater than CH Approve Amount <br>"; }
		}
	return errMsg;
}
function RemarksVisible()
{
	document.getElementById("InitiateErrClm").disabled = true;
    document.getElementById("errClmRmk").style.display='';
	document.getElementById("errClmInsBtn").style.display='';	
}
function focusBox(arg)
{
  aField = arg; 
  setTimeout("aField.focus()", 0);  

}
function pasteIntercept(evt){var input=document.getElementById('medcoRemark');maxLengthPaste(input,3000);}
function pasteInterceptCex(evt){var input=document.getElementById('cexRemark');maxLengthPaste(input,3000);}
//function pasteStayRemark(evt){var input=document.getElementById('stayRemark');maxLengthPaste(input,3000);}
function pasteInputsRmrk(evt){var input=document.getElementById('inputsRmrk');maxLengthPaste(input,3000);}
function pasteProfFeeRmrk(evt){var input=document.getElementById('profFeeRmrk');maxLengthPaste(input,3000);}
function pasteInvestBillRmrk(evt){var input=document.getElementById('investBillRmrk');maxLengthPaste(input,3000);}
function pasteClaimPanelRemark(evt){var input=document.getElementById('claimPanelRemark');maxLengthPaste(input,3000);}
function pasteErrCtdRemark(evt){var input=document.getElementById('errCtdRemark');maxLengthPaste(input,3000);}
function pasteCtdRemark(evt){var input=document.getElementById('ctdRemark');maxLengthPaste(input,3000);}
function pasteErrChRemark(evt){var input=document.getElementById('errChRemark');maxLengthPaste(input,3000);}
function pasteChRemark(evt){var input=document.getElementById('chRemark');maxLengthPaste(input,3000);}
function pasteEoRemark(evt){var input=document.getElementById('eoRemark');maxLengthPaste(input,3000);}
function pasteEoComRemark(evt){var input=document.getElementById('eoComRemark');maxLengthPaste(input,3000);}
function pasteErrAcoRemark(evt){var input=document.getElementById('errAcoRemark');maxLengthPaste(input,3000);}
function pasteAcoRemark(evt){var input=document.getElementById('acoRemark');maxLengthPaste(input,3000);}
function pasteErrMedRemark(evt){var input=document.getElementById('errMedRemark');maxLengthPaste(input,3000);}

var browserName=navigator.appName;
if(browserName=="Microsoft Internet Explorer")
	{
	//For validating maxlength onpaste event--For textarea fields
	if(document.getElementById("medcoRemark")!=null) {document.getElementById("medcoRemark").attachEvent("onpaste",pasteIntercept);}
	if(document.getElementById("cexRemark")!=null) {document.getElementById("cexRemark").attachEvent("onpaste",pasteInterceptCex);}
	//if(document.getElementById("stayRemark")!=null) {document.getElementById("stayRemark").attachEvent("onpaste",pasteStayRemark);}
	if(document.getElementById("inputsRmrk")!=null) {document.getElementById("inputsRmrk").attachEvent("onpaste",pasteInputsRmrk);}
	if(document.getElementById("profFeeRmrk")!=null) {document.getElementById("profFeeRmrk").attachEvent("onpaste",pasteProfFeeRmrk);}
	if(document.getElementById("investBillRmrk")!=null) {document.getElementById("investBillRmrk").attachEvent("onpaste",pasteInvestBillRmrk);}
	if(document.getElementById("claimPanelRemark")!=null) {document.getElementById("claimPanelRemark").attachEvent("onpaste",pasteClaimPanelRemark);}
	if(document.getElementById("errCtdRemark")!=null) {document.getElementById("errCtdRemark").attachEvent("onpaste",pasteErrCtdRemark);}
	if(document.getElementById("ctdRemark")!=null) {document.getElementById("ctdRemark").attachEvent("onpaste",pasteCtdRemark);}
	if(document.getElementById("errChRemark")!=null) {document.getElementById("errChRemark").attachEvent("onpaste",pasteErrChRemark);}
	if(document.getElementById("chRemark")!=null) {document.getElementById("chRemark").attachEvent("onpaste",pasteChRemark);}
	if(document.getElementById("eoRemark")!=null) {document.getElementById("eoRemark").attachEvent("onpaste",pasteEoRemark);}
	if(document.getElementById("eoComRemark")!=null) {document.getElementById("eoComRemark").attachEvent("onpaste",pasteEoComRemark);}
	if(document.getElementById("errAcoRemark")!=null) {document.getElementById("errAcoRemark").attachEvent("onpaste",pasteErrAcoRemark);}
	if(document.getElementById("acoRemark")!=null) {document.getElementById("acoRemark").attachEvent("onpaste",pasteAcoRemark);}
	if(document.getElementById("errMedRemark")!=null) {document.getElementById("errMedRemark").attachEvent("onpaste",pasteErrMedRemark);}
	}
else if(browserName == "Netscape")
	{
	if(document.getElementById("medcoRemark")!=null) {document.getElementById("medcoRemark").addEventListener("paste", pasteIntercept, false);}	
	if(document.getElementById("cexRemark")!=null) {document.getElementById("cexRemark").addEventListener("paste", pasteInterceptCex, false);}	
	//if(document.getElementById("stayRemark")!=null) {document.getElementById("stayRemark").addEventListener("onpaste",pasteStayRemark);}
	if(document.getElementById("inputsRmrk")!=null) {document.getElementById("inputsRmrk").addEventListener("onpaste",pasteInputsRmrk);}
	if(document.getElementById("profFeeRmrk")!=null) {document.getElementById("profFeeRmrk").addEventListener("onpaste",pasteProfFeeRmrk);}
	if(document.getElementById("investBillRmrk")!=null) {document.getElementById("investBillRmrk").addEventListener("onpaste",pasteInvestBillRmrk);}
	if(document.getElementById("claimPanelRemark")!=null) {document.getElementById("claimPanelRemark").addEventListener("onpaste",pasteClaimPanelRemark);}
	if(document.getElementById("errCtdRemark")!=null) {document.getElementById("errCtdRemark").addEventListener("onpaste",pasteErrCtdRemark);}
	if(document.getElementById("ctdRemark")!=null) {document.getElementById("ctdRemark").addEventListener("onpaste",pasteCtdRemark);}
	if(document.getElementById("errChRemark")!=null) {document.getElementById("errChRemark").addEventListener("onpaste",pasteErrChRemark);}
	if(document.getElementById("chRemark")!=null) {document.getElementById("chRemark").addEventListener("onpaste",pasteChRemark);}
	if(document.getElementById("eoRemark")!=null) {document.getElementById("eoRemark").addEventListener("onpaste",pasteEoRemark);}
	if(document.getElementById("eoComRemark")!=null) {document.getElementById("eoComRemark").addEventListener("onpaste",pasteEoComRemark);}
	if(document.getElementById("errAcoRemark")!=null) {document.getElementById("errAcoRemark").addEventListener("onpaste",pasteErrAcoRemark);}
	if(document.getElementById("acoRemark")!=null) {document.getElementById("acoRemark").addEventListener("onpaste",pasteAcoRemark);}
	if(document.getElementById("errMedRemark")!=null) {document.getElementById("errMedRemark").addEventListener("onpaste",pasteErrMedRemark);}
    }
    var disChargeingDate = document.forms[0].onlineDschrgeDate.value;    
$(function() {
	$( "#billDt").datepicker({
		defaultDate: "+1w",
		changeMonth: true,
		changeYear: true,
		maxDate: new Date(y, m, d),
		minDate: disChargeingDate,
		showOn: "both", 
		buttonImage: "images/calend.gif", 
        buttonText: "Calendar",
        buttonImageOnly: true ,
		numberOfMonths: 1
			
	});	
	$( "#caseStDischrgDt,#caseStSurgDt,#caseStAdmDt" ).datepicker({
		defaultDate: "+1w",
		changeMonth: true,
		maxDate: new Date(y, m, d),
		showOn: "both", 
		buttonImage: "images/calend.gif", 
        buttonText: "Calendar",
        buttonImageOnly: true ,
		changeYear: true,
		numberOfMonths: 1
		
	});
});  

function focusFieldView(el)
{
//fn_goToField(el);
focusBox(document.getElementById(el));
}

$('#billAmt').keypress(function(e) {
	var a = [];
    var k = e.which;

    for (i = 48; i < 58; i++)
        a.push(i);
    a.push(8);a.push(9);

    if (!($.inArray(k,a)>=0))
        e.preventDefault();
});	
function fn_getPkgAmnt(asriCat,icdCat,procCode,index,seqNo,procName)
{
	var ctdUnits;
	var ctdApprv="";
	var caseTherapyId = seqNo;	
	var caseId = '<bean:write name="claimsFlowForm" property="caseId" />';
	var xmlhttp;
    var url;
    if(dentalSurg!=null && dentalSurg=='Y' &&  roleId=='<fmt:message key="EHF.Claims.Role.CTD"/>')
	{	
    	ctdUnits = document.getElementById('ctdProcUnits'+index);
    	ctdApprv = document.getElementById('ctdProcUnits'+index).value;
	    if(ctdUnits!=null)
		{
			if(ctdApprv=='-1')
				{
				
					alert('Please select Actual Treated Units of '+procName);
					return false;
				}
		}
	}
    if(dentalSurg!=null && dentalSurg=='Y' &&  roleId=='<fmt:message key="EHF.Claims.Role.CH"/>')
	{	
    	ctdUnits = document.getElementById('chProcUnits'+index);
    	ctdApprv = document.getElementById('chProcUnits'+index).value;
	    if(ctdUnits!=null)
		{
			if(ctdApprv=='-1')
				{
				
					alert('Please select Actual Treated Units of '+procName);
					return false;
				}
		}
	}
    
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
	 url = "/Operations/ClaimsFlow.do?actionFlag=getUniquePrice&caseId="+caseId+"&caseTherapyId="+caseTherapyId+"&ctdApprv="+ctdApprv;
	 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {	
		    	 var resultArray=xmlhttp.responseText;
			        var resultArray = resultArray.split("*");
			        if(resultArray[0]=="SessionExpired"){
			    		alert("Session has been expired");
			    		 parent.sessionExpireyClose();
			    		// var fr = partial(parent.sessionExpireyClose);
			    		// jqueryInfoMsg('Session details',"Session has been expired",fr);
			    		}
			        else
			        {
	                bootbox.alert("Final price for the selected units is "+resultArray[0]);
	                
			        }
		    }}
	 xmlhttp.open("Post",url,true);
		xmlhttp.send(null);		
}

function fn_partialsave()
{
	var xmlhttp;
    var url;
	var subVal=null;
	var alertCont='';
	var count=0;
	var roleId='${UserRole}';
	var caseId = '<bean:write name="claimsFlowForm" property="caseId" />';
	if(dentalSurg!=null && dentalSurg=='Y' &&  roleId=='<fmt:message key="EHF.Claims.Role.CTD"/>')
	{
		document.getElementById('caseUnitsPar').value='';
		for(var i=0;i<100;i++)
			{
				
				if(document.getElementById('ctdProcUnits'+i)!=null)
					{
						if(document.getElementById('ctdProcUnits'+i).value=='-1')
							{
								alertCont='Please select Actual Treated Units';
								focusId='ctdProcUnits'+i;
							}
					}
				if(alertCont.length>0 && alertCont!='' && focusId!='' && focusId.length>0)
				{
					alert(alertCont);
					focusBox(document.getElementById(focusId));
					count++;
					return false;
				}
				
				
			}
	if(count==0)
	{	
		for(var i=0;i<100;i++)
			{
				if(document.getElementById('ctdProcUnits'+i)!=null)
					{
						if(subVal==null)
							subVal=document.getElementById('ctdProcUnits'+i).getAttribute("name")+'@'+document.getElementById('ctdProcUnits'+i).value;
						else
							subVal+='~'+document.getElementById('ctdProcUnits'+i).getAttribute("name")+'@'+document.getElementById('ctdProcUnits'+i).value;
						
					}
				else
				{
					break;
				}
			}
	}
		document.getElementById('caseUnitsPar').value=subVal;	
	}

	if(dentalSurg!=null && dentalSurg=='Y' &&  roleId=='<fmt:message key="EHF.Claims.Role.CH"/>')
	{
		document.getElementById('caseUnitsPar').value='';
		for(var i=0;i<100;i++)
			{
			
				if(document.getElementById('chProcUnits'+i)!=null)
					{
						if(document.getElementById('chProcUnits'+i).value=='-1')
							{
								alertCont='Please select Actual Treated Units';
								focusId='chProcUnits'+i;
							}
					}
				if(alertCont.length>0 && alertCont!='' && focusId!='' && focusId.length>0)
				{
					alert(alertCont);
					focusBox(document.getElementById(focusId));
					count++;
					return false;
				}
				
				
			}
	if(count==0)
	{	
		for(var i=0;i<100;i++)
			{
				if(document.getElementById('chProcUnits'+i)!=null)
					{
						if(subVal==null)
							subVal=document.getElementById('chProcUnits'+i).getAttribute("name")+'@'+document.getElementById('chProcUnits'+i).value;
						else
							subVal+='~'+document.getElementById('chProcUnits'+i).getAttribute("name")+'@'+document.getElementById('chProcUnits'+i).value;
						
					}
				else
				{
					break;
				}
			}
	}
		document.getElementById('caseUnitsPar').value=subVal;	
	}
	

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
	 url = "/Operations/ClaimsFlow.do?actionFlag=getTotalPrice&caseId="+caseId+"&roleId="+roleId+"&subVal="+subVal;
	 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {	
		    	 var resultArray=xmlhttp.responseText;
			        var resultArray = resultArray.split("*");
			        if(resultArray[0]=="SessionExpired"){
			    		alert("Session has been expired");
			    		 parent.sessionExpireyClose();
			    		// var fr = partial(parent.sessionExpireyClose);
			    		// jqueryInfoMsg('Session details',"Session has been expired",fr);
			    		}
			        else
			        {
	                bootbox.alert("Final price for the selected units is "+resultArray[0]);
	                
			        }
		    }
		    }
	 xmlhttp.open("Post",url,true);
		xmlhttp.send(null);	
	
	}
</script>
</form>
</div>
</body>
</fmt:bundle>
</html:html>