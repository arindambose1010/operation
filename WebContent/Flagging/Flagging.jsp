<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/include.jsp"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Flagging</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</head>
	<style>
	td.empty
		{
			padding:5px;
			width:100%;
			border-width:1px;
		}
	body{font-size:1.3em !important;}
	</style>
	<script>
	function getSize(id)
	{
	    var myFSO = new ActiveXObject("Scripting.FileSystemObject");
	    var filepath = document.forms[0].id.value;
	    var thefile = myFSO.getFile(filepath);
	    var size = thefile.size;
	    alert(size + " bytes");
	}
		function focusBox(id)
		{
			aField=id;
			setTimeout("aField.focus()",0);
		}
		function textCounter( field, fieldName, maxlimit )
		{
			var specialCharName=/^[0-9a-zA-Z .,&()\/\n]*$/;
			if(field.id=="flagRemarks")
				{
					var remarks=document.getElementById(field.id).value;
					if(remarks.length>0)
						{
							if(remarks.charAt(0)==" ")
								{
									alert("Remarks cannot start with a space");
									focusBox(document.getElementById(field.id));
								}
							if(!specialCharName.test(remarks))
								{
									alert("Remarks cannot have special characters and numbers");
									focusBox(document.getElementById(field.id));
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
									focusBox(document.getElementById(field.id));
								}
						}
				}	
			
			
			if ( field.value.length > maxlimit )
			{
			   field.value = field.value.substring( 0, maxlimit );
			   alert(fieldName+' length cannot be more than '+ maxlimit+' characters');
			   var fieldValue1=field.value;
			   fieldValue1.trim().substring(0,3999);
			   return false;
			 }
				return true;
		}
		function fn_checkValue(id)
		{
			var specialCharName=/^[0-9a-zA-Z .,&()\/\n]*$/;
			
			if(id=="flagRemarks")
				{
					var remarks=document.getElementById(id).value;
					if(remarks.length>0)
						{
							if(remarks.charAt(0)==" ")
								{
									alert("Remarks cannot start with a space");
									focusBox(document.getElementById(id));
									return false;
								}
							if(!specialCharName.test(remarks))
								{
									alert("Remarks cannot have special characters and numbers");
									focusBox(document.getElementById(id));
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
									focusBox(document.getElementById(id));
									return false;
								}
						}
				}	
			else if(id=="amount")
				{
					var amount=document.getElementById(id).value;
					if(amount.length>0)
						{
							if((isNaN(amount)==true)||(amount==0))
								{
									alert("Amount can only have digits and must be greater than 0");
									focusBox(document.getElementById(id));
									return false;
								}
						}
					var count=0;
					for(var i=0;i<amount.length;i++)
					{
						if(amount.charAt(i)==" ")
							{
								count++;
							}
					}
				    if(count>0)
				    	{
				    		alert("Amount can only have digits");
							focusBox(document.getElementById(id));
							return false;
				    	}
				}
				else if(id=="amountRefund")
				{
					var amountRefund=document.getElementById(id).value;
					if(amountRefund.length>0)
						{
							if((isNaN(amountRefund)==true))
								{
									alert("Amount can only have digits");
									focusBox(document.getElementById(id));
									return false;
									/* ||(amountRefund==0)||(amount!=amountRefund) */
								}
						}
					var count=0;
					for(var i=0;i<amountRefund.length;i++)
					{
						if(amountRefund.charAt(i)==" ")
							{
								count++;
							}
					}
				    if(count>0)
				    	{
				    		alert("Amount refund can only have digits");
							focusBox(document.getElementById(id));
							return false;
				    	}
				}	
			}	
		function fn_checkAttachValue(name,id)
			{
				var specialCharName=/^[a-zA-Z0-9.]*$/;
				
				if(!specialCharName.test(name))
					{
						alert("Attachments cannot have special characters and spaces");
						document.getElementById(id).value=null;
						focusBox(document.getElementById(id));
						return false;
					}
								
				if(!(window.ActiveXObject))
					{
						var size=document.getElementById(id).files[0].size;
						if(size>204800)
							{
								alert("Cannot upload a file of size more than 200KB");
								document.getElementById(id).value=null;
								focusBox(document.getElementById(id));
								return false;
							}		
					}
			}
		
		function fn_flagSelect(id)
			{
				var flagValue=document.getElementById(id).value;
				document.getElementById("flagAttach").style.display="";

				if(flagValue=="-1")
					{
						document.getElementById("flagAttach").style.display="none";
						document.getElementById("amount").disabled=true;
						document.getElementById("amount").value="";
						
						if(document.getElementById("flagStatus1").disabled==true)
							document.getElementById("flagStatus1").disabled=false;
						if(document.getElementById("flagStatus2").disabled==true)
							document.getElementById("flagStatus2").disabled=false;
						if(document.getElementById("flagStatus3").disabled==false)
							document.getElementById("flagStatus3").disabled=true;
					}
				else if(flagValue=='CD354')
					{
						document.getElementById("flagAttach").style.display="";
						document.getElementById("amount").disabled=false;
						document.getElementById("amount").value="";

						document.getElementById("flagStatus1").checked=false;
						document.getElementById("flagStatus2").checked=false;
						
						if(document.getElementById("flagStatus1").disabled==true)
							document.getElementById("flagStatus1").disabled=false;
						if(document.getElementById("flagStatus2").disabled==false)
							document.getElementById("flagStatus2").disabled=true;
					}
				else if(flagValue!=null&&flagValue!="")
					{
						document.getElementById("flagAttach").style.display="";
						document.getElementById("amount").disabled=true;
						document.getElementById("amount").value="";
						
						document.getElementById("flagStatus1").checked=false;
						document.getElementById("flagStatus2").checked=false;
						
						if(document.getElementById("flagStatus1").disabled==true)
							document.getElementById("flagStatus1").disabled=false;
						if(document.getElementById("flagStatus2").disabled==true)
							document.getElementById("flagStatus2").disabled=false;
					}
			}
		function fn_submitFlag()
		{	
			var error=0;
			var flagNature=document.getElementById("flagNature").value;
			if(flagNature==-1)
				{
					error=1;
					alert("Please select the nature of the Flag");
					focusBox(document.getElementById("flagNature"));
					return false;
				}
			var flagStatus1=document.getElementById("flagStatus1").checked;
			var flagStatus2=document.getElementById("flagStatus2").checked;
			var flagStatus3=document.getElementById("flagStatus3").checked;
			
			
			if(flagNature=='CD354')
				{
					if((document.getElementById("flagStatus1").disabled==false))
						{
							if((flagStatus1==false)&&(flagStatus2==false)&&(flagStatus3==false))
								{
									error=1;
									alert("Please select any status for the flag");
									focusBox(document.getElementById("flagStatus1"));
									return false;
								}
						}
				}
			
			if((document.getElementById("flagStatus1").disabled==false)&&(document.getElementById("flagStatus2").disabled==false))
				{
					if((flagStatus1==false)&&(flagStatus2==false)&&(flagStatus3==false))
						{
							error=1;
							alert("Please select any status for the flag");
							focusBox(document.getElementById("flagStatus1"));
							return false;
						}
				}	
			var flagRemarks=document.getElementById("flagRemarks").value;
			if(flagRemarks==null||flagRemarks=="")
				{
					error=1;
					alert("Flag remarks are mandatory");
					focusBox(document.getElementById("flagRemarks"));
					return false;
				}
			var ret=fn_checkValue("flagRemarks");
			if(ret==false)
				return false;
			if(flagNature=='CD354')
				{
					var amount=document.getElementById("amount").value;
					if(amount==null||amount=="")
						{
							error=1;
							alert("Amount cannot be empty for the money collection Flag");
							focusBox(document.getElementById("amount"));
							return false;
						}
					var ret=fn_checkValue("amount");
					if(ret==false)
						return false;
				}
			if(flagNature=='CD354' && document.getElementById("flagStatus3").checked==true)
				{
					var amountRefund=document.getElementById("amountRefund").value;
					if(amountRefund==null||amountRefund=="")
						{
							error=1;
								alert("Refund amount cannot be empty for the Money Collection DeFlagging");
							focusBox(document.getElementById("amountRefund"));
							return false;
						}
					var ret=fn_checkValue("amountRefund");
					if(ret==false)
						return false;
				}
			var attach=document.getElementById("attachment0").value;
			
			if(document.getElementById("flagStatus4").checked!=true)
				{
					if(attach==null||attach=="")
						{
							error=1;
							alert("Attachments are mandatory");
							focusBox(document.getElementById("attachment0"));
							return false;
						}
				}	
			
			if(error==0)
				{
					error=fn_checkSimilarUpload();
					if(error==0)
						{
							var con =confirm("Do you want to proceed ?");
							if(con==true)
								{
									document.getElementById("subFlag").disabled="true";
									
									if(document.getElementById("flagNature")!=null)
										document.getElementById("flagNature").disabled=false;
									if(document.getElementById("flagStatus")!=null)
										document.getElementById("flagStatus").disabled=false;
									if(document.getElementById("flagRemarks")!=null)
										document.getElementById("flagRemarks").disabled=false;
									
						
									document.forms[0].action='/Operations/flaggingAction.do?actionFlag=saveFlagDtls&flagged&flaggedCasesForApproval=${flaggedCasesForApproval}&caseId=${caseId}&newFlag=${newFlag}';
									document.forms[0].method="post";
									document.forms[0].submit();
								}
							else 
								return false;
						}
				}
			
			
			
		}
		function fn_addRemoveElement(id)
			{
				var attachDiv=document.getElementById("attachDiv");
				var attachValId=document.getElementById("attachValue");

				if(id=='add')
				{
					var attachVal=Number(attachValId.value)+1;
						if(attachVal<=5)
							{
								attachValId.value=attachVal;
								var newDiv=document.createElement('div');
								var divName='attachDiv'+attachVal;
								newDiv.setAttribute('id',divName);
									if(attachVal==2)
					    				newDiv.innerHTML='<input type="file" id="attachment1" name="doc[1]" onchange="javascript:checkExtension(id)">';
					    			else if(attachVal==3)
										newDiv.innerHTML='<input type="file" id="attachment2" name="doc[2]" onchange="javascript:checkExtension(id)">';
									else if(attachVal==4)
										newDiv.innerHTML='<input type="file" id="attachment3" name="doc[3]" onchange="javascript:checkExtension(id)">';
									else if(attachVal==5)
						    			newDiv.innerHTML='<input type="file" id="attachment4" name="doc[4]" onchange="javascript:checkExtension(id)">';
						    			
									attachDiv.appendChild(newDiv);	
							}
						else
							{
								alert("A maximum of 5 attachments can only be attached");
								focusBox(document.getElementById(id));
								return false;
							}
				}
				
				if(id=='remove')
					{
						if(attachValId.value>1)
							{
								var attachVal=Number(attachValId.value);
								
								var oldDiv=document.getElementById('attachDiv'+attachVal);
								attachDiv.removeChild(oldDiv);
								attachValId.value=(attachVal-1);
							}
						else
							{
								alert("Atleast one attachment is mandatory");
								focusBox(document.getElementById(id));
								return false;
							}
						
					}
			}
		function fn_checkSimilarUpload()
			{
				 var fileNames = new Array();
					for(var i=0;i<5;i++)
						{
							var id='attachment'+i;
							if(document.getElementById(id)!=null)
								{
									fileNames[i]=document.getElementById(id).value;
								}	
						}
					 if(fileNames.length>1)
						{
						 	var check;
						 	var removeArr=new Array();
								for(var i=0;i<fileNames.length-1;i++)
									{
										check=fileNames[i];
										if(check.length>0)
											{
												for(var j=i+1;j<=fileNames.length;j++)
													{
														var count=0;
															for(var k=0;k<removeArr.length;k++)
																{
																	if(removeArr[k]==j)
																	count++;
																}
													
															if((check.localeCompare(fileNames[j])==0)&&(count==0))
																{
																	removeArr.push(j);
																}
													}				
											}
									}
								if(removeArr.length>0)
									{
										alert("Cannot upload Similar documents");
										for(var l=0;l<removeArr.length;l++)
											{
												var id='attachment'+removeArr[l];
												document.getElementById(id).value=null;
											}
										return 1;
									}	
						} 
					 return 0;
			}
		function checkExtension(id)
			{
				fileName=document.getElementById(id).value;
					if((fileName!=null)||(fileName!="")||(fileName.length>0))
						{
							var dotPos=fileName.lastIndexOf(".");
							var ext=fileName.substring((dotPos+1),fileName.length);
								if((ext=="png")||(ext=="PNG")||(ext=="gif")||(ext=="GIF")||(ext=="jpeg")||(ext=="JPEG")||(ext=="jpg")||(ext=="JPG")||(ext=="pdf")||(ext=="PDF")) 
									{
										var split=fileName.split("\\");
										fileName=split[(split.length)-1];
										
											if(fileName.length>104)
												{
													alert("Filename cannot be of length more than 100 characters");
													document.getElementById(id).value=null;
													focusBox(document.getElementById(id));
													return false;
												}
											else
												{
													fn_checkAttachValue(fileName,id);
												}
									}
								else
									{
										alert("Please upload valid attachments only(PNG/GIF/JPEG/JPG/PDF)");
										document.getElementById(id).value=null;
										focusBox(document.getElementById(id));
										return false;
									}
						}

			}
		
		function getAttachments(caseId,flagId)
			{
					url="/Operations/flaggingAction.do?actionFlag=getAttachments&caseId="+caseId+"&flagId="+flagId;
			 		window.open(url,"_blank",'toolbar=no,resizable=yes,scrollbars=yes,menubar=no,location=no,height=500,width=1000,left=10,top=15');
			}	
		
		function deFlagSelect(status,fNature,amount,flagId)
			{
			var userGroup ='${userGroup}';
			
			if(status=='Y')
				{
					if(userGroup!=null && userGroup=='GP63')
						{
							if(document.getElementById("container")!=null)
								document.getElementById("container").style.display='';
						}
					if(fNature=='CD354')
						alert("To Deflag Money Collection Amount Refunded,Remarks and attachments are mandatory");
					else
						alert("To Deflag Remarks and attachments are mandatory");
					document.getElementById("deFlagId").value=flagId;
					focusBox(document.getElementById("flagRemarks"));
					
					if(document.getElementById("forward")!=null)
						document.getElementById("forward").style.display="none";
					
					document.getElementById("flagStatus1").disabled=true;
					document.getElementById("flagStatus2").disabled=true;
					document.getElementById("flagStatus3").checked=true;
						
					document.getElementById("flagNature").value=fNature;
					document.getElementById("flagNature").disabled=true;
							
					document.getElementById("flagAttach").style.display="";
						if(fNature=='CD354')
							{
								document.getElementById("amount").value=amount;
								document.getElementById("amountRef").style.display="";
								if(document.getElementById("amountRefund")!=null)
									document.getElementById("amountRefund").value="";
							}	
						else
							document.getElementById("amount").value="";
				}
	
			if(status=='N')	
				{
					if(userGroup!=null && userGroup=='GP63')
						{
							if(document.getElementById("container")!=null)
								document.getElementById("container").style.display='';
						}
					document.getElementById("deFlagId").value=flagId;
					alert("To Update Remarks and attachments are mandatory");
					focusBox(document.getElementById("flagRemarks"));
					
						document.getElementById("forward").style.display="none";
						document.getElementById("amountRef").style.display="none";
							
						document.getElementById("flagStatus1").checked=false;
						document.getElementById("flagStatus2").checked=false;
						document.getElementById("flagStatus3").checked=false;
						document.getElementById("flagStatus4").checked=false;	
						
						document.getElementById("flagStatus1").disabled=true;
						document.getElementById("flagStatus2").disabled=true;
						document.getElementById("flagStatus3").disabled=true;
						
						document.getElementById("flagNature").disabled=true;
						document.getElementById("flagNature").value=fNature;
						
						document.getElementById("flagAttach").style.display="";
						if(fNature=='CD354')
							document.getElementById("amount").value=amount;
						else
							document.getElementById("amount").value="";
				}	
			if(status=='fwd')	
				{
					alert("To Forward to JEO remarks are mandatory");
					document.getElementById("deFlagId").value=flagId;
					focusBox(document.getElementById("flagRemarks"));
				
					document.getElementById("flagStatus1").disabled=true;
					document.getElementById("flagStatus2").disabled=true;
					document.getElementById("flagStatus3").disabled=true;
					
					document.getElementById("forward").style.display="";
					document.getElementById("amountRef").style.display="none";	
					
					document.getElementById("flagStatus4").checked=true;
					document.getElementById("flagNature").value=fNature;
					document.getElementById("flagNature").disabled=true;
						
					document.getElementById("flagAttach").style.display="";
					if(fNature=='CD354')
						document.getElementById("amount").value=amount;
					else
						document.getElementById("amount").value="";
				}
			}
		function fn_resetDetails()
		{
			var con =confirm("Do you want to Reset all the details ?");
			if(con==true)
				{
					if(document.getElementById("flagNature").disabled==true)
						document.getElementById("flagNature").disabled=false;
			
					document.getElementById("flagNature").value=-1;
					document.getElementById("flagStatus1").checked=false;
					document.getElementById("flagStatus2").checked=false;
					document.getElementById("flagStatus3").checked=false;
					document.getElementById("flagRemarks").value="";
					document.getElementById("amount").value="";
			
				if(document.getElementById("amountRefund")!=null)
					document.getElementById("amountRefund").value="";
			
				if(document.getElementById("flagStatus4")!=null)
					document.getElementById("flagStatus4").checked=false;
			
					document.getElementById("flagAttach").style.display="none";
					document.getElementById("amountRef").style.display="none";
					document.getElementById("forward").style.display="none";
			
					document.getElementById("attachment0").value="";
				if(document.getElementById("attachment1")!=null)
					document.getElementById("attachment1").value="";				
				if(document.getElementById("attachment2")!=null)
					document.getElementById("attachment2").value="";				
				if(document.getElementById("attachment3")!=null)
					document.getElementById("attachment3").value="";				
				if(document.getElementById("attachment4")!=null)
					document.getElementById("attachment4").value="";			
				if(document.getElementById("fdf")!=null)
					document.getElementById("fdf").checked="";	
			
					document.getElementById("flagStatus1").disabled=false;
					document.getElementById("flagStatus2").disabled=false;
					document.getElementById("flagStatus3").disabled=true;
				}
		}
