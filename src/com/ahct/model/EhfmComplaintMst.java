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
@Table(name="EHFM_COMPLAINT_MST")
public class EhfmComplaintMst  implements java.io.Serializable{

	private EhfmComplaintMstId id;
	private String complName;
	private String mainComplName;
	private String activeYn;
	//private String preOpPostOp;
	private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;
    
    
    
	 public EhfmComplaintMst() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EhfmComplaintMst(EhfmComplaintMstId id, String complName,
			String mainComplName, String activeYn, Date crtDt, String crtUsr,
			Date lstUpdDt, String lstUpdUsr) {
		super();
		this.id = id;
		this.complName = complName;
		this.mainComplName = mainComplName;
		this.activeYn = activeYn;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdDt = lstUpdDt;
		this.lstUpdUsr = lstUpdUsr;
	}
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "complCode", column = @Column(name = "compl_code", nullable = false, length = 100)),
			@AttributeOverride(name = "mainComplCode", column = @Column(name = "main_compl_code", nullable = false, length = 100))
			})
	public EhfmComplaintMstId getId() {
		return id;
	}

	public void setId(EhfmComplaintMstId id) {
		this.id = id;
	}

	@Column(name="compl_name")
	public String getComplName() {
		return complName;
	}

	public void setComplName(String complName) {
		this.complName = complName;
	}
	 
	 @Column(name="main_compl_name")
	public String getMainComplName() {
		return mainComplName;
	}

	public void setMainComplName(String mainComplName) {
		this.mainComplName = mainComplName;
	}

	@Column(name="active_yn")
	public String getActiveYn() {
		return activeYn;
	}
	
	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	/*@Column(name="pre_or_post_op")
	public String getPreOpPostOp() {
		return preOpPostOp;
	}
	public void setPreOpPostOp(String preOpPostOp) {
		this.preOpPostOp = preOpPostOp;
	}*/
	
	@Column(name="CRT_USR", nullable = false)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT", nullable = false)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="LST_UPD_USR", nullable = true)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT", nullable = true)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
}
