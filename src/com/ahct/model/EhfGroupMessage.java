package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ehf_group_message")
public class EhfGroupMessage implements Serializable {

	    private String mId;
	    private String grpId;
	    private String shortMsg;
	    
		private String message;
	    private String path;
	    private String moduleId;
	    private String active;
	    private String lstUpdUsr;
	    private Date crtDt;
	    private String crtUsr;
	    private Date lstUpdDt;
	    
	    
	    @Id
		@Column(name="M_ID")
		public String getmId() {
			return mId;
		}
		public void setmId(String mId) {
			this.mId = mId;
		}
		
		
		@Column(name="SHORT_MESSAGE")
		public String getShortMsg() {
			return shortMsg;
		}
		public void setShortMsg(String shortMsg) {
			this.shortMsg = shortMsg;
		}
		
		
		@Column(name="GRP_ID")
		public String getGrpId() {
			return grpId;
		}
		public void setGrpId(String grpId) {
			this.grpId = grpId;
		}
		
		@Column(name="MESSAGE")
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
		
		@Column(name="PATH")
		public String getPath() {
			return path;
		}
		
		public void setPath(String path) {
			this.path = path;
		}
		
		@Column(name="MODULE_ID")
		public String getModuleId() {
			return moduleId;
		}
		public void setModuleId(String moduleId) {
			this.moduleId = moduleId;
		}
		
		@Column(name="ACTIVE")
		public String getActive() {
			return active;
		}
		public void setActive(String active) {
			this.active = active;
		}
		
		@Column(name="LST_UPD_USR")
		public String getLstUpdUsr() {
			return lstUpdUsr;
		}
		public void setLstUpdUsr(String lstUpdUsr) {
			this.lstUpdUsr = lstUpdUsr;
		}
		
		@Column(name="CRT_DT")
		public Date getCrtDt() {
			return crtDt;
		}
		public void setCrtDt(Date crtDt) {
			this.crtDt = crtDt;
		}
		
		@Column(name="CRT_USR")
		public String getCrtUsr() {
			return crtUsr;
		}
		public void setCrtUsr(String crtUsr) {
			this.crtUsr = crtUsr;
		}
		
		@Column(name="LST_UPD_DT")
		public Date getLstUpdDt() {
			return lstUpdDt;
		}
		public void setLstUpdDt(Date lstUpdDt) {
			this.lstUpdDt = lstUpdDt;
		}
	    
	    
	   
	
	
	
	
}
