package com.ahct.PanelDoc.Service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import com.ahct.PanelDoc.Dao.PanelDocDaoImpl;
import com.ahct.PanelDoc.VO.panelDocVo;
//import com.ahct.bioMetric.vo.BioMetricVo;
import com.tcs.framework.configuration.ConfigurationService;

public class PanelDocServiceImpl implements PanelDocService {

	PanelDocDaoImpl PanelDocDao=new PanelDocDaoImpl();

	public PanelDocDaoImpl getPanelDocDao() {
		return PanelDocDao;
	}

	public void setPanelDocDao(PanelDocDaoImpl panelDocDao) {
		PanelDocDao = panelDocDao;
	}
	

	public panelDocVo getPanelDocLst(panelDocVo panelDocVo)
	{
		panelDocVo panelDoc=new panelDocVo();
		panelDoc=PanelDocDao.getPanelDocLst(panelDocVo);
		return panelDoc;
		
	}
	
    public String saveAccountDetails(panelDocVo panelDocVo)
    {
    	String msg=null;
    	msg=PanelDocDao. saveAccountDetails( panelDocVo);
    	return msg;
    }
	
	public String validatePnlDoc(String loginName)
	{
		boolean isUser=false;
		String loginNameTemp=null;
		loginNameTemp=PanelDocDao.validatePnlDoc(loginName);
		return loginNameTemp;
	}
	
	public String getIfscCode(String bankId)
	{
		String ifscCode=null;
		ifscCode=PanelDocDao.getIfscCode(bankId);
	    return ifscCode;
		
	}
	
	public List<panelDocVo> bankBranchList(String BankName)
	{
		List<panelDocVo> branchLst=new ArrayList<panelDocVo>();
		branchLst=PanelDocDao.bankBranchList(BankName);
		return branchLst;
	}
	
	public List<panelDocVo> banksList()
	{
		List<panelDocVo> bankLst=new ArrayList<panelDocVo>();
		bankLst=PanelDocDao.banksList();
		return bankLst;
	}
	
	public panelDocVo getPanelDocDetails(String UserId)
	{
		panelDocVo userDtls = new panelDocVo();
		userDtls=PanelDocDao.getPanelDocDetails(UserId);
		return userDtls;
	}
	
	public String getUserId(String loginName)
	{
		String userId=null;
		userId=PanelDocDao.getUserId(loginName);
		return userId;
	}
	
	
}
