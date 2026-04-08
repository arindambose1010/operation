<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ include file="/common/include.jsp"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap-select.min.css">
<link href="bootstrap/css/formValidation.min.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">
<link href="bootstrap/css/font-awesome.css" rel="stylesheet">
<script  src="bootstrap/js/modernizr.min.js"></script>
<script  src="bootstrap/js/css3-mediaqueries.js"></script> 
<script src="bootstrap/js/respond.min.js"></script>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/formValidation.min.js"></script>
<script src="bootstrap/js/bootbox.js"></script>
<script src="bootstrap/validator/bootstrap.min.js"></script>
<script src="bootstrap/js/fileinput.min.js"></script>
<link href="bootstrap/css/fileinput.min.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css"    media="screen"> 
<link href="css/themes/<%=themeColour%>/accountstyle.css" rel="stylesheet" type="text/css" />

	<style>
body {
	font-size: 1.2em !important;
	font-family:Arial;
}

.
.pagination {
	display: none;
}

.bottom-margin {
	margin: 0px 0px 3px 0px !important;
}

.marginTop {
	margin-top: 3px
}

.but {
	border-radius: 16px;
}

.text-active,.active .text {
	display: none !important;
}

.mainDiv {
	width: 92%;
	float: right;
	padding-right: 3%;
}

.active .text-active {
	display: inline-block !important;
}

.centerone {
	padding-left: 5%;
}

.rightone {
	padding-right: 3%;
}
</style>
<script>
function validateMaxlength(input,e) { 
	
	 var fieldValue=input.value; 
	 
	 var code;    
	  if (!e) 
	 var e = window.event;   
	   if (e.keyCode) 
	 code = e.keyCode;    
	  else if (e.which)
	  code = e.which;  	
	  
	 if(fieldValue.trim().length>4000) 		
	 { 	
		
	input.value=fieldValue.trim().substr(0,3999); 			
	 var fr=partial(focusBox, document.getElementById("edcReqRemarks"));
	 jqueryAlertMsg('Maxlength Validation','Exceeded maximum limits of 3000 characters.',fr);
	 if(code==8 || code==46 || code==37 || code==38 || code==39 || code==40) 				//Key Code check for backspace,delete,Arrow Left, Arrow Up,Arrow Right,Arrow Down 				
	 { 				e.returnValue=true; 				
	 } 			
	
	 else 		
	 { 				
		 e.returnValue=false; 			 
	 } 		
	 } 	
	 }
function saveDtls(arg)
{
	var actionIdList="";
	
	if(document.getElementById("edcReqRemarks[0]").value==null||document.getElementById("edcReqRemarks[0]").value==""){
		
		alert("please enter remarks");
		return false;
	}


		 actionIdList =document.getElementById("hospId").value+"@"+document.getElementById("actionId").value +"~";
		
			
			document.forms[0].action = "CeoWorkListAction.do?actionVal=SAVEEDCCEODTLS&actionIdList="+actionIdList+"&btnType="+arg;
			document.forms[0].submit();	
		 


	
	
	
		
}
function fn_viewAttachment(test){
	
		
	var url='CeoWorkListAction.do?actionVal=SHOWATTACHMENTS&path='+test;
	 window.open(url,'','width=1150,height=600,resizable=yes,top=60,left=70,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');	
	
}
</script>
<body>

<html:form action="/CeoWorkListAction.do" method="POST" enctype="multipart/form-data">

<div class="container-fluid mainDiv">


<div  class=" tbheader col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group " style="text-align:center;">
<b >Empanelment Disciplinary Committee</b>
</div>
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group  tbcellCss" >

 <label class="labelheading1">Hospital ID:</label>

    <bean:write property="hospId" name="ceoWorkListForm"/>            
        </div>
        
        
      <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group  tbcellCss" >
      
   <label class="labelheading1">Hospital Name: </label>&nbsp;<bean:write property="hospName" name="ceoWorkListForm"/> 
      
      
      </div>  
        
