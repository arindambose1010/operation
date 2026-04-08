package com.ahct.model;
import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EHFM_CHRONIC_DRUG_MST database table.
 * 
 */
@Embeddable
public class EhfmChronicDrugMstPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String chronicPkgCode;
	private String chemicalCode;

    public EhfmChronicDrugMstPK() {
    }

	@Column(name="CHRONIC_PKG_CODE")
	public String getChronicPkgCode() {
		return this.chronicPkgCode;
	}
	public void setChronicPkgCode(String chronicPkgCode) {
		this.chronicPkgCode = chronicPkgCode;
	}

	@Column(name="CHEMICAL_CODE")
	public String getChemicalCode() {
		return this.chemicalCode;
	}
	public void setChemicalCode(String chemicalCode) {
		this.chemicalCode = chemicalCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfmChronicDrugMstPK)) {
			return false;
		}
		EhfmChronicDrugMstPK castOther = (EhfmChronicDrugMstPK)other;
		return 
			this.chronicPkgCode.equals(castOther.chronicPkgCode)
			&& this.chemicalCode.equals(castOther.chemicalCode);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.chronicPkgCode.hashCode();
		hash = hash * prime + this.chemicalCode.hashCode();
		
		return hash;
    }
}