package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class SgvaCRMgmtTestScenDtlsId implements Serializable{
    public SgvaCRMgmtTestScenDtlsId() {
    }
    private Long sno;
    private Long lineNo;
    private String testedBy;

    /** full constructor */
    public SgvaCRMgmtTestScenDtlsId(Long sno,Long lineNo,String testedBy) {
        this.sno = sno;
        this.lineNo=lineNo;
        this.testedBy=testedBy;
    }
    
    public void setSno(Long sno) {
        this.sno = sno;
    }
    
    @Column(name="sno",nullable=false, length=10)
    public Long getSno() {
        return sno;
    }

    public void setLineNo(Long lineNo) {
        this.lineNo = lineNo;
    }

    @Column(name="LINE_NO",nullable=false, length=5)
    public Long getLineNo() {
        return lineNo;
    }

    public void setTestedBy(String testedBy) {
        this.testedBy = testedBy;
    }

    @Column(name="TestedBy",nullable=false, length=50)
    public String getTestedBy() {
        return testedBy;
    }
    
    public boolean equals(Object other) {
          if ( (this == other ) ) return true;
          if ( (other == null ) ) return false;
          if ( !(other instanceof SgvaCRMgmtTestScenDtlsId) ) return false;
          SgvaCRMgmtTestScenDtlsId castOther = ( SgvaCRMgmtTestScenDtlsId ) other; 
          
          return ( ((this.getSno()==castOther.getSno()) || ( this.getSno()!=null && castOther.getSno()!=null && this.getSno().equals(castOther.getSno())) )          
         && ( (this.getTestedBy()==castOther.getTestedBy()) || ( this.getTestedBy()!=null && castOther.getTestedBy()!=null && this.getTestedBy().equals(castOther.getTestedBy())))
          && ( (this.getLineNo()==castOther.getLineNo()) || ( this.getLineNo()!=null && castOther.getLineNo()!=null && this.getLineNo().equals(castOther.getLineNo()) )));
    }
    
    public int hashCode() {
          int result = 17;          
          result = 37 * result + ( getSno() == null ? 0 : this.getSno().hashCode() );        
          result = 37 * result + ( getLineNo() == null ? 0 : this.getLineNo().hashCode() );          
          result = 37 * result + ( getTestedBy() == null ? 0 : this.getTestedBy().hashCode() );
          return result;
    }  
}
