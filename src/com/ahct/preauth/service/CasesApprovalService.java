package com.ahct.preauth.service;
//import java.rmi.RemoteException;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;



import com.ahct.caseSearch.vo.CasesSearchVO;
//import com.ahct.preauth.vo.PastHistoryVO;
import com.ahct.common.vo.LabelBean;

public interface CasesApprovalService {
 
    //public Map getCaseDtls(HashMap lparamMap) throws RemoteException;
    //public List<PastHistoryVO> getpastHistory(String pStrRationCardNo);
    //public PastHistoryVO getHospHis(String pStrHospId);
    public List<LabelBean> getSpecialitiesOfHosp(String PStrHospId);
    public String getOnlinecaseSheetFlag(String caseId);
    public List<CasesSearchVO> getAllCaseSearchDetails(CasesSearchVO vo);
    public List<CasesSearchVO> getCaseSearchDetails(CasesSearchVO vo);
    /*
     * Used to get all the therapies of the case
     */
    public List<CasesSearchVO> getDopDtls(String caseId);
	public List<CasesSearchVO> getAadharDtlsForPen(String cardNo);
	public List<CasesSearchVO> getCardNoList(String aadharid);
	public List<CasesSearchVO> getAllCaseSearchDetailsForPen(CasesSearchVO casesSearchVO);
	public List<CasesSearchVO> getCardType(String loginName, String caseId);
	public List<CasesSearchVO> getAadharEidDtlsForPen(String cardno);
}
