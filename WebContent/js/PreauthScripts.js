// treating doctor mandatory checks

function checkcontact(arg1,arg)//for accepting numbers either 10 0r 11 digits only
{
    var len,str,str1,i
    len=arg1.value.length
    str=arg1.value
    str1="0123456789";
    var e="[7,8,9]";
	if(len<=9 && len>0)
	{
	   alert("Enter 10 digits valid "+arg);
	   focusNClear(arg1);
		//var fr = partial(focusNClear,arg1);
		//jqueryAlertMsg('Preauth mandatory fields',"Enter 10 digits valid "+arg,fr);
	  // arg1.value="";
	 //  arg1.focus();
       return false;	   
	}
	for(i=0,j=0;i<len;i++)
    {
        if((str1.indexOf(str.charAt(i)))==-1)
        {
        	//var fr = partial(focusNClear,arg1);
    		//jqueryAlertMsg('Preauth mandatory fields',"Enter Numeric Data in "+arg,fr);
                alert("Enter Numeric Data in "+arg);
				focusNClear(arg1);
//                arg1.value="";
//                arg1.focus();
                return false;
        }
         if (i==0)
        	{
        	if(str.charAt(i).search(e))
        		{
        		//var fr = partial(focusNClear,arg1);
        		//jqueryAlertMsg('Preauth mandatory fields',arg+" should start with number 7,8 or 9",fr);
        		alert(arg+" should start with number 7,8 or 9");
				focusNClear(arg1);
//        	    arg1.value="";
//        	    arg1.focus();
        	    return false;
        		}
        	}
		if(str.charAt(i)==0)
		{
		j++;
		}
		if(j==len)
		{
			//var fr = partial(focusNClear,arg1);
    		//jqueryAlertMsg('Preauth mandatory fields',arg +" with all zeros is invalid. Please enter a valid "+arg,fr);
			alert(arg +" with all zeros is invalid. Please enter a valid "+arg);
			focusNClear(arg1);
//			arg1.value="";
//			arg1.focus();
			return false;
		}
	}
return true;
}
function chkAlphaNumeric(arg,arg2)
{
    var textbox1 =  eval("document.forms[0]."+arg);    
    var textval = textbox1.value;
    var tbLen = textval.replace(/\s+/g,'').replace(/\s+$/g,'') ;
    if(tbLen.length == 0)
    	{
    	//var fr = partial(focusNClear,textbox1);
		//jqueryAlertMsg('Clinical notes mandatory fields','Blank spaces are not allowed in '+arg2,fr);
    	alert('Blank spaces are not allowed in '+arg2);
		focusNClear(textbox1);
//    	textbox1.focus();
//    	textbox1.value='';
    	return false;
    	}
    if(textval.charAt(0)==' '){ 
    	//var fr = partial(focusNClear,textbox1);
		//jqueryAlertMsg('Clinical notes mandatory fields','Starting blank spaces are not allowed in '+arg2,fr);
		alert('Starting blank spaces are not allowed in '+arg2);
		focusNClear(textbox1);
//    	textbox1.focus();
//    	textbox1.value='';
    	return false;
    }
    var iChars = "~`!#$%^&*+=-[]\\\';,/{}|\":<>?.";
    if(iChars.indexOf(textval.charAt(0))!= -1){
    	//var fr = partial(focusNClear,textbox1);
		//jqueryAlertMsg('Clinical notes mandatory fields','Starting special characters are not allowed in '+arg2,fr);
		alert('Starting special characters are not allowed in '+arg2);
		focusNClear(textbox1);
    }
   var ValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz 0123456789()./";
   var IsNumber=true;
   var Char;
   textval= textval.replace(/\r\n|\r|\n/g, '');
   if(textval.length > 0)
	{
	  for (i = 0; i < textval.length && IsNumber == true; i++) 
		 { 
	        Char = textval.charAt(i); 
           if (ValidChars.indexOf(Char) == -1) 
			{
        	   //var fr = partial(focusNClear,textbox1);
       		   //jqueryAlertMsg('Clinical notes mandatory fields',"Only Characters from A-Z,a-z,0-9 are allowed in "+arg2,fr);
				alert("Only Characters from A-Z,a-z,0-9 are allowed in "+arg2);
				focusNClear(textbox1);
//                textbox1.value= '';
//                textbox1.focus();
				return false;
			 }
			}
	  if( textval.match(/[,.\/]{2}/i))
		{
		//var fr = partial(focusNClear,textbox1);
		  // jqueryAlertMsg('Clinical notes mandatory fields',"continuous special characters are not allowed in "+arg2,fr);
		  alert("Continuous special characters are not allowed in "+arg2);
		  focusNClear(textbox1);
		}
	}
	else 
		return false;
}

