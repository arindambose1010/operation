function getDrugSubTypeList()
{
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
    	//jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
		alert("Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
         
            if(trim(resultArray)=="SessionExpired*"){
            	//jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
				alert("Session has been expired");
				parent.sessionExpireyClose();
            }
            else{
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("@]","");            
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
    	
	url = "./clinicalNotesAction.do?actionFlag=getOpDrugSubList&callType=Ajax&ipOpType=IP&lStrDrugTypeId="+drugTypeCode;
	xmlhttp.open("Post",url,false);
	xmlhttp.send(null);  
	}
}


function getPharSubGrpLst(){
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
    	//jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
		alert("Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(trim(resultArray)=="SessionExpired*"){
            	//jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
				alert("Session has been expired");
				parent.sessionExpireyClose();
            }
            else{
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("@]","");            
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
    	
	//url = "./patientDetails.do?actionFlag=getDrugList&callType=Ajax&lStrDrugSubTypeId="+drugSubTypeCode;
	url = "./clinicalNotesAction.do?actionFlag=getPharDrugList&callType=Ajax&ipOpType=IP&lStrDrugSubTypeId="+drugSubTypeCode;
	xmlhttp.open("Post",url,false);
	xmlhttp.send(null);
	}
}
function getChemicalSubGrp(){
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
    	//jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
		alert("Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(trim(resultArray)=="SessionExpired*"){
            	//jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
				alert("Session has been expired");
				parent.sessionExpireyClose();
            }
            else{
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("@]","");            
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
    	
	url = "./clinicalNotesAction.do?actionFlag=getOpChemSubList&callType=Ajax&ipOpType=IP&pharSubCode="+pSubGrpCode;
	xmlhttp.open("Post",url,false);
	xmlhttp.send(null);
	}
}
function getDrugNameList()
{
	document.getElementById("cSubGrpCode").value="";
	document.forms[0].drugName.options.length=1;
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
    	//jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
		alert("Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(trim(resultArray)=="SessionExpired*"){
            	//jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
				alert("Session has been expired");
				parent.sessionExpireyClose();
            }
            else{
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("@]","");            
                	var drugList = resultArray.split("@,"); 
                	if(drugList.length>0)
                	{  
                		document.forms[0].drugName.options.length=0;
                        document.forms[0].drugName.options[0]=new Option("---select---","-1");
                		for(var i = 0; i<drugList.length;i++)
               		 	{	
                     		var arr=drugList[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null)
                     		{
                         	var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         	var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       	 	document.forms[0].drugName.options[i+1] =new Option(val1,val2);
                     		}
                		}
            		}
            	}
        	}
        }
    }
    	
	//url = "./patientDetails.do?actionFlag=getDrugList&callType=Ajax&lStrDrugSubTypeId="+drugSubTypeCode;
    url = "./clinicalNotesAction.do?actionFlag=getChemSubList&callType=Ajax&ipOpType=IP&cSubGrpCode="+drugSubTypeCode;
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
    	//jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
		alert("Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(trim(resultArray)=="SessionExpired*"){
            	//jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
				alert("Session has been expired");
				parent.sessionExpireyClose();
            }
            else{
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("@]","");            
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
    url = "./clinicalNotesAction.do?actionFlag=getStrengthList&callType=Ajax&ipOpType=IP&strengthTypeCode="+strengthTypeCode;
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
function addTooltip(input) 
{
	var numOptions = document.getElementById(input).options.length;
	 for ( var i = 0; i < numOptions; i++)
		document.getElementById(input).options[i].title = document
				.getElementById(input).options[i].text;
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
    	//jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
		alert("Your browser does not support XMLHTTP!");
    }
  
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(trim(resultArray)=="SessionExpired*"){
            	//jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
				alert("Session has been expired");
				parent.sessionExpireyClose();
            }
            else{
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("@]","");            
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
    url = "./clinicalNotesAction.do?actionFlag=getRouteList&callType=Ajax&ipOpType=IP&routeTypeCode="+routeTypeCode;
	xmlhttp.open("Post",url,false);
	xmlhttp.send(null);
	}
}

