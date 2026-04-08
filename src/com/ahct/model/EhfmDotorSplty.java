package com.ahct.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="EHFM_DOCTOR_SPLTY")
public class EhfmDotorSplty implements java.io.Serializable{
private EhfmDotorSpltyId id;
private  String isActiveYN;
private String reqNo;
private String scheme;
public EhfmDotorSplty(EhfmDotorSpltyId id, String isActiveYN, String reqNo) {
	super();
	this.id = id;
	this.isActiveYN = isActiveYN;
	this.reqNo = reqNo;
}
public EhfmDotorSplty() {
	super();
}
@EmbeddedId
@AttributeOverrides( {
		@AttributeOverride(name = "regNum", column = @Column(name = "REG_NUM", nullable = false, length =50)),
		@AttributeOverride(name = "hospId", column = @Column(name = "HOSP_ID", nullable = false, length = 20)),
		@AttributeOverride(name = "spctlyCode", column = @Column( name = "SPCLTY_CODE", nullable = false, length = 5))})
public EhfmDotorSpltyId getId() {
	return id;
}
public void setId(EhfmDotorSpltyId id) {
	this.id = id;
}
@Column( name = "IS_ACTIVEYN", nullable = true, length = 1)
public String getIsActiveYN() {
	return isActiveYN;
}
public void setIsActiveYN(String isActiveYN) {
	this.isActiveYN = isActiveYN;
}
@Column( name = "REQ_NO", nullable = true, length = 50)
public String getReqNo() {
	return reqNo;
}
public void setReqNo(String reqNo) {
	this.reqNo = reqNo;
}
@Column(name="scheme")
public String getScheme() {
	return scheme;
}
public void setScheme(String scheme) {
	this.scheme = scheme;
}


}
