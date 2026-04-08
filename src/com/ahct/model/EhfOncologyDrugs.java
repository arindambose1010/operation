package com.ahct.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EHF_ONCOLOGY_DRUGS")
public class EhfOncologyDrugs {

    @EmbeddedId
    private EhfOncologyDrugsPK id;

    @Column(name = "DOSAGE", length = 30)
    private String dosage;

    @Column(name = "FREQUENCY", length = 30)
    private String frequency;

    @Column(name = "CRT_DT")
    private Timestamp crtDt;

    @Column(name = "CRT_USR", length = 30)
    private String crtUsr;

    // Default constructor
    public EhfOncologyDrugs() {}

    // Getters and Setters
    public EhfOncologyDrugsPK getId() {
        return id;
    }

    public void setId(EhfOncologyDrugsPK id) {
        this.id = id;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Timestamp getCrtDt() {
        return crtDt;
    }

    public void setCrtDt(Timestamp crtDt) {
        this.crtDt = crtDt;
    }

    public String getCrtUsr() {
        return crtUsr;
    }

    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }
}
