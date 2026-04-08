package com.ahct.annualCheckUp.dao;

import java.util.List;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfAnnualPatientDtls;
import com.ahct.annualCheckUp.vo.AnnualCheckUpVo;
import com.ahct.model.EhfAhcMedicalReport;
import com.ahct.model.EhfAnnualCaseDtls;
import com.ahct.model.EhfAhcPatientTest;


public interface AnnualCheckUpDAO 
{
	List<AnnualCheckUpVo> getAnnualPatientDtls(String  hospId,String roleId,String userState);
	public EhfAnnualPatientDtls getPatientDtls(String patienId);
	public String saveAhcDetails(AnnualCheckUpVo annualCheckUpVo);
	List<AnnualCheckUpVo> searchAnnualPatientDtls(String patID,String PatName,String healthCardNo,String state,String district,String fromDate,String toDate,String user,String hospId,String roleId);
	/**
     * ;
     * @return List<LabelBean> relationList
     * @function This Method is Used For getting Relations List from RelationMst table
     */
    public List<LabelBean> getRelations() throws Exception;
	/**
     * ;
     * @param AnnualCheckUpVo annualCheckUpVo
     * @return AnnualCheckUpVo
     * @function This Method is Used For Retrieving Enrollment Details of Employeecard no
     */
	public AnnualCheckUpVo retrieveCardDetails(AnnualCheckUpVo annualCheckUpVo) throws Exception;
	/**
     * ;
     * @param String payGrade
     * @return String slab
     * @function This Method is Used For Retrieving Slab type for given payGrade
     */
	public String getSlabType(String payGrade)throws Exception;
	/**
     * ;
     * @param String userId
     * @param String roleId
     * @return List<LabelBean> hospitalList
     * @function This Method is Used For getting Hospital List for given user and role
     */
    public List<LabelBean> getHospital(String userId,String roleId)throws Exception;
    
    
    public String getHospActiveStatus(String userId, String roleId);
    
    
    public String getSequence(String pStrSeqName) throws Exception;
    
    
	/**
     * ;
     * @param PatientVO patientVO
     * @return int noOfRecords
     * @function This Method is Used For Registering Direct Patient
     */
	public int registerPatient(AnnualCheckUpVo annualCheckUpVo)throws Exception;
	
	   /**
     * ;
     * @param String hospId
     * @return String hospName
     * @function This Method is Used For getting Hospital Name
     */
    public String getHospName(String hospId)throws Exception;
    
   /* *//**
     * ;
     * @param AnnualCheckUpVo annualCheckUpVo
     * @return int noOfRecords
     * @function This Method is Used For Registering Direct Patient Case
     *//*
	public int registerPatientCase(AnnualCheckUpVo annualCheckUpVo) throws Exception ;*/
    
    
    
	 /**
	  * ;
	  * @param String ahcId
	  * @return EhfAnnualPatientDtls ehfAnnualPatientDtls
	  * @function This Method is Used For retrieving PatientDetails for given ahcId
	  */
	public EhfAnnualPatientDtls getPatientDetails(String ahcId) throws Exception;
	

	public AnnualCheckUpVo getOtherTestDetails(String ahcId);

	List<AnnualCheckUpVo> getAHCClaimCases(String hospId,String roleId,String userState,String caseSearchFlag);

	public EhfAnnualCaseDtls getAhcCaseDtls(String ahcId);
	List<AnnualCheckUpVo> searchAnnualClaimDtls(String patID,String PatName,String healthCardNo,String state,String district,String fromDate,String toDate,String hospId,String roleId,String userState,String caseSearchFlag,String status);
	
   /**
    * ;
    * @param String relationId
    * @return String relationName
    * @function This Method is Used For getting relationName for given relationId from RelationMst table
    */
   public String getRelationName(String relationId) throws Exception;
   /**
    * ;
    * @param String cardNo
    * @return String photoUrl
    * @function This Method is Used For getting photoUrl for given cardNo
    */
   public String getPatientPhoto(String cardNo)throws Exception;
   /**
    * ;
    * @param String locId
    * @return String locName
    * @function This Method is Used For getting Location Name for given Location Id
    */
   public String getLocationName(String locId)throws Exception;
   public AnnualCheckUpVo getFindings(String ahcId);
   public EhfAhcMedicalReport getOverallReport(String ahcId);
   public AnnualCheckUpVo getPatientFullDtls(String patienId);
   
   /**
    * ;
    * @param String AHC ID of the Patient
    * @return String EhfAnnualPatientDtls Object
    * @author Kalyan
    * @function This Method is Used For getting patient object of AHC
    */
   public EhfAnnualPatientDtls getAHCPatRec(String patientId);

   /**
    * ;
    * @param String query to Execute
    * @return List of LabelBean
    * @author Kalyan
    * @function This Method is Used For Executing a HQL Query   
    */
   public List<LabelBean> executeHQLQuery(String query);
   
   /**
    * ;
    * @param String query to Execute
    * @return List of EhfAhcPatientTest
    * @author Kalyan
    * @function This Method is Used For Executing a HQL 
    * Query and return List of EhfAhcPatientTest     
    */
   public List<EhfAhcPatientTest> executeAttachQuery(String query);
   public List<LabelBean> getConsultationDtls(String patientId);
public boolean uploadAttachments(List<AnnualCheckUpVo> ahcTestLst, String ahcId);
   
}
