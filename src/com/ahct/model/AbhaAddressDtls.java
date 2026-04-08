package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Chandana - 8326 - The persistent class for the ABHA_ADDRESS_DTLS database table.
 * 
 */
@Entity
@Table(name="ABHA_ADDRESS_DTLS")
@NamedQuery(name="AbhaAddressDtls.findAll", query="SELECT a FROM AbhaAddressDtls a")
public class AbhaAddressDtls implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "abha_addr_seq")
    @SequenceGenerator(name = "abha_addr_seq", sequenceName = "SEQ_ABHA_ADDR_PK_ID", allocationSize = 1)
	@Column(name="ABHA_ADDR_PK_ID")
	private long abhaAddrPkId;

	@Column(name="ABHA_ADDRESS")
	private String abhaAddress;

	@Column(name="ABHA_ID")
	private String abhaId;

	@Column(name="ACTIVE_YN")
	private String activeYn;

	@Temporal(TemporalType.DATE)
	@Column(name="CRT_DT")
	private Date crtDt;

	@Column(name="CRT_USR")
	private String crtUsr;

	@Temporal(TemporalType.DATE)
	@Column(name="LST_UPD_DT")
	private Date lstUpdDt;

	@Column(name="LST_UPD_USR")
	private String lstUpdUsr;

	public AbhaAddressDtls() {
	}

	public long getAbhaAddrPkId() {
		return this.abhaAddrPkId;
	}

	public void setAbhaAddrPkId(long abhaAddrPkId) {
		this.abhaAddrPkId = abhaAddrPkId;
	}

	public String getAbhaAddress() {
		return this.abhaAddress;
	}

	public void setAbhaAddress(String abhaAddress) {
		this.abhaAddress = abhaAddress;
	}

	public String getAbhaId() {
		return this.abhaId;
	}

	public void setAbhaId(String abhaId) {
		this.abhaId = abhaId;
	}

	public String getActiveYn() {
		return this.activeYn;
	}

	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}

	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

}

