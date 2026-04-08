<%
/**
 * PACKAGE        : Patient
 * FILENAME       : HighEndProformaForm.jsp
 * FUNCTIONALITY  : Screen for HighEnd Oncology Proforma Form
 * @AUTHOR        : Sri Hari
 * DATE           : 12/10/24
 * Calls to other jsp/java  : 
 * Called by other jsp/java : 
 *-------------------------------------------------------------------------------------------------------------------------------------
 *   CR ID        DATE             AUTHOR                                       DESCRIPTION
 * ------------------------------------------------------------------------------------------------------------------------------------
 **/
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.List,com.ahct.common.vo.LabelBean"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
     <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
  	 <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
	 <%@ include file="/common/include.jsp"%> 
	 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Proforma for the prescription of the target therapy and monoclonal antibodies</title>
  
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">

<link href="css/select2.min.css" rel="stylesheet">
<LINK href="./css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<LINK href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" media="screen">
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootbox.min.js"></script>
<script src="js/select2.min.js"></script>

  <style type="text/css">
  /* Additional custom styles can be added here */
    .radio-container label {
      margin-right: 20px;
    }
    .checkbox-row label {
      margin-right: 15px;
    }
.btn
{
border-radius:20px;
}
.modal-header
{
background-color:#0286AD;
}
.btn:hover
{
border-radius:5px;
}

