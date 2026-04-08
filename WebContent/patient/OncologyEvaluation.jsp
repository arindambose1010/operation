<%
/**
 * PACKAGE        : Patient
 * FILENAME       : OncologyEvaluation.jsp
 * FUNCTIONALITY  : Screen for HighEnd Oncology Evaluation Form
 * @AUTHOR        : Sri Hari
 * DATE           : 12/11/24
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
	 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Oncology Evolution Response</title>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">

<link href="css/select2.min.css" rel="stylesheet">
<LINK href="./css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<LINK href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css" />
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootbox.min.js"></script>
<script src="js/select2.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>

<style type="text/css">
  /* Additional custom styles can be added here */
.radio-container label { margin-right: 20px; }
.checkbox-row label { margin-right: 15px; }
.btn{ border-radius:20px; }
.modal-header{ background-color:#0286AD; }
.btn:hover{ border-radius:5px; }

*{margin:0;padding:0;}

input:focus {
  outline:#000 dotted 1px; 
}    
select:focus{
  outline:#000 dotted 1px; 
} 
radio:focus{ outline:#000 dotted 1px; }
textarea:focus{ outline:#000 dotted 1px; }
checkbox:focus{ outline:#000 dotted 1px; }
body { font-family: Arial, sans-serif; }
table { width: 100%; border-collapse: collapse; }
th, td { padding: 5px; }
th { background-color: #f2f2f2; text-align: left; }
.section-header { font-weight: bold; background-color: #e6e6e6; }
.file-upload { display: none !important; }
.file-upload.show {display: inline-block !important;}
.readonly { background-color: #f0f0f0; pointer-events: none; }
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
.label-column {
    width: 40%; 
    text-align: left; 
}
.result-column {
    width: 30%;
}
.attachment-column {
    width: 30%;
}
.rest-column {
    width: 40%;
}

.section-title {
    font-weight: bold;
    padding-top: 15px;
    padding-bottom: 5px;
    font-size: 1.1em;
    text-decoration: underline;
}
 table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
}

th, td {
    padding: 10px;
    text-align: left;
}
</style>
<script>
if('${lResMap.Msg}' =='SUCCESS' ){
	 alert("Evolution Form Saved Successfully");
	 parent.document.getElementById("highEndEvaluationSubmitFlag").value="Y";
	 var url="/Operations/patientDetails.do?actionFlag=getOncologyEvaluationResponse&patientId=${patientId}";
	 parent.document.getElementById("evaluationFrame").src = url;	 
}
</script>
</head>
<body onload="fn_removeLoadingImage();">
	<form action="/Operations/patientDetails.do" method="POST" enctype="multipart/form-data">
	<c:choose>
		<c:when test="${lResMap.isDataAvailable eq 'Y' || lResMap.isDataAvailable eq 'P'}">
			<c:set var="ehfOncoTreatMent" value="${lResMap.ehfOncologyTreatment}"/>
			<c:set var="isDisabled" value="${lResMap.isDataAvailable eq 'Y' ? 'disabled' : ''}" />
			<c:set var="readOnlyClass" value="${lResMap.isDataAvailable eq 'Y' ? 'readonly' : ''}" />
			<input type="hidden" class="form-control" id="patientNo" name="patientNo" value="${lResMap.patientNo}">
			<table>
            <tr>
                <th colspan="3">Recommended Treatment </th>
            </tr>
            <tr><td>Recommended Treatment &nbsp;</td><td><input type="text" id="recommendedTreatment" name="recommendedTreatment" class="${readOnlyClass}" value="${ehfOncoTreatMent.recommendedTreatment}" onkeyup="validateInput(event)"></td></tr>
            <tr><td>Dosage based on BSA &nbsp;</td><td><input type="text" id="dosageBSA" name="dosageBSA" class="${readOnlyClass}" value="${ehfOncoTreatMent.dosageBsa}" onkeyup="validateInput(event)"></td></tr>
            <tr><td>Weight &nbsp;</td><td><input type="number" id="weight" name="weight" class="${readOnlyClass}" value="${ehfOncoTreatMent.weight}" onkeyup="validateNum(event)"></td></tr>
            <tr><td>Height &nbsp;</td><td><input type="number" id="height" name="height" class="${readOnlyClass}" value="${ehfOncoTreatMent.height}" onkeyup="validateNum(event)"></td></tr>
            <tr><td>Body Surface Area &nbsp;</td><td><input type="number" id="bodySurfaceArea" name="bodySurfaceArea" class="${readOnlyClass}" value="${ehfOncoTreatMent.bsa}" onkeyup="validateNum(event)"></td></tr>
            <tr><td>Date of starting drug and dosage &nbsp;</td><td><input type="text" id="startDate" name="startDate" class="${readOnlyClass}" value="${lResMap.startDate}" onkeyup="validateInput(event)"></td></tr>

            <tr>
                <th colspan="3">Clinical Exam</th>
            </tr>
            <tr>
                <td>ECOG Performance Status &nbsp;</td>
                <td>
                    <c:forEach var="i" begin="0" end="5">
                    	<input type="radio" name="ecog" id="ecog${i}" value="${i}" <c:if test="${ehfOncoTreatMent.stage == i}"> checked </c:if>${isDisabled}>${i}
                	</c:forEach>
                </td>
            </tr>

            <tr class="section-header"><td colspan="3">Vital Data</td></tr>
            <tr><td>Temperature &nbsp;</td><td><input type="number" id="temperature" name="temperature" class="${readOnlyClass}" value="${ehfOncoTreatMent.temperature}" onkeyup="validateNum(event)"></td></tr>
            <tr><td>Pulse rate &nbsp;</td><td><input type="number" id="pulseRate" name="pulseRate" class="${readOnlyClass}" value="${ehfOncoTreatMent.pulseRate}" onkeyup="validateNum(event)"></td></tr>
            <tr><td>Respiratory rate &nbsp;</td><td><input type="number" id="respiratoryRate" name="respiratoryRate" class="${readOnlyClass}" value="${ehfOncoTreatMent.respiratoryRate}" onkeyup="validateNum(event)"></td></tr>
            <tr><td>SPO2 &nbsp;</td><td><input type="number" id="spo2" name="spo2" class="${readOnlyClass}" value="${ehfOncoTreatMent.spo2}" onkeyup="validateNum(event)"></td></tr>
            <tr><td>Blood Pressure &nbsp;</td><td><input type="text" id="bloodPressure" name="bloodPressure" class="${readOnlyClass}" value="${ehfOncoTreatMent.bloodPressure}" onkeyup="validateInput(event)"></td></tr>
            <tr><td>General Examination &nbsp;</td><td><input type="text" id="generalExamination"name="generalExamination" class="${readOnlyClass}" value="${ehfOncoTreatMent.generalExamination}" onkeyup="validateInput(event)"></td></tr>

            <tr class="section-header"><td colspan="3">Systemic Examination</td></tr>
            <tr><td>Cardio Vascular System &nbsp;</td><td><input type="text" id="cardioVascular" name="cardioVascular" class="${readOnlyClass}" value="${ehfOncoTreatMent.cardiovascularSystem}" onkeyup="validateInput(event)"></td></tr>
            <tr><td>Respiratory System &nbsp;</td><td><input type="text" id="respiratorySystem" name="respiratorySystem" class="${readOnlyClass}" value="${ehfOncoTreatMent.respiratorySystem}" onkeyup="validateInput(event)"></td></tr>
            <tr><td>Nervous System &nbsp;</td><td><input type="text" id="nervousSystem" name="nervousSystem" class="${readOnlyClass}" value="${ehfOncoTreatMent.nervousSystem}"></td></tr>
            <tr><td>Per Abdominal examination &nbsp;</td><td><input type="text" id="abdominalExam" name="abdominalExam" class="${readOnlyClass}" value="${ehfOncoTreatMent.abdominalExamination}" onkeyup="validateInput(event)"></td></tr>
            <tr><td>Musculo skeletal system &nbsp;</td><td><input type="text" id="musculoSkeletal" name="musculoSkeletal" class="${readOnlyClass}" value="${ehfOncoTreatMent.musculoskeletalSystem}" onkeyup="validateInput(event)"></td></tr>

            <tr class="section-header"><td colspan="3">Haematology Parameters</td></tr>
            <tr><td>CBP &nbsp;<font color="red">*</font></td><td><input type="text" id="cbp" name="cbp" class="${readOnlyClass}" value="${ehfOncoTreatMent.cbp}" onkeyup="validateInput(event)"></td></tr>
            <tr><td>Coagulation Profile &nbsp;</td><td><input type="text" id="coagulationProfile" name="coagulationProfile" class="${readOnlyClass}" value="${ehfOncoTreatMent.coagulationProfile}" onkeyup="validateInput(event)"></td></tr>
            <tr><td>PT &nbsp;</td><td><input type="text" id="pt" name="pt" class="${readOnlyClass}" value="${ehfOncoTreatMent.pt}" onkeyup="validateInput(event)"></td></tr>
            <tr><td>APTT &nbsp;</td><td><input type="text" id="aptt" name="aptt" class="${readOnlyClass}" value="${ehfOncoTreatMent.aptt}" onkeyup="validateInput(event)"></td></tr>
            <tr><td>INR &nbsp;</td><td><input type="text" id="inr" name="inr" class="${readOnlyClass}" value="${ehfOncoTreatMent.inr}" onkeyup="validateInput(event)"></td></tr>

            <tr>
                <td>Bone Marrow Exam &nbsp;</td>
                <td>
                    <input type="radio" name="boneMarrow" id="boneMarrowYes" value="Y" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.boneMarrowExam =='Y'}"> checked </c:if> ${isDisabled}> Yes
                    <input type="radio" name="boneMarrow" id="boneMarrowNo" value="N" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.boneMarrowExam =='N'}"> checked </c:if> ${isDisabled}> No                  
                </td>
                <td>
                	<c:choose>
                	<c:when test="${lResMap.isDataAvailable eq 'P'}">
                		<input type="file" id="boneMarrowFile" name="boneMarrowFile" class="file-upload" class="form-control-file" onchange="validateFile(this)">
                    <c:if test="${ehfOncoTreatMent.boneMarrowAttachment ne null}">
                        <span style="cursor: pointer; color:blue;" id="boneMarrowFileSpan" onclick="viewDocs('${ehfOncoTreatMent.boneMarrowAttachmentPath}');">${ehfOncoTreatMent.boneMarrowAttachment}</span>
                    </c:if>
                	</c:when>
                	<c:otherwise>
	                	<c:if test="${ehfOncoTreatMent.boneMarrowExam == 'Y'}">
	                        <span style="cursor: pointer; color:blue;" onclick="viewDocs('${ehfOncoTreatMent.boneMarrowAttachmentPath}');">${ehfOncoTreatMent.boneMarrowAttachment}</span>
	                    </c:if>
	                    <c:if test="${ehfOncoTreatMent.boneMarrowExam == 'N'}">
	                        <span>NA</span>
	                    </c:if>
                    </c:otherwise>
                    </c:choose>
                 </td>
            </tr>
        </table>

        <table>
            <tr><th class="label-column" >BioChemical parameters</th>
            <th class="result-column">Result</th>
            <th class="attachment-column">Attachment</th></tr>
            <tr>
                <td>Renal Functional Test &nbsp;</td>
                <td>
                    <input type="radio" name="renal" id="renalYes" value="Y" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.renalFunctionalTest =='Y'}"> checked </c:if> ${isDisabled}> Yes
                    <input type="radio" name="renal" id="renalNo" value="N" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.renalFunctionalTest =='N'}"> checked </c:if> ${isDisabled}> No
                </td>
                <td>
                	<c:choose>
                	<c:when test="${lResMap.isDataAvailable eq 'P'}">
                		<input type="file" id="renalFile" name="renalFile" class="file-upload" class="form-control-file"  onchange="validateFile(this)">
                		<c:if test="${ehfOncoTreatMent.renalAttachmentPath ne null}">
                			<span style="cursor: pointer; color:blue;" id="renalFileSpan" onclick="viewDocs('${ehfOncoTreatMent.renalAttachmentPath}');">${ehfOncoTreatMent.renalAttachment}</span>
                		</c:if>
                	</c:when>
                	<c:otherwise>
	                	<c:if test="${ehfOncoTreatMent.renalFunctionalTest == 'Y'}">
	                        <span style="cursor: pointer; color:blue;" onclick="viewDocs('${ehfOncoTreatMent.renalAttachmentPath}');">${ehfOncoTreatMent.renalAttachment}</span>
	                    </c:if>
	                    <c:if test="${ehfOncoTreatMent.renalFunctionalTest == 'N'}">
	                        <span>NA</span>
	                    </c:if>
                    </c:otherwise>
                    </c:choose>
                 </td>
            </tr>
            <tr>
                <td>Liver Functional Test &nbsp;</td>
                <td>
                    <input type="radio" name="liver" id="liverYes" value="Y" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.liverFunctionalTest =='Y'}"> checked </c:if> ${isDisabled}> Yes
                    <input type="radio" name="liver" id="liverNo" value="N" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.liverFunctionalTest =='N'}"> checked </c:if> ${isDisabled}> No
                </td>
                <td>
                	<c:choose>
                	<c:when test="${lResMap.isDataAvailable eq 'P'}">
                		<input type="file" id="liverFile" name="liverFile" class="file-upload" class="form-control-file"  onchange="validateFile(this)">
                		<c:if test="${ehfOncoTreatMent.liverAttachmentPath ne null}">
                			<span style="cursor: pointer; color:blue;" id="liverFileSpan" onclick="viewDocs('${ehfOncoTreatMent.liverAttachmentPath}');">${ehfOncoTreatMent.liverAttachment}</span>
                		</c:if>
                	</c:when>
                	<c:otherwise>
	                	<c:if test="${ehfOncoTreatMent.liverFunctionalTest == 'Y'}">
	                        <span style="cursor: pointer; color:blue;" onclick="viewDocs('${ehfOncoTreatMent.liverAttachmentPath}');">${ehfOncoTreatMent.liverAttachment}</span>
	                    </c:if>
	                    <c:if test="${ehfOncoTreatMent.liverFunctionalTest == 'N'}">
	                        <span>NA</span>
	                    </c:if>
                    </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>Thyriod Functional Test &nbsp;</td>
                <td>
                    <input type="radio" name="thyroid" id="thyroidYes" value="Y" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.thyroidFunctionTest =='Y'}"> checked </c:if> ${isDisabled}> Yes
                    <input type="radio" name="thyroid" id="thyroidNo" value="N" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.thyroidFunctionTest =='N'}"> checked </c:if> ${isDisabled}> No
                </td>
                <td>
                	<c:choose>
                	<c:when test="${lResMap.isDataAvailable eq 'P'}">
                		<input type="file" id="thyroidFile" name="thyroidFile" class="file-upload" class="form-control-file"  onchange="validateFile(this)">
                		<c:if test="${ehfOncoTreatMent.thyroidAttachmentPath ne null}">
                			<span style="cursor: pointer; color:blue;" id="thyroidFileSpan" onclick="viewDocs('${ehfOncoTreatMent.thyroidAttachmentPath}');">${ehfOncoTreatMent.thyroidAttachment}</span>
                		</c:if>
                	</c:when>
                	<c:otherwise>
	                	<c:if test="${ehfOncoTreatMent.thyroidFunctionTest == 'Y'}">
	                        <span style="cursor: pointer; color:blue;" onclick="viewDocs('${ehfOncoTreatMent.thyroidAttachmentPath}');">${ehfOncoTreatMent.thyroidAttachment}</span>
	                    </c:if>
	                    <c:if test="${ehfOncoTreatMent.thyroidFunctionTest == 'N'}">
	                        <span>NA</span>
	                    </c:if>
                    </c:otherwise>
                    </c:choose>
                 </td>
            </tr>
            <tr>
                <td>Complete Urine Exam &nbsp;</td>
                <td>
                    <input type="radio" name="urine" id="urineYes" value="Y" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.completeUrineExam =='Y'}"> checked </c:if> ${isDisabled}> Yes
                    <input type="radio" name="urine" id="urineNo" value="N" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.completeUrineExam =='N'}"> checked </c:if> ${isDisabled}> No
                </td>
                <td>
                	<c:choose>
                	<c:when test="${lResMap.isDataAvailable eq 'P'}">
                		<input type="file" id="urineFile" name="urineFile" class="file-upload" class="form-control-file"  onchange="validateFile(this)">
                		<c:if test="${ehfOncoTreatMent.urineAttachmentPath ne null}">
                			<span style="cursor: pointer; color:blue;" id="urineFileSpan" onclick="viewDocs('${ehfOncoTreatMent.urineAttachmentPath}');">${ehfOncoTreatMent.urineAttachment}</span>
                		</c:if>
                	</c:when>
                	<c:otherwise>
	                	<c:if test="${ehfOncoTreatMent.completeUrineExam == 'Y'}">
	                        <span style="cursor: pointer; color:blue;" onclick="viewDocs('${ehfOncoTreatMent.urineAttachmentPath}');">${ehfOncoTreatMent.urineAttachment}</span>
	                    </c:if>
	                    <c:if test="${ehfOncoTreatMent.completeUrineExam == 'N'}">
	                        <span>NA</span>
	                    </c:if>
                    </c:otherwise>
                    </c:choose>
                 </td>
            </tr>
            <tr>
                <td>Lipid Profile &nbsp;</td>
                <td>
                    <input type="radio" name="lipid" id="lipidYes" value="Y" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.lipidProfile =='Y'}"> checked </c:if> ${isDisabled}> Yes
                    <input type="radio" name="lipid" id="lipidNo" value="N" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.lipidProfile =='N'}"> checked </c:if> ${isDisabled}> No
                </td>
                <td>
                	<c:choose>
                	<c:when test="${lResMap.isDataAvailable eq 'P'}">
                		<input type="file" id="lipidFile" name="lipidFile" class="file-upload" class="form-control-file"  onchange="validateFile(this)">
                		<c:if test="${ehfOncoTreatMent.lipidAttachmentPath ne null}">
                			<span style="cursor: pointer; color:blue;" id="lipidFileSpan" onclick="viewDocs('${ehfOncoTreatMent.lipidAttachmentPath}');">${ehfOncoTreatMent.lipidAttachment}</span>
                		</c:if>
                	</c:when>
                	<c:otherwise>
	                	<c:if test="${ehfOncoTreatMent.lipidProfile == 'Y'}">
	                        <span style="cursor: pointer; color:blue;" onclick="viewDocs('${ehfOncoTreatMent.lipidAttachmentPath}');">${ehfOncoTreatMent.lipidAttachment}</span>
	                    </c:if>
	                    <c:if test="${ehfOncoTreatMent.lipidProfile == 'N'}">
	                        <span>NA</span>
	                    </c:if>
                    </c:otherwise>
                    </c:choose>
                 </td>
            </tr>
        </table>

        <table>
            <tr>
            <th class="label-column">Organ Functional Test</th>
            <th class="result-column">Result</th>
            <th class="attachment-column">Attachment</th></tr>
            <tr>
                <td>2D Echo &nbsp;</td>
                <td>
                    <input type="radio" name="echo" id="echoYes" value="Y" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.dEcho =='Y'}"> checked </c:if> ${isDisabled}> Yes
                    <input type="radio" name="echo" id="echoNo" value="N" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.dEcho =='N'}"> checked </c:if> ${isDisabled}> No
                </td>
                <td>
                	<c:choose>
                	<c:when test="${lResMap.isDataAvailable eq 'P'}">
                		<input type="file" id="echoFile" name="echoFile" class="file-upload" class="form-control-file" onchange="validateFile(this)">
	                	<c:if test="${ehfOncoTreatMent.dEchoAttachmentPath ne null}">
	                		<span style="cursor: pointer; color:blue;" id="echoFileSpan" onclick="viewDocs('${ehfOncoTreatMent.dEchoAttachmentPath}');">${ehfOncoTreatMent.dEchoAttachment}</span>
	                	</c:if>
                	</c:when>
                	<c:otherwise>
	                	<c:if test="${ehfOncoTreatMent.dEcho == 'Y'}">
	                        <span style="cursor: pointer; color:blue;" onclick="viewDocs('${ehfOncoTreatMent.dEchoAttachmentPath}');">${ehfOncoTreatMent.dEchoAttachment}</span>
	                    </c:if>
	                    <c:if test="${ehfOncoTreatMent.dEcho == 'N'}">
	                        <span>NA</span>
	                    </c:if>
                    </c:otherwise>
                    </c:choose>
                 </td>
            </tr>
            <tr>
                <td>Pulmonary Functional Test &nbsp;</td>
                <td>
                    <input type="radio" name="pulmonary" id="pulmonaryYes" value="Y" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.pulmonaryFunctionTest =='Y'}">checked</c:if> ${isDisabled}> Yes
                    <input type="radio" name="pulmonary" id="pulmonaryNo" value="N" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.pulmonaryFunctionTest =='N'}">checked</c:if> ${isDisabled}> No
                </td>
                <td>
                	<c:choose>
                	<c:when test="${lResMap.isDataAvailable eq 'P'}">
                		<input type="file" id="pulmonaryFile" name="pulmonaryFile" class="file-upload" class="form-control-file" onchange="validateFile(this)">
                		<c:if test="${ehfOncoTreatMent.pulmonaryAttachmentPath ne null}">
                			<span style="cursor: pointer; color:blue;" id="pulmonaryFileSpan" onclick="viewDocs('${ehfOncoTreatMent.pulmonaryAttachmentPath}');">${ehfOncoTreatMent.pulmonaryAttachment}</span>
                		</c:if>
                	</c:when>
                	<c:otherwise>
	                	<c:if test="${ehfOncoTreatMent.pulmonaryFunctionTest == 'Y'}">
	                        <span style="cursor: pointer; color:blue;" onclick="viewDocs('${ehfOncoTreatMent.pulmonaryAttachmentPath}');">${ehfOncoTreatMent.pulmonaryAttachment}</span>
	                    </c:if>
	                    <c:if test="${ehfOncoTreatMent.pulmonaryFunctionTest == 'N'}">
	                        <span>NA</span>
	                    </c:if>
                    </c:otherwise>
                    </c:choose>
                 </td>
            </tr>
            <tr>
                <td>ECG &nbsp;</td>
                <td>
                    <input type="radio" name="ecg" id="ecgYes" value="Y" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.ecg =='Y'}">checked</c:if> ${isDisabled}> Yes
                    <input type="radio" name="ecg" id="ecgNo" value="N" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.ecg =='N'}">checked</c:if> ${isDisabled}> No
                </td>
                <td>
                	<c:choose>
                	<c:when test="${lResMap.isDataAvailable eq 'P'}">
                		<input type="file" id="ecgFile" name="ecgFile" class="file-upload" class="form-control-file" onchange="validateFile(this)">
                		<c:if test="${ehfOncoTreatMent.ecgAttachmentPath ne null}">
                			<span style="cursor: pointer; color:blue;" id="ecgFileSpan" onclick="viewDocs('${ehfOncoTreatMent.ecgAttachmentPath}');">${ehfOncoTreatMent.ecgAttachment}</span>
                		</c:if>
                	</c:when>
                	<c:otherwise>
	                	<c:if test="${ehfOncoTreatMent.ecg == 'Y'}">
	                        <span style="cursor: pointer; color:blue;" onclick="viewDocs('${ehfOncoTreatMent.ecgAttachmentPath}');">${ehfOncoTreatMent.ecgAttachment}</span>
	                    </c:if>
	                    <c:if test="${ehfOncoTreatMent.ecg == 'N'}">
	                        <span>NA</span>
	                    </c:if>
                    </c:otherwise>
                    </c:choose>
                 </td>
            </tr>
        </table>

        <table>
            <tr>
            <th class="label-column">Tumor Markers</th>
            <th class="result-column">Result</th>
            <th class="attachment-column">Attachment</th></tr>
            <tr>
                <td>CEA &nbsp;</td>
                <td>
                    <input type="radio" name="cea" id="ceaYes" value="Y" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.cea =='Y'}">checked</c:if> ${isDisabled}> Yes
                    <input type="radio" name="cea" id="ceaNo" value="N" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.cea =='N'}">checked</c:if> ${isDisabled}> No
                </td>
                <td>
                	<c:choose>
                	<c:when test="${lResMap.isDataAvailable eq 'P'}">
                		<input type="file" id="ceaFile" name="ceaFile" class="file-upload" class="form-control-file" onchange="validateFile(this)">
                		<c:if test="${ehfOncoTreatMent.ceaAttachmentPath ne null}">
	                        <span style="cursor: pointer; color:blue;" id="ceaFileSpan" onclick="viewDocs('${ehfOncoTreatMent.ceaAttachmentPath}');">${ehfOncoTreatMent.ceaAttachment}</span>
	                    </c:if>
                	</c:when>
                	<c:otherwise>
	                	<c:if test="${ehfOncoTreatMent.cea == 'Y'}">
	                        <span style="cursor: pointer; color:blue;" onclick="viewDocs('${ehfOncoTreatMent.ceaAttachmentPath}');">${ehfOncoTreatMent.ceaAttachment}</span>
	                    </c:if>
	                    <c:if test="${ehfOncoTreatMent.cea == 'N'}">
	                        <span>NA</span>
	                    </c:if>
                    </c:otherwise>
                    </c:choose>
                 </td>
            </tr>
            <tr>
                <td>Ca-125 &nbsp;</td>
                <td>
                    <input type="radio" name="ca125" id="ca125Yes" value="Y" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.ca125 =='Y'}">checked</c:if> ${isDisabled}> Yes
                    <input type="radio" name="ca125" id="ca125No" value="N" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.ca125 =='N'}">checked</c:if> ${isDisabled}> No
                </td>
                <td>
                	<c:choose>
                	<c:when test="${lResMap.isDataAvailable eq 'P'}">
                		<input type="file" id="ca125File" name="ca125File" class="file-upload" class="form-control-file" onchange="validateFile(this)">
                		<c:if test="${ehfOncoTreatMent.ca125AttachmentPath ne null}">
	                        <span style="cursor: pointer; color:blue;" id="ca125FileSpan" onclick="viewDocs('${ehfOncoTreatMent.ca125AttachmentPath}');">${ehfOncoTreatMent.ca125Attachment}</span>
	                    </c:if>
                	</c:when>
                	<c:otherwise>
	                	<c:if test="${ehfOncoTreatMent.ca125 == 'Y'}">
	                        <span style="cursor: pointer; color:blue;" onclick="viewDocs('${ehfOncoTreatMent.ca125AttachmentPath}');">${ehfOncoTreatMent.ca125Attachment}</span>
	                    </c:if>
	                    <c:if test="${ehfOncoTreatMent.ca125 == 'N'}">
	                        <span>NA</span>
	                    </c:if>
                    </c:otherwise>
                    </c:choose>
                 </td>
            </tr>
            <tr>
                <td>Ca-15-3 &nbsp;</td>
                <td>
                    <input type="radio" name="ca153" id="ca153Yes" value="Y" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.ca153 =='Y'}">checked</c:if> ${isDisabled}> Yes
                    <input type="radio" name="ca153" id="ca153No" value="N" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.ca153 =='N'}">checked</c:if> ${isDisabled}> No
                </td>
                <td>
                	<c:choose>
                	<c:when test="${lResMap.isDataAvailable eq 'P'}">
                		<input type="file" id="ca153File" name="ca153File" class="file-upload" class="form-control-file" onchange="validateFile(this)">
                		<c:if test="${ehfOncoTreatMent.ca153AttachmentPath ne null}">
	                        <span style="cursor: pointer; color:blue;" id="ca153FileSpan" onclick="viewDocs('${ehfOncoTreatMent.ca153AttachmentPath}');">${ehfOncoTreatMent.ca153Attachment}</span>
	                    </c:if>
                	</c:when>
                	<c:otherwise>
	                	<c:if test="${ehfOncoTreatMent.ca153 == 'Y'}">
	                        <span style="cursor: pointer; color:blue;" onclick="viewDocs('${ehfOncoTreatMent.ca153AttachmentPath}');">${ehfOncoTreatMent.ca153Attachment}</span>
	                    </c:if>
	                    <c:if test="${ehfOncoTreatMent.ca153 == 'N'}">
	                        <span>NA</span>
	                    </c:if>
                    </c:otherwise>
                    </c:choose>
                 </td>
            </tr>
            <tr>
                <td>AFP &nbsp;</td>
                <td>
                    <input type="radio" name="afp" id="afpYes" value="Y" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.afp =='Y'}">checked</c:if> ${isDisabled}> Yes
                    <input type="radio" name="afp" id="afpNo" value="N" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.afp =='N'}">checked</c:if> ${isDisabled}> No
                </td>
                <td>
                	<c:choose>
                	<c:when test="${lResMap.isDataAvailable eq 'P'}">
                		<input type="file" id="afpFile" name="ca153File" class="file-upload" class="form-control-file" onchange="validateFile(this)">
                		<c:if test="${ehfOncoTreatMent.afpAttachmentPath ne null}">
	                        <span style="cursor: pointer; color:blue;" id="ca153FileSpan" onclick="viewDocs('${ehfOncoTreatMent.afpAttachmentPath}');">${ehfOncoTreatMent.afpAttachment}</span>
	                    </c:if>
                	</c:when>
                	<c:otherwise>
	                	<c:if test="${ehfOncoTreatMent.afp == 'Y'}">
	                        <span style="cursor: pointer; color:blue;" onclick="viewDocs('${ehfOncoTreatMent.afpAttachmentPath}');">${ehfOncoTreatMent.afpAttachment}</span>
	                    </c:if>
	                    <c:if test="${ehfOncoTreatMent.afp == 'N'}">
	                        <span>NA</span>
	                    </c:if>
                    </c:otherwise>
                    </c:choose>
                 </td>
            </tr>
            <tr>
                <td>Ca-19-9 &nbsp;</td>
                <td>
                    <input type="radio" name="ca199" id="ca199Yes" value="Y" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.ca199 =='Y'}">checked</c:if> ${isDisabled}> Yes
                    <input type="radio" name="ca199" id="ca199No" value="N" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.ca199 =='N'}">checked</c:if> ${isDisabled}> No
                </td>
                <td>
                	<c:choose>
                	<c:when test="${lResMap.isDataAvailable eq 'P'}">
                		<input type="file" id="ca199File" name="ca199File" class="file-upload" class="form-control-file" onchange="validateFile(this)">
                		<c:if test="${ehfOncoTreatMent.ca199AttachmentPath ne null}">
	                        <span style="cursor: pointer; color:blue;" id="ca199FileSpan" onclick="viewDocs('${ehfOncoTreatMent.ca199AttachmentPath}');">${ehfOncoTreatMent.ca199Attachment}</span>
	                    </c:if>
                	</c:when>
                	<c:otherwise>
	                	<c:if test="${ehfOncoTreatMent.ca199 == 'Y'}">
	                        <span style="cursor: pointer; color:blue;" onclick="viewDocs('${ehfOncoTreatMent.ca199AttachmentPath}');">${ehfOncoTreatMent.ca199Attachment}</span>
	                    </c:if>
	                    <c:if test="${ehfOncoTreatMent.ca199 == 'N'}">
	                        <span>NA</span>
	                    </c:if>
                    </c:otherwise>
                    </c:choose>
                 </td>
            </tr>
            <tr>
                <td>PSA &nbsp;</td>
                <td>
                    <input type="radio" name="psa" id="psaYes" value="Y" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.psa =='Y'}">checked</c:if> ${isDisabled}> Yes
                    <input type="radio" name="psa" id="psaNo" value="N" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.psa =='N'}">checked</c:if> ${isDisabled}> No
                </td>
                <td>
                	<c:choose>
                	<c:when test="${lResMap.isDataAvailable eq 'P'}">
                		<input type="file" id="psaFile" name="psaFile" class="file-upload" class="form-control-file" onchange="validateFile(this)">
                		<c:if test="${ehfOncoTreatMent.psaAttachmentPath ne null}">
	                        <span style="cursor: pointer; color:blue;" id="psaFileSpan" onclick="viewDocs('${ehfOncoTreatMent.psaAttachmentPath}');">${ehfOncoTreatMent.psaAttachment}</span>
	                    </c:if>
                	</c:when>
                	<c:otherwise>
	                	<c:if test="${ehfOncoTreatMent.psa == 'Y'}">
	                        <span style="cursor: pointer; color:blue;" onclick="viewDocs('${ehfOncoTreatMent.psaAttachmentPath}');">${ehfOncoTreatMent.psaAttachment}</span>
	                    </c:if>
	                    <c:if test="${ehfOncoTreatMent.psa == 'N'}">
	                        <span>NA</span>
	                    </c:if>
                    </c:otherwise>
                    </c:choose>
                 </td>
            </tr>
            <tr>
                <td>BETA HCG &nbsp;</td>
                <td>
                    <input type="radio" name="betahcg" id="betahcgYes" value="Y" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.betaHcg =='Y'}">checked</c:if> ${isDisabled}> Yes
                    <input type="radio" name="betahcg" id="betahcgNo" value="N" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.betaHcg =='N'}">checked</c:if> ${isDisabled}> No
                </td>
                <td>
                	<c:choose>
                	<c:when test="${lResMap.isDataAvailable eq 'P'}">
                		<input type="file" id="betahcgFile" name="betahcgFile" class="file-upload" class="form-control-file" onchange="validateFile(this)">
                		<c:if test="${ehfOncoTreatMent.betaHcgAttachmentPath ne null}">
	                        <span style="cursor: pointer; color:blue;" id="betahcgFileSpan" onclick="viewDocs('${ehfOncoTreatMent.betaHcgAttachmentPath}');">${ehfOncoTreatMent.betaHcgAttachment}</span>
	                    </c:if>
                	</c:when>
                	<c:otherwise>
	                	<c:if test="${ehfOncoTreatMent.betaHcg == 'Y'}">
	                        <span style="cursor: pointer; color:blue;" onclick="viewDocs('${ehfOncoTreatMent.betaHcgAttachmentPath}');">${ehfOncoTreatMent.betaHcgAttachment}</span>
	                    </c:if>
	                    <c:if test="${ehfOncoTreatMent.betaHcg == 'N'}">
	                        <span>NA</span>
	                    </c:if>
                    </c:otherwise>
                    </c:choose>
                 </td>
            </tr>
            <tr>
                <td>Chromogranin A &nbsp;</td>
                <td>
                    <input type="radio" name="chromogranin" id="chromograninYes" value="Y" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.chromograninA =='Y'}">checked</c:if> ${isDisabled}> Yes
                    <input type="radio" name="chromogranin" id="chromograninNo" value="N" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.chromograninA =='N'}">checked</c:if> ${isDisabled}> No
                </td>
                <td>
                	<c:choose>
                	<c:when test="${lResMap.isDataAvailable eq 'P'}">
                		<input type="file" id="chromograninFile" name="chromograninFile" class="file-upload" class="form-control-file" onchange="validateFile(this)">
                		<c:if test="${ehfOncoTreatMent.chromograninAAttachmentPath ne null}">
	                        <span style="cursor: pointer; color:blue;" id="chromograninFileSpan" onclick="viewDocs('${ehfOncoTreatMent.chromograninAAttachmentPath}');">${ehfOncoTreatMent.chromograninAAttachment}</span>
	                    </c:if>
                	</c:when>
                	<c:otherwise>
	                	<c:if test="${ehfOncoTreatMent.chromograninA == 'Y'}">
	                        <span style="cursor: pointer; color:blue;" onclick="viewDocs('${ehfOncoTreatMent.chromograninAAttachmentPath}');">${ehfOncoTreatMent.chromograninAAttachment}</span>
	                    </c:if>
	                    <c:if test="${ehfOncoTreatMent.chromograninA == 'N'}">
	                        <span>NA</span>
	                    </c:if>
                    </c:otherwise>
                    </c:choose>
                 </td>
            </tr>
        </table>

        <table>
            <tr><th class="label-column">Imaging Test</th>
            <th class="result-column">Result</th>
            <th class="attachment-column">Attachment</th></tr>
            <tr>
                <td>CECT Scan &nbsp;</td>
                <td>
                    <input type="radio" name="cect" id="cectYes" value="Y" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.cectScan =='Y'}">checked</c:if> ${isDisabled}> Yes
                    <input type="radio" name="cect" id="cectNo" value="N" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.cectScan =='N'}">checked</c:if> ${isDisabled}> No
                </td>
                <td>
                	<c:choose>
                	<c:when test="${lResMap.isDataAvailable eq 'P'}">
                		<input type="file" id="cectFile" name="cectFile" class="file-upload" class="form-control-file" onchange="validateFile(this)">
                		<c:if test="${ehfOncoTreatMent.cectAttachmentPath ne null}">
	                        <span style="cursor: pointer; color:blue;" id="cectFileSpan" onclick="viewDocs('${ehfOncoTreatMent.cectAttachmentPath}');">${ehfOncoTreatMent.cectAttachment}</span>
	                    </c:if>
                	</c:when>
                	<c:otherwise>
	                	<c:if test="${ehfOncoTreatMent.cectScan == 'Y'}">
	                        <span style="cursor: pointer; color:blue;" onclick="viewDocs('${ehfOncoTreatMent.cectAttachmentPath}');">${ehfOncoTreatMent.cectAttachment}</span>
	                    </c:if>
	                    <c:if test="${ehfOncoTreatMent.cectScan == 'N'}">
	                        <span>NA</span>
	                    </c:if>
                    </c:otherwise>
                    </c:choose>
                 </td>
            </tr>
            <tr>
                <td>MRI Scan &nbsp;</td>
                <td>
                    <input type="radio" name="mri" id="mriYes" value="Y" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.mriScan =='Y'}">checked</c:if> ${isDisabled}> Yes
                    <input type="radio" name="mri" id="mriNo" value="N" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.mriScan =='N'}">checked</c:if> ${isDisabled}> No
                </td>
                <td>
                	<c:choose>
                	<c:when test="${lResMap.isDataAvailable eq 'P'}">
                		<input type="file" id="mriFile" name="mriFile" class="file-upload" class="form-control-file" onchange="validateFile(this)">
                		<c:if test="${ehfOncoTreatMent.mriAttachmentPath ne null}">
	                        <span style="cursor: pointer; color:blue;" id="mriFileSpan" onclick="viewDocs('${ehfOncoTreatMent.mriAttachmentPath}');">${ehfOncoTreatMent.mriAttachment}</span>
	                    </c:if>
                	</c:when>
                	<c:otherwise>
	                	<c:if test="${ehfOncoTreatMent.mriScan == 'Y'}">
	                        <span style="cursor: pointer; color:blue;" onclick="viewDocs('${ehfOncoTreatMent.mriAttachmentPath}');">${ehfOncoTreatMent.mriAttachment}</span>
	                    </c:if>
	                    <c:if test="${ehfOncoTreatMent.mriScan == 'N'}">
	                        <span>NA</span>
	                    </c:if>
                    </c:otherwise>
                    </c:choose>
                 </td>
            </tr>
            <tr>
                <td>PET CT Scan &nbsp;</td>
                <td>
                    <input type="radio" name="petct" id="petctYes" value="Y" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.petCtScan =='Y'}">checked</c:if> ${isDisabled}> Yes
                    <input type="radio" name="petct" id="petctNo" value="N" onchange="toggleFileUpload(this)" <c:if test="${ehfOncoTreatMent.petCtScan =='N'}">checked</c:if> ${isDisabled}> No
                </td>
                <td>
                	<c:choose>
                	<c:when test="${lResMap.isDataAvailable eq 'P'}">
                		<input type="file" id="petctFile" name="petctFile" class="file-upload" class="form-control-file" onchange="validateFile(this)">
                		<c:if test="${ehfOncoTreatMent.petCtAttachmentPath ne null}">
	                        <span style="cursor: pointer; color:blue;" id="petctFileSpan" onclick="viewDocs('${ehfOncoTreatMent.petCtAttachmentPath}');">${ehfOncoTreatMent.petCtAttachment}</span>
	                    </c:if>
                	</c:when>
                	<c:otherwise>
	                	<c:if test="${ehfOncoTreatMent.petCtScan == 'Y'}">
	                        <span style="cursor: pointer; color:blue;" onclick="viewDocs('${ehfOncoTreatMent.petCtAttachmentPath}');">${ehfOncoTreatMent.petCtAttachment}</span>
	                    </c:if>
	                    <c:if test="${ehfOncoTreatMent.petCtScan == 'N'}">
	                        <span>NA</span>
	                    </c:if>
                    </c:otherwise>
                    </c:choose>
                 </td>
            </tr>
        </table>
         <br>
         <c:if test="${lResMap.isDataAvailable eq 'P'}">
	        <div class="form-buttons" style="display: grid;place-content: center;">
	        	<button  class="btn btn-success" type="button" name="submitBtn" id="submitBtn" value="submit" onclick="submitForm();">Submit</button>
	        </div>
         </c:if>
        
		</c:when>
		<c:otherwise>		
        <table>
            <tr>
                <th colspan="3">Recommended Treatment </th>
            </tr>
            <tr><td>Recommended Treatment &nbsp;</td><td><input type="text" id="recommendedTreatment" name="recommendedTreatment" onkeyup="validateInput(event)"></td></tr>
            <tr><td>Dosage based on BSA &nbsp;</td><td><input type="text" id="dosageBSA" name="dosageBSA" onkeyup="validateInput(event)"></td></tr>
            <tr><td>Weight &nbsp;</td><td><input type="number" id="weight" name="weight" onkeyup="validateNum(event)"></td></tr>
            <tr><td>Height &nbsp;</td><td><input type="number" id="height" name="height" onkeyup="validateNum(event)"></td></tr>
            <tr><td>Body Surface Area &nbsp;</td><td><input type="number" id="bodySurfaceArea" name="bodySurfaceArea" onkeyup="validateNum(event)"></td></tr>
            <tr><td>Date of starting drug and dosage &nbsp;</td><td><input type="text" name="startDate" id="startDate" name="startDate" onkeyup="validateInput(event)"></td></tr>

            <tr>
                <th colspan="3">Clinical Exam</th>
            </tr>
            <tr>
                <td>ECOG Performance Status &nbsp;</td>
                <td>
                	<c:forEach var="i" begin="0" end="5">
                    	<input type="radio" name="ecog" id="ecog${i}" value="${i}"> ${i}
                    </c:forEach>
                </td>
            </tr>

            <tr class="section-header"><td colspan="3">Vital Data</td></tr>
            <tr><td>Temperature &nbsp;</td><td><input type="number" id="temperature" name="temperature" onkeyup="validateNum(event)"></td></tr>
            <tr><td>Pulse rate &nbsp;</td><td><input type="number" id="pulseRate" name="pulseRate" onkeyup="validateNum(event)"></td></tr>
            <tr><td>Respiratory rate &nbsp;</td><td><input type="number" id="respiratoryRate" name="respiratoryRate" onkeyup="validateNum(event)"></td></tr>
            <tr><td>SPO2 &nbsp;</td><td><input type="number" id="spo2" name="spo2" onkeyup="validateNum(event)"></td></tr>
            <tr><td>Blood Pressure &nbsp;</td><td><input type="text" id="bloodPressure" name="bloodPressure" onkeyup="validateInput(event)"></td></tr>

            <tr><td>General Examination &nbsp;</td><td><input type="text" id="generalExamination"name="generalExamination" onkeyup="validateInput(event)"></td></tr>

            <tr class="section-header"><td colspan="3">Systemic Examination</td></tr>
            <tr><td>Cardio Vascular System &nbsp;</td><td><input type="text" id="cardioVascular" name="cardioVascular" onkeyup="validateInput(event)"></td></tr>
            <tr><td>Respiratory System &nbsp;</td><td><input type="text" id="respiratorySystem" name="respiratorySystem" onkeyup="validateInput(event)"></td></tr>
            <tr><td>Nervous System &nbsp;</td><td><input type="text" id="nervousSystem" name="nervousSystem" onkeyup="validateInput(event)"></td></tr>
            <tr><td>Per Abdominal examination &nbsp;</td><td><input type="text" id="abdominalExam" name="abdominalExam" onkeyup="validateInput(event)"></td></tr>
            <tr><td>Musculo skeletal system &nbsp;</td><td><input type="text" id="musculoSkeletal" name="musculoSkeletal" onkeyup="validateInput(event)"></td></tr>

            <tr class="section-header"><td colspan="3">Haematology Parameters</td></tr>
            <tr><td>CBP &nbsp;<font color="red">*</font></td><td><input type="text" id="cbp" name="cbp" onkeyup="validateInput(event)"></td></tr>
            <tr><td>Coagulation Profile &nbsp;</td><td><input type="text" id="coagulationProfile" name="coagulationProfile" onkeyup="validateInput(event)"></td></tr>
            <tr><td>PT &nbsp;</td><td><input type="text" id="pt" name="pt" onkeyup="validateInput(event)"></td></tr>
            <tr><td>APTT &nbsp;</td><td><input type="text" id="aptt" name="aptt" onkeyup="validateInput(event)"></td></tr>
            <tr><td>INR &nbsp;</td><td><input type="text" id="inr" name="inr" onkeyup="validateInput(event)"></td></tr>

            <tr>
                <td class="rest-column">Bone Marrow Exam &nbsp;</td>
                <td>
                    <input type="radio" name="boneMarrow" id="boneMarrowYes" value="Y" onchange="toggleFileUpload(this)"> Yes
                    <input type="radio" name="boneMarrow" id="boneMarrowNo" value="N" onchange="toggleFileUpload(this)"> No                  
                </td>
                <td class="attachment-column"><input type="file" id="boneMarrowFile" name="boneMarrowFile" class="file-upload" class="form-control-file" onchange="validateFile(this)"></td>
            </tr>
        </table>

        <table>
            <tr>
            <th class="label-column">BioChemical parameters</th>
            <th class="result-column">Result</th>
            <th class="attachment-column">Attachment</th></tr>
            <tr>
                <td>Renal Functional Test &nbsp;</td>
                <td>
                    <input type="radio" name="renal" id="renalYes" value="Y" onchange="toggleFileUpload(this)"> Yes
                    <input type="radio" name="renal" id="renalNo" value="N" onchange="toggleFileUpload(this)"> No
                </td>
                <td><input type="file" id="renalFile" name="renalFile" class="file-upload" class="form-control-file"  onchange="validateFile(this)"></td>
            </tr>
            <tr>
                <td>Liver Functional Test &nbsp;</td>
                <td>
                    <input type="radio" name="liver" id="liverYes" value="Y" onchange="toggleFileUpload(this)"> Yes
                    <input type="radio" name="liver" id="liverNo" value="N" onchange="toggleFileUpload(this)"> No
                </td>
                <td><input type="file" id="liverFile" name="liverFile" class="file-upload" class="form-control-file"  onchange="validateFile(this)"></td>
            </tr>
            <tr>
                <td>Thyriod Functional Test &nbsp;</td>
                <td>
                    <input type="radio" name="thyroid" id="thyroidYes" value="Y" onchange="toggleFileUpload(this)"> Yes
                    <input type="radio" name="thyroid" id="thyroidNo" value="N" onchange="toggleFileUpload(this)"> No
                </td>
                <td><input type="file" id="thyroidFile" name="thyroidFile" class="file-upload" class="form-control-file"  onchange="validateFile(this)"></td>
            </tr>
            <tr>
                <td>Complete Urine Exam &nbsp;</td>
                <td>
                    <input type="radio" name="urine" id="urineYes" value="Y" onchange="toggleFileUpload(this)"> Yes
                    <input type="radio" name="urine" id="urineNo" value="N" onchange="toggleFileUpload(this)"> No
                </td>
                <td><input type="file" id="urineFile" name="urineFile" class="file-upload" class="form-control-file"  onchange="validateFile(this)"></td>
            </tr>
            <tr>
                <td>Lipid Profile &nbsp;</td>
                <td>
                    <input type="radio" name="lipid" id="lipidYes" value="Y" onchange="toggleFileUpload(this)"> Yes
                    <input type="radio" name="lipid" id="lipidNo" value="N" onchange="toggleFileUpload(this)"> No
                </td>
                <td><input type="file" id="lipidFile" name="lipidFile" class="file-upload" class="form-control-file"  onchange="validateFile(this)"></td>
            </tr>
        </table>

        <table>
            <tr><th class="label-column">Organ Funtional Test</th><th class="result-column">Result</th><th class="attachment-column">Attachment</th></tr>
            <tr>
                <td>2D Echo &nbsp;</td>
                <td>
                    <input type="radio" name="echo" id="echoYes" value="Y" onchange="toggleFileUpload(this)"> Yes
                    <input type="radio" name="echo" id="echoNo" value="N" onchange="toggleFileUpload(this)"> No
                </td>
                <td><input type="file" id="echoFile" name="echoFile" class="file-upload" class="form-control-file" onchange="validateFile(this)"></td>
            </tr>
            <tr>
                <td>Pulmonary functional Test &nbsp;</td>
                <td>
                    <input type="radio" name="pulmonary" id="pulmonaryYes" value="Y" onchange="toggleFileUpload(this)"> Yes
                    <input type="radio" name="pulmonary" id="pulmonaryNo" value="N" onchange="toggleFileUpload(this)"> No
                </td>
                <td><input type="file" id="pulmonaryFile" name="pulmonaryFile" class="file-upload" class="form-control-file" onchange="validateFile(this)"></td>
            </tr>
            <tr>
                <td>ECG &nbsp;</td>
                <td>
                    <input type="radio" name="ecg" id="ecgYes" value="Y" onchange="toggleFileUpload(this)"> Yes
                    <input type="radio" name="ecg" id="ecgNo" value="N" onchange="toggleFileUpload(this)"> No
                </td>
                <td><input type="file" id="ecgFile" name="ecgFile" class="file-upload" class="form-control-file" onchange="validateFile(this)"></td>
            </tr>
        </table>

        <table>
            <tr>
            <th class="label-column">Tumor Markers</th>
            <th class="result-column">Result</th>
        	<th class="attachment-column">Attachment</th>
            </tr>
            <tr>
                <td>CEA &nbsp;</td>
                <td class="result-column">
                    <input type="radio" name="cea" id="ceaYes" value="Y" onchange="toggleFileUpload(this)" > Yes
                    <input type="radio" name="cea" id="ceaNo" value="N" onchange="toggleFileUpload(this)"> No
                </td>
                <td><input type="file" id="ceaFile" name="ceaFile" class="file-upload" class="form-control-file" onchange="validateFile(this)"></td>
            </tr>
            <tr>
                <td>Ca-125 &nbsp;</td>
                 <td class="result-column">
                    <input type="radio" name="ca125" id="ca125Yes" value="Y" onchange="toggleFileUpload(this)"> Yes
                    <input type="radio" name="ca125" id="ca125No" value="N" onchange="toggleFileUpload(this)"> No
                </td>
                <td><input type="file" id="ca125File" name="ca125File" class="file-upload" class="form-control-file" onchange="validateFile(this)"></td>
            </tr>
            <tr>
                <td>Ca-15-3 &nbsp;</td>
                 <td class="result-column">
                    <input type="radio" name="ca153" id="ca153Yes" value="Y" onchange="toggleFileUpload(this)"> Yes
                    <input type="radio" name="ca153" id="ca153No" value="N" onchange="toggleFileUpload(this)"> No
                </td>
                <td><input type="file" id="ca153File" name="ca153File" class="file-upload" class="form-control-file" onchange="validateFile(this)"></td>
            </tr>
            <tr>
                <td>AFP &nbsp;</td>
                <td class="result-column">
                    <input type="radio" name="afp" id="afpYes" value="Y" onchange="toggleFileUpload(this)"> Yes
                    <input type="radio" name="afp" id="afpNo" value="N" onchange="toggleFileUpload(this)"> No
                </td>
                <td><input type="file" id="afpFile" name="ca153File" class="file-upload" class="form-control-file" onchange="validateFile(this)"></td>
            </tr>
            <tr>
                <td>Ca-19-9 &nbsp;</td>
                 <td class="result-column">
                    <input type="radio" name="ca199" id="ca199Yes" value="Y" onchange="toggleFileUpload(this)"> Yes
                    <input type="radio" name="ca199" id="ca199No" value="N" onchange="toggleFileUpload(this)"> No
                </td>
                <td><input type="file" id="ca199File" name="ca199File" class="file-upload" class="form-control-file" onchange="validateFile(this)"></td>
            </tr>
            <tr>
                <td>PSA &nbsp;</td>
                 <td class="result-column">
                    <input type="radio" name="psa" id="psaYes" value="Y" onchange="toggleFileUpload(this)"> Yes
                    <input type="radio" name="psa" id="psaNo" value="N" onchange="toggleFileUpload(this)"> No
                </td>
                <td><input type="file" id="psaFile" name="psaFile" class="file-upload" class="form-control-file" onchange="validateFile(this)"></td>
            </tr>
            <tr>
                <td>BETA HCG &nbsp;</td>
                 <td class="result-column">
                    <input type="radio" name="betahcg" id="betahcgYes" value="Y" onchange="toggleFileUpload(this)"> Yes
                    <input type="radio" name="betahcg" id="betahcgNo" value="N" onchange="toggleFileUpload(this)"> No
                </td>
                <td><input type="file" id="betahcgFile" name="betahcgFile" class="file-upload" class="form-control-file" onchange="validateFile(this)"></td>
            </tr>
            <tr>
                <td>Chromogranin A &nbsp;</td>
                 <td class="result-column">
                    <input type="radio" name="chromogranin" id="chromograninYes" value="Y" onchange="toggleFileUpload(this)"> Yes
                    <input type="radio" name="chromogranin" id="chromograninNo" value="N" onchange="toggleFileUpload(this)"> No
                </td>
                <td><input type="file" id="chromograninFile" name="chromograninFile" class="file-upload" class="form-control-file" onchange="validateFile(this)"></td>
            </tr>
        </table>

        <table>
            <tr>
            <th class="label-column">Imaging Test</th>
            <th class="result-column">Result</th>
            <th class="attachment-column">Attachment</th></tr>
            <tr>
                <td>CECT Scan &nbsp;</td>
                <td>
                    <input type="radio" name="cect" id="cectYes" value="Y" onchange="toggleFileUpload(this)"> Yes
                    <input type="radio" name="cect" id="cectNo" value="N" onchange="toggleFileUpload(this)"> No
                </td>
                <td><input type="file" id="cectFile" name="cectFile" class="file-upload" class="form-control-file" onchange="validateFile(this)"></td>
            </tr>
            <tr>
                <td>MRI Scan &nbsp;</td>
                <td>
                    <input type="radio" name="mri" id="mriYes" value="Y" onchange="toggleFileUpload(this)"> Yes
                    <input type="radio" name="mri" id="mriNo" value="N" onchange="toggleFileUpload(this)"> No
                </td>
                <td><input type="file" id="mriFile" name="mriFile" class="file-upload" class="form-control-file" onchange="validateFile(this)"></td>
            </tr>
            <tr>
                <td>PET CT Scan &nbsp;</td>
                <td>
                    <input type="radio" name="petct" id="petctYes" value="Y" onchange="toggleFileUpload(this)"> Yes
                    <input type="radio" name="petct" id="petctNo" value="N" onchange="toggleFileUpload(this)"> No
                </td>
                <td><input type="file" id="petctFile" name="petctFile" class="file-upload" class="form-control-file" onchange="validateFile(this)"></td>
            </tr>
        </table>
        
        <br>
        <div class="form-buttons" style="display: grid;place-content: center;">
        	<button  class="btn btn-success" type="button" name="submitBtn" id="submitBtn" value="submit" onclick="submitForm();">Submit</button>
        </div>
        
        <input type="hidden" class="form-control" id="patientNo" name="patientNo" value="${lResMap.patientNo}">
       </c:otherwise>
	</c:choose>
	
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="width: 135%;margin-left: -18%;">
	  <div class="modal-dialog modal-lg" role="document">
	 <!-- <span class="close" onclick="closeModal();" style="margin-top:-2%; color:red; opacity:10">&times;</span> -->
	 <span class="close" onclick="closeModal();" style="margin-top: -2%; color: red; opacity: 1; z-index: 1051; position: absolute; right: 2px; top: 10px;">&times;</span>
	    <div class="modal-content" id="modal-content"style="">
	    	
	      <div class="modal-body" id="viewDocuments" style="max-height:100%;">
	      	      
	      </div>
	   	 </div>
 		</div>
	</div>
	
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
</form>

