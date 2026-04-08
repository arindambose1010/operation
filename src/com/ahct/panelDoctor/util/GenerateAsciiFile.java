
package com.ahct.panelDoctor.util;

import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.apache.log4j.Logger;


public class GenerateAsciiFile 
{
    private static final Logger LOGGEROBJ = Logger.getLogger(GenerateAsciiFile.class);
    private static final String EMPTY_STRING = "";
    private static final String DD_MM_YY_STRING = "dd/MM/yyyy";
    private static final String DOT_STRING = ".";
    private static final String CAMA_STRING =",";
    private static final String SPACE_STRING = " ";
    private static final String SBHY_STRING = "SBHY";
    private static final String GROUP_STRING = "GROUP";
    private static final String T_STRING = "T";
    private static final String I_STRING = "I";
    private static final String G_STRING = "G";
    private static final String R_STRING = "R";
    private static final String N_STRING = "N";
    private static final String ZERO_STRING = "0";
    private static final String DOUBLE_ZERO_STRING = "00";
       /**
     * The piece of code below does iteration for the various columns of each row of
     * the result set 
     */

 public HashMap generateAsciiFile(ArrayList lContentList,HashMap lparamMap)
 {
              
     String lStrFileSNo="";
     int flag=0,lTotCnt=0; 
     double lTransAmt=0,lTotAmt=0;//002
     String lStrTransType="",lStrBankCode="",lStrTodatDate="",lStrBankId="";         
     String lStrUniqueId="",lStrBnfActName="",lStrBnfActId="",lStrBnfAddr="";
     String lStrBnfBankName="",lStrBnfBankBranch="",lStrBnfActNo="",lStrTransAmt="";
     String lStrSrcActNo="",lStrTrans_Type="",lStrIfcCode="",lStrEmailId="";
     String lStrClaintCode="",lStrFileName="";
     
     ArrayList lDataList=new ArrayList();
     ArrayList lFileList=new ArrayList();
     ArrayList lTempList=new ArrayList();
    // ArrayList lCaseIdList=new ArrayList();
     HashMap lFileMap=new HashMap();
     

     //Writing a ASCII file for upload a file in Hdfc Site
     try
     {
    	 if(lContentList.size()>0)
         {
            lFileList=(ArrayList)lContentList.get(0);
            //This Number is to set filename and length must be 3 or 4
            lStrFileSNo=(String)lContentList.get(1);
         }
     
     // This File list contains ArrayList of Arraylist
      if(lFileList.size()>0)
      {
        
        StringBuffer lColumns=new StringBuffer("");
        
         /**
         * FileName format *
         * File Name:ARGT1011.001 *
         *I) Fisrt 4 letters are Claint code then follwoed by date and month. 
         *II)File extension is any serial number (Length sould be 3 0r 4)
         * Transaction Types:
         * (I)nternal to Bank/(G)roup State Bank/( R)TGS above 1 Lack/(N)EFT below 1 Lack
         */           
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        lStrTodatDate=df.format(date); 
        String lStrDay =lStrTodatDate.substring(0,2);
        String lStrMonth=lStrTodatDate.substring(3,5);
        String lStrYear = lStrTodatDate.substring(8,10); // 001
      
         int FileListSize=lFileList.size();
        for(int i=0;i<FileListSize;i++)
        {
        
        lDataList=new ArrayList(); 
        lTempList=new ArrayList();
        //HashMap Contains the Beneficiary Account Information
        lFileMap=(HashMap)lFileList.get(i);  
           
        lStrUniqueId=(String)lFileMap.get("UNIQUE_ID");
        lStrBnfActName=(String)lFileMap.get("BENEFICIARY_ACCOUNT_NAME");
        lStrBnfActId=(String)lFileMap.get("BENEFICIARY_ID");
        lStrBnfAddr=(String)lFileMap.get("BENEFICIARY_ADDR");
        lStrBnfBankName=(String)lFileMap.get("BENEFICIARY_BANK_NAME");
        lStrBnfBankBranch=(String)lFileMap.get("BANK_BRANCH");
        lStrBnfActNo=(String)lFileMap.get("BENEFICIARY_ACCOUNT_NO");
        lTransAmt=(Double) lFileMap.get("TRANSACTION_AMOUNT");
        lStrTransAmt=String.valueOf(lTransAmt);
     //   lTransAmt=Integer.parseInt(lStrTransAmt);
       // lTransAmt=Double.parseDouble(lStrTransAmt);//002
        lTotAmt=lTotAmt+lTransAmt;
        lStrBankId=(String)lFileMap.get("BENEFICIARY_BANK_ID");
        lStrIfcCode=(String)lFileMap.get("BENEFICIARY_BANK_IFC_CODE");
        lStrClaintCode=(String)lFileMap.get("CLAINT_AC_CODE");
        lStrSrcActNo=(String)lFileMap.get("CLAINT_AC_NUMBER");
        lStrTrans_Type=(String)lFileMap.get("TRANSACTION_TYPE");
        lStrEmailId=(String)lFileMap.get("EMAIL_ID");                
        if(lStrEmailId==null)
        	lStrEmailId="";
        if(lStrTrans_Type==null)
        	lStrTrans_Type="";
        //lStrBankCode =lStrBankId;
        lStrBankCode=lStrIfcCode.substring(0,4);
         if(lStrBnfBankBranch.trim().length()<1)
             lStrBnfBankBranch=lStrBnfBankBranch.trim();
             
         if(lStrBnfActName.length()>0)
          {
             lStrBnfActName=lStrBnfActName.replaceAll(","," ");
             lStrBnfActName=lStrBnfActName.trim();                
          }    
         if(lStrBnfBankBranch.length()>0)
          {
             lStrBnfBankBranch=lStrBnfBankBranch.replaceAll(","," ");
             lStrBnfBankBranch=lStrBnfBankBranch.trim();                
          }
          
         if(lStrBnfAddr.length()>0)
         {
             lStrBnfAddr=lStrBnfAddr.replaceAll(","," ");
             lStrBnfAddr=lStrBnfAddr.trim();                
         }
             
        if(lStrUniqueId.length()>0 && lStrBnfActName.length()>0 && lStrBnfActId.length()>0 && lStrBnfAddr.length()>0 && lStrBankId.length()>0 &&
            lStrBnfBankName.length()>0 && lStrBnfBankBranch.length()>0 && lStrBnfActNo.length()>0 && lStrBankCode.length()>0 && lStrIfcCode.length()>0 && lStrSrcActNo.length()>0) 
         {    
            if(lStrBankCode.equalsIgnoreCase("SBHY"))// Source Bank ID (HDFC or SBHY)
            {                  
                lStrTransType="I";//TransTypeInternal
            }
            else if(lStrTrans_Type.equals("GROUP"))
            {                   
                 lStrTransType="G";//TransTypeGroup
            }
            else
            {
               /*if(lTransAmt>200000) 
               lStrTransType="R";//TransTypeRTGS
               else*/
               lStrTransType="N";//TransTypeNEFT
            }
            
            if(lStrBnfBankBranch.trim().length()<1)
                lStrBnfBankBranch=lStrBnfBankBranch.trim();
           //the piece of code below generate in each row 29 field format
            int lColumnsPos[]={0,1,2,3,4,0,0,7,8,0,0,0,0,13,0,0,0,0,0,0,0,0,22,0,24,25,26,27,28};
            String lCoumnsValues[]={lStrTransType,lStrBnfActId,lStrBnfActNo,lStrTransAmt,lStrBnfActName,
                                    lStrBnfAddr,lStrBnfAddr,lStrUniqueId,lStrTodatDate,lStrIfcCode,lStrBnfBankName,
                                    lStrBnfBankBranch,lStrEmailId,lStrSrcActNo};
                           
            for(int j=0,k=0;j<29;j++) {  
 
                if(lColumnsPos[j]==j)
                {
                    lColumns.append(lCoumnsValues[k]);
                    k++;
                }
                else
                {
                    lColumns.append("");
                }
                if(j!=28) {
                    lColumns.append(",");
                }
                }
                    lColumns.replace(0, lColumns.length(), lColumns.toString().trim());  //Change id:005
                    lColumns.append('\n');
                    flag++;
              }
             else
             {                  
                 break;
             }  
             lTotCnt++;
         }//End for loop
         
         //Writing file for upload
         if(lColumns.length()>0)
         {
             //To dispaly Total count and Total Amount in last row with 29 Comma separated values
             String lStrArry[]={"T",Integer.toString(lTotCnt),Double.toString(lTotAmt)};//002
             for(int i=0;i<28;i++)
             {
                 if(i<3){
                 lColumns.append(lStrArry[i]);   
                 lColumns.append(",");
                 }
                 else
                 {
                 lColumns.append(",");
                 }
                 
             }
             
             if(lStrFileSNo.length()>0)
             {
                 if(lStrFileSNo.length()>0 && lStrFileSNo.length()<=1)
                 {
                     lStrFileSNo="00"+lStrFileSNo;
                 }else if(lStrFileSNo.length()>1 && lStrFileSNo.length()<=2)
                 {
                     lStrFileSNo="0"+lStrFileSNo;
                 }
                
             }
             lStrFileName=lStrClaintCode+lStrDay+lStrMonth+lStrYear+"."+lStrFileSNo;        // 001          
            
             String lStrColums=lColumns.toString();
             byte[] lFileBytes = lStrColums.getBytes();            
             //Setting the FileBytes and FileName in HashMap for Upload into Bank Site
             lparamMap.put("FileBytes",lFileBytes);
             lparamMap.put("FileName",lStrFileName); 
             lparamMap.put("FilePath","UploadClaims");                   
           
                              
         }// end If                            
     }
             lparamMap.put("Flag",Integer.toString(flag));
    }catch(Exception e)
    {
    	e.printStackTrace();
     LOGGEROBJ.error(e);
    }
    
   return lparamMap;
  }
}