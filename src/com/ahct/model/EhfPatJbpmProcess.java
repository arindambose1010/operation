package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "EHF_PATIENT_JBPM_PROCESS")
public class EhfPatJbpmProcess implements Serializable {

	private String patientId;
    private String processInstanceId;
    
    public EhfPatJbpmProcess()
    {
    	super();
		// TODO Auto-generated constructor stub
    }
	public EhfPatJbpmProcess(String patientId, String processInstanceId) {
		super();
		this.patientId = patientId;
		this.processInstanceId = processInstanceId;
	}
    @Column(name="PATIENT_ID", nullable = false)
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	@Id
    @Column(name="PROCESS_INSTANCE_ID", nullable = false)
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
    
}