function alertMsg()
	{
	var caseIdS ='${caseIdS}';
		 if(caseIdS!=null)
			{
			var caseId1 ='${caseId1}';
			var cardNo = '${cardNo}'; 
			var patState = '${patState}';
			var patDist = '${patDist}';
			var fromDt= '${fromDt}';
			var hospState= '${hospState}';
			var hospDist ='${hospDist}';
			var hospType  ='${hospType}';
			var hospName  ='${hospName}';
			document.getElementById("caseIdS").value=caseIdS;
			document.getElementById("caseId1").value=caseId1;
			document.getElementById("cardNo").value=cardNo;
			document.getElementById("patState").value=patState;
			document.getElementById("patDist").value=patDist;
			document.getElementById("fromDt").value=fromDt;
			document.getElementById("hospState").value=hospState;
			document.getElementById("hospDist").value=hospDist;
			document.getElementById("hospType").value=hospType;
			document.getElementById("hospName").value=hospName;
			
			} 
		var msg="${msg}";
		if(msg!="" && msg!=null)
			{
				document.getElementById("flagAttach").style.display="";
				if(document.getElementById("flagNature").value=='CD354' && document.getElementById("flagNature").disabled==false)
					{
						document.getElementById("amount").disabled=false;
					}
				if(document.getElementById("flagNature").value=='CD354' && document.getElementById("flagNature").disabled==true)
					{
					if(document.getElementById("amountRefund")!=null)
						document.getElementById("amountRef").style.display="";
					}
				alert(msg);
			}
		var userGroup ='${userGroup}';
		if(userGroup!=null && userGroup=='GP63')
			{
				if(document.getElementById("flagNature")!=null)
					document.getElementById("flagNature").disabled=true;
				
				if(document.getElementById("container")!=null)
					document.getElementById("container").style.display='none';
				
			}
			
	}
