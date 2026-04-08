var standaloneProcIds=null;
var comboProcIds=null;
var deleteLst=new Array();
var addedProcs="";
function fn_loadProcessImage()
{
	document.getElementById('processImagetable').style.display="";
	setTimeout(function()
	{
		document.getElementById('processImagetable').style.display="none";
	},4000);
}


function fn_ipop()
{
	
	var schemeId=document.getElementById("scheme").value;
	var patientScheme=document.getElementById("patientScheme").value;
	var hospType=document.getElementById("hosptype").value;

	if(schemeId!=null && schemeId!='' && schemeId=='CD202')
		onloadDentalTGCond();
	

	var hospId=document.getElementById("hospitalId").value;
	var hospGovu = document.getElementById("hospGovu").value;

	var surgProc="${surgProc}";

var opActiveMsg='${opActiveMsg}';

	//if( (schemeId=="CD202" && patientScheme=="CD501" && hospType=="G") || (hospId=="EHS34") )
	if( (schemeId=="CD202" && patientScheme=="CD501" && hospType=="G") || (hospGovu=="G") )
	{
		
	if( document.forms[0].patientType[0].checked)
	{
	if(opActiveMsg!=null && opActiveMsg!='')
	{
	bootbox.alert(opActiveMsg);

	$('#middleFrame_content').find('input, textarea, button, select').attr('disabled','disabled');
	$('.globalEnable').prop('disabled',false);

	document.getElementById("Submit").disabled=false;
	document.getElementById("Save").disabled=false;
	document.getElementById("addAttach").disabled=false;
	document.getElementById("Reset").style.display="none";
	
	
	

	
	
	}
	document.getElementById("ipNote1").style.display="none";
	//document.getElementById("ipNote2").style.display="none";
	document.getElementById("opNote").style.display="";
	document.getElementById("docNameList").style.display="none";
	document.getElementById("docName").style.display="none";
	document.getElementById("doctorName").style.display="none";
	document.getElementById("unitNameHead").style.display="";
	document.getElementById("unitHODNameHead").style.display="";
	document.getElementById("unitName").style.display="";
	document.getElementById("unitHodName").style.display="";
	document.getElementById("addConsult").style.display="";
	document.getElementById("empPastHistory").style.display="";
	document.getElementById("addAttach").style.display="";
	document.getElementById("prescriptionData").style.display="";
	
		}
	
	else  if(document.forms[0].patientType[1].checked)
	{
		document.getElementById("opNote").style.display="none";
		
		document.getElementById("docNameList").style.display="";
		document.getElementById("docName").style.display="";
		document.getElementById("doctorName").style.display="";
		
		document.getElementById("unitNameHead").style.display="none";
		document.getElementById("unitHODNameHead").style.display="none";
		document.getElementById("unitName").style.display="none";
		document.getElementById("unitHodName").style.display="none";
		document.getElementById("addConsult").style.display="none";
		document.getElementById("empPastHistory").style.display="none";
		document.getElementById("addAttach").style.display="none";
		document.getElementById("prescriptionData").style.display="none";

		//if(hospId!=null && hospId=="EHS34" && surgProc!=null && surgProc=="Y")
		if(hospGovu!=null && hospGovu=="G" && surgProc!=null && surgProc=="Y")
		{
		
document.getElementById("surgDate").style.display="";
document.getElementById("surgDateHead").style.display="";
		}
		else
			
		{   
			if(document.getElementById("surgDate"))
			document.getElementById("surgDate").style.display="none";
		    if(document.getElementById("surgDateHead"))
			document.getElementById("surgDateHead").style.display="none";
		}
	}
	}

	fn_enableHistory();
	fn_enableDiag();
	
}
function fn_enableHistory()
{
	var schemeId=document.getElementById("scheme").value;
	var patientScheme=document.getElementById("patientScheme").value;
	var hospType=document.getElementById("hosptype").value;
	if(schemeId!='CD202' && hospType=='G' )
	{
	var otherComplaint=document.getElementById("otherComplaint").value;
	var patComplaintCode=document.getElementById("patComplaintCode").value;
	if(otherComplaint!=null && otherComplaint!='')
	{
		document.forms[0].presentHistory.disabled=false;
	}
	else
	{
		if(patComplaintCode==null || patComplaintCode=='')
		document.forms[0].presentHistory.disabled=true;
		else
			return false;
	}
	}
			
}
function fn_enableDiag()
{
	var otherDiagName='${otherDiag}';

	if(otherDiagName!=null && otherDiagName!='' && otherDiagName=='Y')
	{
		if(document.getElementById("diagOthers"))
		document.getElementById("diagOthers").checked=true;
		fn_enableOtherDiag();
	}
}
function getSubFieldType(input)
{
	
	var hospGovu = document.getElementById("hospGovu").value;
	var examinField="";
	var id=input.value;	
	var maxlength=5;
	var subTypeField=document.getElementById(id+"h").value;
	var prop = document.getElementById(id+"h").name;
	var hospId=document.getElementById("hospitalId").value;
	
	
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
			{examinField=examinField+"<input type='text' name='"+id+"' id='"+id+"' maxlength='"+maxlength+"' title='"+prop+" Text Field'onchange='validateInnerNum(this)'";
			 if(prop=="BMI") 
				 examinField=examinField+" readOnly/> ";
			 else
				examinField=examinField+" /> ";
			}
		
		else if(prop=='BP Lt.Arm mm/Hg')
			{
			
			examinField=examinField+"<input type='text' style='width:37%' name='"+id+"' id='"+id+"' maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/> / <input type='text' name='BP1' id='BP1' style='width:38%' maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/>";
			
			}
		else if(prop=='BP Rt.Arm mm/Hg') 
			examinField=examinField+"<input type='text' style='width:37%' name='"+id+"' id='"+id+"' maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/> / <input type='text' name='BP2' id='BP2' style='width:38%' maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/>";
		else if(prop=='Temperature(C/F)')
			{
			
			//if(hospId!=null && hospId!='EHS34')
			if(hospGovu!=null && hospGovu!="G")
				
			   {
				
				examinField=examinField+"<input type='radio' name='temp' id='temp' value='C' title='Centigrade' onclick='showTemp()'/>C<input type='radio'  name='temp' id='temp' value='F' title='ForeignHeat' onclick='showTemp()' />F&nbsp;&nbsp;&nbsp;<input type='text' style='width:57%;' name='"+id+"'  id='"+id+"' maxlength='"+maxlength+"' title='"+prop+" Text Field' onchange='validateInnerNum(this)'/>";
			   }
		    else
            {
				examinField=examinField+"<input type='text' style='width:57%;' name='"+id+"'  id='"+id+"' maxlength='"+maxlength+"' title='"+prop+" Text Field' onchange='validateInnerNum(this)'/>";
			
			    }
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
function pasteIntercept(evt)
{  
var input=document.getElementById('presentHistory');
maxLengthPaste(input,3000); 
}
function  pasteInterceptRemarks(evt)
{
	var input=document.getElementById('remarks');
	maxLengthPaste(input,3000); 
}
function  pasteInterceptDocRemarks(evt)
{
	var input=document.getElementById('treatingDocRmks');
	maxLengthPaste(input,3000); 
}
function  pasteInterceptOpRemarks(evt)
{
	var input=document.getElementById('opRemarks');
	maxLengthPaste(input,3000); 
}
function focusFieldView(el)
{
//fn_goToField(el);
focusBox(document.getElementById(el));
}
function addTooltip(input) 
{
	
	var numOptions = document.getElementById(input).options.length;
	 for ( var i = 0; i < numOptions; i++)
		document.getElementById(input).options[i].title = document
				.getElementById(input).options[i].text;
	 
} 
function enable_legalCase(type)
{
	if(type=='usercheck')
	{
		document.getElementById("legalCaseNo").value='';
		document.getElementById("policeStatName").value='';
	}
	if (document.forms[0].legalCase[0].checked)
	{
		document.getElementById("legalCaseNoTd").style.display='';
		document.getElementById("policeStatNameTd").style.display='';
	}
	else if(document.forms[0].legalCase[1].checked)
	{
		document.getElementById("legalCaseNoTd").style.display='none';
		document.getElementById("policeStatNameTd").style.display='none';
	}
}
function enable_dtrsform()
{
	var hospGovu = document.getElementById("hospGovu").value;
	var schemeId=document.getElementById("scheme").value;
	if(document.getElementById("patientScheme"))
	var patientScheme=document.getElementById("patientScheme").value;
	if(document.getElementById("hosptype"))
	var hospType=document.getElementById("hosptype").value;
	if(document.getElementById("hospitalId"))
	var hospId=document.getElementById("hospitalId").value;
	var enableFlag=false;
	//if((schemeId=="CD202" && hospType=="G" && patientScheme =="CD501") || hospId=="EHS34")
	if((schemeId=="CD202" && hospType=="G" && patientScheme =="CD501") || hospGovu=="G")
	enableFlag=true;
	else
	enableFlag=fn_savePatientDetails('DTRS');	
	if(enableFlag==true)
	{
		//if(schemeId!='CD202' && hospType!='G')
		document.getElementById("dtrsTd").style.display='';
	}
	else
		
	{
		
		document.getElementById("Submit").disabled=false;
		//document.getElementById("Submit").className='butdisable';
		if(document.getElementById("prescription"))
			{
		document.getElementById("prescription").disabled=true;
		document.getElementById("prescription").className='butdisable';
			}
		document.getElementById("dtrsTd").style.display='none';
	}
	
	
}
function onloadDentalTGCond()
{
	var localSchemeId=document.getElementById("scheme").value;
	var localSpecLst=new Array();
	var procName=null;
	
	if(specOld!=null && specOld!='' && specOld!= ' ')
		localSpecLst=specOld;
	
	if(localSpecLst!=null && localSpecLst.length>0)
		{
			for(var localVal1=0 ; localVal1 < localSpecLst.length ; localVal1++)
				{
					var listValues = localSpecLst[localVal1].split("~");
					if(listValues.length>0)
						{
							if(listValues[0] != null && listValues[0] != '' && localSchemeId != null && 
									localSchemeId != '' && listValues[2] !=null && listValues[2] != '' )
								{
									if(listValues[0]=='S18' && localSchemeId == 'CD202' )
										{
											//Incase of Previously added Procs names are fetched from Action itself
											procName=listValues[10];
												
											getDentalConditions(listValues[2] , procName , localSchemeId , 'comboCond' );
										}
								}
						}
				}
		}	
}

function getDentalConditions(icdProcCode,procName,schemeId,arg)
{
	var deleteProc='N';
	if(icdProcCode==null || icdProcCode=='')
		icdProcCode=document.getElementById("ICDProcName").value;

	if(schemeId==null || schemeId=='')
		schemeId=document.getElementById("scheme").value;
	
	var patientId=document.getElementById("patientNo").value;
	if(deleteLst!=null)
		{
			for(var i=0 ;i <deleteLst.length ;i++)
				{
					if(icdProcCode==deleteLst[i])
						deleteProc='Y';
				}
		}
	if(icdProcCode!=null && icdProcCode!='' && schemeId!=null && schemeId!='' && arg!=null && arg!='')
		{
			var xmlhttp;
			if(window.XMLHttpRequest)
				xmlhttp=new XMLHttpRequest();
			else if(window.ActiveXObject)
				xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			else
				bootbox.alert('Your Browser does not Support XMLHTTP!');
			
			xmlhttp.onreadystatechange=function ()
				{
					if(xmlhttp.readyState==4)
						{
							var result=xmlhttp.responseText;
							result = result.replace("*","");
							if(result!=null)
								{
									var alertSpecCond=result.split('~~~');
									if(alertSpecCond!=null && alertSpecCond.length>1 
											&& alertSpecCond[0]!=null && alertSpecCond[1]!=null
											&& alertSpecCond[0]=='AlertCont')
										{
											resetDentalProc();
											bootbox.alert(alertSpecCond[1]);
											focusBox(document.getElementById("ICDProcName"));
											return false;
										}
									else
										{
											var jsonData = JSON.parse(result);
											if(jsonData!=null)
												{
													if(arg=='allCond')
														allDentalConditions(jsonData);  
													else if(arg=='unitsCond')
														unitsLeftCond(jsonData);
													else if(arg=='comboCond')
														comboDentalConditions(icdProcCode,procName,jsonData);
												}
										}
								}
						}
				}
			var url="patientDetails.do?actionFlag=getAllDentalConditions&callType=Ajax&icdProcCode="+icdProcCode+"&patSchemeId="+schemeId+"&patientId="+patientId+"&deleteProc="+deleteProc;
			xmlhttp.open("post",url,true);
			xmlhttp.send(null);
		}
}
function resetDentalProc()
{
	document.forms[0].investigations.options.length=0;
	document.getElementById("ICDProcName").value='-1';
	document.getElementById("procUnits").value='-1';
	document.getElementById("ICDProcCode").value='';
	document.getElementById("unitsTd").style.display="none";
	document.getElementById("unitsLabelTd").style.display="none";
}
function unitsLeftCond(jsonData) 													
{
	var totalPreLimit=0,lifeTimeUnitsLeft=0;
	var units=document.getElementById("procUnits").value;
	if(units!=null && units!='-1')
		{
			if(jsonData.lifetimeUnitsLimit!=null && jsonData.lifetimeUnitsLimit!='-1' && jsonData.lifetimeUnitsLimit!='')
				totalPreLimit=Number(jsonData.lifetimeUnitsLimit);
			if(jsonData.actualUnitsLeft!=null && jsonData.actualUnitsLeft!='' && jsonData.actualUnitsLeft!=' ')
				lifeTimeUnitsLeft=Number(jsonData.actualUnitsLeft);
			
			if(units > lifeTimeUnitsLeft && lifeTimeUnitsLeft!='-1' )
				{
					if(lifeTimeUnitsLeft=='0')
						bootbox.alert("No Units can be treated as maximum units i.e. "+totalPreLimit+" are already utilized by the Patient for Selected Procedure");
					else
						bootbox.alert("Maximum Units that be treated for Selected Procedure are "+totalPreLimit+" .A maximum of "+lifeTimeUnitsLeft+" can only be selected as "+(totalPreLimit-lifeTimeUnitsLeft)+" are already utilized by the Patient");
/*						else if (totalPreLimit-lifeTimeUnitsLeft==0)	
						alert("Maximum Units that be treated for Selected Procedure are "+totalPreLimit+" .A maximum of "+lifeTimeUnitsLeft+" can only be selected");
*/
					document.getElementById("procUnits").value="-1";
					false;
				}
		}
}
function comboDentalConditions(icdProcCode,procName,jsonData)
{
	//Added by Srikalyan for TG Combo Dental Conditions 
	//Format for Combo Conditions SelectedProcID!@#SelectedProcName,ComboId1@ComboName1~ComboId2@ComboName2
	var comboProcs=jsonData.comboProcCode;
	var comboProcNames=jsonData.comboProcNames;
	var comboProcsLst=null,comboProcsNamesLst=null,addComboProcs='N';
	
	/*if(comboProcs != null && comboProcs != '')
		 comboProcsLst=comboProcs.split("~");
	if(comboProcNames != null && comboProcNames != '')
		comboProcsNamesLst=comboProcNames.split("~");
	if(comboProcsLst!=null && comboProcsLst.length > 0 && comboProcsLst[0]!=null && comboProcsLst[0]!='NA')
		{
			if(comboProcIds!=null && comboProcIds!='' && comboProcIds.indexOf(icdProcCode+"!@#")=='-1' )
				{
					comboProcIds=comboProcIds+"$"+icdProcCode+"!@#"+procName;
					addComboProcs='Y';
				}	
			else
				{
					comboProcIds=icdProcCode+"!@#"+procName;
					addComboProcs='Y';
				}
			if(addComboProcs=='Y')
				{
					for(var a=0 ; a < comboProcsLst.length ; a++)
						{
							if(a==0)
								comboProcIds=comboProcIds+","+(comboProcsLst[a]+"@"+comboProcsNamesLst[a]);
							else
								comboProcIds=comboProcIds+"~"+(comboProcsLst[a]+"@"+comboProcsNamesLst[a]);
						}
				}	
		}*/
	//Added by Srikalyan for TG Non Combo Dental Conditions
	//Format for Non Combo Conditions SelectedProcID!@#SelectedProcName,NonComboId1@NonComboName1~NonComboId2@NonComboName2
	var noncomboProcs=jsonData.nonComboProcCode;
	var noncomboProcNames=jsonData.comboNonProcNames;
	var noncomboProcsLst=null,noncomboProcsNamesLst=null,addNonComboProcs='N';
	
	if(noncomboProcs != null && noncomboProcs != '')
		noncomboProcsLst=noncomboProcs.split("~");
	if(noncomboProcNames != null && noncomboProcNames != '')
		noncomboProcsNamesLst=noncomboProcNames.split("~");
	
	if(noncomboProcsLst!=null && noncomboProcsLst.length > 0 && noncomboProcsLst[0]!=null && noncomboProcsLst!='NA')
		{
			if(nonComboProcIds!=null && nonComboProcIds!='' && nonComboProcIds.indexOf(icdProcCode+"!@#")=='-1' )
				{
					nonComboProcIds=nonComboProcIds+"$"+icdProcCode+"!@#"+procName;
					addNonComboProcs='Y';
				}	
			else
				{
					nonComboProcIds=icdProcCode+"!@#"+procName;
					addNonComboProcs='Y';
				}
			if(addNonComboProcs=='Y')
				{
					for(var a=0 ; a < noncomboProcsLst.length ; a++)
						{
							if(a==0)
								nonComboProcIds=nonComboProcIds+","+(noncomboProcsLst[a]+"@"+noncomboProcsNamesLst[a]);
							else
								nonComboProcIds=nonComboProcIds+"~"+(noncomboProcsLst[a]+"@"+noncomboProcsNamesLst[a]);
						}
				}	
		}
	
	//Added by Srikalyan for TG Non Combo Dental Conditions
	//Format for StandAlone Proc Conditions SelectedProcID!@#SelectedProcName,NonComboId1@NonComboName1~NonComboId2@NonComboName2
	var stanAloneProcsLoc=jsonData.standaloneProc;
	var stanAloneProcNamesLoc=jsonData.standaloneProcNames;
	var stanaloneProcsLst=null,standaloneProcsNamesLst=null,standaloneProcs='N';
	if(stanAloneProcsLoc != null && stanAloneProcsLoc != '')
		stanaloneProcsLst=stanAloneProcsLoc.split("~");
	if(stanAloneProcNamesLoc != null && stanAloneProcNamesLoc != '')
		standaloneProcsNamesLst=stanAloneProcNamesLoc.split("~");
	
	if(stanaloneProcsLst!=null && stanaloneProcsLst.length > 0 && stanaloneProcsLst[0]!=null && stanaloneProcsLst[0]!='NA' )
		{
			if(standaloneProcIds!=null && standaloneProcIds!='' && standaloneProcIds.indexOf(icdProcCode+"!@#")=='-1' )
				{
					standaloneProcIds=standaloneProcIds+"$"+icdProcCode+"!@#"+procName;
					standaloneProcs='Y';
				}	
			else
				{
					standaloneProcIds=icdProcCode+"!@#"+procName;
					standaloneProcs='Y';
				}
			if(standaloneProcs=='Y')
				{
					for(var a=0 ; a < stanaloneProcsLst.length ; a++)
						{
							if(a==0)
								standaloneProcIds=standaloneProcIds+","+(stanaloneProcsLst[a]+"@"+standaloneProcsNamesLst[a]);
							else
								standaloneProcIds=standaloneProcIds+"~"+(stanaloneProcsLst[a]+"@"+standaloneProcsNamesLst[a]);
						}
				}	
		}
}
function getDentalUnits()
{
var xmlhttp;
var url;
var lStrProcCode=document.getElementById("ICDProcName").value;
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
	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
}

xmlhttp.onreadystatechange=function()
{
	if(xmlhttp.readyState==4)
		{
		var resultArray=xmlhttp.responseText;
        if(resultArray.trim()=="SessionExpired*"){
        	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
        }
        else{
            	if(resultArray!=null)
            	{
            		var unitsList = resultArray.replace("*","");            
                	if(unitsList.length>0 && unitsList!=0)
                	{  
            			document.forms[0].procUnits.options.length=0;
                    	document.forms[0].procUnits.options[0]=new Option("---select---","-1");

                    	for(var i = 1; i<=unitsList;i++)
                		{	
                        	document.forms[0].procUnits.options[i] =new Option(i,i);
                		}
            		}
            	}
        	}
       
        if(document.forms[0].procUnits.options.length==1)
        	{
	            document.getElementById("unitsTd").style.display='none';
	    		document.getElementById("unitsLabelTd").style.display='none';
        	}	
        
		}
}
url = "./patientDetails.do?actionFlag=getUnitsByProc&callType=Ajax&lStrProcCode="+lStrProcCode;
xmlhttp.open("Post",url,true);
xmlhttp.send(null);
}
function fn_savePatientDetails(checkType)
{
	var doctorType=document.forms[0].diagnosedBy.value;
	document.getElementById("drugs").value=drugs;
	document.getElementById("symptoms").value=symptoms;
	var patId=document.getElementById("patientNo").value;
	
	var schemeId=document.getElementById("scheme").value;
	var patientScheme=document.getElementById("patientScheme").value;
	var hospType=document.getElementById("hosptype").value;
	var hospId = document.getElementById("hospitalId").value;
	var hospGovu = document.getElementById("hospGovu").value;
    var ipCase=document.forms[0].patientType[1].checked;
	
	var lErrorMsg='';
	var lField='';
	var genTestsCount=0;
	var ipTestsCount=0;
	var updTestsCount=0;
	for(var temp=1;temp<document.forms[0].elements.length;temp++)
    {
       if(document.forms[0].elements[temp].type=="file")
       {
       	   var val=document.forms[0].elements[temp].value;
           if(val==null || val=="")
           	{
        	 if(document.forms[0].elements[temp].name.charAt(0)=='g')
        	   genTestsCount=genTestsCount+1;
			else if(document.forms[0].elements[temp].name.charAt(0)=='u')
        		 updTestsCount=updTestsCount+1;
        	 else if(document.forms[0].elements[temp].name.charAt(0)=='a')
        		 ipTestsCount=ipTestsCount+1;
			if(lField=='')
				lField=''+document.forms[0].elements[temp].id+'';
           	}
           else
			{
				var rtVal=chkSpecailChars(val.substring(val.lastIndexOf('\\')+1,val.lastIndexOf('.')));
				var fullFileName=val.substring(val.lastIndexOf('\\')+1);
				var fileName1=val.substring(val.lastIndexOf('\\')+1,val.lastIndexOf('.'));
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
				 var sub=val.substring(val.lastIndexOf('.')+1);
				 if((sub=='exe')||(sub=='EXE') || (sub=='rar')||(sub=='RAR') || (sub=='war')||(sub=='WAR')|| (sub=='zip')||(sub=='ZIP'))
				  {
					 if(lErrorMsg=='')
		            	{
							lErrorMsg=lErrorMsg+"Cannot upload exe,rar,war files ";
		       	        }
				  } 
				}
			}
         }
   
	if(checkType=='submit')
	{
		if(document.forms[0].medicalhistoryid.value=="" || document.forms[0].medicalhistoryid.value==null){
			if(lErrorMsg=='')
			{
				lErrorMsg=lErrorMsg+"Please select Medical History Yes or No";
		    }
		}
		else if(document.forms[0].medicalhistoryid.value=="Yes"){
			var medicalHistArr=[];
			for(var i=0;i<document.forms[0].medicalDtlsid.length;i++){
				if(document.forms[0].medicalDtlsid[i].checked==true)
				medicalHistArr.push(document.forms[0].medicalDtlsid[i].value);
			}
			if(medicalHistArr.length==0){
				if(lErrorMsg=='')
				{
					lErrorMsg=lErrorMsg+"Please provide Medical History Details";
			    }	
			}			
		}
		if(document.getElementById("decidiousdentsel").checked==false && document.getElementById("permanentdentsel").checked==false){
			if(lErrorMsg=='')
			{
				lErrorMsg=lErrorMsg+"Either of Deciduous Dentition or Permanent Dentition must be selected";
		    }
		}
				if(document.getElementById("decidiousdentsel").checked==true){
		//Check Decidious Dentition
		var deciduousDent=document.getElementById("deciduousDent");
		  if(deciduousDent != null)
		   {
			var decDentition=document.getElementById("deciduousDent").length; 
			var mainCompId="";
			if(document.getElementById("deciduousDent").selectedIndex == "-1");
			{
			 var options = $('#deciduousDent > option:selected');
			     if(options.length == 0){
			         bootbox.alert('Please select atleast one option in Deciduous Dentition');
			         document.getElementById("deciduousDent").focus();
			         return false;
				
			}
			for (var x=0;x<decDentition;x++)
			{
				 mainCompId = document.forms[0].deciduousDent[x].value;
				
				
				if (document.forms[0].deciduousDent[x].selected)
				{
						if(mainCompId == "CH90")
						{
							    if ($("input[type='checkbox'][name='childMissing']:checked").length == 0){
							    	bootbox.alert("Please select atleast one checkbox in Deciduous Dentition Missing ");
							    	document.getElementById("deciduousDent").focus();
							         return false;
							    }
						}
						if(mainCompId == "CH89")
						{
							 if ($("input[type='checkbox'][name='childMobile']:checked").length == 0){
								 bootbox.alert("Please select atleast one checkbox in Deciduous Dentition Mobile ");
								 document.getElementById("deciduousDent").focus();
						         return false;
						    }
						}
						if(mainCompId == "CH88")
						{
							 if ($("input[type='checkbox'][name='grosslyDecayed']:checked").length == 0){
								 bootbox.alert("Please select atleast one checkbox in Deciduous Dentition Grossly Decayed ");
								 document.getElementById("deciduousDent").focus();
						         return false;
						    }
						}
						if(mainCompId == "CH87")
						{
							 if ($("input[type='checkbox'][name='childCaries']:checked").length == 0){
								 bootbox.alert("Please select atleast one checkbox in Deciduous Dentition Caries ");
								 document.getElementById("deciduousDent").focus();
						         return false;
						    }
						}
						
						
						
				}
				
			}
		  }	
		}
	}
		
		
		
		
		if(document.forms[0].occlusion.value==""||document.forms[0].occlusion.value==null){
			if(lErrorMsg=='')
			{
				lErrorMsg=lErrorMsg+"Please select occlusion";
		    }
		}
		else if(document.forms[0].occlusion.value=="CH83"){
			if(document.forms[0].occlusionOther.value==""){
				if(lErrorMsg=='')
				{
					lErrorMsg=lErrorMsg+"Please enter occlusion Others remarks";
			    }
			}
		}
		
		if(document.forms[0].patientType.value=='OP')
		{
		if(document.forms[0].doctorName.value==-1||document.forms[0].doctorName.value==null||document.forms[0].doctorName.value==""){
			if(lErrorMsg=='')
			{
				lErrorMsg=lErrorMsg+"Please Select Doctor Name ";
		    }
		}
		}
		
		if(document.forms[0].patientType.value=='IP'){
//			if(document.forms[0].followupAdv.value==""||document.forms[0].followupAdv.value==null){
//				if(lErrorMsg=='')
//				{
//					lErrorMsg=lErrorMsg+"Please enter Post OP Instructions and Follow up Advice remarks";
//			    }
//			}
			if(document.forms[0].ipDoctorName.value==-1||document.forms[0].ipDoctorName.value==null||document.forms[0].ipDoctorName.value==""){
				if(lErrorMsg=='')
				{
					lErrorMsg=lErrorMsg+"Please Select Doctor Name ";
			    }
			}
		}
		
		if(document.forms[0].patientType.value=='DOP'){
			if(document.forms[0].medicationGiven.value==""||document.forms[0].medicationGiven.value==null){
				if(lErrorMsg=='')
				{
					lErrorMsg=lErrorMsg+"Please enter details of the Medication Given";
			    }
			}
			if(document.forms[0].ipDoctorName.value==-1||document.forms[0].ipDoctorName.value==null||document.forms[0].ipDoctorName.value==""){
				if(lErrorMsg=='')
				{
					lErrorMsg=lErrorMsg+"Please Select Doctor Name ";
			    }
			}
		}
		if(document.getElementById("permanentdentsel").checked==true){
		//Check permanent dentition
		  var permanent=document.getElementById("permanentDent");
		   if(permanent!= null)
		   {
			var premDentition=document.getElementById("permanentDent").length; 
			var mainCompId="";
		     var options = $('#permanentDent > option:selected');
		     if(options.length == 0){
		         bootbox.alert('Please select atleast one option in Permanent Dentition');
		         document.getElementById("permanentDent").focus();
		         return false;
		     }
			    
			for (var x=0;x<premDentition;x++)
			{
				 mainCompId = document.forms[0].permanentDent[x].value;
				
				
				if (document.forms[0].permanentDent[x].selected)
				{
						if(mainCompId == "CH95")
						{
							    if ($("input[type='checkbox'][name='missing']:checked").length == 0){
							    	 bootbox.alert("Please select atleast one checkbox in Permanent Dentition Missing ");
							    	 document.getElementById("permanentDent").focus();
							         return false;
							    }
						}
						if(mainCompId == "CH96")
						{
							 if ($("input[type='checkbox'][name='caries']:checked").length == 0){
								 bootbox.alert("Please select atleast one checkbox in Permanent Dentition Caries ");
								 document.getElementById("permanentDent").focus();
						         return false;
						    }
						}
						if(mainCompId == "CH92")
						{
							 if ($("input[type='checkbox'][name='decayed']:checked").length == 0){
								 bootbox.alert("Please select atleast one checkbox in Permanent Dentition Root stump / Grossly Decayed ");
								 document.getElementById("permanentDent").focus();
						         return false;
						    }
						}
						if(mainCompId == "CH93")
						{
							 if ($("input[type='checkbox'][name='mobile']:checked").length == 0){
								 bootbox.alert("Please select atleast one checkbox in Permanent Dentition Mobility ");
								 document.getElementById("permanentDent").focus();
						         return false;
						    }
						}
						if(mainCompId == "CH94")
						{
							 if ($("input[type='checkbox'][name='attrition']:checked").length == 0){
								 bootbox.alert("Please select atleast one checkbox in Permanent Dentition Attrition / Abrasion ");
								 document.getElementById("permanentDent").focus();
						         return false;
							    }
						}
						
						
				}
				
			}
			

		   }
	}
		//if(hospId!=null && hospId!="EHS34")
		if(hospGovu!=null && hospGovu!="G")
		{
		if(genTestsCount>0)
		{
			if(lErrorMsg=='')
			{
				lErrorMsg=lErrorMsg+"General investigation attachments are mandatory ";
			} 
		}
		if(updTestsCount>0)
		{
			if(lErrorMsg=='')
			{
				lErrorMsg=lErrorMsg+"General investigation attachments are mandatory ";
			} 
		} 
		}
			
		
	}
	

	if(ipTestsCount>0)
  	{
		if(lErrorMsg=='')
		{
			lErrorMsg=lErrorMsg+"IP investigation attachments are mandatory ";
	    }
  	}
	
	//Mandatory Check for Complaint
	
	//if(hospId!=null && hospId!="EHS34")
	//if(hospGovu!=null && hospGovu!="G")
	//{
	//if((schemeId=='CD201')||(schemeId=='CD202' && hospType!='G') ||(schemeId=='CD202' && hospType=='G' && ipCase) )
	//{
	
	var complaint=document.forms[0].complaints;
	var otherComplaint='';
	if(schemeId!=null && patientScheme!=null && hospType!=null && schemeId=='CD202' &&  patientScheme=='CD501' && hospType=='G' )
	{
		otherComplaint=document.getElementById("otherComplaint").value;
	}

	var complaintCnt=0;
	for (var x=0;x<complaint.length;x++)
	{
		if (complaint[x].selected)
		{
			complaintCnt++;
		}
	}
	if(complaintCnt==0)
	{
		if(lErrorMsg==''){
			 if(schemeId!=null && patientScheme!=null && hospType!=null && schemeId=='CD202' &&  patientScheme=='CD501' && hospType=='G' )
			 {
		          if(otherComplaint==null || otherComplaint=='')
				  lErrorMsg=lErrorMsg+"Select Complaint or Other Complaint ";
			 }
			 else
        lErrorMsg=lErrorMsg+"Select Complaint ";
		}
        if(lField=='')
        lField='complaints';   
	}
//alert(lField);
//Mandatory Check for Patient Complaint
var patientComplaint=document.forms[0].patientComplaint;



var patientComplaintCnt=0;
for (var x=0;x<patientComplaint.length;x++)
{
  if (patientComplaint[x].selected)
  {
   patientComplaintCnt++;
  }
}
if(patientComplaintCnt==0){
 if(lErrorMsg==''){

	 if(schemeId!=null && patientScheme!=null && hospType!=null && schemeId!='CD202' &&  patientScheme!='CD501' && hospType!='G' )
	 {
        lErrorMsg=lErrorMsg+"Select Patient Complaint ";
      }
 }
        if(lField=='')
        lField='patientComplaint';   
}
//Mandatory check for Present History
if(document.forms[0].presentHistory.value=='' || document.forms[0].presentHistory.value==null){
	if(lErrorMsg==''){
             lErrorMsg=lErrorMsg+"Enter History Of Present Illness ";
		}
        if(lField=='')
        lField='presentHistory';   
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
			if (!document.forms[0].panChewing[0].checked && !document.forms[0].panChewing[1].checked)
			{
				if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Pan Chewing Options ";
		      	}
		        if(lField=='')
		        lField='panChewing';   
			}
			else
			{
				var panChewingSubList=document.forms[0].panChewing;
				for(var z=0;z<panChewingSubList.length;z++)
					{
					if(panChewingSubList[z].checked)
						{
						personalHistVal = personalHistVal+",PanChewing$"+panChewingSubList[z].value;
						}
					}
			}
			if (!document.forms[0].gutka[0].checked && !document.forms[0].gutka[1].checked)
			{
				if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Gutka Options ";
		      	}
		        if(lField=='')
		        lField='gutka';   
			}
			else
			{
					var gutkaSubList=document.forms[0].gutka;
					for(var z=0;z<gutkaSubList.length;z++)
						{
						if(gutkaSubList[z].checked)
							{
							personalHistVal = personalHistVal+",Gutka$"+gutkaSubList[z].value;
							}
						}
			}
			if (!document.forms[0].fingerSucking[0].checked && !document.forms[0].fingerSucking[1].checked)
			{
				if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions  Finger/Thumb Sucking Options ";
		      	}
		        if(lField=='')
		        lField='fingerSucking';   
			}
			else
			{
				var fingerSuckingSubList=document.forms[0].fingerSucking;
				for(var z=0;z<fingerSuckingSubList.length;z++)
					{
					if(fingerSuckingSubList[z].checked)
						{
						personalHistVal = personalHistVal+",FingerSucking$"+fingerSuckingSubList[z].value;
						}
					}
			}
			
			if (!document.forms[0].nailBiting[0].checked && !document.forms[0].nailBiting[1].checked)
			{
				if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions  Nail/Lip Biting Options ";
		      	}
		        if(lField=='')
		        lField='nailBiting';   
			}
			else
			{
				var nailBitingSubList=document.forms[0].nailBiting;
				for(var z=0;z<nailBitingSubList.length;z++)
					{
					if(nailBitingSubList[z].checked)
						{
						personalHistVal = personalHistVal+",NailBiting$"+nailBitingSubList[z].value;
						}
					}
			}
			if (!document.forms[0].tongueBiting[0].checked && !document.forms[0].tongueBiting[1].checked)
			{
				if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions  Tongue biting/Thrusting Options ";
		      	}
		        if(lField=='')
		        lField='tongueBiting';   
			}
			else
			{
				var tongueBitingSubList=document.forms[0].tongueBiting;
				for(var z=0;z<tongueBitingSubList.length;z++)
					{
					if(tongueBitingSubList[z].checked)
						{
						personalHistVal = personalHistVal+",TongueBiting$"+tongueBitingSubList[z].value;
						}
					}
			}
			
			if (!document.forms[0].mouthBreathing[0].checked && !document.forms[0].mouthBreathing[1].checked)
			{
				if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Mouth Breathing Options ";
		      	}
		        if(lField=='')
		        lField='mouthBreathing';   
			}
			else
			{
				var mouthBreathingSubList=document.forms[0].mouthBreathing;
				for(var z=0;z<mouthBreathingSubList.length;z++)
					{
					if(mouthBreathingSubList[z].checked)
						{
						personalHistVal = personalHistVal+",MouthBreathing$"+mouthBreathingSubList[z].value;
						}
					}
			}
			
			if (!document.forms[0].bruxism[0].checked && !document.forms[0].bruxism[1].checked)
			{
				if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Teeth clenching / Bruxism Options ";
		      	}
		        if(lField=='')
		        lField='bruxism';   
			}
			else
			{
				var bruxismSubList=document.forms[0].bruxism;
				for(var z=0;z<bruxismSubList.length;z++)
					{
					if(bruxismSubList[z].checked)
						{
						personalHistVal = personalHistVal+",Bruxism$"+bruxismSubList[z].value;
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
/*pavan*/
// mandatory check for the drug history

var drughstYes=document.forms[0].drughistoryid[0].checked;
var drughstNo=document.forms[0].drughistoryid[1].checked;
if(drughstYes==false && drughstNo==false)
	{
	if(lErrorMsg=='')
	{
	lErrorMsg=lErrorMsg+"please select the drug history";
	}
	if(lField=='')
	    lField='drughistoryid';
	}
   
if(drughstYes==true)
	{
	var x=document.getElementById("drughistory").value;
	if(x==null || x=="")
		{
		if(lErrorMsg=='')
		{
		lErrorMsg="please write the drug history in the text box";
		}
		 if(lField=='')
			    lField='drughistory'; 
		}
	
	}

//mandatory check for Medical History
if(document.getElementById('medicalDtlsid11').checked==true)
	{
	if(document.getElementById("showMedicalTextval").value=="" || document.getElementById("showMedicalTextval").value==null)
		{
		if(lErrorMsg==''){
			lErrorMsg="please fill the medical history others text box";
			}
		
		 if(lField=='')
			    lField='medicalDtlsid11';
		}
	}
	


//Mandatory Check validation For Examination Findings and its sublist
var examinFnds=document.forms[0].examinationFnd;
var examinCount=0;
var examinSubCount=0;
var examFndsVal="";
var schemeId='${schemeId}';
for(var i=0;i<examinFnds.length;i++)
{
	if(examinFnds[i].checked)
	{
		//examinCount++;
		var examinFndsValue=examinFnds[i].value;		
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
		else if(examinFndsValue=='GE15' && schemeId=='CD202'){
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

//mandatory check for EXTRA ORAL EXAMINATIONS

if($("#subdental0 option:selected").text()=="Other Lymph nodes")
	{
	if((document.getElementById("subdentalrltxt") != null) && (document.getElementById("subdentalrltxt").value=="" || document.getElementById("subdentalrltxt").value==null))
		{
		if(lErrorMsg==''){
			lErrorMsg=lErrorMsg+"please enter the  Regional Lymphadenopathy other lymoh nodes remarks";
			}
	   if(lField=='')
    	lField="subdentalrltxt";  
		}
	}

if($("#subdental0 option:selected").text()=="Subemental" || $("#subdental0 option:selected").text()=="Submandibular" || $("#subdental0 option:selected").text()=="Submandibular&Submental" )
{
	if($("#dntsublistrl0 option:selected").text()=="" || $("#dntsublistrl0 option:selected").text()==null )
		{
		if(lErrorMsg==''){
			lErrorMsg=lErrorMsg+"please select any one of the sub list multiple select box of Regional Lymphadenopathy";
			}
	   if(lField=='')
    	lField="subdental0"; 
		}
}
if($("#subdental1 option:selected").text()=="Others")
  {
	if(document.getElementById("subdentaljawstxt1").value=="" || document.getElementById("subdentaljawstxt1").value==null)
	{
	if(lErrorMsg==''){
		lErrorMsg=lErrorMsg+"please enter the jaws other remarks";
	}
   if(lField=='')
	lField="subdental1";  
	}
  }

//mandatory check for the Soft Tissue Examination

for(var x=0 ; x<7 ; x++)
	{
if(document.getElementById("intraoral"+x).checked==true)
	{
	if(document.getElementById("dntsublistoral"+x).value=="" || document.getElementById("dntsublistoral"+x).value==null)
		{
		if(lErrorMsg==''){
			lErrorMsg=lErrorMsg+"Write the soft tissue examination "+ $("#intraoralLabel"+x).text()+" remarks";
			}
	if(lField=='')
    	lField="dntsublistoral"+x;  
		}
	}
   
	}
var permanentobj=document.forms[0].permanentDent;
if(permanentobj!=null)
	{
var mainCompId1 = document.forms[0].permanentDent.value;
if(mainCompId1=="CH91")
	{
	
	if(document.getElementById('otherPermntDent').value=="-1")
		{
		if(lErrorMsg==''){
			lErrorMsg="please select the other permannet drop odwn";
			}
			 if(lField=='')
				    lField='otherPermntDent';
		}
	
	var otherPerm = document.getElementById('otherPermntDent').value;
	if(otherPerm != null && (otherPerm =="CH102" || otherPerm == "CH103" || otherPerm == "CH104" || otherPerm == "CH105"))
		{

		if(document.getElementById('otherPermText').value=="" || document.getElementById('otherPermText').value==null)
			{
			if(lErrorMsg==''){
				lErrorMsg="please write the remarks for the  other permannet dentation selected remarks";
				}
				 if(lField=='')
					    lField='otherPermText';
			}
		}
	}
	}
//mandatory check for occlusion
var occTypeman =document.getElementById('occlusion').value;
if(occTypeman != null && occTypeman == "CH83")
	{
	var otherdiv=document.getElementById('occlusionOther').value;
	if(otherdiv==null || otherdiv=="")
		{
		if(lErrorMsg==''){
		lErrorMsg="please write the occlusion remarks in the text box";
		}
		 if(lField=='')
			    lField='occlusion';
		}
	 
	}
//teeth saving 
var carriesdecidous = [];
$.each($("input[name='childCaries']:checked"), function(){            
	carriesdecidous.push($(this).val());
});
var x=carriesdecidous.join("~");
document.getElementById("carriesdecidous").value=x;

var missingdecidous=[];
$.each($("input[name='childMissing']:checked"), function(){            
   missingdecidous.push($(this).val());
});
var x=missingdecidous.join("~");
document.getElementById("missingdecidous").value=x;

  var mobiledecidous=[];
  $.each($("input[name='childMobile']:checked"), function(){            
	  mobiledecidous.push($(this).val());
  });
  var x=mobiledecidous.join("~");
 document.getElementById("mobiledecidous").value=x;
 
var grosslydecadedecidous=[];
$.each($("input[name='grosslyDecayed']:checked"), function(){            
	grosslydecadedecidous.push($(this).val());
});
var x=grosslydecadedecidous.join("~");
document.getElementById("grosslydecadedecidous").value=x;

var carriespermanent=[];
$.each($("input[name='caries']:checked"), function(){            
	carriespermanent.push($(this).val());
});
var x=carriespermanent.join("~");
document.getElementById("carriespermanent").value=x;


var rootstumppermannet=[];
$.each($("input[name='decayed']:checked"), function(){            
	rootstumppermannet.push($(this).val());
});
var x=rootstumppermannet.join("~");
document.getElementById("rootstumppermannet").value=x;


var mobilitypermanent=[];
$.each($("input[name='mobile']:checked"), function(){            
	mobilitypermanent.push($(this).val());
});
var x=mobilitypermanent.join("~");
document.getElementById("mobilitypermanent").value=x;


var attritionpermanent=[];
$.each($("input[name='attrition']:checked"), function(){            
	attritionpermanent.push($(this).val());
});
var x=attritionpermanent.join("~");
document.getElementById("attritionpermanent").value=x;

var missingpermanent=[];
$.each($("input[name='missing']:checked"), function(){            
	missingpermanent.push($(this).val());
});
var x=missingpermanent.join("~");
document.getElementById("missingpermanent").value=x;
   
//OTHER PPERMANNET DENT 
var permanentobj=document.forms[0].permanentDent;
if(permanentobj!=null)
	{
var mainCompId1 = document.forms[0].permanentDent.value;

var otherPerm = document.getElementById('otherPermntDent').value;
var otherpermtext= document.getElementById('otherPermText').value;
var othrpremnt="";
if(mainCompId1!=null)
	{
	if(otherPerm!=null)
		{
		if(otherpermtext!=null)
		
			othrpremnt=otherPerm+'~'+otherpermtext;
		}
	}
	
document.getElementById("otherpermanent").value=othrpremnt;
	}
//probing depth saving 
var probeDepth=document.forms[0].probeDepth;
if(probeDepth!=null)
   {
var probingdepth="";
var finalprobingdepth="";
for(var i=0;i<32;i++)
   {
   var x="probeDepth"+i;
   if(document.getElementById("probeDepth"+i).value!=null && document.getElementById("probeDepth"+i).value!="")
	   {
	   probingdepth=x+"@"+document.getElementById("probeDepth"+i).value;
	   if(finalprobingdepth!=null && finalprobingdepth!="")
	   finalprobingdepth=finalprobingdepth+"~"+probingdepth;
	   else
		   finalprobingdepth=probingdepth;
	   }
   }
  }
document.getElementById("probingdepth").value=finalprobingdepth;


if(symptomCount==0)
	{
	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Add Symptom ";
		}
     if(lField=='')
    	 lField='addSymptom';
	}


	//}
	//}
var opIP='';

if(document.forms[0].patientType[1].checked)
{
	 opIP=document.forms[0].patientType[1].value;
}
else if(document.forms[0].patientType[0].checked)
{
	 opIP=document.forms[0].patientType[0].value;
}
else if(document.forms[0].patientType[2].checked)
{
	 opIP=document.forms[0].patientType[2].value;
}
//Commented chronic OP
/*else if(document.forms[0].patientType[2].checked)
{
	 opIP=document.forms[0].patientType[2].value;
}*/	   
//if(hospId !=null && hospId!="EHS34")
if(hospGovu!=null && hospGovu!="G")
{
 if(genInventList.length==0 && genOldList.length==0 && (opIP !='' && opIP != 'OP' && opIP != 'ChronicOP')){
 if(lErrorMsg==''){
	 
        lErrorMsg=lErrorMsg+"Select General Investigations ";
		}
        if(lField=='')
        lField='genInvestigations';   
}
}

//Check to enable dtrs form
if(checkType=='SaveDTRS')
{
	if(document.getElementById("decidiousdentsel").checked==false && document.getElementById("permanentdentsel").checked==false){
		if(lErrorMsg=='')
		{
			lErrorMsg=lErrorMsg+"Either of Deciduous Dentition or Permanent Dentition must be selected";
	    }
	}
			if(document.getElementById("decidiousdentsel").checked==true){
	//Check Decidious Dentition
	var deciduousDent=document.getElementById("deciduousDent");
	  if(deciduousDent != null)
	   {
		var decDentition=document.getElementById("deciduousDent").length; 
		var mainCompId="";
		if(document.getElementById("deciduousDent").selectedIndex == "-1");
		{
		 var options = $('#deciduousDent > option:selected');
		     if(options.length == 0){
		         bootbox.alert('Please select atleast one option in Deciduous Dentition');
		         document.getElementById("deciduousDent").focus();
		         return false;
			
		}
		for (var x=0;x<decDentition;x++)
		{
			 mainCompId = document.forms[0].deciduousDent[x].value;
			
			
			if (document.forms[0].deciduousDent[x].selected)
			{
					if(mainCompId == "CH90")
					{
						    if ($("input[type='checkbox'][name='childMissing']:checked").length == 0){
						    	bootbox.alert("Please select atleast one checkbox in Deciduous Dentition Missing ");
						    	document.getElementById("deciduousDent").focus();
						         return false;
						    }
					}
					if(mainCompId == "CH89")
					{
						 if ($("input[type='checkbox'][name='childMobile']:checked").length == 0){
							 bootbox.alert("Please select atleast one checkbox in Deciduous Dentition Mobile ");
							 document.getElementById("deciduousDent").focus();
					         return false;
					    }
					}
					if(mainCompId == "CH88")
					{
						 if ($("input[type='checkbox'][name='grosslyDecayed']:checked").length == 0){
							 bootbox.alert("Please select atleast one checkbox in Deciduous Dentition Grossly Decayed ");
							 document.getElementById("deciduousDent").focus();
					         return false;
					    }
					}
					if(mainCompId == "CH87")
					{
						 if ($("input[type='checkbox'][name='childCaries']:checked").length == 0){
							 bootbox.alert("Please select atleast one checkbox in Deciduous Dentition Caries ");
							 document.getElementById("deciduousDent").focus();
					         return false;
					    }
					}
					
					
					
			}
			
		}
	  }	
	}
}
if(document.getElementById("permanentdentsel").checked==true){
	//Check permanent dentition
	  var permanent=document.getElementById("permanentDent");
	   if(permanent!= null)
	   {
		var premDentition=document.getElementById("permanentDent").length; 
		var mainCompId="";
	     var options = $('#permanentDent > option:selected');
	     if(options.length == 0){
	         bootbox.alert('Please select atleast one option in Permanent Dentition');
	         document.getElementById("permanentDent").focus();
	         return false;
	     }
		    
		for (var x=0;x<premDentition;x++)
		{
			 mainCompId = document.forms[0].permanentDent[x].value;
			
			
			if (document.forms[0].permanentDent[x].selected)
			{
					if(mainCompId == "CH95")
					{
						    if ($("input[type='checkbox'][name='missing']:checked").length == 0){
						    	 bootbox.alert("Please select atleast one checkbox in Permanent Dentition Missing ");
						    	 document.getElementById("permanentDent").focus();
						         return false;
						    }
					}
					if(mainCompId == "CH96")
					{
						 if ($("input[type='checkbox'][name='caries']:checked").length == 0){
							 bootbox.alert("Please select atleast one checkbox in Permanent Dentition Caries ");
							 document.getElementById("permanentDent").focus();
					         return false;
					    }
					}
					if(mainCompId == "CH92")
					{
						 if ($("input[type='checkbox'][name='decayed']:checked").length == 0){
							 bootbox.alert("Please select atleast one checkbox in Permanent Dentition Root stump / Grossly Decayed ");
							 document.getElementById("permanentDent").focus();
					         return false;
					    }
					}
					if(mainCompId == "CH93")
					{
						 if ($("input[type='checkbox'][name='mobile']:checked").length == 0){
							 bootbox.alert("Please select atleast one checkbox in Permanent Dentition Mobility ");
							 document.getElementById("permanentDent").focus();
					         return false;
					    }
					}
					if(mainCompId == "CH94")
					{
						 if ($("input[type='checkbox'][name='attrition']:checked").length == 0){
							 bootbox.alert("Please select atleast one checkbox in Permanent Dentition Attrition / Abrasion ");
							 document.getElementById("permanentDent").focus();
					         return false;
						    }
					}
					
					
			}
			
		}
		

	   }
}
	/*if(opIP == '')
	{
	if(lErrorMsg==''){
	       lErrorMsg=lErrorMsg+"Select Patient Type";
		   }
	        if(lField=='')
	        lField='patientType';  
	}*/
	if(lErrorMsg!=null && (lErrorMsg!='' || lErrorMsg!=undefined) && lErrorMsg.length>0)
	{
		var fr=partial(focusBox,document.getElementById(lField));
		bootbox.alert(lErrorMsg);
	    	focusBox(document.getElementById(lField));
	    return;
	 }
	 
	else
	{
		var saveFlag="Yes";
		var fr=partial(registerCase,personalHistVal,examFndsVal,patId,saveFlag,checkType);
		jqueryConfirmMsg('Case Registration Confirmation','Do you want to Save and generate DTRS Form?',fr);
	}
}
 if(checkType=='DTRS')
{
	
	if(lErrorMsg=='')
	{
		
		return true;
	}
	else
	{
		return false;
	}
}
else if(checkType=='submit')
{
if(opIP == '')
{
if(lErrorMsg==''){
       lErrorMsg=lErrorMsg+"Select Patient Type";
	   }
        if(lField=='')
        lField='patientType';  
}
else
{
	//Mandatory Check for Diagnosis Details
	var hospType='${hospType}';
	
	//if(hospId !=null && hospId!="EHS34")
	if(hospGovu!=null && hospGovu!="G")
	{
	if((schemeId=='CD202' && opIP == 'OP' && '${dentalFlg}'!='Y' )||(opIP=='IP')||(hospType!='G'))
	{
		
	if(document.getElementById("diagType").value=="-1" || document.getElementById("diagType").value=="")
		{
		if(lErrorMsg==''){
	        lErrorMsg=lErrorMsg+"Select Diagnosis Type ";
			}
	        if(lField=='')
	        lField='diagType'; 
		}
	if(document.getElementById("mainCatName").value=="-1" || document.getElementById("mainCatName").value=="")
	{
	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select Diagnosis Main Category Name ";
		}
        if(lField=='')
        lField='mainCatName'; 
	}
	if(document.getElementById("catName").value=="-1" || document.getElementById("catName").value=="")
	{
	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select Diagnosis Category Name ";
		}
        if(lField=='')
        lField='catName'; 
	}
	if(document.getElementById('subCatName').value=="-1" || document.getElementById('subCatName').value=="")
	{
	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select Diagnosis Sub Category Name ";
		}
        if(lField=='')
        lField='subCatName'; 
	}
	if(document.getElementById("diseaseName").value=="-1" || document.getElementById("diseaseName").value=="")
	{
	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select Diagnosis Disease Name ";
		}
        if(lField=='')
        lField='diseaseName'; 
	}
	if(document.getElementById("disAnatomicalName").value=="-1" || document.getElementById("disAnatomicalName").value=="")
	{
	if(lErrorMsg==''){
       lErrorMsg=lErrorMsg+"Select Diagnosis Disease Anatomical Name ";
	   }
        if(lField=='')
        lField='disAnatomicalName'; 
	}
	}
	}
	
}

if(opIP == 'IP' || opIP == 'DOP')
{
	//Mandatory Check for Therapy Details
	
	if(catCount==0 && specOld.length==0)
		{
		medOrSur='';
		if(lErrorMsg==''){
	        lErrorMsg=lErrorMsg+"Add Speciality (Category,ICD Category,ICD Procedure Details) ";
			}
	     if(lField=='')
	    	 lField='addSpeciality';
		}
	else
		{
			/*Added by Srikalyan for Dental Changes related to TG.  
			*/
				 if(lErrorMsg==null || lErrorMsg=='' || lErrorMsg==undefined || lErrorMsg.length==0)
					{
					 //(comboProcIds!=null && comboProcIds!='' && comboProcIds.length>0) ||
					 if(		(nonComboProcIds!=null && nonComboProcIds!='' && nonComboProcIds.length>0) ||
								(standaloneProcIds!=null && standaloneProcIds!='' && standaloneProcIds.length>0))
									{
										lErrorMsg=checkDentalTGCond();
										if(lErrorMsg!=null && lErrorMsg!='' && lErrorMsg!=' ' && lField=='')
											lField='asriCatName';
									}	
					}	 
			/*End for Dental Changes related to TG.  
			*/
		}
	if(document.getElementById("treatingDocLabel").style.display=='')
	{
		if(document.getElementById("treatingDocRmks").value=='' || document.getElementById("treatingDocRmks").value==null)
		{
			if(lErrorMsg==''){
				lErrorMsg=lErrorMsg+"Enter Treating Doctor Remarks ";
				}
			if(lField=='')
			lField='treatingDocRmks'; 
		}
	}
//Mandatory Check for IP No
if(document.forms[0].patientType[1].checked && (document.forms[0].ipNo.value=='' || document.forms[0].ipNo.value==null)){
 if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Enter IP NO ";
		}
        if(lField=='')
        lField='ipNo';   
	}
else if(document.forms[0].patientType[2].checked && (document.forms[0].ipNo.value=='' || document.forms[0].ipNo.value==null)){
	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Enter DOP NO ";
		}
        if(lField=='')
        lField='ipNo';   
}
//Mandatory Check for Admission type
if(document.forms[0].patientType[1].checked && (document.forms[0].admissionType.value=='-1' || document.forms[0].admissionType.value==null)){
 if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select Admission Type ";
		}
        if(lField=='')
        lField='admissionType';   
	}
