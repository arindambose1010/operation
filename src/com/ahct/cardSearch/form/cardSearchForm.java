package com.ahct.cardSearch.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.ahct.cardSearch.vo.cardSearchVO;



public class cardSearchForm extends ActionForm
	{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String empRadio;
		private String cardNo;
		private String successFlag;
		private String NAME;
		private String DATEOFBIRTH;
		private String GENDER;
		private String RELATION;
		private String CARDNUMBER;
		private String PHOTO;
		private String cfmsFlag;
		private String photoFile;
		
		private List<cardSearchVO> cardList;
		private List<cardSearchVO> photoList;
		
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
		public String getGENDER() {
			return GENDER;
		}
		public void setGENDER(String gENDER) {
			GENDER = gENDER;
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
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		public String getPhotoFile() {
			return photoFile;
		}
		public void setPhotoFile(String photoFile) {
			this.photoFile = photoFile;
		}
		public List<cardSearchVO> getPhotoList() {
			return photoList;
		}
		public void setPhotoList(List<cardSearchVO> photoList) {
			this.photoList = photoList;
		}
		public String getCfmsFlag() {
			return cfmsFlag;
		}
		public void setCfmsFlag(String cfmsFlag) {
			this.cfmsFlag = cfmsFlag;
		}
		
		
	}
