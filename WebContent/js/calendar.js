function CalenderWindowOpen(val,x,y)
{

//parameter val contains the name of the textbox
var newWindow;

var urlstring = '/Registration/common/Calendar.jsp?requestSent='+val;
var urlstyle = 'height=170,width=268,toolbar=no,minimize=no,status=yes,resizable=no,menubar=no,location=no,scrollbars=no,top='+x+',left='+y;
newWindow = window.open(urlstring,'Calendar',urlstyle);
//newWindow.moveTo(10, 10);
}