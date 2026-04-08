package com.ahct.telephonic.dao;

import com.ahct.telephonic.vo.TelephonicVO;

public interface TelephonicDao {
	
	/**
     * ;
     * @param TelephonicVO telphonicVO
     * @return TelephonicVO retTelephonicVO
     * @function This Method is Used For Retrieving Telephonic Cases Registered to be displayed to MITHRA
     */
	public TelephonicVO getTeleIntimationCases(TelephonicVO telephonicVO)throws Exception;
	/**
     * ;
     * @param TelephonicVO telphonicVO
     * @return TelephonicVO retTelephonicVO
     * @function This Method is Used For Retrieving Telephonic Cases Registered
     */
	public TelephonicVO getRegTeleIntimationCases(TelephonicVO telephonicVO)throws Exception;
}
