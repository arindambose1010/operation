package com.ahct.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="ehf_CASESTATUS_ATTACH")
public class EhfCaseStatusAttach implements java.io.Serializable{
private EhfCaseStatusAttachId id;


private AsrimCombo asrimCombo;
@ManyToOne 
@JoinColumns({
@JoinColumn(name="CASE_STATUS",insertable=false,updatable=false),
@JoinColumn(name="lang_id",insertable=false,updatable=false)

})

public AsrimCombo getAsrimCombo() {
	return asrimCombo;
}
public void setAsrimCombo(AsrimCombo asrimCombo) {
	this.asrimCombo = asrimCombo;
}

@EmbeddedId

@AttributeOverrides( {
   @AttributeOverride(name="caseStatus", column=@Column(name="CASE_STATUS", nullable=false, length=12) ), 
   @AttributeOverride(name="attachType", column=@Column(name="ATTACH_TYPE", nullable=false, length=12) ),
   @AttributeOverride(name="userRole", column=@Column(name="USR_ROLE", nullable=false, length=12) ),
   @AttributeOverride(name="langId", column=@Column(name="lang_id", nullable=false, length=12) )
   } )
public EhfCaseStatusAttachId getId() {
	return id;
}
public void setId(EhfCaseStatusAttachId id) {
	this.id = id;
}

public EhfCaseStatusAttach(EhfCaseStatusAttachId id) {
	super();
	this.id = id;
	
}
public EhfCaseStatusAttach() {
	super();
}


}
