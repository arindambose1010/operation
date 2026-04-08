package com.ahct.attachments.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class AttachmentForm extends ActionForm{
	private String msg;
	private FormFile attachAry[] = new FormFile[200];
	public void setAttachAry ( FormFile[] attachAry )
	  {
	    this.attachAry = attachAry;
	  }
	  public FormFile[] getAttachAry ()
	  {
	    return attachAry ;
	  }
	  public FormFile getAttachedIndex ( int index )
	    {
	      return attachAry[ index ] ;
	    }
	    public void setAttachedIndex ( int index, FormFile value )
	    {
	      attachAry[ index ] = value ;
	    }
	   
	    public FormFile getSgndApplnForm ( int index )
	    {
	      return attachAry[ index ] ;
	    }
	    public void setSgndApplnForm ( int index, FormFile value )
	    {
	      attachAry[ index ] = value ;
	    }
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}   
	    
		
		private String fromDate;
		private String toDate;
		public String getFromDate() {
			return fromDate;
		}
		public void setFromDate(String fromDate) {
			this.fromDate = fromDate;
		}
		public String getToDate() {
			return toDate;
		}
		public void setToDate(String toDate) {
			this.toDate = toDate;
		}
	    

}
