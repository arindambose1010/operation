package com.ahct.claims.util;

import java.util.ArrayList;
import java.util.List;

public class ClaimCases {

	private ClaimCases() {}
	private static final int MAX_LOCK_AGE = 10*60*1000;
	private static final int MAX_LOCK_AGE_FOR_OPD = 2*60*1000;//Chandana - 8602 - To lock the case for 2 minutes
	private static int max_case = 1000;
    private static int inc_size = 1000;
    private static String[] caseId = new String[max_case];
    private static String[] userName = new String[max_case];
    private static long[] lockTime = new long[max_case];
    private static String[] moduleType = new String[max_case];
    
    public static synchronized boolean  isAvailable(String lStrCaseId,String lStrUserId,String lStrModuleType){
        if(lStrCaseId==null) return false;
        for(int i=0;i<max_case;i++){
            if(lStrCaseId.equals(caseId[i])){
                return false;
            }
        }
        lock(lStrCaseId,lStrUserId,lStrModuleType);
        return true;
   }
    
    public static synchronized boolean  checkCaseAvailable(String lStrCaseId,String lStrUserId,String lStrModuleType){
        if(lStrCaseId==null) return false;
        for(int i=0;i<max_case;i++){
            if(lStrCaseId.equals(caseId[i])){
                return false;
            }
        }
        return true;
   }
    private static void lock(String lStrCaseId,String lStrUserId,String lStrModuleType){
        int k=4;
        while(--k>0){
    		for(int i=0;i<max_case;i++){
        		if(caseId[i]==null){
        			if(checkCaseAvailable(lStrCaseId, lStrUserId,lStrModuleType))
        			{
	        			caseId[i] = lStrCaseId;
	        			lockTime[i] = System.currentTimeMillis()+MAX_LOCK_AGE;
	        			userName[i] = lStrUserId;
	        			moduleType[i] = lStrModuleType;
	        			return;
        			}
        		}
        	}
    		increateArraySize2();
    	}
    }
    
    public static String getCaseForUserId(String lStrUserId,String module){        
    		for(int i=0;i<max_case;i++){
        			if(userName[i]!=null && (userName[i].equalsIgnoreCase(lStrUserId))){
        			   if(moduleType[i]!=null && (moduleType[i].equalsIgnoreCase(module)))
        				return caseId[i];	
        			}
        	}
			return "false";
    }
    
    public static void releaseCase(String lStrCaseId){
    	if(lStrCaseId==null)return;
    	long ccTime = System.currentTimeMillis();
    	for(int i=0;i<max_case;i++){
                if(caseId[i]!=null && (lockTime[i] < ccTime || lStrCaseId.equals(caseId[i]))){
                    caseId[i] = null;
                    lockTime[i] = 0L;
                    userName[i] = null;
                    moduleType[i] = null;
    		}
    	}
        showData();
    }
    
    private static void increateArraySize2(){
    	int tmax_case = max_case+inc_size;
            String[] tcaseId = new String[tmax_case];
            String[] tuserName = new String[tmax_case];
            long[] tlockTime = new long[tmax_case];
            for(int i=0;i<max_case;i++){
                tcaseId[i] = caseId[i];
                tuserName[i] = userName[i];
                tlockTime[i] = lockTime[i];
            }
            caseId = tcaseId;
            userName = tuserName;
            lockTime = tlockTime;
            max_case = tmax_case;
    		
        }
    
    public static String showData(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<max_case;i++)
            if(caseId[i]!=null)
                sb.append("\n[ ").append(caseId[i]).append(", ").append(lockTime[i]).append(" ]");
        return sb.toString();
    }
    //Chandana - 8598 - Added below method for checking the available cases with single parameter 
	public static synchronized boolean  isAvailable(String lStrCaseId){
        if(lStrCaseId==null) return false;
        for(int i=0;i<max_case;i++){
            if(lStrCaseId.equals(caseId[i])){
                return false;
            }
        }
        lock(lStrCaseId);
        return true;
   }
	private static void lock(String lStrCaseId){//Chandana - 8598 - To make the lock for 10 minutes
	    int k=4;
	    while(--k>0){
			for(int i=0;i<max_case;i++){
	    		if(caseId[i]==null){
	    			caseId[i] = lStrCaseId;
	    			lockTime[i] = System.currentTimeMillis()+MAX_LOCK_AGE_FOR_OPD;//Chandana - 8602 - Changed this for making the case lock for 2 minutes
	    			return;
	    		}
	    	}
			increateArraySize2();
		}
	}
	public static List<String> getBlockedCasesList(){//Chandana - 8598 - New method to get the list of locked cases
        List<String> lst = new ArrayList<String>();
        for(int i=0;i<max_case;i++)
            if(caseId[i]!=null)
                lst.add(caseId[i]);
        return lst;
    }
	
}
