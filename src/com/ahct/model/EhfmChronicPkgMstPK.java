package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EHFM_CHRONIC_PKG_MST database table.
 * 
 */
@Embeddable
public class EhfmChronicPkgMstPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String icdCatCode;
	private String icdChronicPkgCode;
	private String scheme;

    public EhfmChronicPkgMstPK() {
    }

	@Column(name="ICD_CAT_CODE")
	public String getIcdCatCode() {
		return this.icdCatCode;
	}
	public void setIcdCatCode(String icdCatCode) {
		this.icdCatCode = icdCatCode;
	}

	@Column(name="ICD_CHRONIC_PKG_CODE")
	public String getIcdChronicPkgCode() {
		return this.icdChronicPkgCode;
	}
	public void setIcdChronicPkgCode(String icdChronicPkgCode) {
		this.icdChronicPkgCode = icdChronicPkgCode;
	}

	public String getScheme() {
		return this.scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfmChronicPkgMstPK)) {
			return false;
		}
		EhfmChronicPkgMstPK castOther = (EhfmChronicPkgMstPK)other;
		return 
			this.icdCatCode.equals(castOther.icdCatCode)
			&& this.icdChronicPkgCode.equals(castOther.icdChronicPkgCode)
			&& this.scheme.equals(castOther.scheme);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.icdCatCode.hashCode();
		hash = hash * prime + this.icdChronicPkgCode.hashCode();
		hash = hash * prime + this.scheme.hashCode();
		
		return hash;
    }
}