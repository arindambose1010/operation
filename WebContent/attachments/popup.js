
closePopUp();


function closePopUp()
{
		if(window.opener)
		{
			if(window.opener.closed)
			{
			  self.close();
			}
			else
			{
			setTimeout("closePopUp()",10);
			}
		}
}