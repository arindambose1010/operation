
function resetDentalProc()
	{
		removeSpecialInv();
		//document.forms[0].investigations.options.length=0;
		document.getElementById("procUnits").value='-1';
		document.getElementById("icdProcCode").value='-1';
		document.getElementById("unitsTd").style.display="none";
		document.getElementById("unitsLabelTd").style.display="none";
	}

function getDentalConditions(icdProcCode,procName,schemeId,arg)
	{
		var deleteProc='N';
		if(icdProcCode==null || icdProcCode=='')
			icdProcCode=document.getElementById("icdProcCode").value;
		
		if(schemeId==null || schemeId=='' && document.preauthDetailsForm.schemeId !=null)
			schemeId=document.preauthDetailsForm.schemeId.value; 
	
		var patientId=document.preauthDetailsForm.patientId.value;
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
					alert('Your Browser does not Support XMLHTTP!');
				
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
												alert(alertSpecCond[1]);
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

function checkDentalTGCond()
		{
			var procs=null;
			var specLst=new Array();

			if(surgertIdsarray!=null )
				{
					specLst=surgertIdsarray;
					locSpecLst=surgertIdsarray;
				}
			
			if(specLst!=null && specLst!='' && specLst!=' ')
				{
					for(var i=0;i<specLst.length;i++)
						{
							var categories=categoryIdsarray;
							if(specLst[i]!=null && categories!=null && categories[i]!=null && categories[i]=='S18')
								{
									if(procs==null || procs=='' || procs==' ')
										procs=specLst[i]+"~";
									else
										procs=procs+specLst[i]+"~";
								}
							
						}
					//Checking the Presence of Combinational Procs 
					/*if(comboProcIds!=null && comboProcIds!='' && comboProcIds.length>0)
						{
							var procWiseLst=comboProcIds.split("$");
							for(var j=0;j<procWiseLst.length;j++)//Checking for every added Proc
								{
									var checkCond=0;
									var alertCont=null;
									var indiSpecCombLst=procWiseLst[j].split(",");
									var addedSpecDtls=indiSpecCombLst[0].split("!@#");
									
									var allCombos=indiSpecCombLst[1].split("~");
									for(var k=0;k<allCombos.length;k++)//Checking for every Combo Proc for added Proc
										{
											var splitComboProc=allCombos[k].split("@");
											var comboProcedureId=splitComboProc[0];
											var comboProcedureName=splitComboProc[1];
											if(procs.indexOf(comboProcedureId+"~")==-1)
												{
													checkCond++;
													if(alertCont==null || alertCont=='' || alertCont==' ')
														alertCont=comboProcedureName+"("+comboProcedureId+")";
													else
														alertCont=alertCont+" , "+comboProcedureName+"("+comboProcedureId+")";
												}
										}
									if(checkCond>0)
										{
											var alertMsg="As Procedure "+addedSpecDtls[1]+" is added,Mandatory Combinational Procedures "+alertCont+" should be added.";
											return alertMsg;
											break;
										}
								}
						}*/
					//Checking the Presence of Non Combinational Procs 
					if(nonComboProcIds!=null && nonComboProcIds!='' && nonComboProcIds.length>0)
						{
							var procWiseLst=nonComboProcIds.split("$");
							for(var j=0;j<procWiseLst.length;j++)//Checking for every added Proc
								{
									var checkCond=0;
									var alertCont=null;
									var indiSpecCombLst=procWiseLst[j].split(",");
									var addedSpecDtls=indiSpecCombLst[0].split("!@#");
									
									var allCombos=indiSpecCombLst[1].split("~");
									for(var k=0;k<allCombos.length;k++)//Checking for every Non Combo Proc for added Proc
										{
											var splitComboProc=allCombos[k].split("@");
											var comboProcedureId=splitComboProc[0];
											var comboProcedureName=splitComboProc[1];
											if(procs.indexOf(comboProcedureId+"~")!=-1)
												{
													checkCond++;
													if(alertCont==null || alertCont=='' || alertCont==' ')
														alertCont=comboProcedureName+"("+comboProcedureId+")";
													else
														alertCont=alertCont+" , "+comboProcedureName+"("+comboProcedureId+")";
														//alertCont=alertCont+" , "+comboProcedureName+"("+comboProcedureId+")";
												}
										}
									if(checkCond>0)
										{
											var alertMsg="As Procedure "+addedSpecDtls[1]+" is added,Mandatory Non Combinational Procedures "+alertCont+" should not be added.";
											return alertMsg;
											break;
										}
								}
							
						}
					//Checking the Presence of Non Combinational Procs for Stand Alone Procedures
					if(standaloneProcIds!=null && standaloneProcIds!='' && standaloneProcIds.length>0)
						{
							var procWiseLst=standaloneProcIds.split("$");
							for(var j=0;j<procWiseLst.length;j++)//Checking for every added Proc
								{
									var checkCond=0;
									var alertCont=null;
									var indiSpecCombLst=procWiseLst[j].split(",");
									var addedSpecDtls=indiSpecCombLst[0].split("!@#");
									
									var allCombos=indiSpecCombLst[1].split("~");
									for(var k=0;k<allCombos.length;k++)//Checking for every Non Combo Proc for added Stand Alone Proc
										{
											var splitComboProc=allCombos[k].split("@");
											var comboProcedureId=splitComboProc[0];
											var comboProcedureName=splitComboProc[1];
											if(procs.indexOf(comboProcedureId+"~")!=-1)
												{
													checkCond++;
													if(alertCont==null || alertCont=='' || alertCont==' ')
														alertCont=comboProcedureName+"("+comboProcedureId+")";
													else
														alertCont=alertCont+" , "+comboProcedureName+"("+comboProcedureId+")";
												}
										}
									if(checkCond>0)
										{
											var alertMsg="As Stand Alone Procedure "+addedSpecDtls[1]+" is added,Mandatory Non Combinational Procedures "+alertCont+" should not be added.";
											return alertMsg;
											break;
										}
								}
							
						}
				}
			return "";
	}


function unitsLeftCond(jsonData) 													
	{
		var totalPreLimit=0,lifeTimeUnitsLeft=0;
		var units=document.getElementById("procUnits").value;
		if(units!=null && units!='-1')
			{
				if(jsonData.lifetimeUnitsLimit!=null && jsonData.lifetimeUnitsLimit!='-1' && jsonData.lifetimeUnitsLimit!='' &&
						jsonData.lifetimeUnitsLimit!=' ')
					totalPreLimit=Number(jsonData.lifetimeUnitsLimit);
				if(jsonData.actualUnitsLeft!=null && jsonData.actualUnitsLeft!='' && jsonData.actualUnitsLeft!=' ')
					lifeTimeUnitsLeft=Number(jsonData.actualUnitsLeft);
				
				if(units > lifeTimeUnitsLeft && lifeTimeUnitsLeft!='-1' )
					{
						if(lifeTimeUnitsLeft=='0')
							alert("No Units can be treated as maximum units i.e. "+totalPreLimit+" are already utilized by the Patient for Selected Procedure");
						else
							alert("Maximum Units that be treated for Selected Procedure are "+totalPreLimit+" .A maximum of "+lifeTimeUnitsLeft+" can only be selected as "+(totalPreLimit-lifeTimeUnitsLeft)+" are already utilized by the Patient");
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
		//var comboProcs=jsonData.comboProcCode;
		/*var comboProcNames=jsonData.comboProcNames;
		var comboProcsLst=null,comboProcsNamesLst=null,addComboProcs='N';*/
		
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
		
		if(noncomboProcsLst!=null && noncomboProcsLst.length > 0 && noncomboProcsLst[0]!=null && noncomboProcsLst[0]!='NA')
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
function allDentalConditions(jsonData)
	{
		var singlePreauthUnitsLimit=0;
		if(jsonData.ageMsg != null && jsonData.ageMsg != '' && jsonData.ageMsg != ' ' )
			{
				resetDentalProc();
				alert(jsonData.ageMsg);
				focusBox(document.getElementById("icdProcCode"));
				return false;
			}
		if(jsonData.unitsLimit != null && jsonData.unitsLimit !='' && jsonData.unitsLimit !=' ' )
			{
				singlePreauthUnitsLimit=Number(jsonData.unitsLimit);			
				document.forms[0].procUnits.options.length=0;
				document.forms[0].procUnits.options[0]=new Option("---select---","-1");
				document.getElementById("unitsTd").style.display='';
				document.getElementById("unitsLabelTd").style.display='';
				
				if(singlePreauthUnitsLimit==-1)
					singlePreauthUnitsLimit=32;
				
				for(var i = 1; (i<=singlePreauthUnitsLimit && i<=32);i++)
					{	
						document.forms[0].procUnits.options[i] =new Option(i,i);
					}
			}
		
		if(jsonData.nonComboProcCode!=null && jsonData.nonComboProcCode!='' && jsonData.nonComboProcCode!=' '
				&& jsonData.comboNonProcNames!=null && jsonData.comboNonProcNames!='' && jsonData.comboNonProcNames!=' ' )
			{
				var locSpecLst=new Array();
				
				var specty=null;
				if(document.getElementById('speciality')!=null)
					 specty=document.getElementById('speciality').value;
				else if(surgertIdsarray!=null)
					locSpecLst=surgertIdsarray;
				
				var nonComboLst=jsonData.nonComboProcCode.split("~");
				var nonComboNames=jsonData.comboNonProcNames.split("~");
				
				if(typeof specOld!='undefined' && specOld!=null && specty!=null)
					locSpecLst=specOld.concat(specty.split(","));
				else if((specty=='' || specty==' ' || specty==null) && typeof specOld!='undefined' &&  specOld!=null)
					locSpecLst=specOld;
				else if(specty!=null && specty!='' && specty!=' ')
					locSpecLst=specty.split(",");
				//Checking for each previously Speciality
				for(var i=0;i<locSpecLst.length;i++)
					{
						var listValues=locSpecLst[i].split("~");
						if(listValues!=null && listValues[2]!=null)
							{
								//Checking every Combo Code For individual Speciality
								if(nonComboLst != null && nonComboLst.length > 0 )
									{
										for(var j=0 ; j<nonComboLst.length;j++ )
											{
												if(listValues[2]==nonComboLst[j])
													{
														var procName=$("#ICDProcName option:selected").text();
														var alertCont="For "+procName+" its non Combinational Procedure ";
														alertCont+=nonComboNames[j]+"("+nonComboLst[j]+")"+" has already been added.Hence current Procedure cannot be selected.";
														
														resetDentalProc();
														alert(alertCont);
														focusBox(document.getElementById("ICDProcName"));
														
														return false;
													}
											}
									}
							}
						else
							{
								//Checking every Combo Code For individual Speciality
								if(nonComboLst != null && nonComboLst.length > 0 )
									{
										for(var j=0 ; j<nonComboLst.length;j++ )
											{
												if(locSpecLst[i]==nonComboLst[j])
													{
														var procName=$("#icdProcCode option:selected").text();
														var alertCont="For "+procName+" its non Combinational Procedure ";
														alertCont+=nonComboNames[j]+"("+nonComboLst[j]+")"+" has already been added.Hence current Procedure cannot be selected.";
														
														resetDentalProc();
														alert(alertCont);
														focusBox(document.getElementById("icdProcCode"));
														
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
				if(document.getElementById('speciality')!=null)
					 specty=document.getElementById('speciality').value;
				else if(surgertIdsarray!=null)
					locSpecLst=surgertIdsarray;
				
				var standProcLst=jsonData.standaloneProc.split("~");
				var standProcNames=jsonData.standaloneProcNames.split("~");
				
				/* if(specOld!=null && typeof specOld!='undefined' && specty!=null)
					locSpecLst=specOld.concat(specty.split(","));
				else if((specty=='' || specty==' ' || specty==null) && specOld!=null)
					locSpecLst=specOld;
				else if(specty!=null && specty!='' && specty!=' ')
					locSpecLst=specty.split(","); */
				
				//Checking for each previously Speciality
				for(var i=0;i<locSpecLst.length;i++)
					{
						if(locSpecLst[i]!=null)
							{
								//Checking every Combo Code For individual Speciality
								if(standProcLst != null && standProcLst.length > 0 )
									{
										for(var j=0 ; j<standProcLst.length;j++ )
											{
												if(locSpecLst[i]==standProcLst[j])
													{
														var procName=$("#icdProcCode option:selected").text();
														var alertCont="For "+procName+",Stand Alone Procedure) its non Combinational Procedure ";
														alertCont+=standProcNames[j]+"("+standProcLst[j]+")"+" has already been added.Hence current Procedure cannot be selected.";
														
														resetDentalProc();
														alert(alertCont);
														focusBox(document.getElementById("icdProcCode"));
														
														return false;
													}
											}
									}
							}
					}
				/* for(var i=0;i<locSpecLst.length;i++)
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
														alert(alertCont);
														focusBox(document.getElementById("ICDProcName"));
														
														return false;
													}
											}
									}
							}
					} */
				
			}
		
		var finalAlertCombo="",finalAlertNonConmbo="",finalAlertStandAlone="";
		//jsonData.comboProcCode !=null && jsonData.comboProcCode!='' &&
		if(		jsonData.nonComboProcCode!=null && jsonData.nonComboProcCode!='' && jsonData.nonComboProcCode!=' ' && 
				jsonData.comboNonProcNames!=null && jsonData.comboNonProcNames!='' && jsonData.comboNonProcNames!=' ' && 
				jsonData.standaloneProc!=null && jsonData.standaloneProc!='' && jsonData.standaloneProc!=' ' &&
				jsonData.standaloneProcNames!=null && jsonData.standaloneProcNames!='' && jsonData.standaloneProcNames!=' ')
			{
				//var comboCodesNames=jsonData.comboProcNames.split("~");
				var nonComboCodesNames=jsonData.comboNonProcNames.split("~");
				var standaloneProceNames=jsonData.standaloneProcNames.split("~");
				
				//var comboCodes=jsonData.comboProcCode.split("~");
				var nonComboCodes=jsonData.nonComboProcCode.split("~");
				var standComboCodes=jsonData.standaloneProc.split("~");
				
				/*for(var i=0 ; i<comboCodesNames.length ; i++)
					{
						if(i==0)
							finalAlertCombo=comboCodesNames[i]+"("+comboCodes[i]+")";
						else
							finalAlertCombo=finalAlertCombo+" and "+comboCodesNames[i]+"("+comboCodes[i]+")";
					}*/
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
				
				alert(finalAlertNonConmbo+" should not be added in combination with selected Procedure,As the Selected Procedure is StandAlone Procedure "+finalAlertStandAlone+"should not be added in combination with selected Procedure");
			}
		//jsonData.comboProcCode !=null && jsonData.comboProcCode!='' &&
		/*else if(jsonData.nonComboProcCode!=null && jsonData.comboNonProcNames!='' &&
				(jsonData.standaloneProc==null || jsonData.standaloneProcNames==''))
			{
				var comboCodesNames=jsonData.comboProcNames.split("~");
				var nonComboCodesNames=jsonData.comboNonProcNames.split("~");
				
				var comboCodes=jsonData.comboProcCode.split("~");
				var nonComboCodes=jsonData.nonComboProcCode.split("~");
				
				for(var i=0 ; i<comboCodesNames.length ; i++)
					{
						if(i==0)
							finalAlertCombo=comboCodesNames[i]+"("+comboCodes[i]+")";
						else
							finalAlertCombo=finalAlertCombo+" and "+comboCodesNames[i]+"("+comboCodes[i]+")";
					}
				for(var i=0 ; i<nonComboCodesNames.length ; i++)
					{
						if(i==0)
							finalAlertNonConmbo=nonComboCodesNames[i]+"("+nonComboCodes[i]+")";
						else
							finalAlertNonConmbo=finalAlertNonConmbo+" and "+nonComboCodesNames[i]+"("+nonComboCodes[i]+")";
					}
				alert(finalAlertCombo+" are mandatory Procedures that should be added along with selected Procedure and "+finalAlertNonConmbo+" should not be added in combination with selected Procedure");
			}*/
		else
			{
				/*if(jsonData.comboProcCode!=null && jsonData.comboProcNames!='')
					{
						var comboCodesNames=jsonData.comboProcNames.split("~");
						var comboCodes=jsonData.comboProcCode.split("~");
						for(var i=0 ; i<comboCodesNames.length ; i++)
							{
								if(i==0)
									finalAlertCombo=comboCodesNames[i]+"("+comboCodes[i]+")";
								else
									finalAlertCombo=finalAlertCombo+" and "+comboCodesNames[i]+"("+comboCodes[i]+")";
							}
						alert(finalAlertCombo+" are mandatory Procedures that should be added along with selected Procedure");
					}*/
				if(jsonData.nonComboProcCode!=null && jsonData.nonComboProcCode!='' && jsonData.nonComboProcCode!=' ' &&
						jsonData.comboNonProcNames!=null && jsonData.comboNonProcNames!='' && jsonData.comboNonProcNames!=' ' )
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
						
						alert(finalAlertNonConmbo+" should not be added in combination with selected Procedure");
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
						
						alert("As the selected Procedure is a Stand Alone Procedure "+finalAlertStandAlone+" should not be added in combination ");
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
function commonCodeinRemove(procWiseLst,procCode)
	{
		var count=0;
		var newProcCode=null;
		if(procWiseLst!=null && procWiseLst.length>0)
			{
				for(var j=0;j<procWiseLst.length;j++)
					{
						var splitProcCombo=procWiseLst[j].split(",");
						if(splitProcCombo[0].indexOf(procCode+"!@#")>-1)
							{
								procWiseLst.splice(j,1);
								count++;
							}
					}
				if(count>0)
					{
						for(var j=0;j<procWiseLst.length;j++)
							{
								if(newProcCode==null || newProcCode=='' || newProcCode==' ')
									newProcCode=procWiseLst[j];
								else
									newProcCode=newProcCode+"$"+procWiseLst[j];
							}	
					}
				else
					newProcCode='N';
			}
		return newProcCode;
	}

function onloadDentalTGCond()
	{
		var localSchemeId=document.preauthDetailsForm.schemeId.value;
		var localSpecLst=new Array();
		var categoryIdLst=new Array();
		
		if(surgertIdsarray!=null && surgertIdsarray!='' && surgertIdsarray!= ' ')
			localSpecLst=surgertIdsarray;
		if(categoryIdsarray!=null && categoryIdsarray!='' && categoryIdsarray!= ' ')
			categoryIdLst=categoryIdsarray;
		
		if(localSpecLst!=null && localSpecLst.length>0 && categoryIdLst!=null && categoryIdLst.length>0 ) 
			{
				for(var localVal1=0 ; localVal1 < localSpecLst.length ; localVal1++)
					{
						if(categoryIdLst[localVal1]!=null && localSpecLst[localVal1]!=null)
							{
								if(categoryIdLst[localVal1]=='S18' && localSchemeId == 'CD202' )
									{
										getDentalConditions(localSpecLst[localVal1] , surgeryNamesarray[localVal1] , localSchemeId , 'comboCond' );
									}
							}
					}
			}	
	}
function removeSpecialInv()
	{
	    var my2div = document.getElementById('myDivSplUpload');
	    if (my2div != null) 
			{
				 while (my2div.hasChildNodes()) 
				 	{
					 	my2div.removeChild(my2div.lastChild);    
					}
			}
		var myDivSpl = document.getElementById('myDivSpl');
	    if (myDivSpl != null) 
	    	{
	    		while (myDivSpl.hasChildNodes()) 
	    		 	{
	    			 	myDivSpl.removeChild(myDivSpl.lastChild);    
	    			}
	    	}
	    //document.getElementById('myDivSpl').style.display="";
		//document.getElementById('myDivSplUpload').style.display="";
		 
		for(var i=0;i<=dbFilesArray.length;i++)
			{
				//dbFilesArray.splice(i,1);
				delete dbFilesArray[i];
				//A.splice(0, A.length);
			}
	}