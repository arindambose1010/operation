package com.ahct.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="ehfm_seq")
public class EhfmSeq implements java.io.Serializable{
	// Fields

	private String seqIdentifier;
	private String tableName;
	private Long seqNextVal;
	

	// Constructors

	/** default constructor */
	public EhfmSeq() {
	}
	// Property accessors
	@Id
	@Column(name = "SEQ_IDENTIFIER", unique = true, nullable = false, length = 3)
	public String getSeqIdentifier() {
		return this.seqIdentifier;
	}

	public void setSeqIdentifier(String seqIdentifier) {
		this.seqIdentifier = seqIdentifier;
	}

	@Column(name = "TABLE_NAME", nullable = false, length = 18)
	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name = "SEQ_NEXT_VAL", nullable = false, precision = 6, scale = 0)
	public Long getSeqNextVal() {
		return this.seqNextVal;
	}

	public void setSeqNextVal(Long seqNextVal) {
		this.seqNextVal = seqNextVal;
	}
	public EhfmSeq(String seqIdentifier, String tableName, Long seqNextVal) {
		super();
		this.seqIdentifier = seqIdentifier;
		this.tableName = tableName;
		this.seqNextVal = seqNextVal;
	}

	
}
