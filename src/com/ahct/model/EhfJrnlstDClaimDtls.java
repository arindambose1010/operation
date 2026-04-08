package com.ahct.model;
import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "EHF_JRNLSTD_CLAIM_DTLS")
public class EhfJrnlstDClaimDtls {
    @Id
    @Column(name = "CLAIM_ID", length = 20)
    private String claimId;
    
    @Column(name = "PATIENT_ID")
    private String patientId;
    
    @Column(name = "CLAIM_STATUS", length = 10)
    private String claimStatus;

    @Column(name = "BILL_AMOUNT")
    private Double billAmount;
    
    @Column(name = "CLAIM_AMOUNT")
    private Double claimAmount;
    
    @Column(name = "CH_DEDUCT_AMOUNT")
    private Double chDeductAmount;

  //Chandana - 9045 - New column to store the estimated amount
    @Column(name = "ESTIMATED_AMOUNT")
    private Double estimatedAmount;

    @Column(name = "CH_APPRV_USR", length = 20)
    private String chApprvUsr;
    
    @Column(name = "CH_APPRV_DT")
    @Temporal(TemporalType.TIMESTAMP) //Chandana - 7845 - Changed from date to timestamp, as i need to save date along withtime
    private Date chApprvDt;
    
    @Column(name = "CRT_USR", length = 20)
    private String crtUsr;
    
    @Column(name = "CRT_DT")
    @Temporal(TemporalType.TIMESTAMP) //Chandana - 7845 - Changed from date to timestamp, as i need to save date along withtime
    private Date crtDt;
    
    @Column(name = "LST_UPD_USR", length = 20)
    private String lstUpdUsr;
    
    @Column(name = "LST_UPD_DT")
    @Temporal(TemporalType.TIMESTAMP) //Chandana - 7845 - Changed from date to timestamp, as i need to save date along withtime
    private Date lstUpdDt;
    
    //Chandana - 8602 - Added below 3 columns in the table 
    @Column(name = "ACO_APPRV_AMOUNT")
    private Double acoApprvAmount;
    
    @Column(name = "ACO_APPRV_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acoApprvDt;
    
    @Column(name = "ACO_APPRV_USR")
    private String acoApprvUsr;
    
	// Getters and Setters
    public String getClaimId() {
		return claimId;
	}
	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
    public String getClaimStatus() {
        return claimStatus;
    }
	public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public Double getBillAmount() {
        return billAmount;
    }
    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }
    
    public Double getClaimAmount() {
        return claimAmount;
    }
    public void setClaimAmount(Double claimAmount) {
        this.claimAmount = claimAmount;
    }
    
    public Double getChDeductAmount() {
		return chDeductAmount;
	}
	public void setChDeductAmount(Double chDeductAmount) {
		this.chDeductAmount = chDeductAmount;
	}

    public String getChApprvUsr() {
        return chApprvUsr;
    }
    public void setChApprvUsr(String chApprvUsr) {
        this.chApprvUsr = chApprvUsr;
    }
    
    public Date getChApprvDt() {
        return chApprvDt;
    }
    public void setChApprvDt(Date chApprvDt) {
        this.chApprvDt = chApprvDt;
    }
    
    public String getCrtUsr() {
        return crtUsr;
    }
    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }
    
    public Date getCrtDt() {
        return crtDt;
    }
    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }
    
    public String getLstUpdUsr() {
        return lstUpdUsr;
    }
    public void setLstUpdUsr(String lstUpdUsr) {
        this.lstUpdUsr = lstUpdUsr;
    }
    
    public Date getLstUpdDt() {
        return lstUpdDt;
    }
    public void setLstUpdDt(Date lstUpdDt) {
        this.lstUpdDt = lstUpdDt;
    }
    
	public Double getAcoApprvAmount() {
		return acoApprvAmount;
	}
	public void setAcoApprvAmount(Double acoApprvAmount) {
		this.acoApprvAmount = acoApprvAmount;
	}
	
	public Date getAcoApprvDt() {
		return acoApprvDt;
	}
	public void setAcoApprvDt(Date acoApprvDt) {
		this.acoApprvDt = acoApprvDt;
	}
	
	public String getAcoApprvUsr() {
		return acoApprvUsr;
	}
	public void setAcoApprvUsr(String acoApprvUsr) {
		this.acoApprvUsr = acoApprvUsr;
	}
	public Double getEstimatedAmount() {
		return estimatedAmount;
	}
	public void setEstimatedAmount(Double estimatedAmount) {
		this.estimatedAmount = estimatedAmount;
	}
    
    
}
