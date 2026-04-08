package com.ahct.preauth.service;

import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
import java.util.List;
//import java.util.Map;

import org.apache.log4j.Logger;
//import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ahct.preauth.dao.CasesApprovalDAO;
//import com.ahct.preauth.vo.PastHistoryVO;
import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.common.vo.LabelBean;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
//import com.ahct.ehf.model.AsrimHospitals;
//import com.ahct.ehf.model.AsritEmpnHospInfo;

public class CasesApprovalServiceImpl implements CasesApprovalService{
	static final Logger logger = Logger.getLogger(CasesApprovalService.class);
	
	CasesApprovalDAO casesApprovalDAO;  
	GenericDAO genericDao;	
	HibernateTemplate hibernateTemplate;
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void setCasesApprovalDAO(CasesApprovalDAO casesApprovalDAO) {
		this.casesApprovalDAO = casesApprovalDAO;
	}


public void setGenericDao(GenericDAO genericDao) {
	this.genericDao = genericDao;
}

/*public Map getCaseDtls(HashMap lParamMap)
{
   Map lResMap=new HashMap();
  try
  {   
	  lResMap =casesApprovalDAO.getCaseDtls(lParamMap);      
  }
  catch (Exception e)
  {
	  logger.error(e);
  }
  return lResMap;
}
public List<PastHistoryVO> getpastHistory(String pStrRationCardNo)
{
	List<PastHistoryVO> lstPastHis = new ArrayList<PastHistoryVO>();
	
	  StringBuffer query = new StringBuffer();
	  
	try{
		query.append(" select ac.caseNo as caseNo , ap.firstName || ' ' || ap.middleName || ' ' || ap.lastName as name ,");
		query.append(" ( case when asu.surgDesc is null then '' else (asu.surgDesc || ' ( ' || asu.surgDispCode || ' )')  end) as therapy ,");
		query.append(" case when (ac.csDisUpdDt is not null  or ac.csCnclApvDt is not null) then 'PAST' else case  when ac.caseStatus in ('CD347', 'CD88', 'CD82') " );
		query.append(" then 'Rejected' else case when (ac.csSurgUpdDt is not null and ac.csDisUpdDt is null) or "); 
		query.append(" (ac.csApprvRejDt is not null and ac.csSurgUpdDt is null) then 'LIVE' else case when ac.csPreauthDt is not null then 'Preauth Initiated' ");
		query.append("    else ' ' end end end end as  preauthStatus ,");
		query.append(" case when (ac.csSurgUpdDt is not null and ac.csSurgUpdDt is null) then  'Waiting for Discharge' else case ");
		query.append("  when (ac.csApprvRejDt is not null and ac.csSurgUpdDt is null and ac.caseStatus not in('CD82','CD88','CD347')) then 'Waiting for Treatment' else ' ' end end as onBedStatus ,");
		query.append(" cast(nvl(acc.claimAmount, 0) as string) as  claimPaidCost, asu.surgeryAmt as pkgPrice, aco.cmbDtlName as caseStatus , ");
		query.append(" adm.disMainName as category ,ads.disName as subCat , cast(ap.regHospDate as string) as  regDt , cast(ac.phaseRenewal+1 as string) as policyPeriod ");
		
		 query.append(" from  AsritPatient ap , AsritCaseClaim acc , AsritCaseProcess acp, AsrimCombo aco ,  ");
		 query.append(" AsrimHospitals   ah, AsritCaseSurgery   acs ,AsrimSurgery  asu,  EhfmSpecialities  adm , AsrimDiseaseSub ads , EhfCase ac  ");
		// query.append("  outer join  AsritErroneousClaim ec on (ac.caseId = ec.caseId )");
		 query.append(" where ap.rationCardNo ='"+pStrRationCardNo+"' and  ac.casePatientNo = ap.patientId  and ac.caseId = acc.caseId ");
		 query.append(" and  ac.caseId = acp.caseId and ac.caseStatus = aco.id.cmbDtlId  and  ac.caseHospCode = ah.hospId and acs.caseId = ac.caseId ");
		 query.append("  and asu.surgeryId = acs.surgeryCode and  adm.disMainId = acs.disMainCode  and  ads.disSubId = acs.disSubCode ");
		 lstPastHis= genericDao.executeHQLQueryList(PastHistoryVO.class, query.toString());
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally{
		
	}
	return lstPastHis;
}

public PastHistoryVO getHospHis(String pStrHospId)
{
	PastHistoryVO pastHistoryVO = new PastHistoryVO();
	 SessionFactory factory=hibernateTemplate.getSessionFactory();
	 Session session = factory.openSession();
	 StringBuffer query = new StringBuffer();
	 List<PastHistoryVO> hospDtls = new ArrayList<PastHistoryVO>();
	try
	{
		query.append(" from AsrimHospitals ah,AsritEmpnHospInfo aeh where ah.hospEmpnlRefNo = aeh.hospInfoId  and  ah.hospId = 'HS10' " );
		 Query hospList=session.createQuery(query.toString());
         Iterator hospListItr=hospList.iterate();
         while(hospListItr.hasNext())
         {
         	Object[] pair = (Object[]) hospListItr.next();
         	AsrimHospitals asrimHospitals=(AsrimHospitals)pair[0];
         	AsritEmpnHospInfo	asritEmpnHospInfo=(AsritEmpnHospInfo)pair[1];
         	pastHistoryVO.setNwhname(asrimHospitals.getHospName());
         	pastHistoryVO.setAddress(asrimHospitals.getHospAddr1() + asrimHospitals.getHospAddr2());
         	pastHistoryVO.setContactNo(asrimHospitals.getHospContactNo());
         	pastHistoryVO.setHospBedCap(asritEmpnHospInfo.getHospBedStrength());
         }
         query = new StringBuffer();
         query.append("select  acc.caseHospCode ,  trunc(acbs.lstUpdDt) ");
         query.append("from AsritCaseBedStatus acbs, EhfCase acc where acbs.bedStatus = 'CD438' and acc.caseId = acbs.caseId and   acc.caseHospCode = '"+pStrHospId+"' and ");
         query.append(" trunc(acbs.lstUpdDt) = trunc(sysdate)  group by acc.caseHospCode, trunc(acbs.lstUpdDt) ");
         Query bedList=session.createQuery(query.toString());
         Iterator bedItr=bedList.iterate();
         int count= 0;
         while(bedItr.hasNext())
         {
        	 count++;
         }
         pastHistoryVO.setCurrBedOccu(Integer.toString(count));
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		session.close();
	}
	return pastHistoryVO;
}*/
public List<LabelBean> getSpecialitiesOfHosp(String PStrHospId)
{
	List<LabelBean>  getSpecialities = new ArrayList<LabelBean>();
	 SessionFactory factory=hibernateTemplate.getSessionFactory();
	 Session session = factory.openSession();
	 StringBuffer query = new StringBuffer();
	try{
		query.append(" select distinct adm.disMainId as ID, adm.disMainName || ' (' ||adm.disMainId||')' as VALUE , adm.disMainName as PATH");
		query.append(" from AsrimHospSpeciality ahs, EhfmSpecialities adm " );
		query.append(" where ahs.id.hospId = '"+PStrHospId+"' and ahs.id.specialityId = adm.disMainId and   adm.disActiveYN = 'Y'  order by adm.disMainName ");
		getSpecialities=  session.createQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(LabelBean.class)).list();
	}catch(Exception e)
	{
	e.printStackTrace();	
	}
	finally
	{
		session.close();
		factory.close();
	}
	return getSpecialities;
}

