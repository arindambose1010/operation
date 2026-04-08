package com.ahct.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "EHFM_USERS")
public class EhfmUsers implements Serializable {
	
	@Id
	@Column(name = "user_id", nullable = false, length = 12)
	private String userId;
	@Column(name = "loc_id", nullable = false, length = 5)
	private String locId;
	@Column(name = "lang_id", nullable = false, length = 5)
	private String langId;
	@Column(name = "user_no", nullable = false)
	private String userNo;
	@Column(name = "login_name", nullable = false)
	private String loginName;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "doj")
	private Date doj;
	@Column(name = "dsgn_id", nullable = false)
	private String dsgnId;
	@Column(name = "email_id")
	private String emailId;
	@Column(name = "service_flg", nullable = false)
	private String serviceFlag;
	@Column(name = "mobile_no")
	private String mobileNo;
	@Column(name = "service_expiry_dt")
	private Date serviceExpiryDt;
	@Column(name = "forgot_pwd")
	private String forgetPwd;
	@Column(name = "frst_login_flg")
	private String frstLognFlg;
	@Column(name = "passwd")
	private String passwd;
	@Column(name = "bio_auth_req")
	private String bioAuthReq;
	@Column(name = "digi_verify_req")
	private String digiVerifyReq;
	@Column(name = "user_type")
	private String userType;
	@Column(name = "period_from")
	private Date periodFrom;
	@Column(name = "period_to")
	private Date periodTo;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "crt_dt")
	private Date crtDt;
	@Column(name = "crt_usr", nullable = false)
	private String crtUsr;
	@Column(name = "lst_upd_usr")
	private String lstUpdUser;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lst_upd_dt")
	private Date lstUpdDt;
	@Column(name = "theme")
	private String theme;
	@Column(name = "VPN_ACCESS")
	private String vpnAccess;
	public String getVpnAccess() {
		return vpnAccess;
	}

	public void setVpnAccess(String vpnAccess) {
		this.vpnAccess = vpnAccess;
	}
	/*@OneToMany(mappedBy="ehfmUsers")
	private Set<EhfEnrollAudit> ehfEnrollAudit;*/
	public EhfmUsers() {
		super();
	}

	public EhfmUsers(String userId, String locId, String langId, String userNo,
			String loginName, String firstName, String lastName,
			String middleName, Date doj, String dsgnId, String emailId,
			String serviceFlag, String mobileNo, Date serviceExpiryDt,
			String forgetPwd, String frstLognFlg, String passwd,
			String bioAuthReq, String digiVerifyReq, String userType,
			Date periodFrom, Date periodTo, Date crtDt, String crtUsr,
			String lstUpdUser, Date lstUpdDt, String theme,String vpnAccess) {
		super();
		this.userId = userId;
		this.locId = locId;
		this.langId = langId;
		this.userNo = userNo;
		this.loginName = loginName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.doj = doj;
		this.dsgnId = dsgnId;
		this.emailId = emailId;
		this.serviceFlag = serviceFlag;
		this.mobileNo = mobileNo;
		this.serviceExpiryDt = serviceExpiryDt;
		this.forgetPwd = forgetPwd;
		this.frstLognFlg = frstLognFlg;
		this.passwd = passwd;
		this.bioAuthReq = bioAuthReq;
		this.digiVerifyReq = digiVerifyReq;
		this.userType = userType;
		this.periodFrom = periodFrom;
		this.periodTo = periodTo;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdUser = lstUpdUser;
		this.lstUpdDt = lstUpdDt;
		this.theme = theme;
		this.vpnAccess=vpnAccess;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getDsgnId() {
		return dsgnId;
	}

	public void setDsgnId(String dsgnId) {
		this.dsgnId = dsgnId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getServiceFlag() {
		return serviceFlag;
	}

	public void setServiceFlag(String serviceFlag) {
		this.serviceFlag = serviceFlag;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Date getServiceExpiryDt() {
		return serviceExpiryDt;
	}

	public void setServiceExpiryDt(Date serviceExpiryDt) {
		this.serviceExpiryDt = serviceExpiryDt;
	}

	public String getForgetPwd() {
		return forgetPwd;
	}

	public void setForgetPwd(String forgetPwd) {
		this.forgetPwd = forgetPwd;
	}

	public String getFrstLognFlg() {
		return frstLognFlg;
	}

	public void setFrstLognFlg(String frstLognFlg) {
		this.frstLognFlg = frstLognFlg;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getBioAuthReq() {
		return bioAuthReq;
	}

	public void setBioAuthReq(String bioAuthReq) {
		this.bioAuthReq = bioAuthReq;
	}

	public String getDigiVerifyReq() {
		return digiVerifyReq;
	}

	public void setDigiVerifyReq(String digiVerifyReq) {
		this.digiVerifyReq = digiVerifyReq;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Date getPeriodFrom() {
		return periodFrom;
	}

	public void setPeriodFrom(Date periodFrom) {
		this.periodFrom = periodFrom;
	}

	public Date getPeriodTo() {
		return periodTo;
	}

	public void setPeriodTo(Date periodTo) {
		this.periodTo = periodTo;
	}

	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}

	public Date getLstUpdDt() {
		return lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLocId() {
		return locId;
	}

	public void setLocId(String locId) {
		this.locId = locId;
	}

	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	/*@OneToMany(mappedBy="ehfmUsers")
	public Set<EhfEnrollAudit> getEhfEnrollAudit() {
		return ehfEnrollAudit;
	}

	public void setEhfEnrollAudit(Set<EhfEnrollAudit> ehfEnrollAudit) {
		this.ehfEnrollAudit = ehfEnrollAudit;
	}
*/
	
}
