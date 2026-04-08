package com.ahct.model;

import javax.persistence.Column;

public class EhfmPayGradeMstId implements java.io.Serializable{
	private Long payCode;
	private String payGrade;
	private String srcName;
	
	
	
	 public EhfmPayGradeMstId() {
		super();
	}
	public EhfmPayGradeMstId(Long payCode, String payGrade, String srcName) {
		super();
		this.payCode = payCode;
		this.payGrade = payGrade;
		this.srcName = srcName;
	}
	@Column(name="PAY_CODE", nullable=false, length=22)
	public Long getPayCode() {
		return payCode;
	}
	public void setPayCode(Long payCode) {
		this.payCode = payCode;
	}
	 @Column(name="PAY_GRADE", nullable=false, length=10)
	public String getPayGrade() {
		return payGrade;
	}
	public void setPayGrade(String payGrade) {
		this.payGrade = payGrade;
	}
	 @Column(name="SRC_NAME", nullable=false, length=20)
	public String getSrcName() {
		return srcName;
	}
	public void setSrcName(String scrName) {
		this.srcName = scrName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((payCode == null) ? 0 : payCode.hashCode());
		result = prime * result
				+ ((payGrade == null) ? 0 : payGrade.hashCode());
		result = prime * result + ((srcName == null) ? 0 : srcName.hashCode());
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
		EhfmPayGradeMstId other = (EhfmPayGradeMstId) obj;
		if (payCode == null) {
			if (other.payCode != null)
				return false;
		} else if (!payCode.equals(other.payCode))
			return false;
		if (payGrade == null) {
			if (other.payGrade != null)
				return false;
		} else if (!payGrade.equals(other.payGrade))
			return false;
		if (srcName == null) {
			if (other.srcName != null)
				return false;
		} else if (!srcName.equals(other.srcName))
			return false;
		return true;
	}
	
	

}