//Mandatory Check for Proposed Surgery Date
var catCode=document.getElementById("asriCatName").value;
//if(catCode!=null && catCode.indexOf("S")!=-1 && hospId=="EHS34")
if(catCode!=null && catCode.indexOf("S")!=-1 && hospGovu=="G")
{
	if(document.forms[0].ipDate.value=='' || document.forms[0].ipDate.value==null)
	{
	 if(lErrorMsg==''){
	        lErrorMsg=lErrorMsg+"Enter Proposed Surgery Date ";
			}
	        if(lField=='')
	        lField='ipDate';   
	}
}
//if(hospId!="EHS34")
if(hospGovu!=null && hospGovu!="G")
{
if(document.forms[0].ipDate.value=='' || document.forms[0].ipDate.value==null)
{
 if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Enter Proposed Surgery Date ";
		}
        if(lField=='')
        lField='ipDate';   
}
}
//Mandatory Check for Remarks
if(document.forms[0].remarks.value=='' || document.forms[0].remarks.value==null)
{
 if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Enter Remarks ";
		}
        if(lField=='')
        lField='remarks';   
}
//Mandatory Check for Patient Diagnosed By
//if(hospId!=null && hospId!="EHS34")
if(hospGovu!=null && hospGovu!="G")
		{
if(document.forms[0].ipDiagnosedBy.value=='-1' || document.forms[0].ipDiagnosedBy.value==null){
	  if(lErrorMsg==''){
	         lErrorMsg=lErrorMsg+"Select IP Patient Diagnosed by ";
			 }
	         if(lField=='')
	         lField='ipDiagnosedBy';   
	}
	//Mandatory Check For Doctor Name Drop Down List
	if(schemeId=='CD202')
	{
if(document.forms[0].ipDoctorName.value==null || document.forms[0].ipDoctorName.value==''  ||     document.forms[0].ipDoctorName.value=='-1'){
	 	 if(lErrorMsg==''){
	         lErrorMsg=lErrorMsg+"Select IP Doctor Name ";
			 }
	         if(lField=='')
	         lField='ipDoctorName';
	    
		}
	}
	
	}

//if(hospId!=null && hospId=="EHS34")
if(hospGovu!=null && hospGovu=="G")
{
	
	if(document.forms[0].ipDoctorName.value==null || document.forms[0].ipDoctorName.value==''  ||     document.forms[0].ipDoctorName.value=='-1'){
	 	 if(lErrorMsg==''){
	         lErrorMsg=lErrorMsg+"Select IP Consultant Name ";
			 }
	         if(lField=='')
	         lField='ipDoctorName';
	    
		}
}


		
		if(!document.forms[0].legalCase[0].checked && !document.forms[0].legalCase[1].checked)
		{
			if(lErrorMsg==''){
					lErrorMsg=lErrorMsg+"Select Medico Legal Case ";
				}
				if(lField=='')
					lField='legalCase';
		}
		if(document.forms[0].legalCase[0].checked==true)
		{
			if(document.getElementById("legalCaseNo").value==null || document.getElementById("legalCaseNo").value=='')
			{
				if(lErrorMsg==''){
					lErrorMsg=lErrorMsg+"Enter Legal Case No ";
				}
				if(lField=='')
					lField='legalCaseNo';
			}
			if(document.getElementById("policeStatName").value==null || document.getElementById("policeStatName").value=='')
			{
				if(lErrorMsg==''){
					lErrorMsg=lErrorMsg+"Enter Police Station Name ";
				}
				if(lField=='')
					lField='policeStatName';
			}
		}
	}
if(opIP == 'OP' || opIP == 'ChronicOP')
{
	//Mandatory Check for Prescription Details
	var schemeId=document.getElementById("scheme").value;
	var patientScheme=document.getElementById("patientScheme").value;
	if(schemeId=='CD202')
	{
	if(drugCount==0)
		{
		if(lErrorMsg==''){
	        lErrorMsg=lErrorMsg+"Add Drug (Main Group Name,Therapeutic Main Group Name,Pharmacologicl SubGroup Name,Chemical SubGroup Name,Chemical Substance Name,Route,Strength,Dosage,Medication Period Details) ";
			}
	     if(lField=='')
	    	 lField='addDrug';
		}
	}
	//Mandatory Check For Doctor Details
var hospType='${hospType}';
	if(schemeId=='CD202'  || patientScheme!='CD501' || hospType=='C')
		{
	if(document.forms[0].diagnosedBy.value=='-1' || document.forms[0].diagnosedBy.value==null){
	  if(lErrorMsg==''){
	         lErrorMsg=lErrorMsg+"Select OP Patient Diagnosed by ";
			 }
	         if(lField=='')
	         lField='diagnosedBy';   
		}
	
		if(document.forms[0].doctorName.value==null || document.forms[0].doctorName.value=='' || document.forms[0].doctorName.value=='-1'){
	 	 if(lErrorMsg==''){
	         lErrorMsg=lErrorMsg+"Select OP Doctor Name ";
			 }
	         if(lField=='')
	         lField='doctorName';
	    
		}
		}



		//Mandatory Check For Doctor Name TG OP
		else
		{
		if(schemeId=='CD202')
		{

	
	if(document.getElementById("consultationDataOld")==null && document.getElementById("consultationDataNew").style.display=="none")
	{
		 if(lErrorMsg==''){
			 //alert("Location line 1901");
	         lErrorMsg=lErrorMsg+"Please Add Consultation Details ";
			 }
	         if(lField=='')
	         lField='diagnosedBy';   
		

	}	
if(document.getElementById("consultationDataOld")&& document.getElementById("consultationDataNew"))
{

	if(document.getElementById("consultationDataOld").style.display=="none" && document.getElementById("consultationDataNew").style.display=="none" )
	{

		  if(lErrorMsg==''){
			  //alert("Location line 1915");
		         lErrorMsg=lErrorMsg+"Please Add Consultation Details ";
				 }
		         if(lField=='')
		         lField='diagnosedBy';   
			}
	
	
			if(document.getElementById("consultationDataOld").style.display=="none" && document.getElementById("consultationDataNew").style.display=="none" )
			{
			if(document.forms[0].diagnosedBy.value=='-1' || document.forms[0].diagnosedBy.value==null){
				  if(lErrorMsg==''){
				         lErrorMsg=lErrorMsg+"Select OP Patient Diagnosed by ";
						 }
				         if(lField=='')
				         lField='diagnosedBy';   
					}

			
		if(document.forms[0].unitName.value==null || document.forms[0].unitName.value==''){
			 	 if(lErrorMsg==''){
			         lErrorMsg=lErrorMsg+"Select Unit Name ";
					 }
			         if(lField=='')
			         lField='unitName';
			    
				}
		if(document.forms[0].unitHodName.value==null || document.forms[0].unitHodName.value==''){
			 if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Select Unit HOD Name ";
				 }
		        if(lField=='')
		        lField='unitHodName';
		   
			}
			}
}

		}
		
		
		} 
	}
if(opIP == 'OP')
{
	//Mandatory check for OP No
	if(document.forms[0].opNo.value=='' || document.forms[0].opNo.value==null){
 	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Enter OP NO ";
		}
        if(lField=='')
        lField='opNo';   
	}
	if(document.forms[0].opRemarks.value=='' || document.forms[0].opRemarks.value==null){
 	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Enter OP Remarks ";
		}
        if(lField=='')
        lField='opRemarks';   
	}
	if(document.forms[0].opDate.value=='' || document.forms[0].opDate.value==null){
 	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select OP Date ";
		}
        if(lField=='')
        lField='opDate';   
	} 

}

if(lErrorMsg!=null && (lErrorMsg!='' || lErrorMsg!=undefined) && lErrorMsg.length>0)
{
	var fr=partial(focusBox,document.getElementById(lField));
	//var fr=partial(focusFieldView,lField);
	//jqueryAlertMsg('Case Registration Page Mandatory Fields',lErrorMsg,fr);
   bootbox.alert(lErrorMsg);
    	focusBox(document.getElementById(lField));
    return;
 }
else
{
	var saveFlag="Submit";
	var fr=partial(registerCase,personalHistVal,examFndsVal,patId,saveFlag,checkType);
	jqueryConfirmMsg('Case Registration Confirmation','Do you want to register patient case?',fr);
}
}
} 

function fn_saveDetailsWithoutMandate(checkType)
{
document.getElementById("checkType").value=checkType;
fn_saveDetails();
}


