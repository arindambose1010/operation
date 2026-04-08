package com.ahct.model;
import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EHFM_CHRONIC_DRUGS_MST database table.
 * 
 */
@Embeddable
public class EhfmChronicDrugsMstPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String chronicPkgCode;
	private String drugCode;
	private String routeCode;
	private String strengthCode;

    public EhfmChronicDrugsMstPK() {
    }

	@Column(name="CHRONIC_PKG_CODE")
	public String getChronicPkgCode() {
		return this.chronicPkgCode;
	}
	public void setChronicPkgCode(String chronicPkgCode) {
		this.chronicPkgCode = chronicPkgCode;
	}

	@Column(name="DRUG_CODE")
	public String getDrugCode() {
		return this.drugCode;
	}
	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}

	@Column(name="ROUTE_CODE")
	public String getRouteCode() {
		return this.routeCode;
	}
	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	@Column(name="STRENGTH_CODE")
	public String getStrengthCode() {
		return this.strengthCode;
	}
	public void setStrengthCode(String strengthCode) {
		this.strengthCode = strengthCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfmChronicDrugsMstPK)) {
			return false;
		}
		EhfmChronicDrugsMstPK castOther = (EhfmChronicDrugsMstPK)other;
		return 
			this.chronicPkgCode.equals(castOther.chronicPkgCode)
			&& this.drugCode.equals(castOther.drugCode)
			&& this.routeCode.equals(castOther.routeCode)
			&& this.strengthCode.equals(castOther.strengthCode);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.chronicPkgCode.hashCode();
		hash = hash * prime + this.drugCode.hashCode();
		hash = hash * prime + this.routeCode.hashCode();
		hash = hash * prime + this.strengthCode.hashCode();
		
		return hash;
    }
}