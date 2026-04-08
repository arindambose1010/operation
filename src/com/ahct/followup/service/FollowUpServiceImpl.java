package com.ahct.followup.service;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.CompositeConfiguration;

import com.ahct.attachments.vo.AttachmentVO;
import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.claims.util.ClaimsConstants;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.service.CommonService;
import com.ahct.common.vo.LabelBean;
import com.ahct.followup.dao.FollowUpDAO;
import com.ahct.followup.vo.FollowUpVO;
import com.ahct.model.EhfCaseFollowupClaim;
import com.ahct.model.EhfFollowUpAudit;
import com.ahct.model.EhfFollowUpAuditId;
import com.ahct.model.EhfFollowupChklst;
import com.ahct.model.EhfPatient;
import com.ahct.model.EhfFollowUpClaimAccounts;
import com.ahct.model.EhfCochlearFollowup;
import com.ahct.preauth.vo.CommonDtlsVO;
import com.ahct.preauth.vo.PatientVO;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.ahct.model.EhfCase;

public class FollowUpServiceImpl implements FollowUpService{

	FollowUpDAO followUpDao;
	GenericDAO genericDao;
	CommonService commonService;
	SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
    private static ConfigurationService configurationService;
    private static final Logger logger=Logger.getLogger(FollowUpServiceImpl.class);
	
	/** The config. */
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
	
	public FollowUpDAO getFollowUpDao() {
		return followUpDao;
	}
     public void setFollowUpDao(FollowUpDAO followUpDao) {
		this.followUpDao = followUpDao;
	}

