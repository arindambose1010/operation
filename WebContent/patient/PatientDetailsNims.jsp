<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.List,com.ahct.common.vo.LabelBean"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
     <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
  	 <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
		<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="/patient/PatientDetails.jsp"/>
</head>
<fmt:bundle basename="Registration">
<body onload="fn_loadProcessImage();fn_ipop();">
<div id="processImagetable"
		style="top: 50%; width: 100%; position: absolute; z-index: 60;">
	<table border="0" align="center" width="100%">
		<tr>
			<td>
			<div id="processImage" align="center"><img
				src="images/Progress.gif" width="100" height="100" border="0"
				tabindex="3"></img></div>
			</td>
		</tr>
	</table>
	</div>
<div id="middleFrame_content">
<form action="/patientDetails.do" method="post" enctype="multipart/form-data">
<br>
<table width="95%" style="margin:0 auto">
<tr><td colspan="4">
<table class="tbheader">
<tr><td align="left"><b>&nbsp;&nbsp;&nbsp;<fmt:message key="EHF.Title.PatientRegistration"/></b></td></tr>
</table>
<br>
<table width="100%">
<tr><td width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.HealthCardNo"/></b></td><td width="15%" class="tbcellBorder"><b> &nbsp;<bean:write name="patientForm" property="cardNo"/></b></td>
<td width="5%">&nbsp;</td>
<td width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PatientNo"/></b></td><td width="15%" class="tbcellBorder"><b> &nbsp;<bean:write name="patientForm" property="patientNo"/></b></td>
<td width="5%">&nbsp;</td>

<logic:equal  name="patientForm" property="telephonicReg" value="Yes"><b><td width="15%" class="labelheading1 tbcellCss">Telephonic ID</td>
<td width="15%" class="tbcellBorder"><a href="javascript:viewTelephonicInfo('<bean:write name="patientForm" property="telephonicId" />')"><bean:write name="patientForm" property="telephonicId" /></a></td></b>
</logic:equal>
<logic:notEqual  name="patientForm" property="telephonicReg" value="Yes">
<td width="30%">&nbsp;</td>
</logic:notEqual>
</tr>
</table>
<br>
<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.PatientDetails"/></b></td></tr>
</table>
<table width="100%">
<tr>
<td width="27%" valign="top">
<fieldset style="height:20em;">
 <legend class="legendStyle"><b><fmt:message key="EHF.FrameSet.PatientDetails"/></b></legend>
 <div style="height:18em;overflow:hidden" id='commonDtlsField'>
<table width="100%" height="200px" style="table-layout:fixed;word-wrap:break-word;">
<%-- <tr><td class="labelheading1 tbcellCss" width="50%"><b><fmt:message key="EHF.CardIssueDate"/></b></td><td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write name="patientForm" property="cardIssueDt"/></b></td></tr> --%> 
<tr><td class="labelheading1 tbcellCss" width="40%"><b><fmt:message key="EHF.Name"/></b></td><td class="tbcellBorder" width="60%"><b>&nbsp;<bean:write name="patientForm" property="fname" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Gender"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="gender"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DateOfBirth"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="dateOfBirth"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Age"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="years"/>Y
 		<bean:write name="patientForm" property="months"/>M
		<bean:write name="patientForm" property="days"/>D</b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Relationship"/></b></td><td class="tbcellBorder"><b>&nbsp;<c:if test="${patientForm.relation eq 'New Born Baby'}"  ><b></c:if><bean:write name="patientForm" property="relation"/></b></td></tr>
<%-- <tr><td class="labelheading1"><b><fmt:message key="EHF.Caste"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="caste"/></b></td></tr> --%>
 <tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Slab"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="slab"/></b></td></tr> 
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Designation"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="occupation"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ContactNo"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="contactno"/></b></td></tr>
</table>
</div>
</fieldset>
</td>
<td width="29%" valign="top">
<fieldset style="height:20em;" >
 <legend class="legendStyle" ><b><fmt:message key="EHF.CardAddress"/></b></legend>
  <div style="height:18em;overflow:hidden" id='cardAddressField'>
<table width="100%" height="200px" style="table-layout:fixed;word-wrap:break-word;">
<tr><td class="labelheading1 tbcellCss" width="50%"><b><fmt:message key="EHF.HouseNo"/></b></td><td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write name="patientForm" property="hno"/></b></td> </tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Street"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="street"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b>State</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="state"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.District"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="district" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Mdl/Mcl"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="mandal" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Village"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="village" /></b></td></tr> 
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Pin"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="pin" /></b></td></tr>
</table>
</div>
</fieldset>
</td>
<td width="29%" valign="top">
<fieldset style="height:20em;">
 <legend class="legendStyle"><b><fmt:message key="EHF.CommunicationAddress"/></b></legend>
 <div style="height:18em;overflow:hidden" id='commAddressField'>
<table width="100%" height="200px" style="table-layout:fixed;word-wrap:break-word;">
<tr><td class="labelheading1 tbcellCss" width="50%"><b><fmt:message key="EHF.HouseNo"/></b></td><td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write name="patientForm" property="comm_hno" /></b></td> </tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Street"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_street" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b>State</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_state" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.District"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_dist" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Mdl/Mcl"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_mandal"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Village"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_village" /></b></td></tr> 
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Pin"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_pin" /></b></td></tr>
</table>
</div>
</fieldset>
</td>
<td width="15%" valign="top">
<fieldset style="height:20em;" id='photoField'>
 <legend class="legendStyle"><b><fmt:message key="EHF.Photo"/></b></legend>
<table width="80%" height="80%" style="margin:auto auto">
<tr><td align="center">
 <logic:notEmpty name="patientForm" property="photoUrl">
<img id="patImage"  src="common/showDocument.jsp" width="150" height="150" alt="NO DATA" onmouseover="resizePatImage('patImage','200','200')" onmouseout="resizePatImage('patImage','150','150')"/>
</logic:notEmpty>
<logic:empty name="patientForm" property="photoUrl">
<img  src="images/photonot.gif" width="150" height="150" alt="NO DATA"  />
</logic:empty>
</td></tr></table>
</fieldset>
</td></tr>
</table>
<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.CaseDetails"/></b></td></tr>
</table>
<br>
<table width="100%">
<tr>
<td width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.RegisteredHospital"/> </b></td><td width="15%" class="tbcellBorder"><b><bean:write name="patientForm" property="hospitalName"/></b></td>
<td width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DateAndTime"/> </b> </td><td width="15%" class="tbcellBorder"><b><bean:write name="patientForm" property="dtTime"/></b></td>
<c:if test="${(dentalFlg eq 'Y')}">
<td width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.TreatmentPlanned"/></b><font color="red" class="onlyAp">*</font></td><td width="35%" class="tbcellBorder">
<b>
<html:radio name="patientForm" styleId="treatmentDntl" property="treatmentDntl" value="yes" onclick="javascript:redirecttodental(this.value);" />yes
<html:radio name="patientForm" styleId="treatmentDntl" property="treatmentDntl" value="no"  />no
</b></td>
</c:if>
</tr>
</table>
<logic:notEmpty name="groupList"> 
<logic:iterate name="groupList" id="labelbean">
<logic:equal name="labelbean" property="ID" value="GP2">
<%-- <logic:equal name="UserRole" value="CD9"> --%>



<table class="tbheader">
<!--  
<tr><td><b><fmt:message key="EHF.Title.MedicalDetails"/></b></td></tr>
-->
</table>
<br>
<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="tbheader">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" title="Click Here to View Medical Details" style="color:#fff; display:block;">
         

			<span id="medicalDtls" style="font-size: 1.25em;"><b>&nbsp;&nbsp;Medical Details&nbsp;&nbsp;<span class="glyphicon glyphicon-plus"></span></b></span>

        </a>
    </div>
    <div id="collapseOne" class="panel-collapse collapse"> 
      <div class="panel-body">
<table width="100%" class="medicalDetailsTable" border="0">

<tr>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ComplaintType"/></b> <font color="red" class="onlyAp">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ComplaintCode"/></b> <font color="red" class="onlyAp">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PatientComplaint"/></b> <font color="red" class="onlyAp">*</font></td>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PatientComplaintCode"/></b> <font color="red" class="onlyAp">*</font></td>
</tr>
<tr>
<td valign="top" class="tbcellBorder"><html:select name="patientForm" property="complaints" styleId="complaints" style="WIDTH:17em;;height:5em" title="Select Complaint Type" multiple="multiple" onchange="javascript:getComplaintType('notOnLoad');" onmouseover="addTooltip('complaints')" >
<!--<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>-->
<html:options property="ID" collection="mainComplaintList" labelProperty="VALUE"/>
</html:select></td>
<td  valign="top" class="tbcellBorder"><html:textarea name="patientForm"  property="complaintCode" styleId="complaintCode" style="WIDTH:18em;height:5em"  title="Complaint Code"  onkeydown="validateBackSpace(event)" readonly="true" /></td>	
<td valign="top" class="tbcellBorder"><html:select name="patientForm" property="patientComplaint" styleId="patientComplaint" style="WIDTH:17em;height:5em" title="Select Patient Complaint" multiple="multiple" onchange="getPatComplaintCode()" onmouseover="addTooltip('patientComplaint')">
    </html:select>
 </td>
 <td  valign="top" class="tbcellBorder"><html:textarea name="patientForm"  property="patComplaintCode" styleId="patComplaintCode" style="WIDTH:18em;height:5em" title="Patient Complaint Code" onkeydown="validateBackSpace(event)" readonly="true" /></td>	
</tr>

<tr>
<td class="labelheading1 tbcellCss">Other Complaints</td>
<td colspan="3" class="tbcellBorder" style="height: 70px;"><html:textarea name="patientForm" property="otherComplaint" styleId="otherComplaint" style="width:99%;heigtht:100%;" title="Please enter Other Complaints if Any" styleClass="otherFields" onchange="javascript:check_maxLength('otherComplaint','400');fn_enableHistory();" ></html:textarea></td>
</tr>




<tr>
<td colspan="2" valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.HistoryOfPresentIllness"/></b> <font color="red" class="onlyAp">*</font></td>
<td colspan="2" valign="top" rowspan="3"  class="tbcellBorder">
	<fieldset style="width:98%">
 		<legend class="legendStyle"><b><fmt:message key="EHF.ExaminationFindings"/></b> <font color="red" class="onlyAp">*</font></legend>
		<table width="100%">
		<logic:iterate id="examinFnds" name="examinFndgsList">


		 <tr id="<bean:write name="examinFnds" property='ID'/>ROW">
		
		<c:if test="${examinFnds.ID ne 'GE1' and  examinFnds.ID ne 'GE3' and examinFnds.ID ne 'GE7' and examinFnds.ID ne 'GE9' and examinFnds.ID ne 'GE1' and examinFnds.ID ne 'GE10' }">
		
			<c:if test="${(examinFnds.ID ne 'GE14') ||(examinFnds.ID eq 'GE14' && schemeId eq 'CD201') }">
		 <td width="60%">&nbsp;&nbsp;
		 
 		 <html:multibox name="patientForm"  property="examinationFnd" styleId="examinationFnd" onclick="getSubFieldType(this)">
      		 <bean:write name="examinFnds" property="ID"/>
       	</html:multibox>
       
       		<c:if test="${examinFnds.VALUE eq 'Clubbing Of Fingers/Toes'}">
       			Clubbing
       		</c:if>
       		<c:if test="${examinFnds.VALUE eq 'Oedema In Feet'}">
       			Oedema Feet
       		</c:if>
       		<c:if test="${examinFnds.VALUE ne 'Clubbing Of Fingers/Toes' and examinFnds.VALUE ne 'Oedema In Feet' }">
       				${examinFnds.VALUE}
       		</c:if>
       			</c:if>	
       			
       			
       					<c:if test="${schemeId eq 'CD202' &&  examinFnds.ID eq 'GE14'}">
		 <td width="60%">&nbsp;&nbsp;
		
 		 <html:multibox name="patientForm"  property="examinationFnd" styleId="examinationFnd" onclick="getSubFieldType(this)">
      		 <bean:write name="examinFnds" property="ID"/>
       	</html:multibox>
       
       		BP mm/Hg</td>
       			</c:if>	
       			
       			</c:if>	
       			
       		<td id="<bean:write name="examinFnds" property='ID'/>" width="39%"></td>
       
       		
       		<td width="1%">
       		<input type="hidden" name="<bean:write name='examinFnds' property='VALUE'/>" id="<bean:write name='examinFnds' property='ID'/>h" value="<bean:write name='examinFnds' property='LVL'/>"/></td>
       		</tr>
       		
       		
       		</logic:iterate>
       </table>    
    </fieldset>
 </td>
</tr>
<tr>
<td valign="top" colspan="2"  class="tbcellBorder">
<html:textarea name="patientForm" property="presentHistory" styleId="presentHistory"  style="WIDTH:38.5em;height:5em" disabled="true" title="Enter History Of Present Illness" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpaces(this,'History Of Present Illness')" />
</td>
</tr>
<tr><td colspan="2" class="tbcellBorder">
 <fieldset style="width:98%">
 		<legend class="legendStyle"><b><fmt:message key="EHF.PersonalHistory"/></b> <font color="red" class="onlyAp">*</font></legend>
 		<table width="100%" >
	 <logic:iterate id="phistory" name="personalHistoryList">
	 <c:if test="${phistory.ID ne 'PR4' }">
	 <c:if test="${phistory.ID eq 'PR3' }">
	 	 <tr><td width="33%">
	
 		 <html:multibox name="patientForm"  property="personalHistory" styleId="personalHistory" style="WIDTH:3em;" onclick="getSubListHistory(this,'NA')">
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
	
 		 <html:multibox name="patientForm"  property="personalHistory" styleId="personalHistory" style="WIDTH:3em;" onclick="getSubListHistory(this,'NA')">
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
	
 		 <html:multibox name="patientForm"  property="personalHistory" styleId="personalHistory" style="WIDTH:3em;" onclick="getSubListHistory(this,'NA')">
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
      <tr><td colspan="3">&nbsp;</td></tr>
     <!--  <tr>
      <td  id="habitsTd" colspan="3">
      </td></tr> -->
      </table>
    </fieldset>
