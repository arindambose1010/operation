package com.ahct.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ehfm_therapy_grp_timeout")
public class EhfmTherapyGrpTimeout implements java.io.Serializable{

	private EhfmTherapyGrpTimeoutId id;
	private String timer;
	
	
	@EmbeddedId
	@AttributeOverrides( {
	   @AttributeOverride(name="specialityCode", column=@Column(name="SPECIALITY_CODE", nullable=false) ), 
	   @AttributeOverride(name="icdProcCode", column=@Column(name="ICD_PROC_CODE", nullable=false) ),
	   @AttributeOverride(name="grpId", column=@Column(name="GRP_ID", nullable=false) ),
	   @AttributeOverride(name="moduleType", column=@Column(name="MODULE_TYPE", nullable=false) ),
	    @AttributeOverride(name="scheme", column=@Column(name="SCHEME", nullable=false) )
	   } )
	public EhfmTherapyGrpTimeoutId getId() {
		return id;
	}
	public void setId(EhfmTherapyGrpTimeoutId id) {
		this.id = id;
	}
	@Column(name="timer", nullable=false)
	public String getTimer() {
		return timer;
	}
	public void setTimer(String timer) {
		this.timer = timer;
	}
	public EhfmTherapyGrpTimeout() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
