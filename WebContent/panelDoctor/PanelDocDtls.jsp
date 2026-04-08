<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.io.*,java.text.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ include file="/common/include.jsp"%>
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link href="bootstrap/css/formValidation.min.css" rel="stylesheet" type="text/css" media="screen">
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/formValidation.min.js"></script>
<script src="bootstrap/validator/bootstrap.min.js"></script>
<script src="bootstrap/js/bootbox.min.js"></script>
<link href="bootstrap/css/formValidation.min.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/select2.min.css" rel="stylesheet">
<script src="js/select2.min.js"></script>
<title>Insert title here</title>
</head>
<style>
html, body {
    max-width: 100%;
    overflow-x: hidden;
}
.but
{
border-radius: 15px;
}
.bootbox-close-button
{
display:none;
}
</style>


<body onload="fn_updateCheck();">

<html:form styleId="panelDocForm" method="post" action="/panelDoc.do">


      <div class="row">
     
                         <div class="form-group col-xs-6 col-sm-3 col-md-2 col-lg-2  " >
							<span style="color:#195386" class="glyphicon glyphicon-cog"></span><label>&nbsp;Login Name :</label>
						</div>
						
						 <div class="form-group col-xs-6 col-sm-3 col-md-2 col-lg-2  " >
							
							<html:text name="panelDocForm" styleClass="form-control" styleId="loginName"  title="Login Name"  onkeyup="javascript:fn_validateUser()" property="loginName"></html:text>
						</div>
						
						
						 <div class="form-group col-xs-6 col-sm-3 col-md-2 col-lg-2  " >
							<span style="color:#195386" class="glyphicon glyphicon-user"> </span><label>&nbsp;Employee Name :</label>
						</div>
						
						 <div class="form-group dataFields col-xs-6 col-sm-2 col-md-2 col-lg-2  " >
							
							<html:text name="panelDocForm" styleClass="form-control" styleId="empName"  title="Please Enter Employee Name " property="empName" maxlength="200"></html:text>
							
						</div>
						
							
							</div>
							
	









      <div class="row">
                         <div class="form-group col-xs-6 col-sm-3 col-md-2 col-lg-2  " >
							<span style="color:#195386" class="glyphicon glyphicon-briefcase"></span><label>&nbsp;Bank Name :</label>
						</div>
						
						 <div class="form-group dataFields col-xs-6 col-sm-3 col-md-2 col-lg-2  " >
							<html:select  name="panelDocForm" property="bankName" styleId="bankName"  title="Please select Bank" onchange="getBranchList();" style="width:200px;" >
		                    <html:option  value=" ">----Select----</html:option>
		                    <html:options collection="bankLst" property="bankName" labelProperty="bankName" />
		                    </html:select>
						</div>
						
						
					 <div class="form-group col-xs-6 col-sm-3 col-md-2 col-lg-2  " >
							<span style="color:#195386" class="glyphicon glyphicon-map-marker"> </span><label>&nbsp;Bank Branch :</label>
						</div>
						
						 <div class="form-group dataFields  col-xs-6 col-sm-2 col-md-2 col-lg-2  " >
							<html:select  name="panelDocForm" property="bankId" styleId="bankId" style="width:125px; " title="Please select Bank Branch" onchange="javascript:getIfscCode();" >
		                    <html:option  value=" ">----Select----</html:option>
		                    <html:options collection="branchList" property="bankId" labelProperty="branchName"></html:options>
		                    </html:select>
						</div>
						
						
						
							
							</div>
							
							
							
		
      <div class="row">
     
                        <div class="form-group col-xs-6 col-sm-3 col-md-2 col-lg-2  " >
							<span style="color:#195386" class="glyphicon glyphicon-globe"></span><label>&nbsp;IFSC Code :</label>
						</div>
						
						 <div class="form-group  col-xs-6 col-sm-3 col-md-2 col-lg-2  " >
							
							<html:text name="panelDocForm" styleClass="form-control" styleId="ifscCode" disabled="true"  title="IFSC Code" property="ifscCode"></html:text>
						</div>
						
						
						 <div class="form-group col-xs-6 col-sm-3 col-md-2 col-lg-2  " >
							<span style="color:#195386" class="glyphicon glyphicon-pencil"> </span><label>&nbsp;Account Number :</label>
						</div>
						
                        <div class="form-group  dataFields col-xs-6 col-sm-2 col-md-2 col-lg-2  " >
							<html:text name="panelDocForm" styleClass="form-control" styleId="accountNum"  title="Bank Account Number" property="accountNum" maxlength="20"></html:text>
							
						</div>
						
							
							</div>
							
							
							
							
      <div class="row">
     
                        <div class="form-group col-xs-6 col-sm-3 col-md-2 col-lg-2  " >
							<span style="color:#195386" class="glyphicon glyphicon-credit-card"></span><label>&nbsp;PAN Card NO :</label>
						</div>
						
						<div class="form-group  dataFields  col-xs-6 col-sm-3 col-md-2 col-lg-2  " >	
							<html:text name="panelDocForm" styleClass="form-control" title="PAN Card Number" styleId="pan"  property="pan" maxlength="10"></html:text>
						</div>
						
						
						<div class="form-group col-xs-6 col-sm-3 col-md-2 col-lg-2  " >
							<span style="color:#195386" class="glyphicon glyphicon-pushpin"> </span><label>&nbsp;PAN Name :</label>
						</div>
						
						<div class="form-group  dataFields  col-xs-6 col-sm-2 col-md-2 col-lg-2  " >
							<html:text name="panelDocForm" styleClass="form-control" title="Name on PAN Card"  styleId="panName"  property="panName" maxlength="200"></html:text>
							
						</div>
						
							
							</div>					
							
							<div class="row" >
							<div  style="display:none" class="form-group    col-xs-6 col-sm-3 col-md-2 col-lg-2 " >
							<html:text name="panelDocForm" styleClass="form-control" styleId="loginNameTemp"  property="loginNameTemp"></html:text>
							
						    </div>
							</div>

	<div class="row">
	<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12"  align="center">
	<button class="but btn btn-info"   type="button" id="saveBtn" name="Search" value="Search" title="Click Here To Add/Update Account Details" onclick="javascript:fn_saveDetails()"  ><span class="glyphicon glyphicon-zoom-out"></span>&nbsp;Add/Update</button>
	<button class="but btn btn-danger"   type="button" id="resetBtn" name="Reset" value="Reset" title="Click Here To Reset" onclick="javascript:fn_reset()"  ><span class="glyphicon glyphicon-remove"></span>&nbsp;Reset</button>
	</div>
	</div>
	
	    <div class="row">
	 <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12"  align="left">
	
	<label id="note" style="font-weight:400"><font color="red">NOTE : All fields are Mandatory</font></label>
	</div></div>