function fn_saveDetails(){
	var patId=document.getElementById("patientNo").value;
	document.getElementById("drugs").value=drugs;
	document.getElementById("symptoms").value=symptoms;
	//Mandatory Check validation For Personal History and its sublist
	var personalHistory=document.forms[0].personalHistory;
	var personalCount=0;
	var personalSubCount=0;
	var personalHistVal="";
	var genTestsCount=0;
	var updTestsCount=0;
	var ipTestsCount=0;
	var lErrorMsg='';
	var lField='';

	var schemeId=document.getElementById("scheme").value;
	var patientScheme=document.getElementById("patientScheme").value;
	var hospType=document.getElementById("hosptype").value;
	var hospId = document.getElementById("hospitalId").value;
	var hospGovu = document.getElementById("hospGovu").value;
	//verifyUsedPackage();


		document.getElementById("Save").disabled=false;
		//document.getElementById("Save").className='but';
		document.getElementById("saveDTRS").disabled=false;
		//document.getElementById("saveDTRS").className='but';
		document.getElementById("Reset").disabled=false;
		//document.getElementById("Reset").className='but';
		
		//teeth saving 
		var carriesdecidous = [];
	    $.each($("input[name='childCaries']:checked"), function(){            
	    	carriesdecidous.push($(this).val());
	    });
	    var x=carriesdecidous.join("~");
	   document.getElementById("carriesdecidous").value=x;
	   
	   var missingdecidous=[];
	   $.each($("input[name='childMissing']:checked"), function(){            
		   missingdecidous.push($(this).val());
	   });
	   var x=missingdecidous.join("~");
	  document.getElementById("missingdecidous").value=x;
	  
		  var mobiledecidous=[];
		  $.each($("input[name='childMobile']:checked"), function(){            
			  mobiledecidous.push($(this).val());
		  });
		  var x=mobiledecidous.join("~");
		 document.getElementById("mobiledecidous").value=x;
		 
		var grosslydecadedecidous=[];
		$.each($("input[name='grosslyDecayed']:checked"), function(){            
			grosslydecadedecidous.push($(this).val());
		});
		var x=grosslydecadedecidous.join("~");
		document.getElementById("grosslydecadedecidous").value=x;
		
		var carriespermanent=[];
		$.each($("input[name='caries']:checked"), function(){            
			carriespermanent.push($(this).val());
		});
		var x=carriespermanent.join("~");
		document.getElementById("carriespermanent").value=x;
		
		
		var rootstumppermannet=[];
		$.each($("input[name='decayed']:checked"), function(){            
			rootstumppermannet.push($(this).val());
		});
		var x=rootstumppermannet.join("~");
		document.getElementById("rootstumppermannet").value=x;
		
		
		var mobilitypermanent=[];
		$.each($("input[name='mobile']:checked"), function(){            
			mobilitypermanent.push($(this).val());
		});
		var x=mobilitypermanent.join("~");
		document.getElementById("mobilitypermanent").value=x;
		
		
		var attritionpermanent=[];
		$.each($("input[name='attrition']:checked"), function(){            
			attritionpermanent.push($(this).val());
		});
		var x=attritionpermanent.join("~");
		document.getElementById("attritionpermanent").value=x;
		
		var missingpermanent=[];
		$.each($("input[name='missing']:checked"), function(){            
			missingpermanent.push($(this).val());
		});
		var x=missingpermanent.join("~");
		document.getElementById("missingpermanent").value=x;
		   
	   //OTHER PPERMANNET DENT 
		var permanentobj=document.forms[0].permanentDent;
		if(permanentobj!=null)
			{
		var mainCompId1 = document.forms[0].permanentDent.value;
		
	   var otherPerm = document.getElementById('otherPermntDent').value;
	   var otherpermtext= document.getElementById('otherPermText').value;
	   var othrpremnt="";
		if(mainCompId1!=null)
			{
			if(otherPerm!=null)
				{
				if(otherpermtext!=null)
				
					othrpremnt=otherPerm+'~'+otherpermtext;
				}
			}
		document.getElementById("otherpermanent").value=othrpremnt;
			}
	    //probing depth saving 
	   var probeDepth=document.forms[0].probeDepth;
	  if(probeDepth!=null)
		   {
	   var probingdepth="";
	   var finalprobingdepth="";
	   for(var i=0;i<32;i++)
		   {
		   var x="probeDepth"+i;
		   if(document.getElementById("probeDepth"+i).value!=null && document.getElementById("probeDepth"+i).value!="")
			   {
			   probingdepth=x+"@"+document.getElementById("probeDepth"+i).value;
			   if(finalprobingdepth!=null && finalprobingdepth!="")
			   finalprobingdepth=finalprobingdepth+"~"+probingdepth;
			   else
				   finalprobingdepth=probingdepth;
			   }
		   }
	      }
	   document.getElementById("probingdepth").value=finalprobingdepth;	
	
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
						
				
					/*var drugUseSubList=document.forms[0].drugUse;
					for(var z=0;z<drugUseSubList.length;z++)
						{
						if(drugUseSubList[z].checked)
							{
							personalHistVal = personalHistVal+",DrugUse$"+drugUseSubList[z].value;
							}
						}*/
				
				
					var betelNutSubList=document.forms[0].betelNut;
					for(var z=0;z<betelNutSubList.length;z++)
						{
						if(betelNutSubList[z].checked)
							{
							personalHistVal = personalHistVal+",BetelNut$"+betelNutSubList[z].value;
							}
						}
					
					var panChewingSubList=document.forms[0].panChewing;
					for(var z=0;z<panChewingSubList.length;z++)
						{
						if(panChewingSubList[z].checked)
							{
							personalHistVal = personalHistVal+",PanChewing$"+panChewingSubList[z].value;
							}
						}
					
					var gutkaSubList=document.forms[0].gutka;
							for(var z=0;z<gutkaSubList.length;z++)
								{
								if(gutkaSubList[z].checked)
									{
									personalHistVal = personalHistVal+",Gutka$"+gutkaSubList[z].value;
									}
								}
					
					var fingerSuckingSubList=document.forms[0].fingerSucking;
						for(var z=0;z<fingerSuckingSubList.length;z++)
							{
							if(fingerSuckingSubList[z].checked)
								{
								personalHistVal = personalHistVal+",FingerSucking$"+fingerSuckingSubList[z].value;
								}
							}
					
					var nailBitingSubList=document.forms[0].nailBiting;
						for(var z=0;z<nailBitingSubList.length;z++)
							{
							if(nailBitingSubList[z].checked)
								{
								personalHistVal = personalHistVal+",NailBiting$"+nailBitingSubList[z].value;
								}
							}
					
						var tongueBitingSubList=document.forms[0].tongueBiting;
						for(var z=0;z<tongueBitingSubList.length;z++)
							{
							if(tongueBitingSubList[z].checked)
								{
								personalHistVal = personalHistVal+",TongueBiting$"+tongueBitingSubList[z].value;
								}
							}
					
					var mouthBreathingSubList=document.forms[0].mouthBreathing;
						for(var z=0;z<mouthBreathingSubList.length;z++)
							{
							if(mouthBreathingSubList[z].checked)
								{
								personalHistVal = personalHistVal+",MouthBreathing$"+mouthBreathingSubList[z].value;
								}
							}
					
					var bruxismSubList=document.forms[0].bruxism;
						for(var z=0;z<bruxismSubList.length;z++)
							{
							if(bruxismSubList[z].checked)
								{
								personalHistVal = personalHistVal+",Bruxism$"+bruxismSubList[z].value;
								}
							}
					
				 /* var betelLeafSubList=document.forms[0].betelLeaf;
					for(var z=0;z<betelLeafSubList.length;z++)
						{
						if(betelLeafSubList[z].checked)
							{
							personalHistVal = personalHistVal+",BetelLeaf$"+betelLeafSubList[z].value+")";
							}
						}	*/		
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
	var hospId=document.getElementById("hospitalId").value;
	var hospGovu = document.getElementById("hospGovu").value;
	
	for(var i=0;i<examinFnds.length;i++)
	{
		if(examinFnds[i].checked)
		{
			//examinCount++;
			var examinFndsValue=examinFnds[i].value;		
			var examinFndsName=document.forms[0].elements[examinFndsValue].name;		
			var subType=document.forms[0].elements[examinFndsValue].type;
			
			if(examinFndsValue=='GE11'){
				//if(hospId!=null && hospId!='EHS34')
				if(hospGovu!=null && hospGovu!="G")
					{
					var tempType= '';
					
					if(document.forms[0].temp[0].checked==true) tempType='C';
					if(document.forms[0].temp[1].checked==true) tempType='F';
					examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+tempType;
					examFndsVal = examFndsVal+"#";					
					}
				else 
					{
				
					examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+tempType;
					examFndsVal = examFndsVal+"#";	
					}
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
		
	/*for (var x=0;x<pastHistory.length;x++)
	{
		if (pastHistory[x].checked)
		{
			if(pastHistory[x].value=="PH10")
			{
				if(document.getElementById("pastHistrySurg").value!='' || document.getElementById("pastHistrySurg").value!=null)
				{
					var pastHistrySugValue=document.getElementById("pastHistrySurg").value;
					document.getElementById("pastHistrySurg").value=pastHistrySugValue.replace(/\r\n/g,' ');
					document.getElementById("pastHistrySurg").value=document.getElementById("pastHistrySurg").value.replace(/\n/g,' ');
					
				}
			}
		}
	}*/
	for(var temp=1;temp<document.forms[0].elements.length;temp++)
    {
       if(document.forms[0].elements[temp].type=="file")
       {
       	   var val=document.forms[0].elements[temp].value;
           if(val==null || val=="")
           	{
        	 if(document.forms[0].elements[temp].name.charAt(0)=='g')
        	   genTestsCount=genTestsCount+1;
			 else if(document.forms[0].elements[temp].name.charAt(0)=='u')
        	   updTestsCount=updTestsCount+1;
        	 else if(document.forms[0].elements[temp].name.charAt(0)=='a')
        		 ipTestsCount=ipTestsCount+1;
			if(lField=='')
				lField=''+document.forms[0].elements[temp].id+'';
           	}
           else
			{
				var rtVal=chkSpecailChars(val.substring(val.lastIndexOf('\\')+1,val.lastIndexOf('.')));
				var fullFileName=val.substring(val.lastIndexOf('\\')+1);
				var fileName1=val.substring(val.lastIndexOf('\\')+1,val.lastIndexOf('.'));
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
				 var sub=val.substring(val.lastIndexOf('.')+1);
				 if((sub=='exe')||(sub=='EXE') || (sub=='rar')||(sub=='RAR') || (sub=='war')||(sub=='WAR')|| (sub=='zip')||(sub=='ZIP'))
				  {
					 if(lErrorMsg=='')
		            	{
							lErrorMsg=lErrorMsg+"Cannot upload exe,rar,war files ";
		       	        }
				  } 
				}
			}
         }
    if(schemeId!='CD202'  && hospType!='G')
    {
	if(genTestsCount>0)
  	{
		if(lErrorMsg=='')
		{
			lErrorMsg=lErrorMsg+"General investigation attachments are mandatory";
	    } 
  	}
	if(updTestsCount>0)
		{
			if(lErrorMsg=='')
			{
				lErrorMsg=lErrorMsg+"General investigation attachments are mandatory ";
			} 
		} 	
	if(ipTestsCount>0)
  	{
		if(lErrorMsg=='')
		{
			lErrorMsg=lErrorMsg+"IP investigation attachments are mandatory ";
	    }
  	}
    }

	/*Added by Srikalyan for Dental Changes related to TG.  
	*/
	if(lErrorMsg==null || lErrorMsg=='' || lErrorMsg==undefined || lErrorMsg.length==0)
		{
			var localSchemeId=document.getElementById("scheme").value;
			if(localSchemeId!=null && localSchemeId!='' && localSchemeId=='CD202')
				{
					if((comboProcIds!=null && comboProcIds!='' && comboProcIds.length>0) ||
							(nonComboProcIds!=null && nonComboProcIds!='' && nonComboProcIds.length>0) ||
							(standaloneProcIds!=null && standaloneProcIds!='' && standaloneProcIds.length>0))
								{
									lErrorMsg=checkDentalTGCond();
									if(lErrorMsg!=null && lErrorMsg!='' && lErrorMsg!=' ' && lField=='')
										lField='asriCatName';
								}	
				}								
		}
	/*End for Dental Changes related to TG.  
	*/
    
    /*Added by Venkatesh for NIMS and TG Government hospitals (for submit and DTRS Generation skipping mandatory fields)*/
    var checkType='';
        if(document.getElementById("checkType"))
        	checkType=document.getElementById("checkType").value;
        else
        	checkType="Save";
    	
    var opIP='';
   
    if(document.forms[0].patientType[1].checked)
    {
    	 opIP=document.forms[0].patientType[1].value;
    }
    else if(document.forms[0].patientType[0].checked)
    {
    	 opIP=document.forms[0].patientType[0].value;
    }
    /*else if(document.forms[0].patientType[2].checked)
    {
    	 opIP=document.forms[0].patientType[2].value;
    }*/
    
    
    //if(((hospId!=null && hospId=="EHS34")||(schemeId=="CD202" && hospType=="G")) && checkType!="Save" &&  opIP!="ChronicOP")
    if(((schemeId=="CD202" && hospType=="G")) && checkType!="Save" &&  opIP!="ChronicOP")	
    {

        if("submit")
        {
    	if(opIP == '')
    	{
    	if(lErrorMsg==''){
    	       lErrorMsg=lErrorMsg+"Select Patient Type";
    		   }
    	        if(lField=='')
    	        lField='patientType';  
    	}
    	if(opIP == 'IP')
    	{
    		//Mandatory Check for Therapy Details
    		
    		if(catCount==0 && specOld.length==0)
    			{
    			medOrSur='';
    			if(lErrorMsg==''){
    		        lErrorMsg=lErrorMsg+"Add Speciality (Category,ICD Category,ICD Procedure Details) ";
    				}
    		     if(lField=='')
    		    	 lField='addSpeciality';
    			}
    		if(document.getElementById("treatingDocLabel").style.display=='')
    		{
    			if(document.getElementById("treatingDocRmks").value=='' || document.getElementById("treatingDocRmks").value==null)
    			{
    				if(lErrorMsg==''){
    					lErrorMsg=lErrorMsg+"Enter Treating Doctor Remarks ";
    					}
    				if(lField=='')
    				lField='treatingDocRmks'; 
    			}
    		}
    	//Mandatory Check for IP No
    	
    	if(document.forms[0].ipNo.value=='' || document.forms[0].ipNo.value==null){
    	 if(lErrorMsg==''){
    	        lErrorMsg=lErrorMsg+"Enter IP NO ";
    			}
    	        if(lField=='')
    	        lField='ipNo';   
    		}
    	//Mandatory Check for Admission type
    	if(document.forms[0].admissionType.value=='-1' || document.forms[0].admissionType.value==null){
    	 if(lErrorMsg==''){
    	        lErrorMsg=lErrorMsg+"Select Admission Type ";
    			}
    	        if(lField=='')
    	        lField='admissionType';   
    		}
    	//Mandatory Check for Proposed Surgery Date
    	var catCode=document.getElementById("asriCatName").value;
    	//if(catCode!=null && catCode.indexOf("S")!=-1 && hospId=="EHS34")
    	if(catCode!=null && catCode.indexOf("S")!=-1 && hospGovu=="G")
    	{
    		if(document.forms[0].ipDate.value=='' || document.forms[0].ipDate.value==null)
    		{
    		 if(lErrorMsg==''){
    		        lErrorMsg=lErrorMsg+"Enter Proposed Surgery Date ";
    				}
    		        if(lField=='')
    		        lField='ipDate';   
    		}
    	}
    	//if(hospId!="EHS34")
    	if(hospGovu!=null && hospGovu!="G")
    	{
    	if(document.forms[0].ipDate.value=='' || document.forms[0].ipDate.value==null)
    	{
    	 if(lErrorMsg==''){
    	        lErrorMsg=lErrorMsg+"Enter Proposed Surgery Date ";
    			}
    	        if(lField=='')
    	        lField='ipDate';   
    	}
    	}
    	//Mandatory Check for Remarks
    	if(document.forms[0].remarks.value=='' || document.forms[0].remarks.value==null)
    	{
    	 if(lErrorMsg==''){
    	        lErrorMsg=lErrorMsg+"Enter Remarks ";
    			}
    	        if(lField=='')
    	        lField='remarks';   
    	}
    	//Mandatory Check for Patient Diagnosed By
    	//if(hospId!=null && hospId!="EHS34")
    	if(hospGovu!=null && hospGovu!="G")
    			{
    	if(document.forms[0].ipDiagnosedBy.value=='-1' || document.forms[0].ipDiagnosedBy.value==null){
    		  if(lErrorMsg==''){
    		         lErrorMsg=lErrorMsg+"Select IP Patient Diagnosed by ";
    				 }
    		         if(lField=='')
    		         lField='ipDiagnosedBy';   
    		}
    		//Mandatory Check For Doctor Name Drop Down List
    		if(schemeId=='CD202')
    		{
    	if(document.forms[0].ipDoctorName.value==null || document.forms[0].ipDoctorName.value==''  ||     document.forms[0].ipDoctorName.value=='-1'){
    		 	 if(lErrorMsg==''){
    		         lErrorMsg=lErrorMsg+"Select IP Doctor Name ";
    				 }
    		         if(lField=='')
    		         lField='ipDoctorName';
    		    
    			}
    		}
    		
    		}

    	//if(hospId!=null && hospId=="EHS34")
    	if(hospGovu!=null && hospGovu=="G")
    	{
    		if(document.forms[0].ipDoctorName.value==null || document.forms[0].ipDoctorName.value==''  ||     document.forms[0].ipDoctorName.value=='-1'){
    		 	 if(lErrorMsg==''){
    		         lErrorMsg=lErrorMsg+"Enter IP Consultant Name ";
    				 }
    		         if(lField=='')
    		         lField='ipDoctorName';
    		    
    			}
    	}


    			
    			if(!document.forms[0].legalCase[0].checked && !document.forms[0].legalCase[1].checked)
    			{
    				if(lErrorMsg==''){
    						lErrorMsg=lErrorMsg+"Select Medico Legal Case ";
    					}
    					if(lField=='')
    						lField='legalCase';
    			}
    			if(document.forms[0].legalCase[0].checked==true)
    			{
    				if(document.getElementById("legalCaseNo").value==null || document.getElementById("legalCaseNo").value=='')
    				{
    					if(lErrorMsg==''){
    						lErrorMsg=lErrorMsg+"Enter Legal Case No ";
    					}
    					if(lField=='')
    						lField='legalCaseNo';
    				}
    				if(document.getElementById("policeStatName").value==null || document.getElementById("policeStatName").value=='')
    				{
    					if(lErrorMsg==''){
    						lErrorMsg=lErrorMsg+"Enter Police Station Name ";
    					}
    					if(lField=='')
    						lField='policeStatName';
    				}
    			}
    		}

    	if(opIP == 'OP')
    	{
    		//Mandatory Check for Prescription Details
    		var schemeId=document.getElementById("scheme").value;
    		var patientScheme=document.getElementById("patientScheme").value;
    		/*if(schemeId=='CD201')
    		{
    		if(drugCount==0)
    			{
    			if(lErrorMsg==''){
    		        lErrorMsg=lErrorMsg+"Add Drug (Main Group Name,Therapeutic Main Group Name,Pharmacologicl SubGroup Name,Chemical SubGroup Name,Chemical Substance Name,Route,Strength,Dosage,Medication Period Details) ";
    				}
    		     if(lField=='')
    		    	 lField='addDrug';
    			}
    		}*/
    		//Mandatory Check For Doctor Details
    	var hospType='${hospType}';
    		/*if(schemeId=='CD201'  || patientScheme!='CD501' || hospType=='C')
    			{
    		if(document.forms[0].diagnosedBy.value=='-1' || document.forms[0].diagnosedBy.value==null){
    		  if(lErrorMsg==''){
    		         lErrorMsg=lErrorMsg+"Select OP Patient Diagnosed by ";
    				 }
    		         if(lField=='')
    		         lField='diagnosedBy';   
    			}
    		
    			if(document.forms[0].doctorName.value==null || document.forms[0].doctorName.value=='' || document.forms[0].doctorName.value=='-1'){
    		 	 if(lErrorMsg==''){
    		         lErrorMsg=lErrorMsg+"Select OP Doctor Name ";
    				 }
    		         if(lField=='')
    		         lField='doctorName';
    		    
    			}
    			}*/



    			//Mandatory Check For Doctor Name TG OP
    		
    			//if(schemeId=='CD202' || hospId=="EHS34")
    			if(schemeId=='CD202' || hospGovu=="G")
    			{

    		
    		if(document.getElementById("consultationDataOld")==null && document.getElementById("consultationDataNew").style.display=="none")
    		{
    			 if(lErrorMsg==''){
    				 //alert("Location line 2793");
    		         lErrorMsg=lErrorMsg+"Please Add Consultation Details ";
    				 }
    		         if(lField=='')
    		         lField='diagnosedBy';   
    			

    		}	
    	if(document.getElementById("consultationDataOld")&& document.getElementById("consultationDataNew"))
    	{

    		if(document.getElementById("consultationDataOld").style.display=="none" && document.getElementById("consultationDataNew").style.display=="none" )
    		{

    			  if(lErrorMsg==''){
    				  	//alert("Location line 2808");
    			         lErrorMsg=lErrorMsg+"Please Add Consultation Details ";
    					 }
    			         if(lField=='')
    			         lField='diagnosedBy';   
    				}
    		
    		
    				if(document.getElementById("consultationDataOld").style.display=="none" && document.getElementById("consultationDataNew").style.display=="none" )
    				{
    				if(document.forms[0].diagnosedBy.value=='-1' || document.forms[0].diagnosedBy.value==null){
    					  if(lErrorMsg==''){
    					         lErrorMsg=lErrorMsg+"Select OP Patient Diagnosed by ";
    							 }
    					         if(lField=='')
    					         lField='diagnosedBy';   
    						}

    				
    			if(document.forms[0].unitName.value==null || document.forms[0].unitName.value==''){
    				 	 if(lErrorMsg==''){
    				         lErrorMsg=lErrorMsg+"Select Unit Name ";
    						 }
    				         if(lField=='')
    				         lField='unitName';
    				    
    					}
    			if(document.forms[0].unitHodName.value==null || document.forms[0].unitHodName.value==''){
    				 if(lErrorMsg==''){
    			        lErrorMsg=lErrorMsg+"Select Unit HOD Name ";
    					 }
    			        if(lField=='')
    			        lField='unitHodName';
    			   
    				}
    				}
    	}

    			}
    			
    			
    			 
    		}
    	if(opIP == 'OP')
    	{
    		//Mandatory check for OP No
    		if(document.forms[0].opNo.value=='' || document.forms[0].opNo.value==null){
    	 	if(lErrorMsg==''){
    	        lErrorMsg=lErrorMsg+"Enter OP NO ";
    			}
    	        if(lField=='')
    	        lField='opNo';   
    		}
    		if(document.forms[0].opRemarks.value=='' || document.forms[0].opRemarks.value==null){
    	 	if(lErrorMsg==''){
    	        lErrorMsg=lErrorMsg+"Enter OP Remarks ";
    			}
    	        if(lField=='')
    	        lField='opRemarks';   
    		}
    		if(document.forms[0].opDate.value=='' || document.forms[0].opDate.value==null){
    	 	if(lErrorMsg==''){
    	        lErrorMsg=lErrorMsg+"Select OP Date ";
    			}
    	        if(lField=='')
    	        lField='opDate';   
    		} 

    	}

        }
    	if(lErrorMsg!=null && (lErrorMsg!='' || lErrorMsg!=undefined) && lErrorMsg.length>0)
    	{
    		var fr=partial(focusBox,document.getElementById(lField));
    		//var fr=partial(focusFieldView,lField);
    		//jqueryAlertMsg('Case Registration Page Mandatory Fields',lErrorMsg,fr);
    	   bootbox.alert(lErrorMsg);
    	    	focusBox(document.getElementById(lField));
    	    return;
    	 }
   
    	else
    	{
    		
    		
		var checkType=document.getElementById("checkType").value;
		 if(checkType=='DTRS')
		 {
		 	if(lErrorMsg=='')
		 	{
		 		return true;
		 	}
		 	else
		 	{
		 		return false;
		 	}
		 }
    	if(checkType=="SaveDTRS")
    	{
    		var saveFlag="Yes";
    	var fr=partial(registerCase,personalHistVal,examFndsVal,patId,saveFlag,checkType);
    	jqueryConfirmMsg('Case Registration Confirmation','Do you want to save and generate DTRS?',fr);

    	}
    	else

    	{
    		var saveFlag="Submit";
    		var fr=partial(registerCase,personalHistVal,examFndsVal,patId,saveFlag,checkType);
    		jqueryConfirmMsg('Case Registration Confirmation','Do you want to register patient case?',fr);	
    	}
    	
    	}
    }
    else
    {
	if(lErrorMsg!=null && (lErrorMsg!='' || lErrorMsg!=undefined) && lErrorMsg.length>0)
	{
		var fr=partial(focusBox,document.getElementById(lField));
		//var fr=partial(focusFieldView,lField);
		//jqueryAlertMsg('Case Registration Page Mandatory Fields',lErrorMsg,fr);
	   bootbox.alert(lErrorMsg);
	    	focusBox(document.getElementById(lField));
	    return;
	 }
	else
	{
	var saveFlag="Yes";
	var checkType="Save";
	var fr=partial(registerCase,personalHistVal,examFndsVal,patId,saveFlag,checkType);
	jqueryConfirmMsg('Case Registration Confirmation','Do you want to Save?',fr);

	
	}
	}
}


function registerCase(personalHistVal,examFndsVal,patId,saveFlag,checkType)
{
	 var selInvData='';
	 var selGenInvData='';
	 var updateGenInvData='';
	
	 var consultationData='';
	 var selectedList1  = document.getElementById('addTests');  
	 //var selectedList2  = document.getElementById('investigationSel');

	 for(var i=0;i<genInventList.length;i++)
	 	{
        var ltext='';
        var lvalue='';
        var lId='';
        var price='';
        var investInfo = genInventList[i].split("~");
          ltext = investInfo[2]; 
	   	   lId =  investInfo[1]; 
	   	   price= investInfo[3]; 
          if((selGenInvData!=null || selGenInvData!='') && selGenInvData.length>0)
          {
       	   selGenInvData=selGenInvData+'~';
          }
                 
          selGenInvData=selGenInvData+ltext+'$'+lId+'$'+price;  
  			
    	}


	 /*Added by venkatesh to save consultation doctors details*/
	 
	
	 for(var i=0;i<consultDataList.length;i++)
	 	{
		 	
     
     var consultInfo = consultDataList[i].split("~");
     
       if((consultationData!=null || consultationData!='') && consultationData.length>0)
       {
    	   consultationData=consultationData+'~';
       }
              
       consultationData=consultationData+consultInfo[0]+'$'+consultInfo[1]+'$'+consultInfo[2];

       
			
 	}

	 	
 	for(var i=0;i<updateGenInvestLst.length;i++)
	 	{
        var ltext='';
        var lvalue='';
        var lId='';
        var investInfo = updateGenInvestLst[i].split("~");
          ltext = investInfo[2]; 
	   	   lId =  investInfo[1]; 
          if((updateGenInvData!=null || updateGenInvData!='') && updateGenInvData.length>0)
          {
       	   updateGenInvData=updateGenInvData+'~';
          }          
          updateGenInvData=updateGenInvData+ltext+'$'+lId;  
    	}
 	
	 for(var c=0;c<spec.length;c++)
		{
			var specValues=spec[c].split("~");
			if(specValues[8]!='NA' && specValues[7]!='NA')
			{
				if((selInvData!=null || selInvData!='') && selInvData.length>0)
		           {
		           selInvData=selInvData+'~';
		           }          
		           selInvData=selInvData+specValues[8]+'$'+specValues[7]+'$'+specValues[2]; 
			}				   
		}
		//document.forms[0].addTests.value=selGenInvData;
		document.forms[0].investigationsSel.value=selInvData;
		document.forms[0].personalHistVal.value=personalHistVal;
   		document.forms[0].examFndsVal.value=examFndsVal;
   		document.getElementById("Submit").disabled=true;
		document.getElementById("Submit").className='butdisable';
		document.getElementById("Save").disabled=true;
		document.getElementById("Save").className='butdisable';
		document.getElementById("saveDTRS").disabled=true;
		document.getElementById("saveDTRS").className='butdisable';
		document.getElementById("Reset").disabled=true;
		document.getElementById("Reset").className='butdisable';
		var subdentalJawTxt ="";
		if(document.getElementById("subdental1").value == 'CH47'||document.getElementById("subdental1").value == 'CH49'||document.getElementById("subdental1").value == 'CH50')
			subdentalJawTxt = document.getElementById("subdentaljawstxt1").value;
		else
			subdentalJawTxt = document.getElementById("subdentaljaws1").value;
		var url="./patientDetails.do?actionFlag=saveCaseDetails&patientId="+patId+"&addTests="+selGenInvData+"&updateTests="+updateGenInvData+"&saveFlag="+saveFlag+"&checkType="+checkType+"&consultationData="+consultationData+"&genInvestRemove="+genInvestRemove+"&specRemove="+specRemove
		+"&dentalFlg="+'${dentalFlg}'+"&subdentalJawTxt="+subdentalJawTxt;
		+"&dentalFlg="+'${dentalFlg}';
		if(document.getElementById("treatingDocLabel").style.display=='')
		{
		url=url+"&treatingDocRmks="+document.getElementById("treatingDocRmks").value;
		}
		if(document.forms[0].caseId.value!=''){
			url=url+"&caseId="+document.forms[0].caseId.value;
		}
		
		/*document.forms[0].action="./patientDetails.do?actionFlag=saveCaseDetails&patientId="+patId+"&addTests="+selGenInvDatatherapyId="+therapies+"&doctorType="+doctorType */;
		document.forms[0].action=url;
		document.forms[0].method="post";
		document.forms[0].submit();
}



function getsubSymptomLst(){
	var mainSymptomCode=null;
	document.getElementById("mainSymptomCode").value="";
	document.forms[0].subSymptomName.options.length=1;
	document.getElementById('subSymptomCode').value="";
	document.forms[0].symptomName.options.length=1;
	document.getElementById('symptomCode').value="";
	getSymptomLst();
	if(document.getElementById("mainSymptomName").value=="-1")
		{
		return false;
		}
	else
		{
	mainSymptomCode=document.getElementById("mainSymptomName").value;
	document.getElementById("mainSymptomCode").value=mainSymptomCode;
	var xmlhttp;
    var url;

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
    	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else{
            if(resultArray!=null)
               {
            	resultArray = resultArray.replace("[","");
                resultArray = resultArray.replace("@]*","");
                var symptomList = resultArray.split("@,"); 

            		document.forms[0].subSymptomName.options.length=0;
                    document.forms[0].subSymptomName.options[0]=new Option("---select---","-1");
          	  		
                
				 for(var i = 0; i<symptomList.length;i++)
               		 {	
                    
                     var arr=symptomList[i].split("~");
                   
                     if(arr[1]!=null && arr[0]!=null)
                     {
                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                    	 document.forms[0].subSymptomName.options[i+1] =new Option(val1,val2);
                     }
                	}
            	 }
      		  }
            }
   	 	}
    
	url = "./patientDetails.do?actionFlag=getSubSymptomLst&callType=Ajax&mainSymptomCode="+mainSymptomCode;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
		}
}
function getSubLevelDetails(input)
{
	if(input.checked)
	{
		
		var personalHabits='';
		var KnownAllg='';
		if(input.name=="Known Allergies")
			{
			if(input.value=="PR5.1")
			{
				KnownAllg=KnownAllg+'<table width="100%" border="1"><tr><td>'+
				'Allergic to Medicine:<input type="radio" name="AllMed" id="AllMed" value="AllMedYes" onclick="displayTextBox(this)" title="Yes"/>Yes'+
				'<input type="radio" name="AllMed" id="AllMed" value="AllMedNo" onclick="displayTextBox(this)" title="No"/>No'+
				'<div id="AllMedDiv" style="display:none">Specify:<input type="text" name="AllMedRemrk" id="AllMedRemrk" maxlength="300" title="Remark" style="width:7em" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpecify(this)"/></div></td></tr>'+
				'<tr><td>Allergic to Substance other than medicine:<br><input type="radio" name="AllSub" id="AllSub" value="AllSubYes" onclick="displayTextBox(this)" title="Yes"/>Yes'+
				'<input type="radio" name="AllSub" id="AllSub" value="AllSubNo" onclick="displayTextBox(this)" title="No"/>No'+
				'<div id="AllSubDiv" style="display:none">Specify:<input type="text" name="AllSubRemrk" id="AllSubRemrk" maxlength="300" title="Remark" style="width:7em" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpecify(this)"/></div></td></tr></table>';
                
				document.getElementById("Known AllergiesTd").innerHTML=KnownAllg;
			}else
				{  
			document.getElementById("Known AllergiesTd").innerHTML="";
				}
			}
		if(input.name=="Habits/Addictions")
		 {
			if(input.value=="PR6.1")
			{
		  personalHabits=personalHabits+'<table width="100%" border="1"><tr><td style="background: #1E4B89;color: #FFF;text-align: center;">Deleterious Habits</td><tr><td>'+
	      'Alcohol:<input type="radio" name="alcohol" id="alcohol" value="Regular" title="Regular"/>Regular'+
	      '<input type="radio" name="alcohol" id="alcohol" value="Occasional" title="Occasional"/>Occasional'+
	      '<input type="radio" name="alcohol" id="alcohol" value="Teetotaler" title="Teetotaler"/>Teetotaler </td></tr>'+
	     	'<tr><td>Tobacco:<input type="radio" name="tobacco" id="tobacco" value="Snuff" onclick="displayPackYears(this)" title="Snuff"/>Snuff'+
	      '<input type="radio" name="tobacco" id="tobacco" value="Chewable" onclick="displayPackYears(this)" title="Chewable"/>Chewable'+
	      '<input type="radio" name="tobacco" id="tobacco" value="Smoking" onclick="displayPackYears(this)" title="Smoking"/>Smoking'+
	      '<div id="smokingTd" style="display:none">'+
	     'Pack :<input type="text" name="packNo" id="packNo" maxlength="2" title="Smoking Pack No" style="width:7em" onchange="validateNumber(\'Smoking Pack No\',this)"/>  (per day)<br>'+
	      'Years:<input type="text" name="smokeYears" id="smokeYears" maxlength="2" title="Smoking Years" style="width:7em" onchange="validateNumber(\'Smoking Years\',this)"/>  (since years)</div></td></tr>'+
	     '<tr><td>Betel nut:<input type="radio" name="betelNut" id="betelNut" value="Yes" title="Yes"/>Yes'+
	      '<input type="radio" name="betelNut" id="betelNut" value="No" title="No"/>No</td></tr>'+
	      '<tr><td>Pan Chewing:<input type="radio" name="panChewing" id="panChewing" value="Yes" title="Yes"/>Yes'+
	      '<input type="radio" name="panChewing" id="panChewing" value="No" title="No"/>No</td></tr>'+
	      '<tr><td>Gutka :<input type="radio" name="gutka" id="gutka" value="Yes" title="Yes"/>Yes'+
	      '<input type="radio" name="gutka" id="gutka" value="No" title="No"/>No</td></tr></tr>'+
		  '<tr><tr><td style="background: #1E4B89;color: #FFF;text-align: center;">Parafunctional Habits</td></tr><td>Finger/Thumb Sucking:<input type="radio" name="fingerSucking" id="fingerSucking" value="Yes" title="Yes"/>Yes'+
	      '<input type="radio" name="fingerSucking" id="fingerSucking" value="No" title="No"/>No</td></tr>'+
		  '<tr><td>Nail/Lip Biting:<input type="radio" name="nailBiting" id="nailBiting" value="Yes" title="Yes"/>Yes'+
	      '<input type="radio" name="nailBiting" id="nailBiting" value="No" title="No"/>No</td></tr>'+
		  '<tr><td>Tongue biting/Thrusting:<input type="radio" name="tongueBiting" id="tongueBiting" value="Yes" title="Yes"/>Yes'+
	      '<input type="radio" name="tongueBiting" id="tongueBiting" value="No" title="No"/>No</td></tr>'+
		  '<tr><td>Mouth Breathing:<input type="radio" name="mouthBreathing" id="mouthBreathing" value="Yes" title="Yes"/>Yes'+
	      '<input type="radio" name="mouthBreathing" id="mouthBreathing" value="No" title="No"/>No</td></tr>'+
		  '<tr><td>Teeth clenching / Bruxism:<input type="radio" name="bruxism" id="bruxism" value="Yes" title="Yes"/>Yes'+
		  '<input type="radio" name="bruxism" id="bruxism" value="No" title="No"/>No</td></tr></table>';
		document.getElementById("Habits/AddictionsTd").innerHTML=personalHabits;
			}
		else
			{
		document.getElementById("Habits/AddictionsTd").innerHTML="";
			}
		}
		
		if(input.name=="Drug History")
		{
		if(input.value=="PR8.2")
			{
			KnownAllg=KnownAllg+'<table width="100%" border="1"><tr><td><div id="drughst" style="display:none">Specify:<input type="text" name="drughstRemrk" id="drughstRemrk" maxlength="300" title="Remark" style="width:7em" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpecify(this)"/></div></td></tr></table>';
			 displayTextBox(document.getElementById('PR8.2').id);
			}
		else
			{
			KnownAllg=KnownAllg+'<table width="100%" border="1"><tr><td><div id="drughst" style="display:none">Specify:<input type="text" name="drughstRemrk" id="drughstRemrk" maxlength="300" title="Remark" style="width:7em" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpecify(this)"/></div></td></tr></table>';
			 displayTextBox(document.getElementById("PR8.1"));
			}
		
		}
	}
 else
	{
	document.getElementById("Habits/AddictionsTd").innerHTML="";
	} 
	// parent.fn_resizePage();
}
function displayTextBox(input)
{
	if(input.value=="AllMedYes")
	document.getElementById("AllMedDiv").style.display="";
	else if(input.value=="AllSubYes")
		document.getElementById("AllSubDiv").style.display="";
	else if(input.value=="AllSubNo"){
		document.getElementById("AllSubDiv").style.display="none";}
	else if(input.value=="AllMedNo"){
		document.getElementById("AllMedDiv").style.display="none";		
	}
	else if(input.value=="PR8.2"){
		document.getElementById("drughst").style.display="";}
	else if(input.value=="PR8.1"){
		document.getElementById("AllSubDiv").style.display="none";}
	//parent.fn_resizePage();
}
function getComplaintType(flag){
	var patComCode = "";
	if(flag=="onLoad")
	{
	patComCode = document.getElementById("patComplaintCode").value.split("~"); 
	}
	else
	{
		document.getElementById("complaintCode").value="";
		document.forms[0].patientComplaint.options.length=0;
		document.getElementById("patComplaintCode").value="";
	}
	var complaint=document.forms[0].complaints;
	

	var complaintCnt=0;
	for (var x=0;x<complaint.length;x++)
	{
		if (complaint[x].selected)
		{
			complaintCnt++;
		}
//		if(complaint[x].selected==true && complaint[x].value=="S18.19")
//			{
//			document.getElementById("swCompTable").style.display="";
//			}
//		else if(complaint[x].selected==true && complaint[x].value!="S18.19"){
//			document.getElementById("swCompTable").style.display="none";
//		}
	}
	
	if(complaintCnt==0)
	{
		return false;
	}
	else
		{
		var complainLen=document.getElementById("complaints").length;
		
		var mainCompId="";
		for (var x=0;x<complainLen;x++)
		{
			if (document.forms[0].complaints[x].selected)
			{
				mainCompId = mainCompId + document.forms[0].complaints[x].value+"~";
			}
		}
		
		mainCompId=mainCompId.substring(0,mainCompId.length-1);
		
		document.getElementById("complaintCode").value=mainCompId;
		
		
		
		
	//var mainCompId=document.getElementById("complaints").value;
	
	//document.getElementById("complaintCode").value=mainCompId;
	var xmlhttp;
	var url;
	var docType; 
		
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
	url = "./patientDetails.do?actionFlag=getComplaintList&callType=Ajax&mainCompId="+mainCompId;    
	xmlhttp.onreadystatechange=function()
		{
   		if(xmlhttp.readyState==4)
   		{
      	 	var resultArray=xmlhttp.responseText;
      		if(resultArray.trim()=="SessionExpired*"){
      			jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
        	}
        	else{
       			resultArray = resultArray.replace("[","");
       			resultArray = resultArray.replace("@]*","");
       			var lsubCompList = resultArray.split("@,"); 
		       	if(lsubCompList.length>0)
       			{
       				document.forms[0].patientComplaint.options.length=0;
		            for(var i = 0; i<lsubCompList.length;i++)
             		{	
                  		var arr=lsubCompList[i].split("~"); 
                  		if(arr[1]!=null && arr[0]!=null)
                  		{
                      		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                      		var val2 = arr[0].replace(/^\s+|\s+$/g,"");  
                      		
                      		document.forms[0].patientComplaint.options[i] =new Option(val1,val2);
                      		for(var j=0;j<patComCode.length;j++){
                                if(patComCode[j]==val2)
                              	  document.forms[0].patientComplaint[i].selected = true;                
                                  } 
                            if(document.getElementById("patComplaintCode").value==""){
                      		document.forms[0].presentHistory.disabled=true;
                      		document.forms[0].presentHistory.value="";}
                            else{
                            	document.forms[0].presentHistory.disabled=false;
                                }
                  		}
              		}
        		} 
   			}
   		}			
	}
	xmlhttp.open("Post",url,false);
	xmlhttp.send(null);
	}
}
function showTemp(){document.forms[0].GE11.value="";}
function displayPackYears(input)
{
	if(input.value=="Smoking")
	document.getElementById("smokingTd").style.display="";
	else
		document.getElementById("smokingTd").style.display="none";
	//parent.fn_resizePage();
}
function displaydrugtext(input)
{
	if(input.value=="Yes")
		document.getElementById("drugstd").style.display="";
	else
		document.getElementById("drugstd").style.display="none";
	//parent.fn_resizePage();
}

function getSymptomLst(){
	var mainSymptomCode=null;var subSymptomCode=null;
	document.getElementById("mainSymptomCode").value="";
	document.getElementById("subSymptomCode").value="";
	document.forms[0].symptomName.options.length=1;
	document.getElementById('symptomCode').value="";
	if(document.getElementById("mainSymptomName").value=="-1" || document.getElementById("subSymptomName").value=="-1")
		{
		return false;
		}
	else
		{
	mainSymptomCode=document.getElementById("mainSymptomName").value;
	document.getElementById("mainSymptomCode").value=mainSymptomCode;
	subSymptomCode=document.getElementById("subSymptomName").value;
	document.getElementById("subSymptomCode").value=subSymptomCode;
	var xmlhttp;
    var url;

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
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else{
            if(resultArray!=null)
               {
            	resultArray = resultArray.replace("[","");
                resultArray = resultArray.replace("@]*","");
                var symptomList = resultArray.split("@,"); 

            		document.forms[0].symptomName.options.length=0;
                    document.forms[0].symptomName.options[0]=new Option("---select---","-1");
          	  		
                
				 for(var i = 0; i<symptomList.length;i++)
               		 {	
                    
                     var arr=symptomList[i].split("~");
                   
                     if(arr[1]!=null && arr[0]!=null)
                     {
                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                    	 document.forms[0].symptomName.options[i+1] =new Option(val1,val2);
                     }
                	}
            	 }
      		  }
            }
   	 	}
    
	url = "./patientDetails.do?actionFlag=getSymptomLst&callType=Ajax&mainSymptomCode="+mainSymptomCode+"&subSymptomCode="+subSymptomCode;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
		}
}
function partial(func /*, 0..n args */) {
	 var args = new Array();
	 for (var i = 1; i < arguments.length; i++) { args.push(arguments[i]); }
	  return function() {
	    var allArguments = args.concat(Array.prototype.slice.call(arguments));
	    return func.apply(this, allArguments);
	  };
}
function confirmDentalPatientTypeReset()
{
		addGenInvestigation();
}
function addGenInvestigation(){
	
	var otherInvest=false;
	if(document.getElementById("invOthers"))
    otherInvest=document.getElementById("invOthers").checked;
	if(document.getElementById("otherInvestCount"))
		{
	var otherInvestCountTemp=document.getElementById("otherInvestCount").value;
	
	if(otherInvestCount<=otherInvestCountTemp)
		{
		otherInvestCount=otherInvestCountTemp;
		}
		}
	var testId='';
	if(!otherInvest)
		{
	if(document.getElementById("genBlockInvestName").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("genBlockInvestName"));
	alert("Please select Investigation Block");
	partial(focusBox,document.getElementById("genBlockInvestName"));
	return false;
	}
	if(document.getElementById("genInvestigations").value=="-1")
	{
		var fr=partial(focusBox,document.getElementById("genInvestigations"));
		alert("Please Select Investigations");
		partial(focusBox,document.getElementById("genInvestigations"));
		return false;
	}
		}
	else 
	{
	var otherInvName=document.getElementById("otherInvName").value.trim();
	if(otherInvName==null || otherInvName=="")
		{
		alert("Please Enter Investigation Name");
		document.getElementById("otherInvName").focus();
		document.getElementById("otherInvName").value='';
		return false;
		}

	for(var i=0;i<genOldList.length;i++)
	{
	var invValues=genOldList[i].split("~");
	
	if(invValues[2]!=null)
		{
		var invName='';
		
		invName=invValues[2].replace("@","");
		//invName=invValues[2].replace("\\","\\\\");
		var invNameTemp=invName;
		if(invNameTemp.toUpperCase().localeCompare(document.getElementById("otherInvName").value.trim().toUpperCase())==0)
			{
			
			alert("Investigation Name already added.Please select another Investigation Name");
			document.getElementById("otherInvName").value='';
			document.getElementById("otherInvName").focus();
			return false;
		    }
		}
	}
	
	for(var i=0;i<genInventList.length;i++)
	{
	var invValues=genInventList[i].split("~");
	
	if(invValues[2]!=null)
		{
		var invName='';
		invName=invValues[2].replace("@","");
		invName=invName.trim();
	
		
		if(invName.trim().toUpperCase()==document.getElementById("otherInvName").value.trim().toUpperCase())
			{
			alert("Investigation Name already added.Please select another Investigation Name");
			document.getElementById("otherInvName").value='';
			document.getElementById("otherInvName").focus();
			return false;
		    }
		}
	}
	
	
	}
	if(!otherInvest)
		{
	for(var c=0;c<genOldList.length;c++){
		//var symptomsValues=genInventList[c].split("~");
		if(genOldList[c].indexOf(document.getElementById("genInvestigations").value) !== -1)
			{
			var fr=partial(focusBox,document.getElementById("genInvestigations"));
			alert("Investigation Name already added.Please select another Investigation Name");
			partial(focusBox,document.getElementById("genInvestigations"));
			return false;
			}
		}
	//alert(genInventList.length);
	for(var c=0;c<genInventList.length;c++)
	{
	//var symptomsValues=genInventList[c].split("~");
	if(genInventList[c].indexOf(document.getElementById("genInvestigations").value) !== -1)
		{
		var fr=partial(focusBox,document.getElementById("genInvestigations"));
		jqueryErrorMsg('Unique Investigation Validation',"Investigation Name already added.Please select another Investigation Name",fr);
		partial(focusBox,document.getElementById("genInvestigations"));
		return false;
		}
	}
	
    }
	if(genInventCount<=15)
	{
		if(genInventCount>=0)
		{
			if(document.forms[0].patientType[1].checked)
			{
				//jqueryInfoMsg('Patient Type Details Reset Message',"IP details entered are being reset");
			document.forms[0].patientType[1].checked=false;
			document.getElementById('OPHead2').style.display='none';
			document.getElementById('IPHead2').style.display='none';
			document.getElementById("diagnosisData").style.display="none";
			document.getElementById("prescriptionData").style.display="none";
			document.getElementById("OPDoctor").style.display="none";
			//document.getElementById("ChronicOPTherapy").style.display="none";
			document.getElementById("opClaimTable").style.display="none";
			if(document.getElementById("totalInvestAmt"))
			document.getElementById("totalInvestAmt").style.display="none";
			}
			else if(document.forms[0].patientType[0].checked)
			{
				//jqueryInfoMsg('Patient Type Details Reset Message',"OP details entered are being reset");
			document.forms[0].patientType[0].checked=false;
			document.getElementById('OPHead2').style.display='none';
			document.getElementById('IPHead2').style.display='none';
			document.getElementById("diagnosisData").style.display="none";
			document.getElementById("prescriptionData").style.display="none";
			document.getElementById("OPDoctor").style.display="none";
			//document.getElementById("ChronicOPTherapy").style.display="none";
			document.getElementById("opClaimTable").style.display="none";
			if(document.getElementById("totalInvestAmt"))
			document.getElementById("totalInvestAmt").style.display="none";
			if(document.getElementById("empPastHistory"))
			document.getElementById("empPastHistory").style.display="none";
			if(document.getElementById("consultationDataOld"))
			document.getElementById("consultationDataOld").style.display="none";
			if(document.getElementById("consultationDataNew"))
			document.getElementById("consultationDataNew").style.display="none";
			
			}
			else if(document.forms[0].patientType[2] && document.forms[0].patientType[2].checked)
			{
				//jqueryInfoMsg('Patient Type Details Reset Message',"Chronic OP details entered are being reset");
		
				document.forms[0].patientType[2].checked=false;
			//document.getElementById("opIcdName").disabled=true;
			//document.getElementById("opPkgName").disabled=true;
			
			}
			document.forms[0].patientType[1].disabled=false;
			document.getElementById('genTestTable').style.display='';
			
		}
		var investTableId=document.getElementById('genTestTable');
        
		var newRow=investTableId.insertRow(-1);
		//var newcell=newRow.insertCell(0);
		//newcell.innerHTML='<td width="10%">'+parseInt(genInventCount+1)+'</td>';
		if(otherInvest)
			{
		otherInvestCount++;
		
		testId="OI"+otherInvestCount;
		
		var newcell=newRow.insertCell(0);
		newcell.innerHTML='<td width="30%">Others</td>';
		var newcell=newRow.insertCell(1);
		newcell.innerHTML='<td width="25%">'+document.getElementById("otherInvName").value+'</td>';
			}
		else
			{
			var newcell=newRow.insertCell(0);
			newcell.innerHTML='<td width="30%">'+document.getElementById("genBlockInvestName").options[document.getElementById("genBlockInvestName").selectedIndex].text+'</td>';
			var newcell=newRow.insertCell(1);
			newcell.innerHTML='<td width="25%">'+document.getElementById("genInvestigations").options[document.getElementById("genInvestigations").selectedIndex].text+'</td>';
			}
		newcell=newRow.insertCell(2);
		newcell.innerHTML='<td width="25%"><input type="file"  id='+document.getElementById("genInvestigations").value+' name="genAttach['+genInvestAttachId+']" onchange="checkBrowser(this)"/></td>';
		if(document.getElementById("opPkgName"))
			{
		if(document.getElementById("opPkgName").value!=null && document.getElementById("opPkgName").value!="-1")
			{
	
		newcell=newRow.insertCell(3);
		if(!otherInvest)
		{
		    newcell.innerHTML='<td width="20%"><button  class="btn btn-warning" type="button"  style="padding: 3px 5px;" value="Delete" name='+document.getElementById("genInvestigations").value+'d id='+document.getElementById("genInvestigations").value+'d >Delete&nbsp;<i class="fa fa-times"></i></button></td>';
		}
		else
		{
			newcell.innerHTML='<td width="20%"><button  class="btn btn-warning" type="button"  style="padding: 3px 5px;" value="Delete" name='+testId+'d id='+testId+'d >Delete&nbsp;<i class="fa fa-times"></i></button></td>';	
		}
		
			}
			}
		else
			{
			if(document.forms[0].patientType[0].checked)
				{
				newcell=newRow.inserCell(3);
				newcell.innerHTML='<td width="20%">'+document.getElementById("investPrice").value+'</td>';
				newcell=newRow.inserCell(4);
				}
			else
				{
			newcell=newRow.insertCell(3);
				}
			if(!otherInvest)
			{
			    newcell.innerHTML='<td width="20%"><button  class="btn btn-warning" type="button"  style="padding: 3px 5px;" value="Delete" name='+document.getElementById("genInvestigations").value+'d id='+document.getElementById("genInvestigations").value+'d >Delete&nbsp;<i class="fa fa-times"></i></button></td>';
			}
			else
			{
				newcell.innerHTML='<td width="20%"><button  class="btn btn-warning" type="button"  style="padding: 3px 5px;" value="Delete" name='+testId+'d id='+testId+'d >Delete&nbsp;<i class="fa fa-times"></i></button></td>';	
			}
			}
		
		
		var deleteButName='';
		
		if(otherInvest)
			{
	    deleteButName=testId+'d';
			}
		else
			{
		deleteButName=document.getElementById("genInvestigations").value+'d';
			}
		document.getElementById(deleteButName).onclick = function(){
			 //confirmRemoveRow(this,"geninvestigation");
			fr=partial(deleteGenInvest,this,document.getElementById("genInvestigations").value);
			jqueryConfirmMsg('Delete General Investigation Confirmation',"Do you want to delete General Investigation?",fr);
			 }; 
			 var  genInvest="";
				if(document.getElementById("opPkgName"))
				{
					 if(otherInvest)
						 genInvest="others~"+testId+"~"+document.getElementById("otherInvName").value;	
				else if(document.getElementById("opPkgName").value!=null && document.getElementById("opPkgName").value!="-1")
		     genInvest=document.getElementById("genBlockInvestName").value+"~"+document.getElementById("genInvestigations").value+"~"+document.getElementById("genInvestigations").options[document.getElementById("genInvestigations").selectedIndex].text;
				}
			 else
				 {
		     
				 if(otherInvest)
				 genInvest="others~"+testId+"~"+document.getElementById("otherInvName").value+"~"+0;
				 else
				 genInvest=document.getElementById("genBlockInvestName").value+"~"+document.getElementById("genInvestigations").value+"~"+document.getElementById("genInvestigations").options[document.getElementById("genInvestigations").selectedIndex].text+"~"+document.getElementById("investPrice").value;	 
				 }
		genInventList[genInventCount]=genInvest;
		//alert(genInventList[genInventCount]);
	    //document.getElementById("symptoms").value=symptoms;
	    genInventCount++;
	    if(genInventCount>0)
			{
			document.getElementById("genTestTable").style.display="";
			}
		
		genInvestAttachId=genInvestAttachId+1;
		
	}
	else
	{
		alert("Cannot add more than 15 tests");
	}
	//parent.fn_resizePage();	
}


