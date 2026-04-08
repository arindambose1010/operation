package com.ahct.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "ehfm_medco_dtls" )
public class EhfmMedcoDetails implements java.io.Serializable
{


  // Fields    

  private EhfmMedcoDetailsId id ;

  private Date crtDt;
  private String crtUsr;
  private Date lstUpdDt;
  private String lstUpdUsr;
  private Date effEndDt;

    @EmbeddedId

    @AttributeOverrides ( { @AttributeOverride ( name = "userId", column = @Column ( name = "MEDCO_ID", nullable = false, length = 12 )
        )
        , @AttributeOverride ( name = "hospId", column = @Column ( name = "HOSP_ID", nullable = false, length = 5 )
        )
        , @AttributeOverride ( name = "effStartDate", column = @Column ( name = "START_DT", nullable = false )
        )
        } )
        public EhfmMedcoDetailsId getId() {
            return id;
        }
    public void setId(EhfmMedcoDetailsId id) {
        this.id = id;
    }
 
    @Column(name="CRT_DT", nullable=true)
    public Date getCrtDt() {
        return crtDt;
    }


    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }
    @Column(name="CRT_USR", nullable=true)
    public String getCrtUsr() {
        return crtUsr;
    }
    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }

    @Column(name="LST_UPD_DT", nullable=true) 
    public Date getLstUpdDt() {
        return lstUpdDt;
    }
    public void setLstUpdDt(Date lstUpdDt) {
        this.lstUpdDt = lstUpdDt;
    }

    @Column(name="LST_UPD_USR", nullable=true) 
    public String getLstUpdUsr() {
        return lstUpdUsr;
    }

    public void setLstUpdUsr(String lstUpdUsr) {
        this.lstUpdUsr = lstUpdUsr;
    }
    @Column(name="END_DT", nullable=true) 
    public Date getEffEndDt() {
        return effEndDt;
    }
    public void setEffEndDt(Date effEndDt) {
        this.effEndDt = effEndDt;
    }  
}
