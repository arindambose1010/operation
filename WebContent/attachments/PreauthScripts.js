var cardNoWin = 0;
var AllAttach = 0;
var childWinTelAprvDtls = 0;

function validateFutureDt(arg,arg1)//function for not accepting future date
{
    var childWindow=null;
	var url =  "/ASRI/FrontServlet?requestType=AuthUserViewRH&actionVal=getFutureDtServerDtTimeStamp";
	childWindow = window.open(url,"ValidateFutureDate", 'left=350, top = 400, width=1, height=1');
		var today;
		do{
			try{
				today=childWindow.document.ValidateFutureDate.ServerDtTimeStamp.value;
			   }
			catch(e){}
		}while(today == undefined || today == null);
		childWindow.close();
		 var ChkDt=eval("document.forms[0]."+arg);
		 var ChkDtVal=ChkDt.value; 
		today=new Date(today.substring(6,10),today.substring(3,5)-1,today.substring(0,2));
		// Start For Year Validation (<2005 should not accept)
		if(arg1 =='Health/White Card Issue Date'){
		  if(document.forms[0].lowerYearLimit){
		   ValidateYearLimit(arg,arg1,document.forms[0].lowerYearLimit.value);
		  }
		}//End For Year Validation (<2005 should not accept)
		if(ChkDtVal != null)
		{
		 var valu=new Date(ChkDtVal.substring(6,10),ChkDtVal.substring(3,5)-1,ChkDtVal.substring(0,2));
		 if(valu > today)
			{
				alert("Cannot Enter Future Date in "+arg1);
				ChkDt.value="";
				return false;
			}
			else
			{
				focusNext(ChkDt);
			    return true;	
			}
		 } 
		 
}
function validatePastDt(arg,arg1)//function for not accepting past date
{
    var childWindow=null;
	var url =  "/ASRI/FrontServlet?requestType=AuthUserViewRH&actionVal=getFutureDtServerDtTimeStamp";
	childWindow = window.open(url,"ValidateFutureDate", 'left=350, top = 400, width=1, height=1');
		var today;
		do{
			try{
				today=childWindow.document.ValidateFutureDate.ServerDtTimeStamp.value;
			   }
			catch(e){}
		}while(today == undefined || today == null);
		childWindow.close();
	var fdate=eval("document.forms[0]."+arg);
    var repDt=fdate.value;
    if(repDt != null)
    {
        var ndt = new Date(repDt.substring(6,10),repDt.substring(3,5)-1,repDt.substring(0,2));
		//alert("ndt"+ndt);
        var CurDate = new Date(today.substring(6,10),today.substring(3,5)-1,today.substring(0,2));
		//alert(CurDate);
        if(ndt < CurDate)
        {
                alert(arg1+" should be greater than or equal to Todays Date");
                fdate.value="";
                return false;
        }    
        else
        {
			fdate.onfocus='';
			return true;
        }
    }
   
}
function ValidateYearLimit(arg,arg1,year){
      var ChkDt=eval("document.forms[0]."+arg);
	  var ChkDtVal=ChkDt.value;
	  if(ChkDtVal != null){
	    var selYear=ChkDtVal.substring(6,10);
	    if(selYear < 2005){
	      alert("The Selected Year of "+arg1+" should not be less than "+year);
		  ChkDt.value="";
		  //ChkDtVal.focus();
		  return false;
	    }
	  }
}
function validateTodayPastDt(arg,arg1,arg2)//function for not accepting pastdates 
{
  var curDt= eval("document.forms[0]."+arg);
  var CurntDate = curDt.value;
  var followupdate=eval("document.forms[0]."+arg1);
  var fdate = followupdate.value;
  if(CurntDate!= null && fdate!= null)
  {
      sdt = new Date(fdate.substring(6,10),fdate.substring(3,5)-1,fdate.substring(0,2));
      cdt=new Date(CurntDate.substring(6,10),CurntDate.substring(3,5)-1,CurntDate.substring(0,2));
       if( sdt != '' &&  sdt<=cdt ) 
      {	    
          alert(arg2+" Should not be less than or equal to todays date");	 
          followupdate.value = "";	
          followupdate.focus();
          return;
      }  
   }
}
function validateDate(arg1,arg2,arg3,arg4,arg5)
{
     var today=new Date();
     var Dt1 = eval("document.forms[0]."+arg1);
     var Dt2 = eval("document.forms[0]."+arg2);     
     var RegDt=Dt2.value; 
     if(arg3 != null)
     {
//        var Selected=document.forms[0].txt_PHC_mc; 
        var selectedvalue=Dt1.options[Dt1.selectedIndex].text;
        var Dt=selectedvalue.substring((selectedvalue.length)-10).trim();
        var radio = document.forms[0].elements[arg3];
        for(var i=0;i<radio.length;i++)
        {
            if(radio[i].checked == true)
            {
                var val = radio[i].value;
                 if(val == 'MC')
                {
                    if(RegDt != null && RegDt != "" && Dt!=null && Dt!="")
                    {
                        var HCDt=new Date(Dt.substring(6,10),Dt.substring(3,5)-1,Dt.substring(0,2));
                        var RegesDt=new Date(RegDt.substring(6,10),RegDt.substring(3,5)-1,RegDt.substring(0,2));
                        if(RegesDt<HCDt)
                        {
                            alert(arg5+" should be not be less than "+arg4);
                            Dt2.value="";
                            return false;
                        }
                         validateFutureDt(arg2,arg5);
                    }
                    else
                    {
                        return true;
                    }
                }
                if(val == 'P' || val=='D')
                {
                   validateFutureDt(arg2,arg5); 
                }
            }
         }
       }
       else
       {
           var SelDt= Dt1.value;
           if(RegDt != null && RegDt != "" && SelDt!=null && SelDt!="")
           {
                var FinalDt=new Date(SelDt.substring(6,10),SelDt.substring(3,5)-1,SelDt.substring(0,2));
                var RegesDt=new Date(RegDt.substring(6,10),RegDt.substring(3,5)-1,RegDt.substring(0,2));
                if(RegesDt < FinalDt)
                {
                    alert(arg5+"  should be not be less than "+arg4);
                    Dt2.value="";
                    return false;
                } 
                if(arg2 != "Date") 
                {                    
                    validateFutureDt(arg2,arg5);
                }
           }
           else
          {
                return true;
          }
       }
}
/*function validateDate(arg)
{
    var today=new Date();  
    if(arg == "txt_PHC_mc" || arg == "txt_DtOP")
    {
        var Selected=document.forms[0].txt_PHC_mc; 
        var selectedvalue=Selected.options[Selected.selectedIndex].text;
        var Dt=selectedvalue.substring((selectedvalue.length)-10).trim();
        var RegDt=document.forms[0].txt_DtOP.value;        
        var radio = document.forms[0].elements['rd_Source'];
        for(var i=0;i<radio.length;i++)
        {
            if(radio[i].checked == true)
            {
                var val = radio[i].value;
                 if(val == 'MC')
                {
                    if(RegDt != null && RegDt != "")
                    {
                        var HCDt=new Date(Dt.substring(6,10),Dt.substring(3,5)-1,Dt.substring(0,2));
                        var RegesDt=new Date(RegDt.substring(6,10),RegDt.substring(3,5)-1,RegDt.substring(0,2));
                        if(RegesDt<HCDt)
                        {
                            alert(" Date of Registration should be not be less than Medical Camp Conducted Date");
                            document.forms[0].txt_DtOP.value="";
                            return false;
                        }
                        if(RegesDt>today)
                        {
                            alert("Date of Registration should not be a future date");
                            document.forms[0].txt_DtOP.value="";
                            return false;
                        }
             
                    }
                    else
                    {
                        return true;
                    }
                }
            }
         }
    }
    else if(arg == 'LastDtCycle' || arg == 'BillDate')
    {
        var eDate=document.forms[0].Admn_dt.value;
        var admitdate = new Date(eDate.substring(6,10),eDate.substring(3,5)-1,eDate.substring(0,2));
        var eDt=eval("document.forms[0]."+arg);
        var eDate1=eDt.value;
        if(eDate1 !=null && eDate1 != '')
         {
              var Dt=new Date(eDate1.substring(6,10),eDate1.substring(3,5)-1,eDate1.substring(0,2));
              var arg1="";
              if(arg == 'LastDtCycle')
              arg1="Last Date Cycle";
              if(arg == 'BillDate')
              arg1="Bill Date";*/
           /*   if(Dt<admitdate)
              {
                   alert(arg1+" should not be less than Admission Date");
                   eDt.value="";
              }*/
              /*if(val == 'LastDtCycle')
              {
                 if(Dt > today)
                  {
                       alert(arg1+" should not be Future Date");
                       eDt.value="";
                       return false;
                  }
                  else
                  {
                       return true;
                  }
              }
         }
    }
    else if(arg == "nxtFuDt")
    {
        var CurntDate = document.forms[0].CurrentDt.value;
        var fdate1=eval("document.forms[0]."+arg);
        var fdate=fdate1.value;
        sdt = new Date(fdate.substring(6,10),fdate.substring(3,5)-1,fdate.substring(0,2));
        cdt=new Date(CurntDate.substring(6,10),CurntDate.substring(3,5)-1,CurntDate.substring(0,2));
        if( sdt != '' &&  sdt<=cdt ) 
        {	    
          alert("Next FollowUp Date Should not be less than or equal to todays date");	 
          fdate1.value = "";	
          return false;
        }  
        else
        {
            return true;
        }
    }
    else if(arg == "preauth_strt_dt")
    {
        var AdmissionDt=document.forms[0].AdmissionDt.value;
        var PreauthStrtDt1=eval("document.forms[0]."+arg);
        var PreauthStrtDt=PreauthStrtDt1.value;
        if(PreauthStrtDt != null && PreauthStrtDt != "")
        {
            var AdmnDt=new Date(AdmissionDt.substring(6,10),AdmissionDt.substring(3,5)-1,AdmissionDt.substring(0,2));
            var PreStrtDt=new Date(PreauthStrtDt.substring(6,10),PreauthStrtDt.substring(3,5)-1,PreauthStrtDt.substring(0,2));
            if(PreStrtDt < AdmnDt)
            {
                alert("Preauthorized Treatment Started on Date cannot be less than Date of Admission");
                PreauthStrtDt1.value="";
                return false;
            }            
            if(PreStrtDt > today)
            {
                alert("Preauthorized Treatment Started on Date cannot be Future Date");
                PreauthStrtDt1.value="";
                return false;
            }
        }
        else
        {
            return true;
        }
    }
    else if(arg == "txt_DtRep")
    {
        var fdate=eval("document.forms[0]."+arg);
        var repDt=fdate.value;
        var ndt = new Date(repDt.substring(6,10),repDt.substring(3,5)-1,repDt.substring(0,2));
        var curr_date = today.getDate();
        var curr_month = today.getMonth();
        var curr_year = today.getFullYear();
        var CurDate = new Date(curr_year,curr_month,curr_date);
        if(ndt < CurDate)
        {
                alert("Reporting Date should be greater than or equal to Todays Date");
                fdate.value="";
                return false;
        }
        else
        {
            return true;
        }
    }
    else
    {
       var Issueval=eval("document.forms[0]."+arg);
        var IssueValDt=Issueval.value;   
        if(IssueValDt != null)
        {
            var valu=new Date(IssueValDt.substring(6,10),IssueValDt.substring(3,5)-1,IssueValDt.substring(0,2));
            if(valu > today)
            {
                alert("Cannot Enter Future Date");
                Issueval.value="";
                return false;
            }
            else
            {
                return true;
            }
         } 
    }
}*/
//function checkDt(val,arg)
//{
//     var eDate=document.forms[0].Admn_dt.value;
//     var today=new Date();
//     var admitdate = new Date(eDate.substring(6,10),eDate.substring(3,5)-1,eDate.substring(0,2));
//     var eDt=eval("document.forms[0]."+val);
//     var eDate1=eDt.value;
//     if(eDate1 !=null && eDate1 != '')
//     {
//          var Dt=new Date(eDate1.substring(6,10),eDate1.substring(3,5)-1,eDate1.substring(0,2));
//       /*   if(Dt<admitdate)
//          {
//               alert(arg+" should not be less than Admission Date");
//               eDt.value="";
//          }*/
//          if(val == 'LastDtCycle')
//          {
//             if(Dt > today)
//              {
//                   alert(arg+" should not be Future Date");
//                   eDt.value="";
//              }
//          }
//     }
//}
//function checkDt(val,arg)
//{
//     var eDate=document.forms[0].Admn_dt.value;
//     var today=new Date();
//     var admitdate = new Date(eDate.substring(6,10),eDate.substring(3,5)-1,eDate.substring(0,2));
//     var eDt=eval("document.forms[0]."+val);
//     var eDate1=eDt.value;
//     if(eDate1 !=null && eDate1 != '')
//     {
//          var Dt=new Date(eDate1.substring(6,10),eDate1.substring(3,5)-1,eDate1.substring(0,2));
//       /*   if(Dt<admitdate)
//          {
//               alert(arg+" should not be less than Admission Date");
//               eDt.value="";
//          }*/
//          if(val == 'LastDtCycle')
//          {
//             if(Dt > today)
//              {
//                   alert(arg+" should not be Future Date");
//                   eDt.value="";
//              }
//          }
//     }
//}
//function checkDate(dateString)
//{
//	var fDate=dateString.value;
//	var today=new Date();
//	if(fDate != null)
//	{
//    	fdt = new Date(fDate.substring(6,10),fDate.substring(3,5)-1,fDate.substring(0,2));
//		if(fdt > today)
//		{
//			alert("Cannot Enter Future Date");
//			dateString.value="";
//		}
//	}
//}
function numberfunc(arg) //for accepting only numeric no's including +ve,-ve and decimals
{
    var textbox1 =  eval("document.forms[0]."+arg);
    var textval = textbox1.value;
    if(isNaN(textval))
    {        
        alert("Error: Numeric input only");        
        textbox1.value="";
        textbox1.focus();
    }
}
function isNumeric(form1,arg)//for accepting only numbers 0-9
{
    var len,str,str1,i
    len=form1.value.length
    str=form1.value
    str1="0123456789"
    for(i=0;i<len;i++)
    {
        if((str1.indexOf(str.charAt(i)))==-1)
        {
                alert("Enter Numeric Data in "+arg);
                form1.value="";
                form1.focus();
                return false;
        }
    }
	return true
}
function checkTime(arg1,arg2,arg3) 
{
    var time_hr=eval("document.forms[0]."+arg1);
    var time_min=eval("document.forms[0]."+arg2);
    if((time_hr.value >= 24) || (time_min.value >= 59) ||
    (time_min.value <0)) 
    {
        alert("Time cannot be more than 23:59(HH:MM)");
        arg3.value=""; 
    }
}
function window_open(val,x,y) //used for calender without time component
{
//parameter val contains the name of the textbox
var newWindow;
var urlstring = '/ASRI/common/Calendar.jsp?requestSent='+val;
var urlstyle = 'height=200,width=280,toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=no,top='+x+',left='+y;
newWindow = window.open(urlstring,'Calendar',urlstyle);
}

