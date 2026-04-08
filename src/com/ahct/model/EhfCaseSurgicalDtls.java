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
@Table(name = "EHF_CASE_SuRGICAL_DTLS")
public class EhfCaseSurgicalDtls implements Serializable {
	 private String anaesthetistDesg;
	    private String anaesthetistName;
	    private String anaesthetistQual;
	    private String anaesthetistRegNo;
	    private String anaesthetistMbNo;    
	    private String attachmentDesc;
	    private String caseId;
	    private Date crtDt;
	    private String crtUsr;
	    private String expectedHospStay;
	    private String fileName;
	    private String filePath;
	    private String floor;
	    private String nurseName;
	    private Date pacDateTime;
	    private String pacNotes;
	    private String paramedicName;
	    private String roomNo;
	    private String surgeonDesg;
	    private String surgeonName;
	    private String surgeonQual;
	    private String surgeryAsst1Desg;
	    private String surgeryAsst1Name;
	    private String surgeryAsst1Qual;
	    private String surgeryAsst2;
	    private String surgeryAsst2Desg;
	    private String surgeryAsst2Qual;
	    private String surgeryAsst3;
	    private String surgeryAsst3Desg;
	    private String surgeryAsst3Qual;
	    private String surgeryAsst1CntctNo;
	    private String surgeryAsst1Regno;
	    private String surgeryCntctNo;
	    private Date surgeryDateTime;
	    private String surgeryNotes;
	    private String surgeryRegno;
	    private String surgStartTime;
	    private String surgEndTime;   
	    
	    private String anesthesiaType;
	    private String incisionType;
	    private String opPhotoWebEx;
	    private String videorec;
	    private String swabCnt;
	    private String surturesLigatures;
	    private String specimenRem;
	    private String drainageCnt;
	    private String bloodLoss;
	    private String complications;
	    private String postOperInstru;
	    private String condOfPat;
	    private String specimenName;
	 	private String complicationsRemarks;
	 	
	 	
	 	@Column(name = "specimen_name")
	    public String getSpecimenName() {
			return specimenName;
		}

		public void setSpecimenName(String specimenName) {
			this.specimenName = specimenName;
		}
		@Column(name = "complictaion_remarks")
		public String getComplicationsRemarks() {
			return complicationsRemarks;
		}

		public void setComplicationsRemarks(String complicationsRemarks) {
			this.complicationsRemarks = complicationsRemarks;
		}

		@Column(name = "anesthesia_type")
	    public String getAnesthesiaType() {
			return anesthesiaType;
		}

		public void setAnesthesiaType(String anesthesiaType) {
			this.anesthesiaType = anesthesiaType;
		}
		@Column(name = "incision_type")
		public String getIncisionType() {
			return incisionType;
		}

		public void setIncisionType(String incisionType) {
			this.incisionType = incisionType;
		}
		@Column(name = "opPoto_webEx")
		public String getOpPhotoWebEx() {
			return opPhotoWebEx;
		}

		public void setOpPhotoWebEx(String opPhotoWebEx) {
			this.opPhotoWebEx = opPhotoWebEx;
		}
		@Column(name = "video_rec")
		public String getVideorec() {
			return videorec;
		}

		public void setVideorec(String videorec) {
			this.videorec = videorec;
		}
		 @Column(name = "swab_cnt")
		public String getSwabCnt() {
			return swabCnt;
		}

		public void setSwabCnt(String swabCnt) {
			this.swabCnt = swabCnt;
		}
		  @Column(name = "surtures_ligatures")
		public String getSurturesLigatures() {
			return surturesLigatures;
		}

		public void setSurturesLigatures(String surturesLigatures) {
			this.surturesLigatures = surturesLigatures;
		}
		  @Column(name = "sepcimen_remv")
		public String getSpecimenRem() {
			return specimenRem;
		}

		public void setSpecimenRem(String specimenRem) {
			this.specimenRem = specimenRem;
		}
		  @Column(name = "drainage_cnt")
		public String getDrainageCnt() {
			return drainageCnt;
		}

		public void setDrainageCnt(String drainageCnt) {
			this.drainageCnt = drainageCnt;
		}
		  @Column(name = "blood_loss")
		public String getBloodLoss() {
			return bloodLoss;
		}

		public void setBloodLoss(String bloodLoss) {
			this.bloodLoss = bloodLoss;
		}
		  @Column(name = "complications")
		public String getComplications() {
			return complications;
		}

		public void setComplications(String complications) {
			this.complications = complications;
		}
		  @Column(name = "postOper_instr")
		public String getPostOperInstru() {
			return postOperInstru;
		}

		public void setPostOperInstru(String postOperInstru) {
			this.postOperInstru = postOperInstru;
		}
		  @Column(name = "conditionOf_pat")
		public String getCondOfPat() {
			return condOfPat;
		}

		public void setCondOfPat(String condOfPat) {
			this.condOfPat = condOfPat;
		}

		public EhfCaseSurgicalDtls (){
	    }

