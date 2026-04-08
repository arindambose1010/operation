package com.ahct.model;

import java.io.Serializable;
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
@Table(name="ASRIM_COMBO"
)
public class AsrimCombo implements Serializable
{
    private AsrimComboId id;
    private String cmbDtlName;
    private String cmbHdrId;
    private String cmbParntDtlId;
    private Integer cmbOrder;
    private String cmbDtlVal;
    private String crtUsr;
    private Date crtDt;
    private String lstUpdUsr;
    private Date lstUpdDt;
  //  private String locId;
    private String schemeId;
    private String roleStatusYn;
    private String attachDocType;
    private String attachUpdStatus;
   // private List<AsrimCaseStatusAttach> asrimCaseStatusAttach = new ArrayList<AsrimCaseStatusAttach>();
  


    
  
   /*@OneToMany(mappedBy="asrimCombo")
	public List<AsrimCaseStatusAttach> getAsrimCaseStatusAttach() {
		return asrimCaseStatusAttach;
	}



	public void setAsrimCaseStatusAttach(List<AsrimCaseStatusAttach> asrimCaseStatusAttach) {
		this.asrimCaseStatusAttach = asrimCaseStatusAttach;
	}
  */

	// Constructors
	public void setAttachUpdStatus(String attachUpdStatus) {
		this.attachUpdStatus = attachUpdStatus;
	}



	/** default constructor */
    public AsrimCombo() {
    }

     
    
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
       @AttributeOverride(name="cmbDtlId", column=@Column(name="CMB_DTL_ID", nullable=false, length=10) ), 
       @AttributeOverride(name="langId", column=@Column(name="LANG_ID", nullable=false, length=5) ) } )

    public AsrimComboId getId() {
       return this.id;
    }
    
    public void setId(AsrimComboId id) {
       this.id = id;
    }
       
    
    @Column(name="CMB_DTL_NAME", nullable=false, length=100)

    public String getCmbDtlName() {
       return this.cmbDtlName;
    }
    
    public void setCmbDtlName(String cmbDtlName) {
       this.cmbDtlName = cmbDtlName;
    }
    
    @Column(name="CMB_HDR_ID", nullable=false, length=5)

    public String getCmbHdrId() {
       return this.cmbHdrId;
    }
    
    public void setCmbHdrId(String cmbHdrId) {
       this.cmbHdrId = cmbHdrId;
    }
    
    @Column(name="CMB_PARNT_DTL_ID", length=10)

    public String getCmbParntDtlId() {
       return this.cmbParntDtlId;
    }
    
    public void setCmbParntDtlId(String cmbParntDtlId) {
       this.cmbParntDtlId = cmbParntDtlId;
    }
    
    @Column(name="CMB_ORDER", nullable=false, precision=3, scale=0)

    public Integer getCmbOrder() {
       return this.cmbOrder;
    }
    
    public void setCmbOrder(Integer cmbOrder) {
       this.cmbOrder = cmbOrder;
    }
    
    @Column(name="CMB_DTL_VAL", length=200)

    public String getCmbDtlVal() {
       return this.cmbDtlVal;
    }
    
    public void setCmbDtlVal(String cmbDtlVal) {
       this.cmbDtlVal = cmbDtlVal;
    }
    
    @Column(name="CRT_USR", nullable=false, length=12)

    public String getCrtUsr() {
       return this.crtUsr;
    }
    
    public void setCrtUsr(String crtUsr) {
       this.crtUsr = crtUsr;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CRT_DT", nullable=false, length=7)

    public Date getCrtDt() {
       return this.crtDt;
    }
    
    public void setCrtDt(Date crtDt) {
       this.crtDt = crtDt;
    }
    
    @Column(name="LST_UPD_USR", length=12)

    public String getLstUpdUsr() {
       return this.lstUpdUsr;
    }
    
    public void setLstUpdUsr(String lstUpdUsr) {
       this.lstUpdUsr = lstUpdUsr;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="LST_UPD_DT", length=7)

    public Date getLstUpdDt() {
       return this.lstUpdDt;
    }
    
    public void setLstUpdDt(Date lstUpdDt) {
       this.lstUpdDt = lstUpdDt;
    }
    
    @Column(name="LOC_ID", length=5)
/**
    public String getLocId() {
       return this.locId;
    }
    
    public void setLocId(String locId) {
       this.locId = locId;
    }

*/
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }
    @Column(name="SCHEME_ID", length=5)
    public String getSchemeId() {
        return schemeId;
    }

    public void setRoleStatusYn(String roleStatusYn) {
        this.roleStatusYn = roleStatusYn;
    }

    @Column(name="role_status_yn")
    public String getRoleStatusYn() {
        return roleStatusYn;
    }
    @Column(name="attch_doc_typ", nullable=true, length=20)
    public String getAttachDocType() {
		return attachDocType;
	}

	public void setAttachDocType(String attachDocType) {
		this.attachDocType = attachDocType;
	}

	  @Column(name="attch_upd_stat", nullable=false, length=20)
	public String getAttachUpdStatus() {
		return attachUpdStatus;
	}
}