function openCalender(val,x,y,z) //used for calender with time component 
{
	//parameter val contains the name of the textbox
	var newWindow;
        var urlstring = '/ASRI/reports/Calendar.jsp?requestSent='+val+'&fromToDate='+z+'&timeReq=1';//0007
	var urlstyle = 'height=200,width=280,toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=no,top='+x+',left='+y;
	newWindow = window.open(urlstring,'Calendar',urlstyle);
 }
 function openCalenderServerDt(val,x,y,z,timeReq) //used for calender with server dt & time component  
{
	//parameter val contains the name of the textbox
	//parameter x & y contains the coordinates 
	//parameter z (z=false & timeReq = 1-> time component with server time;z=true & timeReq = 1-> time component with 00:00;z=(false or true) & timeReq = 0 -> without time component)	
	var newWindow;
	var urlstring = "/ASRI/FrontServlet?requestType=AuthUserViewRH&actionVal=getServerDtTimeStamp&requestSent="+val+"&fromToDate="+z+"&timeReq="+timeReq;	
	var urlstyle = 'height=200,width=280,toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=no,top='+x+',left='+y;
	newWindow = window.open(urlstring,'Calendar',urlstyle);
 } 
function checkAlpha(arg1,arg2) //only alphabets A-Z and a-z
{
     var Names=eval("document.forms[0]."+arg1);
     var Names1=Names.value;
     if(Names1 != null && Names1 != "")
     {
       var reg=/^[a-zA-Z]+$/;
       if(Names1.search(reg)==-1)
       {
            alert("Please enter only alphabets in "+arg2);
            return 0;
       }
       return 1;
     }
}
function alphabetfunc(arg,arg2) // alphabets A-Z and a-z  .  and ()
{
    var textbox1 =  eval("document.forms[0]."+arg);
    var textval = textbox1.value;
    var ValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz. ()";
    var Char; 
    if(textval != '')
    {
        for (i = 0; i < textval.length ; i++) 
        { 
            Char = textval.charAt(i); 
            if (ValidChars.indexOf(Char) == -1) 
            {
                alert('Error in '+arg2+': Alphabetic input only ');
                textbox1.value='';
                return false;
            }
        }
    }
    return true;
}
function validateFile()
{
    event.returnValue=false;
}
function right(e) 
{
    if (navigator.appName == 'Netscape' && (e.which == 3 || e.which == 2))
        return false;
    else if (navigator.appName == 'Microsoft Internet Explorer' && (event.button == 2 || event.button == 3)) 
    {
        alert("Sorry, you do not have permission to right click");
        return false;
    }
    return true;
}
function fn_CheckTextAreaLen(arg1,ta,len)
{
    if(ta.value.length > len)
    {
        alert("Enter "+len+" characters only in "+arg1);
        window.event.keyCode =8;
        return;
    }
}
function func_maxlen(arg1,ta,len)
{
   if(ta.value.length > len)
    {
       alert("Please enter only "+ len +" characters in "+arg1);
       ta.value = ta.value.substring(0,len);
    }
}
function checkCR(evt) 
{
    evt = (evt) ? evt : window.event
	var charCode=(evt.which) ? evt.which : evt.keyCode
		
      if( charCode==13)
	{
		alert("This field does not accept enter key");
		window.event.returnValue = false;
  	}
	return true;
}
function eraser(arg)
{
    var selval=eval("document.forms[0]."+arg);
    selval.value="";
}
function trim(stringToTrim) 
{
    return stringToTrim.replace(/^\s+|\s+$/g,"");
}
function trim(str, chars) 
{
    return ltrim(rtrim(str, chars), chars);
}

function ltrim(str, chars) 
{
    chars = chars || "\\s";
    return str.replace(new RegExp("^[" + chars + "]+", "g"), "");
}

function rtrim(str, chars)
{
    chars = chars || "\\s";
    return str.replace(new RegExp("[" + chars + "]+$", "g"), "");
}
function checkSplChars(arg,arg2)
{
    var textbox1 =  eval("document.forms[0]."+arg);
    var textval = textbox1.value;
   var ValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.()`~!@#$%^&*_-+=[]{}|\<>/?0123456789, ";
   var IsNumber=true;
   var Char; 
   for (i = 0; i < textval.length && IsNumber == true; i++) 
   { 
           Char = textval.charAt(i); 
           if (ValidChars.indexOf(Char) == -1) 
	   {
		alert("Enter key , Junk Characters , \'  and \" are not allowed in "+arg2);
                textbox1.value='';
                textbox1.focus();
		return false;
	   }
   }
}
function specialCharactersDoesnotContain(cardNo, label) //for not accepting spl characters in WAP/CMCO card
{
  var iChars = "~`!@#$%^&*()+=-[]\\\';,.{}|\":<>?"; 
  if(label == "Case No")
  {
    iChars +="_";
  }
    for (var i = 0; i < cardNo.length; i++) 
    {     
      if (iChars.indexOf(cardNo.charAt(i)) != -1) 
      {
        alert (label + " should not have special characters");        
        return false;
      }
    }
    return true;
}
//Returns true if from date is less than to date otherwise false
function fnDoesSelectedDateLessThan31stMarch2007(FromDate,ToDate, label)
{
  var FromDateVal;
  var ToDateVal;

  var k = FromDate.indexOf("/");
  var t = FromDate.indexOf("/",3);     
  FromDateVal = FromDate.substr(k+1,t-k-1) +"/"+FromDate.substr(0,k)+"/"+FromDate.substr(t+1,FromDate.length);
      
  k = ToDate.indexOf("/");
  t = ToDate.indexOf("/",3);
  ToDateVal = ToDate.substr(k+1,t-k-1) +"/"+ToDate.substr(0,k)+"/"+ToDate.substr(t+1,ToDate.length);
	
  if (Date.parse(FromDateVal) > Date.parse(ToDateVal))
  {
    return false; 
  }
  else
  {
    alert(label + " should be more than 31st March 2007");
    return true;           
  }
}

//Returns true if from date is less than to date otherwise false
function fnDoesFirstDateLessThanSecDate(FromDt,ToDate, label1,label2)
{
  var FromDateVal;
  var ToDateVal;
  var FromDate = FromDt.value;
  var k = FromDate.indexOf("/");
  var t = FromDate.indexOf("/",3);     
  var hh=0;
  var mm=0;
  if(FromDate.length >= 16)
  {
    hh=FromDate.substr(FromDate.indexOf(" ")+1,FromDate.indexOf(":"));
    mm=FromDate.substr(FromDate.indexOf(":")+1,FromDate.indexOf(":",2));
  }
  FromDateVal = FromDate.substr(k+1,t-k-1) +"/"+FromDate.substr(0,k)+"/"+FromDate.substr(t+1,FromDate.length,hh,mm);
      
  k = ToDate.indexOf("/");
  t = ToDate.indexOf("/",3);
  hh=0;
  mm=0;
  if(ToDate.length >= 16)
  {
    hh=ToDate.substr(ToDate.indexOf(" ")+1,ToDate.indexOf(":"));
    mm=ToDate.substr(ToDate.indexOf(":")+1,ToDate.indexOf(":",2));
  }
  ToDateVal = ToDate.substr(k+1,t-k-1) +"/"+ToDate.substr(0,k)+"/"+ToDate.substr(t+1,ToDate.length,hh,mm);
	
  if (Date.parse(FromDateVal) < Date.parse(ToDateVal))
  {
    alert(label1+" cannot be less than "+label2+"("+ToDate+")");
    FromDt.value='';
    return false; 
  }
}

//Returns true if from date is greater than to date otherwise false
function fnDoesFirstDateMoreThanSecDate(FromDt,ToDate, label1,label2)
{
  var FromDateVal;
  var ToDateVal;
  var FromDate = FromDt.value;
  var k = FromDate.indexOf("/");
  var t = FromDate.indexOf("/",3);     
  var hh=0;
  var mm=0;
  if(FromDate.length >= 16)
  {
    hh=FromDate.substr(FromDate.indexOf(" ")+1,FromDate.indexOf(":"));
    mm=FromDate.substr(FromDate.indexOf(":")+1,FromDate.indexOf(":",2));
  }
  FromDateVal = FromDate.substr(k+1,t-k-1) +"/"+FromDate.substr(0,k)+"/"+FromDate.substr(t+1,FromDate.length,hh,mm);
      
  k = ToDate.indexOf("/");
  t = ToDate.indexOf("/",3);
  hh=0;
  mm=0;
  if(ToDate.length >= 16)
  {
    hh=ToDate.substr(ToDate.indexOf(" ")+1,ToDate.indexOf(":"));
    mm=ToDate.substr(ToDate.indexOf(":")+1,ToDate.indexOf(":",2));
  }
  ToDateVal = ToDate.substr(k+1,t-k-1) +"/"+ToDate.substr(0,k)+"/"+ToDate.substr(t+1,ToDate.length,hh,mm);
	
  if (Date.parse(FromDateVal) < Date.parse(ToDateVal))
  {
    alert(label1+" cannot be more than "+label2);
    FromDt.value='';
    return false; 
  }
}

//start AJAX CALLS

