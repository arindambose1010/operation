package com.ahct.cardSearch.service;

import java.util.List;

import com.ahct.cardSearch.vo.cardSearchVO;
import com.ahct.common.vo.LabelBean;

public interface cardSearchService 
{

	List<cardSearchVO> getCardStatusList(String empRadio, String cardNo);

	String getUsrId(String cardNo);

	LabelBean getCardDetails(String usrId, String lStrEnrolId, String lStrSeqNo, String empRadio);

	String getEmpUsrId(String cardNo);

}