<div class="col-lg-12 col-md-12  col-sm-12 col-xs-12 form-group  tbcellCss" >

 <label class="labelheading1">Hospital Location: </label>&nbsp;<bean:write property="hospLocation" name="ceoWorkListForm"/>
 
</div>


<div class="col-lg-12 col-md-12  col-sm-12 col-xs-12 form-group tbcellCss" >


<label class="labelheading1">Hospital Specialities: </label>&nbsp;
<bean:write property="hospSpltyString" name="ceoWorkListForm"/>

</div>


<div class="col-lg-12 col-md-12   col-sm-12 col-xs-12 form-group tbcellCss" >

 <label class="labelheading1">Attachments BY EDC Committee:&nbsp; </label>


	<logic:present name="ceoWorkListForm"
						property="attPres">
																	
			<bean:size id="sizeAttachTemp" name="ceoWorkListForm" property="attPres" />
			<logic:greaterThan name="sizeAttachTemp" value="0">
			<logic:iterate id="dataAttachTemp" name="ceoWorkListForm" property="attPres" indexId="attachInd">
			<c:if test="${dataAttachTemp.actOrder == 1}">
			<a style="font-size:15px;text-decoration:underline" href="javascript:fn_viewAttachment('<bean:write name="dataAttachTemp" property="attachPath"/>')"><bean:write name="dataAttachTemp" property="ACTUAL_NAME"></bean:write></a>
			</c:if>
		</logic:iterate>
						</logic:greaterThan>
						</logic:present>&nbsp;&nbsp;



</div>



<div style="${showSpecialities};" class="col-lg-12 col-md-12  col-sm-12 col-xs-12 form-group labelheading1 tbcellCss" >



<label><b>DE-Empaneled specialities:&nbsp;</b></label>
<logic:present name="ceoWorkListForm" property="spltyList">
							
							<logic:iterate id="dataSplty" name="ceoWorkListForm" property="spltyList" indexId="k">
							
							<bean:write name ="dataSplty" property ="LBL" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</logic:iterate>
							</logic:present>


</div>




<div>&nbsp;&nbsp;</div>

<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">

	<table  class="col-lg-12 col-md-12 col-sm-12 col-xs-12 " style="padding-left:1em; font-size:13px;" >
		<tr >
		<td class="tbcellBorder labelheading1" colspan="1" ><b >Name of the Article</b></td>
		<td  class="labelheading1 tbcellBorder" colspan="1"><b >Remarks</b></td>
		</tr>
		<logic:iterate id="dataDtlsArt" name="ceoWorkListForm" property="edcRequestDtls" indexId="cntDtlsArt">
		<tr >
		<td class="tbcellBorder" colspan="1">
		<bean:write name="dataDtlsArt" property="articleName"/> (<bean:write name="dataDtlsArt" property="articleValue"/>)
		</td>
		<td class="tbcellBorder" colspan="1">
		<bean:write name="dataDtlsArt" property="remarkValue"/> 
		
		</td>
		</tr>
		</logic:iterate>
		</table>
		</div>
		
		
		
