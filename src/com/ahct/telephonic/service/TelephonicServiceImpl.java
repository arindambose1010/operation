package com.ahct.telephonic.service;

import org.apache.log4j.Logger;

import com.ahct.telephonic.dao.TelephonicDao;
import com.ahct.telephonic.vo.TelephonicVO;

public class TelephonicServiceImpl implements TelephonicService{

	private final static Logger GLOGGER = Logger.getLogger ( TelephonicServiceImpl.class ) ;
	TelephonicDao telephonicDao;
	
	public TelephonicDao getTelephonicDao() {
		return telephonicDao;
	}

	public void setTelephonicDao(TelephonicDao telephonicDao) {
		this.telephonicDao = telephonicDao;
	}
	/**
     * ;
     * @param TelephonicVO telphonicVO
     * @return TelephonicVO retTelephonicVO
     * @function This Method is Used For Retrieving Telephonic Cases Registered to be displayed to MITHRA
     */
	@Override
	public TelephonicVO getTeleIntimationCases(TelephonicVO telephonicVO) {
		TelephonicVO getListCases=null;
		try {
			getListCases = telephonicDao.getTeleIntimationCases(telephonicVO);
		} 
		catch (Exception e) {
			GLOGGER.error("Error occured in getTeleIntimationCases() in TelephonicServiceImpl class."+e.getMessage());
		}
		return getListCases;
	}
	/**
     * ;
     * @param TelephonicVO telphonicVO
     * @return TelephonicVO retTelephonicVO
     * @function This Method is Used For Retrieving Telephonic Cases Registered
     */
	@Override
	public TelephonicVO getRegTeleIntimationCases(TelephonicVO telephonicVO) {
		TelephonicVO getListCases=null;
		try {
			getListCases = telephonicDao.getRegTeleIntimationCases(telephonicVO);
		} 
		catch (Exception e) {
			GLOGGER.error("Error occured in getRegTeleIntimationCases() in TelephonicServiceImpl class."+e.getMessage());
		}
		return getListCases;
	}

	

}
