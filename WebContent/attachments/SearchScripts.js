String.prototype.trim = function() {
a = this.replace(/^\s+/, '');
return a.replace(/\s+$/, '');
};

function checkSplCharsWhileSearch(arg,arg2)
{
    var textbox1 =  eval("document.forms[0]."+arg);
    var textval = textbox1.value;
    textbox1.value = textval.trim();
   var ValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz/0123456789";
   var IsNumber=true;
   var Char; 
   if(textval.length > 0)
	{
	  for (i = 0; i < textval.length && IsNumber == true; i++) 
		 { 
	        Char = textval.charAt(i); 
           if (ValidChars.indexOf(Char) == -1) 
			{
				alert("Only Characters from A-Z,a-z,0-9 and Special character / are allowed in"+arg2);
               // textbox1.value= '';
                textbox1.focus();
				return false;
			 }
			}
	}
	else 
		return false;
}
function checkSplNumber(arg,arg2)
{
    var textbox1 =  eval("document.forms[0]."+arg);
    var textval = textbox1.value;
    textbox1.value = textval.trim();
   var ValidChars = "/0123456789";
   var IsNumber=true;
   var Char; 
   if(textval.length > 0)
	{
	  for (i = 0; i < textval.length && IsNumber == true; i++) 
		 { 
	        Char = textval.charAt(i); 
           if (ValidChars.indexOf(Char) == -1) 
			{
				alert("Only Numbers from 0-9 and Special character / are allowed in "+arg2);
				textbox1.value='';
                textbox1.focus();
				return false;
			 }
			}
	}
	else 
		return false;
}

function checkAlpha(arg,arg2)
{
    var textbox1 =  eval("document.forms[0]."+arg);
    var textval = textbox1.value;
   var ValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";
   var IsNumber=true;
   var Char; 
   if(textval.length > 0)
	{
	if(textval.trim().length==0)
	{
		alert("Blank spaces are not allowed in "+arg2);
		textbox1.value='';
		return false;
	  }
     for (i = 0; i < textval.length && IsNumber == true; i++) 
      { 
           Char = textval.charAt(i); 
           if (ValidChars.indexOf(Char) == -1) 
	         {
		        alert("Only Alphabets are allowed in "+arg2);
                //textbox1.value='';
                textbox1.focus();
		        return false;
	         }
      }
    }

	else 
		return false;
	
}

function checkAddress(arg,arg2)
{
    var textbox1 =  eval("document.forms[0]."+arg);
    var textval = textbox1.value;
   var ValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz/-#,. ()0123456789";
   var IsNumber=true;
   var Char; 
   if(textval.length > 0)
	{
     for (i = 0; i < textval.length && IsNumber == true; i++) 
      { 
           Char = textval.charAt(i); 
           if (ValidChars.indexOf(Char) == -1) 
	         {
		        alert("Only Characters from A-Z,a-z,0-9 and Special characters / # - ,(). are  allowed in "+arg2);
                //textbox1.value='';
                textbox1.focus();
		        return false;
	         }
      }
    }
	else 
		return false;
}

function checkRefCardNo(arg,arg2)
{
    var textbox1 =  eval("document.forms[0]."+arg);
    var textval = textbox1.value;
   var ValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
   var IsNumber=true;
   var Char; 
   if(textval.length > 0)
	{
	  for (i = 0; i < textval.length && IsNumber == true; i++) 
		 { 
	        Char = textval.charAt(i); 
           if (ValidChars.indexOf(Char) == -1) 
			{
				alert("Only Characters from A-Z,a-z,0-9 are allowed in "+arg2);
               // textbox1.value= '';
                textbox1.focus();
				return false;
			 }
			}
	}
	else 
		return false;
}
function checkRefCardNo1(arg,arg2)
{
    var textbox1 =  eval("document.forms[0]."+arg);
    var textval = textbox1.value;
   var ValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789/";
   var IsNumber=true;
   var Char; 
   if(textval.length > 0)
	{
	  for (i = 0; i < textval.length && IsNumber == true; i++) 
		 { 
	        Char = textval.charAt(i); 
           if (ValidChars.indexOf(Char) == -1) 
			{
				alert("Only Characters from A-Z,a-z,0-9,/ are allowed in "+arg2);
               // textbox1.value= '';
                textbox1.focus();
				return false;
			 }
			}
	}
	else 
		return false;
}
function checkIfNoAlphabet(arg,arg2)
{  
   var len,str,str1,i;	
   str=arg.value;
   len=str.length;
   var  str1="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	if(len>0)
	{	 
	for(i=0,j=0;i<len;i++)
	{		
		if(str1.indexOf(str.charAt(i))!=-1)
		{
		j++;
		}		
	}  
	if(j==0)
	{
		alert(arg2+" "+"must contain alphabets");	
		arg.focus();
		return false;
	}
	}
}
function CheckTextArea(arg,arg2)
{
    var textbox1 =  eval("document.forms[0]."+arg);
    var textval = textbox1.value;
	var remarks = arg2;
   var ValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz 0123456789`~!%&*()+-=[]{}\|;:,.<>/?";
   var IsNumber=true;
   var Char; 
   if(textval.length > 0)
	{
     for (i = 0; i < textval.length && IsNumber == true; i++) 
      { 
           Char = textval.charAt(i); 
           if (ValidChars.indexOf(Char) == -1) 
	         {
		        alert("Only Characters from A-Z,a-z,0-9 and Special characters `~!%&*()+-=[]{}\|;:,.<>/? are allowed in "+arg2);
                //textbox1.value='';
                textbox1.focus();
		        return false;
	         }
      }
	  checkIfNoAlphabet(textbox1,remarks);
    }
	else 
		return false;
}

