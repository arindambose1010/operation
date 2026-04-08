 <%
/**
 * FILENAME     :viewDialysisCycles.jsp
 * @AUTHOR      : Sai Krishna	
 * --------------------------------------------------------------
 * Change Id       AUTHOR          DESCRIPTION
 * ---------------------------------------------------------------
 *  8566		   Sai Krishna    Jsp to view the dialysis cycles and Biometric attends for particular patient
 *    
 * --------------------------------------------------------------
 **/
%>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.ResourceBundle,java.util.ArrayList,java.util.HashMap" isErrorPage="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ include file="/common/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Referral Route</title>  
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen"> 
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script LANGUAGE="JavaScript" type="text/javascript" SRC="/<%=context%>/Preauth/maximizeScreen.js"></script>
<script type="text/javascript" src="js/patientscripts.js"></script>
<script LANGUAGE="JavaScript" SRC="scripts/PreauthScripts.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"> 
<%@ include file="/common/includePatientDetails.jsp"%>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fc;
            margin: 0;
            padding: 20px;
        }

        #dialog-modal {
            max-width: 100%;
            overflow-x: auto;
            margin: 0 auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
       .tbheader td {
		   background-color: #66b3ff; 
		   color: white;
		   font-size: 18px;
		   padding: 12px;
		   text-align: center; 
		   font-weight: bold;
		   border: none;
		   border-radius: 8px;
      }
        table th, table td {
            padding: 12px 15px;
            text-align: center;
            font-size: 14px;
            color: #333;
            border-bottom: 1px solid #ddd;
        }
        table th {
            background-color: #66b3ff; 
            color: white;
        }
        table td {
            background-color: #f9f9f9;
        }

        table td:last-child {
            word-wrap: break-word;
            max-width: 200px;
        }

        table tr:nth-child(even) td {
            background-color: #f2f2f2;
        }
        @media (max-width: 768px) {
            table th, table td {
                font-size: 12px;
                padding: 8px;
            }
        }
        #dialog-modal {
            padding: 20px;
            border-radius: 8px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .hoverDiv {
        	display: inline-block;
        	text-align: center;
           
            cursor: pointer;
         
        }

      #pdfContainer {
	z-index: 10000;
	margin: 0% 10%;
	height: 90%;
	width: 80%;
	left: 0%;
	position: fixed;
	float: none;
    background-color: white;
    box-shadow: 0 0 10px rgba(0,0,0,0.3);
    padding-top: 25px; 
}

iframe {
    width: 100%;
    height: 100%;
    border: none;
}
        
    </style>

</head>
<body>
<form >
		
		<div id="pdfContainer" style="display: none; position: fixed; top: 55%; left: 40%; transform: translate(-50%, -50%); width: 80%; height: 80%; background-color: white; border: 2px solid #ccc; z-index: 1000; padding: 10px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);">
    <iframe id="pdfFrame" style="width: 100%; height: 100%;"></iframe>
    <img id="imageFrame" style="width: 100%; height: 100%; display: none;" />
    <button id="closePdfBtn" style="position: absolute; top: 10px; right: 10px; background: red; color: white; border: none; padding: 5px; cursor: pointer;">X</button>
</div>
    <div id="dialog-modal" title="Dialysis Details">
    <div style="text-align: center; width: 100%; font-size: 1.0em; font-weight: bold; margin-bottom: 15px;">
        <strong>PATIENT DIALYSIS DETAILS</strong>
    </div>
    
    <!-- Check if the dialysisList is empty -->
    <c:if test="${empty dialysisList}">
        <!-- If list is empty, show message -->
        <div style="text-align: center; font-size: 1.2em; font-weight: bold; color: #ff0000;">
            No cycles for this record.
        </div>
    </c:if>
    
    <!-- If the list is not empty, display the table -->
    <c:if test="${not empty dialysisList}">
        <table style="width:100%" class="table table-bordered table-condensed cf">
            <thead>
                <tr>
                    <th style="width:15%">Cycle  Number</th>
                    <th style="width:15%">Session Start</th>
                    <th style="width:15%">Session End</th>
                    <th style="width:15%">Cycle Duration Time</th>
                    <th style="width:15%">On Bed Image</th>
                    <th style="width:15%">Dialyzer Image</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dialysis" items="${dialysisList}">
                    <tr>
                        <td><c:out value="Cycle ${dialysis.COUNT}"/></td>
                        <td><c:out value="${dialysis.ID}"/></td>
                        <td><c:out value="${dialysis.VALUE}"/></td>
                        <td><c:out value="${dialysis.CONST}"/></td>
                         <td>
                            <c:if test="${not empty dialysis.LVL}">
                                <div class="hoverDiv" data-filepath="${dialysis.LVL}" data-attachType="${dialysis.TYPE}" style="margin-left: 10%;">
                                    <img class="fileIcon" src="" alt="" width="20" height="20">
                                    <br/>                        
                                </div>
                            </c:if>
                        </td>
                         <td>
                            <c:if test="${not empty dialysis.SUBID}">
                                <div class="hoverDiv" data-filepath="${dialysis.SUBID}" data-attachType="${dialysis.WFTYPE}" style="margin-left: 10%;">
                                    <img class="fileIcon" src="" alt="" width="20" height="20">
                                    <br/>
                                </div>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
    
</form>
			</body>
			    <script type="text/javascript">
            const hoverDivs = document.querySelectorAll('.hoverDiv');
			const pdfContainer = document.getElementById('pdfContainer');
			const pdfFrame = document.getElementById('pdfFrame');
			const closeBtn = document.getElementById('closePdfBtn');

		hoverDivs.forEach(div => {
		    div.addEventListener('mouseenter', (e) => {
		        const pdfSrc = div.getAttribute('data-filepath');
		        const attachId = div.getAttribute('data-attachType');
	            const attachType = div.getAttribute('data-attachType'); 
	            
	            if('IMG'==attachType){
		        	imageFrame.style.display = 'block';
			        pdfFrame.style.display = 'none';
			        imageFrame.src = pdfSrc;
		        }
		        else {
		        	pdfFrame.style.display = 'block';
		        	imageFrame.style.display = 'none';
		        	pdfFrame.src = pdfSrc;
		        } 
	          
		        pdfContainer.style.display = 'block';
		        
		        
		    });


		    pdfContainer.addEventListener('mouseleave', () => {
		        pdfContainer.style.display = 'none';
		        pdfFrame.src = '';
		        imageFrame.src = '';
		    });
		});

		closeBtn.addEventListener('click', (event) => {
		    event.preventDefault();
		    pdfContainer.style.display = 'none';
		});
		
		document.querySelectorAll('.hoverDiv').forEach(div => {
		    const filePath = div.dataset.filepath;
		    const img = div.querySelector('.fileIcon');


		    const lowerCasePath = filePath.toLowerCase();
		    const fileName = lowerCasePath.split('/').pop();
		    
		    div.setAttribute('data-attachType','IMG');
		    if (lowerCasePath.startsWith('data:application/pdf')) {
		        img.src = 'images/pdf_icon.png';
		        img.alt = 'PDF';
		        div.setAttribute('data-attachType','PDF');
		    } else if (
		        lowerCasePath.startsWith('data:image/jpeg') ||
		        lowerCasePath.startsWith('data:image/jpg') ||
		        lowerCasePath.startsWith('data:image/png')
		    ) {
		        img.src = 'images/Image.gif';
		        img.alt = 'IMG';
		        div.setAttribute('data-attachType','IMG');
		        
		    } else {
		        img.src = 'images/image_icon.png';
		        img.alt = '';
		    }
		   
		});
	
	</script>
			</html>
			