<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootbox.js"></script>
<script type="text/javascript" src="bootstrap/js/bootbox.min.js"></script>
<script src="bootstrap/js/static.jquery.dataTables.js"></script>

<script type="text/javascript">
var $ = jQuery.noConflict();
var todayDt = new Date();

$(document).ready(function(){
	$('#startDate').datepicker({
		format: 'dd/mm/yyyy',
		autoclose: true,
    	endDate: todayDt,
    	orientation: 'top auto',
		todayHighlight: true,
		defaultDate: "+1w",
		changeMonth: true,
		changeYear: true,
		showOn: "both", 
        buttonImage: "images/calend.gif", 
        buttonText: "Calendar",
        buttonImageOnly: true ,
		numberOfMonths: 1,
		maxDate: new Date(y, m, d),
		yearRange: '2000:' + new Date().getFullYear(),
	});
});

function fn_loadImage(){
	document.getElementById('processImagetable').style.display="";
}
function fn_removeLoadingImage(){
	document.getElementById('processImagetable').style.display="none";  
}

function toggleFileUpload(radio) {
	const fileUpload = radio.parentElement.nextElementSibling.querySelector('.file-upload');
    if (radio.value === 'Y') {
        fileUpload.classList.add('show');
    } else {
        fileUpload.classList.remove('show');
    }
}

