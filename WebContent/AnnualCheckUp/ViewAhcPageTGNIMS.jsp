<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.ArrayList,java.util.Iterator,java.util.List,com.ahct.patient.vo.PatientVO,com.ahct.common.vo.LabelBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ include file="/common/include.jsp"%>

<fmt:setLocale value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><fmt:message key="EHF.Title.AHC" /></title>
	<link rel="stylesheet" type="text/css"
		href="bootstrap/css/bootstrap.min.css">
		<link href="css/select2.min.css" rel="stylesheet">
	<LINK href="./css/patient.css" type="text/css" rel="stylesheet">
	<link href="css/themes/<%=themeColour%>/theme.css" rel="stylesheet"
		type="text/css" media="screen">
		<LINK href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" media="screen">
	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="js/select2.min.js"></script>
	
	<script src="js/jquery.msgBox.js" type="text/javascript"></script>
	<script src="js/patientscripts.js"></script>
	<%@ include file="/common/includeCalendar.jsp"%>
	<style>
	
	fieldset {
    border: 1px solid silver;
    margin: 0 2px;
    padding: 0.35em 0.625em 0.75em;
	}
	.info{
		color:red;
	}
	body{
	font-size:1.2em;
	}
	.btn
	{
	border-radius:20px;
	}
	.btn:hover
	{
	border-radius:5px;
	}
	.tbheader
		{
			padding:3px !important;
		}
	.tdClass{
		border:1px solid #bcd8ff;
		width:25% !important;
	}	
	.form-control{font-size:1.1em !important;font-weight:normal}
	</style>
	<logic:equal name ="annualCheckUpForm" property="medcoFlag" value="Y">
	<script>
	var ahcInvestList=new Array();
	var delAhcInvest= "";
	var save='${save}';
	var msg='<bean:write name="annualCheckUpForm" property="msg"/>';
	if(msg!=null && msg!=''){
		
		alert(msg);
		if(save!=null && save=='N')
		parent.fn_annualRegisteredPatientsView();
	}
	
	function fn_loadProcessImage() {
		document.getElementById('processImagetable').style.display = "";
		setTimeout(
				function() {
					document.getElementById('processImagetable').style.display = "none";
				}, 3000);
	}
	
	
	var investAttachLength=0;
	var cellPosition=0;

	$('[data-toggle="tooltip"]').tooltip();
	
	$('#ahcInvestAttachTable tr td').each(function (){
		this.addClass("tdClass");
	});
	var arrayLst=new Array();
	var consultAttachLst=new Array();
	var testIdLst= new Array();
	<c:if test="${attachList ne null}">
		<c:forEach items="${attachList}" var ="item">
		arrayLst.push("${item.ID}");
		testIdLst.push("${item.CALLID}");
		</c:forEach>
	</c:if>
	<c:if test="${oldConsultList ne null}">
		<c:forEach items="${oldConsultList}" var ="item">
		consultAttachLst.push("${item.VALUE}");
		
		</c:forEach>
	</c:if>
	
	
	function fn_addTestReport(val,name,price){
		
		
		var tableId=document.getElementById('ahcInvestAttachTable');
		//if(tableId !=null)
		//{
			//Creates a new Row at the Last Position of Table
			if(val.checked==true)
			{
				
				/*for (var i=0;i<arrayLst.length;i++){
				if(val.value==testIdLst[i]){
						alert("Investigation for this Test is already added");
						val.checked=false;
						return false;
					}

				}*/
			/*var row;
			//if((cellPosition%2)==0)
				row=tableId.insertRow(-1);
			//else
			//	row=tableId.rows[tableId.rows.length-1];
			row.id=val.value;
			if(row!=null)
				{
					var cell=row.insertCell(-1);
					cell.innerHTML='<td width="50%" class="tdClass" style="border:1px solid;">'+name+'</td>';
					cell.className='tdClass';
					var cell=row.insertCell(-1);
					cell.innerHTML='<td width="50%" class="tdClass" style="border:1px solid;"><input type="file" style="width:100%" onchange="javascript:checkBrowser(this)" data-toggle="tooltip" title="Choose File to Add Attachment" name="investAttach['+investAttachLength+']" id="investAttach['+investAttachLength+']"></td>';
					cell.className='tdClass';
					cellPosition++;*/
					var  ahcInvest=val.value+"~"+name+"~"+price;
				//	alert(ahcInvest);
				//	alert(investAttachLength);
					ahcInvestList[investAttachLength]=ahcInvest;
					investAttachLength++;
				//	alert(ahcInvestList);
					
				//}
		}
		
			else if(val.checked==false){
				
					/*var row = document.getElementById(val.value);
					if(row!=null){
				    row.parentNode.removeChild(row);*/
				    //alert("deleted");
				  
				    var delInvest=ahcInvestList.indexOf(val.value+"~"+name+"~"+price);
				   if(ahcInvestList.indexOf(val.value+"~"+name+"~"+price) !=-1){
					 
				   	 ahcInvestList.splice(delInvest,1);
				   	investAttachLength--;
				   }
				   else{
				    delAhcInvest=delAhcInvest+val.value+"@";
				   }
				 //   ahcInvestList.splice(row.rowIndex-1,1);
				// delIndex++;
					
							//cellPosition--;
				
				
					}
				
			

			//}	}
		}
	
	function validateInnerNum(input)
	{	
		
		//var hospGovu = document.getElementById("hospGovu").value;

		
		var a=input.value;
		var fr=partial(focusBox,input);
		var regAlphaNum=/^[0-9.]+$/;
		var hospId=document.getElementById("hospitalId").value;
		//if(hospId!=null && hospId!='EHS34')
		 /* if(hospGovu!=null && hospGovu!="G")
			{
			 
	     if(input.id=='GE1' || input.id=='GE2'){
	     	 document.getElementById('GE3').innerHTML='<input type="text" id="GE3" value="" readOnly/>';
	     }} */
			
		if(a.trim()=="")
			{
			jqueryErrorMsg('Number Validation',"Blank spaces are not allowed for "+input.title,fr);
			partial(focusBox,input);
			input.value="";
			return false;
			}
		 
		if(a.charAt(0)=="." || a.charAt(0)==" " || a.charAt(0)=="0")
		{
			jqueryErrorMsg('Number Validation',input.title+ " should not start with . or space or 0",fr);
			partial(focusBox,input);
			input.value="";
			return false;
		}
		 
		if(a.trim().search(regAlphaNum)==-1)
			{
			jqueryErrorMsg('Number Validation',"Only numbers and . are allowed for "+input.title,fr);
			partial(focusBox,input);
			input.value="";
			return false;
			}
		else
			input.value=a.trim();
		
		if(input.id=='GE1' || input.id=='GE2' || input.id=='GE11' || input.id=='GE12' || input.id=='GE13' || input.id=='GE14' || input.id=='GE15' || input.id=='BP1' || input.id=='BP2')
	if(input.value.split(".").length-1>1){
		jqueryErrorMsg('Number Validation',"Please Enter Correct Value in "+input.title,fr);
		partial(focusBox,input);
		input.value="";
		return false;
	}
		
		if(input.id=='GE1'){
			if(input.value>264){
				jqueryErrorMsg('Number Validation'," Height Should be in range of 0- 264 cm.",fr);
				partial(focusBox,input);
				input.value="";
				return false;
				}
				
			heightVar=input.value;
			
			
			var weightVar=document.forms[0].GE2.value;
			//if(hospId!="EHS34")
			/* if(hospGovu!=null && hospGovu!="G")
			{
			if(heightVar!=null && weightVar!='' && weightVar!=null){
				var heightVarr=heightVar*1/100;
				var bmiCal=((weightVar*1)/(heightVarr*heightVarr)).toFixed(2);
				document.getElementById('GE3').innerHTML='<input type=\'text\' id=\'GE3\' value=\''+bmiCal+'\' readOnly/>';
				}
			} */}
		
		if(input.id=='GE2'){
			if(input.value>300){
				jqueryErrorMsg('Number Validation', " Weight Should be in range of 0- 300 Kg.",fr);
				partial(focusBox,input);
				input.value="";
				return false;
				}
			
			
			}
		if(input.id=='GE12'){
			if(input.value>250){
				jqueryErrorMsg('Number Validation', " Pulse rate should be in range of 0-250 per minute",fr);
				partial(focusBox,input);
				input.value="";
				return false;
				}		
			}
		if(input.id=='GE13'){
			if(input.value>60){
				jqueryErrorMsg('Number Validation', " Respiration should be in range of 0-60 per minute",fr);
				partial(focusBox,input);
				input.value="";
				return false;
				}		
			}
		if(input.id=='GE14'||input.id=='GE15'||input.id=='BP1'||input.id=='BP2'){
			if(input.value>300 || input.value<0){
				jqueryErrorMsg('Number Validation',"BP range should be between 0-300 ",fr);
				partial(focusBox,input);
				input.value="";
				return false;
				}
			}	
		if(input.id=='GE11'){	
			
			
				
			var a=input.value;
			var fr=partial(focusBox,input);
			var regAlphaNumT=/^[0-9.CF]+$/;
			var inputlength=input.value.length;
			var mainStrlength=inputlength-1;
			var substr=input.value.slice(-1);
			var mainstr=input.value.substring(0,mainStrlength);
			
			if(document.forms[0].temp[0].checked==true){
				
				if(input.value<24 || input.value>45){
					jqueryErrorMsg('Temperature Validation',"Allowed temperature range is 24-45 C",fr);
					input.value="";
					return false;
					}
				}
		   else if(document.forms[0].temp[1].checked==true){
				if(input.value<75 || input.value>111){
					jqueryErrorMsg('Temperature Validation',"Allowed temperature range is 75-111 F",fr);
					input.value="";
					return false;
					}
				}
			else{
				jqueryErrorMsg('Temperature Validation',"Please Select C or F",fr);
				input.value="";
				return false;
				}				
			
		}
	}
	
	function getSubFieldType(input)
	{
		
		var examinField="";
		var id=input.value;	
		
		var maxlength=5;
		var subTypeField=document.getElementById(id+"h").value;
		var prop = document.getElementById(id+"h").name;
		if(prop=="Height (in Cm)")
		{maxlength=10;}
	     else if(prop=="Weight (in Kg)")
		{maxlength=10;}
	    else if(prop=="BMI")
		maxlength=21;
				
		if(input.checked)
		{
		if(subTypeField=='T')
			{
			if(prop!='BP Lt.Arm mm/Hg'&& prop!='BP Rt.Arm mm/Hg'&& prop!='Temperature(C/F)')
				{examinField=examinField+"<input  type='text' name='"+id+"' id='"+id+"' maxlength='"+maxlength+"' title='"+prop+" Text Field'onchange='validateInnerNum(this)'";
				 if(prop=="BMI") 
					 examinField=examinField+" readOnly/> ";
				 else
					examinField=examinField+" /> ";
				}
			else if(prop=='BP Lt.Arm mm/Hg')
				examinField=examinField+"<input  type='text' style='width:37%' name='"+prop+"' id='"+id+"' maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/>&nbsp; / &nbsp;&nbsp;<input type='text' name='BP1' id='BP1' style='width:38%' maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/>";
			else if(prop=='BP Rt.Arm mm/Hg') 
				examinField=examinField+"<input  type='text' style='width:37%' name='"+prop+"' id='"+id+"' maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/> &nbsp;/&nbsp;&nbsp; <input type='text' name='BP2' id='BP2' style='width:38%'  maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/>";
			else if(prop=='Temperature(C/F)')
				{examinField=examinField+"<input type='radio' name='temp' id='temp' value='C' title='Centigrade' onclick='showTemp()'/>C<input type='radio'  name='temp' id='temp' value='F' title='ForeignHeat' onclick='showTemp()' />F&nbsp;&nbsp;&nbsp;<input type='text'  style='width:57%;' name='"+prop+"'  id='"+id+"' maxlength='"+maxlength+"' title='"+prop+" Text Field'  onchange='validateInnerNum(this)'/>";

				}
			}
		else if(subTypeField=='R')
			{
			
				examinField=examinField+"<input type='radio' name='"+prop+"' id='"+id+"' value='Y' title='Yes' onclick='getExaminSubCatValue(this)'/>Yes<input type='radio'  name='"+prop+"' id='"+id+"' value='N' title='No' onclick='getExaminSubCatValue(this)'/>No<br /><span id='"+prop+"Sub'></span>";
			}
		
		document.getElementById(id).innerHTML=examinField;
		}
		else
		{
		document.getElementById(id).innerHTML="";
		}
		//parent.fn_resizePage();
	}
	function getOtherText(input){
		var othrField="";
		var id=input.value;	
		var subTypeField=document.getElementById(id).value;
		var prop = document.getElementById(id).name;
		var surgName='History Of Past illness Surgeries text Field';
		if(input.checked){
		if(id=='PH11')
		othrField=othrField+"<input type='text' class='form-control' name='pastHistryOthr' id='pastHistryOthr' maxlength='100' title='History Of Past illness Other text Field' onchange='validateInnerAlphaSpace(this)'/>";
		if(id=='PH8')
		othrField=othrField+"<input type='text' class='form-control' name='pastHistryCancers' id='pastHistryCancers' maxlength='100' title='History Of Past illness Cancers text Field' onchange='validateInnerAlphaSpace(this)'/>";
		if(id=='PH12')
		othrField=othrField+"<input type='text' class='form-control' name='pastHistryEndDis' id='pastHistryEndDis' maxlength='100' title='History Of Past illness Endocrine Diseases text Field' onchange='validateInnerAlphaSpace(this)'/>";
		if(id=='PH10')
		othrField=othrField+'<textarea class="form-control" name="pastHistrySurg" id="pastHistrySurg" title="History Of Past illness Surgeries text Field" style="width:12em;height:2em" onkeydown="return maxLengthPress(this,4000,event)" onkeypress="return validateSplKeyCodes(event)" onchange="validateSpaces(this,\''+surgName+'\')"/>';
		else if(id=='FH11')
			othrField=othrField+"<input type='text' class='form-control' name='familyHistoryOthr' id='familyHistoryOthr' maxlength='100' title='Family History Other Text Field' onchange='validateInnerAlphaSpace(this)'/>";
		document.getElementById(id).innerHTML=othrField;
		}
	else
		{
		document.getElementById(id).innerHTML="";
		}
	}
	function getSubListHistory(input,button)
	{	
		
		var parntCode=input.value;
		var prop = document.getElementById(parntCode+"p").name;
		if(input.checked)
		{
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
		   url = "./annualCheckUpAction.do?actionFlag=getPersonalHistory&callType=Ajax&parntCode="+parntCode;    
		   xmlhttp.onreadystatechange=function()
		   {
			  
		       if(xmlhttp.readyState==4)
		       {
	          		var resultArray=xmlhttp.responseText;
	          		
	          		if(resultArray.trim()=="SessionExpired*"){
		    	    	alert("Session has been expired");
		    	    	parent.sessionExpireyClose();
		            }
		           else
		           {
		        	   
		           		resultArray = resultArray.replace("[","");
		           		
		           		resultArray = resultArray.replace("]","");
		           	
		           		var pHistoryList = resultArray.split(","); 
		           		if(pHistoryList.length>0)
		           		{
		        	   		var phistory="";
		               		for(var i = 0; i<pHistoryList.length;i++)
		               		{	
		                    	var arr=pHistoryList[i].split("~");                     
		                    	if(arr[1]!=null && arr[0]!=null)
		                    	{
		                        	var val1 = arr[1].replace(/^\s+|\s+$/g,"");
		                        	var val2 = arr[0].replace(/^\s+|\s+$/g,"");
		                        	
	                                if(button!='reset' && '${lstPerHis}'!=null && '${lstPerHis}'.indexOf(val2,0)!=-1)	                                         
			                    	{
	    		                    	phistory=phistory+"<input type='radio' name='"+prop+"' id='"+parntCode+"' value='"+val2+"' onclick='getSubLevelDetails(this)' title='"+val1+"' checked='checked'/>"+""+val1;
	                                 }
			                    	else
			                    	phistory=phistory+"<input type='radio' name='"+prop+"' id='"+parntCode+"' value='"+val2+"' onclick='getSubLevelDetails(this)' title='"+val1+"'/>"+""+val1;	
				             	}
		               		}
		               		document.getElementById(parntCode).innerHTML=phistory+"<span id='"+prop+"Td'></span>";
		            	}
		         	}
		       }			
		   }
			xmlhttp.open("Post",url,false);
			xmlhttp.send(null);
		}
		else
			{
			document.getElementById(parntCode).innerHTML="";
			}
			 //parent.fn_resizePage();
	}
	function fn_openAttachment(caseId,attachPath)
		{
			$('#attachDiv').modal({
				backdrop : 'static',
				keyboard : false,
				show : true
			},'show');
			if(caseId!=null && attachPath !=null)
				{
					var url = "./annualCheckUpAction.do?actionFlag=openAttachmentInvest&callType=Ajax&caseId="+caseId+"&attachPath="+attachPath+"&fromPatientsView=${fromPatientsView}";
					$("#attachDiv iframe").attr({'src':url,
			 	        'height': '100%',
			 	        'width': '100%'});
				}
		}
		
	
	function checkBrowser(input)
	{
		
	     if(navigator.appName == "Netscape")
	    {
			var sizeCheck=checkFileSizeFF(input);
			var fileCheck=checkFileNameMatch(input);
			if(sizeCheck==false || fileCheck==false)
			input.value='';
	    }
	     if(navigator.appName == "Microsoft Internet Explorer")
	    {  
			var fieldName=input.name;
			var fieldId=input.id;  	
			var sizeCheck=checkFileSizeIE(input);
			var fileCheck=checkFileNameMatch(input);
			if(sizeCheck==false || fileCheck==false)
			{
				var oRow = input.parentNode.parentNode; 
				var filecell;
				if(fieldName.charAt(0)=='g' || fieldName.charAt(0)=='u')
				{
					filecell=oRow.cells[2];
				}
				else if(fieldName.charAt(0)=='a')
				{ 
					filecell=oRow.cells[6];
				}
				filecell.innerHTML='<input type="file"  id='+fieldId+' name='+fieldName+' onchange="checkBrowser(this)"/>';
			}
	    }
	}
	function checkFileSizeFF(input)
	{
		var filesize=input.files[0].size;
		if((filesize/(1024))>200)
		{
			jqueryErrorMsg('File Size Validation',"Uploaded file size exceeded 200KB");
			return false;
		}
	}
	function checkFileSizeIE(input)
	{
		try
		{
	 	var myFSO = new ActiveXObject("Scripting.FileSystemObject");
	 	var filepath = input.value;
	 	var thefile = myFSO.getFile(filepath);
	 	var filesize = thefile.size/(1024);
	 	if(filesize>200)
		{
	 		jqueryErrorMsg('File Size Validation',"Uploaded file size exceeded 200KB");
			return false;
		}
		}
		catch(e)
		{
			jqueryInfoMsg('ActiveX Control Enable',"Please Enable ActiveX control.");
			jqueryInfoMsg('Steps To Enable ActiveX Control',"Go To-->Tools-->Internet Options-->Security-->Trusted Sites-->Click on Sites Button-->Add site url to list-->close-->Click on Custom level Button-->Initialize and script ActiveX controls not marked as safe for scripting---Enable");
			return false;
		}
	}
	function checkFileNameMatch(input)
	{
		var curFile=input.value;
		//var rtVal=chkSpecailChars(curFile.substring(curFile.lastIndexOf('\\')+1));
		
		var rtVal=chkSpecailChars(curFile.substring(curFile.lastIndexOf('\\')+1,curFile.lastIndexOf('.')));
		var fullFileName=curFile.substring(curFile.lastIndexOf('\\')+1);
		var fileName1=curFile.substring(curFile.lastIndexOf('\\')+1,curFile.lastIndexOf('.'));
		if(rtVal ==0)   
			{
			jqueryErrorMsg('File Name Validation',"File name("+fullFileName+") should not contain special characters");
			return false;
			}
		if(fileName1.charAt(0)=='-' || fileName1.charAt(fileName1.length-1)=='-' || fileName1.charAt(0)=='_' || fileName1.charAt(fileName1.length-1)=='_')
			{
			jqueryErrorMsg('File Name Validation',"File name should not start or end with - or _");
			return false;
			}
		
		if( fileName1.match(/[\-\_]{2}/i))
		{
			jqueryErrorMsg('File Name Validation',"File name should not should not contain consecutive special characters");
			return false;
		}
		var extn=curFile.substring(curFile.lastIndexOf('.')+1).toLowerCase();
		if(extn=='jpg' || extn=='jpeg' || extn=='png' || extn=='bmp')  
			{
			var status=true;
			}
		else
			{
			jqueryErrorMsg('File Type Validation',"Can upload jpg,jpeg,png,bmp extension files only");
			return false;
			}
		var matchCount=0;
		for(var temp=1;temp<document.forms[0].elements.length;temp++)
	    {
	       if(document.forms[0].elements[temp].type=="file")
	       {
	       	   var val=document.forms[0].elements[temp].value;
	       	   var fileName = val.substring(val.lastIndexOf('\\')+1,val.lastIndexOf('.'));
	       	   var curFileName = curFile.substring(curFile.lastIndexOf('\\')+1,curFile.lastIndexOf('.'));
			   if(fileName==curFileName)
			   matchCount++;
			   if(matchCount>1)
				{
					jqueryErrorMsg('File Name Validation',"File with this filename already exists.Cannot upload");
					return false;
				}
			   
	       }
	    }
		if(consultAttachLst!=null && consultAttachLst.length>0)
			{
				for(var i=0 ;i<consultAttachLst.length;i++)
					{
						if(consultAttachLst[i].substring(consultAttachLst[i].lastIndexOf('\\')+1,consultAttachLst[i].lastIndexOf('.'))==curFile.substring(curFile.lastIndexOf('\\')+1,curFile.lastIndexOf('.')))
							{
								alert("File with this filename already exists.Cannot upload");
								return false;
							}
					}
			}	
		
	}
