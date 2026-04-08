/**
 * PACKAGE          : com.tcs.sgv.asri.common.util
 * FILENAME         : HtmlEncode.java
 * FUNCTIONALITY    :
 * @VERSION         : 4
 * @AUTHOR          : Common
 * DATE             : 07/18/08
 * Calls to other jsp/java   : 
 * Called by other jsp/java  : 
 * --------------------------------------------------------------------------------------------------
 *   ChangeId         Date                 Author            Change Desciption
 * --------------------------------------------------------------------------------------------------
 *    001            SEP-18-2008          Sheetal            To resolve Performance Issues  
 *    002            AUG-26-2009          Ajaykumar          moving session timeout from here to web.xml
 *    003            NOV-25-2009          Sowjanya(199382)   Changing session timeout JSP
 *    004            JUN-05-2012          Vishwa(340062)     Updating all the employees loggedin flag to N when the server restarts.
 * -------------------------------------------------------------------------------------------------
 */
package com.ahct.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


public class HtmlEncode
{
    private static Logger oslogger = Logger.getLogger(HtmlEncode.class);
    private static boolean restartFlag = true;
    /**
	   *  Check whether the given string contains Cross Site Script(CSS) and/or SQL Injection
	   *
	   *  @name isCrossSiteScript This method checks for CSS  and/or SQL Injection in a given string.
	   *  @param lStrInput String to be checked for CSS and/or SQL Injection.
	   *  
	   *  @return true if contains CSS string or else false if string does not CSS.
	  */
	/*  public static boolean isCrossSiteScript(String lStrInput)
	  {
         if(checkForCSS(lStrInput) || sqlInjection(lStrInput))
         {
            oslogger.debug(">> UnSafe Input <<");
            return true;
         }
         else
         {
            oslogger.debug("Safe Input");
            return false;
         }
    }*/

 /* private static boolean sqlInjection(String strVal)
  {
       String regexpforMETACHAR1= "(%27)|(&#32)|(u0027)|(\')|(--)|(%23)|(&#35)|(u0023)|(#)";  //Regex for detection of SQL meta-characters
       String regexpforMETACHAR2= "((%3D)|(&#61)|(u003D)|(=))[^\n]*((%27)|(&#32)|(u0027)|(\')|(--)|(%3B)|(&#59)|(u003B)|(;))";  //Modified regex for detection of SQL meta-characters
       String regexpforORclause= "\\w*((%27)|(&#32)|(u0027)|('))(\\s*)((%6F)|(&#111)|(u006F)|o|(%4F)|(&#79)|(u004F))((%72)|(&#114)|(u0072)|r|(%52)|(&#82)|(u0052))"; //Regex for typical SQL Injection attack using OR
       String regexpforSQLwords= "((%27)|(&#32)|(u0027)|('))(\\s*)(union|select|insert|update|delete|drop)"; //Regex for detecting SQL Injection with the UNION,SELECT,INSERT,UPDATE,DELETE,DROP keyword
       String regexpforMsSQL= "exec(\\s|\\+)+(s|x)p\\w+";      //Regex for detecting SQL Injection attacks on a MS SQL Server

              // creating RE object for the above regular expression
      RE reObjforMETACHAR1 = new RE(regexpforMETACHAR1,RE.MATCH_CASEINDEPENDENT);
      RE reObjforMETACHAR2 = new RE(regexpforMETACHAR2,RE.MATCH_CASEINDEPENDENT);
      RE reObjforORclause = new RE(regexpforORclause,RE.MATCH_CASEINDEPENDENT);
      RE reObjforSQLwords = new RE(regexpforSQLwords,RE.MATCH_CASEINDEPENDENT);
      RE reObjforMsSQL = new RE(regexpforMsSQL,RE.MATCH_CASEINDEPENDENT);

              //Checking for matching string
     if(reObjforMETACHAR1.match(strVal) || reObjforMETACHAR2.match(strVal) ||
        reObjforORclause.match(strVal) || reObjforSQLwords.match(strVal) ||
        reObjforMsSQL.match(strVal))
     {
        oslogger.debug("SQL injected in sqlInjection method");
        return true;
     }
     else
     {
        return false;
     }
     
  }*/

 /* private static boolean  checkForCSS(String strVal)
  {
           //regular experssion for CSS - Cross site scripting               // Example are below for each regular expression
      String regexpforHTMLTag1="(<|&#60|u003C)\\s*(\\S+)\\s*[^>]*\\s*(>|&#62|u003E)(.*)(<|&#60|u003C)\\/\\s*\\2\\s*(>|&#62|u003E)"; //<script> <//script> <html> </html>
      String regexpforHTMLTag2="(<|&#60|u003C)\\s*(\\S+)\\s*([^>]*)\\s*(>|&#62|u003E)";                //<font face="Arial, Serif" size="+2" color="red">
      String regexpforXMLTag="((<|&#60|u003C).[^(><.)]+(>|&#62|u003E))";                           //<servlet-name attr1=value attr2=value />
      String regexpforEqualVal="(\\s*\\w+\\s*)=\\1";                       // link=1=1   
      
           // creating RE object for the above regular expression
      RE reObjforHTMLTag1 = new RE(regexpforXMLTag,RE.MATCH_CASEINDEPENDENT);
      RE reObjforHTMLTag2 = new RE(regexpforHTMLTag2,RE.MATCH_CASEINDEPENDENT);
      RE reObjforXMLTag = new RE(regexpforXMLTag,RE.MATCH_CASEINDEPENDENT);
      RE reObjforEqualVal = new RE(regexpforEqualVal,RE.MATCH_CASEINDEPENDENT);

          //Checking for matching string
       if(reObjforHTMLTag1.match(strVal) || reObjforHTMLTag2.match(strVal) || 
          reObjforXMLTag.match(strVal) || reObjforEqualVal.match(strVal))
       {
          return true;
       }
       else
       {
          return false;
       }
       
  }*/


