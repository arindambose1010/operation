package com.ahct.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

public class AadhaarABHAUtility {

	public static String checkNullObj(Object aoCheckObject)
    {
        if (aoCheckObject == null)
        {
            return "";
        }

        return aoCheckObject.toString();
    }
	
	public static String getJSONObjValueByKey(JSONObject myJSON,String keyId)
    {
        String result="";
        try
        {
       	 
              Iterator<String> iter =  (toMap(myJSON)).keySet().iterator();
              String loopKey="";
              while (iter.hasNext())
              {
                  loopKey = iter.next();
                  if (loopKey.equalsIgnoreCase(keyId))
                  {
                      return AadhaarABHAUtility.checkNullObj(myJSON.get(loopKey));
                  }
              }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
	
	public static Map<String, Object> toMap(JSONObject jsonobj)  throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Iterator<String> keys = jsonobj.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            Object value = jsonobj.get(key);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }   
            map.put(key, value);
        }   return map;
    }
	
	public static List<Object> toList(JSONArray array) throws Exception {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }
            else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }   return list;
	}
}