var uploadedFiles = [];
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
    
    if (isDuplicateFile(fileName, fileSize)) {
        bootbox.alert("This file has already been uploaded in another section. Please upload a different file.");
        fileInput.value = '';
        return;
    }

    uploadedFiles.push({ name: fileName, size: fileSize });
}

function isDuplicateFile(fileName, fileSize) {
    for (var i = 0; i < uploadedFiles.length; i++) {
        const uploadedFile = uploadedFiles[i];
        if (uploadedFile.name === fileName && uploadedFile.size === fileSize) {
            return true;
        }
    }
    return false;
}

function validateInput(event) {
    const inputField = event.target;
    var value = inputField.value;

    const regex = /^[a-zA-Z0-9/,.\s]*$/;

    if (!regex.test(value)) {
        inputField.value = value.slice(0, -1);
    }
}

function validateNum(event) {
	  const inputField = event.target;
	  var value = inputField.value;

	const regex = /^\d{1,3}(\.\d{1})?$/;
	if (!regex.test(value)) {
	    inputField.value = value.slice(0, -1);
	  }
}

function submitForm() {
    
    /* if ($("#recommendedTreatment").val().trim() === "") {
        bootbox.alert("Please Enter the Recommended Treatment");
        return;
    } */
   /*  if ($("#dosageBSA").val().trim() === "") {
        bootbox.alert("Please Enter the Dosage based on BSA");
        return;
    } */
    /* if ($("#weight").val() == "0" || $("#weight").val() == "") {
        bootbox.alert("Please Enter the Weight");
        return;
    } */
   /*  if ($("#height").val() == "0" || $("#height").val() == "") {
        bootbox.alert("Please Enter the Height");
        return;
    } */
    /* if ($("#bodySurfaceArea").val() == "0" || $("#bodySurfaceArea").val() == "") {
        bootbox.alert("Please Enter the Body Surface Area");
        return;
    } */
   /*  if ($("#startDate").val().trim() === "") {
        bootbox.alert("Please Enter the Date of starting drug and dosage");
        return;
    } */
    /* if ($("#temperature").val() == "0" || $("#temperature").val() == "") {
        bootbox.alert("Please Enter the Temperature");
        return;
    } */
    /* if ($("#pulseRate").val() == "0" || $("#pulseRate").val() == "") {
        bootbox.alert("Please Enter the Pulse rate");
        return;
    } */
   /*  if ($("#respiratoryRate").val() == "0" || $("#respiratoryRate").val() == "") {
        bootbox.alert("Please Enter the Respiratory rate");
        return;
    } */
    /* if ($("#spo2").val() == "0" || $("#spo2").val() == "") {
        bootbox.alert("Please Enter the spo2");
        return;
    } */
 // Validate ECOG Performance Status radio buttons
    var ecogSelected = false;
    const ecogRadios = document.querySelectorAll('input[name="ecog"]');
    for (var i = 0; i < ecogRadios.length; i++) {
        if (ecogRadios[i].checked) {
            ecogSelected = true;
            break;
        }
    }
    /* const ecogValues = ["ecog0", "ecog1", "ecog2", "ecog3", "ecog4", "ecog5"];
    for (var i = 0; i < ecogValues.length; i++) {
        if (document.getElementById(ecogValues[i]).checked) {
            ecogSelected = true;
            break;
        }
    } */

 /*    if (!ecogSelected) {
    	bootbox.alert("Please select the ECOG Performance Status");
        return;
    } */
    /* if ($("#generalExamination").val().trim() === "") {
    	bootbox.alert("Please Enter the General Examination");
        return;
    } */
    /* if ($("#cardioVascular").val().trim() === "") {
    	bootbox.alert("Please Enter the Cardio Vascular System details");
        return;
    } */
    /* if ($("#respiratorySystem").val().trim() === "") {
    	bootbox.alert("Please Enter the Respiratory System details");
        return;
    } */
  /*   if ($("#nervousSystem").val().trim() === "") {
    	bootbox.alert("Please Enter the Nervous System details");
        return;
    } */
    /* if ($("#abdominalExam").val().trim() === "") {
    	bootbox.alert("Please Enter the Per Abdominal Examination details");
        return;
    } */
    /* if ($("#musculoSkeletal").val().trim() === "") {
    	bootbox.alert("Please Enter the Musculo Skeletal System details");
        return;
    }
 */
    // Validate Haematology Parameters inputs
    if ($("#cbp").val().trim() === "") {
    	bootbox.alert("Please Enter the CBP details");
        return;
    }
   /*  if ($("#coagulationProfile").val().trim() === "") {
    	bootbox.alert("Please Enter the Coagulation Profile details");
        return;
    } */
  /*   if ($("#pt").val().trim() === "") {
    	bootbox.alert("Please Enter the PT details");
        return;
    } */
    /* if ($("#aptt").val().trim() === "") {
    	bootbox.alert("Please Enter the APTT details");
        return;
    } */
    /* if ($("#inr").val().trim() === "") {
    	bootbox.alert("Please Enter the INR details");
        return;
    } */

    // Define file inputs and their respective radios
    const fields = [
        { name: "Bone Marrow Exam", radioYes: "boneMarrowYes", radioNo: "boneMarrowNo", fileInput: "boneMarrowFile"},            
        { name: "Renal Functional Test", radioYes: "renalYes", radioNo: "renalNo", fileInput: "renalFile" },
        { name: "Liver Functional Test", radioYes: "liverYes", radioNo: "liverNo", fileInput: "liverFile" },
        { name: "Thyroid Function Test", radioYes: "thyroidYes", radioNo: "thyroidNo", fileInput: "thyroidFile" },
        { name: "Complete Urine Exam", radioYes: "urineYes", radioNo: "urineNo", fileInput: "urineFile" },
        { name: "Lipid Profile", radioYes: "lipidYes", radioNo: "lipidNo", fileInput: "lipidFile" },
        { name: "2D Echo", radioYes: "echoYes", radioNo: "echoNo", fileInput: "echoFile" },
        { name: "Pulmonary Function Test", radioYes: "pulmonaryYes", radioNo: "pulmonaryNo", fileInput: "pulmonaryFile" },
        { name: "ECG", radioYes: "ecgYes", radioNo: "ecgNo", fileInput: "ecgFile" },
        { name: "CEA", radioYes: "ceaYes", radioNo: "ceaNo", fileInput: "ceaFile" },
        { name: "Ca-125", radioYes: "ca125Yes", radioNo: "ca125No", fileInput: "ca125File" },
        { name: "Ca-15-3", radioYes: "ca153Yes", radioNo: "ca153No", fileInput: "ca153File" },
        { name: "AFP", radioYes: "afpYes", radioNo: "afpNo", fileInput: "afpFile" },
        { name: "Ca-19-9", radioYes: "ca199Yes", radioNo: "ca199No", fileInput: "ca199File" },
        { name: "PSA", radioYes: "psaYes", radioNo: "psaNo", fileInput: "psaFile" },
        { name: "BETA HCG", radioYes: "betahcgYes", radioNo: "betahcgNo", fileInput: "betahcgFile" },
        { name: "Chromogranin A", radioYes: "chromograninYes", radioNo: "chromograninNo", fileInput: "chromograninFile" },
        { name: "CECT Scan", radioYes: "cectYes", radioNo: "cectNo", fileInput: "cectFile" },
        { name: "MRI Scan", radioYes: "mriYes", radioNo: "mriNo", fileInput: "mriFile" },
        { name: "PET CT Scan", radioYes: "petctYes", radioNo: "petctNo", fileInput: "petctFile" }
    ];

    // Validate file inputs based on Yes/No radios
    //let atLeastOneSelected = false;
    for (var i = 0; i < fields.length; i++) {
        var field = fields[i];
        var isYesChecked = document.getElementById(field.radioYes).checked;
        //var isNoChecked = document.getElementById(field.radioNo).checked;
        var spanElement = document.getElementById(field.fileInput+"Span");
        var fileInput = document.getElementById(field.fileInput);

        // Check if either "Yes" or "No" is selected
        /* if (!isYesChecked && !isNoChecked) {
            bootbox.alert("Please select Yes or No for " + field.name);
            return;
        } */

        // If "Yes" is selected but no file is uploaded, prompt for file
        if (isYesChecked && (fileInput.files.length == 0 && !spanElement)) {
            bootbox.alert("Please upload the " + field.name + " File");
            return;
        }
        
        /* if (isYesChecked || isNoChecked) {
            atLeastOneSelected = true; // At least one radio is selected

            // If "Yes" is selected but no file is uploaded, add alert message
            if (isYesChecked && fileInput.files.length === 0) {
                bootbox.alert("Please upload the " + field.name + " File");
                return;
            }
        } */
    }
    /* if (!atLeastOneSelected) {
        bootbox.alert("Please select at least one Yes or No for the tests.");
        return;
    } */
        
    if (confirm("Are you sure you want to submit the Oncology Evaluation Form?")) {
	    fn_loadImage();
	    var patientId = document.getElementById("patientNo").value;
	    document.forms[0].action = '/Operations/patientDetails.do?actionFlag=saveOncologyEvaluationForm&patientId=' + patientId;
	    document.forms[0].submit();
    }else{
    	return true;
    }
}

function viewDocs(filePath) {
	fn_loadImage();
    const url = "/Operations/patientDetails.do?actionFlag=viewDocuments&filePath="+filePath;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4) {
    	  fn_removeLoadingImage();
    	  if( this.status == 200){
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

</script>

</body>
</html>