package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * SgvcEmpMstId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class EhfmMedcoDetailsId  implements java.io.Serializable {


    // Fields    

     private String userId;
     private String hospId;
     private Date effStartDate;


    // Constructors

    /** default constructor */
    public EhfmMedcoDetailsId() {
    }

    
    /** full constructor */
    public EhfmMedcoDetailsId(String userId, String hospId, Date effStartDate) {
        this.userId = userId;
        this.hospId = hospId;
        this.effStartDate = effStartDate;
    }

   
    // Property accessors
     @Column(name="MEDCO_ID", nullable=false, length=12)
     public String getUserId() {
         return userId;
     }
     public void setUserId(String userId) {
         this.userId = userId;
     }

    @Column(name="HOSP_ID", nullable=false, length=12)
    public String getHospId() {
        return hospId;
    }

     public void setHospId(String hospId) {
         this.hospId = hospId;
     }
     @Temporal(TemporalType.TIMESTAMP)
     @Column(name="START_DT", nullable=false)
    public Date getEffStartDate() {
        return effStartDate;
    }
    
     public void setEffStartDate(Date effStartDate) {
         this.effStartDate = effStartDate;
     }

 


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof EhfmMedcoDetailsId) ) return false;
		 EhfmMedcoDetailsId castOther = ( EhfmMedcoDetailsId ) other; 
         
		 return ( (this.getUserId()==castOther.getUserId()) || ( this.getUserId()!=null && castOther.getUserId()!=null && this.getUserId().equals(castOther.getUserId()) ) )
 && ( (this.getHospId()==castOther.getHospId()) || ( this.getHospId()!=null && castOther.getHospId()!=null && this.getHospId().equals(castOther.getHospId()) ) )
 && ( (this.getEffStartDate()==castOther.getEffStartDate()) || ( this.getEffStartDate()!=null && castOther.getEffStartDate()!=null && this.getEffStartDate().equals(castOther.getEffStartDate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUserId() == null ? 0 : this.getUserId().hashCode() );
         result = 37 * result + ( getHospId() == null ? 0 : this.getHospId().hashCode() );
         result = 37 * result + ( getEffStartDate() == null ? 0 : this.getEffStartDate().hashCode() );
         return result;
   }


   
}