public String getOnlinecaseSheetFlag(String caseId)
{
	String flag="N";
	try
	  {   
		flag =casesApprovalDAO.getOnlinecaseSheetFlag(caseId);      
	  }
	  catch (Exception e)
	  {
		  logger.error(e);
	  }
	return flag;
}
public List<CasesSearchVO> getAllCaseSearchDetails(CasesSearchVO vo) {
	List<CasesSearchVO> list=casesApprovalDAO.getAllCaseSearchDetails(vo);
	return list;
}
public List<CasesSearchVO> getCaseSearchDetails(CasesSearchVO vo) {
	List<CasesSearchVO> list=casesApprovalDAO.getCaseSearchDetails(vo);
	return list;
}

/*
 * Used to get all the therapies of the case
 */
public List<CasesSearchVO> getDopDtls(String caseId)
{
	
	StringBuffer query= new StringBuffer();
	query.append(" select ect.caseId as caseId,ect.activeyn as activeyn,ect.asriCatCode as speciality, ");
	query.append(" ect.icdProcCode as procCode,etpm.id.process as process ");
	query.append(" ,ec.caseStatus as caseStatus,cast(ec.csSurgDt as string) as csSurgDt ");
	query.append(" from EhfCaseTherapy ect,EhfmTherapyProcMst etpm,EhfCase ec where ");
	query.append(" etpm.id.icdProcCode=ect.icdProcCode and ect.caseId='"+caseId+"'");
	query.append(" and ect.caseId=ec.caseId and ec.caseId='"+caseId+"'");
	query.append(" and etpm.id.state=ec.schemeId");
	List<CasesSearchVO> list=genericDao.executeHQLQueryList(CasesSearchVO.class, query.toString());
	return list;
}
@Override
public List<CasesSearchVO> getAadharDtlsForPen(String cardNo) {
	// TODO Auto-generated method stub
	StringBuffer query = new StringBuffer();
	query.append("select aadhar_id as AADHARID, count(1) as COUNT from (select distinct e.aadhar_id, e.card_no ");
	query.append("from ehf_patient e where e.aadhar_id in (select a.aadhar_id from ");
	query.append("ehf_enrollment_family a where a.ehf_card_no='"+cardNo+"'))  ");
	query.append("group by aadhar_id ");
	
	List<CasesSearchVO> list = genericDao.executeSQLQueryList(CasesSearchVO.class, query.toString());
	return list;
}
@Override
public List<CasesSearchVO> getAadharEidDtlsForPen(String cardNo) {
	// TODO Auto-generated method stub
	StringBuffer query = new StringBuffer();
	query.append("select aadhar_eid as AADHAREID, count(1) as COUNT from (select distinct e.aadhar_eid, e.card_no ");
	query.append("from ehf_patient e where e.aadhar_eid in (select a.aadhar_eid from ");
	query.append("ehf_enrollment_family a where a.ehf_card_no='"+cardNo+"'))  ");
	query.append("group by aadhar_eid ");
	
	List<CasesSearchVO> list = genericDao.executeSQLQueryList(CasesSearchVO.class, query.toString());
	return list;
}
@Override
public List<CasesSearchVO> getCardNoList(String aadharid) {
	// TODO Auto-generated method stub
	StringBuffer query  = new StringBuffer();
	query.append("select distinct e.card_no as CARDNO,e.employee_no as EMPNO from ehf_patient e where e.aadhar_id in ");
	query.append("(select a.aadhar_id from ehf_enrollment_family a where a.aadhar_id='"+aadharid+"') ");
	
	List<CasesSearchVO> list  = genericDao.executeSQLQueryList(CasesSearchVO.class, query.toString());
	
	return list;
}
@Override
public List<CasesSearchVO> getAllCaseSearchDetailsForPen(CasesSearchVO casesSearchVO) {
	List<CasesSearchVO> list=casesApprovalDAO.getAllCaseSearchDetailsForPen(casesSearchVO);
	return list;
}
@Override
public List<CasesSearchVO> getCardType(String loginName,String caseId) {
	// TODO Auto-generated method stub
	StringBuffer query = new StringBuffer();
	query.append("select distinct p.card_type as CARDTYPE,p.card_no as CARDNO,p.employee_no as EMPLOYEENO from ehf_patient p,ehf_case c ");
	query.append("where p.patient_id = c.case_patient_no and c.case_id='"+caseId+"' ");
	//query.append("and p.employee_no = '"+loginName+"' ");
	
	List<CasesSearchVO> list = genericDao.executeSQLQueryList(CasesSearchVO.class, query.toString());
	
	/*if(list!=null && list.size()>0)
	{
		cardType = list.get(0).getCARDTYPE();
	}*/
	return list;
}
}
