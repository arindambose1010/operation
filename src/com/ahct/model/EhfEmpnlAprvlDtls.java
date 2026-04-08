package com.ahct.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table (name="EHF_EMPNL_APRVL_DTLS")
public class EhfEmpnlAprvlDtls implements Serializable
{
    public EhfEmpnlAprvlDtls() {
    }

    
    private EhfEmpnlAprvlDtlsId id; 
    private String app_aceptrejpend_id;
    private Date app_aceptrejpend_date;
    private String app_aceptrejpend_remarks;
    private String inspc_approver_id;
    private Date inspc_approved_date;
    private String inspc_approver_remarks;
    private String eo_jeo_approver_id;
    private Date eo_jeo_approved_date;
    private String ceo_approver_id;
    private Date ceo_approved_date;
    private String inspassined_name1;
    private String inspassined_name2;
    private String inspassined_name3;
    private String inspteam_name1;
    private String inspteam_name2;
    private String inspteam_name3;
    private String inspteam_recomended;
    private String eo_jeo_recomended;
    private String hospcode;
    private String eo_jeo_name;
    private String eo_jeo_approver_remarks;
    private String ceo_approver_remarks;
    private Date date_of_inspection;
    private Date ec_aprvd_dt;    
    private String apprv_status;
    private String ceo_name;
    private String sysgentd;
    private String hospMdName;
    private String distCordnatrName;
    private Date scaRegDate;
    private String othrStCeoRmks;
    private Date othrStCeoRmksDt;
    private String othrStCeoUsrId;

    public void setId(EhfEmpnlAprvlDtlsId id) {
        this.id = id;
    }
    
    @EmbeddedId
    @AttributeOverrides( {
                    @AttributeOverride(name = "hospinfo_Id", column = @Column(name = "HOSPINFO_ID", nullable = false, length = 5)),
                    @AttributeOverride(name = "enhanced_count", column = @Column(name = "ENHANCED_COUNT", nullable = false, length=3)) })

    public EhfEmpnlAprvlDtlsId getId() {
        return id;
    }

    public void setApp_aceptrejpend_id(String app_aceptrejpend_id) {
        this.app_aceptrejpend_id = app_aceptrejpend_id;
    }
    
    @Column (name="APP_ACEPTREJPEND_ID")
    public String getApp_aceptrejpend_id() {
        return app_aceptrejpend_id;
    }



    public void setApp_aceptrejpend_remarks(String app_aceptrejpend_remarks) {
        this.app_aceptrejpend_remarks = app_aceptrejpend_remarks;
    }
 
    @Column (name="APP_ACEPTREJPEND_REMARKS")
    public String getApp_aceptrejpend_remarks() {
        return app_aceptrejpend_remarks;
    }

    public void setInspc_approver_id(String inspc_approver_id) {
        this.inspc_approver_id = inspc_approver_id;
    }
    @Column (name="INSPC_APPROVER_ID")
    public String getInspc_approver_id() {
        return inspc_approver_id;
    }


    public void setInspc_approver_remarks(String inspc_approver_remarks) {
        this.inspc_approver_remarks = inspc_approver_remarks;
    }
    @Column (name="INSPC_APPROVER_REMARKS")
    public String getInspc_approver_remarks() {
        return inspc_approver_remarks;
    }
    
    public void setEo_jeo_approver_id(String eo_jeo_approver_id) {
        this.eo_jeo_approver_id = eo_jeo_approver_id;
    }
    @Column (name="EO_JEO_APPROVER_ID")
    public String getEo_jeo_approver_id() {
        return eo_jeo_approver_id;
    }

 
    
    public void setCeo_approver_id(String ceo_approver_id) {
        this.ceo_approver_id = ceo_approver_id;
    }
    @Column (name="CEO_APPROVER_ID")
    public String getCeo_approver_id() {
        return ceo_approver_id;
    }

   

    public void setInspassined_name1(String inspassined_name1) {
        this.inspassined_name1 = inspassined_name1;
    }
    @Column (name="INSPASSINED_NAME1")
    public String getInspassined_name1() {
        return inspassined_name1;
    }

    public void setInspassined_name2(String inspassined_name2) {
        this.inspassined_name2 = inspassined_name2;
    }
    @Column (name="INSPASSINED_NAME2")
    public String getInspassined_name2() {
        return inspassined_name2;
    }

    public void setInspassined_name3(String inspassined_name3) {
        this.inspassined_name3 = inspassined_name3;
    }
    @Column (name="INSPASSINED_NAME3")
    public String getInspassined_name3() {
        return inspassined_name3;
    }

    public void setInspteam_name1(String inspteam_name1) {
        this.inspteam_name1 = inspteam_name1;
    }
    @Column (name="INSPTEAM_NAME1")
    public String getInspteam_name1() {
        return inspteam_name1;
    }

    public void setInspteam_name2(String inspteam_name2) {
        this.inspteam_name2 = inspteam_name2;
    }
    @Column (name="INSPTEAM_NAME2")
    public String getInspteam_name2() {
        return inspteam_name2;
    }

    public void setInspteam_name3(String inspteam_name3) {
        this.inspteam_name3 = inspteam_name3;
    }
    @Column (name="INSPTEAM_NAME3")
    public String getInspteam_name3() {
        return inspteam_name3;
    }

