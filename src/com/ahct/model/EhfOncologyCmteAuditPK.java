package com.ahct.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EhfOncologyCmteAuditPK implements Serializable {

    @Column(name = "PATIENT_ID", length = 20, nullable = false)
    private String patientId;

    @Column(name = "ACT_ORDER", nullable = false)
    private Long actOrder;

    // Default constructor
    public EhfOncologyCmteAuditPK() {}

    // Parameterized constructor
    public EhfOncologyCmteAuditPK(String patientId, Long actOrder) {
        this.patientId = patientId;
        this.actOrder = actOrder;
    }

    // Getters and Setters
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Long getActOrder() {
        return actOrder;
    }

    public void setActOrder(Long actOrder) {
        this.actOrder = actOrder;
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EhfOncologyCmteAuditPK that = (EhfOncologyCmteAuditPK) o;
        return Objects.equals(patientId, that.patientId) &&
               Objects.equals(actOrder, that.actOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, actOrder);
    }
}