     public void setGenericDao(GenericDAO genericDao) {
 		this.genericDao = genericDao;
 	}
     
     
   public CommonService getCommonService() {
		return commonService;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
@Override
	public String saveFollowUp(FollowUpVO followUpVo, String pStrUserId) {
	   String lStrMsg = followUpDao.saveFollowUp(followUpVo, pStrUserId);
		return lStrMsg;
	}
@Override
public FollowUpVO saveFollowUpClaim(FollowUpVO followUpVO) {
	followUpVO = followUpDao.saveFollowUpClaim(followUpVO);
	return followUpVO;
}

@Override
public FollowUpVO getListCases(FollowUpVO followUpVO) {
	FollowUpVO getListCases = followUpDao.getListCases(followUpVO);
	return getListCases;
}
public List<CommonDtlsVO> getPatientCommonDtls(String lStrCaseId,String lStrFollowUpId) {
	List<CommonDtlsVO> commonDtls =followUpDao.getPatientCommonDtls(lStrCaseId,lStrFollowUpId);
	return commonDtls;	
}
@Override
public List<FollowUpVO> getFollowUpList(String lStrCaseId,String lStrFollowUpId) {
	List<FollowUpVO> followUpList = followUpDao.getFollowUpList(lStrCaseId,lStrFollowUpId);
	return followUpList;
}
/*
 * Added by Srikalyan to get the Medco Name Based on Follow Up ID 
 */
@Override
public String getCrtUsrMedco(String followUpId){
	String medcoName="";
	try
		{
			StringBuffer query=new StringBuffer();
			query.append( " select b.firstName||' '||b.lastName as CRTUSR ");
			query.append( " from EhfClinicalFlpupMpg a , EhfmUsers b");
			query.append( " where a.id.followUpId='"+followUpId+"' ");
			query.append( " and a.crtUser=b.userId ");
			query.append( " order by a.crtDt desc ");
			List<FollowUpVO> flpLst=genericDao.executeHQLQueryList(FollowUpVO.class, query.toString());
			if(flpLst!=null && flpLst.size()>0)
				if(flpLst.get(0)!=null && flpLst.get(0).getCRTUSR()!=null)
					medcoName=flpLst.get(0).getCRTUSR();
		}
	catch(Exception e)
	{
		e.printStackTrace();
//		logger.error("Error Occured in getCrtUsrMedco method in FollowupServiceImpl class"+e.getMessage());
	}
	return medcoName;
}
@Override
public FollowUpVO getFollowUPDtls(FollowUpVO followUpVO) {
	
	//EhfClinicalFlpupMpg ehfClinicalFlpupMpg = genericDao.findById(EhfClinicalFlpupMpg.class, EhfClinicalFlpupMpgId.class,new EhfClinicalFlpupMpgId(followUpVO.getFollowUpId(),"1"));
	EhfCaseFollowupClaim ehfCaseFollowUpClaimCarry=new EhfCaseFollowupClaim(); 
	/*if(followUpVO.getFollowUpId()!=null && followUpVO.getCaseId()!=null)
		{
		
			if(followUpVO.getFollowUpId().charAt(followUpVO.getFollowUpId().length()-1)=='2')
				 ehfCaseFollowUpClaimCarry =  genericDao.findById(EhfCaseFollowupClaim.class,String.class,followUpVO.getCaseId()+"/1");
			if(followUpVO.getFollowUpId().charAt(followUpVO.getFollowUpId().length()-1)=='3')
				 ehfCaseFollowUpClaimCarry =  genericDao.findById(EhfCaseFollowupClaim.class,String.class,followUpVO.getCaseId()+"/2");
			if(followUpVO.getFollowUpId().charAt(followUpVO.getFollowUpId().length()-1)=='4')
				 ehfCaseFollowUpClaimCarry =  genericDao.findById(EhfCaseFollowupClaim.class,String.class,followUpVO.getCaseId()+"/3");
			if(ehfCaseFollowUpClaimCarry!=null)
				{
					if(ehfCaseFollowUpClaimCarry.getCarryFwdAmt()!=null)
						followUpVO.setNewClmFwdAmt(ehfCaseFollowUpClaimCarry.getCarryFwdAmt().toString());
				}	
			if(followUpVO.getFollowUpId().charAt(followUpVO.getFollowUpId().length()-1)=='1')
				followUpVO.setNewClmFwdAmt("0");
		}*/
	
	
		List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
		criteriaList.add(new GenericDAOQueryCriteria("caseId",followUpVO.getCaseId(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("crtDt",followUpVO.getCaseId(),GenericDAOQueryCriteria.CriteriaOperator.DESC));
		List<EhfCaseFollowupClaim> followUpClaimList = genericDao.findAllByCriteria(EhfCaseFollowupClaim.class, criteriaList);
			if(followUpClaimList!=null)
				{
					if(followUpClaimList.size()>0)
						{
					
									for(int i=0;i<followUpClaimList.size();i++)
									{
									
										if(followUpClaimList.get(i).getCaseFollowupId().equalsIgnoreCase(followUpVO.getFollowUpId()))
										{
											if(i==followUpClaimList.size()-1)
											{
												followUpVO.setNewClmFwdAmt("0");
											}
											else
											{
											System.out.println(followUpClaimList.get(i).getCaseFollowupId());
											System.out.println(followUpClaimList.get(i+1).getCaseFollowupId());
											System.out.println(followUpClaimList.get(i+1).getCarryFwdAmt());
											if(followUpClaimList.get(i+1).getCarryFwdAmt()!=null)
											followUpVO.setNewClmFwdAmt(followUpClaimList.get(i+1).getCarryFwdAmt().toString());
											}
										}
									}
									
								
								
							
						}
				}
	
	EhfCaseFollowupClaim ehfCaseFollowUpClaim =  genericDao.findById(EhfCaseFollowupClaim.class,String.class,followUpVO.getFollowUpId());
	if(ehfCaseFollowUpClaim!=null && ehfCaseFollowUpClaim!=null){
		followUpVO.setFollowUpDt(df1.format(ehfCaseFollowUpClaim.getCrtDt()));
		if(ehfCaseFollowUpClaim.getClaimAmount()!=null)
		followUpVO.setClaimAmt(ehfCaseFollowUpClaim.getClaimAmount().toString());
		if(ehfCaseFollowUpClaim.getActualPack()!=null)
		followUpVO.setPackAmt(ehfCaseFollowUpClaim.getActualPack().toString());
		followUpVO.setFollowUpStatus(ehfCaseFollowUpClaim.getFollowUpStatus());
		
		followUpVO.setCaseId(ehfCaseFollowUpClaim.getCaseId());
		//followUpVO.setCLINICALID(ehfClinicalFlpupMpg.getId().getClinicalId());	
		
		if(ehfCaseFollowUpClaim.getCexPharmacyBill()!=null)
		followUpVO.setClaimFcxPharmBill(ehfCaseFollowUpClaim.getCexPharmacyBill());
		if(ehfCaseFollowUpClaim.getCexConsultBill()!=null)
		followUpVO.setClaimFcxConsulBill(ehfCaseFollowUpClaim.getCexConsultBill());
		if(ehfCaseFollowUpClaim.getCexInvestBill()!=null)
		followUpVO.setClaimFcxInvestBill(ehfCaseFollowUpClaim.getCexInvestBill());
		if(ehfCaseFollowUpClaim.getClaimFcxAmount()!=null)
		followUpVO.setClaimFcxAmt(Integer.toString(ehfCaseFollowUpClaim.getClaimFcxAmount().intValue()));
		
		if(ehfCaseFollowUpClaim.getFTDPharmacyBill()!=null)
		followUpVO.setClaimFTDPharmBill(ehfCaseFollowUpClaim.getFTDPharmacyBill());
		if(ehfCaseFollowUpClaim.getFTDConsultBill()!=null)
		followUpVO.setClaimFTDConsulBill(ehfCaseFollowUpClaim.getFTDConsultBill());
		if(ehfCaseFollowUpClaim.getFTDInvestBill()!=null)
		followUpVO.setClaimFTDInvestBill(ehfCaseFollowUpClaim.getFTDInvestBill());
		
		if(ehfCaseFollowUpClaim.getCHPharmacyBill()!=null)
		followUpVO.setClaimCHPharmBill(ehfCaseFollowUpClaim.getCHPharmacyBill());
		if(ehfCaseFollowUpClaim.getCHConsultBill()!=null)
		followUpVO.setClaimCHConsulBill(ehfCaseFollowUpClaim.getCHConsultBill());
		if(ehfCaseFollowUpClaim.getCHInvestBill()!=null)
		followUpVO.setClaimCHInvestBill(ehfCaseFollowUpClaim.getCHInvestBill());
		
		if(ehfCaseFollowUpClaim.getClaimNwhAmount()!=null)
		  followUpVO.setClaimNwhAmt(ehfCaseFollowUpClaim.getClaimNwhAmount().toString());
		if(ehfCaseFollowUpClaim.getFlupClaimSubDate()!=null)
		followUpVO.setFollowUpClaimSubDt(df1.format(ehfCaseFollowUpClaim.getFlupClaimSubDate()));
		followUpVO.setMedcoRemarks(ehfCaseFollowUpClaim.getNwhRemark());
		followUpVO.setNamRemarks(ehfCaseFollowUpClaim.getNamRemark());
		if(ehfCaseFollowUpClaim.getClaimNamAmount()!=null)
		followUpVO.setClaimNamAmt(ehfCaseFollowUpClaim.getClaimNamAmount().toString());
		//if(ehfCaseFollowUpClaim.getClaimFcxAmount()!=null)
		//followUpVO.setClaimFcxAmt(ehfCaseFollowUpClaim.getClaimFcxAmount().toString());
		followUpVO.setFcxRemark(ehfCaseFollowUpClaim.getFcxRemark());
		if(ehfCaseFollowUpClaim.getClaimFtdAmount()!=null)
		followUpVO.setClaimFtdAmt(ehfCaseFollowUpClaim.getClaimFtdAmount().toString());		
		followUpVO.setFtdRmks(ehfCaseFollowUpClaim.getFtdRemark());
		if(ehfCaseFollowUpClaim.getClaimChAmount()!=null)
			followUpVO.setClaimChAmt(ehfCaseFollowUpClaim.getClaimChAmount().toString());		
		followUpVO.setChRemark(ehfCaseFollowUpClaim.getChRemark());
		if(ehfCaseFollowUpClaim.getAcoAprAmt()!=null)
			followUpVO.setAcoAprAmt(ehfCaseFollowUpClaim.getAcoAprAmt().toString());
		followUpVO.setAcoRemark(ehfCaseFollowUpClaim.getAcoRemrk());
		followUpVO.setCeoRemark(ehfCaseFollowUpClaim.getCeoRemark());
		followUpVO.setSchemeType(ehfCaseFollowUpClaim.getSchemeId());
		}				
		EhfFollowupChklst followUpchcklst = genericDao.findById(EhfFollowupChklst.class,String.class,followUpVO.getFollowUpId());		
		if(followUpchcklst!=null){
		followUpVO.setExeAcqverifyRemark(followUpchcklst.getExeAcqverifyRemark());
		followUpVO.setExeAcqverifyRemark(followUpchcklst.getExeAcqverifyRemark());
		followUpVO.setExeAcqvrifyChklst(followUpchcklst.getExeAcqvrifyChklst());
		followUpVO.setExeDisphotoChklst(followUpchcklst.getExeDisphotoChklst());
		followUpVO.setExeDisphotoremark(followUpchcklst.getExeDisphotoremark());
		followUpVO.setExeMedverifyChklst(followUpchcklst.getExeMedverifyChklst());
		followUpVO.setExeMedVerifyRemark(followUpchcklst.getExeMedVerifyRemark());
		followUpVO.setExePatphotoChklst(followUpchcklst.getExePatphotoChklst());
		followUpVO.setExePatphotoRemark(followUpchcklst.getExePatphotoRemark());
		followUpVO.setExeQuantverifyChklst(followUpchcklst.getExeQuantverifyChklst());
		followUpVO.setExeQuantverifyRemark(followUpchcklst.getExeQuantverifyRemark());
		followUpVO.setExereprtcheckChklst(followUpchcklst.getExereprtcheckChklst());
		followUpVO.setExeReprtcheckRemark(followUpchcklst.getExeReprtcheckRemark());
		followUpVO.setFtdBeneficiryChklst(followUpchcklst.getFtdBeneficiryChklst());
		followUpVO.setFtdBeneficiryRemark(followUpchcklst.getFtdBeneficiryRemark());
		followUpVO.setFtdRemarkvrifedChklst(followUpchcklst.getFtdRemarkvrifedChklst());
		followUpVO.setFtdRemarkvrifedRemark(followUpchcklst.getFtdRemarkvrifedRemark());
		}
	
	return followUpVO;
}
@Override
public List<ClaimsFlowVO> getAuditTrail(FollowUpVO followUpVO) {
	List<ClaimsFlowVO> lstWorkList = new ArrayList<ClaimsFlowVO>();
	String args[] = new String[1];
	args[0] =followUpVO.getFollowUpId();
	try{
		StringBuffer query = new StringBuffer();
		query.append(" select au.firstName ||''|| au.lastName as auditName ,a.remarks as cexRemark ,");
		query.append(" a.crtDt as fpauditDate,ac.grpShortName as auditRole,ac1.cmbDtlName as auditAction,a.apprAmt as COUNT ");
		query.append(" from EhfFollowUpAudit a , EhfmGrps ac , EhfmUsers au,EhfmCmbDtls ac1   ");
		query.append(" where a.actId=ac1.cmbDtlId and a.actBy = au.userId and ac.grpId=a.userRole ");
		//query.append(" and a.actId in ('CD62','CD63','CD64','CD65','CD66','CD67','CD68','CD69','CD70','CD104','CD105','CD106','CD107')");
		query.append(" and a.actId in ( ");
		String[] followUpStatus = ClaimsConstants.followUpStatus;
		for(int i=0;i<followUpStatus.length;i++){
			query.append(" '" + followUpStatus[i]+"' ");
			if(i!=followUpStatus.length-1)
				query.append(",");
		}
		query.append(" ) and a.id.caseFollowupId = ? ");
		query.append(" order by a.id.actOrder ");
		
		lstWorkList = genericDao.executeHQLQueryList(ClaimsFlowVO.class, query.toString(), args);
	}catch(Exception e)
	{
		//LOGGER.error("Error occured in getAuditTrail() in ClaimsFlowServiceImpl class."+e.getMessage());
		e.printStackTrace();	
		}
	return lstWorkList;
}
@Override
public String checkValidForFollowup(String lStrCaseId) {
	 String lStrMsg = followUpDao.checkValidForFollowup(lStrCaseId);
		return lStrMsg;
}
@Override
public List<AttachmentVO> getFollowUPAttach(String followUpId) {
	List<AttachmentVO> lstAttachment = new ArrayList<AttachmentVO>();
	String args[] = new String[1];
	args[0] =followUpId;
	String caseId=followUpId.substring(0, followUpId.length()-2);
	try{
		/*StringBuffer query = new StringBuffer();
		query.append(" select ats.invMainCode as caseStat,ats.invMainName as remarks,ats.invName as docType, ats.invCode as cmbprntId ,ats.invName as actualName, eft.attachTotalPath as savedName ,eft.crtDt as crtDt  ");
		query.append(" from EhfFollowupTests eft,EhfmGenInvestigationsMst ats where eft.testId =ats.invCode and eft.followupId=? ");
		lstAttachment = genericDao.executeHQLQueryList(AttachmentVO.class, query.toString(), args);*/
		
		
		StringBuffer query1 = new StringBuffer();
		query1.append(" select ats.invMainCode as caseStat,ats.invMainName as remarks,ats.invName as docType, ats.invCode as cmbprntId ,ats.invName as actualName, eft.attachTotalPath as savedName ,eft.crtDt as crtDt  ");
		query1.append(" ,eft.followupId as followUpId,substring(eft.followupId,-1,1) as inst ");
		query1.append(" from EhfFollowupTests eft,EhfmGenInvestigationsMst ats where eft.testId =ats.invCode and eft.followupId in ('"+caseId+"/1','"+caseId+"/2','"+caseId+"/3','"+caseId+"/4') ");
		lstAttachment = genericDao.executeHQLQueryList(AttachmentVO.class, query1.toString());
		
	}catch(Exception e)
	{
		e.printStackTrace();	
		}
	return lstAttachment;
}

/*
 *This method is used to get the comparative documents for the followup 
 */
@Override
public List<AttachmentVO> getFollowUPCompDocs(String lStrCaseId,String type)
	{
		List<AttachmentVO> lstDocs=new ArrayList<AttachmentVO>();
		StringBuffer sb= new StringBuffer();
		sb.append(" select efda.docSeqId as docSeqId,efda.actualName as actualName ");
		sb.append(" ,efda.savedName as savedName,efda.attachType as attachType ");
		sb.append(" ,efda.followUpId as followUpId,ecd.cmbDtlName as heading ");
		sb.append(" ,substring(efda.followUpId,-1,1) as inst");
		sb.append(" from EhfFollowupDocAttch efda,EhfmCmbDtls ecd where efda.activeYN='Y' ");
		sb.append(" and efda.followUpId in ");
		sb.append(" ('"+lStrCaseId+"/1','"+lStrCaseId+"/2','"+lStrCaseId+"/3','"+lStrCaseId+"/4') ");
		sb.append(" and efda.attachType=ecd.cmbDtlId ");
		if(type!=null)
			{
				if(!"".equalsIgnoreCase(type))
					if("compDocs".equalsIgnoreCase(type))
						sb.append(" and efda.attachType in ('CD161','CD163') ");
					else if("compPhotos".equalsIgnoreCase(type))
						sb.append(" and efda.attachType in ('CD162') ");
					else if("compDTRS".equalsIgnoreCase(type))
						sb.append(" and efda.attachType in ('CD164') ");
			}
		
		lstDocs=genericDao.executeHQLQueryList(AttachmentVO.class, sb.toString());
		return lstDocs;
	}

@Override
public List<LabelBean> getFPCasesForPayments(FollowUpVO followUpVO) {
	List<LabelBean> lstCasesForPayment = new ArrayList<LabelBean>();
	String fromDate;
	String sqlFromDate;
	String toDate;
	String sqlToDate;
	String database=config.getString("Database");
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
	SimpleDateFormat sqlf = new SimpleDateFormat("yyyy-MM-dd");
	
	try{
		StringBuffer query = new StringBuffer();
		query.append(" select distinct EC.caseNo as ID,ECF.flupClaimSubDate as DT,EC.caseId as SUBID,ECF.caseFollowupId as LEVELID,EH.hospName as LVL,ECF.totClaimAmt||'' as WFTYPE,EP.name as VALUE,EP.cardNo as UNITID, EL.locName as EMPNAME,EHB.hospAccName as SUBNAME ");
		query.append(" ,ECF.cochlearYN AS cochlearYN , (case when ECF.cochlearYN ='Y' then 'Cochlear Followup' else 'Regular Followup' end) as followUpType ");
		query.append(" from EhfCase EC,EhfmHospitals EH,EhfPatient EP,EhfmLocations EL,EhfmHospBankDtls EHB,EhfCaseFollowupClaim ECF ");
		query.append(" where EC.caseHospCode=EH.hospId and EP.patientId=EC.casePatientNo  ");
		query.append(" and EL.id.locId=EP.districtCode and EHB.id.hospId=EH.hospId and ECF.caseId=EC.caseId and EH.hospActiveYN in ('Y','S','C','D','E','CB') ");
		query.append(" and EC.schemeId=EHB.id.scheme ");
		
		if(followUpVO.getFollowUpStatus()!=null && !followUpVO.getFollowUpStatus().equalsIgnoreCase(ClaimsConstants.EMPTY))
			query.append(" and ECF.followUpStatus = '"+followUpVO.getFollowUpStatus()+"'");
		if(followUpVO.getFollowUpId()!=null && !followUpVO.getFollowUpId().equalsIgnoreCase(ClaimsConstants.EMPTY))
			query.append("  and upper(ECC.id.followUpId) like  upper('%"+followUpVO.getFollowUpId().trim()+"%') "); 
		if(followUpVO.getPatName()!=null && !followUpVO.getPatName().equalsIgnoreCase(ClaimsConstants.EMPTY))
			query.append(" and upper(EP.name) like  upper('%"+followUpVO.getPatName().trim()+"%') "); 
		if(followUpVO.getWapNo() != null && !followUpVO.getWapNo().equals(ClaimsConstants.EMPTY))
			 query.append("  and upper(EP.cardNo) like  upper('%"+followUpVO.getWapNo().trim()+"%') "); 
		if(followUpVO.getCaseId() != null && !followUpVO.getCaseId().equals(ClaimsConstants.EMPTY))
			 query.append("  and upper(EC.caseNo) like  upper('%"+followUpVO.getCaseId().trim()+"%') "); 
		if(followUpVO.getFromDate()!=null && !followUpVO.getFromDate().equals(ClaimsConstants.EMPTY) && followUpVO.getToDate()!=null && !followUpVO.getToDate().equals(ClaimsConstants.EMPTY))
		{ 
			fromDate=followUpVO.getFromDate();
			sqlFromDate=sqlf.format(sdf.parse(fromDate).getTime());
			toDate=followUpVO.getToDate().toString();
			
			//To include todate in search criteria--adding date+1 
			Calendar cal = Calendar.getInstance();  
        	cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(toDate)); 
        	cal.add(Calendar.DAY_OF_YEAR, 1); // <--  
        	Date tomorrow = cal.getTime();  
        	 String lStrToDate = new SimpleDateFormat("dd-MM-yyyy").format(tomorrow);
        	 //End of date+1 
			 
			sqlToDate=sqlf.format(sdf.parse(toDate).getTime());
			
			if(database.equalsIgnoreCase("ORACLE"))
				query.append("and ECF.crtDt between  TO_DATE('"+fromDate+"','DD-MM-YYYY') and TO_DATE('"+lStrToDate+"','DD-MM-YYYY')");
			else if(database.equalsIgnoreCase("MYSQL"))
				query.append("and ECF.crtDt between '"+sqlFromDate+"' and '"+sqlToDate+"'");
		}
		if(followUpVO.getSchemeType()!=null && !followUpVO.getSchemeType().equalsIgnoreCase("")){
			  query.append(" and ECF.schemeId in ('"+followUpVO.getSchemeType()+"') ");
		  }
		
		if(followUpVO.getCochelarSearch()!=null && "Y".equalsIgnoreCase(followUpVO.getCochelarSearch()))
			{
				query.append(" and ECF.cochlearYN='Y' ");
			}
		
		query.append(" order by ECF.flupClaimSubDate  ");
		lstCasesForPayment = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
	}catch(Exception e)
	{
		//LOGGER.error("Error occured in getAuditTrail() in ClaimsFlowServiceImpl class."+e.getMessage());
		e.printStackTrace();	
		}
	return lstCasesForPayment;
}
@Override
public List<LabelBean> getFPCaseStatus(String userName) {
	List<LabelBean> caseList = new ArrayList<LabelBean>();
	List<LabelBean> newCaseList = new ArrayList<LabelBean>();
	  
	try{
	  StringBuffer query = new StringBuffer();

	  query.append(" select a.cmbDtlId as ID , a.cmbDtlName as VALUE from EhfmCmbDtls a  where a.cmbDtlId in (");
	  String[] fpStatus = ClaimsConstants.followUpStatues;
		if(userName!=null && userName.equalsIgnoreCase("C075"))
			fpStatus =  ClaimsConstants.followUpStatuesRej;
		for (int i = 0; i < fpStatus.length; i++) {
			query.append(" '" + fpStatus[i] + "' ");
			if (i != fpStatus.length - 1)
				query.append(",");
		}
	  query.append(") order by a.cmbDtlName ");
	  caseList= genericDao.executeHQLQueryList(LabelBean.class, query.toString());
	}
	catch(Exception e)
	{
	e.printStackTrace();	
	}
	if(caseList!=null)
		{
			if(caseList.size()>0)
				{
					for(int i=0;i<caseList.size();i++)
						{
							if(caseList.get(i)!=null && caseList.get(i).getID()!=null && caseList.get(i).getVALUE()!=null )
								{
									LabelBean lb=new LabelBean();
									lb.setID(caseList.get(i).getID());
									lb.setVALUE(caseList.get(i).getVALUE());
									newCaseList.add(lb);
									if(caseList.get(i).getID().equalsIgnoreCase(config.getString("EHF.FollowUP.ClaimPaid")) ||
											caseList.get(i).getID().equalsIgnoreCase(config.getString("EHF.FollowUP.ClaimSentForPayment")))
										{
											lb=new LabelBean();
											lb.setID(config.getString("EHF.CochlearCEOID."+caseList.get(i).getID()));
											lb.setVALUE(config.getString("EHF.CochlearCEOValue."+caseList.get(i).getID()));
											newCaseList.add(lb);
										}
								}
						}
				}
		}
	
	return newCaseList;
}


@Override
public List<LabelBean> getFPCaseStatusByGrp(String grpId) {
	List<LabelBean> caseList = new ArrayList<LabelBean>();
	  
	try{
	  StringBuffer query = new StringBuffer();

	  query.append(" select a.cmbDtlId as ID , a.cmbDtlName as VALUE from EhfmCmbDtls a  where a.cmbDtlId in (");
	  String[] fpStatus = ClaimsConstants.followUpStatues;
	  if(grpId!=null && (grpId.equalsIgnoreCase(config.getString("FIN.AccountsJEOGrp"))))
			fpStatus =  ClaimsConstants.followUpStatuesRej;
		for (int i = 0; i < fpStatus.length; i++) {
			query.append(" '" + fpStatus[i] + "' ");
			if (i != fpStatus.length - 1)
				query.append(",");
		}
	  query.append(") order by a.cmbDtlName ");
	  caseList= genericDao.executeHQLQueryList(LabelBean.class, query.toString());
	}
	catch(Exception e)
	{
	e.printStackTrace();	
	}
	
	return caseList;

	

}



public List<LabelBean> getCaseStatusNew() {
	List<LabelBean> caseList = new ArrayList<LabelBean>();
	
	String[] claimStatus;
	try {
		StringBuffer query = new StringBuffer();
		query.append(" select a.cmbDtlId as ID , a.cmbDtlName as VALUE from EhfmCmbDtls a  where a.cmbDtlId in (" );
		

			claimStatus = ClaimsConstants.followUpStatuesCH;

		for (int i = 0; i < claimStatus.length; i++) {
			query.append(" '" + claimStatus[i] + "' ");
			if (i != claimStatus.length - 1)
				query.append(",");
		}
		query.append( ")");
		query.append(" order by a.cmbDtlName ");
		caseList = genericDao.executeHQLQueryList(LabelBean.class,
				query.toString());
	} catch (Exception e) {
		e.printStackTrace();
	}

	return caseList;
}


@Override
public List<LabelBean> getFPCaseStatusNew(String userGroup) {
	List<LabelBean> caseList = new ArrayList<LabelBean>();
	  
	try{
	  StringBuffer query = new StringBuffer();

	  query.append(" select a.cmbDtlId as ID , a.cmbDtlName as VALUE from EhfmCmbDtls a  where a.cmbDtlId in (");
	  String[] fpStatus = ClaimsConstants.followUpStatues;
		if(userGroup!=null && userGroup.equalsIgnoreCase("GP9"))
			fpStatus =  ClaimsConstants.followUpStatuesCH;
		for (int i = 0; i < fpStatus.length; i++) {
			query.append(" '" + fpStatus[i] + "' ");
			if (i != fpStatus.length - 1)
				query.append(",");
		}
	  query.append(") order by a.cmbDtlName ");
	  caseList= genericDao.executeHQLQueryList(LabelBean.class, query.toString());
	}
	catch(Exception e)
	{
	e.printStackTrace();	
	}
	
	return caseList;
}
@Override
public CommonDtlsVO getFPDtlsForPayment(String caseNo,String lStrFollowupId) {
	
		CommonDtlsVO followUpFlowVO = followUpDao.getFPDtlsForPayment(caseNo,lStrFollowupId);
		return followUpFlowVO;
	
}
@Override
public HashMap updateFPClaimStatus(HashMap hParamMap) throws Exception {
	hParamMap = followUpDao.updateFollowupPayments(hParamMap);
	return hParamMap;
}
@Override
public List<FollowUpVO> getClinicalData(FollowUpVO followUpVO) {
	List<FollowUpVO> listClinicalNotes = new ArrayList<FollowUpVO>();
	listClinicalNotes= followUpDao.getClinicalData(followUpVO);
	return listClinicalNotes;
}
/*@Override
public List<DrugsVO> getDrugsDtls(FollowUpVO followUpVO) {
	List<DrugsVO> drugList = followUpDao.getDrugsDtls(followUpVO);
	return drugList;
}*/
@Override
public List<LabelBean> getCaseStatus() {
	List<LabelBean> caseList = new ArrayList<LabelBean>();
	  
	try{
	  StringBuffer query = new StringBuffer();

	  query.append(" select a.cmbDtlId as ID , a.cmbDtlName as VALUE from EhfmCmbDtls a  where a.cmbHdrId in ('CH3')");
	  query.append(" order by a.cmbDtlName ");
	  caseList= genericDao.executeHQLQueryList(LabelBean.class, query.toString());
	}
	catch(Exception e)
	{
	e.printStackTrace();	
	}
	
	return caseList;
}
@Override
public PatientVO getPatientDtls(String patientId) {
	PatientVO patientVo = new PatientVO();
	if(patientId!=null||!"".equalsIgnoreCase(patientId))
	{
	EhfPatient ehfPatient = genericDao.findById(EhfPatient.class, String.class, patientId);
	if(ehfPatient!=null){
		patientVo.setContactNo(ehfPatient.getContactNo().toString());
		patientVo.setFirstName(ehfPatient.getName());
		patientVo.setEmpCode(ehfPatient.getEmployeeNo());
		patientVo.seteMailId(ehfPatient.getEmailId());
		if(ehfPatient.getGender()!=null)
			{
				if(!"".equalsIgnoreCase(ehfPatient.getGender()))
				{
					if(ehfPatient.getGender().equalsIgnoreCase("M"))
						patientVo.setGender("Male");
					else if(ehfPatient.getGender().equalsIgnoreCase("F"))
						patientVo.setGender("Female");
					else 
						patientVo.setGender(ehfPatient.getGender());
				}
			}
		else
			patientVo.setGender(ehfPatient.getGender());
		try {
			patientVo.setAddr1(ehfPatient.getHouseNo()+" "+ehfPatient.getStreet()+" "+commonService.getLocationName(ehfPatient.getDistrictCode())
					+" "+commonService.getLocationName(ehfPatient.getMandalCode())
							+" "+commonService.getLocationName(ehfPatient.getVillageCode()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	}
	return patientVo;
}
@Override
public void setviewFlag(String followUpId, String flag) {
	String msg = null;
	Date date = new Date();
	try
	{
		EhfCaseFollowupClaim caseFollowUpClaim = genericDao.findById(EhfCaseFollowupClaim.class, String.class,
				followUpId);
		if(caseFollowUpClaim != null)
		{
			caseFollowUpClaim.setViewFlag(flag);	
			caseFollowUpClaim.setCaseBlockedDt(new java.sql.Timestamp(date.getTime()));
			genericDao.save(caseFollowUpClaim);
		}
		msg ="success";
	}catch(Exception e)
	{
		msg ="failure";
		e.printStackTrace();
	}	
}
@Override
	public String checkMandatoryAttch(String pStrfollowUpId,
			String pStrattachType) {
		String msg = null;
		StringBuffer query = new StringBuffer();
		Map<String, String> attachNames = new HashMap();
		String caseStatus = "";
		try {
			int i = 0;

			EhfCaseFollowupClaim ehfCaseFollowUpClaim =  genericDao.findById(EhfCaseFollowupClaim.class,String.class,pStrfollowUpId);
			if (ehfCaseFollowUpClaim != null) {
				caseStatus = ehfCaseFollowUpClaim.getFollowUpStatus();
			}

			query.append(" select distinct au.id.docType as ID , cm.cmbDtlName as VALUE ");
			query.append(" from EhfCaseStatusAttach  ac, EhfAttachType au ,EhfmCmbDtls cm where au.activeYN ='Y' and ");
			query.append(" ac.id.attachType = au.id.docType and ac.id.caseStatus ='"
					+ caseStatus + "'  and au.docName='M' ");
			query.append(" and cm.cmbDtlId =au.id.docType and au.id.updType ='"
					+ pStrattachType + "' ");
			List<LabelBean> lstdocType = genericDao.executeHQLQueryList(
					LabelBean.class, query.toString());
			query = new StringBuffer();
			query.append(" select distinct attachType as ID");
			query.append(" from EhfFollowupDocAttch ac where ac.followUpId = '"
					+ pStrfollowUpId + "' and ac.activeYN='Y'  ");
			List<LabelBean> lstAttachType = genericDao.executeHQLQueryList(
					LabelBean.class, query.toString());
			for (LabelBean attachType : lstAttachType) {
				for (LabelBean labelBean : lstdocType) {
					if (labelBean.getID().equals(attachType.getID())) {
						i++;
						attachNames
								.put(labelBean.getID(), labelBean.getVALUE());
					}
				}
			}

			if (i < lstdocType.size()) {
				for (LabelBean labelBean : lstdocType) {
					if (!attachNames.containsKey(labelBean.getID())) {
						if (msg == null || msg.equals(""))
							msg = labelBean.getVALUE();
						else
							msg = msg + " , " + labelBean.getVALUE();
						System.out.println(labelBean.getID() + " : "
								+ labelBean.getVALUE());
					}

				}
				if (msg == null || msg.equals("")) {
					msg = "Please add mandatory attachments";
				} else {
					if (pStrattachType != null
							&& pStrattachType.equalsIgnoreCase("ehfClaim"))
						msg = "Please add " + msg;
					else
						msg = "Please add " + msg + "  attachments ";
				}

			} else {
				msg = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	public String getPatientIdFromCaseId(String caseId)
	{
		String patId=null;
		String query="select p.patientId as ID from EhfCase c,EhfPatient p where c.caseId='"+caseId+"' and c.casePatientNo=p.patientId";
		try
		{
			List<LabelBean> list= genericDao.executeHQLQueryList(LabelBean.class, query);
			if(list!=null && !list.isEmpty())
			{
				patId=list.get(0).getID();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return patId;
	}
	public LabelBean getDTRSData(String caseId)
	{
		List<LabelBean> list=null;
		LabelBean lb=new LabelBean();
		String[] args={caseId};
		String query="select p.cardNo as ID,p.name as VALUE,p.gender as CONST,p.crtDt||'' as LVL,c.caseId as WFTYPE,c.claimNo as UNITID,p.age||'' as EMPID,c.caseId||'' as EMPNAME from EhfCase c,EhfPatient p where c.casePatientNo=p.patientId and c.caseId=?";
		try
		{
			list=genericDao.executeHQLQueryList(LabelBean.class, query,args);
			if(list!=null && !list.isEmpty())
				{
					if(list.get(0)!=null)
						{
							lb=list.get(0);
							if(list.get(0).getCONST()!=null)
								{
									if(!"".equalsIgnoreCase(list.get(0).getCONST()))
										{
											if(list.get(0).getCONST().equalsIgnoreCase("M"))
												lb.setCONST("Male");
											else if(list.get(0).getCONST().equalsIgnoreCase("F"))
												lb.setCONST("Female");
										}
								}
						}
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(lb!=null)
			return lb;
		else
			return null;
	}
	@Override
	public Long getNextFollowUpId(FollowUpVO followUpVo) {
		Long followUpSeq = followUpDao.getNextFollowUpId(followUpVo);
		return followUpSeq;
	}
	@Override
	public String updateFPClaimStatusReady(ClaimsFlowVO claimFlowVO) {
		String result = followUpDao.updateFPClaimStatusReady(claimFlowVO);
		return result;
	}
	@Override
	public List<String> getDrugSubList(String drugTypeCode) {
		List<LabelBean> drugSubList=null;
		List<String> drugSubList1 = new ArrayList<String>();
		try
		{
			drugSubList=followUpDao.getDrugSubList(drugTypeCode);
			 for (LabelBean labelBean: drugSubList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (drugSubList1 != null && drugSubList1.size() > 0) {
	                    	drugSubList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	drugSubList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return drugSubList1;
	}
	@Override
	public String getDrugDetails(String drugCode) {
		String drugDetails=null;
		try
		{
			drugDetails=followUpDao.getDrugDetails(drugCode);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return drugDetails;
	}
	@Override
	public List<String> getInvestigations(String lStrBlockId) {
		List<String> mainInvestList=null;
		try
		{
			mainInvestList=followUpDao.getInvestigations(lStrBlockId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return mainInvestList;
	}
	
	/*
	 * This method is used to retrieve the patient photo
	 * based upon the caseId.
	 */
	@Override
	public String getPatientPhoto(String caseId)
		{
			String photo=null;
			photo=followUpDao.getPatientPhoto(caseId);
			return photo; 
		}
	/*
	 * This method is used to get the all the range for next followup for a case
	 */
	@Override
	public long getFollowUpLst(String caseId)
		{
			long daysDiff=0l;
			daysDiff=followUpDao.getFollowUpLst(caseId);
			return daysDiff;
		}
		
	/*
	 * This method is used to get the next follow up date
	 */
	@Override
	public Date getNxtFollowUpDt(String caseId,String offset)
		{
			Date nxtFollowUpDate;
			nxtFollowUpDate=followUpDao.getNxtFollowUpDt(caseId,offset);
			return nxtFollowUpDate;
		}
	/*
	 * This method is used to get the Approval Cases for follopwUp without Pagination
	 */
	@Override
	public List<CasesSearchVO> getAllListCases(FollowUpVO followUpVO)
		{
			FollowUpVO followUPVO=new FollowUpVO();
			List<CasesSearchVO> lstCaseSearch=followUpDao.getAllListCases(followUpVO);
			return lstCaseSearch;
		}
	public FollowUpVO getFollowupDtls(FollowUpVO followUpVO) {
String followupId=followUpVO.getFollowUpId();
FollowUpVO followupDtls=new FollowUpVO();
EhfCaseFollowupClaim ehfCaseFollowupClaim=new EhfCaseFollowupClaim(); 
if(followupId!=null)
ehfCaseFollowupClaim=genericDao.findById(EhfCaseFollowupClaim.class,String.class,followupId);

if(ehfCaseFollowupClaim!=null)
{
	followupDtls.setClaimCHPharmBill(ehfCaseFollowupClaim.getFTDPharmacyBill());
	followupDtls.setClaimCHConsulBill(ehfCaseFollowupClaim.getFTDConsultBill());
	followupDtls.setClaimCHInvestBill(ehfCaseFollowupClaim.getFTDInvestBill());
	if(ehfCaseFollowupClaim.getClaimFtdAmount()!=null)
	followupDtls.setClaimChAmt(ehfCaseFollowupClaim.getClaimFtdAmount().toString());
}
	return	followupDtls;
	}
	
	/*
	 * Added by Srikalyan for Saving
	 * Cochlear FollowUp Details
	 */
	@Override
	public FollowUpVO saveCochlearFollowUpDetails(FollowUpVO followUpVO)
		{
			FollowUpVO followUpVOFinal=new FollowUpVO();
			String result1="Failed",result2="Failed",patScheme=null,schemeId=null;
			EhfCochlearFollowup ehfCochlearFollowup=new EhfCochlearFollowup();
			int cochlearCount=followUpVO.getCochlearCount();
			DateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			String caseId=null;
			String cochlearFollowUpId=null;
			if(followUpVO.getCaseId()!=null)
				caseId=followUpVO.getCaseId();
			Long actualPackamt=0L;
			followUpVOFinal.setMsg("Failed");
			String initialMapping=followUpVO.getInitialMapping();
			if(initialMapping==null)
				initialMapping="N";
			
			EhfCase ehfCase=getCaseDtls(caseId);
			if(ehfCase!=null)
				{
					if(ehfCase.getPatientScheme()!=null)
						patScheme=ehfCase.getPatientScheme();
					if(ehfCase.getSchemeId()!=null)
						schemeId=ehfCase.getSchemeId();
				}
			
			try
				{
					if(caseId!=null)
						{
							StringBuffer query=new StringBuffer();
							query.append( " select ect.icdProcCode as procCode " );
							query.append( " from EhfCaseTherapy ect where ect.caseId='"+caseId+"' " );
							List<FollowUpVO> csTherLst=followUpDao.executeNormalQuery(query.toString());
							String procCode=null;
							for(FollowUpVO flp:csTherLst)
								{
									if(flp.getProcCode()!=null && procCode!=null)
										procCode=procCode+"~"+flp.getProcCode();
									else if(flp.getProcCode()!=null)
										procCode="~"+flp.getProcCode();
								}
							procCode=procCode+"~";
						if(procCode.contains("~"+config.getString("Cochlear_FollowUp_ProcCode")+"~"))
							{
								if(cochlearCount==0 ||"Y".equalsIgnoreCase(initialMapping))
									{
										String surgeryId=config.getString("Cochlear_FollowUp_ProcCode"),icdProcCode=null;
										FollowUpVO returnVO=getCochFlpProcCode(surgeryId,schemeId,0);
										if(returnVO!=null)
											{
												if(returnVO.getProcCode()!=null)
													icdProcCode=returnVO.getProcCode();
												if(returnVO.getPackageAmt()!=null)
													actualPackamt=returnVO.getPackageAmt().longValue();
											}
									
										cochlearFollowUpId=caseId+"/CF";
										ehfCochlearFollowup.setCochlearFollowupId(cochlearFollowUpId);
										ehfCochlearFollowup.setCaseId(caseId);
										ehfCochlearFollowup.setPatientScheme(patScheme);
										if(followUpVO.getPostOP()!=null)
											ehfCochlearFollowup.setPostopPeriod(followUpVO.getPostOP());
										if(followUpVO.getPostOPRemarks()!=null)
											ehfCochlearFollowup.setPostopRemarks(followUpVO.getPostOPRemarks());
										if(followUpVO.getWoundSight()!=null)
											ehfCochlearFollowup.setWoundSight(followUpVO.getWoundSight());
										if(followUpVO.getSwitchOnDate()!=null)
											ehfCochlearFollowup.setDateOfSwitchOn(sdf.parse(followUpVO.getSwitchOnDate()));
										if(followUpVO.getAudiologist()!=null)
											ehfCochlearFollowup.setAudiologist(followUpVO.getAudiologist());
										
										ehfCochlearFollowup.setCrtDt(new Date());
										ehfCochlearFollowup.setCochlearFollowupCount("0");
										ehfCochlearFollowup.setFollowupType(config.getString("initailMapping"));
										ehfCochlearFollowup.setFollowupProc(icdProcCode);
										ehfCochlearFollowup.setSchemeId(schemeId);
										if(followUpVO.getCRTUSR()!=null)
											ehfCochlearFollowup.setCrtUsr(followUpVO.getCRTUSR());
										
										result1=followUpDao.saveCochlearFollowUpDetails(ehfCochlearFollowup);
										
										followUpVOFinal.setReturnMsg(config.getString("Cochlear_initial_Mapping_Success"));
									}
								else if(cochlearCount==1 || cochlearCount==2 ||
										cochlearCount==3 || cochlearCount==4 || cochlearCount==5 )
									{
										if(followUpVO.getPrevFollowUp()!=null)
											{
											int j=0;
												if("Y".equalsIgnoreCase(followUpVO.getPrevFollowUp()))
													{
														//for(int i=followUpVO.getToStartFUS();i<cochlearCount;i++)
														for(int i=followUpVO.getToStartFUS();i<=followUpVO.getCurrentNum();i++)
															{
																if(i==0)
																	continue;
																EhfCochlearFollowup ehfCochlearFollowuploc=new EhfCochlearFollowup();
																
																String surgeryId=config.getString("Cochlear_FollowUp_ProcCode"),icdProcCode=null;
																FollowUpVO returnVO=getCochFlpProcCode(surgeryId,schemeId,i);
																if(returnVO!=null)
																	{
																		if(returnVO.getProcCode()!=null)
																			icdProcCode=returnVO.getProcCode();
																		if(returnVO.getPackageAmt()!=null)
																			actualPackamt=returnVO.getPackageAmt().longValue();
																	}
																
																cochlearFollowUpId=caseId+"/CF"+i;
																ehfCochlearFollowuploc.setPatientScheme(patScheme);
																ehfCochlearFollowuploc.setCochlearFollowupId(cochlearFollowUpId);
																ehfCochlearFollowuploc.setCaseId(caseId);
																if(followUpVO.getFromDatePrev()!=null)
																	if(followUpVO.getFromDatePrev()[j]!=null)
																		ehfCochlearFollowuploc.setAvTheraphyFromDate(sdf.parse(followUpVO.getFromDatePrev()[j]));	
																if(followUpVO.getToDatePrev()!=null)
																	if(followUpVO.getToDatePrev()[j]!=null)
																		ehfCochlearFollowuploc.setAvTheraphyToDate(sdf.parse(followUpVO.getToDatePrev()[j]));
																if(followUpVO.getReviewDatePrev()!=null)
																	if(followUpVO.getReviewDatePrev()[j]!=null)
																		ehfCochlearFollowuploc.setReviewDate(sdf.parse(followUpVO.getReviewDatePrev()[j]));
																if(followUpVO.getChildProgressRemarksPrev()!=null)
																	if(followUpVO.getChildProgressRemarksPrev()[j]!=null)
																		ehfCochlearFollowuploc.setChildProgressRemarks(followUpVO.getChildProgressRemarksPrev()[j]);
																if(followUpVO.getAudiologistNamePrev()!=null)
																	if(followUpVO.getAudiologistNamePrev()[j]!=null)
																		ehfCochlearFollowuploc.setAvtheraphyAudiologist(followUpVO.getAudiologistNamePrev()[j]);
																
																ehfCochlearFollowuploc.setCrtDt(new Date());
																ehfCochlearFollowuploc.setCochlearFollowupCount(Integer.toString(i));
																if( i > 0 && i <5)
																	{
																		String followUpType=null;
																		followUpType=config.getString("AVTheraphy"+i);
																		ehfCochlearFollowuploc.setFollowupType(followUpType);
																	}
																if(followUpVO.getCRTUSR()!=null)
																	ehfCochlearFollowuploc.setCrtUsr(followUpVO.getCRTUSR());
																
																ehfCochlearFollowuploc.setFollowupProc(icdProcCode);
																ehfCochlearFollowuploc.setSchemeId(schemeId);
																
																result1=followUpDao.saveCochlearFollowUpDetails(ehfCochlearFollowuploc);
																j++;
																
																String  quarter=null;
																switch(i)
																	{
																		case 1:		quarter="First";
																					break;
																		case 2:		quarter="Second";
																					break;
																		case 3:		quarter="Third";
																					break;
																		case 4:		quarter="Fourth";
																					break;
																	}
																
																followUpVOFinal.setReturnMsg("Audio Verbal Therapy for "+quarter+" Quarter has been initiated successfully.");
															}
														
													}
											}
										if(followUpVO.getPrevFollowUp()==null)
											{
												String surgeryId=config.getString("Cochlear_FollowUp_ProcCode"),icdProcCode=null;
												FollowUpVO returnVO=getCochFlpProcCode(surgeryId,schemeId,cochlearCount);
												if(returnVO!=null)
													{
														if(returnVO.getProcCode()!=null)
															icdProcCode=returnVO.getProcCode();
														if(returnVO.getPackageAmt()!=null)
															actualPackamt=returnVO.getPackageAmt().longValue();
													}
												
												cochlearFollowUpId=caseId+"/CF"+cochlearCount;
												ehfCochlearFollowup.setCochlearFollowupId(cochlearFollowUpId);
												ehfCochlearFollowup.setCaseId(caseId);
												ehfCochlearFollowup.setPatientScheme(patScheme);
												if(followUpVO.getFromDate()!=null)
													ehfCochlearFollowup.setAvTheraphyFromDate(sdf.parse(followUpVO.getFromDate()));
												if(followUpVO.getToDate()!=null)
													ehfCochlearFollowup.setAvTheraphyToDate(sdf.parse(followUpVO.getToDate()));
												if(followUpVO.getChildProgressRemarks()!=null)
													ehfCochlearFollowup.setChildProgressRemarks(followUpVO.getChildProgressRemarks().toUpperCase());
												if(followUpVO.getReviewDate()!=null)
													ehfCochlearFollowup.setReviewDate(sdf.parse(followUpVO.getReviewDate()));
												if(followUpVO.getAudiologistName()!=null)
													ehfCochlearFollowup.setAvtheraphyAudiologist(followUpVO.getAudiologistName().toUpperCase());
												
												ehfCochlearFollowup.setCrtDt(new Date());
												ehfCochlearFollowup.setCochlearFollowupCount(Integer.toString(cochlearCount));
												if( cochlearCount > 0 && cochlearCount <5)
													{
														String followUpType=null;
														followUpType=config.getString("AVTheraphy"+cochlearCount);
														ehfCochlearFollowup.setFollowupType(followUpType);
													}
												if(followUpVO.getCRTUSR()!=null)
													ehfCochlearFollowup.setCrtUsr(followUpVO.getCRTUSR().toUpperCase());
												ehfCochlearFollowup.setFollowupProc(icdProcCode);
												ehfCochlearFollowup.setSchemeId(schemeId);
												
												result1=followUpDao.saveCochlearFollowUpDetails(ehfCochlearFollowup);
												
											}	
									}
								if(result1!=null)
									{
										if(!"".equalsIgnoreCase(result1))
											{
												if(result1.equalsIgnoreCase("Success"))
													{
														EhfCaseFollowupClaim ehfCaseFollowupClaim=genericDao.findById(EhfCaseFollowupClaim.class,String.class,cochlearFollowUpId);
														if(ehfCaseFollowupClaim==null)
															{
																ehfCaseFollowupClaim=new EhfCaseFollowupClaim();
																ehfCaseFollowupClaim.setCaseFollowupId(cochlearFollowUpId);
																ehfCaseFollowupClaim.setCaseId(caseId);
																ehfCaseFollowupClaim.setPatientScheme(patScheme);
																ehfCaseFollowupClaim.setFollowUpStatus(config.getString("Cochlear_FollowUp_Done"));
																ehfCaseFollowupClaim.setClaimAmount((double) 0);
																ehfCaseFollowupClaim.setActualPack((long) actualPackamt);
																ehfCaseFollowupClaim.setCochlearYN("Y");
																ehfCaseFollowupClaim.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
																if(followUpVO.getCRTUSR()!=null)
																	ehfCaseFollowupClaim.setCrtUsr(followUpVO.getCRTUSR());
															
																ehfCaseFollowupClaim.setSchemeId(schemeId);
																result2=followUpDao.saveCochCaseClaim(ehfCaseFollowupClaim);
															}
													}
											}
									}
								if(result2!=null)
									{
										if(!"".equalsIgnoreCase(result2))
											followUpVOFinal.setMsg(result2);
										else
											followUpVOFinal.setMsg(result1);
									}
								else
									followUpVOFinal.setMsg(result1);
							
							}
					  }
					if(cochlearFollowUpId != null)
					followUpVOFinal.setCochFollowUpId(cochlearFollowUpId);
				}
			catch(Exception e)
				{
					followUpVOFinal.setMsg("Failed");
					followUpVOFinal.setCochFollowUpId("");
					e.printStackTrace();
//					logger.error("Exception Occured in saveCochlearFollowUpDetails of FollowUpServiceImpl "+e.getMessage());
				}
			return followUpVOFinal;
		}
	
	/*
	 * Added by Srikalyan for getting
	 * Cochlear FollowUp Code
	 */
	@Override
	public FollowUpVO getCochFlpProcCode(String surgeryId,String schemeId,int cochlearCount)
		{
			FollowUpVO returnVO=new FollowUpVO();
			StringBuffer query=new StringBuffer();
			query.append( " select ecf.id.icdCodeProcFp as procCode , packageAmt as packageAmt " );
			query.append( " from EhfmCochFollowupPackage ecf" );
			query.append( " where ecf.id.cochlearSurgeryId = '"+surgeryId+"' " );
			query.append( " and ecf.id.schemeId = '"+schemeId+"' " );
			query.append( " and ecf.activeYn ='Y' and ecf.effEndDt is null ");
			query.append( " and ecf.cochlearCount = '"+cochlearCount+"' " );
			List<FollowUpVO> flpLst=followUpDao.executeNormalQuery(query.toString());
			if(flpLst!=null)
				{
					if(flpLst.size()>0)
						{
							if(flpLst.get(0)!=null)
								{	
									returnVO=flpLst.get(0);
								}
						}
				}
			
			return returnVO;
		}
	
	/*
	 * Added by Srikalyan for getting
	 * Cochlear FollowUp Details
	 */
	@Override
	public List<FollowUpVO> getCochlearDetails(String caseId,int cochlearCount)
		{
			List<FollowUpVO> followUpVOLst=new ArrayList<FollowUpVO>();
			try
				{
					followUpVOLst=followUpDao.getCochlearDetails(caseId,cochlearCount);
				}
			catch(Exception e)
				{
					e.printStackTrace();
//					logger.error("Exception Occured in getCochlearDetails of FollowUpServiceImpl "+e.getMessage());
				}
			return followUpVOLst;
		}
	
	/*
	 * Added by Srikalyan for getting
	 * Case Details
	 */
	@Override
	public EhfCase getCaseDtls(String caseId)
		{
			EhfCase ehfCase=new EhfCase();
			try
				{
					ehfCase=followUpDao.getCaseDtls(caseId);
				}
			catch(Exception e)
				{
					e.printStackTrace();
//					logger.error("Exception Occured in getCaseDtls of FollowUpServiceImpl "+e.getMessage());
				}
			return ehfCase;
		}
	
	
	 
	/*
	 * Added by Srikalyan for get FollowUp Claim cases 
	 */
	 @Override
	 public FollowUpVO getCochlearClaimCases(FollowUpVO followUpVO)
	 	{
		 	List<FollowUpVO> flpLst=new ArrayList<FollowUpVO>();
		 	FollowUpVO flpVO=new FollowUpVO();
		 	String isMithra="N",isMedco="N",userId=null;
		 	int startIndex=0,maxResults=10,pageId=1,totalPages=0,totalRecords=0;
		 	StringBuffer followUpStatus=new StringBuffer();
		 	String casesAppr=followUpVO.getCasesForApprovalFlag();
		 	if(casesAppr==null || "".equalsIgnoreCase(casesAppr))
		 		casesAppr="N";
		 	
		 	String pendingFlag=followUpVO.getPendingFlag();
		 	
		 	userId=followUpVO.getUserId();
		 	
		 	if(followUpVO.getGrpList()!=null)
		 		{
		 			if(followUpVO.getGrpList().size()>0)
		 				{
		 					for(LabelBean label:followUpVO.getGrpList())
		 						{
		 							if(label!=null)
		 								{
		 									if(label.getID()!=null && !"".equalsIgnoreCase(label.getID()) && (pendingFlag == null || (pendingFlag !=null && !("Y").equalsIgnoreCase(pendingFlag))))
		 										{
		 											if(config.getString("Cochlear_"+label.getID()+"_Status")!=null)
		 												{
		 													if(label.getID().equalsIgnoreCase(config.getString("MITHRA_GRP")))
		 														isMithra="Y";
		 													if(label.getID().equalsIgnoreCase(config.getString("MEDCO_GRP")))
		 														isMedco="Y";
		 													
		 													String status=config.getString("Cochlear_"+label.getID()+"_Status");
		 													String[] statusArr=status.split("~");
		 													for(String statusL :statusArr)
		 														{
		 															followUpStatus.append("'");
		 															followUpStatus.append(statusL);
		 															followUpStatus.append("',");
		 														}
		 													if(label.getID().equalsIgnoreCase(config.getString("DYEO_GRP")) && followUpVO.getPatientScheme()!=null 
		 															&& followUpVO.getPatientScheme().equalsIgnoreCase(config.getString("TG")))
		 														{
		 															String sentBckStatus=config.getString("CochlearTDSB");
		 															if(sentBckStatus!=null && !"".equalsIgnoreCase(sentBckStatus))
		 																{
			 																followUpStatus.append("'");
				 															followUpStatus.append(sentBckStatus);
				 															followUpStatus.append("',");
		 																}
		 														}
		 												}
		 										}
		 									else if(label.getID()!=null && !"".equalsIgnoreCase(label.getID()) && (pendingFlag != null && ("Y").equalsIgnoreCase(pendingFlag)))
		 										{
			 										if(config.getString("Cochlear_"+label.getID()+"_Status")!=null)
		 												{
		 													if(label.getID().equalsIgnoreCase(config.getString("MITHRA_GRP")))
		 														isMithra="Y";
		 													if(label.getID().equalsIgnoreCase(config.getString("MEDCO_GRP")))
		 														isMedco="Y";
		 													
		 													String status=config.getString("Cochlear_"+label.getID()+"_SENT_Status");
		 													String[] statusArr=status.split("~");
		 													for(String statusL :statusArr)
		 														{
		 															followUpStatus.append("'");
		 															followUpStatus.append(statusL);
		 															followUpStatus.append("',");
		 														}
		 												}
		 										}
		 								}
		 						}
		 				}
		 		}
		 	String finalStatus=followUpStatus.toString();
		 	if(finalStatus.length()>0)
		 		{
		 			finalStatus=finalStatus.substring(0,finalStatus.lastIndexOf(","));
		 		}
		 	try
		 		{
		 			StringBuffer query=new StringBuffer();
		 			StringBuffer query1=new StringBuffer();
		 			StringBuffer query2=new StringBuffer();
		 			query2.append(" select count (*) as COUNT");
		 			query1.append(" select a.caseFollowupId as followUpId , a.caseId as caseId , ");
		 			query1.append(" b.caseNo as caseNo, c.cardNo as cardNo , c.name as patName , ");
		 			query1.append(" d.cmbDtlName as followUpStatus , to_char(e.crtDt , 'dd/MM/yyyy HH12:MI:SS AM') as dateStr , ");
		 			query1.append(" to_char(a.flupClaimSubDate , 'dd/MM/yyyy HH12:MI:SS AM') as auditDate ,");
		 			query1.append(" to_char(nvl(a.claimAmount,0)) as claimAmt , j.hospName as hospName , ");
		 			query1.append(" h.locName as district , i.locName as hospCity , " );
		 			query1.append(" case when j.hospType = 'C' then 'Corporate' when j.hospType = 'G' then 'Government'  " );
		 			query1.append(" else 'NA' end as name " );
		 			
		 			query.append(" from EhfCaseFollowupClaim a,EhfCase b,EhfPatient c, ");
		 			query.append(" EhfmCmbDtls d,EhfCochlearFollowup e , EhfmLocations h , EhfmLocations i , ");
		 			query.append(" EhfmHospitals j ");
		 			
		 			if(isMithra!=null || isMedco!=null)
	 					{
	 						if(isMithra.equalsIgnoreCase("Y"))
	 							query.append(" ,EhfmHospMithraDtls f ");
	 						else if(isMedco.equalsIgnoreCase("Y"))
	 							query.append(" ,EhfmMedcoDtls g ");
	 					}
		 			query.append(" where a.caseId=b.caseId and b.casePatientNo=c.patientId ");
		 			query.append(" and a.followUpStatus=d.cmbDtlId and a.caseFollowupId=e.cochlearFollowupId ");
		 			query.append(" and j.hospId = b.caseHospCode and h.id.locId = c.districtCode "  );
		 			query.append(" and i.id.locId = j.hospDist "  );
		 			
		 			if("Y".equalsIgnoreCase(casesAppr))
		 				{
		 					
		 					if((followUpVO.getFollowUpStatus()!=null && !"".equalsIgnoreCase(followUpVO.getFollowUpStatus())) && finalStatus!=null)
		 						{
		 							if(finalStatus.contains("'"+followUpVO.getFollowUpStatus()+"'"))
	 									query.append(" and a.followUpStatus in ('"+followUpVO.getFollowUpStatus()+"') ");		
		 							else
	 									query.append(" and a.followUpStatus in ('') ");
		 						}
		 					else if((followUpVO.getFollowUpStatus()==null || "".equalsIgnoreCase(followUpVO.getFollowUpStatus())) && finalStatus!=null)
	 							query.append(" and a.followUpStatus in ("+finalStatus+") ");
		 					else
		 						query.append(" and a.followUpStatus in ('') ");	
				 			
		 				}
		 			else
		 				{
			 				if(followUpVO.getFollowUpStatus()!=null && !"".equalsIgnoreCase(followUpVO.getFollowUpStatus()))
				 				query.append(" and a.followUpStatus in ('"+followUpVO.getFollowUpStatus()+"')");
		 				}
		 			
		 			if(followUpVO.getCaseNo()!=null && !"".equalsIgnoreCase(followUpVO.getCaseNo()))
		 				query.append(" and upper(b.caseNo) like '%"+followUpVO.getCaseNo().toUpperCase()+"%' ");
		 			
		 			if(followUpVO.getFollowUpId()!=null && !"".equalsIgnoreCase(followUpVO.getFollowUpId()))
		 				query.append(" and upper(a.caseFollowupId) like '%"+followUpVO.getFollowUpId().toUpperCase()+"%' ");
		 			
		 			if(followUpVO.getPatName()!=null && !"".equalsIgnoreCase(followUpVO.getPatName()))
		 				query.append(" and upper(trim(c.name)) like trim('%"+followUpVO.getPatName().toUpperCase()+"%') ");
		 			
		 			if(followUpVO.getFromDate()!=null && !"".equalsIgnoreCase(followUpVO.getFromDate()) &&
		 					followUpVO.getToDate()!=null && !"".equalsIgnoreCase(followUpVO.getToDate()))
		 				{
		 					query.append(" and e.crtDt between to_date('"+followUpVO.getFromDate()+"','DD/MM/YYYY' ) ");
		 					query.append(" and to_date('"+followUpVO.getToDate()+"','DD/MM/YYYY') ");
		 				}
		 			
		 			if(followUpVO.getCardNo()!=null && !"".equalsIgnoreCase(followUpVO.getCardNo()))
		 				query.append(" and upper(c.cardNo) like ('%"+followUpVO.getCardNo().toUpperCase()+"%') ");
		 			
		 			if(followUpVO.getPatientScheme()!=null && !"".equalsIgnoreCase(followUpVO.getPatientScheme()))
		 				query.append(" and a.schemeId ='"+followUpVO.getPatientScheme()+"' ");
		 			
		 			if(isMithra!=null || isMedco!=null)
 						{
		 					if(isMithra.equalsIgnoreCase("Y"))
		 						{
		 							query.append(" and f.id.mithraId='"+userId+"' and f.id.hospId = b.caseHospCode ");
		 							query.append(" and f.activeYN='Y' and f.endDt is null ");
		 						}
		 					else if(isMedco.equalsIgnoreCase("Y"))
		 						{
		 							query.append(" and g.id.medcoId='"+userId+"' and g.id.hospId = b.caseHospCode ");
		 							query.append(" and g.activeYN='Y' and g.endDate is null");
		 						}
		 				}
		 			
		 			if(followUpVO.getUserScheme()!=null)
		 				{
		 					if(followUpVO.getUserScheme().equalsIgnoreCase(config.getString("AP")) ||
		 								followUpVO.getUserScheme().equalsIgnoreCase(config.getString("TG")))
		 						{
		 							query.append(" and b.schemeId in ('"+followUpVO.getUserScheme()+"') ");
		 							if(pendingFlag != null && ("Y").equalsIgnoreCase(pendingFlag))
		 				 				query.append(" and a.pendingWith in ('"+followUpVO.getUserId()+"') ");
		 							else
		 								query.append(" and a.pendingWith is null ");
		 						}
		 					else if(followUpVO.getUserScheme().equalsIgnoreCase(config.getString("COMMON")))
		 						{
		 							if(pendingFlag != null && ("Y").equalsIgnoreCase(pendingFlag))
		 								{
		 									query.append(" and (  (b.schemeId in ('"+config.getString("AP")+"') and a.pendingWith in ('"+followUpVO.getUserId()+"'))  ");
		 									query.append(" or     (b.schemeId in ('"+config.getString("TG")+"') ) ");
		 									query.append("     ) " );
		 									
		 								}
		 							else
		 								{
		 									query.append(" and b.schemeId in ('"+config.getString("AP")+"' , '"+config.getString("TG")+"') ");
		 									query.append(" and a.pendingWith is null ");
		 								}
		 							
		 						}
		 				}
		 			else
		 				{
		 					query.append(" and b.schemeId in ('') ");
		 					query.append(" and a.pendingWith is null ");
		 				}
		 			
		 			query.append(" order by a.flupClaimSubDate ");
		 			
		 			if(followUpVO.getCsvFlag()!=null && "Y".equalsIgnoreCase(followUpVO.getCsvFlag()))
		 				{
		 					flpLst=followUpDao.executeQuery((query1.append(query)).toString(),0,0);
		 					flpVO.setClaimList(flpLst);
		 				 	return flpVO;
		 				}
		 			
		 			flpLst=followUpDao.executeQuery((query2.append(query)).toString(),startIndex,maxResults);
		 			
		 			if(flpLst!=null)
		 				{
		 					if(flpLst.size()>0)
		 						{
				 					if(flpLst.get(0)!=null)
				 						{
				 							if(flpLst.get(0).getCOUNT().intValue()!=0)
				 								{
				 									totalRecords=flpLst.get(0).getCOUNT().intValue();
					 								if(followUpVO.getMaxresults()==0)
					 									maxResults=10;
					 								else
					 									maxResults=followUpVO.getMaxresults();
					 								
					 								if(followUpVO.getPageId()==0)
					 									{
					 										pageId=1;
					 										startIndex=0;
					 									}
					 								else
					 									{
					 										pageId=followUpVO.getPageId();
					 										startIndex=(pageId-1) * (maxResults);
					 									}
					 								if(totalRecords%maxResults==0)
					 									totalPages=(totalRecords/maxResults);
					 								else
					 									totalPages=(totalRecords/maxResults)+1;
					 									
					 								flpLst=new ArrayList<FollowUpVO>();
					 								
					 								flpLst=followUpDao.executeQuery((query1.append(query)).toString(),startIndex,maxResults);
					 								
				 								}
				 							else
				 								flpLst=null;
				 						}
				 					else
		 								flpLst=null;
				 					
		 						}
		 					else
 								flpLst=null;
		 				}
		 			else
						flpLst=null;
		 		}
		 	catch(Exception e)
		 		{
		 			e.printStackTrace();
//		 			logger.error("Exception Occured in getCochlearClaimCases of FollowUpServiceImpl "+e.getMessage());
		 		}
		 	
		 	flpVO.setTotalPages(totalPages);
		 	flpVO.setTotalRecords(totalRecords);
		 	flpVO.setPageId(pageId);
		 	flpVO.setMaxresults(maxResults);
		 	flpVO.setStrtIndex(startIndex);
		 	flpVO.setClaimList(flpLst);
		 	
		 	return flpVO;
	 	}

	 /*
	 * Added by Srikalyan for get FollowUp Claim case Details
	 */
	 @Override
	public FollowUpVO getCochlearFlpCase(String caseId,String followUpId)
		{
			FollowUpVO flpVO=new FollowUpVO();
			List<FollowUpVO> flpLst=null;
			StringBuffer query=new StringBuffer();
			try
				{
					query.append(" select b.name as patName,b.cardNo as cardNo,c.hospName as hospName,c.hospCity as hospCity,");
					query.append(" a.caseId as caseId,e.catName as catName,f.cochlearFollowupId as followUpId, ");
					query.append(" f.cochlearFollowupCount as cochlearCountStr , m.procName as procCode ,");
					query.append(" h.cmbDtlName as followUpStatus,a.claimNo as claimNo,i.locName as district, ");
					query.append(" j.locName as mandal,k.locName as village,b.contactNo as contactNo, ");
					query.append(" to_char(a.csSurgUpdDt,'dd/MM/yyyy') as csSurgDt,to_char(a.csDisUpdDt,'dd/MM/yyyy') as csDischDt, ");
					query.append(" to_char(a.csAdmDt,'dd/MM/yyyy') as csAdmDt,g.followUpStatus as statusId,b.patientIpop as patientIpop, ");
					query.append(" (case when b.gender='M' then 'Male' when b.gender='F' then 'Female' else 'NA' end) as gender,");
					query.append(" (b.age||' Years,'||b.ageMonths||' Months,'||b.ageDays||' Days') as age, ");
					//Current Followup Details
					query.append( " f.avtheraphyAudiologist as audiologistName, to_char(f.avTheraphyFromDate,'dd/MM/yyyy') as fromDate ,  " );
					query.append( " to_char(f.avTheraphyToDate , 'dd/MM/yyyy') as toDate , " );
					query.append( " f.childProgressRemarks as childProgressRemarks , to_char(f.reviewDate , 'dd/MM/yyyy' ) as reviewDate , " );
					query.append( " f.postopPeriod as postOP , f.postopRemarks as postOPRemarks , f.woundSight as woundSight , " );
					query.append( " f.audiologist as audiologist ,  to_char(f.dateOfSwitchOn , 'dd/MM/yyyy') as switchOnDate  " );
					
					query.append(" ,to_char(a.caseRegnDate,'dd/MM/yyyy HH12:mm:ss AM') as ROUTE ");
					//Medco Fields
					query.append(" ,to_char(nvl(g.claimNwhAmount,0)) as claimNWHAmtStr,g.nwhRemark as medcoRemarks ");
					//Mithra Fields
					query.append(" ,g.namRemark as namRemarks ");
					//Coc-Cmt Fields
					query.append(" ,to_char(nvl(g.claimCocCmtAmt,0)) as claimCocCmtAmtStr,g.coccmtRemark as coccmtRemark");
					//DY EO Fields
					query.append(" ,to_char(nvl(g.claimDyEoAmt,0)) as claimDyEoAmtStr,g.dyeoRemark as dyeoRemark");
					//ACO Fields
					query.append(" ,to_char(nvl(g.acoAprAmt,0)) as acoAprAmt,g.acoRemrk as acoRemark");
					
					query.append(" from EhfCase a,EhfPatient b,EhfmHospitals c,EhfPatientHospDiagnosis d, ");
					query.append(" EhfmDiagCategoryMst e,EhfCochlearFollowup f,EhfCaseFollowupClaim g, ");
					query.append(" EhfmCmbDtls h,EhfmLocations i,EhfmLocations j,EhfmLocations k, ");
					query.append(" EhfmCochFollowupPackage l , EhfmTherapyProcMst m ");
					query.append(" where a.casePatientNo=b.patientId and a.caseHospCode=c.hospId ");
					query.append(" and l.id.icdCodeProcFp = f.followupProc and f.cochlearFollowupCount = l.cochlearCount ");
					query.append(" and l.id.schemeId = g.schemeId and l.activeYn='Y' " );
					query.append(" and m.id.icdProcCode = l.id.cochlearSurgeryId and l.id.schemeId = m.id.state ");
					query.append(" and m.activeYN='Y' and b.patientId=d.patientId and e.id.catCode=d.categoryCode ");
					query.append(" and f.caseId=a.caseId and g.caseId=a.caseId and f.cochlearFollowupId=g.caseFollowupId ");
					query.append(" and h.cmbDtlId=g.followUpStatus and i.id.locId=b.districtCode ");
					query.append(" and j.id.locId=b.mandalCode and k.id.locId=b.villageCode ");
					query.append(" and a.caseId='"+caseId+"' and f.cochlearFollowupId='"+followUpId+"'");
					
					flpLst=followUpDao.executeNormalQuery(query.toString());
				}
			catch(Exception e)
				{
					e.printStackTrace();
//					logger.error("Exception Occured in getCochlearFlpCase of FollowUpServiceImpl "+e.getMessage());
				}
			
			
			if(flpLst!=null)
				{
					if(flpLst.size()>0)
						{
							if(flpLst.get(0)!=null)
								{
									flpVO=flpLst.get(0);
								}
						}
				}
			
			return flpVO;
			
		}
	/*
	 * Added by Srikalyan to get previous Cochlear 
	 * FollowUp Claim case Details
	 */
	@Override
	 public List<FollowUpVO> getPrevCochlearFlpDtls(String caseId,String followUpId)
	 	{
		 	List<FollowUpVO> flpLst=null;
		 	StringBuffer query=new StringBuffer();
		 	try
		 		{
				 	query.append(" select a.cochlearFollowupId as followUpId,b.actualPack as actualPack, ");
				 	query.append(" to_char(b.claimAmount) as claimAmt,c.cmbDtlName as followUpStatus,d.cmbDtlName as followUptype, ");
				 	query.append(" b.followUpStatus as statusId,b.caseId as caseId  ");
				 	query.append(" from EhfCochlearFollowup a,EhfCaseFollowupClaim b,EhfmCmbDtls c,EhfmCmbDtls d");
				 	query.append(" where a.cochlearFollowupId=b.caseFollowupId and ");
				 	query.append(" d.cmbDtlId=a.followupType and c.cmbDtlId=b.followUpStatus");
				 	query.append(" and a.caseId='"+caseId+"' ");
				 	query.append(" order by a.crtDt,a.cochlearFollowupCount ");
		 		}
		 	catch(Exception e)
		 		{
			 		e.printStackTrace();
//					logger.error("Exception Occured in getPrevCochlearFlpDtls of FollowUpServiceImpl "+e.getMessage());
		 		}
		 	
		 	flpLst=followUpDao.executeNormalQuery(query.toString());
		 	
		 	return flpLst;
	 	}

	 /*
	 * Added by Srikalyan to get Common Cochlear 
	 * FollowUp Claim case Details
	 */
	 @Override
	 public FollowUpVO getCommonDtls(String caseId,String followUpId)
	 	{
		 	List<FollowUpVO> flpLst=null;
		 	FollowUpVO flpVO=null;
		 	StringBuffer query=new StringBuffer();
		 	try
		 		{
		 			query.append(" select a.cochlearFollowupId as followUpId ,to_char(a.crtDt , 'dd/MM/yyyy HH12:MI:SS AM') as dateStr ,");
		 			query.append(" b.actualPack as actualPack ");
		 			query.append(" from EhfCochlearFollowup a , EhfCaseFollowupClaim b");
		 			query.append(" where a.cochlearFollowupId=b.caseFollowupId ");
		 			query.append(" and a.cochlearFollowupId ='"+followUpId+"' and a.caseId='"+caseId+"' ");
		 			
		 		}
		 	catch(Exception e)
	 		{
		 		e.printStackTrace();
//				logger.error("Exception Occured in getPrevCochlearFlpDtls of FollowUpServiceImpl "+e.getMessage());
	 		}
	 	
	 	flpLst=followUpDao.executeNormalQuery(query.toString());
	 	
	 	if(flpLst!=null)
	 		{
	 			if(flpLst.size()>0)
	 				{
	 					if(flpLst.get(0)!=null)
	 						flpVO=flpLst.get(0);
	 				}
	 		}
	 	
	 	return flpVO;
	 	}
	 
	 /*
		 * Added by Srikalyan to Save Cochlear 
		 * FollowUp Claim for Send Back Cases 
		 */
		 public FollowUpVO saveCochlearFlpClaimSendBck(FollowUpVO followUpVO)
			 {
			 	String followUpId=followUpVO.getFollowUpId();
			 	String caseId=followUpVO.getCaseId();
			 	String userGrp=followUpVO.getUserGrp();
			 	String submitButton=followUpVO.getSubmitButton();
			 	String userId=followUpVO.getUserId();
			 	String nextStatus=null;
			 	double apprvAmt=0l;
			 	FollowUpVO flpVO=new FollowUpVO();
			 	flpVO.setStatusId("Failed");
			 	String msg="",smsMsg="",result=null,dataInsufficiency="N";
				 try
			 		{
					 	EhfCaseFollowupClaim ehfCaseFollowUpClaim=new EhfCaseFollowupClaim();
			 			ehfCaseFollowUpClaim=followUpDao.getCaseClaim(followUpId);
			 			if(ehfCaseFollowUpClaim==null)//Case Claim Object should be created at the time of Cochlear Follow up Submission
			 				{
			 					flpVO.setStatusId("Failed");
			 					flpVO.setMsg(config.getString("NoSubmitAuthority"));
			 					return flpVO;
			 				}
			 			
			 			if(ehfCaseFollowUpClaim.getFollowUpStatus() != null && followUpVO.getFollowUpStatus() != null)//Incase Case status has been Changed in the process of Approval
			 				{
			 					if(!ehfCaseFollowUpClaim.getFollowUpStatus().equalsIgnoreCase(followUpVO.getFollowUpStatus()))
			 						{
			 							flpVO.setStatusId("Failed");
			 							flpVO.setMsg(config.getString("CaseAlreadyUpdated"));
			 							return flpVO;
			 						}
			 				}
			 			
			 			if (followUpVO.getFollowUpId() != null && ehfCaseFollowUpClaim.getFollowUpStatus() != null) //Next WorkFlow Status
			 				{
			 					nextStatus = followUpDao.getNextStatus(ehfCaseFollowUpClaim.getFollowUpStatus(),userGrp, submitButton);
			 					
			 					if(nextStatus==null || "".equalsIgnoreCase(nextStatus))
			 						{
				 						flpVO.setStatusId("Failed");
			 							flpVO.setMsg(config.getString("NoSubmitAuthority"));
			 							return flpVO;
			 						}
			 				}
			 			
			 			if(ehfCaseFollowUpClaim!=null && userGrp!=null)
		 					{
				 				ehfCaseFollowUpClaim.setFollowUpStatus(nextStatus);
				 				ehfCaseFollowUpClaim.setLstUpdDt(new Date());
					 			ehfCaseFollowUpClaim.setLstUpdUsr(userId);
				 				ehfCaseFollowUpClaim.setPendingWith("");
				 				ehfCaseFollowUpClaim.setSentbackUsrRemrks(followUpVO.getSendBackRmrks());
				 				if(userGrp.equalsIgnoreCase(config.getString("DYEO_GRP")))
				 					{
				 						apprvAmt=followUpVO.getClaimDyEoAmt();
				 						ehfCaseFollowUpClaim.setSentbackUsrRemrks("");
				 						ehfCaseFollowUpClaim.setClaimDyEoAmt(followUpVO.getClaimDyEoAmt());
				 						ehfCaseFollowUpClaim.setDyeoRemark(followUpVO.getDyeoRemark());
				 						ehfCaseFollowUpClaim.setTotClaimAmt(followUpVO.getClaimDyEoAmt());
				 					}
		 					}
			 			StringBuffer query=new StringBuffer();
			 			long actOrder=0;
			 			query.append(" select nvl(max(ef.id.actOrder),0) as actOrder from EhfFollowUpAudit ef where ef.id.caseFollowupId='"+followUpId+"' ");
			 			List<FollowUpVO> flpLst=followUpDao.executeNormalQuery(query.toString());//Getting Action order for current Approval
			 			
			 			if(flpLst!=null)
			 				if(flpLst.size()>0)
			 					if(flpLst.get(0)!=null)
		 							actOrder=flpLst.get(0).getActOrder()+1;
			 			EhfFollowUpAudit folowUpAudit = new EhfFollowUpAudit();
			 			
			 			EhfFollowUpAuditId ehfFollowUpAuditId=new EhfFollowUpAuditId();
			 			ehfFollowUpAuditId.setCaseFollowupId(followUpId);
			 			ehfFollowUpAuditId.setActOrder(actOrder);
			 			ehfFollowUpAuditId.setCaseType(config.getString("CochlearFlpAuditType"));
			 			
			 			folowUpAudit.setId(ehfFollowUpAuditId);
			 			folowUpAudit.setActBy(userId);
			 			folowUpAudit.setActId(nextStatus);
			 			folowUpAudit.setApprAmt(apprvAmt);
			 			folowUpAudit.setCrtDt(new Date());
			 			folowUpAudit.setCrtUsr(userId);
			 			folowUpAudit.setLangId(ClaimsConstants.LANG_ID);
			 			folowUpAudit.setRemarks(followUpVO.getSendBackRmrks());
			 			if(userGrp!=null && userGrp.equalsIgnoreCase(config.getString("DYEO_GRP")))
		 					{
			 					folowUpAudit.setRemarks(followUpVO.getDyeoRemark());
		 					}
			 			folowUpAudit.setUserRole(userGrp);
			 			
			 			result="";
			 			HashMap<String,Object> saveObjMap=new HashMap<String,Object>();
			 			
			 			saveObjMap.put("caseClaimObj", ehfCaseFollowUpClaim);//Saving Case Claim Object 
			 			result=followUpDao.saveObj(saveObjMap,"caseClaimObj");
			 			if(result==null || (result!=null && !"Success".equalsIgnoreCase(result)))
			 				{
			 					flpVO.setStatusId("Failed");
								flpVO.setMsg(config.getString("techError"));
								return flpVO;
			 				}
			 			
			 			saveObjMap=new HashMap<String,Object>();
			 			saveObjMap.put("flpAuditObj", folowUpAudit);		//Saving Audit Object
			 			result=followUpDao.saveObj(saveObjMap,"flpAuditObj");
			 			if(result==null || (result!=null && !"Success".equalsIgnoreCase(result)))
			 				{
			 					flpVO.setStatusId("Failed");
								flpVO.setMsg(config.getString("techError"));
								return flpVO;
			 				}
			 			msg=config.getString(nextStatus+"_AlertMsg");
			 			flpVO.setMsg(msg);
			 			flpVO.setSmsMsg(smsMsg);
			 		}
				 catch(Exception e)
			 		{
			 			e.printStackTrace();
//			 			logger.error("Exception Occured in saveCochlearFlpClaimSendBck of FollowUpServiceImpl "+e.getMessage());
			 			flpVO.setStatusId("Failed");
						flpVO.setMsg(config.getString("techError"));//Unexpected Errors handled with generalised Message 
			 			
			 		}
			 	return flpVO;
				 
			 }
	 
	 /*
	 * Added by Srikalyan to Save Cochlear 
	 * FollowUp Claim for Each Level in Workflow
	 */
	 @Override
	 @Transactional
	 public FollowUpVO saveCochlearFlpClaim(FollowUpVO followUpVO)
	 	{
		 	String followUpId=followUpVO.getFollowUpId();
		 	String caseId=followUpVO.getCaseId();
		 	//String casesForAppr=followUpVO.getCasesForApprovalFlag();
		 	String userGrp=followUpVO.getUserGrp();
		 	String submitButton=followUpVO.getSubmitButton();
		 	String userId=followUpVO.getUserId();
		 	String nextStatus=null,remarks=null;
		 	double apprvAmt=0l;
		 	FollowUpVO flpVO=new FollowUpVO();
		 	flpVO.setStatusId("Failed");
		 	String msg="",smsMsg="",result=null,dataInsufficiency="N";
		 	try
		 		{
		 	
		 			EhfCaseFollowupClaim ehfCaseFollowUpClaim=new EhfCaseFollowupClaim();
		 			ehfCaseFollowUpClaim=followUpDao.getCaseClaim(followUpId);
		 			if(ehfCaseFollowUpClaim==null)//Case Claim Object should be created at the time of Cochlear Follow up Submission
		 				{
		 					flpVO.setStatusId("Failed");
		 					flpVO.setMsg(config.getString("NoSubmitAuthority"));
		 					return flpVO;
		 				}
		 			
		 			if(ehfCaseFollowUpClaim.getFollowUpStatus() != null && followUpVO.getFollowUpStatus() != null)//Incase Case status has been Changed in the process of Approval
		 				{
		 					if(!ehfCaseFollowUpClaim.getFollowUpStatus().equalsIgnoreCase(followUpVO.getFollowUpStatus()))
		 						{
		 							flpVO.setStatusId("Failed");
		 							flpVO.setMsg(config.getString("CaseAlreadyUpdated"));
		 							return flpVO;
		 						}
		 				}
		 			
		 			if (followUpVO.getFollowUpId() != null && ehfCaseFollowUpClaim.getFollowUpStatus() != null) //Next WorkFlow Status
		 				{
		 					nextStatus = followUpDao.getNextStatus(ehfCaseFollowUpClaim.getFollowUpStatus(),userGrp, submitButton);
		 					
		 					if(nextStatus==null || "".equalsIgnoreCase(nextStatus))
		 						{
			 						flpVO.setStatusId("Failed");
		 							flpVO.setMsg(config.getString("NoSubmitAuthority"));
		 							return flpVO;
		 						}
		 				}
		 			StringBuffer query=new StringBuffer();//To get Patient Id and Hospital Name  for Sending SMS 
		 			query.append(" select a.hospName as hospName , b.casePatientNo as patientId ");
		 			query.append(" from EhfmHospitals a,EhfCase b where ");
		 			query.append(" a.hospId=b.caseHospCode and b.caseId='"+caseId+"' ");
		 			List<FollowUpVO> flpLst=followUpDao.executeNormalQuery(query.toString());
		 			String hospName="NA",patientId=null;
		 			
		 			if(flpLst!=null)
		 				if(flpLst.size()>0)
		 					if(flpLst.get(0)!=null)
		 						{
		 							if(flpLst.get(0).getHospName()!=null)
		 								hospName=flpLst.get(0).getHospName();//Used to get Hospital Name incase of SMS
		 							if(flpLst.get(0).getPatientId()!=null)
		 								patientId=flpLst.get(0).getPatientId();//Used to get Patient Id incase of SMS
		 						}	
		 			
		 			if(ehfCaseFollowUpClaim!=null && userGrp!=null)
		 				{
				 			if(userGrp.equalsIgnoreCase(config.getString("MEDCO_GRP")))//If the User is Medco
					 			{
				 					if(followUpVO.getClaimNwhAmt()!=null && followUpVO.getMedcoRemarks()!=null)
				 						{
					 						String medcoClaimAmount=followUpVO.getClaimNwhAmt();
								 			remarks=followUpVO.getMedcoRemarks();
						 					apprvAmt=Double.parseDouble(medcoClaimAmount);
						 					ehfCaseFollowUpClaim.setClaimNwhAmount(Double.parseDouble(medcoClaimAmount));
						 					ehfCaseFollowUpClaim.setNwhRemark(remarks);
						 					if(nextStatus.equalsIgnoreCase(config.getString("CochlearFollowupClaimInit")))
						 						ehfCaseFollowUpClaim.setFlupClaimSubDate(new java.sql.Timestamp(new Date().getTime()));
						 					
						 					msg=config.getString(nextStatus+"_AlertMsg");
						 					if(config.getString("cochlearSMSStatus").contains("~"+nextStatus+"~"))
						 						smsMsg=config.getString(nextStatus+"_AlertMsg")+" for Cochlear FollowUp Id :"+followUpId+" in Hospital named:"+hospName+"";
				 						}
				 					else
				 						dataInsufficiency="Y";
					 			}
				 			else if(userGrp.equalsIgnoreCase(config.getString("MITHRA_GRP")))//If the User is Mithra
				 				{
				 					if(followUpVO.getNamRemarks()!=null)
				 						{
						 					remarks=followUpVO.getNamRemarks();
						 					apprvAmt=ehfCaseFollowUpClaim.getClaimNwhAmount();
						 					ehfCaseFollowUpClaim.setClaimNamAmount(apprvAmt);
						 					ehfCaseFollowUpClaim.setNamRemark(remarks);
						 					
						 					msg=config.getString(nextStatus+"_AlertMsg");
				 						}
				 					else
				 						dataInsufficiency="Y";
				 				}
				 			else if(userGrp.equalsIgnoreCase(config.getString("COCHCOMT_GRP")))//If the User is Cochlear Committee
			 					{
					 				if(followUpVO.getCoccmtRemark()!=null)
				 						{
						 					remarks=followUpVO.getCoccmtRemark();
						 					apprvAmt=followUpVO.getClaimCocCmtAmt();
						 					ehfCaseFollowUpClaim.setClaimCocCmtAmt(apprvAmt);
						 					ehfCaseFollowUpClaim.setCoccmtRemark(remarks);
						 					/*if(nextStatus.equalsIgnoreCase(config.getString("CochlearCommitteePending")))
						 						ehfCaseFollowUpClaim.setPendingBy(userGrp);*/	
						 					
						 					msg=config.getString(nextStatus+"_AlertMsg");
						 					
				 						}
					 				else
				 						dataInsufficiency="Y";
			 					}
				 			else if(userGrp.equalsIgnoreCase(config.getString("DYEO_GRP")))//If the User is DY EO
			 					{
					 				if(followUpVO.getDyeoRemark()!=null)
				 						{
						 					remarks=followUpVO.getDyeoRemark();
						 					apprvAmt=followUpVO.getClaimDyEoAmt();
						 					ehfCaseFollowUpClaim.setClaimDyEoAmt(apprvAmt);
						 					ehfCaseFollowUpClaim.setDyeoRemark(remarks);
						 					//Total Claim Amount
						 					ehfCaseFollowUpClaim.setTotClaimAmt(apprvAmt);
						 					/*if(nextStatus.equalsIgnoreCase(config.getString("CochlearDyEoPending")))
						 						ehfCaseFollowUpClaim.setPendingBy(userGrp);*/
						 					
						 					msg=config.getString(nextStatus+"_AlertMsg");
						 					smsMsg=config.getString(nextStatus+"_AlertMsg")+" for Cochlear FollowUp Id :"+followUpId+" in Hospital named:"+hospName+"";
						 					
				 						}
					 				else
				 						dataInsufficiency="Y";
			 					}
				 			else if(userGrp.equalsIgnoreCase(config.getString("ACO_GRP")))//If the User is ACO
		 						{
				 					if(followUpVO.getAcoRemark()!=null)
				 						{
				 							remarks=followUpVO.getAcoRemark();
				 							apprvAmt=ehfCaseFollowUpClaim.getClaimDyEoAmt();
				 							
				 							ehfCaseFollowUpClaim.setAcoAprAmt(apprvAmt);
				 							ehfCaseFollowUpClaim.setAcoRemrk(remarks);
				 							//Total Claim Amount
				 							//ehfCaseFollowUpClaim.setTotClaimAmt(apprvAmt);
				 							//Claim Paid--Commented as CEO should Perform this 
				 							//ehfCaseFollowUpClaim.setClaimAmount((apprvAmt));
				 							
				 							msg=config.getString(nextStatus+"_AlertMsg");
						 					//smsMsg=config.getString(nextStatus+"_AlertMsg")+" for Cochlear FollowUp Id :"+followUpId+" in Hospital named:"+hospName+"";
				 						}
		 						}
				 			else if(userGrp.equalsIgnoreCase(config.getString("Payments_GRP")))//If the User is CEO
		 						{
					 				if(followUpVO.getCeoRemark()!=null)
				 						{
					 						remarks=followUpVO.getCeoRemark();
					 						apprvAmt=0;
					 						//Claim Paid
					 						ehfCaseFollowUpClaim.setClaimAmount(apprvAmt);
					 						ehfCaseFollowUpClaim.setCeoRemark(remarks);
					 						
					 						msg=config.getString(nextStatus+"_AlertMsg");
				 						}
		 						}
				 			if(dataInsufficiency.equalsIgnoreCase("Y"))//If Incase the Data from Front end Misses.
				 				{
				 					flpVO.setStatusId("Failed");
									flpVO.setMsg(config.getString("techDataError"));
									return flpVO;
				 				}	
				 			ehfCaseFollowUpClaim.setFollowUpStatus(nextStatus);
				 			ehfCaseFollowUpClaim.setCaseBlockedDt(new java.sql.Timestamp(new Date().getTime()));
				 			ehfCaseFollowUpClaim.setViewFlag("N");
				 			ehfCaseFollowUpClaim.setLstUpdDt(new Date());
				 			ehfCaseFollowUpClaim.setLstUpdUsr(userId);
				 		}	
		 			else   					//If the User Grp or Previous Data Misses.
		 				{
			 				flpVO.setStatusId("Failed");
							flpVO.setMsg(config.getString("techDataError"));
							return flpVO;
		 				}
		 			followUpVO.setMsg(msg);
		 			followUpVO.setSmsMsg(smsMsg);
		 			query=new StringBuffer();
		 			flpLst=null;
		 			long actOrder=0;
		 			query.append(" select nvl(max(ef.id.actOrder),0) as actOrder from EhfFollowUpAudit ef where ef.id.caseFollowupId='"+followUpId+"' ");
		 			flpLst=followUpDao.executeNormalQuery(query.toString());//Getting Action order for current Approval
		 			
		 			if(flpLst!=null)
		 				if(flpLst.size()>0)
		 					if(flpLst.get(0)!=null)
	 							actOrder=flpLst.get(0).getActOrder()+1;
		 			
		 			//Common Audit saving for every Logon. 
		 			EhfFollowUpAudit folowUpAudit = new EhfFollowUpAudit();
		 			
		 			EhfFollowUpAuditId ehfFollowUpAuditId=new EhfFollowUpAuditId();
		 			ehfFollowUpAuditId.setCaseFollowupId(followUpId);
		 			ehfFollowUpAuditId.setActOrder(actOrder);
		 			ehfFollowUpAuditId.setCaseType(config.getString("CochlearFlpAuditType"));
		 			
		 			folowUpAudit.setId(ehfFollowUpAuditId);
		 			folowUpAudit.setActBy(userId);
		 			folowUpAudit.setActId(nextStatus);
		 			folowUpAudit.setApprAmt(apprvAmt);
		 			folowUpAudit.setCrtDt(new Date());
		 			folowUpAudit.setCrtUsr(userId);
		 			folowUpAudit.setLangId(ClaimsConstants.LANG_ID);
		 			folowUpAudit.setRemarks(remarks);
		 			folowUpAudit.setUserRole(userGrp);
		 			
		 			result="";
		 			HashMap<String,Object> saveObjMap=new HashMap<String,Object>();
		 			
		 			saveObjMap.put("caseClaimObj", ehfCaseFollowUpClaim);//Saving Case Claim Object 
		 			result=followUpDao.saveObj(saveObjMap,"caseClaimObj");
		 			if(result==null || (result!=null && !"Success".equalsIgnoreCase(result)))
		 				{
		 					flpVO.setStatusId("Failed");
							flpVO.setMsg(config.getString("techError"));
							return flpVO;
		 				}
		 			
		 			saveObjMap=new HashMap<String,Object>();
		 			saveObjMap.put("flpAuditObj", folowUpAudit);//Saving Audit Object
		 			result=followUpDao.saveObj(saveObjMap,"flpAuditObj");
		 			if(result==null || (result!=null && !"Success".equalsIgnoreCase(result)))
		 				{
		 					flpVO.setStatusId("Failed");
							flpVO.setMsg(config.getString("techError"));
							return flpVO;
		 				}
		 			if(userGrp.equalsIgnoreCase(config.getString("DYEO_GRP")))//If the User is Cochlear Trust Doctor
						{
		 					
		 					if(!nextStatus.equalsIgnoreCase(config.getString("CochlearDyeoRejected")) 
		 							&& !nextStatus.equalsIgnoreCase(config.getString("CochlearDyEoPending")))
		 							{
					 					if(followUpVO.getDyeoRemark()!=null)
					 						{
					 							EhfFollowUpClaimAccounts ehfFollowUpClaimAccounts=genericDao.findById(EhfFollowUpClaimAccounts.class,String.class,followUpId);
					 							
					 							if(ehfFollowUpClaimAccounts==null)
					 								{
					 									ehfFollowUpClaimAccounts=new EhfFollowUpClaimAccounts();
						 								ehfFollowUpClaimAccounts.setCaseFollowUpId(followUpId);
							 							ehfFollowUpClaimAccounts.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
							 							ehfFollowUpClaimAccounts.setCrtUsr(userId);
							 							ehfFollowUpClaimAccounts.setTimeMilSec((long) 0);
							 							ehfFollowUpClaimAccounts.setTransStatus(ClaimsConstants.TransReadyStatus);	
					 								}
					 							else
					 								{
						 								ehfFollowUpClaimAccounts.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
							 							ehfFollowUpClaimAccounts.setLstUpdUsr(userId);
					 								}
					 							ehfFollowUpClaimAccounts.setAprvdAmt(apprvAmt);
					 							ehfFollowUpClaimAccounts.setRemarks(remarks);
					 							
					 							saveObjMap=new HashMap<String,Object>();
					 				 			saveObjMap.put("flpClmActsObj", ehfFollowUpClaimAccounts);//Saving Claim Accounts Object
					 				 			result=followUpDao.saveObj(saveObjMap,"flpClmActsObj");
					 				 			if(result==null || (result!=null && !"Success".equalsIgnoreCase(result)))
						 			 				{
						 			 					flpVO.setStatusId("Failed");
						 								flpVO.setMsg(config.getString("techError"));
						 								return flpVO;
						 			 				}
					 						}
		 							}
		 					
						}
		 			/*if(userGrp.equalsIgnoreCase(config.getString("ACO_GRP")))//If the User is ACO
					{
	 					if(followUpVO.getAcoRemark()!=null)
	 						{
	 							EhfFollowUpClaimAccounts ehfFollowUpClaimAccounts=new EhfFollowUpClaimAccounts();
	 							ehfFollowUpClaimAccounts.setCaseFollowUpId(followUpId);
	 							ehfFollowUpClaimAccounts.setAprvdAmt(apprvAmt);
	 							ehfFollowUpClaimAccounts.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
	 							ehfFollowUpClaimAccounts.setCrtUsr(userId);
	 							ehfFollowUpClaimAccounts.setTimeMilSec((long) 0);
	 							ehfFollowUpClaimAccounts.setRemarks(remarks);
	 							ehfFollowUpClaimAccounts.setTransStatus(ClaimsConstants.TransReadyStatus);
	 							
	 							saveObjMap=new HashMap<String,Object>();
	 				 			saveObjMap.put("flpClmActsObj", ehfFollowUpClaimAccounts);//Saving Claim Accounts Object
	 				 			result=followUpDao.saveObj(saveObjMap,"flpClmActsObj");
	 				 			if(result==null || (result!=null && !"Success".equalsIgnoreCase(result)))
		 			 				{
		 			 					flpVO.setStatusId("Failed");
		 								flpVO.setMsg(config.getString("techError"));
		 								return flpVO;
		 			 				}
	 						}
					}*/
		 			flpVO.setMsg(msg);
		 			flpVO.setSmsMsg(smsMsg);
		 			
		 			if(result==null || result.equalsIgnoreCase("Success"))
		 				flpVO.setStatusId("Success");
		 			
		 			//In Case of Sending SMS for only specific Actions and specific users
		 			if(flpVO.getStatusId()!=null && msg!=null && smsMsg!=null && nextStatus!=null)
		 				{
		 					if("Success".equalsIgnoreCase(flpVO.getStatusId()))
		 						{
									String checkSMSStat="~"+nextStatus+"~";
		 							if(config.getString("cochlearSMSStatus").contains(checkSMSStat))
		 								flpVO.setSendSms("True");
		 									
		 							flpVO.setPatientId(patientId);//In Case of Sending SMS
		 						}
		 				}
		 			
		 			
		 		}
		 	catch(Exception e)
		 		{
		 			e.printStackTrace();
//		 			logger.error("Exception Occured in saveCochlearFlpClaim of FollowUpServiceImpl "+e.getMessage());
		 			flpVO.setStatusId("Failed");
					flpVO.setMsg(config.getString("techError"));//Unexpected Errors handled with generalised Message 
		 			
		 		}
		 	return flpVO;
				 	
	 	}
	 
	 /*
	 * Added by Srikalyan to Get Cochlear 
	 * FollowUp Claim Details for Each Level in Workflow
	 */
	 @Override
	 public List<FollowUpVO> getCochlearClaimWorkFlow(String caseId,String followUpId)
		 {
		 	List<FollowUpVO> workFlow=new ArrayList<FollowUpVO>();
		 	try
		 		{
				 	StringBuffer query=new StringBuffer();
				 	query.append(" select a.remarks as remarks , a.id.caseType as caseType , ");
				 	query.append(" a.userRole as userRole , upper(b.grpName) as userGrp , a.actBy as userId , ");
				 	query.append(" upper(c.firstName || ' ' || c.lastName) as name , to_char(a.crtDt , 'dd/MM/yyyy HH12:MI:SS AM ')  as auditDate , ");
				 	query.append(" to_char(a.apprAmt) as claimAmt , a.actId as statusId , d.cmbDtlName as followUpStatus ");
				 	query.append(" from EhfFollowUpAudit a , EhfmGrps b , EhfmUsers c ,EhfmCmbDtls d ");
				 	query.append(" where a.id.caseFollowupId='"+followUpId+"' ");
				 	query.append(" and a.id.caseType = 'CLAIM' and a.userRole = b.grpId and a.actId = d.cmbDtlId ");
				 	query.append(" and a.actBy = c.userId order by a.id.actOrder ");
				 	
				 	workFlow=followUpDao.executeNormalQuery(query.toString());
		 		}
		 	catch(Exception e)
		 		{
		 			e.printStackTrace();
//		 			logger.error("Exception Occured in getCochlearClaimWorkFlow of FollowUpServiceImpl "+e.getMessage());
		 		}
		 	return workFlow;
		 }
		 
	 
	 /*
	 * Added by Srikalyan to Check previous 
	 * Cochlear FollowUp Claims Status
	 */
	 @Override
	 public String checkInitiatationStatus(String caseId,String followUpId)
	 	{
		 String returnVal="N";
		 try
	 		{
			 	List<FollowUpVO> flpVO=new ArrayList<FollowUpVO>();
			 	StringBuffer query=new StringBuffer();
			 	query.append(" select a.caseFollowupId as followUpId , a.caseId as caseId , ");
			 	//query.append(" a.followUpStatus as followUpStatus , a.claimAmount as claimAmnt, ");
			 	query.append(" a.followUpStatus as followUpStatus , b.cochlearFollowupCount as count  , ");
			 	query.append(" (case when a.claimDyEoAmt is null then 0 else a.claimDyEoAmt end ) as claimAmnt ");
			 	query.append(" from EhfCaseFollowupClaim a, EhfCochlearFollowup b ");
			 	query.append(" where a.caseId='"+caseId+"'  ");//and a.caseFollowupId not in ('"+followUpId+"')
			 	query.append(" and a.caseFollowupId = b.cochlearFollowupId  ");
			 	query.append(" order by a.crtDt asc");
			 	flpVO=followUpDao.executeNormalQuery(query.toString());
			 	if(flpVO!=null)
			 		{
			 			if(flpVO.size()>0)
			 				{
			 					String followUpNum=followUpId;
			 					followUpNum=followUpId.substring(followUpNum.length()-1,followUpNum.length());
			 					if(followUpNum.equalsIgnoreCase("F"))
			 						returnVal="Y";
			 					else
			 						{
			 							int num=Integer.parseInt(followUpNum);
			 							int count=0;
				 						for(int i=0 ; i<num ; i++)
					 						{
					 							if(flpVO.get(i)!=null)
					 								{
					 									if(flpVO.get(i).getCount()!=null)
					 										{
					 											int cochCount=Integer.parseInt(flpVO.get(i).getCount());
					 											if(cochCount==num)
						 											{
					 													continue;
						 											}
					 											else if (num > cochCount)
					 												{
						 												if( (flpVO.get(i).getClaimAmnt()==0 
						 														          && !flpVO.get(i).getFollowUpStatus().equalsIgnoreCase(config.getString("CochlearDyeoRejected")))
						 														|| 
						 																(flpVO.get(i).getClaimAmnt()!=0 && (flpVO.get(i).getFollowUpStatus().equalsIgnoreCase(config.getString("CochlearDyEoPending")) ||
						 																								   (flpVO.get(i).getFollowUpStatus().equalsIgnoreCase(config.getString("CochlearDyEoPendingUpdated"))	))))
									 										{
						 														if(!followUpId.equalsIgnoreCase(flpVO.get(i).getFollowUpId()))
						 															count++;
									 										}
					 												}
					 											
					 										}
					 								}
					 							
					 						}
				 						if(count==0)
			 								returnVal="Y";
			 						}	
			 					
			 				}
			 		}
	 		}
		 catch(Exception e)
		 	{
			 	e.printStackTrace();
//	 			logger.error("Exception Occured in checkInitiatationStatus of FollowUpServiceImpl "+e.getMessage());
	 			return returnVal;
		 	}
		 return returnVal;
	 	}
	 

	 /*
	 * Added by Srikalyan to get the  
	 * Cochlear FollowUp Claims Status
	 */
	 @Override
	 public List<FollowUpVO> getFlpStatusLst()
	 	{
		 	StringBuffer query=new StringBuffer();
		 	List<FollowUpVO> lbLst=new ArrayList<FollowUpVO>();
		 	try
		 		{
				 	query.append(" select a.cmbDtlId as id , a.cmbDtlName as value ");
				 	query.append(" from EhfmCmbDtls a where a.cmbHdrId ='CH14' ");
				 	query.append(" and a.cmbDtlId not in ( 'CD371' , 'CD372' , 'CD373' , 'CD374' , 'CD375')");
				 	
				 	lbLst=followUpDao.executeNormalQuery(query.toString());
		 		}
		 	catch(Exception e)
		 		{
		 			e.printStackTrace();
//		 			logger.error("Exception Occured in checkInitiatationStatus of FollowUpServiceImpl "+e.getMessage());
		 		}
		 	return lbLst;
	 	}
	 /*
	 * Added by Srikalyan for getting
	 * Send Back Details of Cochlear FollowUp Claim
	 */
	 @Override
	public FollowUpVO getSendBackDtls(String followUpId)
	 	{
		 	StringBuffer query=new StringBuffer();
		 	List<FollowUpVO> lbLst=new ArrayList<FollowUpVO>();
		 	FollowUpVO flpVO=new FollowUpVO();
		 	try
		 		{
				 	query.append(" select a.id.referenceNo as id , a.ceoRemarks as ceoRemark ");
				 	query.append(" , a.userRemarks as sendBackRmrks  ");
				 	query.append(" from EhsCeoSendbackDtls a , EhfCaseFollowupClaim b ");
				 	query.append(" where a.id.referenceNo ='"+followUpId+"' ");
				 	query.append(" and a.id.referenceNo = b.id.caseFollowupId and b.pendingWith is null order by a.crtDt desc ");
				 	
				 	lbLst=followUpDao.executeNormalQuery(query.toString());
				 	
				 	if(lbLst==null || lbLst.size()==0)
				 		{
				 			StringBuffer query1=new StringBuffer();
				 			StringBuffer query2=new StringBuffer();
				 			query.setLength(0);
					 		query1.append(" select a.id.caseFollowupId as id , a.ceoRemark as ceoRemark ");
						 	query1.append(" , a.sentbackUsrRemrks as sendBackRmrks  ");
						 	query1.append(" from EhfCaseFollowupClaim a where a.id.caseFollowupId ='"+followUpId+"' ");
						 	//query2.append(" and a.pendingWith is not null ");
						 	query.append(query1);
						 	query.append(query2);
						 	lbLst=followUpDao.executeNormalQuery(query.toString());
						 	/*if(lbLst==null || lbLst.size()==0)
					 			{
						 			query.setLength(0);
						 			query2.setLength(0);
						 			query2.append(" and a.sentbackUsrRemrks is not null ");
						 			query.append(query1);
								 	query.append(query2);
						 		}*/
						 	lbLst=followUpDao.executeNormalQuery(query.toString());
				 		}
				 	
				 	
				 	if(lbLst!=null)
				 		if(lbLst.size()>0)
				 			if(lbLst.get(0)!=null)
				 				flpVO=lbLst.get(0);
		 		}
		 	catch(Exception e)
		 		{
		 			e.printStackTrace();
//		 			logger.error("Exception Occured in getSendBackDtls of FollowUpServiceImpl "+e.getMessage());
		 		}
		 	return flpVO;
	 	}
	 /*
		 * Added by Srikalyan for getting
		 * Case Status of Cochlear FollowUp Claim
		 */
		public FollowUpVO getCaseStatus(String followUpId)
			{
	 			StringBuffer query=new StringBuffer();
	 			List<FollowUpVO> lbLst=new ArrayList<FollowUpVO>();
			 	FollowUpVO flpVO=new FollowUpVO();
			 	try
			 		{
			 			query.append(" select followUpStatus as id  ");
			 			query.append(" from EhfCaseFollowupClaim a where a.id.caseFollowupId ='"+followUpId+"' ");
					 	lbLst=followUpDao.executeNormalQuery(query.toString());
					 	
					 	if(lbLst!=null)
					 		{
					 			if(lbLst.size()>0)
					 				{
					 					if(lbLst.get(0)!=null)
					 						{
					 							if(lbLst.get(0).getId()!=null)
					 								flpVO.setFollowUpStatus(lbLst.get(0).getId());		
					 						}
					 				}
					 		}
			 		}
			 	catch(Exception e)
			 		{
			 			e.printStackTrace();
//			 			logger.error("Exception Occured in getCaseStatus of FollowUpServiceImpl "+e.getMessage());
			 		}
			 	return flpVO;
			}
		
		/*
		 * Added by Srikalyan for getting
		 * Previous Cochlear Followup's dates
		 */
		@Override
		public FollowUpVO getPrevDateDtls(int cochlearCount,String caseId)
			{	
				StringBuffer query=new StringBuffer();
	 			List<FollowUpVO> lbLst=new ArrayList<FollowUpVO>();
			 	FollowUpVO flpVO=new FollowUpVO();
			 	try
		 			{
			 			query.append(" select to_char(a.avTheraphyFromDate , 'DD/MM/YYYY') as fromDate ,  ");
			 			query.append(" to_char( a.avTheraphyToDate , 'DD/MM/YYYY') as toDate , ");
			 			query.append(" to_char( a.reviewDate , 'DD/MM/YYYY') as reviewDate ");
			 			query.append(" from EhfCochlearFollowup a where a.caseId ='"+caseId+"' ");
			 			query.append(" and a.cochlearFollowupCount ='"+Integer.toString(cochlearCount)+"' " );
					 	lbLst=followUpDao.executeNormalQuery(query.toString());
					 	
					 	if(lbLst!=null)
					 		{
					 			if(lbLst.size()>0)
					 				{
					 					if(lbLst.get(0)!=null)
					 						{
					 							if(lbLst.get(0)!=null)
					 								flpVO=lbLst.get(0);;		
					 						}
					 				}
					 		}
		 			}	
			 	
			 	catch(Exception e)
			 		{
			 			e.printStackTrace();
//			 			logger.error("Exception Occured in getPrevDateDtls of FollowUpServiceImpl "+e.getMessage());
			 		}
			 	return flpVO;
			}
		/*
		 * Added by Srikalyan to get the CSV Download for Cochlear Follow Up Cases  
		 */
		@Override
		public StringBuilder cochCSVDownload(FollowUpVO flpVO)
			{
				
				FollowUpVO flpVORes= getCochlearClaimCases(flpVO);
		        StringBuilder line = new StringBuilder(); 
		        char separator = ',';
		        if(flpVORes.getClaimList()!=null)
		        	{
		        		List<FollowUpVO> flpVOLst=flpVORes.getClaimList();
		        		if(flpVOLst.size()>0)
							{
					            line.append("Case No");
							    line.append(separator);
								line.append("Follow Up ID");
								line.append(separator);
								line.append("Follow Up Status");
								line.append(separator);
								line.append("Card No");
								line.append(separator);
								line.append("Patient Name");
								line.append(separator);
								line.append("Patient District");
								line.append(separator);
								line.append("Hospital Name");
								line.append(separator);
								line.append("Hospital District");
								line.append(separator);
								line.append("Hospital Type");
								line.append(separator);
								line.append("Claim Submitted Date");
								line.append(separator);
								line.append("Claim Amount");
								line.append(separator);
								
								line.append("\n");
				                line.append("\n");
								
								for(FollowUpVO flpVOLstObj : flpVOLst)
									{
									    line.append(flpVOLstObj.getCaseNo());
									    line.append(separator);
										line.append(flpVOLstObj.getFollowUpId());
										line.append(separator);
										line.append(flpVOLstObj.getFollowUpStatus());
										line.append(separator);
										line.append(flpVOLstObj.getCardNo());
										line.append(separator);
										line.append(flpVOLstObj.getPatName());
										line.append(separator);
										line.append(flpVOLstObj.getDistrict());
										line.append(separator);
										line.append(flpVOLstObj.getHospName());
										line.append(separator);
										line.append(flpVOLstObj.getHospCity());
										line.append(separator);
										line.append(flpVOLstObj.getName());
										line.append(separator);
										line.append(flpVOLstObj.getAuditDate());
										line.append(separator);
										line.append(flpVOLstObj.getClaimAmt());
										line.append(separator);
										
										line.append("\n");
									}
							}
		        	}
				return line;
			}
		
	/*
	 * Added by Srikalyan to get the Scheme Id Based on Follow Up ID 
	 */
	public String getSchemeId(String followUpId)
		{
			StringBuffer query=new StringBuffer();
			String schemeId=null;
			try
				{
					query.append( " select a.schemeId as schemeType " );
					query.append( " from EhfCaseFollowupClaim a " );
					query.append( " where a.caseFollowupId = '"+followUpId+"'  " );
					
					List<FollowUpVO> lst=followUpDao.executeNormalQuery(query.toString());
					if(lst!=null)
						{
							if(lst.size() > 0)
								{
									if(lst.get(0)!=null)
										if(lst.get(0).getSchemeType()!=null)
											schemeId=lst.get(0).getSchemeType();
											
								}
						}
				}
			catch(Exception e)
				{
					e.printStackTrace();
//					logger.error("Exception Occured in getSchemeId of FollowUpServiceImpl "+e.getMessage());
				}
			return schemeId;
		}
	@Override
	public String getfollowupFtdFlag(String followUpId) {
		// TODO Auto-generated method stub
		StringBuffer query=new StringBuffer();
		String ftdFlag=null;
		try
			{
				query.append( " select count(*) as COUNT " );
				query.append( " from EhfFollowUpAudit a " );
				query.append( " where a.id.caseFollowupId = '"+followUpId+"' and a.actId in ('CD65') " );
				
				List<FollowUpVO> lst=followUpDao.executeNormalQuery(query.toString());
				if(lst!=null)
					{
						if(lst.size() > 0)
							{
								if(lst.get(0)!=null)
									if(lst.get(0).getCOUNT()!=null && lst.get(0).getCOUNT().intValue()>=1)
									{
										ftdFlag="Y";
									}
									else
									{
										ftdFlag="N";
									}
										
							}
						
					}
			}
		catch(Exception e)
			{
				e.printStackTrace();
				logger.error("Exception Occured in getSchemeId of FollowUpServiceImpl "+e.getMessage());
			}
		return ftdFlag;
	}
}
