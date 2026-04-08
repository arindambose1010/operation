package com.ahct.preauth;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.web.servlet.View;

import com.ahct.preauth.vo.PreauthClinicalNotesVO;


public class JSONView  implements View 
{
    /*
             * (non-Javadoc)
             * 
             * @see org.springframework.web.servlet.View#getContentType()
             */
            public String getContentType() 
            {
                    return "application/json";
            }

            /*
             * (non-Javadoc)
             * 
             * @see org.springframework.web.servlet.View#render(java.util.Map,
             *      javax.servlet.http.HttpServletRequest,
             *      javax.servlet.http.HttpServletResponse)
             */
            public void render(Map map, HttpServletRequest request,HttpServletResponse response) throws Exception 
            {
                    response.setContentType(getContentType());
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().print(generateJSON(map));
            }

            /**
             * Generate json.
             * 
             * @param map
             *            the map
             * 
             * @return the string
             * 
             * @throws JSONException
             *             the JSON exception
             */

            public String generateJSON(Map<String, Object> map) throws JSONException 
            {
                      List<PreauthClinicalNotesVO> recList = (List<PreauthClinicalNotesVO>)map.get("recs");
                      return generateSIGJSON(recList).toString();
            }

		public String generateJSONClinicalNew(Map<String, Object> map) throws JSONException 
            {
                      List<PreauthClinicalNotesVO> recList = (List<PreauthClinicalNotesVO>)map.get("recs");
                      List<PreauthClinicalNotesVO> childRecList=(List<PreauthClinicalNotesVO>)map.get("childRecs");
                      return generateSIGJSONClinicalNew(recList,childRecList).toString();
            }
    public String generateJSONClinical(Map<String, Object> map) throws JSONException 
    {
          List<PreauthClinicalNotesVO> recList = (List<PreauthClinicalNotesVO>)map.get("recs");
   
          return generateSIGJSONClinical(recList).toString();
    }
  
            private JSONObject generateSIGJSON(List<PreauthClinicalNotesVO> recList) throws JSONException 
            {
                      JSONArray list = new JSONArray();                       
                      for (PreauthClinicalNotesVO record : recList) 
                      {
                              JSONObject deptsJSONObject = new  JSONObject();                 
                              deptsJSONObject.put("id",record.getID());
                              deptsJSONObject.put("value",record.getVALUE());
                             // deptsJSONObject.put("const",record.getCONST());
                              list.put(deptsJSONObject);
                      }
                      return new JSONObject().put("data", list);
            }
  
private JSONObject generateSIGJSONClinicalNew(List<PreauthClinicalNotesVO> recList,List<PreauthClinicalNotesVO> childRecList) throws JSONException 
            {
               
                      JSONArray list = new JSONArray();                       
                      for (PreauthClinicalNotesVO record : recList) {
                          JSONObject clnclNotesJsonObj = new  JSONObject(); 
                      {   System.out.println(record.getCLINICALID());
                              System.out.println("#############");
                              clnclNotesJsonObj.put("clinicalId",record.getCLINICALID());                              
                              clnclNotesJsonObj.put("temperature",record.getTEMPERATURE());
                              clnclNotesJsonObj.put("pulseRate",record.getPULSE());
                              clnclNotesJsonObj.put("respiratory",record.getRESPIRATORY());
                              clnclNotesJsonObj.put("heartRate",record.getHEART_RATE());
                              clnclNotesJsonObj.put("lungs",record.getLUNGS());   
                              clnclNotesJsonObj.put("bloodPressre",record.getBLOODPRESSURE());
                              clnclNotesJsonObj.put("investDate",record.getINVESTIGATIONDATE());
                      for(PreauthClinicalNotesVO childrecord : childRecList){
                        System.out.println(childrecord.getCLINICALID());
                          if(childrecord.getCLINICALID()== record.getCLINICALID()){
                          clnclNotesJsonObj.put("chemoYN",childrecord.getCHEMO_YN());
                          clnclNotesJsonObj.put("bloodYN",childrecord.getBLOOD_YN());
                          clnclNotesJsonObj.put("radiaYN",childrecord.getRADIATN_YN());
                          clnclNotesJsonObj.put("cndntStrInf",childrecord.getCNDNT_STR_INF());
                          clnclNotesJsonObj.put("infsnStrDt",childrecord.getINFSTRDT());
                          clnclNotesJsonObj.put("infsnStrTime",childrecord.getINF_STR_TIME());
                          clnclNotesJsonObj.put("infsnEndDt",childrecord.getINFENDDT());
                          clnclNotesJsonObj.put("infsEndTime",childrecord.getINF_END_TIME());
                          clnclNotesJsonObj.put("cndEndInf",childrecord.getCNDNT_END_INF());
                      break;
                          }
                          else{
                              clnclNotesJsonObj.put("chemoYN","");
                              clnclNotesJsonObj.put("bloodYN","");
                              clnclNotesJsonObj.put("radiaYN","");
                              clnclNotesJsonObj.put("cndntStrInf","");
                              clnclNotesJsonObj.put("infsnStrDt","");
                              clnclNotesJsonObj.put("infsnStrTime","");
                              clnclNotesJsonObj.put("infsnEndDt","");
                              clnclNotesJsonObj.put("infsEndTime","");
                              clnclNotesJsonObj.put("cndEndInf","");
                          }
                      }
                          }
                              list.put(clnclNotesJsonObj);
                      }
                      return new JSONObject().put("data", list);
            }
    private JSONObject generateSIGJSONClinical(List<PreauthClinicalNotesVO> recList) throws JSONException 
                {
                          JSONArray list = new JSONArray();                       
                          for (PreauthClinicalNotesVO record : recList) {
                              JSONObject clnclNotesJsonObj = new  JSONObject(); 
                              clnclNotesJsonObj.put("clinicalId",record.getCLINICALID());                              
                              clnclNotesJsonObj.put("temperature",record.getTEMPERATURE());
                              clnclNotesJsonObj.put("pulseRate",record.getPULSE());
                              clnclNotesJsonObj.put("respiratory",record.getRESPIRATORY());
                              clnclNotesJsonObj.put("heartRate",record.getHEART_RATE());
                              clnclNotesJsonObj.put("lungs",record.getLUNGS());   
                              clnclNotesJsonObj.put("fluidInput",record.getFLUIDINPT());
                              clnclNotesJsonObj.put("fluidOutput",record.getFLUIDOUTPUT());
                              clnclNotesJsonObj.put("bloodPressre",record.getBLOODPRESSURE());
                              clnclNotesJsonObj.put("investDate",record.getINVESTIGATIONDATE());
                              clnclNotesJsonObj.put("therapyDtls",record.getTHERAPY_DTLS());
                              clnclNotesJsonObj.put("wardType",record.getWARD_TYPE());
                              clnclNotesJsonObj.put("remarks",record.getREMARKS());

                              list.put(clnclNotesJsonObj);                         
                          
                              }
                              return new JSONObject().put("data", list);    
                          }
                       
                }


