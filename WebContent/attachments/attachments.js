var lstFileExtensArray=new Array('gif','jpeg','jpg','bmp','WMF','wmf','GIF','JPEG','JPG','BMP','WRF','wrf','pdf','PDF');
function partial(func /*, 0..n args */) {
	 var args = new Array();
	 for (var i = 1; i < arguments.length; i++) { args.push(arguments[i]); }
	  return function() {
	    var allArguments = args.concat(Array.prototype.slice.call(arguments));
	    return func.apply(this, allArguments);
	  };
}
function addElement(arg)
{
  var ni = document.getElementById(arg+'div');
  var numi = document.getElementById(arg+'cnt');
  var num = (document.getElementById(arg+'cnt').value -1)+ 2;
  numi.value = num;
  var newdiv = document.createElement('div');
  var divIdName = arg+'div'+num;
  var remarks='remarks'+arg+num;
  newdiv.setAttribute('id',divIdName);  
  
  var s =  '<table  align="center"><tr><td colspan=2 width="34%"><textarea name="remarks'+arg+num+'" id="remarks'+arg+num+'" onkeypress="checkCR(event);" rows="50" cols="30"></textarea></td>';
    s= s+'<td colspan=2 align="right" width="32%"><input type="file" name="'+arg+num+'" id="'+arg+num+'" class="FieldBlack"  onkeydown="return validateFile(this);" onmousedown="right(this)"  onmouseup="right(this)" > </input>';
    s=s+'</td><td colspan=2 align="left" width="32%"></td></tr></table>';
  newdiv.innerHTML=s;
  ni.appendChild(newdiv);
 document.getElementById(remarks).onblur = new Function("checkSplChars('"+remarks+"','Remarks');func_maxlen('Remarks',this,2000)");
 
  document.getElementById("Remove"+arg).style.visibility="";
  if(num > 2) 
   document.getElementById("Add"+arg).style.visibility="hidden";
}
function removeElement(arg,flag) 
{
  var d = document.getElementById(arg+'div');
  var numi = document.getElementById(arg+'cnt');
  var num = numi.value ;
  var num1 = num -1;
  if(num > 1)
  {
    numi.value = num1;
    var divNum=arg+'div'+num;
    var olddiv = document.getElementById(divNum);
    d.removeChild(olddiv);
    if(num-1 == 1) 
     document.getElementById("Remove"+arg).style.visibility="hidden";
    if(num-1 < 2) 
     document.getElementById("Add"+arg).style.visibility="";     
  }
  if(flag =='Y')
  {
  parent.fn_resizePage();
  }
}
function addCaseElement(arg,flag)
{
  var ni = document.getElementById(arg+'div');
  var numi = document.getElementById(arg+'cnt');
  var num = (document.getElementById(arg+'cnt').value -1)+ 2;
  numi.value = num;
  var newdiv = document.createElement('div');
  var divIdName = arg+'div'+num;
  newdiv.setAttribute('id',divIdName); 
  var prevNum = num-1;
  var totattachCnt = (document.getElementById("totattachCnt").value -1)+2;
  document.getElementById("totattachCnt").value = totattachCnt;
  var name="attachedIndex["+totattachCnt+"]";
  var s =  '<table  align="center">';
    s= s+'<td colspan=2 align="left" width="32%"><input type="file" name="'+name+'" id="'+arg+num+'" class="FieldBlack"  onkeydown="return validateFile(this);" onmousedown="right(this)"  onmouseup="right(this)" > </input>';
    s=s+'</td><td colspan=2 align="left" width="32%"></td></tr></table>';
  newdiv.innerHTML=s;
  ni.appendChild(newdiv);
  document.getElementById("Remove"+arg).style.visibility="";
  if(num > 2) 
   document.getElementById("Add"+arg).style.visibility="hidden";
  if((arg=="telephonicAttach") && (num>1)){
  document.getElementById("Add"+arg).style.visibility="hidden";
  }
  if(flag =='Y')
	  {
	  parent.fn_resizePage();
	  }
}
function fileFocus(name)
{
	name.style.background="#76A9B4";	
	}
