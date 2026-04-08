package com.ahct.model;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHFM_CHRONIC_DRUG_MST database table.
 * 
 */
@Entity
@Table(name="EHFM_CHRONIC_DRUG_MST")
public class EhfmChronicDrugMst implements Serializable {
	private static final long serialVersionUID = 1L;
	private EhfmChronicDrugMstPK id;
	private String activeYn;
	private String cheSubGrpCode;
	private String cheSubGrpName;
	private String chemicalName;
	private Date crtDt;
	private String crtUsr;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String mainGrpCode;
	private String mainGrpName;
	private String pharSubGrpCode;
	private String pharSubGrpName;
	private String therMainGroupCode;
	private String therMainGrpName;

    public EhfmChronicDrugMst() {
    }


	@EmbeddedId
	public EhfmChronicDrugMstPK getId() {
		return this.id;
	}

	public void setId(EhfmChronicDrugMstPK id) {
		this.id = id;
	}
	

	@Column(name="ACTIVE_YN")
	public String getActiveYn() {
		return this.activeYn;
	}

	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}


	@Column(name="CHE_SUB_GRP_CODE")
	public String getCheSubGrpCode() {
		return this.cheSubGrpCode;
	}

	public void setCheSubGrpCode(String cheSubGrpCode) {
		this.cheSubGrpCode = cheSubGrpCode;
	}


	@Column(name="CHE_SUB_GRP_NAME")
	public String getCheSubGrpName() {
		return this.cheSubGrpName;
	}

	public void setCheSubGrpName(String cheSubGrpName) {
		this.cheSubGrpName = cheSubGrpName;
	}


	@Column(name="CHEMICAL_NAME")
	public String getChemicalName() {
		return this.chemicalName;
	}

	public void setChemicalName(String chemicalName) {
		this.chemicalName = chemicalName;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}


	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}


	@Column(name="LST_UPD_USR")
	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}


	@Column(name="MAIN_GRP_CODE")
	public String getMainGrpCode() {
		return this.mainGrpCode;
	}

	public void setMainGrpCode(String mainGrpCode) {
		this.mainGrpCode = mainGrpCode;
	}


	@Column(name="MAIN_GRP_NAME")
	public String getMainGrpName() {
		return this.mainGrpName;
	}

	public void setMainGrpName(String mainGrpName) {
		this.mainGrpName = mainGrpName;
	}


	@Column(name="PHAR_SUB_GRP_CODE")
	public String getPharSubGrpCode() {
		return this.pharSubGrpCode;
	}

	public void setPharSubGrpCode(String pharSubGrpCode) {
		this.pharSubGrpCode = pharSubGrpCode;
	}


	@Column(name="PHAR_SUB_GRP_NAME")
	public String getPharSubGrpName() {
		return this.pharSubGrpName;
	}

	public void setPharSubGrpName(String pharSubGrpName) {
		this.pharSubGrpName = pharSubGrpName;
	}


	@Column(name="THER_MAIN_GROUP_CODE")
	public String getTherMainGroupCode() {
		return this.therMainGroupCode;
	}

	public void setTherMainGroupCode(String therMainGroupCode) {
		this.therMainGroupCode = therMainGroupCode;
	}


	@Column(name="THER_MAIN_GRP_NAME")
	public String getTherMainGrpName() {
		return this.therMainGrpName;
	}

	public void setTherMainGrpName(String therMainGrpName) {
		this.therMainGrpName = therMainGrpName;
	}

}