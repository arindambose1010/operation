package com.ahct.telephonic.service;

import com.ahct.telephonic.vo.TelephonicVO;

public interface TelephonicService {

	/**
     * ;
     * @param TelephonicVO telphonicVO
     * @return TelephonicVO retTelephonicVO
     * @function This Method is Used For Retrieving Telephonic Cases Registered to be displayed to MITHRA
     */
	public TelephonicVO getTeleIntimationCases(TelephonicVO telephonicVO);
	/**
     * ;
     * @param TelephonicVO telphonicVO
     * @return TelephonicVO retTelephonicVO
     * @function This Method is Used For Retrieving Telephonic Cases Registered 
     */
	public TelephonicVO getRegTeleIntimationCases(TelephonicVO telephonicVO);
}
