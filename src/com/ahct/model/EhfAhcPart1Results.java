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
@Table(name="EHF_AHC_PART1_RESULTS"
)
public class EhfAhcPart1Results implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ahcId ;
    private String r1;
    private String r2;
    private String r3;
    private String r4;
    private String r5;
    private String r6;
    private String r7;
    private String r8;
    private String r9;
    private String r10;
    private String r11;
    private String r12;
    private String r13;
    private String r14;
    private String r15;
    private String r16;
    private String r17;
    private String r18;
    private String r19;
    private String r20;
    private String r21;
    private String r22;
    private String r23;
    private String r24;
    private String r25;
    private String r26;
    private String crtUsr;
    private Date crtDt;
    private String lstUpdUsr;
    private Date lstUpdDt;
    
    public EhfAhcPart1Results() {
    }
    @Id
    @Column(name="AHC_ID")
	public String getAhcId() {
		return ahcId;
	}

	public void setAhcId(String ahcId) {
		this.ahcId = ahcId;
	}
	@Column(name="R1")
	public String getR1() {
		return r1;
	}

	public void setR1(String r1) {
		this.r1 = r1;
	}
	@Column(name="R2")
	public String getR2() {
		return r2;
	}

	public void setR2(String r2) {
		this.r2 = r2;
	}
	@Column(name="R3")
	public String getR3() {
		return r3;
	}

	public void setR3(String r3) {
		this.r3 = r3;
	}
	@Column(name="R4")
	public String getR4() {
		return r4;
	}

	public void setR4(String r4) {
		this.r4 = r4;
	}
	@Column(name="R5")
	public String getR5() {
		return r5;
	}

	public void setR5(String r5) {
		this.r5 = r5;
	}
	@Column(name="R6")
	public String getR6() {
		return r6;
	}

	public void setR6(String r6) {
		this.r6 = r6;
	}
	@Column(name="R7")
	public String getR7() {
		return r7;
	}

	public void setR7(String r7) {
		this.r7 = r7;
	}
	@Column(name="R8")
	public String getR8() {
		return r8;
	}

	public void setR8(String r8) {
		this.r8 = r8;
	}
	@Column(name="R9")
	public String getR9() {
		return r9;
	}

	public void setR9(String r9) {
		this.r9 = r9;
	}
	@Column(name="R10")
	public String getR10() {
		return r10;
	}

	public void setR10(String r10) {
		this.r10 = r10;
	}
	@Column(name="R11")
	public String getR11() {
		return r11;
	}

	public void setR11(String r11) {
		this.r11 = r11;
	}
	@Column(name="R12")
	public String getR12() {
		return r12;
	}

	public void setR12(String r12) {
		this.r12 = r12;
	}
	@Column(name="R13")
	public String getR13() {
		return r13;
	}

	public void setR13(String r13) {
		this.r13 = r13;
	}
	@Column(name="R14")
	public String getR14() {
		return r14;
	}

	public void setR14(String r14) {
		this.r14 = r14;
	}
	@Column(name="R15")
	public String getR15() {
		return r15;
	}

	public void setR15(String r15) {
		this.r15 = r15;
	}
	@Column(name="R16")
	public String getR16() {
		return r16;
	}

	public void setR16(String r16) {
		this.r16 = r16;
	}
	@Column(name="R17")
	public String getR17() {
		return r17;
	}

	public void setR17(String r17) {
		this.r17 = r17;
	}
	@Column(name="R18")
	public String getR18() {
		return r18;
	}

	public void setR18(String r18) {
		this.r18 = r18;
	}
	@Column(name="R19")
	public String getR19() {
		return r19;
	}

	public void setR19(String r19) {
		this.r19 = r19;
	}
	@Column(name="R20")
	public String getR20() {
		return r20;
	}

	public void setR20(String r20) {
		this.r20 = r20;
	}
	@Column(name="R21")
	public String getR21() {
		return r21;
	}

	public void setR21(String r21) {
		this.r21 = r21;
	}
	@Column(name="R22")
	public String getR22() {
		return r22;
	}

	public void setR22(String r22) {
		this.r22 = r22;
	}
	@Column(name="R23")
	public String getR23() {
		return r23;
	}

	public void setR23(String r23) {
		this.r23 = r23;
	}
	@Column(name="R24")
	public String getR24() {
		return r24;
	}

	public void setR24(String r24) {
		this.r24 = r24;
	}
	@Column(name="R25")
	public String getR25() {
		return r25;
	}

	public void setR25(String r25) {
		this.r25 = r25;
	}
	@Column(name="R26")
	public String getR26() {
		return r26;
	}

	public void setR26(String r26) {
		this.r26 = r26;
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