<div>&nbsp;&nbsp;</div>
			<table class="tbheader" >
								<tr>
								
									<td align="center"><b>Remarks</b></td>
								</tr>
							</table>
		
				<logic:notPresent name="ceoWorkListForm" property="articleList">
				
		<div style="${showRemarksTextArea}" class="col-lg-12 col-md-12  col-sm-12 col-xs-12 form-group labelheading1 tbcellCss" >
		
		Remarks on <script >
			HoldDate=new Date();
			document.write(HoldDate.getDate() + "/" + (HoldDate.getMonth()+1) + "/" + HoldDate.getFullYear());
		
			  </script>
			  <html:textarea  name="ceoWorkListForm" styleId="edcReqRemarks[0]" property="edcReqRemarks[0]" rows="5" cols="150" onkeypress="validateMaxlength(this,event)" ></html:textarea> 
			  
		</div>		
			
			</logic:notPresent>


		<logic:present name="ceoWorkListForm" property="articleList">	
		
				<bean:size id="sizeArticle" name="ceoWorkListForm" property="articleList" />
						<logic:greaterThan name="sizeArticle" value="0">
						<logic:iterate id="dataArticle" name="ceoWorkListForm" property="articleList" indexId="artIndex">
						
						
						<div style="${showRemarksTextArea}"  class="col-lg-12 col-md-12  col-sm-12 col-xs-12 form-group labelheading1 tbcellCss">
						
						Remarks on  : <bean:write name="dataArticle" property="LBL"/>
						<html:textarea  name="ceoWorkListForm" styleId="edcReqRemarks[${artIndex}]" property="edcReqRemarks[${artIndex}]" rows="5" onkeypress="validateMaxlength(this,event)" ></html:textarea> 
							<script>
			artCount++;
			article="";
			article='<bean:write name="dataArticle" property="VALUE"/>';
			articlesList1=articlesList1+"~"+article;
			
			</script>
						
						</div>
				
				</logic:iterate>
				
				</logic:greaterThan>
				</logic:present>



		
				<logic:iterate id="data" name="ceoWorkListForm"
					property="remarksList" indexId="i">
					<logic:notEqual name="i" value="0">
						<table>
							<logic:iterate id="innerData" name="data" indexId="j">
								<logic:equal name="j" value="0">
									<thead>
										<tr>
											<th class="tbheader" colspan="6">Remarks by <bean:write
													name="innerData" property="updatedBy" />
											</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td class="tbcellBorder"  align="left">S.No</td>
											<td class="tbcellBorder" align="left">Date</td>
											<td class="tbcellBorder" align="left">Status</td>
											<td class="tbcellBorder" align="left">Articles</td>
											<td class="tbcellBorder">Remarks</td>
											<td class="tbcellBorder">Attachements</td>
										</tr>
								</logic:equal>
								<tr>
									<td class="tbcellBorder" align="left" >
										${j+1}</td>
									<td class="tbcellBorder" align="left" >
										<bean:write name="innerData" property="remarksDt" />
									</td>
									<td class="tbcellBorder" align="left">
										<bean:write name="innerData" property="action" />
									</td>
									<td class="tbcellBorder" align="left" >
										<bean:write name="innerData" property="articleName" />
									</td>
									<td class="tbcellBorder" align="left" >
										<html:textarea name="innerData" property='remarkValue'
											rows="3" style="width:100%" indexed="true" readonly="true" />
									</td>
									<td class="tbcellBorder" align="left" >
										<logic:present name="ceoWorkListForm" property="attPres">

											<bean:size id="sizeAttach" name="ceoWorkListForm"
												property="attPres" />
											<logic:greaterThan name="sizeAttach" value="0">
												<logic:iterate id="dataAttach" name="ceoWorkListForm"
													property="attPres" indexId="k">
													<logic:equal name="j" value="0">

														<c:if test="${dataAttach.actOrder == innerData.actOrder}">
															<a
																href="javascript:fn_viewAttachment('<bean:write name="dataAttach" property="attachPath"/>')"><bean:write
																	name="dataAttach" property="ACTUAL_NAME"></bean:write></a>

														</c:if>
													</logic:equal>
												</logic:iterate>
											</logic:greaterThan>
										</logic:present>

									</td>
								</tr>

							</logic:iterate>
							</tbody>
						</table>
					</logic:notEqual>
				</logic:iterate>

		




<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12 tbcellCss" align="center">
	<button class="but but" id="approveBtn" type="button" value="Approve"  onClick="saveDtls('Approve')" ><span class="glyphicon glyphicon-ok"></span>&nbsp;Approve</button>
	<button class="but but" id="rejectBtn" type="button" value="Reject"  onClick="saveDtls('Reject')" ><span class="glyphicon glyphicon-remove"></span>&nbsp;Reject</button>

</div>




<html:hidden property="hospId" styleId="hospId" name="ceoWorkListForm" />


<html:hidden property="actionId" styleId="actionId" name="ceoWorkListForm" />

</div>
</html:form>
</body>
</html>