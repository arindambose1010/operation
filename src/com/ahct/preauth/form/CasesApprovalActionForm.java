package com.ahct.preauth.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.ahct.preauth.vo.PastHistoryVO;

public class CasesApprovalActionForm extends ActionForm{
private List<PastHistoryVO> cardUtilList ;
private String nwhname;
private String address;
private String contactNo;
private String hospBedCap;
private String currBedOccu;


public String getNwhname() {
	return nwhname;
}
public void setNwhname(String nwhname) {
	this.nwhname = nwhname;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getContactNo() {
	return contactNo;
}
public void setContactNo(String contactNo) {
	this.contactNo = contactNo;
}
public String getHospBedCap() {
	return hospBedCap;
}
public void setHospBedCap(String hospBedCap) {
	this.hospBedCap = hospBedCap;
}
public String getCurrBedOccu() {
	return currBedOccu;
}
public void setCurrBedOccu(String currBedOccu) {
	this.currBedOccu = currBedOccu;
}
public List<PastHistoryVO> getCardUtilList() {
	return cardUtilList;
}
public void setCardUtilList(List<PastHistoryVO> cardUtilList) {
	this.cardUtilList = cardUtilList;
} 

}