function checkClinicalNotes(arg,arg2)
{
    var textbox1 =  eval("document.forms[0]."+arg);
    var textval = textbox1.value;
   var ValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz/-,. ()0123456789";
   var IsNumber=true;
   var Char; 
   if(textval.length > 0)
	{
     for (i = 0; i < textval.length && IsNumber == true; i++) 
      { 
           Char = textval.charAt(i); 
           if (ValidChars.indexOf(Char) == -1) 
	         {
		        alert("Only Characters from A-Z,a-z,0-9 and Special characters /- ,(). are  allowed in "+arg2);
                //textbox1.value='';
                textbox1.focus();
		        return false;
	         }
      }
    }
	else 
		return false;
}

function checkPhoneNo(arg,arg2)
{
    var textbox1 =  eval("document.forms[0]."+arg);
    var textval = textbox1.value;
   var ValidChars = "0123456789";
   var IsNumber=true;
   var Char; 
   if(textval.length > 0)
	{
     for (i = 0; i < textval.length && IsNumber == true; i++) 
      { 
           Char = textval.charAt(i); 
           if (ValidChars.indexOf(Char) == -1) 
	         {
		        alert("Only Numbers are allowed in "+arg2);
				if(arg == 'caseId') 
                {
					textbox1.value='';
				}
                textbox1.focus();
		        return false;
	         }
      }
    }

	else 
		return false;
	
}

function IsNumeric_YN(arg,arg2)
{

	
   var textbox1 =  eval("document.forms[0]."+arg);
   var textval = textbox1.value;
   var ValidChars = "0123456789.";
   var IsNumber=true;
   var Char; 
   
   for (i = 0; i < textval.length && IsNumber == true; i++)
   { 
       Char = textval.charAt(i); 
       if (ValidChars.indexOf(Char) == -1) 
	   {
		alert("Only Numbers are allowed in "+arg2);
         textbox1.value="";       
         textbox1.focus();
		return false;
	   }
   }
}

function IsSpecialNumeric(arg,arg2)
{
   var textbox1 =  eval("document.forms[0]."+arg);
   var textval = textbox1.value;
   var ValidChars = "CFIM0123456789/";
   var IsNumber=true;
   var Char; 
   
   for (i = 0; i < textval.length && IsNumber == true; i++)
   { 
       Char = textval.charAt(i); 
       if (ValidChars.indexOf(Char) == -1) 
	   {
		alert("Only Valid Characters and / are allowed in "+arg2);
         textbox1.value="";       
         textbox1.focus();
		return false;
	   }
   }
}
 
