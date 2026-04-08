
package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

import javax.persistence.Table;

@Entity
@Table
( name = "EHF_EMPNL_HOSPINFO" )
@NamedNativeQueries ( {
@NamedNativeQuery ( 
name ="findHospBasicInfoByHospId" , resultClass = EhfEmpnlHospInfo.class,
query = "select * from EHF_EMPNL_HOSPINFO a where a.HOSPINFO_ID=:HOSPINFO_ID"),
@NamedNativeQuery ( 
name = "findHospStatusByHospInfoId", resultClass = EhfEmpnlHospInfo.class,
query = "select status from EHF_EMPNL_HOSPINFO h where h.hospinfo_id = :hospinfo_id " )

})
public class EhfEmpnlHospInfo implements Serializable
{
  public EhfEmpnlHospInfo ()
  {
  }
      private String info;
      private String staff;
      private String hospInfoId;
      private String hospName;
      private String hospAdd;
      private String hospEstableshedYear;
      private String hospBedStrength;
      private Date crtDt;  
      private String hospMDTelPh;
      
      private String hospMDEmail;
      private String status;
      private String securityCode;
      private Date updDate; 
      private String updUsr; 
      private String cityCode;  
      private String faxNo;
      private String bankAccName;
      private String bankAccNumber;
      private String bankName;
      private String bankBranch;
      private String ifscCode;

	  private String branchCode;
      private String districtCode;
      private String delistRefNo;
      private String rempanelType;
      
      private String enhancedStatus;
      private String enhancedCount;
      private Date enhancedUpdDate; 
      private String enhancedUpdUser;
      private Date enhancedCrtDt;
      private String enhancedCrtUsr; 
      private String hospCategory;
      private String panNumber;
      
      private String hospStreet;
      
      
      private String parentGroup;
      private String hospMDRationCardNo;
      private String hospMDCommStatus;    
    
      private String authSign;
      private String constitution;
      private Date inspStartDt;
      private Date inspEndDt;
      private String sysgen;
      
      private String mandal;
      private String hospLandLine;
      private String stateCode;
     // private String taxStatus;
      private String applictnType;

      private String villageCode;
      private String mandalType;
      private String panHolderName;
      private String lockedBy;
      private Date lockedDate;
      private String ceoName;
      private String parentType;
      private String scheme;
      
