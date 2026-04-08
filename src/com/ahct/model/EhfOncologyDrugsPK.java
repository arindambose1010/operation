package com.ahct.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EhfOncologyDrugsPK implements Serializable {

    @Column(name = "PROFORMA_ID", length = 30, nullable = false)
    private String proformaId;

    @Column(name = "DRUG_ID", length = 30, nullable = false)
    private String drugId;

    // Default constructor
    public EhfOncologyDrugsPK() {}

    // Parameterized constructor
    public EhfOncologyDrugsPK(String proformaId, String drugId) {
        this.proformaId = proformaId;
        this.drugId = drugId;
    }

    // Getters and Setters
    public String getProformaId() {
        return proformaId;
    }

    public void setProformaId(String proformaId) {
        this.proformaId = proformaId;
    }

    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EhfOncologyDrugsPK that = (EhfOncologyDrugsPK) o;
        return Objects.equals(proformaId, that.proformaId) &&
               Objects.equals(drugId, that.drugId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proformaId, drugId);
    }
}