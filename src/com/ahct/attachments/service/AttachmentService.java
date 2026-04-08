package com.ahct.attachments.service;

import java.util.HashMap;
import java.util.Map;

import com.ahct.model.EhfCochlearFollowup;
import com.ahct.attachments.vo.AttachmentVO;


public interface AttachmentService {
	 public Map getEnrollmentAttachments(HashMap lparamMap);//002
	// public String  upldEnrollmentAttach(AttachmentVO AttachmentVO);
	 public String getContentType(String lStrfileExtension) ;
	 public Map getCasesAttachmentsByHql(HashMap lparamMap) ;
	 public String removeAttachments(String docSeqId,String userId);
	 public String removePreauthAttachments(String docSeqId,String userId);
	 public Map getPreauthViewAttachmentsHql(HashMap<String, Object> lparamMap) ; 
	 public String  upldCaseAttachments(AttachmentVO AttachmentVO);
	 public Long getNextVal(String SeqName);
	 public String getIvestPath(String docSeqId);
	public Map<String, Object> getPayResponseAttachment(
			HashMap<String, Object> hParamMap);
	public String upldFlupAttachments(AttachmentVO attachmentVO);
	public String removeFollowUpAttachments(String doc_seq_id, String lStrEmpId);

	public String upldChronicAttachments(AttachmentVO attachmentVO);
	 public String removeChronicAttachments(String docSeqId,String userId);

	public Map<String, Object> getAhcAttachmentsHql(
			HashMap<String, Object> hParamMap);
	public String upldAhcAttachments(AttachmentVO attachmentVO);
	public String removeAhcAttachments(String doc_seq_id, String lStrEmpId);
	/*
	 * Added by Srikalyan for getting Cochlear 
	 * Followup Details 
	 */
	public EhfCochlearFollowup getCochFlpDtls(String cohclearFlpId);
	public String getPharmaBillAttch(String lstrCaseId); 

}
