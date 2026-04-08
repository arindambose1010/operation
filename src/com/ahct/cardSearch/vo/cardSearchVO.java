package com.ahct.cardSearch.vo;

import java.util.List;

public class cardSearchVO 
	{
		private String empRadio;
		private String cardNo;
		private String successFlag;
		private String NAME;
		private String DATEOFBIRTH;
		private char GENDER;
		private String RELATION;
		private String CARDNUMBER;
		private String PHOTO;
		private String photoFile;
		private String FLAG;
		private String AADHARID;
		private String USERID;
		
		private List<cardSearchVO> cardList;
		
		
		public String getEmpRadio() {
			return empRadio;
		}
		public void setEmpRadio(String empRadio) {
			this.empRadio = empRadio;
		}
		public String getCardNo() {
			return cardNo;
		}
		public void setCardNo(String cardNo) {
			this.cardNo = cardNo;
		}
		public String getSuccessFlag() {
			return successFlag;
		}
		public void setSuccessFlag(String successFlag) {
			this.successFlag = successFlag;
		}
		public List<cardSearchVO> getCardList() {
			return cardList;
		}
		public void setCardList(List<cardSearchVO> cardList) {
			this.cardList = cardList;
		}
		public String getNAME() {
			return NAME;
		}
		public void setNAME(String nAME) {
			NAME = nAME;
		}
		public String getDATEOFBIRTH() {
			return DATEOFBIRTH;
		}
		public void setDATEOFBIRTH(String dATEOFBIRTH) {
			DATEOFBIRTH = dATEOFBIRTH;
		}
		public String getRELATION() {
			return RELATION;
		}
		public void setRELATION(String rELATION) {
			RELATION = rELATION;
		}
		public String getCARDNUMBER() {
			return CARDNUMBER;
		}
		public void setCARDNUMBER(String cARDNUMBER) {
			CARDNUMBER = cARDNUMBER;
		}
		public String getPHOTO() {
			return PHOTO;
		}
		public void setPHOTO(String pHOTO) {
			PHOTO = pHOTO;
		}
		public char getGENDER() {
			return GENDER;
		}
		public void setGENDER(char gENDER) {
			GENDER = gENDER;
		}
		public String getPhotoFile() {
			return photoFile;
		}
		public void setPhotoFile(String photoFile) {
			this.photoFile = photoFile;
		}
		
		public void setAADHARID(String aADHARID) {
			AADHARID = aADHARID;
		}
		public String getUSERID() {
			return USERID;
		}
		public void setUSERID(String uSERID) {
			USERID = uSERID;
		}
		public String getFLAG() {
			return FLAG;
		}
		public void setFLAG(String fLAG) {
			FLAG = fLAG;
		}
		public String getAADHARID() {
			return AADHARID;
		}
		
	}
