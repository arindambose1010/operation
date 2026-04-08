package com.ahct.model;

import javax.persistence.Column;

public class EhfContactDtlsAuditId implements java.io.Serializable {

	
	
	private String empNo;
	private Long seqNo;
	
	@Column(name="EMP_NO",nullable=false)
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	
	@Column(name="SEQ_NO",nullable=false)
	public Long getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}
	
	
	
	public EhfContactDtlsAuditId(String empNo, Long seqNo) {
		super();
		this.empNo = empNo;
		this.seqNo = seqNo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((empNo == null) ? 0 : empNo.hashCode());
		result = prime * result
				+ ((seqNo == null) ? 0 : seqNo.hashCode());

		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EhfContactDtlsAuditId other = (EhfContactDtlsAuditId) obj;
		if (empNo == null) {
			if (other.empNo != null)
				return false;
		} else if (!empNo.equals(other.empNo))
			return false;
		if (seqNo == null) {
			if (other.seqNo != null)
				return false;
		} else if (!seqNo.equals(other.seqNo))
			return false;
	
		return true;
	}
	

}