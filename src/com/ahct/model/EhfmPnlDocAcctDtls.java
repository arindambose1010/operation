package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EHFM_PNLDOC_ACCT_DTLS")
public class EhfmPnlDocAcctDtls implements Serializable
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private EhfmPnlDocAcctDtlsId Id;
  private String bankId;
  private String nameAsPerAcc;
  private String panNo;
  private String panHolderName;
  private Date crtDt;
  private String crtUsr;
  private Date lstUpdDt;
  private String lstUpdUsr;
  private String active_yn;
  
  @EmbeddedId 
  @AttributeOverrides( {
      @AttributeOverride(name="userId", column=@Column(name="USER_ID",nullable=false)),
      @AttributeOverride(name="accountNo",column=@Column(name="ACCOUNT_NO",nullable=false ))
  })
  
  public EhfmPnlDocAcctDtlsId getId() {
		return Id;
	}
	public void setId(EhfmPnlDocAcctDtlsId id) {
		Id = id;
	}

	
	@Column(name = "BANK_ID")
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	
	
	@Column(name = "NAME_AS_PER_ACC",length = 100)
	public String getNameAsPerAcc() {
		return nameAsPerAcc;
	}
	public void setNameAsPerAcc(String nameAsPerAcc) {
		this.nameAsPerAcc = nameAsPerAcc;
	}
	
	@Column(name = "PAN_NUMBER",length = 10)
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	
	
	@Column(name = "PAN_HOLDER_NAME",length = 100)
	public String getPanHolderName() {
		return panHolderName;
	}
	public void setPanHolderName(String panHolderName) {
		this.panHolderName = panHolderName;
	}

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DATE", nullable = false)
	public Date getCrtDt() {
		return crtDt;
	}


	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	
	@Column(name = "CRT_USR", nullable = false, length = 20)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="lst_upd_date", nullable = true)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	@Column(name = "LST_UPD_USR", nullable = true, length = 20)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	
	
	@Column(name = "active_yn")
	public String getActive_yn() {
		return active_yn;
	}
	public void setActive_yn(String active_yn) {
		this.active_yn = active_yn;
	}
  
}

