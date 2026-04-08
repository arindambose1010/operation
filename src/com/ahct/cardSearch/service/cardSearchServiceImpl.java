package com.ahct.cardSearch.service;

import java.util.ArrayList;
import java.util.List;












import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ahct.cardSearch.vo.cardSearchVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfmHODMst;
import com.tcs.framework.persistanceConfiguration.GenericDAO;

public class cardSearchServiceImpl implements cardSearchService
	{
	HibernateTemplate hibernateTemplate;
	GenericDAO genericDao;
	public GenericDAO getGenericDao() {
			return genericDao;
		}
		public void setGenericDao(GenericDAO genericDao) {
			this.genericDao = genericDao;
		}
		
		public HibernateTemplate getHibernateTemplate() {
			return hibernateTemplate;
		}
		public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
			this.hibernateTemplate = hibernateTemplate;
		}
		@Override
		public List<cardSearchVO> getCardStatusList(String empRadio,String cardNo) 
		{
			List<cardSearchVO> cardDtls = null;
			StringBuffer query = new StringBuffer();
			try{
				if(empRadio!=null && "J".equalsIgnoreCase(empRadio))
				{
					query.append("select ef.name as NAME,to_char(ef.dob) as DATEOFBIRTH,ef.gender as GENDER,rm.relation_name as RELATION,");
					query.append("ef.journal_card_no as CARDNUMBER,ef.photo as PHOTO,ef.journal_Enroll_Id as AADHARID,to_char(ef.journal_Enroll_Sno) || '' as  FLAG from ehf_jrnlst_enrollment ee,");
					query.append("ehf_jrnlst_family ef,ehfm_relation_mst rm,ehfm_jrnlst j where ef.journal_card_no like ('%"+cardNo.toUpperCase()+"%') ");
					query.append("and j.login_name=ee.journal_code and ee.journal_enroll_prnt_id=ef.journal_enroll_prnt_id ");
					query.append("and ee.scheme='CD202' and ef.card_valid='Y' and ef.relation=rm.relation_id order by ef.relation ");
					
					cardDtls = genericDao.executeSQLQueryList(cardSearchVO.class,query.toString());
				}
				else if(empRadio!=null && "E".equalsIgnoreCase(empRadio))
				{
					System.out.println("In employee");
					query.append("select distinct f.enroll_name as NAME,to_char(f.enroll_dob) as DATEOFBIRTH,f.enroll_gender as GENDER,");
					query.append("rm.relation_name as RELATION,f.ehf_card_no as CARDNUMBER,to_char(f.enroll_sno) || '' as FLAG,f.enroll_id as AADHARID,");
					query.append("(case when f.enroll_sno != '0' then f.enroll_photo when f.enroll_sno = '0' and d.attach_type = 'CD3002' ");
					query.append("and d.attch_actve_yn = 'Y' then d.saved_name else null end) as PHOTO from ehf_enrollment_family f, ehf_employee_doc_attach d,ehfm_relation_mst rm,");
					query.append(" ehf_enrollment e where f.enroll_prnt_id = d.employee_prnt_id and e.enroll_prnt_id = f.enroll_prnt_id and e.scheme='CD202' and  ");
					query.append(" f.ehf_card_no like ('%"+cardNo.toUpperCase()+"%') and d.attach_type='CD3002' and rm.relation_id=f.enroll_relation order by f.enroll_id");
					
					
					cardDtls = genericDao.executeSQLQueryList(cardSearchVO.class,query.toString());
					
				}
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
				
			return cardDtls;
		}
		@Override
		public String getUsrId(String cardNo)
		{
			String userId=null;
			try{
				StringBuffer query = new StringBuffer();
				query.append("select distinct j.user_id as USERID from ehfm_jrnlst j,ehf_jrnlst_enrollment e, ");
				query.append("ehf_jrnlst_family ef where ef.journal_enroll_prnt_id=e.journal_enroll_prnt_id ");
				query.append("and e.journal_code=j.login_name and ef.journal_card_no like '%"+cardNo.toUpperCase()+"%' ");
				List<cardSearchVO> user = genericDao.executeSQLQueryList(cardSearchVO.class,query.toString());
				if(user.size()>0)
				{
					userId=user.get(0).getUSERID();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return userId;
		}
		@Override
		public LabelBean getCardDetails(String usrId, String lStrEnrolId,String lStrSeqNo,String empRadio) 
		{

			
			StringBuffer query = new StringBuffer();
			
			
			
			//Long lSeqNo=Long.parseLong(pStrSeqNo);
			try{
				if(empRadio!=null && "J".equalsIgnoreCase(empRadio))
				{
					if(lStrSeqNo!=null && "0".equalsIgnoreCase(lStrSeqNo))
					{
					query.append(" SELECT INITCAP(EFEF.enrollName) as NEWEMPCODE,EFE.homeEmail as HOSPEMAIL,EFE.homeMobileNo as MOBILENO,EFE.accCardNo as disMainId,EFE.scheme as SCHEMENAME,EFEF.aadharId as AADHARID,EFEF.aadharEid as AADHAREID,AU.loginName as EMPID,INITCAP(EFEF.enrollName) as EMPNAME,EFEF.journalCradNo as ID,AC.cmbDtlName as VALUE,EFEF.journalEnrollParntId as JOBID,to_char(EFEF.enrollDob,'dd/mm/YYYY') as REMARKS,EFEF.enrollGender as TYPE,0 as COUNT,EFE.ofcDistrict as POSTCODE,EML.locName as DISTRICTNAME,EFEF.enrollPhoto as PATH,EML.locShrtName as CUGNUM,AMC.cmbDtlName as flagName ");
					query.append(" FROM EhsJournalistEnrollment EFE ,EhfJrnlstFamily EFEF,EhfmJournalist AU,EhfmCmbDtls AC,EhfmLocations EML,AsrimCombo AMC  ");
					query.append(" where AU.userId='"+usrId+"' and AC.id.cmbDtlId=AU.userType and  EFE.journalCode=AU.loginName and EFE.journalEnrollParntId=EFEF.journalEnrollParntId  and EFEF.enrollRelation='0' and EFEF.journalCradNo is not null and EML.id.locId=EFE.ofcDistrict and AMC.id.cmbDtlId=EFEF.aadharExists");
					}
					else{
						query.append(" SELECT INITCAP(EFEF.enrollName) as NEWEMPCODE,EFE.homeEmail as HOSPEMAIL,EFE.homeMobileNo as MOBILENO,EFE.accCardNo as disMainId,EFE.scheme as SCHEMENAME,EFEF1.aadharId as AADHARID,EFEF1.aadharEid as AADHAREID,AU.loginName as EMPID,INITCAP(EFEF1.enrollName) as EMPNAME,EFEF1.journalCradNo as ID,AC.cmbDtlName as VALUE,EFEF1.enrollPhoto as PATH,EFEF.journalEnrollParntId as JOBID,to_char(EFEF1.enrollDob,'dd/mm/YYYY') as REMARKS,EFEF1.enrollGender as TYPE,EMRM.relationId as COUNT,EFE.ofcDistrict as POSTCODE,EML.locName as DISTRICTNAME ,EML.locShrtName as CUGNUM,AMC.cmbDtlName as flagName,EFEF1.aadharExists as DISNAME  ");
						query.append(" FROM EhsJournalistEnrollment EFE ,EhfJrnlstFamily EFEF,EhfJrnlstFamily EFEF1,EhfmJournalist AU,EhfmCmbDtls AC,EhfmRelationMst EMRM,EhfmLocations EML,AsrimCombo AMC  ");
						query.append(" where AU.userId='"+usrId+"' and AC.id.cmbDtlId=AU.userType and  EFE.journalCode=AU.loginName and  EFE.journalEnrollParntId=EFEF.journalEnrollParntId and EFE.journalEnrollParntId=EFEF1.journalEnrollParntId and EFEF.journalEnrollSno=0 and EFEF1.journalEnrollSno="+lStrSeqNo+" and EFEF.journalCradNo is not null and EFEF1.journalEnrollId='"+lStrEnrolId+"' and to_char(EMRM.relationId)=EFEF1.enrollRelation  and EML.id.locId=EFE.ofcDistrict and AMC.id.cmbDtlId=EFEF1.aadharExists");
					}
				/*
				Session session = null;
				SessionFactory factory = null;*/
				
				/*factory = hibernateTemplate.getSessionFactory();
				session = factory.openSession();*/
					List<LabelBean> lStEnrollDetails=genericDao.executeHQLQueryList(LabelBean.class, query.toString());
				
				
				
				
						if(lStEnrollDetails!=null && lStEnrollDetails.size()>0)
						{	
							String lStrEmpTypeFlag="N";
							List<LabelBean> lStAddressList = getAddressDetails(lStEnrollDetails.get(0).getJOBID(), lStrEmpTypeFlag);
							String lStrAddress="";
							if(lStAddressList!=null && lStAddressList.size()>0)
								{
								LabelBean lAddressBean=lStAddressList.get(0);
								if(lAddressBean.getEMPHNO()!=null){
								lStrAddress=lStrAddress+lAddressBean.getEMPHNO()+",<br/>";
								}
								if(lAddressBean.getEMPSTRTNO()!=null){
								lStrAddress=lStrAddress+lAddressBean.getEMPSTRTNO();
								}
								lStrAddress=lStrAddress+","+lAddressBean.getEMPRESVLGE();
								lStrAddress=lStrAddress+" ,<br/>"+lAddressBean.getEMPRESMDL();
								lStrAddress=lStrAddress+",<br/>"+lAddressBean.getEMPRESDIST()+" District";
						//		lStEnrollDetails.get(0).setDISTRICTNAME(lAddressBean.getEMPRESDIST());
								lStEnrollDetails.get(0).setEMAILID(lStrAddress);
								if(lStEnrollDetails.get(0).getTYPE().equalsIgnoreCase("M"))
								lStEnrollDetails.get(0).setCONST("Male");
								else if(lStEnrollDetails.get(0).getTYPE().equalsIgnoreCase("F")){
									lStEnrollDetails.get(0).setCONST("Female");
								}
								}
							return lStEnrollDetails.get(0);
							
						}
					}
					else if(empRadio!=null && "E".equalsIgnoreCase(empRadio))
					{
						System.out.println("In Employee");
						if(lStrSeqNo!=null && "0".equalsIgnoreCase(lStrSeqNo)){
							query.append(" SELECT EFEF.enrollName as NEWEMPCODE,EFE.scheme as SCHEMENAME,EFEF.aadharId as AADHARID,EFEF.aadharEid as AADHAREID,EFEF.idType as UNITTYPE,AU.loginName as EMPID,EFEF.enrollName as EMPNAME,EFEF.ehfCardNo as ID,AC.cmbDtlName as VALUE,EFEF.enrollPrntId as JOBID,to_char(EFEF.enrollDob,'dd/mm/YYYY') as REMARKS,to_char(EFEF.enrollGender) as TYPE,0 as COUNT,EFE.postDdoCode as PARENT_UNITID,EFE.postDist as POSTCODE,EFE.deptHod as UNITID,EML.locName as DISTRICTNAME,EFE.postDdoCode as DDO,EEDA.savedName as PATH,EML.locShrtName as CUGNUM");
							query.append(" FROM EhfEnrollment EFE ,EhfEmployeeDocAttach EEDA,EhfEnrollmentFamily EFEF,AsrimUsers AU,AsrimCombo AC,EhfmLocations EML ");
							query.append(" where AU.userID='"+usrId+"' and AC.id.cmbDtlId=AU.userRole and  EFE.empCode=AU.loginName and EML.activeYn='Y' and EFE.enrollPrntId=EFEF.enrollPrntId  and EFEF.enrollRelation='0' and EFEF.ehfCardNo is not null and EEDA.employeeParntId=EFEF.enrollPrntId and EEDA.attachactiveYN='Y' and  EEDA.attachType='CD3002' and EML.id.locId=EFE.postDist ");
							}
							else{
								query.append(" SELECT EFEF1.enrollName as NEWEMPCODE,EFE.scheme as SCHEMENAME,EFEF.aadharId as AADHARID,EFEF.aadharEid as AADHAREID,EFEF.idType as UNITTYPE,AU.loginName as EMPID,EFEF.enrollName as EMPNAME,EFEF.ehfCardNo as ID,AC.cmbDtlName as VALUE,EFEF.enrollPhoto as PATH,EFEF.enrollPrntId as JOBID,to_char(EFEF.enrollDob,'dd/mm/YYYY') as REMARKS,to_char(EFEF.enrollGender) as TYPE,EMRM.relationId as COUNT,EFE.postDdoCode as PARENT_UNITID,EFE.postDist as POSTCODE,EFE.deptHod as UNITID,EML.locName as DISTRICTNAME ,EML.locShrtName as CUGNUM ");
								query.append(" FROM EhfEnrollment EFE ,EhfEnrollmentFamily EFEF,EhfEnrollmentFamily EFEF1,AsrimUsers AU,AsrimCombo AC,EhfmRelationMst EMRM,EhfmLocations EML  ");
								query.append(" where AU.userID='"+usrId+"' and AC.id.cmbDtlId=AU.userRole and  EFE.empCode=AU.loginName and EML.activeYn='Y' and EFE.enrollPrntId=EFEF.enrollPrntId and EFE.enrollPrntId=EFEF1.enrollPrntId and EFEF1.enrollSno='0' and EFEF.ehfCardNo is not null and EFEF.enrollId='"+lStrEnrolId+"' and to_char(EMRM.relationId)=EFEF.enrollRelation  and EML.id.locId=EFE.postDist");
							}
							
							
						
							
							List<LabelBean> lStEnrollDetails=genericDao.executeHQLQueryList(LabelBean.class, query.toString());
							
							if(lStEnrollDetails!=null && lStEnrollDetails.size()>0)
							{	
								String lStrEmpTypeFlag="N";
								if(lStEnrollDetails.get(0).getVALUE()!=null &&lStEnrollDetails.get(0).getVALUE().equalsIgnoreCase("Employee"))
								{
									lStrEmpTypeFlag="Y";
									if(lStEnrollDetails.get(0).getSCHEMENAME()!=null && "CD201".equalsIgnoreCase(lStEnrollDetails.get(0).getSCHEMENAME())){
										if(lStEnrollDetails.get(0).getPOSTCODE()!=null && "26".equalsIgnoreCase(lStEnrollDetails.get(0).getPOSTCODE())){
										EhfmHODMst ehfmHODMst=genericDao.findById(EhfmHODMst.class,String.class,lStEnrollDetails.get(0).getUNITID());
										if(ehfmHODMst!=null){
											lStEnrollDetails.get(0).setPARENT_UNITID(ehfmHODMst.getHodName());
											lStEnrollDetails.get(0).setWFTYPE("Y");
										}
										}
									}
									else{
										lStEnrollDetails.get(0).setPARENT_UNITID(null);
									}
								
								}
								
								List<LabelBean> lStAddressList = getAddressDetailsEmployee(lStEnrollDetails.get(0).getJOBID(), lStrEmpTypeFlag);
								String lStrAddress="";
								if(lStAddressList!=null && lStAddressList.size()>0)
									{
									LabelBean lAddressBean=lStAddressList.get(0);
									if(lAddressBean.getEMPHNO()!=null){
									lStrAddress=lStrAddress+lAddressBean.getEMPHNO();
									}
									if(lAddressBean.getEMPSTRTNO()!=null){
									lStrAddress=lStrAddress+"<br>,"+lAddressBean.getEMPSTRTNO();
									}
									lStrAddress=lStrAddress+","+lAddressBean.getEMPRESVLGE();
									lStrAddress=lStrAddress+" ,<br>"+lAddressBean.getEMPRESMDL();
									lStrAddress=lStrAddress+","+lAddressBean.getEMPRESDIST()+" District";
							//		lStEnrollDetails.get(0).setDISTRICTNAME(lAddressBean.getEMPRESDIST());
									lStEnrollDetails.get(0).setEMAILID(lStrAddress);
									lStEnrollDetails.get(0).setCONST("Male");
									if(lStEnrollDetails.get(0).getTYPE().equalsIgnoreCase("F"))
									{
										lStEnrollDetails.get(0).setCONST("Female");
									}
									}
								return lStEnrollDetails.get(0);
								
							}
							
					}
				}
				catch(Exception e){
					e.printStackTrace();
					System.out.println("Exception in getcarddetails method");
				}
				finally{
					/*session.close();
					factory.close();*/
				}
			return null;
		
		}
		private List<LabelBean> getAddressDetailsEmployee(String enrollParentId,String empTypeFlag) 
		{
			List<LabelBean> finalEmpDtlsList = new ArrayList<LabelBean>();
			StringBuffer query = new StringBuffer();
			String args[]=new String[1];
			args[0]=enrollParentId;
			query.append("select distinct ee.empHno as EMPHNO,ee.empHstreetno as EMPSTRTNO,e1.locName as EMPRESDIST,e2.locName  as EMPRESMDL,e3.locName as EMPRESVLGE, ");
			query.append("case when ee.empHemail is null then  '-NA-' else  ee.empHemail end as EMPRESMAIL ,  ");
			query.append("ee.empHphone as EMPRESPH ,ee.empIdentiMarks as EMPIDM1,ee.empIdentiMarksSecond as EMPIDM2, coalesce(ee.empRationNo, '-NA-') as EMPRATNCRDNO,e6.locName as empHstate ");
			if (empTypeFlag != null && !"".equalsIgnoreCase(empTypeFlag)
					&& "Y".equalsIgnoreCase(empTypeFlag)) {
				query.append(" ,ee.empOffHno as EMPOFCHNO,ee.empOstreetno as EMPOFCSTRTNO,e4.locName as EMPOFCVLGE, e5.locName as EMPOFCDIST , e7.locName as EMPOFCMDL ,e8.locName as EMPOFCVLGE ,");
				query.append("e6.locName as empOstate, ee.empOphone as EMPOFCPH, case when ee.empOemail is null then '-NA-' else  ee.empOemail end as EMPOFCMAIL   ");

			}

			query.append("from EhfEnrollment ee,EhfmLocations e1,EhfmLocations e2,EhfmLocations e3,EhfmLocations e6,EhfmLocations e7,EhfmLocations e8");
			if (empTypeFlag != null && !"".equalsIgnoreCase(empTypeFlag)
					&& "Y".equalsIgnoreCase(empTypeFlag)) {
				query.append(" ,EhfmLocations e4,EhfmLocations e5  ");

			}
			query.append(" where ee.empHdist = e1.id.locId    and ee.empHmandMunci = e2.id.locId  and ee.empHstate=e6.id.locId and ee.empOmandMunci = e7.id.locId and  ee.empOvillTwn = e8.id.locId");
			query.append(" and ee.empHvillTwn = e3.id.locId and e1.activeYn='Y' and e2.activeYn='Y' and e3.activeYn='Y' and e6.activeYn='Y' ");

			if (empTypeFlag != null && !"".equalsIgnoreCase(empTypeFlag)
					&& "Y".equalsIgnoreCase(empTypeFlag)) {

				query.append("  and ee.empOdist=e5.id.locId  and ee.empOstate = e4.id.locId and e4.activeYn='Y' and e5.activeYn='Y' ");
			}

			query.append(" and ee.id.enrollPrntId =? ");
			List<LabelBean> empDtlsList = genericDao.executeHQLQueryList(
					LabelBean.class, query.toString(),args);
			if (empDtlsList != null && !empDtlsList.isEmpty())
				finalEmpDtlsList.add(empDtlsList.get(0));

			return finalEmpDtlsList;
		}
		public List<LabelBean> getAddressDetails(String enrollParentId,String empTypeFlag) {
			List<LabelBean> finalEmpDtlsList = new ArrayList<LabelBean>();
			StringBuffer query = new StringBuffer();
			String args[]=new String[1];
			args[0]=enrollParentId;
			query.append("select distinct INITCAP(ee.homeHouseNo) as EMPHNO,INITCAP(ee.homeStreetname) as EMPSTRTNO,INITCAP(e1.locName) as EMPRESDIST,INITCAP(e2.locName)  as EMPRESMDL,INITCAP(e3.locName) as EMPRESVLGE, ");
			query.append("case when ee.homeEmail is null then  '-NA-' else  ee.homeEmail end as EMPRESMAIL ,  ");
			query.append("ee.homeMobileNo as EMPRESPH ,coalesce(ee.rationCardNo, '-NA-') as EMPRATNCRDNO ");
			/*if (empTypeFlag != null && !"".equalsIgnoreCase(empTypeFlag)
					&& "Y".equalsIgnoreCase(empTypeFlag)) {
				query.append(" ,ee.ofcHouseNo as EMPOFCHNO,ee.ofcStreetName as EMPOFCSTRTNO,e4.locName as EMPOFCVLGE, ");
				query.append("e5.locName as empOstate, ee.ofcMobileNo as EMPOFCPH, case when ee.ofcEmail is null then '-NA-' else  ee.ofcEmail end as EMPOFCMAIL   ");

			}*/

			query.append("from EhsJournalistEnrollment ee,EhfmLocations e1,EhfmLocations e2,EhfmLocations e3");
			/*if (empTypeFlag != null && !"".equalsIgnoreCase(empTypeFlag)
					&& "Y".equalsIgnoreCase(empTypeFlag)) {
				query.append(" ,EhfmLocations e4,EhfmLocations e5  ");

			}*/
			query.append(" where ee.homeDistrict = e1.id.locId    and ee.homeMunc = e2.id.locId  ");
			query.append(" and ee.homeVillage = e3.id.locId  ");

			/*if (empTypeFlag != null && !"".equalsIgnoreCase(empTypeFlag)
					&& "Y".equalsIgnoreCase(empTypeFlag)) {

				query.append("  and ee.ofcDistrict=e5.id.locId  and ee.ofcState = e4.id.locId ");
			}*/

			query.append(" and ee.id.journalEnrollParntId =? ");
			List<LabelBean> empDtlsList = genericDao.executeHQLQueryList(
					LabelBean.class, query.toString(),args);
			if (empDtlsList != null && !empDtlsList.isEmpty())
				finalEmpDtlsList.add(empDtlsList.get(0));

			return finalEmpDtlsList;

		}
		@Override
		public String getEmpUsrId(String cardNo) 
		{
			String userId=null;
			try{
				StringBuffer query = new StringBuffer();
				query.append("select distinct j.user_id as USERID from asrim_users j,ehf_enrollment e, ");
				query.append("ehf_enrollment_family ef where ef.enroll_prnt_id=e.enroll_prnt_id ");
				query.append("and e.emp_code=j.login_name and ef.ehf_card_no like '%"+cardNo.toUpperCase()+"%' ");
				List<cardSearchVO> user = genericDao.executeSQLQueryList(cardSearchVO.class,query.toString());
				if(user.size()>0)
				{
					userId=user.get(0).getUSERID();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return userId;
		}
		
	}
