package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

@SuppressWarnings("serial")
public class EhfmDoctorQlfctnsId implements Serializable {

	private String regNum;
	private String hospId;
	private String qualId;
	
	public EhfmDoctorQlfctnsId()
	{
	}
	public EhfmDoctorQlfctnsId(String regNum, String hospId, String qualId) {
		super();
		this.regNum = regNum;
		this.hospId = hospId;
		this.qualId = qualId;
	}
	@Column(name="REG_NUM", nullable = false,length = 50)
	public String getRegNum() {
		return regNum;
	}
	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
	@Column(name="HOSP_ID", nullable = false,length = 20)
	public String getHospId() {
		return hospId;
	}
	public void setHospId(String hospId) {
		this.hospId = hospId;
	}
	@Column(name="QUAL_ID", nullable = false,length = 10)
	public String getQualId() {
		return qualId;
	}
	public void setQualId(String qualId) {
		this.qualId = qualId;
	}
	public boolean equals(Object other) {
        if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof EhfmDoctorQlfctnsId) ) return false;
		 EhfmDoctorQlfctnsId castOther = ( EhfmDoctorQlfctnsId ) other; 
        
		 return  ( (this.getRegNum()==castOther.getRegNum()) || ( this.getRegNum()!=null && castOther.getRegNum()!=null && this.getRegNum().equals(castOther.getRegNum()) ) )
    && ( (this.getHospId()==castOther.getQualId()) || ( this.getHospId()!=null && castOther.getHospId()!=null && this.getHospId().equals(castOther.getHospId()) ) )
	&& ( (this.getQualId()==castOther.getQualId()) || ( this.getQualId()!=null && castOther.getQualId()!=null && this.getQualId().equals(castOther.getQualId()) ) );
  }
  
  public int hashCode() {
        int result = 17;
        
        result = 37 * result + ( getRegNum() == null ? 0 : this.getRegNum().hashCode() );
        result = 37 * result + ( getQualId() == null ? 0 : this.getQualId().hashCode() );
        result = 37 * result + ( getQualId() == null ? 0 : this.getQualId().hashCode() );
        return result;
  }

	
}