function addDrugs()
{
   document.getElementById("addDrug").disabled=true;
   document.getElementById('SaveNotesBut').disabled=false;
   if(document.getElementById("drugTypeCode").value==-1)
   {
		var fr=partial(focusBox,document.getElementById("drugTypeCode"));
		//jqueryAlertMsg('Drug Addition Required Fields',"Please select Main Group Name");
		alert("Please select Main Group Name");
		document.getElementById("addDrug").disabled=false;
		return false;
	}
	if(document.getElementById("drugSubTypeName").value==-1)
	{
		var fr=partial(focusBox,document.getElementById("drugSubTypeName"));
		//jqueryAlertMsg('Drug Addition Required Fields',"Please select Therapeutic Main Group Name");
		alert("Please select Therapeutic Main Group Name");
		document.getElementById("addDrug").disabled=false;
		return false;
	}
	if(document.getElementById("pSubGrpName").value==-1)
	{
		var fr=partial(focusBox,document.getElementById("pSubGrpName"));
		//jqueryAlertMsg('Drug Addition Required Fields',"Please select Pharmacological SubGroup Name");
		alert("Please select Pharmacological SubGroup Name");
		document.getElementById("addDrug").disabled=false;
		return false;
	}
	if(document.getElementById("cSubGrpName").value==-1)
	{
		var fr=partial(focusBox,document.getElementById("cSubGrpName"));
		//jqueryAlertMsg('Drug Addition Required Fields',"Please select Chemical SubGroup Name");
		alert("Please select Chemical SubGroup Name");
		document.getElementById("addDrug").disabled=false;
		return false;
	}
	if(document.getElementById("drugName").value==-1)
	{
		var fr=partial(focusBox,document.getElementById("drugName"));
		//jqueryAlertMsg('Drug Addition Required Fields',"Please select Chemical Substance Name");
		alert("Please select Chemical Substance Name");
		document.getElementById("addDrug").disabled=false;
		return false;
	}
	if(document.getElementById("routeType").value==-1)
	{
		var fr=partial(focusBox,document.getElementById("routeType"));
		//jqueryAlertMsg('Drug Addition Required Fields',"Please select Route ");
		alert("Please select Route Type");
		document.getElementById("addDrug").disabled=false;
		return false;
	}
	if(document.getElementById("route").value==-1)
	{
		var fr=partial(focusBox,document.getElementById("route"));
		//jqueryAlertMsg('Drug Addition Required Fields',"Please select Route");
		alert("Please select Route");
		document.getElementById("addDrug").disabled=false;
		return false;
	}
	if(document.getElementById("strengthType").value==-1)
	{
		var fr=partial(focusBox,document.getElementById("strengthType"));
		//jqueryAlertMsg('Drug Addition Required Fields',"Please select Strength ");
		alert("Please select Strength Type");
		document.getElementById("addDrug").disabled=false;
		return false;
	}
	if(document.getElementById("strength").value==-1)
	{
		var fr=partial(focusBox,document.getElementById("strength"));
		//jqueryAlertMsg('Drug Addition Required Fields',"Please select Strength ");
		alert("Please select Strength");
		document.getElementById("addDrug").disabled=false;
		return false;
	}
	if(document.getElementById("dosage").value==-1)
	{
		var fr=partial(focusBox,document.getElementById("dosage"));
		//jqueryAlertMsg('Drug Addition Required Fields',"Please select Dosage");
		alert("Please select Dosage");
		document.getElementById("addDrug").disabled=false;
		return false;
	}
	if(document.getElementById("medicatPeriod").value=="")
	{
		var fr=partial(focusBox,document.getElementById("medicatPeriod"));
		//jqueryAlertMsg('Drug Addition Required Fields',"Please enter Medication Period");
		alert("Please enter Medication Period");
		document.getElementById("addDrug").disabled=false;
		return false;
	}

	for(var c=0;c<drugs.length;c++)
	{
		var drugValues=drugs[c].split("~");
		if(drugValues[4]==document.getElementById("drugName").value)
		{
			//jqueryErrorMsg('Unique Drug Validation',"Drug Name already added.Please select another Drug Name");
			alert("Drug Name already added.Please select another Drug Name");
			document.getElementById("addDrug").disabled=false;
			return false;
		}
	}
	var drugTable=document.getElementById("drugsTable"); 
	drugTable.setAttribute('style',"table-layout:fixed;");
	var newRow=drugTable.insertRow(-1);
	var newcell=newRow.insertCell(0);
	newcell.setAttribute('class',"tbcellBorder");
	newcell.innerHTML='<td width="2%">'+parseInt(drugCount+1)+'</td>';
	var newcell=newRow.insertCell(1);
	newcell.setAttribute('style',"word-wrap:break-word");
	newcell.setAttribute('width',"10%");
	newcell.setAttribute('class',"tbcellBorder");
	newcell.innerHTML='<td width="10%" style="word-wrap:break-word;">'+document.getElementById("drugTypeCode").options[document.getElementById("drugTypeCode").selectedIndex].text+'</td>';
	newcell=newRow.insertCell(2);
	newcell.setAttribute('style',"word-wrap:break-word");
	newcell.setAttribute('width',"10%");
	newcell.setAttribute('class',"tbcellBorder");
	newcell.innerHTML='<td width="10%" style="word-wrap:break-word;">'+document.getElementById("drugSubTypeName").options[document.getElementById("drugSubTypeName").selectedIndex].text+'</td>';
	newcell=newRow.insertCell(3);
	newcell.setAttribute('style',"word-wrap:break-word");
	newcell.setAttribute('width',"10%");
	newcell.setAttribute('class',"tbcellBorder");
	newcell.innerHTML='<td width="10%" style="word-wrap:break-word;">'+document.getElementById("pSubGrpName").options[document.getElementById("pSubGrpName").selectedIndex].text+'</td>';
	newcell=newRow.insertCell(4);
	newcell.setAttribute('style',"word-wrap:break-word");
	newcell.setAttribute('class',"tbcellBorder");
	newcell.innerHTML='<td width="10%" style="word-wrap:break-word;">'+document.getElementById("cSubGrpName").options[document.getElementById("cSubGrpName").selectedIndex].text+'</td>';
	newcell=newRow.insertCell(5);
	newcell.setAttribute('style',"word-wrap:break-word");
	newcell.setAttribute('width',"10%");
	newcell.setAttribute('class',"tbcellBorder");
	newcell.innerHTML='<td width="10%" style="word-wrap:break-word;">'+document.getElementById("drugName").options[document.getElementById("drugName").selectedIndex].text+'</td>';
	newcell=newRow.insertCell(6);
	newcell.setAttribute('style',"word-wrap:break-word");
	newcell.setAttribute('width',"5%");
	newcell.setAttribute('class',"tbcellBorder");
	newcell.innerHTML='<td width="5%" style="word-wrap:break-word;">'+document.getElementById("routeType").options[document.getElementById("routeType").selectedIndex].text+'</td>';
	newcell=newRow.insertCell(7);
	newcell.setAttribute('style',"word-wrap:break-word");
	newcell.setAttribute('width',"10%");
	newcell.setAttribute('class',"tbcellBorder");
	newcell.innerHTML='<td width="10%" style="word-wrap:break-word;">'+document.getElementById("route").options[document.getElementById("route").selectedIndex].text+'</td>';
	newcell=newRow.insertCell(8);
	newcell.setAttribute('style',"word-wrap:break-word");
	newcell.setAttribute('width',"5%");
	newcell.setAttribute('class',"tbcellBorder");
	newcell.innerHTML='<td width="5%" style="word-wrap:break-word;">'+document.getElementById("strengthType").options[document.getElementById("strengthType").selectedIndex].text+'</td>';
	newcell=newRow.insertCell(9);
	newcell.setAttribute('style',"word-wrap:break-word");
	newcell.setAttribute('width',"10%");
	newcell.setAttribute('class',"tbcellBorder");
	newcell.innerHTML='<td width="10%" style="word-wrap:break-word;">'+document.getElementById("strength").options[document.getElementById("strength").selectedIndex].text+'</td>';
    newcell=newRow.insertCell(10);
    newcell.setAttribute('style',"word-wrap:break-word");
    newcell.setAttribute('width',"5%");
	newcell.setAttribute('class',"tbcellBorder");
	newcell.innerHTML='<td width="2%" style="word-wrap:break-word;">'+document.getElementById("dosage").value+'</td>';
	newcell=newRow.insertCell(11);
	newcell.setAttribute('style',"word-wrap:break-word");
	newcell.setAttribute('width',"5%");
	newcell.setAttribute('class',"tbcellBorder");
	newcell.innerHTML='<td width="2%" style="word-wrap:break-word;">'+document.getElementById("medicatPeriod").value+'</td>';
	newcell=newRow.insertCell(12);
	newcell.setAttribute('width',"10%");
	newcell.setAttribute('class',"tbcellBorder");
	newcell.innerHTML='<td width="10%" style="word-wrap:break-word;"><input class="but" type="button" value="Delete" name='+document.getElementById("drugName").value+' id='+document.getElementById("drugName").value+' /></td>';
	
	var deleteButName=document.getElementById("drugName").value;
	 document.getElementById(deleteButName).onclick = function(){
		 confirmRemoveRow(this,"drug");
		 }; 
	
	var drug=document.getElementById("drugTypeCode").value+"~"+document.getElementById("drugSubTypeName").value+"~"+document.getElementById("pSubGrpName").value+"~"+
	         document.getElementById("cSubGrpName").value+"~"+document.getElementById("drugName").value+"~"+document.getElementById("routeType").value+"~"+
	         document.getElementById("route").value+"~"+document.getElementById("strengthType").value+"~"+document.getElementById("strength").value+"~"+document.getElementById("dosage").value+"~"+document.getElementById("medicatPeriod").value;
  
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
	document.getElementById("addDrug").disabled=false;
	getDrugSubTypeList();
	//parent.fn_resizePage();
}

