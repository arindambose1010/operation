package com.ahct.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import com.ahct.model.EhfmDentalCmbDtlsId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table
(name = "EHFM_DENTAL_CMB_DTLS" )
public class EhfmDentalCmbDtls implements Serializable
{
		private EhfmDentalCmbDtlsId id;
		private String labelNAME;
		private String labelPrntID;
		private String ActiveYn;
		private Date crtDt;
		private String crtUsr;
		private Date lstUpdDt;
		private String lstUpdUsr;
		
		
		public EhfmDentalCmbDtls()
		{
			super();
		}
		public EhfmDentalCmbDtls(EhfmDentalCmbDtlsId id,String labelNAME ,String labelPrntID, String ActiveYn, String crtUsr ,Date crtDt,Date lstUpdDt ,String lstUpdUsr)
		{
			this.id=id;
			this.labelNAME=labelNAME;
			this.labelPrntID=labelPrntID;
			this.ActiveYn=ActiveYn;
			this.crtUsr=crtUsr;
			this.crtDt=crtDt;
			this.lstUpdDt=lstUpdDt;
			this.lstUpdUsr=lstUpdUsr;
		}
		@EmbeddedId

		@AttributeOverrides( {
		@AttributeOverride(name="labelID", column=@Column(name="label_ID", nullable=false) ), 
		@AttributeOverride(name="scheme", column=@Column(name="Scheme", nullable=false) ) } )
		
		public EhfmDentalCmbDtlsId getId() {
			return id;
		}
		public void setId(EhfmDentalCmbDtlsId id) {
			this.id = id;
		}
		
		
		@Column(name="label_NAME")
		public String getLabelNAME() {
			return labelNAME;
		}
		public void setLabelNAME(String labelNAME) {
			this.labelNAME = labelNAME;
		}
		@Column(name="label_prnt_ID")
		public String getLabelPrntID() {
			return labelPrntID;
		}
		public void setLabelPrntID(String labelPrntID) {
			this.labelPrntID = labelPrntID;
		}
		@Column(name="Active_Yn")
		public String getActiveYn() {
			return ActiveYn;
		}
		public void setActiveYn(String activeYn) {
			ActiveYn = activeYn;
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
		@Column(name="LST_UPD_USR")
		public String getLstUpdUsr() {
			return lstUpdUsr;
		}
		public void setLstUpdUsr(String lstUpdUsr) {
			this.lstUpdUsr = lstUpdUsr;
		}
		
		
		
		
		
		
}
