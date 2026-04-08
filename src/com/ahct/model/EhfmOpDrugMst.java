package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EHFM_OP_DRUG_MST")
public class EhfmOpDrugMst implements Serializable{

	private String mainGrpCode;
	private String mainGrpName;
	private String therMainGrpName;
	private String therMainGrpCode;
	private String pharSubGrpCode;
	private String pharSubGrpName;
	private String cheSubGrpCode;
	private String cheSubGrpName;
	private String chemicalCode;
	private String chemicalName;
	private String activeYN;
	private Date crtDt;
	private String crtUsr;
	private Date lstUpdDt;
	private String lstUpdUsr;
			
	public EhfmOpDrugMst() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public EhfmOpDrugMst(String mainGrpCode, String mainGrpName,
			String therMainGrpName, String therMainGrpCode,
			String pharSubGrpCode, String pharSubGrpName, String cheSubGrpCode,
			String cheSubGrpName, String chemicalCode, String chemicalName,
			String activeYN, Date crtDt, String crtUsr, Date lstUpdDt,
			String lstUpdUsr) {
		super();
		this.mainGrpCode = mainGrpCode;
		this.mainGrpName = mainGrpName;
		this.therMainGrpName = therMainGrpName;
		this.therMainGrpCode = therMainGrpCode;
		this.pharSubGrpCode = pharSubGrpCode;
		this.pharSubGrpName = pharSubGrpName;
		this.cheSubGrpCode = cheSubGrpCode;
		this.cheSubGrpName = cheSubGrpName;
		this.chemicalCode = chemicalCode;
		this.chemicalName = chemicalName;
		this.activeYN = activeYN;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdDt = lstUpdDt;
		this.lstUpdUsr = lstUpdUsr;
	}


	@Column(name="MAIN_GRP_CODE")
	public String getMainGrpCode() {
		return mainGrpCode;
	}
	public void setMainGrpCode(String mainGrpCode) {
		this.mainGrpCode = mainGrpCode;
	}
	@Column(name="MAIN_GRP_NAME")
	public String getMainGrpName() {
		return mainGrpName;
	}
	public void setMainGrpName(String mainGrpName) {
		this.mainGrpName = mainGrpName;
	}
	@Column(name="THER_MAIN_GRP_NAME")
	public String getTherMainGrpName() {
		return therMainGrpName;
	}
	public void setTherMainGrpName(String therMainGrpName) {
		this.therMainGrpName = therMainGrpName;
	}
	@Column(name="ther_main_group_code")
	public String getTherMainGrpCode() {
		return therMainGrpCode;
	}
	public void setTherMainGrpCode(String therMainGrpCode) {
		this.therMainGrpCode = therMainGrpCode;
	}
	@Column(name="PHAR_SUB_GRP_CODE")
	public String getPharSubGrpCode() {
		return pharSubGrpCode;
	}
	public void setPharSubGrpCode(String pharSubGrpCode) {
		this.pharSubGrpCode = pharSubGrpCode;
	}
	@Column(name="PHAR_SUB_GRP_NAME")
	public String getPharSubGrpName() {
		return pharSubGrpName;
	}
	public void setPharSubGrpName(String pharSubGrpName) {
		this.pharSubGrpName = pharSubGrpName;
	}
	@Column(name="CHE_SUB_GRP_CODE")
	public String getCheSubGrpCode() {
		return cheSubGrpCode;
	}
	public void setCheSubGrpCode(String cheSubGrpCode) {
		this.cheSubGrpCode = cheSubGrpCode;
	}
	@Column(name="CHE_SUB_GRP_NAME")
	public String getCheSubGrpName() {
		return cheSubGrpName;
	}
	public void setCheSubGrpName(String cheSubGrpName) {
		this.cheSubGrpName = cheSubGrpName;
	}
	@Id
	@Column(name="CHEMICAL_CODE")
	public String getChemicalCode() {
		return chemicalCode;
	}
	public void setChemicalCode(String chemicalCode) {
		this.chemicalCode = chemicalCode;
	}
	@Column(name="CHEMICAL_NAME")
	public String getChemicalName() {
		return chemicalName;
	}
	public void setChemicalName(String chemicalName) {
		this.chemicalName = chemicalName;
	}
	
	@Column(name = "active_yn")
	public String getActiveYN() {
		return activeYN;
	}

	public void setActiveYN(String activeYN) {
		this.activeYN = activeYN;
	}

