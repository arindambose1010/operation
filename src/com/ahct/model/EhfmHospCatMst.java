package com.ahct.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHFM_HOSP_SPECIALITY")
@NamedNativeQueries ( {
@NamedNativeQuery ( 
name ="findHospSpltyById" , resultClass = EhfmHospCatMst.class,
query = "select * from EHFM_HOSP_SPECIALITY ehc where ehc.HOSP_ID=:HOSP_ID")

})
public class EhfmHospCatMst implements java.io.Serializable{
private EhfmHospCatMstId id;
private String langId;
private Integer disBedCapacity;
private String isActvFlg;
private String crtUsr;
private Date crtDt;
private String lstUpdUsr;
private Date lstUpDt;
@EmbeddedId

@AttributeOverrides( {
   @AttributeOverride(name="hospId", column=@Column(name="HOSP_ID", nullable=false) ), 
   @AttributeOverride(name="catId", column=@Column(name="ICD_Cat_Code", nullable=false) ),
   @AttributeOverride(name="phaseId", column=@Column(name="PHASE_ID", nullable=false) ),
   @AttributeOverride(name="renewal", column=@Column(name="RENEWAL", nullable=false) ),
   @AttributeOverride(name="schemeId", column=@Column(name="SCHEME_ID", nullable=false) )
   } )
public EhfmHospCatMstId getId() {
	return id;
}
public void setId(EhfmHospCatMstId id) {
	this.id = id;
}
@Column(name="lang_id" ,nullable=true)
public String getLangId() {
	return langId;
}
public void setLangId(String langId) {
	this.langId = langId;
}
@Column(name="dis_bedcapacity" ,nullable=true)
public Integer getDisBedCapacity() {
	return disBedCapacity;
}
public void setDisBedCapacity(Integer disBedCapacity) {
	this.disBedCapacity = disBedCapacity;
}
@Column(name="is_active_flg" ,nullable=true)
public String getIsActvFlg() {
	return isActvFlg;
}
public void setIsActvFlg(String isActvFlg) {
	this.isActvFlg = isActvFlg;
}
@Column(name="crt_usr" ,nullable=true)
public String getCrtUsr() {
	return crtUsr;
}
public void setCrtUsr(String crtUsr) {
	this.crtUsr = crtUsr;
}
@Column(name="crt_dt" ,nullable=true)
public Date getCrtDt() {
	return crtDt;
}
public void setCrtDt(Date crtDt) {
	this.crtDt = crtDt;
}
@Column(name="lst_upd_usr" ,nullable=true)
public String getLstUpdUsr() {
	return lstUpdUsr;
}
public void setLstUpdUsr(String lstUpdUsr) {
	this.lstUpdUsr = lstUpdUsr;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="lst_upd_dt" ,nullable=true)
public Date getLstUpDt() {
	return lstUpDt;
}
public void setLstUpDt(Date lstUpDt) {
	this.lstUpDt = lstUpDt;
}
public EhfmHospCatMst(EhfmHospCatMstId id, String langId,
		Integer disBedCapacity, String isActvFlg, String crtUsr, Date crtDt,
		String lstUpdUsr, Date lstUpDt) {
	super();
	this.id = id;
	this.langId = langId;
	this.disBedCapacity = disBedCapacity;
	this.isActvFlg = isActvFlg;
	this.crtUsr = crtUsr;
	this.crtDt = crtDt;
	this.lstUpdUsr = lstUpdUsr;
	this.lstUpDt = lstUpDt;
}
public EhfmHospCatMst() {
	super();
}


}
