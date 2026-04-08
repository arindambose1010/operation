package com.ahct.chronicOp.service;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ahct.model.EhfCase;
import com.ahct.model.EhfChronicCaseDtlPK;
import com.ahct.model.EhfChronicPatientDtl;
import com.ahct.patient.vo.CommonDtlsVO;
import com.ahct.patient.vo.DrugsVO;
import com.ahct.patient.vo.PreauthVO;
import com.ahct.preauth.vo.PreauthClinicalNotesVO;
import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.chronicOp.dao.ChronicOPDAO;
import com.ahct.model.EhfChronicCaseDtl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ahct.chronicOp.vo.ChronicOPVO;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.vo.LabelBean;
import com.tcs.framework.persistanceConfiguration.GenericDAO;





public class ChronicOPServiceImpl implements ChronicOPService{
	private final static Logger GLOGGER = Logger.getLogger ( ChronicOPServiceImpl.class ) ;
	ChronicOPDAO ChronicOpDao;
	GenericDAO genericDao;
	HibernateTemplate hibernateTemplate;
	
	


	public GenericDAO getGenericDao() {
		return genericDao;
	}


	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}







	public ChronicOPDAO getChronicOpDao() {
		return ChronicOpDao;
	}

	public void setChronicOpDao(ChronicOPDAO chronicOpDao) {
		this.ChronicOpDao = chronicOpDao;
	}


	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}


	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


	@Override
	public List<ChronicOPVO> getChronicOPPatients(String hospId,String roleId,String userState) {
		
		List<ChronicOPVO> chrOPList=null;
		
		chrOPList=ChronicOpDao.getChronicOPPatients(hospId,roleId,userState);
		
		return chrOPList;
	}

	


	/**
     * ;
     * @return List<LabelBean> relationList
     * @function This Method is Used For getting Relations List from RelationMst table
     */
	@Override
	public List<LabelBean> getRelations() 
	{
		List<LabelBean> relationsList=null;
		try
		{
			relationsList=ChronicOpDao.getRelations();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return relationsList;
		
	}

	
	

	
	
	/**
     * ;
     * @param String cardNo
     * @return String photoUrl
     * @function This Method is Used For getting photoUrl for given cardNo
     */
	@Override
	public String getPatientPhoto(String cardNo) {
		String photoUrl=null;
		try
		{
			photoUrl=ChronicOpDao.getPatientPhoto(cardNo);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getPatientPhoto() in ChronicOPServiceImpl class."+e.getMessage());
		}
		return photoUrl;
	}


	
	@Override
	public String getHospActiveStatus(String userId, String roleId)
	{
		String actStatus="";
		try
		{
			actStatus=ChronicOpDao.getHospActiveStatus(userId, roleId);
		}
		catch(Exception e)
		{
			GLOGGER.error("Error in method getHospActiveStatus() of ChronicOPServiceImpl "+e.getMessage());
		}
		return actStatus;
	}
	
	/**
     * ;
     * @param ChronicOPVO chronicOPVO
     * @return ChronicOPVO
     * @function This Method is Used For Retrieving Enrollment Details of Employee/Pensioner card no
     */
	@Override
	public ChronicOPVO retrieveCardDetails(ChronicOPVO chronicOPVO) {
		ChronicOPVO patientVO=null;
		try
		{
			patientVO=ChronicOpDao.retrieveCardDetails(chronicOPVO);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			GLOGGER.error("Exception Occurred in retrieveCardDetails() in ChronicOPServiceImpl class."+e.getMessage());
		}
		return patientVO;
	}
	/**
     * ;
     * @param String userId
     * @param String roleId
     * @return List<LabelBean> hospitalList
     * @function This Method is Used For getting Hospital List for given user and role
     */
	@Override
	public List<LabelBean> getHospital(String userId,String roleId) {
		List<LabelBean> hospitalList=null;
		try {
			hospitalList = ChronicOpDao.getHospital(userId,roleId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return hospitalList;
	}
	
	
	@Override
	public String getSequence(String pStrSeqName)throws Exception
	{
		String seqId = ChronicOpDao.getSequence(pStrSeqName);
		return seqId;
	}
	/**
     * ;
     * @param String hospId
     * @return String hospName
     * @function This Method is Used For getting Hospital Name
     */
	@Override
	public String getHospName(String hospId) {
		String hospitalName=null;
		try
		{
			hospitalName=ChronicOpDao.getHospName(hospId);
		} 
		catch (Exception e)
		{
			GLOGGER.error("Exception Occurred in getHospName() in ChronicOPServiceImpl class."+e.getMessage());
		}
		return hospitalName;
	}
	
	
	/**
     * ;
     * @param chronicOPVO
     * @return int noOfRecords
     * @function This Method is Used For Registering Direct Patient
     */
	@Override
	public int registerPatient(ChronicOPVO chronicOPVO) 
	{
		int i=0;
		try {
			i = ChronicOpDao.registerPatient(chronicOPVO);
		}
		catch (Exception e) {
			e.printStackTrace();
			//GLOGGER.error("Exception Occurred in registerPatient() in ChronicOPServiceImpl class."+e.getMessage());
		}
		return i;
		
	}
	
	/**
     * ;
     * @param String chronicId
     * @return EhfChronicPatientDtl ehfChronicPatientDtl
     * @function This Method is Used For retrieving PatientDetails for given chronicId
     */
	@Override
	public EhfChronicPatientDtl getPatientDetails(String chronicId) {
		EhfChronicPatientDtl ehfChronicPatientDtl=null;
		try 
		{
			ehfChronicPatientDtl=(EhfChronicPatientDtl)ChronicOpDao.getPatientDetails(chronicId);
		} 
		catch (Exception e) 
		{
			GLOGGER.error("Exception Occurred in getPatientDetails() in ChronicOPServiceImpl class."+e.getMessage());
		}
		return ehfChronicPatientDtl;
	}
	
	
	/**
     * ;
     * @param String locId
     * @return String locName
     * @function This Method is Used For getting Location Name for given Location Id
     */
	@Override
	public String getLocationName(String locId) {
		String locationName=null;
		try
		{
			locationName=ChronicOpDao.getLocationName(locId);
		} 
		catch (Exception e)
		{
			GLOGGER.error("Exception Occurred in getLocationName() in ChronicOPServiceImpl class."+e.getMessage());
		}
		return locationName;
	}
	
	 /**
     * ;
     * @param String relationId
     * @return String relationName
     * @function This Method is Used For getting relationName for given relationId from RelationMst table
     */
	@Override
	public String getRelationName(String relationId) {
		String relationName=null;
		try
		{
			relationName=ChronicOpDao.getRelationName(relationId);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getRelationName() in ChronicOPServiceImpl class."+e.getMessage());
		}
		return relationName;
	}
	
	
	/**
     * ;
     * @param String cardNo
     * @return String photoUrl
     * @function This Method is Used For getting photoUrl for given cardNo
     */

	@Override
	public List<ChronicOPVO> searchChronicOPPatients(String chronicId,
			String patName, String state, String district, String fromDt,
			String toDate,String user,String hospId,String roleId) {
		List<ChronicOPVO> chrOPList=null;



		chrOPList=ChronicOpDao.searchChronicOPPatients(chronicId, patName, state, district, fromDt, toDate,user,hospId,roleId);

		



		
		return chrOPList;
	}



	@Override
	public List<ChronicOPVO> getChronicClaimCases(String roleId,String hospId,String userState) {
		
		List<ChronicOPVO> chrOPList=null;

		chrOPList=ChronicOpDao.getChronicClaimCases(roleId,hospId,userState);
		return chrOPList;
	}





	@Override
	public String getHospitalID(String userId,String roleId) {



		
		

		 String hospId=null;
		 hospId=ChronicOpDao.getHospitalID(userId,roleId);
		return hospId;


	}


	

	@Override
	public ChronicOPVO getChronicOPCasesView(ChronicOPVO chronicOPVO) {


		ChronicOPVO list=null;
		list=ChronicOpDao.getChronicOPCasesView(chronicOPVO);
		
		return list;
	}


	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



/*added by venkatesh*/
	
	


	@Override
	public String saveCaseDetails(ChronicOPVO ChronicOpVO)
	{
		String lStrChronicId=null;
		try 
		{
			lStrChronicId = ChronicOpDao.saveCaseDetails(ChronicOpVO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			//GLOGGER.error("Exception Occurred in saveCaseDetails() in ChronicOPServiceImpl class."+e.getMessage());
		}      
		return lStrChronicId;
		
	}
	public String getChronicStatus(String chronicId,String chronicNo)
	{
		String chronicStatus = null;
		EhfChronicCaseDtl ehfChronicCaseDtl=new EhfChronicCaseDtl();
		try{
			EhfChronicCaseDtlPK ehfChronicCaseDtlPK=new EhfChronicCaseDtlPK(chronicId,chronicNo);
			ehfChronicCaseDtl= genericDao.findById(EhfChronicCaseDtl.class, EhfChronicCaseDtlPK.class, ehfChronicCaseDtlPK);
		if(ehfChronicCaseDtl != null)
		{
			chronicStatus = ehfChronicCaseDtl.getChronicStatus();
		}
		}catch(Exception e)
		{
		e.printStackTrace();	
		}
		return chronicStatus;
	}
	
	@Override
	public PreauthVO getPatientFullDtls(String lStrCaseId,String chronicNo,String showAll) {
		PreauthVO preauthVO = ChronicOpDao.getPatientFullDtls(lStrCaseId,chronicNo,showAll);
		return preauthVO;
	}

	@Override
	public String deleteGenInvest(String chronicId, String investId) {
		String result="false";
		try
		{
			result=ChronicOpDao.deleteGenInvest(chronicId,investId);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in deleteGenInvest() in ChronicOPServiceImpl class."+e.getMessage());	
		}
		return result;
	}
	@Override
	public List<CommonDtlsVO> getPatientCommonDtls(String chronicId)
	{
		List<CommonDtlsVO> commonDtls=null;
		try {
			commonDtls = ChronicOpDao.getPatientCommonDtls(chronicId);
		} 
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getPatientCommonDtls() in ChronicOPServiceImpl class."+e.getMessage());	
		}
		return commonDtls;		
	}
	
	public String  getChronicInstallment(String chronicId){
		String installment = null;
		
		try {
			installment=ChronicOpDao.getChronicInstallment(chronicId);
		} 
		catch (Exception e) {
			e.printStackTrace();
			//GLOGGER.error("Exception Occurred in getChronicInstallment() in ChronicOPServiceImpl class."+e.getMessage());	
		}
		return installment;	
	}
	public boolean validateAttachments(String chronicNo,String caseStatus,String usrRole)
	{
		boolean status=false;
		try {
			status=ChronicOpDao.validateAttachments(chronicNo,caseStatus,usrRole);
		} 
		catch (Exception e) {
			e.printStackTrace();
			//GLOGGER.error("Exception Occurred in validateAttachments() in ChronicOPServiceImpl class."+e.getMessage());	
		}
		return status;
	}
	public long getPackageAmount(String chronicId,String installment,String scheme)
	{
		long pkgAmount=0;
		try {
			pkgAmount=ChronicOpDao.getPackageAmount(chronicId,installment,scheme);
		} 
		catch (Exception e) {
			e.printStackTrace();
			//GLOGGER.error("Exception Occurred in getPackageAmount() in ChronicOPServiceImpl class."+e.getMessage());	
		}
		return pkgAmount;
		
	}
	
	public List<ClaimsFlowVO> getAuditTrail(String chronicNo){
		
		List<ClaimsFlowVO> lstWrkFlw=new ArrayList<ClaimsFlowVO>();
		try {
			lstWrkFlw=ChronicOpDao.getAuditTrail(chronicNo);
		} 
		catch (Exception e) {
			e.printStackTrace();
			//GLOGGER.error("Exception Occurred in getAuditTrail() in ChronicOPServiceImpl class."+e.getMessage());	
		}
		return lstWrkFlw;
		
		
	}
	public String saveChronicClaim(ChronicOPVO chronicOPVO)
	{
		
		String msg=null;
		try {
			msg=ChronicOpDao.saveChronicClaim(chronicOPVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
			//GLOGGER.error("Exception Occurred in saveChronicClaim() in ChronicOPServiceImpl class."+e.getMessage());	
		}
		return msg;
	}
	
	public boolean validateChronicClaim(String chronicId,String chronicNo)
	{
		boolean claimInit=false;
		try {
			claimInit=ChronicOpDao.validateChronicClaim(chronicId,chronicNo);
		} 
		catch (Exception e) {
			e.printStackTrace();
			//GLOGGER.error("Exception Occurred in validateChronicClaim() in ChronicOPServiceImpl class."+e.getMessage());	
		}
		return claimInit;
	}
	
	@Override
	public List<LabelBean> getOpPkgList(String scheme) {
		List<LabelBean> opPkgList= new ArrayList<LabelBean>();
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct a.id.icdChronicPkgCode as ID , a.icdOpPkgName as VALUE from EhfmChronicPkgMst a where a.activeYn='Y' and a.id.scheme='"+scheme+"' order by a.icdOpPkgName asc");     
			opPkgList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			GLOGGER.error("Exception Occurred in getOpPkgList() in ChronicOPServiceImpl class."+e.getMessage());
		}
		return opPkgList;
	}
	
	
	public List<String> getChronicInvestList(String opCatCode,String installment) {
		List<String> investList1 = new ArrayList<String>();
		List<LabelBean> investList=null;
		try
		{
			investList=ChronicOpDao.getChronicInvestBlockName(opCatCode,installment);
			 for (LabelBean labelBean: investList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (investList1 != null && investList1.size() > 0) {
	                    	investList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	investList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			GLOGGER.error("Exception Occurred in getChronicInvestList() in ChronicOPServiceImpl class."+e.getMessage());
		}
		return investList1;
	}
	
	
	public List<String> getChronicDrugsList(String opCatCode) {
		List<String> drugList1 = new ArrayList<String>();
		List<LabelBean> drugList=null;
		try
		{
			drugList=ChronicOpDao.getChronicDrugs(opCatCode);
			 for (LabelBean labelBean: drugList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (drugList1 != null && drugList1.size() > 0) {
	                    	drugList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	drugList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//GLOGGER.error("Exception Occurred in getChronicDrugsList() in ChronicOPServiceImpl class."+e.getMessage());
		}
		return drugList1;
	}
	public List<String> getChronicChemSubstanceList(String opCatCode) {
		List<String> drugList1 = new ArrayList<String>();
		List<LabelBean> drugList=null;
		try
		{
			drugList=ChronicOpDao.getChronicChemSubstance(opCatCode);
			 for (LabelBean labelBean: drugList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (drugList1 != null && drugList1.size() > 0) {
	                    	drugList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	drugList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//GLOGGER.error("Exception Occurred in getChronicChemSubstanceList() in ChronicOPServiceImpl class."+e.getMessage());
		}
		return drugList1;
	}
	@Override
	public List<ChronicOPVO> searchChronicClaimCases(String chronicId,
			String patName, String state, String district, String fromDt,
			String toDate, String user, String hospId, String roleId) {
		 List<ChronicOPVO> list=null;
		 list=ChronicOpDao.searchChronicClaimCases(chronicId, patName, state, district, fromDt, toDate, user, hospId, roleId);
		return list;
	}

	
	@Override
	public ChronicOPVO searchChroniOPCases(ChronicOPVO chronicOPVO)  {
		
		ChronicOPVO list=null;
		list=ChronicOpDao.searchChroniOPCases(chronicOPVO);
		
		return list;
		
		
		
		
	}
	
	@Override
	public List<String> getchronicInvestigations(String lStrBlockId,String packageCode,String installment) {
		List<String> mainInvestList=null;
		try
		{
			mainInvestList=ChronicOpDao.getchronicInvestigations(lStrBlockId,packageCode,installment);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//GLOGGER.error("Exception Occurred in getchronicInvestigations() in ChronicOPServiceImpl class."+e.getMessage());
		}
		return mainInvestList;
	}
	@Override
	public List<String> getOpIcdList(String opPkgCode,String scheme) {
		List<String> icdList1 = new ArrayList<String>();
		List<LabelBean> icdList=null;
		try
		{
			icdList=ChronicOpDao.getOpIcdList(opPkgCode,scheme);
			 for (LabelBean labelBean: icdList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (icdList1 != null && icdList1.size() > 0) {
	                    	icdList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	icdList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getOpIcdList() in ChronicOPServiceImpl class."+e.getMessage());
		}
		return icdList1;
	}
	
	@Override
	public List<String> getOpDrugSubList(String drugTypeCode) {
		List<LabelBean> drugSubList=null;
		List<String> drugSubList1 = new ArrayList<String>();
		try
		{
			drugSubList=ChronicOpDao.getOpDrugSubList(drugTypeCode);
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
			GLOGGER.error("Exception Occurred in getDrugSubList() in ChronicOPServiceImpl class."+e.getMessage());
		}
		return drugSubList1;
	}
	@Override
	public List<String> getOpPharSubList(String drugTypeCode) {
		List<LabelBean> drugSubList=null;
		List<String> drugSubList1 = new ArrayList<String>();
		try
		{
			drugSubList=ChronicOpDao.getOpPharSubList(drugTypeCode);
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
			//GLOGGER.error("Exception Occurred in getOpPharSubList() in ChronicOPServiceImpl class."+e.getMessage());
		}
		return drugSubList1;
	}
	@Override
	public List<String> getOpChemSubList(String pharSubCode) {
		List<LabelBean> drugSubList=null;
		List<String> drugSubList1 = new ArrayList<String>();
		try
		{
			drugSubList=ChronicOpDao.getOpChemSubList(pharSubCode);
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
			//GLOGGER.error("Exception Occurred in getOpChemSubList() in ChronicOPServiceImpl class."+e.getMessage());
		}
		return drugSubList1;
	}
	@Override
	public List<String> getChemSubList(String cSubGrpCode) {
		List<LabelBean> drugSubList=null;
		List<String> drugSubList1 = new ArrayList<String>();
		try
		{
			drugSubList=ChronicOpDao.getChemSubList(cSubGrpCode);
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
			//GLOGGER.error("Exception Occurred in getChemSubList() in ChronicOPServiceImpl class."+e.getMessage());
		}
		return drugSubList1;
	}
	
	public ChronicOPVO getCotdChkList(String chronicId,String chronicNo) throws Exception
	{
		ChronicOPVO chronicOPVO=new ChronicOPVO();
		try {
			chronicOPVO=ChronicOpDao.getCotdChkList(chronicId,chronicNo);
		} 
		catch (Exception e) {
			e.printStackTrace();
			//GLOGGER.error("Exception Occurred in getCotdChkList() in ChronicOPServiceImpl class."+e.getMessage());	
		}
		return chronicOPVO;
	}
	public ChronicOPVO getCoexChkList(String chronicId,String chronicNo) throws Exception
	{
		ChronicOPVO chronicOPVO=new ChronicOPVO();
		try {
			chronicOPVO=ChronicOpDao.getCoexChkList(chronicId,chronicNo);
		} 
		catch (Exception e) {
			e.printStackTrace();
			//GLOGGER.error("Exception Occurred in getCoexChkList() in ChronicOPServiceImpl class."+e.getMessage());	
		}
		return chronicOPVO;
	}
	
	public Number getMonthsbetween(String chronicId,String chronicNo) throws Exception
	{
		Number diff=null;
		try {
			diff=ChronicOpDao.getMonthsbetween(chronicId,chronicNo);
		} 
		catch (Exception e) {
			e.printStackTrace();
			//GLOGGER.error("Exception Occurred in getMonthsbetween() in ChronicOPServiceImpl class."+e.getMessage());	
		}
		return diff;
	}
	
	public String saveChronicInstallment(ChronicOPVO ChronicOpVO) throws Exception
	{
		String msg=null;
		try {
			msg=ChronicOpDao.saveChronicInstallment(ChronicOpVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
			//GLOGGER.error("Exception Occurred in saveChronicInstallment() in ChronicOPServiceImpl class."+e.getMessage());	
		}
		return msg;
	}
	public boolean getUserPkgCode(String pkgCode,String cardNo)
	{
		boolean status=false;
		try {
			status=ChronicOpDao.getUserPkgCode(pkgCode,cardNo);
		} 
		catch (Exception e) {
			e.printStackTrace();
			//GLOGGER.error("Exception Occurred in getUserPkgCode() in chronicOpServiceImpl class."+e.getMessage());	
		}
		return status;
	}
	
	public boolean cancelCase(String chronicId,String chronicNo,String user)
	{
		boolean status=false;
		try {
			status=ChronicOpDao.cancelCase(chronicId,chronicNo,user);
		} 
		catch (Exception e) {
			e.printStackTrace();
			//GLOGGER.error("Exception Occurred in cancelCase() in chronicOpServiceImpl class."+e.getMessage());	
		}
		return status;
	}
	public Number getDaysBetween(String chronicId,String chronicNo)
	{
		Number  diff=null;
		try {
			diff=ChronicOpDao.getDaysBetween(chronicId, chronicNo);
		} 
		catch (Exception e) {
			e.printStackTrace();
			//GLOGGER.error("Exception Occurred in getDaysBetween() in chronicOpServiceImpl class."+e.getMessage());	
		}
		return diff;
		
	}
	
	public String setviewFlag(String pStrCaseId, String pStrFlag, String lStrEmpId)
	{
		String msg = null;
		Date date = new Date();
		try
		{
			EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, pStrCaseId);
			if(ehfCase != null)
			{
				ehfCase.setViewFlag(pStrFlag);	
				ehfCase.setCase_blocked_dt(new java.sql.Timestamp(date.getTime()));
				ehfCase.setCaseBlockedUsr(lStrEmpId);
				genericDao.save(ehfCase);
			}
			msg ="success";
		}catch(Exception e)
		{
			msg ="failure";
			e.printStackTrace();
		}
		return msg;
	}
	
	
	
	@Override
	public String getTimeOutCount(String caseId1, List<LabelBean> groupsList, String lStrModule) {
		List<LabelBean> caseList = new ArrayList<LabelBean>();
		String timer = "0";
		  SessionFactory factory=hibernateTemplate.getSessionFactory();
		  Session session = factory.openSession();
		try{
		  StringBuffer query = new StringBuffer();
		  query.append(" select nvl(max(b.timer),0) as ID from EhfCaseTherapy a,EhfmTherapyGrpTimeout b  where a.asriCatCode=b.id.specialityCode and a.icdProcCode=b.id.icdProcCode ");
		  query.append(" and a.activeyn='Y' and a.caseId='"+caseId1+"' and b.id.moduleType='"+lStrModule+"' and b.id.grpId in (");
		  for(int k=0; k<groupsList.size() ;k++)
			 {
			   if(k!=0 && k!=groupsList.size())
			     {
				   query.append(" , ");  
			     }
			   query.append(" '"+groupsList.get(k).getID()+"' ");	
			 }
		  query.append(" ) ");  
		  caseList=session.createQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(LabelBean.class)).list();
		  if(caseList!=null && caseList.size()>0){
			  timer = caseList.get(0).getID();
		  }
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		finally
		{
			session.close();
			factory.close();
		}
		return timer;
	}
	
	
	public String getCaseStatusForCase(String caseId)
	{
		String lStrCaseStatus = ""; 
		EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, caseId);
		if(ehfCase!=null){
			lStrCaseStatus = ehfCase.getCaseStatus();
		}
		return lStrCaseStatus;	
	}
	
	public long getPackageDrugAmount(ChronicOPVO  chronicOPVO)
	{
		long drugAmt=0;
		drugAmt=ChronicOpDao.getPackageDrugAmount(chronicOPVO);
		return drugAmt;
	}
	public String getMedcoScheme(String userId)
	{
		String userScheme=null;
		userScheme=ChronicOpDao.getMedcoScheme(userId);
		return userScheme;
	}
	public float calculateDrugAmount(DrugsVO  drugsVO)
	{
		float drugAmt=0;
		drugAmt=ChronicOpDao.calculateDrugAmount(drugsVO);
		return drugAmt;
		
	}
	/*@Override
	public List<String> getRouteList(String actCode) {
		List<LabelBean> routeList=null;
		List<String> routeList1 = new ArrayList<String>();
		try
		{
			routeList=ChronicOpDao.getRouteList(actCode);
			 for (LabelBean labelBean: routeList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (routeList1 != null && routeList1.size() > 0) {
	                    	routeList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	routeList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			GLOGGER.error("Exception Occurred in getDrugSubList() in ChronicOPServiceImpl class."+e.getMessage());
		}
		return routeList1;
	}
	@Override
	public List<String> getStrengthList(String actCode) {
		List<LabelBean> strengthList=null;
		List<String> strengthList1 = new ArrayList<String>();
		try
		{
			strengthList=ChronicOpDao.getStrengthList(actCode);
			 for (LabelBean labelBean: strengthList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (strengthList1 != null && strengthList1.size() > 0) {
	                    	strengthList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	strengthList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			GLOGGER.error("Exception Occurred in getStrengthList() in ChronicOPServiceImpl class."+e.getMessage());
		}
		return strengthList1;
	}*/
	
	/*Added by venkatesh to fetch Server Date */
	public String getCreatedDt(String format) throws Exception {		
		String createdDts = null;
			try{
			StringBuffer query = new StringBuffer();
			query.append(" select TO_CHAR(SYSDATE,?) as SERVERDT from dual");
	    	String args[]=new String[1];
	    	args[0]=format;
			List<PreauthClinicalNotesVO> crtDt= genericDao.executeSQLQueryList(PreauthClinicalNotesVO.class,query.toString(),args);
		        if(crtDt!=null && crtDt.size()>0){
		        	createdDts= crtDt.get(0).getSERVERDT();
		        }			
			}
			
			catch(Exception e){
				e.printStackTrace();
			}
		    return createdDts;
		}
	
	public List<LabelBean> getChronicStatus() 
	{
		 List<LabelBean> chronicStat=new ArrayList<LabelBean>();
		  chronicStat=ChronicOpDao.getChronicStatus();
		  return chronicStat;
	}
	
	public String updateSentBackClaims(ChronicOPVO  chronicOPVO)
	{
		
		String msg=null;
		msg=ChronicOpDao.updateSentBackClaims(chronicOPVO);
		return msg;
	}
	

	public long getCasePackageAmount(String chronicId,String chronicNo)
	{
		long pkgAmt=0;
		pkgAmt=ChronicOpDao.getCasePackageAmount( chronicId, chronicNo);
		return pkgAmt;
	}
	

	/*
	 * Added by Srikalyan to get The Case/Patient Scheme
	 */
	public String getChronicScheme(String chronicId,String chronicNo)
		{
			String schemeId=null;
			StringBuffer query=new StringBuffer();
			try
				{
					query.append(" select a.schemeId as schemeId ");
					query.append(" from EhfChronicPatientDtl a where ");
					/*if(chronicNo!=null && !"".equalsIgnoreCase(chronicNo))
						query.append(" a.id.chronicNo ='"+chronicNo+"' ");*/
					if(chronicId!=null && !"".equalsIgnoreCase(chronicId))
						query.append(" a.id.chronicId ='"+chronicId+"' ");
					query.append(" order by crtDt desc ");
					
					List<ChronicOPVO> voLst=genericDao.executeHQLQueryList(ChronicOPVO.class,query.toString());
					if(voLst!=null)
						{
							if(voLst.size()>0 && voLst.get(0)!=null)
								{
									if(voLst.get(0).getSchemeId()!=null)
										schemeId=voLst.get(0).getSchemeId();
								}
						}
				}
			catch(Exception e)
				{
					e.printStackTrace();
					//GLOGGER.error("Exception Occurred in getChronicScheme() in ChronicOPServiceImpl class."+e.getMessage());
				}
			return schemeId;
			
		}
	
}