</td></tr>
<tr>
<td colspan="2" class="tbcellBorder">
<fieldset style="width:98%">
 		<legend class="legendStyle"><b><fmt:message key="EHF.PastHistory"/></b> </legend>
 		<table width="100%">
	 <logic:iterate id="psthistory" name="pastHistoryList" indexId="cnt">
	 
	 

	 <c:if test="${psthistory.ID ne 'PH12' and  psthistory.ID ne 'PH8' and  psthistory.ID ne 'PH9' }">
	 <tr><td width="50%">&nbsp;&nbsp;
 		 <html:multibox name="patientForm"  property="pastHistory" styleId="pastHistory" onclick="getOtherText(this)" title="History Of Past Illness">
      		 <bean:write name="psthistory" property="ID"/>
       	</html:multibox>
       			<c:if test="${psthistory.ID ne 'PH11' && psthistory.ID ne 'PH7'}">
	       			<bean:write name="psthistory" property="VALUE"/>
	       	 	 </c:if>
	       	 	 <c:if test="${psthistory.ID eq 'PH11'}">
	       			Others
	       	 	 </c:if>
	       	 	  <c:if test="${psthistory.ID eq 'PH7'}">
	       			Nuerological Strokes
	       	 	 </c:if>
	     </td>
       		<td id="<bean:write name="psthistory" property='ID'/>" width="60%"></td>
		</tr>
		</c:if>
      </logic:iterate>
      
      </table>
    </fieldset>
</td>
<td colspan="2" class="tbcellBorder">
<fieldset style="width:98%">
 		<legend class="legendStyle"><b><fmt:message key="EHF.FamilyHistory"/></b> </legend>
 		<table width="100%">
	 <logic:iterate id="fhistory" name="familyHistoryList">
	 <c:if test="${fhistory.ID ne 'FH8' }">
	 <tr><td width="40%">&nbsp;&nbsp;
 		 <html:multibox name="patientForm"  property="familyHistory" styleId="familyHistory" onclick="getOtherText(this)">
      		 <bean:write name="fhistory" property="ID"/>
       	</html:multibox>
       		<bean:write name="fhistory" property="VALUE"/></td>
       		<td id="<bean:write name="fhistory" property='ID'/>" width="30%"></td>
       		</tr></c:if>
       </logic:iterate>
       </table>
    </fieldset>
</td>
</tr>
<tr><td colspan="4"><b><fmt:message key="EHF.SystemicExamFnds"/></b></td></tr>
     <tr>
      <td class="labelheading1 tbcellCss" width="25%">
        <html:checkbox value="O" title="Click Here to Add other Symptoms" styleId="otherSymptoms" onchange="fn_enableOtherSymptoms();" name="patientForm" property="otherSymptoms" ><b>&nbsp;Symptoms</b></html:checkbox>
        </td></tr>
    
 
 <tr id="otherSym" style="display:none;">
 <td class="labelheading1 tbcellCss">Symptom Name</td>
 <td colspan="2"><html:text style="width:100%" name="patientForm" property="otherSymptomName" styleId="otherSymptomName" styleClass="otherFields"  onchange="javascript:check_maxLength('otherSymptomName','200')"></html:text></td>
 </tr>  
 
      
<tr>
<td class="labelheading1 tbcellCss mainSymptoms"><b><fmt:message key="EHF.MainSymptomName"/></b> <font color="red" class="onlyAp">*</font></td>
<td class="labelheading1 tbcellCss mainSymptoms"><b><fmt:message key="EHF.MainSymptomCode"/></b> <font color="red" class="onlyAp">*</font></td>
<td class="labelheading1 tbcellCss mainSymptoms"><b><fmt:message key="EHF.SubSymptomName"/></b> <font color="red" class="onlyAp">*</font></td>
<td class="labelheading1 tbcellCss mainSymptoms"><b><fmt:message key="EHF.SubSymptomCode"/></b> <font color="red" class="onlyAp">*</font></td>
</tr>
<tr>
<td class="tbcellBorder mainSymptoms"><html:select name="patientForm" property="mainSymptomName" title="Select Main Symptom Name" styleId="mainSymptomName" style="WIDTH: 17em;" onchange="getsubSymptomLst()" onmouseover="addTooltip('mainSymptomName')">
           <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
           <html:options property="ID" collection="mainSymptonLst" labelProperty="VALUE"/>
    </html:select></td>
<td class="tbcellBorder mainSymptoms"><html:text name="patientForm"  property="mainSymptomCode" styleId="mainSymptomCode" title="Main Symptom Code" style="WIDTH: 17em;" maxlength="10" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder mainSymptoms"><html:select name="patientForm" property="subSymptomName" styleId="subSymptomName" title="Select Sub-Symptom Name" style="WIDTH: 17em;" onmouseover="addTooltip('subSymptomName')" onchange="getSymptomLst();">
           <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
    </html:select></td>
<td class="tbcellBorder mainSymptoms"><html:text name="patientForm"  property="subSymptomCode" styleId="subSymptomCode" title="subSymptom Code" style="WIDTH: 17em;" maxlength="10" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss mainSymptoms"><b><fmt:message key="EHF.SymptomName"/></b> <font color="red" class="onlyAp">*</font></td>
<td class="labelheading1 tbcellCss mainSymptoms"><b><fmt:message key="EHF.SymptomCode"/></b> <font color="red" class="onlyAp">*</font></td>
</tr>
<tr>
<td class="tbcellBorder mainSymptoms"><html:select name="patientForm" property="symptomName" styleId="symptomName" title="Select Symptom Name" style="WIDTH: 17em;" onmouseover="addTooltip('symptomName')" onchange="getSymptomCode();">
           <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
    </html:select></td>
<td class="tbcellBorder mainSymptoms"><html:text name="patientForm"  property="symptomCode" styleId="symptomCode" title="Symptom Code" style="WIDTH: 17em;" maxlength="10" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td colspan="2"><button  class="btn btn-success" type="button" name="addSymptom" id="addSymptom" value="Add Symptoms" onclick="addSymptoms()">Add Symptom&nbsp;<i class="fa fa-plus"></i></button></td>
</tr>
<tr><td colspan="6" class="tbcellBorder ">
  <table  width="100%"  id="symptomsTable" style="display:none" border="1">
      <tr>  
      	<td width="5%"><fmt:message key="EHF.SNO"/></td>        
        <td width="15%"><fmt:message key="EHF.SymptomCode"/></td>   
       	<td width="15%"><fmt:message key="EHF.SymptomName"/></td>
        <td width="15%"><fmt:message key="EHF.SubSymptomCode"/></td>
        <td width="15%"><fmt:message key="EHF.SubSymptomName"/></td>
        <td width="10%"><fmt:message key="EHF.MainSymptomCode"/></td>
        <td width="10%"><fmt:message key="EHF.MainSymptomName"/></td>
        <td width="5%">&nbsp;</td>
        </tr>
        <logic:present name="patientForm" property="symList">
        <bean:size  id="symSize" name="patientForm" property="symList"/>
        <logic:greaterThan value="0" name="symSize">
		<%int i = 1;%>
        <logic:iterate id="symlst" name="patientForm" property="symList" indexId="sno" >
        <tr>  
      	<td width="5%"><%=i++ %></td>        
        <td width="15%"><bean:write name="symlst" property="LEVELID" /></td>   
       	<td width="15%"><bean:write name="symlst" property="VALUE" /></td>
        <td width="15%"><bean:write name="symlst" property="DSGNID" /></td>
        <td width="15%"><bean:write name="symlst" property="SUBID" /></td>
        <td width="10%"><bean:write name="symlst" property="UNITID" /></td>
        <td width="10%"><bean:write name="symlst" property="ID" /></td>
        <td width="5%"><button class="btn btn-warning" type="button" value="Delete" style="padding: 3px 5px;" name=<bean:write name='symlst' property='LEVELID' /> id=<bean:write name='symlst' property='LEVELID' /> onclick="javascript:confirmRemoveRow(this,'symptom');">Delete&nbsp;<i class="fa fa-times"></i></button></td>
        </tr>
        </logic:iterate></logic:greaterThan></logic:present>
        </table>
</td></tr>
<tr><td colspan="4" >
<font color="red">Select atleast One General Investigation to enable IP.</font>
</td></tr>
<tr><td><br></td></tr>
<tr><td colspan="4"><b><fmt:message key="EHF.GenInvestigations"/></b> <font color="red" class="onlyAp">*&nbsp;&nbsp;(Upload files with size less than 200KB)</font></td></tr>
<tr><td colspan="4"><table width="100%">
<tr>
<td class="labelheading1 tbcellCss" width="25%" id="InvBlockName"><b>Investigation Block Name</b><font color="red" class="onlyAp">*</font></td>
<td class="labelheading1 tbcellCss" width="25%" id="InvName"><b>Investigation Name</b><font color="red" class="onlyAp">*</font></td>
      
      
    <logic:equal value="CD202" name="patientForm" property="scheme">
     <logic:equal value="G" name="patientForm" property="hosptype">
      <td class="labelheading1 tbcellCss" width="25%">
        <html:checkbox value="O"  title="Click Here to Add other Investigations" styleId="invOthers" onchange="fn_enableOthers();" name="patientForm" property="investOthers" ><b>&nbsp;Other Investigations</b></html:checkbox>
        </td>
        </logic:equal>
        </logic:equal>
      
        
<td width="25%">&nbsp;</td></tr>
<tr>
<td class="tbcellBorder" id="InvBlckLst">
<html:select name="patientForm" property="genBlockInvestName" styleId="genBlockInvestName" title="Select Block Investigation Name" style="WIDTH: 17em;" onmouseover="addTooltip('genBlockInvestName')" onchange="getGenInvestigation();">
          <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
          <html:options property="ID" collection="investigationsList" labelProperty="VALUE"/>
         
  
          
    </html:select></td>
    <td class="tbcellBorder" id="invLst"><html:select name="patientForm" property="genInvestigations" styleId="genInvestigations" style="WIDTH: 17em;"  onchange="javascript:getInvestPrice();" onmouseover="addTooltip('genInvestigations')">
       <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
    </html:select>
    </td>
    </tr>
   
    <tr>
   
     <td class="labelheading1 tbcellCss" width="25%" Id="otherInvNameHead" style="display:none"><b>Other Investigation Name</b></td>
     </tr>
     <tr>
     <td class="tbCellBorder"><html:textarea name="patientForm" property="otherInvName" styleId="otherInvName" style="width:97%;font-weight:bold;display:none;" styleClass="otherFields" onchange="javascript:check_maxLength('otherInvName','400');fn_enableIp();" ></html:textarea></td>
  </tr>
  <tr><td><br></td></tr>
 <tr>
<td colspan="4" style="align:center"><button class="btn btn-success" type="button" name="add" value="Add Tests" onclick="confirmPatientTypeReset()">Add Tests&nbsp;<i class="fa fa-plus"></i></button></td>
</tr></table></td></tr>
<tr><td colspan="4" class="tbcellBorder">
  <table  width="100%"  id="genTestTableID" style="display:none" border="1">
   <tr><td colspan="5" class="labelheading1 tbcellCss">Previously Added Investigation</td></tr>
     	<tr>
     	 	 <td width="10%" ><fmt:message key="EHF.SNO"/></td> 
     	 	<td width="30%" ><b>Investigation Block Name</b></td>       
        	<td width="25%" ><b><fmt:message key="EHF.TestName"/></b></td>   
        	<td width="25%"><b><fmt:message key="EHF.Attachment"/></b></td>
        	<td width="20%">&nbsp;</td></tr>
        <logic:present name="patientForm" property="genInvList">
        <bean:size  id="genInvSize" name="patientForm" property="genInvList"/>
        <logic:greaterThan value="0" name="genInvSize">
		<%int j = 1;%>
		<script>var rowNo=2;</script>
        <logic:iterate id="gnInvlst" name="patientForm" property="genInvList" indexId="sno" >
        <tr>  
      	 <td width="10%"><%=j++ %></td>  
      	<td width="30%" ><bean:write name="gnInvlst" property="ACTION" /></td>       
        <td width="25%"><bean:write name="gnInvlst" property="VALUE" /></td> 
		<logic:empty name="gnInvlst" property="LVL">
		<script>
			var updateGenInvest="<bean:write name="gnInvlst" property="ACTION" />~<bean:write name="gnInvlst" property="VALUE" />~<bean:write name="gnInvlst" property="ID" />";
			updateGenInvestLst[updateGenInvestCount]=updateGenInvest;
			updateGenInvestCount++;
			var investTableId=document.getElementById('genTestTableID');   
			var newRow=investTableId.rows[rowNo];
			newcell=newRow.insertCell(2);
			newcell.innerHTML='<td width="25%"><input type="file"  id=<bean:write name="gnInvlst" property="ID" /> name="updateGenAttach['+updGenInvestAttachId+']" onchange="checkBrowser(this)"/></td>';
			updGenInvestAttachId++;
		</script>
		</logic:empty>
		<logic:notEmpty  name="gnInvlst" property="LVL">
       	<td width="25%"><a href="#" onclick="javascript:fn_openAtachment('<bean:write name="gnInvlst" property="LVL" />','<bean:write name="gnInvlst" property="LVL" />');">View</a></td>
		</logic:notEmpty>
		<script>rowNo++;</script>
       	<td width="20%"><button class="btn btn-warning" type="button" value="Delete" name=<bean:write name="gnInvlst" property="ID" /> id=<bean:write name="gnInvlst" property="ID" /> onclick="javascript:confirmRemoveGenInvest(this,'geninvestigations','<bean:write name="gnInvlst" property="ID" />');">Delete&nbsp;<i class="fa fa-times"></i></button></td>
        </tr>
        </logic:iterate></logic:greaterThan></logic:present>
   </table>
