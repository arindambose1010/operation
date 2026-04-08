package com.ahct.common.util;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class StringUtility
{
  private static Logger oslogger = Logger.getLogger(StringUtility.class);

  public static String[] strSplit(String paramString1, String paramString2)
  {
    if (paramString1.length() == 0)
      return new String[0];
    String str = "";
    int i = paramString1.indexOf(paramString2, 0);
    ArrayList localArrayList = new ArrayList();
    int j = 0;
    while (i != -1)
    {
      localArrayList.add(paramString1.substring(j, i));
      j = i + paramString2.length();
      i = paramString1.indexOf(paramString2, j);
    }
    localArrayList.add(paramString1.substring(j));
    if (localArrayList.isEmpty())
      return new String[0];
    String[] arrayOfString = new String[localArrayList.size()];
    int k = localArrayList.size();
    for (int m = 0; m < k; m++)
      arrayOfString[m] = ((String)localArrayList.get(m));
    return arrayOfString;
  }

  public static String replaceString(String paramString1, String paramString2, String paramString3)
  {
    String str = "";
    if (paramString1.length() == 0)
      return "";
    int i = paramString1.indexOf(paramString2, 0);
    int j = 0;
    while (i != -1)
    {
      str = str + paramString1.substring(j, i) + paramString3;
      j = i + paramString2.length();
      i = paramString1.indexOf(paramString2, j);
    }
    str = str + paramString1.substring(j);
    return str;
  }

  public static String getParameter(String paramString, HttpServletRequest paramHttpServletRequest)
    throws Exception
  {
    String str = "";
    try
    {
      if ((paramHttpServletRequest.getParameter(paramString) != null) && (paramHttpServletRequest.getParameter(paramString).length() > 0))
        str = new String(paramHttpServletRequest.getParameter(paramString).getBytes("ISO-8859-1"), "UTF8");
      else
        str = "";
    }
    catch (Exception localException)
    {
      oslogger.error("Error in StringUtility getParameter():" + localException.getMessage());
      throw localException;
    }
    return str;
  }

  public static String[] getParameterValues(String paramString, HttpServletRequest paramHttpServletRequest)
    throws Exception
  {
    String[] arrayOfString1 = new String[0];
    String[] arrayOfString2 = new String[0];
    if (paramHttpServletRequest.getParameterValues(paramString) != null)
      arrayOfString2 = paramHttpServletRequest.getParameterValues(paramString);
    try
    {
      arrayOfString1 = new String[arrayOfString2.length];
      if ((arrayOfString2 != null) && (arrayOfString2.length > 0))
        for (int i = 0; i < arrayOfString2.length; i++)
          arrayOfString1[i] = new String(arrayOfString2[i].getBytes("ISO-8859-1"), "UTF8");
      arrayOfString2 = null;
    }
    catch (Exception localException)
    {
      oslogger.error("Error in StringUtility getParameterValues():" + localException.getMessage());
      throw localException;
    }
    return arrayOfString1;
  }

  public static String stringToHTMLString(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer(paramString.length());
    int i = 0;
    int j = paramString.length();
    for (int m = 0; m < j; m++)
    {
      int k = paramString.charAt(m);
      if (k == 32)
      {
        if (i != 0)
        {
          i = 0;
          localStringBuffer.append("&nbsp;");
        }
        else
        {
          i = 1;
          localStringBuffer.append(' ');
        }
      }
      else
      {
        i = 0;
        if (k == 34)
        {
          localStringBuffer.append("&quot;");
        }
        else if (k == 38)
        {
          localStringBuffer.append("&amp;");
        }
        else if (k == 60)
        {
          localStringBuffer.append("&lt;");
        }
        else if (k == 62)
        {
          localStringBuffer.append("&gt;");
        }
        else if (k == 10)
        {
          localStringBuffer.append("&lt;br/&gt;");
        }
        else
        {
          int n = 0xFFFF & k;
          if (n < 160)
          {
            localStringBuffer.append(k);
          }
          else
          {
            localStringBuffer.append("&#");
            localStringBuffer.append(new Integer(n).toString());
            localStringBuffer.append(';');
          }
        }
      }
    }
    return localStringBuffer.toString();
  }
}