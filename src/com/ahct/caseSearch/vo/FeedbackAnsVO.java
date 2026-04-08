package com.ahct.caseSearch.vo;

import java.util.Date;


public class FeedbackAnsVO {
	
	private String ques_id;
	private String ques_answer; 
	private String remarks;                 
	private String crt_usr;
	private Date crt_dt;                  
	private Date lst_upd_dt;
	private String lst_upd_usr;
	private String case_id;
	private String user_id;
	
	public String getCase_id() {
		return case_id;
	}
	public void setCase_id(String case_id) {
		this.case_id = case_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
    public String getQues_id() {
		return ques_id;
	}
	public void setQues_id(String ques_id) {
		this.ques_id = ques_id;
	}
	public String getQues_answer() {
		return ques_answer;
	}
	public void setQues_answer(String ques_answer) {
		this.ques_answer = ques_answer;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCrt_usr() {
		return crt_usr;
	}
	public void setCrt_usr(String crt_usr) {
		this.crt_usr = crt_usr;
	}
	public Date getCrt_dt() {
		return crt_dt;
	}
	public void setCrt_dt(Date crt_dt) {
		this.crt_dt = crt_dt;
	}
	public Date getLst_upd_dt() {
		return lst_upd_dt;
	}
	public void setLst_upd_dt(Date lst_upd_dt) {
		this.lst_upd_dt = lst_upd_dt;
	}
	public String getLst_upd_usr() {
		return lst_upd_usr;
	}
	public void setLst_upd_usr(String lst_upd_usr) {
		this.lst_upd_usr = lst_upd_usr;
	}


}