</td></tr> 

<tr><td colspan="4" class="tbcellBorder">
  <table  width="100%"  id="genTestTable" style="display:none" border="1">
     	<tr>
     	  <!--  <td width="10%" ><fmt:message key="EHF.SNO"/></td>   -->
     	 	<td width="30%" ><b>Investigation Block Name</b></td>       
        	<td width="25%" ><b><fmt:message key="EHF.TestName"/></b></td>   
        	<td width="25%"><b><fmt:message key="EHF.Attachment"/></b></td>
        	<td width="20%">&nbsp;</td></tr> 
  </table>
</td></tr> 
</table></div></div></div></div>
<tr><td colspan="4" class="labelheading1 tbcellCss">
<table width="100%">
<tr><td  width="15%"><b><fmt:message key="EHF.PatientType"/></b><font color="red">*</font></td>

<c:if test="${nimsHosp eq 'Y' }">
<td id="patientType2" width="15%"><html:radio name="patientForm" property="patientType" value="IPM" title="Out Patient" styleId="patientType" onclick="enableIPOP()"  /><b>NON LISTED DISEASES</b></td>
<td id="patientType1" width="10%" ><html:radio name="patientForm" property="patientType" value="IP" title="In Patient" styleId="patientType"  onclick="enableIPOP()"/><b><fmt:message key="EHF.IP"/></b></td>
</c:if>
<c:if test="${nimsHosp ne 'Y' }">
<td id="patientType2" width="15%"><html:radio name="patientForm" property="patientType" value="OP" title="Out Patient" styleId="patientType" onclick="enableIPOP()"  /><b>General OP</b></td>
<td id="patientType1" width="10%" ><html:radio name="patientForm" property="patientType" value="IP" title="In Patient" styleId="patientType"  onclick="enableIPOP()"/><b><fmt:message key="EHF.IP"/></b></td>
</c:if>
 <!-- <td id="patientType3" width="15%" ><html:radio name="patientForm" property="patientType" value="ChronicOP" title="Chronic OP" styleId="patientType"  onclick="saveChronicOP()"/><b>Chronic OP</b></td>   -->


<!--<td id="patientType3" width="10%" class="labelheading1 tbcellCss"><html:radio name="patientForm" property="patientType" value="ChronicOP" title="Chronic Out Patient" styleId="patientType" onclick="enableIPOP()"/><b><fmt:message key="EHF.ChronicOP"/></b></td>-->
<td  width="25%" class="labelheading1 tbcellCss"><button class="btn btn-success"  type="button" id="saveDTRS" onclick="javascript:fn_saveDetailsWithoutMandate('SaveDTRS');">Save and Generate DTRS&nbsp;<i class="fa fa-print"></i></button></td>
<td id="dtrsTd" width="25%"  class="labelheading1 tbcellCss" style="display:none">
<a href="javascript:generateDTRSPrint('<bean:write name="patientForm" property="caseId" />','<bean:write name="patientForm" property="hospitalId" />')"><b>DTRS Print Form</b></a>
</td>
</tr>
</table>
</td></tr>

<tr>
<td id="empPastHistory" style="display:none;">


<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="tbheader">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" title="Click Here to View Past History" style="color:#fff; display:block;">
         

			<span id="empPastHstry" style="font-size: 1.25em;"><b><i  style="color:#ff0000" class="fa fa-user-plus"></i>&nbsp;&nbsp;Patient Past History&nbsp;&nbsp;<i style="color:#ff0000" class="fa fa-user-plus"></i></b></span>

        </a>
    </div>
    <div id="collapseTwo" class="panel-collapse collapse"> 
      <div class="panel-body">
      
      <table width="95%" style="margin:auto;">
     <logic:notEmpty name="patientForm" property="lstCaseSearch" >
     
     <tr>
											<th class="tbheader1" width="12%">Case ID</th>
											<th class="tbheader1" width="14%">PatientName</th>
											<th class="tbheader1" width="25%">Hospital Name</th>
											<th class="tbheader1" width="10%">Case Type</th>
											<th class="tbheader1" width="15%">Case Status</th>
											<th class="tbheader1" width="10%">Registered Date</th>		
										<!--	<th class="tbheader1" width="3%">Preauth Amount</th>
											<th class="tbheader1" width="2%">Claim Amount</th>	   -->				
											
										</tr>										
										<logic:iterate  name="patientForm" property="lstCaseSearch" id="data" >
												<tr>
													<td align="center" width="12%" class="tbcellBorder" style="word-wrap: break-word;">
														<b><font style="color:#862010"><a  id="pastHstryView"  data-toggle="modal" data-target="#pastHistoryModal" href="#" onclick="javascript:getDiagnosisDetails('<bean:write name="data" property="CASESTATUSID"/>','<bean:write  name="data" property="CATID"/>','<bean:write  name="data" property="HOSPNAME"/>','<bean:write  name="data" property="SUBMITTEDDATE"/>','<bean:write  name="data" property="PATIENTID"/>','<bean:write  name="data" property="CASENO"/>','<bean:write  name="data" property="PATIPOP"/>')"><bean:write  name="data" property="CASENO"/></a></font></b>
													</td>
													<td align="center" width="14%" class="tbcellBorder" style="word-wrap: break-word;">
														<bean:write  name="data" property="PATNAME"/>
													</td>
													<td align="center" width="18%" class="tbcellBorder" style="word-wrap: break-word;">
														<bean:write  name="data" property="HOSPNAME"/>
													</td>
													<td align="center" width="15%" class="tbcellBorder" style="word-wrap: break-word;">
														<b><font style="color:#B32900"><bean:write  name="data" property="PATIPOP"/></font></</b>
													</td>
													<td align="center" width="15%" class="tbcellBorder" style="word-wrap: break-word;">
														<bean:write  name="data" property="CASESTATUS"/>
													</td>
													<td align="center" width="20%" class="tbcellBorder" style="word-wrap: break-word;">
														<bean:write  name="data" property="SUBMITTEDDATE"/>
													</td>
												<!--	<td align="center" width="3%" class="tbcellBorder" style="word-wrap: break-word;">
														<bean:write  name="data" property="PREAUTHAPPAMT"/>
													</td>
													<td align="center" width="2%" class="tbcellBorder" style="word-wrap: break-word;">
														<bean:write  name="data" property="CLAIMAMT"/>
													</td>  -->																																						
												</tr>
										
										</logic:iterate>
										</logic:notEmpty>
									
      <logic:empty name="patientForm" property="lstCaseSearch" >
      <tr>
      <td align="center" style="color:#434645">
      <i class="fa fa-user-times"></i>&nbsp;<b>No Past History Found</b>
      </td>
      </tr>
      
      </logic:empty>
      </table>
      </div>
      </div>
    </div>
  </div>

</td></tr>

<tr><td  id="diagnosisData" style="display:none;" colspan="4">
<table width="100%">  
    <tr>
     <!--   <td class="labelheading1 tbcellCss" width="25%">
        <html:checkbox  title="Click Here to Add Other Diagnosis" styleId="diagOthers" onchange="fn_enableOtherDiag();" name="patientForm" property="diagOthers" value="Y"><b>&nbsp;Other Diagnosis</b></html:checkbox>
        </td> -->
        <td class="labelheading1 tbcellCss" id="diagOthersHead" style="width:20%" ><b>Diagnosis Name</b></td>
        <td class="tbCellBorder" colspan="3" Id="diagOthersName" ><html:textarea  name="patientForm" property="diagOthersName"  styleId="diagOthName" style="width:100%;font-weight:bold;" styleClass="otherFields" onchange="javascript:check_maxLength('diagOthName','4000');"></html:textarea></td>
        </tr> 

<!--  
        <tr class="existDiag">
<td class="tbcellCss" style="color:#A81C06">
<input type="radio"  name="diagCondition" value="c" Id="contains" onchange="javascript:getDiagListAuto();" checked="checked">&nbsp;&nbsp;<b>Contains</b>&nbsp;&nbsp;&nbsp;&nbsp;
<input type="radio"  name="diagCondition" value="s" Id="startsWith" onchange="javascript:getDiagListAuto();">&nbsp;&nbsp;<b>Starts With</b>

</td>
<td  class="tbcellCss">
<html:text name="patientForm" property="diagnosisName" styleId="diagnosisName" onkeyup="javascript:getDiagListAuto();" style="width:100%"></html:text>
</td>
<td  class="labelheading1 tbcellCss existDrugs" id="diagAuto1" colspan="2" style="color:#A81C06">
<span ><i  class="fa fa-hand-o-left fa-3 autoData"></i></span><b><font>&nbsp;&nbsp;Please Enter KeyWords to Filter Diagnosis List&nbsp;&nbsp;</font></b><i class="fa fa-list"></i>
</td>
        </tr>
        <tr class="existDiag">
                <td  class="labelheading1 tbcellCss existDrugs" width="10%"><b>	Disease Anatomical Name</b></td>
<td valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="drugName" styleId="disNameAuto" style="width:17em;" title="Select Diagnosis Name" onkeyup="javascript:getDiagListAuto();" onchange="javascript:getDiagDetailsAuto();" onmouseover="addTooltip('drugName')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		 
</html:select></td>

<td  class="labelheading1 tbcellCss existDrugs" id="diagAuto2" colspan="2" style="color:#A81C06;display:none">
<span ><i  class="fa fa-hand-o-left fa-3 autoData"></i></span><b><font>&nbsp;&nbsp;Select Diagnosis Name to Auto Populate Data&nbsp;&nbsp;</font></b><i class="fa fa-medkit"></i> 
</td>

        </tr>

-->

<!--  

<tr class="existDiag">
<td width="25%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DiagType"/></b> <font color="red" class="onlyAp">*</font></td>
<td width="25%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DiagCode"/></b> <font color="red" class="onlyAp">*</font></td>
<td width="25%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.MainCatName"/></b> <font color="red" class="onlyAp">*</font></td>
<td width="25%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.MainCatCode"/></b><font color="red" class="onlyAp">*</font></td>
</tr>
<tr class="existDiag">
<td class="tbcellBorder"><html:select name="patientForm" property="diagType" styleId="diagType" style="WIDTH:17em" title="Select Diagnosis Type" onchange="getDiagnMainCatList('noOnload')" onmouseover="addTooltip('diagType')">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
            <html:options property="ID" collection="diagnTypeList" labelProperty="VALUE"/>
    </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="diagCode" styleId="diagCode" title="Diagnosis Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder"><html:select name="patientForm" property="mainCatName" styleId="mainCatName" style="WIDTH:17em" title="Select Main Category Name" onchange="getDiagnCategoryList()" onmouseover="addTooltip('mainCatName')">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
      </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="mainCatCode" styleId="mainCatCode" title="Main Category Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>
<tr class="existDiag">
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.CatName"/></b> <font color="red" class="onlyAp">*</font></td>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.CatCode"/></b> <font color="red" class="onlyAp">*</font></td>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.SubCatName"/></b> <font color="red" class="onlyAp">*</font></td>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.SubCatCode"/></b> <font color="red" class="onlyAp">*</font></td>
</tr>
<tr class="existDiag">
<td class="tbcellBorder"><html:select name="patientForm" property="catName" styleId="catName" style="WIDTH:17em" title="Select Category Name" onchange="getDiagnSubCategoryList()" onmouseover="addTooltip('catName')">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
	 </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="catCode" styleId="catCode" title="Category Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder"><html:select name="patientForm" property="subCatName" styleId="subCatName" style="WIDTH:17em" title="Select Sub Category Name" onchange="getDiagnDiseaseList()" onmouseover="addTooltip('subCatName')">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
            </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="subCatCode" styleId="subCatCode" title="Sub Category Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>
<tr class="existDiag">
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DiseaseName"/></b> <font color="red" class="onlyAp">*</font></td>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DiseaseCode"/></b> <font color="red" class="onlyAp">*</font></td>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DisAnatomicalName"/></b> <font color="red" class="onlyAp">*</font></td>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DisAnatomicalCode"/></b> <font color="red" class="onlyAp">*</font></td>
</tr>
<tr class="existDiag">
<td class="tbcellBorder"><html:select name="patientForm" property="diseaseName" styleId="diseaseName" style="WIDTH:17em" title="Select Disease Name" onchange="getDiagnDisAnatomicalList()" onmouseover="addTooltip('diseaseName')">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
 	 </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="diseaseCode" styleId="diseaseCode" title="Disease Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder"><html:select name="patientForm" property="disAnatomicalName" styleId="disAnatomicalName" style="WIDTH:17em" title="Select Disease Anatomical Name" onmouseover="addTooltip('disAnatomicalName')" onchange="getDisAnatomicalCode()">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
    </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="disAnatomicalCode" styleId="disAnatomicalCode" title="Disease Anatomical Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b>Specialty</b> <font color="red">*</font></td>

