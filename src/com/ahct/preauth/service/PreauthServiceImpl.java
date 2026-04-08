package com.ahct.preauth.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.ahct.attachments.service.AttachmentService;
import com.ahct.attachments.vo.AttachmentVO;
import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.claims.util.ClaimsConstants;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.service.CommonService;
import com.ahct.common.util.SendSMS;
import com.ahct.common.vo.PatientSmsVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.preauth.util.PreauthConstants;
import com.ahct.preauth.vo.DrugsVO;
import com.ahct.preauth.dao.PreauthClinicalNotesDAO;
import com.ahct.preauth.dao.PreauthDtlsDao;
//import com.ahct.preauth.vo.CmaAuditVO;
import com.ahct.preauth.vo.CommonDtlsVO;
import com.ahct.preauth.vo.PatientVO;
import com.ahct.preauth.vo.PreauthVO;
import com.ahct.preauth.vo.cochlearCaseVO;
import com.lowagie.text.pdf.codec.Base64;
import com.tcs.framework.configuration.ConfigurationService;
import com.ahct.model.AsritCaseCardValidateDtls;
import com.ahct.model.EhfAudit;
import com.ahct.model.EhfAuditId;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfDischargeSummary;
import com.ahct.model.EhfPatientDrugsNabh;
import com.ahct.model.EhfmDentalProcCriteria;
import com.ahct.model.EhfmDentalProcCriteriaPK;
import com.ahct.model.EhfmHospQuantityNIMS;
import com.ahct.model.EhfmUsers;
import com.ahct.model.EhfCaseClaim;
import com.ahct.model.EhfCaseEnhPatientDrugs;
import com.ahct.model.EhfCaseEnhancementDtls;
import com.ahct.model.EhfCaseFollowupClaim;
import com.ahct.model.EhfCasePreauthAmounts;
import com.ahct.model.EhfCaseTherapy;
import com.ahct.model.EhfCaseTherapyInvest;
import com.ahct.model.EhfEmployeeDocAttach;
import com.ahct.model.EhfFollowUpAudit;
import com.ahct.model.EhfFollowUpAuditId;
import com.ahct.model.EhfPanelDocCaseDtls;
import com.ahct.model.EhfPatient;
import com.ahct.model.EhfPatientDocAttach;
import com.ahct.model.EhfPatientHospDiagnosis;
import com.ahct.model.EhfPatientPersonalHistory;
import com.ahct.model.EhfTelephonicRegn;
import com.ahct.model.EhfmCmbDtls;
import com.ahct.model.EhfmComorbid;
import com.ahct.model.EhfmDiagCategoryMst;
import com.ahct.model.EhfmDiagCategoryMstId;
import com.ahct.model.EhfmDiagDisAnatomicalMst;
import com.ahct.model.EhfmDiagDiseaseMst;
import com.ahct.model.EhfmDiagMainCatMst;
import com.ahct.model.EhfmDiagMainCatMstId;
import com.ahct.model.EhfmDiagSubCatMst;
import com.ahct.model.EhfmDiagSubCatMstId;
import com.ahct.model.EhfmDiagnosisMst;
import com.ahct.model.EhfmHospQuantity;
import com.ahct.model.EhfmHospitals;
import com.ahct.model.EhfmSeq;
import com.ahct.model.EhfmSlabPackage;
import com.ahct.model.EhfmSlabPackageId;
import com.ahct.model.EhfmTherapyProcMst;
import com.ahct.model.EhfmTherapyProcMstId;
import com.ahct.model.EhfmWorkflowStatus;
import com.ahct.model.EhfmWorkflowStatusId;
import com.ahct.model.EhfmCaseCochlearDtl;
import com.ahct.model.EhfmEDCHospActionDtls;
import com.ahct.model.EhfmEDCHospActionDtlsId;
import com.ahct.common.util.SMSServices;
import com.tcs.framework.emailConfiguration.EmailConstants;
import com.tcs.framework.emailConfiguration.EmailVO;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria.CriteriaOperator;

public class PreauthServiceImpl implements PreauthService 
{
	
	PreauthDtlsDao preauthDtlsDao ;
      GenericDAO genericDao;
      AttachmentService attachmentService;
      CommonService commonService ;
      PreauthClinicalNotesDAO preauthClinicalNotesDao;
      
      
	public void setPreauthClinicalNotesDao(
			PreauthClinicalNotesDAO preauthClinicalNotesDao) {
		this.preauthClinicalNotesDao = preauthClinicalNotesDao;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
	static final Logger gLogger = Logger.getLogger(PreauthServiceImpl.class);
	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}
	public void setPreauthDtlsDao(PreauthDtlsDao preauthDtlsDao)
	{
		this.preauthDtlsDao = preauthDtlsDao;
	}
	
	public List<CommonDtlsVO> getPatientCommonDtls(String caseId) throws Exception
	{
		List<CommonDtlsVO> commonDtls =preauthDtlsDao.getPatientCommonDtls(caseId);
		return commonDtls;		
	}
	
	public List<EhfPatientDocAttach> getOnBedPhotoDtls(String patId) throws Exception
	{
		List<EhfPatientDocAttach> asritPatientDocAttach = preauthDtlsDao.getOnBedPhotoDtls(patId);
		return asritPatientDocAttach;
		
	}

/*public Map getPatientOP(HashMap lParamMap)
	{
		Map patientOp=new HashMap();
		patientOp=preauthDtlsDao.getPatientOP(lParamMap);
		return patientOp;
	}

public Map getFraudCrDtls(String pStrCaseId) 
{
	Map lResMap=preauthDtlsDao.getFraudCrDtls(pStrCaseId);
	return lResMap;
}*/


/*public Map saveFraudCrDtls(CmaAuditVO cmaVO)
{
    Map resMap = preauthDtlsDao.saveFraudCrDtls(cmaVO);
    return resMap;
}*/
public List<LabelBean> getDocSpecList(PreauthVO preauthVO)
{
	List<LabelBean> docSpecList = new ArrayList<LabelBean>();
	try{
		StringBuffer query = new StringBuffer();
		query.append("select distinct adm.disMainId as ID , adm.disMainName  as VALUE " );
		query.append("	from EhfmSpecialities adm , AsrimHospSpeciality ahs  ");
		query.append(" where adm.disMainId = ahs.id.specialityId and ahs.id.hospId = '"+preauthVO.getHospitalCode()+"' and  ahs.activeYN ='Y'  ");
		if(preauthVO.getRenewal() != null && !preauthVO.getRenewal().equals(""))
		{
			query.append(" ahs.renewal = '"+preauthVO.getRenewal()+"'   ");	
		}
		if(preauthVO.getPhase() != null && !preauthVO.getPhase().equals(""))
		{
			query.append(" ahs.phaseId = '"+preauthVO.getPhase()+"'   ");	
		}
		query.append("  order by adm.disMainName  ");
		docSpecList = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
	}
	catch(Exception e)
    {
	e.printStackTrace();	
    }
	return docSpecList;
}
public List<LabelBean> getDocAvailLst(PreauthVO preauthVO)
{
	List<LabelBean> docAvailLst = new ArrayList<LabelBean>();	
	 //List listdocAvail = new ArrayList() ;
	try
	{
		StringBuffer query = new StringBuffer();
		query.append(" select z.id.regNum  as ID,z.splstName as  VALUE  ");
		query.append(" from EhfmSplstDctrs z , EhfmDotorSplty y where z.isActiveYn ='Y' and z.isConsultant='"+preauthVO.getConsultant()+"'  ");
		query.append(" and y.id.spctlyCode='"+preauthVO.getDisMainId()+"' and z.id.regNum = y.id.regNum  and z.apprvStatus = 'CD896' and z.id.regNum is not null ");
		query.append(" and z.id.hospId = '"+preauthVO.getHospitalCode()+"' ");
		docAvailLst = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
		/*for(LabelBean labelBean:docAvailLst)
		{
			listdocAvail.add(labelBean.getID());	
		}*/
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return docAvailLst;
}
public String getDocdetails(PreauthVO preauthVO)
{
	List<LabelBean> lstDocDtls = new ArrayList<LabelBean>();
	String docDtls = "";
	try
	{
		StringBuffer query = new StringBuffer();
		query.append(" select z.id.regNum ||'~'||z.splstName ||'~'||z.contactNo||'~'||z.mobile||'~'||y.qual as ID ");
		query.append(" from EhfmSplstDctrs z , EhfmDoctorQlfctns x, EhfmQualMst y ");
		query.append(" where z.id.regNum ='"+preauthVO.getRegNo()+"'  and z.id.regNum = x.id.regNum  ");
		query.append(" and z.isActiveYn='Y' and x.id.qualId = y.qualId  and z.id.hospId='HS10'");
		lstDocDtls = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
		/*for(LabelBean labelBean:lstDocDtls)
		{
			docDtls.add(labelBean.getID());	
		}*/
		if(lstDocDtls!=null && lstDocDtls.size()>0)
		{
			docDtls= lstDocDtls.get(0).getID();
		}
	}
	catch(Exception e)
	{
	e.printStackTrace();	
	}
	return docDtls;
}
public List<LabelBean> getSubDiseases(PreauthVO preauthVO)
{
	List<LabelBean> lstdiseaseDtls = new ArrayList<LabelBean>();
	List diseaseDtls = new ArrayList();
	try
	{
		StringBuffer query = new StringBuffer();
		query.append(" select  s.disSubId||'~'||s.disName as ID  ");
		query.append(" from AsrimDiseaseSub s where s.dismainId ='"+preauthVO.getDisMainId()+"'   order by s.disName asc ");
		lstdiseaseDtls = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
		for(LabelBean labelBean:lstdiseaseDtls)
		{
			diseaseDtls.add(labelBean.getID());
		}
	}catch(Exception e)
	{
	e.printStackTrace();	
	}
	return diseaseDtls;
}
public List<LabelBean> getSurgeryList(PreauthVO preauthVO)
{
	List<LabelBean> lstSurgeryDtls = new ArrayList<LabelBean>();
	List SurgeryDtls = new ArrayList();
	try
	{
		StringBuffer query = new StringBuffer();
		query.append(" select asu.surgeryId||'~'||asu.surgDesc || '-' || asu.surgDispCode  as ID ");
		query.append(" from  AsrimSurgery asu ");
		query.append(" where  asu.disMainId = '"+preauthVO.getDisMainId()+"' and asu.medmgmtYN = '"+preauthVO.getTherapyType()+"' and asu.activeYN= 'Y' ");
		lstSurgeryDtls = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
		for(LabelBean labelBean : lstSurgeryDtls)
		{
			SurgeryDtls.add(labelBean.getID());
		}
	}catch(Exception e)
	{
	e.printStackTrace();	
	}
	return SurgeryDtls;
}
public List<LabelBean> getSpecialInvestigationLst(PreauthVO preauthVO)
{
	List<LabelBean> lstSpecialInvst = new ArrayList<LabelBean>();
	List SpecialInvstDtls = new ArrayList();
	String surgeryId = null;
	try
	{
		StringBuffer query = new StringBuffer();
		query.append(" select distinct eti.id.investigationId||'~'||eti.investDesc as ID ");
		StringTokenizer str = new StringTokenizer(preauthVO.getSurgeryId(),"~");
		while(str.hasMoreTokens())
		{
			if(surgeryId!= null && !surgeryId.equalsIgnoreCase("") )
				surgeryId = surgeryId +"','"+str.nextToken();
			else
				surgeryId = str.nextToken();
		}
		query.append(" from EhfmTherapyInvest eti where eti.id.ICDProcCode in('"+surgeryId+"') ");
		if(preauthVO.getAsriCatName()!=null && !preauthVO.getAsriCatName().equalsIgnoreCase(""))
			query.append("  and eti.id.asriSpec in('"+preauthVO.getAsriCatName()+"') ");
		query.append(" and eti.activeYn ='Y' ");  //and eti.preOpPostOp ='PRE'
		query.append(" and eti.id.schemeId='"+preauthVO.getScheme()+"'");
		lstSpecialInvst = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
		for(LabelBean labelBean : lstSpecialInvst)
		{
			SpecialInvstDtls.add(labelBean.getID()+"@");
		}
	}catch(Exception e)
	{
	e.printStackTrace();	
	}
	return SpecialInvstDtls;
}
public List<AttachmentVO> getcaseAttachments(PreauthVO preauthVO)
{
	List<AttachmentVO> lstAttachments = new ArrayList<AttachmentVO>();
	try
	{
		StringBuffer query = new StringBuffer();
		query.append(" select ect.filename as fileName , ect.attachTotalPath as savedName , ect.sno as docCount , ect.investigationId as surgInvstId ");
		query.append(" from  EhfCaseTherapyInvest ect where ect.caseId = '"+preauthVO.getCaseId()+"' ");
		query.append(" and ect.icdProcCode ='"+preauthVO.getSurgeryId()+"' and ect.investigationId ='"+preauthVO.getSplInvstId()+"' and ect.activeYN ='Y' ");
		lstAttachments = genericDao.executeHQLQueryList(AttachmentVO.class, query.toString());
		
	
	
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	System.out.println("lst size"+lstAttachments.size());
	if(lstAttachments != null && lstAttachments.size() >0)
	System.out.println(lstAttachments.get(0).getFileName());
	return lstAttachments;
}
public String getSurgeryDtls(PreauthVO preauthVO)
{
	List<LabelBean> lstSurg = new ArrayList<LabelBean>();
	String surgeryDtls = "";
	try{
		StringBuffer query = new StringBuffer();
		query.append(" select  dm.disMainName ||'~'||etcm.icdCatName||'~'||etp.procName||'~'||etp.procName||'~'||etp.hospstayAmt||'~'||etp.commonCatAmt||'~'||etp.icdAmt||'~'||etp.noOfDays as ID ,  etcm.id.asriCatCode||'~'||etcm.id.icdCatCode||'~'||etp.id.icdProcCode||'~'||etp.id.icdProcCode||'~'||etp.hospstayAmt||'~'||etp.commonCatAmt||'~'||etp.icdAmt||'~'||etp.noOfDays as VALUE");
		query.append(" from EhfmTherapyProcMst etp , EhfmTherapyCatMst etcm ,EhfmSpecialities dm  ");
		query.append(" where etp.icdCatCode = etcm.id.icdCatCode and etcm.id.asriCatCode ='"+preauthVO.getCatId()+"' and etp.activeYN ='Y' ");
		query.append(" and etcm.id.icdCatCode ='"+preauthVO.getIcdCatCode()+"'  and etp.id.icdProcCode ='"+preauthVO.getIcdProcCode()+"'  and etp.id.asriCode = '"+preauthVO.getCatId()+"' ");
		query.append(" and etcm.id.asriCatCode = dm.disMainId  ");
		lstSurg = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
		if(lstSurg!=null && lstSurg.size()>0)
		{
			surgeryDtls= lstSurg.get(0).getID();
		}
		
	}
	catch(Exception e)
	{
		e.printStackTrace();	
	}
	return surgeryDtls;
}
private EhfPatient getPatientInfo(String lStrpatientId)
{
	EhfPatient ehfPatient = genericDao.findById(EhfPatient.class, String.class, lStrpatientId);
	return ehfPatient;
}

public PreauthVO getTelephonicDtls(String caseId)
{
	PreauthVO preauthVO = new PreauthVO();
	SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
	
	try{
		if(caseId!=null && !"".equalsIgnoreCase(caseId))
		{
			EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, caseId);
			if(ehfCase != null)
			{
				preauthVO.setCaseNo(ehfCase.getCaseNo());
				if(ehfCase.getCsPreauthDt() != null)
				preauthVO.setAdmissionRadio( sdf1.format(ehfCase.getCsPreauthDt()));
				if(ehfCase.getEnhAppvAmt() != null && !ehfCase.getEnhAppvAmt().equals(""))
				preauthVO.setEnhApprAmt( Long.toString( ehfCase.getEnhAppvAmt()));
				EhfPatient ehfPatient = getPatientInfo(ehfCase.getCasePatientNo());	
			if(ehfPatient != null)
			{
				if(ehfPatient.getIntimationId() != null)
				{
					
					EhfTelephonicRegn ehfTelephonicRegn = genericDao.findById(EhfTelephonicRegn.class, String.class, ehfPatient.getIntimationId());	
			if(ehfTelephonicRegn != null)
			{
				
				preauthVO.setTelephonicRemarks(ehfTelephonicRegn.getTeleIntimRemarks());
				preauthVO.setTelephonicFlag(config.getString("activeY"));
			}
			else
				preauthVO.setTelephonicFlag(config.getString("activeN"));
				}
			}
			}
			preauthVO.setCaseStatusId(getCaseStatus(caseId));
		}
	}
	catch(Exception e)
	{
	e.printStackTrace();	
	}
return preauthVO;	
}
public List<PreauthVO> getcaseSurgertDtls(String caseId)  
{
	List<PreauthVO> lstSurgerydlts = new ArrayList<PreauthVO>();
	EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, caseId);
	//String hospId = "";
	if(ehfCase!=null)
		//hospId=ehfCase.getCaseHospCode();
	try{
		StringBuffer query = new StringBuffer();
		query.append(" select DISTINCT am.disMainName as asriCatName  ,  pm.id.process as process, am.disMainId  as catId ,cm.icdCatName as catName , cm.id.icdCatCode as  icdCatCode ,");
		query.append(" pm.procName as  procName , pm.id.icdProcCode as icdProcCode, cast(ec.caseTherapyId as string) as seqNo," );
		query.append("  ec.docName as docName,ec.docRegNum as docReg,ec.docQual as docQual,ec.docMobileNo as docMobNo,ec.procUnits as opProcUnits , ec.surgProcUnits as surgProcUnits ,ec.toothedUnits as toothedUnits");
		//query.append(" z.title||' '||z.splstName as docName ");
		query.append( " ,pm.hospstayAmt as hospStayAmt , pm.commonCatAmt as commonCatAmt , pm.icdAmt as icdAmt " );
		query.append("  ,ec.splInvRemarks as  investRemarks , cast(pm.noOfDays as string) as days, eh.isActvFlg as activeYN ");
		query.append(" ,pm.hospstayAmt as hospStayAmt , pm.commonCatAmt as  commonCatAmt, pm.icdAmt as icdAmt, pm.noOfDays as noOfDays");
		query.append(" from EhfCaseTherapy ec , EhfmTherapyCatMst cm  , EhfmTherapyProcMst pm , EhfmSpecialities am, ");
		query.append("  EhfmHospCatMst eh, EhfCase ecc ");
		//query.append(" EhfmSplstDctrs z  ");
		query.append(" where ec.caseId ='"+caseId+"' and ec.asriCatCode = cm.id.asriCatCode and ec.icdCatCode = cm.id.icdCatCode ");
		//query.append(" and z.id.regNum=ec.docRegNum ");
		//query.append(" and ec.asriCatCode =pm.id.asriCode ");
		query.append(" and ec.icdProcCode=pm.id.icdProcCode ");
		query.append(" and ec.activeyn = 'Y' ");
		query.append(" and cm.id.asriCatCode = am.disMainId and ecc.caseId= ec.caseId and eh.id.hospId= ecc.caseHospCode ");
		query.append(" and eh.id.catId= ec.asriCatCode ");
		query.append(" and (pm.id.state=ecc.schemeId or ecc.schemeId='1') and pm.id.process in ('IP','DOP') ");
		/*if(hospId!=null && !hospId.equalsIgnoreCase(""))
			query.append(" and z.id.hospId='"+hospId+"' ");*/
		lstSurgerydlts = genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
		for(PreauthVO preauthVO:lstSurgerydlts)
		{
			query = new StringBuffer();
			query.append(" select DISTINCT ei.investigationId as therapyId, ti.investDesc as filename, cast(ei.sno as string) as filePath,cast(ei.attachTotalPath as string) as ipOpFlag ");
			query.append(" from EhfCaseTherapyInvest ei , EhfmTherapyInvest ti , EhfCaseTherapy ec ,EhfCase a where ei.caseId  ='"+caseId+"' and ei.icdProcCode = '"+preauthVO.getIcdProcCode()+"'   ");
			query.append(" and ei.investigationId =ti.id.investigationId  and ei.activeYN = 'Y' and ti.id.ICDProcCode = '"+preauthVO.getIcdProcCode()+"'  ");
			query.append("  and ei.caseId=ec.caseId and ec.asriCatCode=ti.id.asriSpec and ti.id.ICDProcCode=ec.icdProcCode ");
			query.append(" and a.schemeId = ti.id.schemeId and a.caseId = ec.caseId ");
			List<PreauthVO> lstsplInvest = genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
			preauthVO.setLstSplInvet(lstsplInvest);
		}
		
	}catch(Exception e)
	{
	e.printStackTrace();	
	}
	return lstSurgerydlts;
}
public String saveEnhancementPreauth(PreauthVO preauthVO)
{
String msg = null;
String nxtUsr=null,currUsr=null;
Date date = new Date();
//Collection<Object> saveObjects=new ArrayList<Object>();
try{
	/**
	 * handle code when page gets refreshed
	 */
		
	EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, preauthVO.getCaseId());
	String enhcurrStatus = preauthVO.getCaseStatusId();
	EhfmWorkflowStatus ehfmWorkflowNextStatus = getEhfmWorkflowStatus(preauthVO);
	if(enhcurrStatus != null && preauthVO.getCaseStatusId() != null && !enhcurrStatus.equalsIgnoreCase(preauthVO.getCaseStatusId()))
	{
		msg = config.getString("preauth_msg_caseUpdated");
		return msg;	
	}
	if(enhcurrStatus != null && ehfmWorkflowNextStatus != null && enhcurrStatus.equalsIgnoreCase(ehfmWorkflowNextStatus.getNextStatus()))
	{
		msg = config.getString("preauth_msg_"+ehfmWorkflowNextStatus.getNextStatus());	
		return msg;
	}
	/**
	 * add enhancement details if present
	 */
	
	if(preauthVO.getEnhancementDtls() != null && !preauthVO.getEnhancementDtls().equalsIgnoreCase(""))
	{
		
		if(preauthVO.getEnhancementDtls() != null && !preauthVO.getEnhancementDtls().equalsIgnoreCase(""))
		{
			String[]  str2 = preauthVO.getEnhancementDtls().substring(0, preauthVO.getEnhancementDtls().length()-1).split("@,");
			for(int i=0; i<str2.length;i++)
			{
				String list=str2[i];
				String[] str3=list.split(",");
				EhfCaseEnhancementDtls ehfCaseEnhancementDtls = new EhfCaseEnhancementDtls();
				ehfCaseEnhancementDtls.setSno(Long.parseLong(commonService.getSequence(config.getString("Ehf_Case_Enhancement_Dtls"))));
				ehfCaseEnhancementDtls.setCaseId(preauthVO.getCaseId());
				ehfCaseEnhancementDtls.setType(str3[0]);
				ehfCaseEnhancementDtls.setEnhCode(str3[1]);
				ehfCaseEnhancementDtls.setEnhQuantCode(str3[2]);
				ehfCaseEnhancementDtls.setNoOfUnits(Integer.parseInt(str3[3]));
				ehfCaseEnhancementDtls.setAmount(Integer.parseInt(str3[4]));
				ehfCaseEnhancementDtls.setCrtDt(new java.sql.Timestamp(date.getTime()));
				ehfCaseEnhancementDtls.setCrtUser(preauthVO.getCruUsr());
				ehfCaseEnhancementDtls.setActiveYN(config.getString("activeY"));
				genericDao.save(ehfCaseEnhancementDtls);
				//saveObjects.add(ehfCaseEnhancementDtls);
			}
		
	}
	}
	
	
	/**
	 * make previously addedd enhancement lis inactive
	 */
	if(preauthVO.getRemEnhList()!=null){
	StringTokenizer str4 = new 	StringTokenizer(preauthVO.getRemEnhList(),"~");
	while(str4.hasMoreTokens())
	{
		EhfCaseEnhancementDtls EhfCaseEnhancementDtls = genericDao.findById(EhfCaseEnhancementDtls.class, Long.class, Long.parseLong(str4.nextElement().toString()));	
	if(EhfCaseEnhancementDtls != null)
	{
		EhfCaseEnhancementDtls.setActiveYN(config.getString("activeN"));
		//saveObjects.add(EhfCaseEnhancementDtls);
		genericDao.save(EhfCaseEnhancementDtls);
	}
	}
}
	/**
	 * save enhancement drugs
	 */
	
	EhfCaseEnhPatientDrugs ehfDrugs=null;
	if(preauthVO.getDrugList()!=null){
	for(DrugsVO drugsVO : preauthVO.getDrugList())
	{
		ehfDrugs = new EhfCaseEnhPatientDrugs(); 
		ehfDrugs.setDrugId(Long.parseLong(commonService.getSequence(config.getString("EHF_CASE_ENH_PATIENT_DRUGS"))));
		ehfDrugs.setCaseId(preauthVO.getCaseId());
		ehfDrugs.setPatientId(preauthVO.getPatientID());
		ehfDrugs.setDrugTypeCode(drugsVO.getDrugTypeName());
		ehfDrugs.setDrugSubTypeCode(drugsVO.getDrugSubTypeName());
		ehfDrugs.setDrugCode(drugsVO.getDrugName());
		ehfDrugs.setRoute(drugsVO.getRoute());
		ehfDrugs.setStrength(drugsVO.getStrength());
		ehfDrugs.setDosage(drugsVO.getDosage());
		ehfDrugs.setMedicationPeriod(drugsVO.getMedicationPeriod());
		ehfDrugs.setCrtDt(new java.sql.Timestamp(date.getTime()));
		ehfDrugs.setCrtUsr(preauthVO.getCruUsr());
		ehfDrugs.setActiveYN("Y");
		ehfDrugs.setUnits(drugsVO.getNoOfUnit());
		ehfDrugs.setAmtPerUnit(drugsVO.getAmountPerUnit());
		ehfDrugs.setTotAmt(drugsVO.getTotAmt());
		genericDao.save(ehfDrugs);
	}
	}
	/**
	 * make previously addedd drugs list inactive
	 */
	if(preauthVO.getDrugDeletionString()!=null){
	StringTokenizer str5 = new 	StringTokenizer(preauthVO.getDrugDeletionString(),",");
	while(str5.hasMoreTokens())
	{
		EhfCaseEnhPatientDrugs ehfCaseEnhPatientDrugs = genericDao.findById(EhfCaseEnhPatientDrugs.class, Long.class, Long.parseLong(str5.nextElement().toString()));	
	if(ehfCaseEnhPatientDrugs != null)
	{
		ehfCaseEnhPatientDrugs.setActiveYN(config.getString("activeN"));
		//saveObjects.add(EhfCaseEnhancementDtls);
		genericDao.save(ehfCaseEnhPatientDrugs);
	}
	}}
	/** get
	 * next status and  to save
	 */
	EhfmWorkflowStatus ehfmWorkflowStatus = getEhfmWorkflowStatus(preauthVO);
	if(ehfmWorkflowStatus != null)
	{
		EhfCasePreauthAmounts ehfCasePreauthAmounts = genericDao.findById(EhfCasePreauthAmounts.class, String.class, preauthVO.getCaseId());
		preauthVO.setStatusToSave(ehfmWorkflowStatus.getNextStatus());
			preauthVO.setRemarks(preauthVO.getEnhRemarks());	
			
			if(ehfCase != null)
			{
				ehfCase.setEnhCaseStatus(ehfmWorkflowStatus.getNextStatus());
				ehfCase.setCaseStatus(ehfmWorkflowStatus.getNextStatus());
				if(ehfmWorkflowStatus.getNextStatus() != null && (ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_enhancement_intiated"))
						|| ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_enhancement_reinitiated"))
						|| ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_enhancement_reinitiated_ctd"))
						|| ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_enh_eo_pending_updated"))
						|| ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_enh_ceo_pending_updated")))
						)
				{
					ehfCase.setEnhFlag(config.getString("activeY"));
					if(preauthVO.getEnhReqAmt() != null && !preauthVO.getEnhReqAmt().equalsIgnoreCase(""))
					{
					ehfCase.setEnhReqAmt(Long.parseLong(preauthVO.getEnhReqAmt()));
					ehfCasePreauthAmounts.setEnhAmt(Double.parseDouble(preauthVO.getEnhReqAmt()));
					
					}
					if(ehfmWorkflowStatus.getNextStatus() != null && !ehfmWorkflowStatus.getNextStatus().equals("") && ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_enhancement_intiated")))
					{
					ehfCase.setCsEnhReqDt(new java.sql.Timestamp(date.getTime()));
					ehfCasePreauthAmounts.setEnhReqdate(new java.sql.Timestamp(date.getTime()));
					}
					preauthVO.setAuditAmt(preauthVO.getEnhReqAmt());
				}
				if(ehfmWorkflowStatus.getNextStatus() != null && (ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_enhancement_recApprove"))||ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_enhancement_cpd_recApprove"))
					//	||(ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_enhancement_recReject"))) || (ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_enhancement_recPending"))) 
						))
				{
					if(preauthVO.getEnhApprvAmt() != null && !preauthVO.getEnhApprvAmt().equalsIgnoreCase(""))
					{
					preauthVO.setAuditAmt(preauthVO.getEnhApprvAmt());
					}
					
				}
				
								
				if(ehfmWorkflowStatus.getNextStatus() != null && (ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_enhancement_reject")))
						||(ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_enhancement_approve"))) 
						||(ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_enhancement_recReject")))
						)
				{
					if(preauthVO.getEnhApprvAmt() != null && !preauthVO.getEnhApprvAmt().equalsIgnoreCase(""))
					{
						ehfCasePreauthAmounts.setChEnhAppvAmt(Double.parseDouble(preauthVO.getEnhApprvAmt()));
						ehfCasePreauthAmounts.setChEnhAppvDt(new java.sql.Timestamp(date.getTime()));
						 
					}
					if(ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_enhancement_approve")))
					{
						//ehfCasePreauthAmounts.setEnhAmt(Double.parseDouble(preauthVO.getEnhApprvAmt()));
						 preauthVO.setAuditAmt(preauthVO.getEnhApprvAmt());
					float totAmt = (float) (ehfCase.getPreauthTotalPackageAmt()+ Double.parseDouble(preauthVO.getEnhApprvAmt())) ;
					if(totAmt > Integer.parseInt(config.getString("preauth_package_amt_limit")))
					{
						ehfCase.setCeoApprovalFlag("Y");
						nxtUsr=config.getString("EO_Grp");
						currUsr="Claim Head";
					}
					else
					{
						ehfCase.setCsEnhApvDt(new java.sql.Timestamp(date.getTime()));
						ehfCase.setEnhAppvAmt(Long.parseLong(preauthVO.getEnhApprvAmt()));
						ehfCasePreauthAmounts.setEnhAppvAmt(Double.parseDouble(preauthVO.getEnhApprvAmt()));
					}
					}
					else
					ehfCase.setCsEnhRejDt(new java.sql.Timestamp(date.getTime()));	
				}
				if(ehfmWorkflowStatus.getNextStatus() != null && (ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_enh_eo_appv")))
						)
				{
					ehfCasePreauthAmounts.setEoEnhAppvAmt(Double.parseDouble(preauthVO.getEnhApprvAmt()));
					ehfCasePreauthAmounts.setEoEnhAppvDt(new java.sql.Timestamp(date.getTime()));
					preauthVO.setAuditAmt(preauthVO.getEnhApprvAmt());
					if(ehfCase.getCeoApprovalFlag().equalsIgnoreCase("Y"))
						{
							nxtUsr=config.getString("Group.CEO");
							currUsr="EO Operations";
						}
				}
				if(ehfmWorkflowStatus.getNextStatus() != null && (ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_enh_ceo_appv")))
				)
		         {
					long enhApprvAmt=(long)Double.parseDouble(preauthVO.getEnhApprvAmt());
					ehfCasePreauthAmounts.setCeoEnhAppvAmt(Double.parseDouble(preauthVO.getEnhApprvAmt()));
					ehfCasePreauthAmounts.setCeoEnhAppvDt(new java.sql.Timestamp(date.getTime()));
					ehfCasePreauthAmounts.setEnhAppvAmt(Double.parseDouble(preauthVO.getEnhApprvAmt()));
					ehfCase.setCsEnhApvDt(new java.sql.Timestamp(date.getTime()));
					ehfCase.setCeoApprovalFlag("A");
					ehfCase.setEnhAppvAmt(enhApprvAmt);
					//ehfCase.setPckAppvAmt((long) (ehfCase.getPckAppvAmt()+Double.parseDouble(preauthVO.getEnhApprvAmt())));
					ehfCase.setPckAppvAmt((long) (ehfCase.getPckAppvAmt()));
					preauthVO.setAuditAmt(preauthVO.getEnhApprvAmt());
		         }
				if(ehfmWorkflowStatus.getNextStatus() != null && (ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_enh_eo_rej")))
						||(ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_enh_ceo_reject"))) 
						
						)
				{
					ehfCasePreauthAmounts.setEnhRejDate(new java.sql.Timestamp(date.getTime()));
					ehfCasePreauthAmounts.setEnhAppvAmt((double) 0);
					ehfCase.setCeoApprovalFlag("R");
					ehfCase.setEnhAppvAmt(0L);
					ehfCase.setCsEnhRejDt(new java.sql.Timestamp(date.getTime()));
				}
				ehfCase.setViewFlag(config.getString("activeN"));
				//saveObjects.add(ehfCasePreauthAmounts);
				//saveObjects.add(ehfCase);
				ehfCase.setLstUpdDt(new java.sql.Timestamp(date.getTime()));
				ehfCase.setLstUpdUsr(preauthVO.getCruUsr());
				ehfCase.setCochlearYN(preauthVO.getCochlearYN());
				ehfCase.setOrganTransYN(preauthVO.getOrganTransYN());
				ehfCasePreauthAmounts.setLstUpdDt(new java.sql.Timestamp(date.getTime()));
				ehfCasePreauthAmounts.setLstUpdUsr(preauthVO.getCruUsr());
				
				genericDao.save(ehfCasePreauthAmounts);
				genericDao.save(ehfCase);
			}
		//	genericDao.saveAll(saveObjects);
			saveAstitAudit(preauthVO);
	}
	msg = config.getString("preauth_msg_"+ehfmWorkflowStatus.getNextStatus());
	
	if(ehfCase.getCeoApprovalFlag()!=null && ehfmWorkflowStatus.getNextStatus()!=null)
	 	{
		 	if(ehfCase.getCeoApprovalFlag().equalsIgnoreCase("Y"))
		 		{
					if(ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_enh_eo_appv"))||
							ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_enhancement_approve")))
						{
							if(ehfCase.getSchemeId()!=null && ehfCase.getSchemeId().equalsIgnoreCase(config.getString("AP")))
								{
			 						if("true".equalsIgnoreCase(config.getString("SmsRequired")))
				 					     {
											String currentUser=preauthVO.getCruUsr();
				 							EhfmUsers ehfmUsers =new EhfmUsers();
				 							ehfmUsers=genericDao.findById(EhfmUsers.class, String.class, currentUser);
				 							if(ehfmUsers!=null)
					 							{
													String resultMsg=null;
													StringBuffer query=new StringBuffer();
													//SendSMS sendSms =new SendSMS();
													SMSServices SMSServicesobj = new SMSServices();
													query.append(" select (eu.firstName||' '||eu.lastName) as EMPNAME , ");
													query.append(" eu.mobileNo as MOBILENO ");
													query.append(" from EhfmUsers eu , EhfmUsrGrpsMpg eug ");
													query.append(" where eu.serviceFlag='Y' and eu.userId = eug.id.usergrpId ");
													query.append(" and eu.userType in ('"+config.getString("AP")+"') ");
													query.append(" and eug.id.grpId='"+nxtUsr+"' ");
													List<LabelBean> lbLst=new ArrayList<LabelBean>();
													lbLst=genericDao.executeHQLQueryList(LabelBean.class, query.toString());
													if(lbLst!=null)
														{
															if(lbLst.size()>0)
																{	
																	for(LabelBean lb:lbLst)
																		{
																		String templateId="1407162124365047811";
																			String smsText=null;
										    	 					     	smsText="Enhancement case with Case Number : "+ehfCase.getCaseId()+" has been Recommended for Approval by "+ehfmUsers.getFirstName()+" "+ehfmUsers.getLastName()+" , "+currUsr+" and the case is pending in your worklist.\n\nAHCT, Govt. of Telangana";
										    	 					     	//resultMsg=sendSms.sendSMS(smsText,lb.getMOBILENO());
										    	 					     	if("Y".equalsIgnoreCase(config.getString("AsriSms")))
										    	 					     	{
											    	 							Map<String,Object> lMap = new HashMap<String,Object>();
											    	 							lMap.put("msg",smsText );
											    	 							lMap.put("mobile",lb.getMOBILENO());
											    	 							lMap.put("templateId",templateId);
											    	 							resultMsg = commonService.saveToAsritSms(lMap);
										    	 					     	}
																		}
																}
														}
					 							}
				 					     }
								}
						}
		 		}
	 	}
	
	EhfPatient ehfPatient = genericDao.findById(EhfPatient.class, String.class, preauthVO.getPatientID());
	SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
     String crtDate=sdfw.format(date);
     Date crtDt = sdfw.parse((sdfw.format(date)));
	/* if("true".equalsIgnoreCase(config.getString("SmsRequired")))
     {
		 String smsNextVal=commonService.getSequence(config.getString("PATIENT_SMS_SNO"));
     	if(ehfPatient.getContactNo()!=null && !ehfPatient.getContactNo().equals(""))
     	{
     	String lStrResultMsg=null;
     	PatientSmsVO patientSmsVO=new PatientSmsVO();
     	patientSmsVO.setSerialNo(Long.parseLong(smsNextVal));
     	patientSmsVO.setPhoneNo(ehfPatient.getContactNo().toString());
     	patientSmsVO.setSmsText("Dear "+ehfPatient.getName().trim()+" , Your case with case Id  "+preauthVO.getCaseId()+"  has been " + msg +" in "+getHospName(ehfPatient.getRegHospId()) );
     	patientSmsVO.setEmpCode(ehfPatient.getEmployeeNo());
     	patientSmsVO.setEmpName(ehfPatient.getName().trim());
     	patientSmsVO.setCrtUsr(preauthVO.getCruUsr());
     	patientSmsVO.setCrtDt(crtDt);
     	patientSmsVO.setSmsAction("Case with caseId " +preauthVO.getCaseId() + msg);
     	patientSmsVO.setPatientId(ehfPatient.getPatientId());
     	lStrResultMsg=commonService.sendPatientSms(patientSmsVO);
     	
     	}
     }*/
     if (config.getBoolean("EmailRequired")) 
     {
     	String mailId=null;
     	if(ehfPatient.getEmailId()!=null && !ehfPatient.getEmailId().equals(""))
     	{
     	mailId=ehfPatient.getEmailId();
     	String[] emailToArray = {mailId};
     	EmailVO emailVO = new EmailVO();
			emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
			emailVO.setTemplateName(config.getString("EHFPreauthtemplateName"));
			emailVO.setFromEmail(config.getString("emailFrom"));
			emailVO.setToEmail(emailToArray);
			emailVO.setSubject(config.getString("preauthemailSubject"));
			Map<String, String> model = new HashMap<String, String>();
			model.put("patientName", ehfPatient.getName().trim());
			model.put("registeredHosp", getHospName(ehfPatient.getRegHospId()));
			model.put("status", msg);
			model.put("statusDate",crtDate);
			model.put("CaseId",preauthVO.getCaseId());
			if(ehfCase!=null)
				{
					if(ehfCase.getCaseNo()!=null)
						{
							model.put("CaseId",ehfCase.getCaseNo());
						}
					else
						model.put("CaseId",preauthVO.getCaseId());
				}	
			else
				model.put("CaseId",preauthVO.getCaseId());
			
			if(ehfPatient!=null)
				{
					if(ehfPatient.getPatientScheme()!=null)
						{
							if(ehfPatient.getPatientScheme().equalsIgnoreCase(config.getString("Scheme.JHS")))
								{
									emailVO.setSubject(config.getString("preauthemailSubjectJournalist"));
									emailVO.setFromEmail(config.getString("emailFromJournalist"));
									emailVO.setTemplateName(config.getString("EHFPreauthtemplateNameJourn"));
									commonService.generateNonImageMail(emailVO, model);
								}
							else
								{
									emailVO.setTemplateName(config.getString("EHFPreauthtemplateName"));
									commonService.generateMail(emailVO, model);
								}
						}
					else
						{
							emailVO.setTemplateName(config.getString("EHFPreauthtemplateName"));
							commonService.generateMail(emailVO, model);
						}
				}
			
     	}
		}
	
}catch(Exception e)
{
e.printStackTrace();	
}
return msg;
}
@Transactional
public String savePreauth(PreauthVO preauthVO)

{
	String msg = null;
	String updateCaseResult="failure";
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	Collection<Object> saveObjects=new ArrayList<Object>();
	
	//To calculate Total amount
	StringBuffer query= new StringBuffer();
	Long nabhAmt= 0L;
	Long surgAmt=0L;
	Long dopTeethPrice=0L,deductAmount=0L;
	Long hospStayAmount=0L;
	Long hospStayAmountDOP=0L;
	Long commonCatAmount=0L;
	//Long tempCommonCatAmount=0L;
	Long noOfDays=0L;
	Long noOfDaysDOP=0L;
	//Long checkLastCategoryAmt=0L;
	String tempCategory="";
	Long totAmt=0L;
	//boolean addSurgAmt=false;
	float comorbidAmt = 0;
	/**
	 * Added by ramalakshmi for hubspoke 
	 */
	String hospType = getHospType(preauthVO.getCaseId());//Added for hubspoke hospitals
	
	
	String procCode = getProcCode(preauthVO.getCaseId());
	//End of hubspoke CR
	
	
	try
	{
		if(preauthVO.getCaseId()!=null && !"".equalsIgnoreCase(preauthVO.getCaseId()))
		{
			/**
			 * handle code when page gets refreshed
			 */
			String currStatus = getCaseStatus(preauthVO.getCaseId());	
			EhfmWorkflowStatus ehfmWorkflowNextStatus = getEhfmWorkflowStatus(preauthVO);
			if(currStatus != null && preauthVO.getCaseStatusId() != null && !currStatus.equalsIgnoreCase(preauthVO.getCaseStatusId()))
			{
				msg = config.getString("preauth_case_updation_msg");
				return msg;	
			}
			if(currStatus != null && ehfmWorkflowNextStatus != null && currStatus.equalsIgnoreCase(ehfmWorkflowNextStatus.getNextStatus()))
			{
				msg = config.getString("preauth_msg_"+ehfmWorkflowNextStatus.getNextStatus());	
				return msg;
			}
		
	
			/**
			 * Partial Save begins
			 */
			if(ehfmWorkflowNextStatus!=null && ehfmWorkflowNextStatus.getNextStatus() .equalsIgnoreCase(config.getString("preauth_partail_save")) )
		    {
				if(preauthVO.getSplInvstId() != null && !preauthVO.getSplInvstId().equalsIgnoreCase(""))
				{
					StringTokenizer str = new StringTokenizer(preauthVO.getSplInvstId(),"*");	
					while(str.hasMoreTokens())
					{
						StringTokenizer str1 = new 	StringTokenizer(str.nextToken(),",");
						while(str1.hasMoreTokens())
						{
							EhfCaseTherapy ehfCaseTherapy = new EhfCaseTherapy();
							ehfCaseTherapy.setCaseTherapyId( Long.parseLong(commonService.getSequence(config.getString("CASE_THERAPY_ID"))));
							ehfCaseTherapy.setAsriCatCode(str1.nextToken());
							ehfCaseTherapy.setIcdCatCode(str1.nextToken());
							ehfCaseTherapy.setAsriProcCode( str1.nextToken());
							ehfCaseTherapy.setIcdProcCode(str1.nextToken());
							ehfCaseTherapy.setSplInvRemarks(str1.nextToken());
							ehfCaseTherapy.setDocRegNum(str1.nextToken());
							ehfCaseTherapy.setDocName(str1.nextToken());
							ehfCaseTherapy.setDocQual(str1.nextToken());
							ehfCaseTherapy.setDocMobileNo(str1.nextToken());
							ehfCaseTherapy.setProcUnits(str1.nextToken());
							ehfCaseTherapy.setSurgProcUnits(ehfCaseTherapy.getProcUnits());
							ehfCaseTherapy.setActiveyn(config.getString("activeY"));
							ehfCaseTherapy.setCaseId(preauthVO.getCaseId());
							ehfCaseTherapy.setCrtDt(new java.sql.Timestamp(date.getTime()));
							ehfCaseTherapy.setCrtUsr(preauthVO.getCruUsr());
							/*@author Sravan
							 * Added to main audit for regular changes in package prices
							 */
							String process= getPatientType(ehfCaseTherapy.getAsriCatCode(),ehfCaseTherapy.getIcdProcCode(), "CD202");
							EhfmTherapyProcMstId ehfmTherapyProcMstId=new EhfmTherapyProcMstId(ehfCaseTherapy.getAsriCatCode(),ehfCaseTherapy.getIcdProcCode(), "CD202",process);
							EhfmTherapyProcMst ehfmMainTherapyNabh=genericDao.findById(EhfmTherapyProcMst.class,EhfmTherapyProcMstId.class,ehfmTherapyProcMstId);
							if(ehfmMainTherapyNabh != null)
							{
								if(ehfmMainTherapyNabh.getHospstayAmt() != null)
								ehfCaseTherapy.setHospstayAmt(ehfmMainTherapyNabh.getHospstayAmt());
								if(ehfmMainTherapyNabh.getCommonCatAmt() != null )
								ehfCaseTherapy.setCommonCatAmt(ehfmMainTherapyNabh.getCommonCatAmt());
								if(ehfmMainTherapyNabh.getIcdAmt() != null )
								ehfCaseTherapy.setIcdAmt(ehfmMainTherapyNabh.getIcdAmt());
							}
							saveObjects.add(ehfCaseTherapy);
						}
					}
				}
				/**
				 * making previously added therapies in EhfCaseTherapy inactive
				 */
				if(preauthVO.getCaseSurgId() !=null && !preauthVO.getCaseSurgId().equalsIgnoreCase(""))
				{
					StringTokenizer str2 = new StringTokenizer(preauthVO.getCaseSurgId(),"~");
					while(str2.hasMoreTokens())
					{
						EhfCaseTherapy ehfCaseTherapy1 = genericDao.findById(EhfCaseTherapy.class, Long.class, Long.parseLong(str2.nextToken()));	
						ehfCaseTherapy1.setActiveyn(config.getString("activeN"));
						ehfCaseTherapy1.setLstUpdUsr(preauthVO.getCruUsr());
						ehfCaseTherapy1.setLstUpdDt(new java.sql.Timestamp(date.getTime()));
						saveObjects.add(ehfCaseTherapy1);
						//genericDao.save(ehfCaseTherapy1);
						/**
						 * making inactive of prev added investigations
						 */
						HashMap<String,Object> map=new HashMap<String,Object>();
			    		map.put("caseId", ehfCaseTherapy1.getCaseId());
			    		map.put("icdProcCode", ehfCaseTherapy1.getIcdProcCode());
			    		map.put("activeYN", config.getString("activeY"));
			    		List<EhfCaseTherapyInvest> lStEhfCaseTherapyInvest=(List<EhfCaseTherapyInvest>)genericDao.findAllByPropertyMatch(EhfCaseTherapyInvest.class,map);
						for(EhfCaseTherapyInvest ehfCaseTherapyInvest:lStEhfCaseTherapyInvest)
						{
							ehfCaseTherapyInvest.setActiveYN(config.getString("activeN"));
							saveObjects.add(ehfCaseTherapyInvest);
						}
						
					}
				}
			}
			/**
			 * Partial Save ends
			 */
			
			/**
			 * update admission type and proposed date
			 */
			EhfPatientHospDiagnosis ehfPatientHospDiagnosis = genericDao.findById(EhfPatientHospDiagnosis.class, String.class, preauthVO.getPatientID());
			if(ehfPatientHospDiagnosis != null)
			{
				ehfPatientHospDiagnosis.setCaseAdmType(preauthVO.getAdmissionRadio());
				ehfPatientHospDiagnosis.setLstUpdDt(new java.sql.Timestamp(date.getTime()));
				ehfPatientHospDiagnosis.setLstUpdUsr(preauthVO.getCruUsr());
				saveObjects.add(ehfPatientHospDiagnosis);
			}
			genericDao.saveAll(saveObjects);
			saveObjects= new ArrayList<Object>();
			
			if(!"IPM".equalsIgnoreCase(preauthVO.getIpOpFlag()))
			{
			/**
			 * Get Preauth Package Amounts
			 */
			//preauthVO= getTotalAmounts(preauthVO);
			/**
			 * Get the NABH Amount
			 */
			if(preauthVO.getHospStayAmt()!=null && !"".equalsIgnoreCase(preauthVO.getHospStayAmt()))
				nabhAmt= Long.parseLong(preauthVO.getHospStayAmt());
			/**
			 * Get the list of all active therapies
			 */
			System.out.println(procCode);
			System.out.println(hospType);;
			/**
			 * Added by ramalakshmi for hubspoke hospitals
			 */
			if(hospType!=null && ("SPOKE".equalsIgnoreCase(hospType) || "HUB".equalsIgnoreCase(hospType)) && (procCode!=null && procCode.equalsIgnoreCase("N18.6.A")))
			{
				query.append(" select distinct emt.hospstayAmt as hospStayAmt, emt.commonCatAmt as commonCatAmt, emt.icdAmt as icdAmt,ee.schemeId as scheme, ");
		    	query.append(" emt.noOfDays as noOfDays, emt.id.asriCode as test, emt.id.icdProcCode as testKnown, emt.procName as ipOpFlag, ect.procUnits as opProcUnits ");
		    	query.append(" ,emt.id.process as process ");
		    	query.append(" from EhfmHubSpokeMainTherapy emt, EhfCaseTherapy ect,EhfCase ee  ");
		    	query.append(" where ect.caseId = ? and ect.activeyn = ? and emt.id.asriCode = ect.asriCatCode  ");
		    	query.append(" and ee.caseId=ect.caseId and emt.id.state=ee.schemeId and emt.id.hubHospId=ee.caseHospCode ");
		    	query.append(" and emt.id.icdProcCode = ect.icdProcCode and emt.icdCatCode = ect.icdCatCode order by  emt.id.asriCode ");
			}
			else{
	    	query.append(" select distinct emt.hospstayAmt as hospStayAmt, emt.commonCatAmt as commonCatAmt, emt.icdAmt as icdAmt,ee.schemeId as scheme, ");
	    	query.append(" emt.noOfDays as noOfDays, emt.id.asriCode as test, emt.id.icdProcCode as testKnown, emt.procName as ipOpFlag, ect.procUnits as opProcUnits ");
	    	query.append(" ,emt.id.process as process ");
	    	query.append(" from EhfmTherapyProcMst emt, EhfCaseTherapy ect,EhfCase ee  ");
	    	query.append(" where ect.caseId = ? and ect.activeyn = ? and emt.id.asriCode = ect.asriCatCode  ");
	    	query.append(" and ee.caseId=ect.caseId and emt.id.state=ee.schemeId ");
	    	query.append(" and emt.id.icdProcCode = ect.icdProcCode and emt.icdCatCode = ect.icdCatCode order by  emt.id.asriCode ");
			}
	    	String[] args=new String[2];
	    	args[0]=preauthVO.getCaseId();
	    	args[1]="Y";
	    	
	    	List<PreauthVO> surgList= genericDao.executeHQLQueryList(PreauthVO.class, query.toString(), args);
	    	
	    	//Variables ued in calculating common category amount
	    	String str="";
	    	List<String> list=new ArrayList<String>();
	    	
	    	if(surgList!=null && surgList.size()>0)
	    	{
	    		for(int i=0; i<surgList.size(); i++)
	    		{
	    			
	    			//Added by Srikalyan for Dental TG Special Conditions
	    			if(surgList.get(i)!=null)
	    				{
	    					if(surgList.get(i).getTestKnown()!=null 
	    							&& surgList.get(i).getTest()!=null 
	    							&& surgList.get(i).getScheme()!=null 
	    							&& surgList.get(i).getOpProcUnits()!=null 
									&& !"".equalsIgnoreCase(surgList.get(i).getOpProcUnits())
									&& !"-1".equalsIgnoreCase(surgList.get(i).getOpProcUnits())
									&& surgList.get(i).getTest().equalsIgnoreCase(config.getString("DentalSurgeryID"))
	    							&& surgList.get(i).getScheme().equalsIgnoreCase(config.getString("TG")))
	    						{
	    							EhfmDentalProcCriteria ehfmDentalProcCriteria =new EhfmDentalProcCriteria();
	    							EhfmDentalProcCriteriaPK ehfmDentalProcCriteriaPK=new EhfmDentalProcCriteriaPK();
	    							ehfmDentalProcCriteriaPK.setIcdProcCode(surgList.get(i).getTestKnown());
	    							ehfmDentalProcCriteriaPK.setSchemeId(surgList.get(i).getScheme());
	    							ehfmDentalProcCriteria = genericDao.findById(EhfmDentalProcCriteria.class, EhfmDentalProcCriteriaPK.class, ehfmDentalProcCriteriaPK);
	    							if(ehfmDentalProcCriteria!=null && ehfmDentalProcCriteria.getActiveYn()!=null 
	    									&& "Y".equalsIgnoreCase(ehfmDentalProcCriteria.getActiveYn()))
	    								{
	    									if(ehfmDentalProcCriteria.getFrameworkPrice()!=null
	    											&& !"".equalsIgnoreCase(ehfmDentalProcCriteria.getFrameworkPrice())
	    											&& !"NA".equalsIgnoreCase(ehfmDentalProcCriteria.getFrameworkPrice()))
	    										{
	    											int opUnits=Integer.parseInt(surgList.get(i).getOpProcUnits());
	    											Long frameWorkPrice=Long.parseLong(ehfmDentalProcCriteria.getFrameworkPrice());
	    											//If Subsequent Price Available
	    											if(ehfmDentalProcCriteria.getSubsequentPrice()!=null && 
	    												!"".equalsIgnoreCase(ehfmDentalProcCriteria.getSubsequentPrice()) &&
	    												!"NA".equalsIgnoreCase(ehfmDentalProcCriteria.getSubsequentPrice()))
	    												{
	    													Long subsequentPrice=Long.parseLong(ehfmDentalProcCriteria.getSubsequentPrice());
	    													
	    													//Frame Work Price for First two Units and Subsequent Price for other Units
	    													if(opUnits>2)
    															dopTeethPrice=dopTeethPrice+(2*frameWorkPrice)+((opUnits-2)*subsequentPrice);
	    													else
	    														dopTeethPrice=dopTeethPrice+(opUnits*frameWorkPrice);
	    														
	    												}
	    											//If Subsequent Price not Available
	    											else
	    												{
	    													dopTeethPrice=dopTeethPrice+((opUnits)*frameWorkPrice);
	    												}
	    											continue;
	    										}
	    									/*if(ehfmDentalProcCriteria.getComoboProcCode()!=null 
	    											&& !"".equalsIgnoreCase(ehfmDentalProcCriteria.getComoboProcCode())
	    											&& !"NA".equalsIgnoreCase(ehfmDentalProcCriteria.getComoboProcCode()))
	    										{
	    											String[] listValues=ehfmDentalProcCriteria.getComoboProcCode().split("~");
	    											if(listValues!=null && listValues.length>0)
	    												{
	    													for(String value:listValues)
	    														{
		    														boolean finalBoolean=checkDependentProc(preauthVO.getCaseId(),surgList.get(i).getTestKnown(),value);
		    														if(finalBoolean==true)
		    															{
		    																StringBuffer queryLoc =new StringBuffer();
		    																queryLoc.append( " select a.icdAmt as VALUE , a.id.icdProcCode as ICDCODE " );
		    																queryLoc.append( " from EhfmTherapyProcMst a " );
		    																queryLoc.append( " where a.id.state='"+config.getStringArray("TG")+"'" );
		    																queryLoc.append( " and a.id.icdProcCode = '"+value+"'  " );
		    																queryLoc.append( " and a.id.asriCode = '"+config.getString("DentalSurgeryID")+"' " );
		    																queryLoc.append( " and a.activeYN = 'Y' " );
		    																
		    																List<LabelBean> beanLst=genericDao.executeHQLQueryList(LabelBean.class, query.toString());
		    																if(beanLst!=null && beanLst.size()>0)
		    																	{
		    																		if(beanLst.get(0)!=null && beanLst.get(0).getVALUE()!=null)
		    																			deductAmount=Long.parseLong(beanLst.get(0).getVALUE());
		    																	}
		    															}
	    														}
	    												}
	    										}*/
	    								}
	    						}
	    				}
	    			//End of Code added by Srikalyan for Dental TG Special Conditions
	    			
	    			
	    			// Get the sum of all icd procedure amounts
	        		if(surgList.get(i).getIcdAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getIcdAmt()))
	    			{
	        			if(surgList.get(i).getOpProcUnits()!=null && !"".equalsIgnoreCase(surgList.get(i).getOpProcUnits()) && !"-1".equalsIgnoreCase(surgList.get(i).getOpProcUnits()))
	        				surgAmt= surgAmt+ (Long.parseLong(surgList.get(i).getIcdAmt()) * Long.parseLong(surgList.get(i).getOpProcUnits()));
	        			else
	        				surgAmt= surgAmt+ Long.parseLong(surgList.get(i).getIcdAmt());
	    			}
	        		//End-Get the sum of all icd pocedure amounts
	        		
	    			if(i==0)
	    			{
	    				if(surgList.get(i).getNoOfDays()!=null)
	    					noOfDays= surgList.get(i).getNoOfDays().longValue();
	    				
	    				if(surgList.get(i).getProcess()!=null && !"".equalsIgnoreCase(surgList.get(i).getProcess()))
	    				{
		    				if(!surgList.get(i).getProcess().equalsIgnoreCase(config.getString("Dental_OP")))
	    						{
				    				if(surgList.get(i).getHospStayAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getHospStayAmt()))
				    					hospStayAmount= Long.parseLong(surgList.get(i).getHospStayAmt());
	    						}
		    				if(surgList.get(i).getProcess().equalsIgnoreCase(config.getString("Dental_OP")))
								{
			    					if(surgList.get(i).getHospStayAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getHospStayAmt()))
				    					{
			    							hospStayAmountDOP= (Long.parseLong(surgList.get(i).getHospStayAmt()) * Long.parseLong(surgList.get(i).getOpProcUnits()));
			    							//hospStayAmount = hospStayAmount + hospStayAmountDOP;
				    					}
								}
	    				}
	    				
	    			}
	    			if(i>0)
	    			{
	    				// Get the maximum No of Days
	    				if(surgList.get(i).getNoOfDays()!=null)
	    				{
	    					if(noOfDays<surgList.get(i).getNoOfDays().longValue())
	    						noOfDays= surgList.get(i).getNoOfDays().longValue();
	    				}
	    				//End-Get the maximum No of Days
	    				
	    				/*// Get the maximum Hospital Stay Amount
	    				if(surgList.get(i).getHospStayAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getHospStayAmt()))
	    				{
	    					if(hospStayAmount< Long.parseLong(surgList.get(i).getHospStayAmt()))
	    						hospStayAmount= Long.parseLong(surgList.get(i).getHospStayAmt());
	    				}	
	    				// End-Get the maximum Hospital Stay Amount*/	
    				
	    				
	    				if(surgList.get(i).getProcess()!=null && !"".equalsIgnoreCase(surgList.get(i).getProcess()))
	    				{
	    					if(!surgList.get(i).getProcess().equalsIgnoreCase(config.getString("Dental_OP")))
	    					{	
			    				// Get the maximum Hospital Stay Amount non DOPS
			    				if(surgList.get(i).getHospStayAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getHospStayAmt()))
				    			{
				    				if(hospStayAmount< Long.parseLong(surgList.get(i).getHospStayAmt()))
				    				hospStayAmount= Long.parseLong(surgList.get(i).getHospStayAmt());
				    			}	
			    				// End-Get the maximum Hospital Stay Amount
	    					}
	    					
	    					if(surgList.get(i).getProcess().equalsIgnoreCase(config.getString("Dental_OP")))
	    					{
	    						// Get No of Days for each DOP
	    						if(surgList.get(i).getNoOfDays()!=null)
			    				{
			    						noOfDaysDOP = noOfDaysDOP + surgList.get(i).getNoOfDays().longValue();
			    				}
	    						//End-Get No of Days for eachy DOP
	    						
	    						// Get the Hospital Stay Amount for each DOP
			    				if(surgList.get(i).getHospStayAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getHospStayAmt())
			    						&& surgList.get(i).getOpProcUnits()!=null && !"".equalsIgnoreCase(surgList.get(i).getOpProcUnits()) && !"-1".equalsIgnoreCase(surgList.get(i).getOpProcUnits()))
				    			{
				    				hospStayAmountDOP = hospStayAmountDOP + (Long.parseLong(surgList.get(i).getHospStayAmt()) * Long.parseLong(surgList.get(i).getOpProcUnits()));
				    			}	
			    				// End-Get Hospital Stay Amount for each DOP
	    					}
	    				}	
	    				
	    				
	    				
	    				/*//Calculate Common category Amount
	    				if(tempCategory!=null && "".equalsIgnoreCase(tempCategory))
	    				{	
	    					if(surgList.get(i).getTest()!=null && !"".equalsIgnoreCase(surgList.get(i).getTest()))
	    						tempCategory= surgList.get(i).getTest();
	    					if(surgList.get(i).getCommonCatAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getCommonCatAmt()))
	    						tempCommonCatAmount= Long.parseLong(surgList.get(i).getCommonCatAmt());
	    				}
	    				else if(surgList.get(i).getTest()!=null && tempCategory.equalsIgnoreCase(surgList.get(i).getTest()))
	    				{
	    					if(surgList.get(i).getCommonCatAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getCommonCatAmt()))
	    					{
	    						if(tempCommonCatAmount< Long.parseLong(surgList.get(i).getCommonCatAmt()))
	    						{
	    							tempCommonCatAmount= Long.parseLong(surgList.get(i).getCommonCatAmt());
	    							if(i==surgList.size()-1)
		    						{
	    								if(surgList.get(i-1).getCommonCatAmt()!=null && !"".equalsIgnoreCase(surgList.get(i-1).getCommonCatAmt()))
	    								{
			    							if(!(tempCommonCatAmount==Long.parseLong(surgList.get(i-1).getCommonCatAmt())))
			    									checkLastCategoryAmt= tempCommonCatAmount;
	    								}
		    						}
	    						}
	    						
	    					}
	    					
	    				}
	    				if(surgList.get(i).getTest()!=null && !tempCategory.equalsIgnoreCase(surgList.get(i).getTest()))
	    				{
	    					checkLastCategoryAmt= checkLastCategoryAmt+tempCommonCatAmount;
	    					tempCategory= surgList.get(i).getTest();
	    					if(surgList.get(i).getCommonCatAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getCommonCatAmt()))
	    						tempCommonCatAmount= Long.parseLong(surgList.get(i).getCommonCatAmt());
	    					commonCatAmount= commonCatAmount+tempCommonCatAmount;
	    					addSurgAmt=true;
	    					i--;
	    				}
	    				else
	    					addSurgAmt=false;*/
	    				
	    			}
	    			if(surgList.get(i).getTest()!=null && !"".equalsIgnoreCase(surgList.get(i).getTest()) && !tempCategory.equalsIgnoreCase(surgList.get(i).getTest()) )
	    			{
	    				if(i==0)
	    				{
	    					if(surgList.get(i).getCommonCatAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getCommonCatAmt()))
	    						str= surgList.get(i).getCommonCatAmt()+"~";
	    				}
	    				else
	    				{
	    					list.add(str);
	    					str="";
	    					if(surgList.get(i).getCommonCatAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getCommonCatAmt()))
	    						str= str+ surgList.get(i).getCommonCatAmt()+"~";
	    				}
	    			}
	    			else
	    			{
	    				if(surgList.get(i).getCommonCatAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getCommonCatAmt()))
    						str= str+ surgList.get(i).getCommonCatAmt()+"~";
	    			}
	    			if(i==surgList.size()-1)
	    			{
	    				list.add(str);
	    			}
	    			if(surgList.get(i).getTest()!=null)
	    				tempCategory= surgList.get(i).getTest();
	    		}
	    	}
	    	for(int len=0; len<list.size(); len++)
	    	{
	    		if(list.get(len)!=null && !"".equalsIgnoreCase(list.get(len)))
	    		{
	    			Long maxCatAmt=0L;
	    			String arr[]= list.get(len).split("~");
	    			for(int i=0; i<arr.length; i++)
	    			{
	    				if(arr[i]!=null && !"".equalsIgnoreCase(arr[i]))
	    				{
	    					if(i==0)
	    					{
	    						maxCatAmt= Long.parseLong(arr[i]);
	    					}
	    					else
	    					{
	    						if(maxCatAmt< Long.parseLong(arr[i]))
	    								maxCatAmt= Long.parseLong(arr[i]);
	    					}
	    				}
	    			}
	    			commonCatAmount= commonCatAmount+maxCatAmt;
	    		}
	    	}
	    	hospStayAmount = hospStayAmount + hospStayAmountDOP;
	    	
	    	totAmt= surgAmt+hospStayAmount+commonCatAmount+(noOfDays*nabhAmt);
	    	//dopTeethPrice would be >0 for only TG Dental Special Proc's
	    	
	    	if(dopTeethPrice>0)
	    		totAmt=totAmt+dopTeethPrice;
	    	/*if(deductAmount>0)
	    		totAmt=totAmt-deductAmount;*/
	    	
	    	/*if(preauthVO.getScheme()!=null && preauthVO.getScheme().equalsIgnoreCase("CD201")){
	    		if(preauthVO.getNabhFlg()!=null && preauthVO.getNabhFlg().equalsIgnoreCase("Y")){
	    			totAmt = (long) (totAmt + (totAmt*0.25)) ;
	    		}
	    	}*/
	    	preauthVO.setTotPackgAmt(totAmt.toString());
	    	preauthVO.setPreauthPckgAmt(totAmt.toString());
	    	
	    	/**
			 * Calculate the Comorbid Amount
			 */
	    	if(preauthVO.getComorBidVals()!=null && !"".equalsIgnoreCase(preauthVO.getComorBidVals()))
	    	{
	    		String comorbidList[]= preauthVO.getComorBidVals().split("~");
	    		List<String> comorBidValList= new ArrayList<String>();
	    		for(int len=0; len<comorbidList.length; len++)
	    		{
	    			comorBidValList.add(comorbidList[len]);
	    		}
	    		/**
				 * Get the amount associated to each comorbid
				 */
	    		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
	    		criteriaList.add(new GenericDAOQueryCriteria("comorbidId",comorBidValList,GenericDAOQueryCriteria.CriteriaOperator.IN));
	    		List<EhfmComorbid> ehfmComorbid = genericDao.findAllByCriteria( EhfmComorbid.class, criteriaList) ;	
	    		
	    		if(ehfmComorbid!=null)
	    		{
	    			for(int i=0; i<ehfmComorbid.size(); i++)
	    			{
	    				if(ehfmComorbid.get(i).getComorbidVal()!=null && !"".equalsIgnoreCase(ehfmComorbid.get(i).getComorbidVal()))
	    					comorbidAmt=comorbidAmt+ Float.parseFloat(ehfmComorbid.get(i).getComorbidVal());
	    			}
	    		}
	    		
	    		preauthVO.setComorBidAmt(String.valueOf(Math.round(comorbidAmt)));
	    	}
	    	else
	    		preauthVO.setComorBidAmt("");
		}
			
			/**
			 * update admission date preauth Date in EhfCase 
			 */
			EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, preauthVO.getCaseId());
			EhfPatient ehfPatient = genericDao.findById(EhfPatient.class, String.class, preauthVO.getPatientID());
			
			SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		    String crtDate=sdfw.format(date);
		    Date crtDt = sdfw.parse((sdfw.format(date)));
		    
		    if(ehfCase != null && ehfmWorkflowNextStatus !=null)
			{
		    	if(ehfmWorkflowNextStatus.getNextStatus() .equalsIgnoreCase(config.getString("preauth_partail_save")))
		    	{
		    		if(preauthVO.getAdmissionDate() != null)
		    		ehfCase.setCsAdmDt(sdf.parse(preauthVO.getAdmissionDate()));
		    		ehfCase.setPendingFlag("Y");
		    		ehfCase.setPreauthTotPckgAmt( preauthVO.getTotPackgAmt());
					if(preauthVO.getComorBidAmt() != null && !preauthVO.getComorBidAmt().equalsIgnoreCase("") && !preauthVO.getComorBidAmt().equalsIgnoreCase("NaN"))
					{
						ehfCase.setComorBidAmt(Long.parseLong(preauthVO.getComorBidAmt()));	
						ehfCase.setPreauthTotalPackageAmt(Long.parseLong( preauthVO.getPreauthPckgAmt()) + Long.parseLong(preauthVO.getComorBidAmt()));
						preauthVO.setAuditAmt(String.valueOf(Long.parseLong( preauthVO.getPreauthPckgAmt()) + Long.parseLong(preauthVO.getComorBidAmt())));
					}
					else
					{
						ehfCase.setComorBidAmt((long) 0);
						ehfCase.setPreauthTotalPackageAmt(Long.parseLong( preauthVO.getTotPackgAmt()));	
						preauthVO.setAuditAmt(preauthVO.getTotPackgAmt());
					}
					ehfCase.setComorbidVals(preauthVO.getComorBidVals());	
					ehfCase.setPreauthPckgAmt(preauthVO.getPreauthPckgAmt());
					ehfCase.setProcedureConsent(preauthVO.getProcedureConsent());
					ehfCase.setMedCardioClearence(preauthVO.getMedCardioClearence());
					ehfCase.setBloodTransfusion(preauthVO.getBloodTransfusion());
		    	}
		    	
		    	else
				{
		    		if(ehfmWorkflowNextStatus.getNextStatus() !=null)
		    			{
			    			if((ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_medco_initiated"))
									|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_ppd_pending_updated"))
									|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_ptd_Pending_Updated"))
									|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_eo_pendingUpdated"))
									|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_medco_ceoPendingUpdate"))
									|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_organ_group_pending_updated"))
									|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_organ_ptd_pending_updated"))
									|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_organ_eo_pending_updated"))
									|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_organ_ceo_pending_updated"))								
									|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_cochlear_group_pending_updated"))
									|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_cochlear_ptd_pending_updated"))
									|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_cochlear_eo_pending_updated"))
									|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_cochlear_ceo_pending_updated")))
									|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_spoke_forwarded"))
									|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_spoke_pending_updated")))
			    				{
			    					ehfCase.setPreauthTotPckgAmt( preauthVO.getTotPackgAmt());
				    				if(preauthVO.getComorBidAmt() != null && !preauthVO.getComorBidAmt().equalsIgnoreCase("") && !preauthVO.getComorBidAmt().equalsIgnoreCase("NaN"))
										{
											ehfCase.setComorBidAmt(Long.parseLong(preauthVO.getComorBidAmt()));	
											ehfCase.setPreauthTotalPackageAmt(Long.parseLong( preauthVO.getPreauthPckgAmt()) + Long.parseLong(preauthVO.getComorBidAmt()));
										}
									else
										{
											ehfCase.setComorBidAmt((long) 0);
											ehfCase.setPreauthTotalPackageAmt(Long.parseLong( preauthVO.getTotPackgAmt()));	
										}
									ehfCase.setComorbidVals(preauthVO.getComorBidVals());	
									ehfCase.setPreauthPckgAmt(preauthVO.getPreauthPckgAmt());
			    				}
		    			}
		    			
		    		
		    		
				    ehfCase.setPendingFlag("");
				    if(preauthVO.getComorBidAmt() != null && !preauthVO.getComorBidAmt().equalsIgnoreCase("") && !preauthVO.getComorBidAmt().equalsIgnoreCase("NaN") && !"null".equalsIgnoreCase(preauthVO.getComorBidAmt()))
				    	preauthVO.setAuditAmt(String.valueOf(Long.parseLong( preauthVO.getTotPackgAmt()) + Long.parseLong(preauthVO.getComorBidAmt())));
				    else
				    	preauthVO.setAuditAmt(preauthVO.getTotPackgAmt());
				}
			   
				saveObjects.add(ehfCase);
				
				/**
				 * saving amounts in EhfCasePreauthAmounts
				 */
				EhfCasePreauthAmounts ehfCasePreauthAmounts ;
				ehfCasePreauthAmounts = genericDao.findById(EhfCasePreauthAmounts.class, String.class, preauthVO.getCaseId());
				if(ehfCasePreauthAmounts == null)
					ehfCasePreauthAmounts = new EhfCasePreauthAmounts();
				ehfCasePreauthAmounts.setCaseId(preauthVO.getCaseId());
				if(ehfmWorkflowNextStatus.getNextStatus() .equalsIgnoreCase(config.getString("preauth_partail_save")) 
						)
				{
					if(ehfCase.getComorBidAmt()!=null)
						ehfCasePreauthAmounts.setComorbidAmt(ehfCase.getComorBidAmt().doubleValue());
					if(ehfCase.getPreauthPckgAmt()!=null && !"".equalsIgnoreCase(ehfCase.getPreauthPckgAmt()))
						ehfCasePreauthAmounts.setPackgAmt(Double.parseDouble(ehfCase.getPreauthPckgAmt()));
					if(ehfCase.getPreauthTotalPackageAmt()!=null)
						ehfCasePreauthAmounts.setTotPackgAmt((ehfCase.getPreauthTotalPackageAmt()).doubleValue());
					else if(ehfCase.getPreauthTotPckgAmt()!=null && !"".equalsIgnoreCase(ehfCase.getPreauthTotPckgAmt()))
						ehfCasePreauthAmounts.setTotPackgAmt(Double.parseDouble(ehfCase.getPreauthTotPckgAmt()));
				}
				else if(ehfmWorkflowNextStatus.getNextStatus() !=null)
    			{
	    			if((ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_medco_initiated"))
							|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_ppd_pending_updated"))
							|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_ptd_Pending_Updated"))
							|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_eo_pendingUpdated"))
							|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_medco_ceoPendingUpdate"))
							|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_organ_group_pending_updated"))
							|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_organ_ptd_pending_updated"))
							|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_organ_eo_pending_updated"))
							|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_organ_ceo_pending_updated"))
							|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_cochlear_group_pending_updated"))
							|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_cochlear_ptd_pending_updated"))
							|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_cochlear_eo_pending_updated"))
							|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_cochlear_ceo_pending_updated")))
							|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_spoke_forwarded"))
							|| ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_spoke_pending_updated")))
	    				{
		    				if(ehfCase.getComorBidAmt()!=null)
								ehfCasePreauthAmounts.setComorbidAmt(ehfCase.getComorBidAmt().doubleValue());
							if(ehfCase.getPreauthPckgAmt()!=null && !"".equalsIgnoreCase(ehfCase.getPreauthPckgAmt()))
								ehfCasePreauthAmounts.setPackgAmt(Double.parseDouble(ehfCase.getPreauthPckgAmt()));
							if(ehfCase.getPreauthTotalPackageAmt()!=null)
								ehfCasePreauthAmounts.setTotPackgAmt((ehfCase.getPreauthTotalPackageAmt()).doubleValue());
							else if(ehfCase.getPreauthTotPckgAmt()!=null && !"".equalsIgnoreCase(ehfCase.getPreauthTotPckgAmt()))
								ehfCasePreauthAmounts.setTotPackgAmt(Double.parseDouble(ehfCase.getPreauthTotPckgAmt()));
	    				}
    			}
				if(ehfmWorkflowNextStatus.getNextStatus() .equalsIgnoreCase(config.getString("preauth_medco_initiated")) 
						|| currStatus.equalsIgnoreCase(config.getString("preauth_ptd_pending"))
						|| currStatus.equalsIgnoreCase(config.getString("preauth_spoke_forwarded")))
				{
					ehfCasePreauthAmounts.setPreauthInitiatedDt(new java.sql.Timestamp(date.getTime()));
					ehfCasePreauthAmounts.setCrtDt(new java.sql.Timestamp(date.getTime()));
					ehfCasePreauthAmounts.setCrtUsr(preauthVO.getCruUsr());
				}
					
				ehfCasePreauthAmounts.setLstUpdDt(new java.sql.Timestamp(date.getTime()));
				ehfCasePreauthAmounts.setLstUpdUsr(preauthVO.getCruUsr());
				saveObjects.add(ehfCasePreauthAmounts);
			}
		    
		    genericDao.saveAll(saveObjects);
		    
		    /**
	    	 * By Srikalyan for Direct Preauthorization approval for 
	    	 * TG Patients in Govt Hospitals
	    	 */
		    if((currStatus!=null && currStatus.equalsIgnoreCase(config.getString("preauth_inpatient_caseReg"))
	    			 && preauthVO.getButtonVal()!=null && ehfmWorkflowNextStatus!=null && ehfmWorkflowNextStatus.getNextStatus()!=null
	    			 && preauthVO.getButtonVal().equalsIgnoreCase(config.getString("Preauth.Medco.Approve.Button"))
	    			 && ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("Preauth.Medco.Immediate.Approve")))
		    	|| (currStatus!=null && currStatus.equalsIgnoreCase(config.getString("preauth_spoke_forwarded"))
		    		&& preauthVO.getButtonVal()!=null && ehfmWorkflowNextStatus!=null && ehfmWorkflowNextStatus.getNextStatus()!=null
		    		&& preauthVO.getButtonVal().equalsIgnoreCase(config.getString("Preauth.Medco.Approve.Button"))
		    		&& ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_hub_approved")))
		    	|| (currStatus!=null && currStatus.equalsIgnoreCase(config.getString("preauth_spoke_pending_updated"))
			    		&& preauthVO.getButtonVal()!=null && ehfmWorkflowNextStatus!=null && ehfmWorkflowNextStatus.getNextStatus()!=null
			    		&& preauthVO.getButtonVal().equalsIgnoreCase(config.getString("Preauth.Medco.Approve.Button"))
			    		&& ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_hub_approved"))))
	    		 
	    	 	{
		    	
		    	if((currStatus!=null && currStatus.equalsIgnoreCase(config.getString("preauth_inpatient_caseReg"))
	    			 && preauthVO.getButtonVal()!=null && ehfmWorkflowNextStatus!=null && ehfmWorkflowNextStatus.getNextStatus()!=null
	    			 && preauthVO.getButtonVal().equalsIgnoreCase(config.getString("Preauth.Medco.Approve.Button"))
	    			 && ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("Preauth.Medco.Immediate.Approve"))))
	    			 {
		    		msg = config.getString("preauth_msg_CD651");	
					
	    			 }
		    	if((currStatus!=null && currStatus.equalsIgnoreCase(config.getString("preauth_spoke_forwarded"))
			    		&& preauthVO.getButtonVal()!=null && ehfmWorkflowNextStatus!=null && ehfmWorkflowNextStatus.getNextStatus()!=null
			    		&& preauthVO.getButtonVal().equalsIgnoreCase(config.getString("Preauth.Medco.Approve.Button"))
			    		&& ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_hub_approved")))
			    	|| (currStatus!=null && currStatus.equalsIgnoreCase(config.getString("preauth_spoke_pending_updated"))
				    		&& preauthVO.getButtonVal()!=null && ehfmWorkflowNextStatus!=null && ehfmWorkflowNextStatus.getNextStatus()!=null
				    		&& preauthVO.getButtonVal().equalsIgnoreCase(config.getString("Preauth.Medco.Approve.Button"))
				    		&& ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_hub_approved"))))
		    	{
		    		msg = config.getString("preauth_msg_CD654");	
					
		    	}
	    		 	boolean statusGovTG=checkTGPatReg(ehfCase.getCaseHospCode(),ehfCase.getCaseId());
	    		 	String hubFlg = checkHubHosp(ehfCase.getCaseHospCode());
	    		 	if(statusGovTG==true || (hubFlg!=null && hubFlg.equalsIgnoreCase("HUB")))
	    		 		{
	    		 			/**
	    		 			 * Claim Number Generation
	    		 			 */
		    		 		 StringBuffer claimNum = new StringBuffer();
		    					
		    				 Calendar cal = Calendar.getInstance () ;
		    				 String lStrYear = cal.get ( Calendar.YEAR ) + "" ;
		    				 StringBuffer locQuery = new StringBuffer();	 	
		    				 locQuery.append(" select lm.locShrtName as ID , ep.districtCode as VALUE  ,");
		    				 locQuery.append("  ep.cardNo as LVL  ");
		    				 locQuery.append(" from EhfCase ec , EhfPatient ep , EhfmLocations lm where  ");
		    				 locQuery.append(" ec.caseId = '"+ehfCase.getCaseId()+"' and ec.casePatientNo = ep.patientId ");
		    				 locQuery.append(" and ep.districtCode = lm.id.locId ");
		    				 
		    				 List<LabelBean> lstClaimDtls = genericDao.executeHQLQueryList(LabelBean.class, locQuery.toString());
		    				 if(lstClaimDtls != null && lstClaimDtls.size() >0)
		    				 	{
			    					 /**
			    					  * get latest Ip case for claim number increment
			    					  */

		    					
			    					 locQuery = new StringBuffer();
			    					 locQuery.append(" select ec.preauthFwdDate as crtDt , ec.claimNo as ID , ep.patientIpop as VALUE, year(ec.preauthFwdDate) as COUNT ,current_date() as crtDt  ");
			    					 locQuery.append(" from EhfCase ec , EhfPatient ep where ec.casePatientNo = ep.patientId and ep.cardNo ='"+lstClaimDtls.get(0).getLVL()+"' ");
			    					 locQuery.append(" and ep.patientIpop ='IP' and ec.caseId  not in ('"+ehfCase.getCaseId()+"') and ec.claimNo is not null  ");
			    					 locQuery.append(" and ec.preauthFwdDate is not null and year(ec.preauthFwdDate) ='"+Integer.parseInt(lStrYear)+"' order by ec.preauthFwdDate desc ");
			    					 List<LabelBean> lstClaimNos = genericDao.executeHQLQueryList(LabelBean.class, locQuery.toString());

		    					
			    					// get claim number from prev calim No
			    					if(lstClaimNos != null && lstClaimNos.size() > 0
			    							&& lstClaimNos.get(0)!=null
			    							&& lstClaimNos.get(0).getID()!=null )
			    						{
			    							String prevClaimNo = lstClaimNos.get(0).getID();
			    							if(prevClaimNo != null)
			    								{
							    					String prevClaimLstSeq =  prevClaimNo.substring(prevClaimNo.lastIndexOf("/")+1); 
							    					claimNum.append(prevClaimNo.substring(0,prevClaimNo.lastIndexOf("/")));
							    				    claimNum.append("/");
							    					claimNum.append(Integer.parseInt(prevClaimLstSeq)+1);
			    								}
			    						}
			    					else
				    					{
				    						// get sequence form sequnece Mst

											claimNum.append(config.getString("preauth_claimNo_payee"));	
				    						
				    						claimNum.append("/");
				    						claimNum.append(lstClaimDtls.get(0).getID());
				    						claimNum.append("/");
				    						claimNum.append(lStrYear);
				    						claimNum.append("/");
				    						claimNum.append(lstClaimDtls.get(0).getVALUE());
				    						claimNum.append(commonService.getSequence(config.getString("claim_seq_no")));
				    						claimNum.append("/");
				    						claimNum.append("1");
				    					}
				    					ehfCase.setClaimNo(claimNum.toString());
				    					//msg = msg +" and claim number is "+ claimNum.toString() ;
				    				}
		    				 	EhfCasePreauthAmounts ehfCasePreauthAmounts ;
		    					ehfCasePreauthAmounts = genericDao.findById(EhfCasePreauthAmounts.class, String.class, preauthVO.getCaseId());
		    					if(ehfCasePreauthAmounts != null)
		    						{
		    							/**
		    							 * saving respective columns 
		    							 */
		    							Double totAmtAppr = (double) 0;
		    							/**
		    							 * Medco Approval Fields
		    							 */
		    							ehfCase.setCsPreauthDt(new java.sql.Timestamp(date.getTime()));
		    							ehfCasePreauthAmounts.setPreauthInitiatedDt(new java.sql.Timestamp(date.getTime()));
		    							/**
		    							 * Mithra Approval Fields
		    							 */
		    							ehfCasePreauthAmounts.setPreauthFwdDt(new java.sql.Timestamp(date.getTime()));
		    							ehfCase.setPreauthFwdDate(new java.sql.Timestamp(date.getTime()));
		    							/**
		    							 * PPD Approval Fields
		    							 */
		    							ehfCasePreauthAmounts.setPpdAppvDate(new java.sql.Timestamp(date.getTime()));
		    							
		    							/**
		    							 * PTD Approval Fields
		    							 */
		    							ehfCasePreauthAmounts.setPtdAppvDate(new java.sql.Timestamp(date.getTime()));
		    							if(ehfCase.getPreauthPckgAmt()!=null)
		    								{
		    									ehfCasePreauthAmounts.setPtdAppvPackageAmt(Double.parseDouble(ehfCase.getPreauthPckgAmt()));
		    									totAmtAppr = totAmtAppr + Double.parseDouble(ehfCase.getPreauthPckgAmt());
		    								}
		    							if(ehfCase.getComorBidAmt()!=null)
		    								{
		    									ehfCasePreauthAmounts.setPtdAppvComorbidAmt((ehfCase.getComorBidAmt()).doubleValue());
		    									totAmtAppr = totAmtAppr + ehfCase.getComorBidAmt().doubleValue();
		    								}
		    							ehfCasePreauthAmounts.setPtdAppvTotalPackgAmt(totAmtAppr);
		    							if(totAmtAppr > Integer.parseInt(config.getString("preauth_package_amt_limit")))
		    								{
			    								ehfCase.setCeoApprovalFlag(config.getString("activeY"));
	    										preauthVO.setStatusToSave(config.getString("Preauth.Medco.Recommend.Approve"));

	    		    							/**
	    		    							 * EO Approval Fields--Only for Cases having Amounts greater than 2 Lakhs
	    		    							 */
	    										ehfCasePreauthAmounts.setEoappvDt(new java.sql.Timestamp(date.getTime()));
	    										if(ehfCase.getPreauthPckgAmt()!=null)
    												ehfCasePreauthAmounts.setEoAppvPackgAmt(Double.parseDouble(ehfCase.getPreauthPckgAmt()));
	    										if(ehfCase.getComorBidAmt()!=null)
    												ehfCasePreauthAmounts.setEoAppvComorbidAmt((ehfCase.getComorBidAmt().doubleValue()));
	    										if(totAmtAppr!=null)
	    											ehfCasePreauthAmounts.setEoAppvTotalAmt(totAmtAppr);
		    								}
		    							else
		    								{
		    									if(ehfCase.getPreauthPckgAmt() != null)
			    								ehfCase.setPckAppvAmt(Long.parseLong(ehfCase.getPreauthPckgAmt()));
		    									ehfCase.setPreauthAprvDate(new java.sql.Timestamp(date.getTime()));
		    									if(ehfCase.getComorBidAmt()!= null)
		    										ehfCase.setComorbidAppvAmt(ehfCase.getComorBidAmt());
		    								}

		    							ehfCasePreauthAmounts.setLstUpdDt(new java.sql.Timestamp(date.getTime()));
		    							ehfCasePreauthAmounts.setLstUpdUsr(preauthVO.getCruUsr());
		    							
		    							preauthVO.setAuditAmt(ehfCasePreauthAmounts.getPtdAppvTotalPackgAmt().toString());
		    							
			    						genericDao.save(ehfCase);
			    						genericDao.save(ehfCasePreauthAmounts);
			    						
			    						if("true".equalsIgnoreCase(config.getString("SmsRequired")))
		   								     {
		   										 String smsNextVal=commonService.getSequence(config.getString("PATIENT_SMS_SNO"));
		   								     	 if(ehfPatient.getContactNo()!=null && !ehfPatient.getContactNo().equals(""))
		   								     		{
				    								     	String lStrResultMsg=null;
				    								     	PatientSmsVO patientSmsVO=new PatientSmsVO();
				    								     	patientSmsVO.setSerialNo(Long.parseLong(smsNextVal));
				    								     	patientSmsVO.setPhoneNo(ehfPatient.getContactNo().toString());
				    								     	patientSmsVO.setSmsText("Dear "+ehfPatient.getName().trim()+" , Your case No.  "+preauthVO.getCaseId()+"  has been " + msg +" in "+getHospName(ehfPatient.getRegHospId()));
				    								     	patientSmsVO.setEmpCode(ehfPatient.getEmployeeNo());
				    								     	patientSmsVO.setEmpName(ehfPatient.getName().trim());
				    								     	patientSmsVO.setCrtUsr(preauthVO.getCruUsr());
				    								     	patientSmsVO.setCrtDt(crtDt);
				    								     	patientSmsVO.setSmsAction("Case with caseId " +preauthVO.getCaseId() + msg);
				    								     	patientSmsVO.setPatientId(ehfPatient.getPatientId());
				    								     	commonService.sendPatientSms(patientSmsVO);
		   								     	
		   								     		}
		   								     }
			    						
		    						}
	    		 		}
	    	 	}
	    	/**
	    	 * End for Direct Preauthorization
	    	 */
		    
		    /**
			 * ppd grp flag
			 */
			
			query = new StringBuffer();
			query.append(" select ec.asriCatCode as VALUE from EhfCaseTherapy ec , EhfmSpecialities es where ec.asriCatCode = es.disMainId  and ec.caseId = '"+preauthVO.getCaseId()+"' and ec.activeyn='Y' ");
			query.append(" group by  ec.asriCatCode ");
			List<LabelBean> count = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
			if(count != null && count.size() > 1)
				ehfCase.setPpdGrpFlg("Y");
			else
				ehfCase.setPpdGrpFlg("N"); 
			ehfCase.setCochlearYN(preauthVO.getCochlearYN());
			ehfCase.setOrganTransYN(preauthVO.getOrganTransYN());
			
			genericDao.save(ehfCase);
			
			if(ehfmWorkflowNextStatus != null)
			{
				EhfmCmbDtls asrimCombo = getAsrimCombo(ehfmWorkflowNextStatus.getNextStatus(),preauthVO.getLangId());
				
				if(ehfCase.getCeoApprovalFlag()==null ||
						(ehfCase.getCeoApprovalFlag()!=null && !ehfCase.getCeoApprovalFlag().equalsIgnoreCase("Y")))
					preauthVO.setStatusToSave(ehfmWorkflowNextStatus.getNextStatus());
					
				if(preauthVO.getStatusToSave()==null)	
					preauthVO.setStatusToSave(ehfmWorkflowNextStatus.getNextStatus());
				
				if(asrimCombo != null)
				{
					preauthVO.setRemarks(preauthVO.getRemarks());	
					saveAstitAudit(preauthVO);
				}
				if(ehfmWorkflowNextStatus.getNextStatus() .equalsIgnoreCase(config.getString("preauth_partail_save")) )
				{
					preauthVO.setStatusToSave(ehfmWorkflowNextStatus.getId().getCurrStatus());	
				}
				else
				preauthVO.setStatusToSave(ehfmWorkflowNextStatus.getNextStatus());
				
				if((ehfCase.getCeoApprovalFlag()!=null 
						&& ehfCase.getCeoApprovalFlag().equalsIgnoreCase("Y")) && preauthVO.getButtonVal()!=null 
		    			&& preauthVO.getButtonVal().equalsIgnoreCase(config.getString("Preauth.Medco.Approve.Button")))
					preauthVO.setStatusToSave(config.getString("Preauth.Medco.Recommend.Approve"));
				
				updateCaseResult= updateAsritCase(preauthVO);
				
			}
			
			msg = config.getString("preauth_msg_"+ehfmWorkflowNextStatus.getNextStatus());
			if(ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("Preauth.Medco.Immediate.Approve")))
					msg += " with Claim Number : "+ehfCase.getClaimNo();
			if(ehfmWorkflowNextStatus.getNextStatus().equalsIgnoreCase(config.getString("Preauth.Medco.Recommend.Approve")))
					msg += " with Claim Number : "+ehfCase.getClaimNo()+" and requires CEO's approval";
			
			/**
			 * send sms and email
			 */
			if(updateCaseResult!=null && !"".equalsIgnoreCase(updateCaseResult) && "success".equalsIgnoreCase(updateCaseResult))
			{
				 /*if("true".equalsIgnoreCase(config.getString("SmsRequired")))
			     {
					 String smsNextVal=commonService.getSequence(config.getString("PATIENT_SMS_SNO"));
			     	if(ehfPatient.getContactNo()!=null && !ehfPatient.getContactNo().equals(""))
			     	{
			     	String lStrResultMsg=null;
			     	PatientSmsVO patientSmsVO=new PatientSmsVO();
			     	patientSmsVO.setSerialNo(Long.parseLong(smsNextVal));
			     	patientSmsVO.setPhoneNo(ehfPatient.getContactNo().toString());
			     	patientSmsVO.setSmsText("Dear "+ehfPatient.getName().trim()+" , Your case with case Id "+preauthVO.getCaseId()+"  has been " + msg +" in "+getHospName(ehfPatient.getRegHospId()));
			     	patientSmsVO.setEmpCode(ehfPatient.getEmployeeNo());
			     	patientSmsVO.setEmpName(ehfPatient.getName().trim());
			     	patientSmsVO.setCrtUsr(preauthVO.getCruUsr());
			     	patientSmsVO.setCrtDt(crtDt);
			     	patientSmsVO.setSmsAction("Case with caseId " +preauthVO.getCaseId() + msg);
			     	patientSmsVO.setPatientId(ehfPatient.getPatientId());
			     	lStrResultMsg=commonService.sendPatientSms(patientSmsVO);
			     	
			     	}
			     }*/
				if (config.getBoolean("EmailRequired")) 
			    {
			    	String mailId=null;
			    	if(ehfPatient.getEmailId()!=null && !ehfPatient.getEmailId().equals(""))
			    	{
				     	mailId=ehfPatient.getEmailId();
				     	String[] emailToArray = {mailId};
				     	EmailVO emailVO = new EmailVO();
						emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
						emailVO.setTemplateName(config.getString("EHFPreauthtemplateName"));
						emailVO.setFromEmail(config.getString("emailFrom"));
						emailVO.setToEmail(emailToArray);
						emailVO.setSubject(config.getString("preauthemailSubject"));
						Map<String, String> model = new HashMap<String, String>();
						model.put("patientName", ehfPatient.getName().trim());
						model.put("registeredHosp", getHospName(ehfPatient.getRegHospId()));
						model.put("status", msg);
						model.put("statusDate",crtDate);
						model.put("CaseId",preauthVO.getCaseId());
						if(ehfCase!=null)
							{
								if(ehfCase.getCaseNo()!=null)
									{
										model.put("CaseId",ehfCase.getCaseNo());
									}
								else
									model.put("CaseId",preauthVO.getCaseId());
							}	
						if(ehfPatient!=null)
							{
								if(ehfPatient.getPatientScheme()!=null)
									{
										if(ehfPatient.getPatientScheme().equalsIgnoreCase(config.getString("Scheme.JHS")))
											{
												emailVO.setSubject(config.getString("preauthemailSubjectJournalist"));
												emailVO.setFromEmail(config.getString("emailFromJournalist"));
												emailVO.setTemplateName(config.getString("EHFPreauthtemplateNameJourn"));
												commonService.generateNonImageMail(emailVO, model);
											}
										else
											{
												emailVO.setTemplateName(config.getString("EHFPreauthtemplateName"));
												commonService.generateMail(emailVO, model);
											}
									}
								else
									{
										emailVO.setTemplateName(config.getString("EHFPreauthtemplateName"));
										commonService.generateMail(emailVO, model);
									}
							}
			    	}
				}
			}
			
		}
		else
			msg="Could not update Preauthorization";
	}
	catch(Exception e)
	{
//		gLogger.error("Exception in method savePreauth() of PreauthServiceImpl--> "+ e.getMessage());
		e.printStackTrace();
		msg ="Not updated";
	}
	
	return msg;
}

private String getProcCode(String caseId) {
	// TODO Auto-generated method stub
	String procCode=null;
	List<LabelBean> procDtls = null;
	StringBuffer query=new StringBuffer();
	try{
		query.append("select icd_Proc_Code as ID from ehf_case a,ehf_case_therapy b where a.case_id=b.case_id and b.activeyn='Y' ");
		query.append(" and  b.case_id='"+caseId+"' ");
		procDtls=genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		if(procDtls!=null && procDtls.size()>0)
		{
			procCode=procDtls.get(0).getID();
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return procCode;
}
private String getHospType(String caseId) {
	// TODO Auto-generated method stub
	String hospType=null;
	List<LabelBean> hospDtls = null;
	StringBuffer query=new StringBuffer();
	try{
		query.append("select a.hub_spoke as ID from ehfm_hospitals a,ehf_case b where a.hosp_id=b.case_hosp_code and ");
		query.append("b.case_id='"+caseId+"' ");
		hospDtls=genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		if(hospDtls!=null && hospDtls.size()>0)
		{
			hospType=hospDtls.get(0).getID();
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return hospType;
}
/**
 * @author Kalyan
 * @return boolean 
 * @param current Procedure Code and Dependent Procedure Code 
 */
private boolean checkDependentProc(String caseId , String procCode , String comboCode)
	{
		boolean returnValue=false;
		try
			{
				StringBuffer query=new StringBuffer();
				
				query.append( " select distinct c.icdProcCode as ICDCODE " );
				query.append( " from EhfCase a , EhfPatient b , EhfCaseTherapy c , " );
				query.append( " EhfPatient d , EhfCase e " );
				query.append( " where a.caseId ='"+caseId+"' " );
				query.append( " and c.icdProcCode ='"+comboCode+"' ");
				query.append( " and a.casePatientNo = b.patientId " );
				query.append( " and b.cardNo = d.cardNo " );
				query.append( " and c.caseId = e.caseId " );
				query.append( " and d.patientId = e.casePatientNo and c.activeyn ='Y' " );
				query.append( " and e.caseStatus not in ("+config.getString("Preauth_Cancelled_Status").replace('~', ',')+") ");
				query.append( " and c.crtDt > ( sysdate-365 ) " );
				
				query.append( " order by c.icdProcCode ");
				
				List<LabelBean> newBeanLst=genericDao.executeHQLQueryList(LabelBean.class, query.toString());
				if(newBeanLst!=null && newBeanLst.size()>0)
					returnValue=true;
				
			}
		catch(Exception e)
			{
				e.printStackTrace();
//				gLogger.error("Exception in method checkDependentProc() of PreauthServiceImpl--> "+ e.getMessage());
				return returnValue;
			}
		
		return returnValue;
	
	}
private EhfmWorkflowStatus getEhfmWorkflowStatus(PreauthVO preauthVO)
{
	EhfmWorkflowStatus ehfmWorkflowStatus = genericDao.findById(EhfmWorkflowStatus.class, EhfmWorkflowStatusId.class,new EhfmWorkflowStatusId(preauthVO.getCaseStatusId(),preauthVO.getUserRole(),preauthVO.getButtonVal()));	
    return ehfmWorkflowStatus;
}
private EhfmCmbDtls getAsrimCombo(String pStrcmbDtlId,String langId)
{
	EhfmCmbDtls asrimCombo = genericDao.findById(EhfmCmbDtls.class, String.class,pStrcmbDtlId);	
	return asrimCombo;
}
public String checkMandatoryAttch(String pStrCaseId , String pStrattachType,String ipFlag)
{
boolean organFlag=false;
boolean IUIFlag=false;
String msg = null,newBornBaby=null;
StringBuffer query = new StringBuffer();
Map<String,String> attachNames = new HashMap<String,String>();
EhfCase ehfCase=genericDao.findById(EhfCase.class, String.class,pStrCaseId );
//Added to check pharma bills are necessary
String checkPharmAttch = attachmentService.getPharmaBillAttch(pStrCaseId);
if(ehfCase.getNewBornBaby()!=null)
	newBornBaby=ehfCase.getNewBornBaby();
try
{
	int i=0;
String caseStatus =	getCaseStatus(pStrCaseId);	
query.append(" select distinct au.id.docType as ID , cm.cmbDtlName as VALUE ");
query.append(" from EhfCaseStatusAttach  ac, EhfAttachType au ,EhfmCmbDtls cm where au.activeYN ='Y' and ");
query.append(" ac.id.attachType = au.id.docType and ac.id.caseStatus ='"+caseStatus+"'  and au.docName='M' ");
if(newBornBaby!=null)
	{
		if(!newBornBaby.equalsIgnoreCase("Y"))
			query.append(" and cm.cmbDtlId not in ('"+config.getString("EHF.Claims.newBabyIDStat")+"')");
	}
else
	{
		query.append(" and cm.cmbDtlId not in ('"+config.getString("EHF.Claims.newBabyIDStat")+"')");
	}
					if(!"".equalsIgnoreCase(ipFlag) && "IPM".equalsIgnoreCase(ipFlag))
						{
							query.append(" and cm.cmbDtlId not in ('CD80')");
						}
					
					if(!"".equalsIgnoreCase(checkPharmAttch) && "Failure".equalsIgnoreCase(checkPharmAttch))
					{
						query.append(" and cm.cmbDtlId not in ('CD163')");
					}
					if(organFlag==false)
					{
						String attach=config.getString("organTransAttachments");
						String[] allAttach=attach.split("~");
						String finalAttach="";
						if(allAttach.length>0)
						{
							for(int j=0;j<allAttach.length;j++)
							{
								finalAttach=finalAttach+"'"+allAttach[j]+"',";
							}
							if(finalAttach.length()>0)
							{
								finalAttach=finalAttach.substring(0,finalAttach.length()-1);
							}
						}
						query.append(" and cm.cmbDtlId not in ("+finalAttach+")");
					}
					if(IUIFlag==false)
					{
						String attach=config.getString("IUIAttachments");
						String[] allAttach=attach.split("~");
						String finalAttach="";
						if(allAttach.length>0)
						{
							for(int j=0;j<allAttach.length;j++)
							{
								finalAttach=finalAttach+"'"+allAttach[j]+"',";
							}
							if(finalAttach.length()>0)
							{
								finalAttach=finalAttach.substring(0,finalAttach.length()-1);
							}
						}
						query.append(" and cm.cmbDtlId not in ("+finalAttach+")");
					}
query.append(" and cm.cmbDtlId =au.id.docType and au.id.updType ='"+pStrattachType+"' ");
List<LabelBean> lstdocType = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
query = new StringBuffer();
query.append(" select distinct attachType as ID");
query.append(" from EhfCaseDocAttch ac where ac.caseId = '"+pStrCaseId+"' and ac.activeYN='Y'  ");
List<LabelBean> lstAttachType = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
for(LabelBean attachType:lstAttachType)
{
	for(LabelBean labelBean:lstdocType)
	{
		if(labelBean.getID().equals(attachType.getID()))	
				{
			i++;
			attachNames.put(labelBean.getID(), labelBean.getVALUE());
				}
	}
}

if( i < lstdocType.size())
{
	for(LabelBean labelBean:lstdocType)
	{
		 if (!attachNames.containsKey(labelBean.getID())) {
			 if(msg == null || msg.equals(""))
			 msg = labelBean.getVALUE();
			 else
				 msg = msg+ " , "+labelBean.getVALUE(); 
			 System.out.println(labelBean.getID() + " : " + labelBean.getVALUE());	
		 }
		
	}
	if(msg == null || msg.equals(""))
	{
		msg ="Please add mandatory attachments";		
	}
	else
		{
		if(pStrattachType!=null && pStrattachType.equalsIgnoreCase("ehfClaim"))
			msg = "Please add " + msg;
			else
		    msg = "Please add " + msg + "  attachments ";
		}
	
}
else
{
	msg ="success";	
}
}
catch(Exception e)
{
e.printStackTrace();	
}
return msg;
}
public String getCaseStatus(String caseId)
{
	String caseStatus = null;
	try{
	EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, caseId);
	if(ehfCase != null)
	{
		caseStatus = ehfCase.getCaseStatus();
	}
	}catch(Exception e)
	{
	e.printStackTrace();	
	}
	return caseStatus;
}
public String  getceoApprovalFlag(String preauthCaseId)
{
	String ceoApprovalFlag = null;
	try{
	EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, preauthCaseId);
	if(ehfCase != null)
	{
		ceoApprovalFlag = ehfCase.getCeoApprovalFlag();
	}
	}catch(Exception e)
	{
	e.printStackTrace();	
	}
	return ceoApprovalFlag;	
}
private Long getMaxActOrder(String pStrCaseId)
{
	Long actOrder = (long) 0;
	try
	{
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
	    criteriaList.add(new GenericDAOQueryCriteria("id.caseId",pStrCaseId, GenericDAOQueryCriteria.CriteriaOperator.EQUALS ));
	    actOrder= genericDao.getMaxByPropertyCriteria(EhfAudit.class, "id.actOrder",criteriaList);
		
	}
	catch(Exception e)
	{
		//gLogger.error("actOrder"+actOrder);
//		gLogger.error("CaseId"+pStrCaseId);
		gLogger.error("Error in method getMaxActOrder of class ValidateCaseImpl.java--> "+ e.getMessage());
	}
	
	
	if(actOrder.intValue()==0)
	{
		StringBuffer query = new StringBuffer();
		try{
			query.append(" select max(a.id.actOrder) as IDVAL from EhfAudit a where a.id.caseId ='"+pStrCaseId+"' ");
			List<LabelBean> lstMax = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
			if(lstMax != null && lstMax.size() >0)
			{
				actOrder = 	lstMax.get(0).getIDVAL();
			}
		}catch(Exception e)
		{
			//gLogger.error("actOrder"+actOrder);
//   		 	gLogger.error("CaseId"+pStrCaseId);
			gLogger.error("Error in method getMaxActOrder of class PreauthServiceImpl.java--> "+ e.getMessage());	
		}
	}	
	return actOrder;
}
/***
 * @author Kalyan
 * @param Case ID
 * @return Dental Flag
 * @method Checks Whether the case is Dental or not  
 */
@Override
public String checkDentalCase(String caseId)
	{
	 	String returnMsg="N";
	 	try 	
	 		{
	 			StringBuffer query=new StringBuffer();
	 			query.append( " select a.asriCatCode as CATID " );
	 			query.append( " from EhfCaseTherapy a " );
	 			query.append( " where a.caseId = '"+caseId+"' " );
	 			query.append( " and a.activeyn = 'Y' and a.asriCatCode ='S18'  " );
	 			
	 			List<CasesSearchVO> list=genericDao.executeHQLQueryList(CasesSearchVO.class, query.toString());
	 			if(list!=null && list.size()>0)
	 				returnMsg="Y";
	 			
	 		}
	 	catch(Exception e)
	 		{
	 			e.printStackTrace();
	 			return returnMsg;
	 		}
	 	return returnMsg;
	}
public String sentForPreauth(PreauthVO preauthVO)
{
	String msg = "failure";
	try{
	preauthVO.setRemarks("Sent For Preauthorisation");
	preauthVO.setCaseStatusId("CD76");
	updateAsritCase(preauthVO);
	saveAstitAudit(preauthVO);	
	msg="success";
	}catch(Exception e)
	{
	e.printStackTrace();
	msg ="failure";
	}
	return msg;
}
private String saveAstitAudit(PreauthVO preauthVO)
{

	String msg= "failure";
	Long actOrder=0L;
	try
	{
	
	actOrder = getMaxActOrder(preauthVO.getCaseId())+1;
	EhfAudit ehfAudit = new EhfAudit();
	ehfAudit.setId(new EhfAuditId(actOrder,preauthVO.getCaseId()));
	if("IPM".equalsIgnoreCase(preauthVO.getIpOpFlag()))
	{
		StringBuffer sb = new StringBuffer();
		String[] args = new String[1];
		args[0] = preauthVO.getCaseId();
		String asriCatCode = "";
		sb.append("select icd_cat_code ID from ehf_case_therapy where case_id = ? ");
		List<LabelBean> tempList = genericDao.executeSQLQueryList(LabelBean.class, sb.toString(), args);
		if(tempList.size() > 0)
			asriCatCode = tempList.get(0).getID();
		ehfAudit.setActId("CD651");
		
	}
	else
	ehfAudit.setActId(preauthVO.getStatusToSave());
	ehfAudit.setActBy(preauthVO.getCruUsr());
	ehfAudit.setRemarks(preauthVO.getRemarks());
	ehfAudit.setCrtUsr(preauthVO.getCruUsr());
	ehfAudit.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
	ehfAudit.setLangId("en_US");

	if(preauthVO.getAuditAmt() != null && !preauthVO.getAuditAmt().equalsIgnoreCase(""))
	{
		if(!preauthVO.getStatusToSave().equalsIgnoreCase(config.getString("preauth_hub_rejected")) &&
				!preauthVO.getStatusToSave().equalsIgnoreCase(config.getString("preauth_hub_pending")))
				{
			ehfAudit.setApprvAmt(Double.parseDouble(preauthVO.getAuditAmt()));
				}
	}
	ehfAudit.setUserRole(preauthVO.getUserRole());
	genericDao.save(ehfAudit);

	
	
	msg="success";
	}catch(Exception e)
	{
		//gLogger.error("actOrder"+actOrder);
//		gLogger.error("CaseId"+preauthVO.getCaseId());
//		gLogger.error("Error in saveAstitAudit class PreauthServiceImpl.java--> "+ e.getMessage());
	e.printStackTrace();	
	msg="failure";
	}
	return msg;
}
private String updateAsritCase(PreauthVO preauthVO)
{
	String msg = "failure";
	Date date = new Date();
	String templateId="";
	try
	{
		EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, preauthVO.getCaseId());
		List<PreauthVO> preauthVOLst=new ArrayList<PreauthVO>();
		if(ehfCase != null)
		{
			if(preauthVO.getCruUsr()!=null)
			{	
				StringBuffer sb= new StringBuffer();
				sb.append(" select emd1.id.medcoId as medcoId from EhfmMedcoDtls emd1 where ");
				sb.append(" emd1.id.medcoId='"+preauthVO.getCruUsr()+"' and emd1.id.hospId='"+ehfCase.getCaseHospCode()+"' and activeYN='Y' ");
				preauthVOLst = genericDao.executeHQLQueryList(PreauthVO.class, sb.toString());
				if(preauthVOLst.size()>0)
					{
						String nabhFlg=null;
						if(ehfCase!=null)
							{
							
							
							EhfmEDCHospActionDtls ehfmEDCHospActionDtls=new EhfmEDCHospActionDtls();
							EhfmEDCHospActionDtlsId ehfmEDCHospActionDtlsId=new EhfmEDCHospActionDtlsId(ehfCase.getCaseHospCode(),ehfCase.getSchemeId());
							ehfmEDCHospActionDtls=genericDao.findById(EhfmEDCHospActionDtls.class,EhfmEDCHospActionDtlsId.class,ehfmEDCHospActionDtlsId);
								//EhfmHospitals ehfmhospitals=genericDao.findById(EhfmHospitals.class,String.class,ehfCase.getCaseHospCode());
								if(ehfmEDCHospActionDtls.getNabhFlag()!=null)
									nabhFlg=ehfmEDCHospActionDtls.getNabhFlag();
							}
						if(nabhFlg!=null && (preauthVO.getStatusToSave().equalsIgnoreCase(config.getString("preauth_medco_initiated"))
								|| preauthVO.getStatusToSave().equalsIgnoreCase(config.getString("preauth_ppd_pending_updated"))
								|| preauthVO.getStatusToSave().equalsIgnoreCase(config.getString("preauth_ptd_Pending_Updated"))
								|| preauthVO.getStatusToSave().equalsIgnoreCase(config.getString("preauth_eo_pendingUpdated"))
								|| preauthVO.getStatusToSave().equalsIgnoreCase(config.getString("preauth_medco_ceoPendingUpdate"))
								|| preauthVO.getStatusToSave().equalsIgnoreCase(config.getString("preauth_cochlear_group_pending_updated"))
								|| preauthVO.getStatusToSave().equalsIgnoreCase(config.getString("preauth_cochlear_ptd_pending_updated"))
								|| preauthVO.getStatusToSave().equalsIgnoreCase(config.getString("preauth_cochlear_eo_pending_updated"))
								|| preauthVO.getStatusToSave().equalsIgnoreCase(config.getString("preauth_cochlear_ceo_pending_updated")))
								|| preauthVO.getStatusToSave().equalsIgnoreCase(config.getString("Preauth.Medco.Immediate.Approve"))
								|| preauthVO.getStatusToSave().equalsIgnoreCase(config.getString("Preauth.Medco.Recommend.Approve")))
							ehfCase.setNabhFlg(nabhFlg);
					}	
			}
			if("IPM".equalsIgnoreCase(preauthVO.getIpOpFlag()))
			{
				StringBuffer sb = new StringBuffer();
				String[] args = new String[1];
				args[0] = preauthVO.getCaseId();
				String asriCatCode = "";
				sb.append("select icd_cat_code ID from ehf_case_therapy where case_id = ? ");
				List<LabelBean> tempList = genericDao.executeSQLQueryList(LabelBean.class, sb.toString(), args);
				if(tempList.size() > 0)
					asriCatCode = tempList.get(0).getID();
				
				/**
	 			 * Claim Number Generation
	 			 */
				/*if(( (!"".equalsIgnoreCase(asriCatCode) &&  asriCatCode.contains("MMNC"))  && Long.parseLong(preauthVO.getPreauthTotalPackageAmt()) < 50000) ||
						((!"".equalsIgnoreCase(asriCatCode) &&  asriCatCode.contains("MMNC"))  && (Long.parseLong(preauthVO.getPreauthTotalPackageAmt()) < 100000) ))
				{*/
		 		 StringBuffer claimNum = new StringBuffer();
					
				 Calendar cal = Calendar.getInstance () ;
				 String lStrYear = cal.get ( Calendar.YEAR ) + "" ;
				 StringBuffer locQuery = new StringBuffer();	 	
				 locQuery.append(" select lm.locShrtName as ID , ep.districtCode as VALUE  ,");
				 locQuery.append(" ep.patientScheme as PATIENTSCHEME , ep.cardNo as LVL  ");
				 locQuery.append(" from EhfCase ec , EhfPatient ep , EhfmLocations lm where  ");
				 locQuery.append(" ec.caseId = '"+ehfCase.getCaseId()+"' and ec.casePatientNo = ep.patientId ");
				 locQuery.append(" and ep.districtCode = lm.id.locId ");
				 
				 List<LabelBean> lstClaimDtls = genericDao.executeHQLQueryList(LabelBean.class, locQuery.toString());
				 if(lstClaimDtls != null && lstClaimDtls.size() >0)
				 	{
    					 /**
    					  * get latest Ip case for claim number increment
    					  */
    					 String paitentSchemetype =null;
					
    					 locQuery = new StringBuffer();
    					 locQuery.append(" select ec.preauthFwdDate as crtDt , ec.claimNo as ID , ep.patientScheme as PATIENTSCHEME ,ep.patientIpop as VALUE, year(ec.preauthFwdDate) as COUNT ,current_date() as crtDt  ");
    					 locQuery.append(" from EhfCase ec , EhfPatient ep where ec.casePatientNo = ep.patientId and ep.cardNo ='"+lstClaimDtls.get(0).getLVL()+"' ");
    					 locQuery.append(" and ep.patientIpop ='IPM' and ec.caseId  not in ('"+ehfCase.getCaseId()+"') and ec.claimNo is not null  ");
    					 locQuery.append(" and ec.preauthFwdDate is not null and year(ec.preauthFwdDate) ='"+Integer.parseInt(lStrYear)+"' order by ec.preauthFwdDate desc ");
    					 List<LabelBean> lstClaimNos = genericDao.executeHQLQueryList(LabelBean.class, locQuery.toString());
    					 if(lstClaimDtls!=null && lstClaimDtls.size()>0 && lstClaimDtls.get(0) !=null 
    							 && lstClaimDtls.get(0).getPATIENTSCHEME()!=null)
    						 paitentSchemetype=lstClaimDtls.get(0).getPATIENTSCHEME();
					
    					// get claim number from prev calim No
    					if(lstClaimNos != null && lstClaimNos.size() > 0
    							&& lstClaimNos.get(0)!=null
    							&& lstClaimNos.get(0).getID()!=null )
    						{
    							String prevClaimNo = lstClaimNos.get(0).getID();
    							if(prevClaimNo != null)
    								{
				    					String prevClaimLstSeq =  prevClaimNo.substring(prevClaimNo.lastIndexOf("/")+1); 
				    					claimNum.append(prevClaimNo.substring(0,prevClaimNo.lastIndexOf("/")));
				    				    claimNum.append("/");
				    					claimNum.append(Integer.parseInt(prevClaimLstSeq)+1);
    								}
    						}
    					else
	    					{
	    						// get sequence form sequnece Mst
	    						if("CD501".equalsIgnoreCase(paitentSchemetype))
	    						 claimNum.append("EHS");	
	    						else
	    				         claimNum.append("JHS");
	    						
	    						claimNum.append("/");
	    						claimNum.append(lstClaimDtls.get(0).getID());
	    						claimNum.append("/");
	    						claimNum.append(lStrYear);
	    						claimNum.append("/");
	    						claimNum.append(lstClaimDtls.get(0).getVALUE());
	    						claimNum.append(commonService.getSequence(config.getString("claim_seq_no")));
	    						claimNum.append("/");
	    						claimNum.append("1");
	    					}
	    					ehfCase.setClaimNo(claimNum.toString());
	    					//msg = msg +" and claim number is "+ claimNum.toString() ;
	    				}
				 	EhfCasePreauthAmounts ehfCasePreauthAmounts ;
					ehfCasePreauthAmounts = genericDao.findById(EhfCasePreauthAmounts.class, String.class, preauthVO.getCaseId());
					if(ehfCasePreauthAmounts != null)
						{
							/**
							 * saving respective columns 
							 */
							Double totAmtAppr = (double) 0;
							/**
							 * Medco Approval Fields
							 */
							ehfCase.setCsPreauthDt(new java.sql.Timestamp(date.getTime()));
							ehfCasePreauthAmounts.setPreauthInitiatedDt(new java.sql.Timestamp(date.getTime()));
							/**
							 * Mithra Approval Fields
							 */
							ehfCasePreauthAmounts.setPreauthFwdDt(new java.sql.Timestamp(date.getTime()));
							ehfCase.setPreauthFwdDate(new java.sql.Timestamp(date.getTime()));
							/**
							 * PPD Approval Fields
							 */
							ehfCasePreauthAmounts.setPpdAppvDate(new java.sql.Timestamp(date.getTime()));
							
							/**
							 * PTD Approval Fields
							 */
							ehfCasePreauthAmounts.setPtdAppvDate(new java.sql.Timestamp(date.getTime()));
							if(ehfCase.getPreauthPckgAmt()!=null)
								{
									ehfCasePreauthAmounts.setPtdAppvPackageAmt(Double.parseDouble(ehfCase.getPreauthPckgAmt()));
									totAmtAppr = totAmtAppr + Double.parseDouble(ehfCase.getPreauthPckgAmt());
								}
							if(ehfCase.getComorBidAmt()!=null)
								{
									ehfCasePreauthAmounts.setPtdAppvComorbidAmt((ehfCase.getComorBidAmt()).doubleValue());
									totAmtAppr = totAmtAppr + ehfCase.getComorBidAmt().doubleValue();
								}
							ehfCasePreauthAmounts.setPtdAppvTotalPackgAmt(totAmtAppr);
							if( (totAmtAppr > Integer.parseInt(config.getString("preauth_package_amt_limit")))  )
								{
    								ehfCase.setCeoApprovalFlag(config.getString("activeY"));
									preauthVO.setStatusToSave(config.getString("Preauth.Medco.Recommend.Approve"));

	    							/**
	    							 * EO Approval Fields--Only for Cases having Amounts greater than 2 Lakhs
	    							 */
									ehfCasePreauthAmounts.setEoappvDt(new java.sql.Timestamp(date.getTime()));
									if(ehfCase.getPreauthPckgAmt()!=null)
										ehfCasePreauthAmounts.setEoAppvPackgAmt(Double.parseDouble(ehfCase.getPreauthPckgAmt()));
									if(ehfCase.getComorBidAmt()!=null)
										ehfCasePreauthAmounts.setEoAppvComorbidAmt((ehfCase.getComorBidAmt().doubleValue()));
									if(totAmtAppr!=null)
										ehfCasePreauthAmounts.setEoAppvTotalAmt(totAmtAppr);
								}
							else
								{
    								ehfCase.setPckAppvAmt(Long.parseLong(preauthVO.getPreauthTotalPackageAmt()));
    								ehfCase.setPreauthPckgAmt((preauthVO.getPreauthTotalPackageAmt()));
    								ehfCase.setPreauthTotalPackageAmt(Long.parseLong(preauthVO.getPreauthTotalPackageAmt()));
									ehfCase.setPreauthAprvDate(new java.sql.Timestamp(date.getTime()));
									if(ehfCase.getComorBidAmt()!= null)
										ehfCase.setComorbidAppvAmt(ehfCase.getComorBidAmt());
								}

							ehfCasePreauthAmounts.setLstUpdDt(new java.sql.Timestamp(date.getTime()));
							ehfCasePreauthAmounts.setLstUpdUsr(preauthVO.getCruUsr());
							
							preauthVO.setAuditAmt(ehfCasePreauthAmounts.getPtdAppvTotalPackgAmt().toString());
							
    						genericDao.save(ehfCasePreauthAmounts);
    						
    						/*if("true".equalsIgnoreCase(config.getString("SmsRequired")))
								     {
			    							SendSMS sendSms =new SendSMS();
											 msg= "Dear "+ehfPatient.getName().trim()+" , Your case No.  "+preauthVO.getCaseId()+"  has been " + msg +" in "+getHospName(ehfPatient.getRegHospId());
											 sendSms.sendSMS(msg,ehfPatient.getContactNo().toString() );
								     }*/
						}
				//}
				/*if (!"".equalsIgnoreCase(asriCatCode) &&  asriCatCode.contains("MMC"))
				{
					if(Long.parseLong(preauthVO.getPreauthTotalPackageAmt()) >= 100000)
					{
						ehfCase.setExceedFlg("Y");
						if("true".equalsIgnoreCase(config.getString("SmsRequired")))
					     {
   							//SendSMS sendSms =new SendSMS();
							SMSServices SMSServicesobj = new SMSServices();
   							String contactNo = config.getString("EXCEED_ALERT_SMS");
   							EhfmHospitals hospDtls = genericDao.findById(EhfmHospitals.class, String.class, ehfCase.getCaseHospCode());
   							if(hospDtls != null)
   							{
   								String[] cntctNo =  contactNo.split("~"); 
   								for(int i=0; i<cntctNo.length;i++)
   								{	
									 msg= "Alert! Case with Registration Number"+preauthVO.getCaseId().trim()+" is registered in"+hospDtls.getHospName().trim()+" as Medical Management Critical and exceeded the maximum limit. ";
									 SMSServicesobj.sendSingleSMS(msg,cntctNo[i],templateId );
   								}
   							}
					     }
					}
				}
				else if (!"".equalsIgnoreCase(asriCatCode) &&  asriCatCode.contains("MMNC"))
				{
					if(Long.parseLong(preauthVO.getPreauthTotalPackageAmt()) >= 50000)
					{
						ehfCase.setExceedFlg("Y");
						if("true".equalsIgnoreCase(config.getString("SmsRequired")))
					     {
   							//SendSMS sendSms =new SendSMS();
							SMSServices SMSServicesobj = new SMSServices();
   							String contactNo = config.getString("EXCEED_ALERT_SMS");
   							EhfmHospitals hospDtls = genericDao.findById(EhfmHospitals.class, String.class, ehfCase.getCaseHospCode());
   							if(hospDtls != null)
   							{
   								String[] cntctNo =  contactNo.split("~"); 
   								for(int i=0; i<cntctNo.length;i++)
   								{	
									 msg= "Alert! Case with Registration Number"+preauthVO.getCaseId().trim()+" is registered in"+hospDtls.getHospName().trim()+" as Medical Management Non Critical and exceeded the maximum limit.";
									 SMSServicesobj.sendSingleSMS(msg,cntctNo[i],templateId );
   								}
   							}
					     }
					}

				}*/
				ehfCase.setCaseStatus("CD651");	
				ehfCase.setPckAppvAmt(Long.parseLong(preauthVO.getPreauthTotalPackageAmt()));
				
				/*if( Long.parseLong(preauthVO.getPreauthTotalPackageAmt()) >Integer.parseInt(config.getString("preauth_package_amt_limit")))
						
				{
					ehfCase.setCeoApprovalFlag(config.getString("activeY"));
				}*/
			}
			else
			ehfCase.setCaseStatus(preauthVO.getStatusToSave());
			if(preauthVO.getStatusToSave() != null && preauthVO.getStatusToSave().equalsIgnoreCase(config.getString("preauth_medco_initiated")) )
			ehfCase.setCsPreauthDt(new java.sql.Timestamp(date.getTime()));
			if(preauthVO.getStatusToSave() != null && preauthVO.getStatusToSave().equalsIgnoreCase(config.getString("preauth_nam_forwarded")) )
			ehfCase.setPreauthFwdDate(new java.sql.Timestamp(date.getTime()));
			ehfCase.setLstUpdDt(new java.sql.Timestamp(date.getTime()));
			ehfCase.setLstUpdUsr(preauthVO.getCruUsr());
			ehfCase.setViewFlag(config.getString("activeN"));
			if(preauthVO.getStatusToSave() != null && preauthVO.getStatusToSave().equalsIgnoreCase(config.getString("preauth_cancelled_status")) )
			ehfCase.setPreauthCancelDt(new java.sql.Timestamp(date.getTime()));
				
			genericDao.save(ehfCase);
			msg="success";
		}	
	}
	catch(Exception e)
	{
		msg="failure";
	e.printStackTrace();	
	}
	return msg;
}
public PreauthVO getQuestionerData(String pStrcaseId)
{
	StringBuffer query = new StringBuffer();
	PreauthVO preauthVO = new PreauthVO();
	List<PreauthVO> lstPreauthVO = new ArrayList<PreauthVO>();
	try
	{
		query.append("select e.enrollName as enrollName , e.ehfCardNo as enrollCardNo , cast(e.enrollGender as string) as enrollSex, cast(e.enrollDob as string)  as enrollAge , e.enrollId as enrollId , ");	
		query.append("  a.rationCardNo as patCardNo , a.firstName as patName , cast(a.gender as string) as patSex , cast(a.dateOfBirth as string) as patAge , a.patientId as patientID ");
		query.append(" from EhfEnrollmentFamily e , AsritPatient a , EhfCase c  where a.rationCardNo = e.ehfCardNo ");	
		query.append(" and c.caseId = '"+pStrcaseId+"' and c.casePatientNo = a.patientId ");
		lstPreauthVO = genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
	}catch(Exception e)
	{
	e.printStackTrace();	
	}
	
if(lstPreauthVO !=null && lstPreauthVO.size() >0)
{
	preauthVO = lstPreauthVO.get(0);
	
	List<GenericDAOQueryCriteria> criteraiList = new ArrayList<GenericDAOQueryCriteria>();
	criteraiList.add(new GenericDAOQueryCriteria("employeeParntId",preauthVO.getEnrollId(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	criteraiList.add(new GenericDAOQueryCriteria("attachactiveYN",config.getString("activeY"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	criteraiList.add(new GenericDAOQueryCriteria("attachType","CD3002",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	List<EhfEmployeeDocAttach> lstEhfEmployeeDocAttach = genericDao.findAllByCriteria(EhfEmployeeDocAttach.class, criteraiList);
	
	if(lstEhfEmployeeDocAttach != null && lstEhfEmployeeDocAttach.size() >0)
	{
		preauthVO.setEnrollPotoAttach(lstEhfEmployeeDocAttach.get(0).getSavedName());	
	}
	AsritCaseCardValidateDtls asritCaseCardValidateDtls = genericDao.findById(AsritCaseCardValidateDtls.class, String.class, lstPreauthVO.get(0).getPatientID());
	if(asritCaseCardValidateDtls != null)
	{
		preauthVO.setPatName(asritCaseCardValidateDtls.getOnlinePatientName());
		preauthVO.setEnrollName(asritCaseCardValidateDtls.getIdPatientName());
		preauthVO.setNameRadio(asritCaseCardValidateDtls.getChkPatientName());
		preauthVO.setAgeRadio(asritCaseCardValidateDtls.getChkPatientAge());
		preauthVO.setPatSex(asritCaseCardValidateDtls.getOnlinePatientGender());
		preauthVO.setEnrollSex(asritCaseCardValidateDtls.getIdPatientGender());
		preauthVO.setGenderRadio(asritCaseCardValidateDtls.getChkPatientGender());
		preauthVO.setPhotoradio(asritCaseCardValidateDtls.getChkPatientPhoto());
		preauthVO.setPrePat1Radio(asritCaseCardValidateDtls.getChkNamePatient());
		preauthVO.setPreDoc1Radio(asritCaseCardValidateDtls.getChkNameDoctor());
		preauthVO.setPreRamco1Radio(asritCaseCardValidateDtls.getChkNameRamco());
		preauthVO.setPrePat2Radio(asritCaseCardValidateDtls.getChkSignPatient());
		preauthVO.setPreDoc2Radio(asritCaseCardValidateDtls.getChkSignDoctor());
		preauthVO.setPreRamco2Radio(asritCaseCardValidateDtls.getChkSignRamco());
		preauthVO.setConPat1Radio(asritCaseCardValidateDtls.getChkPreNamePatient());
		preauthVO.setConDoc1Radio(asritCaseCardValidateDtls.getChkPreNameDoctor());
		preauthVO.setConRamco1Radio(asritCaseCardValidateDtls.getChkPreNameRamco());
		preauthVO.setConPat2Radio(asritCaseCardValidateDtls.getChkPreSignPatient());
		preauthVO.setConDoc2Radio(asritCaseCardValidateDtls.getChkPreSignDoctor());
		preauthVO.setConRamco2Radio(asritCaseCardValidateDtls.getChkPreSignRamco());
		preauthVO.setNumRadio(asritCaseCardValidateDtls.getChkPatientNum());
		
		preauthVO.setQuesMsg(config.getString("activeY"));
	}
	return preauthVO;
}
else
	return null;
}
public String saveQuestionnaire(PreauthVO preauthVO)
{
	String msg = null;
	Date date = new Date();
	try{
		AsritCaseCardValidateDtls asritCaseCardValidateDtls = new AsritCaseCardValidateDtls();
		asritCaseCardValidateDtls.setPatientId(preauthVO.getPatientID());
		asritCaseCardValidateDtls.setOnlineCardNo(preauthVO.getPatCardNo());
		asritCaseCardValidateDtls.setRegCardNo(preauthVO.getEnrollCardNo());
		//asritCaseCardValidateDtls.setOpPatientName(opPatientName);
		asritCaseCardValidateDtls.setOnlinePatientName(preauthVO.getPatName());
		asritCaseCardValidateDtls.setIdPatientName(preauthVO.getEnrollName());
		asritCaseCardValidateDtls.setChkPatientName(preauthVO.getNameRadio());
		asritCaseCardValidateDtls.setChkPatientNum(preauthVO.getNumRadio());
		//asritCaseCardValidateDtls.setOpPatientAge(opPatientAge)
		//asritCaseCardValidateDtls.setOnlinePatientAge(onlinePatientAge);
		//asritCaseCardValidateDtls.setIdPatientAge(idPatientAge);
		asritCaseCardValidateDtls.setChkPatientAge(preauthVO.getAgeRadio());
		//asritCaseCardValidateDtls.setOpPatientGender(opPatientGender);
		asritCaseCardValidateDtls.setOnlinePatientGender(preauthVO.getPatSex());
		asritCaseCardValidateDtls.setIdPatientGender(preauthVO.getEnrollSex());
		asritCaseCardValidateDtls.setChkPatientGender(preauthVO.getGenderRadio());
		//asritCaseCardValidateDtls.setOnbedPatientPhoto(onbedPatientPhoto);
		//asritCaseCardValidateDtls.setOnlinePatientPhoto(onlinePatientPhoto);
		//asritCaseCardValidateDtls.setIdPatientPhoto(idPatientPhoto);
		asritCaseCardValidateDtls.setChkPatientPhoto(preauthVO.getPhotoradio());
		//asritCaseCardValidateDtls.setOpParentName(opParentName);
		asritCaseCardValidateDtls.setChkNamePatient(preauthVO.getPrePat1Radio());
		asritCaseCardValidateDtls.setChkNameDoctor(preauthVO.getPreDoc1Radio());
		asritCaseCardValidateDtls.setChkNameRamco(preauthVO.getPreRamco1Radio());
		asritCaseCardValidateDtls.setChkSignPatient(preauthVO.getPrePat2Radio());
		asritCaseCardValidateDtls.setChkSignDoctor(preauthVO.getPreDoc2Radio());
		asritCaseCardValidateDtls.setChkSignRamco(preauthVO.getPreRamco2Radio());
		//asritCaseCardValidateDtls.setChkPackage(chkPackage);
	//	asritCaseCardValidateDtls.setChkPrice(chkPrice);
	//	asritCaseCardValidateDtls.setChkCardNo(chkCardNo);
		asritCaseCardValidateDtls.setChkPreNamePatient(preauthVO.getConPat1Radio());
		asritCaseCardValidateDtls.setChkPreNameDoctor(preauthVO.getConDoc1Radio());
		asritCaseCardValidateDtls.setChkPreNameRamco(preauthVO.getConRamco1Radio());
		asritCaseCardValidateDtls.setChkPreSignPatient(preauthVO.getConPat2Radio());
		asritCaseCardValidateDtls.setChkPreSignDoctor(preauthVO.getConDoc2Radio());
		asritCaseCardValidateDtls.setChkPreSignRamco(preauthVO.getConRamco2Radio());
		asritCaseCardValidateDtls.setCrtUsr(preauthVO.getCruUsr());
		asritCaseCardValidateDtls.setCrtDt(new java.sql.Timestamp(date.getTime()));
		genericDao.save(asritCaseCardValidateDtls);
	}
	catch(Exception e)
	{
	e.printStackTrace();	
	}
	return msg;
}
public String sentVerifyPanel(PreauthVO preauthVO)
{
	String msg = null,eoCeoSmS="N",currUsr=null,nextUsr=null;
	Date date = new Date();
	try{
	preauthVO.setRemarks(preauthVO.getRemarks());
	String Cochlear=preauthVO.getCochlearYN();
	String organTrans=preauthVO.getOrganTransYN();
	String scheme=preauthVO.getScheme();
	EhfmWorkflowStatus ehfmWorkflowStatus = getEhfmWorkflowStatus(preauthVO);
	if(preauthVO.getCaseId()!=null && !"".equalsIgnoreCase(preauthVO.getCaseId()))
	{
		EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, preauthVO.getCaseId());
		EhfCasePreauthAmounts ehfCasePreauthAmounts = genericDao.findById(EhfCasePreauthAmounts.class, String.class, preauthVO.getCaseId());
		String currStatus =ehfCase.getCaseStatus();	
		if(currStatus != null && preauthVO.getCaseStatusId() != null && !currStatus.equalsIgnoreCase(preauthVO.getCaseStatusId()))
		{
			msg =config.getString("preauth_case_updation_msg");
			return msg;	
		}
		if(currStatus != null && ehfmWorkflowStatus != null && currStatus.equalsIgnoreCase(ehfmWorkflowStatus.getNextStatus()))
		{
			msg = config.getString("preauth_msg_"+ehfmWorkflowStatus.getNextStatus());	
			return msg;
		}
		if(ehfmWorkflowStatus != null)
		{
			if(("Y").equals(Cochlear))
			{
				if(currStatus.equalsIgnoreCase(config.getString("preauth_nam_forwarded")))
				{
					preauthVO.setStatusToSave(config.getString("preauth_cochlear_pex_forwarded"));    //verifying cochlear case by PEX
				}
				if(currStatus.equalsIgnoreCase(config.getString("preauth_medco_initiated")))
				{
					preauthVO.setStatusToSave(config.getString("preauth_cochlear_nam_forwarded"));    //verifying cochlear case by PEX
				}
				/*else if(currStatus.equalsIgnoreCase(config.getString("preauth_cochlear_pex_forwarded")))
				{
					preauthVO.setStatusToSave(config.getString("preauth_cochlear_group_approved"));    //verifying cochlear case by PEX
				}
				else if(currStatus.equalsIgnoreCase(config.getString("preauth_cochlear_group_approved")))
				{
					preauthVO.setStatusToSave(config.getString("preauth_cochlear_ppd_approved"));    //verifying cochlear case by PEX
				}
				else if(currStatus.equalsIgnoreCase(config.getString("preauth_cochlear_ppd_approved")))
				{
					preauthVO.setStatusToSave(config.getString("preauth_cochlear_ceo_approved"));    //verifying cochlear case by PEX
				}*/
				else
				{
					preauthVO.setStatusToSave(ehfmWorkflowStatus.getNextStatus());
				}
				
				
			}
			else
			{
			preauthVO.setStatusToSave(ehfmWorkflowStatus.getNextStatus());
			}
			//Added by ramalakshmi for organ transplant
			if(("Y").equals(organTrans))
			{
				if((currStatus.equalsIgnoreCase(config.getString("preauth_pex_forwarded")) || currStatus.equalsIgnoreCase(config.getString("preauth_ppd_organ_pending_updated")) ) && preauthVO.getButtonVal()!=null && "CD32".equalsIgnoreCase(preauthVO.getButtonVal()))
				{
					preauthVO.setStatusToSave(config.getString("preauth_ppd_organ_approve"));    //verifying organ case by PEX
				}
			/*	if(currStatus.equalsIgnoreCase(config.getString("preauth_pex_forwarded")) && preauthVO.getButtonVal()!=null && "CD31".equalsIgnoreCase(preauthVO.getButtonVal()))
				{
					preauthVO.setStatusToSave(config.getString("preauth_ppd_organ_reject"));    //verifying organ case by PEX
				}*/
				else if((currStatus.equalsIgnoreCase(config.getString("preauth_pex_forwarded")) || currStatus.equalsIgnoreCase(config.getString("preauth_ppd_organ_pending_updated"))) && preauthVO.getButtonVal()!=null && "CD33".equalsIgnoreCase(preauthVO.getButtonVal()))
				{
					preauthVO.setStatusToSave(config.getString("preauth_ppd_organ_reject"));    //verifying organ case by PEX
				}
				else if(currStatus.equalsIgnoreCase(config.getString("preauth_pex_forwarded")) && preauthVO.getButtonVal()!=null && "CD31".equalsIgnoreCase(preauthVO.getButtonVal()))
				{
					preauthVO.setStatusToSave(config.getString("preauth_ppd_organ_pending"));    //verifying organ case by PEX
				}
				else
				{
					preauthVO.setStatusToSave(ehfmWorkflowStatus.getNextStatus());
				}
				/*if(currStatus.equalsIgnoreCase(config.getString("preauth_medco_initiated")))
				{
					preauthVO.setStatusToSave(config.getString("preauth_organ_nam_forwarded"));    //verifying organ case by NAM
				}
			
				else
				{*/
					//preauthVO.setStatusToSave(ehfmWorkflowStatus.getNextStatus());
				/*}*/
				
				
			}
			else
			{
			preauthVO.setStatusToSave(ehfmWorkflowStatus.getNextStatus());
			}
			if(ehfmWorkflowStatus.getNextStatus() != null && (ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_nam_forwarded"))
					|| ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_pex_forwarded"))
					|| ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_ppd_approved"))
					|| ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_cochlear_pex_forwarded"))
					|| ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_cochlear_ptd_approved"))
					|| ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_cochlear_group_approved"))
					|| ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_cochlear_eo_approved"))
					|| ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_cochlear_ceo_approved"))
					|| ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_organ_pex_forwarded"))
					|| ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_organ_ptd_approved"))
					|| ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_organ_group_approved"))
					|| ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_organ_eo_approved"))
					|| ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_organ_ceo_approved"))
					)
					)
			{
				if(ehfCase != null && ehfCase.getPreauthTotPckgAmt() != null && ehfCase.getComorBidAmt() != null)
				{
					preauthVO.setAuditAmt(Long.toString( Long.parseLong(ehfCase.getPreauthTotPckgAmt()) + ehfCase.getComorBidAmt()));
				}
				else if(ehfCase != null && ehfCase.getPreauthTotPckgAmt() != null )
				{
					preauthVO.setAuditAmt(Long.toString( Long.parseLong(ehfCase.getPreauthTotPckgAmt()) ));	
				}
				
			}
			if(ehfmWorkflowStatus.getNextStatus() != null && ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_ptd_approved")))
			{
				/*if((preauthVO.getApprvdPckAmt() != null && !preauthVO.getApprvdPckAmt().equalsIgnoreCase("")) && (preauthVO.getComorBidAppvAmt() != null && !preauthVO.getComorBidAppvAmt() .equals("")) )
				{
					preauthVO.setAuditAmt(Float.toString (Float.parseFloat(preauthVO.getApprvdPckAmt())+Float.parseFloat(preauthVO.getComorBidAppvAmt())));
					}	
				else if(preauthVO.getApprvdPckAmt() != null && !preauthVO.getApprvdPckAmt().equalsIgnoreCase(""))
				{
					preauthVO.setAuditAmt(preauthVO.getApprvdPckAmt());
				}
				else if(preauthVO.getComorBidAppvAmt() != null && !preauthVO.getComorBidAppvAmt().equalsIgnoreCase(""))
				{
					preauthVO.setAuditAmt(preauthVO.getComorBidAppvAmt());
				}*/
				preauthVO.setAuditAmt(preauthVO.getPtdTotalApprvAmt());
			}
			
			msg = config.getString("preauth_msg_"+ehfmWorkflowStatus.getNextStatus());
			//msg ="Updated successfully";
		}
	
		if(ehfCase != null && ehfmWorkflowStatus != null)
		{
			
			/**
			 * saving respective columns based on status
			 */
			/**
			 *  enhancement rejected date
			 */
			preauthVO.setPatientID(ehfCase.getCasePatientNo());
			if(ehfmWorkflowStatus.getNextStatus() != null && ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_enh_rejected")))
			{
				ehfCase.setCsEnhRejDt(new java.sql.Timestamp(date.getTime()));	
				ehfCasePreauthAmounts.setEnhRejDate(new java.sql.Timestamp(date.getTime()));
			}
			/**
			 *  enhancement approved date
			 */
			if(ehfmWorkflowStatus.getNextStatus() != null && ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_enh_approved")))
			{
				if(preauthVO.getEnhApprAmt() != null && !preauthVO.getEnhApprAmt().equalsIgnoreCase("") )
				{
					ehfCase.setEnhAppvAmt(Long.parseLong(preauthVO.getEnhApprAmt()));	
					ehfCasePreauthAmounts.setEnhAppvAmt(Double.parseDouble(preauthVO.getEnhApprAmt()));
				}
				ehfCase.setCsEnhApvDt(new java.sql.Timestamp(date.getTime()));	
				ehfCasePreauthAmounts.setEnnAppvDt(new java.sql.Timestamp(date.getTime()));
				
			}
			if(ehfmWorkflowStatus.getNextStatus() != null && ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_nam_forwarded")))
					{
				ehfCasePreauthAmounts.setPreauthFwdDt(new java.sql.Timestamp(date.getTime()));
					}
			if(ehfmWorkflowStatus.getNextStatus() != null && (ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_ptd_approved"))))
			{
				ehfCasePreauthAmounts.setPtdAppvDate(new java.sql.Timestamp(date.getTime()));
				if(preauthVO.getApprvdPckAmt() != null && !preauthVO.getApprvdPckAmt().equalsIgnoreCase("") )
				{
					ehfCasePreauthAmounts.setPtdAppvPackageAmt(Double.parseDouble(preauthVO.getApprvdPckAmt())); //+Integer.parseInt(preauthVO.getNabhAmt())
					}
				if(preauthVO.getComorBidAppvAmt() != null && !preauthVO.getComorBidAppvAmt().equalsIgnoreCase("") )
				{
					ehfCasePreauthAmounts.setPtdAppvComorbidAmt(Double.parseDouble(preauthVO.getComorBidAppvAmt()));
					}
				Double totAmt = (double) 0;
				if(ehfCasePreauthAmounts.getPtdAppvComorbidAmt() != null)
					totAmt = totAmt + ehfCasePreauthAmounts.getPtdAppvComorbidAmt();
				if(ehfCasePreauthAmounts.getPtdAppvPackageAmt() != null)
					totAmt = totAmt +ehfCasePreauthAmounts.getPtdAppvPackageAmt();
				ehfCasePreauthAmounts.setPtdAppvTotalPackgAmt(totAmt);
				if(scheme!=null &&
						((config.getString("AP").equalsIgnoreCase(scheme)&&(totAmt > Integer.parseInt(config.getString("preauth_package_amt_limit"))))
						||
						(config.getString("TG").equalsIgnoreCase(scheme)&&(totAmt > Integer.parseInt(config.getString("preauth_package_amt_limit_TG")))))
						)
					{
						ehfCase.setCeoApprovalFlag(config.getString("activeY"));
						preauthVO.setStatusToSave(config.getString("preauth_ptd_recommended_approve"));
						eoCeoSmS="Y";
						nextUsr=config.getString("EO_Grp");
						currUsr="Trust Doctor";
					}
				else
				{
					ehfCase.setPckAppvAmt(Long.parseLong(preauthVO.getApprvdPckAmt()));
					ehfCase.setPreauthAprvDate(new java.sql.Timestamp(date.getTime()));
					if(preauthVO.getComorBidAppvAmt() != null && !preauthVO.getComorBidAppvAmt().equalsIgnoreCase("") )
					{
					ehfCase.setComorbidAppvAmt(Long.parseLong(preauthVO.getComorBidAppvAmt()));
					}
				}
				preauthVO.setAuditAmt(ehfCasePreauthAmounts.getPtdAppvTotalPackgAmt().toString());
			}
			
			
			/*for ap cochlear 
			if(scheme!=null && scheme.equalsIgnoreCase(config.getString("AP")))
					{*/
			
			if(ehfmWorkflowStatus.getNextStatus() != null && ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_cochlear_ptd_approved")))
			{
				ehfCasePreauthAmounts.setEoappvDt(new java.sql.Timestamp(date.getTime()));
				if(preauthVO.getApprvdPckAmt() != null && !preauthVO.getApprvdPckAmt().equalsIgnoreCase("") )
				{
					ehfCasePreauthAmounts.setEoAppvPackgAmt(Double.parseDouble(preauthVO.getApprvdPckAmt())); //+Integer.parseInt(preauthVO.getNabhAmt())
					}
				if(preauthVO.getComorBidAppvAmt() != null && !preauthVO.getComorBidAppvAmt().equalsIgnoreCase("") )
				{
					ehfCasePreauthAmounts.setEoAppvComorbidAmt(Double.parseDouble(preauthVO.getComorBidAppvAmt()));
					}
				Double totAmt = (double) 0;
				if(ehfCasePreauthAmounts.getPtdAppvComorbidAmt() != null)
					totAmt = totAmt + ehfCasePreauthAmounts.getEoAppvComorbidAmt();
				if(ehfCasePreauthAmounts.getEoAppvPackgAmt() != null)
					totAmt = totAmt +ehfCasePreauthAmounts.getEoAppvPackgAmt();
				ehfCasePreauthAmounts.setEoAppvTotalAmt(totAmt);
				/*if(totAmt > Integer.parseInt(config.getString("preauth_package_amt_limit")) && !ehfCase.getCochlearYN().equalsIgnoreCase("Y"))
				{
					ehfCase.setCeoApprovalFlag(config.getString("activeY"));
					preauthVO.setStatusToSave(config.getString("preauth_ptd_recommended_approve"));
				}*/
				
					ehfCase.setPckAppvAmt(Long.parseLong(preauthVO.getApprvdPckAmt()));
					ehfCase.setPreauthAprvDate(new java.sql.Timestamp(date.getTime()));
					if(preauthVO.getComorBidAppvAmt() != null && !preauthVO.getComorBidAppvAmt().equalsIgnoreCase("") )
					{
					ehfCase.setComorbidAppvAmt(Long.parseLong(preauthVO.getComorBidAppvAmt()));
					}
				
				preauthVO.setAuditAmt(ehfCasePreauthAmounts.getEoAppvTotalAmt().toString());
			}
			
			
			if(ehfmWorkflowStatus.getNextStatus() != null 
					&& ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_cochlear_eo_approved")))
							{
						ehfCasePreauthAmounts.setCeoappvDt(new java.sql.Timestamp(date.getTime()));
						if(preauthVO.getApprvdPckAmt() != null && !preauthVO.getApprvdPckAmt().equalsIgnoreCase("") )
						{
							ehfCasePreauthAmounts.setCeoAppvPackgAmt(Double.parseDouble(preauthVO.getApprvdPckAmt())); //+Double.parseDouble(preauthVO.getNabhAmt())
							}
						if(preauthVO.getComorBidAppvAmt() != null && !preauthVO.getComorBidAppvAmt().equalsIgnoreCase("") )
						{
							ehfCasePreauthAmounts.setCeoAppvComorbidAmt(Double.parseDouble(preauthVO.getComorBidAppvAmt()));
							}	
						Double totAmt = (double) 0;
						if(ehfCasePreauthAmounts.getCeoAppvComorbidAmt() != null)
							totAmt = totAmt + ehfCasePreauthAmounts.getCeoAppvComorbidAmt();
						if(ehfCasePreauthAmounts.getCeoAppvPackgAmt() != null)
							totAmt = totAmt +ehfCasePreauthAmounts.getCeoAppvPackgAmt();
						ehfCasePreauthAmounts.setCeoAppvTotalAmt(totAmt);
						ehfCase.setCeoApprovalFlag("A");
						preauthVO.setAuditAmt(ehfCasePreauthAmounts.getCeoAppvTotalAmt().toString());
						//ehfCase.setPckAppvAmt(totAmt.longValue());
						ehfCase.setPckAppvAmt(ehfCasePreauthAmounts.getCeoAppvPackgAmt().longValue());
						ehfCase.setPreauthAprvDate(new java.sql.Timestamp(date.getTime()));
						if(ehfCasePreauthAmounts.getCeoAppvComorbidAmt() != null )
							ehfCase.setComorbidAppvAmt(ehfCasePreauthAmounts.getCeoAppvComorbidAmt().longValue());
					}
			
			
					/*}*/
			
			
			//For Organ Transplantation
			
			
			
			if(ehfmWorkflowStatus.getNextStatus() != null && ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_organ_ptd_approved"))  )
			{
				ehfCasePreauthAmounts.setEoappvDt(new java.sql.Timestamp(date.getTime()));
				if(preauthVO.getApprvdPckAmt() != null && !preauthVO.getApprvdPckAmt().equalsIgnoreCase("") )
				{
					ehfCasePreauthAmounts.setEoAppvPackgAmt(Double.parseDouble(preauthVO.getApprvdPckAmt())); //+Integer.parseInt(preauthVO.getNabhAmt())
					}
				if(preauthVO.getComorBidAppvAmt() != null && !preauthVO.getComorBidAppvAmt().equalsIgnoreCase("") )
				{
					ehfCasePreauthAmounts.setEoAppvComorbidAmt(Double.parseDouble(preauthVO.getComorBidAppvAmt()));
					}
				Double totAmt = (double) 0;
				if(ehfCasePreauthAmounts.getPtdAppvComorbidAmt() != null)
					totAmt = totAmt + ehfCasePreauthAmounts.getEoAppvComorbidAmt();
				if(ehfCasePreauthAmounts.getEoAppvPackgAmt() != null)
					totAmt = totAmt +ehfCasePreauthAmounts.getEoAppvPackgAmt();
				ehfCasePreauthAmounts.setEoAppvTotalAmt(totAmt);
				/*if(totAmt > Integer.parseInt(config.getString("preauth_package_amt_limit")) && !ehfCase.getCochlearYN().equalsIgnoreCase("Y"))
				{
					ehfCase.setCeoApprovalFlag(config.getString("activeY"));
					preauthVO.setStatusToSave(config.getString("preauth_ptd_recommended_approve"));
				}*/
				
					ehfCase.setPckAppvAmt(Long.parseLong(preauthVO.getApprvdPckAmt()));
					ehfCase.setPreauthAprvDate(new java.sql.Timestamp(date.getTime()));
					if(preauthVO.getComorBidAppvAmt() != null && !preauthVO.getComorBidAppvAmt().equalsIgnoreCase("") )
					{
					ehfCase.setComorbidAppvAmt(Long.parseLong(preauthVO.getComorBidAppvAmt()));
					}
				
				preauthVO.setAuditAmt(ehfCasePreauthAmounts.getEoAppvTotalAmt().toString());
			}
			
			if(ehfmWorkflowStatus.getNextStatus() != null && ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_organ_eo_approved")))
			{
				ehfCasePreauthAmounts.setCeoappvDt(new java.sql.Timestamp(date.getTime()));
				if(preauthVO.getApprvdPckAmt() != null && !preauthVO.getApprvdPckAmt().equalsIgnoreCase("") )
				{
					ehfCasePreauthAmounts.setCeoAppvPackgAmt(Double.parseDouble(preauthVO.getApprvdPckAmt())); //+Double.parseDouble(preauthVO.getNabhAmt())
					}
				if(preauthVO.getComorBidAppvAmt() != null && !preauthVO.getComorBidAppvAmt().equalsIgnoreCase("") )
				{
					ehfCasePreauthAmounts.setCeoAppvComorbidAmt(Double.parseDouble(preauthVO.getComorBidAppvAmt()));
					}	
				Double totAmt = (double) 0;
				if(ehfCasePreauthAmounts.getCeoAppvComorbidAmt() != null)
					totAmt = totAmt + ehfCasePreauthAmounts.getCeoAppvComorbidAmt();
				if(ehfCasePreauthAmounts.getCeoAppvPackgAmt() != null)
					totAmt = totAmt +ehfCasePreauthAmounts.getCeoAppvPackgAmt();
				ehfCasePreauthAmounts.setCeoAppvTotalAmt(totAmt);
				ehfCase.setCeoApprovalFlag("A");
				preauthVO.setAuditAmt(ehfCasePreauthAmounts.getCeoAppvTotalAmt().toString());
				//ehfCase.setPckAppvAmt(totAmt.longValue());
				ehfCase.setPckAppvAmt(ehfCasePreauthAmounts.getCeoAppvPackgAmt().longValue());
				ehfCase.setPreauthAprvDate(new java.sql.Timestamp(date.getTime()));
				if(ehfCasePreauthAmounts.getCeoAppvComorbidAmt() != null )
					ehfCase.setComorbidAppvAmt(ehfCasePreauthAmounts.getCeoAppvComorbidAmt().longValue());
			
			
			}
			
			if(ehfmWorkflowStatus.getNextStatus() != null && ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_ppd_approved")))
			{
				ehfCasePreauthAmounts.setPpdAppvDate(new java.sql.Timestamp(date.getTime()));
			}
			
			if(ehfmWorkflowStatus.getNextStatus() != null &&( ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_ceo_approved"))
		))
					{
				ehfCasePreauthAmounts.setCeoappvDt(new java.sql.Timestamp(date.getTime()));
				if(preauthVO.getApprvdPckAmt() != null && !preauthVO.getApprvdPckAmt().equalsIgnoreCase("") )
				{
					ehfCasePreauthAmounts.setCeoAppvPackgAmt(Double.parseDouble(preauthVO.getApprvdPckAmt())); //+Double.parseDouble(preauthVO.getNabhAmt())
					}
				if(preauthVO.getComorBidAppvAmt() != null && !preauthVO.getComorBidAppvAmt().equalsIgnoreCase("") )
				{
					ehfCasePreauthAmounts.setCeoAppvComorbidAmt(Double.parseDouble(preauthVO.getComorBidAppvAmt()));
					}	
				Double totAmt = (double) 0;
				if(ehfCasePreauthAmounts.getCeoAppvComorbidAmt() != null)
					totAmt = totAmt + ehfCasePreauthAmounts.getCeoAppvComorbidAmt();
				if(ehfCasePreauthAmounts.getCeoAppvPackgAmt() != null)
					totAmt = totAmt +ehfCasePreauthAmounts.getCeoAppvPackgAmt();
				ehfCasePreauthAmounts.setCeoAppvTotalAmt(totAmt);
				ehfCase.setCeoApprovalFlag("A");
				preauthVO.setAuditAmt(ehfCasePreauthAmounts.getCeoAppvTotalAmt().toString());
				//ehfCase.setPckAppvAmt(totAmt.longValue());
				ehfCase.setPckAppvAmt(ehfCasePreauthAmounts.getCeoAppvPackgAmt().longValue());
				ehfCase.setPreauthAprvDate(new java.sql.Timestamp(date.getTime()));
				if(ehfCasePreauthAmounts.getCeoAppvComorbidAmt() != null )
					ehfCase.setComorbidAppvAmt(ehfCasePreauthAmounts.getCeoAppvComorbidAmt().longValue());
			}
			if(ehfmWorkflowStatus.getNextStatus() != null && ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_eo_approved")))
			{
				ehfCasePreauthAmounts.setEoappvDt(new java.sql.Timestamp(date.getTime()));
				if(preauthVO.getApprvdPckAmt() != null && !preauthVO.getApprvdPckAmt().equalsIgnoreCase("") )
				{
					ehfCasePreauthAmounts.setEoAppvPackgAmt(Double.parseDouble(preauthVO.getApprvdPckAmt()));//+Double.parseDouble(preauthVO.getNabhAmt())
					}
				if(preauthVO.getComorBidAppvAmt() != null && !preauthVO.getComorBidAppvAmt().equalsIgnoreCase("") )
				{
					ehfCasePreauthAmounts.setEoAppvComorbidAmt(Double.parseDouble(preauthVO.getComorBidAppvAmt()));
					}	
				Double totAmt = (double) 0;
				if(ehfCasePreauthAmounts.getEoAppvComorbidAmt() != null)
					totAmt = totAmt + ehfCasePreauthAmounts.getEoAppvComorbidAmt();
				if(ehfCasePreauthAmounts.getEoAppvPackgAmt() != null)
					totAmt = totAmt +ehfCasePreauthAmounts.getEoAppvPackgAmt();
				ehfCasePreauthAmounts.setEoAppvTotalAmt(totAmt);
				preauthVO.setAuditAmt(ehfCasePreauthAmounts.getEoAppvTotalAmt().toString());
			}
			if(ehfmWorkflowStatus.getNextStatus() != null && (ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_ceo_rejected"))
					||ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_organ_ceo_rejected"))
					||ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_cochlear_ceo_reject"))))
			{
				ehfCase.setCeoApprovalFlag("R");	
				ehfCase.setPreauthRejDate(new java.sql.Timestamp(date.getTime()));
			}
			if(ehfmWorkflowStatus.getNextStatus() != null && ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_ptd_rejected"))
					||ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_cochlear_ptd_reject"))
					||ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_organ_ptd_reject"))
					|| (ehfmWorkflowStatus.getNextStatus() != null && ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_eo_rejected"))))
			{
				ehfCase.setPreauthRejDate(new java.sql.Timestamp(date.getTime()));
			}
			
			ehfCasePreauthAmounts.setLstUpdDt(new java.sql.Timestamp(date.getTime()));
			ehfCasePreauthAmounts.setLstUpdUsr(preauthVO.getCruUsr());
			/**
			 * generate claim number when nam forwards
			 */
			if(ehfmWorkflowStatus.getNextStatus() != null && ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_nam_forwarded")))
			{
			StringBuffer claimNum = new StringBuffer();
			
			 Calendar cal = Calendar.getInstance () ;
			 String lStrYear = cal.get ( Calendar.YEAR ) + "" ;
			 StringBuffer query = new StringBuffer();
			 query.append(" select lm.locShrtName as ID ,ep.districtCode as VALUE  ,ep.cardNo as LVL ");
			 query.append(" from EhfCase ec , EhfPatient ep , EhfmLocations lm where  ");
			 query.append(" ec.caseId = '"+ehfCase.getCaseId()+"' and ec.casePatientNo = ep.patientId and ep.districtCode =lm.id.locId   ");
			 query.append(" ");
			 List<LabelBean> lstClaimDtls = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
			if(lstClaimDtls != null && lstClaimDtls.size() >0)
			{
				 /**
				  * get latest Ip case for claim number increment
				  */
				 query = new StringBuffer();
				 query.append(" select ec.preauthFwdDate as crtDt , ec.claimNo as ID , ep.patientIpop as VALUE, year(ec.preauthFwdDate) as COUNT ,current_date() as crtDt  ");
				 query.append(" from EhfCase ec , EhfPatient ep where ec.casePatientNo = ep.patientId and ep.cardNo ='"+lstClaimDtls.get(0).getLVL()+"' ");
				 query.append(" and ep.patientIpop ='IP' and ec.caseId  not in ('"+ehfCase.getCaseId()+"') and ec.claimNo is not null  ");
				 query.append(" and ec.preauthFwdDate is not null and year(ec.preauthFwdDate) ='"+Integer.parseInt(lStrYear)+"' order by ec.preauthFwdDate desc ");
				 List<LabelBean> lstClaimNos = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
				
				// get claim number from prev calim No
				if(lstClaimNos != null && lstClaimNos.size() >0)
				{
				String prevClaimNo = lstClaimNos.get(0).getID();
				if(prevClaimNo != null)
				{
				String prevClaimLstSeq =  prevClaimNo.substring(prevClaimNo.lastIndexOf("/")+1); 
				claimNum.append(prevClaimNo.substring(0,prevClaimNo.lastIndexOf("/")));
			    claimNum.append("/");
				claimNum.append(Integer.parseInt(prevClaimLstSeq)+1);
				}
				}
				else
				{
					// get sequence form sequnece Mst
					claimNum.append(config.getString("preauth_claimNo_payee"));	
					claimNum.append("/");
					claimNum.append(lstClaimDtls.get(0).getID());
					claimNum.append("/");
					claimNum.append(lStrYear);
					claimNum.append("/");
					claimNum.append(lstClaimDtls.get(0).getVALUE());
					claimNum.append(commonService.getSequence(config.getString("claim_seq_no")));
					claimNum.append("/");
					claimNum.append("1");
				}
				ehfCase.setClaimNo(claimNum.toString());
				msg = msg +" and claim number is "+ claimNum.toString() ;
			}
			}
		genericDao.save(ehfCase);
		genericDao.save(ehfCasePreauthAmounts);
		
		/**
		 * save data in EHF_PNLDOC_CASE_DTLS if any action is taken by PPD
		 */
		if(ehfmWorkflowStatus.getNextStatus() != null && !"".equalsIgnoreCase(ehfmWorkflowStatus.getNextStatus()) &&
					(config.getString("preauth_ppd_approved").equalsIgnoreCase(ehfmWorkflowStatus.getNextStatus()) || 
							config.getString("preauth_ppd_rejected").equalsIgnoreCase(ehfmWorkflowStatus.getNextStatus()) || 
								config.getString("preauth_ppd_pending").equalsIgnoreCase(ehfmWorkflowStatus.getNextStatus())))
		{
			String seqVal=commonService.getSequence("panel_doc_pmnt_seq");
			if(seqVal!=null && !"".equalsIgnoreCase(seqVal))
			{
				EhfPanelDocCaseDtls ehfPanelDocCaseDtls= new EhfPanelDocCaseDtls();
				ehfPanelDocCaseDtls.setId(Long.parseLong(seqVal));
				ehfPanelDocCaseDtls.setCaseId(preauthVO.getCaseId());
				ehfPanelDocCaseDtls.setDocId(preauthVO.getCruUsr());
				ehfPanelDocCaseDtls.setCasePmntStatus(ehfmWorkflowStatus.getNextStatus());
				ehfPanelDocCaseDtls.setCaseAppOrPendDate(new java.sql.Timestamp(date.getTime()));
				ehfPanelDocCaseDtls.setHospId(ehfCase.getCaseHospCode());
				ehfPanelDocCaseDtls.setScheme(ehfCase.getSchemeId());
				ehfPanelDocCaseDtls.setCrtUsr(preauthVO.getCruUsr());
				ehfPanelDocCaseDtls.setCrtDt(new java.sql.Timestamp(date.getTime()));
				genericDao.save(ehfPanelDocCaseDtls);
			}
		}
		
		}
		String updateCase= updateAsritCase(preauthVO);
		if(updateCase!=null && !"".equalsIgnoreCase(updateCase) && "success".equalsIgnoreCase(updateCase))
		{
			 String updateAudit= saveAstitAudit(preauthVO);
			 
			 if(updateAudit!=null && !"".equalsIgnoreCase(updateAudit) && "success".equalsIgnoreCase(updateAudit))
			 {
		/**
		 * send sms and email
		 */
		EhfPatient ehfPatient = genericDao.findById(EhfPatient.class, String.class, preauthVO.getPatientID());
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
	     String crtDate=sdfw.format(date);
	     Date crtDt = sdfw.parse((sdfw.format(date)));
	     
	     if(ehfCase.getCeoApprovalFlag()!=null && ehfmWorkflowStatus.getNextStatus()!=null)
	     	{
	    	 	if(ehfCase.getCeoApprovalFlag().equalsIgnoreCase("Y"))
	    	 		{
	 					if(ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_eo_approved")) ||
	 							(ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_ptd_approved")) && eoCeoSmS.equalsIgnoreCase("Y")))
	 						{
	 							if(ehfCase.getSchemeId()!=null && ehfCase.getSchemeId().equalsIgnoreCase(config.getString("AP")))
	 								{
				 						if("true".equalsIgnoreCase(config.getString("SmsRequired")))
										     {
				 								if(ehfmWorkflowStatus.getNextStatus().equalsIgnoreCase(config.getString("preauth_eo_approved")))
				 									{
				 										currUsr="EO Operations";
				 										nextUsr=config.getString("Group.CEO");
				 									}
				 								
												String currentUser=preauthVO.getCruUsr();
					 							EhfmUsers ehfmUsers =new EhfmUsers();
					 							ehfmUsers=genericDao.findById(EhfmUsers.class, String.class, currentUser);
					 							if(ehfmUsers!=null)
						 							{
														String resultMsg=null;
														StringBuffer query=new StringBuffer();
														//SendSMS sendSms =new SendSMS();
														SMSServices SMSServicesobj = new SMSServices();
														query.append(" select (eu.firstName||' '||eu.lastName) as EMPNAME , ");
														query.append(" eu.mobileNo as MOBILENO ");
														query.append(" from EhfmUsers eu , EhfmUsrGrpsMpg eug ");
														query.append(" where eu.serviceFlag='Y' and eu.userId = eug.id.usergrpId ");
														query.append(" and eu.userType in ('"+ehfCase.getSchemeId()+"') ");
														query.append(" and eug.id.grpId='"+nextUsr+"' ");
														List<LabelBean> lbLst=new ArrayList<LabelBean>();
														lbLst=genericDao.executeHQLQueryList(LabelBean.class, query.toString());
														if(lbLst!=null)
															{
																if(lbLst.size()>0)
																	{	
																		for(LabelBean lb:lbLst)
																			{
																			    String templateId="1407161769818615014";
																				String smsText=null;
											    	 					     	smsText="Above 2 lakhs case with case number : "+ehfCase.getCaseId()+" has been Recommended for Approval by "+ehfmUsers.getFirstName()+" "+ehfmUsers.getLastName()+", "+currUsr+" and the case is pending in your worklist.\n\nAHCT, Govt. of Telangana";
											    	 					     	//resultMsg=sendSms.sendSMS(smsText,lb.getMOBILENO());
											    	 					     	if("Y".equalsIgnoreCase(config.getString("AsriSms")))
											    	 					     	{
												    	 							Map<String,Object> lMap = new HashMap<String,Object>();
												    	 							lMap.put("msg",smsText );
												    	 							lMap.put("mobile",lb.getMOBILENO());
												    	 							lMap.put("templateId",templateId);
												    	 							resultMsg = commonService.saveToAsritSms(lMap);
											    	 					     	}
																			}
																	}
															}
						 							}
										     }
	 								}
	 						}
	    	 		}
	     	}
	     
	     if(("ptd".equalsIgnoreCase(preauthVO.getViewType()) && ehfCase.getCeoApprovalFlag()==null) 
	    		 /*|| ("ceo".equalsIgnoreCase(preauthVO.getViewType()) && ehfCase.getCeoApprovalFlag()!=null)*/
	    		 || ("ch".equalsIgnoreCase(preauthVO.getViewType())))
 	     	{
				 if("true".equalsIgnoreCase(config.getString("SmsRequired")))
			     {
					 String smsNextVal=commonService.getSequence(config.getString("PATIENT_SMS_SNO"));
			     	if(ehfPatient.getContactNo()!=null && !ehfPatient.getContactNo().equals(""))
			     	{
			     	String lStrResultMsg=null;
			     	PatientSmsVO patientSmsVO=new PatientSmsVO();
			     	patientSmsVO.setSerialNo(Long.parseLong(smsNextVal));
			     	patientSmsVO.setPhoneNo(ehfPatient.getContactNo().toString());
			     	patientSmsVO.setSmsText("Dear "+ehfPatient.getName().trim()+" , Your case No.  "+preauthVO.getCaseId()+"  has been " + msg +" in "+getHospName(ehfPatient.getRegHospId()));
			     	patientSmsVO.setEmpCode(ehfPatient.getEmployeeNo());
			     	patientSmsVO.setEmpName(ehfPatient.getName().trim());
			     	patientSmsVO.setCrtUsr(preauthVO.getCruUsr());
			     	patientSmsVO.setCrtDt(crtDt);
			     	patientSmsVO.setSmsAction("Case with caseId " +preauthVO.getCaseId() + msg);
			     	patientSmsVO.setPatientId(ehfPatient.getPatientId());
			     	lStrResultMsg=commonService.sendPatientSms(patientSmsVO);
			     	
			     	}
			     }
 	     	}
	     if (config.getBoolean("EmailRequired")) 
	     {
	     	String mailId=null;
	     	if(ehfPatient.getEmailId()!=null && !ehfPatient.getEmailId().equals(""))
	     	{
	     	mailId=ehfPatient.getEmailId();
	     	String[] emailToArray = {mailId};
	     	EmailVO emailVO = new EmailVO();
				emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
				emailVO.setTemplateName(config.getString("EHFPreauthtemplateName"));
				emailVO.setFromEmail(config.getString("emailFrom"));
				emailVO.setToEmail(emailToArray);
				emailVO.setSubject(config.getString("preauthemailSubject"));
				Map<String, String> model = new HashMap<String, String>();
				model.put("patientName", ehfPatient.getName().trim());
				model.put("registeredHosp", getHospName(ehfPatient.getRegHospId()));
				model.put("status", msg);
				model.put("statusDate",crtDate);
				
				if(ehfCase!=null)
					{
						if(ehfCase.getCaseNo()!=null)
							{
								model.put("CaseId",ehfCase.getCaseNo());
							}
						else
							model.put("CaseId",preauthVO.getCaseId());
					}	
				else
					model.put("CaseId",preauthVO.getCaseId());
				
				if(ehfPatient!=null)
					{
						if(ehfPatient.getPatientScheme()!=null)
							{
								if(ehfPatient.getPatientScheme().equalsIgnoreCase(config.getString("Scheme.JHS")))
									{
										emailVO.setSubject(config.getString("preauthemailSubjectJournalist"));
										emailVO.setFromEmail(config.getString("emailFromJournalist"));
										emailVO.setTemplateName(config.getString("EHFPreauthtemplateNameJourn"));
										commonService.generateNonImageMail(emailVO, model);
									}
								else
									{
										emailVO.setTemplateName(config.getString("EHFPreauthtemplateName"));
										commonService.generateMail(emailVO, model);
									}
							}
						else
							{
								emailVO.setTemplateName(config.getString("EHFPreauthtemplateName"));
								commonService.generateMail(emailVO, model);
							}
					}
	     	}
			}
			 }
		
		}
	}
	else
		msg ="failure";
	}catch(Exception e)
	{
	e.printStackTrace();
	msg ="failure";
	}
	return msg;
}
public List<PreauthVO> getAuditTrail(PreauthVO PStrpreauthVO)
{
	List<PreauthVO> lstauditVO = new ArrayList<PreauthVO>();
	StringBuffer query = new StringBuffer();
	StringTokenizer str = null;
	try
	{
		String preauthStatus = null;
		if(PStrpreauthVO.getEnhancementFlag() != null && !PStrpreauthVO.getEnhancementFlag().equals("") && PStrpreauthVO.getEnhancementFlag().equalsIgnoreCase(config.getString("activeY")))
		{
		 str = new StringTokenizer(config.getString("preauth_enhancement_audit_status"),"~");	
		}
		else
		 str = new StringTokenizer(config.getString("preauth_audit_status"),"~");
		while(str.hasMoreTokens())
		{
			if(preauthStatus!= null && !preauthStatus.equalsIgnoreCase("") )
				preauthStatus = preauthStatus +"','"+str.nextToken();
			else
				preauthStatus = str.nextToken();
		}
		query.append(" select  a.actId as actId,");
		query.append(" a.remarks as auditRemarks , ");
//		query.append(" case when   a.actId='"+config.getString("preauth_ptd_pending")+"'or  a.actId='"+config.getString("preauth_medco_preauth_reinitiated")+"' then cast('0' as long) else ");
//		query.append(" case when  a.actId in ('"+config.getString("preauth_ptd_approved")+"','"+config.getString("ehf_clinical_surgeryUpdate")+"','"+config.getString("ehf_clinical_dischargeUpdate")+"') then ec.pckAppvAmt  else case when  a.actId='"+config.getString("preauth_enh_approved")+"' ");
//		query.append(" then ec.enhAppvAmt else cast(ec.preauthTotPckgAmt as long) end end end as auditAmount , ");
		query.append("  case when a.apprvAmt is null then cast('0' as long)  else  a.apprvAmt end  as auditAmount , ");
		query.append(" au.firstName ||' '|| au.lastName as actby ,  ");
		query.append(" cast(a.id.actOrder as string) as rationCard,  a.crtDt  as auditDate, ac1.cmbDtlName as auditAction ,");
		query.append(" a.userRole as userRole, ac.grpShortName as cmbDtlname , ac.grpShortName as  auditRole");
		query.append(" from EhfAudit a , EhfmGrps ac , EhfmUsers au , EhfmCmbDtls ac1 , EhfCase ec  ");
		query.append(" where a.id.caseId = '"+PStrpreauthVO.getCaseId()+"' and a.actBy = au.userId and ac.grpId=a.userRole ");
		query.append(" and ac1.cmbDtlId = a.actId and ec.caseId = '"+PStrpreauthVO.getCaseId()+"'   ");
		query.append(" and a.actId in ('"+preauthStatus+"') ");
		query.append(" order by a.id.actOrder ");
		
		lstauditVO = genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
	}catch(Exception e)
	{
	e.printStackTrace();	
	}
	return lstauditVO;
}

public String checkClinicalNotes(String pStrCaseId)
{
	String msg = "success";
	try
	{
		StringBuffer query = new StringBuffer();
		Calendar startCal = GregorianCalendar.getInstance(); 
		long startTime =0L;
		query.append(" select a.caseRegnDate as date,p.patientIpop as patientType  from EhfCase a,EhfPatient p where p.id.patientId=a.casePatientNo and a.caseId ='"+pStrCaseId+"' " );
		List<PreauthVO> lstPreauthVo = genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
		if(lstPreauthVo != null && lstPreauthVo.size() >0)
		{
			if(startCal!=null && lstPreauthVo!=null)
			{
				if(lstPreauthVo.size()>0 && lstPreauthVo.get(0).getDate()!=null)
					startCal.setTime(lstPreauthVo.get(0).getDate());
			}
		}
		if(startCal!=null)
			startTime = startCal.getTimeInMillis(); 
		Long days=((new java.util.Date()).getTime() - startTime)/ 86400000; 
		// System.out.println(days);
		query = new StringBuffer();
		query.append(" select count(*) as IDVAL from EhfCaseClinicalNotes a where a.caseId ='"+pStrCaseId+"'  ");
		//query.append(" and a.crtDt <= sysdate ");
		List<LabelBean> lstLabelBean = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
		if(lstLabelBean != null && lstLabelBean.size() >0)
		{
		Long count = lstLabelBean.get(0).getIDVAL();
		if(days > count && ((lstPreauthVo.get(0).getPatientType() != null && !"".equalsIgnoreCase(lstPreauthVo.get(0).getPatientType())) && !"IPM".equalsIgnoreCase(lstPreauthVo.get(0).getPatientType())) )
		{
			msg ="Please enter "+(days-count)+" clinical notes";	
		}
		if( count == 0 && days <= count && ((lstPreauthVo.get(0).getPatientType() != null && !"".equalsIgnoreCase(lstPreauthVo.get(0).getPatientType())) && !"IPM".equalsIgnoreCase(lstPreauthVo.get(0).getPatientType())) )
		{
			msg ="Please enter atleast one clinical notes"	;
		}
		
		}
	}
	catch(Exception e)
	{
	e.printStackTrace();	
	}
	return msg;
}
public String savePotoAttach(AttachmentVO attachmentVO)
{
	String msg = null;
	Date date = new Date();
	try
	{
		EhfPatientDocAttach asritPatientDocAttach = new EhfPatientDocAttach();
		asritPatientDocAttach.setActualName(attachmentVO.getActualName());
		asritPatientDocAttach.setAttachType("CD3002");
		asritPatientDocAttach.setCrtDate(new java.sql.Timestamp(date.getTime()));
		asritPatientDocAttach.setCrtUser(attachmentVO.getCrtUsrName());
		asritPatientDocAttach.setDocSeqId(getNextVal(config.getString("preauth_EHF_PATIENT_DOC_ATTCH")));
		asritPatientDocAttach.setLangId("en_US");
		asritPatientDocAttach.setPatientId(attachmentVO.getEmpParentId());
		asritPatientDocAttach.setSavedName(attachmentVO.getSavedName());
		genericDao.save(asritPatientDocAttach);
		
		
	}catch(Exception e)
	{
	e.printStackTrace();	
	}
	return msg;
}
private Long getNextVal(String SeqName)
{
	  Long cnt = (long) 0;
	  try{
		  EhfmSeq SgvcSeqMst = genericDao.findFirstByPropertyMatch(EhfmSeq.class,"tableName",SeqName);
		  if(SgvcSeqMst != null)
		  {
			 cnt =  SgvcSeqMst.getSeqNextVal(); 
		  }
		  SgvcSeqMst.setSeqNextVal(SgvcSeqMst.getSeqNextVal()+1);
		  genericDao.save(SgvcSeqMst);
			
	  }
catch(Exception e)
	{
		e.printStackTrace();
	}
	
	  return cnt;
}
public PreauthVO getPatientOpDtls(String pStrCaseId,String pStrPatientId)
{
	PreauthVO PreauthVO = preauthDtlsDao.getPatientOpDtls(pStrCaseId, pStrPatientId);
	return PreauthVO;
}
public ClaimsFlowVO getInvestigationDetails(ClaimsFlowVO claimsFlowVO){
	List<LabelBean> investDetails = null;
	StringBuffer queryBuff = new StringBuffer();
	if(!"".equalsIgnoreCase(claimsFlowVO.getNimsFlag()) && "N".equalsIgnoreCase(claimsFlowVO.getNimsFlag()))	
	queryBuff.append(" select type WFTYPE, quant_name NAME, sum(no_of_units) COUNT, ehq.amount UNITAMOUNT, sum(ec.amount) AMOUNT,ec.attach_path ATTACH   from ehf_case_enhancement_dtls ec, ehfm_hosp_quantity_nabh ehq where enh_quan_code = quant_id  and ec.active_y_n='Y'  and case_id = ? group by type, quant_name ,ehq.amount,ec.attach_path ");
	else
		queryBuff.append(" select type WFTYPE, item_desc NAME, sum(no_of_units) COUNT, ehq.amount UNITAMOUNT, sum(ec.amount) AMOUNT,ec.attach_path ATTACH   from ehf_case_enhancement_dtls ec, EHFM_HOSP_QUANTITY_NIMS ehq where enh_quan_code = item_code  and ec.active_y_n='Y'  and case_id = ? group by type, item_desc ,ehq.amount,ec.attach_path ");	
		
		investDetails = genericDao.executeSQLQueryList(LabelBean.class,queryBuff.toString(),new String[]{claimsFlowVO.getCaseId()} );
		
	if(investDetails != null && investDetails.size() > 0 ){
		claimsFlowVO.setInvestDetails(investDetails);
	}
	queryBuff = new StringBuffer();
	queryBuff.append("  select sum(amount) as TOTCONSUMABLEAMOUNT from ehf_case_enhancement_dtls where  case_id = ? and active_y_n='Y' ");
	investDetails = genericDao.executeSQLQueryList(LabelBean.class,queryBuff.toString(),new String[]{claimsFlowVO.getCaseId()} );
		
	if(investDetails != null && investDetails.size() > 0 ){
		claimsFlowVO.setTotalConsumableAmount(investDetails.get(0).getTOTCONSUMABLEAMOUNT());
	}
	
	return claimsFlowVO;
}
//To get drug details
public ClaimsFlowVO getDrugDetailsData(ClaimsFlowVO claimFlowVO) {

	List<LabelBean> drugDetails = null;
	StringBuffer queryBuff = new StringBuffer();
	queryBuff.append(" select patient_ipop MEDISURGFLAG from ehf_patient,ehf_case where case_id=? and patient_id=case_patient_no ");
	drugDetails = genericDao.executeSQLQueryList(LabelBean.class,queryBuff.toString(),new String[]{claimFlowVO.getCaseId()} );
	if(drugDetails!=null && drugDetails.get(0).getMEDISURGFLAG().equalsIgnoreCase(ClaimsConstants.MEDICAL_FLAG))
	{
		queryBuff = new StringBuffer();
		claimFlowVO.setMedicalSurgFlag(ClaimsConstants.MEDICAL_FLAG);	
		queryBuff.append(" select ee.caseId as CASEID, cast(ee.amount as string )  as DRUGAMOUNT, cast(ee.id.sno as string) as SEQID,  ee.attachName as ATTACHNAME, ee.attachPath as ATTACHPATH, to_char(ee.crtDt,'DD-MM-YYYY hh24:mi:ss') as CRTDT ");
		queryBuff.append(" from EhfPatientDrugsNabh ee  WHERE ee.activeYN = 'Y' and ee.caseId = '"+claimFlowVO.getCaseId()+"' ");
		drugDetails=genericDao.executeHQLQueryList(LabelBean.class, queryBuff.toString());	
		
		if(drugDetails != null && drugDetails.size() > 0 ){
			claimFlowVO.setDrugDetails(drugDetails);
		}
		queryBuff = new StringBuffer();
		queryBuff.append("  select sum(AMOUNT) as TOTALDRUGAMT from EHF_PATIENT_DRUGS_NABH where  case_id = ? and ACTIVE_YN='Y' ");
		drugDetails = genericDao.executeSQLQueryList(LabelBean.class,queryBuff.toString(),new String[]{claimFlowVO.getCaseId()} );
			
		if(drugDetails != null && drugDetails.size() > 0 ){
			claimFlowVO.setTotalDrugAmt(drugDetails.get(0).getTOTALDRUGAMT());
		}
	}
	else
	{
		claimFlowVO.setMedicalSurgFlag(ClaimsConstants.SURGICAL_FLAG);			
	}
	return claimFlowVO;
}
public PreauthVO getTreatingDocDtls(String patientId,String caseId)
{
	PreauthVO preauthVO = new PreauthVO();	
	try
	{
		if(caseId!=null && !"".equalsIgnoreCase(caseId))
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
			EhfCase  ehfCase = genericDao.findById(EhfCase.class, String.class, caseId);
			if(ehfCase != null)
			{
				if(ehfCase.getCsAdmDt() != null)
					preauthVO.setAdmissionDate(sdf1.format( ehfCase.getCsAdmDt()));
				preauthVO.setProcedureConsent(ehfCase.getProcedureConsent());
				preauthVO.setMedCardioClearence(ehfCase.getMedCardioClearence());
				preauthVO.setBloodTransfusion(ehfCase.getBloodTransfusion());
				preauthVO.setHospitalId(ehfCase.getCaseHospCode());
				preauthVO.setPatientID(ehfCase.getCasePatientNo());
				preauthVO.setIpRegDate(sdf.format(ehfCase.getCrtDt()));
				preauthVO.setPatientScheme(ehfCase.getPatientScheme());
				preauthVO.setCochlearYN(ehfCase.getCochlearYN());
				preauthVO.setCochlearQues(ehfCase.getCochlearQues());
				preauthVO.setOrganTransYN(ehfCase.getOrganTransYN());//For organ tranplant
				preauthVO.setEnhancementFlag(ehfCase.getEnhFlag());
				if(ehfCase.getIpNo()!=null)
					preauthVO.setPatientIPNo(ehfCase.getIpNo());	
				if(ehfCase.getCsEnhRejDt() != null && !ehfCase.getCsEnhRejDt().equals(""))
				preauthVO.setEnhRejDt(ehfCase.getCsEnhRejDt().toString());
				preauthVO.setEnhCaseStatus(ehfCase.getEnhCaseStatus());
				if(ehfCase.getComorBidAmt() != null && !ehfCase.getComorBidAmt().equals(""))
				preauthVO.setComorBidAmt(ehfCase.getComorBidAmt().toString());
				preauthVO.setComorBidVals(ehfCase.getComorbidVals());
				preauthVO.setCeoApprvFlag(ehfCase.getCeoApprovalFlag());
				preauthVO.setMedicalSurgicalFlag(preauthClinicalNotesDao.getMedicalFlag(caseId));
				preauthVO.setPendingFlag(ehfCase.getPendingFlag());
				preauthVO.setCaseNabhFlg(ehfCase.getNabhFlg());
				if(ehfCase.getEnhReqAmt() != null)
				preauthVO.setEnhAmt(ehfCase.getEnhReqAmt().toString());
				if(ehfCase.getCsPreauthDt() != null && !ehfCase.getCsPreauthDt().equals("") )
					preauthVO.setPreauthInitiatedDt(ehfCase.getCsPreauthDt().toString());
				if(ehfCase.getPreauthAprvDate() != null && !ehfCase.getPreauthAprvDate().equals("") )
					preauthVO.setPreauthAppvDt(ehfCase.getPreauthAprvDate().toString());
				if(ehfCase.getPreauthRejDate() != null && !ehfCase.getPreauthRejDate().equals("") )
					preauthVO.setPreauthRejDt(ehfCase.getPreauthRejDate().toString());
				if(ehfCase.getCsSurgDt() != null && !ehfCase.getCsSurgDt().equals("") )
					preauthVO.setSurgeryDt(sdf.format(ehfCase.getCsSurgDt()));
				if(ehfCase.getCsDisDt() != null && !ehfCase.getCsDisDt().equals("") )
					preauthVO.setDischrgeDt(sdf.format(ehfCase.getCsDisDt()));
				if(ehfCase.getPreauthCancelDt() != null && !ehfCase.getPreauthCancelDt().equals("") )
					preauthVO.setPreauthCancelDt(ehfCase.getPreauthCancelDt().toString());
				if(ehfCase.getComorbidVals() != null && !ehfCase.getComorbidVals() .equalsIgnoreCase(""))
				{
					List<LabelBean> comorBidVals = new ArrayList<LabelBean>();
					StringTokenizer str = new StringTokenizer(ehfCase.getComorbidVals(),"~");
					while(str.hasMoreTokens())
					{
						EhfmComorbid ehfmComorbid = genericDao.findById(EhfmComorbid.class, String.class, str.nextToken());	
						if(ehfmComorbid != null)
						{
							comorBidVals.add(new LabelBean(ehfmComorbid.getComorbidName(),ehfmComorbid.getComorbidVal(), ehfmComorbid.getComorbidId()));	
						}
					}
					preauthVO.setComorbidValues(comorBidVals);
				}
				if(ehfCase.getPreauthTotalPackageAmt()!=null)
				{
					preauthVO.setPreauthTotalPackageAmt(String.valueOf(ehfCase.getPreauthTotalPackageAmt()));
				}
				if(ehfCase.getCsEnhApvDt() != null && !ehfCase.getCsEnhApvDt().equals(""))
					preauthVO.setEnhApvDt(ehfCase.getCsEnhApvDt().toString());
				if(!"".equalsIgnoreCase(ehfCase.getExceedFlg()) &&  ehfCase.getExceedFlg() != null)
					preauthVO.setExceedFlag(ehfCase.getExceedFlg());
				preauthVO.setTotPackgAmt(ehfCase.getPreauthTotPckgAmt());
				preauthVO.setPreauthPckgAmt(ehfCase.getPreauthPckgAmt());
				preauthVO.setScheme(ehfCase.getSchemeId());
				EhfPatientHospDiagnosis ehfPatientHospDiagnosis = genericDao.findById(EhfPatientHospDiagnosis.class, String.class, ehfCase.getCasePatientNo());	
				if(ehfPatientHospDiagnosis != null)
				{
					preauthVO.setDocName(ehfPatientHospDiagnosis.getDoctName());
					preauthVO.setDocReg(ehfPatientHospDiagnosis.getDoctRegNo());
					preauthVO.setDocQual(ehfPatientHospDiagnosis.getDoctQuali());
					preauthVO.setDocMobNo(ehfPatientHospDiagnosis.getDoctMobNo());
					preauthVO.setDocType(ehfPatientHospDiagnosis.getDoctType());
					if(ehfPatientHospDiagnosis.getCaseAdmType()!=null && !"".equalsIgnoreCase(ehfPatientHospDiagnosis.getCaseAdmType()))
						preauthVO.setAdmissionRadio(ehfPatientHospDiagnosis.getCaseAdmType().trim());
					preauthVO.setLegalCaseCheck(ehfPatientHospDiagnosis.getLegalCaseCheck());
					preauthVO.setLegalCaseNo(ehfPatientHospDiagnosis.getLegalCaseNo());
					preauthVO.setPoliceStatName(ehfPatientHospDiagnosis.getPoliceStatName());
					/**
					 * get diagnosis and treatment
					 */
					List<GenericDAOQueryCriteria> criteraiList = new ArrayList<GenericDAOQueryCriteria>();
					criteraiList.add(new GenericDAOQueryCriteria("id.diagnosisCode",ehfPatientHospDiagnosis.getDiagnosisType(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					
					List<EhfmDiagnosisMst> lstEhfmDiagnosisMst = genericDao.findAllByCriteria(EhfmDiagnosisMst.class, criteraiList);
					
					if(lstEhfmDiagnosisMst != null && lstEhfmDiagnosisMst.size() >0)
						preauthVO.setDiagnosisType(lstEhfmDiagnosisMst.get(0).getDiagnosisName());
					EhfmDiagMainCatMst ehfmDiagMainCatMst =  genericDao.findById(EhfmDiagMainCatMst.class, EhfmDiagMainCatMstId.class, new EhfmDiagMainCatMstId(ehfPatientHospDiagnosis.getMainCatCode(),ehfPatientHospDiagnosis.getDiagnosisType()));
			        if(ehfmDiagMainCatMst !=null)
			        	preauthVO.setMainCatName(ehfmDiagMainCatMst.getMainCatName());
			        EhfmDiagSubCatMst ehfmDiagSubCatMst = genericDao.findById(EhfmDiagSubCatMst.class, EhfmDiagSubCatMstId.class, new EhfmDiagSubCatMstId( ehfPatientHospDiagnosis.getSubCatCode(),ehfPatientHospDiagnosis.getCategoryCode()));
			    	if(ehfmDiagSubCatMst != null)
			    		preauthVO.setSubCatName(ehfmDiagSubCatMst.getSubCatName());
			    	EhfmDiagCategoryMst ehfmDiagCategoryMst = genericDao.findById(EhfmDiagCategoryMst.class, EhfmDiagCategoryMstId.class,new EhfmDiagCategoryMstId(ehfPatientHospDiagnosis.getCategoryCode(), ehfPatientHospDiagnosis.getMainCatCode()));
				    if(ehfmDiagCategoryMst != null)
				    	preauthVO.setCatName(ehfmDiagCategoryMst.getCatName());
				    
				    List<GenericDAOQueryCriteria> criteriaList = null;
				    criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
		    		 criteriaList.add(new GenericDAOQueryCriteria("id.subCatCode",ehfPatientHospDiagnosis.getSubCatCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					 criteriaList.add(new GenericDAOQueryCriteria("id.diseaseCode",ehfPatientHospDiagnosis.getDiseaseCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					 List<EhfmDiagDiseaseMst> diseaseLst = genericDao.findAllByCriteria(EhfmDiagDiseaseMst.class, criteriaList);
				   // EhfmDiagDiseaseMst ehfmDiagDiseaseMst = genericDao.findById(EhfmDiagDiseaseMst.class,EhfmDiagDiseaseMstId.class,new EhfmDiagDiseaseMstId(ehfPatientHospDiagnosis.getDiseaseCode(),ehfPatientHospDiagnosis.getCategoryCode(), ehfPatientHospDiagnosis.getSubCatCode()));
					if(diseaseLst != null && diseaseLst.size()>0)
						preauthVO.setDisName(diseaseLst.get(0).getDiseaseName());
				    
				    
				    criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
		    		 criteriaList.add(new GenericDAOQueryCriteria("id.diseaseCode",ehfPatientHospDiagnosis.getDiseaseCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					 criteriaList.add(new GenericDAOQueryCriteria("id.disAnatomicalCode",ehfPatientHospDiagnosis.getDiseaseAnatCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					 List<EhfmDiagDisAnatomicalMst> disAnatomicalLst = genericDao.findAllByCriteria(EhfmDiagDisAnatomicalMst.class, criteriaList);
				    //EhfmDiagDisAnatomicalMst ehfmDiagDisAnatomicalMst = genericDao.findById(EhfmDiagDisAnatomicalMst.class, EhfmDiagDisAnatomicalMstId.class,new EhfmDiagDisAnatomicalMstId(ehfPatientHospDiagnosis.getDiseaseAnatCode(),ehfPatientHospDiagnosis.getDiseaseCode(),ehfPatientHospDiagnosis.getCategoryCode(), ehfPatientHospDiagnosis.getSubCatCode()));
			    	if(disAnatomicalLst != null && disAnatomicalLst.size()>0)
			    		preauthVO.setDisAnatomicalSitename(disAnatomicalLst.get(0).getDisAnatomicalName());
			    	
				}
				
				 EhfmHospitals ehfmHospitals = genericDao.findById(EhfmHospitals.class, String.class, ehfCase.getCaseHospCode());
				
				 /*Added by venkatesh for bifurcation changes*/
				 
				 EhfmEDCHospActionDtls ehfmEDCHospActionDtls=new EhfmEDCHospActionDtls();
				 EhfmEDCHospActionDtlsId ehfmEDCHospActionDtlsId=new EhfmEDCHospActionDtlsId(ehfCase.getCaseHospCode(),ehfCase.getSchemeId());
				 ehfmEDCHospActionDtls=genericDao.findById(EhfmEDCHospActionDtls.class,EhfmEDCHospActionDtlsId.class,ehfmEDCHospActionDtlsId);
						
				 if(ehfmHospitals != null)
				 {
					 preauthVO.setHospitalId(ehfmHospitals.getHospId());
					 preauthVO.setHospitalName(ehfmHospitals.getHospName());
					 preauthVO.setHospDispCode(ehfmHospitals.getHospDispCode());
					 if(ehfmHospitals.getHouseNumber() != null && !ehfmHospitals.getHouseNumber().equalsIgnoreCase("null"))
					 preauthVO.setHospAddress(ehfmHospitals.getHouseNumber());
					 if(ehfmHospitals.getStreet() != null && !ehfmHospitals.getStreet().equalsIgnoreCase("null"))
					 preauthVO.setHospAddress(preauthVO.getHospAddress()+" , "+ehfmHospitals.getStreet());
					 if(ehfmHospitals.getHospCity() != null && !ehfmHospitals.getHospCity().equalsIgnoreCase("null"))
						 preauthVO.setHospAddress(preauthVO.getHospAddress()+" , "+ehfmHospitals.getHospCity());
					 if(ehfmHospitals.getHospContactNo() != null && !ehfmHospitals.getHospContactNo().equalsIgnoreCase("null"))
						 preauthVO.setHospAddress(preauthVO.getHospAddress()+" , "+ehfmHospitals.getHospContactNo());
					 preauthVO.setHospContactNo(ehfmHospitals.getHospContactNo());
					 preauthVO.setNabhFlg(ehfmHospitals.getNabhFlg());
					 EhfPatient ehfpatient = getPatientInfo(ehfCase.getCasePatientNo());
					 String nabhFlag = ehfmEDCHospActionDtls.getNabhFlag();
					 if(ehfpatient.getSchemeId()!=null && ehfpatient.getSchemeId().equalsIgnoreCase("CD201") && ehfCase.getPhaseId()!=null && ehfCase.getPhaseId()==0)
					 {
						 if(nabhFlag!=null && nabhFlag.equalsIgnoreCase("Y"))
							 nabhFlag = "N";
					 }
					 EhfmSlabPackage ehfmSlabPackage = genericDao.findById(EhfmSlabPackage.class, EhfmSlabPackageId.class, new EhfmSlabPackageId(nabhFlag,ehfpatient.getSlab()));
					 if(ehfmSlabPackage != null)
					 preauthVO.setHospStayAmt(ehfmSlabPackage.getAmount());
					 
				 }
				 preauthVO.setDoctorSpeciality(getDocSpeciality(preauthVO.getDocReg()));
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();	
	}
	return preauthVO;
}

public String getDocSpeciality(String docRegNo)
{
	String docSpec = "";
	try {
		StringBuffer lQueryBuffer = new StringBuffer();
		lQueryBuffer.append(" select  distinct es.disMainId as ID, esd.id.regNum as VALUE, es.disMainName as UNITID ");
		lQueryBuffer.append(" from EhfmSplstDctrs esd, EhfmSpecialities es, EhfmDotorSplty eds ");
		lQueryBuffer.append(" where esd.id.regNum='"+docRegNo+"' and eds.id.regNum= esd.id.regNum ");
		lQueryBuffer.append(" and es.disMainId = eds.id.spctlyCode and es.disActiveYN='Y' ");
		List<LabelBean> specList = genericDao.executeHQLQueryList(LabelBean.class, lQueryBuffer.toString());
		if (!specList.isEmpty())
		{
			for(int i=0; i<specList.size(); i++)
			{
				if(specList.get(i).getUNITID()!=null && !"".equalsIgnoreCase(specList.get(i).getUNITID()))
				{
					if(i==0 || docSpec==null || "".equalsIgnoreCase(docSpec))
						docSpec= docSpec+specList.get(i).getUNITID();
					else
						docSpec= docSpec+", "+specList.get(i).getUNITID();
				}
			}
		}
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	return docSpec;
}
/**
 * added by 350262 and changed acc to new data
 */

public String upldSplInvestAttachments(AttachmentVO attachmentVO)
{
	String msg = null;
	Date date = new Date();
	try
	{
		/**
		 * Make the previous attachment of same procedure inactive
		 */
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		criteriaList.add(new GenericDAOQueryCriteria("activeYN","Y",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	    criteriaList.add(new GenericDAOQueryCriteria("caseId",attachmentVO.getCaseId(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	    criteriaList.add(new GenericDAOQueryCriteria("icdProcCode",attachmentVO.getSurgeryId(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));	
	    List<EhfCaseTherapyInvest> lstCaseTherapyInvest=genericDao.findAllByCriteria(EhfCaseTherapyInvest.class, criteriaList);
	    for(EhfCaseTherapyInvest caseTherapyInvest : lstCaseTherapyInvest)
		{
	    	caseTherapyInvest.setActiveYN("N");
	    	caseTherapyInvest.setLstUpdDt(new java.sql.Timestamp(date.getTime()));
	    	caseTherapyInvest.setLstUpdUsr(attachmentVO.getCrtUsrName());
			genericDao.save(caseTherapyInvest);
		}
	    /**
		 * end
		 */
	    
		EhfCaseTherapyInvest ehfCaseTherapyInvest = new EhfCaseTherapyInvest();
		ehfCaseTherapyInvest.setSno(Long.parseLong(commonService.getSequence(config.getString("CASE_THER_INVEST_ID"))));
		//ehfCaseTherapyInvest.setSno(getNextVal(config.getString("preauth_EHF_CASE_THERAPY_INVEST")));
		ehfCaseTherapyInvest.setCaseId(attachmentVO.getCaseId());
		ehfCaseTherapyInvest.setFilename(attachmentVO.getActualName());
		ehfCaseTherapyInvest.setAttachTotalPath(attachmentVO.getSavedName());
		ehfCaseTherapyInvest.setActiveYN(config.getString("activeY"));
		ehfCaseTherapyInvest.setIcdProcCode(attachmentVO.getSurgeryId());
		ehfCaseTherapyInvest.setInvestigationId(attachmentVO.getSurgInvstId());
		ehfCaseTherapyInvest.setCrtDt(new java.sql.Timestamp(date.getTime()));
		ehfCaseTherapyInvest.setCrtUsr(attachmentVO.getCrtUsrName());
		genericDao.save(ehfCaseTherapyInvest);
		
	}catch(Exception e)
	{
	e.printStackTrace();	
	}
	return msg;
}
/*private Long getMaxSno(String tabName , String colName) {
	Long sno = (long) 0;
	try {
		StringBuffer lQueryBuffer = new StringBuffer();
		lQueryBuffer.append("select MAX("+colName+") as IDVAL  from "+tabName+"  ");
		List<LabelBean> maxSno = genericDao.executeHQLQueryList(LabelBean.class, lQueryBuffer.toString());
		if (!maxSno.isEmpty())
			if (maxSno.get(0).getIDVAL() != null)
				sno = maxSno.get(0).getIDVAL()+1;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return sno;
}*/
public String deleteSplInvstAttach(String pStrsno,String pStrCrtUsr)
{
	String msg = null;
	Date date = new Date();
	try
	{
	EhfCaseTherapyInvest ehfCaseTherapyInvest = genericDao.findById(EhfCaseTherapyInvest.class, Long.class, Long.parseLong(pStrsno));
	if(ehfCaseTherapyInvest != null)
	{
		ehfCaseTherapyInvest.setActiveYN(config.getString("activeN"));
		ehfCaseTherapyInvest.setLstUpdUsr(pStrCrtUsr);
		ehfCaseTherapyInvest.setLstUpdDt(new java.sql.Timestamp(date.getTime()));
		genericDao.save(ehfCaseTherapyInvest);
		msg=ehfCaseTherapyInvest.getFilename() + "  deleted successfully";
	}
	}catch(Exception e)
	{
		msg = "failure";
	e.printStackTrace();	
	}
	return msg;
}
public PreauthVO getNamDeclarationDtls(String pStrPatId)
{
	PreauthVO preauthVO = new PreauthVO();
	StringBuffer query = new StringBuffer();
	try
	{
		query.append(" select p.name as patientName , p.cardNo as patCardNo , l1.locName as district , l2.locName  as mandal , l3.locName as village , p.crtDt as date ");
		query.append(" from EhfPatient p ,EhfmLocations l1, EhfmLocations l2 , EhfmLocations l3  ");
		query.append(" where p.patientId = '"+pStrPatId+"' and  p.districtCode = l1.id.locId and  ");
		query.append(" p.mandalCode = l2.id.locId and p.villageCode = l3.id.locId ");
		List<PreauthVO> lstPreauthVO = genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
       if(lstPreauthVO != null && lstPreauthVO.size() >0)
    	   preauthVO = lstPreauthVO.get(0);
	}catch(Exception e)
	{
	e.printStackTrace();	
	}
	return preauthVO;
}
public List<LabelBean> getHospStayist(String pStrenhType)
{
	List<LabelBean> lstHospStay = new ArrayList<LabelBean>();
	StringBuffer query = new StringBuffer();
	try
	{
		query.append(" select a.hospStayId as ID , a.stayType as VALUE   from EhfmHospStay a where a.enhType ='"+pStrenhType+"' order by a.stayType asc ");
		lstHospStay = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	return lstHospStay;
}
public List<LabelBean> getHospStayQuantList(String pStrenhTypeId)
{
	List<LabelBean> lstHospStayQuant = new ArrayList<LabelBean>();
	StringBuffer query = new StringBuffer();
	try
	{
		query.append(" select a.quantId as ID , a.quantName as VALUE   from EhfmHospQuantity a where a.enhTypeId='"+pStrenhTypeId+"' and a.activeYn='Y' order by a.quantName asc ");
		lstHospStayQuant = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	return lstHospStayQuant;	
}
 public Integer getQuantAmount(String quantId)
 {
	 int amount =0;
	 try
	 {
		 EhfmHospQuantity ehfmHospQuantity = genericDao.findById(EhfmHospQuantity.class, String.class, quantId); 
		 if(ehfmHospQuantity != null)
		 {
			 amount = ehfmHospQuantity.getAmount(); 	 
		 }
		 
	 }catch(Exception e)
	 {
		e.printStackTrace(); 
	 }
	return amount; 
 }
 
 public Integer getQuantAmountForNabh(String quantId, String nabhFlag,String caseId)
 {
	 int amount =0;
	 try
	 {
		 EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, caseId);
		 if(ehfCase!=null){
			 if(ehfCase.getPhaseId()!=null && ehfCase.getPhaseId()==0 && ehfCase.getSchemeId()!=null && ehfCase.getSchemeId().equalsIgnoreCase("CD201") && nabhFlag!=null && nabhFlag.equalsIgnoreCase("Y"))
				 nabhFlag="N";
		 }
		 
		 
		List<GenericDAOQueryCriteria> critList = new ArrayList<GenericDAOQueryCriteria>();
		critList.add(new GenericDAOQueryCriteria("quantId",quantId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));		
		critList.add(new GenericDAOQueryCriteria("id.nabhFlag",nabhFlag,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));		
		List<EhfmSlabPackage> ehfmSlabPackage = genericDao.findAllByCriteria( EhfmSlabPackage.class, critList) ;	
		if(ehfmSlabPackage!=null && ehfmSlabPackage.size()>0)
		{
			if(ehfmSlabPackage.get(0).getAmount()!=null && !"".equalsIgnoreCase(ehfmSlabPackage.get(0).getAmount()))
				amount= Integer.parseInt(ehfmSlabPackage.get(0).getAmount());
		}
		 
	 }catch(Exception e)
	 {
		 gLogger.error("Exception in method getQuantAmountForNabh() of PreauthServiceImpl--> "+ e.getMessage());
	 }
	return amount; 
 }
 public List<PreauthVO> getEnhancementList(String pStrcaseId,String patientType,String nimsFlag)
 {
	 List<PreauthVO> lstEnhancement = new ArrayList<PreauthVO>();
	 StringBuffer query = new StringBuffer();
	 try
	 {
		 if(!"".equalsIgnoreCase(nimsFlag) && "N".equalsIgnoreCase(nimsFlag))
		 {
			 query.append("  select ee.type as enhType ,hs.stayType as enhCode , qm.quantName as enhQuant , cast(ee.noOfUnits as string ) as enhUnits , cast(ee.amount as string ) as enhAmt ");	
			 query.append(" , cast(ee.sno as string) as enhSno , ee.enhQuantCode  as enhQuantCode,ee.attachPath as attachPath,ee.attachName as attachName,to_char(ee.crtDt,'DD-MM-YYYY hh24:mi:ss') as caseCrtDt ");
			 query.append(" from EhfCaseEnhancementDtls ee , EhfmHospStay hs , EhfmHospQuantity qm  ");
			 query.append("  where ee.enhQuantCode = qm.quantId and ");	
			 query.append(" ee.enhCode = hs.id.hospStayId  ");
			 query.append("   and ee.caseId = '"+pStrcaseId+"' and ee.activeYN ='Y' order by ee.crtDt ");	
		 }
		 else
		 {
			 query.append("  select ee.type as enhType ,hs.deptName as enhCode , qm.itemDesc as enhQuant , cast(ee.noOfUnits as string ) as enhUnits , cast(ee.amount as string ) as enhAmt ");	
			 query.append(" , cast(ee.sno as string) as enhSno , ee.enhQuantCode  as enhQuantCode,ee.attachPath as attachPath,ee.attachName as attachName,to_char(ee.crtDt,'DD-MM-YYYY hh24:mi:ss') as caseCrtDt ");
			 query.append(" from EhfCaseEnhancementDtls ee , EhfmHospStayNIMS hs , EhfmHospQuantityNIMS qm  ");
			 query.append("  where ee.enhQuantCode = qm.itemCode and ");	
			 query.append(" ee.enhCode = hs.id.deptId  ");
			 query.append("   and ee.caseId = '"+pStrcaseId+"' and ee.activeYN ='Y' order by ee.crtDt ");	
		 }
		 lstEnhancement = genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
	 }catch(Exception e)
	 {
	e.printStackTrace();	 
	 }
	 return lstEnhancement;
 }
/* private List<PreauthVO> getAuditTrailASRI(PreauthVO PStrpreauthVO)
 {
 	List<PreauthVO> lstWorkList = new ArrayList<PreauthVO>();	
 	List<PreauthVO> lstauditVO = new ArrayList<PreauthVO>();
 	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy hh:mm:ss");
 	 String sName=null;
 	  String lStrStatId=null;
 	try{
 		StringBuffer query = new StringBuffer();
 		query.append(" select  a.actId as actId,");
 		query.append(" a.remarks as auditRemarks , ");
 		query.append(" au.firstName ||''|| au.lastName as actby ,  ");
 		query.append(" cast(a.id.actOrder as string) as rationCard,  a.crtDt as date,");
 		query.append(" au.userRole as userRole, ac.cmbDtlName as cmbDtlname ");
 		query.append(" from EhfAudit a , AsrimCombo ac , AsrimUsers au  ");
 		query.append(" where a.id.caseId = '"+PStrpreauthVO.getCaseId()+"' and a.actBy = au.userID and ac.id.cmbDtlId=au.userRole ");
 		query.append(" order by a.id.actOrder ");
 		
 		lstWorkList = genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
 		for(PreauthVO preauthVO :lstWorkList)
 		{
 		 if(!"CD691".equalsIgnoreCase(preauthVO.getActId()))
          {            
 			 PreauthVO  auditVO=new PreauthVO();
              if(!"CD9".equalsIgnoreCase(PStrpreauthVO.getUserRole()) && !"CD10".equalsIgnoreCase(PStrpreauthVO.getUserRole()))
                  sName=" - "+preauthVO.getActby();
              else 
              {
                  if(!"CD9".equalsIgnoreCase(preauthVO.getUserRole()) && !"CD10".equalsIgnoreCase(preauthVO.getUserRole()))
                  sName="";
                  else
                      sName=" - "+preauthVO.getActby();
              }
              auditVO.setAuditName(sName);
              auditVO.setAuditRole(preauthVO.getCmbDtlname());
              auditVO.setAuditComboRole(preauthVO.getUserRole());
              auditVO.setAuditDate( preauthVO.getDate());
              if(preauthVO.getAuditRemarks()!=null)                
              auditVO.setAuditRemarks(preauthVO.getAuditRemarks());
              else
              auditVO.setAuditRemarks("-");
              lStrStatId=preauthVO.getActId();
              //RAMCO -In Patient Case Registered
              if("CD73".equalsIgnoreCase(lStrStatId))
              {
              auditVO.setAuditAction("Initiate");
              auditVO.setAuditAmount(0);
              } 
              //RAMCO -Preauth Updated
              else if("CD75".equalsIgnoreCase(lStrStatId))
              {
              auditVO.setAuditAction("Forward");
              auditVO.setAuditAmount(0);
              }
              //RAMCO -Sent For Preauthorization
              else if("CD76".equalsIgnoreCase(lStrStatId))
              {
              auditVO.setAuditAction("Forward");
              auditVO.setAuditAmount(0);
              } 
              //Preauth Executive Verified
              else if("CD771".equalsIgnoreCase(lStrStatId))
              {
              auditVO.setAuditAction("Verified");
              auditVO.setAuditAmount(0);
              } 
              //Insurance Pending Updated
              else if("CD92".equalsIgnoreCase(lStrStatId))
              {
              auditVO.setAuditAction("Update Insurance Pending");
              auditVO.setAuditAmount(0);
              }
              else if("CD108".equalsIgnoreCase(lStrStatId))
              {
              auditVO.setAuditAction("Update Trust Pending");
              auditVO.setAuditAmount(0);
              }          
              else if("CD109".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Update Ceo Pending");
              auditVO.setAuditAmount(0);
              } 
              else if("CD226".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Update Committee Pending");
              auditVO.setAuditAmount(0);
              } 
              
              else if("CD305".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Update Panel Doctor Pending");
              auditVO.setAuditAmount(0);
              }                
              else if("CD260".equalsIgnoreCase(lStrStatId) || "CD275".equalsIgnoreCase(lStrStatId) || "CD299".equalsIgnoreCase(lStrStatId) ||
                      "CD79".equalsIgnoreCase(lStrStatId) || "CD304".equalsIgnoreCase(lStrStatId) || "CD103".equalsIgnoreCase(lStrStatId) ||
                      "CD93".equalsIgnoreCase(lStrStatId) || "CD94".equalsIgnoreCase(lStrStatId) || "CD348".equalsIgnoreCase(lStrStatId))
              {
              auditVO.setAuditAction("Recommend Pending");
              auditVO.setAuditAmount(0);
              }                
              else if("CD111".equalsIgnoreCase(lStrStatId) || "CD312".equalsIgnoreCase(lStrStatId) || "CD481".equalsIgnoreCase(lStrStatId))
              {
              auditVO.setAuditAction(" Recommend Termination");  
              auditVO.setAuditAmount(0);
              }
              else if("CD77".equalsIgnoreCase(lStrStatId) || "CD302".equalsIgnoreCase(lStrStatId) || "CD101".equalsIgnoreCase(lStrStatId))
              {
              auditVO.setAuditAction("Recommend Approval");  
             // auditVO.setAuditAmount(preVO.getInsAprvAmt());
              }
              else if("CD78".equalsIgnoreCase(lStrStatId) || "CD303".equalsIgnoreCase(lStrStatId) || "CD102".equalsIgnoreCase(lStrStatId))
              {
              auditVO.setAuditAction("Recommend Rejection");   
              auditVO.setAuditAmount(0);
              }
              else if("CD67".equalsIgnoreCase(lStrStatId) || "CD65".equalsIgnoreCase(lStrStatId) || "CD69".equalsIgnoreCase(lStrStatId))
              {
              auditVO.setAuditAction("Recommend Cancellation Pending");    
              auditVO.setAuditAmount(0);
              }
              else if("CD126".equalsIgnoreCase(lStrStatId) || "CD311".equalsIgnoreCase(lStrStatId) || "CD480".equalsIgnoreCase(lStrStatId) )
              {
              auditVO.setAuditAction("Recommend Cancellation");        
              auditVO.setAuditAmount(0);
              }             
              else if("CD319".equalsIgnoreCase(lStrStatId))
              {
              auditVO.setAuditAction("Recommend Termination Pending");
              auditVO.setAuditAmount(0);
              } 
              else if("CD128".equalsIgnoreCase(lStrStatId))
              {
              auditVO.setAuditAction("Terminate");
              auditVO.setAuditAmount(0);
              }
              else if("CD81".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Approve");
             // auditVO.setAuditAmount(preVO.getTrustAprvAmt());
              }
              else if("CD91".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Recommend Approval");
             // auditVO.setAuditAmount(preVO.getTrustAprvAmt());
              }
              else if("CD82".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Reject");
              auditVO.setAuditAmount(0);
              }
              else if("CD219".equalsIgnoreCase(lStrStatId))
              {
              auditVO.setAuditAction("Recommend Cancellation Pending");
              auditVO.setAuditAmount(0);
              }
              else if("CD127".equalsIgnoreCase(lStrStatId) )
              {
              auditVO.setAuditAction("Cancel");
              auditVO.setAuditAmount(0);
              }               
              else if("CD324".equalsIgnoreCase(lStrStatId))
              {  
              auditVO.setAuditAction("Recommend Termination Pending");
              auditVO.setAuditAmount(0);
              }
              else if("CD136".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Terminate");
              auditVO.setAuditAmount(0);
              }
              else if("CD84".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Approved");
            //  auditVO.setAuditAmount(preVO.getCeoAprvAmt());
              }
              else if("CD88".equalsIgnoreCase(lStrStatId))
              {
              auditVO.setAuditAction("Reject");
              auditVO.setAuditAmount(0);
              }
              else if("CD228".equalsIgnoreCase(lStrStatId))
              {
              auditVO.setAuditAction("Recommend Cancellation Pending");
              auditVO.setAuditAmount(0);
              }
              else if("CD135".equalsIgnoreCase(lStrStatId) )
              {
              auditVO.setAuditAction("Cancel");
              auditVO.setAuditAmount(0);
              }               
              else if("CD326".equalsIgnoreCase(lStrStatId))
              {  
              auditVO.setAuditAction("Recommend Termination Pending");
              auditVO.setAuditAmount(0);
              }
              else if("CD483".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Recommend Termination");
              auditVO.setAuditAmount(0);
              }
              else if("CD346".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Recommend Approval");
             // auditVO.setAuditAmount(preVO.getCmoAprvAmt());
              }
              else if("CD347".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Recommend Rejection");
              auditVO.setAuditAmount(0);
              }
              else if("CD256".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Recommend Cancellation Pending");
              auditVO.setAuditAmount(0);
              }
              else if("CD482".equalsIgnoreCase(lStrStatId) )
              {
              auditVO.setAuditAction("Recommend Cancellation");
              auditVO.setAuditAmount(0);
              }               
              else if("CD3".equalsIgnoreCase(lStrStatId)||"CD48".equalsIgnoreCase(lStrStatId))
              {
              auditVO.setAuditAction("Recommend Enhancement Pending");
              auditVO.setAuditAmount(0);
              }
              else if("CD428".equalsIgnoreCase(lStrStatId))
              {
              auditVO.setAuditAction("Recommend Enhancement Approval");
              //auditVO.setAuditAmount(preVO.getCommitteeEnhAmt());
              }
              else if("CD29".equalsIgnoreCase(lStrStatId))
              {
              auditVO.setAuditAction("Recommend Rejection");
              auditVO.setAuditAmount(0);
              }
              else if("CD225".equalsIgnoreCase(lStrStatId) || "CD429".equalsIgnoreCase(lStrStatId))
              {
              auditVO.setAuditAction("Recommend Approval");
             // auditVO.setAuditAmount(preVO.getCooApprvAMT());
              }
              else if("CD50".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Recommend Enhancement Pending");
              auditVO.setAuditAmount(0);
              }
              else if("CD430".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Enhancement Approved");
            //  auditVO.setAuditAmount(preVO.getCeoEnhApprvAmt());
              }
              else if("CD933".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Recommend Enhancement Pending");
              auditVO.setAuditAmount(0);
              }
              else if("CD935".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Enhancement Approved");
             // auditVO.setAuditAmount(preVO.getCmoEnhApprvAmt());
              }    
              else if("CD110".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Cancel Requested");
              auditVO.setAuditAmount(0);
              }   
              else if("CD66".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Updated Cancel Pending");
              auditVO.setAuditAmount(0);
              }
              else if("CD227".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Updated Cancel Pending");
              auditVO.setAuditAmount(0);
              }
              else if("CD85".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Surgery Update");
              auditVO.setAuditAmount(0);
              }
              else if("CD329".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Treatment Schedule Update");
              auditVO.setAuditAmount(0);
              }                
              else if("CD425".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Approved to Request Enhancement");
              auditVO.setAuditAmount(0);
              }    
               else if("CD86".equalsIgnoreCase(lStrStatId))
               { 
               auditVO.setAuditAction("Discharge Update ");
               auditVO.setAuditAmount(0);
               }
               else if("CD426".equalsIgnoreCase(lStrStatId))
               { 
               auditVO.setAuditAction("Requested Enhancement");
               // auditVO.setAuditAmount(preVO.getNwhEnhAmt());
               }
              else if("CD15".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Update Technical Committee Enhancement pending");
              auditVO.setAuditAmount(0);
              }
              else if("CD934".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Update CMO Enhancement pending");
              auditVO.setAuditAmount(0);
              }
              else if("CD49".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Update GM Medical Enhancement pending ");
              auditVO.setAuditAmount(0);
              }
              else if("CD64".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Update CEO Enhancement pending ");
              auditVO.setAuditAmount(0);
              }
              
              else if("CD80".equalsIgnoreCase(lStrStatId) ||"CD310".equalsIgnoreCase(lStrStatId)||
              "CD104".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Recommend Cancellation");
              auditVO.setAuditAmount(0);
              }
              else if("CD65".equalsIgnoreCase(lStrStatId) ||"CD67".equalsIgnoreCase(lStrStatId)||
              "CD69".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Recommend Cancellation Pending");
              auditVO.setAuditAmount(0);
              }
              else if("CD66".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Update Insurance Pending  For Cancellation");
              auditVO.setAuditAmount(0);
              }
              else if("CD70".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Update Committee Pending  For Cancellation");
              auditVO.setAuditAmount(0);
              }
              else if("CD68".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Update Panel Doctor Pending  For Cancellation");
              auditVO.setAuditAmount(0);
              }
              else if("CD267".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Update Insurance Pending  For Termination");
              auditVO.setAuditAmount(0);
              }
              else if("CD300".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Update Committee Pending  For Termination");
              auditVO.setAuditAmount(0);
              }
              else if("CD298".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Update Panel Doctor Pending  For Termination");
              auditVO.setAuditAmount(0);
              }
              else if("CD427".equalsIgnoreCase(lStrStatId)||"CD430".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Enhancement Approved");
            //  auditVO.setAuditAmount(preVO.getTrustEnhApprvAmt());
              }   
              else if("CD430".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Enhancement Approved");
            //  auditVO.setAuditAmount(preVO.getCeoEnhApprvAmt());
              }  
              else if("CD257".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Update CMO Pending  For Cancellation");
              auditVO.setAuditAmount(0);
              }  
              else if("CD327".equalsIgnoreCase(lStrStatId))
              { 
              auditVO.setAuditAction("Update CMO Pending  For Termination");
              auditVO.setAuditAmount(0);
              }  
              
              
              lstauditVO.add(auditVO);
              if("CD86".equalsIgnoreCase(preauthVO.getActId()))
              {
              break;
              }
              //preVO.setAuditAmount(lRs.getString("act_by"));
          }
 	}
 		 // end of code
 		
 		
 	}catch(Exception e)
 	{
 	e.printStackTrace();	
 	}
 	return lstauditVO;
 }*/
 public List<CommonDtlsVO> getComorBidVals()
 {
	 List<CommonDtlsVO> listComorBid = new ArrayList<CommonDtlsVO>();
	 StringBuffer query = new StringBuffer();
	 try
	 {
		 query.append(" select distinct  e.comorbidId as comorbidId , e.comorbidName as comorbidName , e.comorbidVal as comorbidVal  ");
		 query.append(" from EhfmComorbid e ");
		 listComorBid = genericDao.executeHQLQueryList(CommonDtlsVO.class, query.toString());
	 }catch(Exception e)
	 {
		e.printStackTrace(); 
	 }
	 return listComorBid;
 }
 public List<CommonDtlsVO> getComorbidsDisList(String pStrCaseId,String pStrProcIds)
 {
	 List<CommonDtlsVO> listComorBid = new ArrayList<CommonDtlsVO>();	
	 StringBuffer query = new StringBuffer();
	 List<String>  listProcs = new ArrayList<String>();
	 try
	 {
		 if(pStrProcIds == null || pStrProcIds.equalsIgnoreCase(""))
		 {
		 query.append(" select e.comorbidId as comorbidId , e.comorbidName as comorbidName , e.comorbidVal as comorbidVal  ");
		 query.append(" from EhfmComorbid e , EhfCaseTherapy ec ,EhfmProcComorbid p  ");
		 query.append(" where ec.caseId = '"+pStrCaseId+"' and ec.icdProcCode = p.icdProcCode and p.comorbidId =  e.comorbidId     "); 
		 query.append(" and p.activeYN ='Y' "); 
		 listComorBid = genericDao.executeHQLQueryList(CommonDtlsVO.class, query.toString());
		 }
		 else
		 {
			 StringTokenizer str = new StringTokenizer(pStrProcIds,",");
			 while(str.hasMoreTokens())
			 {
				 listProcs.add(str.nextToken().toString());	 
			 }
			 query = new StringBuffer();	
			 query.append(" select distinct e.comorbidId as comorbidId , e.comorbidName as comorbidName , e.comorbidVal as comorbidVal  ");
			 query.append(" from EhfmComorbid e , EhfmProcComorbid p where ");
			 
			 if(listProcs != null && listProcs.size() >0)
				 query.append(" p.icdProcCode in ( ");  
			 for(int k=0; k<listProcs.size() ;k++)
			 {
			   if(k!=0 && k!=listProcs.size())
			     {
				   query.append(" , ");  
			     }
			   query.append(" '"+listProcs.get(k)+"' ");	
			 }
			  if(listProcs != null && listProcs.size() >0)
				  query.append(" ) ");  
			  else
				  query.append("  p.icdProcCode in  ('') ");  	 
			  query.append(" and p.comorbidId =  e.comorbidId  ");
			  listComorBid = genericDao.executeHQLQueryList(CommonDtlsVO.class, query.toString());
			 }
			 
	 }catch(Exception e)
	 {
	e.printStackTrace();	 
	 }
	 return listComorBid;
 }

public List<LabelBean> getSymptomsDtls(String lStrCaseId) {
	List<LabelBean> symptomList = new ArrayList<LabelBean>();
	try{
		StringBuffer query = new StringBuffer();
		query.append("select ESF.mainSymptomName as ID , ESF.subSymptomName  as SUBID , ESF.symptomName as VALUE , " );
		query.append(" ESD.mainSymptomCode as UNITID, ESD.subSymptomCode as DSGNID, ESD.id.symptomCode as LEVELID ");
		query.append("	from EhfSymtematicExamDtls ESD,EhfmSystematicExamFnd ESF where ESF.id.symptomCode = ESD.id.symptomCode ");
		query.append(" and ESD.id.caseId='"+lStrCaseId+"'");
		symptomList = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
	}catch(Exception ex){
		ex.printStackTrace();
	}
	return symptomList;
}
public List<LabelBean> getInvestigations(String pStrpatientId)
{
	List<LabelBean> investigationsLst = new ArrayList<LabelBean>();
	try{
	StringBuffer query = new StringBuffer();
	query.append(" select ats.invName as VALUE ");
	query.append(" from EhfPatientTests apt, EhfmGenInvestigationsMst ats   ");
	query.append(" where apt.patientId ='"+pStrpatientId+"' and ats.invCode=apt.testId  ");
	}catch(Exception ex){
		ex.printStackTrace();
	}
	return investigationsLst;
}
private Long getAttachmentRejCount(String pStrCaseId)
{
	StringBuffer query = new StringBuffer();
	Long cnt =(long) 0;
	try{
	query.append("  select count(ea.actId) as COUNT  from EhfAudit ea  ");
	query.append("   where ea.id.caseId ='"+pStrCaseId+"'  and ea.actId in(  '"+config.getString("preauth_partail_save")+"') ");
	List<LabelBean> lstCnt = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
	if(lstCnt != null && lstCnt.size() >0)
	{
	if(lstCnt.get(0).getCOUNT() != null && !lstCnt.get(0).getCOUNT().equals(""))
	{
		cnt = 	 (Long) lstCnt.get(0).getCOUNT();
	}
	}
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	return cnt;
}
public List<LabelBean> getDocCount(String pStrCaseId,String pStrrejFlag) {
	List<LabelBean> docCount = new ArrayList<LabelBean>();
	String rejCnt = getAttachmentRejCount(pStrCaseId)+"";
	String args[] = new String[2];
	args[0] = pStrCaseId;
	args[1] = "%~" + rejCnt;
try{
	StringBuffer query = new StringBuffer();
	query.append("select count(a.docSeqId) as COUNT,  a.attachType as VALUE  from  EhfCaseDocAttch a  where a.caseId='"
			+ args[0] + "' and a.activeYN = 'Y' ");
	query.append("and  a.attachType in ('"+config.getString("Signedprf_docs")+"' ,'"+config.getString("medical_card_attch")+"' , ");
	query.append(" '"+config.getString("blood_transfusion_attch")+"' ,'"+config.getString("webEx_attch")+"' ,'"+config.getString("VideoRec_atch")+"')  ");
	if(pStrrejFlag != null && pStrrejFlag.equals("Y"))
	query.append("  and docCount like '"+args[1] + "'");
	query.append("  group by  a.attachType ");
	docCount = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
		}
	 catch (Exception e) {
		e.printStackTrace();
	} 

	return docCount;

}
public CommonDtlsVO getOtherDtls(String pStrCaseId,String pStrPatId) {
	String query="";
	CommonDtlsVO userData=new CommonDtlsVO();;
	try
	{
		/**
		 * Fetching habbits and allergies
		 */
		EhfPatientPersonalHistory ehfPatientPersonalHistory=genericDao.findById(EhfPatientPersonalHistory.class, String.class, pStrPatId);
		userData.setAllergy(ehfPatientPersonalHistory.getKnownAllergies());
		userData.setHabbits(ehfPatientPersonalHistory.getAddictions());
		
		/**
		 * Fetching investigations 
		 */
		query="select gim.invMainName as test, gim.invName as name, pt.attachTotalPath as route from EhfmGenInvestigationsMst gim,EhfPatientTests pt "; 
		query+=" where pt.testId=gim.invCode and pt.patientId='"+pStrPatId+"' ";
		List<PreauthVO> list1=genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
		userData.setInvestigations(list1);
		/**
		 * Fetching Diagnosis
		 */
		query="select dm.diagnosisName as diagnosisType,d.diagnosisType as refCardNo, mm.mainCatName as mainCatName, d.mainCatCode as occName, cm.catName as catName, d.categoryCode as relationName "+
				", sm.subCatName as subCatName,d.subCatCode as houseNo, ddm.diseaseName as disName, d.diseaseCode as street, ddam.disAnatomicalName as disAnatomicalSitename, d.diseaseAnatCode as hamlet "+ 
				" from EhfPatientHospDiagnosis d,EhfmDiagnosisMst dm,EhfmDiagMainCatMst mm,EhfmDiagCategoryMst cm,  "+ 
				" EhfmDiagSubCatMst sm,EhfmDiagDiseaseMst ddm,EhfmDiagDisAnatomicalMst ddam "+ 
				" where d.diagnosisType=dm.id.diagnosisCode and d.mainCatCode=mm.id.mainCatCode and "+ 
				" d.categoryCode=cm.id.catCode and d.subCatCode=sm.id.subCatCode and "+ 
				" d.diseaseCode=ddm.id.diseaseCode and d.diseaseAnatCode=ddam.id.disAnatomicalCode and d.patientId='"+pStrPatId+"'";
		List<PreauthVO> diagList=genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
		if(diagList!=null && !diagList.isEmpty())
		{
			PreauthVO dg=diagList.get(0);
			userData.setDiagnosisType(dg.getDiagnosisType());
			userData.setCARDNO(dg.getRefCardNo());
			userData.setMainCatName(dg.getMainCatName());
			userData.setCardType(dg.getOccName());
			userData.setCatName(dg.getCatName());
			userData.setCASESTAT(dg.getRelationName());
			userData.setSubCatName(dg.getSubCatName());
			userData.setHOSPNAME(dg.getHouseNo());
			userData.setDisName(dg.getDisName());
			userData.setHOSPADDR(dg.getStreet());
			userData.setDisAnatomicalSitename(dg.getDisAnatomicalSitename());
			userData.setADMTYPE(dg.getHamlet());
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return userData;
}
public List<DrugsVO> getIpDrugs(String pStrPatId,String flag)
{
	StringBuffer query = new StringBuffer();
	List<DrugsVO> drugs=null;
	try
	{
		query.append("select dm.drugName as drugName,dm.id.drugCode as drugCode,pd.route as route,pd.strength as strength,pd.dosage as dosage,pd.medicationPeriod as medicationPeriod,pd.issueFromDt as issueFromDt,pd.issueToDt as issueToDt ");
		query.append(" ,dm.drugTypeName as drugTypeName ,dm.drugSubTypeName as drugSubTypeName  ");
		if(flag != null && flag.equalsIgnoreCase("Preauth"))
		query.append(" ,  pd.units as noOfUnit , pd.totAmt as totAmt , pd.drugId as drugId ");
		query.append(" from EhfmDrugsMst dm, ");
		if(flag != null && flag.equalsIgnoreCase("Preauth"))
		query.append(" EhfCaseEnhPatientDrugs pd ");
		else
		query.append(" EhfPatientDrugs pd");	
		query.append("  where pd.drugCode=dm.id.drugCode and pd.patientId='"+pStrPatId+"'");
		if(flag != null && flag.equalsIgnoreCase("Preauth"))
		query.append(" and pd.activeYN ='Y' ");	
		drugs=genericDao.executeHQLQueryList(DrugsVO.class, query.toString());
		
		
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return drugs;
}
public List<DrugsVO> getMedicalDrugs(String caseID,String flag)
{
	StringBuffer query = new StringBuffer();
	List<DrugsVO> drugs=null;
	try
	{
		query.append(" select ee.caseId as CASEID, cast(ee.amount as string )  as AMOUNT, cast(ee.id.sno as string) as SEQID,  ee.attachName as ATTACHNAME, ee.attachPath as ATTACHPATH, to_char(ee.crtDt,'DD-MM-YYYY hh24:mi:ss') as CRTDT ");
		query.append(" from EhfPatientDrugsNabh ee  WHERE ee.activeYN = 'Y' and ee.caseId = '"+caseID+"' ");
		drugs=genericDao.executeHQLQueryList(DrugsVO.class, query.toString());
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return drugs;
}
public List<DrugsVO> getDrugs(String pStrPatId,String flag)
{
	StringBuffer query = new StringBuffer();
	List<DrugsVO> drugs=null;
	try
	{
		query.append(" select EDM.mainGrpCode as drugTypeCode,EDM.therMainGrpCode as drugSubTypeCode,EDM.pharSubGrpCode as pSubGrpCode,EDM.cheSubGrpCode as cSubGrpCode,EDM.chemicalCode as drugCode, ");
		query.append(" EDM.mainGrpName as drugTypeName,EDM.therMainGrpName as drugSubTypeName,EDM.pharSubGrpName as pSubGrpName,EDM.cheSubGrpName as cSubGrpName,EDM.chemicalName as drugName, ");
		query.append(" ER.routeTypeCode as routeType,ER.routeTypeName as routeTypeName,ER.routeCode as route,ER.routeName as routeName,ES.strengthUnitCode as strengthType,ES.strengthUnitName as strengthTypeName ,");
		query.append(" ES.strengthCode as strength,ES.strengthName as strengthName,EPD.dosage as dosage,EPD.medicationPeriod as medicationPeriod from EhfPatientDrugs EPD,EhfmOpDrugMst EDM,EhfmOpStrengthMst ES, ");
		query.append(" EhfmOpRouteMst ER where EPD.drugCode=EDM.chemicalCode and ER.routeCode=EPD.route and ES.strengthCode=EPD.strength ");
		query.append("  and EPD.patientId='"+pStrPatId+"' ");
		drugs=genericDao.executeHQLQueryList(DrugsVO.class, query.toString());
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return drugs;
}
public List<PreauthVO> getCasesWorkList(String caseId)
{
	List<PreauthVO> casesList= new ArrayList<PreauthVO>();
	try
	{
		casesList= preauthDtlsDao.getCasesWorkList(caseId);
	}
	catch(Exception e)
	{
		gLogger.error("Exception in method getCasesWorkList in PreauthServiceImpl"+ e.getMessage());
	}
	return casesList;
}
// To get all attachments of a case
public List<AttachmentVO> getAttachmentsForCase(String caseId)
{
	List<AttachmentVO> lstAttachments = new ArrayList<AttachmentVO>();
	try
	{
		StringBuffer query = new StringBuffer();
		query.append(" select actualName as fileName , savedName as savedName , attachType as attachType ");
		query.append(" from  EhfCaseDocAttch  where caseId = '"+caseId+"' and activeYN ='Y' ");
		lstAttachments = genericDao.executeHQLQueryList(AttachmentVO.class, query.toString());
		
	}catch(Exception e)
	{
		gLogger.error("Exception in method getAttachmentsForCase of PreauthServiceImpl--> "+e.getMessage());
	}
	return lstAttachments;
}
public String getEmpNameById(String id)
{
	return preauthDtlsDao.getEmpNameById(id);
}

public String getDoctorById(String id) {
	return preauthDtlsDao.getDoctorById(id);
}

public String getFollowUpStatus(String followUpId) {
	String caseStatus = null;
	try{
		EhfCaseFollowupClaim ehfCaseFollowUpClaim = genericDao
		.findById(EhfCaseFollowupClaim.class,
				String.class,
				followUpId);
	if(ehfCaseFollowUpClaim != null)
	{
		caseStatus = ehfCaseFollowUpClaim.getFollowUpStatus();
	}
	}catch(Exception e)
	{
	e.printStackTrace();	
	}
	return caseStatus;
}
public String cancelPreauth(PreauthVO preauthVO)
{
	String msg = null;
	try
	{
		preauthVO.setStatusToSave(config.getString("preauth_cancelled_status"));
		preauthVO.setUserRole(config.getString("preauth_medco_role"));
		updateAsritCase(preauthVO);	
		saveAstitAudit(preauthVO);
		msg = "Preauthorization has been cancelled by MEDCO";
	}
	catch(Exception e)
	{
	msg="failed";
	e.printStackTrace();	
	}
	return msg;
}

	private String getHospName(String hospId) {
	String hospitalName=null;
	try
	{
		hospitalName=preauthDtlsDao.getHospName(hospId);
	} 
	catch (Exception e)
	{
		gLogger.error("Exception Occurred in getHospName() in PreauthServiceImpl class."+e.getMessage());
	}
	return hospitalName;
}

	/*private PreauthVO getTotalAmounts(PreauthVO preauthVO)
	{
		StringBuffer query= new StringBuffer();
		Long nabhAmt= 0L;
		Long surgAmt=0L;
		Long hospStayAmount=0L;
		Long commonCatAmount=0L;
		Long tempCommonCatAmount=0L;
		Long noOfDays=0L;
		String tempCategory="";
		Long totAmt=0L;
		boolean addSurgAmt=false;
		float comorbidAmt = 0;
		try
		{
			*//**
			 * Get the NABH Amount
			 *//*
			if(preauthVO.getHospStayAmt()!=null && !"".equalsIgnoreCase(preauthVO.getHospStayAmt()))
				nabhAmt= Long.parseLong(preauthVO.getHospStayAmt());
			*//**
			 * Get the list of all active therapies
			 *//*
			
	    	query.append(" select distinct emt.hospstayAmt as hospStayAmt, emt.commonCatAmt as commonCatAmt, emt.icdAmt as icdAmt, ");
	    	query.append(" emt.noOfDays as noOfDays, emt.id.asriCode as test, emt.id.icdProcCode as testKnown, emt.procName as ipOpFlag ");
	    	query.append(" from EhfmTherapyProcMst emt, EhfCaseTherapy ect  ");
	    	query.append(" where ect.caseId = ? and ect.activeyn = ? and emt.id.asriCode = ect.asriCatCode  ");
	    	query.append(" and emt.id.icdProcCode = ect.icdProcCode and emt.icdCatCode = ect.icdCatCode order by  emt.id.asriCode ");
	    	
	    	String[] args=new String[2];
	    	args[0]=preauthVO.getCaseId();
	    	args[1]="Y";
	    	
	    	List<PreauthVO> surgList= genericDao.executeHQLQueryList(PreauthVO.class, query.toString(), args);
	    	if(surgList!=null && surgList.size()>0)
	    	{
	    		for(int i=0; i<surgList.size(); i++)
	    		{
	    			// Get the sum of all icd procedure amounts
	        		if(surgList.get(i).getIcdAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getIcdAmt()) && !addSurgAmt)
	    			{
	    				surgAmt= surgAmt+ Long.parseLong(surgList.get(i).getIcdAmt());
	    			}
	        		//End-Get the sum of all icd pocedure amounts
	        		
	    			if(i==0)
	    			{
	    				if(surgList.get(i).getNoOfDays()!=null)
	    					noOfDays= surgList.get(i).getNoOfDays().longValue();
	    				
	    				if(surgList.get(i).getHospStayAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getHospStayAmt()))
	    					hospStayAmount= Long.parseLong(surgList.get(i).getHospStayAmt());
	    				
	    				if(surgList.get(i).getCommonCatAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getCommonCatAmt()))
	    					commonCatAmount= Long.parseLong(surgList.get(i).getCommonCatAmt());
	    				
	    				//Calculate Common category Amount
	    				if(surgList.get(i).getTest()!=null && !"".equalsIgnoreCase(surgList.get(i).getTest()))
    						tempCategory= surgList.get(i).getTest();
    				}
	    			if(i>0)
	    			{
	    				// Get the maximum No of Days
	    				if(surgList.get(i).getNoOfDays()!=null)
	    				{
	    					if(noOfDays<surgList.get(i).getNoOfDays().longValue())
	    						noOfDays= surgList.get(i).getNoOfDays().longValue();
	    				}
	    				//End-Get the maximum No of Days
	    				
	    				// Get the maximum Hospital Stay Amount
	    				if(surgList.get(i).getHospStayAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getHospStayAmt()))
	    				{
	    					if(hospStayAmount< Long.parseLong(surgList.get(i).getHospStayAmt()))
	    						hospStayAmount= Long.parseLong(surgList.get(i).getHospStayAmt());
	    				}	
	    				// End-Get the maximum Hospital Stay Amount
	    				
	    				//Calculate Common category Amount
	    				if(tempCategory!=null && "".equalsIgnoreCase(tempCategory))
	    				{	
	    					if(surgList.get(i).getTest()!=null && !"".equalsIgnoreCase(surgList.get(i).getTest()))
	    						tempCategory= surgList.get(i).getTest();
	    					if(surgList.get(i).getCommonCatAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getCommonCatAmt()))
	    						tempCommonCatAmount= Long.parseLong(surgList.get(i).getCommonCatAmt());
	    				}
	    				else if(surgList.get(i).getTest()!=null && tempCategory.equalsIgnoreCase(surgList.get(i).getTest()))
	    				{
	    					if(surgList.get(i).getCommonCatAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getCommonCatAmt()))
	    					{
	    						if(tempCommonCatAmount< Long.parseLong(surgList.get(i).getCommonCatAmt()))
	    						{
	    							tempCommonCatAmount= Long.parseLong(surgList.get(i).getCommonCatAmt());
	    						}
	    					}
	    					
	    				}
	    				if(surgList.get(i).getTest()!=null && !tempCategory.equalsIgnoreCase(surgList.get(i).getTest()))
	    				{
	    					tempCategory= surgList.get(i).getTest();
	    					if(surgList.get(i).getCommonCatAmt()!=null && !"".equalsIgnoreCase(surgList.get(i).getCommonCatAmt()))
	    						tempCommonCatAmount= Long.parseLong(surgList.get(i).getCommonCatAmt());
	    					commonCatAmount= commonCatAmount+tempCommonCatAmount;
	    					addSurgAmt=true;
	    					i--;
	    				}
	    				else
	    					addSurgAmt=false;
	    				
	    			}
	    		}
	    	}
	    	
	    	totAmt= surgAmt+hospStayAmount+commonCatAmount+(noOfDays*nabhAmt);
	    	preauthVO.setTotPackgAmt(totAmt.toString());
	    	preauthVO.setPreauthPckgAmt(totAmt.toString());
	    	
	    	*//**
			 * Calculate the Comorbid Amount
			 *//*
	    	if(preauthVO.getComorBidVals()!=null && !"".equalsIgnoreCase(preauthVO.getComorBidVals()))
	    	{
	    		String comorbidList[]= preauthVO.getComorBidVals().split("~");
	    		List<String> comorBidValList= new ArrayList<String>();
	    		for(int len=0; len<comorbidList.length; len++)
	    		{
	    			comorBidValList.add(comorbidList[len]);
	    		}
	    		*//**
				 * Get the amount associated to each comorbid
				 *//*
	    		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
	    		criteriaList.add(new GenericDAOQueryCriteria("comorbidId",comorBidValList,GenericDAOQueryCriteria.CriteriaOperator.IN));
	    		List<EhfmComorbid> ehfmComorbid = genericDao.findAllByCriteria( EhfmComorbid.class, criteriaList) ;	
	    		
	    		if(ehfmComorbid!=null)
	    		{
	    			for(int i=0; i<ehfmComorbid.size(); i++)
	    			{
	    				if(ehfmComorbid.get(i).getComorbidVal()!=null && !"".equalsIgnoreCase(ehfmComorbid.get(i).getComorbidVal()))
	    					comorbidAmt=comorbidAmt+ Float.parseFloat(ehfmComorbid.get(i).getComorbidVal());
	    			}
	    		}
	    		
	    		preauthVO.setComorBidAmt(String.valueOf(Math.round(comorbidAmt)));
	    	}
	    	else
	    		preauthVO.setComorBidAmt("");
		}
		catch(Exception e)
		{
			gLogger.error("Exception in method getTotalAmounts() of PreauthServiceImpl.java--<"+e.getMessage());
			preauthVO.setCaseId(null);
			e.printStackTrace();
		}
	    
		return preauthVO;
	}*/
	
	public PatientVO retrieveCardDetails(PatientVO patientVo) {
		PatientVO patientVO=null;
		try
		{
			patientVO=preauthDtlsDao.retrieveCardDetails(patientVo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			gLogger.error("Exception Occurred in retrieveCardDetails() in PreauthServiceImpl class."+e.getMessage());
		}
		return patientVO;
	}
	public List<String> getDrugSubList(String drugTypeCode) {
		List<LabelBean> drugSubList=null;
		List<String> drugSubList1 = new ArrayList<String>();
		try
		{
			drugSubList=preauthDtlsDao.getDrugSubList(drugTypeCode);
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
//			gLogger.error("Exception Occurred in getDrugSubList() in PreauthServiceImpl class."+e.getMessage());
		}
		return drugSubList1;
	}
	
	public String getDrugDetails(String drugCode) {
		String drugDetails=null;
		try
		{
			drugDetails=preauthDtlsDao.getDrugDetails(drugCode);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			gLogger.error("Exception Occurred in getDrugDetails() in PreauthServiceImpl class."+e.getMessage());
		}
		return drugDetails;
	}
public CasesSearchVO getCaseCommonDtls(String caseId) {
	CasesSearchVO commonDtls =preauthDtlsDao.getCaseCommonDtls(caseId);
	return commonDtls;	
}
public PatientVO getTelephonicIntimationDtls(PatientVO patientVo) {
	PatientVO patientVO = new PatientVO();
	try
	{
	patientVO =preauthDtlsDao.getTelephonicIntimationDtls(patientVo);
	}
	catch(Exception e)
	{			
		gLogger.error("Exception Occurred in getTelephonicIntimationDtls() in PreauthServiceImpl class."+e.getMessage());
	}		
	return patientVO;
}

/*added for cochlear questionnaire*/

public List<cochlearCaseVO> getCochlearCaseDtls(String CaseId,String type)
{
	List<cochlearCaseVO> cochlearCaseVo=new ArrayList<cochlearCaseVO>();
	StringBuffer query = new StringBuffer();
	String caseId=CaseId;
	query.append("select a.case_id as CASEID,c.enroll_name as CHILDNAME,a.case_no as CASENO,a.claim_no as CLAIMNO,b.hosp_name as HOSPITALNAME, ");
	query.append("trunc(months_between(sysdate,c.enroll_dob)/12) ||'years  '||trunc(mod(months_between(sysdate,c.enroll_dob),12)) ||'months  '|| ");
	query.append("trunc(sysdate-add_months(c.enroll_dob,trunc(months_between(sysdate,c.enroll_dob))))||'days  ' as CHILDAGE ");
	
	EhfmCaseCochlearDtl ehfmCaseCochlearDtl=new EhfmCaseCochlearDtl();
	ehfmCaseCochlearDtl = genericDao.findById(EhfmCaseCochlearDtl.class, String.class, caseId);
	
	if(ehfmCaseCochlearDtl!=null)
	{	
	query.append(",e.AGEOFONSETPROB as ONSETAGE,e.AGEOFINTERVENTION as INTERVENTIONAGE ,e.CONVENHAID as CONVENTIONALAIDS,e.BENFOFCONVENHAID as BENEFITFROMCONVENTIONAL,e.DEVOFAUDCOMP as AUDITORY,e.INORAL_AURAL as ORALAURAL,e.CHILDSPEECHREAD as SPEAKREAD,e.AWARNESSMOTHERABTPROB as MOTHERAWARENESS, "); 
	query.append("e.AWAREOFIMPTECH as AUDIOVERBAL,e.MOTIVSPEECH as MOTIVATIONSPEECH,e.MOTIVERBAL as MOTIVATIONAUDIO,e.REALEXPCT as REALISTICEXPECT,e.MIDEARINF as MIDDLEEAR,e.CONGENABNORM as CONGENITAL,e.NORMALBONYCAPSULE as BONYCAPSULE, ");
	query.append("e.NORMALMILE as MILESTONES,e.NORMSPMECH as SPEECHMECHANISM,e.BEHAVPROB as ADHD,e.STABLEBEHAVR as STABLEQUIET,e.AUTISTEND as AUTISTIC, e.STUBBORNBEHAVR as STUBBORN,e.IMITATIVBEHAVR as IMITATIVE,e.FITORUNFIT as FITUNFIT,  ");
	query.append("to_char(e.ASSESSDATE,'dd-mm-yyyy') as ASSESSDATE,e.CONVENHAID_RMRKS as CONVENTIONALAIDS_REMARKS,e.BENFOFCONVENHAID_RMRKS as BENEFIT_REMARKS,e.DEVOFAUDCOMP_RMRKS as AUDITORY_REMARKS,e.INORAL_AURAL_RMRKS as ORALAURAL_REMARKS,e.CHILDSPEECHREAD_RMRKS as SPEAKREAD_REMARKS,  ");
	query.append("e.AWARNESSMOTHERABTPROB_RMRKS as MOTHERAWARENESS_REMARKS ,e.AWAREOFIMPTECH_RMRKS as AUDIOVERBAL_REMARKS,e.MOTIVSPEECH_RMRKS as MOTIVATIONSPEECH_REMARKS,e.MOTIVERBAL_RMRKS as MOTIVATIONAUDIO_REMARKS,  ");
	query.append("e.REALEXPCT_RMRKS as REALISTICEXPECT_REMARKS,e.MIDEARINF_RMRKS as MIDDLEEAR_REMARKS,e.CONGENABNORM_RMRKS as CONGENITAL_REMARKS,e.NORMALBONYCAPSULE_RMRKS as BONYCAPSULE_REMARKS,e.NORMALMILE_RMRKS as MILESTONES_REMARKS,e.NORMSPMECH_RMRKS as SPEECHMECHANISM_REMARKS,  ");
	query.append("e.BEHAVPROB_RMRKS as ADHD_REMARKS,e.STABLEBEHAVR_RMRKS as STABLEQUIET_REMARKS,e.AUTISTEND_RMRKS as AUTISTIC_REMARKS,e.STUBBORNBEHAVR_RMRKS as STUBBORN_REMARKS,e.IMITATIVBEHAVR_RMRKS as IMITATIVE_REMARKS,e.FITORUNFIT_RMRKS as FITUNFIT_REMARKS,e.father_name as FATHERNAME  ");
	
	}
	
	
	query.append("from ehf_case a,ehfm_hospitals b ,ehf_enrollment_family c,ehf_patient d ");
	
	if(ehfmCaseCochlearDtl!=null)
	{
		query.append(",ehfm_case_cochlear_dtls e  ");
	}
	
	query.append("where a.case_patient_no=d.patient_id and a.case_hosp_code=b.hosp_id and d.card_no=c.ehf_card_no  ");
	
	if(ehfmCaseCochlearDtl!=null)
	{
		query.append("and a.case_id=e.case_id ");
	}
	query.append("and a.case_id='"+caseId+"'");
	cochlearCaseVo= genericDao.executeSQLQueryList(cochlearCaseVO.class, query.toString());
	
	return cochlearCaseVo;
	
}
public String saveCochlearCaseDtls(cochlearCaseVO cochlearCaseVo)
{
	
	EhfmCaseCochlearDtl ehfmCaseCochlearDtl=new EhfmCaseCochlearDtl();
	EhfCase ehfcase=new EhfCase();
	String status=null;
    String caseId=cochlearCaseVo.getCASEID();
	ehfmCaseCochlearDtl = genericDao.findById(EhfmCaseCochlearDtl.class, String.class, caseId);
	
	if(ehfmCaseCochlearDtl==null)
	{
		ehfmCaseCochlearDtl=new EhfmCaseCochlearDtl();
	}
	
	
	ehfmCaseCochlearDtl.setCaseId(caseId);
	ehfmCaseCochlearDtl.setAgeofonsetprob(cochlearCaseVo.getONSETAGE());
	ehfmCaseCochlearDtl.setAgeofintervention(cochlearCaseVo.getINTERVENTIONAGE());
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	String dateInString = cochlearCaseVo.getASSESSDATE();
    Date assessDate=new Date();
	try {
 
		assessDate = formatter.parse(dateInString);
		System.out.println(assessDate);
		System.out.println(formatter.format(assessDate));
 
	} catch (Exception e) {
		e.printStackTrace();
	}
	ehfmCaseCochlearDtl.setAssessdate(assessDate);
	ehfmCaseCochlearDtl.setFatherName(cochlearCaseVo.getFATHERNAME());
	ehfmCaseCochlearDtl.setConvenhaid(cochlearCaseVo.getCONVENTIONALAIDS());
	ehfmCaseCochlearDtl.setBenfofconvenhaid(cochlearCaseVo.getBENEFITFROMCONVENTIONAL());
	ehfmCaseCochlearDtl.setDevofaudcomp(cochlearCaseVo.getAUDITORY());
	ehfmCaseCochlearDtl.setInoralAural(cochlearCaseVo.getORALAURAL());
	ehfmCaseCochlearDtl.setChildspeechread(cochlearCaseVo.getSPEAKREAD());
	ehfmCaseCochlearDtl.setAwarnessmotherabtprob(cochlearCaseVo.getMOTHERAWARENESS());
	ehfmCaseCochlearDtl.setAwareofimptech(cochlearCaseVo.getAUDIOVERBAL());
	ehfmCaseCochlearDtl.setMotivspeech(cochlearCaseVo.getMOTIVATIONSPEECH());
	ehfmCaseCochlearDtl.setMotiverbal(cochlearCaseVo.getMOTIVATIONAUDIO());
	ehfmCaseCochlearDtl.setRealexpct(cochlearCaseVo.getREALISTICEXPECT());
	ehfmCaseCochlearDtl.setMidearinf(cochlearCaseVo.getMIDDLEEAR());
	ehfmCaseCochlearDtl.setCongenabnorm(cochlearCaseVo.getCONGENITAL());
	ehfmCaseCochlearDtl.setNormalbonycapsule(cochlearCaseVo.getBONYCAPSULE());
	ehfmCaseCochlearDtl.setNormalmile(cochlearCaseVo.getMILESTONES());
	ehfmCaseCochlearDtl.setNormspmech(cochlearCaseVo.getSPEECHMECHANISM());
	ehfmCaseCochlearDtl.setBehavprob(cochlearCaseVo.getADHD());
	ehfmCaseCochlearDtl.setStablebehavr(cochlearCaseVo.getSTABLEQUIET());
	ehfmCaseCochlearDtl.setAutistend(cochlearCaseVo.getAUTISTIC());
	ehfmCaseCochlearDtl.setStubbornbehavr(cochlearCaseVo.getSTUBBORN());
	ehfmCaseCochlearDtl.setImitativbehavr(cochlearCaseVo.getIMITATIVE());
	ehfmCaseCochlearDtl.setFitorunfit(cochlearCaseVo.getFITUNFIT());
	
	ehfmCaseCochlearDtl.setConvenhaidRmrks(cochlearCaseVo.getCONVENTIONALAIDS_REMARKS());
	ehfmCaseCochlearDtl.setBenfofconvenhaidRmrks(cochlearCaseVo.getBENEFIT_REMARKS());
	ehfmCaseCochlearDtl.setDevofaudcompRmrks(cochlearCaseVo.getAUDITORY_REMARKS());
	ehfmCaseCochlearDtl.setInoralAuralRmrks(cochlearCaseVo.getORALAURAL_REMARKS());
	ehfmCaseCochlearDtl.setChildspeechreadRmrks(cochlearCaseVo.getSPEAKREAD_REMARKS());
	ehfmCaseCochlearDtl.setAwarnessmotherabtprobRmrks(cochlearCaseVo.getMOTHERAWARENESS_REMARKS());
	ehfmCaseCochlearDtl.setAwareofimptechRmrks(cochlearCaseVo.getAUDIOVERBAL_REMARKS());
	ehfmCaseCochlearDtl.setMotivspeechRmrks(cochlearCaseVo.getMOTIVATIONSPEECH_REMARKS());
	ehfmCaseCochlearDtl.setMotiverbalRmrks(cochlearCaseVo.getMOTIVATIONAUDIO_REMARKS());
	ehfmCaseCochlearDtl.setRealexpctRmrks(cochlearCaseVo.getREALISTICEXPECT_REMARKS());
	ehfmCaseCochlearDtl.setMidearinfRmrks(cochlearCaseVo.getMIDDLEEAR_REMARKS());
	ehfmCaseCochlearDtl.setCongenabnormRmrks(cochlearCaseVo.getCONGENITAL_REMARKS());
	ehfmCaseCochlearDtl.setNormalbonycapsuleRmrks(cochlearCaseVo.getBONYCAPSULE_REMARKS());
	ehfmCaseCochlearDtl.setNormalmileRmrks(cochlearCaseVo.getMILESTONES_REMARKS());
	ehfmCaseCochlearDtl.setNormspmechRmrks(cochlearCaseVo.getSPEECHMECHANISM_REMARKS());
	ehfmCaseCochlearDtl.setBehavprobRmrks(cochlearCaseVo.getADHD_REMARKS());
	ehfmCaseCochlearDtl.setStablebehavrRmrks(cochlearCaseVo.getSTABLEQUIET_REMARKS());
	ehfmCaseCochlearDtl.setAutistendRmrks(cochlearCaseVo.getAUTISTIC_REMARKS());
	ehfmCaseCochlearDtl.setStubbornbehavrRmrks(cochlearCaseVo.getSTUBBORN_REMARKS());
	ehfmCaseCochlearDtl.setImitativbehavrRmrks(cochlearCaseVo.getIMITATIVE_REMARKS());
	ehfmCaseCochlearDtl.setFitorunfitRmrks(cochlearCaseVo.getFITUNFIT_REMARKS());
	
	try
	{
		genericDao.save(ehfmCaseCochlearDtl);
		status="success";
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		status="fail";
	}
	if(("success").equals(status))
	{
	ehfcase = genericDao.findById(EhfCase.class, String.class, caseId);
	ehfcase.setCochlearQues("Y");
	try
	{
		genericDao.save(ehfcase);
		
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		
	}
	}
	
	
	return status;
}

private cochlearCaseVO viewcochlearQuestionnaire(String caseId)
{
	cochlearCaseVO cochlearCaseVo=new cochlearCaseVO();
	EhfmCaseCochlearDtl ehfmCaseCochlearDtl=new EhfmCaseCochlearDtl();
	ehfmCaseCochlearDtl = genericDao.findById(EhfmCaseCochlearDtl.class, String.class, caseId);
	
	cochlearCaseVo.setCASENO(ehfmCaseCochlearDtl.getCaseId());
	cochlearCaseVo.setFATHERNAME(ehfmCaseCochlearDtl.getFatherName());
	
	 Date date = new Date();
	 date=ehfmCaseCochlearDtl.getAssessdate();
	 DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
	 String strDate = dateFormat.format(date);
	 
     System.out.println("Date converted to String: " + strDate);
	cochlearCaseVo.setASSESSDATE(strDate);
	cochlearCaseVo.setONSETAGE(ehfmCaseCochlearDtl.getAgeofonsetprob());
	cochlearCaseVo.setINTERVENTIONAGE(ehfmCaseCochlearDtl.getAgeofintervention());
	cochlearCaseVo.setCONVENTIONALAIDS(ehfmCaseCochlearDtl.getConvenhaid());
	cochlearCaseVo.setBENEFITFROMCONVENTIONAL(ehfmCaseCochlearDtl.getBenfofconvenhaid());
	cochlearCaseVo.setAUDITORY(ehfmCaseCochlearDtl.getDevofaudcomp());
	cochlearCaseVo.setORALAURAL(ehfmCaseCochlearDtl.getInoralAural());
	cochlearCaseVo.setSPEAKREAD(ehfmCaseCochlearDtl.getChildspeechread());
	cochlearCaseVo.setMOTHERAWARENESS(ehfmCaseCochlearDtl.getAwarnessmotherabtprob());
	cochlearCaseVo.setAUDIOVERBAL(ehfmCaseCochlearDtl.getAwareofimptech());
	cochlearCaseVo.setMOTIVATIONSPEECH(ehfmCaseCochlearDtl.getMotivspeech());
	cochlearCaseVo.setMOTIVATIONAUDIO(ehfmCaseCochlearDtl.getMotiverbal());
	cochlearCaseVo.setREALISTICEXPECT(ehfmCaseCochlearDtl.getRealexpct());
	cochlearCaseVo.setMIDDLEEAR(ehfmCaseCochlearDtl.getMidearinf());
	cochlearCaseVo.setCONGENITAL(ehfmCaseCochlearDtl.getCongenabnorm());
	cochlearCaseVo.setBONYCAPSULE(ehfmCaseCochlearDtl.getNormalbonycapsule());
	cochlearCaseVo.setMILESTONES(ehfmCaseCochlearDtl.getNormalmile());
	cochlearCaseVo.setSPEECHMECHANISM(ehfmCaseCochlearDtl.getNormspmech());
	cochlearCaseVo.setADHD(ehfmCaseCochlearDtl.getBehavprob());
	cochlearCaseVo.setSTABLEQUIET(ehfmCaseCochlearDtl.getStablebehavr());
	cochlearCaseVo.setAUTISTIC(ehfmCaseCochlearDtl.getAutistend());
	cochlearCaseVo.setSTUBBORN(ehfmCaseCochlearDtl.getStubbornbehavr());
	cochlearCaseVo.setIMITATIVE(ehfmCaseCochlearDtl.getImitativbehavr());
	cochlearCaseVo.setFITUNFIT(ehfmCaseCochlearDtl.getFitorunfit());
	
	
	
	
	cochlearCaseVo.setCONVENTIONALAIDS_REMARKS(ehfmCaseCochlearDtl.getConvenhaidRmrks());
	cochlearCaseVo.setBENEFIT_REMARKS(ehfmCaseCochlearDtl.getBenfofconvenhaidRmrks());
	cochlearCaseVo.setAUDITORY_REMARKS(ehfmCaseCochlearDtl.getDevofaudcompRmrks());
	cochlearCaseVo.setORALAURAL_REMARKS(ehfmCaseCochlearDtl.getInoralAuralRmrks());
	cochlearCaseVo.setSPEAKREAD_REMARKS(ehfmCaseCochlearDtl.getChildspeechreadRmrks());
	cochlearCaseVo.setMOTHERAWARENESS_REMARKS(ehfmCaseCochlearDtl.getAwarnessmotherabtprobRmrks());
	cochlearCaseVo.setAUDIOVERBAL_REMARKS(ehfmCaseCochlearDtl.getAwareofimptechRmrks());
	cochlearCaseVo.setMOTIVATIONSPEECH_REMARKS(ehfmCaseCochlearDtl.getMotivspeechRmrks());
	cochlearCaseVo.setMOTIVATIONAUDIO_REMARKS(ehfmCaseCochlearDtl.getMotiverbalRmrks());
	cochlearCaseVo.setREALISTICEXPECT_REMARKS(ehfmCaseCochlearDtl.getRealexpctRmrks());
	cochlearCaseVo.setMIDDLEEAR_REMARKS(ehfmCaseCochlearDtl.getMidearinfRmrks());
	cochlearCaseVo.setCONGENITAL_REMARKS(ehfmCaseCochlearDtl.getCongenabnormRmrks());
	cochlearCaseVo.setBONYCAPSULE_REMARKS(ehfmCaseCochlearDtl.getNormalbonycapsuleRmrks());
	cochlearCaseVo.setMILESTONES_REMARKS(ehfmCaseCochlearDtl.getNormalmileRmrks());
	cochlearCaseVo.setSPEECHMECHANISM_REMARKS(ehfmCaseCochlearDtl.getNormspmechRmrks());
	cochlearCaseVo.setADHD_REMARKS(ehfmCaseCochlearDtl.getBehavprobRmrks());
	cochlearCaseVo.setSTABLEQUIET_REMARKS(ehfmCaseCochlearDtl.getStablebehavrRmrks());
	cochlearCaseVo.setAUTISTIC_REMARKS(ehfmCaseCochlearDtl.getAutistendRmrks());
	cochlearCaseVo.setSTUBBORN_REMARKS(ehfmCaseCochlearDtl.getStubbornbehavrRmrks());
	cochlearCaseVo.setIMITATIVE_REMARKS(ehfmCaseCochlearDtl.getImitativbehavrRmrks());
	cochlearCaseVo.setFITUNFIT_REMARKS(ehfmCaseCochlearDtl.getFitorunfitRmrks());
	
	
	
	return cochlearCaseVo;
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

public PreauthVO getCaseDetails(String caseId)
{
	PreauthVO preauthVO=new PreauthVO();
	
	EhfCase ehfCase=new EhfCase();
	EhfCasePreauthAmounts ehfCasePreauthAmounts=new EhfCasePreauthAmounts(); 
	EhfCaseClaim  ehfCaseClaim=new EhfCaseClaim();
	if(caseId!=null && !("").equalsIgnoreCase(caseId))
	{
	ehfCase=genericDao.findById(EhfCase.class, String.class, caseId);
	ehfCasePreauthAmounts=genericDao.findById(EhfCasePreauthAmounts.class, String.class, caseId);
	ehfCaseClaim=genericDao.findById(EhfCaseClaim.class, String.class, caseId);
	}
	if(ehfCase!=null && ehfCasePreauthAmounts!=null  )
	{
		preauthVO.setCaseId(ehfCase.getCaseId());  
    	preauthVO.setCaseStatusId(ehfCase.getCaseStatus());
    	preauthVO.setCochlearYN(ehfCase.getCochlearYN());
    	preauthVO.setOrganTransYN(ehfCase.getOrganTransYN());
    	preauthVO.setScheme(ehfCase.getSchemeId());
    	if(ehfCasePreauthAmounts.getEoEnhAppvAmt()!=null)
    	preauthVO.setEnhApprAmt(ehfCasePreauthAmounts.getEoEnhAppvAmt().toString());
    	
    	if(ehfCasePreauthAmounts.getEoAppvPackgAmt()!=null)
    	preauthVO.setApprvdPckAmt(ehfCasePreauthAmounts.getEoAppvPackgAmt().toString());
    	
    	if(ehfCasePreauthAmounts.getEoAppvComorbidAmt()!=null)
    	preauthVO.setComorBidAppvAmt(ehfCasePreauthAmounts.getEoAppvComorbidAmt().toString());
    	
    	if(ehfCasePreauthAmounts.getPtdAppvTotalPackgAmt()!=null)
    	preauthVO.setPtdTotalApprvAmt(ehfCasePreauthAmounts.getPtdAppvTotalPackgAmt().toString());
    	
    	if(ehfCaseClaim.getChNabhAmt()!=null)
    	preauthVO.setNabhAmt(ehfCaseClaim.getChNabhAmt().toString());
    	if(ehfCase.getEnhFlag()!=null)
    	preauthVO.setEnhFlag(ehfCase.getEnhFlag());
	}
	else
	{
			System.out.println("details missing for case ****** in getCaseDetails(): "+caseId);
	}
	
	return preauthVO;
}


public String updateSentBackClaims(ClaimsFlowVO claimFlowVO)
{
	
	
	String sendBackFlag=claimFlowVO.getSendBackFlag();
	String remarks=claimFlowVO.getRemarks();
	String actionDone=claimFlowVO.getActionDone();
	String claimType=claimFlowVO.getModuleType();
	String nextStatus=null;
	Date date=new Date();
	String msg=null;
	
	if(!("followUp").equalsIgnoreCase(claimType))
	{
		String caseId=claimFlowVO.getCaseId();
		EhfCase ehfCase=new EhfCase();
		ehfCase=genericDao.findById(EhfCase.class,String.class,caseId);
		if(ehfCase!=null)
		{
		if(("Y").equalsIgnoreCase(ehfCase.getEnhFlag()))
				nextStatus=config.getString("EHF.preauth.CEOEnhancementPendingUpdated");
		if(("Y").equalsIgnoreCase(ehfCase.getCochlearYN()))
		        nextStatus=config.getString("EHF.preauth.CEOCochlearPendingUpdated");
		if(("Y").equalsIgnoreCase(ehfCase.getOrganTransYN()))
	        nextStatus=config.getString("EHF.preauth.CEOOrganTransPendingUpdated");
		else
			    nextStatus=config.getString("EHF.preauth.CEOPendingUpdated");
		
		}
	
		Long lActOrder = 0L;
		StringBuffer lQueryBuffer = new StringBuffer();
		String args[] = new String[1];
		lQueryBuffer
				.append(" select max(au.id.actOrder) as COUNT from EhfAudit au where au.id.caseId=? ");
		args[0] = claimFlowVO.getCaseId();
		List<ClaimsFlowVO> actOrderList = genericDao.executeHQLQueryList(
				ClaimsFlowVO.class, lQueryBuffer.toString(), args);
		if (actOrderList != null && !actOrderList.isEmpty()
				&& actOrderList.get(0).getCOUNT() != null) {
			if (actOrderList.get(0).getCOUNT().longValue() >= 0)
				lActOrder = actOrderList.get(0).getCOUNT().longValue() + 1;
		}
		try
		{
		
			ehfCase.setCaseStatus(nextStatus);
			ehfCase.setLstUpdDt(date);
			ehfCase.setLstUpdUsr(claimFlowVO.getUserId());
			ehfCase.setPendingWith("");
		

		
		// insert into asrit_audit
		EhfAudit asritAudit = new EhfAudit();
		EhfAuditId asritAuditPK = new EhfAuditId(lActOrder,
				claimFlowVO.getCaseId());
		asritAudit.setId(asritAuditPK);
		asritAudit.setActId(nextStatus);
		asritAudit.setActBy(claimFlowVO.getUserId());
		asritAudit.setCrtUsr(claimFlowVO.getUserId());
		asritAudit.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
		asritAudit.setLangId(ClaimsConstants.LANG_ID);
		asritAudit.setRemarks(remarks);
		//asritAudit.setApprvAmt(approveAmt);
		asritAudit.setUserRole(claimFlowVO.getRoleId());

		genericDao.save(asritAudit);
	    
	    genericDao.save(ehfCase);
	     
	    msg="Case Updated and Sent to CEO Successfully";
		}
		catch(Exception e)
		{
			msg="Failed to Save Remarks.Please try Again";
			e.printStackTrace();
//			gLogger.error("Error occured in updateSentBackClaims() method in preauthServiceImpl"+e.getMessage());
		}
	    
	}
	
	else
	{
		String followUpId=claimFlowVO.getCaseId();
		EhfCaseFollowupClaim ehfCaseFollowupClaim=new EhfCaseFollowupClaim();
		ehfCaseFollowupClaim=genericDao.findById(EhfCaseFollowupClaim.class,String.class,followUpId);
		if(ehfCaseFollowupClaim!=null)
		{
		
			    nextStatus=config.getString("EHF.followUp.CEOPendingUpdated");
		
		}
	
		Long lActOrder = 0L;
		StringBuffer lQueryBuffer = new StringBuffer();
		String args[] = new String[1];
		lQueryBuffer
				.append(" select max(au.id.actOrder) as COUNT from EhfFollowUpAudit au where au.id.caseFollowupId=? ");
		args[0] = followUpId;
		List<ClaimsFlowVO> actOrderList = genericDao.executeHQLQueryList(
				ClaimsFlowVO.class, lQueryBuffer.toString(), args);
		if (actOrderList != null && !actOrderList.isEmpty()
				&& actOrderList.get(0).getCOUNT() != null) {
			if (actOrderList.get(0).getCOUNT().longValue() >= 0)
				lActOrder = actOrderList.get(0).getCOUNT().longValue() + 1;
		}
		try
		{
		
			ehfCaseFollowupClaim.setFollowUpStatus(nextStatus);
			ehfCaseFollowupClaim.setLstUpdDt(date);
			ehfCaseFollowupClaim.setLstUpdUsr(claimFlowVO.getUserId());
			ehfCaseFollowupClaim.setPendingWith("");
		

		
		// insert into asrit_audit
			EhfFollowUpAudit asritAudit = new EhfFollowUpAudit();
			EhfFollowUpAuditId asritAuditPK = new EhfFollowUpAuditId(lActOrder,
				claimFlowVO.getCaseId(),"CLAIM");
		asritAudit.setId(asritAuditPK);
		asritAudit.setActId(nextStatus);
		asritAudit.setActBy(claimFlowVO.getUserId());
		asritAudit.setCrtUsr(claimFlowVO.getUserId());
		asritAudit.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
		asritAudit.setLangId(ClaimsConstants.LANG_ID);
		asritAudit.setRemarks(remarks);
		//asritAudit.setApprvAmt(approveAmt);
		asritAudit.setUserRole(claimFlowVO.getRoleId());

		genericDao.save(asritAudit);
	    
	    genericDao.save(ehfCaseFollowupClaim);
	     
	    msg="Case Updated and Sent to CEO Successfully";
		}
		catch(Exception e)
		{
			msg="Failed to Save Remarks.Please try Again";
			e.printStackTrace();
//			gLogger.error("Error occured in updateSentBackClaims() method in preauthServiceImpl"+e.getMessage());
		}
		
	}

	

	
	return msg;
	
}

public boolean verifyClaimPending(ClaimsFlowVO claimFlowVO)
{
boolean isPending=false;
String[] claimStatus;
String caseId=claimFlowVO.getCaseId();
String user=claimFlowVO.getUserId();
StringBuffer query = new StringBuffer();
List<LabelBean> verifyList=new ArrayList<LabelBean>();
try
{
query.append("select case_id as ID  from ehf_case ec where ec.case_id='"+caseId+"' and ec.case_status in (");
claimStatus =  ClaimsConstants.preauthCEOSentBackStatuses;
		for (int i = 0; i < claimStatus.length; i++) {
			query.append(" '" + claimStatus[i] + "' ");
			if (i != claimStatus.length - 1)
				query.append(",");
		}
		query.append(" ) ");
		query.append( "and ec.pending_with='"+user+"' ");


verifyList=genericDao.executeSQLQueryList(LabelBean.class,query.toString());

if(verifyList.size()>0)
	isPending=true;
else
	isPending=false;
}
catch(Exception e)
{
	e.printStackTrace();
}


return isPending;
}


public boolean verifyFollowUpClaimPending(String followupId)

{
boolean isPending=false;
String[] claimStatus;

StringBuffer query = new StringBuffer();
List<LabelBean> verifyList=new ArrayList<LabelBean>();
try
{
query.append("select case_followup_id as ID  from ehf_case_followup_claim ec where ec.case_followup_id='"+followupId+"' and ec.followup_status in (");
claimStatus =  ClaimsConstants.followupCEOSentBackStatuses;
		for (int i = 0; i < claimStatus.length; i++) {
			query.append(" '" + claimStatus[i] + "' ");
			if (i != claimStatus.length - 1)
				query.append(",");
		}
		query.append(" ) ");


verifyList=genericDao.executeSQLQueryList(LabelBean.class,query.toString());

if(verifyList.size()>0)
	isPending=true;
else
	isPending=false;
}
catch(Exception e)
{
	e.printStackTrace();
}


return isPending;
}
@Override
public String getNABHhosptls(String intiid, String caseNo) {
	String nabhflag="N";
	List<LabelBean> hosplist = new ArrayList<LabelBean>();
	try{
		StringBuffer query = new StringBuffer();
		
		query.append(" select em.id.hospId as  HOSPID from  EhfCase ec, EhfmEDCHospActionDtls em ");
		query.append("  WHERE ec.caseHospCode='"+intiid+"' and em.nabhFlag = 'Y' and ec.schemeId = 'CD202' ");
		query.append(" and ec.caseNo = '"+caseNo+"' and ec.schemeId = em.id.scheme and ec.caseHospCode = em.id.hospId ");
		hosplist = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
		if(hosplist !=null && hosplist.size()>0)
		{
			nabhflag = "Y";
		}
	}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
	return nabhflag;
}
	public String getPatientScheme(String lStrCaseId)
		{
			
			String patientScheme=null;
			patientScheme=preauthDtlsDao.getPatientScheme( lStrCaseId);
			return patientScheme;
			
		}
		public String getCaseScheme(String lStrCaseId)
{
	String patientScheme=null;
	EhfCase ehfCase=new EhfCase();
	try
	{
	if(lStrCaseId!=null)
	{
	ehfCase=genericDao.findById(EhfCase.class,String.class,lStrCaseId);
	}
	if(ehfCase!=null)
	{
		patientScheme=ehfCase.getSchemeId();
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return patientScheme;
	
	
}
		
		/***
		 * @author Kalyan
		 * @param Patient ID
		 * @return Follow Up Flag a String
		 * @function This Method is Used to check Dental Follow UP Eligibilty for a Specific Patient. 
		 */
		public PreauthVO checkDenFlp(String caseID)
			{
				String procCodeCond=null;
				PreauthVO returnVO = new PreauthVO();
				try
					{
						String[] arr=new String[2];
						if(config.getString("dentalFlpConds")!=null)
							arr=config.getString("dentalFlpConds").split("~");
						
						for(String str:arr)
							{
								if(procCodeCond==null)
									procCodeCond="'"+str+"'";
								else
									procCodeCond+=",'"+str+"'";
							}
						
						StringBuffer query=new StringBuffer();					
						query.append( " select " );
						query.append( "   to_char(nvl(sum(case when c.icdProcCode = '"+arr[0]+"' then 1 else 0 end),0)) as surgCountStr , " );
						query.append( "   to_char(nvl(sum(case when c.icdProcCode = '"+arr[1]+"' then 1 else 0 end),0)) as flpSurgCount  " );
						query.append( "   from EhfPatient a , EhfCase b , EhfCaseTherapy c " );
						query.append( "   , EhfPatient d , EhfCase e " );
						query.append( "   where e.caseId = '"+caseID+"' " );
						query.append( "   and b.caseId not in ('"+caseID+"') " );
						query.append( "   and a.patientId = e.casePatientNo  ");
						query.append( "   and a.cardNo = d.cardNo and a.schemeId = d.schemeId  " );
						query.append( "   and b.casePatientNo = d.patientId and b.caseId = c.caseId  " );
						query.append( "   and b.caseStatus not in ("+config.getString("Preauth_Cancelled_Status").replace('~',',')+") ");
						query.append( "   and c.activeyn = 'Y' and c.icdProcCode in ("+procCodeCond+") " );
						query.append( "   and case when c.icdProcCode = '"+arr[0]+"' and b.csDisDt < (sysdate-11) " );
						query.append( " 		   then 1	" );
						query.append( "            when c.icdProcCode = '"+arr[1]+"' " );
						query.append( " 		   then 1	" );			
						query.append( " 		   else 0 ");
						query.append( "       end = 1 ");
						
						
						Map <String , Object > resMap = new HashMap <String ,Object> ();
						String classPath="com.ahct.preauth.vo.PreauthVO";
						resMap=preauthDtlsDao.executeNormalQuery(classPath,query.toString());
						
						List<PreauthVO> localVOLst=returnVOLst(resMap,classPath);
						if( localVOLst!=null && localVOLst.size()>0 && localVOLst.get(0)!=null )
							{
								/**Only Initial Proc utilized and Follow Up Proc need to be utilized then List VO will be Sent
								   1.Conditions Surgery Count Should be greater than 0.
								   2.Follow Up Surgery Count should be 0 as this should be performed only once*/
								if(localVOLst.get(0).getSurgCountStr() !=null && !"".equalsIgnoreCase(localVOLst.get(0).getSurgCountStr())
												&& (Integer.parseInt(localVOLst.get(0).getSurgCountStr()) > 0)
										&& localVOLst.get(0).getFlpSurgCount() !=null && !"".equalsIgnoreCase(localVOLst.get(0).getFlpSurgCount())
												&& (Integer.parseInt(localVOLst.get(0).getFlpSurgCount()) == 0))
									{
										//Means Specified Follow Up Proc should not be removed From The List
										returnVO.setActId("N");
									}
								/**If both Initial Proc and Follow Up Proc are utilized or
								   Both Initial Proc and Follow Up Proc are not utilized 
								   and for all the other conditions  
								   */
								else
									{
										//Means Remove the Specified Follow Up Proc From The List
										returnVO.setProcCode(arr[1]);
										returnVO.setActId("Y");
									}
								
							}
					}
				catch(Exception e)
					{
						e.printStackTrace();
//						gLogger.error("Exception Occurred in checkSpecialDenatlCond() in PreauthServiceImpl class."+e.getMessage());
						return returnVO;
					}
				
				return returnVO;
			}
		
		/**
		 * @author Kalyan
		 * @param Map That contains List and ClassPath
		 * @return Final List
		 * @method Returns preauthVO List from Map 
		 */
		@SuppressWarnings("unchecked")
		private List<PreauthVO> returnVOLst(Map<String,Object> resMap,String classPath)
			{
				List<PreauthVO> returnLst=new ArrayList<PreauthVO>();
				try
					{
						if(resMap!=null)
							{	
								if(resMap.get(classPath)!=null)
									{
										List<PreauthVO> preVOLst=(List<PreauthVO>)resMap.get(classPath);;
										if(preVOLst!=null)
											{
												if(preVOLst.size()>0)
													{
														returnLst=preVOLst;
													}
											}
									}
							}
					}	
				catch(Exception e)
					{
						e.printStackTrace();
//						gLogger.error("Exception Occurred in returnVOLst() in PreauthServiceImpl class."+e.getMessage());
						return returnLst;
					}
				return returnLst;
			}
		/***
		 * @author Kalyan
		 * @param Case ID and Hosp ID
		 * @return Status of Preauth-Approval or initiation
		 * @function This Method is Used to check TG Patient Registration in TG Hospital  
		 */
		public boolean checkTGPatReg(String hospId,String caseId)
			{
				boolean retMsg=false;
				try
					{
						StringBuffer query=new StringBuffer();
						query.append(" select schemeId as scheme from EhfCase a where caseId ='"+caseId+"' ");
						
						Map <String , Object > resMap = new HashMap <String ,Object> ();
						String classPath="com.ahct.preauth.vo.PreauthVO";
						resMap=preauthDtlsDao.executeNormalQuery(classPath,query.toString());
						
						List<PreauthVO> localVOLst=returnVOLst(resMap,classPath);
						if( localVOLst!=null && localVOLst.size()>0 && localVOLst.get(0)!=null && localVOLst.get(0).getScheme()!=null
								&& localVOLst.get(0).getScheme().equalsIgnoreCase(config.getString("Scheme.TG.State")))
							{
								query.setLength(0);
								query.append(" select cast(a.hospType as string) as hospType from EhfmHospitals a  ");
								query.append(" ,EhfmEDCHospActionDtls b ");
								query.append(" where a.id.hospId ='"+hospId+"' and b.id.hospId = a.id.hospId ");
								query.append(" and b.id.scheme = '"+localVOLst.get(0).getScheme()+"' ");
								query.append(" and a.stateCode='"+config.getString("telangana_hdr_id")+"'  ");
								
								resMap = new HashMap <String ,Object> ();
								classPath="com.ahct.preauth.vo.PreauthVO";
								resMap=preauthDtlsDao.executeNormalQuery(classPath,query.toString());
								localVOLst=returnVOLst(resMap,classPath);
								if( localVOLst!=null && localVOLst.size()>0 && localVOLst.get(0)!=null 
										&& localVOLst.get(0).getHospType()!=null
										&& localVOLst.get(0).getHospType().equalsIgnoreCase("G"))
									{
										retMsg=true;
									}
							}
					}	
				catch(Exception e)
					{
						e.printStackTrace();
//						gLogger.error("Exception Occurred in checkTGPatReg() in PreauthServiceImpl class."+e.getMessage());
						return retMsg;
					}
				return retMsg;
			}
		/**
		 * Added by ramalakshmi for getting hub hospital
		 */
		private String checkHubHosp(String caseHospCode) 
		{
			String hubFlg = "";
			List<LabelBean> hospDtls = null;
			StringBuffer query=new StringBuffer();
			try{
				query.append("select hub_spoke as ID from ehfm_hospitals where hosp_id = '"+caseHospCode+"' ");
				hospDtls = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
				if(hospDtls!=null && hospDtls.size()>0)
				{
					hubFlg = hospDtls.get(0).getID();
				}
					
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return hubFlg;
		}
		@Override
		public int getCount(String caseId,String roleId) 
		{
			int count = 0;
			
			try
			{
			StringBuffer query = new StringBuffer();	
			String args[] = new String[1];
			
			if(roleId!=null && roleId.equalsIgnoreCase(config.getString("preauth_ptd_role")))
				args[0] = config.getString("preauth_ptd_pending");
			
			query.append("select count(*) as COUNT from ehf_audit where act_id=? and case_id='"+caseId+"' ");

			List<LabelBean> orderList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(),args);
		
			
			if (!orderList.isEmpty())
				if (orderList.get(0).getCOUNT() != null)
					count = orderList.get(0).getCOUNT().intValue();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return count;
		}
		@Override
		public List<CasesSearchVO> getPreauthDtls(String cardNo) 
		{
			StringBuffer query= new StringBuffer();
			query.append("select card_no as CARDNO, case when count(rn)>1 then max(case when rn = 1 then cs_preauth_dt end) ");
			query.append(" - max(case when rn = 2 then cs_preauth_dt end) else 0 end as DIFF ");
			query.append(" from (select epe.patient_id,ece.case_id,card_no,cs_preauth_dt,ROW_NUMBER() OVER(PARTITION BY card_no ORDER BY cs_preauth_dt desc) ");
			query.append(" rn from ehf_patient epe, ehf_case ece where epe.patient_id = ece.case_patient_no and epe.register_y_n = 'Y' ");
			query.append(" and epe.patient_ipop = 'IP' and epe.scheme_id = 'CD202' and epe.card_no='"+cardNo+"') ");
			query.append(" where rn in (1, 2) group by card_no");
			
			List<CasesSearchVO> preauthList=genericDao.executeSQLQueryList(CasesSearchVO.class, query.toString());
			return preauthList;
		}
		
		public EhfPatient getPatDtls(String patientId) {
			// TODO Auto-generated method stub
			EhfPatient ehfPatient = genericDao.findById(EhfPatient.class, String.class, patientId);
			return ehfPatient;
		}
		@Override
		public String saveEnhDtls(PreauthVO preauthVO)
		{
			String msg = null;
			Date date = new Date();
			String templateId="";
		try{
			StringBuffer sb = new StringBuffer();
			String[] args = new String[1];
			args[0] = preauthVO.getCaseId();
			String asriCatCode = "";
			sb.append("select icd_cat_code ID from ehf_case_therapy where case_id = ? ");
			List<LabelBean> tempList = genericDao.executeSQLQueryList(LabelBean.class, sb.toString(), args);
			if(tempList.size() > 0)
				asriCatCode = tempList.get(0).getID();
			
			EhfCase ehfCaseObj = genericDao.findById(EhfCase.class, String.class, preauthVO.getCaseId());
			if(ehfCaseObj != null)
			{
				/*if( ( ( (asriCatCode != null && !"".equalsIgnoreCase(asriCatCode)) &&  asriCatCode.contains("MMC")) && ( preauthVO.getEnhAmt() != null && Long.parseLong(preauthVO.getEnhAmt()) >= 100000))   || 
				   (( (asriCatCode != null && !"".equalsIgnoreCase(asriCatCode)) &&  asriCatCode.contains("MMNC")) && (preauthVO.getEnhAmt() != null && Long.parseLong(preauthVO.getEnhAmt()) >= 50000)) )*/
				if(preauthVO.getHospitalId()!=null && preauthVO.getPatientID()!=null && !"".equalsIgnoreCase(preauthVO.getHospitalId()) && !"".equalsIgnoreCase(preauthVO.getPatientID()) && "IPM".equalsIgnoreCase(preauthVO.getPatientID()) && "EHS34".equalsIgnoreCase(preauthVO.getHospitalId()))
					
				{
						
					ehfCaseObj.setExceedFlg("Y");
					if(preauthVO.getEnhAmounts() != null && !"".equalsIgnoreCase(preauthVO.getEnhAmounts()))
					{		
						if( (Long.parseLong(preauthVO.getEnhAmounts()) < 200000 &&  Long.parseLong(preauthVO.getEnhAmt()) >= 200000) ||
								(Long.parseLong(preauthVO.getEnhAmounts()) < 500000 &&  Long.parseLong(preauthVO.getEnhAmt()) >= 500000) || 	
								(Long.parseLong(preauthVO.getEnhAmounts()) < 700000 &&  Long.parseLong(preauthVO.getEnhAmt()) >= 700000) || 
								(Long.parseLong(preauthVO.getEnhAmounts()) < 1000000 &&  Long.parseLong(preauthVO.getEnhAmt()) >= 1000000) ||
								(Long.parseLong(preauthVO.getEnhAmt()) >= 1000000) )
								
						{
							ehfCaseObj.setCaseStatus(PreauthConstants.HOLD_STATUS);
							Long actOrder = (long) 0;
							List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
							criteriaList.add(new GenericDAOQueryCriteria("id.caseId",preauthVO.getCaseId(), GenericDAOQueryCriteria.CriteriaOperator.EQUALS ));
							actOrder= genericDao.getMaxByPropertyCriteria(EhfAudit.class, "id.actOrder",criteriaList);
							
							if(actOrder == null )
								actOrder=(long) 0;
							EhfAudit asritAudit=new EhfAudit();
							EhfAuditId asritAuditPK=new EhfAuditId(actOrder+1,preauthVO.getCaseId());
							asritAudit.setId(asritAuditPK);
							asritAudit.setActId(PreauthConstants.HOLD_STATUS);
							asritAudit.setActBy(preauthVO.getCruUsr());
							asritAudit.setRemarks("As per Note order Dated 30/01/2017,this Case has been put on hold due to exceeding the ceiling amount");
							asritAudit.setCrtUsr(preauthVO.getCruUsr());
							if(!"".equalsIgnoreCase(preauthVO.getEnhAmt()) && preauthVO.getEnhAmt() != null)
							asritAudit.setApprvAmt(Double.parseDouble(preauthVO.getEnhAmt()));
							asritAudit.setCrtDt(new java.sql.Timestamp(date.getTime()));
							asritAudit.setUserRole(config.getString("preauth_medco_role"));
							asritAudit.setLangId("en_US");
							genericDao.save(asritAudit);
						}
					
						
					}
						/*if("true".equalsIgnoreCase(config.getString("SmsRequired")))
					     {
   							//SendSMS sendSms =new SendSMS();
							SMSServices SMSServicesobj = new SMSServices();
   							String contactNo = config.getString("EXCEED_ALERT_SMS");
   							EhfmHospitals hospDtls = genericDao.findById(EhfmHospitals.class, String.class, ehfCaseObj.getCaseHospCode());
   							if(hospDtls != null)
   							{
   								String[] cntctNo =  contactNo.split("~"); 
   								for(int i=0; i<cntctNo.length;i++)
   								{	
									 msg= "Alert! Case with Registration Number "+preauthVO.getCaseId().trim()+" is registered in "+hospDtls.getHospName().trim()+" as Medical Management Case and exceeded the maximum limit.";
									 SMSServicesobj.sendSingleSMS(msg,cntctNo[i] ,templateId);
   								}
   							}
					     }*/
					
				}
				
				else
				{
					ehfCaseObj.setExceedFlg("");
				}
				if(preauthVO.getEnhAmt() != null)
				ehfCaseObj.setEnhAmounts(Long.parseLong(preauthVO.getEnhAmt()));
				genericDao.save(ehfCaseObj);
				msg="Success";
			}
			
			if(preauthVO.getEnhancementDtls() != null && !preauthVO.getEnhancementDtls().equalsIgnoreCase(""))
			{
				
				if(preauthVO.getEnhancementDtls() != null && !preauthVO.getEnhancementDtls().equalsIgnoreCase(""))
				{
					String[]  str2 = preauthVO.getEnhancementDtls().substring(0, preauthVO.getEnhancementDtls().length()-1).split("@,");
					for(int i=0; i<str2.length;i++)
					{
						String list=str2[i];
						String[] str3=list.split(",");
						EhfCaseEnhancementDtls ehfCaseEnhancementDtls = new EhfCaseEnhancementDtls();
						ehfCaseEnhancementDtls.setSno(Long.parseLong(commonService.getSequence(config.getString("Ehf_Case_Enhancement_Dtls"))));
						ehfCaseEnhancementDtls.setCaseId(preauthVO.getCaseId());
						ehfCaseEnhancementDtls.setType(str3[0]);
						ehfCaseEnhancementDtls.setEnhCode(str3[1]);
						ehfCaseEnhancementDtls.setEnhQuantCode(str3[2]);
						ehfCaseEnhancementDtls.setNoOfUnits(Integer.parseInt(str3[3]));
						ehfCaseEnhancementDtls.setAmount(Integer.parseInt(str3[4]));
						ehfCaseEnhancementDtls.setCrtDt(new java.sql.Timestamp(date.getTime()));
						ehfCaseEnhancementDtls.setCrtUser(preauthVO.getCruUsr());
						ehfCaseEnhancementDtls.setActiveYN(config.getString("activeY"));
						if(preauthVO.getAttachmentsList().size() > 0)
						{
							AttachmentVO attachVO = preauthVO.getAttachmentsList().get(i);
							ehfCaseEnhancementDtls.setAttachPath(attachVO.getAttachPath());
							ehfCaseEnhancementDtls.setAttachName(attachVO.getFileName());
							
						}
						
						genericDao.save(ehfCaseEnhancementDtls);
						msg="Success";
					}
				
			}
			}
			
			EhfPatientDrugsNabh patientDrugs=new EhfPatientDrugsNabh();
			  
			if(preauthVO.getDrugs()!=null && preauthVO.getDrugs().length()>0)
			{
				String[]  str = preauthVO.getDrugs().substring(0, preauthVO.getDrugs().length()-1).split("@,");	
				for(int i=0; i<str.length;i++)
					{
						String list=str[i];
						String[] str3=list.split("~");		
						System.out.println(str3[0]+str3[1]+str3[2]);
						patientDrugs=new EhfPatientDrugsNabh(); 
						patientDrugs.setSno(Long.parseLong(commonService.getSequence(config.getString("EHF_CASE_ENH_PATIENT_DRUGS"))));
						patientDrugs.setActiveYN(config.getString("activeY"));
						patientDrugs.setCaseId(preauthVO.getCaseId());
							if(preauthVO.getDrugsAttchList().size() > 0)
							{
								AttachmentVO attachVO = preauthVO.getDrugsAttchList().get(i);
								patientDrugs.setAttachName(attachVO.getFileName());
								patientDrugs.setAttachPath(attachVO.getAttachPath());
							}
							patientDrugs.setAmount( Double.parseDouble(str3[2]));
							patientDrugs.setCrtDt(new java.sql.Timestamp(date.getTime()));
							patientDrugs.setCrtUser(preauthVO.getCruUsr());
							
							 try{
					             genericDao.save(patientDrugs);   
					             }catch(Exception e)
					             {
					            	e.printStackTrace(); 
					            	
//					            	gLogger.error("actOrder"+preauthVO.getCaseId());
//					            	gLogger.error("Error in method saveEnhDtls of class PreauthServiceImpl.java--> "+ e.getMessage());
					             }
							msg="Success";
					}
			}
			if("Success".equalsIgnoreCase(msg))
				msg="Successfully updated the Details";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			gLogger.error("Exception in method savePreauth() of PreauthServiceImpl--> "+ e.getMessage());
			msg ="Not updated";
		}
			return msg;
		}
		@Override
		public String deleteEnhnDtls(PreauthVO preauthVO) 
		{
			String msg="";
			EhfCaseEnhancementDtls caseEnhancementDtls = genericDao.findById(EhfCaseEnhancementDtls.class, Long.class, Long.parseLong(preauthVO.getIcdCatCode().toString()));	
			if(caseEnhancementDtls != null && (preauthVO.getCaseId().equalsIgnoreCase(caseEnhancementDtls.getCaseId())))
			{
				caseEnhancementDtls.setActiveYN(config.getString("activeN"));
				genericDao.save(caseEnhancementDtls);
				msg="success";
			}
			
			EhfPatientDrugsNabh ehfPatientDrugsNabhObj = genericDao.findById(EhfPatientDrugsNabh.class, Long.class, Long.parseLong(preauthVO.getIcdCatCode().toString()));	
			if(ehfPatientDrugsNabhObj != null  && (preauthVO.getCaseId().equalsIgnoreCase(ehfPatientDrugsNabhObj.getCaseId())))
			{
				ehfPatientDrugsNabhObj.setActiveYN(config.getString("activeN"));
				genericDao.save(ehfPatientDrugsNabhObj);
				msg="success";
			}
			
			return msg;
		}
		@Override
		public String savePreauthDtls(PreauthVO preauthVO) 
		{
			String rslt = "true";
			SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy");
		    Date lCurrentDt=new Date();
		    SimpleDateFormat sdformat=new SimpleDateFormat("dd/MM/yyyy");
		    Long lActOrder=0L;
		    Date lCurrentDate1=new java.sql.Timestamp(new Date().getTime()); 
		     try{
			    	 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
			    	 criteriaList.add(new GenericDAOQueryCriteria("id.caseId",preauthVO.getCaseId(),CriteriaOperator.EQUALS));
		    	 try{
			    	 lActOrder=genericDao.getMaxByPropertyCriteria(EhfAudit.class,"id.actOrder",criteriaList);
			    	 lActOrder=lActOrder+1;
		    	 	}
		    	 catch(Exception e)
		    	 	{
//		    		 gLogger.error("actOrder"+lActOrder);
//		    		 gLogger.error("CaseId"+preauthVO.getCaseId());
//		    		 gLogger.error("Error in method saveDischargeSummaryDtls of class PreauthServiceImpl.java and actOrder-->"+ e.getMessage());
		    	 	}
		    	 
		    	 //  insert or update asrit_discharge_summary
		         EhfDischargeSummary asritDischargeSummary=genericDao.findById(EhfDischargeSummary.class,String.class,preauthVO.getCaseId());
		        if(asritDischargeSummary==null){
		            asritDischargeSummary=new EhfDischargeSummary();
		        }
		        asritDischargeSummary.setCaseId(preauthVO.getCaseId());
		                 
		        if(asritDischargeSummary.getCrtDt()==null)
		        asritDischargeSummary.setCrtDt(lCurrentDate1);
		        if(asritDischargeSummary.getCrtDt()==null)
		        asritDischargeSummary.setCrtUsr(preauthVO.getCruUsr());
		        asritDischargeSummary.setLstUpdDt(lCurrentDate1);
		        asritDischargeSummary.setLstUpdUsr(preauthVO.getCruUsr());
		        
		        genericDao.save(asritDischargeSummary);   
		      
		        EhfCase asritcase=genericDao.findById(EhfCase.class,String.class,preauthVO.getCaseId());
		             asritcase.setLstUpdDt(lCurrentDt);
		             asritcase.setLstUpdUsr(preauthVO.getCruUsr());
		        if( preauthVO.getDischargeDt()!=null)
		        	asritcase.setCsDisDt(sdfw.parse(preauthVO.getDischargeDt()));
		        if(preauthVO.getTreatmntDt() != null)
		        	asritcase.setCsSurgDt(sdfw.parse(preauthVO.getTreatmntDt()));
		     //   if(preauthVO.getEnhAmt() != null)
		        //	asritcase.setEnhAppvAmt(Long.parseLong(preauthVO.getEnhAmt()));
		       
		        if(preauthVO.getDischargeDt()!=null )
		        { 
		            asritcase.setCaseStatus(config.getString("ehf_clinical_dischargeUpdate"));
		            asritcase.setCsDisUpdBy(preauthVO.getCruUsr());
		            asritcase.setCsDisUpdDt(lCurrentDate1);
		            
		        // insert into Asrit_Audit
		            
		            EhfAudit asritAudit=new EhfAudit();
		            EhfAuditId asritAuditPK=new EhfAuditId(lActOrder,preauthVO.getCaseId());
		             asritAudit.setId(asritAuditPK);
		             asritAudit.setActId(config.getString("ehf_clinical_dischargeUpdate"));
		             asritAudit.setActBy(preauthVO.getCruUsr());
		             asritAudit.setCrtUsr(preauthVO.getCruUsr());
		             asritAudit.setCrtDt(lCurrentDate1);
		             asritAudit.setUserRole(config.getString("preauth_medco_role"));
		             if(!"".equalsIgnoreCase(preauthVO.getIpOpFlag()) && "IPM".equalsIgnoreCase(preauthVO.getIpOpFlag()))
		             {
		            	if(preauthVO.getEnhAmt() != null)
		            	 asritAudit.setApprvAmt(Double.parseDouble(preauthVO.getEnhAmt()));
		             }
		          /*   if(preauthVO.getIcdProcCode() != null && config.getString("Dialysis.Procs").contains("~"+preauthVO.getIcdProcCode()+"~"))
					{
		    		 	Long pckAppAmt=0L;
		    		 	Double totalCycles=0.0;
		    		 	Double cyclesDone=0.0;
		    		 	Double icdAmt=0.0;
		    		 	
		    		 	icdAmt = Double.parseDouble(preauthVO.getIcdAmt());
		    		 	totalCycles =  Double.parseDouble(preauthVO.getTotCycles());
		    		 	cyclesDone=preauthVO.getCycles().doubleValue();
		    		 	
		    		 	pckAppAmt = Math.round((cyclesDone/totalCycles)*icdAmt);
		    		 	asritcase.setPckAppvAmt(pckAppAmt);
		    		 	asritcase.setPreauthTotalPackageAmt(pckAppAmt);
		    		 	asritAudit.setApprvAmt(Double.parseDouble(pckAppAmt.toString()));
					}  */
		             asritAudit.setLangId("en_US");
		             try{
		             genericDao.save(asritAudit);   
		             }catch(Exception e)
		             {
		            	e.printStackTrace(); 
//		            	gLogger.error("actOrder"+lActOrder);
//		            	gLogger.error("actOrder"+preauthVO.getCaseId());
//		            	gLogger.error("Error in method getMaxActOrder of class PreauthClinicalNotesDaoImpl.java--> "+ e.getMessage());
		             }
		             
		        }
		        genericDao.save(asritcase);     
		       
		    }
		    catch(Exception e){
		    	rslt="false";
//		    	gLogger.error("Error in saveDischargeSummaryDtls() of PreauthClinicalNotesDAOImpl"+e.getMessage());        
		        e.printStackTrace();
		    }
		return rslt;

		}
		//By ramalakshmi for holding cases
		@Override
		public String holdUnholdCase(PreauthVO preauthVO){
			String resultMsg = "";
			Long actOrder = (long) 0;
			//Long lActOrder = 0L;
			Date lCurrentDate1=new java.sql.Timestamp(new Date().getTime());
			String caseStatus = "";
			EhfCasePreauthAmounts ehfCasePreauthAmounts = null;
			try{
					EhfCase ehfcase=genericDao.findById(EhfCase.class,String.class,preauthVO.getCaseId());
			if(ehfcase!=null)
			{
				if(preauthVO.getButtonVal() != null && preauthVO.getButtonVal().trim().length() > 0 && (PreauthConstants.HOLD_STATUS).equalsIgnoreCase(preauthVO.getButtonVal()))
				{
					caseStatus = PreauthConstants.HOLD_STATUS;
					ehfcase.setExceedFlg("Y");
				}
				else
				{
					ehfCasePreauthAmounts = genericDao.findById(EhfCasePreauthAmounts.class,String.class,preauthVO.getCaseId());
						if(ehfCasePreauthAmounts != null && ehfCasePreauthAmounts.getCeoAppvTotalAmt() != null)
						{
							caseStatus = PreauthConstants.CEO_PREAUTH_APPROVED_STATUS;
						}
						else
						{
							caseStatus = PreauthConstants.MEDCO_PREAUTH_APPROVED_STATUS;
						}
							ehfcase.setExceedFlg("");
				}
					ehfcase.setCaseStatus(caseStatus);
					ehfcase.setLstUpdDt(lCurrentDate1);
					ehfcase.setLstUpdUsr("Others");
					genericDao.save(ehfcase);
			}
				
				List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("id.caseId",preauthVO.getCaseId(), GenericDAOQueryCriteria.CriteriaOperator.EQUALS ));
				actOrder= genericDao.getMaxByPropertyCriteria(EhfAudit.class, "id.actOrder",criteriaList);
				
				EhfAudit asritAudit=new EhfAudit();
				EhfAuditId asritAuditPK=new EhfAuditId(actOrder+1,preauthVO.getCaseId());
				asritAudit.setId(asritAuditPK);
				asritAudit.setActId(caseStatus);
				asritAudit.setActBy(preauthVO.getCruUsr());
				asritAudit.setRemarks(preauthVO.getHoldRemarks());
				asritAudit.setCrtUsr(preauthVO.getCruUsr());
				if(!"".equalsIgnoreCase(preauthVO.getApprvdPckAmt()) && preauthVO.getApprvdPckAmt() != null)
				asritAudit.setApprvAmt(Double.parseDouble(preauthVO.getApprvdPckAmt()));
				asritAudit.setCrtDt(lCurrentDate1);
				asritAudit.setUserRole(preauthVO.getUserRole());
				asritAudit.setLangId("en_US");
				genericDao.save(asritAudit);
				resultMsg = "Y";
			}
			catch(Exception e){
				resultMsg = "N";
//				gLogger.error("## Exception while holding or unholding the case in PreauthServiceImpl --> holdstatus -> "+ preauthVO.getButtonVal());
				e.printStackTrace();
			}
			return resultMsg;
		}
		@Override
		public String updateMedcoRemarks(PreauthVO preauthVO)
		{
			String rslt = "true";
			SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy");
		    Date lCurrentDt=new Date();
		    SimpleDateFormat sdformat=new SimpleDateFormat("dd/MM/yyyy");
		    Long lActOrder=0L;
		    Date lCurrentDate1=new java.sql.Timestamp(new Date().getTime()); 
		     try{
			    	 List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
			    	 criteriaList.add(new GenericDAOQueryCriteria("id.caseId",preauthVO.getCaseId(),CriteriaOperator.EQUALS));
		    	 try{
			    	 lActOrder=genericDao.getMaxByPropertyCriteria(EhfAudit.class,"id.actOrder",criteriaList);
			    	 lActOrder=lActOrder+1;
		    	 	}
		    	 catch(Exception e)
		    	 	{
//		    		 gLogger.error("actOrder"+lActOrder);
//		    		 gLogger.error("CaseId"+preauthVO.getCaseId());
		    		 gLogger.error("Error in method saveDischargeSummaryDtls of class PreauthServiceImpl.java and actOrder-->"+ e.getMessage());
		    	 	}
		    	
		      
		        EhfCase asritcase=genericDao.findById(EhfCase.class,String.class,preauthVO.getCaseId());
		        		asritcase.setCaseStatus("CD324");
		        		asritcase.setLstUpdUsr(preauthVO.getCruUsr());
		            
		            EhfAudit asritAudit=new EhfAudit();
		            EhfAuditId asritAuditPK=new EhfAuditId(lActOrder,preauthVO.getCaseId());
		             asritAudit.setId(asritAuditPK);
		             asritAudit.setActId("CD324");
		             asritAudit.setRemarks(preauthVO.getRemarks());
		             asritAudit.setActBy(preauthVO.getCruUsr());
		             asritAudit.setCrtUsr(preauthVO.getCruUsr());
		             asritAudit.setCrtDt(lCurrentDate1);
		             asritAudit.setUserRole(config.getString("preauth_medco_role"));
		            // asritAudit.setApprvAmt(Double.parseDouble(preauthVO.getEnhAmt()));
		             asritAudit.setLangId("en_US");
		             try{
		             genericDao.save(asritAudit);   
		             }catch(Exception e)
		             {
		            	e.printStackTrace(); 
//		            	gLogger.error("actOrder"+lActOrder);
//		            	gLogger.error("actOrder"+preauthVO.getCaseId());
//		            	gLogger.error("Error in method getMaxActOrder of class PreauthServiceImpl.java--> "+ e.getMessage());
		             }
		             
		        
		        genericDao.save(asritcase);     
		       
		    }
		    catch(Exception e){
		    	rslt="false";
//		    	gLogger.error("Error in saveDischargeSummaryDtls() of PreauthServiceImpl"+e.getMessage());        
		        e.printStackTrace();
		    }
		return rslt;

		}
		@Override
		public ClaimsFlowVO saveEnhanceAmounts(ClaimsFlowVO claimFlowVO)
		{
			EhfCase  ehfCase = genericDao.findById(EhfCase.class, String.class, claimFlowVO.getCaseId());
		 try{	
				if(ehfCase != null)
				{
					Long enhAmt = 0L;
					if(claimFlowVO.getTotalConsumableAmount() != null)
						enhAmt = claimFlowVO.getTotalConsumableAmount().longValue();
					if(claimFlowVO.getTotalDrugAmt()!= null)
						enhAmt= enhAmt.longValue() +claimFlowVO.getTotalDrugAmt().longValue();
					if(enhAmt != null)
					{
						ehfCase.setEnhAmounts(enhAmt);
						genericDao.save(ehfCase);   
					}
					claimFlowVO.setEnhAmounts(enhAmt);
				}
		 	}
		 catch(Exception e)
		 	{
//		    	gLogger.error("Error in saveEnhanceAmounts() of PreauthServiceImpl"+e.getMessage());        
		        e.printStackTrace();
		    }
			return claimFlowVO;
		}
		@Override
		public String getStatusCase(String caseId) 
		{
			String msg="";
			EhfCase ehfCaseObj = genericDao.findById(EhfCase.class, String.class, caseId);
			if(ehfCaseObj != null)
			{
				if(PreauthConstants.HOLD_STATUS.equalsIgnoreCase(ehfCaseObj.getCaseStatus()) && ehfCaseObj.getCaseStatus() != null)
				{
					msg="The case is locked due to exceeding the ceiling amount, to unlock the case please contact Dr.Suresh 9849888789";
				}
			}
			return msg;
		}
		@Override
		public Integer getQuantAmountNIMS(String quantId)
		{
			 int amount =0;
			 try
			 {
				 EhfmHospQuantityNIMS ehfmHospQuantity = genericDao.findById(EhfmHospQuantityNIMS.class, String.class, quantId); 
				 if(ehfmHospQuantity != null)
				 {
					 amount = ehfmHospQuantity.getAmount(); 	 
				 }
				 
			 }catch(Exception e)
			 {
				e.printStackTrace(); 
			 }
			return amount;
		}
		@Override
		public List<LabelBean> getNimsDepts() 
		{
			List<LabelBean> lstHospStay = new ArrayList<LabelBean>();
			StringBuffer query = new StringBuffer();
			try
			{
				query.append(" select distinct a.id.deptId as ID , a.deptName as VALUE   from EhfmHospStayNIMS a  order by a.deptName asc ");
				lstHospStay = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return lstHospStay;

		}
		@Override
		public List<LabelBean> getHospStayQuantListNIMS(String enhTypeId)
		{
			List<LabelBean> lstHospStayQuant = new ArrayList<LabelBean>();
			StringBuffer query = new StringBuffer();
			try
			{
				query.append(" select a.id.itemCode as ID , a.itemDesc as VALUE   from EhfmHospQuantityNIMS a where a.deptId='"+enhTypeId+"' and a.activeYn='Y' order by a.itemDesc asc ");
				lstHospStayQuant = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return lstHospStayQuant;
		}
		@Override
		public boolean checkNimsCase(String caseId) 
		{
			boolean returnValue=false;
			try
				{
					StringBuffer query=new StringBuffer();
					
					query.append( " select ec.case_id as ID,ec.case_hosp_code as VALUE from ehf_case ec,ehf_patient ep " );
					query.append( " WHERE ec.case_patient_no=ep.patient_id and ep.patient_ipop='IPM' and ec.case_hosp_code in ('EHS34','EHS13')" );
					query.append( " and ec.scheme_id='"+config.getString("Scheme.TG.State")+"' and ec.case_id='"+caseId+"' " );
				
					List<LabelBean> newBeanLst=genericDao.executeSQLQueryList(LabelBean.class, query.toString());
					if(newBeanLst!=null && newBeanLst.size()>0)
						returnValue=true;
					
				}
			catch(Exception e)
				{
					e.printStackTrace();
//					gLogger.error("Exception in method checkNimsCase() of PreauthServiceImpl--> "+ e.getMessage());
					return returnValue;
				}
			
			return returnValue;
		}
		public String getPatientType(String asriCode,String icdProcCode,String scheme)
		{
			String patientType="";
			StringBuffer query=new StringBuffer();
			query.append( " select et.process as ID from EHFM_MAIN_THERAPY et  WHERE et.asri_code='"+asriCode+"' and ICD_PROC_CODE='"+icdProcCode+"' AND STATE='"+scheme+"' ");
			List<LabelBean> newBeanLst=genericDao.executeSQLQueryList(LabelBean.class, query.toString());
			if(newBeanLst.size() >0)
			{
				if(newBeanLst != null)
					patientType=newBeanLst.get(0).getID();
			}
			return patientType;
		}
		@Override
		public String getHospScheme(String caseId) {
			String nimsHospId=null;
			EhfCase ehfCase=new EhfCase();
			try
			{
			if(caseId!=null)
			{
			ehfCase=genericDao.findById(EhfCase.class,String.class,caseId);
			}
			if(ehfCase!=null)
			{
				nimsHospId=ehfCase.getCaseHospCode();
			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return nimsHospId;
		}
		@Override
		public String getPatType(String patientId) {
			String patType=null;
			EhfPatient ehfPatient=new EhfPatient();
			try
			{
			if(patientId!=null)
			{
				ehfPatient=genericDao.findById(EhfPatient.class,String.class,patientId);
			}
			if(ehfPatient!=null)
			{
				patType=ehfPatient.getPatientIpop();
			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return patType;
		}
		//sai krishna:#CR8566:method to get the details of Dialysis Cycles details.
				@Override
				public List<LabelBean> getDialysisCycles(PreauthVO preauthVO) 
				{
					String CaseId=preauthVO.getCaseId();
					List<LabelBean> lstDlyClyLst = new ArrayList<LabelBean>();
					StringBuffer query = new StringBuffer();
					try
					{
						
						query.append("SELECT DISTINCT ecdd.CYCLE_NO AS COUNT,TO_CHAR(ecpb.PATIENT_INTIME, 'DD-MM-YYYY hh24:mi:ss') AS ID, ");
						query.append("TO_CHAR(ecpb.PATIENT_OUTTIME, 'DD-MM-YYYY hh24:mi:ss') AS VALUE,FLOOR((ecpb.PATIENT_OUTTIME - ecpb.PATIENT_INTIME) * 24) || ':' || ");
						query.append("LPAD(FLOOR(MOD((ecpb.PATIENT_OUTTIME - ecpb.PATIENT_INTIME) * 24 * 60, 60)), 2, '0') || ':' || ");
						query.append("LPAD(FLOOR(MOD((ecpb.PATIENT_OUTTIME - ecpb.PATIENT_INTIME) * 24 * 60 * 60, 60)), 2, '0') AS CONST, ");
						query.append("LISTAGG(ecda.FILE_PATH, '~') WITHIN GROUP (ORDER BY ecda.FILE_PATH) AS LVL FROM EHF_CASE EC,EHF_CASE_PATIENT_BIOMETRIC ecpb, ");
						query.append("EHF_CASE_DIALYSIS_DTLS ecdd,EHF_CASE_DOC_ATTCH ecda,EHF_CASE_DIALYSIS_IMG_MPG ecdim WHERE ");
						query.append("EC.CASE_ID = ecpb.CASE_ID AND EC.CASE_ID = ecdd.CASE_ID AND EC.CASE_ID = ecda.CASE_ID AND EC.CASE_ID = ecdim.CASE_ID ");
						query.append("AND ECPB.CYCLE = ECDD.CYCLE_NO AND ecpb.CYCLE = ecdim.CYCLE_NO AND ecda.doc_seq_id = ecdim.doc_seq_id ");
						query.append("AND ecda.ATTACH_TYPE IN ('CD7335', 'CD7336') AND EC.CASE_ID ='"+CaseId+"' GROUP BY ecdd.CYCLE_NO, ");
						query.append("TO_CHAR(ecpb.PATIENT_INTIME, 'DD-MM-YYYY hh24:mi:ss'),TO_CHAR(ecpb.PATIENT_OUTTIME, 'DD-MM-YYYY hh24:mi:ss'), ");
						query.append("FLOOR((ecpb.PATIENT_OUTTIME - ecpb.PATIENT_INTIME) * 24) || ':' || LPAD(FLOOR(MOD((ecpb.PATIENT_OUTTIME - ecpb.PATIENT_INTIME) * 24 * 60, 60)), 2, '0') || ':' || ");
						query.append("LPAD(FLOOR(MOD((ecpb.PATIENT_OUTTIME - ecpb.PATIENT_INTIME) * 24 * 60 * 60, 60)), 2, '0') ORDER BY 1 ");
						lstDlyClyLst = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
						if (lstDlyClyLst != null && !lstDlyClyLst.isEmpty()) {
						    for (LabelBean lst : lstDlyClyLst) {
						        if (lst.getLVL() != null && !lst.getLVL().isEmpty()) {
						            try {
						                String[] files = lst.getLVL().split("~");
						                if (files.length == 2) {
						                    String ext1 = files[0].substring(files[0].lastIndexOf(".") + 1);
						                    if ("pdf".equalsIgnoreCase(ext1)) {
						                        lst.setTYPE("PDF");
						                    } else {
						                        lst.setTYPE("IMG");
						                    }
						                    lst.setLVL(getAttachBytes(files[0])); 
						                    String ext2 = files[1].substring(files[1].lastIndexOf(".") + 1);
						                    if ("pdf".equalsIgnoreCase(ext2)) {
						                        lst.setWFTYPE("PDF");
						                    } else {
						                        lst.setWFTYPE("IMG");
						                    }
						                    lst.setSUBID(getAttachBytes(files[1])); 
						                }
						            } catch (Exception e) {
						                System.err.println("Error converting image to base64: " + e.getMessage());
						            }
						        }
						    }
						}
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					return lstDlyClyLst;

				}
				//sai:#CR8566:Private method to change the Onbed Photo path to base64 for Dialysis Cycles details.
				private String getAttachBytes(String savedName) throws Exception{
					byte[] bytes = null;
					String extType= "";
					String ext = null;
					try{
						ext= savedName.substring(savedName.lastIndexOf(".")+1);
						if("pdf".equalsIgnoreCase(ext)){
							extType = "data:application/pdf;base64,";
						}else{
							extType = "data:image/"+ext+";base64,";
						}
						String basePath = savedName.substring(0,savedName.lastIndexOf("/"));
				        String relativePath = savedName.substring(basePath.length());
						bytes = Files.readAllBytes(Paths.get(basePath,relativePath));
					}catch(Exception e){
						extType = "data:image/gif;base64,";
						bytes = Files.readAllBytes(Paths.get("/NAS4-TS-Production/images","NoData.gif"));
					}
					return extType+Base64.encodeBytes(bytes);
				}
				//sai krishna:#CR8566:method to get the dialysis cycles count to  link display condition
				@Override
				public String getDailysisCnt(String caseId) 
				{
					String cnt=null;
					List<LabelBean> DialysisCnt = new ArrayList<LabelBean>();
					StringBuffer query = new StringBuffer();
					StringBuffer query1 = new StringBuffer();
					try{
						query.append("select count(*) COUNT from EHF_CASE_DIALYSIS_DTLS a where a.case_id='"+caseId+"' ");
						DialysisCnt = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
						if(DialysisCnt!=null && !DialysisCnt.isEmpty()){
							cnt=DialysisCnt.get(0).getCOUNT().toString();
						}
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					return cnt;
				}
		
}
