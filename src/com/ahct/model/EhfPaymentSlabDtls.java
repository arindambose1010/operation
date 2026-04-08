package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "EHF_PAYMENT_SLAB_DTLS")
public class EhfPaymentSlabDtls  implements Serializable{
	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	 private EhfPaymentSlabDtlsID id;
	 private Double slabAmount;
	private Double totalClaimAmt;
	private Double totalTdsAmt;
	private String directDeduction;
	private String rqstActiveYn;
	private Date crtDt;
	private String crtUsr;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String slabamtApplicable;
	 

	 @EmbeddedId
		@AttributeOverrides( {
				@AttributeOverride(name = "hospDeductorId", column = @Column(name = "HOSP_DEDUCTOR_ID", nullable = false)),								
				@AttributeOverride(name = "scheme", column = @Column(name = "SCHEME", nullable = false)) 		
		})
			 
	public EhfPaymentSlabDtlsID getId() {
		return id;
	}
	public void setId(EhfPaymentSlabDtlsID id) {
		this.id = id;
	}
	@Column(name = "SLAB_AMOUNT", nullable = true)
	public Double getSlabAmount() {
		return slabAmount;
	}
	public void setSlabAmount(Double slabAmount) {
		this.slabAmount = slabAmount;
	}
	
	@Column(name = "TOTAL_CLAIM_AMT", nullable = true)
	public Double getTotalClaimAmt() {
		return totalClaimAmt;
	}
	public void setTotalClaimAmt(Double totalClaimAmt) {
		this.totalClaimAmt = totalClaimAmt;
	}
	
	@Column(name = "TOTAL_TDS_AMT", nullable = true)
	public Double getTotalTdsAmt() {
		return totalTdsAmt;
	}
	public void setTotalTdsAmt(Double totalTdsAmt) {
		this.totalTdsAmt = totalTdsAmt;
	}
	
	@Column(name = "DIRECT_DEDUCTION", nullable = true)
	public String getDirectDeduction() {
		return directDeduction;
	}
	
	public void setDirectDeduction(String directDeduction) {
		this.directDeduction = directDeduction;
	}
	@Column(name = "RQST_ACTIVE_YN", nullable = true)
	public String getRqstActiveYn() {
		return rqstActiveYn;
	}
	public void setRqstActiveYn(String rqstActiveYn) {
		this.rqstActiveYn = rqstActiveYn;
	}
	@Column(name = "SLABAMT_APPLICABLE", nullable = true)
	public String getSlabamtApplicable() {
		return slabamtApplicable;
	}
	public void setSlabamtApplicable(String slabamtApplicable) {
		this.slabamtApplicable = slabamtApplicable;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRT_DT", nullable = true)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name = "CRT_USR", nullable = true)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LST_UPD_DT", nullable = true)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	@Column(name = "LST_UPD_USR", nullable = true)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}	
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}     
	 
	 
      
	 


}