<td class="tbcellBorder">
<html:select name="patientForm" property="medicalSpclty" styleId="medicalSpclty" style="width:17em;" title="Select Speciality" onchange="getMedicalCategoryList(this.id)"  onmouseover="addTooltip('medicalSpclty')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:options property="ID" collection="medicalSpltyList" labelProperty="VALUE"/>
</html:select></td>

<td  class="labelheading1 tbcellCss"><b>Category</b> <font color="red">*</font></td>

<td  class="tbcellBorder">
<html:select name="patientForm" property="medicalCat" styleId="medicalCat" style="width:17em;" title="Select Category"  onmouseover="addTooltip('medicalCat')">
		 <c:if test="${fn:length(medicalCatList) gt 0}">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:options property="ID" collection="medicalCatList" labelProperty="VALUE"/>
		</c:if>
</html:select></td>
</tr>
</table>
</td></tr> -->
<tr><td style="display:none" id="IPHead2" colspan="4">
<table width="100%">
<tr><td><b><font><fmt:message key="EHF.Therapy"/>:</font></b></td></tr>
<tr>
<td width="25%"  class="labelheading1 tbcellCss"><b> <fmt:message key="EHF.CatName"/> </b> <font color="red" class="onlyAp">*</font></td>
<td width="25%"  class="labelheading1 tbcellCss"><b> <fmt:message key="EHF.CatCode"/> </b> <font color="red" class="onlyAp">*</font></td>
<td width="25%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ICDCatName"/></b> <font color="red" class="onlyAp">*</font></td>
<td width="25%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ICDCatCode"/></b> <font color="red" class="onlyAp">*</font></td>
</tr>
<tr>
<td class="tbcellBorder"><html:select name="patientForm" property="asriCatName" styleId="asriCatName" style="WIDTH: 17em" title="Select Category " onchange="getIcdCategoryCodes()" onmouseover="addTooltip('asriCatName')">
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
<logic:notEmpty name="AsricategoryList">
<html:options property="ID" collection="AsricategoryList" labelProperty="VALUE"/>
</logic:notEmpty>
</html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="asriCatCode" styleId="asriCatCode" title="Category Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder"><html:select name="patientForm" property="ICDCatName" styleId="ICDCatName" style="WIDTH: 17em" title="Select ICD Category " onchange="javascript:getTherapyICDProcedureList(1);" onmouseover="addTooltip('ICDCatName')">
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
<logic:notEmpty name="ICDcategoryList">
<html:options property="ID" collection="ICDcategoryList" labelProperty="VALUE"/>
</logic:notEmpty>
</html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="ICDCatCode" styleId="ICDCatCode" title="ICD Category Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>


<tr id="dentalSpecProc" style="display:none">
<td>
<html:radio styleId="SpecialProc" style="display:none" name="patientForm" property="dentalProc"  value="" onchange="ShowCeoAlert();getTherapyICDProcedureList(1);"><b>&nbsp;&nbsp;Special Procedures&nbsp;&nbsp;</b></html:radio>
</td>
<td>
<html:radio styleId="normalProc" style="display:none" name="patientForm" property="dentalProc"  value="" onchange="getTherapyICDProcedureList(1);" ><b>&nbsp;&nbsp;Normal Procedures&nbsp;&nbsp;</b></html:radio>
</td>
</tr>



<tr>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ICDProc"/></b> <font color="red" class="onlyAp">*</font></td>
<%-- <td><b><fmt:message key="EHF.ProcCode"/></b> <font color="red">*</font></td> --%>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ICDProcCode"/></b> <font color="red" class="onlyAp">*</font></td>
<td  class="labelheading1 tbcellCss" id="unitsLabelTd" style="display:none"><b>Units</b> <font color="red" class="onlyAp">*</font></td>
<td  class="labelheading1 tbcellCss"><b>Treating Doctor</b> <font color="red" class="onlyAp">*</font></td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td class="tbcellBorder"><html:select name="patientForm" property="ICDProcName" styleId="ICDProcName" style="WIDTH:17em" title="Select ICD Procedure" onchange="getProcedureCodes();" onmouseover="addTooltip('ICDProcName')">                        
            <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
<logic:notEmpty name="ICDprocedureList">
<html:options property="ID" collection="ICDprocedureList" labelProperty="VALUE"/>
</logic:notEmpty>           
    </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="ICDProcCode" styleId="ICDProcCode" title="ICD Procedure Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder" id="unitsTd" style="display:none">
<html:select name="patientForm" property="procUnits" styleId="procUnits" style="WIDTH:17em" title="Select No of Units" onmouseover="addTooltip('procUnits')">
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
<%-- <html:options property="ID" collection="dentalUnitsList" labelProperty="VALUE"/> --%>
</html:select>
</td>
<td class="tbcellBorder"><html:select name="patientForm" property="docSpecReg" styleId="docSpecReg" style="WIDTH:17em" title="Select Treating Doctor" onmouseover="addTooltip('docSpecReg')" onchange="fn_getIPDoctorsDetails()">                        
            <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>     
</html:select></td>
</tr>
<tr><td colspan="4"><table width="100%">
<tr id="ipDoctorData" style="display:none">
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DoctorName"/></b> <font color="red">*</font><br><html:text name="patientForm" property="ipOtherDocName" styleId="ipOtherDocName" maxlength="50" style="WIDTH:15em" onchange="checkAlphaSpace('ipOtherDocName','Doctor Name')" title="Enter Doctor Name"/></td>
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Doctor.RegistrationNo"/></b><font color="red">*</font><br><html:text name="patientForm" property="ipDocRegNo" styleId="ipDocRegNo" maxlength="20" style="WIDTH:15em" onchange="validateAlphaNum('Doctor Registration No',this,'Registration No')" title="Enter Doctor Registration No"/></td>
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.doctor.Qualification"/></b><font color="red">*</font><br><html:text name="patientForm" property="ipDocQual" styleId="ipDocQual" maxlength="30" style="WIDTH:15em" onchange="checkAlpha('ipDocQual','Doctor Qualification','Qualification')" title="Enter Doctor Qualification"/></td>
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ContactNo"/></b><font color="red">*</font><br><html:text name="patientForm" property="ipDocMobileNo" styleId="ipDocMobileNo" maxlength="10" onchange="validateMobile(this);" style="WIDTH:15em" title="Enter Doctor Contact No"/></td>
</tr></table></td></tr>
<tr>
<td colspan="4" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Investigations"/></b>&nbsp;&nbsp;&nbsp;<font color="red">(Upload files with size less than 200KB)</font></td>
</tr>
<tr>
<td colspan="4">
<table width="100%">
<tr>
<td width="40%" class="tbcellBorder">
<html:select name="patientForm" property="investigations" styleId="investigations" style="WIDTH: 99%" multiple="multiple" onmouseover="addTooltip('investigations')">
</html:select>
</td>
<td width="20%"><input class="but" type="button" name="addSpeciality" id="addSpeciality" value="Add Speciality" onclick="addSpecialities()"></input></td>
<!-- <td width="20%"><input class="but" type="button" name="deleteSpeciality" id="deleteSpeciality" value="Delete Speciality" onclick="removeProcedure()"></input></td> -->
<td style="display:none" id="procSelectedTd" width="20%">
<select name="procSelected" id="procSelected" style="width:15em" onchange="confirmRemoveRow(this,'procSelected')"></select>
</td>
<td width="40%"  class="tbcellBorder" style="display:none">
      <html:select name="patientForm" property="investigationSel" styleId="investigationSel" style="WIDTH:99%" size="4" onmouseover="addTooltip('investigationSel')">
    	</html:select>
 </td>
</tr>
<tr><td colspan="4" class="tbcellBorder">
  <table  width="100%"  id="categoryTableID" style="display:none" border="1">
  <tr><td colspan="8" class="labelheading1 tbcellCss">Previously Added Speciality</td></tr>
      <tr>  
      	<!-- <td width="5%" ><fmt:message key="EHF.SNO"/></td> -->      
        <td width="15%" ><b><fmt:message key="EHF.CatName"/></b></td>   
       	<td width="15%"><b><fmt:message key="EHF.ICDCatName"/></b></td>
        <td width="15%"><b><fmt:message key="EHF.ICDProc"/></b></td>
		<td width="5%"><b>Units</b></td>
        <td width="10%"><b>Treating Doctor</b></td>
		<td width="20%" ><b><fmt:message key="EHF.TestName"/></b></td>   
       	<td width="15%"><b><fmt:message key="EHF.Attachment"/></b></td>       	
        <td width="5%">&nbsp;</td>
        </tr>
        <logic:present name="patientForm" property="investigationLt">
        <bean:size  id="investSize" name="patientForm" property="investigationLt"/>
        <logic:greaterThan value="0" name="investSize">
		<logic:iterate id="investLst" name="patientForm" property="investigationLt" >
        <tr>        	      
        <td width="15%"><bean:write name="investLst" property="asriCatName" /></td>   
       	<td width="15%"><bean:write name="investLst" property="catName" /></td>  
       	<td width="15%"><bean:write name="investLst" property="procName" /></td>
		<c:if test="${investLst.opProcUnits ne '-1' && investLst.opProcUnits ne null}">
			<td width="5%"><bean:write name="investLst" property="opProcUnits" /></td>
		</c:if>
		<c:if test="${investLst.opProcUnits eq '-1' || investLst.opProcUnits eq null}">
			<td width="5%">-NA-</td>
		</c:if>		
       	<td width="10%"><bean:write name="investLst" property="docName" /></td> 
       	<td width="20%"><bean:write name="investLst" property="filename" /></td>
		<c:if test="${investLst.name eq 'NA'}">
       	<td width="20%"><bean:write name="investLst" property="name" /></td>
       	</c:if>
       	<c:if test="${investLst.name eq 'View'}">
       	<td width="15%"><a href="#" onclick="javascript:fn_openAtachment('<bean:write name="investLst" property="filePath" />','<bean:write name="investLst" property="filePath" />');"><bean:write name="investLst" property="name" /></a></td>
       	</c:if>
       	<td width="5%"><input class="but" type="button" value="Delete" name=<bean:write name="investLst" property="auditName" /> id=<bean:write name="investLst" property="auditName" />  onclick="javascript:confirmRemoveRowOnload(this,'specialityOnload','<bean:write name="investLst" property="icdProcCode"/>','<bean:write name="investLst" property="investRemarks" />','<bean:write name="investLst" property="catId" />');"/></td>
        </tr>
        </logic:iterate></logic:greaterThan></logic:present>
        </table></td></tr>

<tr><td colspan="4" class="tbcellBorder">
        <table  width="100%"  id="categoryTable" style="display:none" border="1">
        <!--<tr><td colspan="6" class="labelheading1 tbcellCss">Newly Added Speciality</td></tr>-->
      <tr>  
      	<!-- <td width="5%" ><fmt:message key="EHF.SNO"/></td> -->      
        <td width="15%" ><b><fmt:message key="EHF.CatName"/></b></td>   
       	<td width="15%"><b><fmt:message key="EHF.ICDCatName"/></b></td>
        <td width="15%"><b><fmt:message key="EHF.ICDProc"/></b></td>
		<td width="5%"><b>Units</b></td>
        <td width="10%"><b>Treating Doctor</b></td>
		<td width="20%" ><b><fmt:message key="EHF.TestName"/></b></td>   
       	<td width="15%"><b><fmt:message key="EHF.Attachment"/></b></td>
        <td width="5%">&nbsp;</td>
        </tr></table>
</td></tr>
</table></td></tr>
<tr><td colspan="2"><div id="treatingDocLabel" class="labelheading1 tbcellCss" style="display:none"><b>Treating Doctor Remarks</b><font color="red">*</font></div>
<div id="treatingDocRemarks"></div></td></tr>
<tr><td colspan="4">
<table width="100%" >
<tr><td width="50%" valign="top">
<table width="100%">
<tr><td width="50%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.IPNumber"/></b> <font color="red">*</font></td>
<td width="50%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.AdmissionType"/></b> <font color="red">*</font></td></tr>
<tr><td width="50%" class="tbcellBorder">
<html:text name="patientForm" property="ipNo" styleId="ipNo" style="width:17em;" maxlength="20" onchange="validateAlphaNum('IP NO',this,'')" title="Enter IP NO"/>
</td>
<td width="50%" class="tbcellBorder">
<html:select name="patientForm" property="admissionType" styleId="admissionType" title="Select Admission Type" style="width:17em;" onmouseover="addTooltip('admissionType')">
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
<html:option value="PLN"><fmt:message key="EHF.Planned"/></html:option>
<html:option value="EMG"><fmt:message key="EHF.Emergency"/></html:option>
</html:select></td></tr>
</table></td>

<td width="50%" valign="top">
<table width="100%">
<tr><td id="surgDateHead" width="50%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PropSurgDt"/></b> <font color="red">*</font></td>
<td width="50%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Remarks"/></b> <font color="red">*</font></td></tr>
<tr><td id="surgDate" width="50%" class="tbcellBorder">
<html:text name="patientForm" property="ipDate" styleId="ipDate" style="width:14em;" title="Select Proposed Surgery Date" onchange="validatePropSurgeryDate('Proposed Surgery Date',this)"  onkeydown="validateBackSpace(event)" readonly="true"/>
<td width="50%" class="tbcellBorder">
<html:textarea name="patientForm" property="remarks" styleId="remarks" title="Enter Remarks" style="width:18em;;height:3em"  onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'IP Remarks');blockConsecutiveChars('IP Remarks',this,this.value)"/>
</td></tr></table></td></tr>
</table></td></tr>

