package com.ahct.CEO.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.ahct.CEO.vo.EdcRequestRemarksVO;
import com.ahct.CEO.vo.EdcVO;
import com.ahct.CEO.vo.EmpanelHospVO;
import com.ahct.CEO.vo.UtilityVO;
import com.ahct.attachments.vo.AttachmentVO;

public class CeoWorkListForm extends ActionForm{
	
	private List<EmpanelHospVO> workList;
	private int startPage;
	private int endPage;
	private int startIndex;
	private int endIndex;
	private String rowsPerPage;
	private String showPage;
	private String next;
	private String prev;
	private String menu;
	private List<EdcVO> edcCeoWorklist;
	private List<EmpanelHospVO> hospDtlsList;
	private String hospId;
	private String hospName;
	private String hospLocation;
	private String remarks;
	private String scheme;
	private String actionId;
	private List<AttachmentVO> attPres;
	private String hospType;
	private String hospSpltyString;
	private List<UtilityVO> spltyList;
	private  List<UtilityVO> articleList;
	private String[] edcReqRemarks = new String[60];
	private List<List<EdcRequestRemarksVO>> remarksList;
	private List<EdcRequestRemarksVO> edcRequestDtls;
	private Number dutyDctrCnt;
	private Number paramdcCnt;
	private Number splstCnt;
	
	public String getHospId() {
		return hospId;
	}
	public void setHospId(String hospId) {
		this.hospId = hospId;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
	public String getHospLocation() {
		return hospLocation;
	}
	public void setHospLocation(String hospLocation) {
		this.hospLocation = hospLocation;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	public List<AttachmentVO> getAttPres() {
		return attPres;
	}
	public void setAttPres(List<AttachmentVO> attPres) {
		this.attPres = attPres;
	}

	public String getHospType() {
		return hospType;
	}
	public void setHospType(String hospType) {
		this.hospType = hospType;
	}
	public String getHospSpltyString() {
		return hospSpltyString;
	}
	public void setHospSpltyString(String hospSpltyString) {
		this.hospSpltyString = hospSpltyString;
	}
	public List<UtilityVO> getSpltyList() {
		return spltyList;
	}
	public void setSpltyList(List<UtilityVO> spltyList) {
		this.spltyList = spltyList;
	}
	public List<UtilityVO> getArticleList() {
		return articleList;
	}
	public void setArticleList(List<UtilityVO> articleList) {
		this.articleList = articleList;
	}
	public String[] getEdcReqRemarks() {
		return edcReqRemarks;
	}
	public void setEdcReqRemarks(String[] edcReqRemarks) {
		this.edcReqRemarks = edcReqRemarks;
	}
	public List<List<EdcRequestRemarksVO>> getRemarksList() {
		return remarksList;
	}
	public void setRemarksList(List<List<EdcRequestRemarksVO>> remarksList) {
		this.remarksList = remarksList;
	}
	public List<EdcRequestRemarksVO> getEdcRequestDtls() {
		return edcRequestDtls;
	}
	public void setEdcRequestDtls(List<EdcRequestRemarksVO> edcRequestDtls) {
		this.edcRequestDtls = edcRequestDtls;
	}

	public List<EmpanelHospVO> getWorkList() {
		return workList;
	}

	public void setWorkList(List<EmpanelHospVO> workList) {
		this.workList = workList;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public String getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(String rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public String getShowPage() {
		return showPage;
	}

	public void setShowPage(String showPage) {
		this.showPage = showPage;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrev() {
		return prev;
	}

	public void setPrev(String prev) {
		this.prev = prev;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}
	public List<EdcVO> getEdcCeoWorklist() {
		return edcCeoWorklist;
	}

	public void setEdcCeoWorklist(List<EdcVO> edcCeoWorklist) {
		this.edcCeoWorklist = edcCeoWorklist;
	}

	public List<EmpanelHospVO> getHospDtlsList() {
		return hospDtlsList;
	}

	public void setHospDtlsList(List<EmpanelHospVO> hospDtlsList) {
		this.hospDtlsList = hospDtlsList;
	}

	public Number getDutyDctrCnt() {
		return dutyDctrCnt;
	}

	public void setDutyDctrCnt(Number dutyDctrCnt) {
		this.dutyDctrCnt = dutyDctrCnt;
	}

	public Number getParamdcCnt() {
		return paramdcCnt;
	}

	public void setParamdcCnt(Number paramdcCnt) {
		this.paramdcCnt = paramdcCnt;
	}

	public Number getSplstCnt() {
		return splstCnt;
	}

	public void setSplstCnt(Number splstCnt) {
		this.splstCnt = splstCnt;
	}
	

}
