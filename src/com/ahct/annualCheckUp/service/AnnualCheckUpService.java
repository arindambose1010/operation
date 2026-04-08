package com.ahct.annualCheckUp.service;

import java.util.List;

import com.ahct.model.EhfAhcMedicalReport;
import com.ahct.model.EhfAhcPatientTest;
import com.ahct.model.EhfAnnualCaseDtls;
import com.ahct.annualCheckUp.vo.AnnualCheckUpVo;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfAnnualPatientDtls;
import com.ahct.annualCheckUp.vo.AnnualCheckUpVo;;

public interface AnnualCheckUpService 
{
	/**
     * ;
     * @return List<LabelBean> relationList
     * @function This Method is Used For getting Relations List from RelationMst table
     */
    public List<LabelBean> getRelations();
    /**
     * ;
     * @param AnnualCheckUpVo annualCheckUpVo
     * @return annualCheckUpVo
     * @function This Method is Used For Retrieving Enrollment Details of Employee card no
     */
    public AnnualCheckUpVo retrieveCardDetails(AnnualCheckUpVo annualCheckUpVo);
    
    /**
     * ;
     * @param AnnualCheckUpVo annualCheckUpVo
     * @return int noOfRecords
     * @function This Method is Used For Registering Direct Patient
     */
	public int registerPatient(AnnualCheckUpVo annualCheckUpVo);
	
/*    *//**
     * ;
     * @param AnnualCheckUpVo annualCheckUpVo
     * @return int noOfRecords
     * @function This Method is Used For Registering Direct Patient Case
     *//*
	public int registerPatientCase(AnnualCheckUpVo annualCheckUpVo) throws Exception ;*/
    
	/**
     * ;
     * @param String hospId
     * @return String hospName
     * @function This Method is Used For getting Hospital Name
     */
	public String getHospName(String hospId);
	public EhfAnnualPatientDtls getPatientDtls(String patienId);
	public String saveAhcDetails(AnnualCheckUpVo annualCheckupVo);
	List<AnnualCheckUpVo> searchAnnualPatientDtls(String patID,String PatName,String healthCardNo,String state,String district,String fromDate,String toDate,String user);
	


	List<AnnualCheckUpVo> searchAnnualPatientDtls(String patID,String PatName,String healthCardNo,String state,String district,String fromDate,String toDate,String user,String hospId,String roleId);

	List<AnnualCheckUpVo> searchAnnualClaimDtls(String patID,String PatName,String healthCardNo,String state,String district,String fromDate,String toDate,String hospId,String roleId,String userState,String caseSearchFlag,String status);
	/**
     * ;
     * @param String userId
     * @param String roleId
     * @return List<LabelBean> hospitalList
     * @function This Method is Used For getting Hospital List for given user and role
     */
	public List<LabelBean> getHospital(String userId,String roleId);
	
	public String getHospActiveStatus(String userId, String roleId);
	
	public String getSequence(String pStrSeqName) throws Exception;
	
	List<AnnualCheckUpVo> getAnnualPatientDtls(String hospId,String roleId,String userState);
	

	List<AnnualCheckUpVo> getAHCClaimCases(String hospId,String roleId,String userState,String caseSearchFlag,String status);

	 /**
	  * ;
	  * @param String ahcId
	  * @return EhfAnnualPatientDtls ehfAnnualPatientDtls
	  * @function This Method is Used For retrieving PatientDetails for given ahcId
	  */
	public EhfAnnualPatientDtls getPatientDetails(String ahcId) throws Exception;

	
	 /**
    * ;
    * @param String relationId
    * @return String relationName
    * @function This Method is Used For getting relationName for given relationId from RelationMst table
    */
   public String getRelationName(String relationId);
   
	/**
    * ;
    * @param String locId
    * @return String locName
    * @function This Method is Used For getting Location Name for given Location Id
    */
	public String getLocationName(String locId);
	
	public AnnualCheckUpVo getOtherTestDetails(String ahcId);
	public EhfAnnualCaseDtls getCaseDetails(String ahcId);
	
   /**
    * ;
    * @param String cardNo
    * @return String photoUrl
    * @function This Method is Used For getting photoUrl for given cardNo
    */
   public String getPatientPhoto(String cardNo);
   public AnnualCheckUpVo getFindings(String ahcId);
   public EhfAhcMedicalReport getOverallReport(String ahcId);
public AnnualCheckUpVo getPatientFullDtls(String patienId);

	/**
	 * @param AHCID
	 * @author Kalyan
	 * @return List of LabelBean containing general Investigations 
	 * @function To get the General Investigations for AHC Patient
	 */
	public List<LabelBean> getGeneralInvest(String patienId);
	
	/**
	 * @author Kalyan
	 * @param patientId
	 * @return List of LabelBean containing AHC Attachments Details
	 */
	public List<EhfAhcPatientTest> getAttachDtls(String patientId);
	
	/**
	 * @author Kalyan
	 * @param AHCID
	 * @return Check 14 Days Condition for AHC Registered Cases
	 */
	public String checkRegDaysCond(String patientId);
	public List<LabelBean> getConsultationDtls(String patientId);
	public boolean uploadAttachments(List<AnnualCheckUpVo> ahcTestLst,String ahcId);
	
	/**
	 * @author Kalyan
	 * @return list of All AHC Case status
	 */
	public List<LabelBean> getAhcStatus();
}
