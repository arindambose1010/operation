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
@Table(name = "EHF_FOLLOWUP_CHKLST")
public class EhfFollowupChklst implements Serializable{

	public String caseFollowupId;
	public String exeDisphotoChklst;
	public String exeDisphotoremark;
	public String exePatphotoChklst;
	public String exePatphotoRemark;
	public String exeAcqvrifyChklst;
	public String exeAcqverifyRemark;
	public String exeMedverifyChklst;
	public String exeMedVerifyRemark;
	public String exeQuantverifyRemark;
	public String exeQuantverifyChklst;
	public String exereprtcheckChklst;
	public String exeReprtcheckRemark;
	public String ftdRemarkvrifedChklst;
	public String ftdRemarkvrifedRemark;
	public String ftdBeneficiryChklst;
	public String ftdBeneficiryRemark;
	private Date crtDt;
    private String crtUsr;
	private String lstUpdUsr;
	private Date lstUpdDt;

	@Id
	@Column(name="case_followup_id",nullable= false)
	 public String getCaseFollowupId() {
		return caseFollowupId;
	}
	public void setCaseFollowupId(String caseFollowupId) {
		this.caseFollowupId = caseFollowupId;
	}
	@Column(name="exe_disPhoto_chklst") 
	public String getExeDisphotoChklst() {
		return exeDisphotoChklst;
	}
	public void setExeDisphotoChklst(String exeDisphotoChklst) {
		this.exeDisphotoChklst = exeDisphotoChklst;
	}
	@Column(name="exe_disPhoto_remark") 
	public String getExeDisphotoremark() {
		return exeDisphotoremark;
	}
	public void setExeDisphotoremark(String exeDisphotoremark) {
		this.exeDisphotoremark = exeDisphotoremark;
	}
	@Column(name="exe_patPhoto_chklst") 
	public String getExePatphotoChklst() {
		return exePatphotoChklst;
	}
	public void setExePatphotoChklst(String exePatphotoChklst) {
		this.exePatphotoChklst = exePatphotoChklst;
	}
	@Column(name="exe_patPhoto_remark") 
	public String getExePatphotoRemark() {
		return exePatphotoRemark;
	}
	public void setExePatphotoRemark(String exePatphotoRemark) {
		this.exePatphotoRemark = exePatphotoRemark;
	}
	@Column(name="exe_acqVrify_chklst") 
	public String getExeAcqvrifyChklst() {
		return exeAcqvrifyChklst;
	}
	public void setExeAcqvrifyChklst(String exeAcqvrifyChklst) {
		this.exeAcqvrifyChklst = exeAcqvrifyChklst;
	}
	@Column(name="exe_acqVrify_remark") 
	public String getExeAcqverifyRemark() {
		return exeAcqverifyRemark;
	}
	public void setExeAcqverifyRemark(String exeAcqverifyRemark) {
		this.exeAcqverifyRemark = exeAcqverifyRemark;
	}
	@Column(name="exe_medVerify_chklst") 
	public String getExeMedverifyChklst() {
		return exeMedverifyChklst;
	}
	public void setExeMedverifyChklst(String exeMedverifyChklst) {
		this.exeMedverifyChklst = exeMedverifyChklst;
	}
	@Column(name="exe_medVerify_remark") 
	public String getExeMedVerifyRemark() {
		return exeMedVerifyRemark;
	}
	public void setExeMedVerifyRemark(String exeMedVerifyRemark) {
		this.exeMedVerifyRemark = exeMedVerifyRemark;
	}
	@Column(name="exe_quantVerfy_chklst") 
	public String getExeQuantverifyRemark() {
		return exeQuantverifyRemark;
	}
	public void setExeQuantverifyRemark(String exeQuantverifyRemark) {
		this.exeQuantverifyRemark = exeQuantverifyRemark;
	}
	@Column(name="exe_quantVerfy_remark") 
	public String getExeQuantverifyChklst() {
		return exeQuantverifyChklst;
	}
	public void setExeQuantverifyChklst(String exeQuantverifyChklst) {
		this.exeQuantverifyChklst = exeQuantverifyChklst;
	}
	@Column(name="exe_reprtCheck_chklst") 
	public String getExereprtcheckChklst() {
		return exereprtcheckChklst;
	}
	public void setExereprtcheckChklst(String exereprtcheckChklst) {
		this.exereprtcheckChklst = exereprtcheckChklst;
	}
	@Column(name="exe_reprtCheck_remark") 
	public String getExeReprtcheckRemark() {
		return exeReprtcheckRemark;
	}
	public void setExeReprtcheckRemark(String exeReprtcheckRemark) {
		this.exeReprtcheckRemark = exeReprtcheckRemark;
	}
	@Column(name="ftd_remrkVrifed_chklst") 
	public String getFtdRemarkvrifedChklst() {
		return ftdRemarkvrifedChklst;
	}
	public void setFtdRemarkvrifedChklst(String ftdRemarkvrifedChklst) {
		this.ftdRemarkvrifedChklst = ftdRemarkvrifedChklst;
	}
	@Column(name="ftd_remrkVrifed_remrk") 
	public String getFtdRemarkvrifedRemark() {
		return ftdRemarkvrifedRemark;
	}
	public void setFtdRemarkvrifedRemark(String ftdRemarkvrifedRemark) {
		this.ftdRemarkvrifedRemark = ftdRemarkvrifedRemark;
	}
	@Column(name="ftd_beneficiry_chklst") 
	public String getFtdBeneficiryChklst() {
		return ftdBeneficiryChklst;
	}
	public void setFtdBeneficiryChklst(String ftdBeneficiryChklst) {
		this.ftdBeneficiryChklst = ftdBeneficiryChklst;
	}
	@Column(name="ftd_beneficiry_remark") 
	public String getFtdBeneficiryRemark() {
		return ftdBeneficiryRemark;
	}
	public void setFtdBeneficiryRemark(String ftdBeneficiryRemark) {
		this.ftdBeneficiryRemark = ftdBeneficiryRemark;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Column(name="lst_upd_by")
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

@Temporal(TemporalType.TIMESTAMP)
@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}	  
}
