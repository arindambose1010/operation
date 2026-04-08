package com.ahct.model;
import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EHFM_GENERAL_EXAMIN_FNDS_MST database table.
 * 
 */
@Embeddable
public class EhfmGeneralExaminFndsMstPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String code;
	private String schemeId;

    public EhfmGeneralExaminFndsMstPK() {
    }

	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code = code;
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
		if (!(other instanceof EhfmGeneralExaminFndsMstPK)) {
			return false;
		}
		EhfmGeneralExaminFndsMstPK castOther = (EhfmGeneralExaminFndsMstPK)other;
		return 
			this.code.equals(castOther.code)
			&& this.schemeId.equals(castOther.schemeId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.code.hashCode();
		hash = hash * prime + this.schemeId.hashCode();
		
		return hash;
    }
}