      @Column ( name = "LOCKED_DATE",nullable = true)    
      public Date getLockedDate() {
		return lockedDate;
	}
      
	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}
	@Column ( name = "LOCKED_BY", length = 100,nullable = true)    
    public String getLockedBy() {
		return lockedBy;
	}
	public void setLockedBy(String lockedBy) {
		this.lockedBy = lockedBy;
	}
	
	@Column ( name = "PANHOLDERNAME", length = 100) 
  	public String getPanHolderName() {
		return panHolderName;
	}
	public void setPanHolderName(String panHolderName) {
		this.panHolderName = panHolderName;
	}
	
	
	public void setVillageCode(String villageCode) {
  		this.villageCode = villageCode;
  	}
  	 @Column ( name = "VILLAGE_CODE", length = 100 , nullable = true )
  	 public String getVillageCode() {
  			return villageCode;
  		}
  	 
  	
  	public void setMandalType(String mandalType) {
  		this.mandalType = mandalType;
  	}
  	@Column ( name = "MANDAL_TYPE", length = 100 , nullable = true )
  	public String getMandalType() {
  		return mandalType;
  	}
   

	@Id
    @Column ( name = "HOSPINFO_ID", length = 100 , nullable = false )
    public String getHospInfoId() {
        return hospInfoId;
    }
    
 	public void setHospInfoId(String hospInfoId) {
        this.hospInfoId = hospInfoId;
    }

    public void setHospName(String hospName) {
        this.hospName = hospName;
    }
    @Column ( name = "HOSP_NAME", length = 200 , nullable = true )
    public String getHospName() {
        return hospName;
    }

    public void setHospAdd(String hospAdd) {
        this.hospAdd = hospAdd;
    }
    @Column ( name = "HOSP_ADD", length = 200 , nullable = true )
    public String getHospAdd() {
        return hospAdd;
    }

    public void setHospEstableshedYear(String hospEstableshedYear) {
        this.hospEstableshedYear = hospEstableshedYear;
    }
    @Column ( name = "HOSP_ESTABLISH_YEAR", length = 4 , nullable = true )
    public String getHospEstableshedYear() {
        return hospEstableshedYear;
    }

    public void setHospBedStrength(String hospBedStrength) {
        this.hospBedStrength = hospBedStrength;
    }
    @Column ( name = "HOSP_BED_STRENGTH", length = 5 , nullable = true )
    public String getHospBedStrength() {
        return hospBedStrength;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }
    @Column ( name = "CRT_DT", nullable = true )
    public Date getCrtDt() {
        return crtDt;
    }

    

    public void setHospMDTelPh(String hospMDTelPh) {
        this.hospMDTelPh = hospMDTelPh;
    }
    @Column ( name = "HOSP_MD_TEL_PH", length =20 , nullable = true )
    public String getHospMDTelPh() {
        return hospMDTelPh;
    }

  

    public void setHospMDEmail(String hospMDEmail) {
        this.hospMDEmail = hospMDEmail;
    }
    @Column ( name = "HOSP_MD_EMAIL", length = 100 , nullable = true )
    public String getHospMDEmail() {
        return hospMDEmail;
    }

   
    public void setStatus(String status) {
        this.status = status;
    }
    @Column ( name = "STATUS", length = 10 , nullable = true )
    public String getStatus() {
        return status;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
    @Column ( name = "SECURITY_CODE", length = 10 , nullable = true )
    public String getSecurityCode() {
        return securityCode;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }
    @Column ( name = "UPD_DT",nullable = true )
    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdUsr(String updUsr) {
        this.updUsr = updUsr;
    }
    @Column ( name = "UPD_USR", length = 20 , nullable = true )
    public String getUpdUsr() {
        return updUsr;
    }

  
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
    @Column ( name = "CITY_CODE", length = 20 , nullable = true )
    public String getCityCode() {
        return cityCode;
    }

   

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }
    @Column ( name = "FAX_NO", length = 20 , nullable = true )
    public String getFaxNo() {
        return faxNo;
    }
   
    public void setBankAccName(String bankAccName) {
        this.bankAccName = bankAccName;
    }
    @Column ( name = "BANKACNTNAME", length = 200 , nullable = true )
    public String getBankAccName() {
        return bankAccName;
    }

    public void setBankAccNumber(String bankAccNumber) {
        this.bankAccNumber = bankAccNumber;
    }
    @Column ( name = "BANKACNTNUMBR", length = 20 , nullable = true )
    public String getBankAccNumber() {
        return bankAccNumber;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    @Column ( name = "BANKNAME", length = 100 , nullable = true )
    public String getBankName() {
        return bankName;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }
    @Column ( name = "BANKBRANCH", length = 100 , nullable = true )
    public String getBankBranch() {
        return bankBranch;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }
    @Column ( name = "IFSCCODE", length = 20 , nullable = true )
    public String getIfscCode() {
        return ifscCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }
    @Column ( name = "DISTRICT_CODE", length = 20 , nullable = true )
    public String getDistrictCode() {
        return districtCode;
    }


    public void setEnhancedStatus(String enhancedStatus) {
        this.enhancedStatus = enhancedStatus;
    }
    @Column ( name = "ENHANCED_STATUS", length = 10 , nullable = true )
    public String getEnhancedStatus() {
        return enhancedStatus;
    }

    public void setEnhancedCount(String enhancedCount) {
        this.enhancedCount = enhancedCount;
    }
    @Column ( name = "ENHANCED_COUNT", nullable = true )
    public String getEnhancedCount() {
        return enhancedCount;
    }

    public void setEnhancedUpdDate(Date enhancedUpdDate) {
        this.enhancedUpdDate = enhancedUpdDate;
    }
    @Column ( name = "ENHANCED_UPD_DT", length = 100 , nullable = true )
    public Date getEnhancedUpdDate() {
        return enhancedUpdDate;
    }

    public void setEnhancedUpdUser(String enhancedUpdUser) {
        this.enhancedUpdUser = enhancedUpdUser;
    }
    @Column ( name = "ENHANCED_UPD_USR", length = 20, nullable = true )
    public String getEnhancedUpdUser() {
        return enhancedUpdUser;
    }

    public void setEnhancedCrtDt(Date enhancedCrtDt) {
        this.enhancedCrtDt = enhancedCrtDt;
    }
    @Column ( name = "ENHANCED_CRT_DT", length = 100 , nullable = true )
    public Date getEnhancedCrtDt() {
        return enhancedCrtDt;
    }


    public void setHospCategory(String hospCategory) {
        this.hospCategory = hospCategory;
    }
    @Column ( name = "HOSP_CATEGORY", length = 20 , nullable = true )
    public String getHospCategory() {
        return hospCategory;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }
    @Column ( name = "PANNUMBER", length = 50 , nullable = true )
    public String getPanNumber() {
        return panNumber;
    }


    public void setHospStreet(String hospStreet) {
        this.hospStreet = hospStreet;
    }
    @Column ( name = "HOSP_STREET", length = 200 , nullable = true )
    public String getHospStreet() {
        return hospStreet;
    }


    public void setEnhancedCrtUsr(String enhancedCrtUsr) {
        this.enhancedCrtUsr = enhancedCrtUsr;
    }
    @Column ( name = "ENHANCED_CRT_USER", length = 20 , nullable = true )
    public String getEnhancedCrtUsr() {
        return enhancedCrtUsr;
    }

    public void setParentGroup(String parentGroup) {
        this.parentGroup = parentGroup;
    }

    @Column ( name = "PARENT_GROUP", length = 100 , nullable = true )
    public String getParentGroup() {
        return parentGroup;
    }
    public void setAuthSign(String authSign) {
        this.authSign = authSign;
    }
    
    @Column ( name = "AUTH_SIGN_NAME",length = 100 , nullable = true  )
    public String getAuthSign() {
        return authSign;
    }

    public void setConstitution(String constitution) {
        this.constitution = constitution;
    }

    @Column ( name = "HOSP_CONSTITUTION" ,length = 50 , nullable = true)
    public String getConstitution() {
        return constitution;
    }

    public void setHospMDRationCardNo(String hospMDRationCardNo) {
        this.hospMDRationCardNo = hospMDRationCardNo;
    }
    @Column ( name = "MD_RATIONCARD_NO", length = 30 , nullable = true )
    public String getHospMDRationCardNo() {
        return hospMDRationCardNo;
    }

    public void setHospMDCommStatus(String hospMDCommStatus) {
        this.hospMDCommStatus = hospMDCommStatus;
    }
    @Column ( name = "MD_COMMUNITY_STATUS", length = 10 , nullable = true )
    public String getHospMDCommStatus() {
        return hospMDCommStatus;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    @Column ( name = "BRANCH_CODE",length = 50 , nullable = true  )
    public String getBranchCode() {
        return branchCode;
    }
    public void setInspStartDt(Date inspStartDt) {
        this.inspStartDt = inspStartDt;
    }
    @Column(name="INSP_START_DATE", nullable = true )
    public Date getInspStartDt() {
        return inspStartDt;
    }

    public void setInspEndDt(Date inspEndDt) {
        this.inspEndDt = inspEndDt;
    }
   @Column(name="INSP_END_DATE", nullable = true)
    public Date getInspEndDt() {
        return inspEndDt;
    }

    public void setSysgen(String sysgen) {
        this.sysgen = sysgen;
    }
    @Column(name="SYSGEN",length = 5 , nullable = true )
    public String getSysgen() {
        return sysgen;
    }
    public void setMandal(String mandal) {
        this.mandal = mandal;
    }
    @Column(name="MANDAL",length = 20 , nullable = true )
    public String getMandal() {
        return mandal;
    }
    public void setStaff(String staff) {
        this.staff = staff;
    }
    @Column(name="STAFF",length = 30 , nullable = true)
    public String getStaff() {
        return staff;
    }

    public void setInfo(String info) {
        this.info = info;
    }
   @Column(name="INFO", length = 30 , nullable = true)
    public String getInfo() {
        return info;
    }
   @Column(name="HOSP_LANDLINE",length = 20 , nullable = true)
   public String getHospLandLine() {
	return hospLandLine;
   }
   public void setHospLandLine(String hospLandLine) {
	this.hospLandLine = hospLandLine;
   }
   
   @Column(name="STATE_CODE",length = 20 , nullable = true)
	public String getStateCode() {
	return stateCode;
}

public void setStateCode(String stateCode) {
	this.stateCode = stateCode;
}
  
 	@Column ( name = "DELIST_REFNO", length = 20 , nullable = true )
    public String getDelistRefNo() {
		return delistRefNo;
	}

	public void setDelistRefNo(String delistRefNo) {
		this.delistRefNo = delistRefNo;
	}
	@Column ( name = "REEMPANEL_TYPE", length = 20 , nullable = true )
	public String getRempanelType() {
		return rempanelType;
	}

	public void setRempanelType(String rempanelType) {
		this.rempanelType = rempanelType;
	}
	 /* @Column ( name = "TAXEXSTATUS", length = 100 , nullable = true )
	    public String getTaxStatus() {
			return taxStatus;
		}

		public void setTaxStatus(String taxStatus) {
			this.taxStatus = taxStatus;
		}*/
	
	
	@Column (name = "APPLICTN_TYPE", length = 10 , nullable = true )
	public String getApplictnType() {
		return applictnType;
	}

	public void setApplictnType(String applictnType) {
		this.applictnType = applictnType;
	}

	@Column (name = "HOSP_MD_CEO_NAME", length = 100 , nullable = true )
	public String getCeoName() {
		return ceoName;
	}

	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}
	@Column (name = "PARENT_TYPE", length = 50 , nullable = true )
	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}
	@Column (name = "SCHEME", length = 10 , nullable = true )
	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

   
}
