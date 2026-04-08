package com.ahct.attachments.dao;

import java.util.HashMap;
import java.util.Map;

public interface AttachmentDao {

    //public Map getEnrollmentAttachmentsByHql(HashMap lparamMap) ;
    public Map getCasesAttachmentsByHql(HashMap lparamMap) ;
    public Map getPreauthViewAttachmentsHql(HashMap<String, Object> lparamMap);
	public Map getPayResponseAttachment(HashMap<String, Object> hParamMap);
	public Map getAhcAttachmentsHql(HashMap<String, Object> hParamMap);
    
	/*
	 * Added by Srikalyan for getting Model Class
	 * Object by using Class Name and Id as Parameters 
	 */
	public Map idMethod(String obj,String id); 
}
