function biometricAttendance(UserRole)
{
var authenticateSystem=false; 
var macaddress;  
var sysmacids;
var obj = new ActiveXObject("WbemScripting.SWbemLocator");
var s = obj.ConnectServer(".");
var properties = s.ExecQuery("SELECT * FROM Win32_NetworkAdapterConfiguration");
var e = new Enumerator (properties);
while(!e.atEnd())
{
e.moveNext();
var p = e.item ();
if(!p) continue;
macaddress=macaddress+"~"+p.MACAddress;
}

var arr=macaddress.split("~");
  for(var i=0;i<arr.length;i++)
  {
      var temp=arr[i].replace(/:/g,"-");
if(temp && temp!=null){
	if(sysmacids== null){
       sysmacids = temp;
	}else{
	sysmacids = sysmacids +","+ temp;
	}
}
  }

return sysmacids;
//sysmacids="00-24-7E-0B-F5-59";
//var ContactsURL = "biometricAction.do?actionFlag=displayBioCapture&userId="+UserRole+"&macActVal="+sysmacids;
//window.open(ContactsURL,'BiometricAttendance','width=550, height=200, top=260,left=170,resizable=no,titlebar=no,scrollbars=yes,menubar=no,toolbar=no');
}