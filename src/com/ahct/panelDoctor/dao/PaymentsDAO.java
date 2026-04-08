package com.ahct.panelDoctor.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ahct.panelDoctor.vo.TransactionVO;




public interface PaymentsDAO {

	
	ArrayList getDoctorDetails(HashMap lparamMap);

	public ArrayList insertUploadFile(HashMap lparamMap) throws Exception;

	ArrayList updateTdsPayments(HashMap hParamMap);

	Map payTDSFund(HashMap ldataMap);

	List getTdsAccountDtls(HashMap hParamMap);

	TransactionVO submitTdsOrRfPaymentsInAccounts(TransactionVO transactionVO) throws ParseException;

	void setTDSAuditDetails(HashMap hParamMap) throws Exception;

	String SetPanelDocStatus(HashMap lparamMap);

	String getSequence(String string) throws Exception;

	ArrayList getRejDoctorDetails(HashMap lparamMap);



	
	
}