function checkTextarea4Junkdata(arg,arg2)
{
   var textbox1 =  eval("document.forms[0]."+arg);
   var textval = textbox1.value;
   //var ValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz 0123456789`~!@#$%^&*()_+-=[]{}\|;:,.<>/?";
   var IsNumber=true;
   var charCode;
   
   for (i = 0; i < textval.length && IsNumber == true; i++) 
   { 
     charCode = textval.charCodeAt(i);		 
     if( (charCode<13 && charCode>13)  || (charCode>13 && charCode<32) || charCode>126)
	 {
			alert("No Junk Data's are allowed in "+ arg2);
			//textbox1.value='';
			textbox1.focus();
			return false;
	 }
		if( (textval.charCodeAt(0)==13) )
	   {
			alert("First character can not be the enter key in Remarks");
			textbox1.focus();
			return false;
	   }
   }  
}
function fn_CheckTextAreaLen(arg1,ta,len)
{
    if(ta.value.length > len)
    {
        alert("Enter "+len+" characters only in "+arg1);
		ta.focus();
        return;
    }
}
function func_minlen(arg1,ta,len)
{	
	if(ta.value.trim().length <= len)
    {
        alert("The Remarks entered should not be less than or equal to "+len+" characters in "+arg1);
		ta.focus();
        return;
    }
	return false;
}


function checkAlphadot(arg,arg2)
{
    var textbox1 =  eval("document.forms[0]."+arg);
    var textval = textbox1.value;
   var ValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz. ";
   var IsNumber=true;
   var Char; 
   if(textval.length > 0)
	{
     for (i = 0; i < textval.length && IsNumber == true; i++) 
      { 
           Char = textval.charAt(i); 
           if (ValidChars.indexOf(Char) == -1) 
	         {
		        alert("Only Alphabets and dot are allowed in "+arg2);
                //textbox1.value='';
                textbox1.focus();
		        return false;
	         }
      }
    }

	else 
		return false;
	
}

function checkIPOPNo(arg,arg2)
{
    var textbox1 =  eval("document.forms[0]."+arg);
    var textval = textbox1.value;
   var ValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
   var IsNumber=true;
   var Char; 
   if(textval.length > 0)
	{
	  for (i = 0; i < textval.length && IsNumber == true; i++) 
		 { 
	        Char = textval.charAt(i); 
           if (ValidChars.indexOf(Char) == -1) 
			{
				alert("Only Characters from A-Z,a-z,0-9 are allowed in "+arg2);
               // textbox1.value= '';
                textbox1.focus();
				return false;
			 }
			}
			checkIfNoNumeric(textbox1,arg2);
	}
	else 
		return false;
}
function checkRefNo(arg,arg2)
{
    var textbox1 =  eval("document.forms[0]."+arg);
    var textval = textbox1.value;
   var ValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789/";
   var IsNumber=true;
   var Char; 
   if(textval.length > 0)
	{
	  for (i = 0; i < textval.length && IsNumber == true; i++) 
		 { 
	        Char = textval.charAt(i); 
           if (ValidChars.indexOf(Char) == -1) 
			{
				alert("Only Characters from A-Z,a-z,0-9,/ are allowed in "+arg2);
               // textbox1.value= '';
                textbox1.focus();
				return false;
			 }
			}
			checkIfNoNumeric(textbox1,arg2);
	}
	else 
		return false;
}
function checkNameVal(form1)
{
  
   var len,str,str1,i		
    len=form1.value.length		
    str=form1.value
		
    str1="abcdefghijklmnopqurstuvwxyABCDEFGHIJKLMNOPQRSTUVWXYZ "
	
	 for(i=0;i<len;i++)
    {		
        if((str1.indexOf(str.charAt(i)))==-1)
        {
                alert("Only Characters from A-Z,a-z and Space are allowed");
                form1.value="";
                form1.focus();
                return false;
        }
    }  
 return true;
} 
function fn_checkAllZero(obj,arg)
{
var len=obj.value.length;
var str=obj.value;
if(len>0){
for(i=0,j=0;i<len;i++)
    {        
		if(str.charAt(i)==0)
		{
		j++;
		}
		if(j==len)
		{
			alert(arg +" with all zeros is invalid. Please enter a valid "+arg);
			obj.value="";
			obj.focus();
			return false;
		}
	}
	}
}
function fn_checkSingleChar(obj,arg)
{
	var len=obj.value.length;
	var str=obj.value;
	if(len==1)
	{
	alert(arg + ' cannot be a single character value');
	obj.value="";
	obj.focus();
	return false;
	}
}
function checkIfNoNumeric(obj,arg)
{  
   var len,str,str1,i		
    len=obj.value.length		
    str=obj.value
		
     str1="0123456789"
	if(len>0)
	{	 
	for(i=0,j=0;i<len;i++)
	{		
		if(str1.contains(str.charAt(i)))
		{
		j++;
		}		
	}  
	if(j==0)
	{
		alert(arg +" should not be without numeric value");
		obj.focus();
		return false;
	}
	}
} 

