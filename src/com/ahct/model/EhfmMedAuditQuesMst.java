package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
( name = "ehfm_med_audit_qst_mst" )
public class EhfmMedAuditQuesMst implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String questionId;
	private String quesDesc;
	private String quesNo;
	private Integer quesSno;
	private String quesCategory;
	
	
	
	
	public EhfmMedAuditQuesMst() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public EhfmMedAuditQuesMst(String questionId, String quesDesc,
			String quesNo, Integer quesSno, String quesCategory) {
		super();
		this.questionId = questionId;
		this.quesDesc = quesDesc;
		this.quesNo = quesNo;
		this.quesSno = quesSno;
		this.quesCategory = quesCategory;
	}


	@Id
	 @Column(name = "QUESTION_ID", nullable = false, length = 50)
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	 @Column(name = "QUESTION_DESC", nullable = false, length = 3500)
	public String getQuesDesc() {
		return quesDesc;
	}
	public void setQuesDesc(String quesDesc) {
		this.quesDesc = quesDesc;
	}
	@Column(name = "QUESTION_NO", nullable = false, length = 10)
	public String getQuesNo() {
		return quesNo;
	}
	public void setQuesNo(String quesNo) {
		this.quesNo = quesNo;
	}
	@Column(name = "QUES_S_NO", nullable = false, length = 10)
	public Integer getQuesSno() {
		return quesSno;
	}
	public void setQuesSno(Integer quesSno) {
		this.quesSno = quesSno;
	}
	@Column(name = "QUES_CATEGORY", nullable = false, length = 10)
	public String getQuesCategory() {
		return quesCategory;
	}
	public void setQuesCategory(String quesCategory) {
		this.quesCategory = quesCategory;
	}
	
	
	

}
