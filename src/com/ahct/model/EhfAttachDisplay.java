package com.ahct.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="EHF_ATTACH_DISPLAY")
public class EhfAttachDisplay implements java.io.Serializable{
private EhfAttachDisplayId id;
private String isVisibleYN;
@EmbeddedId

@AttributeOverrides( {
   @AttributeOverride(name="attachType", column=@Column(name="ATTACH_TYPE", nullable=false, length=20) ), 
   @AttributeOverride(name="userRole", column=@Column(name="USR_ROLE", nullable=false, length=20) ),
   @AttributeOverride(name="viewType", column=@Column(name="VIEW_TYPE", nullable=false, length=20) )
   } )
public EhfAttachDisplayId getId() {
	return id;
}
public void setId(EhfAttachDisplayId id) {
	this.id = id;
}
@Column(name="ISVISIBLE_Y_N", nullable=true)
public String getIsVisibleYN() {
	return isVisibleYN;
}
public void setIsVisibleYN(String isVisibleYN) {
	this.isVisibleYN = isVisibleYN;
}
public EhfAttachDisplay(EhfAttachDisplayId id, String isVisibleYN) {
	super();
	this.id = id;
	this.isVisibleYN = isVisibleYN;
}
public EhfAttachDisplay() {
	super();
}

}