<tr>
<td class="labelheading1 tbcellCss"><b>Consultant Name&nbsp;<font color="red">*</font></b></td>
<td><html:text name="patientForm" property="ipDoctorName" styleId="ipDoctorName" style="width:100%"></html:text></td>

</tr>



<!-- 
<tr>
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PatientDiagnosedby"/></b> <font color="red">*</font>
<html:select name="patientForm" property="ipDiagnosedBy" styleId="ipDiagnosedBy" style="width:17em" onchange="fn_getIPDoctorsList()" title="Select Patient Diagnosed by" onmouseover="addTooltip('ipDiagnosedBy')"> 
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
<html:option value="MEDCO"><fmt:message key="EHF.MEDCO"/></html:option>
</html:select>
</td>
<td valign="top" id="ipDocNameList" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DoctorName"/></b> <font color="red">*</font>
<html:select name="patientForm" property="ipDoctorName" styleId="ipDoctorName" style="WIDTH:17em" title="Select Doctor Name" onmouseover="addTooltip('ipDoctorName')">
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
</html:select></td>
<td>&nbsp;</td>
</tr>  -->
<tr> 
<td valign="top" class="labelheading1 tbcellCss" colspan="2"><b>Medico Legal Case,If any</b><font color="red">*</font>
	<html:radio name="patientForm" property="legalCase" value="Y" styleId="legalCase" onclick="enable_legalCase('usercheck')" title="Yes"/><b>Yes</b> 
	<html:radio name="patientForm" property="legalCase" value="N" styleId="legalCase" onclick="enable_legalCase('usercheck')" title="No"/><b>No</b> 
</td>
<td id='legalCaseNoTd' style="display:none" valign="top" class="labelheading1 tbcellCss"><b>Legal Case No</b><br>
	<html:text name="patientForm" property="legalCaseNo" style="WIDTH:17em" styleId="legalCaseNo" title="Legal Case No" maxlength="20" onchange="validateAlphaNum('Legal Case No',this,'Legal Case No')"/> 
</td>
<td id="policeStatNameTd" style="display:none" valign="top" class="labelheading1 tbcellCss"><b>Police Station Name</b><br>
	<html:text name="patientForm" property="policeStatName" style="WIDTH:17em" styleId="policeStatName" title="Police Station Name" maxlength="100" onchange="checkAlphaSpace('policeStatName','Police Station Name')"/> 
</td>
</tr>
<tr><td colspan="3">	
	<div id="ipDoctorDataDiv"> </div></td>
</tr>
</table>
</td></tr>
<!--End Of  Fields For Patient Type IP -->
<tr><td  id="prescriptionData" style="display:none;" colspan="4">
<table width="100%">
<tr><td class="labelheading1 tbcellCss"><font ><b><fmt:message key="EHF.Prescription"/>:</b></font></td></tr>
<tr>
<!--  
  <td class="labelheading1 tbcellCss" id="diagOthersHeadOP" style="width:20%" ><b>Diagnosis Name</b></td>
        <td class="tbCellBorder" colspan="3" Id="diagOthersName" ><html:textarea  name="patientForm" property="diagOthersName"   style="width:100%;font-weight:bold;" styleClass="otherFields" onchange="javascript:check_maxLength('otherDiagnosis','4000');"></html:textarea></td>
-->

<td colspan="4" id="existDrugsHead" style="display:none" class="labelheading1 tbcellCss"><font size="2px">Existing Drugs</font></td></tr>
<tr><td colspan="4" class="tbcellBorder">
<table  width="100%"  id="existDrugs" style="display:none" border="1">
      <tr>  
      	<td width="5%"><fmt:message key="EHF.SNO"/></td>        
        <td width="15%"><fmt:message key="EHF.DrugTypeName"/></td>   
       	<td width="15%"><fmt:message key="EHF.DrugSubTypeName"/></td>
        <td width="15%"><fmt:message key="EHF.DrugName"/></td>
        <td width="15%"><fmt:message key="EHF.Route"/></td>
        <td width="10%"><fmt:message key="EHF.Strength"/></td>
        <td width="10%"><fmt:message key="EHF.Dosage"/></td>
        <td width="10%"><fmt:message key="EHF.MedicationPeriod"/></td>
        <td width="5%">&nbsp;</td>
        </tr></table>
</td></tr>


    <tr>
     <!--   <td class="labelheading1 tbcellCss" width="25%">
        <html:checkbox value="OD" title="Click Here to Add Other Drugs" styleId="drugOthers" onchange="fn_enableOtherDrugs();" name="patientForm" property="drugsOthers" ><b>&nbsp;Other Drugs</b></html:checkbox>
        </td> -->
        
        <td width="100%" id="othDrugName" ><html:textarea styleClass="otherFields" name="patientForm" property="otherDrugName"  onchange="javascript:check_maxLength('otherDrugName','4000')" styleId="otherDrugName" title="Please enter Drug Details" style="width:100%;height:150px;font-weight:bold;background-color:#e5e5ff"></html:textarea></td>
        </tr>
        
        <!--  
        <tr>
        <td  class="labelheading1 tbcellCss existDrugs" width="10%"><b>Chemical Substance Name</b></td>
<td valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="drugName" styleId="drugNameAuto" style="width:17em;" title="Select Drug Name" onchange="javascript:getDrugDetailsAuto()" onmouseover="addTooltip('drugName')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		  <html:options collection="drugsListAuto" property="ID" labelProperty="VALUE"/>
</html:select></td>
<td  class="labelheading1 tbcellCss existDrugs" colspan="2" style="color:#A81C06">
<span ><i  class="fa fa-hand-o-left fa-3 autoData"></i></span><b><font>&nbsp;&nbsp;Select Drug Name to Auto Populate Data&nbsp;&nbsp;</font></b><i class="fa fa-medkit"></i> 
</td>
        </tr>
        

<tr>

<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Main Group Name</b> <font color="red" class="onlyAp">*</font></td>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Main Group Code</b><font color="red" class="onlyAp">*</font></td>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Therapeutic Main Group Name</b> <font color="red" class="onlyAp">*</font></td>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Therapeutic Main Group Code</b> <font color="red" class="onlyAp">*</font></td>
</tr>
<tr>
<td valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="drugTypeCode" styleId="drugTypeCode" style="width:17em;" title="Select Drug type" onchange="getDrugSubTypeList()" onmouseover="addTooltip('drugTypeCode')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:options property="ID" collection="drugsList" labelProperty="VALUE"/>
</html:select></td>
<td class="tbcellBorder existDrugs"><html:text name="patientForm"  property="drugCode" styleId="drugCode" title="Drug Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="drugSubTypeName" styleId="drugSubTypeName" style="width:17em;" title="Select Drug Sub type" onchange="getPharSubGrpLst()" onmouseover="addTooltip('drugSubTypeName')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
</html:select></td>
<td class="tbcellBorder existDrugs"><html:text name="patientForm"  property="drugSubTypeCode" styleId="drugSubTypeCode" title="Drug Sub Type Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>

<tr>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Pharmacological SubGroup Name</b><font color="red" class="onlyAp">*</font> </td>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Pharmacological SubGroup Code</b> <font color="red" class="onlyAp">*</font></td>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Chemical SubGroup Name</b><font color="red" class="onlyAp">*</font> </td>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Chemical SubGroup Code</b><font color="red" class="onlyAp">*</font> </td>
</tr>
<tr>
<td valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="pSubGrpName" styleId="pSubGrpName" style="width:17em;" title="Select Pharmacological SubGroup " onchange="getChemicalSubGrp()" onmouseover="addTooltip('pSubGrpName')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>		
</html:select></td>
<td class="tbcellBorder existDrugs"><html:text name="patientForm"  property="pSubGrpCode" styleId="pSubGrpCode" title="Pharmacological SubGroup Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="cSubGrpName" styleId="cSubGrpName" style="width:17em;" title="Chemical SubGroup" onchange="getDrugNameList()" onmouseover="addTooltip('cSubGrpName')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
</html:select></td>
<td class="tbcellBorder existDrugs"><html:text name="patientForm"  property="cSubGrpCode" styleId="cSubGrpCode" title="Drug Sub Type Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>

<tr>
<td class="labelheading1 tbcellCss existDrugs"><b>Chemical Substance Name</b> <font color="red" class="onlyAp">*</font></td>
<td class="labelheading1 tbcellCss existDrugs"><b>Chemical Substance Code</b> <font color="red" class="onlyAp">*</font></td>
<td class="labelheading1 tbcellCss" id="otherDrug" style="display:none"><b>Drug Name</b><font color="red" class="onlyAp">*</font> </td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Route"/></b><font color="red" class="onlyAp">*</font> </td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Strength"/></b><font color="red" class="onlyAp">*</font> </td>   
</tr>
<tr>
<td valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="drugName" styleId="drugName" style="width:17em;" title="Select Drug Name" onchange="getDrugDetails()" onmouseover="addTooltip('drugName')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>  
</html:select></td>
<td class="tbcellBorder existDrugs"><html:text name="patientForm"  property="asriDrugCode" styleId="asriDrugCode" title="Drug Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td width="50%" id="othDrugName" style="display:none;"><html:text styleClass="otherFields" name="patientForm" property="otherDrugName"  onchange="javascript:check_maxLength('otherDrugName','200')" styleId="otherDrugName" title="Please enter Drug Name"></html:text></td>
<td class="tbcellBorder">
<table >
<tr><td width='50%'><html:select name="patientForm" property="routeType" styleId="routeType" style="width:8em;" title="Select Route Type" onchange="getRouteList()" onmouseover="addTooltip('routeType')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:options property="ID" collection="routeTypeLst" labelProperty="VALUE"/>
</html:select></td>

<td width='50%'><html:select name="patientForm" property="route" styleId="route" style="width:8em;" title="Select Route "  onmouseover="addTooltip('route')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>		
</html:select></td> </tr>
</table></td>
<td class="tbcellBorder">   
<table >
<tr><td width='50%'><html:select name="patientForm" property="strengthType" styleId="strengthType" style="width:8em;" title="Select Strength Type" onchange="getStrengthList()" onmouseover="addTooltip('strengthType')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:options property="ID" collection="strengthTypeLst" labelProperty="VALUE"/>
</html:select></td>
<td width='50%'><html:select name="patientForm" property="strength" styleId="strength" style="width:8em;" title="Select Strength "  onmouseover="addTooltip('strength')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>		
</html:select></td> </tr>
</table></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Dosage"/></b><font color="red" class="onlyAp">*</font> </td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.MedicationPeriod"/></b><font color="red" class="onlyAp">*</font> </td>
<td colspan="2">&nbsp;</td>
</tr> 
<tr>
<td class="tbcellBorder">
<html:select name="patientForm" property="dosage" styleId="dosage" style="width:17em;" title="Select Dosage"  onmouseover="addTooltip('dosage')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:option value="OD">OD</html:option>
		<html:option value="BD">BD</html:option>
		<html:option value="TID">TID</html:option>
		<html:option value="QID">QID</html:option>
</html:select></td>
<td class="tbcellBorder"><html:text name="patientForm" property="medicatPeriod" styleId="medicatPeriod" maxlength="3" style="WIDTH:17em" onchange="validateNumber('Medication Period',this)" title="Enter Medication Period"/></td>
<td colspan="2"><button type="but" class="btn btn-success" name="addDrug" id="addDrug" value="Add Drugs" onclick="addDrugs()">Add Drugs&nbsp;<i class="fa fa-plus-square"></i></button></td>
</tr>
-->
<tr><td colspan="4" class="tbcellBorder"> 
<!-- 
<div id='drugsContent' style="width:950px;overflow:auto; overflow-y:hidden"> 
  <table  width="100%"  id="drugsTable" style="display:none" border="1">
      <tr>  
      	<td width="5%"><b><fmt:message key="EHF.SNO"/></b></td>        
        <td width="10%"><b>Main Group Name</b></td>   
       	<td width="10%"><b>Therapeutic Main Group Name</b></td>
        <td width="10%"><b>Pharmacological SubGroup Name</b></td>
		<td width="10%"><b>Chemical SubGroup Name</b></td>
		<td width="10%"><b>Chemical Substance Name</b></td>
        <td width="5%"><b>Route Type</b></td>
        <td width="10%"><b><fmt:message key="EHF.Route"/></b></td>
        <td width="5%"><b>Strength Type</b></td>
        <td width="10%"><b><fmt:message key="EHF.Strength"/></b></td>
        <td width="5%"><b><fmt:message key="EHF.Dosage"/></b></td>
        <td width="5%"><b><fmt:message key="EHF.MedicationPeriod"/></b></td>
        <td width="5%">&nbsp;</td>
        </tr>
        <logic:present name="patientForm" property="drugLt">
        <bean:size  id="drugSize" name="patientForm" property="drugLt"/>
        <logic:greaterThan value="0" name="drugSize">
		<%int k = 1;%>
        <logic:iterate id="drugLst" name="patientForm" property="drugLt" >
        <tr>  
      	<td width="5%"><%=k++ %></td>        
        <td width="10%"><bean:write name="drugLst" property="DRUGTYPENAME" /></td>   
       	<td width="10%"><bean:write name="drugLst" property="DRUGSUBTYPENAME" /></td> 
       	<td width="10%"><bean:write name="drugLst" property="PSUBGRPNAME"/></td>
        <td width="10%"><bean:write name="drugLst" property="CSUBGRPNAME"/></td> 
       	<td width="10%"><bean:write name="drugLst" property="DRUGNAME" /></td> 
       	<td width="5%"><bean:write name="drugLst" property="ROUTETYPENAME" /></td> 
       	<td width="10%"><bean:write name="drugLst" property="ROUTENAME" /></td>  
       	<td width="5%"><bean:write name="drugLst" property="STRENGTHTYPENAME" /></td>
       	<td width="10%"><bean:write name="drugLst" property="STRENGTHNAME" /></td>  
       	<td width="5%"><bean:write name="drugLst" property="DOSAGE" /></td>  
       	<td width="5%"><bean:write name="drugLst" property="MEDICATIONPERIOD" /></td> 
       	<td width="5%"><input class="but" type="button" value="Delete" name=<bean:write name='drugLst' property='DRUGCODE' /> id=<bean:write name='drugLst' property='DRUGCODE' /> onclick="javascript:confirmRemoveRow(this,'drug');"/></td>
        </tr>
        </logic:iterate></logic:greaterThan></logic:present>        
        </table></div>  -->