  public static String verifySession(HttpServletRequest request, HttpServletResponse response)
  {
    String lStrResultPage = "";
    long lCurrentTimeComI18N = System.currentTimeMillis();

//    try
//    {
        HttpSession session = request.getSession(false);
        

        if(session == null || session.getAttribute("UserID")==null)
        {
             request.setAttribute("Message","Your session has expired. Login again");
             lStrResultPage = "SessionExpired";//003
        }
        //002
        
//        else
//        {
//           ResourceBundle comI18NBundle = ResourceBundle.getBundle("SGVConstants");
//
//           long lTimeOut = Long.parseLong(comI18NBundle.getString("MAX_INACTIVE_INTERVAL")) * 1000;
//  
//           long lLastAccessedTimeComI18N = 0;
//
//           if(session != null)
//             lLastAccessedTimeComI18N = session.getLastAccessedTime();
//  
//            String lStrComI18NUserName = "";
//            if(session.getAttribute("UserID") !=null)
//             {
//                lStrComI18NUserName=(String)session.getAttribute("UserID");
//             }
//            long sessionPeriod = System.currentTimeMillis() - session.getCreationTime();
//  
//           if(lStrComI18NUserName == null)
//           {
//             oslogger.info("User is removed from the session & Redirecting to login page");
//             session.invalidate();
//             request.setAttribute("Message","Your session has expired. Login again 22222222222");
//             lStrResultPage = "IndexPage";
//           }
//           else if((lCurrentTimeComI18N - lLastAccessedTimeComI18N) > lTimeOut)
//           {
//             oslogger.info("Invalidating user session & Redirecting to login page");
//             session.invalidate();
//             request.setAttribute("Message","Your session has expired. Login again 3333333333333");
//             lStrResultPage = "IndexPage";
//           }
//        }
//    }
//    catch(Exception e)
//    {
//        oslogger.error(e);
//       lStrResultPage = "ReDirectLogin";
//    }
    
    return lStrResultPage;
  }







     /**
       *  Here the snippet to convert a String to HTML. This one is little
       *  bit better because it deals with space versus non-breaking space (&nbsp;) 
       *  and Unicode characters. 
       *
       *  @name stringToHTMLString This method will return an HTML string.
       *  @param string String to be converted as an HTML string.
       *  
       *  @return the HTML string.
       */
      public static String escapeHTML(String string)
      {
          StringBuffer sb = new StringBuffer(string.length());
          // true if last char was blank
          boolean lastWasBlankChar = false;
          int len = string.length();
          char c;

          for (int i = 0; i < len; i++)
          {
              c = string.charAt(i);
              if (c == ' ')
              {
                  // blank gets extra work,
                  // this solves the problem you get if you replace all
                  // blanks with &nbsp;, if you do that you loss 
                  // word breaking
                  if (lastWasBlankChar)
                  {
                      lastWasBlankChar = false;
                      sb.append("&nbsp;");
                  }
                  else 
                  {
                      lastWasBlankChar = true;
                      sb.append(' ');
                  }
              }
              else 
              {
                  lastWasBlankChar = false;
                  // HTML Special Chars
                  if (c == '"')
                      sb.append("&quot;");
                  else if (c == '&')
                      sb.append("&amp;");
                  else if (c == '<')
                      sb.append("&lt;");
                  else if (c == '>')
                      sb.append("&gt;");
                  else
                  {
                      int ci = 0xffff & c;
                      if (ci < 160 )
                      {
                          // nothing special only 7 Bit
                          sb.append(c);
                      }
                      else 
                      {
                          // Not 7 Bit use the unicode system
                          sb.append("&#");
                          sb.append(new Integer(ci).toString());
                          sb.append(';');
                      }
                    }
                  }
              }
          return sb.toString();
      }

    /*  public PatientVO setChildGender(PatientVO lPatientVO)
      {
        String lStrGender = lPatientVO.getGender();
        String lStrChild = lPatientVO.getChildYN() ;
        if(lStrChild.equalsIgnoreCase("Y"))
        {
          lPatientVO.setGender("C");
          lPatientVO.setChildYN(lStrGender);
        }
        else if(lStrChild.equalsIgnoreCase("M") || lStrChild.equalsIgnoreCase("F") )
        {
          lPatientVO.setGender(lStrChild);
          lPatientVO.setChildYN("Y");
        }
        return lPatientVO;
      }*/
      
   /* public static void checkForServerRestart() {//start:004
        if(restartFlag)        {
            restartFlag = false;
            Connection lCon = null;
            PreparedStatement p = null;
            try {
                lCon = DBConnection.getConnection();
                p = lCon.prepareStatement(" update asrim_employee_codes ec set ec.is_loggedin=? where ec.is_loggedin=? ");
                p.setString(1,"N");
                p.setString(2,"Y");
                p.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                DBConnection.closeConnection(lCon);
                DBConnection.closeStatement(p);
            }
        }        
    }//end:004*/
    
}
