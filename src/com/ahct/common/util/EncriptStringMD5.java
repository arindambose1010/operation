package com.ahct.common.util;

import java.security.MessageDigest;

public class EncriptStringMD5 {
   
    public static String generateEncriptedString(String lStrPassword)
    {
        String lStrEncriptedPassword="";    
        byte[] defaultBytes = lStrPassword.getBytes();
        try{
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();
            
            StringBuffer hexString = new StringBuffer();
            for (int i=0;i<messageDigest.length;i++) {
            hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }   
            lStrEncriptedPassword = hexString+"";        
          
        }catch(Exception e)
        {
        e.printStackTrace();
        }
        
        return lStrEncriptedPassword;
    }
    public static String getMD5(String text) 
    {
        String lStrEncriptedPassword1="";    
        try
        {
            final MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(text.getBytes("utf-8"));
            final StringBuilder hexStringBuilder = new StringBuilder();
            final byte[] digest = algorithm.digest();
            for (byte digestItem : digest) 
            {
                String hex = Integer.toHexString(0xFF & digestItem);
                if (hex.length() == 1) 
                {
                    hexStringBuilder.append('0');
                }
                hexStringBuilder.append(hex.toLowerCase());
                lStrEncriptedPassword1 = hexStringBuilder.toString();
            }
        }
        catch(Exception e)
                {
                e.printStackTrace();
                }
       return lStrEncriptedPassword1;
    }
}