function addSymptoms()
{
	var otherSymptom=false;
	var symptomId=''; 
	
	if(document.getElementById("otherSymptoms"))
	otherSymptom=document.getElementById("otherSymptoms").checked;
	
	var otherSymptomCount=document.getElementById("otherSymptomCount").value;
	if(!otherSymptom)
		{
	if(document.getElementById("mainSymptomName").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("mainSymptomName"));
	alert("Please select Main Symptom Name");
	partial(focusBox,document.getElementById("mainSymptomName"));
	return false;
	}
	if(document.getElementById("mainSymptomCode").value=="")
		{
		var fr=partial(focusBox,document.getElementById("mainSymptomCode"));
		alert("Please Enter Main Symptom Code");
		partial(focusBox,document.getElementById("mainSymptomCode"));
		return false;
		}
	
	if(document.getElementById("subSymptomName").value==-1)
		{
		var fr=partial(focusBox,document.getElementById("subSymptomName"));
		alert("Please select Sub Symptom Name");
		partial(focusBox,document.getElementById("subSymptomName"));
		return false;
		}
	if(document.getElementById("subSymptomCode").value=="")
		{
		var fr=partial(focusBox,document.getElementById("subSymptomCode"));
		alert("Please Enter Sub Symptom Name");
		partial(focusBox,document.getElementById("subSymptomCode"));
		return false;
		}
	if(document.getElementById("symptomName").value==-1)
		{
		var fr=partial(focusBox,document.getElementById("symptomName"));
		alert("Please select Symptom Name");
		partial(focusBox,document.getElementById("symptomName"));
		return false;
		}
	if(document.getElementById("symptomCode").value=="")
		{
		var fr=partial(focusBox,document.getElementById("symptomCode"));
		alert("Please enter Symptom Code");
		partial(focusBox,document.getElementById("symptomCode"));
		return false;
		}
	
	for(var c=0;c<symptoms.length;c++)
	{
	var symptomsValues=symptoms[c].split("~");
	if(symptoms[c].indexOf(document.getElementById("symptomName").value) !== -1)
		{
		jqueryErrorMsg('Unique Symptoms Validation',"Symptom Name already added.Please select another Symptom Name",fr);
		return false;
		}
	}
	
		}
	
	if(otherSymptom)
		{
		var otherSymptomName=document.getElementById("otherSymptomName").value.trim();
		if(otherSymptomName==null || otherSymptomName=='')
			{
			alert("Please enter other Symptom Name");
			document.getElementById("otherSymptomName").focus();
			document.getElementById("otherSymptomName").value='';
			return false;
			}
		
		
		for(var i=0;i<symptoms.length;i++)
			{
			var symptomValues=symptoms[i].split("~");
			
			if(symptomValues[2]!=null)
				{
				var symptomName='';
				symptomName=symptomValues[2].replace("@","");
				symptomName=symptomName.trim();
			
				var uniqueSymptom=(symptomName.toUpperCase()==document.getElementById("otherSymptomName").value.toUpperCase());
				if(uniqueSymptom)
					{
				jqueryErrorMsg('Unique Symptoms Validation',"Symptom Name already added.Please select another Symptom Name",fr);
				document.getElementById("otherSymptomName").value='';
				return false;
				    }
				}
			}
		}
	
		var sympTable=document.getElementById("symptomsTable");    
		var newRow=sympTable.insertRow(-1);
		if(!otherSymptom)
			{
		var newcell=newRow.insertCell(0);
		newcell.innerHTML='<td width="5%" class="tbcellBorder">'+parseInt(symptomCount+1)+'</td>';
		var newcell=newRow.insertCell(1);
		newcell.innerHTML='<td width="15%" class="tbcellBorder">'+document.getElementById("symptomCode").value+'</td>';
		newcell=newRow.insertCell(2);
		newcell.innerHTML='<td width="15%" class="tbcellBorder">'+document.getElementById("symptomName").options[document.getElementById("symptomName").selectedIndex].text+'</td>';
		newcell=newRow.insertCell(3);
		newcell.innerHTML='<td width="15%" class="tbcellBorder">'+document.getElementById("subSymptomCode").value+'</td>';
		newcell=newRow.insertCell(4);
		newcell.innerHTML='<td width="15%" class="tbcellBorder">'+document.getElementById("subSymptomName").options[document.getElementById("subSymptomName").selectedIndex].text+'</td>';
		newcell=newRow.insertCell(5);
		newcell.innerHTML='<td width="10%" class="tbcellBorder">'+document.getElementById("mainSymptomCode").value+'</td>';  
		newcell=newRow.insertCell(6);
		newcell.innerHTML='<td width="15%" class="tbcellBorder">'+document.getElementById("mainSymptomName").options[document.getElementById("mainSymptomName").selectedIndex].text+'</td>';
		newcell=newRow.insertCell(7);
		newcell.innerHTML='<td width="5%" class="tbcellBorder"><button class="btn btn-warning" style="padding: 3px 5px;" type="button" value="Delete" name='+document.getElementById("symptomName").value+' id='+document.getElementById("symptomName").value+' onclick="removeSymptom(this)">Delete&nbsp;<i class="fa fa-times"></i></button></td>';
			}
		else
			{
			otherSymptomCount++;
			
			symptomId="OS"+otherSymptomCount;
			var newcell=newRow.insertCell(0);
			newcell.innerHTML='<td width="5%" class="tbcellBorder">'+parseInt(symptomCount+1)+'</td>';
			var newcell=newRow.insertCell(1);
			newcell.innerHTML='<td width="15%" class="tbcellBorder">Others</td>';
			newcell=newRow.insertCell(2);
			newcell.innerHTML='<td width="15%" class="tbcellBorder">Others</td>';
			newcell=newRow.insertCell(3);
			newcell.innerHTML='<td width="15%" class="tbcellBorder">Others</td>';
			newcell=newRow.insertCell(4);
			newcell.innerHTML='<td width="15%" class="tbcellBorder">Others</td>';
			newcell=newRow.insertCell(5);
			newcell.innerHTML='<td width="10%" class="tbcellBorder">Others</td>';  
			newcell=newRow.insertCell(6);
			newcell.innerHTML='<td width="15%" class="tbcellBorder">'+document.getElementById("otherSymptomName").value+'</td>';
			newcell=newRow.insertCell(7);
			newcell.innerHTML='<td width="5%" class="tbcellBorder"><button class="btn btn-warning" style="padding: 3px 5px;" type="button" value="Delete" name='+symptomId+' id='+symptomId+' onclick="removeSymptom(this)"/>Delete&nbsp;<i class="fa fa-times"></i></button></td>';
			
			}
		var deleteButName='';
		 if(!otherSymptom)
		  deleteButName=document.getElementById("symptomName").value;
		 else
		  deleteButName=symptomId;
		 
		 document.getElementById(deleteButName).onclick = function(){
			 confirmRemoveRow(this,"symptom");
			 }; 
			 var symptm='';
		
		if(!otherSymptom)
		{	 
		symptm=document.getElementById("mainSymptomName").value+"~"+document.getElementById("subSymptomName").value+"~"+document.getElementById("symptomName").value;
		}
		else
		{
			symptm=symptomId+"~others~"+document.getElementById("otherSymptomName").value;
			document.getElementById("otherSymptomName").value='';
		}
		symptoms[symptomCount]=symptm+"@";
	    document.getElementById("symptoms").value=symptoms;
	    symptomCount++;
		if(symptomCount>0)
			{
			document.getElementById("symptomsTable").style.display="";
			}
		document.getElementById("mainSymptomName").value="-1";
		document.getElementById("subSymptomName").value="-1";
		document.getElementById("symptomName").value="-1";
		document.getElementById("mainSymptomCode").value="";
		document.getElementById("symptomCode").value="";
		document.getElementById("subSymptomCode").value="";
		document.forms[0].subSymptomName.options.length=1;
		document.forms[0].symptomName.options.length=1;
		
		//parent.fn_resizePage();
}
function getSymptomCode()
{
	if(document.getElementById('symptomName').value=="-1")
		document.getElementById('symptomCode').value="";
	else
	document.getElementById('symptomCode').value=document.getElementById('symptomName').value;
}
function getPatComplaintCode()
{
	var patComplainLen=document.getElementById("patientComplaint").length;
	var patComplaints="";
	for (var x=0;x<patComplainLen;x++)
    {
       if (document.forms[0].patientComplaint[x].selected)
       {
    	   patComplaints = patComplaints + document.forms[0].patientComplaint[x].value+"~";
       }
    }
	document.getElementById("patComplaintCode").value=patComplaints.substring(0,patComplaints.length-1);
	document.forms[0].presentHistory.disabled=false;
}
function focusBox(arg)
{	
  aField = arg; 
  setTimeout("aField.focus()", 0);
  var x=getOffset( arg ).top;  

}
function focusFieldView(el)
{
//fn_goToField(el);
focusBox(document.getElementById(el));
}
function getGenInvestigation(){
	 var chronicId=document.getElementById("patientNo").value;
	if(document.getElementById("genBlockInvestName").value=="-1")
	{
	return false;
	}
else
	{
	var blockId=document.getElementById("genBlockInvestName").value;
	var xmlhttp;var url;
   if (window.XMLHttpRequest)
   {xmlhttp=new XMLHttpRequest();}
   else if (window.ActiveXObject)
   {xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");}
   else{jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");}
 xmlhttp.onreadystatechange=function()
   {
       if(xmlhttp.readyState==4)
       {
           var resultArray=xmlhttp.responseText;
           if(resultArray.trim()=="SessionExpired*"){
           	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
           }
           else
           {
           	if(resultArray!=null)
           	{
           		resultArray = resultArray.replace("[","");
               	resultArray = resultArray.replace("@]*","");            
               	var opIcdList = resultArray.split("@,"); 
               	
               	if(opIcdList.length>0)
               	{  
               		document.getElementById("genInvestigations").options.length=0;
               		document.getElementById("genInvestigations").options[0]=new Option("---select---","-1");
               		for(var i = 0; i<opIcdList.length;i++)
              		 	{	
                    		var arr=opIcdList[i].split("~");
                    		if(arr[1]!=null && arr[0]!=null)
                    		{
                        		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                        		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                      	 		document.getElementById("genInvestigations").options[i+1] =new Option(val1,val2);
                    		}
               		}
           		}
           	}
       	}
       }
   } 
 
	url = "./patientDetails.do?actionFlag=getGenInvestList&callType=Ajax&lStrBlockId="+blockId;}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	
}
function getInvestPrice()
{
var xmlHttp;
var blockId=document.getElementById("genBlockInvestName").value;
var investId=document.getElementById("genInvestigations").value;
//alert(blockId);alert(investId);
if(blockId==null || blockId=="-1" || investId==null || investId=="-1" )
	{
	return false;
	}
else
	{
if(window.XMLHttpRequest)
	{
	xmlHttp=new XMLHttpRequest();
	}
else if(window.ActiveXObject())
	{
	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
else{
	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }

xmlhttp.onreadystatechange=function()
{
	 if(xmlhttp.readyState==4)
     {
	var resultArray=xmlhttp.responseText;

	if(resultArray=="SessionExpired")
		{
		jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
		}
	else if(resultArray!=null)
		{
		resultArray=resultArray.replace("*","");
		//alert(resultArray);
		document.getElementById("investPrice").value=resultArray;
		}
	
	
     }
}

var url=url = "./patientDetails.do?actionFlag=getInvestPrice&callType=Ajax&blockId="+blockId+"&investId="+investId;
xmlhttp.open("Post", url,true);
xmlhttp.send(null);
	}
}
function deleteGenInvest(src,investId){

    var oRow=src.parentNode.parentNode;
	genInventList.splice(oRow.rowIndex-1,1);		
    document.getElementById("genTestTable").deleteRow(oRow.rowIndex);
	genInventCount--;
	
	
		if(genInventCount==0)
			{
					if(document.getElementById("opPkgName")){
						document.getElementById("opPkgName").disabled=false;
					}
					document.getElementById('genTestTable').style.display='none';
			        if(genOldList.length==0){
					document.forms[0].patientType[1].disabled=true;
					document.forms[0].patientType[1].checked=false;
					document.forms[0].patientType[0].checked=false;
					//document.forms[0].patientType[2].checked=false;
					if(document.getElementById('patientType'))
						{
					var a=document.getElementById('patientType').value;
					document.getElementById('patientType').value='';
					
					
					
					if((a=="OP") || (a=="IP") || (a=="DOP"))
						{
					document.getElementById("diagType").value=-1;
					document.getElementById("diagCode").value="";
					document.getElementById("mainCatName").options.length = 1;
					document.getElementById("mainCatCode").value = "";
					document.getElementById("catName").options.length = 1;
					document.getElementById("catCode").value= "";
					document.getElementById("subCatName").options.length = 1;
					document.getElementById("subCatCode").value = "";
					document.getElementById("diseaseName").options.length = 1;
					document.getElementById("diseaseCode").value = "";
					document.getElementById("disAnatomicalName").options.length = 1;
					document.getElementById("disAnatomicalCode").value = "";
					
					
					
					document.getElementById('IPHead2').style.display='none';
					document.getElementById("diagnosisData").style.display="none";
					document.getElementById('OPHead2').style.display='none';
					document.getElementById("prescriptionData").style.display="none";
					document.getElementById("OPDoctor").style.display="none";
//					document.getElementById("ChronicOPTherapy").style.display="none";
			        }}
			        }
			}
			//parent.fn_resizePage();
			}

function getDiagnMainCatList()
{
	document.getElementById("diagCode").value="";
	document.forms[0].mainCatName.options.length=1;
	document.getElementById("mainCatCode").value="";
	getDiagnCategoryList();
	if(document.forms[0].diagType.value=="-1")
		{
		return false;
		}
	else
		{
	document.getElementById("diagCode").value=document.forms[0].diagType.value;
	var xmlhttp;
    var url;

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
    	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else
            {
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
            		resultArray = resultArray.replace("@]*","");            
            		var mainCatList = resultArray.split("@,"); 
            		if(mainCatList.length>0)
            		{  
                		document.forms[0].mainCatName.options.length=0;
                        document.forms[0].mainCatName.options[0]=new Option("---select---","-1");
                        for(var i = 0; i<mainCatList.length;i++)
                        {	
                        	var arr=mainCatList[i].split("~");
                        	if(arr[1]!=null && arr[0]!=null)
                        	{
                        		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                        		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                        		document.forms[0].mainCatName.options[i+1] =new Option(val1,val2);
                        	}
                        }
            		}
            	}
            }
        }
    }
    	
	url = "./patientDetails.do?actionFlag=getDiagnMainCategory&callType=Ajax&lStrDiagnId="+document.forms[0].diagType.value;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
		}
}
function getDiagnCategoryList()
{
	document.getElementById("mainCatCode").value="";
	document.getElementById("catName").options.length=1;
	document.getElementById("catCode").value="";
	getDiagnSubCategoryList();
	if(document.forms[0].mainCatName.value=="-1")
		{
		return false;
		}
	else
		{
	document.getElementById("mainCatCode").value=document.forms[0].mainCatName.value;
	var xmlhttp;
    var url;

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
    	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else
            {
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
            		resultArray = resultArray.replace("@]*","");            
            		var categoryList = resultArray.split("@,"); 
            		if(categoryList.length>0)
            		{  
                		document.forms[0].catName.options.length=0;
                        document.forms[0].catName.options[0]=new Option("---select---","-1");
                        for(var i = 0; i<categoryList.length;i++)
                        {	
                        	var arr=categoryList[i].split("~");
                        	if(arr[1]!=null && arr[0]!=null)
                        	{
                        		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                        		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                        		document.forms[0].catName.options[i+1] =new Option(val1,val2);
                        	}
                        }
            		}
            	}
            }
        }
    }
    	
	url = "./patientDetails.do?actionFlag=getDiagnCategory&callType=Ajax&lStrDiagnMainId="+document.forms[0].mainCatName.value;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
		}
}
function getDiagnSubCategoryList()
{
	document.getElementById("catCode").value="";
	document.forms[0].subCatName.options.length=1;
	document.forms[0].diseaseName.options.length=1;
	document.forms[0].disAnatomicalName.options.length=1;
	document.getElementById("subCatCode").value="";
	document.getElementById("diseaseCode").value="";
	document.getElementById("disAnatomicalCode").value="";
	if(document.getElementById("catName").value=="-1")
	{
		return false;
	}
	else
	{
	document.getElementById("catCode").value=document.getElementById("catName").value;
	var xmlhttp;
    var url;

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
    	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else
            {
            	if(resultArray!=null)
            	{
            		var finalSubDetailsList = resultArray.split("$"); 
            		var subCategory;
            		var disease;
            		var disAnatomical;
            		if(finalSubDetailsList.length>0)
            		{  
            			subCategory=finalSubDetailsList[0];
            			disease=finalSubDetailsList[1];
            			disAnatomical=finalSubDetailsList[2];
            		}
            		subCategory = subCategory.replace("[","");
            		subCategory = subCategory.replace("@]",""); 
            		var subCategoryList = subCategory.split("@,"); 
            		if(subCategoryList.length>0)
            		{  
				   		document.forms[0].subCatName.options.length=0;
                        document.forms[0].subCatName.options[0]=new Option("---select---","-1");
                        for(var i = 0; i<subCategoryList.length;i++)
                        {	
                              var arr=subCategoryList[i].split("~");
                              if(arr[1]!=null && arr[0]!=null)
                              {
                            	  var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                            	  var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                            	  document.forms[0].subCatName.options[i+1] =new Option(val1,val2);
                              }
                        }
            		}
            		disease = disease.replace("[","");
            		disease = disease.replace("@]","");            
            		var diseaseList = disease.split("@,"); 
            		if(diseaseList.length>0)
            		{  
                		document.forms[0].diseaseName.options.length=0;
                        document.forms[0].diseaseName.options[0]=new Option("---select---","-1");
                        for(var i = 0; i<diseaseList.length;i++)
                        {	
                        	var arr=diseaseList[i].split("~");
                        	if(arr[1]!=null && arr[0]!=null)
                        	{
                        		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                        		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                        		document.forms[0].diseaseName.options[i+1] =new Option(val1,val2);
                        	}
                        }
            		}
				
            		disAnatomical = disAnatomical.replace("[","");
            		disAnatomical = disAnatomical.replace("@]*","");  
            		var disAnatomicalList = disAnatomical.split("@,"); 
            		if(disAnatomicalList.length>0)
            		{  
                		document.forms[0].disAnatomicalName.options.length=0;
                        document.forms[0].disAnatomicalName.options[0]=new Option("---select---","-1");
            		
                        for(var i = 0; i<disAnatomicalList.length;i++)
                        {	
                        	var arr=disAnatomicalList[i].split("~");
                        	if(arr[1]!=null && arr[0]!=null)
                        	{
                        		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                        		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                        		document.forms[0].disAnatomicalName.options[i+1] =new Option(val1,val2);
                        	}
                        }	
            		}
            	}
            }
        }
    }
    	
	url = "./patientDetails.do?actionFlag=getDiagnCatSubDetails&callType=Ajax&lStrListType=categoryId&lStrDiagnCode="+document.forms[0].catName.value;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	}
}