function chkAlpha(arg,arg2)
{
    var textbox1 =  eval("document.forms[0]."+arg);    
    var textval = textbox1.value;
    var tbLen = textval.replace(/\s+/g,'').replace(/\s+$/g,'') ;
    if(tbLen.length == 0)
    	{
    	// var fr = partial(focusNClear,textbox1);
 		// jqueryAlertMsg('Preauth mandatory fields','Blank spaces are not allowed in '+arg2,fr);
    	alert('Blank spaces are not allowed in '+arg2);
		focusNClear(textbox1);
//    	textbox1.focus();
//    	textbox1.value='';
    	return false;
    	}
    if(textval.charAt(0)==' '){ 
    	//var fr = partial(focusNClear,textbox1);
		// jqueryAlertMsg('Preauth mandatory fields','Starting blank spaces are not allowed in '+arg2,fr);
    	alert('Starting blank spaces are not allowed in '+arg2);
		focusNClear(textbox1);
//    	textbox1.focus();
//    	textbox1.value='';
    	return false;
    }
   var ValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ()./";
   var IsNumber=true;
   var Char; 
   if(textval.length > 0)
	{
	  for (i = 0; i < textval.length && IsNumber == true; i++) 
		 { 
	        Char = textval.charAt(i); 
           if (ValidChars.indexOf(Char) == -1) 
			{
        	  // var fr = partial(focusNClear,textbox1);
      		   //jqueryAlertMsg('Preauth mandatory fields',"Only Characters from A-Z,a-z are allowed in "+arg2,fr);
				alert("Only Characters from A-Z,a-z are allowed in "+arg2);
				focusNClear(textbox1);
//                textbox1.value= '';
//                textbox1.focus();
				return false;
			 }
			}
	  
	if( textval.match(/[,.\(\)\/]{2}/i))
		{
		//var fr = partial(focusNClear,textbox1);
		 //  jqueryAlertMsg('Preauth mandatory fields',"continuous special characters are not allowed in "+arg2,fr);
		 alert("Continuous special characters are not allowed in "+arg2);
		 focusNClear(textbox1);
		}
	 var iChars = "~`!#$%^&*+=-[]\\\';,/{}|\":<>?.";
	    if(iChars.indexOf(textval.charAt(0))!= -1){
	    	//var fr = partial(focusNClear,textbox1);
			//jqueryAlertMsg('Preauth mandatory fields','Starting special characters are not allowed in '+arg2,fr);	
			alert("Starting special characters are not allowed in "+arg2);
			focusNClear(textbox1);
	   }

	}
	else 
		return false;
}

function checkRadio1(id,value)
{
	document.getElementById(id.name).value=value;
	}

function fn_cancelPreauth()
{
	 if(document.forms[0].cancelRemarks.value == null || document.forms[0].cancelRemarks.value=='')
		{
			 //var fr = partial(focusBox,document.getElementById('cancelRemarks'));
			 //jqueryAlertMsg('Preauth mandatory fields','Please enter the remarks',fr);
			 alert("Please enter the remarks ");
			focusNClear(document.getElementById('cancelRemarks'));
			return;
		}
	 if(document.forms[0].cancelRemarks.value != null)
		 {
		 var tbLen = document.forms[0].cancelRemarks.value.replace(/\s+/g,'').replace(/\s+$/g,'') ;
		    if(tbLen.length == 0)
		    	{
		    	 //var fr = partial(focusBox,document.getElementById('cancelRemarks'));
				 //jqueryAlertMsg('Preauth mandatory fields','Blank spaces are not allowed in remarks',fr);
		    	 alert('Blank spaces are not allowed in remarks');
				 focusNClear(document.getElementById('cancelRemarks'));
				 return;
		    	}
		 
	}

	//var fr = partial(fn_cancelSub);
	 //jqueryConfirmMsg('Preauth Cancellation','Do you want to cancel preauth ? ',fr);	
	 if(confirm('Do you want to cancel preauth ?'))
	 {
		fn_cancelSub();
	 }

}
function fn_cancelSub()
{	
	document.getElementById('cancelPreauth').disabled=true;
	document.forms[0].action="/Operations/preauthDetails.do?actionFlag=cancelPreauth";
    document.forms[0].submit();	
}
function trim (str) 
{ 
	return str.replace (/^\s+|\s+$/g, '');
}