function fn_goBack(id)
	{
		var caseIdS ='${caseIdS}';
		var caseId1 ='${caseId1}';
		var cardNo = '${cardNo}'; 
		var patState = '${patState}';
		var patDist = '${patDist}';
		var fromDt= '${fromDt}';
		var hospState= '${hospState}';
		var hospDist ='${hospDist}';
		var hospType  ='${hospType}';
		var hospName  ='${hospName}';
		document.getElementById("caseIdS").value=caseIdS;
		document.getElementById("caseId1").value=caseId1;
		document.getElementById("cardNo").value=cardNo;
		document.getElementById("patState").value=patState;
		document.getElementById("patDist").value=patDist;
		document.getElementById("fromDt").value=fromDt;
		document.getElementById("hospState").value=hospState;
		document.getElementById("hospDist").value=hospDist;
		document.getElementById("hospType").value=hospType;
		document.getElementById("hospName").value=hospName;
		
		document.forms[0].action="./flaggingAction.do?actionFlag=viewFlaggedCases&flaggedCasesForApproval=${flaggedCasesForApproval}&newFlag=${newFlag}";
		document.forms[0].method="post";
		document.forms[0].submit();
}
		
	</script>
<body onload="javascript:alertMsg()">
<form name="flaggingForm" action="/flaggingAction.do" method="POST" enctype="multipart/form-data">
<logic:equal name="flaggingForm" property="authority" value="true">
<!-- <div id="panel-group">	
	<div id="header">
		<table id="header" width="100%">
			<tr class="HeaderRow">
				<td class="tbheader" width="100%"> -->