function callchangeDistrict(arg1,arg2) 
{
	if(document.forms[0].telephonic)
	{
		if(arg1 == 'ddl_District')
		{
		   var Hospital = document.forms[0].HospitalName.value;
		   if(Hospital != null && Hospital != '-1')	   
		   {    				
				document.forms[0].HospitalName.value='-1';
				
				var dd1=document.forms[0].DiseaseMainCat;
				for (var q=dd1.options.length;q>=1;q--)
				{
					  dd1.options[q]=null;
				} 
				var dd2=document.forms[0].DiseaseSubCat;
				for (var q=dd2.options.length;q>=1;q--)
				{
					  dd2.options[q]=null;
				} 
				var dd3=document.forms[0].Surgery;
				for (var q=dd3.options.length;q>=1;q--)
				{
					  dd3.options[q]=null;
				} 
		   }
		}
	}
	if(document.forms[0].btn_OPEntry)
       document.forms[0].btn_OPEntry.disabled=true;
  if(document.forms[0].cmcoPg)
  {
	var val ="C";
	var selected_option='-1';
     if(document.forms[0].CMCOSelect) 
     { 
        var selVal=document.forms[0].CMCOSelect.selectedIndex;
        selected_option = document.forms[0].CMCOSelect.options[selVal].value;  
        cardno='';
     }
    if(!(val=='C' && (selected_option=='N' || selected_option=='-1')))
    {
      cardno=fn_chkCMCOCardNo();
    }
  }
 if(document.forms[0].elements['CCType'] != null && !document.forms[0].cmcoPg)
 { 
  var radio = document.forms[0].elements['CCType']; 
     for(var i=0;i<radio.length;i++)
    {
        if(radio[i].checked == true)
        {
            var val = radio[i].value;
        }
    }    
     var selected_option='-1';
     if(document.forms[0].CMCOSelect) 
     { 
        var selVal=document.forms[0].CMCOSelect.selectedIndex;
        selected_option = document.forms[0].CMCOSelect.options[selVal].value;  
        cardno='';
     }
    if(!(val=='C' && (selected_option=='N' || selected_option=='-1')))
    {
      cardno=fn_chkWhiteCardNo();
    }
  }
  else
    cardno=fn_chkWhiteCardNo();
    if(cardno == undefined )   
      cardno="";
var Selected=document.getElementById(arg1);
  var selectedvalue=Selected.options[Selected.selectedIndex].value;
  var ltemp= Selected.options[Selected.selectedIndex].text;
	if (document.implementation && document.implementation.createDocument)
	{
		xmlDoc = document.implementation.createDocument("", "", null);
		xmlDoc.onload = populateDropDownMandal;
	}
	else if (window.ActiveXObject)
	{
		xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
		xmlDoc.onreadystatechange = function () 
		{  if (xmlDoc.readyState == 4) 
			populateDropDownMandal(arg2)
		};
 	}
	else
	{
		alert('Your browser can\'t handle this script');
		return;
	}
  var url =  "/ASRI/FillData?actionVal=CmbDetailsPatientIPOP&pageName=InPatient&cmbhdr=LH7&langid=en_US&cmbparentid="+Selected.value+"&cmbhdrid=&cardNo="+cardno;
 	xmlDoc.load(url);
  
}
function populateDropDownMandal(arg2)
{
	
	var browser = 'ie';
	var nameIndex = 0;
	var valueIndex = 1;
	if (document.implementation && document.implementation.createDocument)
	{
		browser = 'firefox';
	  var nameIndex = 1;
	  var valueIndex = 3;
	}
    var dd3;
	var dd3=document.getElementById(arg2);
//    dd3= document.forms[0].ddl_Mandal;
    
  //empty control
  for (var q=dd3.options.length;q>=0;q--)
  {
      dd3.options[q]=null;
  } 

	var x = xmlDoc.getElementsByTagName('item');

	for (j=0;j<x[0].childNodes.length;j++)
	{      
		if (x[0].childNodes[j].nodeType != 1) continue;
		var theData = document.createTextNode(x[0].childNodes[j].nodeName);
	}
	
	for (i=0;i<x.length;i++)
	{
	  var name = '';
	  var value = '';
		for (j=0;j<x[i].childNodes.length;j++)
		{
			if (x[i].childNodes[j].nodeType != 1) continue;
			var theData = document.createTextNode(x[i].childNodes[j].firstChild.nodeValue);
			if (j==nameIndex) name = theData.nodeValue;
			if (j==valueIndex) value = theData.nodeValue;		
		}

    dd3.options[i] = new Option(name, value);
	}
	if(document.forms[0].btn_OPEntry)
	  document.forms[0].btn_OPEntry.disabled=false;
} 
function callchangePHCDistrict()
  {
	if(document.forms[0].btn_OPEntry)
	   document.forms[0].btn_OPEntry.disabled=true;
    if(document.forms[0].elements['CCType'] != null)
    {
    var radio = document.forms[0].elements['CCType']; 
     for(var i=0;i<radio.length;i++)
    {
        if(radio[i].checked == true)
        {
            var val = radio[i].value;
        }
    }
   
         var selected_option='-1';
     if(document.forms[0].CMCOSelect) 
     { 
        var selVal=document.forms[0].CMCOSelect.selectedIndex;
        selected_option = document.forms[0].CMCOSelect.options[selVal].value;  
        cardno='';
     }
    if(!(val=='C' && (selected_option=='N' || selected_option=='-1')))
    {
      cardno=fn_chkWhiteCardNo();
    }
    }
    else
    cardno=fn_chkWhiteCardNo();
   if(cardno == undefined )   
   cardno="";
  var Selected=document.forms[0].txt_District_phc;
   
 
  var selectedvalue=Selected.options[Selected.selectedIndex].value;
	if (document.implementation && document.implementation.createDocument)
	{
		xmlDoc = document.implementation.createDocument("", "", null);
		xmlDoc.onload = populateDropDownPHCMandal;
	}
	else if (window.ActiveXObject)
	{
		xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
		xmlDoc.onreadystatechange = function () 
		{  if (xmlDoc.readyState == 4) 
			populateDropDownPHCMandal()
		};
 	}
	else
	{
		alert('Your browser can\'t handle this script');
		return;
	}
  var url =  "/ASRI/FillData?actionVal=CmbDetailsPatientIPOP&pageName=InPatient&cmbhdr=LH7&langid=en_US&cmbparentid="+Selected.value+"&cmbhdrid=&cardNo="+cardno+ "&FromPHC=FromPHC";
 	xmlDoc.load(url);
  }

  function populateDropDownPHCMandal()
{
	
	var browser = 'ie';
	var nameIndex = 0;
	var valueIndex = 1;
	if (document.implementation && document.implementation.createDocument)
	{
		browser = 'firefox';
	  var nameIndex = 1;
	  var valueIndex = 3;
	}
    var dd3;
    dd3= document.forms[0].txt_Mandal_phc;
    
  //empty control
  for (var q=dd3.options.length;q>=0;q--)
  {
      dd3.options[q]=null;
  } 

	var x = xmlDoc.getElementsByTagName('item');

	for (j=0;j<x[0].childNodes.length;j++)
	{      
		if (x[0].childNodes[j].nodeType != 1) continue;
		var theData = document.createTextNode(x[0].childNodes[j].nodeName);
	}
	
	for (i=0;i<x.length;i++)
	{
	  var name = '';
	  var value = '';
		for (j=0;j<x[i].childNodes.length;j++)
		{
			if (x[i].childNodes[j].nodeType != 1) continue;
			var theData = document.createTextNode(x[i].childNodes[j].firstChild.nodeValue);
			if (j==nameIndex) name = theData.nodeValue;
			if (j==valueIndex) value = theData.nodeValue;		
		}

    dd3.options[i] = new Option(name, value);
	}
	if(document.forms[0].btn_OPEntry)
	  document.forms[0].btn_OPEntry.disabled=false;
}  
function callchangeHCDistrict()
{
  if(document.forms[0].btn_OPEntry)
    document.forms[0].btn_OPEntry.disabled=true;
  if(document.forms[0].elements['CCType'] != null)
 { 
  var radio = document.forms[0].elements['CCType']; 
     for(var i=0;i<radio.length;i++)
    {
        if(radio[i].checked == true)
        {
            var val = radio[i].value;
        }
    }    
     var selected_option='-1';
     if(document.forms[0].CMCOSelect) 
     { 
        var selVal=document.forms[0].CMCOSelect.selectedIndex;
        selected_option = document.forms[0].CMCOSelect.options[selVal].value;  
        cardno='';
     }
    if(!(val=='C' && (selected_option=='N' || selected_option=='-1')))
    {
      cardno=fn_chkWhiteCardNo();
    }
  }
  else
   cardno=fn_chkWhiteCardNo();
   if(cardno == undefined )   
   cardno="";
  var Selected=document.forms[0].txt_District_mc;
   
 
  var selectedvalue=Selected.options[Selected.selectedIndex].value;
	if (document.implementation && document.implementation.createDocument)
	{
		xmlDoc = document.implementation.createDocument("", "", null);
		xmlDoc.onload = populateDropDownHCMandal;
	}
	else if (window.ActiveXObject)
	{
		xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
		xmlDoc.onreadystatechange = function () 
		{  if (xmlDoc.readyState == 4) 
			populateDropDownHCMandal()
		};
 	}
	else
	{
		alert('Your browser can\'t handle this script');
		return;
	}
  var url =  "/ASRI/FillData?actionVal=CmbDetailsPatientIPOP&pageName=InPatient&cmbhdr=LH7&langid=en_US&cmbparentid="+Selected.value+"&cmbhdrid=&cardNo="+cardno+ "&FromPHC=FromPHC";
 	xmlDoc.load(url);
  }

  function populateDropDownHCMandal()
{
	
	var browser = 'ie';
	var nameIndex = 0;
	var valueIndex = 1;
	if (document.implementation && document.implementation.createDocument)
	{
		browser = 'firefox';
	  var nameIndex = 1;
	  var valueIndex = 3;
	}
    var dd3;
    dd3= document.forms[0].txt_Mandal_mc;
    
  //empty control
  for (var q=dd3.options.length;q>=0;q--)
  {
      dd3.options[q]=null;
  } 

	var x = xmlDoc.getElementsByTagName('item');

	for (j=0;j<x[0].childNodes.length;j++)
	{      
		if (x[0].childNodes[j].nodeType != 1) continue;
		var theData = document.createTextNode(x[0].childNodes[j].nodeName);
	}
	
	for (i=0;i<x.length;i++)
	{
	  var name = '';
	  var value = '';
		for (j=0;j<x[i].childNodes.length;j++)
		{
			if (x[i].childNodes[j].nodeType != 1) continue;
			var theData = document.createTextNode(x[i].childNodes[j].firstChild.nodeValue);
			if (j==nameIndex) name = theData.nodeValue;
			if (j==valueIndex) value = theData.nodeValue;		
		}

    dd3.options[i] = new Option(name, value);
	}
	if(document.forms[0].btn_OPEntry)
	    document.forms[0].btn_OPEntry.disabled=false;
} 
function callChangeVillage(arg1,arg2)
  {
  if(document.forms[0].btn_OPEntry)
     document.forms[0].btn_OPEntry.disabled=true;
  if(document.forms[0].cmcoPg)
  {
	var val ="C";
	var selected_option='-1';
     if(document.forms[0].CMCOSelect) 
     { 
        var selVal=document.forms[0].CMCOSelect.selectedIndex;
        selected_option = document.forms[0].CMCOSelect.options[selVal].value;  
        cardno='';
     }
    if(!(val=='C' && (selected_option=='N' || selected_option=='-1')))
    {
      cardno=fn_chkCMCOCardNo();
    }
  }
  if(document.forms[0].elements['CCType'] != null && !document.forms[0].cmcoPg)
 { 
  var radio = document.forms[0].elements['CCType']; 
     for(var i=0;i<radio.length;i++)
    {
        if(radio[i].checked == true)
        {
            var val = radio[i].value;
        }
    }    
     var selected_option='-1';
     if(document.forms[0].CMCOSelect) 
     { 
        var selVal=document.forms[0].CMCOSelect.selectedIndex;
        selected_option = document.forms[0].CMCOSelect.options[selVal].value;  
        cardno='';
     }
    if(!(val=='C' && (selected_option=='N' || selected_option=='-1')))
    {
      cardno=fn_chkWhiteCardNo();
    }
  }
  else
 cardno=fn_chkWhiteCardNo();
   if(cardno == undefined )   
   cardno="";
 // var Selected=document.forms[0].ddl_Village;
var Selected=document.getElementById(arg1);
  var selectedvalue=Selected.options[Selected.selectedIndex].value;
	if (document.implementation && document.implementation.createDocument)
	{
		xmlDoc = document.implementation.createDocument("", "", null);
		xmlDoc.onload = populateDropDownHamlet;
	}
	else if (window.ActiveXObject)
	{
		xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
		xmlDoc.onreadystatechange = function () 
		{  if (xmlDoc.readyState == 4) 
			populateDropDownHamlet(arg2)
		};
 	}
	else
	{
		alert('Your browser can\'t handle this script');
		return;
	}
  var url =  "/ASRI/FillData?actionVal=CmbDetailsPatientIPOP&pageName=InPatient&cmbhdr=LH9&cmbparentid="+Selected.value+"&cmbhdrid=&cardNo="+cardno+"&langid=en_US";
 	xmlDoc.load(url);  
  }

