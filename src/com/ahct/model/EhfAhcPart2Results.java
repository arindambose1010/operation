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
@Table(name="EHF_AHC_PART2_RESULTS"
)
public class EhfAhcPart2Results implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ahcId ;
    private String r27;
    private String r28;
    private String r29;
    private String r30;
    private String r31;
    private String r32;
    private String r33;
    private String r34;
    private String r35;
    private String r36;
    private String r37;
    private String r38;
    private String r39;
    private String r40;
    private String r41;
    private String r42;
    private String r43;
    private String r44;
    private String r45;
    private String r46;
    private String r47;
    private String r48;
    private String r49;
    private String r50;
    private String r51;
  
    private String crtUsr;
    private Date crtDt;
    private String lstUpdUsr;
    private Date lstUpdDt;
    
    public EhfAhcPart2Results() {
    }
    @Id
    @Column(name="AHC_ID")
	public String getAhcId() {
		return ahcId;
	}

	public void setAhcId(String ahcId) {
		this.ahcId = ahcId;
	}
	
	 @Column(name="R27")
	public String getR27() {
		return r27;
	}
	public void setR27(String r27) {
		this.r27 = r27;
	}
	 @Column(name="R28")
	public String getR28() {
		return r28;
	}
	public void setR28(String r28) {
		this.r28 = r28;
	}
	 @Column(name="R29")
	public String getR29() {
		return r29;
	}
	public void setR29(String r29) {
		this.r29 = r29;
	}
	 @Column(name="R30")
	public String getR30() {
		return r30;
	}
	public void setR30(String r30) {
		this.r30 = r30;
	}
	 @Column(name="R31")
	public String getR31() {
		return r31;
	}
	public void setR31(String r31) {
		this.r31 = r31;
	}
	 @Column(name="R32")
	public String getR32() {
		return r32;
	}
	public void setR32(String r32) {
		this.r32 = r32;
	}
	 @Column(name="R33")
	public String getR33() {
		return r33;
	}
	public void setR33(String r33) {
		this.r33 = r33;
	}
	 @Column(name="R34")
	public String getR34() {
		return r34;
	}
	public void setR34(String r34) {
		this.r34 = r34;
	}
	 @Column(name="R35")
	public String getR35() {
		return r35;
	}
	public void setR35(String r35) {
		this.r35 = r35;
	}
	 @Column(name="R36")
	public String getR36() {
		return r36;
	}
	public void setR36(String r36) {
		this.r36 = r36;
	}
	 @Column(name="R37")
	public String getR37() {
		return r37;
	}
	public void setR37(String r37) {
		this.r37 = r37;
	}
	 @Column(name="R38")
	public String getR38() {
		return r38;
	}
	public void setR38(String r38) {
		this.r38 = r38;
	}
	 @Column(name="R39")
	public String getR39() {
		return r39;
	}
	public void setR39(String r39) {
		this.r39 = r39;
	}
	 @Column(name="R40")
	public String getR40() {
		return r40;
	}
	public void setR40(String r40) {
		this.r40 = r40;
	}
	 @Column(name="R41")
	public String getR41() {
		return r41;
	}
	public void setR41(String r41) {
		this.r41 = r41;
	}
	 @Column(name="R42")
	public String getR42() {
		return r42;
	}
	public void setR42(String r42) {
		this.r42 = r42;
	}
	 @Column(name="R43")
	public String getR43() {
		return r43;
	}
	public void setR43(String r43) {
		this.r43 = r43;
	}
	 @Column(name="R44")
	public String getR44() {
		return r44;
	}
	public void setR44(String r44) {
		this.r44 = r44;
	}
	 @Column(name="R45")
	public String getR45() {
		return r45;
	}
	public void setR45(String r45) {
		this.r45 = r45;
	}
	 @Column(name="R46")
	public String getR46() {
		return r46;
	}
	public void setR46(String r46) {
		this.r46 = r46;
	}
	 @Column(name="R47")
	public String getR47() {
		return r47;
	}
	public void setR47(String r47) {
		this.r47 = r47;
	}
	 @Column(name="R48")
	public String getR48() {
		return r48;
	}
	public void setR48(String r48) {
		this.r48 = r48;
	}
	 @Column(name="R49")
	public String getR49() {
		return r49;
	}
	public void setR49(String r49) {
		this.r49 = r49;
	}
	 @Column(name="R50")
	public String getR50() {
		return r50;
	}
	public void setR50(String r50) {
		this.r50 = r50;
	}
	 @Column(name="R51")
	public String getR51() {
		return r51;
	}
	public void setR51(String r51) {
		this.r51 = r51;
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
    
    
    
   
}