function checkSimilarUpload(vAttachCnt,typ,view)
{
   var rtVal = 1;
   var vFileName=''; var vSplit;
   var vFileName2;
   var vSplit2; 
   var sub1 ='';
   for(l=1;l<=vAttachCnt;l++)
	   {
	   document.getElementById(typ+l).style.background='';
	   }
   for(i=1;i<=vAttachCnt;i++)
   {
	   var   ext1=false;
        vFileName = document.getElementById(typ+i).value; 
        
		var fr = partial(fileFocus,document.getElementById(typ+i));
        if(vFileName == '')
        {
          // alert("All Attachments are mandatory");
           jqueryAlertMsg('Attachments check'," All Attachments are mandatory");
           rtVal = 0; 
           return rtVal;
           break;
        }
        else
        {
            vSplit=vFileName.split("\\");
            vFileName = vSplit[(vSplit.length)-1];
            for(j=i+1;j<=vAttachCnt;j++)
            { 
                 vFileName2 = document.getElementById(typ+j).value;
                 vSplit2=vFileName2.split("\\");
                 vFileName2 = vSplit2[(vSplit.length)-1];
                 if(vFileName2 == vFileName)
                 {
               	  jqueryAlertMsg('Attachments check'," Cannot Upload similar documents",fr);
                   // alert("Cannot Upload similar documents");
                    rtVal =0; 
                    return rtVal;
                    break;
                 }
            } 
            var pos1=vFileName.lastIndexOf(".");
            sub1=vFileName.substring(0,pos1);
            rtVal = chkSpecailCharsAttach(sub1);
            if(rtVal ==0) 
            {
            	jqueryAlertMsg('Attachments check'," File Name should not contain special characters",fr);
             // alert('File Name should not contain special characters'); 
			  rtVal=0;
			  return rtVal;
              break;
            } 
        }
            if(view == 'CaseAttachments')
            {
                for(k=0;k<=dbFilesCnt;k++)      		
                {
                	rtVal = chkSpecailCharsAttach(sub1);
                    if(rtVal ==0) 
                    {
                    	jqueryAlertMsg('Attachments check'," File Name should not contain special characters",fr);
                     // alert('File Name should not contain special characters'); 
					  rtVal=0;
					  return rtVal;
                      break;
                    }
                    if( vFileName == dbFilesArray[k][1])
                    {
                    	 jqueryAlertMsg('Attachments check'," Cannot Upload similar documents \n\n \'"+vFileName+"\' is already attached in \'"+dbFilesArray[k][0]+"\'",fr);
                       // alert(" Cannot Upload similar documents \n\n \'"+vFileName+"\' is already attached in \'"+dbFilesArray[k][0]+"\'");
                        rtVal=0;
                        return rtVal;
                        break;
                    } 
					
                }					
				if(rtVal == 0)
				{	
					break;						
				}
            }
            if(view == 'ahcAttachments')
            {
                for(k=0;k<=dbFilesCnt;k++)      		
                {
                	rtVal = chkSpecailCharsAttach(sub1);
                    if(rtVal ==0) 
                    {
                    	jqueryAlertMsg('Attachments check'," File Name should not contain special characters",fr);
                     // alert('File Name should not contain special characters'); 
					  rtVal=0;
					  return rtVal;
                      break;
                    }
                    if( vFileName == dbFilesArray[k][1])
                    {
                    	 jqueryAlertMsg('Attachments check'," Cannot Upload similar documents \n\n \'"+vFileName+"\' is already attached in \'"+dbFilesArray[k][0]+"\'",fr);
                       // alert(" Cannot Upload similar documents \n\n \'"+vFileName+"\' is already attached in \'"+dbFilesArray[k][0]+"\'");
                        rtVal=0;
                        return rtVal;
                        break;
                    } 
					
                }					
				if(rtVal == 0)
				{	
					break;						
				}
            }
            var len=vFileName.length;	 
            if(len > 0)
            {
                var pos=vFileName.lastIndexOf(".");
                var sub=vFileName.substring(pos+1,len); 
                if((sub=='exe')||(sub=='EXE') || (sub=='rar')||(sub=='RAR') || (sub=='war')||(sub=='WAR')|| (sub=='zip')||(sub=='ZIP'))
                {
                	ext1=false;	
                }
                if((sub=='jpg')||(sub=='JPG') || (sub=='jpeg')||(sub=='JPEG') || (sub=='gif')||(sub=='GIF')|| (sub=='bmp')||(sub=='BMP')||(sub=='wmf')||(sub=='WMF')||(sub=='pdf')||(sub=='PDF')||(sub=='WRF')||(sub=='wrf'))
                {
                	ext1=true;
                }
                
                if(typ=='onBedPoto' || typ=='aftersurg' || typ == 'dischphoto' || typ=='ClaimClinicalPhoto' || typ == 'ClinicalPhotoPreauth' || typ=='CommitteeApprovalDocument')
                {
                      if((sub=="gif")||(sub=="jpeg")||(sub=="jpg")||(sub=="bmp")||(sub=="WMF")
                              ||(sub=="wmf")||(sub=="GIF")||(sub=="JPEG")||(sub=="JPG")||(sub=="BMP"))
                      {
                        flag="true";			  
                        rtVal =1; 
                        //break;
                      } 
                      else
                      {				
                        flag="false";
                        jqueryAlertMsg('Attachments check'," Please attach a valid photo image (jpg,bmp,gif,wmf).",fr);
                        //alert("The file is not a valid photo image.\nPlease attach a valid photo image.");
                        rtVal =0; 
                        return rtVal;
                        break;                            
                      }
                }

            }
            if(view == 'InspectionAttachments' || view == 'PatientPhotoCMO' || view == 'TelePatAttach' || view == 'EnrollmentAttach')
            {
                for(j=0;j<=attachcnt;j++)
                {
                    if(vFileName == attachmentArray[j][1])
                    {
                    	jqueryAlertMsg('Attachments check'," Cannot Upload similar documents  \n\n \'"+vFileName+"\' is already attached in \'"+attachmentArray[j][0]+"\'",fr);
                       // alert("Cannot Upload similar documents  \n\n \'"+vFileName+"\' is already attached in \'"+attachmentArray[j][0]+"\'");
                        rtVal=0;
                        break;
                    }                                    
                    rtVal = chkSpecailChars(vFileName);
                    if(rtVal ==0) 
                    {
                    	jqueryAlertMsg('Attachments check'," File Name should not contain special characters",fr);
                     // alert('File Name should not contain special characters'); 
                      break;
                    }
                }									
				if(rtVal == 0)
				{	
					break;						
				}
            }
            if(typ=='billattach' || typ == 'casesheetattach')
            {
                  if((sub=='PDF')||(sub=='pdf'))
                  {
                          flag="true";	
                          rtVal =1;
                          //break;
                  }
                  else
                  {				
                    flag="false";
                    jqueryAlertMsg('Attachments check'," Attach PDF file only",fr);
                   // alert("Attach PDF file only");
                    rtVal =0; break;
                  }
            }
            if(typ=='webExRec' || typ=='ClaimWebexRecording')
            {
                  if((sub=='WRF')||(sub=='wrf'))
                  {
                          flag="true";	
                          rtVal =1;
                          //break;
                  }
                  else
                  {				
                        flag="false";
                        jqueryAlertMsg('Attachments check'," Attach WRF file only");
                       // alert("Attach WRF file only");
                        rtVal =0; break;
                  }
             }
            if(!ext1)
        	  {
          	
              jqueryAlertMsg('Attachments check'," Cannot Upload "+sub + " files . Please attach gif,jpg,bmp,wmf,wrf,pdf formats only",fr);
             
              // alert("Cannot Upload exe,rar,war files");
              rtVal =0;
              return rtVal;
              break;
        	  }
        
   }   
   return rtVal;
}
function checkMandatoryRemarks(vAttachCnt,typ)
{
   var rtVal = 1;
   var vFileName=''; var vSplit;
   for(i=1;i<=vAttachCnt;i++)
   {
    vFileName = document.getElementById(typ+i).value;
    if(vFileName == '')
    {
       alert("All Attachments are mandatory");
       rtVal = 0; break;
    }
    else
    {
      vSplit=vFileName.split("\\");
      vFileName = vSplit[(vSplit.length)-1];
      rtVal = chkSpecailChars(vFileName);
      if(rtVal ==0) 
      {
         alert('File Name should not contain special characters'); 
         break;
      }
      if(document.getElementById("remarks"+typ+i).value== '')
      {
        alert("All Remarks are mandatory");    
        rtVal = 0;
        break;
      }
    }
   }
   return rtVal;
}
function checkSimilarUploads(vAtCnt,typ)
{
  var rtVal = 1;
  var vFileName; var vFileName2;
  var vSplit; var vSplit2;  
  var x;
  var ext =false;
  for(i=1;i<=vAtCnt;i++) 
  {
     vFileName = document.getElementById(typ+i).value;
     vSplit=vFileName.split("\\");
     vFileName = vSplit[(vSplit.length)-1];		 
    for(j=i+1;j<=vAtCnt;j++)
     { 
          vFileName2 = document.getElementById(typ+j).value;
          vSplit2=vFileName2.split("\\");
          vFileName2 = vSplit2[(vSplit.length)-1];
          if(vFileName2 == vFileName)
          {
        	  jqueryAlertMsg('Attachments check'," Cannot Upload similar documents");
            // alert("Cannot Upload similar documents");
             rtVal =0; break;
          }
     }   
     if(rtVal ==0)  { break; }
     var len=vFileName.length;	 
      if(len > 0)
      {
          var pos=vFileName.lastIndexOf(".");
          var sub=vFileName.substring(pos+1,len); 
          if((sub=='exe')||(sub=='EXE') || (sub=='rar')||(sub=='RAR') || (sub=='war')||(sub=='WAR')|| (sub=='zip')||(sub=='ZIP'))
          {
              flag="false";
              jqueryAlertMsg('Attachments check'," Cannot Upload exe,rar,war files");
             // alert("Cannot Upload exe,rar,war files");
              rtVal =0; break;
          } 
          /*for(var i=0;i<=lstFileExtensArray.length;i++)
        	  {
        	  if(lstFileExtensArray[i]==sub)
        		  {
        		  ext=true;
        		  break;
        		  }
        	  else
        		  {
        		  ext=false;
        		  }
        	  }*/
          
          if((sub=='jpg')||(sub=='JPG') || (sub=='jpeg')||(sub=='JPEG') || (sub=='gif')||(sub=='GIF')|| (sub=='bmp')||(sub=='BMP')||(sub=='wmf')||(sub=='WMF')||(sub=='pdf')||(sub=='PDF')||(sub=='WRF')||(sub=='wrf'))
          {
        	  ext=true;
          } 
          
          if(typ=='onBedPoto' || typ=='aftersurg' || typ == 'dischphoto' || typ=='ClaimClinicalPhoto' || typ == 'ClinicalPhotoPreauth' || typ=='CommitteeApprovalDocument')
          {
                if((sub=="gif")||(sub=="jpeg")||(sub=="jpg")||(sub=="bmp")||(sub=="WMF")
                        ||(sub=="wmf")||(sub=="GIF")||(sub=="JPEG")||(sub=="JPG")||(sub=="BMP"))
                {
                  flag="true";			  
                  rtVal =1; 
                  //break;
                } 
                else
                {				
                  flag="false";
                  jqueryAlertMsg('Attachments check'," Please attach a valid photo image (jpg,bmp,gif,wmf).");
                  //alert("The file is not a valid photo image.\nPlease attach a valid photo image.");
                  rtVal =0; break;                            
                }
          }
		  if(typ == 'cardproof' || typ == 'addressAttach' || typ == 'photoAttach' || typ == 'kioskOnbedPhoto' || typ == 'PhotoID' || typ == 'patientPhotoWithDefect' || typ == 'empPhoto') 
		  {
			if((sub=="jpeg") || (sub=="gif") || (sub=="JPEG") ||(sub=="jpg") ||(sub=="GIF") ||(sub=="JPG"))
			{
			  flag="true";			  
			  rtVal =1; 
			  //break; 
			}
			else
			{
			  flag="false";
			  jqueryAlertMsg('Attachments check'," Please attach JPEG/JPG/GIF format only");
			  //alert("Please attach JPEG/JPG/GIF format only");
			  rtVal =0;
			  break;
			}
		  }
          if(typ=='angiogram')
          {
                if((sub=='WRF')||(sub=='wrf'))
                {
                        flag="true";	
                        rtVal =1;
                        //break;
                }
                else
                {				
                      flag="false";
                      jqueryAlertMsg('Attachments check'," Attach WRF file only");
                     // alert("Attach WRF file only");
                      rtVal =0; break;
                }
           }
          if(typ=='billattach' || typ == 'casesheetattach')
          {
                if((sub=='PDF')||(sub=='pdf'))
                {
                        flag="true";	
                        rtVal =1;
                        //break;
                }
                else
                {				
                  flag="false";
                  jqueryAlertMsg('Attachments check'," Attach PDF file only");
                 // alert("Attach PDF file only");
                  rtVal =0; break;
                }
          }
          if(!ext)
    	  {
    	  flag="false";
          jqueryAlertMsg('Attachments check'," Cannot Upload "+sub + " files . Please attach gif,jpg,bmp,wmf,wrf,pdf formats only");
         // alert("Cannot Upload exe,rar,war files");
          rtVal =0; break;
    	  }
     } 
  }
  return rtVal;
}


function saveAttachment1(typ,vAttachCnt)
{
  var vAttachCntdata = document.getElementById(vAttachCnt).value;
  submitCall(typ,'addAtachments',vAttachCntdata);  
} 

function chkSpecailCharsAttach(vFileName)
{
   var val =1;  
   var iChars = "*|\":<>[]{}`\';()$#%&^.,!@?/"; 
   var iChars1="-_";
    for (var i = 0; i < vFileName.length; i++) 
    {
        if (iChars.indexOf(vFileName.charAt(i)) != -1)
        {  
          val = 0; break;
         
        } 
    }
    if( vFileName.match(/[\-\_]{2}/i))
	{
	 var val =0;
	}
    if (iChars1.indexOf(vFileName.charAt(0))!=-1 || iChars1.indexOf(vFileName.charAt(vFileName.length-1)) != -1)
	{
	 var val =0;
	}
    return val;
} 