package com.ahct.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="ehf_med_audit_answers")
public class EhfMedicalAuditAnswers implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EhfMedicalAuditAnswersId id;
	 private String answer;
	    private String remarks;
	    private String crtUsr;
	    private Date crtDt;
	    private String lstUpdUsr;
	    private Date lstUpdDt;
	    
	  
	    @EmbeddedId
	    @AttributeOverrides( {
	                  
	     @AttributeOverride(name = "caseId", column = @Column(name = "CASE_ID", nullable = false, length=80)),
	     @AttributeOverride(name = "questionId", column = @Column(name = "QUESTION_ID", nullable = false, length=50))})
		public EhfMedicalAuditAnswersId getId() {
			return id;
		}
		public void setId(EhfMedicalAuditAnswersId id) {
			this.id = id;
		}
		 @Column(name="ANSWER", nullable=true, length=50)
		public String getAnswer() {
			return answer;
		}
		public void setAnswer(String answer) {
			this.answer = answer;
		}
		 @Column(name="REMARKS", nullable=true, length=3500)
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		@Column(name="CRT_USR", nullable=true, length=15)
		public String getCrtUsr() {
			return crtUsr;
		}
		public void setCrtUsr(String crtUsr) {
			this.crtUsr = crtUsr;
		}
		 @Temporal(TemporalType.TIMESTAMP)
		    @Column(name="CRT_DT", nullable=true, length=20)
		public Date getCrtDt() {
			return crtDt;
		}
		public void setCrtDt(Date crtDt) {
			this.crtDt = crtDt;
		}
		@Column(name="LST_UPD_USR", nullable=true, length=15)
		public String getLstUpdUsr() {
			return lstUpdUsr;
		}
		public void setLstUpdUsr(String lstUpdUsr) {
			this.lstUpdUsr = lstUpdUsr;
		}
		@Temporal(TemporalType.TIMESTAMP)
	    @Column(name="LST_UPD_DT", nullable=true,length=20)
		public Date getLstUpdDt() {
			return lstUpdDt;
		}
		public void setLstUpdDt(Date lstUpdDt) {
			this.lstUpdDt = lstUpdDt;
		}
	 
	
	    

}
