$(document).ready(function() {
for(var i=1 ; i <=10; i++)
{
var dropbox1 = document.getElementById("dropbox"+i);

if(dropbox1 != null){
// init event handlers
dropbox1.addEventListener("dragenter", dragEnter, false);
dropbox1.addEventListener("dragexit", dragExit, false);
dropbox1.addEventListener("dragover", dragOver, false);
dropbox1.addEventListener("dragleave", dragLeave, false);
dropbox1.addEventListener("drop", dropPhoto, false);

}
}// end of for loop


// adding static style sheets for drag n drop 


$("#dropbox1").on('dragover', function (e) { 
	$("#dropbox1").addClass("upload-cont");
});
$("#dropbox1").on('drop', function (e) {
	$("#dropbox1").removeClass("upload-cont");
});
$("#dropbox1").on('dragleave', function (e) {
	$("#dropbox1").removeClass("upload-cont");
});
$("#dropbox2").on('dragover', function (e) { 
	$("#dropbox2").addClass("upload-cont");
});
$("#dropbox2").on('drop', function (e) {
	$("#dropbox2").removeClass("upload-cont");
});
$("#dropbox2").on('dragleave', function (e) {
	$("#dropbox2").removeClass("upload-cont");
});
$("#dropbox3").on('dragover', function (e) { 
	$("#dropbox3").addClass("upload-cont");
});
$("#dropbox3").on('drop', function (e) {
	$("#dropbox3").removeClass("upload-cont");
});
$("#dropbox3").on('dragleave', function (e) {
	$("#dropbox3").removeClass("upload-cont");
});
$("#dropbox4").on('dragover', function (e) { 
	$("#dropbox4").addClass("upload-cont");
});
$("#dropbox4").on('drop', function (e) {
	$("#dropbox4").removeClass("upload-cont");
});
$("#dropbox4").on('dragleave', function (e) {
	$("#dropbox4").removeClass("upload-cont");
});


$("#dropbox5").on('dragover', function (e) { 
	$("#dropbox5").addClass("upload-cont");
});
$("#dropbox5").on('drop', function (e) {
	$("#dropbox5").removeClass("upload-cont");
});
$("#dropbox5").on('dragleave', function (e) {
	$("#dropbox5").removeClass("upload-cont");
});
$("#dropbox6").on('dragover', function (e) { 
	$("#dropbox6").addClass("upload-cont");
});
$("#dropbox6").on('drop', function (e) {
	$("#dropbox6").removeClass("upload-cont");
});
$("#dropbox6").on('dragleave', function (e) {
	$("#dropbox6").removeClass("upload-cont");
});
$("#dropbox7").on('dragover', function (e) { 
	$("#dropbox7").addClass("upload-cont");
});
$("#dropbox7").on('drop', function (e) {
	$("#dropbox7").removeClass("upload-cont");
});
$("#dropbox7").on('dragleave', function (e) {
	$("#dropbox7").removeClass("upload-cont");
});
$("#dropbox8").on('dragover', function (e) { 
	$("#dropbox8").addClass("upload-cont");
});
$("#dropbox8").on('drop', function (e) {
	$("#dropbox8").removeClass("upload-cont");
});
$("#dropbox8").on('dragleave', function (e) {
	$("#dropbox8").removeClass("upload-cont");
});

$("#dropbox9").on('dragover', function (e) { 
	$("#dropbox9").addClass("upload-cont");
});
$("#dropbox9").on('drop', function (e) {
	$("#dropbox9").removeClass("upload-cont");
});
$("#dropbox9").on('dragleave', function (e) {
	$("#dropbox9").removeClass("upload-cont");
});
$("#dropbox10").on('dragover', function (e) { 
	$("#dropbox10").addClass("upload-cont");
});
$("#dropbox10").on('drop', function (e) {
	$("#dropbox10").removeClass("upload-cont");
});
$("#dropbox10").on('dragleave', function (e) {
	$("#dropbox10").removeClass("upload-cont");
});



}); // end 

function dragEnter(evt) {
	evt.stopPropagation();
	evt.preventDefault();
}

function dragExit(evt) {
	evt.stopPropagation();
	evt.preventDefault();
}
function dragLeave(evt)
{
	evt.stopPropagation();
	evt.preventDefault();
	}

function dragOver(evt) {
	evt.stopPropagation();
	evt.preventDefault();
}


function dropPhoto(evt) {
	var dropId = this.id.replace(/dropbox/g,"");
	values=dropArray[dropId-1];
	//alert(values);
	var docTypes=values.split("~");
	var docType=docTypes[0];
	var cmbDtlId=docTypes[1];
	var caseId=docTypes[2];
	evt.stopPropagation();
	evt.preventDefault();
	var files = evt.dataTransfer.files;
	var count = files.length;

// check similar uploads from dbfiles

for(i=0;i<count;i++)
{
	var vFileName = files[i].name;
	 for(k=0;k<=dbFilesCnt;k++)      		
     {						
         if( vFileName == dbFilesArray[k][1])
         {
             alert(" Cannot Upload similar documents \n\n \'"+vFileName+"\' is already attached in \'"+dbFilesArray[k][0]+"\'");
             rtVal=0;
             return;
             break;
         } 
			rtVal = chkSpecailChars(vFileName);
         if(rtVal ==0) 
         {
           alert('File Name should not contain special characters'); 
			  rtVal=0;
			  return;
           break;
         }
     }					
		if(rtVal == 0)
		{	
			return;
			break;						
		}
}

	
	var isExeCorrect=checkFileExtension1(files[0].name);
	
	var isSizeCorrect=checkFileSize(files[0].size);
	
	if(isExeCorrect==true && isSizeCorrect==true)
	{
	 var formdata = new FormData();
     formdata.append("attachment", files[0]);

     var xhr = new XMLHttpRequest();       

     xhr.open("POST","/Operations/dataentry?actionFlag=uploadPreauthAttachments&docType="+docType+"&caseId="+caseId+"&attachType="+cmbDtlId, true);

     xhr.send(formdata);

     xhr.onload = function(e) {

         if (this.status == 200) {
        	 var result=this.responseText;
        	 alert(result);
        	 url="/Operations/attachmentAction.do?actionFlag=onload&UpdType=ehfPreauth&caseId="+caseId+"&caseAttachmentFlag=Y";      
      	   document.forms[0].action=url;   
      	   document.forms[0].submit();
          
         }
     };
	}
}

function checkFileExtension1(name)
{  
     var filename;
     filename = name;   
     if(filename!='')
     var len= filename.length;  
     if(len>0)
     {
        var pos=filename.lastIndexOf(".");
        var sub=filename.substring(pos+1,len);   
        var extension=sub.toLowerCase();
        if(( extension =="pdf")||(extension=="doc")||(extension=="xls")||(extension=="xlxs")||(extension=="txt")||(extension=="xlsx")
        		||(extension=="jpeg")||(extension=="jpg")||(extension=="png")||(extension=="gif") || (extension=="zip"))
        {
        	return true;
        }
        else
        {        
            alert("Please attach valid document  (PDF/DOC/XLS/XLXS/TXT/XLSX/JPEG/JPG/GIF/PNG/ZIP)");
            
            return false;
        }      
    }     
     
}

function checkFileSize(size)
{
 if(size>204800)
	 {
	  alert("File size should be less than 200kb");
	
	  return false;
	 }
  return true;
}