</script>
</logic:equal>
	</head>
	<body onload="fn_loadProcessImage();">
	 <div id="processImagetable"
		style="top: 50%; width: 100%; position: absolute; z-index: 60;">
	<table border="0" align="left" width="50%">
		<tr>
			<td align="center">
			<div id="processImage" align="center"><img
				src="images/Progress.gif" width="100" height="100" border="0"
				tabindex="3"></img></div>
			</td>
		</tr>
	</table>
	</div> 
	<div id="middleFrame_content">
	<form action="/annualCheckUpAction.do" method="post"
		enctype="multipart/form-data"><br>
	<table style="margin: 0 auto; width: 100%">
		<tr>
			<td colspan="4">
				<table class="tbheader table-responsive" width="100%">
			
				<tr style="margin: 0 auto; width: 100%">
					<td align="center" width="100%"><b>&nbsp;&nbsp;&nbsp;<fmt:message
						key="EHF.Title.AnnualHealthCheckup" /></b></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
		<table class="table table-responsive" width="100%">
				<tr>
					<td class="labelheading1 tbcellCss"><b><fmt:message
						key="EHF.HealthCardNo" /></b></td>
					<td width="15%" class="tbcellBorder"><b> &nbsp;<bean:write
						name="annualCheckUpForm" property="cardNo" /></b></td>
					<td>&nbsp;</td>
					<td class="labelheading1 tbcellCss"><b><fmt:message
						key="EHF.PatientNo" /></b></td>
					<td width="15%" class="tbcellBorder"><b> &nbsp;<bean:write
						name="annualCheckUpForm" property="patientNo" /></b></td>
					<td>&nbsp;</td>
				</tr>
			</table>
			
			<!-- <br> -->
				<table class="tbheader table-responsive" width="100%">
					<tr>
						<td align="center" width="100%"><b><fmt:message key="EHF.Title.PatientDetails" /></b></td>
					</tr>
				</table>
		
			</td>
		</tr>
		<tr>
			<td>
			<table class="table table-responsive" style="margin: 0 auto; width: 100%">
				<tr>
					<td width="27%" valign="top">
					<fieldset ><legend
						class="legendStyle"><b><fmt:message
						key="EHF.FrameSet.PatientDetails" /></b></legend>
					<div  id="commonDtlsField" style="height:23em;">
					<table width="100%" height="220px" class="table-responsive" style="table-layout:fixed;word-wrap:break-word;">
						<%-- <tr><td class="labelheading1 tbcellCss" width="50%"><b><fmt:message key="EHF.CardIssueDate"/></b></td><td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write name="annualCheckUpForm" property="cardIssueDt"/></b></td></tr> --%>
						<tr>
							<td class="labelheading1 tbcellCss" width="40%"><b><fmt:message
								key="EHF.Name" /></b></td>
							<td class="tbcellBorder" width="60%"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="name" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Gender" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="gender" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.DateOfBirth" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="dateOfBirth" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Age" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="years" />Y <bean:write
								name="annualCheckUpForm" property="months" />M <bean:write
								name="annualCheckUpForm" property="days" />D</b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Relationship" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="relation" /></b></td>
						</tr>
						<%-- <tr><td class="labelheading1"><b><fmt:message key="EHF.Caste"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="annualCheckUpForm" property="caste"/></b></td></tr> --%>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Slab" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="slab" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Designation" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="designation" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.ContactNo" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="contactNo" /></b></td>
						</tr>
					</table>
					</div>
					</fieldset>
					</td>
					<td width="29%" valign="top">
					<fieldset ><legend
						class="legendStyle"><b><fmt:message
						key="EHF.CardAddress" /></b></legend>
					<div  id='cardAddressField' style="height:23em;">
					<table width="100%" height="200px" class="table-responsive" style="table-layout:fixed;word-wrap:break-word;">
						<tr>
							<td class="labelheading1 tbcellCss" width="50%"><b><fmt:message
								key="EHF.HouseNo" /></b></td>
							<td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="houseNo" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Street" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="street" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b>State</b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="state" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.District" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="district" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Mdl/Mcl" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="mandal" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Village" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="village" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Pin" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="pin" /></b></td>
						</tr>
					</table>
					
					</div>
					</fieldset>
					</td>
					<td  width="29%" valign="top">
					<fieldset height="23em;"><legend
						class="legendStyle"><b><fmt:message
						key="EHF.CommunicationAddress" /></b></legend>
					<div id="commAddressField" style="height:23em;">
					<table width="100%" 
						style="table-layout: fixed; word-wrap: break-word;">
						<tr>
							<td class="labelheading1 tbcellCss" width="50%"><b><fmt:message
								key="EHF.HouseNo" /></b></td>
							<td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="commHouseNo" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Street" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="commStreet" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b>State</b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="commState" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.District" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="commDistrict" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Mdl/Mcl" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="commMandal" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Village" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="commVillage" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Pin" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="commPin" /></b></td>
						</tr>
					</table>
				
					</div>
					</fieldset>
					</td>
					<td width="15%" valign="top" >
					<fieldset  id='photoField'><legend
						class="legendStyle"><b><fmt:message key="EHF.Photo" /></b></legend>
					<table width="80%" height="80%" style="margin: auto auto">
						<tr>
							<td align="center"><logic:notEmpty name="annualCheckUpForm"
								property="photoUrl">
								<img id="patImage" src="common/showDocument.jsp" width="150"
									height="150" alt="NO DATA"
									onmouseover="resizePatImage('patImage','200','200')"
									onmouseout="resizePatImage('patImage','150','150')" />
							</logic:notEmpty> <logic:empty name="annualCheckUpForm" property="photoUrl">
								<img src="images/photonot.gif" width="150" height="150"
									alt="NO DATA" />
							</logic:empty></td>
						</tr>
					</table>
					</fieldset>
					</td>
				</tr>
			</table>
			<table class="tbheader" style="margin: 0 auto; width: 100%">
				<tr>
					<td align="center"><b><fmt:message
						key="EHF.Title.CaseDetails" /></b></td>
				</tr>
			</table>
			
			<table width="100%">
				<tr>
					<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message
						key="EHF.RegisteredHospital" /> </b></td>
					<td width="25%" class="tbcellBorder"><b><bean:write
						name="annualCheckUpForm" property="hospitalName" /></b></td>
					<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message
						key="EHF.DateAndTime" /> </b></td>
					<td width="25%" class="tbcellBorder"><b><bean:write
						name="annualCheckUpForm" property="dtTime" /></b></td>
				</tr>
			</table>
			
			<table class="tbheader" style="margin: 0 auto; width: 100%">
				<tr>
					<td width="100%" align="center"><b><fmt:message
						key="EHF.Title.MedicalDetails" /></b></td>
				</tr>
			</table>
			
			<table width="100%" class="medicalDetailsTable" border="0">
				<tr>
					<td colspan="2" valign="top" class="labelheading1 tbcellCss"><b><fmt:message
						key="EHF.HistoryOfPresentIllness" /></b> <font color="red">*</font></td>
					<td colspan="2" valign="top" rowspan="3" class="tbcellBorder">
					<fieldset><legend class="legendStyle"><b><fmt:message
						key="EHF.ExaminationFindings" /></b> <font color="red">*</font></legend>
					<table width="100%">
						<logic:iterate id="examinFnds" name="examinFndgsList">
							<tr>
								<!-- <td width="60%">&nbsp;&nbsp; <html:multibox
									name="annualCheckUpForm" property="examinationFnd"
									styleId="examinationFnd" onclick="getSubFieldType(this)">
									<bean:write name="examinFnds" property="ID" /> 
								</html:multibox> <bean:write name="examinFnds" property="VALUE" /></td>  -->
	<c:if test="${examinFnds.ID ne 'GE1' and  examinFnds.ID ne 'GE3' and examinFnds.ID ne 'GE7' and examinFnds.ID ne 'GE9' and examinFnds.ID ne 'GE1' and examinFnds.ID ne 'GE10' }">							
