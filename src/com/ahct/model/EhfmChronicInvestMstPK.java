package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EHFM_CHRONIC_INVEST_MST database table.
 * 
 */
@Embeddable
public class EhfmChronicInvestMstPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String chronicDisCode;
	private String invCode;
	private String installment;

    public EhfmChronicInvestMstPK() {
    }

	@Column(name="CHRONIC_DIS_CODE")
	public String getChronicDisCode() {
		return this.chronicDisCode;
	}
	public void setChronicDisCode(String chronicDisCode) {
		this.chronicDisCode = chronicDisCode;
	}

	@Column(name="INV_CODE")
	public String getInvCode() {
		return this.invCode;
	}
	public void setInvCode(String invCode) {
		this.invCode = invCode;
	}

	public String getInstallment() {
		return this.installment;
	}
	public void setInstallment(String installment) {
		this.installment = installment;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfmChronicInvestMstPK)) {
			return false;
		}
		EhfmChronicInvestMstPK castOther = (EhfmChronicInvestMstPK)other;
		return 
			this.chronicDisCode.equals(castOther.chronicDisCode)
			&& this.invCode.equals(castOther.invCode)
			&& this.installment.equals(castOther.installment);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.chronicDisCode.hashCode();
		hash = hash * prime + this.invCode.hashCode();
		hash = hash * prime + this.installment.hashCode();
		
		return hash;
    }
}