function getDiagnDiseaseList()
{
	document.forms[0].diseaseName.options.length=1;
	document.forms[0].disAnatomicalName.options.length=1;
	document.getElementById("subCatCode").value="";
	document.getElementById("diseaseCode").value="";
	document.getElementById("disAnatomicalCode").value="";
	if(document.getElementById("subCatName").value=="-1")
		{
			return false;
		}
		else
			{
	document.getElementById("subCatCode").value=document.getElementById("subCatName").value;
	var xmlhttp;
    var url;

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
    	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else
            {
            	if(resultArray!=null)
            	{
            		var finalSubDetailsList = resultArray.split("$"); 
            		var disease;
            		var disAnatomical;
            		if(finalSubDetailsList.length>0)
            		{  
            			disease=finalSubDetailsList[0];
            			disAnatomical=finalSubDetailsList[1];
            		}
		
            		disease = disease.replace("[","");
            		disease = disease.replace("@]","");            
            		var diseaseList = disease.split("@,"); 
            		if(diseaseList.length>0)
            		{  
                		document.forms[0].diseaseName.options.length=0;
                        document.forms[0].diseaseName.options[0]=new Option("---select---","-1");
            		
                        for(var i = 0; i<diseaseList.length;i++)
                        {	
                        	var arr=diseaseList[i].split("~");
                        	if(arr[1]!=null && arr[0]!=null)
                        	{
                        		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                        		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                        		document.forms[0].diseaseName.options[i+1] =new Option(val1,val2);
                        	}
                        }
            		}
				
            		disAnatomical = disAnatomical.replace("[","");
            		disAnatomical = disAnatomical.replace("@]*","");            
            		var disAnatomicalList = disAnatomical.split("@,"); 
            		if(disAnatomicalList.length>0)
            		{  
                		document.forms[0].disAnatomicalName.options.length=0;
                        document.forms[0].disAnatomicalName.options[0]=new Option("---select---","-1");
            		
                        for(var i = 0; i<disAnatomicalList.length;i++)
                        {	
                        	var arr=disAnatomicalList[i].split("~");
                        	if(arr[1]!=null && arr[0]!=null)
                        	{
                        		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                        		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                        		document.forms[0].disAnatomicalName.options[i+1] =new Option(val1,val2);
                        	}
                        }	
            		}
            	}
            }
        }
    }
    	
	url = "./patientDetails.do?actionFlag=getDiagnCatSubDetails&callType=Ajax&lStrListType=subCategoryId&lStrDiagnCode="+document.forms[0].subCatName.value;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
			}
}
function getDiagnDisAnatomicalList()
{
	document.forms[0].disAnatomicalName.options.length=1;
	document.getElementById("diseaseCode").value="";
	document.getElementById("disAnatomicalCode").value="";
	if(document.getElementById("diseaseName").value=="-1")
	{
		return false;
	}
	else
		{
	document.getElementById("diseaseCode").value=document.getElementById("diseaseName").value;
	var xmlhttp;
    var url;

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
    	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else
            {
            	if(resultArray!=null)
            	{
            		var finalSubDetailsList = resultArray.split("$"); 
            		var disAnatomical;
            		if(finalSubDetailsList.length>0)
            		{  
            			disAnatomical=finalSubDetailsList[0];
            		}
				 
            		disAnatomical = disAnatomical.replace("[","");
            		disAnatomical = disAnatomical.replace("@]*","");            
            		var disAnatomicalList = disAnatomical.split("@,"); 
            		if(disAnatomicalList.length>0)
            		{  
                		document.forms[0].disAnatomicalName.options.length=0;
                        document.forms[0].disAnatomicalName.options[0]=new Option("---select---","-1");
            		
                        for(var i = 0; i<disAnatomicalList.length;i++)
                        {	
                        	var arr=disAnatomicalList[i].split("~");
                        	if(arr[1]!=null && arr[0]!=null)
                        	{
                        		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                        		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                        		document.forms[0].disAnatomicalName.options[i+1] =new Option(val1,val2);
                        	}	
                        }	
            		}
            	}
            }
        }
    }
    	
	url = "./patientDetails.do?actionFlag=getDiagnCatSubDetails&callType=Ajax&lStrListType=diseaseId&lStrDiagnCode="+document.forms[0].diseaseName.value;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
		}
}
function getDisAnatomicalCode()
{
	document.getElementById("disAnatomicalCode").value="";
	if(document.getElementById("disAnatomicalName").value=="-1")
		{
		return false;
		}
	else
		{
	document.getElementById("disAnatomicalCode").value=document.getElementById("disAnatomicalName").value;
		}
}
function getIcdCategoryCodes()
{
	var hospId = document.getElementById("hospitalId").value;
	var hospGovu = document.getElementById("hospGovu").value;
	var treatType=document.forms[0].patientType.value;
	
	document.getElementById("asriCatCode").value="";
	document.forms[0].investigations.options.length=0;
	document.forms[0].docSpecReg.options.length=1;
	document.forms[0].ICDCatName.options.length=1;
	document.getElementById("unitsTd").style.display='none';
	document.getElementById("unitsLabelTd").style.display='none';
	document.getElementById("procUnits").value='-1';
	getTherapyICDProcedureList(1);
	if(document.getElementById("asriCatName").value=="-1")
	{
		return false;
	}
	else
	{
		var asriCatCode=document.getElementById("asriCatName").value;
		var schemeId=document.forms[0].scheme.value;
		if(medOrSur!=''){
              if(medOrSur!=asriCatCode.substr(0,1)){
            	  var fr = partial(focusBox,document.getElementById("asriCatName"));
            	  alert("Category("+ asriCatCode+") cannot be added.Please select either Medical or Surgical type Only.");
            	  partial(focusBox,document.getElementById("asriCatName"));
            	  document.getElementById("asriCatName").value='-1';
            	  return false;
				}
		}	
		document.getElementById("asriCatCode").value=asriCatCode;
		/*if(asriCatCode.trim()=='S18')
		{
			document.getElementById("unitsTd").style.display='';
			document.getElementById("unitsLabelTd").style.display='';
		}*/
		var xmlhttp;
		var url;

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
			jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
		}
  
		xmlhttp.onreadystatechange=function()
		{
			if(xmlhttp.readyState==4)
			{
				var resultArray=xmlhttp.responseText;
				if(resultArray.trim()=="SessionExpired*"){
					jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
				}
				else{
					if(resultArray!=null)
					{
						var totalList = resultArray.split("*");
						
						var categList = totalList[0];var docList = totalList[1];
                    
						categList = categList.replace("[","");categList = categList.replace("@]","");
						if(docList!=null)
							{
						docList = docList.replace("[","");
						docList = docList.replace("@]","");  
						var doctorList = docList.split("@,"); 
					        }
						var categoryList = categList.split("@,"); 
						
						if(categoryList.length>0)
						{  
							document.forms[0].ICDCatName.options.length=0;
							document.forms[0].ICDCatName.options[0]=new Option("---select---","-1");
							for(var i = 0; i<categoryList.length;i++)
							{	
								var arr=categoryList[i].split("~");
								if(arr[1]!=null && arr[0]!=null)
								{
									var val1 = arr[1].replace(/^\s+|\s+$/g,"");
									var val2 = arr[0].replace(/^\s+|\s+$/g,"");
									document.forms[0].ICDCatName.options[i+1] =new Option(val1,val2);
								}
							}
						}
						if(doctorList.length>0)
						{  
							document.forms[0].docSpecReg.options.length=0;
							document.forms[0].docSpecReg.options[0]=new Option("---select---","-1");
							for(var i = 0; i<doctorList.length;i++)
							{	
								var arr=doctorList[i].split("~");
								if(arr[1]!=null && arr[0]!=null)
								{
									var val1 = arr[1].replace(/^\s+|\s+$/g,"");
									var val2 = arr[0].replace(/^\s+|\s+$/g,"");
									document.forms[0].docSpecReg.options[i+1] =new Option(val1,val2);
								}
							}
						}
					}
				}
			}
		}
    	
		url = "./patientDetails.do?actionFlag=getICDCatByAsriCode&callType=Ajax&lStrAsriCatId="+asriCatCode+"&treatType="+treatType+"&hospId="+hospId;
		xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
	}
}
function getTherapyICDProcedureList(param)
{
	var patientId=document.getElementById("patientNo").value;
	var hospId = document.getElementById("hospitalId").value;
	var treatType=document.forms[0].patientType.value;
	var hosType=null;
		 if(document.getElementById("hosType")!=null)
			{
			hosType=document.getElementById("hosType").value;
			}
	document.forms[0].ICDCatCode.value="";
	document.forms[0].ICDProcName.options.length=1;
	getProcedureCodes();
	if(document.forms[0].ICDCatName.value=="-1")
		{
		return false;
		}
	else
		{
	var icdCatCode=document.forms[0].ICDCatName.value;
	var asriCode=document.forms[0].asriCatName.value;
	document.forms[0].ICDCatCode.value=icdCatCode;
	var schemeId=document.forms[0].scheme.value;
	var xmlhttp;
    var url;

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
    	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;

            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else{
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("@]*","");            
                	var procedureList = resultArray.split("@,"); 
                	if(procedureList.length>0)
                	{  
                		if(param==1)
            			{
                			document.forms[0].ICDProcName.options.length=0;
                        	document.forms[0].ICDProcName.options[0]=new Option("---select---","-1");
            			}
            			for(var i = 0; i<procedureList.length;i++)
                		{	
                     		var arr=procedureList[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null)
                     		{
                         		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                         		if(param==1)
                    	 		{
                        	 		document.forms[0].ICDProcName.options[i+1] =new Option(val1,val2);
                    	 		}
                     		}
                		}
            		}
            	}
        	}
        }
    }
    	
	url = "./patientDetails.do?actionFlag=getProcByCat&callType=Ajax&lStrICDCatId="+icdCatCode+"&lStrAsriCode="+asriCode+"&patientId="+patientId+"&treatType="+treatType+"&hospId="+hospId+"&hosType="+hosType;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
		}
}

function getProcedureCodes()
{
	document.getElementById("ICDProcCode").value="";
	var locSchemeId=document.getElementById("scheme").value;
	var asriCatCode=document.getElementById("asriCatCode").value;
	if(document.getElementById("ICDProcName").value=="-1")
		{
			document.getElementById("unitsTd").style.display="none";
			document.getElementById("unitsLabelTd").style.display="none";
			return false;
		}
	else
		{
			var icdProcCode=document.getElementById("ICDProcName").value;
			document.getElementById("ICDProcCode").value=icdProcCode;
			if(asriCatCode!=null  && ((locSchemeId !=null && locSchemeId=='CD202') || (locSchemeId==null || locSchemeId=='' || locSchemeId==' ') ))
				getTherapyInvestigations();
		}
	
	if(asriCatCode!=null && asriCatCode=='S18')
		{
			var checkCond=0;
			document.forms[0].procUnits.options.length=0;
			document.forms[0].procUnits.options[0]=new Option("---select---","-1");
			if(locSchemeId !=null && locSchemeId=='CD202')
				{
					var procName=$("#ICDProcName option:selected").text();
					if(locSchemeId!=null && locSchemeId!='' && locSchemeId=='CD202')
						{
							//Checking Non Combo Codes at the time of Adding new Procedure 
							if(nonComboProcIds!=null && nonComboProcIds!=='' && nonComboProcIds!= ' ')
								{
									var procWiseLst=nonComboProcIds.split("$");
									for(var j=0;j<procWiseLst.length;j++)//Checking for every added Proc
										{
											var alertCont=null;
											var indiSpecCombLst=procWiseLst[j].split(",");
											var addedSpecDtls=indiSpecCombLst[0].split("!@#");
											
											var allCombos=indiSpecCombLst[1].split("~");
											for(var k=0;k<allCombos.length;k++)//Checking for every Combo Proc for added Proc
												{
													var splitComboProc=allCombos[k].split("@");
													var noncomboProcedureId=splitComboProc[0];
													var noncomboProcedureName=splitComboProc[1];
													if(allCombos[k].indexOf(document.getElementById("ICDProcName").value+"@")!='-1')
														{
															checkCond++;
															if(alertCont==null || alertCont=='' || alertCont==' ')
																alertCont=noncomboProcedureName+"("+noncomboProcedureId+")";
															else
																alertCont=alertCont+" , "+noncomboProcedureName+"("+noncomboProcedureId+")";
														}
												}
											if(checkCond>0)
												{
													var alertMsg="As Procedure "+addedSpecDtls[1]+"("+addedSpecDtls[0]+") is added,Mandatory Non Combinational Procedures "+alertCont+" should not be added.";
													
													resetDentalProc();
													bootbox.alert(alertMsg);
													focusBox(document.getElementById("ICDProcName"));
													return false;
												}
										}
								}
							//Checking Non Combo Codes at the time of Adding new Procedure for Stand Alone Procedures
							if(standaloneProcIds!=null && standaloneProcIds!=='' && standaloneProcIds!= ' ')
								{
									var procWiseLst=standaloneProcIds.split("$");
									for(var j=0;j<procWiseLst.length;j++)//Checking for every added Proc
										{
											var alertCont=null;
											var indiSpecCombLst=procWiseLst[j].split(",");
											var addedSpecDtls=indiSpecCombLst[0].split("!@#");
											
											var allCombos=indiSpecCombLst[1].split("~");
											for(var k=0;k<allCombos.length;k++)//Checking for every Combo Proc for added Proc
												{
													var splitComboProc=allCombos[k].split("@");
													var noncomboProcedureId=splitComboProc[0];
													var noncomboProcedureName=splitComboProc[1];
													if(allCombos[k].indexOf(document.getElementById("ICDProcName").value+"@")!='-1')
														{
															checkCond++;
															if(alertCont==null || alertCont=='' || alertCont==' ')
																alertCont=noncomboProcedureName+"("+noncomboProcedureId+")";
															else
																alertCont=alertCont+" , "+noncomboProcedureName+"("+noncomboProcedureId+")";
														}
												}
											if(checkCond>0)
												{
													var alertMsg="As Stand Alone Procedure "+addedSpecDtls[1]+"("+addedSpecDtls[0]+") is added,Mandatory Non Combinational Procedures "+alertCont+" should not be added.";
													
													resetDentalProc();
													bootbox.alert(alertMsg);
													focusBox(document.getElementById("ICDProcName"));
													return false;
												}
										}
								}
							if(checkCond==0)
								{
									getTherapyInvestigations();
									getDentalConditions(icdProcCode,procName,locSchemeId,'allCond');
								}
						}
				}
			else
				{
					document.getElementById("unitsTd").style.display='';
					document.getElementById("unitsLabelTd").style.display='';
					getDentalUnits("N");
				}
		}
	
	
	
}
function getTherapyInvestigations()
{
	document.forms[0].investigations.options.length=0;
	//document.getElementById("unitsTd").style.display='none';
	//document.getElementById("unitsLabelTd").style.display='none';
	var locSchemeId=document.getElementById("scheme").value;
	var therapies=document.getElementById("ICDProcName").value;
	var asriCode=document.forms[0].asriCatName.value;
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
		   jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
	   }   
	 	url = "./patientDetails.do?actionFlag=getTherapyInvestigations&callType=Ajax&therapyId="+therapies+"&asriCode="+asriCode+"&scheme="+locSchemeId;    
	    xmlhttp.onreadystatechange=function()
	    {
	       if(xmlhttp.readyState==4)
	       {
	    	   var resultArray=xmlhttp.responseText;
	    	   if(resultArray.trim()=="SessionExpired*"){
	    		   jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
	            }
	            else
	            {
					var totalResult = resultArray.split("*");
                    
                    var investList = totalResult[0];var procedureType = totalResult[1];
                    
                    investList = investList.replace("[","");investList = investList.replace("@]","");
                    procedureType = procedureType.replace("[","");procedureType = procedureType.replace("@]","");  
					/*if(procedureType.trim()=="OP")
					{
						document.getElementById("unitsTd").style.display='';
						document.getElementById("unitsLabelTd").style.display='';
					}*/
	           		var investigationsList = investList.split("@,"); 
	           		if(investigationsList.length>0)
	           		{
						document.forms[0].investigations.options.length=0;
	               		for(var i =0 ; i<investigationsList.length;i++)
	               		{	
            				var arr=investigationsList[i].split("~");  
	                    	if(arr[1]!=null && arr[0]!=null)
	                    	{
	                    		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                				var val2 = arr[0].replace(/^\s+|\s+$/g,"");
	                    		document.forms[0].investigations.options[i] =new Option(val1,val2);
                			}
	                    }
                     }
	                }
	            }
	       	}
		xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
}
function addSpecialities()
{
	
	var localSchemeId=document.getElementById("scheme").value;
	if(document.getElementById("asriCatName").value==-1)
	{
		var fr=partial(focusBox,document.getElementById("asriCatName"));
		alert("Please select Therapy Category Name");
		partial(focusBox,document.getElementById("asriCatName"));
		return false;
	}
	if(document.getElementById("ICDCatName").value==-1)
	{
		var fr=partial(focusBox,document.getElementById("ICDCatName"));
		alert("Please select Therapy ICD Category");
		partial(focusBox,document.getElementById("ICDCatName"));
		return false;
	}
	if(document.getElementById("ICDProcName").value==-1)
	{
		var fr=partial(focusBox,document.getElementById("ICDProcName"));
		alert("Please select Therapy ICD Procedure");
		partial(focusBox,document.getElementById("ICDProcName"));
		return false;
	}
	if(document.getElementById("unitsTd").style.display=='')
	{
		if(document.getElementById("procUnits").value=='-1')
		{
			var fr=partial(focusBox,document.getElementById("procUnits"));
			alert("Please select Units");
			partial(focusBox,document.getElementById("procUnits"));
			return false;
		}
	}
	if(document.getElementById("docSpecReg").value==-1)
	{
		var fr=partial(focusBox,document.getElementById("docSpecReg"));
    	alert("Please select Treating Doctor");
    	partial(focusBox,document.getElementById("ipOtherDocName"));
		return false;
	}
	if(document.getElementById("docSpecReg").value=='0')
	{
		if(document.getElementById("ipOtherDocName").value=='')
		{
		var fr=partial(focusBox,document.getElementById("ipOtherDocName"));
		alert("Please enter Other Treating Doctor Name");
		partial(focusBox,document.getElementById("ipOtherDocName"));
		return false;
		}
		if(document.getElementById("ipDocRegNo").value=='')
		{
		var fr=partial(focusBox,document.getElementById("ipDocRegNo"));
		alert("Please enter Other Treating Doctor Registration No");
		partial(focusBox,document.getElementById("ipDocRegNo"));
		return false;
		}
		if(document.getElementById("ipDocQual").value=='')
		{
		var fr=partial(focusBox,document.getElementById("ipDocQual"));
		alert("Please enter Other Treating Doctor Qualification");
		partial(focusBox,document.getElementById("ipDocQual"));
		return false;
		}
		if(document.getElementById("ipDocMobileNo").value=='')
		{
		var fr=partial(focusBox,document.getElementById("ipDocMobileNo"));
    	alert("Please enter Other Treating Doctor Mobile No");
    	partial(focusBox,document.getElementById("ipDocMobileNo"));
		return false;
		}
	}
	document.getElementById("procSelectedTd").style.display='none';
	var investigations=document.forms[0].investigations;
	var investCount=0;
	if(investigations.length>0)
	{
		for (var x=0;x<investigations.length;x++)
		{
			if (investigations[x].selected)
			{
				var mflag=false;
				investCount++;
				for(var c=0;c<specOld.length;c++)
				{
					
					var specValues=specOld[c].split("~");
					if(specValues[2]==document.getElementById("ICDProcName").value && specValues[7]==investigations[x].value)
					{
						alert("Investigation  "+investigations[x].text+"  already added.Please select another Investigation");
						//return false;
						mflag=true;
					}
				}
				for(var c=0;c<spec.length;c++)
				{
					
					var specValues=spec[c].split("~");
					if(specValues[2]==document.getElementById("ICDProcName").value && specValues[7]==investigations[x].value)
					{
						alert("Investigation  "+investigations[x].text+"  already added.Please select another Investigation");
						//return false;
						mflag=true;
					}
				}
			
				if(mflag==false)
				{
					
				var catTable=document.getElementById("categoryTable");    
				var newRow=catTable.insertRow(-1);
				// var newcell=newRow.insertCell(0);
				//newcell.innerHTML='<td width="5%">'+parseInt(catCount+1)+'</td>';
				var newcell=newRow.insertCell(0);
				newcell.innerHTML='<td width="15%">'+document.getElementById("asriCatName").options[document.getElementById("asriCatName").selectedIndex].text+'</td>';
				newcell=newRow.insertCell(1);
				newcell.innerHTML='<td width="15%">'+document.getElementById("ICDCatName").options[document.getElementById("ICDCatName").selectedIndex].text+'</td>';
				newcell=newRow.insertCell(2);
				newcell.innerHTML='<td width="15%">'+document.getElementById("ICDProcName").options[document.getElementById("ICDProcName").selectedIndex].text+'</td>';
				newcell=newRow.insertCell(3);
				if(document.getElementById("unitsTd").style.display=='')
				{
					newcell.innerHTML='<td width="5%">'+document.getElementById("procUnits").value+'</td>';
				}
				else if(document.getElementById("unitsTd").style.display=='none')
				{
					newcell.innerHTML='<td width="5%">-NA-</td>';
				}
				else
					newcell.innerHTML='<td width="5%">-NA-</td>';
				if(document.getElementById("docSpecReg").value=='0')
				{
				newcell=newRow.insertCell(4);
				newcell.innerHTML='<td width="10%">'+document.getElementById("ipOtherDocName").value+'</td>';
				}
				else
				{
				newcell=newRow.insertCell(4);
				newcell.innerHTML='<td width="10%">'+document.getElementById("docSpecReg").options[document.getElementById("docSpecReg").selectedIndex].text+'</td>';
				}
				newcell=newRow.insertCell(5);
				newcell.innerHTML='<td width="20%">'+investigations[x].text+'</td>';
				newcell=newRow.insertCell(6);
				newcell.innerHTML='<td width="20%"><input type="file" style="width:100%" size="13" id='+investigations[x].value+' name="attach['+therapyAttachId+']" onchange="checkBrowser(this)"/></td>';	
				newcell=newRow.insertCell(7);
				newcell.innerHTML='<td width="5%"><input class="but" type="button" value="Delete" name='+document.getElementById("ICDProcName").value+investigations[x].value+' id='+document.getElementById("ICDProcName").value+investigations[x].value+'  /></td>';
					var deleteButName=document.getElementById("ICDProcName").value+investigations[x].value;
					 document.getElementById(deleteButName).onclick = function(){
						 confirmRemoveRow(this,"speciality");
						 }; 

				var speciality='';
				var otherDocStr='';
				if(spec.length>0)
				{
					if(document.getElementById("docSpecReg").value=='0')
					{
						otherDocStr=document.getElementById("ipOtherDocName").value+"~"+document.getElementById("ipDocRegNo").value+"~"+document.getElementById("ipDocQual").value+"~"+document.getElementById("ipDocMobileNo").value;
					}
					else
					{
						otherDocStr=document.getElementById("ipOtherDocName").value+"~"+document.getElementById("ipDocRegNo").value+"~"+" "+"~"+" ";
					}
					for(var i=0;i<spec.length;i++)
					{
						var specValues=spec[i].split("~");
						if(specValues[2]==document.getElementById("ICDProcName").value)
						{
							speciality=" "+"~"+" "+"~"+document.getElementById("ICDProcName").value+'~'+otherDocStr+'~'+investigations[x].value+'~'+investigations[x].text+"~"+document.getElementById("procUnits").value;
						}
						else
						{
							speciality=document.getElementById("asriCatName").value+"~"+document.getElementById("ICDCatName").value+"~"+document.getElementById("ICDProcName").value+'~'+otherDocStr+'~'+investigations[x].value+'~'+investigations[x].text+"~"+document.getElementById("procUnits").value;
						}
					}
					
				}		
				else
				{
					if(document.getElementById("docSpecReg").value=='0')
					{
						otherDocStr=document.getElementById("ipOtherDocName").value+"~"+document.getElementById("ipDocRegNo").value+"~"+document.getElementById("ipDocQual").value+"~"+document.getElementById("ipDocMobileNo").value;
					}
					else
					{
						otherDocStr=document.getElementById("ipOtherDocName").value+"~"+document.getElementById("ipDocRegNo").value+"~"+" "+"~"+" ";
					}
					speciality=document.getElementById("asriCatName").value+"~"+document.getElementById("ICDCatName").value+"~"+document.getElementById("ICDProcName").value+'~'+otherDocStr+'~'+investigations[x].value+'~'+investigations[x].text+"~"+document.getElementById("procUnits").value;
					
				}
				spec[catCount]=speciality;
				
				/*Added for TG Dental*/
				
			       
					addedProcs[catCount]=document.getElementById("ICDProcName").value;
						//
					
					/*Added by venkatesh for blocking surgery date in NIMS for Medical procs*/
				/*var hospId = document.getElementById("hospitalId").value;
				if( hospId!=null && hospId=='EHS34')*/
				 var hospGovu = document.getElementById("hospGovu").value;
				 if(hospGovu!=null && hospGovu!="G")
					{
				var catType=document.getElementById("asriCatName").value;
				if(catType!=null && catType.indexOf("M")!=-1)
					{
					if(document.getElementById("surgDateHead") != null && document.getElementById("surgDate") != null)
						{
							document.getElementById("surgDateHead").style.display="none";
							document.getElementById("surgDate").style.display="none";
						}
					
					}
				else
					{
					if(document.getElementById("surgDateHead") != null && document.getElementById("surgDate") != null)
					{
						document.getElementById("surgDateHead").style.display="";
						document.getElementById("surgDate").style.display="";
					}
					
					}
					}
				//otherDocDetails[catCount]=otherDocStr;
				document.getElementById("speciality").value=spec;
				//document.getElementById("otherDocDetailsList").value=otherDocDetails;
				catCount++;
				therapyAttachId++;
				if(catCount>0)
				{
					document.getElementById("categoryTable").style.display="";
				}
				
				//Added by Srikalyan to get TG Dental Conditions	
				var procName=$("#ICDProcName option:selected").text();
				if(localSchemeId!=null && localSchemeId!='' && localSchemeId=='CD202')
					getDentalConditions(document.getElementById("ICDProcName").value , procName , localSchemeId , 'comboCond' );
			}
			}
		}
		if(investCount==0)
		{
			alert("Please select atleast one investigation to add speciality");
		}
		else
		{
			if(!document.getElementById("telephonicId").value=='')
				{
					var therapy=document.getElementById("therapy").value;
					if(therapy!=document.getElementById("ICDProcName").value)
					{
						var lname="Treating Doctor Remarks";
						alert("Telephonic therapy is not matched with IP selected therapies.Please enter treating doctor remarks.");
						document.getElementById("treatingDocLabel").style.display='';
						document.getElementById("treatingDocRemarks").innerHTML='<textarea name="treatingDocRmks" id="treatingDocRmks" title="Treating Doctor Remarks" style="width:35em;height:3em" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event)" onchange="validateSpaces(this,\''+lname+'\')"/>';
					}
				}
			if(procList.length==0)
			{
				var text=document.getElementById("ICDProcName").options[document.getElementById("ICDProcName").selectedIndex].text;
				var value=document.getElementById("ICDProcName").value;
				var procedure=text+"~"+value;
				procList[procList.length]=procedure;
			}
			else
			{	
				var matchCount=0;
				for(var p=0;p<procList.length;p++)
				{
					var procValues=procList[p].split("~");
					if(procValues[1]==document.getElementById("ICDProcName").value)
						{
						matchCount++;
						}
				}
				if(matchCount==0)
				{
					var text=document.getElementById("ICDProcName").options[document.getElementById("ICDProcName").selectedIndex].text;
					var value=document.getElementById("ICDProcName").value;
					var procedure=text+"~"+value;
					procList[procList.length]=procedure;
				}
			}
		}
	}
	else
	{
		if(procList.length==0)
		{
			var text=document.getElementById("ICDProcName").options[document.getElementById("ICDProcName").selectedIndex].text;
			var value=document.getElementById("ICDProcName").value;
			var procedure=text+"~"+value;
			procList[procList.length]=procedure;
		}
		else
		{	
			var matchCount=0;
			for(var p=0;p<procList.length;p++)
			{
				var procValues=procList[p].split("~");
				if(procValues[1]==document.getElementById("ICDProcName").value)
					{
					matchCount++;
					}
			}
			if(matchCount==0)
			{
				var text=document.getElementById("ICDProcName").options[document.getElementById("ICDProcName").selectedIndex].text;
				var value=document.getElementById("ICDProcName").value;
				var procedure=text+"~"+value;
				procList[procList.length]=procedure;
			}
		}
		for(var c=0;c<spec.length;c++)
			{
				var specValues=spec[c].split("~");
				if(specValues[2]==document.getElementById("ICDProcName").value)
					{
					alert("Procedure already added.Please select another ICD Procedure");
						return false;
					}
			}
		for(var c=0;c<specOld.length;c++)
		{
			var specValues=specOld[c].split("~");
			if(specValues[2]==document.getElementById("ICDProcName").value)
			{
				alert("Procedure already added.Please select another ICD Procedure");
				return false;
				
			}
		}
		if(!document.getElementById("telephonicId").value=='')
			{
			var therapy=document.getElementById("therapy").value;
			if(therapy!=document.getElementById("ICDProcName").value)
				{
				var lname="Treating Doctor Remarks";
				alert("Telephonic therapy is not matched with IP selected therapies.Please enter treating doctor remarks.");
				document.getElementById("treatingDocLabel").style.display='';
				document.getElementById("treatingDocRemarks").innerHTML='<textarea name="treatingDocRmks" id="treatingDocRmks" title="Treating Doctor Remarks" style="width:35em;height:3em" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event)"  onchange="validateSpaces(this,\''+lname+'\')"/>';
				}
			}
		var catTable=document.getElementById("categoryTable");    
		var newRow=catTable.insertRow(-1);
		//var newcell=newRow.insertCell(0);
		//newcell.innerHTML='<td width="5%">'+parseInt(catCount+1)+'</td>';
		var newcell=newRow.insertCell(0);
		newcell.innerHTML='<td width="10%">'+document.getElementById("asriCatName").options[document.getElementById("asriCatName").selectedIndex].text+'</td>';
		newcell=newRow.insertCell(1);
		newcell.innerHTML='<td width="15%">'+document.getElementById("ICDCatName").options[document.getElementById("ICDCatName").selectedIndex].text+'</td>';
		newcell=newRow.insertCell(2);
		newcell.innerHTML='<td width="20">'+document.getElementById("ICDProcName").options[document.getElementById("ICDProcName").selectedIndex].text+'</td>';
		newcell=newRow.insertCell(3);
		if(document.getElementById("unitsTd").style.display=='')
		{
			newcell.innerHTML='<td width="5%">'+document.getElementById("procUnits").value+'</td>';
		}
		else if(document.getElementById("unitsTd").style.display=='none')
		{
			newcell.innerHTML='<td width="5%">-NA-</td>';
		}
		else
			newcell.innerHTML='<td width="5%">-NA-</td>';
		if(document.getElementById("docSpecReg").value=='0')
		{
			newcell=newRow.insertCell(4);
			newcell.innerHTML='<td width="10%">'+document.getElementById("ipOtherDocName").value+'</td>';
		}
		else
		{
			newcell=newRow.insertCell(4);
			newcell.innerHTML='<td width="10%">'+document.getElementById("docSpecReg").options[document.getElementById("docSpecReg").selectedIndex].text+'</td>';
		}
		newcell=newRow.insertCell(5);
		newcell.innerHTML='<td width="20">NA</td>';
		newcell=newRow.insertCell(6);
		newcell.innerHTML='<td width="25%">NA</td>';
		newcell=newRow.insertCell(7);
		newcell.innerHTML='<td width="5%"><input class="but" type="button" value="Delete" name='+document.getElementById("ICDProcName").value+'NA id='+document.getElementById("ICDProcName").value+'NA  /></td>';
		
		var deleteButName=document.getElementById("ICDProcName").value+'NA';
		 document.getElementById(deleteButName).onclick = function(){
			 confirmRemoveRow(this,"speciality");
			 };
		var otherDocStr='';
		if(document.getElementById("docSpecReg").value=='0')
		{
			otherDocStr=document.getElementById("ipOtherDocName").value+"~"+document.getElementById("ipDocRegNo").value+"~"+document.getElementById("ipDocQual").value+"~"+document.getElementById("ipDocMobileNo").value;
		}
		else
		{
			otherDocStr=document.getElementById("ipOtherDocName").value+"~"+document.getElementById("ipDocRegNo").value+"~"+" "+"~"+" ";
		}			 
		speciality=document.getElementById("asriCatName").value+"~"+document.getElementById("ICDCatName").value+"~"+document.getElementById("ICDProcName").value+'~'+otherDocStr+'~NA~NA'+"~"+document.getElementById("procUnits").value;
		
		
		
		spec[catCount]=speciality;
		document.getElementById("speciality").value=spec;
		
		//Added by Srikalyan to get TG Dental Conditions	
		var procName=$("#ICDProcName option:selected").text();
		if(localSchemeId!=null && localSchemeId!='' && localSchemeId=='CD202')
			getDentalConditions(document.getElementById("ICDProcName").value , procName , localSchemeId , 'comboCond' );
		
		//otherDocDetails[catCount]=otherDocStr;
		//document.getElementById("otherDocDetailsList").value=otherDocDetails;
		
		catCount++;
		if(catCount>0)
			{
				document.getElementById("categoryTable").style.display="";
			}
	}
	medOrSur=document.getElementById("asriCatName").value.substr(0,1);
	//parent.fn_resizePage();
}
function fn_getIPDoctorsDetails(){

  	if(document.forms[0].docSpecReg.value=="-1")
  	{
  		return false;
  	}
  	else
  	{
		var hospId = document.getElementById("hospitalId").value;
  		var doctorId=document.forms[0].docSpecReg.value;   
		var selVal=document.forms[0].docSpecReg.selectedIndex;
		
		if(doctorId == '0')
		{
        	document.getElementById("ipDoctorData").style.display='';
			document.getElementById("ipOtherDocName").value="";
       		document.getElementById("ipDocRegNo").value="";
        	document.getElementById("ipDocQual").value="";
        	document.getElementById("ipDocMobileNo").value="";
     	}
		else
		{
			document.getElementById("ipDoctorData").style.display='none';
			document.getElementById("ipDocRegNo").value=document.forms[0].docSpecReg.options[selVal].value;
			document.getElementById("ipOtherDocName").value=document.forms[0].docSpecReg.options[selVal].text;
			document.getElementById("ipDocQual").value="";
        	document.getElementById("ipDocMobileNo").value="";
		}

    }
  //parent.fn_resizePage();
}