    public void setInspteam_recomended(String inspteam_recomended) {
        this.inspteam_recomended = inspteam_recomended;
    }
    @Column (name="INSPTEAM_RECOMENDED")
    public String getInspteam_recomended() {
        return inspteam_recomended;
    }

    public void setEo_jeo_recomended(String eo_jeo_recomended) {
        this.eo_jeo_recomended = eo_jeo_recomended;
    }
    @Column (name="EO_JEO_RECOMENDED")
    public String getEo_jeo_recomended() {
        return eo_jeo_recomended;
    }

    public void setHospcode(String hospcode) {
        this.hospcode = hospcode;
    }
    @Column (name="HOSPCODE")
    public String getHospcode() {
        return hospcode;
    }

    public void setEo_jeo_name(String eo_jeo_name) {
        this.eo_jeo_name = eo_jeo_name;
    }
    @Column (name="EO_JEO_NAME")
    public String getEo_jeo_name() {
        return eo_jeo_name;
    }

    public void setEo_jeo_approver_remarks(String eo_jeo_approver_remarks) {
        this.eo_jeo_approver_remarks = eo_jeo_approver_remarks;
    }
    @Column (name="EO_JEO_APPROVER_REMARKS")
    public String getEo_jeo_approver_remarks() {
        return eo_jeo_approver_remarks;
    }

    public void setCeo_approver_remarks(String ceo_approver_remarks) {
        this.ceo_approver_remarks = ceo_approver_remarks;
    }
    @Column (name="CEO_APPROVER_REMARKS")
    public String getCeo_approver_remarks() {
        return ceo_approver_remarks;
    }
   

    public void setApprv_status(String apprv_status) {
        this.apprv_status = apprv_status;
    }
    @Column (name="APPRV_STATUS")
    public String getApprv_status() {
        return apprv_status;
    }

    public void setCeo_name(String ceo_name) {
        this.ceo_name = ceo_name;
    }
    @Column (name="CEO_NAME")
    public String getCeo_name() {
        return ceo_name;
    }
   
    public void setApp_aceptrejpend_date(Date app_aceptrejpend_date) {
        this.app_aceptrejpend_date = app_aceptrejpend_date;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column (name="APP_ACEPTREJPEND_DATE")
    public Date getApp_aceptrejpend_date() {
        return app_aceptrejpend_date;
    }

    public void setInspc_approved_date(Date inspc_approved_date) {
        this.inspc_approved_date = inspc_approved_date;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column (name="INSPC_APPROVED_DATE")
    public Date getInspc_approved_date() {
        return inspc_approved_date;
    }

    public void setEo_jeo_approved_date(Date eo_jeo_approved_date) {
        this.eo_jeo_approved_date = eo_jeo_approved_date;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column (name="EO_JEO_APPROVED_DATE")
    public Date getEo_jeo_approved_date() {
        return eo_jeo_approved_date;
    }

    public void setCeo_approved_date(Date ceo_approved_date) {
        this.ceo_approved_date = ceo_approved_date;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column (name="CEO_APPROVED_DATE")
    public Date getCeo_approved_date() {
        return ceo_approved_date;
    }

    public void setEc_aprvd_dt(Date ec_aprvd_dt) {
        this.ec_aprvd_dt = ec_aprvd_dt;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column (name="EC_APRVD_DT")
    public Date getEc_aprvd_dt() {
        return ec_aprvd_dt;
    }

    public void setDate_of_inspection(Date date_of_inspection) {
        this.date_of_inspection = date_of_inspection;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column (name="DATE_OF_INSPECTION")
    public Date getDate_of_inspection() {
        return date_of_inspection;
    }
    
    public void setSysgentd(String sysgentd) {
		this.sysgentd = sysgentd;
	}
    
    @Column (name="SYSGENTD")
    public String getSysgentd() {
		return sysgentd;
	}
    public void setHospMdName(String hospMdName) {
        this.hospMdName = hospMdName;
    }
    @Column (name="HOSP_MD_NAME")
    public String getHospMdName() {
        return hospMdName;
    }

    public void setDistCordnatrName(String distCordnatrName) {
        this.distCordnatrName = distCordnatrName;
    }
    @Column (name="DIST_CORDNATR_NAME")
    public String getDistCordnatrName() {
        return distCordnatrName;
    }

    public void setScaRegDate(Date scaRegDate) {
        this.scaRegDate = scaRegDate;
    }
   
    @Column (name="SCA_REG_DATE")
    public Date getScaRegDate() {
        return scaRegDate;
    }
    @Column (name="OTHR_STCEO_RMKS",length = 4000)
	public String getOthrStCeoRmks() {
		return othrStCeoRmks;
	}

	public void setOthrStCeoRmks(String othrStCeoRmks) {
		this.othrStCeoRmks = othrStCeoRmks;
	}
	 @Column (name="OTHR_STCEO_RMKS_DT",length = 50)
	public Date getOthrStCeoRmksDt() {
		return othrStCeoRmksDt;
	}

	public void setOthrStCeoRmksDt(Date othrStCeoRmksDt) {
		this.othrStCeoRmksDt = othrStCeoRmksDt;
	}
	 @Column (name="OTHR_STCEO_USRID",length = 50)
	public String getOthrStCeoUsrId() {
		return othrStCeoUsrId;
	}

	public void setOthrStCeoUsrId(String othrStCeoUsrId) {
		this.othrStCeoUsrId = othrStCeoUsrId;
	}
   
  
}