	@Column(name = "CRT_USR", nullable = false)
	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRT_DT", nullable = false)
	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	@Column(name = "LST_UPD_USR", nullable = true)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LST_UPD_DT", nullable = true)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((activeYN == null) ? 0 : activeYN.hashCode());
		result = prime * result
				+ ((cheSubGrpCode == null) ? 0 : cheSubGrpCode.hashCode());
		result = prime * result
				+ ((cheSubGrpName == null) ? 0 : cheSubGrpName.hashCode());
		result = prime * result
				+ ((chemicalCode == null) ? 0 : chemicalCode.hashCode());
		result = prime * result
				+ ((chemicalName == null) ? 0 : chemicalName.hashCode());
		result = prime * result + ((crtDt == null) ? 0 : crtDt.hashCode());
		result = prime * result + ((crtUsr == null) ? 0 : crtUsr.hashCode());
		result = prime * result
				+ ((lstUpdDt == null) ? 0 : lstUpdDt.hashCode());
		result = prime * result
				+ ((lstUpdUsr == null) ? 0 : lstUpdUsr.hashCode());
		result = prime * result
				+ ((mainGrpCode == null) ? 0 : mainGrpCode.hashCode());
		result = prime * result
				+ ((mainGrpName == null) ? 0 : mainGrpName.hashCode());
		result = prime * result
				+ ((pharSubGrpCode == null) ? 0 : pharSubGrpCode.hashCode());
		result = prime * result
				+ ((pharSubGrpName == null) ? 0 : pharSubGrpName.hashCode());
		result = prime * result
				+ ((therMainGrpCode == null) ? 0 : therMainGrpCode.hashCode());
		result = prime * result
				+ ((therMainGrpName == null) ? 0 : therMainGrpName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EhfmOpDrugMst other = (EhfmOpDrugMst) obj;
		if (activeYN == null) {
			if (other.activeYN != null)
				return false;
		} else if (!activeYN.equals(other.activeYN))
			return false;
		if (cheSubGrpCode == null) {
			if (other.cheSubGrpCode != null)
				return false;
		} else if (!cheSubGrpCode.equals(other.cheSubGrpCode))
			return false;
		if (cheSubGrpName == null) {
			if (other.cheSubGrpName != null)
				return false;
		} else if (!cheSubGrpName.equals(other.cheSubGrpName))
			return false;
		if (chemicalCode == null) {
			if (other.chemicalCode != null)
				return false;
		} else if (!chemicalCode.equals(other.chemicalCode))
			return false;
		if (chemicalName == null) {
			if (other.chemicalName != null)
				return false;
		} else if (!chemicalName.equals(other.chemicalName))
			return false;
		if (crtDt == null) {
			if (other.crtDt != null)
				return false;
		} else if (!crtDt.equals(other.crtDt))
			return false;
		if (crtUsr == null) {
			if (other.crtUsr != null)
				return false;
		} else if (!crtUsr.equals(other.crtUsr))
			return false;
		if (lstUpdDt == null) {
			if (other.lstUpdDt != null)
				return false;
		} else if (!lstUpdDt.equals(other.lstUpdDt))
			return false;
		if (lstUpdUsr == null) {
			if (other.lstUpdUsr != null)
				return false;
		} else if (!lstUpdUsr.equals(other.lstUpdUsr))
			return false;
		if (mainGrpCode == null) {
			if (other.mainGrpCode != null)
				return false;
		} else if (!mainGrpCode.equals(other.mainGrpCode))
			return false;
		if (mainGrpName == null) {
			if (other.mainGrpName != null)
				return false;
		} else if (!mainGrpName.equals(other.mainGrpName))
			return false;
		if (pharSubGrpCode == null) {
			if (other.pharSubGrpCode != null)
				return false;
		} else if (!pharSubGrpCode.equals(other.pharSubGrpCode))
			return false;
		if (pharSubGrpName == null) {
			if (other.pharSubGrpName != null)
				return false;
		} else if (!pharSubGrpName.equals(other.pharSubGrpName))
			return false;
		if (therMainGrpCode == null) {
			if (other.therMainGrpCode != null)
				return false;
		} else if (!therMainGrpCode.equals(other.therMainGrpCode))
			return false;
		if (therMainGrpName == null) {
			if (other.therMainGrpName != null)
				return false;
		} else if (!therMainGrpName.equals(other.therMainGrpName))
			return false;
		return true;
	}
	
	
	
}
