package com.ahct.model;
import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EHFM_COCH_FOLLOWUP_PACKAGES database table.
 * 
 */
@Embeddable
public class EhfmCochFollowupPackagePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cochlearSurgeryId;
	private String icdCodeProcFp;
	private String schemeId;

    public EhfmCochFollowupPackagePK() {
    }

	@Column(name="COCHLEAR_SURGERY_ID")
	public String getCochlearSurgeryId() {
		return this.cochlearSurgeryId;
	}
	public void setCochlearSurgeryId(String cochlearSurgeryId) {
		this.cochlearSurgeryId = cochlearSurgeryId;
	}

	@Column(name="ICD_CODE_PROC_FP")
	public String getIcdCodeProcFp() {
		return this.icdCodeProcFp;
	}
	public void setIcdCodeProcFp(String icdCodeProcFp) {
		this.icdCodeProcFp = icdCodeProcFp;
	}

	@Column(name="SCHEME_ID")
	public String getSchemeId() {
		return this.schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfmCochFollowupPackagePK)) {
			return false;
		}
		EhfmCochFollowupPackagePK castOther = (EhfmCochFollowupPackagePK)other;
		return 
			this.cochlearSurgeryId.equals(castOther.cochlearSurgeryId)
			&& this.icdCodeProcFp.equals(castOther.icdCodeProcFp)
			&& this.schemeId.equals(castOther.schemeId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cochlearSurgeryId.hashCode();
		hash = hash * prime + this.icdCodeProcFp.hashCode();
		hash = hash * prime + this.schemeId.hashCode();
		
		return hash;
    }
}