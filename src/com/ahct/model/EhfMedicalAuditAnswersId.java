package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfMedicalAuditAnswersId implements Serializable {

	private String caseId;
    private String questionId;
    
    
    
	public EhfMedicalAuditAnswersId(String caseId, String questionId) {
		super();
		this.caseId = caseId;
		this.questionId = questionId;
	}
	
	
	public EhfMedicalAuditAnswersId() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(name="CASE_ID", nullable=false, length=80)
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	 @Column(name="QUESTION_ID", nullable=false, length=50)
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	 public boolean equals(Object other) {
         if ( (this == other ) ) return true;
                 if ( (other == null ) ) return false;
                 if ( !(other instanceof EhfMedicalAuditAnswersId) ) return false;
                 EhfMedicalAuditAnswersId castOther = ( EhfMedicalAuditAnswersId ) other; 
         
                 return  ( (this.getCaseId()==castOther.getCaseId()) || ( this.getCaseId()!=null && castOther.getCaseId()!=null && this.getCaseId().equals(castOther.getCaseId()) ) )
                 && ( (this.getQuestionId()==castOther.getQuestionId()) || ( this.getQuestionId()!=null && castOther.getQuestionId()!=null && this.getQuestionId().equals(castOther.getQuestionId()) ) );
    }
    
    public int hashCode()
    {
         int result = 17;         
        
         result = 37 * result + ( getCaseId() == null ? 0 : this.getCaseId().hashCode() );
         result = 37 * result + ( getQuestionId() == null ? 0 : this.getQuestionId().hashCode() );
         return result;
    }
}