</td></tr>
</table>
</td></tr>
<tr><td style="display:none" id="OPHead2" colspan="4">
<table width="100%">
<c:if test="${nimsHosp eq 'Y' }">
<tr>
<td class="labelheading1 tbcellCss"><b>Specialty</b> <font color="red">*</font></td>

<td class="tbcellBorder">
<html:select name="patientForm" property="medicalSpclty" styleId="medicalSpclty" style="width:17em;" title="Select Speciality" onchange="getMedicalCategoryList(this.id)"  onmouseover="addTooltip('medicalSpclty')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:options property="ID" collection="medicalSpltyList" labelProperty="VALUE"/>
</html:select></td>

<td  class="labelheading1 tbcellCss"><b>Category</b> <font color="red">*</font></td>

<td  class="tbcellBorder">
<html:select name="patientForm" property="medicalCat" styleId="medicalCat" style="width:17em;" title="Select Category" onchange="getMedicalDrugList(this.id)"  onmouseover="addTooltip('medicalCat')">
		 <c:if test="${fn:length(medicalCatList) gt 0}">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:options property="ID" collection="medicalCatList" labelProperty="VALUE"/>
		</c:if>
</html:select></td>
</tr>
<tr id="medicalDrugId" style="display:none">
<%-- <td  class="labelheading1 tbcellCss"><b>Oncology Drug</b> <font color="red">*</font></td>
<td  class="tbcellBorder">
<html:select name="patientForm" property="medicalDrug" styleId="medicalDrug" style="width:17em;" title="Select Drug" onmouseover="addTooltip('medicalDrug')">
		 <c:if test="${fn:length(medicalDrugList) gt 0}">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:options property="ID" collection="medicalDrugList" labelProperty="VALUE"/>
		</c:if>
</html:select></td> --%>
<!-- Start CR#4511-SriHari-10/10/24 -->
<td class="tbcellBorder" colspan="4"> 
<button type="button" class="btn btn-info" id="proformaId" onclick="proformaForm()" data-toggle="modal" data-target="#proformaModal" title="Click Here To View/Edit Proforma Form">View/Edit Proforma Form<i class="fa fa-files-o"></i></button>
<c:if test="${highEndEvaluationFlag eq 'P' || highEndEvaluationFlag eq 'Y'}">
<button type="button" class="btn btn-info" id="evalutionId" onclick="evaluationForm()" data-toggle="modal" data-target="#evaluationModal" title="Click Here To View/Edit Evaluation Form">View/Edit Evaluation Form<i class="fa fa-files-o"></i></button>
</c:if>

<div class="" id=""></div>
</td>
<!-- End CR#4511-SriHari -->
</tr>
</c:if>
<tr>
<td width="20%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.OPNO"/></b> <font color="red">*</font></td>
<td width="20%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.RegistrationDate"/></b> <font color="red">*</font></td>
<td width="40%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Remarks"/></b> <font color="red">*</font></td>
<td width="20%">&nbsp;</td>
</tr>
<tr>
<td valign="top" class="tbcellBorder"><html:text name="patientForm" property="opNo" styleId="opNo" maxlength="20" style="width:17em" onchange="validateAlphaNum('OP NO',this,'')" title="Enter OP NO"/></td>
<td valign="top" class="tbcellBorder"><html:text name="patientForm" property="opDate" styleId="opDate" style="width:17em" value="${serverDt}" disabled="true"/></td>
<td class="tbcellBorder">
<html:textarea name="patientForm" property="opRemarks" styleId="opRemarks" title="Enter OP Remarks" style="width:18em;;height:3em"  onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'OP Remarks');blockConsecutiveChars('OP Remarks',this,this.value)"/>
</td>
<td>&nbsp;</td>
</tr>
</table></td></tr>
</table>

<table width="95%" style="margin:left;display:none" id="OPDoctor">
<tr>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PatientDiagnosedby"/></b> <font color="red">*</font></td>
<td width="25%" id="docName" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DoctorName"/></b> <font color="red">*</font></td>
<td width="25%" class="labelheading1 tbcellCss" id="unitNameHead" style="display:none;"><b>Unit Name</b> <font color="red">*</font></td>
<td width="25%" class="labelheading1 tbcellCss" id="unitHODNameHead" style="display:none;"><b>Unit HOD Name</b> <font color="red">*</font></td>
</tr>
<tr>
<td valign="top" class="tbcellBorder">




<html:select name="patientForm" property="diagnosedBy" styleId="diagnosedBy" style="width:17em" onchange="fn_getDoctorsList()" title="Select Patient Diagnosed by" onmouseover="addTooltip('diagnosedBy')"> 
<html:option value="-1" ><fmt:message key="EHF.Select"/></html:option>
<html:option value="MEDCO" styleId="onlyIp1" style="display:none"><fmt:message key="EHF.MEDCO"/></html:option>
<html:option value="Resident Doctor">Resident Doctor</html:option>
<html:option value="Consultant"><fmt:message key="EHF.Consultant"/></html:option>
<html:option value="DutyDoctor" styleId="onlyIp2" style="display:none"><fmt:message key="EHF.DutyDoctor"/></html:option>
</html:select>
</td>

<td valign="top" id="unitName" style="display:none"  class="labelheading1 tbcellCss"><html:text name="patientForm" property="unitName" styleId="unitName" maxlength="50" style="WIDTH:17em"  title="Enter Unit Name"/></td>
<td valign="top" id="unitHodName" style="display:none"  class="labelheading1 tbcellCss"><html:text name="patientForm" property="unitHodName" styleId="unitHodName" maxlength="50" style="WIDTH:17em" title="Enter Unit HOD  Name"/></td>
<td valign="top" id="addConsult" style="display:none"  class="labelheading1 tbcellCss"><button class="btn btn-primary" id="addConsult" onclick="javascript:fn_addConsultant();">Add Consultation Details&nbsp;<i class="fa fa-stethoscope"></i></button></td>

<td valign="top" id="docNameList" class="tbcellBorder">
<html:select name="patientForm" property="doctorName" styleId="doctorName" style="WIDTH:17em"  title="Select Doctor Name" onmouseover="addTooltip('doctorName')"> <!-- onchange="fn_getDoctorsDetails()" -->
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
</html:select></td>
<td valign="top" id="docNametext" style="display:none"  class="labelheading1 tbcellCss"><html:text name="patientForm" property="otherDocName" styleId="otherDocName" maxlength="50" style="WIDTH:17em" onchange="checkAlphaSpace('otherDocName','Doctor Name')" title="Enter Doctor Name"/></td>
<td width="20%">&nbsp;</td>
<td class="tbcellCss" id="consulFee" style="display:none"><b><font color="#B01000">Rs 150</font></b></td>
</tr>
<tr><td colspan="4" class="tbcellBorder">			
	<div id="doctorDataDiv"> </div></td>
</tr>
<tr id="doctorData" style="display:none">
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Doctor.RegistrationNo"/></b><font color="red">*</font>&nbsp;
<html:text name="patientForm" property="docRegNo" styleId="docRegNo" styleClass="otherFields" maxlength="20" style="WIDTH:9em"    onchange="javascript:check_maxLength('docRegNo','100')" title="Enter Doctor Registration No"/></td>
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.doctor.Qualification"/></b><font color="red">*</font>&nbsp;
<html:text name="patientForm" property="docQual" styleId="docQual" maxlength="30" style="WIDTH:9em" styleClass="otherFields" onchange="javascript:check_maxLength('docQual','100')" title="Enter Doctor Qualification"/></td>
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ContactNo"/></b><font color="red">*</font>&nbsp;
<html:text name="patientForm" property="docMobileNo" styleId="docMobileNo" maxlength="10" onchange="validateMobile(this);" style="WIDTH:9em" title="Enter Doctor Contact No"/></td>
</tr>
</table>



<table width="95%" id="consultationDataNew" style="margin:auto;display:none;">
<tbody>
<tr>
<td class="tbheader" colspan="4">Consultation Details&nbsp;&nbsp;&nbsp;<i class="fa fa-medkit"></i></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss" style="width:10%"><b>S.No</b></td>
<td class="labelheading1 tbcellCss" style="width:20%"><b>Unit Name</b></td>
<td class="labelheading1 tbcellCss" style="width:20%"><b>Diagnosed By</b></td>
<td class="labelheading1 tbcellCss" style="width:20%"><b>Unit HOD Name</b></td>
</tr>
</tbody>
</table>

<br><br>

<logic:notEmpty name="patientForm" property="consultData">
<table width="95%" style="margin:auto;" id="consultationDataOld">
<tbody>
<tr>
<td class="tbheader" colspan="5">Previously Added Consultation Details&nbsp;&nbsp;&nbsp;<i class="fa fa-medkit"></i></td></tr>
<tr>
<td class="labelheading1 tbcellCss" style="width:10%"><b>S.No</b></td>
<td class="labelheading1 tbcellCss" style="width:20%"><b>Unit Name</b></td>
<td class="labelheading1 tbcellCss" style="width:20%"><b>Diagnosed By</b></td>
<td class="labelheading1 tbcellCss" style="width:20%"><b>Unit HOD Name</b></td>
<td class="labelheading1 tbcellCss" style="width:20%"><b>Consultation Date</b></td>
</tr>

<c:set var="count" value="1"></c:set>
<logic:iterate id="consultData" name="patientForm" property="consultData">
<tr>
<td class="tbcellBorder" style="width:10%"><b>${count}</b></td>
<td class="tbcellBorder" style="width:20%"><b><bean:write name="consultData" property="unitName"></bean:write></b></td>
<td class="tbcellBorder" style="width:20%"><b><bean:write name="consultData" property="diagnoisedBy"></bean:write></b></td>
<td class="tbcellBorder" style="width:20%"><b><bean:write name="consultData" property="hodName"></bean:write></b></td>
<td class="tbcellBorder" style="width:20%"><b><bean:write name="consultData" property="consultationTime"></bean:write></b></td>
</tr>
<c:set var="count" value="${count+1}"></c:set>
</logic:iterate>
</tbody>
</table>
</logic:notEmpty>

<table width="100%" id="opClaimTable" style="display:none">
<tbody>
<tr>
<td width="30%" class="labelheading1 tbcellCss" id="consulFeeHead" ><b>Consulation Fee</b></td>
<td width="30%" class="labelheading1 tbcellCss" id="consulFeeHead" ><b>Cost of Investigations</b> </td>
<td width="30%" class="labelheading1 tbcellCss" id="consulFeeHead" ><b>Total OP Amount</b></td>
</tr>
<tr>
<td class="tbcellCss" ><b><font color="#B01000">&#x20B9; <bean:write name="patientForm" property="consultFee"></bean:write>&nbsp;/-</font></b></td>
<td class="tbcellCss" id="costOfInvest" ><b><font color="#B01000">&#x20B9; <bean:write name="patientForm" property="totInvestPrice"></bean:write>&nbsp;/-</font></b></td>
<td class="tbcellCss" id="totalOpCost" ><b><font color="#B01000">&#x20B9; <bean:write name="patientForm" property="totalOpCost"></bean:write>&nbsp;/-</font></b></td>
</tr>
</tbody>
</table>


<table width="100%"><tr>
<td colspan="4" id="opNote" style="display:none">
<c:if test="${nimsHosp ne 'Y' }">
 <font color="red"><b>OP Case Should be Submitted before 14 days after Case Registration</b></font> 
</c:if>
</td></tr>

<tr><td colspan="4" id="ipNote1">
</td></tr>
<tr><td>&nbsp;</td></tr>

<tr><td colspan="4">
<table width="100%">
</table></table>

<table width="70%" align="center" style="margin-left:30%">
<tr>
<td align="center" colspan="3"> <button class="btn btn-primary has-spinner"  type="button" id="Submit" onclick="javascript:fn_saveDetailsWithoutMandate('submit')">Submit&nbsp;<span class="spinner"><i class="fa fa-medkit"></i></span></button>
 <button class="btn btn-primary has-spinner"  type="button" id="Save" onclick="javascript:fn_saveDetailsWithoutMandate('Save')">Save&nbsp;<span class="spinner"><i class="fa fa-floppy-o"></i></span></button>
 <button class="btn btn-primary"  type="button" id="Reset" onclick="javascript:fn_resetAll()">Reset&nbsp;<i class="fa fa-eraser"></i></button>