function fn_checkprobingdepth(id ,input)
{
	var fr=partial(focusBox,id);
			if(input>6)
				{
				alert("probing depth cannot be more than 6mm",fr);
				document.getElementById(id).value="";
				return false;
				}
			else if(input<1)
				{
				alert("probing depth cannot be less than 1mm",fr);
				document.getElementById(id).value="";
				return false;
				}
			else 
				return true;
}


function validateInnerNum(input)
{	
	
	var hospGovu = document.getElementById("hospGovu").value;

	
	var a=input.value;
	var fr=partial(focusBox,input);
	var regAlphaNum=/^[0-9.]+$/;
	var hospId=document.getElementById("hospitalId").value;
	//if(hospId!=null && hospId!='EHS34')
	 if(hospGovu!=null && hospGovu!="G")
		{
		 
     if(input.id=='GE1' || input.id=='GE2'){
     	 document.getElementById('GE3').innerHTML='<input type="text" id="GE3" value="" readOnly/>';
     }}
		
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
		if(hospGovu!=null && hospGovu!="G")
		{
		if(heightVar!=null && weightVar!='' && weightVar!=null){
			var heightVarr=heightVar*1/100;
			var bmiCal=((weightVar*1)/(heightVarr*heightVarr)).toFixed(2);
			document.getElementById('GE3').innerHTML='<input type=\'text\' id=\'GE3\' value=\''+bmiCal+'\' readOnly/>';
			}
		}}
	
	if(input.id=='GE2'){
		if(input.value>300){
			jqueryErrorMsg('Number Validation', " Weight Should be in range of 0- 300 Kg.",fr);
			partial(focusBox,input);
			input.value="";
			return false;
			}
		
		//if(hospId!="EHS34")
		if(hospGovu!=null && hospGovu!="G")
		{
		weightVar=input.value;
		var heightVar=document.forms[0].GE1.value;
		if(heightVar!=null && heightVar!='' && weightVar!=null){
		var heightVarw=heightVar*1/100;
		var bmiCal=((weightVar*1)/(heightVarw*heightVarw)).toFixed(2);
		document.getElementById('GE3').innerHTML='<input type=\'text\' id=\'GE3\' value=\''+bmiCal+'\' readOnly/>';
		}}			
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
		
		//if(hospId!="EHS34")
		if(hospGovu!=null && hospGovu!="G")
		
			{
			
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
		//else if(hospId!=null && hospId =="EHS34")
		else if(hospGovu!=null && hospGovu=="G")
			{
			
    
 	 
			var a=input.value;
			var fr=partial(focusBox,input);
			var regAlphaNumT=/^[0-9.CF]+$/;
			var inputlength=input.value.length;
			var mainStrlength=inputlength-1;
			var substr=input.value.slice(-1);
			var mainstr=input.value.substring(0,mainStrlength);

				if(input.value<24 || input.value>111){
					jqueryErrorMsg('Temperature Validation',"Allowed temperature range is 24-111 C/F",fr);
					input.value="";
					return false;
					}
				
			
					
			}
	}
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
function validateSpecify(input)
{
	var a=input.value;
	var fr = partial(focusBox,input);
	if(a.trim()=="")
	{
	input.value="";
		jqueryErrorMsg('Text Validation',"Blank spaces are not allowed for Specify");
	return false;
	}
	if(a.charAt(0)==" ")
		{
		input.value="";
		jqueryErrorMsg('Text Validation',"Specify should not start with space");
		return false;
		}
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

function validateSpaces(input,arg1)
{
	var a=input.value;
	var fr = partial(focusBox,input);
	if(a.trim()=="")
	{
	input.value="";
		jqueryErrorMsg('Text Validation',"Blank spaces are not allowed for "+arg1,fr);
	return false;
	}
	if(a.charAt(0)==" ")
	{
		input.value="";
		jqueryErrorMsg('Text Validation',arg1+ " should not start with space",fr);
		return false;
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
function chkSpecailChars(vFileName)
{
   var val =1;  
   var iChars = "*|\":<>[]{}`\';()$#%&^.,!@?/";    
    for (var i = 0; i < vFileName.length; i++) 
    {
        if (iChars.indexOf(vFileName.charAt(i)) != -1)
        {         
          val = 0; break;
        } 
    }
    return val;
}
function validateAlphaNum(arg1,input,fieldType)
{

	var a=input.value;
	
		if(fieldType=="Registration No")
		{
			if(a.trim()=="")
			{
				input.value="";
				alert("Blank spaces are not allowed for "+arg1);
				focusBox(input);
				return false;
			}
			if(a.charAt(0)=="/")
			{
				input.value="";
				alert(arg1+ " should not start with special characters");
				focusBox(input);
				return false;
			}
			if(a.charAt(a.length-1)=="/")
			{
				input.value="";
				alert(arg1+ " should not end with special characters");
				focusBox(input);
				return false;
			}
			var regAlphaNum=/^[0-9a-zA-Z\/]+$/;
			if(a.search(regAlphaNum)==-1)
			{
				input.value="";
				alert("Only alphanumerics and / are allowed for "+arg1);
				focusBox(input);
				return false;
			}
			else
			{
				var flag=true;
				flag=consecutiveSpecialChar(a.trim());
				if(flag)
				{
					input.value=a.trim();
				}
				else
				{
					alert("Consecutive Special Characters and spaces are not allowed for "+arg1);
					focusBox(input);
					input.value="";
					return false;
				}
			}
		}
			
		if(fieldType=="Legal Case No")
		{
			if(a!=null && a!=''){
			if(a.trim()=="")
			{
				input.value="";
				alert("Blank spaces are not allowed for "+arg1);
				focusBox(input);
				return false;
			}
			if(a.charAt(0)=="." || a.charAt(0)=="-")
			{
				input.value="";
				alert(arg1+ " should not start with special characters");
				focusBox(input);
				return false;
			}
			if(a.charAt(a.length-1)=="." || a.charAt(a.length-1)=="-")
			{
				input.value="";
				alert(arg1+ " should not end with special characters");
				focusBox(input);
				return false;
			}
			var regAlphaNum=/^[0-9a-zA-Z.-]+$/;
			if(a.search(regAlphaNum)==-1)
			{
				input.value="";
				alert("Only alphanumerics and .- are allowed for "+arg1);
				focusBox(input);
				return false;
			}
			else
			{
				var flag=true;
				flag=consecutiveSpecialChar(a.trim());
				if(flag)
				{
					input.value=a.trim();
				}
				else
				{
					alert("Consecutive Special Characters and spaces are not allowed for "+arg1);
					focusBox(input);
					input.value="";
					return false;
				}
			}
			}
		}
		
		if(fieldType=="Drug Batch Num")
		{
			if(a!=null && a!=''){
			if(a.trim()=="")
			{
				input.value="";
				alert("Blank spaces are not allowed for "+arg1);
				focusBox(input);
				return false;
			}
			if(a.charAt(0)=="." || a.charAt(0)=="-")
			{
				input.value="";
				alert(arg1+ " should not start with special characters");
				focusBox(input);
				return false;
			}
			if(a.charAt(a.length-1)=="." || a.charAt(a.length-1)=="-")
			{
				input.value="";
				alert(arg1+ " should not end with special characters");
				focusBox(input);
				return false;
			}
			var regAlphaNum=/^[0-9a-zA-Z.-]+$/;
			if(a.search(regAlphaNum)==-1)
			{
				input.value="";
				alert("Only alphanumerics and .- are allowed for "+arg1);
				focusBox(input);
				return false;
			}
			else
			{
				var flag=true;
				flag=consecutiveSpecialChar(a.trim());
				if(flag)
				{
					input.value=a.trim();
				}
				else
				{
					alert("Consecutive Special Characters and spaces are not allowed for "+arg1);
					focusBox(input);
					input.value="";
					return false;
				}
			}
			}
		}
		
		if(fieldType=="AHC Field")
		{
			if(a!=null && a!=''){
			if(a.trim()=="")
			{
				input.value="";
				alert("Blank spaces are not allowed for "+arg1);
				focusBox(input);
				return false;
			}
			if(a.charAt(0)=="." || a.charAt(0)=="-")
			{
				input.value="";
				alert(arg1+ " should not start with special characters");
				focusBox(input);
				return false;
			}
			if(a.charAt(a.length-1)=="." || a.charAt(a.length-1)=="-")
			{
				input.value="";
				alert(arg1+ " should not end with special characters");
				focusBox(input);
				return false;
			}
			var regAlphaNum=/^[0-9a-zA-Z.]+$/;
			if(a.search(regAlphaNum)==-1)
			{
				input.value="";
				alert("Only alphanumerics and . are allowed for "+arg1);
				focusBox(input);
				return false;
			}
			else
			{
				var flag=true;
				flag=consecutiveSpecialChar(a.trim());
				if(flag)
				{
					input.value=a.trim();
				}
				else
				{
					alert("Consecutive Special Characters and spaces are not allowed for "+arg1);
					
					input.value="";
					focusBox(input);
					return false;
				}
			}
			}
		}
		else{
				if(a.trim()=="")
				{
					input.value="";
					alert("Blank spaces are not allowed for "+arg1);
					focusBox(input);
					return false;
				}
				var regAlphaNum=/^[0-9a-zA-Z]+$/;
				if(a.search(regAlphaNum)==-1)
				{
					input.value="";
					alert("Only alphanumerics are allowed for "+arg1);
					focusBox(input);
					return false;
				}
				else
				{
					input.value=a.trim();
				}
			}
		return true;
}

function blockConsecutiveChars(name,input, value)
{
	var flag=true;
	var fr=partial(focusBox,input);
	flag=consecutiveSpecialChar(value.trim());
	if(flag)
	{
		input.value=value.trim();
	}
	else
	{
		jqueryErrorMsg('Special Character Validation',"Consecutive spaces are not allowed for "+name,fr);
		input.value="";
		return false;
	}
}


function confirmRemoveRow(src,type)
{
	var fr;
	if(type=="speciality")
		{
		fr=partial(removeProcInvest,src);
		jqueryConfirmMsg('Delete Procedure Investigation Confirmation','Do you want to delete Procedure Investigation?',fr);
		}
	else if(type=="drug")
		{
		fr=partial(removeDrug,src);
		jqueryConfirmMsg('Delete Drug Confirmation','Do you want to delete Drug?',fr);
		}
	else if(type=="symptom")
	{
		fr=partial(removeSymptoms,src);
		jqueryConfirmMsg('Delete Symptom Confirmation',"Do you want to delete Symptom?",fr);
		}
	else if(type=="geninvestigations")
		{
		fr=partial(removeGenInvestigations,src);
		jqueryConfirmMsg('Delete General Investigation Confirmation',"Do you want to delete General Investigation?",fr);
		}
	else if(type=="geninvestigation")
	{
	fr=partial(deleteGenInvest,src);
	jqueryConfirmMsg('Delete General Investigation Confirmation',"Do you want to delete General Investigation?",fr);
	}
	else if(type=="ipinvestigations")
		{
		fr=partial(removeRow,src);
		jqueryConfirmMsg('Delete IP Investigation Confirmation',"Do you want to delete IP Investigation?",fr);
		}
	else if(type=='procSelected')
		{
		if(src.value!='0')
		{
			jqueryConfirmMsg('Delete Procedure Confirmation','Do you want to delete selected Procedure?',removeProcSelected);
		}
		}
	else if(type=="editDrug")
		{
		fr=partial(editDrug,src);
		jqueryConfirmMsg('Edit Drug Confirmation',"Do you want to edit drug?",fr);
		}
		//parent.fn_resizePage();
}

function removeProcInvest(src)
{
		var oRow=src.parentNode.parentNode;
		var matchCount=0;
		var matchProcCount=0;
		var remSpecValues=spec[oRow.rowIndex-1].split("~");
		spec.splice(oRow.rowIndex-1,1);
		otherDocDetails.splice(oRow.rowIndex-1,1);
		//alert(remSpecValues[2]);
		for(var i=0;i<spec.length;i++)
		{
			var specValues=spec[i].split("~");
			if(specValues[2]==remSpecValues[2])
			{
			matchProcCount++;
			}
			if(specValues[2]==remSpecValues[2] && remSpecValues[0]=='' && remSpecValues[1]=='')
				{
				matchCount++;
				speciality=remSpecValues[0]+"~"+remSpecValues[1]+"~"+specValues[2]+'~'+specValues[3]+'~'+specValues[4]+'~'+specValues[5]+"~"+specValues[6]+"~"+specValues[7]+'~'+specValues[8]+'~'+specValues[9];
				spec[i]=speciality;
				}
			if(matchCount==1)
				break;
		}
		if(matchProcCount==0)
			{
				for(var p=0;p<procList.length;p++)
				{
				var procValues=procList[p].split("~");
				if(procValues[1]==remSpecValues[2])
					{
					procList.splice(p,1);
					var localSchemeId=document.getElementById("scheme").value;
					deleteLst.push(procValues[1]);
					if(localSchemeId!=null && localSchemeId!='' && localSchemeId=='CD202')
						removeComboProc(procValues[1]);
					}
				}
			}
		document.getElementById("speciality").value=spec;
		document.getElementById("otherDocDetailsList").value=otherDocDetails;
		document.getElementById("categoryTable").deleteRow(oRow.rowIndex);
		catCount--;
		for(var i=1;i<=catCount;i++)
			{
			
			var newRow=document.getElementById("categoryTable").rows[i];
			var snoCell=newRow.cells[0];
			//snoCell.innerHTML='<td width="10%">'+parseInt(i)+'</td>';
			}
		
		if(catCount==0)
			{
			document.getElementById("categoryTable").style.display="none";
			if(specOld.length==0)
				medOrSur='';
			}
		document.getElementById("procSelectedTd").style.display='none';
		if(!document.getElementById("telephonicId").value=='')
		{
			var therapy=document.getElementById("therapy").value;
			var therapyMatch=0;
			for(var c=0;c<spec.length;c++)
			{
				var specValues=spec[c].split("~");
				if(specValues[2]!=therapy)
				{
					therapyMatch++;
				}
			}
			if(therapyMatch==0)
			{
				document.getElementById("treatingDocLabel").style.display='none';
				document.getElementById("treatingDocRemarks").innerHTML='';
			}
		}
		//parent.fn_resizePage();
}
function removeProcInvestOnload(src,procCode,investId,asriCatCode){
	
	deleteLst.push(procCode);
	var localSchemeId=document.getElementById("scheme").value;
	if(localSchemeId!=null && localSchemeId!='' && localSchemeId=='CD202')
		removeComboProc(procCode);
	
    specRemove = specRemove+procCode+"~"+asriCatCode+"~"+investId+"@";		
	var oRow = src.parentNode.parentNode; 
	document.getElementById("categoryTableID").deleteRow(oRow.rowIndex);           		
	for(var i=0;i<specOld.length;i++)
		{
			if(procCode!='' && procCode!=null && procCode!=' ' )
				{
					if(specOld[i].indexOf(procCode,0)!=-1)
	   					specOld.splice(i,1);
				}		
			else if(investId!='' && investId!='NA' && investId!=null)
       			{
           			if(specOld[i].indexOf(investId,0)!=-1)
        				specOld.splice(i,1);
           		}
       	}
   		
    if(specOld.length==0)
    	
			{
				document.getElementById('categoryTableID').style.display='none';
				if(catCount==0)
					medOrSur='';
			}
}
function unitsLeftCond(jsonData) 													
{
	var totalPreLimit=0,lifeTimeUnitsLeft=0;
	var units=document.getElementById("procUnits").value;
	if(units!=null && units!='-1')
		{
			if(jsonData.lifetimeUnitsLimit!=null && jsonData.lifetimeUnitsLimit!='-1' && jsonData.lifetimeUnitsLimit!='')
				totalPreLimit=Number(jsonData.lifetimeUnitsLimit);
			if(jsonData.actualUnitsLeft!=null && jsonData.actualUnitsLeft!='' && jsonData.actualUnitsLeft!=' ')
				lifeTimeUnitsLeft=Number(jsonData.actualUnitsLeft);
			
			if(units > lifeTimeUnitsLeft && lifeTimeUnitsLeft!='-1' )
				{
					if(lifeTimeUnitsLeft=='0')
						bootbox.alert("No Units can be treated as maximum units i.e. "+totalPreLimit+" are already utilized by the Patient for Selected Procedure");
					else
						bootbox.alert("Maximum Units that be treated for Selected Procedure are "+totalPreLimit+" .A maximum of "+lifeTimeUnitsLeft+" can only be selected as "+(totalPreLimit-lifeTimeUnitsLeft)+" are already utilized by the Patient");
/*						else if (totalPreLimit-lifeTimeUnitsLeft==0)	
						alert("Maximum Units that be treated for Selected Procedure are "+totalPreLimit+" .A maximum of "+lifeTimeUnitsLeft+" can only be selected");
*/
					document.getElementById("procUnits").value="-1";
					false;
				}
		}
}
function comboDentalConditions(icdProcCode,procName,jsonData)
{
	//Added by Srikalyan for TG Combo Dental Conditions 
	//Format for Combo Conditions SelectedProcID!@#SelectedProcName,ComboId1@ComboName1~ComboId2@ComboName2
	var comboProcs=jsonData.comboProcCode;
	var comboProcNames=jsonData.comboProcNames;
	var comboProcsLst=null,comboProcsNamesLst=null,addComboProcs='N';
	
	/*if(comboProcs != null && comboProcs != '')
		 comboProcsLst=comboProcs.split("~");
	if(comboProcNames != null && comboProcNames != '')
		comboProcsNamesLst=comboProcNames.split("~");
	if(comboProcsLst!=null && comboProcsLst.length > 0 && comboProcsLst[0]!=null && comboProcsLst[0]!='NA')
		{
			if(comboProcIds!=null && comboProcIds!='' && comboProcIds.indexOf(icdProcCode+"!@#")=='-1' )
				{
					comboProcIds=comboProcIds+"$"+icdProcCode+"!@#"+procName;
					addComboProcs='Y';
				}	
			else
				{
					comboProcIds=icdProcCode+"!@#"+procName;
					addComboProcs='Y';
				}
			if(addComboProcs=='Y')
				{
					for(var a=0 ; a < comboProcsLst.length ; a++)
						{
							if(a==0)
								comboProcIds=comboProcIds+","+(comboProcsLst[a]+"@"+comboProcsNamesLst[a]);
							else
								comboProcIds=comboProcIds+"~"+(comboProcsLst[a]+"@"+comboProcsNamesLst[a]);
						}
				}	
		}*/
	//Added by Srikalyan for TG Non Combo Dental Conditions
	//Format for Non Combo Conditions SelectedProcID!@#SelectedProcName,NonComboId1@NonComboName1~NonComboId2@NonComboName2
	var noncomboProcs=jsonData.nonComboProcCode;
	var noncomboProcNames=jsonData.comboNonProcNames;
	var noncomboProcsLst=null,noncomboProcsNamesLst=null,addNonComboProcs='N';
	
	if(noncomboProcs != null && noncomboProcs != '')
		noncomboProcsLst=noncomboProcs.split("~");
	if(noncomboProcNames != null && noncomboProcNames != '')
		noncomboProcsNamesLst=noncomboProcNames.split("~");
	
	if(noncomboProcsLst!=null && noncomboProcsLst.length > 0 && noncomboProcsLst[0]!=null && noncomboProcsLst!='NA')
		{
			if(nonComboProcIds!=null && nonComboProcIds!='' && nonComboProcIds.indexOf(icdProcCode+"!@#")=='-1' )
				{
					nonComboProcIds=nonComboProcIds+"$"+icdProcCode+"!@#"+procName;
					addNonComboProcs='Y';
				}	
			else
				{
					nonComboProcIds=icdProcCode+"!@#"+procName;
					addNonComboProcs='Y';
				}
			if(addNonComboProcs=='Y')
				{
					for(var a=0 ; a < noncomboProcsLst.length ; a++)
						{
							if(a==0)
								nonComboProcIds=nonComboProcIds+","+(noncomboProcsLst[a]+"@"+noncomboProcsNamesLst[a]);
							else
								nonComboProcIds=nonComboProcIds+"~"+(noncomboProcsLst[a]+"@"+noncomboProcsNamesLst[a]);
						}
				}	
		}
	
	//Added by Srikalyan for TG Non Combo Dental Conditions
	//Format for StandAlone Proc Conditions SelectedProcID!@#SelectedProcName,NonComboId1@NonComboName1~NonComboId2@NonComboName2
	var stanAloneProcsLoc=jsonData.standaloneProc;
	var stanAloneProcNamesLoc=jsonData.standaloneProcNames;
	var stanaloneProcsLst=null,standaloneProcsNamesLst=null,standaloneProcs='N';
	if(stanAloneProcsLoc != null && stanAloneProcsLoc != '')
		stanaloneProcsLst=stanAloneProcsLoc.split("~");
	if(stanAloneProcNamesLoc != null && stanAloneProcNamesLoc != '')
		standaloneProcsNamesLst=stanAloneProcNamesLoc.split("~");
	
	if(stanaloneProcsLst!=null && stanaloneProcsLst.length > 0 && stanaloneProcsLst[0]!=null && stanaloneProcsLst[0]!='NA' )
		{
			if(standaloneProcIds!=null && standaloneProcIds!='' && standaloneProcIds.indexOf(icdProcCode+"!@#")=='-1' )
				{
					standaloneProcIds=standaloneProcIds+"$"+icdProcCode+"!@#"+procName;
					standaloneProcs='Y';
				}	
			else
				{
					standaloneProcIds=icdProcCode+"!@#"+procName;
					standaloneProcs='Y';
				}
			if(standaloneProcs=='Y')
				{
					for(var a=0 ; a < stanaloneProcsLst.length ; a++)
						{
							if(a==0)
								standaloneProcIds=standaloneProcIds+","+(stanaloneProcsLst[a]+"@"+standaloneProcsNamesLst[a]);
							else
								standaloneProcIds=standaloneProcIds+"~"+(stanaloneProcsLst[a]+"@"+standaloneProcsNamesLst[a]);
						}
				}	
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
	
}

function removeComboProc(procCode)
{
	var newProcCode=null;
	/*if(comboProcIds!=null && comboProcIds!='' && comboProcIds!=' ')
		{
			var procWiseLst=comboProcIds.split("$");
			var oldLength=procWiseLst.length;
			newProcCode=commonCodeinRemove(procWiseLst,procCode);//Check whether the Proc Code contains in Existing Combo Codes
			if((newProcCode!=null && newProcCode!='' && newProcCode!=' ' && newProcCode!='N') || (oldLength!=null && oldLength==1 && (newProcCode==null || newProcCode=='' || newProcCode==' ')))
				comboProcIds=newProcCode;
		}*/
	if(nonComboProcIds!=null && nonComboProcIds!='' && nonComboProcIds!=' ')
		{
			newProcCode=null;
			var procWiseLst=nonComboProcIds.split("$");
			var oldLength=procWiseLst.length;
			newProcCode=commonCodeinRemove(procWiseLst,procCode);//Check whether the Proc Code contains in Existing Non Combo Codes	
			if((newProcCode!=null && newProcCode!='' && newProcCode!=' ' && newProcCode!='N') || (oldLength!=null && oldLength==1 && (newProcCode==null || newProcCode=='' || newProcCode==' ')))
				nonComboProcIds=newProcCode;
		}
	if(standaloneProcIds!=null && standaloneProcIds!='' && standaloneProcIds!=' ')
		{
			newProcCode=null;
			var procWiseLst=standaloneProcIds.split("$");
			var oldLength=procWiseLst.length;
			newProcCode=commonCodeinRemove(procWiseLst,procCode);//Check whether the Proc Code contains in Existing Stand Alone Non Combo Codes
			if((newProcCode!=null && newProcCode!='' && newProcCode!=' ' && newProcCode!='N') || (oldLength!=null && oldLength==1 && (newProcCode==null || newProcCode=='' || newProcCode==' ')))
				standaloneProcIds=newProcCode;
		}
}

function validatePropSurgeryDate(arg1,input)
{
	var entered = input.value;
	entered = entered.split("-");
	var byr = parseInt(entered[2]); 
	if(isNaN(byr))
	{
		input.value="";
		jqueryErrorMsg('Proposed Surgery Date Validation',"Select "+arg1);
	}
	else
	{
	var bmth = parseInt(entered[1],10); 
	var bdy = parseInt(entered[0],10);
	var DoB=""+(bmth)+"/"+ bdy +"/"+ byr;
	DoB=Date.parse(DoB);
	var today= new Date();
	var nowmonth = today.getMonth();
	var nowday = today.getDate();
	var nowyear = today.getFullYear();
	var DoC=""+(nowmonth+1)+"/"+ nowday +"/"+ nowyear;
	DoC=Date.parse(DoC);
	if(DoC>DoB)
		{
		input.value="";
		jqueryErrorMsg('Proposed Surgery Date Validation',arg1+" should be greater than or equal to today's date");
		}
	else
		{
	var maxSurgDate = new Date();
	maxSurgDate.setMonth(maxSurgDate.getMonth()+3);
	var maxSurgCal=""+(maxSurgDate.getMonth()+1)+"/"+maxSurgDate.getDate()+"/"+ maxSurgDate.getFullYear();
	maxSurgCal=Date.parse(maxSurgCal);
	if(DoB>maxSurgCal)
		{
		input.value="";
		jqueryErrorMsg('Proposed Surgery Date Validation',arg1+" should not be greater than 3 months from today's date");
		}
		}
	}
	
}
function allDentalConditions(jsonData)
{
	var singlePreauthUnitsLimit=0;
	if(jsonData.ageMsg != null && jsonData.ageMsg != '' && jsonData.ageMsg != ' ' )
		{
			resetDentalProc();
			bootbox.alert(jsonData.ageMsg);
			focusBox(document.getElementById("ICDProcName"));
			return false;
		}
	if(jsonData.unitsLimit != null && jsonData.unitsLimit !='' && jsonData.unitsLimit !=' ')
		{
			singlePreauthUnitsLimit=Number(jsonData.unitsLimit);			
			document.forms[0].procUnits.options.length=0;
			document.forms[0].procUnits.options[0]=new Option("---select---","-1");
			document.getElementById("unitsTd").style.display='';
			document.getElementById("unitsLabelTd").style.display='';
			
			if(singlePreauthUnitsLimit==-1)
				singlePreauthUnitsLimit=32;
			
			for(var i = 1; i<=singlePreauthUnitsLimit;i++)
				{	
					document.forms[0].procUnits.options[i] =new Option(i,i);
				}
		}
	else if(jsonData.unitsLimit == null || jsonData.unitsLimit =='' || jsonData.unitsLimit ==' '){
		document.getElementById("unitsTd").style.display='none';
		document.getElementById("unitsLabelTd").style.display='none';
	}
	
	if(jsonData.nonComboProcCode!=null && jsonData.nonComboProcCode!='' && jsonData.nonComboProcCode!=' '
			&& jsonData.comboNonProcNames!=null && jsonData.comboNonProcNames!='' && jsonData.comboNonProcNames!=' ' )
		{
			var locSpecLst=new Array();
			var specty=document.getElementById('speciality').value;
			var nonComboLst=jsonData.nonComboProcCode.split("~");
			var nonComboNames=jsonData.comboNonProcNames.split("~");
			
			if(specOld!=null && specty!=null)
				locSpecLst=specOld.concat(specty.split(","));
			else if((specty=='' || specty==' ' || specty==null) && specOld!=null)
				locSpecLst=specOld;
			else if(specty!=null && specty!='' && specty!=' ')
				locSpecLst=specty.split(",");
			
			//Checking for each previously Speciality
			for(var i=0;i<locSpecLst.length;i++)
				{
					var listValues=locSpecLst[i].split("~");
					if(listValues[2]!=null)
						{
							//Checking every Combo Code For individual Speciality
							if(nonComboLst != null && nonComboLst.length > 0 )
								{
									for(var j=0 ; j<nonComboLst.length;j++ )
										{
											if(listValues[2]==nonComboLst[j])
												{
													var procName=$("#ICDProcName option:selected").text();
													var alertCont="For "+procName+"("+$('#ICDProcName').val()+") its non Combinational Procedure ";
													alertCont+=nonComboNames[j]+"("+nonComboLst[j]+")"+" has already been added.Hence current Procedure cannot be selected.";
													
													resetDentalProc();
													bootbox.alert(alertCont);
													focusBox(document.getElementById("ICDProcName"));
													
													return false;
												}
										}
								}
						}
				}
			
		}
	if(jsonData.standaloneProc!=null && jsonData.standaloneProc!='' && jsonData.standaloneProc!=' '
			&& jsonData.standaloneProcNames!=null && jsonData.standaloneProcNames!='' && jsonData.standaloneProcNames!=' ' )
		{
			var locSpecLst=new Array();
			var specty=document.getElementById('speciality').value;
			var standProcLst=jsonData.standaloneProc.split("~");
			var standProcNames=jsonData.standaloneProcNames.split("~");
			
			if(specOld!=null && specty!=null)
				locSpecLst=specOld.concat(specty.split(","));
			else if((specty=='' || specty==' ' || specty==null) && specOld!=null)
				locSpecLst=specOld;
			else if(specty!=null && specty!='' && specty!=' ')
				locSpecLst=specty.split(",");
			
			//Checking for each previously Speciality
			for(var i=0;i<locSpecLst.length;i++)
				{
					var listValues=locSpecLst[i].split("~");
					if(listValues[2]!=null)
						{
							//Checking every Combo Code For individual Speciality
							if(standProcLst != null && standProcLst.length > 0 )
								{
									for(var j=0 ; j<standProcLst.length;j++ )
										{
											if(listValues[2]==standProcLst[j])
												{
													var procName=$("#ICDProcName option:selected").text();
													var alertCont="For "+procName+"("+$('#ICDProcName').val()+",Stand Alone Procedure) its non Combinational Procedure ";
													alertCont+=standProcNames[j]+"("+standProcLst[j]+")"+" has already been added.Hence current Procedure cannot be selected.";
													
													resetDentalProc();
													bootbox.alert(alertCont);
													focusBox(document.getElementById("ICDProcName"));
													
													return false;
												}
										}
								}
						}
				}
			
		}
	
	var finalAlertNonConmbo="",finalAlertStandAlone="";
	if(		   jsonData.nonComboProcCode!=null && jsonData.nonComboProcCode!='' && jsonData.nonComboProcCode!=' '
			&& jsonData.comboNonProcNames!=null && jsonData.comboNonProcNames!='' && jsonData.comboNonProcNames!=' '
			&& jsonData.standaloneProc!=null && jsonData.standaloneProc!='' && jsonData.standaloneProc!=' '
			&& jsonData.standaloneProcNames!=null && jsonData.standaloneProcNames!='' && jsonData.standaloneProcNames!=' ')
		{
			var nonComboCodesNames=jsonData.comboNonProcNames.split("~");
			var standaloneProceNames=jsonData.standaloneProcNames.split("~");
			var nonComboCodes=jsonData.nonComboProcCode.split("~");
			var standComboCodes=jsonData.standaloneProc.split("~");
			for(var i=0 ; i<nonComboCodesNames.length ; i++)
				{
					if(i==0)
						finalAlertNonConmbo=nonComboCodesNames[i]+"("+nonComboCodes[i]+")";
					else
						finalAlertNonConmbo=finalAlertNonConmbo+" and "+nonComboCodesNames[i]+"("+nonComboCodes[i]+")";
				}
			for(var i=0 ; i<standaloneProceNames.length ; i++)
				{
					if(i==0)
						finalAlertStandAlone=standaloneProceNames[i]+"("+standComboCodes[i]+")";
					else
						finalAlertStandAlone=finalAlertStandAlone+" and "+standaloneProceNames[i]+"("+standComboCodes[i]+")";
				}
			
			bootbox.alert(+finalAlertNonConmbo+" should not be added in combination with selected Procedure,As the Selected Procedure is StandAlone Procedure "+finalAlertStandAlone+"should not be added in combination with selected Procedure");
		}
	else
		{
			if(jsonData.nonComboProcCode!=null && jsonData.nonComboProcCode!='' && jsonData.nonComboProcCode!=' '
					&& jsonData.comboNonProcNames!=null && jsonData.comboNonProcNames!='' && jsonData.comboNonProcNames!=' ')
				{
					var nonComboCodesNames=jsonData.comboNonProcNames.split("~");
					var nonComboCodes=jsonData.nonComboProcCode.split("~");
					for(var i=0 ; i<nonComboCodesNames.length ; i++)
						{
							if(i==0)
								finalAlertNonConmbo=nonComboCodesNames[i]+"("+nonComboCodes[i]+")";
							else
								finalAlertNonConmbo=finalAlertNonConmbo+" and "+nonComboCodesNames[i]+"("+nonComboCodes[i]+")";
						}
					
					bootbox.alert(finalAlertNonConmbo+" should not be added in combination with selected Procedure");
				}
			if(jsonData.standaloneProc!=null && jsonData.standaloneProc!='' && jsonData.standaloneProc!=' '
				&& jsonData.standaloneProcNames!=null && jsonData.standaloneProcNames!='' && jsonData.standaloneProcNames!=' ' )
				{
					var standaloneProceNames=jsonData.standaloneProcNames.split("~");
					var standComboCodes=jsonData.standaloneProc.split("~");
					for(var i=0 ; i<standaloneProceNames.length ; i++)
						{
							if(i==0)
								finalAlertStandAlone=standaloneProceNames[i]+"("+standComboCodes[i]+")";
							else
								finalAlertStandAlone=finalAlertStandAlone+" and "+standaloneProceNames[i]+"("+standComboCodes[i]+")";
						}
					
					bootbox.alert("As the selected Procedure is a Stand Alone Procedure "+finalAlertStandAlone+" should not be added in combination ");
				}
		}
	
}
//Used to validate consecutive special characters and spaces
function consecutiveSpecialChar(value)
{
	var str = /\W/g;
	var Symbol = "";
	var count=0;
	if (value.match(str))
	{
		var var_length = value.length;
		var i = 0;
		for (i = 0; i < var_length-1; i++) 
		{
			symbol1 = value.charAt(i);
			symbol2 = value.charAt(i+1);
			if ((symbol1.match(str) && symbol2.match(str))) {
				return false;
			}
			else
			{
				count=count+1;
			}
		}
		if(count==var_length-1)
		{
			return true;
		}
	}
	else
	{
		return true;
	}

}
function checkAlphaSpace(arg1,arg2) //only alphabets A-Z and a-z
{
    var Names=eval("document.forms[0]."+arg1);
    var fr = partial(focusBox,Names);
    var Names1=Names.value;
    if(Names1.trim()=="")
  	{
		Names.value="";
    	jqueryErrorMsg('Alphabet Validation','Blank spaces are not allowed for '+arg2,fr);
		return false;
  	}
    if(Names1 != null && Names1 != "")
    {
		var reg=/^[a-zA-Z ]+$/;
		if(Names1.search(reg)==-1)
		{
    	   Names.value="";
    	   jqueryErrorMsg('Alphabet Validation','Enter only alphabets in '+arg2,fr);
           return 0;
		}
		else
		{
			var flag=true;
			flag=consecutiveSpecialChar(Names1.trim());
			if(flag)
			{
				Names.value=Names1.trim();
			}
			else
			{
				jqueryErrorMsg('Special Character Validation',"Consecutive Special Characters and spaces are not allowed for "+arg2,fr);
				Names.value="";
				return false;
			}
		}
     
    }
}

function removeSymptoms(src){
	var oRow=src.parentNode.parentNode;
	symptoms.splice(oRow.rowIndex-1,1);
	document.getElementById("symptoms").value=symptoms;
	document.getElementById("symptomsTable").deleteRow(oRow.rowIndex);
	symptomCount--;
	for(var i=1;i<=symptomCount;i++)
		{
		var newRow=document.getElementById("symptomsTable").rows[i];
		var snoCell=newRow.cells[0];
		snoCell.innerHTML='<td width="10%">'+parseInt(i)+'</td>';
		}
	if(symptomCount==0)
		{
		document.getElementById("symptomsTable").style.display="none";
		}
		//parent.fn_resizePage();
}

function removeRow(src)
{  
		var availableList1 = document.getElementById('investigations');
		var selectedList1  = document.getElementById('investigationSel');  

		for(var i=0;i<selectedList1.length;i++)
		{
			if(src.name==selectedList1[i].id){        
				var newOption = new Option();
				newOption.text = selectedList1.options[i].text; 
				newOption.value = selectedList1.options[i].value;
				newOption.id = newOption.value ;
				availableList1.options[availableList1.length] = newOption; 

				selectedList1.options.selectedIndex=i;
				selectedList1.remove(selectedList1.options.selectedIndex);         
				// var oRow = src.parentElement.parentElement;----Not working in mozilla
				var oRow = src.parentNode.parentNode; 
				// once the row reference is obtained, delete it passing in its rowIndex/
				// document.all("testTable").deleteRow(oRow.rowIndex);----Not working in mozilla
				document.getElementById("testTable").deleteRow(oRow.rowIndex);
				var investTableId=document.getElementById('testTable');
				for(var i=1;selectedList1.length>=i;i++)
				{
					var newRow=investTableId.rows[i];
					var snocell=newRow.cells[0];
					snocell.innerHTML='<td width="10%">'+parseInt(i)+'</td>';
				}
				if(selectedList1.length==0){
					document.getElementById('testTable').style.display='none';
				}
			}
		} 
		//parent.fn_resizePage();
}
var str="";
function removeProcSelected()
{
	if(catCount>0)
	{
		var procCode;
		var procSelected=document.getElementById("procSelected");
		for(var p=0;p<procSelected.length;p++)
		{
			if(procSelected[p].selected)
			{
				procCode=procSelected[p].value;
			}
		}
			for(var x=0;x<spec.length;x++)
			{			
			var specValues=spec[x].split("~");
			if(specValues[2]==procCode)
				{
				document.getElementById("categoryTable").deleteRow(x+1);
				spec.splice(x,1);
				//otherDocDetails.splice(x,1);
				catCount--;
				x--;
				}
			}
			document.getElementById("speciality").value=spec;
			for(var i=1;i<=catCount;i++)
				{
				var newRow=document.getElementById("categoryTable").rows[i];
				var snoCell=newRow.cells[0];
				snoCell.innerHTML='<td width="10%">'+parseInt(i)+'</td>';
				}
			if(catCount==0)
				{
				document.getElementById("categoryTable").style.display="none";
				if(specOld.length==0)
					medOrSur='';
				}
			for(var d=0;d<procList.length;d++)
				{
					var procValues=procList[d].split("~");
					if(procValues[1]==procCode)
					procList.splice(d,1);
				}
			
			if(!document.getElementById("telephonicId").value=='')
			{
				var therapy=document.getElementById("therapy").value;
				var therapyMatch=0;
				for(var c=0;c<spec.length;c++)
				{
					var specValues=spec[c].split("~");
					if(specValues[2]!=therapy)
					{
						therapyMatch++;
					}
				}
				if(therapyMatch==0)
				{
					document.getElementById("treatingDocLabel").style.display='none';
					document.getElementById("treatingDocRemarks").innerHTML='';
				}
			}
		}
	else
	  {
	 return false;
	  }
	  document.getElementById("procSelectedTd").style.display='none';
	 // parent.fn_resizePage();
}

function editDrug(src)
{
	var oRow=src.parentNode.parentNode;
	var drugString=existDrugsArr[oRow.rowIndex-1];
	var drugCodes=drugString.split("~");
	
	var drugType=drugCodes[0].substring(drugCodes[0].lastIndexOf('(')+1,drugCodes[0].length-1);
	document.getElementById("drugTypeCode").value=drugType;
	getDrugSubTypeList();
	
	var drugSubType=drugCodes[1].substring(drugCodes[1].lastIndexOf('(')+1,drugCodes[1].length-1);
	document.getElementById("drugSubTypeName").value=drugSubType;
	document.getElementById("drugSubTypeCode").value=drugSubType;
	getDrugNameList();
	
	var drugCode=drugCodes[2].substring(drugCodes[2].lastIndexOf('(')+1,drugCodes[2].length-1);
	document.getElementById("drugName").value=drugCode;
	document.getElementById("asriDrugCode").value=drugCode;
	
	document.getElementById("route").value=drugCodes[3];
	document.getElementById("strength").value=drugCodes[4];
	document.getElementById("dosage").value=drugCodes[5];
	document.getElementById("medicatPeriod").value=drugCodes[6];

	//parent.fn_resizePage();
}

function removeDrug(src)
{
		var oRow=src.parentNode.parentNode;
		drugs.splice(oRow.rowIndex-1,1);
		document.getElementById("drugs").value=drugs;
		document.getElementById("drugsTable").deleteRow(oRow.rowIndex);
		drugCount--;
		for(var i=1;i<=drugCount;i++)
			{
			var newRow=document.getElementById("drugsTable").rows[i];
			var snoCell=newRow.cells[0];
			snoCell.innerHTML='<td width="10%">'+parseInt(i)+'</td>';
			}
		if(drugCount==0)
			{
			document.getElementById("drugsTable").style.display="none";
			document.getElementById("prescription").disabled=true;
			document.getElementById("prescription").className='butdisable';
			}
		calculateDrugsAmt("remove");
			//parent.fn_resizePage();
}

function confirmRemoveGenInvest(src,type,investId,price){
	fr=partial(removeGenInvestOnload,src,investId,price);
	jqueryConfirmMsg('Delete General Investigation Confirmation',"Patient Type IP details entered will be reset.Do you want to delete general investigations?",fr);
}
function confirmRemoveChronicInvest(src,type,investId){
	fr=partial(removeGenInvestOnload,src,investId,'');
	jqueryConfirmMsg('Delete General Investigation Confirmation',"Do you want to delete general investigations?",fr);
}
function confirmRemoveSymOnload(src,type,id){
	if(type=="drug")
	{
	fr=partial(removeDrugAjax,src,id);
	jqueryConfirmMsg('Delete Drug Confirmation','Do you want to delete Drug?',fr);
	}
else if(type=="symptom")
{
	fr=partial(removeSymptomsAjax,src,id);
	jqueryConfirmMsg('Delete Symptom Confirmation',"Do you want to delete Symptom?",fr);
	}
}
function confirmRemoveRowOnload(src,type,procCode,investId,asriCatCode)
{
		fr=partial(removeProcInvestOnload,src,procCode,investId,asriCatCode);
		jqueryConfirmMsg('Delete Procedure Investigation Confirmation','Do you want to delete Procedure Investigation?',fr);
       // parent.fn_resizePage();
}

function removeGenInvestOnload(src,investId,price)
{
	
    genInvestRemove=genInvestRemove+investId+"@";
		var oRow = src.parentNode.parentNode; 
		
		
		document.getElementById("genTestTableID").deleteRow(oRow.rowIndex);
		genOldList.splice(oRow.rowIndex-1,1);
		
		for(var i=0;i<genOldList.length;i++){
	        if(genOldList[i].indexOf(investId,0)!=-1)
	        	{
	        	
	        	
	        	var schemeId=document.getElementById("scheme").value;
	        	var patientScheme="";
	        	if(document.getElementById("patientScheme"))
	        	 patientScheme=document.getElementById("patientScheme").value;
	        	var hospType=document.getElementById("hosptype").value;
	        	
	        	genOldList.splice(i,1);
	        	if(document.forms[0].patientType[0].checked)
	        		{
	        	
	        	if( schemeId=="CD202" && patientScheme=="CD501" && hospType=="G")
	 		{
	        	
	        	var invPrice=price;
	        	
//alert(invPrice);
	        	var totInvestPrice=document.forms[0].totInvestPrice.value;
	        	var effInvPrice=totInvestPrice-invPrice;
	        	document.forms[0].totInvestPrice.value=effInvPrice;
	        	document.getElementById("totalInvPrice").innerHTML='<b><font color="#B01000"> &#x20B9;'+effInvPrice+'</font></b>';
	        	
	        	document.getElementById("costOfInvest").innerHTML='<b><font color="#B01000"> &#x20B9;'+effInvPrice+'</font></b>';
	        	//var totOpCost=document.forms[0].totalOpCost.value;
	        	var consulAmt=document.getElementById("consultFee").value;
	        	var effOpCost=parseFloat(consulAmt)+parseFloat(effInvPrice);
	        	//document.forms[0].totalOpCost.value=effOpCost;
	        	document.getElementById("totalOpCost").innerHTML='<b><font color="#B01000"> &#x20B9;'+effOpCost+'</font></b>';
	        			}
	        	
	        	
	        	
		         }
	        	}
	       	}
		
 if(genOldList.length==0)
			{
 	//document.getElementById("opIcdName").disabled=false;
		//document.getElementById("opPkgName").disabled=false;
		document.getElementById('genTestTableID').style.display='none';
		//document.getElementById("drugs").value="";
		//document.getElementById("drugsTable").style.display="none";
 	
				if(genInventCount==0)
				{
					document.forms[0].patientType[1].disabled=true;
					document.forms[0].patientType[1].checked=false;
					document.forms[0].patientType[0].checked=false;
					//document.forms[0].patientType[2].checked=false;
					//document.forms[0].patientType[1].value="";
					//document.forms[0].patientType[0].value="";
					var a=document.getElementById('patientType').value;
					document.getElementById("patientType").value="";
					var b=document.getElementById("patientType").value;
					
					
					if((a!="ChronicOP"))
					{
					document.getElementById("diagType").value=-1;
					document.getElementById("diagCode").value=-1;
					document.getElementById("mainCatName").options.length = 1;
					document.getElementById("mainCatCode").value = "";
					document.getElementById("catName").options.length = 1;
					document.getElementById("catCode").value=-1;
					document.getElementById("subCatName").options.length = 1;
					document.getElementById("subCatCode").value = "";
					document.getElementById("diseaseName").options.length = 1;
					document.getElementById("diseaseCode").value = "";
					document.getElementById("disAnatomicalName").options.length = 1;
					document.getElementById("disAnatomicalCode").value = "";
					
					
					document.getElementById('IPHead2').style.display='none';
					document.getElementById("diagnosisData").style.display="none";
					document.getElementById('OPHead2').style.display='none';
					document.getElementById("prescriptionData").style.display="none";
					document.getElementById("OPDoctor").style.display="none";
//					document.getElementById("ChronicOPTherapy").style.display="none";
				}}
			}
 
 
 
}






function getDrugNameList()
{
	var chronicId=document.getElementById("patientNo").value;
	document.getElementById("cSubGrpCode").value="";
	document.getElementById("drugName").options.length=1;
	getDrugDetails();
	if(document.getElementById("cSubGrpName").value=="-1")
		{
		return false;
		}
	else
		{
	var drugSubTypeCode=document.getElementById("cSubGrpName").value;
	document.getElementById("cSubGrpCode").value=drugSubTypeCode;
	var xmlhttp;
    var url;

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
    	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else{
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("@]*","");            
                	var drugList = resultArray.split("@,"); 
                	if(drugList.length>0)
                	{  
                		document.getElementById("drugName").options.length=0;
                		document.getElementById("drugName").options[0]=new Option("---select---","-1");
                		for(var i = 0; i<drugList.length;i++)
               		 	{	
                     		var arr=drugList[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null)
                     		{
                         	var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         	var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                         	document.getElementById("drugName").options[i+1] =new Option(val1,val2);
                     		}
                		}
            		}
            	}
        	}
        }
    }
    /*if(chronicId!=null)
	{
	url = "./chronicAction.do?actionFlag=getChemSubList&callType=Ajax&cSubGrpCode="+drugSubTypeCode;
	}
else
	{*/
	//url = "./patientDetails.do?actionFlag=getDrugList&callType=Ajax&lStrDrugSubTypeId="+drugSubTypeCode;
    url = "./patientDetails.do?actionFlag=getChemSubList&callType=Ajax&cSubGrpCode="+drugSubTypeCode;
	
/*	}*/
    xmlhttp.open("Post",url,false);
	xmlhttp.send(null);
	}
}



function getDrugDetails()
{
	document.getElementById("asriDrugCode").value="";
	if(document.getElementById("drugName").value=="-1")
	{
	return false;
	}
else
	{document.getElementById("asriDrugCode").value=document.getElementById("drugName").value;
	}
}

function getRouteTypeList()
{
	
	
	//document.forms[0].routeType.options.length=1;
	document.getElementById("asriDrugCode").value="";
	document.getElementById("dosage").value="-1";
	document.getElementById("medicatPeriod").value="";
	/*document.getElementById("batchNo").value="";
	document.getElementById("expiryDt").value="";*/
	
	if(document.getElementById("drugName").value=="-1")
	{
		
		
	return false;
	}
else
	{
	var actcode=document.getElementById("drugName").value;
	document.getElementById("asriDrugCode").value=actcode;	
	
	
	/*var xmlhttp;
    var url;
    

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
    	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else{
            	if(resultArray!=null)
            	{
            		if(resultArray.replace("*","")>0)
            		{
            			jqueryAlertMsg("Therapy Category Validation","Therapy "+ opCatName+" is not allowed for this Employee/Pensioner to register for ChronicOP");
            		}
            		else
            		{
            			resultArray = resultArray.replace("[","");
                		resultArray = resultArray.replace("@]",""); 
                		var routeTypeList = resultArray.split("@,"); 
               	 		if(routeTypeList.length>0)
                		{  
                			document.forms[0].routeType.options.length=0;
                    		document.forms[0].routeType.options[0]=new Option("---select---","-1");
                			for(var i = 0; i<routeTypeList.length;i++)
               			 	{	
                           		var arr=routeTypeList[i].split("~");
	                   		 	if(arr[1]!=null && arr[0]!=null)
                     			{
                         	 		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                        	 		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       		 		document.forms[0].routeType.options[i+1] =new Option(val1,val2);
                     			}
                			}
           				}
            		}
            	}
        	}
        }
    }
    
	url = "./chronicAction.do?actionFlag=getRouteTypeList&callType=Ajax&actCode="+actcode;
    	
  
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);*/
	}
}


function getRouteList(){
	
	if(document.getElementById("routeType").value=="-1")
		{
		return false;
		}
	else
		{
	var routeTypeCode=document.getElementById("routeType").value;
	var xmlhttp;
    var url;

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
    	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else{
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("@]*","");            
                	var routeList = resultArray.split("@,"); 
                	if(routeList.length>0)
                	{  
                		document.forms[0].route.options.length=0;
                        document.forms[0].route.options[0]=new Option("---select---","-1");
                		for(var i = 0; i<routeList.length;i++)
               		 	{	
                     		var arr=routeList[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null)
                     		{
                         	var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         	var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       	 	document.forms[0].route.options[i+1] =new Option(val1,val2);
                     		}
                		}
            		}
            	}
        	}
        }
    }
    url = "./patientDetails.do?actionFlag=getRouteList&callType=Ajax&routeTypeCode="+routeTypeCode;
	xmlhttp.open("Post",url,false);
	xmlhttp.send(null);
	}
}

