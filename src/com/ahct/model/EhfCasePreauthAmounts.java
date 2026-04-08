package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "ehf_case_preauth_amounts")
public class EhfCasePreauthAmounts implements Serializable{
private String caseId;
private Double comorbidAmt;
private Double packgAmt;
private Double totPackgAmt;
private Double ppdAppvComorbidAmt;
private Double ppdAppvPackageAmt;
private Double ppdAppvTotalAmt;
private Date ppdAppvDate;
private Double ptdAppvComorbidAmt;
private Double ptdAppvPackageAmt;
private Double ptdAppvTotalPackgAmt;
private Date ptdAppvDate;
private Double ceoAppvComorbidAmt;
private Double ceoAppvPackgAmt;
private Double ceoAppvTotalAmt;
private Date ceoappvDt;
private Date preauthInitiatedDt;
private Date preauthFwdDt;
private Double enhAmt;
private Date enhReqdate;
private Date ennAppvDt;
private Double enhAppvAmt;
private Date enhRejDate;
private String crtUsr;
private String lstUpdUsr;
private Date crtDt;
private Date lstUpdDt;


private Double eoAppvComorbidAmt;
private Double eoAppvPackgAmt;
private Double eoAppvTotalAmt;
private Date eoappvDt;

private Double chEnhAppvAmt;
private Date chEnhAppvDt;
private Double eoEnhAppvAmt;
private Date eoEnhAppvDt;
private Double ceoEnhAppvAmt;
private Date ceoEnhAppvDt;



@Column(name="ch_enh_appv_amt")
public Double getChEnhAppvAmt() {
	return chEnhAppvAmt;
}
public void setChEnhAppvAmt(Double chEnhAppvAmt) {
	this.chEnhAppvAmt = chEnhAppvAmt;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="ch_enh_appv_dt")
public Date getChEnhAppvDt() {
	return chEnhAppvDt;
}
public void setChEnhAppvDt(Date chEnhAppvDt) {
	this.chEnhAppvDt = chEnhAppvDt;
}
@Column(name="eo_enh_appv_amt")
public Double getEoEnhAppvAmt() {
	return eoEnhAppvAmt;
}
public void setEoEnhAppvAmt(Double eoEnhAppvAmt) {
	this.eoEnhAppvAmt = eoEnhAppvAmt;
}

@Temporal(TemporalType.TIMESTAMP)
@Column(name="eo_enh_appv_dt")
public Date getEoEnhAppvDt() {
	return eoEnhAppvDt;
}
public void setEoEnhAppvDt(Date eoEnhAppvDt) {
	this.eoEnhAppvDt = eoEnhAppvDt;
}
@Column(name="ceo_enh_appv_amt")
public Double getCeoEnhAppvAmt() {
	return ceoEnhAppvAmt;
}
public void setCeoEnhAppvAmt(Double ceoEnhAppvAmt) {
	this.ceoEnhAppvAmt = ceoEnhAppvAmt;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="ceo_enh_appv_dt")
public Date getCeoEnhAppvDt() {
	return ceoEnhAppvDt;
}
public void setCeoEnhAppvDt(Date ceoEnhAppvDt) {
	this.ceoEnhAppvDt = ceoEnhAppvDt;
}
@Column(name="eo_appv_comorbid_amt")
public Double getEoAppvComorbidAmt() {
	return eoAppvComorbidAmt;
}
public void setEoAppvComorbidAmt(Double eoAppvComorbidAmt) {
	this.eoAppvComorbidAmt = eoAppvComorbidAmt;
}
@Column(name="eo_appv_package_amt")
public Double getEoAppvPackgAmt() {
	return eoAppvPackgAmt;
}
public void setEoAppvPackgAmt(Double eoAppvPackgAmt) {
	this.eoAppvPackgAmt = eoAppvPackgAmt;
}
@Column(name="eo_appv_total_package_amt")
public Double getEoAppvTotalAmt() {
	return eoAppvTotalAmt;
}
public void setEoAppvTotalAmt(Double eoAppvTotalAmt) {
	this.eoAppvTotalAmt = eoAppvTotalAmt;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="eo_appv_date")
public Date getEoappvDt() {
	return eoappvDt;
}
public void setEoappvDt(Date eoappvDt) {
	this.eoappvDt = eoappvDt;
}
@Id
@Column(name="CASE_ID")
public String getCaseId() {
	return caseId;
}
public void setCaseId(String caseId) {
	this.caseId = caseId;
}
@Column(name="comorbid_amt")
public Double getComorbidAmt() {
	return comorbidAmt;
}
public void setComorbidAmt(Double comorbidAmt) {
	this.comorbidAmt = comorbidAmt;
}
@Column(name="package_amt")
public Double getPackgAmt() {
	return packgAmt;
}
public void setPackgAmt(Double packgAmt) {
	this.packgAmt = packgAmt;
}
@Column(name="total_package_amt")
public Double getTotPackgAmt() {
	return totPackgAmt;
}
public void setTotPackgAmt(Double totPackgAmt) {
	this.totPackgAmt = totPackgAmt;
}
@Column(name="ppd_appv_comorbid_amt")
public Double getPpdAppvComorbidAmt() {
	return ppdAppvComorbidAmt;
}
public void setPpdAppvComorbidAmt(Double ppdAppvComorbidAmt) {
	this.ppdAppvComorbidAmt = ppdAppvComorbidAmt;
}
@Column(name="ppd_appv_package_amt")
public Double getPpdAppvPackageAmt() {
	return ppdAppvPackageAmt;
}
public void setPpdAppvPackageAmt(Double ppdAppvPackageAmt) {
	this.ppdAppvPackageAmt = ppdAppvPackageAmt;
}
@Column(name="ppd_appv_total_package_amt")
public Double getPpdAppvTotalAmt() {
	return ppdAppvTotalAmt;
}
public void setPpdAppvTotalAmt(Double ppdAppvTotalAmt) {
	this.ppdAppvTotalAmt = ppdAppvTotalAmt;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="ppd_apprv_date")
public Date getPpdAppvDate() {
	return ppdAppvDate;
}
public void setPpdAppvDate(Date ppdAppvDate) {
	this.ppdAppvDate = ppdAppvDate;
}
@Column(name="ptd_appv_comorbid_amt")
public Double getPtdAppvComorbidAmt() {
	return ptdAppvComorbidAmt;
}
public void setPtdAppvComorbidAmt(Double ptdAppvComorbidAmt) {
	this.ptdAppvComorbidAmt = ptdAppvComorbidAmt;
}
@Column(name="ptd_appv_package_amt")
public Double getPtdAppvPackageAmt() {
	return ptdAppvPackageAmt;
}
public void setPtdAppvPackageAmt(Double ptdAppvPackageAmt) {
	this.ptdAppvPackageAmt = ptdAppvPackageAmt;
}
@Column(name="ptd_appv_total_package_amt")
public Double getPtdAppvTotalPackgAmt() {
	return ptdAppvTotalPackgAmt;
}
public void setPtdAppvTotalPackgAmt(Double ptdAppvTotalPackgAmt) {
	this.ptdAppvTotalPackgAmt = ptdAppvTotalPackgAmt;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="ptd_appv_date")
public Date getPtdAppvDate() {
	return ptdAppvDate;
}
public void setPtdAppvDate(Date ptdAppvDate) {
	this.ptdAppvDate = ptdAppvDate;
}
@Column(name="ceo_appv_comorbid_amt")
public Double getCeoAppvComorbidAmt() {
	return ceoAppvComorbidAmt;
}
public void setCeoAppvComorbidAmt(Double ceoAppvComorbidAmt) {
	this.ceoAppvComorbidAmt = ceoAppvComorbidAmt;
}
@Column(name="ceo_appv_package_amt")
public Double getCeoAppvPackgAmt() {
	return ceoAppvPackgAmt;
}
public void setCeoAppvPackgAmt(Double ceoAppvPackgAmt) {
	this.ceoAppvPackgAmt = ceoAppvPackgAmt;
}
@Column(name="ceo_appv_total_package_amt")
public Double getCeoAppvTotalAmt() {
	return ceoAppvTotalAmt;
}
public void setCeoAppvTotalAmt(Double ceoAppvTotalAmt) {
	this.ceoAppvTotalAmt = ceoAppvTotalAmt;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="ceo_appv_date")
public Date getCeoappvDt() {
	return ceoappvDt;
}
public void setCeoappvDt(Date ceoappvDt) {
	this.ceoappvDt = ceoappvDt;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="preauth_initiated_dt")
public Date getPreauthInitiatedDt() {
	return preauthInitiatedDt;
}
public void setPreauthInitiatedDt(Date preauthInitiatedDt) {
	this.preauthInitiatedDt = preauthInitiatedDt;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="preauth_nam_forwarded_dt")
public Date getPreauthFwdDt() {
	return preauthFwdDt;
}
public void setPreauthFwdDt(Date preauthFwdDt) {
	this.preauthFwdDt = preauthFwdDt;
}
@Column(name="enh_req_amt")
public Double getEnhAmt() {
	return enhAmt;
}
public void setEnhAmt(Double enhAmt) {
	this.enhAmt = enhAmt;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="enh_req_date")
public Date getEnhReqdate() {
	return enhReqdate;
}
public void setEnhReqdate(Date enhReqdate) {
	this.enhReqdate = enhReqdate;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="enh_appv_date")
public Date getEnnAppvDt() {
	return ennAppvDt;
}
public void setEnnAppvDt(Date ennAppvDt) {
	this.ennAppvDt = ennAppvDt;
}
@Column(name="enh_appv_amt")
public Double getEnhAppvAmt() {
	return enhAppvAmt;
}
public void setEnhAppvAmt(Double enhAppvAmt) {
	this.enhAppvAmt = enhAppvAmt;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="enh_rej_date")
public Date getEnhRejDate() {
	return enhRejDate;
}
public void setEnhRejDate(Date enhRejDate) {
	this.enhRejDate = enhRejDate;
}
@Column(name="crt_user")
public String getCrtUsr() {
	return crtUsr;
}
public void setCrtUsr(String crtUsr) {
	this.crtUsr = crtUsr;
}
@Column(name="lst_upd_usr")
public String getLstUpdUsr() {
	return lstUpdUsr;
}
public void setLstUpdUsr(String lstUpdUsr) {
	this.lstUpdUsr = lstUpdUsr;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="crt_dt")
public Date getCrtDt() {
	return crtDt;
}
public void setCrtDt(Date crtDt) {
	this.crtDt = crtDt;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="lst_up_dt")
public Date getLstUpdDt() {
	return lstUpdDt;
}
public void setLstUpdDt(Date lstUpdDt) {
	this.lstUpdDt = lstUpdDt;
}
public EhfCasePreauthAmounts() {
	super();
}



}