<button type="button" class="btn btn-danger has-spinner"    style="display:none" id="cancel" onclick="javascript:fn_cancel()"; data-toggle="tooltip" data-placement="right" title="Click Here To Cancel Chronic Case">Cancel&nbsp;<span class="glyphicon glyphicon-remove"></span></button>
<button type="button" class="btn btn-info"    style="display:none" id="addAttach" onclick="javascript:fn_addAttach()"; data-toggle="modal" data-target="#addAttachModal" title="Click Here To Add Attachments">Add Attachments&nbsp;<i class="fa fa-files-o"></i></button>
</td>
<td width="20%"></td>
</tr>
</table>

<br><br>
</logic:equal></logic:iterate></logic:notEmpty>
<!--  modal for showing attachments -->
<div class="modal fade col-lg-12 col-md-12 col-sm-12 col-xs-12" id="attachModal" >
<div class="modal-dialog" id="modal-1gx">
 <div class="modal-content">
 
 <div class="modal-body" style="height: 410px;">
 
 <iframe  id="attachFrame" class="embed-responsive-item" height="100%" width="100%" scrolling="yes" frameborder="0"></iframe>
 </div>
 
 <div class="modal-footer">
 <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
 </div>
 </div></div></div>

 <!-- div for showing past history  -->
 
 <div id="pastHistoryModal" class="modal col-lg-12 col-md-12 col-sm-12 col-xs-12 fade">
 <div class="modal-dialog" id="modal-1gx" style="
    margin-left: 8%;
    margin-right: 5%;">
 <div class="modal-content" style=";width:140%;align:center;margin:auto;">
 <div class="modal-header" style="height: 36px;color:#FFF;">
<span class="glyphicon glyphicon-user"> </span><label>&nbsp;Case Details</label>
</div>

 <div class="modal-body" style="height:410px;">
 <iframe id="pastHistoryFrame" class="embed-responsive-item" height="100%" width="100%" scrolling="yes" frameborder="0"></iframe>
 </div>
 <div class="modal-footer">
 <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
 </div>
 </div></div>
 </div>
 
  <!-- div for  adding attachments   -->
 
 <div id="addAttachModal" class="modal col-lg-12 col-md-12 col-sm-12 col-xs-12 fade">
 <div class="modal-dialog" id="modal-1gx" style="
    margin-left: 8%;
    margin-right: 5%;">
 <div class="modal-content" style=";width:140%;align:center;margin:auto;">
 <div class="modal-header" style="height: 36px;color:#FFF;">
<span class="glyphicon glyphicon-user"> </span><label>&nbsp;Attachments</label>
</div>

 <div class="modal-body" style="height:410px;">
 <iframe id="addAttachFrame" class="embed-responsive-item" height="100%" width="100%" scrolling="yes" frameborder="0"></iframe>
 </div>
 <div class="modal-footer">
 <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
 </div>
 </div></div>
 </div>
 
 
<!--  modal for showing attachments -->
<div class="modal fade col-lg-12 col-md-12 col-sm-12 col-xs-12" id="proformaModal" aria-hidden="true" data-backdrop="static">
<div class="modal-dialog" id="modal-1gx" style="width: 900px;">
 <div class="modal-content">
  <div class="modal-header" style="height: 36px;color:#FFF;">
<span class="glyphicon glyphicon-user"> </span><label>&nbsp;High End Oncology Drugs Proforma Form</label>
</div>
 <div class="modal-body" style="height: 410px;">
 
 <iframe  id="proformaFrame" class="embed-responsive-item" height="100%" width="100%" scrolling="yes" frameborder="0"></iframe>
 </div>
 
 <div class="modal-footer">
 <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
 </div>
 </div></div></div>
 
 <div class="modal fade col-lg-12 col-md-12 col-sm-12 col-xs-12" id="evaluationModal" aria-hidden="true" data-backdrop="static">
<div class="modal-dialog" id="modal-1gx" style="width: 900px;">
 <div class="modal-content">
  <div class="modal-header" style="height: 36px;color:#FFF;">
<span class="glyphicon glyphicon-user"> </span><label>&nbsp;Oncology Evaluation Response Form</label>
</div>
 <div class="modal-body" style="height: 410px;">
 
 <iframe  id="evaluationFrame" class="embed-responsive-item" height="100%" width="100%" scrolling="yes" frameborder="0"></iframe>
 </div>
 
 <div class="modal-footer">
 <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
 </div>
 </div></div></div>
 
<div class="modal fade" id="otpModal" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" style="width: 500px;">
        <div class="modal-content">
            <div class="modal-header" style="height: 36px; color: #FFF; background-color: #007bff;">
                <span class="glyphicon glyphicon-lock"></span>
                <label>&nbsp;OTP Verification</label>
                <!-- <button type="button" class="close" onclick="cancelOtpVerification()" aria-label="Close">&times;</button> -->
            </div>
            <div class="modal-body" style="padding: 20px;">
                <p>Please enter the OTP sent to your registered contact number.</p>
                <input type="text" id="otpInput" class="form-control" maxlength="6" placeholder="Enter OTP" style="width: 100%; margin-bottom: 10px;" onkeyup="this.value = this.value.replace(/[^0-9]/g, '').slice(0, 6);" />
                <div id="otpTimer" style="margin-bottom: 10px; color: red; font-weight: bold;">Time remaining: 5:00</div>
                <button id="verifyOtpBtn" class="btn btn-success" onclick="verifyOtp(); return false;">Verify OTP</button>
                <button id="resendOtpBtn" class="btn btn-link" onclick="resendOtp(); return false;" disabled>Resend OTP</button>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-warning" onclick="cancelOtpVerification()">Cancel</button>
            </div>
        </div>
    </div>
</div>

<html:hidden name="patientForm" property="caseId" styleId="caseId"/>
<html:hidden name="patientForm" property="patientScheme" styleId="patientScheme"/>
<html:hidden name="patientForm" property="testsCount" styleId="testsCount"/> 
<html:hidden name="patientForm" property="patientNo" styleId="patientNo"/>
<html:hidden name="patientForm" property="hospitalId" styleId="hospitalId"/>
<input type="hidden" name="investigationsSel" id="investigationsSel"/>
<html:hidden name="patientForm" property="personalHistVal" styleId="personalHistVal"/>
<html:hidden name="patientForm" property="examFndsVal" styleId="examFndsVal"/>
<html:hidden name="patientForm" property="speciality" styleId="speciality"/>
<html:hidden name="patientForm" property="otherDocDetailsList" styleId="otherDocDetailsList"/>
<html:hidden name="patientForm" property="drugs" styleId="drugs"/>
<html:hidden name="patientForm" property="symptoms" styleId="symptoms"/>
<html:hidden name="patientForm" property="hosptype" styleId="hosptype"/>
<html:hidden name="patientForm" property="cardNo" styleId="cardNo"/>
<html:hidden name="patientForm" property="fname" styleId="fname"/>
<html:hidden name="patientForm" property="telephonicId" styleId="telephonicId"/>
<html:hidden name="patientForm" property="telephonicReg" styleId="telephonicReg"/>
<html:hidden name="patientForm" property="therapyType" styleId="therapyType"/>
<html:hidden name="patientForm" property="therapyCategory" styleId="therapyCategory"/>
<html:hidden name="patientForm" property="therapy" styleId="therapy"/>
<html:hidden name="patientForm" property="personalHist" styleId="personalHist"/>
<html:hidden name="patientForm" property="gender" styleId="gender"/>
<html:hidden name="patientForm" property="years" styleId="years"/>
<html:hidden name="patientForm" property="months" styleId="months"/>
<html:hidden name="patientForm" property="days" styleId="days"/>
<html:hidden name="patientForm" property="scheme" styleId="scheme"/>
<html:hidden name="patientForm" property="patientScheme" styleId="patientScheme"/>
<html:hidden name="patientForm" property="dentalSpecApproval"/>
<html:hidden name="patientForm" property="dentalProc"/>
<html:hidden name="patientForm" property="consultFee" styleId="consultFee"/>
<html:hidden name="patientForm" property="investPrice" styleId="investPrice"/>
<html:hidden name="patientForm" property="totInvestPrice" styleId="totInvestPrice"/>
<html:hidden name="patientForm" property="totalOpCost" styleId="totalOpCost"/>
<html:hidden name="patientForm" property="otherInvestCount" styleId="otherInvestCount"/>
<html:hidden name="patientForm" property="otherDrugCount" styleId="otherDrugCount"/>
<html:hidden name="patientForm" property="opActiveMsg" styleId="opActiveMsg"/>
<html:hidden name="patientForm" property="otherSymptomCount" styleId="otherSymptomCount"/>

<html:hidden name="patientForm" property="postDist" styleId="postDist"/>
<html:hidden name="patientForm" property="stoCode" styleId="stoCode"/>
<html:hidden name="patientForm" property="ddoCode" styleId="ddoCode"/>
<html:hidden name="patientForm" property="deptHod" styleId="deptHod"/>
<html:hidden name="patientForm" property="checkType" styleId="checkType"/>
<html:hidden name="patientForm" property="hosType" styleId="hosType" value="${patientForm.hosType}"/>
<input type ="hidden" name="hospGovu" id= "hospGovu" value="${hospGovu}"/>
<input type ="hidden" name="highEndProformaSubmitFlag" id= "highEndProformaSubmitFlag" value="${highEndProformaSubmitFlag}"/>
<input type ="hidden" name="highEndEvaluationSubmitFlag" id= "highEndEvaluationSubmitFlag" value="${highEndEvaluationFlag}"/>
<input type ="hidden" name="highEndProformaId" id= "highEndProformaId" value="${highEndProformaId}"/>


<script>

$(function(){
    $('a, button').click(function() {
        $(this).toggleClass('active');
    });
});

var personalHistory=document.forms[0].personalHistory;
for(var i=0;i<personalHistory.length;i++)
    {
	if('${PatientOpList.personalHis}'==''){
		personalHistory[i].checked=true;
	      getSubListHistory(personalHistory[i],'NA');
	}
	else{
		if('${PatientOpList.personalHis}'.indexOf(personalHistory[i].value,0)!=-1){
	        personalHistory[i].checked=true;
            getSubListHistory(personalHistory[i],'NA'); 
	}
     }
    }
	var examinationFnd=document.forms[0].examinationFnd;
	for(var i=0;i<examinationFnd.length;i++)
	{
		if('${PatientOpList.examFindg}'==''){
			examinationFnd[i].checked=true;
			getSubFieldType(examinationFnd[i]);
		}else{
			if('${PatientOpList.examFindg}'.indexOf(examinationFnd[i].value,0)!=-1){
				examinationFnd[i].checked=true;
				getSubFieldType(examinationFnd[i]); 
		}
	   }
	}

function getChronicOPType()
{
	if(document.getElementById("hosptype").value!="G"||document.getElementById("hosptype").value=="G"){
		document.getElementById("patientType3").style.display="none";
	}
}
//getChronicOPType();
var browserName=navigator.appName;
if(browserName=="Microsoft Internet Explorer")
	{
	//For validating maxlength onpaste event--For textarea fields
	document.getElementById("presentHistory").attachEvent("onpaste",pasteIntercept);
	document.getElementById("remarks").attachEvent("onpaste",pasteInterceptRemarks);
	document.getElementById("opRemarks").attachEvent("onpaste",pasteInterceptOpRemarks);
	if(document.getElementById("treatingDocLabel").style.display=='')
		{
		document.getElementById("treatingDocRmks").attachEvent("onpaste",pasteInterceptDocRemarks);
		}
	}
else if(browserName == "Netscape")
	{
	document.getElementById("presentHistory").addEventListener("paste", pasteIntercept, false);
	document.getElementById("remarks").addEventListener("paste", pasteInterceptRemarks, false);
	document.getElementById("opRemarks").addEventListener("paste", pasteInterceptOpRemarks, false);
	if(document.getElementById("treatingDocLabel").style.display=='')
		{
		document.getElementById("treatingDocRmks").addEventListener("paste",pasteInterceptDocRemarks,false);
		}
	}
//Added to remove S4 speciality -Gynaecology and  Obstretics if the patient is male	
if(document.getElementById("gender").value=='Male')
{
	var selectSpeciality=document.getElementById('asriCatName');
	for (var ss=0;ss<selectSpeciality.length;ss++) {
		if (selectSpeciality.options[ss].value=='S4') {
			selectSpeciality.remove(ss);
		}
	}
} 
//Added to remove S8 speciality -Paediatric surgery and M4 - Paediatrics if the patient age is above 14 yrs
if(document.getElementById("years").value>=14)
{
	var selectSpeciality=document.getElementById('asriCatName');
	for (var ss=0;ss<selectSpeciality.length;ss++) {
		if (selectSpeciality.options[ss].value=='M4') {
			selectSpeciality.remove(ss);
		}
	}
	for (var ss=0;ss<selectSpeciality.length;ss++) {
			if (selectSpeciality.options[ss].value=='S8') {
			selectSpeciality.remove(ss);
		}
	}
	for (var ss=0;ss<selectSpeciality.length;ss++) {
		if (selectSpeciality.options[ss].value=='S16') {          /*ADDED FOR REMOVING COCHLEAR CASE*/
		selectSpeciality.remove(ss);
	}
}
}

//Added to populate complaints 
var compCodes = document.getElementById("complaintCode").value.split("~");
var complaint=document.forms[0].complaints;
for (var x=0;x<complaint.length;x++)
{
	for(var j=0;j<compCodes.length;j++)
	{
		if(compCodes[j]==document.forms[0].complaints[x].value)
			document.forms[0].complaints[x].selected = true;                
	}
}

if(document.getElementById("complaintCode").value!=null && document.getElementById("complaintCode").value!="")
	getComplaintType("onLoad");