function getStrengthList(){
	
	if(document.getElementById("strengthType").value=="-1")
		{
		return false;
		}
	else
		{
	var strengthTypeCode=document.getElementById("strengthType").value;
	var xmlhttp;
    var url;

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
    	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else{
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("@]*","");            
                	var strengthList = resultArray.split("@,"); 
                	if(strengthList.length>0)
                	{  
                		document.forms[0].strength.options.length=0;
                        document.forms[0].strength.options[0]=new Option("---select---","-1");
                		for(var i = 0; i<strengthList.length;i++)
               		 	{	
                     		var arr=strengthList[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null)
                     		{
                         	var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         	var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       	 	document.forms[0].strength.options[i+1] =new Option(val1,val2);
                     		}
                		}
            		}
            	}
        	}
        }
    }
    url = "./patientDetails.do?actionFlag=getStrengthList&callType=Ajax&strengthTypeCode="+strengthTypeCode;
	xmlhttp.open("Post",url,false);
	xmlhttp.send(null);
	}
}


function getOpPackageList()
{
	document.getElementById("opCatCode").value="";
	document.forms[0].opPkgName.options.length=1;
	getOpIcdList();
	if(document.getElementById("opCatName").value=="-1")
		{
		return false;
		}
	else
		{
	var opCatCode=document.getElementById("opCatName").value;
	var opCatName=document.getElementById("opCatName").options[document.getElementById("opCatName").selectedIndex].text;
	document.getElementById("opCatCode").value=opCatCode;
	var xmlhttp;
    var url;
    var chronicId=document.getElementById("patientNo").value;
   

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
    	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else{
            	if(resultArray!=null)
            	{
            		if(resultArray.replace("*","")>0)
            		{
            			alert("Therapy "+ opCatName+" is not allowed for this Employee/Pensioner to register for ChronicOP");
            		}
            		else
            		{
            			resultArray = resultArray.replace("[","");
                		resultArray = resultArray.replace("@]",""); 
                		var opPkgList = resultArray.split("@,"); 
               	 		if(opPkgList.length>0)
                		{  
                			document.forms[0].opPkgName.options.length=0;
                    		document.forms[0].opPkgName.options[0]=new Option("---select---","-1");
                			for(var i = 0; i<opPkgList.length;i++)
               			 	{	
                           		var arr=opPkgList[i].split("~");
	                   		 	if(arr[1]!=null && arr[0]!=null)
                     			{
                         	 		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                        	 		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       		 		document.forms[0].opPkgName.options[i+1] =new Option(val1,val2);
                     			}
                			}
           				}
            		}
            	}
        	}
        }
    }
   /* if(chronicId!=null)
    	{
    	url = "./chronicAction.do?actionFlag=getChronicPkgList&callType=Ajax&lStrOpCatCode="+opCatCode+"&lStrCardNo="+document.getElementById("cardNo").value;
    	}
    else
    	{*/
	url = "./patientDetails.do?actionFlag=getOpPkgList&callType=Ajax&lStrOpCatCode="+opCatCode+"&lStrCardNo="+document.getElementById("cardNo").value;
    	}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	/*}*/
}
$(document).ready(function (){
	$('#addDrug').click(function (e){
		e.preventDefault();
	});
});
function addDrugs()
{
	var otherDrugs=false;	
	if(document.getElementById("drugOthers"))
    var otherDrugs=document.getElementById("drugOthers").checked;
    var drugId='';
    
if(!otherDrugs)

{
	
if(document.getElementById("drugTypeCode").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("drugTypeCode"));
	alert("Please select Main Group Name");
	return false;
	}
if(document.getElementById("drugSubTypeName").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("drugSubTypeName"));
	alert("Please select Therapeutic Main Group Name");
	return false;
	}
if(document.getElementById("pSubGrpName").value==-1)
{
var fr=partial(focusBox,document.getElementById("pSubGrpName"));
alert("Please select Pharmacological SubGroup Name");
return false;
}
if(document.getElementById("cSubGrpName").value==-1)
{
var fr=partial(focusBox,document.getElementById("cSubGrpName"));
alert("Please select Chemical SubGroup Name");
return false;
}
if(document.getElementById("drugName").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("drugName"));
	alert("Please select Chemical Substance Name");
	return false;
	}


}


if(otherDrugs)
	{
	var otherDrugName=document.getElementById("otherDrugName").value;
	if(otherDrugName==null || otherDrugName=="")
		{
		alert("Please Enter Drug Name");
		document.getElementById("otherDrugName").focus();
		return false;
		}
	
	}
/*Valid for other drugs and regular drugs*/

if(document.getElementById("routeType").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("routeType"));
	alert("Please select Route ");
	return false;
	}
if(document.getElementById("route").value==-1)
{
var fr=partial(focusBox,document.getElementById("route"));
alert("Please select Route");
return false;
}
if(document.getElementById("strengthType").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("strengthType"));
	alert("Please select Strength ");
	return false;
	}
if(document.getElementById("strength").value==-1)
{
var fr=partial(focusBox,document.getElementById("strength"));
alert("Please select Strength ");
return false;
}
if(document.getElementById("dosage").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("dosage"));
	alert("Please select Dosage");
	return false;
	}
if(document.getElementById("medicatPeriod").value=="")
	{
	var fr=partial(focusBox,document.getElementById("medicatPeriod"));
	alert("Please enter Medication Period");
	return false;
	}
if(document.getElementById("medicatPeriod").value >90)
{
	alert("Medication Period cannot be greater than 90 Days");
//document.forms[0].medicatPeriod.value='';
return false;
}

