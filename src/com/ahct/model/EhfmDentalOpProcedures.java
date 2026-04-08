package com.ahct.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="EHFM_DENTAL_OP_PROCEDURES")
public class EhfmDentalOpProcedures 
{
	private String icdProcCode;
	private Integer dopUnits;
	
	@Id
	@Column(name="ICD_PROC_CODE", nullable = false)
	public String getIcdProcCode() {
		return icdProcCode;
	}
	public void setIcdProcCode(String icdProcCode) {
		this.icdProcCode = icdProcCode;
	}
	
	@Column(name="DOP_UNITS", nullable = true)
	public Integer getDopUnits() {
		return dopUnits;
	}
	public void setDopUnits(Integer dopUnits) {
		this.dopUnits = dopUnits;
	}
	
	public EhfmDentalOpProcedures() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