<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="tbheader">
					<span><b>Flagging Details</b></span>
	<!-- 			</td>
		</table> -->
	</div>
	<c:if test="${newFlag eq 'Y' || userGroup eq 'GP63'}">
	<div id="container" class="table-responsive">
		<table id="flaggingDetails" width="95%" align="center" style="padding-top:0px;margin:auto">
			<tr>
				<td class="labelheading1 tbcellCss"  width="17%">
					<b>Nature of Flag :<b><font color="red">*</font></b></b>
				</td>
				<td class="tbcellBorder"  width="30%">
					<html:select name="flaggingForm" property="flagNature" styleId="flagNature" title="Select the Nature of Flag" onchange="javascript:fn_flagSelect(id)">		
					<html:option value="-1">Select</html:option>
					<html:options property="ID" collection="flagList" labelProperty="flagName" ></html:options>
					</html:select>
				</td>
				<td class="labelheading1 tbcellCss"  width="20%">
					<b>Status of flag :<b><font color="red">*</font></b></b>
				</td>
				<td class="tbcellBorder"  width="33%">
					<html:radio name="flaggingForm" property="flagStatus" styleId="flagStatus1" value="CD361">Flagged</html:radio>
					<html:radio name="flaggingForm" property="flagStatus" styleId="flagStatus2" value="CD367">Verified</html:radio>
					<html:radio name="flaggingForm" property="flagStatus" disabled="true" styleId="flagStatus3" value="CD370">DE Flagged</html:radio>
						<div id="forward" style="display:none;">
							<html:radio name="flaggingForm" property="flagStatus" styleId="flagStatus4" value="CD365">Forward-Flagged Case</html:radio>
						</div>
				</td>
			</tr>		
			<tr>
				<td class="labelheading1 tbcellCss">
					<b>Remarks:<b><font color="red">*</font></b></b>
				</td>		
				<td class="tbcellBorder">
					<html:textarea name="flaggingForm" cols="40"  property="flagRemarks" styleId="flagRemarks" onchange="javascript:textCounter(this,'Remarks',4000)" onkeypress="javascript:textCounter(this,'Remarks',4000)" title="Please enter Remarks"></html:textarea>
				</td>																																												
				<td class="labelheading1 tbcellCss">
					<b>Amount Collected:</b>
				</td>
				<td class="tbcellBorder">
					<html:text  name="flaggingForm" property="amount" maxlength="10" styleId="amount" onchange="javascript:fn_checkValue(this.id)" title="Please enter the amount" disabled="true"></html:text>
				</td>
			</tr>

			<tr id="amountRef" style="display:none;">
				<td  width="17%"></td>
				<td  width="30%">
				<td class="labelheading1 tbcellCss" width="20%">
					<b>Amount Refunded:<b><font color="red">*</font></b></b>
				</td>
				<td class="tbcellBorder" width="33%">
					<html:text  name="flaggingForm" property="amountRefund" maxlength="10" styleId="amountRefund" onchange="javascript:fn_checkValue(this.id)" title="Please enter the amount Refunded" ></html:text>
				</td>
			</tr>		
		</table>									
				<table>           
			<tr class="empty">
				<td>
					&nbsp;
				</td>
			</tr>
			</table>
			<c:if test="${back eq 'true'}">
				<div id="back" style="float:right;margin-right:20px">
				<input Type="button" id="back" name="back" class="but"  value="Back"  onclick="javascript:fn_goBack(this.id)" title="Click to go Back"/>
				</div>
			</c:if>
					<div id="flagAttach" style="display:none;">
						<table id="attach" style="padding-top:0px;margin:0px 0px 0px 350px;">
							<tr>
								<td>
									<div id="attachDiv" >
										<input type="file" id="attachment0" name="doc[0]" onchange="javascript:checkExtension(this.id)"/>
										 <!-- <input type="file" id="attachment0" name="doc[0]" onchange="javascript:getSize(this.id)"/> -->
									</div>	
								</td>
								<td>
										<!-- <button type="button" class="btn btn-default" id="add" value="+" name="add" onclick="javascript:fn_addRemoveElement(this.id)" title="Click to add new attachment"></button>
										<button type="button" class="btn btn-default" id="remove" name="delete" value="-" onclick="javascript:fn_addRemoveElement(this.id)" title="Click to remove attachment" ></button> -->
										<input Type="button" id="add" name="add" class="but"  value="+" style="width:20px" onclick="javascript:fn_addRemoveElement(this.id)" title="Click to add new attachment"/>
           						    	<input Type="button" id="remove" name="delete" class="but" value="-" style="width:20px" onclick="javascript:fn_addRemoveElement(this.id)" title="Click to remove attachment" />
								</td>
							</tr>
						</table>
					</div>	
				<input type="hidden" id="attachValue" value="1">
								
			<table id="buttons" width="80%" align="center" style="margin:auto">
			<tr>
				<td colspan="4" align="center">
					<button type="button" class="but" id="subFlag" title="Click to submit Flag details" name="submitFlag" tabindex="0" onclick="javascript:fn_submitFlag()">
							Submit
					</button>
					<button type="reset" class="but" id="reset" title="click to reset details" name="resetDetails" tabindex="0" onclick="javascript:fn_resetDetails()">
							Reset
					</button>
				</td>
			</tr>
		</table>
		</div>		
	</c:if>	
