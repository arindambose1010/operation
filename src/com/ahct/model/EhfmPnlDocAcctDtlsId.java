package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EhfmPnlDocAcctDtlsId implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userId;
	private String accountNo;
	
	public EhfmPnlDocAcctDtlsId(String userId, String accountNo) {
		this.userId=userId;
		this.accountNo=accountNo;
	}
	public EhfmPnlDocAcctDtlsId() {
		// TODO Auto-generated constructor stub
	}
	@Column(name = "USER_ID", nullable = false)
		public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "ACCOUNT_NO", nullable = false)
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EhfmPnlDocAcctDtlsId other = (EhfmPnlDocAcctDtlsId) obj;
		if (accountNo == null) {
			if (other.accountNo != null)
				return false;
		} else if (!accountNo.equals(other.accountNo))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
	public int hashCode() {
	    int result = 17;
	    
	    result = 37 * result + ( getUserId() == null ? 0 : this.getUserId().hashCode() );
	    result = 37 * result + ( getAccountNo() == null ? 0 : this.getAccountNo().hashCode() );
	    return result;
	}   


}