function confirmRemoveRow(src,type)
{
	var fr;
	
	 if(type=="drug")
		{
			//fr=partial(removeDrug,src);
			//jqueryConfirmMsg('Delete Drug Confirmation','Do you want to delete Drug?',fr);
			if(confirm('Do you want to delete Drug?'))
			{
				removeDrug(src);
			}
		}
	
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
			}
			//parent.fn_resizePage();
}
function validateNumber(arg1,input,zeroValid)
{
	var a=input.value;
	var fr = partial(focusBox,input);
	var regDigit=/^\d+$/ ;
	if(a.search(regDigit)==-1)
		{
		input.value="";
		//jqueryErrorMsg('Number Validation','Only numbers are allowed for '+arg1,fr);
		alert('Only numbers are allowed for '+arg1);
		focusBox(input);
		return false;
		}
	if(zeroValid !='0')
		{
	if(a.charAt(0)=="0")
	{
	input.value="";
		//jqueryErrorMsg('Number Validation',arg1+ ' should not start with 0',fr);
		alert(arg1+ ' should not start with 0');
		focusBox(input);
	return false;
	}
		}
}

function fn_radioClick(id,value)
{
	
	document.getElementById(id.name).value=value;
	if((id.name=='specimenRem' || id.name=='complications' ) && value=='Yes')
		{
		if(document.getElementById(id.name+'Div'))
			document.getElementById(id.name+'Div').style.display="";
		}
	else if((id.name=='specimenRem' || id.name=='complications' ) && value=='No')
		{
		if(document.getElementById(id.name+'Div'))
			document.getElementById(id.name+'Div').style.display="none";
		}
		
	//parent.fn_resizePage();
	}
function trim (str) 
{ 
	return str.replace (/^\s+|\s+$/g, '');
}