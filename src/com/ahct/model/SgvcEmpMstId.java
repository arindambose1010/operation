package com.ahct.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * SgvcEmpMstId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class SgvcEmpMstId  implements java.io.Serializable {


    // Fields    

     private String empId;
     private String locId;
     private String langId;


    // Constructors

    /** default constructor */
    public SgvcEmpMstId() {
    }

    
    /** full constructor */
    public SgvcEmpMstId(String empId, String locId, String langId) {
        this.empId = empId;
        this.locId = locId;
        this.langId = langId;
    }

   
    // Property accessors

    @Column(name="EMP_ID", nullable=false, length=12)

    public String getEmpId() {
        return this.empId;
    }
    
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    @Column(name="LOC_ID", nullable=false, length=5)

    public String getLocId() {
        return this.locId;
    }
    
    public void setLocId(String locId) {
        this.locId = locId;
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
		 if ( !(other instanceof SgvcEmpMstId) ) return false;
		 SgvcEmpMstId castOther = ( SgvcEmpMstId ) other; 
         
		 return ( (this.getEmpId()==castOther.getEmpId()) || ( this.getEmpId()!=null && castOther.getEmpId()!=null && this.getEmpId().equals(castOther.getEmpId()) ) )
 && ( (this.getLocId()==castOther.getLocId()) || ( this.getLocId()!=null && castOther.getLocId()!=null && this.getLocId().equals(castOther.getLocId()) ) )
 && ( (this.getLangId()==castOther.getLangId()) || ( this.getLangId()!=null && castOther.getLangId()!=null && this.getLangId().equals(castOther.getLangId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getEmpId() == null ? 0 : this.getEmpId().hashCode() );
         result = 37 * result + ( getLocId() == null ? 0 : this.getLocId().hashCode() );
         result = 37 * result + ( getLangId() == null ? 0 : this.getLangId().hashCode() );
         return result;
   }   





}