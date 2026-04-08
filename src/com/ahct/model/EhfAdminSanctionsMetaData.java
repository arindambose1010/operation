
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
@Table(name = "EHF_ADMIN_SANCTIONS_META_DATA")
public class EhfAdminSanctionsMetaData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sanctionId;
	private String issuingAuthority;
	private String issuingAuthorityName;
	 private Date formDate;
	 private String subject;
	 private String deptName;
	 private String reference;
	 private String scheme;
	 private String sanctionAmount;
	 private String detailedHead;
	 private String detailedHeadName; 
	 private String subHead;
	 
	 private String subHeadName;
	 private String majorHead;
	 private String majorHeadName;
	 private String sourceOfBudget; 
	 private String sourceCode;
	 
	 private Date sanctionOrderDate; 
	 private Date purchaseDate;
	 private String inspectionAuthority;
	 private String executingAuthority;
	 private String vendorType;
	 private String vendorName;
	 private String vendorCode;
	 private String toBeIssuedOn;
	 private String typoOfSanction;
	 private String specification;
	 private String cost;
	 private Date crtDt;
     private String crtUsr;
   	 private Date lstUpdDt;
	 private String lstUpdUsr;
     
	 
	 @Id
	 @Column(name = "SANCTION_ID", nullable = false, length = 30)
	 public String getSanctionId() {
			return sanctionId;
		}
		public void setSanctionId(String sanctionId) {
			this.sanctionId = sanctionId;
		}
	  @Column(name = "ISSUING_AUTHORITY")
	 public String getIssuingAuthority() {
			return issuingAuthority;
		}
		public void setIssuingAuthority(String issuingAuthority) {
			this.issuingAuthority = issuingAuthority;
		}
		@Column(name = "ISSUING_AUTHORITY_NAME")
		public String getIssuingAuthorityName() {
			return issuingAuthorityName;
		}
		public void setIssuingAuthorityName(String issuingAuthorityName) {
			this.issuingAuthorityName = issuingAuthorityName;
		}
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name = "FORM_DATE")
		public Date getFormDate() {
			return formDate;
		}
		public void setFormDate(Date formDate) {
			this.formDate = formDate;
		}
		@Column(name = "SUBJECT")
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		@Column(name = "DEPARTMENT_ID")
		public String getDeptName() {
			return deptName;
		}
		public void setDeptName(String deptName) {
			this.deptName = deptName;
		}
		@Column(name = "REFERENCE")
		public String getReference() {
			return reference;
		}
		public void setReference(String reference) {
			this.reference = reference;
		}
		@Column(name = "SCHEME")
		public String getScheme() {
			return scheme;
		}
		public void setScheme(String scheme) {
			this.scheme = scheme;
		}
		@Column(name = "SANCTION_AMOUNT")
		public String getSanctionAmount() {
			return sanctionAmount;
		}
		public void setSanctionAmount(String sanctionAmount) {
			this.sanctionAmount = sanctionAmount;
		}
		@Column(name = "DETAILED_HEAD")
		public String getDetailedHead() {
			return detailedHead;
		}
		public void setDetailedHead(String detailedHead) {
			this.detailedHead = detailedHead;
		}
		@Column(name = "DETAILED_HEAD_NAME")
		public String getDetailedHeadName() {
			return detailedHeadName;
		}
		public void setDetailedHeadName(String detailedHeadName) {
			this.detailedHeadName = detailedHeadName;
		}
		@Column(name = "SUB_HEAD")
		public String getSubHead() {
			return subHead;
		}
		public void setSubHead(String subHead) {
			this.subHead = subHead;
		}
		@Column(name = "SUB_HEAD_NAME")
		public String getSubHeadName() {
			return subHeadName;
		}
		public void setSubHeadName(String subHeadName) {
			this.subHeadName = subHeadName;
		}
		@Column(name = "MAJOR_HEAD")
		public String getMajorHead() {
			return majorHead;
		}
		public void setMajorHead(String majorHead) {
			this.majorHead = majorHead;
		}
		@Column(name = "MAJOR_HEAD_NAME")
		public String getMajorHeadName() {
			return majorHeadName;
		}
		public void setMajorHeadName(String majorHeadName) {
			this.majorHeadName = majorHeadName;
		}
		@Column(name = "SOURCE_OF_BUDGET")
		public String getSourceOfBudget() {
			return sourceOfBudget;
		}
		public void setSourceOfBudget(String sourceOfBudget) {
			this.sourceOfBudget = sourceOfBudget;
		}
		@Column(name = "SOURCE_OF_BUDGET_CODE")
		public String getSourceCode() {
			return sourceCode;
		}
		public void setSourceCode(String sourceCode) {
			this.sourceCode = sourceCode;
		}
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name = "SANCTION_ORDER_DATE")
		public Date getSanctionOrderDate() {
			return sanctionOrderDate;
		}
		public void setSanctionOrderDate(Date sanctionOrderDate) {
			this.sanctionOrderDate = sanctionOrderDate;
		}
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name = "PURCHASE_VALIDITY_DATE")
		public Date getPurchaseDate() {
			return purchaseDate;
		}
		public void setPurchaseDate(Date purchaseDate) {
			this.purchaseDate = purchaseDate;
		}
		@Column(name = "INSPECTION_AUTHORITY")
		public String getInspectionAuthority() {
			return inspectionAuthority;
		}
		public void setInspectionAuthority(String inspectionAuthority) {
			this.inspectionAuthority = inspectionAuthority;
		}
		@Column(name = "EXECUTING_AUTHORITY")
		public String getExecutingAuthority() {
			return executingAuthority;
		}
		public void setExecutingAuthority(String executingAuthority) {
			this.executingAuthority = executingAuthority;
		}
		@Column(name = "VENDOR_TYPE")
		public String getVendorType() {
			return vendorType;
		}
		public void setVendorType(String vendorType) {
			this.vendorType = vendorType;
		}
		@Column(name = "VENDOR_NAME")
		public String getVendorName() {
			return vendorName;
		}
		public void setVendorName(String vendorName) {
			this.vendorName = vendorName;
		}
		@Column(name = "VENDOR_CODE")
		public String getVendorCode() {
			return vendorCode;
		}
		public void setVendorCode(String vendorCode) {
			this.vendorCode = vendorCode;
		}
		@Column(name = "TO_BE_ISSUED_ON")
		public String getToBeIssuedOn() {
			return toBeIssuedOn;
		}
		public void setToBeIssuedOn(String toBeIssuedOn) {
			this.toBeIssuedOn = toBeIssuedOn;
		}
		@Column(name = "TYPE_OF_SANCTION")
		public String getTypoOfSanction() {
			return typoOfSanction;
		}
		public void setTypoOfSanction(String typoOfSanction) {
			this.typoOfSanction = typoOfSanction;
		}
		@Column(name = "SPECIFICATION")
		public String getSpecification() {
			return specification;
		}
		public void setSpecification(String specification) {
			this.specification = specification;
		}
		@Column(name = "COST")
		public String getCost() {
			return cost;
		}
		public void setCost(String string) {
			this.cost = string;
		}
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name="CRT_DT", nullable = false)
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
		@Column(name="LST_UPD_DT", nullable = true)
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
}