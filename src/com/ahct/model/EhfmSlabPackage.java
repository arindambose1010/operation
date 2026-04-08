package com.ahct.model;

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
@Table(name="ehfm_slab_package")
public class EhfmSlabPackage implements java.io.Serializable{
private EhfmSlabPackageId id;
private String amount;
private String quantId;
private  Date crtDt;
private String crtUsr;


@Column(name="amount")
public String getAmount() {
	return amount;
}
public void setAmount(String amount) {
	this.amount = amount;
}
@Column(name="QUANT_ID")
public String getQuantId() {
	return quantId;
}
public void setQuantId(String quantId) {
	this.quantId = quantId;
}
@EmbeddedId

@AttributeOverrides( {
   @AttributeOverride(name="nabhFlag", column=@Column(name="nabh_flag", nullable=false) ), 
   @AttributeOverride(name="slabType", column=@Column(name="slab_type", nullable=false) ) } )
public EhfmSlabPackageId getId() {
	return id;
}
public void setId(EhfmSlabPackageId id) {
	this.id = id;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="crt_dt")
public Date getCrtDt() {
	return crtDt;
}
public void setCrtDt(Date crtDt) {
	this.crtDt = crtDt;
}
@Column(name="crt_user")
public String getCrtUsr() {
	return crtUsr;
}
public void setCrtUsr(String crtUsr) {
	this.crtUsr = crtUsr;
}
public EhfmSlabPackage(EhfmSlabPackageId id, Date crtDt, String crtUsr) {
	super();
	this.id = id;
	this.crtDt = crtDt;
	this.crtUsr = crtUsr;
}
public EhfmSlabPackage() {
	super();
}


}