<c:if test="${(examinFnds.ID ne 'GE14')}">
		 <td width="60%">&nbsp;&nbsp;
		 
 		 <html:multibox name="annualCheckUpForm"  property="examinationFnd" styleId="examinationFnd" onclick="getSubFieldType(this)">
      		 <bean:write name="examinFnds" property="ID"/>
       	</html:multibox>
       
       		<bean:write name="examinFnds" property="VALUE"/></td>
       			</c:if>	
       			
       			
       					<c:if test="${examinFnds.ID eq 'GE14'}">
		 <td width="60%">&nbsp;&nbsp;
		
 		 <html:multibox name="annualCheckUpForm"  property="examinationFnd" styleId="examinationFnd" onclick="getSubFieldType(this)">
      		 <bean:write name="examinFnds" property="ID"/>
       	</html:multibox>
       
       		BP mm/Hg</td>
       			</c:if>	
       			</c:if>
								<td id="<bean:write name="examinFnds" property='ID'/>"
									width="39%"></td>
								<td width="1%"><input type="hidden"
									name="<bean:write name='examinFnds' property='VALUE'/>"
									id="<bean:write name='examinFnds' property='ID'/>h"
									value="<bean:write name='examinFnds' property='LVL'/>" /></td>
							</tr>
						</logic:iterate>
					</table>
					</fieldset>
					</td>
				</tr>
				<tr>
					<td valign="top" colspan="2" class="tbcellBorder"><html:textarea
						name="annualCheckUpForm" property="presentHistory" 
						styleClass="form-control" styleId="presentHistory"
						style="resize:none;" 
						title="Enter History Of Present Illness"
						onkeydown="return maxLengthPress(this,4000,event)"
						onkeypress="return checkAlphaNumericCodes(event);"
						onchange="validateSpaces(this,'History Of Present Illness')" /></td>
				</tr>
				<tr>
					<td colspan="2" class="tbcellBorder">
					<fieldset><legend class="legendStyle"><b><fmt:message
						key="EHF.PersonalHistory" /></b> <font color="red">*</font></legend>
					<table width="100%">
						<logic:iterate id="phistory" name="personalHistoryList">
							<%-- <tr>
								<td width="43%"><html:multibox name="annualCheckUpForm"
									property="personalHistory" styleId="personalHistory"
									style="WIDTH:3em;" onclick="getSubListHistory(this,'NA')">
									<bean:write name="phistory" property="ID" />
								</html:multibox> <bean:write name="phistory" property="VALUE" /></td>
								<td id="<bean:write name="phistory" property='ID'/>" width="59%"
									height="1em"></td>
								<td width="1%"><input type="hidden"
									name="<bean:write name='phistory' property='VALUE'/>"
									id="<bean:write name='phistory' property='ID'/>p" /></td>
							</tr> --%>
						
						<c:if test="${phistory.ID ne 'PR4' }">
							 <c:if test="${phistory.ID eq 'PR3' }">
							 	 <tr><td width="33%">
							
						 		 <html:multibox name="annualCheckUpForm"  property="personalHistory" styleId="personalHistory" style="WIDTH:3em;" onclick="getSubListHistory(this,'NA')">
						      		 <bean:write name="phistory" property="ID"/>
						       	</html:multibox>
						       		Bowel</td>
						       		<td id="<bean:write name="phistory" property='ID'/>" width="59%" height="1em"></td>
						       		<td width="1%">
						       		<input type="hidden" name="<bean:write name='phistory' property='VALUE'/>" id="<bean:write name='phistory' property='ID'/>p"/></td>
						       		</tr>
							 </c:if>
							 
							 
							  <c:if test="${phistory.ID eq 'PR6' }">
							 	 <tr><td width="33%">
							
						 		 <html:multibox name="annualCheckUpForm"  property="personalHistory" styleId="personalHistory" style="WIDTH:3em;" onclick="getSubListHistory(this,'NA')">
						      		 <bean:write name="phistory" property="ID"/>
						       	</html:multibox>
						       		Addictions</td>
						       		<td id="<bean:write name="phistory" property='ID'/>" width="59%" height="1em"></td>
						       		<td width="1%">
						       		<input type="hidden" name="<bean:write name='phistory' property='VALUE'/>" id="<bean:write name='phistory' property='ID'/>p"/></td>
						       		</tr>
							 </c:if>
							 
							   <c:if test="${phistory.ID ne 'PR6' and phistory.ID ne 'PR3' and phistory.ID ne 'PR4'}">
							 <tr><td width="33%">
							
						 		 <html:multibox name="annualCheckUpForm"  property="personalHistory" styleId="personalHistory" style="WIDTH:3em;" onclick="getSubListHistory(this,'NA')">
						      		 <bean:write name="phistory" property="ID"/>
						       	</html:multibox>
						       		<bean:write name="phistory" property="VALUE"/></td>
						       		<td id="<bean:write name="phistory" property='ID'/>" width="59%" height="1em"></td>
						       		<td width="1%">
						       		<input type="hidden" name="<bean:write name='phistory' property='VALUE'/>" id="<bean:write name='phistory' property='ID'/>p"/></td>
						       		</tr>
						       		</c:if>
						   </c:if>
						</logic:iterate>
						<tr>
							<td colspan="3">&nbsp;</td>
						</tr>
						<!--  <tr>
      <td  id="habitsTd" colspan="3">
      </td></tr> -->
					</table>
					</fieldset>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="tbcellBorder">
					<fieldset><legend class="legendStyle"><b><fmt:message
						key="EHF.PastHistory" /></b> </legend>
					<table width="100%">
						<logic:iterate id="psthistory" name="pastHistoryList"
							indexId="cnt">
							<c:if test="${psthistory.ID ne 'PH12' and  psthistory.ID ne 'PH8' }">
							<tr>
								<td width="50%">&nbsp;&nbsp; <html:multibox
									name="annualCheckUpForm" property="pastHistory"
									styleId="pastHistory" onclick="getOtherText(this)"
									title="History Of Past Illness">
									<bean:write name="psthistory" property="ID" />
								</html:multibox> <bean:write name="psthistory" property="VALUE" /></td>
								<td id="<bean:write name="psthistory" property='ID'/>"
									width="60%"></td>
							</tr>
							</c:if>
						</logic:iterate>

					</table>
					</fieldset>
					</td>
					<td colspan="2" class="tbcellBorder">
					<fieldset><legend class="legendStyle"><b><fmt:message
						key="EHF.FamilyHistory" /></b> </legend>
					<table width="100%">
						<logic:iterate id="fhistory" name="familyHistoryList">
						<c:if test="${fhistory.ID ne 'FH8' }">
							<tr>
								<td width="40%">&nbsp;&nbsp; <html:multibox
									name="annualCheckUpForm" property="familyHistory"
									styleId="familyHistory" onclick="getOtherText(this)">
									<bean:write name="fhistory" property="ID" />
								</html:multibox> <bean:write name="fhistory" property="VALUE" /></td>
								<td id="<bean:write name="fhistory" property='ID'/>" width="30%"></td>
							</tr>
						</c:if>							
						</logic:iterate>
					</table>
					</fieldset>
					</td>
				</tr>
			</table>
			<table  style="width:100%" border="0">
				<tr  class="tbheader"  >
					<td width="100%" align="center" style="padding:5px !important;" >
						<b><fmt:message key="EHF.Title.InvestDetails"></fmt:message></b>
					</td>
				</tr>
				<tr width="100%">
					<td width="100%" align="center">
						<b><fmt:message key="EHF.AHC.Package.Statement"></fmt:message></b>
					</td></tr>
					<tr><td width="100%" align="center">
						<b><span style="color: red">Atleast one Investigation is mandatory*</span></b>
					</td>
				</tr>	
			</table>
			<c:if test="${investList ne null}">
				<c:if test="${fn:length(investList)>0}">
						
					<table style="width:100%" border="0">
						<tr>
							<c:forEach items="${investList}" var="iterator" varStatus="iterateInd">
								<c:if test="${((iterateInd.index)%5)==0}">
								</tr>
								<tr>
								</c:if>
								<td class="tbcellBorder" width="20%">
								<html:checkbox name="annualCheckUpForm" property="testCheck" value="${iterator.ID }" styleId="testCheck" onclick="javascript:fn_addTestReport(this,'${iterator.VALUE }','${iterator.CONST }');"/>
								${iterator.VALUE }
								</td>
								
							</c:forEach>	
						</tr>
					</table>
				</c:if>
			</c:if>
		<!-- 	<c:if test="${attachList ne null }">
				<c:if test="${fn:length(attachList) gt 0}">
					<table  style="width:100%" border="0">
						<tr  class="tbheader"  >
							<td width="100%" align="center" style="padding:5px !important;" >
								<b>PREVIOUSLY ADDED ATTACHMENTS</b>
							</td>
						</tr>
					</table>
					<table width="100%">
						<tbody>
							<tr>
							<c:set var="listLength" value="${fn:length(investList)}" />
							<c:if test="${listLength < 4 }">
								<c:set var="tdWidth" value="${100/(listLength)}" />
							</c:if>	
							<c:if test="${listLength > 3 }">
								<c:set var="tdWidth" value="25" />
							</c:if>
								<c:forEach items="${attachList }" var ="iterator" varStatus="item">
									<c:if test="${((item.index)%4)==0 }">
										<tr>
									</c:if>
										<td class="tbcellBorder" width="${tdWidth}%">
										${item.index+1} . ${iterator.ATTACH} 
											   &nbsp; &nbsp; &nbsp;<a  href="javascript:fn_openAttachment('${iterator.caseId}','${iterator.VALUE}')" title="click to open Attachment">
											
											 ${iterator.ID} 
											</a>
										</td>
								</c:forEach>	
							</tr>
						</tbody>	
					</table>
				</c:if>
			</c:if>-->
			<!--<table width="100%" style="margin-top:5px !important;">
			<tr>
			<td width="30%" align="center" style="border:1px solid #bcd8ff" colspan="4">
						<b><font color="black"><fmt:message key="EHF.InvestAttach"></fmt:message>:</font></b>
					</td>
			</tr>
		 <tr><td colspan="4">
				<table width="100%" id="ahcInvestAttachTable" style="padding-right:10em;padding-left:10em;">
				<tr id="emptyRow" style="line-height:0px !important;">
					
					<td width="50%">&nbsp;</td>
					<td width="50%">&nbsp;</td>
					
				</tr>
			</table>
				</td> -->	
					<!--<td width="15%" >
						&nbsp;
					</td>
					<td width="30%" align="center" style="border:1px solid #bcd8ff">
						<b><font color="black"><fmt:message key="EHF.InvestAttach"></fmt:message>:</font></b>
					</td>
					 <td width="30%" style="border:1px solid #bcd8ff" align="center" >
						<input style="padding-top:6px;padding-left:5px;float:left;width:200px !important" type="file" name="investAttach[0]" id="investAttach[0]" title="Please add Investigation Attachments" onchange="javascript:checkBrowser(this)"/>
						<button style="float:left;margin-left:0px" onclick="javascript:fn_InvestAttach('add');"class="btn btn-primary" type="button" name="addAttchBut" data-toggle="tooltip" title="Click to Add More Attachments" id="addAttchBut"><i class="fa fa-plus-square"></i></button>
						<button style="float:left;" onclick="javascript:fn_InvestAttach('remove');" class="btn btn-danger" type="button" name="remAttchBut" data-toggle="tooltip" title="Click to Remove Attachments" id="remAttchBut"><i class="fa fa-times"></i></button>
					</td> 
					<td width="15%">
						&nbsp;
					</td>
					
				</tr>
			</table>-->
			
			<table  style="width:100%" border="0">
						<tr  class="tbheader"  >
							<td width="100%" align="center" style="padding:5px !important;" >
								<b>CONSULTATION DETAILS</b>
							</td>
						</tr>
						<tr>
							<td width="100%" align="center">
							<b><span style="color: red">Atleast one Consultation is mandatory*</span></b>
							</td>
						</tr>
					</table>
			<table id="consultDtls">
			<tr style="width:100%">
			<td width="13%" class="tbcellBorder labelheading1"><b>Consultation By :</b></td>
			<td width="15%" class="tbcellBorder ">
				<select name="consultBy" id="consultBy">
					<option value="-1" >Select</option>
					<option value="Phy" >Physician</option>
					<option value="Gyn" >Gynaecologist</option>
					<option value="Dci" >Diet Counselling Investigation</option>
					<option value="Eye" >Eye Examination</option>
					<option value="Opt" >Ophthalmologist</option>
				</select>
			</td>
			<td width="12%" class="tbcellBorder labelheading1"><b>Doctor Name :</b></td>
			<td width="15%" class="tbcellBorder "><input type="text" value="${annualCheckUpForm.diagnosedBy}" name="diagnosedBy" id="diagnosedBy"/></td>
			<td width="10%" class="tbcellBorder labelheading1"><b>Remarks :</b></td>
			<td width="22%" class="tbcellBorder "><textarea style="width:100%" name="docRmrks" id="docRmrks"></textarea></td>
			<td width="13%" class="tbcellBorder labelheading1" align="center" >
				<button id="addConsult" name="addConsult" onclick="javascript:addConsultation()" class="btn btn-primary">Add Consultation</button>
			</td>
			</tr>
			
			</table>
			<table width="100%"  id="consultDtlsData">	
				<thead id="heading" style="display:none">
					<tr id="heading1">
						<td class="tbheader" colspan="5">	
							ADDED CONSULTATIONS
						</td>	
					</tr>
					<tr id="heading2" style="width:100%">
						<td class="tbcellBorder labelheading1" width="15%">
							<b>Consultation By</b>
						</td>
						<td class="tbcellBorder labelheading1" width="20%">
							<b>Doctor Name</b>
						</td>
						<td class="tbcellBorder labelheading1" width="30%">
							<b>Remarks</b> 
						</td>
						<td class="tbcellBorder labelheading1" width="20%">
							<b>Attachment</b> 
						</td>
						<td class="tbcellBorder labelheading1" width="10%">
							<b>Action</b>
						</td>
					</tr>
				</thead>
				<tbody>
				
				</tbody>
			</table>

				<c:if test='${oldConsultList ne null}'>
					<c:if test='${fn:length(oldConsultList) gt 0 }'>
					<table style="width:100%;"  id="consultDtlsDataId">	
				<thead id="heading" >
					<tr id="heading1">
						<td class="tbheader" colspan="6">	
							PREVIOUSLY ADDED CONSULTATIONS
						</td>	
					</tr>
					<tr id="heading2" style="width:100%">
					<td class="tbcellBorder labelheading1" width="5%">
							<b>Sno.</b>
						</td>
						<td class="tbcellBorder labelheading1" width="15%">
							<b>Consultation By</b>
						</td>
						<td class="tbcellBorder labelheading1" width="20%">
							<b>Doctor Name</b>
						</td>
						<td class="tbcellBorder labelheading1" width="30%">
							<b>Remarks</b> 
						</td>
						<td class="tbcellBorder labelheading1" width="20%">
							<b>Attachment</b> 
						</td>
						<td class="tbcellBorder labelheading1" width="10%">
							<b>Action</b>
						</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${oldConsultList}" var ="iterator1" varStatus="item">
						<tr>
							<td class="tbcellBorder" >${item.index+1}</td>
							<td class="tbcellBorder" >${iterator1.UNITTYPE}</td>       
        					<td class="tbcellBorder">${iterator1.UNITNAME} </td> 
							<td class="tbcellBorder">${iterator1.REMARKS} </td> 
							<td class="tbcellBorder"><a  onclick="javascript:fn_openAttachment('${iterator1.ID}','${iterator1.PATH}');">View</a></td>
							<td class="tbcellBorder"><button class="btn btn-warning" type="button" value="Delete" onclick="javascript:fn_deleteConsultOnload(this,'${iterator1.COUNT}');">Delete&nbsp;<i class="fa fa-times"></i></button></td>
							</tr></c:forEach>	
					
				 
				</tbody>
			</table>
			</c:if>
				
				</c:if>
			<!-- Modal for change theme  --> 
