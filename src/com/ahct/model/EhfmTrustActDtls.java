package com.ahct.model;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "EHFM_TRUST_ACT_DTLS")
public class EhfmTrustActDtls implements Serializable 
{
    private String accountNo;
    private String activeYn;
    private String actType;
    private String address;
    private String bankId;
    private String claimType;
    private Timestamp crtDt;
    private String crtUser;
    private String deductorType;
    private String description;
    private String errFileCode;
    private String fileCode;
    private String form16Address;
    private String id;
    private String mailId;
    private Long mobileNo;
    private String nameAsperAct;
    private String panNumber;
    private String schemeId;
    private String tanNumber;
    private String tdsPaymentUser;

    @Column(name="ACCOUNT_NO")
    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    @Column(name="ACTIVE_YN")
    public String getActiveYn() {
        return activeYn;
    }

    public void setActiveYn(String activeYn) {
        this.activeYn = activeYn;
    }
    @Id
    @Column(name="ACT_TYPE", nullable = false)
    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="BANK_ID")
    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    @Column(name="CLAIM_TYPE")
    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    @Column(name="CRT_DT")
    public Timestamp getCrtDt() {
        return crtDt;
    }

    public void setCrtDt(Timestamp crtDt) {
        this.crtDt = crtDt;
    }

    @Column(name="CRT_USER")
    public String getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    @Column(name="DEDUCTOR_TYPE")
    public String getDeductorType() {
        return deductorType;
    }

    public void setDeductorType(String deductorType) {
        this.deductorType = deductorType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="ERR_FILE_CODE")
    public String getErrFileCode() {
        return errFileCode;
    }

    public void setErrFileCode(String errFileCode) {
        this.errFileCode = errFileCode;
    }

    @Column(name="FILE_CODE")
    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    @Column(name="FORM16_ADDRESS")
    public String getForm16Address() {
        return form16Address;
    }

    public void setForm16Address(String form16Address) {
        this.form16Address = form16Address;
    }

	@Column(name="ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="MAIL_ID")
    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    @Column(name="MOBILE_NO")
    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Column(name="NAME_ASPER_ACT")
    public String getNameAsperAct() {
        return nameAsperAct;
    }

    public void setNameAsperAct(String nameAsperAct) {
        this.nameAsperAct = nameAsperAct;
    }

    @Column(name="PAN_NUMBER")
    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    @Column(name="SCHEME_ID")
    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    @Column(name="TAN_NUMBER")
    public String getTanNumber() {
        return tanNumber;
    }

    public void setTanNumber(String tanNumber) {
        this.tanNumber = tanNumber;
    }

    @Column(name="TDS_PAYMENT_USER")
    public String getTdsPaymentUser() {
        return tdsPaymentUser;
    }

    public void setTdsPaymentUser(String tdsPaymentUser) {
        this.tdsPaymentUser = tdsPaymentUser;
    }
}