var pastHistory=document.forms[0].pastHistory;
var pastHistoryVal='${PatientOpList.pastIllness}'.split("~");

for(var i=0;i<pastHistory.length;i++)
    {
	for(var j=0;j<pastHistoryVal.length;j++)
	{
		if(document.getElementById(pastHistory[i].value))
		{
	if(pastHistory[i].value==pastHistoryVal[j])
	{
		pastHistory[i].checked=true;
			if(pastHistory[i].value=='PH11' || pastHistory[i].value=='PH8' || pastHistory[i].value=='PH12' || pastHistory[i].value=='PH10'){
			getOtherText(pastHistory[i]);
			if(pastHistory[i].value=='PH11' && '${PatientOpList.pastIllenesOthr}'!='')
				document.getElementById('pastHistryOthr').value='${PatientOpList.pastIllenesOthr}';
			if(pastHistory[i].value=='PH8' && '${PatientOpList.pastIllenesCancers}'!='')
				document.getElementById('pastHistryCancers').value='${PatientOpList.pastIllenesCancers}';
			if(pastHistory[i].value=='PH12' && '${PatientOpList.pastIllenesEndDis}'!='')
				document.getElementById('pastHistryEndDis').value='${PatientOpList.pastIllenesEndDis}';
			if(pastHistory[i].value=='PH10' && '${PatientOpList.pastIllenesSurg}'!='')
				document.getElementById('pastHistrySurg').value='${PatientOpList.pastIllenesSurg}';
			}	}
	}}}
var familyHistory=document.forms[0].familyHistory;
var famHistoryVal;
if('${PatientOpList.familyHis}'!='')
{
	famHistoryVal='${PatientOpList.familyHis}'.split("~");
	for(var i=0;i<familyHistory.length;i++)
    {
		for(var j=0;j<famHistoryVal.length;j++)
		{
			if(document.getElementById(familyHistory[i].value))
			{
			if(familyHistory[i].value==famHistoryVal[j])
			{
				familyHistory[i].checked=true;
				if(familyHistory[i].value=='FH11'){	
					getOtherText(familyHistory[i]);
					if('${PatientOpList.familyHistoryOthr}'!='')
						document.getElementById('familyHistoryOthr').value='${PatientOpList.familyHistoryOthr}';
				}
			}
		}}
	} 
}
if('${PatientOpList.lstPerHis}'!=null && '${PatientOpList.lstPerHis}'.indexOf("PR5.1",0)!=-1)
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
	if('${PatientOpList.lstPerHis}'!=null && '${PatientOpList.lstPerHis}'.indexOf("PR6.1",0)!=-1)
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
	
var addition='${PatientOpList.test}';var additionKnown='${PatientOpList.testKnown}';
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
	
	if(document.getElementById("GE1"))
	{	
	if('${PatientOpList.height}'!='NA' && '${PatientOpList.height}'!=''){
		document.forms[0].GE1.value='${PatientOpList.height}';
		heightVar='${PatientOpList.height}';
	}
	}
	if('${PatientOpList.weight}'!='NA' && '${PatientOpList.weight}'!=''){
		document.forms[0].GE2.value='${PatientOpList.weight}';
		weightVar='${PatientOpList.weight}';
		}
	if(document.getElementById("GE3"))
	{
	if('${PatientOpList.bmi}'!='NA' && '${PatientOpList.bmi}'!='')
		document.forms[0].GE3.value='${PatientOpList.bmi}';
	}
	if('${PatientOpList.pallor}'!=''){
			if('${PatientOpList.pallor}'=='Y')
	      	  document.forms[0].GE4[0].checked='checked';
	        else  if('${PatientOpList.pallor}'=='N')
	      	  document.forms[0].GE4[1].checked='checked';
		}
	if('${PatientOpList.cyanosis}'!=''){
		if('${PatientOpList.cyanosis}'=='Y')
      	  document.forms[0].GE5[0].checked='checked';
        else  if('${PatientOpList.cyanosis}'=='N')
      	  document.forms[0].GE5[1].checked='checked';
	}
	if('${PatientOpList.clubbingOfFingers}'!=''){
		if('${PatientOpList.clubbingOfFingers}'=='Y')
      	  document.forms[0].GE6[0].checked='checked';
        else  if('${PatientOpList.clubbingOfFingers}'=='N')
      	  document.forms[0].GE6[1].checked='checked';
	}
	if(document.getElementById("GE7"))
	{
	if('${PatientOpList.lymphadenopathy}'!=''){
		if('${PatientOpList.lymphadenopathy}'=='Y')
      	  document.forms[0].GE7[0].checked='checked';
        else  if('${PatientOpList.lymphadenopathy}'=='N')
      	  document.forms[0].GE7[1].checked='checked';
	}
	}
	if('${PatientOpList.edema}'!=''){
		if('${PatientOpList.edema}'=='Y')
      	  document.forms[0].GE8[0].checked='checked';
        else  if('${PatientOpList.edema}'=='N')
      	  document.forms[0].GE8[1].checked='checked';
	}
	if(document.getElementById("GE9"))
	{
	if('${PatientOpList.malNutrition}'!=''){
		if('${PatientOpList.malNutrition}'=='Y')
      	  document.forms[0].GE9[0].checked='checked';
        else  if('${PatientOpList.malNutrition}'=='N')
      	  document.forms[0].GE9[1].checked='checked';
	}
	}
	if(document.getElementById("GE10"))
	{
	if('${PatientOpList.dehydration}'!=''){
		if('${PatientOpList.dehydration}'=='Y'){
      	  document.forms[0].GE10[0].checked='checked';
      	  var examinField="<input type='radio' name='dehydrationY' id='dehydrationY' value='Mild' title='Mild'/>Mild<input type='radio'  name='dehydrationY' id='dehydrationY' value='Moderate' title='Moderate'/>Moderate<input type='radio'  name='dehydrationY' id='dehydrationY' value='Severe' title='Severe'/>Severe";
	      document.getElementById("DehydrationSub").innerHTML=examinField;
      	  
      	  if('${PatientOpList.dehydrationType}'!=''){
      		if('${PatientOpList.dehydrationType}'=='Mild')
      		document.forms[0].dehydrationY[0].checked='checked';
      		if('${PatientOpList.dehydrationType}'=='Moderate')
      		document.forms[0].dehydrationY[1].checked='checked';
      		if('${PatientOpList.dehydrationType}'=='Severe')
      		document.forms[0].dehydrationY[2].checked='checked';
          }
		}
        else  if('${PatientOpList.dehydration}'=='N')
      	  document.forms[0].GE10[1].checked='checked';
	}}	
	if('${PatientOpList.temperature}'!='NA' && '${PatientOpList.temperature}'!=''){
		var temp = '${PatientOpList.temperature}';
		var tempType = temp.charAt(temp.length - 1);
		temp = temp.slice(0,str.length-1);
		document.forms[0].GE11.value=temp;	
		
		/* if(tempType=="C"){
			document.forms[0].temp[0].checked='checked';
			}
		else if(tempType=="F")
			document.forms[0].temp[1].checked='checked';	 */		
	}	
	if('${PatientOpList.pulseRate}'!='NA' && '${PatientOpList.pulseRate}'!='')
		document.forms[0].GE12.value='${PatientOpList.pulseRate}';
	if('${PatientOpList.respirationRate}'!='NA' && '${PatientOpList.respirationRate}'!='')
		document.forms[0].GE13.value='${PatientOpList.respirationRate}';
	if('${PatientOpList.bpLmt}'!='NA' && '${PatientOpList.bpLmt}'!=''){
		
		var bpLmt='${PatientOpList.bpLmt}'.split("/");
		document.forms[0].GE14.value=bpLmt[0];
		document.forms[0].BP1.value=bpLmt[1];	
	}
	
	if('${PatientOpList.bpRmt}'!='NA' && '${PatientOpList.bpRmt}'!='' && '${PatientOpList.bpRmt}'!=null && '${PatientOpList.bpRmt}'!='/')
		{var bpRmt='${PatientOpList.bpRmt}'.split("/");
		
		if(document.getElementById("GE15"))
		{
		document.forms[0].GE15.value=bpRmt[0];
		document.forms[0].BP2.value=bpRmt[1];	}}
	else
	{
		var schemeId='${schemeId}';
		if(schemeId=='CD202')
		{if(document.getElementById("GE15ROW"))
			document.getElementById("GE15ROW").style.display="none";
		}
	}	
		if('${symptomsList}'!=null && '${symptomsLst}'!='')
		{
			document.getElementById("symptomsTable").style.display="";
			symptoms='${symptomsLst}'.split("#");
			symptomCount=symptoms.length-1;
		}
		
		if('${genInvestFlag}'!=null && '${genInvestFlag}'!=''){
		document.getElementById("genTestTableID").style.display="";
		genOldList='${genInvestLst}'.split("@");
		genOldList.splice(genOldList.length-1,1);
		if(genOldList.length>0)
			document.forms[0].patientType[1].disabled=false;
		/*genInventCount=genInventList.length-1;*/
		}
		
		if('${PatientOpList.ipOpFlag}'!=null){
			if('${PatientOpList.ipOpFlag}'=='IP'){
			document.forms[0].patientType[1].disabled=false;
			document.forms[0].patientType[1].checked=true;
			document.getElementById('IPHead2').style.display='';
			document.getElementById("diagnosisData").style.display="";
			getDiagDtlsOnload();
				/*setTimeout(function(){fn_getIPDoctorsList();}, 1999);}
			*/
			
			if('${invetLst}'!='')
    		{
    			document.getElementById("categoryTableID").style.display="";
    			specOld='${invetLst}'.split("@");
    			specOld.splice(specOld.length-1,1);    			
    			if(specOld.length>0){
        			
                var specialApprovalFlag="${dentalSpecialAppvFlag}";
               
        			
                    for(var i=0;i<specOld.length;i++){
                    	var inSpec = specOld[i].split("~");
                    	medOrSur=inSpec[0].substr(0,1);
                    }
    				
        		}  
    			/*catCount=spec.length-1;*/
    		}
			}
	        else if ('${PatientOpList.ipOpFlag}'=='OP' || '${PatientOpList.ipOpFlag}'=='IPM')
	        	{document.getElementById("OPHead2").style.display="";
	    		document.getElementById("prescriptionData").style.display="";
	    		document.getElementById("OPDoctor").style.display="";
	    		document.getElementById("diagnosisData").style.display="";

	    		//document.getElementById('IPHead2').style.display='none';
				//document.getElementById("diagnosisData").style.display="none";
	    		
	    		//getDiagDtlsOnload();
	    		/*if ('${PatientOpList.docType}'!=null){
					setTimeout(function(){fn_getDoctorsList();}, 1999);}*/
	    		/*if('${drugsLst}'!='')
	    		{
	    			document.getElementById("drugsTable").style.display="";
	    			drugs='${drugsLst}'.split("#");
	    			drugCount=drugs.length-1;
	    		}*/	    		
	    		}
		}
function getDiagDtlsOnload()
{
	/*if('${PatientOpList.diagnosisType}'!=null && '${PatientOpList.diagnosisType}'!='' && '${PatientOpList.diagnosisType}'!='-1')
	{
		getDiagnMainCatList();
		setTimeout(function()
		{		                                                     		  	           
			if('${PatientOpList.mainCatName}'!=null && '${PatientOpList.mainCatName}'!='' && '${PatientOpList.mainCatName}'!='-1')
			{
				document.getElementById("mainCatCode").value='${PatientOpList.mainCatName}';
			    document.forms[0].mainCatName.value='${PatientOpList.mainCatName}';
			    getDiagnCategoryList();
			    setTimeout(function()
				{
					if('${PatientOpList.catId}'!=null && '${PatientOpList.catId}'!='' && '${PatientOpList.catId}'!='-1')
					{
						document.getElementById("catCode").value='${PatientOpList.catId}';
				    	document.forms[0].catName.value='${PatientOpList.catId}';
				    	getDiagnSubCategoryList();
				    	setTimeout(function()
						{
							if('${PatientOpList.subCatName}'!=null && '${PatientOpList.subCatName}'!='' && '${PatientOpList.subCatName}'!='-1')
							{
								document.getElementById("subCatCode").value='${PatientOpList.subCatName}';
								document.forms[0].subCatName.value='${PatientOpList.subCatName}';
								getDiagnDiseaseList();
								setTimeout(function()
								{
									if('${PatientOpList.disMainId}'!=null && '${PatientOpList.disMainId}'!='')
									{
										document.getElementById("diseaseCode").value='${PatientOpList.disMainId}';
										document.forms[0].diseaseName.value='${PatientOpList.disMainId}';
										getDiagnDisAnatomicalList();
									}
									setTimeout(function()
									{
										if('${PatientOpList.disAnatomicalSitename}'!=null && '${PatientOpList.disAnatomicalSitename}'!=''&& '${PatientOpList.disAnatomicalSitename}'!='-1')
										{
											document.getElementById("disAnatomicalCode").value='${PatientOpList.disAnatomicalSitename}';
											document.forms[0].disAnatomicalName.value='${PatientOpList.disAnatomicalSitename}';
										}
									},1999);
								},1999);
							}
						}, 1999);
					   }
			    }, 1999);
			}
		}, 1999);
	}*/	
}
enable_legalCase('onload');
enable_dtrsform();

</script>

<script>
$("#genBlockInvestName").select2();
$("#genInvestigations").select2();
$("#drugNameAuto").select2();
$("#disNameAuto").select2();


</script>
<%--</logic:equal>--%>

</table>
</form>
</div>
</body>
</fmt:bundle>
</html>