</div>		
	<c:if test="${previousFlags eq 'Y'}">	
		<div id="raisedFlags" class="table-responsive">
			<table id="raisedFlagDetails" class=".table table-responsive"  width="100%" style="margin:30px 0px 0px 0px;">
				<tr>
					<td class="tbheader1" style="width:7%;">
						<b>Flag ID</b>
					</td>
					<td class="tbheader1" style="width:15%;">
						<b>Name</b>
					</td>
					<td class="tbheader1" style="width:10%;">
						<b>Role</b>
					</td>
					<td class="tbheader1" style="width:12%;">
						<b>Flagged Date and Time</b>
					</td>
					<td class="tbheader1" style="width:16%;">
						<b>Remarks</b>
					</td>
					<td class="tbheader1" style="width:13%;">
						<b>Nature of Flag</b>
					</td>
					<td class="tbheader1" style="width:7%;">
						<b>Status of Flag</b>
					</td>
					<td class="tbheader1" style="width:5%;">
						<b>Amount Collected/Refunded</b>
					</td>
					<td class="tbheader1" style="width:5%;">
						<b>Attachments</b>
					</td>
					<c:if test="${flaggedCasesForApproval eq 'Y'}">
						<td class="tbheader1" style="width:10%;">
						<b>	Action</b>
						</td>
					</c:if>
				</tr>
							<logic:iterate id="flag" name="previousFlagsList" type="com.ahct.flagging.vo.FlaggingVO" indexId="iid">
							<%-- <c:if test="${(flag.flagNature ne 'Money Collection')||((flag.flagNature eq 'Money Collection') && ((userGroup eq 'GP45') && (flag.dateDif > 15)) || ((userGroup ne 'GP45') && (flag.dateDif < 15) && (flag.currentStatus ne 'JEO Forwarded'))))}"> --%>
								<tr>
										<td class="tbcellBorder">		<bean:write name="flag" property="flagId"/>			 </td>
										<td class="tbcellBorder">		<bean:write name="flag" property="firstName"/>				 
								<bean:write name="flag" property="middleName"/>	<bean:write name="flag" property="lastName"/> </td>
										<td class="tbcellBorder">	<c:choose>
											<c:when test="${flaggingSchemeId eq 'CD201' && (flag.role eq 'MITHRA' || flag.role eq 'Aarogya Mithra')}" >
												VAIDYA MITHRA
											</c:when>
											<c:otherwise >
													<bean:write name="flag" property="role"/>	
											</c:otherwise>
										</c:choose>		 </td>
										<td class="tbcellBorder">		<bean:write name="flag" property="crtDt"/>			 </td>
										<td class="tbcellBorder">		<bean:write name="flag" property="flagRemarks"/>	 </td>
										<td class="tbcellBorder">		<bean:write name="flag" property="flagNature"/>		 </td>
										<td class="tbcellBorder">		<bean:write name="flag" property="flagStatus"/>		 </td>
										
										<td class="tbcellBorder" align="center">		
										<c:if test="${(flag.flagNature eq 'Money Collection') && ( flag.currentStatus eq 'DeFlagged') && (flag.actId eq 'CD370')}">
										<bean:write name="flag" property="amountRef"/>
										</c:if>
										<c:if test="${(flag.flagNature eq 'Money Collection') && ( flag.currentStatus eq 'DeFlagged') && (flag.actId ne 'CD370')}">
										<bean:write name="flag" property="amount"/>	
										</c:if>		
										<c:if test="${(flag.flagNature ne 'Money Collection') && ( flag.currentStatus eq 'DeFlagged')}">
										<bean:write name="flag" property="amount"/>	 
										</c:if> 
										<c:if test="${flag.currentStatus ne 'DeFlagged'}">
										<bean:write name="flag" property="amount"/>	 
										</c:if>     	 </td>
										<td class="tbcellBorder" align="center">		
		<a href="#" onclick="getAttachments('<bean:write name="flag" property="caseId"/>','<bean:write name="flag" property="flagId"/>')">View</a>		 </td>
										
								<c:if test="${flaggedCasesForApproval eq 'Y'}">
										<c:choose>
												 <c:when test="${(flag.actionOrder eq 1) && ( flag.currentStatus ne 'Verified') && ( flag.currentStatus ne 'DeFlagged') 
												 			         && (    (  flag.flagNature ne 'Money Collection') && ((userGroup eq 'GP41'||userGroup eq 'GP42'||userGroup eq 'GP43'||flag.crtUsr eq lStrEmpId)) 
												 			     		  || ( (flag.flagNature eq 'Money Collection') && (( ((userGroup eq 'GP41'||userGroup eq 'GP42'||userGroup eq 'GP43') )&& (flag.gmOpFlag ne 'Y') ) 
												 						     	                                       || ( (userGroup eq 'GP45') && (flag.currentStatus ne 'JEO Forwarded') && (flag.gmOpFlag eq 'Y'))
												 								                                       || ( (userGroup eq 'GP63') && (flag.currentStatus eq 'JEO Forwarded') && (flag.gmOpFlag eq 'Y') )
												 									     	      							)
												 							 )
												 						)
												                 }">	 
												 		<td class="tbcellBorder">	
																	Deflag:<br>Yes<html:radio name="flaggingForm" styleId="fdf" property="fdf" value="df" 
																				onclick="javascript:deFlagSelect('Y','${flag.flagNatureId}','${flag.amount}','${flag.flagId}')"/>
							   	        							No<html:radio name="flaggingForm" styleId="fdf" property="fdf" value="f" 
							   	        										onclick="javascript:deFlagSelect('N','${flag.flagNatureId}','${flag.amount}','${flag.flagId}')"/>
							   	        										
							   	        										<c:if test="${(flag.flagNature eq 'Money Collection') && (userGroup eq 'GP45') && (flag.gmOpFlag eq 'Y')}"><br>
							   	        												Forward<html:radio name="flaggingForm" styleId="fdf" property="fdf" value="fwd" 
																							onclick="javascript:deFlagSelect('fwd','${flag.flagNatureId}','${flag.amount}','${flag.flagId}')"/>
							   	        										</c:if> 
							   	     					 </td>
												 </c:when>	
												<c:otherwise>
												<td class="tbcellBorder" align="center">--NA--</td>
												</c:otherwise>
										</c:choose>
								</c:if>	
								</tr>
								
							</logic:iterate>
			</table>
		</div>
	</c:if>	
	<html:hidden property="deFlagId" styleId="deFlagId" name="flaggingForm" value="" />
	<html:hidden property="caseIdS" styleId="caseIdS" name="flaggingForm" />
	<html:hidden property="caseId1" styleId="caseId1" name="flaggingForm" />
	<html:hidden property="cardNo" styleId="cardNo" name="flaggingForm" />
	<html:hidden property="patState" styleId="patState" name="flaggingForm" />
	<html:hidden property="patDist" styleId="patDist" name="flaggingForm" />
	<html:hidden property="fromDt" styleId="fromDt" name="flaggingForm" />
	<html:hidden property="hospState" styleId="hospState" name="flaggingForm" />
	<html:hidden property="hospDist" styleId="hospDist" name="flaggingForm" />
	<html:hidden property="hospType" styleId="hospType" name="flaggingForm" />
	<html:hidden property="hospName" styleId="hospName" name="flaggingForm" />
	</div>
