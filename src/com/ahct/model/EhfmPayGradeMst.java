package com.ahct.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table

( name = "ehf_paygrade_mst" )
public class EhfmPayGradeMst {
	EhfmPayGradeMstId id;
	private String lowerPayScale;
	private String upperPayScale;
	private String goNumber;
	private Long prcYear;
	private String payScale; 
	private String slab;
	
	@EmbeddedId

	@AttributeOverrides( {
	   @AttributeOverride(name="payCode", column=@Column(name="PAY_CODE", nullable=false, length=22) ),
	   @AttributeOverride(name="payGrade", column=@Column(name="PAY_GRADE", nullable=false, length=10) ),
	   @AttributeOverride(name="srcName",  column=@Column(name="SRC_NAME", nullable=false, length=20) )
	    
	   } )
	public EhfmPayGradeMstId getId() {
		return id;
	}
	public void setId(EhfmPayGradeMstId id) {
		this.id = id;
	}
	 @Column(name="LOWER_PAY_SCALE", nullable=true, length=20)
	public String getLowerPayScale() {
		return lowerPayScale;
	}
	public void setLowerPayScale(String lowerPayScale) {
		this.lowerPayScale = lowerPayScale;
	}
	 @Column(name="UPPER_PAY_SCALE", nullable=true, length=20)
	public String getUpperPayScale() {
		return upperPayScale;
	}
	public void setUpperPayScale(String upperPayScale) {
		this.upperPayScale = upperPayScale;
	}
	 @Column(name="GO_NUMBER", nullable=true, length=100)
	public String getGoNumber() {
		return goNumber;
	}
	public void setGoNumber(String goNumber) {
		this.goNumber = goNumber;
	}
	 @Column(name="PRC_YEAR", nullable=true, length=5)
	public Long getPrcYear() {
		return prcYear;
	}
	public void setPrcYear(Long prcYear) {
		this.prcYear = prcYear;
	}
	 @Column(name="PAY_SCALE", nullable=true, length=400)
	public String getPayScale() {
		return payScale;
	}
	public void setPayScale(String payScale) {
		this.payScale = payScale;
	}
	@Column(name="SLAB")
	public String getSlab() {
		return slab;
	}
	public void setSlab(String slab) {
		this.slab = slab;
	}
	
	
	
	
	
	
	
	

}