function populateDropDownHamlet(arg2)
{
	
	var browser = 'ie';
	var nameIndex = 0;
	var valueIndex = 1;
	if (document.implementation && document.implementation.createDocument)
	{
		browser = 'firefox';
	  var nameIndex = 1;
	  var valueIndex = 3;
	}
    var dd3;
    //dd3= document.forms[0].ddl_Hamlet;
	  dd3=document.getElementById(arg2);
    
  //empty control
  for (var q=dd3.options.length;q>=0;q--)
  {
      dd3.options[q]=null;
  } 

	var x = xmlDoc.getElementsByTagName('item');

	for (j=0;j<x[0].childNodes.length;j++)
	{      
		if (x[0].childNodes[j].nodeType != 1) continue;
		var theData = document.createTextNode(x[0].childNodes[j].nodeName);
	}
	
	for (i=0;i<x.length;i++)
	{
	  var name = '';
	  var value = '';
		for (j=0;j<x[i].childNodes.length;j++)
		{
			if (x[i].childNodes[j].nodeType != 1) continue;
			var theData = document.createTextNode(x[i].childNodes[j].firstChild.nodeValue);
			if (j==nameIndex) name = theData.nodeValue;
			if (j==valueIndex) value = theData.nodeValue;		
		}

    dd3.options[i] = new Option(name, value);
	}
	if(document.forms[0].btn_OPEntry)
	   document.forms[0].btn_OPEntry.disabled=false;
}  
function callChangeMandal(arg1,arg2,arg3)
  { 
	  if(document.forms[0].btn_OPEntry)
	    document.forms[0].btn_OPEntry.disabled=true;
  if(document.forms[0].cmcoPg)
  {
	var val ="C";
	var selected_option='-1';
     if(document.forms[0].CMCOSelect) 
     { 
        var selVal=document.forms[0].CMCOSelect.selectedIndex;
        selected_option = document.forms[0].CMCOSelect.options[selVal].value;  
        cardno='';
     }
    if(!(val=='C' && (selected_option=='N' || selected_option=='-1')))
    {
      cardno=fn_chkCMCOCardNo();
    }
  }
  if(document.forms[0].elements['CCType'] != null && !document.forms[0].cmcoPg)
 { 
  var radio = document.forms[0].elements['CCType']; 
     for(var i=0;i<radio.length;i++)
    {
        if(radio[i].checked == true)
        {
            var val = radio[i].value;
        }
    }    
     var selected_option='-1';
     if(document.forms[0].CMCOSelect) 
     { 
        var selVal=document.forms[0].CMCOSelect.selectedIndex;
        selected_option = document.forms[0].CMCOSelect.options[selVal].value;  
        cardno='';
     }
    if(!(val=='C' && (selected_option=='N' || selected_option=='-1')))
    {
      cardno=fn_chkWhiteCardNo();
    }
  }
  else
  cardno=fn_chkWhiteCardNo();
  if(cardno == undefined )   
   cardno="";
//var Selected=document.forms[0].ddl_Mandal;
var Selected=document.getElementById(arg1);
  var selectedvalue=Selected.options[Selected.selectedIndex].value;
	if (document.implementation && document.implementation.createDocument)
	{
		xmlDoc = document.implementation.createDocument("", "", null);
    xmlDoc1 = document.implementation.createDocument("", "", null);
		xmlDoc.onload = populateDropDownVillage;
                if(arg3!='' && arg3!='Tel')
    xmlDoc1.onload = populateDropDownPHC;
	}
	else if (window.ActiveXObject)
	{
		xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
    xmlDoc1 = new ActiveXObject("Microsoft.XMLDOM");
		xmlDoc.onreadystatechange = function () 
		{  if (xmlDoc.readyState == 4) 
			populateDropDownVillage(arg2)
		};
    xmlDoc1.onreadystatechange = function () 
		{  if (xmlDoc1.readyState == 4) 
		if(arg3!=''  && arg3!='Tel')
		{
			populateDropDownPHC(arg3)
			}
		};
 	}
	else
	{
		alert('Your browser can\'t handle this script');
		return;
	}   
  var url =  "/ASRI/FillData?actionVal=CmbDetailsPatientIPOP&pageName=InPatient&cmbhdr=LH8&cmbparentid="+Selected.value+"&cmbhdrid=&cardNo="+cardno+"&langid=en_US";
 	xmlDoc.load(url);  
  var url =  "/ASRI/FillData?actionVal=CmbDetailsPatientIPOP&pageName=InPatient&cmbhdr=LH8PHC&cmbparentid="+Selected.value+"&cmbhdrid=&cardNo="+cardno+"&langid=en_US";
 	xmlDoc1.load(url);
  }

  function populateDropDownPHC(arg3)
{
	
	var browser = 'ie';
	var nameIndex = 0;
	var valueIndex = 1;
	if (document.implementation && document.implementation.createDocument)
	{
		browser = 'firefox';
	  var nameIndex = 1;
	  var valueIndex = 3;
	}
    var dd3;
    
  // for PHC List
  var dd3;
//    dd3= document.forms[0].ddl_phcrole;
	  var dd3=document.getElementById(arg3);
    
  //empty control
  for (var q=dd3.options.length;q>=0;q--)
  {
      dd3.options[q]=null;
  } 

	var x = xmlDoc1.getElementsByTagName('item');

	for (j=0;j<x[0].childNodes.length;j++)
	{
      
		if (x[0].childNodes[j].nodeType != 1) continue;
		var theData = document.createTextNode(x[0].childNodes[j].nodeName);
	}
	
	for (i=0;i<x.length;i++)
	{
	  var name = '';
	  var value = '';
		for (j=0;j<x[i].childNodes.length;j++)
		{
			if (x[i].childNodes[j].nodeType != 1) continue;
			var theData = document.createTextNode(x[i].childNodes[j].firstChild.nodeValue);
			if (j==nameIndex) name = theData.nodeValue;
			if (j==valueIndex) value = theData.nodeValue;		
		}

    dd3.options[i] = new Option(name, value);
	}
	if(document.forms[0].btn_OPEntry)
	   document.forms[0].btn_OPEntry.disabled=false;
}  
function populateDropDownVillage(arg2)
{
	
	var browser = 'ie';
	var nameIndex = 0;
	var valueIndex = 1;
	if (document.implementation && document.implementation.createDocument)
	{
		browser = 'firefox';
	  var nameIndex = 1;
	  var valueIndex = 3;
	}
    var dd3;
//    dd3= document.forms[0].ddl_Village;
var dd3=document.getElementById(arg2);
    
  //empty control
  for (var q=dd3.options.length;q>=0;q--)
  {
      dd3.options[q]=null;
  } 

	var x = xmlDoc.getElementsByTagName('item');

	for (j=0;j<x[0].childNodes.length;j++)
	{      
		if (x[0].childNodes[j].nodeType != 1) continue;
		var theData = document.createTextNode(x[0].childNodes[j].nodeName);
	}
	
	for (i=0;i<x.length;i++)
	{
	  var name = '';
	  var value = '';
		for (j=0;j<x[i].childNodes.length;j++)
		{
			if (x[i].childNodes[j].nodeType != 1) continue;
			var theData = document.createTextNode(x[i].childNodes[j].firstChild.nodeValue);
			if (j==nameIndex) name = theData.nodeValue;
			if (j==valueIndex) value = theData.nodeValue;		
		}

    dd3.options[i] = new Option(name, value);
	}
	if(document.forms[0].btn_OPEntry)
	   document.forms[0].btn_OPEntry.disabled=false;
}  
function callChangePHCMandal()
  {
	  if(document.forms[0].btn_OPEntry)
	     document.forms[0].btn_OPEntry.disabled=true;
  if(document.forms[0].elements['CCType'] != null)
 { 
  var radio = document.forms[0].elements['CCType']; 
     for(var i=0;i<radio.length;i++)
    {
        if(radio[i].checked == true)
        {
            var val = radio[i].value;
        }
    }    
     var selected_option='-1';
     if(document.forms[0].CMCOSelect) 
     { 
        var selVal=document.forms[0].CMCOSelect.selectedIndex;
        selected_option = document.forms[0].CMCOSelect.options[selVal].value;  
        cardno='';
     }
    if(!(val=='C' && (selected_option=='N' || selected_option=='-1')))
    {
      cardno=fn_chkWhiteCardNo();
    }
  }
  else
cardno=fn_chkWhiteCardNo();
  if(cardno == undefined )   
   cardno="";
var Selected=document.forms[0].txt_Mandal_phc;
   
 
  var selectedvalue=Selected.options[Selected.selectedIndex].value;
	if (document.implementation && document.implementation.createDocument)
	{
		xmlDoc = document.implementation.createDocument("", "", null);
		xmlDoc.onload = populateDropDownPHCPHC;
	}
	else if (window.ActiveXObject)
	{
		xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
		xmlDoc.onreadystatechange = function () 
		{  if (xmlDoc.readyState == 4) 
			populateDropDownPHCPHC()
		};
 	}
	else
	{
		alert('Your browser can\'t handle this script');
		return;
	}   
  var url =  "/ASRI/FillData?actionVal=CmbDetailsPatientIPOP&pageName=InPatient&cmbhdr=LH8PHC&cmbparentid="+Selected.value+"&cmbhdrid=&cardNo="+cardno+"&langid=en_US";
 	xmlDoc.load(url);    
  }

 function populateDropDownPHCPHC()
{
	
	var browser = 'ie';
	var nameIndex = 0;
	var valueIndex = 1;
	if (document.implementation && document.implementation.createDocument)
	{
		browser = 'firefox';
	  var nameIndex = 1;
	  var valueIndex = 3;
	}
    var dd3;
    
  // for PHC List
  var dd3;
    dd3= document.forms[0].txt_PHC_phc;
    
  //empty control
  for (var q=dd3.options.length;q>=0;q--)
  {
      dd3.options[q]=null;
  } 

	var x = xmlDoc.getElementsByTagName('item');

	for (j=0;j<x[0].childNodes.length;j++)
	{
      
		if (x[0].childNodes[j].nodeType != 1) continue;
		var theData = document.createTextNode(x[0].childNodes[j].nodeName);
	}
	
	for (i=0;i<x.length;i++)
	{
	  var name = '';
	  var value = '';
		for (j=0;j<x[i].childNodes.length;j++)
		{
			if (x[i].childNodes[j].nodeType != 1) continue;
			var theData = document.createTextNode(x[i].childNodes[j].firstChild.nodeValue);
			if (j==nameIndex) name = theData.nodeValue;
			if (j==valueIndex) value = theData.nodeValue;		
		}

    dd3.options[i] = new Option(name, value);
	}
	if(document.forms[0].btn_OPEntry)
	   document.forms[0].btn_OPEntry.disabled=false;
}  

function callChangeHCMandal()
  {
	  if(document.forms[0].btn_OPEntry)
	     document.forms[0].btn_OPEntry.disabled=true;
  if(document.forms[0].elements['CCType'] != null)
 { 
  var radio = document.forms[0].elements['CCType']; 
     for(var i=0;i<radio.length;i++)
    {
        if(radio[i].checked == true)
        {
            var val = radio[i].value;
        }
    }    
     var selected_option='-1';
     if(document.forms[0].CMCOSelect) 
     { 
        var selVal=document.forms[0].CMCOSelect.selectedIndex;
        selected_option = document.forms[0].CMCOSelect.options[selVal].value;  
        cardno='';
     }
    if(!(val=='C' && (selected_option=='N' || selected_option=='-1')))
    {
      cardno=fn_chkWhiteCardNo();
    }
  }
  else
cardno=fn_chkWhiteCardNo();
  if(cardno == undefined )   
   cardno="";
var Selected=document.forms[0].txt_Mandal_mc;
   
 
  var selectedvalue=Selected.options[Selected.selectedIndex].value;
	if (document.implementation && document.implementation.createDocument)
	{
		xmlDoc = document.implementation.createDocument("", "", null);
		xmlDoc.onload = populateDropDownHCHC;
	}
	else if (window.ActiveXObject)
	{
		xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
		xmlDoc.onreadystatechange = function () 
		{  if (xmlDoc.readyState == 4) 
			populateDropDownHCHC()
		};
 	}
	else
	{
		alert('Your browser can\'t handle this script');
		return;
	}   
  var url =  "/ASRI/FillData?actionVal=CmbDetailsPatientIPOP&pageName=InPatient&cmbhdr=LH8HC&cmbparentid="+Selected.value+"&cmbhdrid=&cardNo="+cardno+"&langid=en_US";
 	xmlDoc.load(url);    
  }
  
    function callChangeMedCamp()
  {
  
    if(document.forms[0].btn_OPEntry)
	     document.forms[0].btn_OPEntry.disabled=true;
  if(document.forms[0].elements['CCType'] != null)
 { 
  var radio = document.forms[0].elements['CCType']; 
     for(var i=0;i<radio.length;i++)
    {
        if(radio[i].checked == true)
        {
            var val = radio[i].value;
        }
    }    
     var selected_option='-1';
     if(document.forms[0].CMCOSelect) 
     { 
        var selVal=document.forms[0].CMCOSelect.selectedIndex;
        selected_option = document.forms[0].CMCOSelect.options[selVal].value;  
        cardno='';
     }
    if(!(val=='C' && (selected_option=='N' || selected_option=='-1')))
    {
      cardno=fn_chkWhiteCardNo();
    }
  }
  else
cardno=fn_chkWhiteCardNo();
  if(cardno == undefined )   
   cardno="";
var Selected=document.forms[0].txt_PHC_mc;
   
 
  var selectedvalue=Selected.options[Selected.selectedIndex].value;
   var selectedName=Selected.options[Selected.selectedIndex].text;
  var opdate=getHCDate(selectedName);
	opdate=trim(opdate);
  document.forms[0].txt_DtOP.value=opdate;
	if (document.implementation && document.implementation.createDocument)
	{
		xmlDoc = document.implementation.createDocument("", "", null);
		xmlDoc.onload = populateDropDownScHosp;
	}
	else if (window.ActiveXObject)
	{
		xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
		xmlDoc.onreadystatechange = function () 
		{  if (xmlDoc.readyState == 4) 
			populateDropDownScHosp()
		};
 	}
	else
	{
		alert('Your browser can\'t handle this script');
		return;
	}  
  var url =  "/ASRI/FillData?actionVal=CmbDetails&pageName=ScHosp&cmbparentid="+Selected.value;
   	xmlDoc.load(url);    
  }
  
  function populateDropDownScHosp()
{
	
	var browser = 'ie';
	var nameIndex = 0;
	var valueIndex = 1;
	if (document.implementation && document.implementation.createDocument)
	{
		browser = 'firefox';
	  var nameIndex = 1;
	  var valueIndex = 3;
	}
    var dd3;
    
  
  //var dd3;
    dd3= document.forms[0].screenedHospital;
    
  //empty control
  for (var q=dd3.options.length;q>=0;q--)
  {
      dd3.options[q]=null;
  } 

	var x = xmlDoc.getElementsByTagName('item');
	for (j=0;j<x[0].childNodes.length;j++)
	{
      
		if (x[0].childNodes[j].nodeType != 1) continue;
		var theData = document.createTextNode(x[0].childNodes[j].nodeName);
	}
	
	for (i=0;i<x.length;i++)
	{
	  var name = '';
	  var value = '';
		for (j=0;j<x[i].childNodes.length;j++)
		{
			if (x[i].childNodes[j].nodeType != 1) continue;
			var theData = document.createTextNode(x[i].childNodes[j].firstChild.nodeValue);
			if (j==nameIndex) name = theData.nodeValue;
			if (j==valueIndex) value = theData.nodeValue;		
		}

    dd3.options[i] = new Option(name, value);
	}
	if(document.forms[0].btn_OPEntry)
	   document.forms[0].btn_OPEntry.disabled=false;
}  
  
 function populateDropDownHCHC()
{
	
	var browser = 'ie';
	var nameIndex = 0;
	var valueIndex = 1;
	if (document.implementation && document.implementation.createDocument)
	{
		browser = 'firefox';
	  var nameIndex = 1;
	  var valueIndex = 3;
	}
    var dd3;
    
  // for PHC List
  var dd3;
    dd3= document.forms[0].txt_PHC_mc;
    
  //empty control
  for (var q=dd3.options.length;q>=0;q--)
  {
      dd3.options[q]=null;
  } 

	var x = xmlDoc.getElementsByTagName('item');

	for (j=0;j<x[0].childNodes.length;j++)
	{
      
		if (x[0].childNodes[j].nodeType != 1) continue;
		var theData = document.createTextNode(x[0].childNodes[j].nodeName);
	}
	
	for (i=0;i<x.length;i++)
	{
	  var name = '';
	  var value = '';
		for (j=0;j<x[i].childNodes.length;j++)
		{
			if (x[i].childNodes[j].nodeType != 1) continue;
			var theData = document.createTextNode(x[i].childNodes[j].firstChild.nodeValue);
			if (j==nameIndex) name = theData.nodeValue;
			if (j==valueIndex) value = theData.nodeValue;		
		}

    dd3.options[i] = new Option(name, value);
	}
	if(document.forms[0].btn_OPEntry)
	   document.forms[0].btn_OPEntry.disabled=false;
}  
var selectedList;
var availableList;
function createObjects()
{
        availableList = document.getElementById("id_listtests");
        selectedList = document.getElementById("id_listtests1");
}

