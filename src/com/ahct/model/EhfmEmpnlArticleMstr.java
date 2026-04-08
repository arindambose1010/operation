package com.ahct.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EHFM_EMPNL_ARTCLE_MASTER")
public class EhfmEmpnlArticleMstr implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String articleId;
	private String articleDesc;
	private String activeYn;
	
	
	@Id
	@Column(name="ARTICLE_ID")
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	@Column(name="ARTICLE_DESC")
	public String getArticleDesc() {
		return articleDesc;
	}
	public void setArticleDesc(String articleDesc) {
		this.articleDesc = articleDesc;
	}
	
	@Column(name="ACTIVE_YN")
	public String getActiveYn() {
		return activeYn;
	}
	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	
public EhfmEmpnlArticleMstr(String articleId,String articleDesc,String activeYn){
	
	this.activeYn=activeYn;
	this.articleDesc=articleDesc;
	this.articleId=articleId;
}
public EhfmEmpnlArticleMstr() {
	super();
}
}