	    @Column(name = "ANAESTHETIST_DESG")
	    public String getAnaesthetistDesg() {
	        return anaesthetistDesg;
	    }

	    public void setAnaesthetistDesg(String anaesthetistDesg) {
	        this.anaesthetistDesg = anaesthetistDesg;
	    }

	    @Column(name = "ANAESTHETIST_NAME")
	    public String getAnaesthetistName() {
	        return anaesthetistName;
	    }

	    public void setAnaesthetistName(String anaesthetistName) {
	        this.anaesthetistName = anaesthetistName;
	    }

	    @Column(name = "ANAESTHETIST_QUAL")
	    public String getAnaesthetistQual() {
	        return anaesthetistQual;
	    }

	    public void setAnaesthetistQual(String anaesthetistQual) {
	        this.anaesthetistQual = anaesthetistQual;
	    }

	    @Column(name = "ATTACHMENT_DESC")
	    public String getAttachmentDesc() {
	        return attachmentDesc;
	    }

	    public void setAttachmentDesc(String attachmentDesc) {
	        this.attachmentDesc = attachmentDesc;
	    }

	    @Id
	    @Column(name = "CASE_ID", nullable = false)
	    public String getCaseId() {
	        return caseId;
	    }

	    public void setCaseId(String caseId) {
	        this.caseId = caseId;
	    }
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "CRT_DT", nullable = false)
	    public Date getCrtDt() {
	        return crtDt;
	    }

	    public void setCrtDt(Date crtDt) {
	        this.crtDt = crtDt;
	    }

	    @Column(name = "CRT_USR", nullable = false)
	    public String getCrtUsr() {
	        return crtUsr;
	    }

	    public void setCrtUsr(String crtUsr) {
	        this.crtUsr = crtUsr;
	    }

	    @Column(name = "EXPECTED_HOSP_STAY")
	    public String getExpectedHospStay() {
	        return expectedHospStay;
	    }

	    public void setExpectedHospStay(String expectedHospStay) {
	        this.expectedHospStay = expectedHospStay;
	    }

	    @Column(name = "FILE_NAME")
	    public String getFileName() {
	        return fileName;
	    }

	    public void setFileName(String fileName) {
	        this.fileName = fileName;
	    }

	    @Column(name = "FILE_PATH")
	    public String getFilePath() {
	        return filePath;
	    }

	    public void setFilePath(String filePath) {
	        this.filePath = filePath;
	    }

	    public String getFloor() {
	        return floor;
	    }

	    public void setFloor(String floor) {
	        this.floor = floor;
	    }

	    @Column(name = "NURSE_NAME")
	    public String getNurseName() {
	        return nurseName;
	    }

	    public void setNurseName(String nurseName) {
	        this.nurseName = nurseName;
	    }
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "PAC_DATE_TIME")
	    public Date getPacDateTime() {
	        return pacDateTime;
	    }

	    public void setPacDateTime(Date pacDateTime) {
	        this.pacDateTime = pacDateTime;
	    }

	    @Column(name = "PAC_NOTES")
	    public String getPacNotes() {
	        return pacNotes;
	    }

	    public void setPacNotes(String pacNotes) {
	        this.pacNotes = pacNotes;
	    }

	    @Column(name = "PARAMEDIC_NAME")
	    public String getParamedicName() {
	        return paramedicName;
	    }

	    public void setParamedicName(String paramedicName) {
	        this.paramedicName = paramedicName;
	    }

	    @Column(name = "ROOM_NO")
	    public String getRoomNo() {
	        return roomNo;
	    }

	    public void setRoomNo(String roomNo) {
	        this.roomNo = roomNo;
	    }

	    @Column(name = "SURGEON_DESG")
	    public String getSurgeonDesg() {
	        return surgeonDesg;
	    }

	    public void setSurgeonDesg(String surgeonDesg) {
	        this.surgeonDesg = surgeonDesg;
	    }

	    @Column(name = "SURGEON_NAME")
	    public String getSurgeonName() {
	        return surgeonName;
	    }

	    public void setSurgeonName(String surgeonName) {
	        this.surgeonName = surgeonName;
	    }

	    @Column(name = "SURGEON_QUAL")
	    public String getSurgeonQual() {
	        return surgeonQual;
	    }

	    public void setSurgeonQual(String surgeonQual) {
	        this.surgeonQual = surgeonQual;
	    }

	    @Column(name = "SURGERY_ASST1_DESG")
	    public String getSurgeryAsst1Desg() {
	        return surgeryAsst1Desg;
	    }

	    public void setSurgeryAsst1Desg(String surgeryAsst1Desg) {
	        this.surgeryAsst1Desg = surgeryAsst1Desg;
	    }

	    @Column(name = "SURGERY_ASST1_NAME")
	    public String getSurgeryAsst1Name() {
	        return surgeryAsst1Name;
	    }

	    public void setSurgeryAsst1Name(String surgeryAsst1Name) {
	        this.surgeryAsst1Name = surgeryAsst1Name;
	    }

	    @Column(name = "SURGERY_ASST1_QUAL")
	    public String getSurgeryAsst1Qual() {
	        return surgeryAsst1Qual;
	    }

	    public void setSurgeryAsst1Qual(String surgeryAsst1Qual) {
	        this.surgeryAsst1Qual = surgeryAsst1Qual;
	    }

	    @Column(name = "SURGERY_ASST2")
	    public String getSurgeryAsst2() {
	        return surgeryAsst2;
	    }

	    public void setSurgeryAsst2(String surgeryAsst2) {
	        this.surgeryAsst2 = surgeryAsst2;
	    }

	    @Column(name = "SURGERY_ASST2_DESG")
	    public String getSurgeryAsst2Desg() {
	        return surgeryAsst2Desg;
	    }

	    public void setSurgeryAsst2Desg(String surgeryAsst2Desg) {
	        this.surgeryAsst2Desg = surgeryAsst2Desg;
	    }

	    @Column(name = "SURGERY_ASST2_QUAL")
	    public String getSurgeryAsst2Qual() {
	        return surgeryAsst2Qual;
	    }

	    public void setSurgeryAsst2Qual(String surgeryAsst2Qual) {
	        this.surgeryAsst2Qual = surgeryAsst2Qual;
	    }

	    @Column(name = "SURGERY_ASST3")
	    public String getSurgeryAsst3() {
	        return surgeryAsst3;
	    }

	    public void setSurgeryAsst3(String surgeryAsst3) {
	        this.surgeryAsst3 = surgeryAsst3;
	    }

	    @Column(name = "SURGERY_ASST3_DESG")
	    public String getSurgeryAsst3Desg() {
	        return surgeryAsst3Desg;
	    }

	    public void setSurgeryAsst3Desg(String surgeryAsst3Desg) {
	        this.surgeryAsst3Desg = surgeryAsst3Desg;
	    }

	    @Column(name = "SURGERY_ASST3_QUAL")
	    public String getSurgeryAsst3Qual() {
	        return surgeryAsst3Qual;
	    }

	    public void setSurgeryAsst3Qual(String surgeryAsst3Qual) {
	        this.surgeryAsst3Qual = surgeryAsst3Qual;
	    }

	    @Column(name = "SURGERY_ASST1_CNTCT_NO")
	    public String getSurgeryAsst1CntctNo() {
	        return surgeryAsst1CntctNo;
	    }

	    public void setSurgeryAsst1CntctNo(String surgeryAsst1CntctNo) {
	        this.surgeryAsst1CntctNo = surgeryAsst1CntctNo;
	    }


	    @Column(name = "SURGERY_ASST1_REGNO")
	    public String getSurgeryAsst1Regno() {
	        return surgeryAsst1Regno;
	    }

	    public void setSurgeryAsst1Regno(String surgeryAsst1Regno) {
	        this.surgeryAsst1Regno = surgeryAsst1Regno;
	    }

	    @Column(name = "SURGERY_CNTCT_NO")
	    public String getSurgeryCntctNo() {
	        return surgeryCntctNo;
	    }

	    public void setSurgeryCntctNo(String surgeryCntctNo) {
	        this.surgeryCntctNo = surgeryCntctNo;
	    }
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "SURGERY_DATE_TIME")
	    public Date getSurgeryDateTime() {
	        return surgeryDateTime;
	    }

	    public void setSurgeryDateTime(Date surgeryDateTime) {
	        this.surgeryDateTime = surgeryDateTime;
	    }

	    @Column(name = "SURGERY_NOTES")
	    public String getSurgeryNotes() {
	        return surgeryNotes;
	    }

	    public void setSurgeryNotes(String surgeryNotes) {
	        this.surgeryNotes = surgeryNotes;
	    }

	    @Column(name = "SURGERY_REGNO")
	    public String getSurgeryRegno() {
	        return surgeryRegno;
	    }

	    public void setSurgeryRegno(String surgeryRegno) {
	        this.surgeryRegno = surgeryRegno;
	    }
	    @Column(name = "ANESTHETIST_REGNO")
	    public String getAnaesthetistRegNo() {
	        return anaesthetistRegNo;
	    }

	    public void setAnaesthetistRegNo(String anaesthetistRegNo) {
	        this.anaesthetistRegNo = anaesthetistRegNo;
	    }
	   
	    @Column(name = "ANESTHETIST_MBNO")
	    public String getAnaesthetistMbNo() {
	        return anaesthetistMbNo;
	    }
	    public void setAnaesthetistMbNo(String anaesthetistMbNo) {
	        this.anaesthetistMbNo = anaesthetistMbNo;
	    }
	    @Column(name="SURG_STRT_TIME")
	    public String getSurgStartTime() {
	        return surgStartTime;
	    }
	    public void setSurgStartTime(String surgStartTime) {
	        this.surgStartTime = surgStartTime;
	    }
	    
	    @Column(name="SURG_END_TIME")
	    public String getSurgEndTime() {
	        return surgEndTime;
	    }
	    public void setSurgEndTime(String surgEndTime) {
	        this.surgEndTime = surgEndTime;
	    }
}