if(!otherDrugs)
	{
for(var c=0;c<drugs.length;c++)
	{
	var drugValues=drugs[c].split("~");
	if(drugValues[4]==document.getElementById("drugName").value)
		{
		jqueryErrorMsg('Unique Drug Validation',"Drug Name already added.Please select another Drug Name");
		return false;
		}
	}
	}
	var drugTable=document.getElementById("drugsTable");    
	var newRow=drugTable.insertRow(-1);
	var newcell=newRow.insertCell(0);
	newcell.innerHTML='<td width="5%">'+parseInt(drugCount+1)+'</td>';
	
	if(!otherDrugs)
		{
	newcell=newRow.insertCell(1);
	newcell.innerHTML='<td width="10%">'+document.getElementById("drugTypeCode").options[document.getElementById("drugTypeCode").selectedIndex].text+'</td>';
	newcell=newRow.insertCell(2);
	newcell.innerHTML='<td width="10%">'+document.getElementById("drugSubTypeName").options[document.getElementById("drugSubTypeName").selectedIndex].text+'</td>';
	newcell=newRow.insertCell(3);
	newcell.innerHTML='<td width="10%">'+document.getElementById("pSubGrpName").options[document.getElementById("pSubGrpName").selectedIndex].text+'</td>';
	newcell=newRow.insertCell(4);
	newcell.innerHTML='<td width="10%">'+document.getElementById("cSubGrpName").options[document.getElementById("cSubGrpName").selectedIndex].text+'</td>';
	newcell=newRow.insertCell(5);	
	newcell.innerHTML='<td width="10%">'+document.getElementById("drugName").options[document.getElementById("drugName").selectedIndex].text+'</td>';
		}
	else
		{
		
		var otherIDrugCountTemp=document.getElementById("otherDrugCount").value;
		
		if(otherDrugCount<=otherIDrugCountTemp)
			{
			otherDrugCount=otherIDrugCountTemp;
			}
		otherDrugCount++;
		drugId="OD"+otherDrugCount;
		
		newcell=newRow.insertCell(1);
		newcell.innerHTML='<td width="10%">Others</td>';
		newcell=newRow.insertCell(2);
		newcell.innerHTML='<td width="10%">Others</td>';
		newcell=newRow.insertCell(3);
		newcell.innerHTML='<td width="10%">Others</td>';
		newcell=newRow.insertCell(4);
		newcell.innerHTML='<td width="10%">Others</td>';
		newcell=newRow.insertCell(5);	
		newcell.innerHTML='<td width="10%">'+document.getElementById("otherDrugName").value+'</td>';
		}
	
	newcell=newRow.insertCell(6);
	newcell.innerHTML='<td width="5%">'+document.getElementById("routeType").options[document.getElementById("routeType").selectedIndex].text+'</td>';
	newcell=newRow.insertCell(7);
	newcell.innerHTML='<td width="10%">'+document.getElementById("route").options[document.getElementById("route").selectedIndex].text+'</td>';
	newcell=newRow.insertCell(8);
	newcell.innerHTML='<td width="5%">'+document.getElementById("strengthType").options[document.getElementById("strengthType").selectedIndex].text+'</td>';
	newcell=newRow.insertCell(9);
	newcell.innerHTML='<td width="10%">'+document.getElementById("strength").options[document.getElementById("strength").selectedIndex].text+'</td>';
    newcell=newRow.insertCell(10);
	newcell.innerHTML='<td width="5%">'+document.getElementById("dosage").value+'</td>';
	newcell=newRow.insertCell(11);
	newcell.innerHTML='<td width="5%">'+document.getElementById("medicatPeriod").value+'</td>';
	if(!otherDrugs)
		{
	newcell=newRow.insertCell(12);
	newcell.innerHTML='<td width="5%"><input class="but" type="button" value="Delete" name='+document.getElementById("drugName").value+' id='+document.getElementById("drugName").value+' /></td>';
		}
	else
		{
	newcell=newRow.insertCell(12);
	newcell.innerHTML='<td width="5%"><input class="but" type="button" value="Delete" name='+drugId+' id='+drugId+' /></td>';
		}
	var deleteButName='';
	if(otherDrugs)
	deleteButName=drugId;
	else
    deleteButName=document.getElementById("drugName").value;
	
	 document.getElementById(deleteButName).onclick = function(){
		 confirmRemoveRow(this,"drug");
		 }; 
	var drug="";
	if(!otherDrugs)
		{
	drug=document.getElementById("drugTypeCode").value+"~"+document.getElementById("drugSubTypeName").value+"~"+document.getElementById("pSubGrpName").value+"~"+
	         document.getElementById("cSubGrpName").value+"~"+document.getElementById("drugName").value+"~"+document.getElementById("routeType").value+"~"+
	         document.getElementById("route").value+"~"+document.getElementById("strengthType").value+"~"+document.getElementById("strength").value+"~"+document.getElementById("dosage").value+"~"+document.getElementById("medicatPeriod").value;
		}
	else
		{
	drug=document.getElementById("otherDrugName").value+"~"+drugId+"~"+document.getElementById("routeType").value+"~"+
    document.getElementById("route").value+"~"+document.getElementById("strengthType").value+"~"+document.getElementById("strength").value+"~"+document.getElementById("dosage").value+"~"+document.getElementById("medicatPeriod").value;
		}
	
    drugs[drugCount]=drug+"@";
    document.getElementById("drugs").value=drugs;
	drugCount++;
	if(drugCount>0)
		{
		document.getElementById("drugsTable").style.display="";
		}
	document.getElementById("drugTypeCode").value="-1";
	document.getElementById("routeType").value="-1";
	document.getElementById("route").value="-1";
	document.getElementById("strengthType").value="-1";
	document.getElementById("strength").value="-1";
	document.getElementById("dosage").value="-1";
	document.getElementById("medicatPeriod").value="";
	document.getElementById("otherDrugName").value="";
	if(document.getElementById("drugNameAuto"))
		
	$("#drugNameAuto").select2('val','');
	//getDrugSubTypeList();
	//parent.fn_resizePage();
}



function getDrugSubTypeList()
{
	var chronicId=document.getElementById("patientNo").value;
	document.getElementById("drugCode").value="";
	document.forms[0].drugSubTypeName.options.length=1;
	getPharSubGrpLst();
	if(document.getElementById("drugTypeCode").value=="-1")
		{
		return false;
		}
	else
		{
	var drugTypeCode=document.getElementById("drugTypeCode").value;
	document.getElementById("drugCode").value=drugTypeCode;
	var xmlhttp;
    var url;

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
    	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else{
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("@]*","");            
                	var drugSubList = resultArray.split("@,"); 
                	if(drugSubList.length>0)
                	{  
                		document.forms[0].drugSubTypeName.options.length=0;
                        document.forms[0].drugSubTypeName.options[0]=new Option("---select---","-1");
                		for(var i = 0; i<drugSubList.length;i++)
               		 	{	
                  	       var arr=drugSubList[i].split("~");
	                       if(arr[1]!=null && arr[0]!=null)
                     		{
                         		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       	 		document.forms[0].drugSubTypeName.options[i+1] =new Option(val1,val2);
                     		}
                		}
            		}
            	}
        	}
        }
    }
    	/*if(chronicId!=null)
    		{
    		url = "./chronicAction.do?actionFlag=getOpDrugSubList&callType=Ajax&lStrDrugTypeId="+drugTypeCode;
    		}
    	else
    		{*/
	//url = "./patientDetails.do?actionFlag=getDrugSubList&callType=Ajax&lStrDrugTypeId="+drugTypeCode;
	url = "./patientDetails.do?actionFlag=getOpDrugSubList&callType=Ajax&lStrDrugTypeId="+drugTypeCode;
		/*}*/
	xmlhttp.open("Post",url,false);
	xmlhttp.send(null);  
	}
}



function validateNumber(arg1,input)
{
	var a=input.value;
	var fr = partial(focusBox,input);
	var regDigit=/^\d+$/ ;
	if(a.search(regDigit)==-1)
		{
		input.value="";
		jqueryErrorMsg('Number Validation','Only numbers are allowed for '+arg1,fr);
		return false;
		}
	if(a.charAt(0)=="0")
	{
	input.value="";
		jqueryErrorMsg('Number Validation',arg1+ ' should not start with 0',fr);
	return false;
	}
}
function fn_getDoctorsDetails(){
 	document.getElementById("docNameList").style.display='';
 	document.getElementById("docNametext").style.display='none';
 	document.getElementById("doctorData").style.display='none';
 	document.getElementById('doctorDataDiv').style.display='none';
 	if(document.forms[0].doctorName.value=="-1")
 		{
 		return false;
 		}
 	else
 		{
		var xmlhttp;
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
  	 		jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
   	 	}   
		var doctorType=document.forms[0].diagnosedBy.value;
		var hospId = document.getElementById("hospitalId").value;
		var selVal=document.forms[0].doctorName.selectedIndex;
		var doctorId=document.forms[0].doctorName.options[selVal].value;
		//document.forms[0].doctorId.value=doctorId;       
		var url;
		var dd3;
		if(doctorId == '0')
		{
		document.getElementById("docNameList").style.display='none';
        document.getElementById("docNametext").style.display='';
        document.getElementById("doctorData").style.display='';
        document.getElementById('doctorDataDiv').style.display='none';
        document.getElementById("docRegNo").value="";
        document.getElementById("docQual").value="";
        document.getElementById("docMobileNo").value="";
     	}
		else
		{
		var val1='';val2='';val3='';
    	url = './patientDetails.do?actionFlag=getDoctorsDetails&callType=Ajax&hospId='+hospId+'&doctorType='+doctorType+'&doctId='+doctorId;	
   		xmlhttp.onreadystatechange=function()
   		{
       		if(xmlhttp.readyState==4)
       		{
			var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*")
            {
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
           	else
           	{
           	resultArray = resultArray.replace("*","");
            if(resultArray.length>0)
           	{   
            	var arr=resultArray.split("~");                     
                val1=arr[0];
                val2=arr[2];
                val3=arr[1];
            } 
            var details="";
			document.getElementById('doctorDataDiv').style.display='';
			dd3= document.getElementById('doctorDataDiv');
			document.forms[0].docRegNo.value=val1;
			document.forms[0].docQual.value=val2;
			document.forms[0].docMobileNo.value=val3; 
			details="<table width=100% border=0 align=left cellSpacing=0 cellPadding=0 >";
            details=details+"<tr><td width='30%'><b>Registration No:</b>"+val1+"</td><td width='30%'><b>Qualification:</b>"+val2+"</td><td width='30%'><b>Contact No:</b>"+val3+"</td><td>&nbsp;</td></tr>";		
            details=details+"</table>";
			dd3.innerHTML=details;
       		}
       		}			
    	}
	}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
 	}
	//parent.fn_resizePage();
}
function getPharSubGrpLst(){
	var chronicId=document.getElementById("patientNo").value;
	document.getElementById("drugSubTypeCode").value="";
	document.forms[0].pSubGrpName.options.length=1;
	getChemicalSubGrp();
	if(document.getElementById("drugSubTypeName").value=="-1")
		{
		return false;
		}
	else
		{
	var drugSubTypeCode=document.getElementById("drugSubTypeName").value;
	document.getElementById("drugSubTypeCode").value=drugSubTypeCode;
	var xmlhttp;
    var url;

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
    	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else{
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("@]*","");            
                	var drugList = resultArray.split("@,"); 
                	if(drugList.length>0)
                	{  
                		document.forms[0].pSubGrpName.options.length=0;
                        document.forms[0].pSubGrpName.options[0]=new Option("---select---","-1");
                		for(var i = 0; i<drugList.length;i++)
               		 	{	
                     		var arr=drugList[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null)
                     		{
                         	var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         	var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       	 	document.forms[0].pSubGrpName.options[i+1] =new Option(val1,val2);
                     		}
                		}
            		}
            	}
        	}
        }
    }
   /* if(chronicId!=null)
	{
	url = "./chronicAction.do?actionFlag=getPharDrugList&callType=Ajax&lStrDrugSubTypeId="+drugSubTypeCode;
	}
else
	{*/
	//url = "./patientDetails.do?actionFlag=getDrugList&callType=Ajax&lStrDrugSubTypeId="+drugSubTypeCode;
	url = "./patientDetails.do?actionFlag=getPharDrugList&callType=Ajax&lStrDrugSubTypeId="+drugSubTypeCode;
	/*}*/
	xmlhttp.open("Post",url,false);
	xmlhttp.send(null);
	}
}


function getChemicalSubGrp(){
	var chronicId=document.getElementById("patientNo").value;
	document.getElementById("pSubGrpCode").value="";
	document.forms[0].cSubGrpName.options.length=1;
	getDrugNameList();
	if(document.getElementById("pSubGrpName").value=="-1")
		{
		return false;
		}
	else
		{
	var pSubGrpCode=document.getElementById("pSubGrpName").value;
	document.getElementById("pSubGrpCode").value=pSubGrpCode;
	var xmlhttp;
    var url;

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
    	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else{
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("@]*","");            
                	var drugList = resultArray.split("@,"); 
                	if(drugList.length>0)
                	{  
                		document.forms[0].cSubGrpName.options.length=0;
                        document.forms[0].cSubGrpName.options[0]=new Option("---select---","-1");
                		for(var i = 0; i<drugList.length;i++)
               		 	{	
                     		var arr=drugList[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null)
                     		{
                         	var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         	var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       	 	document.forms[0].cSubGrpName.options[i+1] =new Option(val1,val2);
                     		}
                		}
            		}
            	}
        	}
        }
    }
    /*if(chronicId!=null)
	{
	url = "./chronicAction.do?actionFlag=getOpChemSubList&callType=Ajax&pharSubCode="+pSubGrpCode;
	}
else
	{*/
	url = "./patientDetails.do?actionFlag=getOpChemSubList&callType=Ajax&pharSubCode="+pSubGrpCode;
	/*}*/
	xmlhttp.open("Post",url,false);
	xmlhttp.send(null);
	}
}

function getDrugNameList()
{
	var chronicId=document.getElementById("patientNo").value;
	document.getElementById("cSubGrpCode").value="";
	document.getElementById("drugName").options.length=1;
	getDrugDetails();
	if(document.getElementById("cSubGrpName").value=="-1")
		{
		return false;
		}
	else
		{
	var drugSubTypeCode=document.getElementById("cSubGrpName").value;
	document.getElementById("cSubGrpCode").value=drugSubTypeCode;
	var xmlhttp;
    var url;

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
    	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else{
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("@]*","");            
                	var drugList = resultArray.split("@,"); 
                	if(drugList.length>0)
                	{  
                		document.getElementById("drugName").options.length=0;
                		document.getElementById("drugName").options[0]=new Option("---select---","-1");
                		for(var i = 0; i<drugList.length;i++)
               		 	{	
                     		var arr=drugList[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null)
                     		{
                         	var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         	var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                         	document.getElementById("drugName").options[i+1] =new Option(val1,val2);
                     		}
                		}
            		}
            	}
        	}
        }
    }
    /*if(chronicId!=null)
	{
	url = "./chronicAction.do?actionFlag=getChemSubList&callType=Ajax&cSubGrpCode="+drugSubTypeCode;
	}
else
	{*/
	//url = "./patientDetails.do?actionFlag=getDrugList&callType=Ajax&lStrDrugSubTypeId="+drugSubTypeCode;
    url = "./patientDetails.do?actionFlag=getChemSubList&callType=Ajax&cSubGrpCode="+drugSubTypeCode;
	
/*	}*/
    xmlhttp.open("Post",url,false);
	xmlhttp.send(null);
	}
}

function fn_openAtachment(filepath,fileName)
{  
	var url = "./patientDetails.do?actionFlag=viewAttachment&filePath="+filepath+"&fileName="+fileName;
    window.open(url,"",'width=500,height=250,resizable=yes,top=100,left=110,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
}

function getSubLevelDetailsNims(input)
{

	if(input.checked)
	{
		var personalHabits='';
		var KnownAllg='';
		if(input.name=="Known Allergies")
			{
			if(input.value=="PR5.1")
			{
				KnownAllg=KnownAllg+'<table width="100%" border="1"><tr><td>'+
				'Allergic to Medicine:<input type="radio" name="AllMed" id="AllMed" value="AllMedYes" onclick="displayTextBox(this)" title="Yes"/>Yes'+
				'<input type="radio" name="AllMed" id="AllMed" value="AllMedNo" onclick="displayTextBox(this)" title="No"/>No'+
				'<div id="AllMedDiv" style="display:none">Specify:<input type="text" name="AllMedRemrk" id="AllMedRemrk" maxlength="300" title="Remark" style="width:7em" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpecify(this)"/></div></td></tr>'+
				'<tr><td>Allergic to Substance other than medicine:<br><input type="radio" name="AllSub" id="AllSub" value="AllSubYes" onclick="displayTextBox(this)" title="Yes"/>Yes'+
				'<input type="radio" name="AllSub" id="AllSub" value="AllSubNo" onclick="displayTextBox(this)" title="No"/>No'+
				'<div id="AllSubDiv" style="display:none">Specify:<input type="text" name="AllSubRemrk" id="AllSubRemrk" maxlength="300" title="Remark" style="width:7em" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpecify(this)"/></div></td></tr></table>';
                
				document.getElementById("Known AllergiesTd").innerHTML=KnownAllg;
			}else
				{  
			document.getElementById("Known AllergiesTd").innerHTML="";
				}
			}
		if(input.name=="Habits/Addictions")
		 {
			if(input.value=="PR6.1")
			{
		  personalHabits=personalHabits+'<table width="100%" border="1"><tr><td>'+
	      'Alcohol:<input type="radio" name="alcohol" id="alcohol" value="Regular" title="Regular"/>Yes'+
	      '<input type="radio" name="alcohol" id="alcohol" value="Occasional" title="Occasional"/>No'+
	      
	     	'<tr><td>Tobacco:<input type="radio" name="tobacco" id="tobacco" value="Snuff" onclick="displayPackYears(this)" title="Snuff"/>Snuff'+
	      '<input type="radio" name="tobacco" id="tobacco" value="Chewable" onclick="displayPackYears(this)" title="Chewable"/>Chewable'+
	      '<input type="radio" name="tobacco" id="tobacco" value="Smoking" onclick="displayPackYears(this)" title="Smoking"/>Smoking'+
	      '<div id="smokingTd" style="display:none">'+
	     'Pack :<input type="text" name="packNo" id="packNo" maxlength="2" title="Smoking Pack No" style="width:7em" onchange="validateNumber(\'Smoking Pack No\',this)"/>  (per day)<br>'+
	      'Years:<input type="text" name="smokeYears" id="smokeYears" maxlength="2" title="Smoking Years" style="width:7em" onchange="validateNumber(\'Smoking Years\',this)"/>  (since years)</div></td></tr>'+
	      '<tr><td>Drug Abuse:<input type="radio" name="drugUse" id="drugUse" value="Yes" title="Yes"/>Yes'+
	      '<input type="radio" name="drugUse" id="drugUse" value="No" title="No"/>No</td></tr>'+
	      '<tr><td>Ghutka:<input type="radio" name="Ghutka" id="Ghutka" value="Yes" title="Yes"/>Yes'+
	      '<input type="radio" name="Ghutka" id="Ghutka" value="No" title="No"/>No</td></tr></table>';
		document.getElementById("Habits/AddictionsTd").innerHTML=personalHabits;
			}
		else
			{
		document.getElementById("Habits/AddictionsTd").innerHTML="";
			}
		}
	}
 else
	{
	document.getElementById("Habits/AddictionsTd").innerHTML="";
	} 
	// parent.fn_resizePage();

}

function enableDentalIPOP(){
	var schemeId=document.getElementById("scheme").value;
	var patientScheme=document.getElementById("patientScheme").value;
	var hospType=document.getElementById("hosptype").value;
	var treatType=document.forms[0].patientType.value;
	var hospId = document.getElementById("hospitalId").value;
	var caseId=document.getElementById("caseId").value;
	var hospGovu = document.getElementById("hospGovu").value;
     catCount=0;
	var catTable = document.getElementById("categoryTable");
	for(var count = catTable.rows.length-1 ; count>0; count--)
	{
		catTable.deleteRow(count);
	}
	document.getElementById("categoryTable").style.display="none";
	spec=new Array();
	addedProcs=new Array();
	document.getElementById("speciality").value=spec;
	
	otherDocDetails=new Array();
	document.getElementById("otherDocDetailsList").value=otherDocDetails;
	
	procList=new Array();
	document.getElementById("procSelectedTd").style.display="none";
	if(document.getElementById("treatingDocLabel").style.display=='')
	{
	document.getElementById("treatingDocLabel").style.display='none';
	document.getElementById("treatingDocRemarks").innerHTML='';
	}
	var existDrugsTable = document.getElementById("existDrugs");
	for(var count = existDrugsTable.rows.length - 1 ; count>0; count--)
		{
		existDrugsTable.deleteRow(count);
		}
	document.getElementById("existDrugs").style.display="none";
	document.getElementById("existDrugsHead").style.display="none";
	

if(document.forms[0].patientType[1].checked)
{
	document.getElementById("admisnTypeSel").style.display="";
	document.getElementById("admisnType").style.display="";
//	document.getElementById("postOPInstr").innerHTML="*";
	document.getElementById("medicGiven").innerHTML="";
	document.getElementById("ipDop").innerHTML='IP Number';
	document.getElementById("admissionDetailsDiv").style.display="";
	document.getElementById("medicationDiv1").style.display="";
	document.getElementById("medicationDiv2").style.display="";
	if(schemeId=="CD202" && patientScheme=="CD501" && hospType=="G")
	{
		$('.onlyAp').css('display','');
		document.getElementById("onlyIp1").style.display="";
		document.getElementById("onlyIp2").style.display="";
		document.getElementById("docNameList").style.display="";
		document.getElementById("docName").style.display="";
		document.getElementById("doctorName").style.display="";
		document.getElementById("ipNote1").style.display="";
		document.getElementById("opNote").style.display="none";
		document.getElementById("Submit").style.display="";
		document.getElementById("Save").style.display="";
		document.getElementById("Reset").style.display="";
		document.getElementById("unitNameHead").style.display="none";
		document.getElementById("unitHODNameHead").style.display="none";
		document.getElementById("addConsult").style.display="none";
		document.getElementById("unitName").style.display="none";
		document.getElementById("unitHodName").style.display="none";
		if(document.getElementById("consultationDataNew"))
		document.getElementById("consultationDataNew").style.display="none";
		if(document.getElementById("consultationDataOld"))
		document.getElementById("consultationDataOld").style.display="none";
		if(document.getElementById("empPastHistory"))
		document.getElementById("empPastHistory").style.display="none";
		if(document.getElementById("addAttach"))
		document.getElementById("addAttach").style.display="";
	}
	document.getElementById("cancel").style.display="none";
	document.getElementById("asriCatName").value=-1;
	document.getElementById("asriCatCode").value="";
	document.getElementById("ICDCatName").options.length = 1;
	document.getElementById("ICDCatCode").value="";
	document.getElementById("ICDProcName").options.length = 1;
	document.getElementById("ICDProcCode").value="";
	document.getElementById("procUnits").value=-1;
	document.getElementById("unitsTd").style.display='none';
	document.getElementById("unitsLabelTd").style.display='none';
	document.getElementById("docSpecReg").options.length = 1;
	document.getElementById("ipDoctorData").style.display='none';
	document.getElementById("ipOtherDocName").value="";
	document.getElementById("ipDocRegNo").value="";
	document.getElementById("ipDocQual").value="";
	document.getElementById("ipDocMobileNo").value="";
	document.getElementById("investigations").options.length = 0;
	document.getElementById("ipNo").value="";
	document.getElementById("admissionType").value="-1";
	document.getElementById("ipDate").value="";
	if(hospGovu!=null && hospGovu!="G")	
	{
		document.getElementById("ipDiagnosedBy").value="-1";
		document.getElementById("ipDoctorName").options.length= 1;
	}
	document.getElementById("remarks").value="";	
	document.getElementById("IPHead2").style.display="";
	document.getElementById("OPHead2").style.display="none";
	document.getElementById("prescriptionData").style.display="none";
	document.getElementById("OPDoctor").style.display="none";
	document.getElementById("diagnosisData").style.display="";
	
	if(document.forms[0].legalCase[0].checked==true)
	{
		document.getElementById("legalCaseNoTd").style.display='none';
		document.getElementById("legalCaseNo").value='';
		document.getElementById("policeStatNameTd").style.display='none';
		document.getElementById("policeStatName").value='';
		document.forms[0].legalCase[0].checked=false;
	}
	else
		document.forms[0].legalCase[1].checked=false;
}
else if(document.forms[0].patientType[2].checked)
{	
	document.getElementById("admisnTypeSel").style.display="none";
	document.getElementById("admisnType").style.display="none";
//	document.getElementById("postOPInstr").innerHTML="";
	document.getElementById("medicGiven").innerHTML="*";
	document.getElementById("ipDop").innerHTML="DOP Number";
	document.getElementById("admissionDetailsDiv").style.display="none";
	document.getElementById("medicationDiv1").style.display="";
	document.getElementById("medicationDiv2").style.display="";
	document.getElementById("diagnosisData").style.display="";
	if(schemeId=="CD202" && patientScheme=="CD501" && hospType=="G")
	{
		$('.onlyAp').css('display','');
		document.getElementById("onlyIp1").style.display="";
		document.getElementById("onlyIp2").style.display="";
		document.getElementById("docNameList").style.display="";
		document.getElementById("docName").style.display="";
		document.getElementById("doctorName").style.display="";
		document.getElementById("ipNote1").style.display="";
		document.getElementById("opNote").style.display="none";
		document.getElementById("Submit").style.display="";
		document.getElementById("Save").style.display="";
		document.getElementById("Reset").style.display="";
		document.getElementById("unitNameHead").style.display="none";
		document.getElementById("unitHODNameHead").style.display="none";
		document.getElementById("addConsult").style.display="none";
		document.getElementById("unitName").style.display="none";
		document.getElementById("unitHodName").style.display="none";
		if(document.getElementById("consultationDataNew"))
		document.getElementById("consultationDataNew").style.display="none";
		if(document.getElementById("consultationDataOld"))
		document.getElementById("consultationDataOld").style.display="none";
		if(document.getElementById("empPastHistory"))
		document.getElementById("empPastHistory").style.display="none";
		if(document.getElementById("addAttach"))
		document.getElementById("addAttach").style.display="";
	}
	document.getElementById("cancel").style.display="none";
	document.getElementById("asriCatName").value=-1;
	document.getElementById("asriCatCode").value="";
	document.getElementById("ICDCatName").options.length = 1;
	document.getElementById("ICDCatCode").value="";
	document.getElementById("ICDProcName").options.length = 1;
	document.getElementById("ICDProcCode").value="";
	document.getElementById("procUnits").value=-1;
	document.getElementById("unitsTd").style.display='none';
	document.getElementById("unitsLabelTd").style.display='none';
	document.getElementById("docSpecReg").options.length = 1;
	document.getElementById("ipDoctorData").style.display='none';
	document.getElementById("ipOtherDocName").value="";
	document.getElementById("ipDocRegNo").value="";
	document.getElementById("ipDocQual").value="";
	document.getElementById("ipDocMobileNo").value="";
	document.getElementById("investigations").options.length = 0;
	document.getElementById("ipNo").value="";
	document.getElementById("admissionType").value="-1";
	document.getElementById("ipDate").value="";
	if(hospGovu!=null && hospGovu!="G")	
	{
	document.getElementById("ipDiagnosedBy").value="-1";
	document.getElementById("ipDoctorName").options.length= 1;
	}
	document.getElementById("remarks").value="";	
	
	document.getElementById("IPHead2").style.display="";
	document.getElementById("OPHead2").style.display="none";
	document.getElementById("prescriptionData").style.display="none";
	document.getElementById("OPDoctor").style.display="none";
	document.getElementById("diagnosisData").style.display="";
	
	if(document.forms[0].legalCase[0].checked==true)
	{
		document.getElementById("legalCaseNoTd").style.display='none';
		document.getElementById("legalCaseNo").value='';
		document.getElementById("policeStatNameTd").style.display='none';
		document.getElementById("policeStatName").value='';
		document.forms[0].legalCase[0].checked=false;
	}
	else
		document.forms[0].legalCase[1].checked=false;
}
else if(document.forms[0].patientType[0].checked)
{
		document.getElementById("admissionDetailsDiv").style.display="none";
		document.getElementById("medicationDiv1").style.display="none";
		document.getElementById("medicationDiv2").style.display="none";
		document.getElementById("prescriptionData").style.display="";
		document.getElementById("diagnosisData").style.display="";
		if((schemeId=="CD202" && patientScheme=="CD501" && hospType=="G")|| hospGovu=="G")	
		{
			if(document.getElementById("onlyIp1"))
			document.getElementById("onlyIp1").style.display="none";
			if(document.getElementById("onlyIp2"))
			document.getElementById("onlyIp2").style.display="none";
			document.getElementById("ipNote1").style.display="none";
			document.getElementById("opNote").style.display="";
			document.getElementById("docName").style.display="none";
			document.getElementById("docNameList").style.display="none";
			document.getElementById("doctorName").style.display="none";
			document.getElementById("unitNameHead").style.display="";
			document.getElementById("unitHODNameHead").style.display="";
			document.getElementById("unitName").style.display="";
			document.getElementById("addConsult").style.display="";
			document.getElementById("unitHodName").style.display="";
			if(document.getElementById("consultationDataOld"))
			document.getElementById("consultationDataOld").style.display="";
			if(document.getElementById("empPastHistory"))
			document.getElementById("empPastHistory").style.display="";
			document.getElementById("addAttach").style.display="";
			if(consultDataList.length>1)
			{
				document.getElementById("consultationDataNew").style.display="";
			}
			$('.onlyAp').css('display','none');
			var opActiveMsg=document.getElementById("opActiveMsg").value;
			if(opActiveMsg!=null && opActiveMsg!='')
			{
				document.getElementById("Submit").disabled=true;
				document.getElementById("Submit").className='butdisable';
				document.getElementById("Submit").style.display="none";
				document.getElementById("Save").style.display="none";
				document.getElementById("Reset").style.display="none";	
			}
		}
		document.getElementById("drugTypeCode").value=-1;
		document.getElementById("drugCode").value="";
		document.getElementById("drugSubTypeName").options.length=1;
		document.getElementById("drugSubTypeCode").value="";
		document.getElementById("pSubGrpName").options.length=1;
		document.getElementById("pSubGrpCode").value="";
		document.getElementById("cSubGrpName").options.length=1;
		document.getElementById("cSubGrpCode").value="";
		document.getElementById("drugName").options.length = 1;
		document.getElementById("asriDrugCode").value="";
		document.getElementById("diagnosedBy").value="-1";
		document.getElementById("dosage").value="-1";
		document.getElementById("medicatPeriod").value="";
		drugCount=0;
		
		var drugTable = document.getElementById("drugsTable");
		for(var count = drugTable.rows.length-1 ; count>0; count--)
		{
			drugTable.deleteRow(count);
		}
		document.getElementById("drugsTable").style.display="none";
		drugs=new Array();
		drugsTemp=new Array();
		document.getElementById("opNo").value="";
		document.getElementById("opRemarks").value="";
		document.forms[0].doctorName.options.length=1;
		document.getElementById("docNameList").style.display="";
		document.getElementById("docNametext").style.display="none";
		document.getElementById("otherDocName").value="";
	    document.getElementById("doctorDataDiv").style.display="none";
		document.getElementById("doctorData").style.display="none";
		document.getElementById("docRegNo").value="";
		document.getElementById("docQual").vlaue="";
		document.getElementById("docMobileNo").value="";
		document.getElementById("IPHead2").style.display="none";
		document.getElementById("OPHead2").style.display="";
		document.getElementById("OPDoctor").style.display="";
		document.getElementById("cancel").style.display="none";
}

}
function generateDTRSPrint(caseId,hospId)
{
	if(caseId!=null && caseId!='')
		{
	var url="./patientDetails.do?actionFlag=dtrsPrintForm&caseId="+caseId+"&printFormType=DTRS";
	window.open(url, '_blank','toolbar=no,resize=yes,scrollbars=yes,width=900, height=700, top=50,left=50');
		}
	else
		{
		bootbox.alert("Please generate DTRS Form and try again");
		return false;
		}
}
function enableMedicalHistory(yesRno){
	if(yesRno=='Yes'){
		document.getElementById("medicalhistorytable").style.display="";
	}
	else
		document.getElementById("medicalhistorytable").style.display="none";
}
function fn_permDentOthersValidation(oValue){
	if(!oValue.match(/^[0-9]+(,[0-9]+)*$/)){
		alert("Please enter number or comma(,) separated numbers only");
		document.getElementById("otherPermText").value="";
	}		
}
function permDeciddentsel(){
	if(document.getElementById("decidiousdentsel").checked==true){
		document.getElementById("decidiousBlock").style.display="";
	}
	else{
		document.getElementById("decidiousBlock").style.display="none";
	}
	if(document.getElementById("permanentdentsel").checked==true){
		document.getElementById("permanentBlock").style.display="";
	}
	else{
		document.getElementById("permanentBlock").style.display="none";
	}
}
function enableSoftTissueExamin(value){
	if(value=="Yes"){
		document.getElementById("softtissueexaminblock").style.display="";
	}
	else if(value=="NAD"){
		document.getElementById("softtissueexaminblock").style.display="none";
		}
}