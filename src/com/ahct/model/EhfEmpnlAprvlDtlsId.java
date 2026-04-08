package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EhfEmpnlAprvlDtlsId  implements Serializable  {
    public EhfEmpnlAprvlDtlsId() {
    }
    
    private String hospinfo_Id;
    private String enhanced_count;


    public void setHospinfo_Id(String hospinfo_Id) {
        this.hospinfo_Id = hospinfo_Id;
    }
    @Column (name="HOSPINFO_ID",nullable=false)
    public String getHospinfo_Id() {
        return hospinfo_Id;
    }

    public void setEnhanced_count(String enhanced_count) {
        this.enhanced_count = enhanced_count;
    }
    @Column (name="ENHANCED_COUNT",nullable=false)
    public String getEnhanced_count() {
        return enhanced_count;
    }
    
    
    
    public int hashCode()
    {
         int result = 17;         
         result = 37 * result + ( getHospinfo_Id()  == null ? 0 : this.getHospinfo_Id() .hashCode() );
         result = 37 * result + ( getEnhanced_count() == null ? 0 : this.getEnhanced_count().hashCode() );
         return result;
    }
    
    
    public boolean equals(Object other) {
         if ( (this == other ) ) return true;
                 if ( (other == null ) ) return false;
                 if ( !(other instanceof EhfEmpnlAprvlDtlsId) ) return false;
                 EhfEmpnlAprvlDtlsId castOther = ( EhfEmpnlAprvlDtlsId ) other; 
         
                 return ( (this.getHospinfo_Id()==castOther.getHospinfo_Id()) || ( this.getHospinfo_Id()!=null && castOther.getHospinfo_Id()!=null && this.getHospinfo_Id().equals(castOther.getHospinfo_Id()) ) )    
                 && ( (this.getEnhanced_count()==castOther.getEnhanced_count()) || ( this.getEnhanced_count()!=null && castOther.getEnhanced_count()!=null && this.getEnhanced_count().equals(castOther.getEnhanced_count()) ) );
    }


  
}
