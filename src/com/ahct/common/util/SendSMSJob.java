package com.ahct.common.util;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import org.apache.axis2.AxisFault;

import com.ahct.model.EhfSmsBuffer;
import com.tcs.framework.persistanceConfiguration.GenericDAO;

public class SendSMSJob {
	GenericDAO genericDao;

	public GenericDAO getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}
	
	private List<EhfSmsBuffer> getAllFailedList()
	{
		List<EhfSmsBuffer> ehfSmsBufferList=null;
		try
		{
			ehfSmsBufferList=genericDao.findAllByPropertyMatch(EhfSmsBuffer.class, "resentYN", "N");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ehfSmsBufferList;
	}
	public void sendSMSJobStart()
	{
		List<EhfSmsBuffer> ehfSmsBufferList=getAllFailedList();
		resendAllSms(ehfSmsBufferList);
	}

	private void resendAllSms(List<EhfSmsBuffer> ehfSmsBufferList) {
		SendSMS sms=new SendSMS();
		for(EhfSmsBuffer ehfSmsBuffer:ehfSmsBufferList)
		{
			try 
			{
				String msg=sms.sendSMSScheduler(ehfSmsBuffer.getSmsText(), ehfSmsBuffer.getPhoneNo());
				if(msg!=null && msg.equalsIgnoreCase("success"))
				{
					saveRecord(ehfSmsBuffer);
				}
				else
					break;
			} catch (AxisFault e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void saveRecord(EhfSmsBuffer ehfSmsBuffer) {
		try {
			ehfSmsBuffer.setResentYN("Y");
			ehfSmsBuffer.setLstUpdDt(new Date());
			genericDao.save(ehfSmsBuffer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