</logic:equal>	
<logic:equal name="flaggingForm" property="flagged" value="false">
						<br><br><br>
						
						<table  style="padding-top:0px;width:100%">
							<tr>
								<td align="center"><b><font size="2">No Records Found</font></b></td>
							</tr>
						</table>
</logic:equal>

<logic:equal name="flaggingForm" property="authority" value="false">
	
	 <div id="notAuthorised" align="center"  style="margin:200px 0px 0px 400px;text-align:center;vertical-align:middle;line-height:60%;"> 	
					<table>
					<tr><td align="center" class="labelheading1 tbcellCss" ><b> You are Not Authorised to raise a Flag </b></td></tr></table>
	</div>
</logic:equal>
<logic:equal name="flaggingForm" property="authority" value="noDcDmTlAuth">
	
	 <div id="notAuthorised" align="center"  style="margin:200px 0px 0px 160px;text-align:center;vertical-align:middle;"> 	
					<table>
					<tr><td align="center" class="labelheading1 tbcellCss" ><b>Flag cannot be raised as the Hospital to which Case has been registered is not Mapped to you</b></td></tr></table>
	</div>
</logic:equal>
<logic:equal name="flaggingForm" property="authority" value="exp">
	
	 <div id="notAuthorised" align="center"  style="margin:200px 0px 0px 400px;height:60%;text-align:center;vertical-align:middle;"> 	
					<b> Session Expired please reload or login again</b>
	</div>
</logic:equal>

</form>
</body>
</html>