*{margin:0;padding:0;}

 input:focus 
{
  outline:#000 dotted 1px; 
}    
select:focus
{
  outline:#000 dotted 1px; 
} 
radio:focus
{
  outline:#000 dotted 1px; 
}
textarea:focus
{
  outline:#000 dotted 1px; 
}
checkbox:focus
{
  outline:#000 dotted 1px; 
}
.readonly { background-color: #f0f0f0; pointer-events: none; }
</style>

<style type="text/css">
*{margin:0;padding:0;}

 input:focus 
{
  outline:#000 dotted 1px; 
}  
.bootbox-close-button
{
display:none;
}  
select:focus
{
  outline:#000 dotted 1px; 
} 
radio:focus
{
  outline:#000 dotted 1px; 
}
textarea:focus
{
  outline:#000 dotted 1px; 
}
checkbox:focus
{
  outline:#000 dotted 1px; 
}
body {
font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
font-size: 12px;
line-height: 1.42857143;
color: #333;
background-color: #fff;
}
.col-xs-2, .col-sm-2, .col-md-2, .col-lg-2{
padding-right: 10px;
}

</style>
<script>
if('${lResMap.Msg}' =='SUCCESS' ){
	 alert("Proforma Form Saved Successfully");
	// $('#proformaModal').modal('hide');
	var proformaId = '${lResMap.proformaId}';
	parent.document.getElementById("highEndProformaSubmitFlag").value="Y";
	parent.document.getElementById("highEndProformaId").value=proformaId;
	var url="patientDetails.do?actionFlag=getProformaFormHighEnd&proformaId="+proformaId;
	parent.document.getElementById("proformaFrame").src = url;
}
</script>
</head>
<body onload="fn_removeLoadingImage();">
  <div class="container mt-5">
    <html:form action="/patientDetails.do" method="post" enctype="multipart/form-data">
    <c:choose>
    <c:when test="${lResMap.formSubmit eq 'Y' || lResMap.formSubmit eq 'P'}">
    <c:set var="ehfOncoMed" value="${lResMap.ehfOncologyMedical}"/>
    <c:set var="isReadOnly" value="${lResMap.formSubmit eq 'Y'}" />
	<c:set var="isDisabled" value="${lResMap.formSubmit eq 'Y'}" />
	<c:set var="readOnlyClass" value="${isReadOnly ? 'readonly' : ''}" />
	<c:set var="savedMolecular" value="${ehfOncoMed.molecularMarkers}"/>
	<c:set var="allMarkers" value="er,pr,her2neu,egfr,alk,ros,ret,met,braf,kras,nras,pik3ca,brca,hrd,pdl1,msi,tmb,jak2,bcrabl,irma,ngs,others" />
	<div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
	  <div class="row">
	  <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
        <label class="labelheading1">Name of the patient: &nbsp;<font color="red">*</font></label>
		    <input type="text" class="form-control readonly" id="patientName" name="patientName" value="${ehfOncoMed.patientName}">
      </div>
      
      <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
        <label class="labelheading1">EHS/EJHS Card No: &nbsp;<font color="red">*</font></label>
        <input type="text" class="form-control readonly" id="cardNo" name="cardNo" value="${ehfOncoMed.cardNo}">
      </div>
      
      <div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
        <label class="labelheading1">Hospital Registration No: &nbsp;<font color="red">*</font></label>
        <input type="text" class="form-control readonly" id="hospitalRegNo" name="hospitalRegNo" value="${ehfOncoMed.regNo}" >
      </div>
      </div>
      <div class="form-group">
	  <div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <label class="labelheading1">Diagnosis: &nbsp;<font color="red">*</font></label>
        <input type="text" class="form-control ${readOnlyClass}" id="diagnosis" name="diagnosis" value="${ehfOncoMed.diagnosis}" >
      </div>
      </div>
	  <div class="row">
      <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <label class="labelheading1">Stage: &nbsp;</label><br>
          <input class="form-check-input" type="radio" name="stage" id="stage1" value="StageI" ${ehfOncoMed.stage eq 'StageI' ? 'checked' : ''} ${isDisabled ? 'disabled' : ''}>
          <label class="form-check-label" for="stage1">Stage I</label>
          <input class="form-check-input" type="radio" name="stage" id="stage2" value="StageII" ${ehfOncoMed.stage eq 'StageII' ? 'checked' : ''} ${isDisabled ? 'disabled' : ''}>
          <label class="form-check-label" for="stage2">Stage II</label>
         <input class="form-check-input" type="radio" name="stage" id="stage3" value="StageIII" ${ehfOncoMed.stage eq 'StageIII' ? 'checked' : ''} ${isDisabled ? 'disabled' : ''}>
          <label class="form-check-label" for="stage3">Stage III</label>
          <input class="form-check-input" type="radio" name="stage" id="stage4" value="StageIV" ${ehfOncoMed.stage eq 'StageIV' ? 'checked' : ''} ${isDisabled ? 'disabled' : ''}>
          <label class="form-check-label" for="stage4">Stage IV</label>
      </div>
	  </div>
	  </div>

      <div class="row">
        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
        <label class="labelheading1" style="margin-top: 10px;">Organs: &nbsp;<font color="red">*</font></label>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">        
        <%-- <input type="text" class="form-control readonly" id="organ" name="organ" value="${ehfOncoMed.organName}" > --%>
        <select class="form-control" id="organ" name="organId" ${isDisabled ? 'disabled' : 'onchange="getMolecules(this.value)"'}>
        <option value="-1">--Select--</option>
	        <c:forEach items="${lResMap.organsList}" var="organ" varStatus="i">
	            <option value="${organ.ID}" ${ehfOncoMed.organId eq organ.ID ? 'selected' : ''}>${organ.NAME}</option>
	        </c:forEach>
        </select>
      </div>
      <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"></div>
      <input type="hidden" id="organIdInput" name="organId" value="${ehfOncoMed.organId}">
      <input type="hidden" id="organNameInput" name="organName" value="${ehfOncoMed.organName}">
      </div>
      <br>
      
<div class="form-group">
    <label class="labelheading1">Molecular Markers: &nbsp;<font color="red">*</font></label>
     <div class="row g-2">
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="er" styleId="er" disabled="true"/>
                    <label class="form-check-label" for="er">ER</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="pr" styleId="pr" disabled="true"/>
                    <label class="form-check-label" for="pr">PR</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="her2neu" styleId="her2neu" disabled="true"/>
                    <label class="form-check-label" for="her2neu">Her2 Neu</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="egfr" styleId="egfr" disabled="true"/>
                    <label class="form-check-label" for="egfr">EGFR</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="alk" styleId="alk" disabled="true"/>
                    <label class="form-check-label" for="alk">ALK</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="ros" styleId="ros" disabled="true"/>
                    <label class="form-check-label" for="ros">ROS</label>
                </div>
            </div>
        </div>
        <div class="row g-2 mt-2">
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="ret" styleId="ret" disabled="true"/>
                    <label class="form-check-label" for="ret">RET</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="met" styleId="met" disabled="true"/>
                    <label class="form-check-label" for="met">MET</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="braf" styleId="braf" disabled="true"/>
                    <label class="form-check-label" for="braf">BRAF</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="kras" styleId="kras" disabled="true"/>
                    <label class="form-check-label" for="kras">KRAS</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="nras" styleId="nras" disabled="true"/>
                    <label class="form-check-label" for="nras">NRAS</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="pik3ca" styleId="pik3ca" disabled="true"/>
                    <label class="form-check-label" for="pik3ca">PIK3CA</label>
                </div>
            </div>
        </div>
        <div class="row g-2 mt-2">
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="brca" styleId="brca" disabled="true"/>
                    <label class="form-check-label" for="brca">BRCA</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="hrd" styleId="hrd" disabled="true"/>
                    <label class="form-check-label" for="hrd">HRD</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="pdl1" styleId="pdl1" disabled="true"/>
                    <label class="form-check-label" for="pdl1">PDL1</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="msi" styleId="msi" disabled="true"/>
                    <label class="form-check-label" for="msi">MSI</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="tmb" styleId="tmb" disabled="true"/>
                    <label class="form-check-label" for="tmb">TMB</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="jak2" styleId="jak2" disabled="true"/>
                    <label class="form-check-label" for="jak2">JAK2</label>
                </div>
            </div>
        </div>
        <div class="row g-2 mt-2">
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="bcrabl" styleId="bcrabl" disabled="true"/>
                    <label class="form-check-label" for="bcrabl">BCR ABL</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="irma" styleId="irma" disabled="true"/>
                    <label class="form-check-label" for="irma">IRMA</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="ngs" styleId="ngs" disabled="true"/>
                    <label class="form-check-label" for="ngs">NGS</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="others" styleId="others" onclick="showDiv()" disabled="true"/>
                    <label class="form-check-label" for="others">Others</label>
                </div>
            </div>
        </div>
		</div>
		 <div class="form-group" id="othersDiv" style="display:none;">
            <label class="labelheading1">Remarks(It is mandatory to fill when others is selected): &nbsp;<font color="red">*</font></label>
            <textarea class="form-control ${readOnlyClass}" id="remarks" name="remarks" rows="4" onkeyup="validateInput(event);">${ehfOncoMed.remarks}</textarea>
        </div>
    
      <div class="form-group">
        <label class="labelheading1">Plan of Treatment: &nbsp;<font color="red">*</font></label>
        <c:if test="${lResMap.formSubmit eq 'P'}">
        	<input type="file" class="form-control-file" id="attachment" name="attachment" accept=".pdf,.doc,.docx,.txt">
        </c:if>
        <span style="cursor: pointer; color:blue;" onclick="viewDocs('${ehfOncoMed.attachmentPath}');">${ehfOncoMed.attachment}</span>
      </div>
      
      <div class="form-group">
        <div class="row">
        <div class="checkbox-row col-lg-4 col-md-4 col-sm-4 col-xs-4">
        <label class="labelheading1">Drug Availability in India: &nbsp;<font color="red">*</font></label>
        </div>
        <div class="checkbox-row col-lg-8 col-md-8 col-sm-8 col-xs-8">
        <div class="checkbox-row col-lg-3 col-md-3 col-sm-3 col-xs-3">
          <input class="form-check-input" type="radio" name="availability" id="generic" value="Generic" ${isDisabled ? 'disabled' : ''}>
          <label class="form-check-label" for="generic">Generic</label>
        </div>
        <div class="checkbox-row col-lg-4 col-md-4 col-sm-4 col-xs-4">
          <input class="form-check-input" type="radio" name="availability" id="biosimilar" value="Biosimilar" ${isDisabled ? 'disabled' : ''}>
          <label class="form-check-label" for="biosimilar">Biosimilar</label>
        </div>
        <div class="checkbox-row col-lg-5 col-md-5 col-sm-5 col-xs-5">
          <input class="form-check-input" type="radio" name="availability" id="alternative" value="Alternative Molecule" ${isDisabled ? 'disabled' : ''}>
          <label class="form-check-label" for="alternative">Alternative Molecule</label>
        </div>
        <div class="checkbox-row col-lg-5 col-md-5 col-sm-5 col-xs-5">
          <input class="form-check-input" type="radio" name="availability" id="inovative" value="Inovative" ${isDisabled ? 'disabled' : ''}>
          <label class="form-check-label" for="alternative">Inovative</label>
        </div>
      </div>
      </div>
      </div>
      <div class="form-group">
        <label class="labelheading1">Drug List: &nbsp;<font color="red">*</font></label>
        <table class="table table-bordered">
          <thead>
            <tr>
              <th>S No</th>
              <th>Drug Name</th>
              <th>Dose</th>
              <th>Frequency</th>
            </tr>
          </thead>
          <tbody>
		  <c:choose>
		  <c:when test="${lResMap.formSubmit eq 'Y'}">
				<c:forEach items="${lResMap.ehfOncologyDrugsList}" var="drug" varStatus="i">
				<tr>
				  <td>${i.index+1}</td>
				  <td>${drug.VALUE }</td>
				  <td><input type="text" name="dosage${i.index}" id="dosage${i.index}"  value="${drug.TYPE}" class="readonly"></td>
				  <td><input type="text" name="frequency${i.index}" id="frequency${i.index}" value="${drug.QUANTITY}" class="readonly"></td>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
			<c:forEach items="${medicalDrugList}" var="drug" varStatus="i">
            <tr>
                <td>${i.index + 1}.
                    <input class="form-check-input" type="checkbox" name="drugList" id="drugList${i.index}" value="${drug.ID}~${i.index}"
                           <c:forEach var="savedDrug" items="${lResMap.ehfOncologyDrugsList}">
                               <c:if test="${drug.ID == savedDrug.ID}">checked</c:if>
                           </c:forEach> />
                </td>
                <td>${drug.VALUE}</td>
                <td>
                    <input type="text" name="dosage${i.index}" id="dosage${i.index}" 
                           value="<c:forEach var='savedDrug' items='${lResMap.ehfOncologyDrugsList}'><c:if test='${drug.ID == savedDrug.ID}'>${fn:trim(savedDrug.TYPE)} </c:if></c:forEach>">
                </td>
                <td>
                    <input type="text" name="frequency${i.index}" id="frequency${i.index}" 
                           value="<c:forEach var='savedDrug' items='${lResMap.ehfOncologyDrugsList}'><c:if test='${drug.ID == savedDrug.ID}'>${fn:trim(savedDrug.QUANTITY)}</c:if></c:forEach>">
                </td>
            </tr>
	        </c:forEach>
	        </c:otherwise>
		</c:choose>
          </tbody>
        </table>
      </div>
      
      <div class="form-group">
        <div class="row">
        <div class="checkbox-row col-lg-4 col-md-4 col-sm-4 col-xs-4">
        <label class="labelheading1">Drug Approval by Authority DCGI (CDSCO) &nbsp;<font color="red">*</font></label>
        </div>
        <div class="checkbox-row col-lg-8 col-md-8 col-sm-8 col-xs-8">
        <div class="checkbox-row col-lg-3 col-md-3 col-sm-3 col-xs-3">
          <input class="form-check-input" type="radio" name="approvalAuthority" id="DrugApprovedDCGI" value="Yes" ${isDisabled ? 'disabled' : ''}>
          <label class="form-check-label" for="DrugApprovedDCGI">Yes</label>
        </div>
        <div class="checkbox-row col-lg-3 col-md-3 col-sm-3 col-xs-3">
          <input class="form-check-input" type="radio" name="approvalAuthority" id="DrugNotApprovedDCGI" value="No" ${isDisabled ? 'disabled' : ''}>
          <label class="form-check-label" for="DrugNotApprovedDCGI">No</label>
        </div>
      </div>
      </div>
      </div>
      
      <!-- <div class="form-group">
        <div class="row">
        <div class="checkbox-row col-lg-4 col-md-4 col-sm-4 col-xs-4">
        <label class="labelheading1">Drug Availability in India &nbsp;<font color="red">*</font></label><br>
       </div>
        <div class="checkbox-row col-lg-8 col-md-8 col-sm-8 col-xs-8">
        <div class="checkbox-row col-lg-3 col-md-3 col-sm-3 col-xs-3">
          <input class="form-check-input" type="radio" name="availabilityIndia" id="radioAvailableIndia" value="Yes" disabled>
          <label class="form-check-label" for="radioAvailableIndia">Yes</label>
        </div>
        <div class="checkbox-row col-lg-3 col-md-3 col-sm-3 col-xs-3">
          <input class="form-check-input" type="radio" name="availabilityIndia" id="radioNotAvailableIndia" value="No" disabled>
          <label class="form-check-label" for="radioNotAvailableIndia">No</label>
        </div>
      </div>
      </div>
      </div> -->
      
      <div class="form-group">
        <label class="labelheading1">Magnitude of Clinical Benefit Score ESMO &nbsp;</label><br>
         <div class="row">
        <div class="checkbox-row col-lg-4 col-md-4 col-sm-4 col-xs-4">
        <label class="labelheading1">Palliative Setting &nbsp;<font color="red">*</font></label><br>
         </div>
        <div class="checkbox-row col-lg-8 col-md-8 col-sm-8 col-xs-8">
        <div class="checkbox-row col-lg-3 col-md-3 col-sm-3 col-xs-3">
          <input class="form-check-input" type="radio" name="palliativeSetting" id="yes_radio" value="Yes" ${isDisabled ? 'disabled' : ''}>
          <label class="form-check-label" for="yes_radio">Yes</label>
         </div>
        <div class="checkbox-row col-lg-3 col-md-3 col-sm-3 col-xs-3">
          <input class="form-check-input" type="radio" name="palliativeSetting" id="no_radio" value="No" ${isDisabled ? 'disabled' : ''}>
          <label class="form-check-label" for="no_radio">No</label>
      	</div>
      </div>
      </div>
       <div class="row">
        <div class="checkbox-row col-lg-4 col-md-4 col-sm-4 col-xs-4">
        <label class="labelheading1">MCBS Grades&nbsp;<font color="red">*</font></label>
		</div>
        <div class="checkbox-row col-lg-8 col-md-8 col-sm-8 col-xs-8">
        <div class="checkbox-row col-lg-3 col-md-3 col-sm-3 col-xs-3">
          <input class="form-check-input" type="radio" name="mcbsGrade" id="grade_5" value="5" ${isDisabled ? 'disabled' : ''}>
          <label class="form-check-label" for="grade_5">5</label>
        </div>
        <div class="checkbox-row col-lg-3 col-md-3 col-sm-3 col-xs-3">
          <input class="form-check-input" type="radio" name="mcbsGrade" id="grade_4" value="4" ${isDisabled ? 'disabled' : ''}>
          <label class="form-check-label" for="grade_4">4</label>
        </div>
        <div class="checkbox-row col-lg-3 col-md-3 col-sm-3 col-xs-3">
          <input class="form-check-input" type="radio" name="mcbsGrade" id="grade_3" value="3" ${isDisabled ? 'disabled' : ''}>
          <label class="form-check-label" for="grade_3">3</label>
        </div>
      </div>
      </div>
   	</div>

      
      <div class="form-group">
        <label class="labelheading1">Evaluation Form: &nbsp;</label><br>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="radio" name="section" id="section2A" value="2A" ${isDisabled ? 'disabled' : ''}>
          <label class="form-check-label" for="section2A">Section 2A</label>
        </div>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="radio" name="section" id="section2B" value="2B" ${isDisabled ? 'disabled' : ''}>
          <label class="form-check-label" for="section2B">Section 2B</label>
        </div>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="radio" name="section" id="section2C" value="2C" ${isDisabled ? 'disabled' : ''}>
          <label class="form-check-label" for="section2C">Section 2C</label>
        </div>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="radio" name="section" id="section3" value="3"  ${isDisabled ? 'disabled' : ''}>
          <label class="form-check-label" for="section3">Section 3</label>
        </div>
      </div>
      
      <div class="form-group">
      <div>
        <label class="labelheading1">Name of Doctor: &nbsp;<font color="red">*</font></label>
        <input type="text" class="form-control ${readOnlyClass}" id="doctorName" name="doctorName" value="${ehfOncoMed.doctorName}">
      </div>
      
      <div>
        <label class="labelheading1">Name of Superintendent: &nbsp;<font color="red">*</font></label>
        <input type="text" class="form-control ${readOnlyClass}" id="superintendentName" name="superintendentName" value="${ehfOncoMed.superIndentName}">
      </div>
      </div>
      <c:if test="${lResMap.formSubmit eq 'P'}">
      	<input type="button" id='Submit' value="Submit" class="btn btn-primary" >
      </c:if>     	
      </div>
      </c:when>
      <c:otherwise>      
      <div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
	  <div class="row">
	  <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
        <label class="labelheading1">Name of the patient: &nbsp;<font color="red">*</font></label>
        <input type="text" class="form-control" id="patientName" name="patientName">
      </div>
      
      <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
        <label class="labelheading1">EHS/EJHS Card No: &nbsp;<font color="red">*</font></label>
        <input type="text" class="form-control" id="cardNo" name="cardNo">
      </div>
      
      <div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
        <label class="labelheading1">Hospital Registration No: &nbsp;<font color="red">*</font></label>
        <input type="text" class="form-control" id="hospitalRegNo" name="hospitalRegNo" onkeyup="validateInput(event)">
      </div>
      </div>
      <div class="form-group">
	  <div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <label class="labelheading1">Diagnosis: &nbsp;<font color="red">*</font></label>
      <input type="text" class="form-control" id="diagnosis" name="diagnosis" onkeyup="validateInput(event)">
      </div>
      </div>
	  <div class="row">
      <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <label class="labelheading1">Stage: &nbsp;</label><br>
          <input class="form-check-input" type="radio" name="stage" id="stage1" value="StageI">
          <label class="form-check-label" for="stage1">Stage I</label>
          <input class="form-check-input" type="radio" name="stage" id="stage2" value="StageII">
          <label class="form-check-label" for="stage2">Stage II</label>
         <input class="form-check-input" type="radio" name="stage" id="stage3" value="StageIII">
          <label class="form-check-label" for="stage3">Stage III</label>
          <input class="form-check-input" type="radio" name="stage" id="stage4" value="StageIV">
          <label class="form-check-label" for="stage4">Stage IV</label>
      </div>
	  </div>
	  </div>
      
      <div class="row">
        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
        <label class="labelheading1" style="margin-top: 10px;">Organs: &nbsp;<font color="red">*</font></label>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
        <select class="form-control" id="organ" name="organId" onchange="getMolecules(this.value)">
            <option value="-1">--Select--</option>
            <c:forEach items="${lResMap.organsList}" var="organ" varStatus="i">
            	<option value="${organ.ID}">${organ.NAME}</option>
            </c:forEach>
        </select>
        <input type="hidden" id="organIdInput" name="organId" value="">
    	<input type="hidden" id="organNameInput" name="organName" value="">
      </div>
      <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"></div>
      </div>
      <br>
      
<div class="form-group">
    <label class="labelheading1">Molecular Markers: &nbsp;<font color="red">*</font></label>
            <div class="row g-2">
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="er" styleId="er" disabled="true"/>
                    <label class="form-check-label" for="er">ER</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="pr" styleId="pr" disabled="true"/>
                    <label class="form-check-label" for="pr">PR</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="her2neu" styleId="her2neu" disabled="true"/>
                    <label class="form-check-label" for="her2neu">Her2 Neu</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="egfr" styleId="egfr" disabled="true"/>
                    <label class="form-check-label" for="egfr">EGFR</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="alk" styleId="alk" disabled="true"/>
                    <label class="form-check-label" for="alk">ALK</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="ros" styleId="ros" disabled="true"/>
                    <label class="form-check-label" for="ros">ROS</label>
                </div>
            </div>
        </div>
        <div class="row g-2 mt-2">
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="ret" styleId="ret" disabled="true"/>
                    <label class="form-check-label" for="ret">RET</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="met" styleId="met" disabled="true"/>
                    <label class="form-check-label" for="met">MET</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="braf" styleId="braf" disabled="true"/>
                    <label class="form-check-label" for="braf">BRAF</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="kras" styleId="kras" disabled="true"/>
                    <label class="form-check-label" for="kras">KRAS</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="nras" styleId="nras" disabled="true"/>
                    <label class="form-check-label" for="nras">NRAS</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="pik3ca" styleId="pik3ca" disabled="true"/>
                    <label class="form-check-label" for="pik3ca">PIK3CA</label>
                </div>
            </div>
        </div>
        <div class="row g-2 mt-2">
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="brca" styleId="brca" disabled="true"/>
                    <label class="form-check-label" for="brca">BRCA</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="hrd" styleId="hrd" disabled="true"/>
                    <label class="form-check-label" for="hrd">HRD</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="pdl1" styleId="pdl1" disabled="true"/>
                    <label class="form-check-label" for="pdl1">PDL1</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="msi" styleId="msi" disabled="true"/>
                    <label class="form-check-label" for="msi">MSI</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="tmb" styleId="tmb" disabled="true"/>
                    <label class="form-check-label" for="tmb">TMB</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="jak2" styleId="jak2" disabled="true"/>
                    <label class="form-check-label" for="jak2">JAK2</label>
                </div>
            </div>
        </div>
        <div class="row g-2 mt-2">
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="bcrabl" styleId="bcrabl" disabled="true"/>
                    <label class="form-check-label" for="bcrabl">BCR ABL</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="irma" styleId="irma" disabled="true"/>
                    <label class="form-check-label" for="irma">IRMA</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="ngs" styleId="ngs" disabled="true"/>
                    <label class="form-check-label" for="ngs">NGS</label>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="form-check">
                    <html:checkbox property="molecularMarkers" value="others" styleId="others" onclick="showDiv()" />
                    <label class="form-check-label" for="others">Others</label>
                </div>
            </div>
             
        </div>
        <div class="form-group" id="othersDiv" style="display:none;">
            <label class="labelheading1">Remarks(It is mandatory to fill when others is selected): &nbsp;<font color="red">*</font></label>
            <textarea class="form-control" id="remarks" name="remarks" rows="4" onkeyup="validateInput(event);"></textarea>
        </div>
</div>

      
      <div class="form-group">
        <label class="labelheading1">Plan of Treatment: &nbsp;<font color="red">*</font></label>
        <input type="file" class="form-control-file" id="attachment" name="attachment" accept=".pdf,.doc,.docx,.txt" onchange="validateFile(this)">
      </div>
      
      <div class="form-group">
        <div class="row">
        <div class="checkbox-row col-lg-4 col-md-4 col-sm-4 col-xs-4">
        <label class="labelheading1">Drug Availability in India: &nbsp;<font color="red">*</font></label>
        </div>
        <div class="checkbox-row col-lg-8 col-md-8 col-sm-8 col-xs-8">
        <div class="checkbox-row col-lg-3 col-md-3 col-sm-3 col-xs-3">
          <input class="form-check-input" type="radio" name="availability" id="generic" value="Generic">
          <label class="form-check-label" for="generic">Generic</label>
        </div>
        <div class="checkbox-row col-lg-4 col-md-4 col-sm-4 col-xs-4">
          <input class="form-check-input" type="radio" name="availability" id="biosimilar" value="Biosimilar">
          <label class="form-check-label" for="biosimilar">Biosimilar</label>
        </div>
        <div class="checkbox-row col-lg-5 col-md-5 col-sm-5 col-xs-5">
          <input class="form-check-input" type="radio" name="availability" id="alternative" value="Alternative Molecule">
          <label class="form-check-label" for="alternative">Alternative Molecule</label>
        </div>
        <div class="checkbox-row col-lg-5 col-md-5 col-sm-5 col-xs-5">
          <input class="form-check-input" type="radio" name="availability" id="inovative" value="Inovative">
          <label class="form-check-label" for="alternative">Inovative</label>
        </div>
      </div>
      </div>
      </div>
      <div class="form-group">
        <label class="labelheading1">Drug List: &nbsp;<font color="red">*</font></label>
        <table class="table table-bordered">
          <thead>
            <tr>
              <th>S No</th>
              <th>Drug Name</th>
              <th>Dose</th>
              <th>Frequency</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach items="${medicalDrugList}" var="drug" varStatus="i">
            <tr>
              <td>${i.index+1}.<input class="form-check-input" type="checkbox" name="drugList" id="drugList${i.index}" value="${drug.ID}~${i.index}"></td>
              <td>${drug.VALUE }</td>
              <td><input type="text" name="dosage${i.index}" id="dosage${i.index}" onkeyup="validateInput(event)"></td>
              <td><input type="text" name="frequency${i.index}" id="frequency${i.index}" onkeyup="validateInput(event)"></td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
      
      <div class="form-group">
        <div class="row">
        <div class="checkbox-row col-lg-4 col-md-4 col-sm-4 col-xs-4">
        <label class="labelheading1">Drug Approval by Authority DCGI (CDSCO) &nbsp;<font color="red">*</font></label>
        </div>
        <div class="checkbox-row col-lg-8 col-md-8 col-sm-8 col-xs-8">
        <div class="checkbox-row col-lg-3 col-md-3 col-sm-3 col-xs-3">
          <input class="form-check-input" type="radio" name="approvalAuthority" id="DrugApprovedDCGI" value="Yes">
          <label class="form-check-label" for="DrugApprovedDCGI">Yes</label>
        </div>
        <div class="checkbox-row col-lg-3 col-md-3 col-sm-3 col-xs-3">
          <input class="form-check-input" type="radio" name="approvalAuthority" id="DrugNotApprovedDCGI" value="No">
          <label class="form-check-label" for="DrugNotApprovedDCGI">No</label>
        </div>
      </div>
      </div>
      </div>
      
   <!--    <div class="form-group">
        <div class="row">
        <div class="checkbox-row col-lg-4 col-md-4 col-sm-4 col-xs-4">
        <label class="labelheading1">Drug Availability in India &nbsp;<font color="red">*</font></label><br>
       </div>
        <div class="checkbox-row col-lg-8 col-md-8 col-sm-8 col-xs-8">
        <div class="checkbox-row col-lg-3 col-md-3 col-sm-3 col-xs-3">
          <input class="form-check-input" type="radio" name="availabilityIndia" id="radioAvailableIndia" value="Yes">
          <label class="form-check-label" for="radioAvailableIndia">Yes</label>
        </div>
        <div class="checkbox-row col-lg-3 col-md-3 col-sm-3 col-xs-3">
          <input class="form-check-input" type="radio" name="availabilityIndia" id="radioNotAvailableIndia" value="No">
          <label class="form-check-label" for="radioNotAvailableIndia">No</label>
        </div>
      </div>
      </div>
      </div> -->
    
      <div class="form-group">
        <label class="labelheading1">Magnitude of Clinical Benefit Score ESMO &nbsp;<font color="red">*</font></label><br>
         <div class="row">
        <div class="checkbox-row col-lg-4 col-md-4 col-sm-4 col-xs-4">
        <label class="labelheading1">Palliative Setting</label><br>
         </div>
        <div class="checkbox-row col-lg-8 col-md-8 col-sm-8 col-xs-8">
        <div class="checkbox-row col-lg-3 col-md-3 col-sm-3 col-xs-3">
          <input class="form-check-input" type="radio" name="palliativeSetting" id="yes_radio" value="Yes">
          <label class="form-check-label" for="yes_radio">Yes</label>
         </div>
        <div class="checkbox-row col-lg-3 col-md-3 col-sm-3 col-xs-3">
          <input class="form-check-input" type="radio" name="palliativeSetting" id="no_radio" value="No">
          <label class="form-check-label" for="no_radio">No</label>
      	</div>
      </div>
      </div>
       <div class="row">
        <div class="checkbox-row col-lg-4 col-md-4 col-sm-4 col-xs-4">
        <label class="labelheading1">MCBS Grades&nbsp;<font color="red">*</font></label>
		</div>
        <div class="checkbox-row col-lg-8 col-md-8 col-sm-8 col-xs-8">
        <div class="checkbox-row col-lg-3 col-md-3 col-sm-3 col-xs-3">
          <input class="form-check-input" type="radio" name="mcbsGrade" id="grade_5" value="5">
          <label class="form-check-label" for="grade_5">5</label>
        </div>
        <div class="checkbox-row col-lg-3 col-md-3 col-sm-3 col-xs-3">
          <input class="form-check-input" type="radio" name="mcbsGrade" id="grade_4" value="4">
          <label class="form-check-label" for="grade_4">4</label>
        </div>
        <div class="checkbox-row col-lg-3 col-md-3 col-sm-3 col-xs-3">
          <input class="form-check-input" type="radio" name="mcbsGrade" id="grade_3" value="3">
          <label class="form-check-label" for="grade_3">3</label>
        </div>
      </div>
      </div>
   	</div>

      
      <div class="form-group">
        <label class="labelheading1">Evaluation Form: &nbsp;</label><br>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="radio" name="section" id="section2A" value="2A">
          <label class="form-check-label" for="section2A">Section 2A</label>
        </div>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="radio" name="section" id="section2B" value="2B">
          <label class="form-check-label" for="section2B">Section 2B</label>
        </div>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="radio" name="section" id="section2C" value="2C">
          <label class="form-check-label" for="section2C">Section 2C</label>
        </div>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="radio" name="section" id="section3" value="3">
          <label class="form-check-label" for="section3">Section 3</label>
        </div>
      </div>
      
      <div class="form-group">
      <div>
        <label class="labelheading1">Name of Doctor: &nbsp;<font color="red">*</font></label>
        <input type="text" class="form-control" id="doctorName" name="doctorName" onkeyup="validateInput(event)">
      </div>
      
      <div>
        <label class="labelheading1">Name of Director/Superintendent: &nbsp;<font color="red">*</font></label>
        <input type="text" class="form-control" id="superintendentName" name="superintendentName" onkeyup="validateInput(event)">
      </div>
      </div>
      <input type="button" id='Submit' value="Submit" class="btn btn-primary">
      </div>
      </c:otherwise>
      </c:choose>
      
      <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="width: 135%;margin-left: -18%;">
	  <div class="modal-dialog modal-lg" role="document">
	 <!-- <span class="close" onclick="closeModal();" style="margin-top:-2%; color:red; opacity:10">&times;</span> -->
	 <span class="close" onclick="closeModal();" style="margin-top: 0%; color: red; opacity: 1; z-index: 1000; position: relative;">&times;</span>
	    <div class="modal-content" id="modal-content"style="">
	    	<!-- <span class="close" onclick="closeModal();" style="margin-top:0%; color:red; opacity:10", margin-right:2%>&times;</span> -->
	      <div class="modal-body" id="viewDocuments" style="max-height:100%;">
	      
	      </div>
	   	 </div>
 		</div>
	</div>
<html:hidden name="patientForm" property="proformaId" styleId="proformaId" value="${lResMap.proformaId}"/>
	<!-- Progress Bar -->
<div id="processImagetable" style="top:50%;position:absolute;z-index:60;height:100%">
<table border="0" align="center" width="100%" style="height:400" >
   <tr>
      <td>
        <div id="processImage" align="center">
          <img src="images/Progress.gif" width="100" height="100" border="0"></img>
         </div>
       </td>
     </tr>
  </table>
</div>
    </html:form>
  </div>
  <script type="text/javascript">
  function radioSelect(name,value){
	  var radioButtons = document.getElementsByName(name);
      for (var i = 0; i < radioButtons.length; i++) {
          if (radioButtons[i].value === value) {
              radioButtons[i].checked = true;
              break;
          }
      }
  }
  function setCheckboxValues(name, values, checked, flag) {
      var checkboxes = document.getElementsByName(name);
      
      for (var i = 0; i < checkboxes.length; i++) {
          if (values.includes(checkboxes[i].value)) {
              checkboxes[i].checked = checked;
              if(flag == 'P')
              	checkboxes[i].disabled = false;
          }
      }
  }
function fn_loadImage(){
	document.getElementById('processImagetable').style.display="";
}
function fn_removeLoadingImage(){
	document.getElementById('processImagetable').style.display="none";  
}
  $(document).ready(function() {
	  if('${lResMap.formSubmit}'=='Y' || '${lResMap.formSubmit}'=='P'){
		  radioSelect('stage','${ehfOncoMed.stage}');
		  radioSelect('availability','${ehfOncoMed.availability}');
		  radioSelect('approvalAuthority','${ehfOncoMed.approvalAuthority}');
		  //radioSelect('availabilityIndia','${ehfOncoMed.availabilityIndia}');
		  radioSelect('palliativeSetting','${ehfOncoMed.palliativeSetting}');
		  radioSelect('mcbsGrade','${ehfOncoMed.mcbsGrade}');
		  radioSelect('section','${ehfOncoMed.section}');
		  setCheckboxValues('molecularMarkers','${ehfOncoMed.molecularMarkers}'.split(","),'checked', '${lResMap.formSubmit}');
		  if('${lResMap.formSubmit}'=='P')
			  document.getElementById('others').disabled = false;
		  if(document.getElementById('others').checked)
			  showDiv();
	  }else{
		  $('#patientName').val(parent.document.getElementById('fname').value);
		  $('#cardNo').val(parent.document.getElementById('cardNo').value);
		  $('#hospitalRegNo').val(parent.document.getElementById('hospitalId').value);
	  }
	    $("#Submit").on("click", function(event) {
	      // Prevent form submission to handle validation
	      event.preventDefault();
	      
	      // Initialize error messages array
	      var errors = [];

	      // Validate patient name
	      if ($("#patientName").val().trim() === "") {
	        errors.push("Patient Name is required.");
	      }

	      // Validate card No
	      if ($("#cardNo").val().trim() === "") {
	        errors.push("Card No is required.");
	      }

	      // Validate Hospital Registration No
	      if ($("#hospitalRegNo").val().trim() === "") {
	        errors.push("Hospital Registration No is required.");
	      }

	      // Validate Diagnosis
	      if ($("#diagnosis").val().trim() === "") {
	        errors.push("Diagnosis is required.");
	      }

	      // Validate Stage
	      /* if ($('input[name="stage"]:checked').length === 0) {
	        errors.push("Stage is required.");
	      } */

	      // Validate Molecular Markers
	      if ($('input[name="molecularMarkers"]:checked').length === 0) {
	        errors.push("At least one Molecular Marker must be selected.");
	      }
	      
	      if ($('#others').prop('checked') == true && $("#remarks").val().trim() === ""){
	           errors.push("Remarks are required when 'Others' is selected.");	            
	        }
	        
	      // Validate Attachment
	      if ($("#attachment").val().trim() === "") {
	    	  if('${lResMap.formSubmit}'!= 'P')
	    		  errors.push("Plan of Treatment (Attachment) is required.");
	      }

	      // Validate Availability
	      if ($('input[name="availability"]:checked').length === 0) {
	        errors.push("Drug Availability in India is required.");
	      }
	      
	      // Validate Drug List
	      var drugListChecked = false;
	      $('input[name="drugList"]').each(function(index) {
	        if ($(this).is(":checked")) {
	          drugListChecked = true;
	          var dosage = $("#dosage"+index).val().trim();
	          var frequency = $("#frequency"+index).val().trim();
	          if (dosage === "") {
	            errors.push("Dosage is required for selected drug.");
	          }
	          if (frequency === "") {
	            errors.push("Frequency is required for selected drug.");
	          }
	        }
	      });
	      if (!drugListChecked) {
	        errors.push("At least one drug must be selected from the Drug List.");
	      }

	      // Validate Drug Approval by Authority DCGI
	      if ($('input[name="approvalAuthority"]:checked').length === 0) {
	        errors.push("Drug Approval by Authority DCGI is required.");
	      }
	      
	  	  // Validate Evaluation Form 
	      /* if ($('input[name="section"]:checked').length === 0) {
	        errors.push("Evaluation Form is required.");
	      } */
	      
	      // Validate Palliative Setting
	      if ($('input[name="palliativeSetting"]:checked').length === 0) {
	        errors.push("Palliative Setting is required.");
	      }
	   // Validate MCBS Grade
	      if ($('input[name="mcbsGrade"]:checked').length === 0) {
	        errors.push("MCBS Grade is required.");
	      }

	   // Validate Doctor name
	      if ($("#doctorName").val().trim() === "") {
	        errors.push("Doctor Name is required.");
	      }
	   // Validate Superintendent name
	      if ($("#superintendentName").val().trim() === "") {
	        errors.push("Superintendent Name is required.");
	      }
	   
	  	 if($("#organ").val() == "-1"){
	  		 errors.push("Please select the Organ");
	  	 }
	      // Display errors
	      if (errors.length > 0) {
	        alert(errors.join("\n"));
	      } else {
	        // Submit the form if no errors    
    		if (confirm("Are you sure you want to submit the Proforma Form?")) {
		        fn_loadImage();
		        var cardNo = parent.document.getElementById("cardNo").value;
		        var url = "patientDetails.do?actionFlag=saveProformaFormHighEnd&cardNo="+cardNo;
		        $("form").attr("action", url).attr("method", "post").submit();	      
	        }else{
	    	   return true;
	        }
	      }
	    });
	  });

  function getMolecules(selectedOrgan) {
	  	const organSelect = document.getElementById('organ');
	    const organName = organSelect.options[organSelect.selectedIndex].text;

	    // Set both organId and organName in hidden fields or directly to form inputs
	    document.getElementById('organIdInput').value = selectedOrgan;
	    document.getElementById('organNameInput').value = organName;
	    $.ajax({
	        url: '/Operations/patientDetails.do?actionFlag=getMoleculesForOrgan', 
	        type: 'GET',
	        data: { organId: selectedOrgan },
	        success: function(response) {
	            const molecules = JSON.parse(response); 
	            const moleculesName = molecules.map(function(molecule) {
	                return molecule.NAME;
	            });
	            // All available checkbox values
	            const allMolecules = [
	                'er', 'pr', 'her2neu', 'egfr', 'alk', 'ros', 
	                'ret', 'met', 'braf', 'kras', 'nras', 'pik3ca', 
	                'brca', 'hrd', 'pdl1', 'msi', 'tmb', 'jak2', 
	                'bcrabl', 'irma', 'ngs'
	            ];

	            allMolecules.forEach(function(molecule) {
	                const checkbox = document.getElementById(molecule);

	                if (checkbox) {
	                    if (moleculesName.includes(molecule)) {
	                        checkbox.checked = true;
	                        checkbox.disabled = false;
	                    } 
	                    else {
	                        checkbox.checked = false;
	                        checkbox.disabled = true;
	                    }
	                }
	            });
	        },
	        error: function(xhr, status, error) {
	            console.error('Error fetching molecular markers:', error);
	        }
	    });
	}

  function viewDocs(filePath) {	
	  	fn_loadImage();
	    const url = "/Operations/patientDetails.do?actionFlag=viewDocuments&filePath="+filePath;
	    var xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function() {
	      if (this.readyState == 4) {
	    	  fn_removeLoadingImage();
	    	  if(this.status == 200){
	    		var response = this.responseText;
	  	        var modalContent = document.getElementById("viewDocuments");
	  	        var fileExt = filePath.substr(filePath.lastIndexOf('.') + 1).toLowerCase();
	  	        if (fileExt === "pdf") {
	  	          modalContent.innerHTML = '<iframe src="data:application/pdf;base64,'+response+'" frameBorder="0" scrolling="auto" height="500px " width="100%" style="visibility: visible; height:680px"></iframe>';
	  	        } else if (fileExt === "jpg" || fileExt === "jpeg" || fileExt === "png" || fileExt === "gif") {
	  	          modalContent.innerHTML = '<img src="data:image/jpeg;base64,' + response + '" style="height:500px; width:100%">';
	  	        } else {
	  	          modalContent.innerHTML = '<p>Unsupported file type</p>';
	  	        }
	  	        $('#myModal').modal('show');
	    	  }else{
	    		  alert("Failed to load document. Please try again."); 
	    	  }
	      }
	    };
	    xhttp.open("GET", url, true);
	    xhttp.send();
	}

	function closeModal() {
		$('#myModal').modal('hide');
	}
	function validateFile(fileInput){
		if (!fileInput || fileInput.files.length === 0) {
	        return false;
	    }

	    const file = fileInput.files[0];
	    const allowedExtensions = ['pdf', 'doc', 'docx', 'jpg', 'jpeg', 'png'];
	    const fileSizeLimit = 2 * 1024 * 1024; // 2 MB

	    const fileName = file.name.toLowerCase();
	    const fileExtension = fileName.split('.').pop();
	    const fileSize = file.size;

	    if (!allowedExtensions.includes(fileExtension)) {
	    	bootbox.alert("Invalid file type. Only PDF, DOC, DOCX, JPG, JPEG, and PNG are allowed");
	    	fileInput.value = '';
	    	return;
	    }
	    
	    if (fileSize > fileSizeLimit) {
	    	bootbox.alert("File size exceeds the 2MB limit");
	    	fileInput.value = '';
	        return;
	    }
	    
	 	// Validate filename (only alphanumeric characters, underscores, hyphens, and periods allowed)
	    const fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
	    const validNameRegex = /^[a-zA-Z0-9_-]+$/;

	    if (!validNameRegex.test(fileNameWithoutExtension)) {
	        bootbox.alert("Invalid filename. Only letters, numbers, underscores, and hyphens are allowed. No special characters.");
	        fileInput.value = '';
	        return;
	    }	    
	}
	function validateInput(event) {
	    const inputField = event.target;
	    let value = inputField.value;

	    const regex = /^[a-zA-Z0-9/,.\s]*$/;

	    if (!regex.test(value)) {
	        inputField.value = value.slice(0, -1);
	    }
	}

  function showDiv(){
	  const checkbox = document.getElementById("others");
	  const othersDiv = document.getElementById("othersDiv");
	  
	  if(checkbox.checked){
		  othersDiv.style.display = 'block';
	  } 
	  else{
		  othersDiv.style.display = 'none';
		  document.getElementById("remarks").value='';
	  }
  }	

  </script>
</body>
</html>
