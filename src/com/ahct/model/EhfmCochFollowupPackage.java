package com.ahct.model;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the EHFM_COCH_FOLLOWUP_PACKAGES database table.
 * 
 */
@Entity
@Table(name="EHFM_COCH_FOLLOWUP_PACKAGES")
public class EhfmCochFollowupPackage implements Serializable {
	private static final long serialVersionUID = 1L;
	private EhfmCochFollowupPackagePK id;
	private String activeYn;
	private String cochelarFollowupMstId;
	private Date crtDt;
	private String crtUsr;
	private Date effEndDt;
	private Date effStrtDt;
	private String cochlearCount;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private BigDecimal packageAmt;

    public EhfmCochFollowupPackage() {
    }


	@EmbeddedId
	public EhfmCochFollowupPackagePK getId() {
		return this.id;
	}

	public void setId(EhfmCochFollowupPackagePK id) {
		this.id = id;
	}
	

	@Column(name="ACTIVE_YN")
	public String getActiveYn() {
		return this.activeYn;
	}

	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}


	@Column(name="COCHELAR_FOLLOWUP_MST_ID")
	public String getCochelarFollowupMstId() {
		return this.cochelarFollowupMstId;
	}

	public void setCochelarFollowupMstId(String cochelarFollowupMstId) {
		this.cochelarFollowupMstId = cochelarFollowupMstId;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}


	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="EFF_END_DT")
	public Date getEffEndDt() {
		return this.effEndDt;
	}

	public void setEffEndDt(Date effEndDt) {
		this.effEndDt = effEndDt;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="EFF_STRT_DT")
	public Date getEffStrtDt() {
		return this.effStrtDt;
	}

	public void setEffStrtDt(Date effStrtDt) {
		this.effStrtDt = effStrtDt;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}


	@Column(name="LST_UPD_USR")
	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}


	@Column(name="PACKAGE_AMT")
	public BigDecimal getPackageAmt() {
		return this.packageAmt;
	}

	public void setPackageAmt(BigDecimal packageAmt) {
		this.packageAmt = packageAmt;
	}

	@Column(name="COCHLEAR_COUNT")
	public String getCochlearCount() {
		return cochlearCount;
	}

	public void setCochlearCount(String cochlearCount) {
		this.cochlearCount = cochlearCount;
	}
	
	
	
	
	

}