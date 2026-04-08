package com.ahct.annualCheckUp.service;

import java.util.List;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfAnnualPatientDtls;
import com.ahct.annualCheckUp.vo.AnnualCheckUpVo;
import com.ahct.annualCheckUp.dao.AnnualCheckUpDAO;
import com.ahct.model.EhfAhcMedicalReport;
import com.ahct.model.EhfAhcPatientTest;
import com.ahct.model.EhfAnnualCaseDtls;
import com.ahct.model.EhfAnnualPatientDtls;
import com.ahct.patient.vo.PatientVO;
import com.tcs.framework.configuration.ConfigurationService;
import org.apache.commons.configuration.CompositeConfiguration;

public class AnnualCheckUpServiceImpl implements AnnualCheckUpService

{
	private final static Logger GLOGGER = Logger.getLogger ( AnnualCheckUpServiceImpl.class ) ;
	AnnualCheckUpDAO annualCheckUpDAO;
	
	 public AnnualCheckUpDAO getAnnualCheckUpDAO() {
		return annualCheckUpDAO;
	}
	public void setAnnualCheckUpDAO(AnnualCheckUpDAO annualCheckUpDAO) {
		this.annualCheckUpDAO = annualCheckUpDAO;
	}
	
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;
	static
		{	
			configurationService=ConfigurationService.getInstance();
			config=configurationService.getConfiguration();
		}
	 /* Sets the configuration service.
	 * 
	 * @param configurationService
	 *            the new configuration service
	 */
	public static void setConfigurationService(
			ConfigurationService configurationService) {
		AnnualCheckUpServiceImpl.configurationService = configurationService;
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
			relationsList=annualCheckUpDAO.getRelations();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return relationsList;
		
	}
		/**
	     * ;
	     * @param AnnualCheckUpVo annualCheckUpVo
	     * @return AnnualCheckUpVo
	     * @function This Method is Used For Retrieving Enrollment Details of Employee card no
	     */
		@Override
		public AnnualCheckUpVo retrieveCardDetails(AnnualCheckUpVo annualCheckUpVo)
		{
			AnnualCheckUpVo annualCheckUpVo1=null;
			try
			{
				annualCheckUpVo1=annualCheckUpDAO.retrieveCardDetails(annualCheckUpVo);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return annualCheckUpVo1;
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
				hospitalList = annualCheckUpDAO.getHospital(userId,roleId);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return hospitalList;
		}
		
		@Override
		public String getHospActiveStatus(String userId, String roleId)
		{
			String actStatus="";
			try
			{
				actStatus=annualCheckUpDAO.getHospActiveStatus(userId, roleId);
			}
			catch(Exception e)
			{
				GLOGGER.error("Error in method getHospActiveStatus() of AnnualCheckUpServiceimpl "+e.getMessage());
			}
			return actStatus;
		}
		
		
		@Override
		public String getSequence(String pStrSeqName)throws Exception{
			String seqId = annualCheckUpDAO.getSequence(pStrSeqName);
			return seqId;
		}
		
		/**
	     * ;
	     * @param annualCheckUpVo
	     * @return int noOfRecords
	     * @function This Method is Used For Registering Direct Patient
	     */
		@Override
		public int registerPatient(AnnualCheckUpVo annualCheckUpVo) 
		{
			int i=0;
			try {
				i = annualCheckUpDAO.registerPatient(annualCheckUpVo);
			}
			catch (Exception e) {
				e.printStackTrace();
				GLOGGER.error("Exception Occurred in registerPatient() in AnnualCheckUpServiceimpl class."+e.getMessage());
			}
			return i;
			
		}
		
/*		*//**
	     * ;
	     * @param annualCheckUpVo
	     * @return int noOfRecords
	     * @function This Method is Used For Registering Direct Patient Case
	     *//*
		@Override
		public int registerPatientCase(AnnualCheckUpVo annualCheckUpVo) throws Exception 
		{
			int i=0;
			try {
				i = annualCheckUpDAO.registerPatientCase(annualCheckUpVo);
			}
			catch (Exception e) {
				e.printStackTrace();
				GLOGGER.error("Exception Occurred in registerPatientCase() in AnnualCheckUpServiceimpl class."+e.getMessage());
			}
			return i;
			
		}*/
		
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
				hospitalName=annualCheckUpDAO.getHospName(hospId);
			} 
			catch (Exception e)
			{
				GLOGGER.error("Exception Occurred in getHospName() in AnnualCheckUpServiceimpl class."+e.getMessage());
			}
			return hospitalName;
		}
	@Override
	public List<AnnualCheckUpVo> getAnnualPatientDtls(String hospId,String roleId,String userState) {
	
		
		List<AnnualCheckUpVo> list=null;
		list=annualCheckUpDAO.getAnnualPatientDtls(hospId, roleId,userState);
		
		return list;
	}
	
	/**
     * ;
     * @param String ahcId
     * @return EhfAnnualPatientDtls ehfAnnualPatientDtls
     * @function This Method is Used For retrieving PatientDetails for given chronicId
     */
	@Override
	public EhfAnnualPatientDtls getPatientDetails(String ahcId) {
		EhfAnnualPatientDtls ehfAnnualPatientDtls=null;
		try 
		{
			ehfAnnualPatientDtls=(EhfAnnualPatientDtls)annualCheckUpDAO.getPatientDetails(ahcId);
		} 
		catch (Exception e) 
		{
			GLOGGER.error("Exception Occurred in getPatientDetails() in AnnualCheckUpServiceimpl class."+e.getMessage());
		}
		return ehfAnnualPatientDtls;
	}
	@Override
	public EhfAnnualPatientDtls getPatientDtls(String patienId) {
		// TODO Auto-generated method stub
		EhfAnnualPatientDtls epd =annualCheckUpDAO.getPatientDtls(patienId);
		
		return epd;
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
			locationName=annualCheckUpDAO.getLocationName(locId);
		} 
		catch (Exception e)
		{
			GLOGGER.error("Exception Occurred in getLocationName() in AnnualCheckUpServiceimpl class."+e.getMessage());
		}
		return locationName;
	}
	@Override
	public String saveAhcDetails(AnnualCheckUpVo annualCheckupVo) {
		String ahcId= annualCheckUpDAO.saveAhcDetails(annualCheckupVo);
		return ahcId;
	}
	@Override
	public List<AnnualCheckUpVo> searchAnnualPatientDtls(String patID,
			String PatName, String healthCardNo, String state, String district,
			String fromDate, String toDate, String user,String hospId,String roleId) {
		
		List<AnnualCheckUpVo> annList=null;
		annList=annualCheckUpDAO.searchAnnualPatientDtls(patID, PatName, healthCardNo, state, district, fromDate, toDate,user,hospId,roleId);

		
		return annList;
		
	}
	
	 /**
     * ;
     * @param String relationId
     * @return String relationName
     * @function This Method is Used For getting relationName for given relationId from RelationMst table
     */
	@Override

	public List<AnnualCheckUpVo> getAHCClaimCases(String hospId,String roleId,String userState,String caseSearchFlag,String status) {
		
		
List<AnnualCheckUpVo> annList=annualCheckUpDAO.getAHCClaimCases(hospId,roleId,userState,caseSearchFlag);
		
		
		return annList;
		
	}
	@Override
	public AnnualCheckUpVo getOtherTestDetails(String ahcId) {
		AnnualCheckUpVo testDtlsVo = annualCheckUpDAO.getOtherTestDetails(ahcId);
		return testDtlsVo;
	}
	@Override
	public EhfAnnualCaseDtls getCaseDetails(String ahcId) {
		EhfAnnualCaseDtls epd =annualCheckUpDAO.getAhcCaseDtls(ahcId);
		
		return epd;
	}

	public String getRelationName(String relationId) {
		String relationName=null;
		try
		{
			relationName=annualCheckUpDAO.getRelationName(relationId);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getRelationName() in AnnualCheckUpServiceimpl class."+e.getMessage());
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
	public String getPatientPhoto(String cardNo) {
		String photoUrl=null;
		try
		{
			photoUrl=annualCheckUpDAO.getPatientPhoto(cardNo);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getPatientPhoto() in AnnualCheckUpServiceimpl class."+e.getMessage());
		}
		return photoUrl;
	}
	@Override
	public List<AnnualCheckUpVo> searchAnnualPatientDtls(String patID,
			String PatName, String healthCardNo, String state, String district,
			String fromDate, String toDate, String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnnualCheckUpVo getFindings(String ahcId) {
		// TODO Auto-generated method stub
		AnnualCheckUpVo findings = annualCheckUpDAO.getFindings(ahcId);
		return findings;
	}
	@Override
	public List<AnnualCheckUpVo> searchAnnualClaimDtls(String patID,
			String PatName, String healthCardNo, String state, String district,
			String fromDate, String toDate, String hospId, String roleId,String userState,String caseSearchFlag,String status) {
		
		
		List<AnnualCheckUpVo> list=null;
		list=annualCheckUpDAO.searchAnnualClaimDtls(patID, PatName, healthCardNo, state, district, fromDate, toDate, hospId, roleId,userState, caseSearchFlag, status);
		return list;

	}
	@Override
	public EhfAhcMedicalReport getOverallReport(String ahcId) {
		// TODO Auto-generated method stub
		EhfAhcMedicalReport report = annualCheckUpDAO.getOverallReport(ahcId);
		/*StringBuffer result=new StringBuffer();
		result.append(report.getHaemoglobinLevel()+"~"+report.getBloodSugarLevel()+"~");
		result.append(report.getCholesterolLevel()+"~"+report.getLiverFunctioning()+"~");
		result.append(report.getKidneyStatus()+"~"+report.getCardiacStatus()+"~");*/
		return report;
	}
	@Override
	public AnnualCheckUpVo getPatientFullDtls(String patienId) {
		// TODO Auto-generated method stub
		AnnualCheckUpVo fullDtls=annualCheckUpDAO.getPatientFullDtls(patienId);
		return fullDtls;
	}
	
	
	/**
	 * @param AHCID
	 * @author Kalyan
	 * @return List of LabelBean containing general Investigations 
	 * @function To get the General Investigations for AHC Patient
	 */
	@Override
	public List<LabelBean> getGeneralInvest(String patientId)
		{
			List<LabelBean> returnLst=new ArrayList<LabelBean>();
			try
				{
					if(patientId!=null)
						{
							EhfAnnualPatientDtls patientClassObj=annualCheckUpDAO.getAHCPatRec(patientId);
							if(patientClassObj!=null)
								{
									String gender=null,genderBoth=null;
									if(patientClassObj.getGender()!=null && !"".equalsIgnoreCase(patientClassObj.getGender())
										&& patientClassObj.getSchemeId()!=null && !"".equalsIgnoreCase(patientClassObj.getSchemeId()))
										{
											
											if(patientClassObj.getGender().equalsIgnoreCase("M"))
												gender=config.getString("MaleStat");
											else if(patientClassObj.getGender().equalsIgnoreCase("F"))
												gender=config.getString("FemaleStat");
											
											if(config.getString("bothGender")!=null)
												genderBoth=config.getString("bothGender");
											
											StringBuffer query=new StringBuffer();
											query.append( " select a.investigationName as VALUE  , a.price as CONST,a. testId as  ID " );
											query.append( " from EhfmAhcInvestigationDtl a  " );
											query.append( " where a.gender in ('"+gender+"','"+genderBoth+"') and a.activeYn='Y' " );
											query.append( " and a.schemeId='"+patientClassObj.getSchemeId()+"' " );
											
											query.append( " order by a.investigationName " );
											
											returnLst=annualCheckUpDAO.executeHQLQuery(query.toString());
										}
								}
						}
				}
			catch(Exception e)
				{
					e.printStackTrace();
					GLOGGER.error("Exeption occurred in getGeneralInvest method of AnnualCheckUpServiceImpl Class"+e.getMessage());
					return returnLst;
				}
			return returnLst;
		}
	
	/**
	 * @author Kalyan
	 * @param patientId
	 * @return List of EhfAhcPatientTest containing AHC Attachments Details
	 */
	public List<EhfAhcPatientTest> getAttachDtls(String patientId)
		{
			List<EhfAhcPatientTest> attachList=new ArrayList<EhfAhcPatientTest>();
			if(patientId !=null && !"".equalsIgnoreCase(patientId))
				{
					try
						{
							StringBuffer query=new StringBuffer();
							query.append("  from EhfAhcPatientTest where patientId = '"+patientId+"' order by attachPath,crtDt " );
							
							 attachList = annualCheckUpDAO.executeAttachQuery(query.toString());
						}
					catch(Exception e)
						{		
							e.printStackTrace();
							GLOGGER.error("Exception occured in getAttachDtls method of AnnualCheckUpServiceImpl class"+e.getMessage());
							return attachList;
						}	
				}
			return attachList;
		}
	
	@Override
	public List<LabelBean> getConsultationDtls(String patientId)
	{
		
		return annualCheckUpDAO.getConsultationDtls(patientId);
		
	}
	
	/**
	 * @author Kalyan
	 * @param AHCID
	 * @return Check 14 Days Condition for AHC Registered Cases
	 */
	public String checkRegDaysCond(String ahcId)
		{
			String cond="N";
			StringBuffer query = new StringBuffer();
			
			if(ahcId!=null && !"".equalsIgnoreCase(ahcId))
				{
					try
						{
							List<LabelBean> ahcRecList = new ArrayList<LabelBean>();
							query.append( " select a.ahcId as ID , a.regHospDate as REGNDATE " );
							query.append( " , case when (sysdate-reg_hosp_date) > 14 then 'N' " );
							query.append( "    	   else 'Y' " );
							query.append( "   end  as CONST " );
							query.append( " from EhfAnnualPatientDtls a " );
							query.append( " where a.ahcId = '"+ahcId+"' " );
							
							ahcRecList = annualCheckUpDAO.executeHQLQuery(query.toString());
							if(ahcRecList!=null && ahcRecList.size()>0 && ahcRecList.get(0)!=null
									&& ahcRecList.get(0).getCONST()!=null && 
									!"".equalsIgnoreCase(ahcRecList.get(0).getCONST()))
								{
									cond = ahcRecList.get(0).getCONST();
								}
						}
					catch(Exception e)
						{
							e.printStackTrace();
							GLOGGER.error("Exception occured in checkRegDaysCond method of AnnualCheckUpServiceImpl class"+e.getMessage());
							return cond;
						}
				}
			return cond;
			
		}
	@Override
	public boolean uploadAttachments(List<AnnualCheckUpVo> ahcTestLst,String ahcId) {
		return annualCheckUpDAO.uploadAttachments( ahcTestLst,ahcId);
		
	}
	/**
	 * @author Kalyan
	 * @return list of All AHC Case status
	 */
	@Override
	public List<LabelBean> getAhcStatus()
		{
			String ahcHeaderId=config.getString("AHC.CmbHdrId");
			StringBuffer query = new StringBuffer();
			List<LabelBean> ahcRecList =new ArrayList<LabelBean>();
			
			if(ahcHeaderId!=null && !"".equalsIgnoreCase(ahcHeaderId))
				{
					try
						{
							query.append( " select a.cmbDtlId as ID , a.cmbDtlName as VALUE " );
							query.append( " from EhfmCmbDtls a " );
							query.append( " where a.cmbHdrId = '"+ahcHeaderId+"' " );
							query.append( " order by a.cmbDtlName ");
							
							ahcRecList = annualCheckUpDAO.executeHQLQuery(query.toString());
							
						}
					catch(Exception e)
						{
							e.printStackTrace();
							GLOGGER.error("Exception occured in getAhcStatus method of AnnualCheckUpServiceImpl class"+e.getMessage());
							return ahcRecList;
						}
				}
			return ahcRecList;
		}
	}
