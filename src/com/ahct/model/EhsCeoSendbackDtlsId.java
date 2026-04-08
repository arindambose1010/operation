package com.ahct.model;

import java.io.Serializable;
import javax.persistence.Column;

public class EhsCeoSendbackDtlsId implements Serializable {

	private String id;
	private String referenceNo;
	
	public EhsCeoSendbackDtlsId()
	{super();}

	public EhsCeoSendbackDtlsId(String id, String referenceNo) {
		super();
		this.id = id;
		this.referenceNo = referenceNo;
	}
	
	@Column(name="ID",nullable=false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="REFERENCE_NO",nullable=false)
	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	
	
}
