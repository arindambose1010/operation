package com.ahct.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="EHFM_HOSP_QUANTITY_MST")
		public class EhfmHospStayNIMS implements java.io.Serializable{
		private String deptId;
		private String deptName;
		private String crtUsr;
		private Date crtDt;
		
		@Id
		@Column(name="DEPT_ID")
		public String getDeptId() {
			return deptId;
		}
		public void setDeptId(String deptId) {
			this.deptId = deptId;
		}
		@Column(name="DEPT_NAME")
		public String getDeptName() {
			return deptName;
		}
		public void setDeptName(String deptName) {
			this.deptName = deptName;
		}
		@Column(name="crt_usr")
		public String getCrtUsr() {
			return crtUsr;
		}
		public void setCrtUsr(String crtUsr) {
			this.crtUsr = crtUsr;
		}
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name="crt_dt")
		public Date getCrtDt() {
			return crtDt;
		}
		public void setCrtDt(Date crtDt) {
			this.crtDt = crtDt;
		}
		
		public EhfmHospStayNIMS() {
			super();
		}
		
		public EhfmHospStayNIMS(String deptId, String deptName, String crtUsr,
				Date crtDt, String enhType) {
			super();
			this.deptId = deptId;
			this.deptName = deptName;
			this.crtUsr = crtUsr;
			this.crtDt = crtDt;
		}
	
		
	}
		
		
