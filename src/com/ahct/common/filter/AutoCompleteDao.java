package com.ahct.common.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;
import com.ahct.model.MUrlFilter;


public class AutoCompleteDao {
    GenericDAO genericDao;
    
	public GenericDAO getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}

	public static Map<String, Object> urlFilterMap = new HashMap<String, Object>();
	private static Logger logger = null;
	public static Map<String, String> scriptMap = null;
	public static Map<String, Object> sessionMap = new HashMap<String, Object>();
	static{
		logger = Logger.getLogger(AutoCompleteDao.class);
	}
	public void reloadURLFilters(HttpServletRequest request) throws Exception {
        try{
            logger.info("## came to reload the URL Filters");
            ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());   
            reloadURLFilters(ctx);
            logger.info("## reloaded successfully");
        }catch(Exception e){
            logger.error("## Error while reloading .. Error --> " + e.getMessage());
            throw new Exception("## Error while reloading URLFilters. Error "+e.getMessage());
        }
    }
        
    public void reloadURLFilters(ApplicationContext ctx) throws Exception{
        List<MUrlFilter> tempList = new ArrayList<MUrlFilter>();
        List<MUrlFilter> patternList = new ArrayList<MUrlFilter>();
        List<MUrlFilter> _SQLPatternList = new ArrayList<MUrlFilter>();
        Map<String, String> paramReservedMap = new HashMap<String, String>();
        Map<String, String> valueReservedMap = new HashMap<String, String>();
        Map<String, String> paramExceptionMap = new HashMap<String, String>();
        List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
        try{
       	
            criteriaList.add(new GenericDAOQueryCriteria("activeYn", "Y", GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
        	criteriaList.add(new GenericDAOQueryCriteria("id.type", "SQLPattern", GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
            _SQLPatternList = genericDao.findAllByCriteria(MUrlFilter.class, criteriaList);
            
            criteriaList.remove(1);
            criteriaList.add(new GenericDAOQueryCriteria("id.type", "Pattern", GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
            patternList = genericDao.findAllByCriteria(MUrlFilter.class, criteriaList);
            
            criteriaList.remove(1);
            criteriaList.add(new GenericDAOQueryCriteria("id.type", "ValueReserved", GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
            tempList = genericDao.findAllByCriteria(MUrlFilter.class, criteriaList);
            
            if(tempList != null && !tempList.isEmpty()){
                for(MUrlFilter list : tempList)
                    valueReservedMap.put(list.getId().getValue(), list.getId().getValue());
            }
            
            criteriaList.remove(1);
            criteriaList.add(new GenericDAOQueryCriteria("id.type", "ParameterReserved", GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
            tempList = genericDao.findAllByCriteria(MUrlFilter.class, criteriaList);
            
            if(tempList != null && !tempList.isEmpty()){
                for(MUrlFilter list : tempList)
                    paramReservedMap.put(list.getId().getValue(), list.getId().getValue());
            }
            
            criteriaList.remove(1);
            criteriaList.add(new GenericDAOQueryCriteria("id.type", "ParameterException", GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
            tempList = genericDao.findAllByCriteria(MUrlFilter.class, criteriaList);
            
            if(tempList != null && !tempList.isEmpty()){
                for(MUrlFilter list : tempList)
                    paramExceptionMap.put(list.getId().getValue(), list.getId().getValue());
            }
            
            Map<String, Object> tempMap = new HashMap<String, Object>();
            
            tempMap.put("SQLPatternList", _SQLPatternList);
            tempMap.put("PatternList", patternList);
            tempMap.put("ParamReservedMap", paramReservedMap);
            tempMap.put("ValueReservedMap", valueReservedMap);
            tempMap.put("ParamExceptionMap", paramExceptionMap);
                            
            urlFilterMap = Collections.unmodifiableMap(tempMap);
            logger.info("## Reloaded URL Filter Map");
        }
        catch(Exception e){
        	e.printStackTrace();
            logger.error("## Error while reloading URL Filters Map .. Error --> " + e.getMessage());
            throw new Exception("## Error while reloading URL Filters Map. Error --> " + e.getMessage());
        }
    }

}
