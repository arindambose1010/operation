package com.ahct.model;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name = "AsritAccountsDocAttch.findAll", 
    query = "select o from AsritAccountsDocAttch o")
@Table(name = "EHF_ACCOUNTS_DOC_ATTCH")
public class AsritAccountsDocAttch implements java.io.Serializable
{
	private AsritAccountsDocAttchPK id;
    private String actualName;
    private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;
    private String hasBeenDeleted;
    public AsritAccountsDocAttch() {
    }
    
    public void setId(AsritAccountsDocAttchPK id) {
        this.id = id;
    }

    
    @EmbeddedId
    @AttributeOverrides( {
        @AttributeOverride(name="transId", column=@Column(name="TRANS_ID", nullable=false) ), 
        @AttributeOverride(name="savedName", column=@Column(name="SAVED_NAME", nullable=false) )
        } )
    public AsritAccountsDocAttchPK getId() {
        return id;
    }

    @Column(name="ACTUAL_NAME")
    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LST_UPD_DT")
    public Date getLstUpdDt() {
        return lstUpdDt;
    }

    public void setLstUpdDt(Date lstUpdDt) {
        this.lstUpdDt = lstUpdDt;
    }

    @Column(name="LST_UPD_USR")
    public String getLstUpdUsr() {
        return lstUpdUsr;
    }

    public void setLstUpdUsr(String lstUpdUsr) {
        this.lstUpdUsr = lstUpdUsr;
    }

    
	public void setHasBeenDeleted(String hasBeenDeleted) {
		this.hasBeenDeleted = hasBeenDeleted;
	}

	@Column(name="HAS_BEEN_DELETED")
	public String getHasBeenDeleted() {
		return hasBeenDeleted;
	}
}