<html:hidden name="panelDocForm" property="userId"></html:hidden>

</html:form>
</body>
<script>

$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
    
  /*   
    var resultMsg="${resultMsg}";


   if(resultMsg=="Panel Doctor account details savsssed successfully")
   {
	   alert("Panel Doctor account details saved successfully");
	   
   }
    	 window.opener.location.reload(); */

    
});
</script>
<script>
function fn_updateCheck()
{
	var updateExisting="${updateExisting}";
	var newAccount="${newAccount}";
	//alert(updateExisting);
	if(updateExisting!="Y") /*for new acoount*/
	{
		//if(newAccount=="Y")
        fn_reset();
    
	}
	else
	{
		if(newAccount=="N")
		document.getElementById("loginName").disabled=true;
	}
}

function fn_reset()
{
	var updateExisting="${updateExisting}";
	if(updateExisting!="Y")
	{
	document.forms[0].loginName.value="";
	}
	document.forms[0].loginNameTemp.value="";
	document.forms[0].empName.value="";
	document.forms[0].pan.value="";
	document.forms[0].panName.value="";
	$("#bankId").select2('val','');
	$("#bankName").select2('val','');
	
	document.forms[0].ifscCode.value="";
	document.forms[0].accountNum.value="";
}
function getBranchList()
{
	var xmlHttp;
	if(window.XMLHttpRequest)
	{
		xmlHttp=new XMLHttpRequest();
	}
	else if(window.ActiveXObject)
	{
		xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	else
	{
		alert("your browser does not support XMLHTTP!");
	}

	var bankName=document.getElementById("bankName").value;
	//alert(bankName);
	$("#bankId").select2('val','');
	document.forms[0].ifscCode.value="";
	if(bankName=="" || bankName==null)
	{
		return false;
	}
    var url="/<%=context%>/panelDoc.do?actionFlag=getBranchList&bankName="+bankName;
	xmlHttp.onreadystatechange=function()
	{
		if(xmlHttp.readyState==4)
		{
			
		 var resultArray=xmlHttp.responseText;
		 //alert(resultArray);
		 var  resultArray=resultArray.split("*");

		 if(resultArray[0]=="SessionExpired")
			 {
	    		alert("Session has been expired");
	    		parent.sessionExpireyClose();
	    		 
	    		}
		 else
		 {
					if(resultArray[0]!=null && resultArray[0]!="")
					{
					var resultArray1=resultArray[0].replace("[","");
					resultArray1 = resultArray1.replace("]",""); 
		            var branchList = resultArray1.split("@");
		          //  alert( branchList);
					}
					document.forms[0].bankId.options.length=0;
					document.forms[0].bankId.options[0]=new Option("-----Select-----","");

					for(var i=0;i<branchList.length;i++)
					{
						var arr=branchList[i].split("~");
                 		if(arr[1]!=null && arr[0]!=null)
                 		{
	                       var bankId=arr[0].replace(/^\s+|\s+$/g,"");
	                       bankId=bankId.replace(",","");
	                       bankId=bankId.trim();
	                      // alert(bankId);
                           var branchName=arr[1].replace(/^\s+|\s+$/g,"");
                           document.forms[0].bankId.options[i+1]=new Option(branchName,bankId);
                 		}
       					}
		 }
		
		}
	}
	xmlHttp.open("Post",url,true);
	xmlHttp.send(null);
	
}
function getIfscCode()
{
	var xmlhttp;
	if(window.XMLHttpRequest)
	{
		xmlhttp=new XMLHttpRequest();
	}
	else if(window.ActiveXObject)
	{
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	else
	{
		alert("your browser does not support XMLHTTP!");
	}
	var bankId=document.forms[0].bankId.value;
	if(bankId==null || bankId=="")
	{
    return false;
	}
    var url="/<%=context%>/panelDoc.do?actionFlag=getIfsc&bankId="+bankId;
    xmlhttp.onreadystatechange=function()
    {
if(xmlhttp.readyState==4)
{
	 var resultArray=xmlhttp.responseText;
	// alert(resultArray);
	 var  resultArray=resultArray.split("*");

	 if(resultArray[0]=="SessionExpired")
		 {
    		alert("Session has been expired");
    		parent.sessionExpireyClose();
    		 
    		}
	 else
	 {
				if(resultArray[0]!=null && resultArray[0]!="")
				{
				var resultArray1=resultArray[0].replace("[","");
				resultArray1 = resultArray1.replace("]",""); 
document.forms[0].ifscCode.value=resultArray1;
				}
	 }
}
    }
    xmlhttp.open("Post",url,true);
    xmlhttp.send(null);
}
function fn_saveDetails()
{
    $('#panelDocForm').formValidation().formValidation('validate');
	var x=$('#panelDocForm').data('formValidation').isValid();
	//alert(x);
	if(x)
	{

		    bootbox.confirm("Are you Sure to Save the Details?", function(result) {
		    if (result) 
			    {
		    	document.getElementById("saveBtn").disabled=true;
		    	//fn_loadImage();
		    	//fn_save();
		    	//$('#panelDocDetails').modal('hide');
		    	document.forms[0].action="/<%=context%>/panelDoc.do?actionFlag=savePanelDocDtls";
		    	document.forms[0].method="post";
		    	document.forms[0].submit();
		    	console.log("User confirmed dialog");
		    	parent.hideModal();
		    	//$('#panelDocDetails').modal('hide');
		        
		    } 
		    else {
		        console.log("User declined dialog");
		    }
		});	
	
	}
}
function fn_save()
{
	var xmlhttp;
	if(window.XMLHttpRequest)
	{
		xmlhttp=new window.XMLHttpRequest();
	}
	else if(window.ActiveXObject)
	{
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	else
	{
		alert("your browser does not support XMLHTTP!");
	}
 var url="/<%=context%>/panelDoc.do?actionFlag=savePanelDocDtls";
 xmlhttp.onreadystatechange=function()
 {
if(xmlhttp.readyState==4)
{
	 var resultArray=xmlhttp.responseText;
		// alert(resultArray);
		 var  resultArray=resultArray.split("*");

		 if(resultArray[0]=="SessionExpired")
			 {
	    		alert("Session has been expired");
	    		parent.sessionExpireyClose();
	    		 
	    		}
		 else
		 {
					if(resultArray[0]!=null && resultArray[0]!="")
					{
					var resultArray1=resultArray[0].replace("[","");
					resultArray1 = resultArray1.replace("]",""); 
	                alert(resultArray1);
	                this.close();
					}
		 }
	}
	    }
	    xmlhttp.open("Post",url,true);
	    xmlhttp.send(null);
}
var loginNameTemp;
function fn_validateUser()
{
	var xmlhttp;
	if(window.XMLHttpRequest)
	{
		xmlhttp=new window.XMLHttpRequest();
	}
	else if(window.ActiveXObject)
	{
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	else
	{
		alert("your browser does not support XMLHTTP!");
	}
	var loginName=document.forms[0].loginName.value;
	if(loginName==null || loginName=="")
	{
     return false;
	}
 var url="/<%=context%>/panelDoc.do?actionFlag=validateUser&loginName="+loginName;
 xmlhttp.onreadystatechange=function()
 {
if(xmlhttp.readyState==4)
{
	 var resultArray=xmlhttp.responseText;
		 //alert(resultArray);
		 var  resultArray=resultArray.split("*");

		 if(resultArray[0]=="SessionExpired")
			 {
	    		alert("Session has been expired");
	    		parent.sessionExpireyClose();
	    		 
	    		}
		 else
		 {
					if(resultArray[0]!=null && resultArray[0]!="")
					{
					var resultArray1=resultArray[0].replace("[","");
					resultArray1 = resultArray1.replace("]",""); 
	                //alert(resultArray1);
	                loginNameTemp=resultArray1;
	               document.forms[0].loginNameTemp.value=loginNameTemp;
                   var loginName=document.forms[0].loginName.value;
                   if(loginName==loginNameTemp){
	               fn_pnlDocDetails(loginName);
                   }
                   else
                   {
                	   document.forms[0].empName.value="";
                		document.forms[0].pan.value="";
                		document.forms[0].panName.value="";
                		$("#bankId").select2('val','');
                		$("#bankName").select2('val','');
                		document.forms[0].ifscCode.value="";
                		document.forms[0].accountNum.value="";
                   }
	              // alert(document.forms[0].loginNameTemp.value);
					}
		 }
	}
	    }
	    xmlhttp.open("Post",url,false);
	    xmlhttp.send(null);
}

function fn_pnlDocDetails(loginName)
{
	//alert(userId);
	
	document.forms[0].action="/<%=context%>/panelDoc.do?actionFlag=panelDocDetails&newAccount=Y&loginName="+loginName;
	document.forms[0].method="Post";
	document.forms[0].submit();
	//document.getElementById("panelDocDtls").src=url;
}
</script>
<script>
$("#bankName").select2();
$("#bankId").select2();
</script>

<script>
$(document).ready(function()
		{
$('#panelDocForm').find('[name="bankName"]')
.select2()
// Revalidate the color when it is changed
.change(function(e) {
    $('#panelDocForm').formValidation('revalidateField', 'bankName');
})
.end()







.formValidation(
{
framework:'bootstrap',
icon:
{
valid:'glyphicon glyphicon-ok',
	invalid:'glyphicon glyphicon-remove',
		validating:'glyphicon glyphicon-refresh'
},
excluded: ':disabled',
fields:
{
    empName:
    {
     validators :
     {
		notEmpty:
		{
			message:'Employee Name must not be empty'
		},
		regexp:
		{
		
		regexp:/^[a-zA-Z.]+$/,
		
		regexp:/^[A-Za-z.]+( [A-Za-z.]+)*$/,
		message:'only Alphabets and . are allowed,only one space is allowed between words'
			

		}
     }
    },

	panName:
	{
		validators:
		{
			notEmpty:
			{
			message:'Name on PAN Card must not be Empty'
			},
			regexp:
			{
			
			regexp:/^[a-zA-Z.]+$/,
			
			regexp:/^[A-Za-z.]+( [A-Za-z.]+)*$/,
			message:'only Alphabets and . are allowed,only one space is allowed between words'
				

			}
		}
	},
    accountNum:
	{
		validators:
		{
			notEmpty:
			{
			message:'Account Number must not be Empty'
			},
		    regexp:
		    {
		    regexp:/^[0-9]{6,15}$/,
		    message:'Enter a valid Account Numner'
		    }
		}
	},
	
	bankName:
	{
	validators:
	{
		/*callback: {
	        message: 'Please choose Bank',
	        callback: function(value, validator, $field) {
	            // Get the selected options
	            var options = validator.getFieldElements('bankName').val();
	            return (options != null && options!=" ");
	        }*/
	        notEmpty:
	        {
             message:'Please Select Bank'
	        }
	}
		
    },
	bankId:
	{
	validators:
	{
		/*callback: {
	        message: 'Please choose Bank',
	        callback: function(value, validator, $field) {
	            // Get the selected options
	            var options = validator.getFieldElements('bankName').val();
	            return (options != null && options!=" ");
	        }*/
	        notEmpty:
	        {
             message:'Please Select Bank Branch'
	        }
	}
		
    },
    pan:
    {
		validators:
		{
		notEmpty:
		{
		    	message:'PAN Number Should Not Be Empty'
		},
		regexp:
		{
		regexp:/^([A-Za-z.]){5}([0-9]){4}([A-Za-z.]){1}?$/,
		message:'Entered PAN Number is not a Valid Format'
		}
		    }
    },
    loginName:
    {
    	trigger: 'keyup',
		validators:
		{
		notEmpty:
		{
		message:'Login Name must not be Empty'
		},
		
		regexp:
		{
		regexp:/^[A-Za-z0-9_]+$/,
        message:'Only AlphaNumeric Characters and _ is allowed in Login Name'

		},
		identical:
		{		
		field:'loginNameTemp',
		message:'Please enter a valid Login Name'
		}
		}
    }
    /*bankName:{
        validators:
        {
		notEmpty:
		{
		message:'Bank Name is Mandatory.Please select'
		}
        }
    }*/
}});


		});

</script>
</html>