/**
 * PACKAGE          : com.tcs.sgv.asri.common.util
 * FILENAME         : TimeStamp.java
 * FUNCTIONALITY    :
 * @VERSION         : 2
 * @AUTHOR          : Common
 * DATE             : 07/18/08
 * Calls to other jsp/java   : 
 * Called by other jsp/java  : 
 * --------------------------------------------------------------------------------------------------
 *   ChangeId         Date                 Author            Change Desciption
 * --------------------------------------------------------------------------------------------------
 *    001            SEP-18-2008          Sheetal         To resolve Performance Issues  
 * -------------------------------------------------------------------------------------------------
 */
package com.ahct.common.util;

import java.util.Calendar;


public class TimeStamp {
    public TimeStamp() {
    }
    // Iput Date Format should be 11/03/2008 10:10:00
     public long getTimeStampFromDate(String ts)
       {
        long l=0;
        String ldateTIme[]={};
        String lDateArr[]={};
        int lHH=00,lMM=00,lSec=00;
        int lday,lmonth,lyear;
           String lDate="";
           String lTime="";
           String lTimeStamp=ts.trim();
           if(lTimeStamp.length()>10) {
            ldateTIme=lTimeStamp.split(" ");
           }
           if(!(ldateTIme.equals(""))&& ldateTIme.length==2)
           {
            lDate=ldateTIme[0];
            lTime=ldateTIme[1];
            lDateArr=lDate.split("/");
               
              
           lday= Integer.parseInt(lDateArr[0]);
           lmonth=Integer.parseInt(lDateArr[1])-1;//Month value is 0-based. e.g., 0 for January.
           lyear=Integer.parseInt(lDateArr[2]);
           String lTimeArr[]=lTime.split(":");
           
           lHH= Integer.parseInt(lTimeArr[0]);
           lMM=Integer.parseInt(lTimeArr[1]);
           lSec=Integer.parseInt(lTimeArr[2]);

           }
           else
           {
                   lDateArr=lTimeStamp.split("/"); 
                   lday= Integer.parseInt(lDateArr[0]);
                   lmonth=Integer.parseInt(lDateArr[1])-1;//Month value is 0-based. e.g., 0 for January.
                   lyear=Integer.parseInt(lDateArr[2]);
           }
           Calendar c=Calendar.getInstance();
           c.clear();
           c.set(lyear,lmonth,lday,lHH,lMM,lSec);
           l=c.getTimeInMillis();           
           return l;
       }

    public static void main(String ar[])
    {
    String str="11/03/2008 10:10:00";
    TimeStamp ts=new TimeStamp();
    System.out.println( ts.getTimeStampFromDate(str));
    
    }
}
