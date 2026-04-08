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
<%@ include file="/common/includeBootstrap.jsp"%>
<html>  
<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
  
<title>Card Search</title>   
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script src="js/jquery-1.9.1.min.js"></script>


<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<style>
	body{font-family:Arial, Helvetica, sans-serif;font-size:14px;color:#333;}
	input[type="text"] {font-weight:normal;color:#000;font-size:1em;}
	.tbheader {background: #325F95;color:#fff;font-size:1em;font-family:"Helvetica Neue",Helvetica,Arial,sans-serif;
	text-align:center;border-radius: 0.4em;line-height: 1.8em;} 
	.page-header {background: #1E4B89;color:#fff;} 
	.heading{background: #447FCF;}
	#faqHead{background:#688652;}
	#trainingHead{background:#6595B9;}
	aside#sideLinks div {background: #447FCF;}
	.modal-header{background:#688652;color:#fff;}
	.custom-nav li a:hover{background:#447FCF;}

	.labelheading1{color:#447bc6;}
	.tbcellCss{margin:2px; padding:3px;background:#e9f2ff;border:1px solid #bcd8ff;}
	.tbcellBorder{margin:2px; padding:3px;background:#fff;border:1px solid #bcd8ff;}
	.but { background-color: #325F95;color:#fff;font-weight:bold;}
	.but:hover{color:#fff;}
	.tbcellCss3{padding:0.4em;background:#e9f2ff;border:0.1em solid #bcd8ff;margin-bottom:o.4em;}
</style>
<style type="text/css"> 
	.zoomin img { 
		height:50px; 
		width: 100px; 
			-webkit-transition: all 2s ease; 
			-moz-transition: all 2s ease; 
			-ms-transition: all 2s ease; 
			transition: all 2s ease; 
		} 
	.zoomin img:hover { 
		width: 300px; 
		height: 300px; 
	} 
</style>

<script type="text/javascript">

	function fn_search()
		{
			empRadio="";
			 for (var i=0; i<document.forms[0].elements.length; i++)
				{	
					var type = document.forms[0].elements[i].type;
					if (type=="radio")
					{	
						if(document.forms[0].elements[i].checked)
						{	
							empRadio=document.forms[0].elements[i].value;
						}
					}
				}
			 var cardNo=document.getElementById("cardNo").value;
			 
			 if(empRadio=="" && cardNo=="")
				 {
				 	var fr = partial(focusBox, document.getElementById('cardNo'));
					jqueryAlertMsg("Alert","Please Enter Card Number and Please select card type",fr);
					return false;
				 }
			 else if(cardNo=="")
				 {
				 var fr = partial(focusBox, document.getElementById('cardNo'));
					jqueryAlertMsg("Alert","Please Enter Card Number",fr);
					return false;
				 }
			 else if(empRadio=="")
				 {
				 	var fr = partial(focusBox, document.getElementById('empRadio'));
					jqueryAlertMsg("Alert","Please select card type",fr);
					return false;
				 }
			 else
				 {
					 document.forms[0].action='/Operations/cardSearchAction.do?actionFlag=searchCardDetails&cardNo='+cardNo+'&empRadio='+empRadio;
					 document.forms[0].submit();
				 }
		}
	function fn_reset()
	{
		document.forms[0].empRadio[0].checked=false;
		document.forms[0].empRadio[1].checked=false;
		document.getElementById("cardNo").value="";
	}
	function partial(func /*, 0..n args */) 
	{
		var args = new Array();
		for ( var i = 1; i < arguments.length; i++) 
		{
			args.push(arguments[i]);
		}
		return function() 
		{
			var allArguments = args.concat(Array.prototype.slice
					.call(arguments));
			return func.apply(this, allArguments);
		};
	}
	function focusBox(arg) 
	{
		aField = arg;
		setTimeout("aField.focus()", 0);
	}
	function fn_viewHealthCardPage(enrolId,seqNo,langId)
	{
		empRadio="";
		 for (var i=0; i<document.forms[0].elements.length; i++)
			{	
				var type = document.forms[0].elements[i].type;
				if (type=="radio")
				{	
					if(document.forms[0].elements[i].checked)
					{	
						empRadio=document.forms[0].elements[i].value;
					}
				}
			}
		 if(empRadio!=null && empRadio == "J")
			 {
				if(seqNo!=""){
					
					var usrId='${usrId}';	
				document.getElementById("cardPage").style.display="";
				
				document.getElementById("cardPage").src='/Operations/cardSearchAction.do?actionFlag=printCard&enrolId='+enrolId+'&seqNo='+seqNo+'&langId='+langId+'&empRadio='+empRadio;
				if(usrId!=null && usrId!='')
					document.getElementById("cardPage").src='/Operations/cardSearchAction.do?actionFlag=printCard&enrolId='+enrolId+'&seqNo='+seqNo+'&langId='+langId+'&usrId='+usrId+'&empRadio='+empRadio;
				
				}
			 }
		 if(empRadio!=null && empRadio == "E")
			 {
			 if(seqNo!=""){
					
					var usrId='${empUsrId}';	
				document.getElementById("cardPage").style.display="";
				document.getElementById("cardPage").src='/Operations/cardSearchAction.do?actionFlag=printCardEmployee&enrolId='+enrolId+'&seqNo='+seqNo+'&langId='+langId+'&empRadio='+empRadio;
				if(usrId!=null && usrId!='')
					document.getElementById("cardPage").src='/Operations/cardSearchAction.do?actionFlag=printCardEmployee&enrolId='+enrolId+'&seqNo='+seqNo+'&langId='+langId+'&usrId='+usrId+'&empRadio='+empRadio;
				}
			 }
		 
	}
	function fn_onLoadOfIframe(){
		var iframes=window.document.getElementsByTagName("iframe");
		var iframes=window.document.getElementsByTagName("iframe");
		iframes[0].frameBorder="0";   //  For other browsers.
		iframes[0].setAttribute("frameBorder", "0");   //  For other browsers (just a backup for the above).
		iframes[0].contentWindow.document.body.style.border="none"; 
		iframes[0].style.borderStyle="none";	
	} 
</script>


</head>
<body>
	<html:form action="/cardSearchAction.do" method="post" enctype="multipart/form-data" styleId="cardSearchForm">
		<div class="container-fluid center-block">
			<div class="tbheader row form-group">		
				<b>Card Search</b>
			</div>
			<div class="row" style="padding-left:25%">
				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4  labelheading1 tbcellCss3 " > 
					Card Type:&nbsp;
		    		<html:radio  property="empRadio" value="E" styleId="empRadio" title="Employee" >Employee/Pensioner &nbsp;&nbsp;</html:radio>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<html:radio  property="empRadio" value="J" styleId="empRadio" title="Journalist" >Journalist&nbsp;&nbsp;</html:radio>
		   		</div>
		   		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4  labelheading1 tbcellCss3 " > 
		   			Card Number
		   			<html:text property="cardNo"  maxlength="12" styleId="cardNo" title="Please enter card number" ></html:text>
		   		</div>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-6 col-xs-6" style="align:center;padding-left:40%"><br/>
				<button class="btn but" id="search" name="search" type="button"  title="Click here to search" onClick="fn_search()">Search</button>
             	<button class="btn but" id="reset" name="reset"  type="button"  title="click here to reset" onClick="fn_reset()">Reset</button>
            </div><br/>
            <% int newVar=0; %>
            
           <logic:present name="cardSearchForm" property="cardList"><br/>
						<bean:size id="size" name="cardSearchForm" property="cardList" />
						<logic:greaterThan name="size" value="0"> 
									<%
										int i = 1;
									%><br/>
									<div class="form-group col-xs-12 col-sm-12 col-md-5 col-lg-5 pull-left"><br/>
					            	<table style="width:80%;" class="table-bordered" >
					            		<tr>
											<th  class="tbheader" style="width:10%"><b>Name</b></th>
											<!-- <th  class="tbheader" style="width:10%"><b>Date Of Birth</b></th>  -->
									 		<!-- <th  class="tbheader" style="width:1%"><b>Gender</b></th> -->
											<th  class="tbheader"><b>Relation</b></th>
											<th  class="tbheader" style="width:10%"><b>Card Number</b></th>
											<th  class="tbheader" style="width:30%"><b>Photo</b></th> 
											<th  class="tbheader" style="width:15%"><b>View  Card</b></th>
					            		</tr>
					            		
					            		<logic:iterate id="cardList" name="cardSearchForm" property="cardList"  indexId="ctr" >
						            		<tr>
						            			
												<td class="tbcellBorder"><bean:write name="cardList" property="NAME"/></td>
												<%-- <td class="tbcellBorder"><bean:write name="cardList" property="DATEOFBIRTH"/></td>  --%>
												<%-- <td class="tbcellBorder"><bean:write name="cardList" property="GENDER"/></td>  --%>
												<td class="tbcellBorder"><bean:write name="cardList" property="RELATION"/></td>
												<td class="tbcellBorder"><bean:write name="cardList" property="CARDNUMBER"/></td>
												 <td class="tbcellBorder"><img src="" class="img img-responsive" style="width:50%;" id="image${ctr}"/>
												<script>
												
													var j='<%=request.getAttribute("photo"+newVar++)%>';

					  								document.getElementById("image"+"${ctr}").src='data:image/jpeg;base64,'+j;
				  								</script> </td> 
				  								<td class="tbcellBorder">
				  									<logic:equal name="cardList" property="RELATION" value="Self">
								                     <a   title="Click to view Health Card" onclick="fn_viewHealthCardPage('<bean:write name="cardList" property="AADHARID" />','<bean:write name="cardList" property="FLAG" />','en_US')" style="cursor:pointer"><b>View Card</b></a> 
								                    </logic:equal>
								                    <logic:notEqual name="cardList" property="RELATION" value="Self">
								                     	
								                     <a  title="Click to view Health Card" onclick="fn_viewHealthCardPage('<bean:write name="cardList" property="AADHARID" />','<bean:write name="cardList" property="FLAG" />','en_US')" style="cursor:pointer">View Card</a> 
							                    	</logic:notEqual>
				  								</td>
						            		</tr>
						            			
					            		</logic:iterate>
					            	</table>
					            	</div>
					            	<div class="form-group col-xs-12 col-sm-12 col-md-7 col-lg-7 pull-right"><br/>
							
										<div style="width: 100%;height: 100%" >
										
								      		<iframe  height="100%" id="cardPage" style="display:none;position:absolute;width: 100%;height:600px;border-left: none;" onload="javascript:fn_onLoadOfIframe();" ></iframe>  
								     	</div>
						   
						    		</div> 
					     </logic:greaterThan>
			</logic:present> 
            <br/>
            
            <!-- <div class="modal fade bs-example-modal-md " data-backdrop="false" id="cardViewDiv" id="dragit" tabindex="-1" role="dialog" aria-hidden=true>
				<div class="modal-dialog modal-md">
					<div class="modal-content">
						<div class="modal-header" style="text-align:right">
							<button type="button" title="Click here to close" class="btn btn-primary" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
							<h5 class="modal-title" align="center" >Download Health Card</h5>
						</div>
						<div class="modal-body ativa-scroll"></div>
					</div>
				</div>
			</div> -->
            
            <div class="container-fluid center-block"><br/>
            <c:if test="${size eq '0'}">
				<logic:empty name="cardSearchForm"  property="cardList">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 labelheading1 tbcellCss3" align="center">
						<label><b>No Records Found</b></label>
					</div>
				</logic:empty>
			</c:if>
			</div>
            
		</div>
	</html:form>

</body>
</html>