<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AAROGYASRI HEALTHCARE TRUST</title>
		<style type="text/css">
			html,body{margin:0px;padding:0px;}
			.div1{border:0px;}
			.span1{display:block;margin:auto;border:1px solid grey;padding:8px;width:980px;}
			.table1{width:100%;border:0px solid black;border-collapse:collapse;margin:auto;}
			.table1 td{padding:0;margin:0;border:0px solid red;}
			.table2{border:1px solid black;border-collapse:collapse;width:80%;margin:auto;}
			.table2 td{border:1px solid grey;padding:1px;padding-left:3px;}
			.table2 thead td{background:#EEEEEE;}
		</style>
</head>

<script>

function fn_convertMoney(amt,divid)   
{  
    var junkVal = String(amt);
	junkVal = junkVal.replace(/,/g,"");
    junkVal=Math.floor(junkVal);    
    var obStr=new String(junkVal);  
    numReversed=obStr.split("");   
    actnumber=numReversed.reverse();   
    if(Number(junkVal) <0)   
    {   
        return false;   
    }   
    if(Number(junkVal)==0)   
    {   
        document.getElementById('container').innerHTML=obStr+''+'Rupees Zero Only';   
        return false;   
    }   
    if(actnumber.length>11)   
    {   
        return false;   
    }   
  
    var iWords=["Zero", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine"];   
    var ePlace=['Ten', ' Eleven', ' Twelve', ' Thirteen', ' Fourteen', ' Fifteen', ' Sixteen', ' Seventeen', ' Eighteen', ' Nineteen'];   
    var tensPlace=['dummy', ' Ten', ' Twenty', ' Thirty', ' Forty', ' Fifty', ' Sixty', ' Seventy', ' Eighty', ' Ninety' ];   
  
    var iWordsLength=numReversed.length;   
    var inWords=new Array();   
    var finalWord="";   
    j=0;   
    for(var i=0; i<iWordsLength; i++)   
    {   

        switch(i)   
        {   
            case 0:   
            if(actnumber[i]==0 || actnumber[i+1]==1 )   
            {   
                inWords[j]='';   
            }   
            else  
            {   
                inWords[j]=iWords[actnumber[i]];   
            }   
            inWords[j]=inWords[j]+' Only ';   
            break;   
        case 1:   
            tens_complication();   
            break;   
        case 2:   
            if(actnumber[i]==0)   
            {   
                inWords[j]='';   
            }   
            else if(actnumber[i-1]!=0 && actnumber[i-2]!=0)   
            {   
                inWords[j]=iWords[actnumber[i]]+' Hundred and';   
            }   
            else  
            {   
                inWords[j]=iWords[actnumber[i]]+' Hundred';   
            }   
            break;   
        case 3:		
	          if(actnumber[i]==0 && actnumber[i+1]==0)
	          {
                   inWords[j]='';
	          }		
			 else if(actnumber[i]==0 || actnumber[i+1]==1)   
            {   
                inWords[j]=''+"Thousand";  
				 	
            } 			
            else  
            {   
                inWords[j]=iWords[actnumber[i]]+"Thousand"; 
								
            }   

	      break;   
        case 4:   
            tens_complication();   
            break;   
        case 5:   
			if(actnumber[i]==0 && actnumber[i+1]==0)
			{
              inWords[j]='';
			}	
            else if(actnumber[i]==0 || actnumber[i+1]==1 )   
            {   
                inWords[j]=''+" Lakh";   
            }   
            else  
            {   
                inWords[j]=iWords[actnumber[i]]+" Lakh"; 
				
            }   
			
              
            break;   
         case 6:   
            tens_complication();   
            break;   
         case 7:   
			if(actnumber[i]==0 && actnumber[i+1]==0)
			{
              inWords[j]='';
			}	
            else if(actnumber[i]==0 || actnumber[i+1]==1 )   
            {   
                inWords[j]=''+" Crore";   
            }   
            else  
            {   
                inWords[j]=iWords[actnumber[i]]+" Crore";   
            }   
         
            break;   
        case 8:   
            tens_complication();   
            break;   

		case 9:   
			if(actnumber[i]==0 && actnumber[i+1]==0)
			{
              inWords[j]='';
			}	
            else if(actnumber[i]==0 || actnumber[i+1]==1 )   
            {   
                inWords[j]=''+"  Billion ";   
            }   
            else  
            {   
                inWords[j]=iWords[actnumber[i]]+" Billion ";   
            }   
         
            break;   
        case 10:   
            tens_complication();   
            break;   

        default:   
            break;   
        }   
        j++;   
    }   
  
    function tens_complication()   
    {   
        if(actnumber[i]==0)   
        {   
            inWords[j]='';   
        }   
        else if(actnumber[i]==1)   
        {   
            inWords[j]=ePlace[actnumber[i-1]];   
        }   
        else  
        {   
            inWords[j]=tensPlace[actnumber[i]];   
        }   
    }   
    inWords.reverse();   
    for(i=0; i<inWords.length; i++)   
    {   
        finalWord+=inWords[i];   
		
    }
	
	if(document.getElementById(divid)!=null)
		{
		
		document.getElementById(divid).innerHTML=finalWord;  
		document.getElementById('convert1').innerHTML=finalWord;  
		}
		 

}

function printPage() {
    var printButton = document.getElementById("printBtn");
    printButton.style.visibility = 'hidden';
    window.print();
    printButton.style.visibility = 'visible';
}

</script>

<body onload="fn_convertMoney(<bean:write name='adminSanctionForm' property='sanctionAmount'/>,'convert')">
<form name = "adminSanctionForm" method="post" action="/adminSanction.do" enctype="multipart/form-data">

		<div class='div1'>
			<span class='span1'>
			<table class='table1'>
				<tr><td colspan='10' style='font-size:25px;'><center><b>AAROGYASRI HEALTHCARE TRUST</b></center></td></tr>
				<logic:equal property="scheme" name="adminSanctionForm" value="SC13">
 				<tr><td colspan='10' style='font-size:20px;'><center><b>(A GoAP TRUST)</b></center></td></tr>
 				</logic:equal>
 				<logic:equal property="scheme" name="adminSanctionForm" value="SC14">
 				<tr><td colspan='10' style='font-size:20px;'><center><b>(A GoTG TRUST)</b></center></td></tr>
 				</logic:equal>
				<tr><td style='width:10%;'>&nbsp;</td><td style='width:10%;'>&nbsp;</td><td style='width:10%;'>&nbsp;</td><td style='width:10%;'>&nbsp;</td><td style='width:10%;'>&nbsp;</td><td style='width:10%;'>&nbsp;</td><td style='width:10%;'>&nbsp;</td><td style='width:10%;'>&nbsp;</td><td style='width:10%;'>&nbsp;</td><td style='width:10%;'>&nbsp;</td></tr>
				<tr><td colspan='10'>&nbsp;</td></tr>
				<tr><td colspan='10'><center>PROCEEDINGS OF THE 
				<logic:equal property="issuingAuthority" name="adminSanctionForm" value="CEO">
				CHIEF EXECUTIVE OFFICER
				</logic:equal>
				<logic:equal property="issuingAuthority" name="adminSanctionForm" value="EO_ADMIN">
				EXECUTIVE OFFICER (ADMIN)
				</logic:equal>
				:: AAROGYASRI HEALTHCARE TRUST</center></td></tr>
				<tr><td colspan='10'><center>Present: Sri /Ms <bean:write name="adminSanctionForm" property="issuingAuthorityName"/></center></td></tr>
				<tr><td colspan='10'>&nbsp;</td></tr>
				<tr><td colspan='7'>Proceedings.No. <bean:write name="adminSanctionForm" property="sanctionId"/> </td><td colspan='3'>Dated: <bean:write name="adminSanctionForm" property="date"/> </td></tr>
				<tr><td colspan='10'>&nbsp;</td></tr>
				<tr><td>&nbsp;</td><td colspan='9'>Sub:  AHCT – Administrative sanction for incurring an expenditure of Rs <bean:write name="adminSanctionForm" property="sanctionAmount"/> (Rupees <span id="convert1"></span>) towards  <bean:write name="adminSanctionForm" property="subject"/> - Orders issued.</td></tr>
				<tr><td>&nbsp;</td><td colspan='9'>Ref:  <bean:write name="adminSanctionForm" property="reference"/> </td></tr>
				<tr><td colspan='10' style='text-decoration:underline;'>ORDER</td></tr>
				<tr><td colspan='10' >
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;After careful consideration, sanction is hereby accorded by the undersigned as per the delegation of powers in vogue, for incurring an estimated expenditure of 
						Rs. <bean:write name="adminSanctionForm" property="sanctionAmount"/> (Rupees <span id="convert"></span>)  towards  procurement  of  the  following  item(s) /execution of the following work by 
						<bean:write name="adminSanctionForm" property="deptName"/>  department , under the Scheme "
						<logic:equal property="scheme" name="adminSanctionForm" value="SC13">
						Employe Health Scheme AP
						</logic:equal>
						<logic:equal property="scheme" name="adminSanctionForm" value="SC14">
						Employe Health Scheme TG
						</logic:equal>" :
				</td></tr>
				<tr><td colspan='10'>&nbsp;</td></tr>
				<tr><td colspan='10'>
				
				
				<logic:equal property="specFlag" value="Y" name="adminSanctionForm">
				<table class='table2'>
						<thead>
							<tr>
								<td style='width:40px;'><center>S.No.</center></td>
								<td style='width:150px;'>Item of supply/work</td>
								<td>Specification</td>
								<td style='width:100px;'>Cost</td>
							</tr>
						</thead>
						<tbody>
<%
System.out.println("Before For");

String specString =(String)request.getAttribute("specString");
String costString = (String)request.getAttribute("costString");

String[] specList=specString.split("~");
String[] costList=costString.split("~");

for(int sci=1;sci<=specList.length;sci++){
																						
String sp = specList[sci-1];
String cs = costList[sci-1];

if(sp==null)break;%>
	<tr>
	<td><center><%=sci%></center></td>
	<td><bean:write name="adminSanctionForm" property="typoOfSanction"/>  </td>
  <td><%=sp%></td>
  <td><%=cs%></td>
</tr>		
<%}%>		

		</tbody>
	</table>
	</logic:equal>
				</td></tr>
				<tr><td colspan='10'>&nbsp;</td></tr>
				<tr><td colspan='10'>2.&nbsp;&nbsp;&nbsp;The above sanction is subject to the following conditions:- </td></tr>
				<tr><td style='text-align:right;'>1.&nbsp;</td><td colspan='9'>The purchases shall be made strictly in accordance with the existing procedures laid down by Trust. </td></tr>
				<tr><td style='text-align:right;'>2.&nbsp;</td><td colspan='9'>The amount is utilized after observing all codal formalities required under the rules.</td></tr>
				<tr><td style='text-align:right;'>3.&nbsp;</td><td colspan='9'>That the statement of expenditure and utilization certificate is submitted to the undersigned on weekly/quarterly basis.</td></tr>
				<tr><td style='text-align:right;'>4.&nbsp;</td><td colspan='9'>The sanction shall be valid up to maximum period of <bean:write name="adminSanctionForm" property="sanctionOrderDate"/> months from the date of issue or the financial year ending, whichever is earlier.</td></tr>
				<tr><td style='text-align:right;'>5.&nbsp;</td><td colspan='9'>The material available including these purchases should be consumed within a period of <bean:write name="adminSanctionForm" property="purchaseDate"/>, failing which no further sanction shall be issued. </td></tr>
				<tr><td style='text-align:right;'>6.&nbsp;</td><td colspan='9'>The <bean:write name="adminSanctionForm" property="inspectionAuthority"/>  shall inspect the supplies / work.</td></tr>
				<tr><td style='text-align:right;'>7.&nbsp;</td><td colspan='9'>The <bean:write name="adminSanctionForm" property="executingAuthority"/> shall execute the works.</td></tr>
				
				<tr><td colspan='10'>&nbsp;</td></tr>
				<tr><td colspan='10'>3.&nbsp;&nbsp;&nbsp;The expenditure is debitable to the head of account <bean:write name="adminSanctionForm" property="detailedHeadName"/> (<bean:write name="adminSanctionForm" property="detailedHead"/> ).</td></tr>
				<tr><td colspan='10'>4.&nbsp;&nbsp;&nbsp;This sanction is issued after having satisfied that the proposed purchases /procurement / works are absolutely essential for implementing the Scheme and that there seems to be no overstocking/over procuring of the materials.</td></tr>
				<tr><td colspan='10'>5.&nbsp;&nbsp;&nbsp;The Joint Executive Officer (Finance) shall make payments to the concerned after due scrutiny of the bills.</td></tr>
				<tr><td colspan='10'>&nbsp;</td></tr>
				<tr><td colspan='10'>&nbsp;</td></tr>
				<tr><td colspan='10'>&nbsp;</td></tr>
				<tr><td colspan='10' style='text-align:right;'>EXECUTIVE OFFICER (ADMIN) / CHIEF EXECUTIVE OFFICER</td></tr>
				<tr><td colspan='10'>&nbsp;</td></tr>
				<tr><td colspan='10'>To</td></tr>
				<tr><td colspan='10'><b><bean:write name="adminSanctionForm" property="toBeIssuedOn"/></b></td></tr>
				<tr><td colspan='10'>Copy to:</td></tr>
				<tr><td colspan='10'>The Joint Executive Officer (Finance), AHCT.</td></tr>
				<tr><td colspan='10'>The Joint Executive Officer (Admn), AHCT.</td></tr>
				<tr><td colspan='10'>PS to CEO.,AHCT.</td></tr>
				<tr><td colspan='10'>SF / SC</td></tr>
				<tr><td colspan='10'>&nbsp;</td></tr>
				<tr><td colspan='10'>Door No.8-2-293/82/a/ahct, Road No.46, Jubilee Hills, Hyderabad – 500 033</td></tr>
				<tr><td colspan='10'>&nbsp;</td></tr>
				<tr><td colspan='10'>&nbsp;</td></tr>
				<tr><td colspan='10' align="center"><button class="but" id="printBtn"  name="printBtn" tabindex="0" type="button"  value="print" title="Print form" onClick="javascript:printPage()">Print</button></td></tr>
			 	
			</table>
			</span>
		</div>
		</form>
</body>
</html>