function calculatelen()
{		
	if(document.forms[0].moreTestsSele.value=="false"){
	var OPDateRow = document.getElementById('idOPDateRow');    
    var NumberRow = document.getElementById('idNumberRow');
    var OPLabel = document.getElementById('idOPLabel');
    var IPLabel = document.getElementById('idIPLabel');
    var FinalOpinion = document.getElementById('FinalOpinionRow');
	if (document.getElementById("id_listtests1").length == 0) 
	{
	  document.getElementById('buttonsdisplay').style.display="";
	  document.forms[0].DecideIPIP[0].checked=true;      
      NumberRow.style.display="";   
      OPDateRow.style.display="";
      OPLabel.style.display="";
      IPLabel.style.display="none";
      FinalOpinion.style.display="";
      var sysdate = new Date();
      var displayDate = sysdate.getDate() + "/" + (sysdate.getMonth() + 1) + "/" + sysdate.getYear();
      document.forms[0].txtOPDate.value=displayDate;
	}
	else
	{
		document.forms[0].DecideIPIP[0].checked=false;
		document.forms[0].PatientTypeCheck.value="N";
		document.getElementById('buttonsdisplay').style.display="none";
		NumberRow.style.display="none";   
	    OPDateRow.style.display="none";
	    OPLabel.style.display="none";
	    IPLabel.style.display="none";
	    FinalOpinion.style.display="none";
	    document.forms[0].txtOPDate.value="";
		document.forms[0].txt_Opinion.value="";
		document.forms[0].txt_PatNo.value="";
	}
	}
}      
function addTestDone()
{  
        createObjects();
        if ((availableList != null) && (selectedList != null)) 
        { 
            if(availableList.length < 1) 
             {
                alert('There are no items in the source ListBox');
                return false;
             }
            if(availableList.options.selectedIndex == -1) // when no Item is selected the index will be -1
             {
                alert('Please select an Item to move');
                return false;
              }
            while (availableList.options.selectedIndex >= 0 ) 
            { 
                   var newOption = new Option(); // Create a new instance of ListItem 
                   newOption.text = availableList.options[availableList.options.selectedIndex].text; 
                   newOption.value = availableList.options[availableList.options.selectedIndex].value; 
                   selectedList.options[selectedList.length] = newOption; //Append the item in Target Listbox
                   availableList.remove(availableList.options.selectedIndex); //Remove the item from Source Listbox 
            } 
            var len= selectedList.options.length;
            var sum=0;
            for (var loop=0; loop<len; loop++)
             {
             var arr=selectedList.options[loop].value;
             
             var array=arr.split(":");
             
             sum=sum + eval(array[1]);
             }
             document.getElementById("id_txtCostEstimate").value=sum;
              selectedList.options[0].selected=true;            
			  calculatelen();
        }
    return false; 
}    
function delTestDone()
{  
    createObjects();    
    if ((availableList != null) && (selectedList != null)) 
        { 
            if(selectedList.length < 1) 
             {
                alert('There are no items in the source ListBox');
                return false;
             }
            if(selectedList.options.selectedIndex == -1) // when no Item is selected the index will be -1
             {
                alert('Please select an Item to move');
                return false;
              }
            while ( selectedList.options.selectedIndex >= 0 ) 
            { 
                   var newOption = new Option(); // Create a new instance of ListItem 
                   newOption.text = selectedList.options[selectedList.options.selectedIndex].text; 
                   newOption.value = selectedList.options[selectedList.options.selectedIndex].value; 
                   availableList.options[availableList.length] = newOption; //Append the item in Target Listbox
                   selectedList.remove(selectedList.options.selectedIndex); //Remove the item from Source Listbox 
            } 
             var len= selectedList.options.length;
            var sum=0;
            for (var loop=0; loop<len; loop++)
             {
             var arr=selectedList.options[loop].value;
             
             var array=arr.split(":");
             
             sum=sum + eval(array[1]);
             }
     document.getElementById("id_txtCostEstimate").value=sum;
            if (selectedList.length >0 )
           {
             selectedList.options[0].selected=true;
          }  
		  calculatelen();
        }
    return false; 
}
var isNN = (navigator.appName.indexOf("Netscape")!=-1);
function autoTab(input,len, e) 
{
  if(!((input.name == 'WCNo15' && (document.forms[0].elements['chkHead'].checked ==true)) || (input.name == 'WCNo17' && document.getElementById('id_btnShow').disabled==true)))//016
  {
    var keyCode = (isNN) ? e.which : e.keyCode; 
    
    
     if( keyCode== 32 )
     {
       input.value='';
       return 0;
    }

    var filter = (isNN) ? [0,8,9] : [0,8,9,16,17,18,37,38,39,40,46];
    if(input.value.length >= len && !containsElement(filter,keyCode)) 
     {
       input.value = input.value.slice(0, len);
       input.form[(getIndex(input)+1) % input.form.length].focus();
     }
	  function containsElement(arr, ele) 
	  {
	 
		   var found = false, index = 0;
		while(!found && index < arr.length)
		  if(arr[index] == ele)
			 found = true;
			  else
			 index++;
		   return found;
	 }
	  function getIndex(input) 
	  {
		   var index = -1, i = 0, found = false;
			while (i < input.form.length && index == -1)
					if (input.form[i] == input)index = i;
					  else i++; 
			return index;
	 }
  }
 return true;
}
function fn_chkWhiteCardNo()
{

  var resWhite="";
  var resColNo ="";
  Card.innerHTML = "" ;   
  var patUpdate = "";
  var dispCard="";
  var dispcardlen="";
  if(document.getElementById("UpdPatDtls"))
  {
      patUpdate = document.getElementById("UpdPatDtls").value;
  }
  var radio = "";
  if(patUpdate == "Yes")      
  {
    radio = "1";
    }
  else
  {
    radio = document.forms[0].elements['CCType'];
    }
  for(var i=0;i<radio.length;i++)
  {
          Card.innerHTML = "" ;
          var val="";
          if(patUpdate == "Yes") 
             val=document.forms[0].CardType.value;
          else if(radio[i].checked == true)
             val = radio[i].value;             
          if(val=='J')
          {
            dispCard="Journalist Card No.";
            dispcardlen="16";
            dispcardfulllen="19";
          }
          else if(val=='W')
          {
            dispCard="White Card No.";
            dispcardlen="15";
            dispcardfulllen="18";
          }
          else if(val=='T' || val=='R')
          {
            dispCard="Temporary/Rachabanda HouseHold Card No.";
            dispcardlen="15";
            dispcardfulllen="18";
          }
                if(val == 'C')
                {                
                        resColNo = resColNo + (document.forms[0].elements['CCNo1'].value);
                        resColNo = resColNo + (document.forms[0].elements['CCNo2'].value);
                        resColNo = resColNo + (document.forms[0].elements['CCNo3'].value);
                        resColNo = resColNo + (document.forms[0].elements['CCNo4'].value);
                        resColNo = resColNo + (document.forms[0].elements['CCNo5'].value);
                        resColNo = resColNo + (document.forms[0].elements['CCNo6'].value);
                        resColNo = resColNo + (document.forms[0].elements['CCNo7'].value);
                        resColNo = resColNo + (document.forms[0].elements['CCNo8'].value);
                        resColNo = resColNo + (document.forms[0].elements['CCNo9'].value);
                        resColNo = resColNo + (document.forms[0].elements['CCNo10'].value);
                        resColNo = resColNo + (document.forms[0].elements['CCNo11'].value);
                        resColNo = resColNo + (document.forms[0].elements['CCNo12'].value);
                        resColNo = resColNo + (document.forms[0].elements['CCNo13'].value);
                        resColNo = resColNo + (document.forms[0].elements['CCNo14'].value);
                        resColNo = resColNo + (document.forms[0].elements['CCNo15'].value);
                        resColNo = resColNo + (document.forms[0].elements['CCNo16'].value);
                        resColNo = resColNo + (document.forms[0].elements['CCNo17'].value);
                        resColNo = resColNo + (document.forms[0].elements['CCNo18'].value);
                        if (resColNo.length < 18)       
                        {                                
                                Card.innerHTML = "Enter Complete Collector Certificate No" ;
                                return "";
                            }
                      else
                      {
                        Card.innerHTML = "" ;
                      
                      } 
              return resColNo;
                }                
             else if(val == 'W' || val == 'J' || val == 'T')
                {
                        if(val=='J')
                        {
                            resWhite = resWhite +  (document.forms[0].elements['WCNo0'].value);
                        }
                        resWhite = resWhite +  (document.forms[0].elements['WCNo1'].value);
                        resWhite = resWhite +  (document.forms[0].elements['WCNo2'].value);
                        resWhite = resWhite +  (document.forms[0].elements['WCNo3'].value);
                        resWhite = resWhite +  (document.forms[0].elements['WCNo4'].value);
                        resWhite = resWhite +  (document.forms[0].elements['WCNo5'].value);
                        resWhite = resWhite +  (document.forms[0].elements['WCNo6'].value);
                        resWhite = resWhite +  (document.forms[0].elements['WCNo7'].value);
                        resWhite = resWhite +  (document.forms[0].elements['WCNo8'].value);
                        resWhite = resWhite +  (document.forms[0].elements['WCNo9'].value);
                        resWhite = resWhite +  (document.forms[0].elements['WCNo10'].value);
                        resWhite = resWhite +  (document.forms[0].elements['WCNo11'].value);
                        resWhite = resWhite +  (document.forms[0].elements['WCNo12'].value);
                        resWhite = resWhite +  (document.forms[0].elements['WCNo13'].value);
                        resWhite = resWhite +  (document.forms[0].elements['WCNo14'].value);
                        resWhite = resWhite +  (document.forms[0].elements['WCNo15'].value);
                        resWhite=resWhite.toUpperCase();       
//                        if(document.forms[0].telephonic != null)
//                        {                       
//                            if(document.forms[0].telephonic.value != null && document.forms[0].telephonic.value != "")
//                            {                         
//                                if (resWhite != null &&  resWhite != 'undefined' && resWhite != '' && (resWhite.length)<parseInt(dispcardlen) && (resWhite.length)>0 )
//                                {
//                                    Card.innerHTML="Enter Complete "+dispCard;  
//                                    return "";
//                                }                            
//                            }
//                        }                        
//                        else if(document.forms[0].FromOPView != null)
//                        {
//                            if(document.forms[0].FromOPView.value != null && document.forms[0].FromOPView.value == "Yes")
//                            {                           
//                                if (resWhite != null &&  resWhite != 'undefined' && resWhite != '' && (resWhite.length)<parseInt(dispcardlen) && (resWhite.length)>0 )
//                                {
//                                    Card.innerHTML="Enter Complete "+dispCard; 
//                                    return "";
//                                }                            
//                            }
//                        }
//                        else 
                        if(document.forms[0].FromOPView == null && document.forms[0].telephonic == null)
                        {
                         if ((resWhite.length)< parseInt(dispcardlen))
                            {
                                Card.innerHTML="Enter Complete "+dispCard;  
                                return "";
                            }
                        }
//                      if(document.forms[0].FromViewPatDtls == null)  
//                      {
                          var lStrTemp='',lStrDist='',lStrMandal='';
                          var lStrFpshp='',lStrSequnceNo='';
                          if ((resWhite.length)==parseInt(dispcardlen) )    
                         { 
                          if(val=='J'){
                          lStrTemp=resWhite.substring(0,4).toUpperCase();
                          lStrDist=resWhite.substring(4,6);
                          lStrMandal=resWhite.substring(6,8);
                          lStrFpshp=resWhite.substring(8,11);
                          lStrSequnceNo=resWhite.substring(11,16);}
                          else if(val=='W' || val == 'T'){
                          lStrTemp=resWhite.substring(0,3).toUpperCase();
                          lStrDist=resWhite.substring(3,5);
                          lStrMandal=resWhite.substring(5,7);
                          lStrFpshp=resWhite.substring(7,10);
                          lStrSequnceNo=resWhite.substring(10,15);}
                           if(((lStrTemp=='WAP' || lStrTemp=='AAP'  || lStrTemp=='YAP') && val=='W')  || (lStrTemp=='JPAP' && val=='J') || ((lStrTemp=='TAP' || lStrTemp =='RAP') && val=='T'))
                           {
                              if(lStrDist!='00'){
    
                                 if(lStrMandal!='00'){
                                 
                                        if(lStrFpshp!='000'){
                                               if(lStrSequnceNo!='00000'){
    
                                               }
                                               else{
                                                 alert('In valid '+dispCard);
                                                 return "";
                                              }                                           
                                        }
                                       else{
                                         alert('In valid '+dispCard);
                                         return "";
                                      }
                                 }
                                  else{
                                     alert('In valid '+dispCard);
                                     return "";
                                  }
                              }
                           else{
                              alert('In valid '+dispCard);
                              return "";
                           }                          
                           }
                           else{
                              alert('In valid '+dispCard);
                              return "";
                           }
                         }
//                       }
                        if (document.forms[0].elements['chkHead'].checked !=true)
                        {
                            resWhite = resWhite + "/";
                            resWhite = resWhite +  (document.forms[0].elements['WCNo16'].value);
                            resWhite = resWhite +  (document.forms[0].elements['WCNo17'].value);
                        } 
                        if(document.forms[0].elements['chkHead'].checked == true)
                        {//debugger;
                            if (resWhite.length < parseInt(dispcardlen))
                            {
                                Card.innerHTML = "Enter Complete "+dispCard;
                                return "";
                            }
                             else
                             {
                                Card.innerHTML = "" ;
                                resWhite = resWhite + "/";
                            resWhite = resWhite +  "0";
                            resWhite = resWhite +"1";
                             }
                        } 
                        if(document.forms[0].telephonic != null)
                        {                       
                            if(document.forms[0].telephonic.value != null && document.forms[0].telephonic.value != "")
                            {                         
                                if (resWhite != null &&  resWhite != 'undefined' && resWhite != '' && resWhite !="/" && (resWhite.length)<parseInt(dispcardfulllen) && (resWhite.length)>0 )
                                {
                                    Card.innerHTML="Enter Complete "+dispCard;  
                                    return "";
                                }                            
                            }
                        }  
                        else if(document.forms[0].FromOPView != null)
                        {
                            if(document.forms[0].FromOPView.value != null && document.forms[0].FromOPView.value == "Yes")
                            {                           
                                if (resWhite != null &&  resWhite != 'undefined' && resWhite != '' && resWhite !="/" && (resWhite.length)<parseInt(dispcardfulllen) && (resWhite.length)>0 )
                                {
									
                                    Card.innerHTML="Enter Complete "+dispCard; 
                                    return "";
                                }                            
                            }
                        }
                        if(document.forms[0].FromOPView == null && document.forms[0].telephonic == null)//comment removed by sowjanya on production issue
                       {       
                            if(document.forms[0].elements['chkHead'].checked == false)
                            {
                                 var lStrlstTwo='';
                                  if (resWhite.length < parseInt(dispcardfulllen))
                                    {
                                        Card.innerHTML = "Enter Complete  "+dispCard;
                                        return "";
                                    }
                                    else
                                     {
                                        Card.innerHTML = "" ;
                                          lStrlstTwo=resWhite.substring((parseInt(dispcardfulllen)-2),(parseInt(dispcardfulllen))); 
                                          if(lStrlstTwo!='00')
                                          {
    
                                          }
                                           else{
                                            alert("In valid  "+dispCard);
                                            return "";
                                           }
                                     }
                            }
                        }
                 }//white card no                    
              //}//end of if
            }// end of for         
if(resWhite == undefined)
resWhite="";  
return resWhite;   
}
function fn_chkFamilyHead()
{
    if (document.forms[0].elements['chkHead'].checked==true)
    {
        document.forms[0].elements['WCNo16'].disabled=true;
        document.forms[0].elements['WCNo16'].value="0";
        document.forms[0].elements['WCNo17'].disabled=true;
        document.forms[0].elements['WCNo17'].value="1";
        if(document.forms[0].cb_Gender != null)
        {
            document.forms[0].cb_Gender.checked = false;
            document.forms[0].cb_Gender.disabled = true;
        }
        else
        {
            document.forms[0].cb_ChildYN.checked = false;
            document.forms[0].cb_ChildYN.disabled = true;
        }
    }
    else
    {
        document.forms[0].elements['WCNo16'].disabled=false;
        document.forms[0].elements['WCNo16'].value="";
        document.forms[0].elements['WCNo17'].disabled=false;
        document.forms[0].elements['WCNo17'].value="";

        if(document.forms[0].cb_Gender != null)
        {
            document.forms[0].cb_Gender.disabled = false;
        }
        else
        {
            document.forms[0].cb_ChildYN.disabled = false;
        }
        alert("Please Enter complete card no and click on Retrieve Details");
		lStrClickFlg = 0;
		if(!(document.getElementById('telephonic') || document.getElementById('FromOPView')))
		DisableScreen();
		return false;
    }  
}
function fn_chkChildYN()
{
    var check="";
    if(document.forms[0].elements['cb_ChildYN'])
    {
        if(document.forms[0].elements['cb_ChildYN'].checked==true)
        {
            check="0";
        }
    }
    if(document.forms[0].elements['cb_child_yn'])
    {
        if(document.forms[0].elements['cb_child_yn'].checked==true)
        {
            check="0";
        }
    }
    /*if(document.forms[0].elements['cb_child_yn'].checked==true)
    {
            document.getElementById("monthsid").style.display="";
            document.getElementById("daysid").style.display="";
    }
    else
    {
            document.getElementById("monthsid").style.display="none";
            document.getElementById("daysid").style.display="none";
    }*/
    if (check != "")
    {
		if(document.forms[0].elements['WCNo16'])
        document.forms[0].elements['WCNo16'].disabled=false;
		if(document.forms[0].elements['WCNo17'])
        document.forms[0].elements['WCNo17'].disabled=false;
		if(document.forms[0].chkHead)
        {
			document.forms[0].chkHead.checked = false;
			document.forms[0].chkHead.disabled = true;
		}
        //document.forms[0].txt_Age.value=0;
        /*if(document.forms[0].FromOPView == null)
        /{*/
            document.forms[0].txt_Months.readOnly = false;
            document.forms[0].txt_Days.readOnly = false;
              document.forms[0].txt_Months.value=0;
              document.forms[0].txt_Days.value=0;
              var monthDisp= document.getElementById("monthsid");
              monthDisp.style.display='';
              var daysDisp= document.getElementById("daysid");
              daysDisp.style.display='';
			  if(document.forms[0].telephonic){
				  if(document.forms[0].telephonic.value=="YES"){
				  monthDisp.style.display='none';
				  daysDisp.style.display='none';
				  }
			  }
         //}   
    }
    else
    {
		if(document.forms[0].elements['WCNo16'])
			document.forms[0].elements['WCNo16'].disabled=false;
		if(document.forms[0].elements['WCNo17'])
			document.forms[0].elements['WCNo17'].disabled=false;
		if(document.forms[0].chkHead)
        {
			document.forms[0].chkHead.checked = false;
			document.forms[0].chkHead.disabled = false;
		}
        /*if(document.forms[0].FromOPView == null)
        {*/
            document.forms[0].txt_Months.readOnly =true;
            document.forms[0].txt_Days.readOnly = true;            
            document.forms[0].txt_Months.value = 0 ;
            document.forms[0].txt_Days.value = 0 ;
            var monthDisp= document.getElementById("monthsid");
            monthDisp.style.display='none';
            var daysDisp= document.getElementById("daysid");
            daysDisp.style.display='none';
       // }
    }  
}  
 function showClaimsPatientForm(arg)
{ 
     var selected_option="";
     var val="";
     var cardno="";
	 var resFlag = null;
	 if(document.forms[0].RestrictFlag != null)
	  {
		var resFlag= document.forms[0].RestrictFlag.value;
	  }
     if(arg == "CMPatient")
     {
         /*var radio = document.forms[0].elements['CCType']; 
         for(var i=0;i<radio.length;i++)
        {
            if(radio[i].checked == true)
            {
                 val = radio[i].value;
            }
        }*/
		val="C";
          selected_option='-1';
         if(document.forms[0].CMCOSelect) 
         { 
            var selVal=document.forms[0].CMCOSelect.selectedIndex;
            selected_option = document.forms[0].CMCOSelect.options[selVal].value;  
            cardno='';
         }
     }
    if(val != "" && selected_option != "" || arg == "InPatient" )
    {
        if(!(val=='C')/* && (selected_option=='N' || selected_option=='-1'))*/ || arg == "InPatient")
        {    
          cardno=fn_chkWhiteCardNo();
          if( cardno != undefined)
          cardno= cardno.toUpperCase(); 
        }
		else if(!(val=='C' && (selected_option=='N' || selected_option=='-1')))
		{
		  cardno=fn_chkCMCOCardNo();
          if( cardno != undefined)
          cardno= cardno.toUpperCase();
		}
    }
    else 
    cardno=arg;
	if(resFlag != null)
	{
   var url="/ASRI/FrontServlet?requestType=CaseDetailsRH&actionVal=getPatientTotalDtls&CardNo="+cardno+"&RestrictFlag="+resFlag;
	}
	else
	{
   var url="/ASRI/FrontServlet?requestType=CaseDetailsRH&actionVal=getPatientTotalDtls&CardNo="+cardno;
	}
   cardNoWin = window.open(url,"CardNo",'width=1150,height=600,resizable=1,top=20,left=0,scrollbars=1');
}
function getTelApvDtls(arg1)
{
    url="/ASRI/FrontServlet?requestType=PatientDetails&actionVal=CmbDetails&pageName=TelephonicPatientEntry&cmbhdr=LH6&cmbparentid=S17&cmbhdrid=CH4~CH11~CH6&langid=en_US&telephonic_id="+arg1;    
    childWinTelAprvDtls = window.open(url,"TelephonicPatientEntry",'width=1150,height=600,resizable=1,top=20,left=0,scrollbars=1'); 
}
function selectSource()
{
var radio = document.forms[0].elements['rd_Source'];
for(var i=0;i<radio.length;i++)
{
    if(radio[i].checked == true)
    {
        var val = radio[i].value;
        if(val == 'P')
        {
             tr_PHC_id1.style.display = '';
			 if(document.getElementById('tr_PHC_id2'))
             tr_PHC_id2.style.display = '';
            
            var row = document.getElementById('tr_MC_id1');
            if(row != null)
            {
                tr_MC_id1.style.display = 'none';
             }
			 if(document.getElementById('tr_MC_id2'))
             {row = document.getElementById('tr_MC_id2');
            if(row != null)
            {
                tr_MC_id2.style.display = 'none';
             }
              row = document.getElementById('tr_MC_id3');
			  }
              row = document.getElementById('screenedHosp');
			if(row != null)
            {
                screenedHosp.style.display = 'none';
                document.getElementById('screenedHospital').value="-1";
             }
        if(document.forms[0].id_calendar)
          document.getElementById('id_calendar').style.display='';
        }
        else
         if(val == 'MC')
        {
             tr_MC_id1.style.display = '';
			 if(document.getElementById('tr_MC_id2'))
             tr_MC_id2.style.display = '';
			 if(document.getElementById('screenedHosp') != null)
                 screenedHosp.style.display = '';

            var row = document.getElementById('tr_PHC_id1');
            if(row != null)
            {
                tr_PHC_id1.style.display = 'none';
             }
			 if(document.getElementById('tr_PHC_id2'))
             {row = document.getElementById('tr_PHC_id2');
            if(row != null)
            {
                tr_PHC_id2.style.display = 'none';
             }
              row = document.getElementById('tr_PHC_id3');
			  }
       if(document.forms[0].id_calendar)
       document.getElementById('id_calendar').style.display='none';
            
        }
        else
        {
             tr_MC_id1.style.display = 'none';
			 if(document.getElementById('tr_MC_id2'))
             tr_MC_id2.style.display = 'none';
         	 if(document.getElementById('screenedHosp') != null)
                 screenedHosp.style.display = '';
         
             tr_PHC_id1.style.display = 'none';
			 if(document.getElementById('tr_PHC_id2'))
             tr_PHC_id2.style.display = 'none';
			 if(document.forms[0].id_calendar)
       document.getElementById('id_calendar').style.display='';
        }
        
     }
}  
} 
//start rajashekar for Emergency check
function fn_EmergCheck()
{
if(document.getElementById('EmrChk').checked==true){
document.getElementById('exempReasons').style.display='';
document.getElementById('emrChkTxt').style.display='';
document.getElementById('id_Fp_Capture').style.display='none';
}
else{
document.forms[0].emrChkTxt.value="";
document.forms[0].exemptionReasons.value="-1";
document.getElementById('exempReasons').style.display='none';
document.getElementById('emrChkTxt').style.display='none';
document.getElementById('id_Fp_Capture').style.display='';
}
}//end
function deletePatientDtls()
{
    document.forms[0].Delete.disabled = true;
    document.forms[0].action= "/ASRI/FrontServlet?requestType=PatientDetails&actionVal=DeletePatientDtlsUntilCaseCreation";
    document.forms[0].submit();
}
function selectPatientType()
{    
    var rdPatientType = document.forms[0].elements['DecideIPIP'];
    var AdmissionDetailsRow = document.getElementById('idAdmissionDetailsRow');
    var OPDateRow = document.getElementById('idOPDateRow');    
    var NumberRow = document.getElementById('idNumberRow');
    var OPLabel = document.getElementById('idOPLabel');
    var IPLabel = document.getElementById('idIPLabel');
    var SelectTest = document.getElementById('SelectTest');
    var FinalOpinion = document.getElementById('FinalOpinionRow');
    var TestsCost = document.getElementById('TestsCost');
    var id_cmbCategory = document.getElementById('id_cmbCategory');
	var genTestSlip = document.getElementById('GenerateTestSlip');//FB26867
    if(rdPatientType[0].checked == true)
    { 
      NumberRow.style.display="";
      AdmissionDetailsRow.style.display="none";     
      OPDateRow.style.display="";
      OPLabel.style.display="";
      IPLabel.style.display="none";
      FinalOpinion.style.display="";
      var sysdate = new Date();
      var displayDate = sysdate.getDate() + "/" + (sysdate.getMonth() + 1) + "/" + sysdate.getYear();
      document.forms[0].txtOPDate.value=displayDate;
      SelectTest.style.display="none";
      TestsCost.style.display="none";
	  genTestSlip.style.display="none";//FB26867
      id_cmbCategory.disabled=true;
	  
    }
    else if(rdPatientType[1].checked == true)
    {
      NumberRow.style.display="";
      AdmissionDetailsRow.style.display="";   
      OPDateRow.style.display="none";
      OPLabel.style.display="none";
      IPLabel.style.display="";
      FinalOpinion.style.display="";
      var sysdate = new Date();
      var displayDate = sysdate.getDate() + "/" + (sysdate.getMonth() + 1) + "/" + sysdate.getYear();
      document.forms[0].txtAdmissionDate.value=displayDate;
      SelectTest.style.display="none";
      TestsCost.style.display="none";
	  genTestSlip.style.display="none";//FB26867
      id_cmbCategory.disabled=true;
    }
    else if(rdPatientType[2].checked == true)
    {
	  document.forms[0].moreTestsSele.value="true";
      NumberRow.style.display="none";
      AdmissionDetailsRow.style.display="none";   
      OPDateRow.style.display="none";
      OPLabel.style.display="none";
      IPLabel.style.display="none";
      FinalOpinion.style.display="none";
      SelectTest.style.display="";
      TestsCost.style.display="none";
	  genTestSlip.style.display="";//FB26867
      id_cmbCategory.disabled=true;
    }
}
function fn_chkCMCOCardNo()
{
var resColNo='';
 resColNo = resColNo + (document.forms[0].elements['CCNo1'].value);
				resColNo = resColNo + (document.forms[0].elements['CCNo2'].value);
				resColNo = resColNo + (document.forms[0].elements['CCNo3'].value);
				resColNo = resColNo + (document.forms[0].elements['CCNo4'].value);
				resColNo = resColNo + (document.forms[0].elements['CCNo5'].value);
				resColNo = resColNo + (document.forms[0].elements['CCNo6'].value);
				resColNo = resColNo + (document.forms[0].elements['CCNo7'].value);
				resColNo = resColNo + (document.forms[0].elements['CCNo8'].value);
				resColNo = resColNo + (document.forms[0].elements['CCNo9'].value);
				resColNo = resColNo + (document.forms[0].elements['CCNo10'].value);
				resColNo = resColNo + (document.forms[0].elements['CCNo11'].value);
				resColNo = resColNo + (document.forms[0].elements['CCNo12'].value);
				resColNo = resColNo + (document.forms[0].elements['CCNo13'].value);
				resColNo = resColNo + (document.forms[0].elements['CCNo14'].value);
				resColNo = resColNo + (document.forms[0].elements['CCNo15'].value);
				resColNo = resColNo + (document.forms[0].elements['CCNo16'].value);
				resColNo = resColNo + (document.forms[0].elements['CCNo17'].value);
				resColNo = resColNo + (document.forms[0].elements['CCNo18'].value);
				if (resColNo.length < 18)       
				{                                
						Card.innerHTML = "Enter Complete Collector Certificate No" ;
						return "";
				}
			  else
			  {
				Card.innerHTML = "" ;
			  
			  } 
	if(resColNo == undefined)
	resColNo=""; 
  return resColNo;
}
function RetreiveDetails(arg)
{
  var cardno ='';
   var val='';
   var cmcobiourl='';
   if(arg != 'CMOPatient')
   {
   var radio = document.forms[0].elements['CCType']; 
   for(var i=0;i<radio.length;i++)
    {
        if(radio[i].checked == true)
        {
            val = radio[i].value;
        }
    }
	}
    if(arg == 'CMOPatient')
    {
         var selected_option='-1',fingerPrint='';
		 val="C";
         if(document.forms[0].CMCOSelect) 
         { 
            var selVal=document.forms[0].CMCOSelect.selectedIndex;
            selected_option = document.forms[0].CMCOSelect.options[selVal].value;  
            cardno='';
			cmcobiourl=cmcobiourl+"&CMCOSelect="+selected_option;
		 }
		 if(document.forms[0].fingerPrint)
		{
			 fingerPrint=document.forms[0].fingerPrint.value;
			 cmcobiourl=cmcobiourl+"&fingerPrint="+fingerPrint;
		}
        if(!(val=='C' && (selected_option=='N' || selected_option=='-1')))
        {
          cardno=fn_chkCMCOCardNo();		  
           if( cardno != undefined)
            cardno= cardno.toUpperCase();    
           if(cardno !="undefined" && cardno!=null) 
           { 
             if(specialCharactersDoesnotContain(cardno,"Card No") == false)
             {
              return;
             }
           }
         } 
     }     
    else
    {
       cardno=fn_chkWhiteCardNo(); 
       if( cardno != undefined)
        cardno= cardno.toUpperCase();         
       if(cardno !="undefined" && cardno!=null) 
       { 
         if(specialCharactersDoesnotContain(cardno,"Card No") == false)
         {
          return;
         }
       } 
    } 
    if(arg == 'TelephonicPatientEntry' || arg == 'OutPatient')
    {
        if(cardno == '' || cardno == '/')
        {
            //alert("Please enter Card Number");
            return;
        }
    }
   var lang_id=document.forms[0].lang_id.value; 
   if(cardno!='' && cardno!=undefined)
   {   
       var url;
	   //Start chandu for TAP Child Changes.
	   if(document.getElementById('id_addTAPBenf').checked ==true)
	   {
	   url="/ASRI/FrontServlet?requestType=PatientDetails&actionVal=RetreiveDetails&pageName="+arg+"&cardNo="+cardno+"&langid="+lang_id+cmcobiourl+"&isTapChild=True";
	   }
	   else{
       url="/ASRI/FrontServlet?requestType=PatientDetails&actionVal=RetreiveDetails&pageName="+arg+"&cardNo="+cardno+"&langid="+lang_id+cmcobiourl;
	   }
	   //End Chandu
       if(arg == 'OutPatient')
       {     
            var apcode=document.forms[0].apcode.value;    
            url+="&PType=OP&apcode="+apcode+"&CardType="+val; 
       }
       else if(arg == 'CMOPatient' || arg == 'InPatient')
       { var patientcheck='';if(document.forms[0].Patientcheck)
	    patientcheck=document.forms[0].Patientcheck.checked;
          var flg="1";
          url+="&PType=IP&CardType="+val+"&ClickFlg="+flg+"&patientcheck="+patientcheck;
       }
       else 
       {
          url+="&PType=IP&CardType="+val;
       }
       document.forms[0].action=url;
       document.forms[0].method="post";
       document.forms[0].submit();
   }
}
function fn_openAtach(filepath)
{  
    var Storage=null;
    if(document.forms[0].NewStorage1!=null)
    Storage=document.forms[0].NewStorage1.value;
    var url = "/ASRI/FrontServlet?requestType=ReadAttachRequestHandler&actionVal=openAtach&filepath="+filepath+"&Storage="+Storage;
    childWindow= window.open(url,"",'width=1150,height=600,resizable=yes,top=100,left=0,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
}
//Start FB24678
function fn_openAtach1(filepath,no,index)
{  
   document.getElementById(no).value = "CLICKED";
   var url = "/ASRI/FrontServlet?requestType=ReadAttachRequestHandler&actionVal=openAtach&filepath="+filepath;
   wNo[index]= window.open(url,'win'+no,'width=1150,height=600,resizable=yes,top=100,left=0,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');   
}

//End FB24678
function fn_openAtachDisable(filepath)
{  
    var Storage=null;
    if(document.forms[0].NewStorage1!=null)
    Storage=document.forms[0].NewStorage1.value;
    var url="/ASRI/common/showImage.jsp?filepath="+filepath+"&Storage="+Storage;
    childWindow= window.open(url,"",'width=1150,height=600,resizable=yes,top=100,left=0,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
}
function fn_openAtach_Condition(filepath,arg)
{  
    if(arg == 'PRE' || arg=='COCH' || arg=='HEMO' || arg =='CL' || arg =='HEMOCL' )
	{
		var Storage=null;
		if(document.forms[0].NewStorage1!=null)
		Storage=document.forms[0].NewStorage1.value;
		var url="/ASRI/common/showImage.jsp?filepath="+filepath+"&Storage="+Storage;
		childWindow= window.open(url,"",'width=1150,height=600,resizable=yes,top=100,left=0,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
	}
	else
	{
		return false;
	}
}

function fnGenTestSlip(PatientId)
{
   var urlstring='/ASRI/FrontServlet?requestType=PrintRH&actionVal=PrintOPIPRS&PatientId='+PatientId;
   var urlstyle = 'width=1150,height=600,resizable=yes,top=100,left=0,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=yes';
   newWindow = window.open(urlstring,'OPIPTestSlip',urlstyle);
} 
function fnGenReferenceSlip(PatientId)
{
   var urlstring='/ASRI/FrontServlet?requestType=PrintRH&actionVal=PrintRefLetter&PatientId='+PatientId;
   var urlstyle = 'width=1150,height=600,resizable=yes,top=100,left=0,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=yes';
   newWindow = window.open(urlstring,'OPIPTestSlip',urlstyle);
}
function fnCompareDates(FromDate,ToDate)
{
    var FromDateVal;
    var ToDateVal;
    
    var k = FromDate.indexOf("/");
    var t = FromDate.indexOf("/",3);     
    FromDateVal = FromDate.substr(k+1,t-k-1) +"/"+FromDate.substr(0,k)+"/"+FromDate.substr(t+1,FromDate.length);
    
    k = ToDate.indexOf("/");
    t = ToDate.indexOf("/",3);
    ToDateVal = ToDate.substr(k+1,t-k-1) +"/"+ToDate.substr(0,k)+"/"+ToDate.substr(t+1,ToDate.length);
    
    if (Date.parse(FromDateVal) > Date.parse(ToDateVal))
                  {
                        alert("From date should be less than To date");
      return false; 
                  }
    else
      return true;           
}
function fnMonthDiff(FromDate,ToDate)
{
	if(document.forms[0].UserRole!=null)
        {
            var UserRole = document.forms[0].UserRole.value;
            if('CD6' == UserRole)
            {
                return true;
            }
        }
        var FromDateVal;
	var ToDateVal;            
	var k = FromDate.indexOf("/");
	var t = FromDate.indexOf("/",3);   
	FromDateVal = FromDate.substr(k+1,t-k-1)+"/"+FromDate.substr(0,k)+"/"+FromDate.substr(t+1,t-1); 

	var fromYear = parseInt(FromDate.substr(t+1,t-1));			
	var fromMon = Number(FromDate.substr(k+1,t-k-1));
	var fromDt=Number(FromDate.substr(0,k));	

	k = ToDate.indexOf("/");
	t = ToDate.indexOf("/",3);
	ToDateVal = ToDate.substr(k+1,t-k-1) +"/"+ToDate.substr(0,k)+"/"+ToDate.substr(t+1,t-1);

	var toYear = Number(ToDate.substr(t+1,t-1));
	var toMon = Number(ToDate.substr(k+1,t-k-1));
	var toDt=Number(ToDate.substr(0,k));

	if(toYear == fromYear)
	{				
		if((toDt - fromDt >=0 && toMon - fromMon <= 2) || (toDt - fromDt <0 && toMon - fromMon <= 3))
		{
			return true;
		}
		else 
		{   
			alert('Can not select more than 3 months difference');
			return false;
		}
	}
	else if(toYear > fromYear)
	{
		if((toDt - fromDt >=0 &&  fromMon - toMon >=10) || (toDt - fromDt <0 && fromMon - toMon >= 9))
		{
			return true;
		}
		else 
		{  
			alert('Can not select more than 3 months difference');
			return false;
		}
	}
	else
	{
		alert('Please select valid From and To dates');
		return false;
	}
}
var attpopup;
function fn_viewAtach(mode,uploadType)
{  
    var CaseId;
	if(document.forms[0].CaseId)
	CaseId=document.forms[0].CaseId.value;
	else
	CaseId=document.forms[0].caseId.value;
    var PatId=document.forms[0].PatId.value;
    var CaseNo;
	if(document.forms[0].CaseNo)
		CaseNo=document.forms[0].CaseNo.value;
	else
		CaseNo=document.forms[0].caseNo.value;
    if(attpopup && !attpopup.closed)
      attpopup.close();
    Url="/ASRI/FrontServlet?requestType=CaseAttachRequestHandler&actionVal=getAtachments&CaseId="+CaseId+"&PatId="+PatId+"&CaseNo="+CaseNo+"&mode="+mode+"&uploadType="+uploadType;	
    attpopup=window.open(Url,"_blank","toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, copyhistory=no, width=1150, height=600, top=50,left=0");
}
function reSizeIN(val)
{
    document.getElementById('in'+val).style.display='none';
    document.getElementById('out'+val).style.display='';
    document.getElementById('tab'+val).style.display='';
}
function reSizeOUT(val)
{
    document.getElementById('in'+val).style.display='';
    document.getElementById('out'+val).style.display='none';
    document.getElementById('tab'+val).style.display='none';
}

function showDocument(caseId,clinicalId,fileName)
{
  var url="/ASRI/FrontServlet?requestType=ClinicalDataRH&actionVal=showAttachments&lClinicalId="+clinicalId+"&data="+fileName+"&CaseId="+caseId;
   childWin = window.open(url,"",'width=1150,height=600,resizable=1,top=20,left=0,scrollbars=1');
}
function Validate(arg)
{
  
    var validFileobj=eval("document.forms[0]."+arg);
    var validFile=validFileobj.value;
    if(validFile=="")
    {
          alert("Please  Attach File");
          return;
    }
    else
    {          
          var pos1=validFile.lastIndexOf("\\");
          var sub1=validFile.substring(pos1+1,len);
          newArray=sub1.split(' ');
          if(newArray.length > 1)
          {
            alert('File Name contains spaces please Rename the file name with out spaces..');
            return false;
          }		  
          vSplit=validFile.split("\\");
          vFileName = vSplit[(vSplit.length)-1];      
          var len=validFile.length; 		  
          if(len > 0)
          {
              var pos=validFile.lastIndexOf(".");
              var sub=validFile.substring(pos+1,len);
    
              if((sub=="gif")||(sub=="jpeg")||(sub=="jpg")||(sub=="bmp")||(sub=="WMF")
              ||(sub=="wmf")||(sub=="GIF")||(sub=="JPEG")||(sub=="JPG")||(sub=="BMP"))
              {
                     rtVal = chkSpecailChars(vFileName);
                     if(rtVal ==0) 
                     {
                           alert('File Name should not contain special characters');
                           if(arg == 'Attachment')
                           {
                                document.forms[0].Attachment.outerHTML='<input type="file"name="FileUpload"  value="">';
                           }
                           window.location.reload( );                   
                     }
                    else
                    {          
                      if(arg == 'Attachment')
                      {
                        sendImage();
                      }
                      else
                      {
                          InsertFile('1');
                          window.parent.document.forms[0].flag.value=1;        
                          window.parent.document.forms[0].FileName.value="";                           
                      }
                    }    
              }
              else
              {
                 if(arg == 'Attachment')
                 {
                    document.forms[0].Attachment.outerHTML='<input type="file"name="FileUpload"  value="">';
                 }                 
                 alert("Attach Images only");                 
                 window.location.reload( );
              }
           }
    }
}
function checkZero(arg)
{
        while((parseInt(arg.substring(0,1))==0) && arg.length>1)
        {
                arg=arg.substring(1);
        }
        return arg;
}

function fnSearchWithPagination()
{
    document.forms[0].Search.value="true";
    fn_pagination();
}
function showSearch()
{
    document.getElementById("Click").style.display="none";
    document.getElementById("AdvSearch").style.display="";
}
function hideSearch()
{
    document.forms[0].Search.value="false";
    if(document.forms[0].ButtonOption != null)
    {
        document.forms[0].ButtonOption.value="false";
    }
    fnReset();
    document.getElementById("Click").style.display="";
    document.getElementById("AdvSearch").style.display="none";    
}
function fn_addClinicalData(arg,prepost,caseid)
{   
    document.forms[0].action="/ASRI/FrontServlet?requestType="+arg+"&actionVal=viewClinicData&PrePost="+prepost+"&CaseId="+caseid;
    document.forms[0].submit();
}
function onKeyDownEvent(event)
{
      if ( (event.altKey) || ((event.keyCode == 8) &&  (event.srcElement.type != "text" &&
      event.srcElement.type != "textarea" &&
      event.srcElement.type != "password")) || 
      ((event.ctrlKey) && ((event.keyCode == 78) || (event.keyCode == 82)) ) ||
        
      (event.keyCode == 116) ) {
      event.keyCode = 0;
      event.returnValue = false;
      }
}
function deletePatientDtls()
{
    document.forms[0].Delete.disabled = true;
    document.forms[0].action= "/ASRI/FrontServlet?requestType=PatientDetails&actionVal=DeletePatientDtlsUntilCaseCreation";
    document.forms[0].submit();
}
function getDataCategory(x,arg) 
{
   var Selected="";
   var selectedvalue="";
   var Gender="";
   if(document.forms[0].rd_Gender){
   for (var i=0; i < document.forms[0].rd_Gender.length; i++)
    {
     if (document.forms[0].rd_Gender[i].checked)
	  {
		Gender = document.forms[0].rd_Gender[i].value;
	  }
    }
	}
   if( arg == 'TelephonicPg' )
   {   
        var genderMale=document.getElementById("rd_Gender0").checked;
		var genderFemale=document.getElementById("rd_Gender1").checked;
        if((!genderMale) && (!genderFemale)){
            alert("Please select gender to proceed");
			document.forms[0].HospitalName.value="-1";	
            return false;			
	   }

	   var district = document.forms[0].ddl_District.value;
       var Hospital = document.forms[0].HospitalName.value;
       if(Hospital == null || Hospital == '-1')
       {
            alert("Please select the Hospital Details to Proceed");
            document.forms[0].DiseaseMainCat.value = '-1';
            return;
       }
	   if(district == null || district == '-1')	   
       {
            alert("Please select the Patient District  to Proceed");
            document.forms[0].DiseaseMainCat.value = '-1';
            return;
       }	   
      Selected=document.forms[0].DiseaseMainCat;
   }
   else if(arg=='CMRefPats')
   {        
       var Hospital = document.forms[0].ddl_Hospital.value;
 
		if(document.forms[0].PhaseId)
		var phase=document.forms[0].PhaseId.value;
		if(document.forms[0].renewalID)
		var renewal=document.forms[0].renewalID.value;
        if(Hospital == null || Hospital == '-1')
        {
            alert("Please select the Hospital Details to Proceed");
            document.forms[0].cmbCategory.value = '-1';
            return;
        }
		var genderMale=document.getElementById("rd_Gender0").checked;
		var genderFemale=document.getElementById("rd_Gender1").checked;
        if((!genderMale) && (!genderFemale)){
            alert("Please select gender to proceed");
			document.forms[0].ddl_Hospital.value="-1";	
            return false;			
	     }
		
		if(document.forms[0].PreCMORefCount){
         if(document.forms[0].PreCMORefCount.value!=0)
         {
         if(Hospital!==document.forms[0].PreHospID.value)
         {	 
           document.getElementById("id_btnOPEntry").disabled=true;
           alert("This card is Already registered in another Hospital. Please Cancel the Previous Request");
           document.forms[0].ddl_Hospital.value="-1";
         return;
         }
         }
		 }
        Selected=document.forms[0].cmbCategory;
   }     
   else
   {
      Selected=document.forms[0].Category;
   }   
   if(x==1)
   {
      var FillSelect=document.forms[0].DiseaseSubCat;
   }
   if(x==2)
   {
      var FillSelect=document.forms[0].DiseaseSubCat;
      var FillSec=document.forms[0].Surgery;
      var secVal=FillSelect.options[FillSelect.selectedIndex].value;      
   }
   if(x!='')
   {
       selectedvalue=Selected.options[Selected.selectedIndex].value;
        if (document.implementation && document.implementation.createDocument)
        {
                xmlDoc = document.implementation.createDocument("", "", null);
                xmlDoc.onload = populateDropDownCat;
        }
        else if (window.ActiveXObject)
        {
                xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
                xmlDoc.onreadystatechange = function () 
                {  if (xmlDoc.readyState == 4) 
                        populateDropDownCat(x,arg);
                }
        }
        else
        {
                alert('Your browser can\'t handle this script');
                return;
        }
      if(x==1)
      {
             var url =  '/ASRI/FillData?actionVal=getSubDisease&mainCatId='+selectedvalue;
      }
	  if(x==2 && arg=='CMRefPats')
	   {
			var url =  '/ASRI/FillData?actionVal=getSurgery&mainCatId='+selectedvalue+'&subCatId='+secVal+'&hospId='+Hospital+'&phase='+phase+'&renewal='+renewal+'&status=GovtFlg';			
	   }
      if(x==2 && arg=='TelephonicPg')
      {
             var url =  '/ASRI/FillData?actionVal=getSurgery&mainCatId='+selectedvalue+'&subCatId='+secVal+'&hospId='+Hospital+'&phase='+district+'~D&status=GovtFlg';			 
      }  
      if(x==2 && (arg!='CMRefPats' && arg !='TelephonicPg'))
      {
             var url =  '/ASRI/FillData?actionVal=getSurgery&mainCatId='+selectedvalue+'&subCatId='+secVal+'&patientId='+Hospital;			 
      }  
      if(x==3)
      {
             var url =  '/ASRI/FillData?actionVal=DiseaseEmpanelled&HospId='+Hospital+'&Gender='+Gender;         
      }
      if(x == 4)
      {
             var url =  '/ASRI/FillData?actionVal=getAllSurgeries&mainCatId='+selectedvalue;
      }  
      if(x == 5)  //FB14701--start
      {
	   var Hospital = document.forms[0].Hosp_Id.value;
             var url =  '/ASRI/FillData?actionVal=getAllFlwUpCategory&mainCatId='+Hospital;
      }	 
      if(x == 6)  //FB14701
      {
	  var Category = document.forms[0].Category.value;
	 
             var url =  '/ASRI/FillData?actionVal=getAllFlwUpSurgeries&mainCatId='+Category;
      }	  
            //load the xml
            xmlDoc.load(url);
   }
}

function populateDropDownCat(x,arg)
{
	
	var browser = 'ie';
	var nameIndex = 0;
	var valueIndex = 1;
	var testVar=x;
	var deathFlg="";
	if(document.forms[0].deathFlag)
     deathFlg=document.forms[0].deathFlag.value;
	if (document.implementation && document.implementation.createDocument)
	{
		browser = 'firefox';
	  var nameIndex = 1;
	  var valueIndex = 3;
	} 
      var xval;
    var dd3;	
    if(x==1)
	{
    dd3= document.forms[0].DiseaseSubCat;
	var dd2= document.forms[0].Surgery;
	  for (var q=dd2.options.length;q>=0;q--)
	  {
		  dd2.options[q]=null;
	  } 
	  dd2.options[0] = new Option('--Select--','-1');
	}
    else if(x==2 || x==4)
    {
        dd3= document.forms[0].Surgery;
    }
    else if(x==3)
    {
        if(document.forms[0].DiseaseMainCat != null)
        dd3= document.forms[0].DiseaseMainCat;
        
        else if(document.forms[0].cmbCategory != null)
        dd3= document.forms[0].cmbCategory;
        
        if(document.forms[0].Surgery != null)
        {
            var dd2= document.forms[0].Surgery;
            for (var q=dd2.options.length;q>=0;q--)
            {
                    dd2.options[q]=null;
            } 
            dd2.options[0] = new Option('--Select--','-1');
            dd2= document.forms[0].DiseaseSubCat;
            for (var q=dd2.options.length;q>=0;q--)
            {
                    dd2.options[q]=null;
            } 
            dd2.options[0] = new Option('--Select--','-1');
        }
     }	      
      else if( x==5  )  //FB14701--start
    {
        dd3= document.forms[0].Category;
		
    }
    else if(x==6)
    {
        dd3= document.forms[0].Surgery;
		
    }
  //empty control
  for (var q=dd3.options.length;q>=0;q--)
  {
      dd3.options[q]=null;
  } 
  var x = xmlDoc.getElementsByTagName('item');
  for (j=0;j<x[0].childNodes.length;j++)
  {
        if (x[0].childNodes[j].nodeType != 1) continue;
        var theData = document.createTextNode(x[0].childNodes[j].nodeName);
  }
	for (i=0;i<x.length;i++)
	{
	  var name = '';
	  var value = '';
		for (j=0;j<x[i].childNodes.length;j++)
		{
			if (x[i].childNodes[j].nodeType != 1) continue;
			var theData = document.createTextNode(x[i].childNodes[j].firstChild.nodeValue);
			if (j==nameIndex) name = theData.nodeValue;
			if (j==valueIndex) value = theData.nodeValue;		
		}
         dd3.options[i] = new Option(name, value);
	}
	if(document.forms[0].btn_OPEntry)
	   document.forms[0].btn_OPEntry.disabled=false;
    if((arg == 'TelephonicPg') && (deathFlg=="true"))
       document.forms[0].btn_OPEntry.disabled=true;
}
function chkSpecailChars(vFileName)
{
   var val =1;  
   var iChars = "*|\":<>[]{}`\';()$#%&^";    
    for (var i = 0; i < vFileName.length; i++) 
    {
        if (iChars.indexOf(vFileName.charAt(i)) != -1)
        {         
          val = 0; break;
        } 
    }
    return val;
} 

//start -- added for Tool Tip Message
var HelpX, HelpY, HelpText;
var ToShow, ToClear;
function DoFlyOver()
{
    if( ToClear != -1 ) window.clearTimeout( ToClear );
    FOArea.innerText = HelpText;
    FOArea.style.posLeft = HelpX + 8;
    FOArea.style.posTop = HelpY + 8;
    FOArea.style.display = "";
    ToClear = setTimeout( "ClearFlyOver()", 8000, "JAVASCRIPT" );
}
function ClearFlyOver()
{
    FOArea.style.display = "none";
}
function FlyOver( Text )
{
    HelpText = Text;
    HelpX = window.event.clientX;
    HelpY = window.event.clientY;
    ToShow = setTimeout( "DoFlyOver()", 500, "JAVASCRIPT" );
}
function checkcontact(arg1,arg)//for accepting numbers either 10 0r 11 digits only
{
    var len,str,str1,i
    len=arg1.value.length
    str=arg1.value
    str1="0123456789"
	if(len<=9 && len>0)
	{
	   alert("Enter 10 or 11 Digit valid "+arg);
	   arg1.value="";
	   arg1.focus();
       return false;	   
	}
	for(i=0,j=0;i<len;i++)
    {
        if((str1.indexOf(str.charAt(i)))==-1)
        {
                alert("Enter Numeric Data in "+arg);
                arg1.value="";
                arg1.focus();
                return false;
        }
		if(str.charAt(i)==0)
		{
		j++;
		}
		if(j==len)
		{
			alert(arg +" with all zeros is invalid. Please enter a valid "+arg);
			arg1.value="";
			arg1.focus();
			return false;
		}
	}
return true;
}
	
//end -- added for Tool Tip Message
function checkphone(arg,arg1)
{
 var e="[7,8,9]";
 if(arg.value.length!=10)
 {
    alert("Enter a 10 Digit valid "+arg1);
    arg.value="";
    return false;
 }
 if(arg.value.search(e))
 {
    alert(arg1+" should start with number 7,8 or 9");
    arg.value="";
    return false;
 }
return true;
}
function fn_openAttachPg(mode,uploadType)
{ 
    var CaseId;
	if(document.forms[0].caseId)
	CaseId=document.forms[0].caseId.value;
	else
	CaseId=document.forms[0].CaseId.value;
	var ViewStatus;
	if(uploadType=='erroneous')
	ViewStatus='ERR';
	else
	ViewStatus=document.forms[0].ViewStatus.value;
	Url="/ASRI/FrontServlet?requestType=AttachmentRH&actionVal=getCaseAttachments&CaseId="+CaseId+"&mode="+mode+"&uploadType="+uploadType+"&ViewStatus="+ViewStatus;
    AllAttach = window.open(Url,"newCaseAtt","toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, copyhistory=no, width=1150, height=600, top=50,left=0");
}
function fn_getSelVal(inFlag){
var lSurgeryObj=document.forms[0].Surgery;
var selVal=lSurgeryObj.options[lSurgeryObj.options.selectedIndex].text;
if(inFlag=='onLoadYES'){


document.forms[0].id_Rmks.value='This is a Provisional Approval given for ----------Procedure for --------- (Diagnosis) based on the indication stated by the treatingdoctor over phone.The Preauthorisation has to be raised with in 72 hrs with completeclinical and documentary evidence,from the Telephonic Intimation ID only.';

}
else if(inFlag=='onLoadNO'){
lSurgeryObj=document.forms[0].Surgery;
selVal=lSurgeryObj.options[lSurgeryObj.options.selectedIndex].text;
document.forms[0].id_Rmks.value='This is a Provisional Approval given for '+selVal + ' Procedure for  --------- (Diagnosis) based on the indication stated by the treating doctor over phone.The Preauthorisation has to be raised with in 72 hrs with complete clinical and documentary evidence,from the Telephonic Intimation ID only.';
}
}
  var restored = true   
function MaximizeRestore()
 {if (restored == true)
            {   
                document.getElementById("testimage").style.width="374";
                document.getElementById("testimage").style.height="448";                
                restored = false;
            }
            else
            {
                document.getElementById("testimage").style.width="100";
                document.getElementById("testimage").style.height="75";                
                restored = true;
            }
        }
function fn_view(patId){
var url="/ASRI/patientAction.do?actionFlag=viewPatientDetails&patientId="+patId;
document.forms[0].action=url;
document.forms[0].method="post";
document.formst[0].submit();
}
function getHCDate(Selected)
{
var len = Selected.length;
var opDate=Selected.substring(len-10,len);
return opDate;
}
//Start Chandu for CMA
function openCMA(caseId)
{
  url="/ASRI/FrontServlet?requestType=PreAuthRH&actionVal=viewCMAremarks&pageName=CmaAuditPage&CaseId="+caseId;
  CmaWin = window.open(url,"CmaAuditPage",'width=1150,height=600,resizable=1,top=20,left=0,scrollbars=1');
return ;
}
//End Chandu for CMA


function focusNext(arg){
	var eles = document.forms[0].elements;
	var nextEle;
	var k = false;
	for(var i in eles){
		var ele = eles[i];
		if(k){
			nextEle = ele;
			break;
		}	
		if(ele == arg)
			k = true;
	}
	if(nextEle != null || nextEle != undefined){
		try{
			// do{
			// alert("raja");
			// }while(nextEle ==undefined);
			//alert("Focusing " + nextEle.name);
			nextEle.focus();
		}catch(e){}
		
	}
}
