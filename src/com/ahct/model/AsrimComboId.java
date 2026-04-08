package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AsrimComboId implements Serializable{
    private String cmbDtlId;
    private String langId;


    // Constructors

    /** default constructor */
    public AsrimComboId() {
    }

    
    /** full constructor */
    public AsrimComboId(String cmbDtlId, String langId) {
       this.cmbDtlId = cmbDtlId;
       this.langId = langId;
    }

    
    // Property accessors

    @Column(name="CMB_DTL_ID", nullable=false, length=10)

    public String getCmbDtlId() {
       return this.cmbDtlId;
    }
    
    public void setCmbDtlId(String cmbDtlId) {
       this.cmbDtlId = cmbDtlId;
    }

    @Column(name="LANG_ID", nullable=false, length=5)

    public String getLangId() {
       return this.langId;
    }
    
    public void setLangId(String langId) {
       this.langId = langId;
    }
    



    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
                if ( (other == null ) ) return false;
                if ( !(other instanceof AsrimComboId) ) return false;
                AsrimComboId castOther = ( AsrimComboId ) other; 
        
                return ( (this.getCmbDtlId()==castOther.getCmbDtlId()) || ( this.getCmbDtlId()!=null && castOther.getCmbDtlId()!=null && this.getCmbDtlId().equals(castOther.getCmbDtlId()) ) )
    && ( (this.getLangId()==castOther.getLangId()) || ( this.getLangId()!=null && castOther.getLangId()!=null && this.getLangId().equals(castOther.getLangId()) ) );
    }
    
    public int hashCode() {
        int result = 17;
        
        result = 37 * result + ( getCmbDtlId() == null ? 0 : this.getCmbDtlId().hashCode() );
        result = 37 * result + ( getLangId() == null ? 0 : this.getLangId().hashCode() );
        return result;
    }





    
}