<div class="modal fade" id="attachDiv"> 
  <div class="modal-dialog"> 
    <div class="modal-content" >
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h2 class="modal-title" align="center"> Attachments</h2>
      </div>
      <div class="modal-body" style="overflow-y:scroll;height:250px;align:center" >
        <iframe src="/"  class="embed-responsive-item" seamless="" id="" width=" 100%" height="100%" ></iframe>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->  
</div><!-- /.modal -->
			
			
			
			
			
			<table  style="margin-top:3px !important;margin: 0 auto; width: 100%">
<tr>

<td width="30%">&nbsp;</td>		
<td width="40" align="center" colspan="3"> <button class="btn btn-primary"  type="button" id="Submit" onclick="javascript:fn_savePatientDetails('submit')">Submit&nbsp;<i class="fa fa-medkit"></i></button>
 <button class="btn btn-primary"  type="button" id="Save" onclick="javascript:fn_saveDetails()">Save&nbsp;<i class="fa fa-floppy-o"></i></button>
 <button class="btn btn-primary"  type="button" id="Reset" onclick="javascript:fn_resetAll()">Reset&nbsp;<i class="fa fa-eraser"></i></button></td>
<td width="30%">&nbsp;</td>
</tr>
</table>
</td></tr>
</table>
			
	
	<html:hidden name="annualCheckUpForm" property="patientNo" styleId="patientNo"/>
	<html:hidden name="annualCheckUpForm" property="examFndsVal" styleId="examFndsVal"/>
	<html:hidden name="annualCheckUpForm" property="personalHistVal" styleId="personalHistVal"/>
	<html:hidden name="annualCheckUpForm" property="personalHist" styleId="personalHist"/>
	<html:hidden name="annualCheckUpForm" property="hospitalId" styleId="hospitalId"/>

	</form>
	</div>
	</body>
	<logic:equal name ="annualCheckUpForm" property="medcoFlag" value="Y">
	<script>
	function focusBox(arg)
	{	
		  aField = arg; 
		  setTimeout("aField.focus()", 0); 

	}
	
	var checkDaysCond='${checkDaysCond}';
	(function ()
			{
				if(checkDaysCond!=null && checkDaysCond!='' && checkDaysCond!=' ' && checkDaysCond=='N')
					{
						alert('Case Cannot be Registered as Case Registration Validity expired(14 Days from Patient Registration).');
						document.getElementById('Submit').disabled=true;
						document.getElementById('Save').disabled=true;
						document.getElementById('Reset').disabled=true;
						document.getElementById('Submit').onclick='';
						document.getElementById('Save').onclick='';
						document.getElementById('Reset').onclick='';
					}
			})();
	$.each(testIdLst, function (index, value) {
		  $('input[name="testCheck"][value="' + value.toString().trim() + '"]').prop("checked", true);
		});
	function resetMedicalDetails()
	{
		$('input[name="testCheck"]').prop("checked", false);
		
		document.getElementById("presentHistory").value="";
		var pastHistory=document.forms[0].pastHistory;
		for(var i=0;i<pastHistory.length;i++)
		{
			if(pastHistory[i].checked)
			{
	        pastHistory[i].checked=false;
	        getOtherText(pastHistory[i]);
			}
		}
		var personalHistory=document.forms[0].personalHistory;
		for(var i=0;i<personalHistory.length;i++)
		{
			if(personalHistory[i].checked)
			{
	        personalHistory[i].checked=true;
	        getSubListHistory(personalHistory[i],'reset');
			}
		}
		var examinationFnd=document.forms[0].examinationFnd;
		for(var i=0;i<examinationFnd.length;i++)
		{
			if(examinationFnd[i].checked)
			{
				examinationFnd[i].checked=true;
				getSubFieldType(examinationFnd[i]);
			}
		}
		var familyHistory=document.forms[0].familyHistory;
		for(var i=0;i<familyHistory.length;i++)
		{
			if(familyHistory[i].checked)
			{
				familyHistory[i].checked=false;
				getOtherText(familyHistory[i]);
			}
		}
		
		for (var i=0;i<investAttachLength;i++)
			{
				var id='investAttach['+i+']';
				if(document.getElementById(id)!=null)
					{
						document.getElementById(id).value='';
					}
			}
		
		}
	function fn_resetAll(){
		var msg='Do you want to reset?';
		 if(confirm(msg)==true)
			 resetMedicalDetails();
	}
	$('#addConsult').click(function(event){
		event.preventDefault();
	});
	var rowId=0;
	var consultCount=0;
	var updateConsultCnt=0;
	var updateConsultLst=new Array();
	var consultLst=new Array();
	var consultAttachId=0;
	var updConsultAttachId=0;
	var consultOldLst=new Array();
	function addConsultation()
		{
			var consult =document.getElementById('consultBy').value;
			var diagnosedBy =document.getElementById('diagnosedBy').value;
			var docRmrks =document.getElementById('docRmrks').value;
			if(consult=='-1')
				{
					alert('Please select Consultation Details');
					focusBox(document.getElementById('consultBy'));
					return false;
				}
			if(diagnosedBy=='')
				{
					alert('Please Enter Doctor Name');
					focusBox(document.getElementById('diagnosedBy'));
					return false;
				}
			if(diagnosedBy[0]==' ')
				{
					alert('Doctor Name cannot Start with Space');
					focusBox(document.getElementById('diagnosedBy'));
					return false;
				}
			var length=diagnosedBy.length;
			var count=0;
			for(var j=0;j<length;j++)
				{
					if(diagnosedBy[j]==' ')
						count++;
				}
			if(count==length)
				{
					alert('Doctor Name cannot have only Space');
					focusBox(document.getElementById('diagnosedBy'));
					return false;
				}
			if(docRmrks=='')
				{
					alert('Please Enter Remarks');
					focusBox(document.getElementById('docRmrks'));
					return false;
				}
			if(docRmrks[0]==' ')
				{
					alert('Remarks cannot Start with Space');
					focusBox(document.getElementById('docRmrks'));
					return false;
				}
			length=docRmrks.length;
			count=0;
			for(var j=0;j<length;j++)
				{
					if(docRmrks[j]==' ')
						count++;
				}
			if(count==length)
				{
					alert('Remarks cannot have only Space');
					focusBox(document.getElementById('docRmrks'));
					return false;
				}
			
			var tableId=document.getElementById('consultDtlsData');
			var element=document.getElementById('consultBy');
			if(tableId !=null)
				{
					//Creates a new Row at the Last Position of Table
					var row;
					row=tableId.insertRow(-1);
					row.id="rowId"+rowId;
					if(row!=null)
						{
							cell=row.insertCell(-1);
							cell.innerHTML='<td width="5%" class="tbcellBorder "> '+element.options[element.selectedIndex].text+'</td>';
							cell.className="tbcellBorder";
							
							cell=row.insertCell(-1);
							cell.innerHTML='<td width="5%" class="tbcellBorder "> '+document.getElementById('diagnosedBy').value+'</td>';
							cell.className="tbcellBorder";
							
							cell=row.insertCell(-1);
							cell.innerHTML='<td width="5%" class="tbcellBorder "> '+document.getElementById('docRmrks').value+'</td>';
							cell.className="tbcellBorder";
							
							cell=row.insertCell(-1);
							cell.innerHTML='<td width="5%" class="tbcellBorder "><input type="file" onchange="javascript:checkBrowser(this)" name="consultAttach['+consultAttachId+']" id="consultAttach['+consultAttachId+']"/></td>';
							cell.className="tbcellBorder";
							
							cell=row.insertCell(-1);
							cell.innerHTML='<td width="5%" class="tbcellBorder "> <button class="btn btn-danger" onclick="javascript:deleteInvestRow(\''+row.id+'\',this)" type="button">Delete</button></td>';
							cell.className="tbcellBorder";
							
							var consulData= element.options[element.selectedIndex].text+"~"+document.getElementById('diagnosedBy').value+"~"+document.getElementById('docRmrks').value;
							consultLst[consultCount]=consulData;
							consultCount++;rowId++;
							consultAttachId++;
							//alert(consultLst);
						}
				}
			if(tableId.rows.length>2)
				document.getElementById('heading').style.display="";
			else
				document.getElementById('heading').style.display="none";
			
			document.getElementById('consultBy').value="-1";
			document.getElementById('diagnosedBy').value="";
			document.getElementById('docRmrks').value="";
			
		}
	function deleteInvestRow(arg,src)
		{
			
			 var oRow=src.parentNode.parentNode;
			 consultLst.splice(oRow.rowIndex-2,1);	
		//	if(consultLst.splice(count,1));
			
			$('#'+arg).closest('tr').remove();
			consultCount--;
			consultAttachId--;
			var tableId=document.getElementById('consultDtlsData');
			if(tableId.rows.length>2)
				document.getElementById('heading').style.display="";
			else
				document.getElementById('heading').style.display="none";
		}
	 
	function fn_saveDetails(){
		var patId=document.getElementById("patientNo").value;
		//alert(patId);
		//Mandatory Check validation For Personal History and its sublist
		var personalHistory=document.forms[0].personalHistory;
		var personalCount=0;
		var personalSubCount=0;
		var personalHistVal="";
		var genTestsCount=0;
	
		var lErrorMsg='';
		var lField='';
		
		for(var i=0;i<personalHistory.length;i++)
		{
			if(personalHistory[i].checked)
			{
			var personalHistValue=personalHistory[i].value;
			var personalHistSubList=document.forms[0].elements[personalHistValue];
			for(var j=0;j<personalHistSubList.length;j++)
			{
				if(personalHistSubList[j].checked)	
				{
				personalHistVal = personalHistVal+personalHistValue+"~"+personalHistSubList[j].value;	
				personalSubCount++;
				if(personalHistSubList[j].value=='PR5.1'){
					if (!document.forms[0].AllMed[0].checked && !document.forms[0].AllMed[1].checked)
					{
						
					}
					else if(document.forms[0].AllMed[0].checked){
						if(document.getElementById("AllMedRemrk").value==''|| document.getElementById("AllMedRemrk").value==null)
							{
							personalHistVal = personalHistVal+",AllMed$AllMedYes";					
							}
							else {
							personalHistVal = personalHistVal+",AllMed$AllMedYes(AllMedRemrk@"+document.getElementById("AllMedRemrk").value;
							}
							
						}
					else
					{
					var AllMedList=document.forms[0].AllMed;
					for(var z=0;z<AllMedList.length;z++)
						{
						if(AllMedList[z].checked)
							{
							personalHistVal = personalHistVal+",AllMed$"+AllMedList[z].value;
							}
						}
					}
					if (!document.forms[0].AllSub[0].checked && !document.forms[0].AllSub[1].checked)
					{
						
					}
					else if(document.forms[0].AllSub[0].checked){
						if(document.getElementById("AllSubRemrk").value==''|| document.getElementById("AllSubRemrk").value==null)
							{
							personalHistVal = personalHistVal+",AllSub$AllSubYes";				
							}
							else {
							personalHistVal = personalHistVal+",AllSub$AllSubYes(AllSubRemrk@"+document.getElementById("AllSubRemrk").value;
							}
							
						}
					else
					{
					var AllSubList=document.forms[0].AllSub;
					for(var z=0;z<AllSubList.length;z++)
						{
						if(AllSubList[z].checked)
							{
							personalHistVal = personalHistVal+",AllSub$"+AllSubList[z].value;
							}
						}
					}
				}
				
				if(personalHistSubList[j].value=='PR6.1')
					{
					if (!document.forms[0].alcohol[0].checked && !document.forms[0].alcohol[1].checked && !document.forms[0].alcohol[2].checked)
						{
							 
						}
					else
						{
						var alcoholSubList=document.forms[0].alcohol;
						for(var z=0;z<alcoholSubList.length;z++)
							{
							if(alcoholSubList[z].checked)
								{
								personalHistVal = personalHistVal+"(Alcohol$"+alcoholSubList[z].value;
								}
							}
						}
					if (!document.forms[0].tobacco[0].checked && !document.forms[0].tobacco[1].checked && !document.forms[0].tobacco[2].checked)
					{
						
					}
					else if(document.forms[0].tobacco[2].checked)
						{
						if(document.getElementById("packNo").value=='' || document.getElementById("packNo").value==null)
							{
							 
							}
						else
							{
							personalHistVal = personalHistVal+",Tobacco$Smoking(PackNo@"+document.getElementById("packNo").value;
							}
						if(document.getElementById("smokeYears").value=='' || document.getElementById("smokeYears").value==null)
						{
						
						}
						else
							{
							personalHistVal = personalHistVal+"*Years@"+document.getElementById("smokeYears").value+")";
							}
					}
					else
					{
						var tobaccoSubList=document.forms[0].tobacco;
						for(var z=0;z<tobaccoSubList.length;z++)
							{
							if(tobaccoSubList[z].checked)
								{
								personalHistVal = personalHistVal+",Tobacco$"+tobaccoSubList[z].value;
								}
							}
					}
							
					
						var drugUseSubList=document.forms[0].drugUse;
						for(var z=0;z<drugUseSubList.length;z++)
							{
							if(drugUseSubList[z].checked)
								{
								personalHistVal = personalHistVal+",DrugUse$"+drugUseSubList[z].value;
								}
							}
					
					
						var betelNutSubList=document.forms[0].betelNut;
						for(var z=0;z<betelNutSubList.length;z++)
							{
							if(betelNutSubList[z].checked)
								{
								personalHistVal = personalHistVal+",BetelNut$"+betelNutSubList[z].value;
								}
							}
					  var betelLeafSubList=document.forms[0].betelLeaf;
						for(var z=0;z<betelLeafSubList.length;z++)
							{
							if(betelLeafSubList[z].checked)
								{
								personalHistVal = personalHistVal+",BetelLeaf$"+betelLeafSubList[z].value+")";
								}
							}			
				  }
				}
			}
			
			if(personalSubCount==0)
				{
				
				}
			personalSubCount=0;
			personalHistVal = personalHistVal+"#";
		     }
			else
				{
				personalCount++;
				}
		}

		//Mandatory Check validation For Examination Findings and its sublist
		var examinFnds=document.forms[0].examinationFnd;
		var examinCount=0;
		var examinSubCount=0;
		var examFndsVal="";
		for(var i=0;i<examinFnds.length;i++)
		{
			if(examinFnds[i].checked)
			{
				//examinCount++;
				var examinFndsValue=examinFnds[i].value;		
				var examinFndsName=document.forms[0].elements[examinFndsValue].name;		
				var subType=document.forms[0].elements[examinFndsValue].type;
				
				if(examinFndsValue=='GE11'){			
						var tempType= '';
						if(document.forms[0].temp[0].checked==true) tempType='C';
						if(document.forms[0].temp[1].checked==true) tempType='F';
						examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+tempType;
						examFndsVal = examFndsVal+"#";					
					}
				else if(examinFndsValue=='GE14'){
					    examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+"/"+document.getElementById("BP1").value;
						examFndsVal = examFndsVal+"#";
					}
				else if(examinFndsValue=='GE15'){
						examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+"/"+document.getElementById("BP2").value;
					}
				else if(examinFndsValue!='GE14' && examinFndsValue!='GE15'){				
				if(subType=="text" )
				{
	            examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value;
				}
				else
				{
					var examinFndsSubList=document.forms[0].elements[examinFndsValue];
					
					
					for(var j=0;j<examinFndsSubList.length;j++)
					{
					if(examinFndsSubList[j].checked)	
						{
						examFndsVal = examFndsVal+examinFndsValue+"~"+examinFndsSubList[j].value;
						examinSubCount++; 
						if(examinFndsSubList[j].name=="Dehydration" && examinFndsSubList[j].value=="Y")
							{
							var d;
								var dehydSubList=document.forms[0].dehydrationY;
								 for(d=0;d<dehydSubList.length;d++)
									{
									if(dehydSubList[d].checked)
										{
										examFndsVal = examFndsVal+dehydSubList[d].value;
										}
									}
							}
						}
					}
					examinSubCount=0;
				}
				examFndsVal = examFndsVal+"#";
			}
			}
			else
				{
				examinCount++;
				}
			}
			
		for (var x=0;x<pastHistory.length;x++)
		{
			if (pastHistory[x].checked)
			{
				if(pastHistory[x].value=="PH10")
				{
					if(document.getElementById("pastHistrySurg").value!='' || document.getElementById("pastHistrySurg").value!=null)
					{
						var pastHistrySugValue=document.getElementById("pastHistrySurg").value;
						document.getElementById("pastHistrySurg").value=pastHistrySugValue.replace(/\n/g,' ');
					}
				}
			}
		}
		
		if(lErrorMsg!=null && (lErrorMsg!='' || lErrorMsg!=undefined) && lErrorMsg.length>0)
		{
			//var fr=partial(focusBox,document.getElementById(lField));
			var fr=partial(focusFieldView,lField);
			alert(lErrorMsg);partial(focusFieldView,lField);
		    return;
		 }
		else
		{
		var saveFlag="Yes";
		var checkType="Save";
		
		for (var i=0;i<investAttachLength;i++)
		{
			
			var id='investAttach['+i+']';
			if(document.getElementById(id)!=null)
				{
					if(document.getElementById(id).value==null ||
							document.getElementById(id).value=='')
						{
							if((i==0 && arrayLst!=null && arrayLst.length==0) || (i>0))
								{
									alert('Please add all Investigation Attachments');
									focusBox(document.getElementById(id));
									return false;									
								}
						}
				}
		}
		for (var i=0;i<consultAttachId;i++)
		{
			
			var id='consultAttach['+i+']';
			if(document.getElementById(id)!=null)
				{
					if(document.getElementById(id).value==null ||
							document.getElementById(id).value=='')
						{
							/* if((i==0 && arrayLst!=null && arrayLst.length==0) || (i>0))
								{ */
									alert('Please add all Consultation Attachments');
									focusBox(document.getElementById(id));
									return false;									
								/* } */
						}
				}
		}
		
		var res=confirm('Do you want to Save?');
		
		if(res==true){
			registerCase(personalHistVal,examFndsVal,patId,saveFlag,checkType);
		}
		}
		}
	function fn_savePatientDetails(checkType)
	{
		var patId=document.getElementById("patientNo").value;
		var lErrorMsg='';
		var lField='';
		var genTestsCount=0;
		var ipTestsCount=0;
		var updTestsCount=0;


		 /* if(genInventList.length==0 && genOldList.length==0 ){
			 if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Select General Investigations ";
					}
			        if(lField=='')
			        lField='genInvestigations';   
			} */
			
	//Mandatory check for Present History
	if(document.forms[0].presentHistory.value=='' || document.forms[0].presentHistory.value==null){
		if(lErrorMsg==''){
	             lErrorMsg=lErrorMsg+"Enter History Of Present Illness\n ";
			}
	        if(lField=='')
	        lField='presentHistory';   
	}
	//Mandatory check for History Of Past Illness
	var pastHistory=document.forms[0].pastHistory;
	var pastHistoryCnt=0;
	for (var x=0;x<pastHistory.length;x++)
	{
	  if (pastHistory[x].checked)
	  {
	   pastHistoryCnt++;
	   if(pastHistory[x].value=="PH11")
		   {
		   if(document.getElementById("pastHistryOthr").value=='' || document.getElementById("pastHistryOthr").value==null)
			   {
			   if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Enter Other History Of Past Illness value ";
			      }
			        if(lField=='')
			        lField='pastHistryOthr'; 
			   }
		   }
		   if(pastHistory[x].value=="PH8")
		   {
		   if(document.getElementById("pastHistryCancers").value=='' || document.getElementById("pastHistryCancers").value==null)
			   {
			   if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Enter History Of Past Illness Cancers value ";
			      }
			        if(lField=='')
			        lField='pastHistryCancers'; 
			   }
		   }
		   if(pastHistory[x].value=="PH12")
		   {
		   if(document.getElementById("pastHistryEndDis").value=='' || document.getElementById("pastHistryEndDis").value==null)
			   {
			   if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Enter History Of Past Illness Endocrine Diseases value ";
			      }
			        if(lField=='')
			        lField='pastHistryEndDis'; 
			   }
		   }
		   if(pastHistory[x].value=="PH10")
		   {
		   if(document.getElementById("pastHistrySurg").value=='' || document.getElementById("pastHistrySurg").value==null)
			   {
			   if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Enter History Of Past Illness Surgeries value ";
			      }
			        if(lField=='')
			        lField='pastHistrySurg'; 
			   }
			 if(checkType=='SaveDTRS')
				{
				if(document.getElementById("pastHistrySurg").value!='' || document.getElementById("pastHistrySurg").value!=null)
					{
						var pastHistrySugValue=document.getElementById("pastHistrySurg").value;
						document.getElementById("pastHistrySurg").value=pastHistrySugValue.replace(/\n/g,' ');
					}
				}
		   }
	  }
	}
	//Mandatory Check validation For Personal History and its sublist
	var personalHistory=document.forms[0].personalHistory;
	var personalCount=0;
	var personalSubCount=0;
	var personalHistVal="";

	for(var i=0;i<personalHistory.length;i++)
	{
		if(personalHistory[i].checked)
		{
		//personalCount++;
		var personalHistValue=personalHistory[i].value;
		var personalHistSubList=document.forms[0].elements[personalHistValue];
		for(var j=0;j<personalHistSubList.length;j++)
		{
			if(personalHistSubList[j].checked)	
			{
			personalHistVal = personalHistVal+personalHistValue+"~"+personalHistSubList[j].value;	
			personalSubCount++;
			if(personalHistSubList[j].value=='PR5.1'){
				if (!document.forms[0].AllMed[0].checked && !document.forms[0].AllMed[1].checked)
				{
					if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Select Personal History Known Allergies(Allergies to Medicine) Options ";
			     	 }
			        if(lField=='')
			        lField='AllMed';   
				}
				else if(document.forms[0].AllMed[0].checked){
					if(document.getElementById("AllMedRemrk").value==''|| document.getElementById("AllMedRemrk").value==null)
						{
							if(lErrorMsg==''){
						        lErrorMsg=lErrorMsg+"Enter Personal History Known Allergies(Allergies to Medicine) Specify Reason ";
						      }
							 if(lField=='') lField='AllMedRemrk'; 						
						}
						else {
						personalHistVal = personalHistVal+",AllMed$AllMedYes(AllMedRemrk@"+document.getElementById("AllMedRemrk").value;
						}
						
					}
				else
				{
				var AllMedList=document.forms[0].AllMed;
				for(var z=0;z<AllMedList.length;z++)
					{
					if(AllMedList[z].checked)
						{
						personalHistVal = personalHistVal+",AllMed$"+AllMedList[z].value;
						}
					}
				}
				if (!document.forms[0].AllSub[0].checked && !document.forms[0].AllSub[1].checked)
				{
					if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Select Personal History Known Allergies(Allergies to Substance other than medicine) Options ";
			     	 }
			        if(lField=='')
			        lField='AllSub';   
				}
				else if(document.forms[0].AllSub[0].checked){
					if(document.getElementById("AllSubRemrk").value==''|| document.getElementById("AllSubRemrk").value==null)
						{
							if(lErrorMsg==''){
						        lErrorMsg=lErrorMsg+"Enter Personal History Known Allergies(Allergies to Substance other than Medicine) Specify Reason ";
						      }
							 if(lField=='') lField='AllSubRemrk'; 						
						}
						else {
						personalHistVal = personalHistVal+",AllSub$AllSubYes(AllSubRemrk@"+document.getElementById("AllSubRemrk").value;
						}
						
					}
				else
				{
				var AllSubList=document.forms[0].AllSub;
				for(var z=0;z<AllSubList.length;z++)
					{
					if(AllSubList[z].checked)
						{
						personalHistVal = personalHistVal+",AllSub$"+AllSubList[z].value;
						}
					}
				}
			}
			
			if(personalHistSubList[j].value=='PR6.1')
				{
				if (!document.forms[0].alcohol[0].checked && !document.forms[0].alcohol[1].checked && !document.forms[0].alcohol[2].checked)
					{
						if(lErrorMsg==''){
				         lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Alcohol options ";
				      	}
				        if(lField=='')
				        lField='alcohol';   
					}
				else
					{
					var alcoholSubList=document.forms[0].alcohol;
					for(var z=0;z<alcoholSubList.length;z++)
						{
						if(alcoholSubList[z].checked)
							{
							personalHistVal = personalHistVal+"(Alcohol$"+alcoholSubList[z].value;
							}
						}
					}
				if (!document.forms[0].tobacco[0].checked && !document.forms[0].tobacco[1].checked && !document.forms[0].tobacco[2].checked)
				{
					if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Tobacco Options ";
			     	 }
			        if(lField=='')
			        lField='tobacco';   
				}
				else if(document.forms[0].tobacco[2].checked)
					{
					if(document.getElementById("packNo").value=='' || document.getElementById("packNo").value==null)
						{
						if(lErrorMsg==''){
					        lErrorMsg=lErrorMsg+"Enter Personal History Habits/Addictions Tobacco Smoking PackNo ";
					      }
					     if(lField=='')
					        lField='packNo';   
						}
					else
						{
						personalHistVal = personalHistVal+",Tobacco$Smoking(PackNo@"+document.getElementById("packNo").value;
						}
					if(document.getElementById("smokeYears").value=='' || document.getElementById("smokeYears").value==null)
					{
					if(lErrorMsg==''){
				        lErrorMsg=lErrorMsg+"Enter Personal History Habits/Addictions Tobacco Smoking Years ";
				      }
				      if(lField=='')
				        lField='smokeYears';   
					}
					else
						{
						personalHistVal = personalHistVal+"*Years@"+document.getElementById("smokeYears").value+")";
						}
				}
				else
				{
					var tobaccoSubList=document.forms[0].tobacco;
					for(var z=0;z<tobaccoSubList.length;z++)
						{
						if(tobaccoSubList[z].checked)
							{
							personalHistVal = personalHistVal+",Tobacco$"+tobaccoSubList[z].value;
							}
						}
				}
						
				if (!document.forms[0].drugUse[0].checked && !document.forms[0].drugUse[1].checked)
				{
					if(lErrorMsg==''){
			       lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Drug Use Options ";
			     	 }
			        if(lField=='')
			        lField='drugUse';   
				}
				else
				{
					var drugUseSubList=document.forms[0].drugUse;
					for(var z=0;z<drugUseSubList.length;z++)
						{
						if(drugUseSubList[z].checked)
							{
							personalHistVal = personalHistVal+",DrugUse$"+drugUseSubList[z].value;
							}
						}
				}
				if (!document.forms[0].betelNut[0].checked && !document.forms[0].betelNut[1].checked)
				{
					if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Betel Nut Options ";
			     	 }
			        if(lField=='')
			        lField='betelNut';   
				}
				else
				{
					var betelNutSubList=document.forms[0].betelNut;
					for(var z=0;z<betelNutSubList.length;z++)
						{
						if(betelNutSubList[z].checked)
							{
							personalHistVal = personalHistVal+",BetelNut$"+betelNutSubList[z].value;
							}
						}
				}
				if (!document.forms[0].betelLeaf[0].checked && !document.forms[0].betelLeaf[1].checked)
				{
					if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Betel Leaf Options ";
			      	}
			        if(lField=='')
			        lField='betelLeaf';   
				}
				else
				{
					var betelLeafSubList=document.forms[0].betelLeaf;
					for(var z=0;z<betelLeafSubList.length;z++)
						{
						if(betelLeafSubList[z].checked)
							{
							personalHistVal = personalHistVal+",BetelLeaf$"+betelLeafSubList[z].value+")";
							}
						}
				}
				
			  }
			}
		}
		
		if(personalSubCount==0)
			{
			if(lErrorMsg==''){
					lErrorMsg=lErrorMsg+"Select Personal History "+personalHistSubList[0].name+" Options ";
					}
	        if(lField=='')
	        lField=personalHistValue;  
			}
		personalSubCount=0;
		personalHistVal = personalHistVal+"#";
	     }
		else
			{
			personalCount++;
			}
	}
	if(personalCount>0)
		{
		if(lErrorMsg==''){
	        lErrorMsg=lErrorMsg+"Select All Personal History Options ";
			}
	        if(lField=='')
	        lField='personalHistory';  
		}
	 var value = $("[name=testCheck]:checked").length;
	 
	if(value<=0){
		if(lErrorMsg=='')
			{
		 		lErrorMsg=lErrorMsg+"Select atleast one investigation ";
			}
		if(lField=='')
			 lField="testCheck";
		}
	//Mandatory Check validation For Examination Findings and its sublist
	var examinFnds=document.forms[0].examinationFnd;
	var examinCount=0;
	var examinSubCount=0;
	var examFndsVal="";
	for(var i=0;i<examinFnds.length;i++)
	{
		if(examinFnds[i].checked)
		{
			//examinCount++;
			var examinFndsValue=examinFnds[i].value;		
			//var examinFndsName=document.forms[0].elements[examinFndsValue].name;
			var examinFndsName=document.forms[0].elements[examinFndsValue].title;
			var subType=document.forms[0].elements[examinFndsValue].type;
			
			if(examinFndsValue=='GE11'){
				if(document.forms[0].temp[0].checked==false && document.forms[0].temp[1].checked==false){
					if(lErrorMsg==''){
						lErrorMsg=lErrorMsg+"Please select C or F option in "+examinFndsName+"";
						}
						if(lField=='')
						lField=examinFndsValue; 
					}
				if(document.forms[0].elements[examinFndsValue].value=="" || document.forms[0].elements[examinFndsValue].value==null)
				{
				if(lErrorMsg==''){
				lErrorMsg=lErrorMsg+"Enter Examination Findings "+examinFndsName+"";
				}
				if(lField=='')
				lField=examinFndsValue; 
				}
				else{
					var tempType= '';
					if(document.forms[0].temp[0].checked==true) tempType='C';
					if(document.forms[0].temp[1].checked==true) tempType='F';
					examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+tempType;
					examFndsVal = examFndsVal+"#";
					}
				}
			else if(examinFndsValue=='GE14'){
				if(document.forms[0].elements[examinFndsValue].value=="" || document.forms[0].elements[examinFndsValue].value==null
						||document.getElementById("BP1").value==""||document.getElementById("BP1").value==null )
					
				{
				if(lErrorMsg==''){
				lErrorMsg=lErrorMsg+"Enter Examination Findings "+examinFndsName+"";
				}
				if(lField=='')
				lField=examinFndsValue; 
				}
				else{
					
					examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+"/"+document.getElementById("BP1").value;
					examFndsVal = examFndsVal+"#";
					}
				}
			else if(examinFndsValue=='GE15'){
				if(document.forms[0].elements[examinFndsValue].value=="" || document.forms[0].elements[examinFndsValue].value==null||
						document.getElementById("BP2").value==""||document.getElementById("BP2").value==null)
					
				{
				if(lErrorMsg==''){
				lErrorMsg=lErrorMsg+"Enter Examination Findings "+examinFndsName+"";
				}
				if(lField=='')
				lField=examinFndsValue; 
				}
				else{
					examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+"/"+document.getElementById("BP2").value;
	           }
				}
			else if(examinFndsValue!='GE14' && examinFndsValue!='GE15'){
				
			if(subType=="text" )
			{
			if(document.forms[0].elements[examinFndsValue].value=="" || document.forms[0].elements[examinFndsValue].value==null)
			{
			if(lErrorMsg==''){
			lErrorMsg=lErrorMsg+"Enter Examination Findings "+examinFndsName+"";
			}
			
			if(lField=='')
			lField=examinFndsValue; 
			}
			else{
				
				examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value;
				
				}
			}
			else
			{
				var examinFndsSubList=document.forms[0].elements[examinFndsValue];
				
				
				for(var j=0;j<examinFndsSubList.length;j++)
				{
				if(examinFndsSubList[j].checked)	
					{
					examFndsVal = examFndsVal+examinFndsValue+"~"+examinFndsSubList[j].value;
					examinSubCount++; 
					if(examinFndsSubList[j].name=="Dehydration" && examinFndsSubList[j].value=="Y")
						{
						if(!document.forms[0].dehydrationY[0].checked && !document.forms[0].dehydrationY[1].checked && !document.forms[0].dehydrationY[2].checked)
							{
							if(lErrorMsg==''){
								lErrorMsg=lErrorMsg+"Select Examination Findings Dehydration Sub Options ";
								}
								if(lField=='')
								lField='dehydrationY'; 
							}
						else
							{
							var d;
							var dehydSubList=document.forms[0].dehydrationY;
							 for(d=0;d<dehydSubList.length;d++)
								{
								if(dehydSubList[d].checked)
									{
									examFndsVal = examFndsVal+dehydSubList[d].value;
									}
								}
	 
							}
						}
					}
				}
				if(examinSubCount==0)
				{
				if(lErrorMsg==''){
				lErrorMsg=lErrorMsg+"Select Examination Findings "+examinFndsSubList[0].name+" Options ";
				}
				if(lField=='')
				lField=examinFndsValue;  
				}
				examinSubCount=0;
			}
			examFndsVal = examFndsVal+"#";
		}
		}
		else
			{
			examinCount++;
			}
		}
		
	if(examinCount>0)
		{
		if(lErrorMsg==''){
	        lErrorMsg=lErrorMsg+"Select All Examination Findings Options ";
			}
	        if(lField=='')
	        lField='examinationFnd'; 
		}
	//Mandatory Check validation For Family History 
	var familyHistory=document.forms[0].familyHistory;
	var familyHistCount=0;
	for(var i=0;i<familyHistory.length;i++)
	{
		if(familyHistory[i].checked)
		{
		familyHistCount++;
		if(familyHistory[i].value=="FH11")
		   {
		   if(document.getElementById("familyHistoryOthr").value=='' || document.getElementById("familyHistoryOthr").value==null)
			   {
			   if(lErrorMsg==''){
			     lErrorMsg=lErrorMsg+"Enter Other Family History Value ";
				 }
			        if(lField=='')
			        lField='familyHistoryOthr'; 
			   }
		   }
		}
	}
	/* if(document.getElementById("diagnosedBy").value==null || document.getElementById("diagnosedBy").value==''){
		if(lErrorMsg==''){
							lErrorMsg=lErrorMsg+"Please enter Doctor Name. ";
		}		
		if(lField=='')
	        lField='diagnosedBy';
	} */
	if(lErrorMsg!=null && (lErrorMsg!='' || lErrorMsg!=undefined) && lErrorMsg.length>0)
	{
		alert(lErrorMsg);
		focusBox(document.getElementById(lField));
		return;
	 }
	

	if(checkType=='submit')
	{
		
	if(lErrorMsg!=null && (lErrorMsg!='' || lErrorMsg!=undefined) && lErrorMsg.length>0)
	{
		alert(lErrorMsg);
		focusBox(document.getElementById(lField));
		return;
	 }
	
	 
	else
	{
		for (var i=0;i<investAttachLength;i++)
			{
				var id='investAttach['+i+']';
				if(document.getElementById(id)!=null)
					{
						if(document.getElementById(id).value==null ||
								document.getElementById(id).value=='')
							{
								if((i==0 && arrayLst!=null && arrayLst.length==0) || (i>0))
									{
										alert('Please add all Investigation Attachments');
										focusBox(document.getElementById(id));
										return false;									
									}
							}
					}
			}
		
		var tableId=document.getElementById('consultDtlsData');
		var tableId1=document.getElementById('consultDtlsDataId');
		var element=document.getElementById('consultBy');
		
		if(tableId1!=null && tableId1.rows.length==2 && tableId.rows.length==2)
			{
				alert('Please add Consultation Details');
				focusBox(document.getElementById('consultBy'));
				return false;
			}
		else if(tableId.rows.length==2 && tableId1==null)
			{
				alert('Please add Consultation Details');
				focusBox(document.getElementById('consultBy'));
				return false;
			}
		
		for (var i=0;i<100;i++)
			{
				var id='consultAttach['+i+']';
				if(document.getElementById(id)!=null)
					{
						if(document.getElementById(id).value==null ||
								document.getElementById(id).value=='')
							{
								alert('Please add all Consultation Attachments');
								focusBox(document.getElementById(id));
								return false;									
									
							}
					}
			}
		
		
		var saveFlag="Submit";
		if(confirm('Do you want to register Annual health checkup case?')==true)
			registerCase(personalHistVal,examFndsVal,patId,saveFlag,checkType);
			
	}
	}
	}  
	function registerCase(personalHistVal,examFndsVal,patId,saveFlag,checkType)
	{
		
	    var selInvData='';
		 var selGenInvData='';
		 var consultationData='',updateGenInvData='';
		 
			document.forms[0].personalHistVal.value=personalHistVal;
	   		document.forms[0].examFndsVal.value=examFndsVal;
	   		


		 for(var i=0;i<ahcInvestList.length;i++)
		 	{
	        var ltext='';
	        var lvalue='';
	        var lId='';
	        var price='';
	        var investInfo = ahcInvestList[i].split("~");
	        
	          ltext = investInfo[1]; 
		   	   lId =  investInfo[0]; 
		   	   price= investInfo[2]; 
	          if((selGenInvData!=null || selGenInvData!='') && selGenInvData.length>0)
	          {
	       	   selGenInvData=selGenInvData+'~';
	          }
	                 
	          selGenInvData=selGenInvData+ltext+'$'+lId+'$'+price;  
	  			
	    	}
	/*Added by venkatesh to save consultation doctors details*/
		 
		
		 for(var i=0;i<consultLst.length;i++)
		 	{
			 	
	     
	     var consultInfo = consultLst[i].split("~");
	     
	       if((consultationData!=null || consultationData!='') && consultationData.length>0)
	       {
	    	   consultationData=consultationData+'~';
	       }
	              
	       consultationData=consultationData+consultInfo[0]+'$'+consultInfo[1]+'$'+consultInfo[2];

	       
				
	 	}
		 
			 
			//alert(selGenInvData);
			//var url="./annualCheckUpAction.do?actionFlag=saveAhcDetails&patientId="+patId+"&addTests="+selGenInvData+"&updateTests="+updateGenInvData+"&saveFlag="+saveFlag+"&checkType="+checkType+"&investAttachLength="+investAttachLength+"&fromPatientsView=${fromPatientsView}";
			var url="./annualCheckUpAction.do?actionFlag=saveAhcDetails&patientId="+patId+"&addTests="+selGenInvData+"&removeInvest="+delAhcInvest+"&addConsultData="+consultationData+"&consultDtlsRemove="+consultDtlsRemove+"&saveFlag="+saveFlag+"&checkType="+checkType+"&investAttachLength="+investAttachLength+"&fromPatientsView=${fromPatientsView}";
			
			//alert(url);
			
			/*document.forms[0].action="./patientDetails.do?actionFlag=saveCaseDetails&patientId="+patId+"&addTests="+selGenInvDatatherapyId="+therapies+"&doctorType="+doctorType */;
			document.forms[0].action=url;
			document.forms[0].method="post";
			document.getElementById("Submit").disabled=true;
			document.getElementById("Submit").className='butdisable';
			document.getElementById("Save").disabled=true;
			document.getElementById("Save").className='butdisable';
			document.getElementById("Reset").disabled=true;
			document.getElementById("Reset").className='butdisable';
			document.forms[0].submit();
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
	function maxLengthPaste(field,maxChars,event)
	{
	      event.returnValue=false;
	      if(window.clipboardData)
	    	  {
	      		if((field.value.length +  window.clipboardData.getData("Text").length) > maxChars) 
				{
	      			alert("Characters should not exceed 3000");
	        	return false;
	        	}
	      		event.returnValue=true;
	    	  }
	      if (event.clipboardData) 
	      {
	    	if((field.value.length + event.clipboardData.getData('text/plain').length) > maxChars)
	    		{
	    		alert("Characters should not exceed 3000");
	        	return false;
	        	}
	      		event.returnValue=true;
	      }
	}
	function browserDetect()
	{
		 var objAgent = navigator.userAgent; 
		 var objbrowserName  = navigator.appName;
		 var objOffsetVersion;
		if ((objOffsetVersion=objAgent.indexOf("Chrome"))!=-1) { 
			 objbrowserName = "Chrome"; 
		}
		else if ((objOffsetVersion=objAgent.indexOf("MSIE"))!=-1) { 
			objbrowserName = "Microsoft Internet Explorer"; 
		}
		else if ((objOffsetVersion=objAgent.indexOf("Firefox"))!=-1) { 
			objbrowserName = "Firefox"; 
		}
		return objbrowserName;
	}
	function checkAlphaNumericCodes(event) {
		browserName=browserDetect();
		//if(navigator.appName=="Microsoft Internet Explorer" || navigator.appVersion=='5.0 (Windows NT 5.1) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.168 Safari/535.19')
		if(browserName=="Microsoft Internet Explorer" || browserName=="Chrome")
		{
		var charCode=event.keyCode;
		if ((charCode<65 || charCode>90)&&(charCode<97 || charCode>122)&&(charCode<48 || charCode>57)&&(charCode!=13 && charCode!=32))
				    return false; 	
						return true;  
		}
		else if(browserName=="Firefox")
		{
			var inputValue = String.fromCharCode(event.keyCode || event.charCode)
			var regExpr = /^[0-9a-zA-Z\s]+$/;
			if(event.keyCode != 0) {
				if(event.keyCode == 38 || event.keyCode == 40 || event.keyCode == 37 || event.keyCode == 39 ||
					event.keyCode == 8 || event.keyCode == 13 || event.keyCode == 46 || event.keyCode == 36 ||
					event.keyCode == 35 || event.keyCode == 33 || event.keyCode == 34 || event.keyCode == 45 ||
					event.keyCode == 9) {
				} else {
					return false;
				}
			} else if(event.charCode != 0){
				if(!inputValue.match(regExpr)) {
					return false;
				}
			}
			return true;
		}
	}
	
	var personalHistory=document.forms[0].personalHistory;
	for(var i=0;i<personalHistory.length;i++)
	    {
		if('${otherFindings.personalHis}'==''){
			personalHistory[i].checked=true;
			  getSubListHistory(personalHistory[i],'NA');
		}
		else{
			if('${otherFindings.personalHis}'.indexOf(personalHistory[i].value,0)!=-1){
				personalHistory[i].checked=true;
	            getSubListHistory(personalHistory[i],'NA'); 
		}
	     }
	    }
		var examinationFnd=document.forms[0].examinationFnd;
		for(var i=0;i<examinationFnd.length;i++)
		{
			if('${otherFindings.examFindg}'==''){
				examinationFnd[i].checked=true;
				getSubFieldType(examinationFnd[i]);
			}
			else{
				if('${otherFindings.examFindg}'.indexOf(examinationFnd[i].value,0)!=-1){
					examinationFnd[i].checked=true;
					getSubFieldType(examinationFnd[i]); 
			}
		     }
		}

	
	var pastHistory=document.forms[0].pastHistory;
	var pastHistoryVal='${otherFindings.pastIllness}'.split("~");
	
	for(var i=0;i<pastHistory.length;i++)
	    {
		for(var j=0;j<pastHistoryVal.length;j++)
		{
		if(pastHistory[i].value==pastHistoryVal[j])
		{
			pastHistory[i].checked=true;
				if(pastHistory[i].value=='PH11' || pastHistory[i].value=='PH8' || pastHistory[i].value=='PH12' || pastHistory[i].value=='PH10'){
				getOtherText(pastHistory[i]);
				if(pastHistory[i].value=='PH11' && '${otherFindings.pastIllenesOthr}'!='')
					document.getElementById('pastHistryOthr').value='${otherFindings.pastIllenesOthr}';
				if(pastHistory[i].value=='PH8' && '${otherFindings.pastIllenesCancers}'!='')
					document.getElementById('pastHistryCancers').value='${otherFindings.pastIllenesCancers}';
				if(pastHistory[i].value=='PH12' && '${otherFindings.pastIllenesEndDis}'!='')
					document.getElementById('pastHistryEndDis').value='${otherFindings.pastIllenesEndDis}';
				if(pastHistory[i].value=='PH10' && '${otherFindings.pastIllenesSurg}'!='')
					document.getElementById('pastHistrySurg').value='${otherFindings.pastIllenesSurg}';
				}
		}}}
	var familyHistory=document.forms[0].familyHistory;
	var famHistoryVal;
	if('${otherFindings.familyHis}'!='')
	{
		famHistoryVal='${otherFindings.familyHis}'.split("~");
		for(var i=0;i<familyHistory.length;i++)
	    {
			for(var j=0;j<famHistoryVal.length;j++)
			{
				if(familyHistory[i].value==famHistoryVal[j])
				{
					familyHistory[i].checked=true;
					if(familyHistory[i].value=='FH11'){	
						getOtherText(familyHistory[i]);
						if('${otherFindings.familyHistoryOthr}'!='')
							document.getElementById('familyHistoryOthr').value='${otherFindings.familyHistoryOthr}';
					}
				}
			}
		} 
	}
	
	if('${otherFindings.lstPerHis}'!=null && '${otherFindings.lstPerHis}'.indexOf("PR5.1",0)!=-1)
		{ 
			var KnownAllg='<table width="100%" border="1"><tr><td>'+
		'Allergic to Medicine:<input type="radio" name="AllMed" id="AllMed" value="AllMedYes" onclick="displayTextBox(this)" title="Yes"/>Yes'+
		'<input type="radio" name="AllMed" id="AllMed" value="AllMedNo" onclick="displayTextBox(this)" title="No"/>No'+
		'<div id="AllMedDiv" style="display:none">Specify:<input type="text" name="AllMedRemrk" id="AllMedRemrk" maxlength="300" title="Remark" style="width:7em" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpecify(this)"/></div></td></tr>'+
		'<tr><td>Allergic to Substance other than medicine:<br><input type="radio" name="AllSub" id="AllSub" value="AllSubYes" onclick="displayTextBox(this)" title="Yes"/>Yes'+
		'<input type="radio" name="AllSub" id="AllSub" value="AllSubNo" onclick="displayTextBox(this)" title="No"/>No'+
		'<div id="AllSubDiv" style="display:none">Specify:<input type="text" name="AllSubRemrk" id="AllSubRemrk" maxlength="300" title="Remark" style="width:7em" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpecify(this)"/></div></td></tr></table>';
	    
		document.getElementById("Known AllergiesTd").innerHTML=KnownAllg;
		}
		if('${otherFindings.lstPerHis}'!=null && '${otherFindings.lstPerHis}'.indexOf("PR6.1",0)!=-1)
	{
	var personalHabits='<table width="100%" border="1"><tr><td>'+
	'Alcohol:<input type="radio" name="alcohol" id="alcohol" value="Regular" title="Regular"/>Regular'+
	'<input type="radio" name="alcohol" id="alcohol" value="Occasional" title="Occasional"/>Occasional'+
	'<input type="radio" name="alcohol" id="alcohol" value="Teetotaler" title="Teetotaler"/>Teetotaler </td></tr>'+
		'<tr><td>Tobacco:<input type="radio" name="tobacco" id="tobacco" value="Snuff" onclick="displayPackYears(this)" title="Snuff"/>Snuff'+
	'<input type="radio" name="tobacco" id="tobacco" value="Chewable" onclick="displayPackYears(this)" title="Chewable"/>Chewable'+
	'<input type="radio" name="tobacco" id="tobacco" value="Smoking" onclick="displayPackYears(this)" title="Smoking"/>Smoking'+
	'<div id="smokingTd" style="display:none">'+
	'Pack :<input type="text" name="packNo" id="packNo" maxlength="2" title="Smoking Pack No" style="width:7em" onchange="validateNumber(\'Smoking Pack No\',this)"/>  (per day)<br>'+
	'Years:<input type="text" name="smokeYears" id="smokeYears" maxlength="2" title="Smoking Years" style="width:7em" onchange="validateNumber(\'Smoking Years\',this)"/>  (since years)</div></td></tr>'+
	'<tr><td>Drug Use:<input type="radio" name="drugUse" id="drugUse" value="Yes" title="Yes"/>Yes'+
	'<input type="radio" name="drugUse" id="drugUse" value="No" title="No"/>No</td></tr>'+
	'<tr><td>Betel nut:<input type="radio" name="betelNut" id="betelNut" value="Yes" title="Yes"/>Yes'+
	'<input type="radio" name="betelNut" id="betelNut" value="No" title="No"/>No</td></tr>'+
	'<tr><td>Betel leaf:<input type="radio" name="betelLeaf" id="betelLeaf" value="Yes" title="Yes"/>Yes'+
	'<input type="radio" name="betelLeaf" id="betelLeaf" value="No" title="No"/>No</td></tr></table>';
	document.getElementById("Habits/AddictionsTd").innerHTML=personalHabits;
	}

		var personalHistVal= '${otherFindings.lstPerHis}';	
		personalHistVal=personalHistVal.replace("[","");
		personalHistVal=personalHistVal.replace("]","");
		var perHistListing=personalHistVal.split(",");
		for(i=0;i<perHistListing.length;i++){
			var pr= perHistListing[i];
			var prval=pr.substring(0,6);
			prval1=pr.substring(0,5);
			var prval2=prval.substring(prval.length-1,prval.length);
			var val=parseInt(prval2,10)-1;
			if(prval1.trim()=="PR1.1"){
				document.forms[0].PR1[val].checked='checked';
			}
			if(prval1.trim()=="PR2."){
				document.forms[0].PR2[val].checked='checked';
			}
			if(prval1.trim()=="PR3."){
				document.forms[0].PR3[val].checked='checked';
			}
			if(prval1.trim()=='PR4.'){
				if(document.forms[0].PR4!=null && document.forms[0].PR4[val]!=null)
					document.forms[0].PR4[val].checked='checked';
			}
			if(prval1.trim()=='PR5.'){
				document.forms[0].PR5[val].checked='checked';
			}
			if(prval1.trim()=='PR6.'){
				document.forms[0].PR6[val].checked='checked';
			}
			if(prval1.trim()=='PR7.'){
				document.forms[0].PR7[val].checked='checked';
			}

		}
		
	
	var addition='${otherFindings.addiction}';var additionKnown='${otherFindings.allergy}';
		addition=addition.replace("PR6.1(","");
		additionKnown=additionKnown.replace("PR5.1,","");
		var additionList = addition.split(",");
		var addKnownList = additionKnown.split(",");
		if(addKnownList.length>0){
			
			for(var i = 0; i<addKnownList.length;i++)
		    {	            			    
				var addtn = addKnownList[i].split("$");	
				           		        
				if(addtn[0]=='AllMed'){
					var spitedY = addtn[1].split("(");	
					if(spitedY[0]=='AllMedYes'){	               						
						document.forms[0].AllMed[0].checked='checked';
						document.getElementById("AllMedDiv").style.display='block';
						if(spitedY.length>1){
						var valueY = spitedY[1].split("@");					
						document.getElementById("AllMedRemrk").value=valueY[1];
						}
					}
					else if(addtn[1]=='AllMedNo'){
						document.forms[0].AllMed[1].checked='checked';
				}
			   }
				if(addtn[0]=='AllSub'){
					var spitedY = addtn[1].split("(");	
					if(spitedY[0]=='AllSubYes'){
						
						document.forms[0].AllSub[0].checked='checked';
						document.getElementById("AllSubDiv").style.display='block';
						if(spitedY.length>1){
							var valueY = spitedY[1].split("@");					
							document.getElementById("AllSubRemrk").value=valueY[1];
							}
					}
					else if(addtn[1]=='AllSubNo'){
						document.forms[0].AllSub[1].checked='checked';
				}
			   }
		}
		}
		if(additionList.length>0)
		{
			for(var i = 0; i<additionList.length;i++)
		    {	
			
			    var addtn = additionList[i].split("$");
			    if(addtn[0]=='Alcohol')
			    	{if(addtn[1]=='Regular')
			    		document.forms[0].alcohol[0].checked='checked';
			    	else if (addtn[1]=='Occasional')
			    		document.forms[0].alcohol[1].checked='checked';
			    	else if (addtn[1]=='Teetotaler')
			    		document.forms[0].alcohol[2].checked='checked';
			    	}
			    else if(addtn[0]=='Tobacco')
				    {
			    	var tabacoLst = addtn[1].split("(");
			    	
			    	if(tabacoLst[0]=='Snuff')
			    		document.forms[0].tobacco[0].checked='checked';
			    	else if (tabacoLst[0]=='Chewable')
			    		document.forms[0].tobacco[1].checked='checked';
			    	else if (tabacoLst[0]=='Smoking')
				    	{
			    		document.forms[0].tobacco[2].checked='checked';
			    		tabacoLst[1] = tabacoLst[1].replace(")","");
			    		
			    		document.getElementById("smokingTd").style.display='block';
			    		
			    		var smokSub = tabacoLst[1].split("*");
			    	
			    		if(smokSub.length>0)
				    		{
		                       for(var j=0;j<smokSub.length;j++){
		                    	   
		                    	  var smokeVal= smokSub[j].split("@");
		                    	  
		                    	  if(smokeVal[0]=='PackNo')
		                    		  document.forms[0].packNo.value=smokeVal[1];
		                    	  else
		                    		  document.forms[0].smokeYears.value=smokeVal[1];
		                           } 
				    		}
				    	}
		             }
			    else if(addtn[0]=='DrugUse')
				    {
		              if(addtn[1]=='Yes')
		            	  document.forms[0].drugUse[0].checked='checked';
		              else  if(addtn[1]=='No')
		            	  document.forms[0].drugUse[1].checked='checked';
		            }
			    else if(addtn[0]=='BetelNut')
			    {
			    	if(addtn[1]=='Yes')
		          	  document.forms[0].betelNut[0].checked='checked';
		            else  if(addtn[1]=='No')
		          	  document.forms[0].betelNut[1].checked='checked';
			    }
			    else if(addtn[0]=='BetelLeaf')
			    {
			    	addtn[1] = addtn[1].replace(")","");
			    	if(addtn[1]=='Yes')
		          	  document.forms[0].betelLeaf[0].checked='checked';
		            else  if(addtn[1]=='No')
		          	  document.forms[0].betelLeaf[1].checked='checked';
			    }
		    }
		}	
		if('${otherFindings.height}'!='NA' && '${otherFindings.height}'!=''){
			if(document.forms[0].GE1!=null)
				{
					document.forms[0].GE1.value='${otherFindings.height}';
					heightVar='${otherFindings.height}';
				}	
		}
		if('${otherFindings.weight}'!='NA' && '${otherFindings.weight}'!=''){
			if(document.forms[0].GE2!=null)
				{
					document.forms[0].GE2.value='${otherFindings.weight}';
					weightVar='${otherFindings.weight}';
				}
		}
		
		/* if('${otherFindings.bmi}'!='NA' && '${otherFindings.bmi}'!='')
			document.forms[0].GE3.value='${otherFindings.bmi}'; */
		if('${otherFindings.pallor}'!=''){
			if(document.forms[0].GE4!=null )
				{
					if('${otherFindings.pallor}'=='Y')
			      	  document.forms[0].GE4[0].checked='checked';
			        else  if('${otherFindings.pallor}'=='N')
			      	  document.forms[0].GE4[1].checked='checked';
				}	
			}
		if('${otherFindings.cyanosis}'!=''){
			if(document.forms[0].GE5!=null )
				{
					if('${otherFindings.cyanosis}'=='Y')
			      	  document.forms[0].GE5[0].checked='checked';
			        else  if('${otherFindings.cyanosis}'=='N')
			      	  document.forms[0].GE5[1].checked='checked';
				}	
		}
		if('${otherFindings.clubbingOfFingers}'!=''){
			if(document.forms[0].GE6!=null )
				{
					if('${otherFindings.clubbingOfFingers}'=='Y')
			      	  document.forms[0].GE6[0].checked='checked';
			        else  if('${otherFindings.clubbingOfFingers}'=='N')
			      	  document.forms[0].GE6[1].checked='checked';
				}	
		}
		if('${otherFindings.lymphadenopathy}'!=''){
			if(document.forms[0].GE7!=null )
				{
					if('${otherFindings.lymphadenopathy}'=='Y')
			      	  document.forms[0].GE7[0].checked='checked';
			        else  if('${otherFindings.lymphadenopathy}'=='N')
			      	  document.forms[0].GE7[1].checked='checked';
				}	
		}
		if('${otherFindings.oedema}'!=''){
			if(document.forms[0].GE8!=null )
				{
					if('${otherFindings.oedema}'=='Y')
			      	  document.forms[0].GE8[0].checked='checked';
			        else  if('${otherFindings.oedema}'=='N')
			      	  document.forms[0].GE8[1].checked='checked';
				}	
		}
		if('${otherFindings.malNutrition}'!=''){
			if(document.forms[0].GE9!=null )
				{
					if('${otherFindings.malNutrition}'=='Y')
			      	  document.forms[0].GE9[0].checked='checked';
			        else  if('${otherFindings.malNutrition}'=='N')
			      	  document.forms[0].GE9[1].checked='checked';
				}	
		}
		if('${otherFindings.dehydration}'!=''){
			if('${otherFindings.dehydration}'=='Y'){
				if(document.forms[0].GE10!=null )
					{
			      	  document.forms[0].GE10[0].checked='checked';
			      	  var examinField="<input type='radio' name='dehydrationY' id='dehydrationY' value='Mild' title='Mild'/>Mild<input type='radio'  name='dehydrationY' id='dehydrationY' value='Moderate' title='Moderate'/>Moderate<input type='radio'  name='dehydrationY' id='dehydrationY' value='Severe' title='Severe'/>Severe";
				      document.getElementById("DehydrationSub").innerHTML=examinField;
			      	  
			      	  if('${otherFindings.dehydrationType}'!='' && document.forms[0].dehydrationY!=null){
			      		if('${otherFindings.dehydrationType}'=='Mild')
			      		document.forms[0].dehydrationY[0].checked='checked';
			      		if('${otherFindings.dehydrationType}'=='Moderate')
			      		document.forms[0].dehydrationY[1].checked='checked';
			      		if('${otherFindings.dehydrationType}'=='Severe')
			      		document.forms[0].dehydrationY[2].checked='checked';
			          }
					} 
			}
	        else  if('${otherFindings.dehydration}'=='N' && document.forms[0].GE10!=null)
	      	  document.forms[0].GE10[1].checked='checked';
		}	
		if('${otherFindings.temperature}'!='NA' && '${otherFindings.temperature}'!='' &&
				document.forms[0].GE11!=null){
			var temp = '${otherFindings.temperature}';
			var tempType = temp.charAt(temp.length - 1);
			temp = temp.slice(0,str.length-1);
			document.forms[0].GE11.value=temp;	
			
			if(document.forms[0].temp!=null)
				{
					if(tempType=="C"){
						document.forms[0].temp[0].checked='checked';
						}
					else if(tempType=="F")
						document.forms[0].temp[1].checked='checked';
				}	
		}	
		if('${otherFindings.pulseRate}'!='NA' && '${otherFindings.pulseRate}'!='' && document.forms[0].GE12!=null)
			document.forms[0].GE12.value='${otherFindings.pulseRate}';
		if('${otherFindings.respirationRate}'!='NA' && '${otherFindings.respirationRate}'!='' && document.forms[0].GE13!=null)
			document.forms[0].GE13.value='${otherFindings.respirationRate}';
		if('${otherFindings.bpLmt}'!='NA' && '${otherFindings.bpLmt}'!=''
				 && document.forms[0].GE14!=null
				 && document.forms[0].BP1!=null){
			var bpLmt='${otherFindings.bpLmt}'.split("/");
			document.forms[0].GE14.value=bpLmt[0];
			document.forms[0].BP1.value=bpLmt[1];	
		}	
		if('${otherFindings.bpRmt}'!='NA' && '${otherFindings.bpRmt}'!=''
			 && document.forms[0].GE15!=null
			 && document.forms[0].BP2!=null)
			{var bpRmt='${otherFindings.bpRmt}'.split("/");
			document.forms[0].GE15.value=bpRmt[0];
			document.forms[0].BP2.value=bpRmt[1];	
		}



		if('${genInvestFlag}'!=null && '${genInvestFlag}'!=''){
			if(document.getElementById("genTestTableID")!=null)
			document.getElementById("genTestTableID").style.display="";
			genOldList='${genInvestLst}'.split("@");
			genOldList.splice(genOldList.length-1,1);
		}

			
		var browserName=navigator.appName;
		if(browserName=="Microsoft Internet Explorer")
			{
			//For validating maxlength onpaste event--For textarea fields
			document.getElementById("presentHistory").attachEvent("paste", pasteInterceptPresent, false);
			
			
			}
		
		
	</script>
	
	</logic:equal>
	<script>
	$("#genBlockInvestName").select2();
	$("#genInvestigations").select2();
	var consultDtlsRemove='';var oldConsultList=new Array();
	if('${oldConsultLst}'!=null && '${oldConsultLst}'!='')
		oldConsultList='${oldConsultLst}'.split("@");
	
	function fn_deleteConsultOnload(src,investId){
		consultDtlsRemove=consultDtlsRemove+investId+"@";
    		var oRow = src.parentNode.parentNode; 
    		document.getElementById("consultDtlsDataId").deleteRow(oRow.rowIndex);
    		//oldConsultList.splice(oRow.rowIndex-1,1);
    		for(var i=0;i<oldConsultList.length;i++){
        		
    	        if(oldConsultList[i].indexOf(investId,0)!=-1)
    	        	{
    	        	//var schemeId=document.getElementById("scheme").value;
    	        	//var patientScheme="";
    	        	//if(document.getElementById("patientScheme"))
    	        	// patientScheme=document.getElementById("patientScheme").value;
    	        	//var hospType=document.getElementById("hosptype").value;
    	        	oldConsultList.splice(i,1);
    	        	consultAttachLst.splice(i,1);
    	        	//alert(oldConsultList);
    	        	}
    		}
    		
    		if(document.getElementById("consultDtlsDataId")!=null)
    			{
		    		if(document.getElementById("consultDtlsDataId").rows.length==2)
		    			document.getElementById("consultDtlsDataId").style.display='none';
		    		else
		    			document.getElementById("consultDtlsDataId").style.display='';
    			}
	}
	
	</script>
	</html>
</fmt:bundle>