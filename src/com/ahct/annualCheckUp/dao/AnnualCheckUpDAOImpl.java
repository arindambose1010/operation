package com.ahct.annualCheckUp.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.lang.reflect.Field;
import java.math.BigDecimal;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.annualCheckUp.vo.AnnualCheckUpVo;
import com.ahct.model.EhfAhcAudit;
import com.ahct.model.EhfAhcAuditId;
import com.ahct.model.EhfAhcConsultationDtls;
import com.ahct.model.EhfAhcExamFind;
import com.ahct.model.EhfAhcHospDiagnosis;
import com.ahct.model.EhfAhcMedicalReport;
import com.ahct.model.EhfAhcPart1Results;
import com.ahct.model.EhfAhcPart2Results;
import com.ahct.model.EhfAhcPersonalHistory;
import com.ahct.model.EhfAnnualCaseDtls;
import com.ahct.model.EhfEmployeeDocAttach;
import com.ahct.model.EhfEnrollment;
import com.ahct.model.EhfEnrollmentFamily;
import com.ahct.model.EhfAnnualPatientDtls;
import com.ahct.model.EhfmCmbDtls;
import com.ahct.model.EhfmLocations;
import com.ahct.model.EhfmHospMithraDtls;
import com.ahct.model.EhfmHospMithraDtlsId;
import com.ahct.model.EhfmHospitals;
import com.ahct.model.EhfmMedcoDtls;
import com.ahct.model.EhfmMedcoDtlsId;
import com.ahct.model.EhfmPersonalHistoryMst;
import com.ahct.model.EhfmRelationMst;
import com.ahct.model.EhfAhcPatientTest;
import com.ahct.patient.vo.PatientVO;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;


@Transactional
public class AnnualCheckUpDAOImpl implements AnnualCheckUpDAO {
	private final static Logger GLOGGER = Logger
			.getLogger(AnnualCheckUpDAOImpl.class);
	GenericDAO genericDao;
	HibernateTemplate hibernateTemplate;
	GenericDAOQueryCriteria genericDaoqueryCrit;

	public GenericDAOQueryCriteria getGenericDaoqueryCrit() {
		return genericDaoqueryCrit;
	}

	public void setGenericDaoqueryCrit(
			GenericDAOQueryCriteria genericDaoqueryCrit) {
		this.genericDaoqueryCrit = genericDaoqueryCrit;
	}

	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}

	/**
	 * Sets the configuration service.
	 * 
	 * @param configurationService
	 *            the new configuration service
	 */
	public static void setConfigurationService(
			ConfigurationService configurationService) {
		AnnualCheckUpDAOImpl.configurationService = configurationService;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public GenericDAO getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}

	/**
	 * ;
	 * 
	 * @return List<LabelBean> relationList
	 * @function This Method is Used For getting Relations List from RelationMst
	 *           table
	 */
	@Override
	public List<LabelBean> getRelations() throws Exception {
		List<LabelBean> relationList = new ArrayList<LabelBean>();
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		criteriaList.add(new GenericDAOQueryCriteria("relationName", null,
				GenericDAOQueryCriteria.CriteriaOperator.ASC));
		List<EhfmRelationMst> relationMstList = genericDao.findAllByCriteria(
				EhfmRelationMst.class, criteriaList);
		for (EhfmRelationMst relationMst : relationMstList) {
			LabelBean labelBean = new LabelBean();
			labelBean.setID(relationMst.getRelationId().toString());
			labelBean.setVALUE(relationMst.getRelationName());
			relationList.add(labelBean);
		}

		return relationList;
	}

	/**
	 * ;
	 * 
	 * @param String
	 *            payGrade
	 * @return String slab
	 * @function This Method is Used For Retrieving Slab type for given payGrade
	 */
	@Override
	public String getSlabType(String payGrade) throws Exception {
		List<LabelBean> payGradeList = new ArrayList<LabelBean>();
		String slabType = null;
		StringBuffer query = null;
		try {
			query = new StringBuffer();
			query.append("select distinct a.slab as VALUE from EhfmPayGradeMst a where a.id.payGrade='"
					+ payGrade + "'");
			payGradeList = genericDao.executeHQLQueryList(LabelBean.class,
					query.toString());
			if (payGradeList != null && payGradeList.size() > 0) {
				slabType = payGradeList.get(0).getVALUE();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return slabType;
	}

	/**
	 * ;
	 * 
	 * @param AnnualCheckUpVo
	 *            annualCheckUpVo
	 * @return AnnualCheckUpVo
	 * @function This Method is Used For Retrieving Enrollment Details of
	 *           Employee card no
	 */
	@Override
	public AnnualCheckUpVo retrieveCardDetails(AnnualCheckUpVo annualCheckUpVo)
			throws Exception {
		AnnualCheckUpVo annualCheckUpVo1 = null;
		EhfEnrollmentFamily ehfEnrollmentFamily = null;
		SessionFactory factory = null;
		Session session = null;
		Query hQuery = null;
		String cardValidStatus = config.getString("Card.ValidStatus");
		try {
			factory = hibernateTemplate.getSessionFactory();
			session = factory.openSession();
			StringBuffer query = new StringBuffer();
			
			query.append("from EhfEnrollmentFamily ef where ef.ehfCardNo='"+annualCheckUpVo.getCardNo()+"' and ef.enrollStatus  in ("+cardValidStatus.replace("~", ",")+")");
			hQuery=session.createQuery(query.toString());
			if(hQuery.list().size()>0)
			{
				StringBuffer query1 = new StringBuffer();
				ehfEnrollmentFamily=(EhfEnrollmentFamily) hQuery.list().get(0);
			query1.append("from EhfEnrollmentFamily ef where ef.ehfCardNo='"
					+ annualCheckUpVo.getCardNo()
					+ "' and ef.annualCheckup='Y' and ef.enrollStatus  in ("
					+ cardValidStatus.replace("~", ",") + ")");
			hQuery = session.createQuery(query1.toString());
			if (hQuery.list().size() > 0) {
				ehfEnrollmentFamily = (EhfEnrollmentFamily) hQuery.list()
						.get(0);
			}
		} }catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
		if(ehfEnrollmentFamily!=null && ehfEnrollmentFamily.getAnnualCheckup().equalsIgnoreCase("Y"))
		{
			//To check if card is deactivated due to patient's death
			if(ehfEnrollmentFamily.getEnrollStatus()!=null && !"".equalsIgnoreCase(ehfEnrollmentFamily.getEnrollStatus())
					&& !(config.getString("inactivate_card_death_status").equalsIgnoreCase(ehfEnrollmentFamily.getEnrollStatus())))
			{
				String enrollParntId=ehfEnrollmentFamily.getEnrollPrntId();
				StringBuffer query1=new StringBuffer(); 
				List<String> l=null;
				query1.append("from EhfEnrollment where enrollPrntId='"+enrollParntId+"'");
				if(annualCheckUpVo.getCardType()!=null && !"".equalsIgnoreCase(annualCheckUpVo.getCardType()))
				{
					if(annualCheckUpVo.getCardType().equalsIgnoreCase("E"))
					{
		
						query1.append(" and empType='"+config.getString("Role.Employee")+"'");
					/*
					 * else
					 * if(annualCheckUpVo.getCardType().equalsIgnoreCase("P")) {
					 * query1
					 * .append(" and empType in('"+config.getString("Role.Pensioner"
					 * )+"','"+config.getString("Role.ServicePensioner")+"')");
					 * }
					 */
				}

				try {
					if (!hibernateTemplate.getSessionFactory()
							.getCurrentSession().isOpen()) {
						throw new RuntimeException(
								"No Session open for executing the query "
										+ query1);
					} else
						l = hibernateTemplate.getSessionFactory()
								.getCurrentSession()
								.createQuery(query1.toString()).list();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Iterator ite = l.iterator();
				EhfEnrollment ehfEnrollment = null;
				if (ite.hasNext()) {
					ehfEnrollment = (EhfEnrollment) ite.next();
				}
				if (ehfEnrollment != null) {
					annualCheckUpVo1 = new AnnualCheckUpVo();
					
					if(ehfEnrollment.getScheme()!=null && !"".equalsIgnoreCase(ehfEnrollment.getScheme()))
					{
						if(annualCheckUpVo.getSchemeId()!=null && !"".equalsIgnoreCase(annualCheckUpVo.getSchemeId())
								&& !config.getString("COMMON").equalsIgnoreCase(annualCheckUpVo.getSchemeId()))
							{
								if(!ehfEnrollment.getScheme().equalsIgnoreCase(annualCheckUpVo.getSchemeId()))
									{
										annualCheckUpVo1=new AnnualCheckUpVo();
										annualCheckUpVo1.setMsg(config.getString("invalid_state_remarks"));
										return annualCheckUpVo1;
									}
							}
					}
					
					annualCheckUpVo1.setEmpCode(ehfEnrollment.getEmpCode());
					annualCheckUpVo1.setDateOfBirth(ehfEnrollmentFamily
							.getEnrollDob().toString());
					annualCheckUpVo1.setGender(Character
							.toString(ehfEnrollmentFamily.getEnrollGender()));
					annualCheckUpVo1.setName(ehfEnrollmentFamily
							.getEnrollName());
					annualCheckUpVo1.setRelation(ehfEnrollmentFamily
							.getEnrollRelation());
					annualCheckUpVo1.setCaste(ehfEnrollment.getEmpCommu());
					annualCheckUpVo1.setContactno(ehfEnrollment.getEmpHphone());
					annualCheckUpVo1.setAddr1(ehfEnrollment.getEmpHno());
					annualCheckUpVo1.setAddr2(ehfEnrollment.getEmpHstreetno());
					annualCheckUpVo1.setState(ehfEnrollment.getEmpHstate());
					annualCheckUpVo1.setDistrict(ehfEnrollment.getEmpHdist());
					annualCheckUpVo1.setMdl_mcl(ehfEnrollment
							.getEmpHmandMunciSel());
					annualCheckUpVo1
							.setMandal(ehfEnrollment.getEmpHmandMunci());
					annualCheckUpVo1.setVillage(ehfEnrollment.getEmpHvillTwn());

					annualCheckUpVo1.setDept_Hod(ehfEnrollment.getDeptHod());
					annualCheckUpVo1.setPost_Dist(ehfEnrollment.getPostDist());
					// annualCheckUpVo1.setDdoOffUnit(ehfEnrollment.getDdoOffUnit());
					annualCheckUpVo1.setPost_Ddo_Code(ehfEnrollment
							.getPostDdoCode());

					String slab = getSlabType(ehfEnrollment.getPayScale());
					if (slab != null) {
						annualCheckUpVo1.setSlab(slab);
					} else {
						annualCheckUpVo1.setSlab(config
								.getString("Slab.SemiPrivateWard"));
					}
					if (ehfEnrollment.getScheme() != null) {
						annualCheckUpVo1.setScheme(ehfEnrollment.getScheme());
					} else {
						annualCheckUpVo1.setScheme(config
								.getString("Scheme.ap"));
					}
					if (ehfEnrollment.getEmpHemail() != null) {
						annualCheckUpVo1.seteMailId(ehfEnrollment
								.getEmpHemail());
					}

					if (annualCheckUpVo.getCardNo().endsWith("01")) {
						annualCheckUpVo1.setMaritalStatus(ehfEnrollment
								.getEmpMaritalStat());
						if (annualCheckUpVo.getCardType() != null
								&& !"".equalsIgnoreCase(annualCheckUpVo
										.getCardType())
								&& annualCheckUpVo.getCardType()
										.equalsIgnoreCase("E")) {
							String service = ehfEnrollment.getService();
							String categoryName = ehfEnrollment.getServDsgn();
							String hod = ehfEnrollment.getDeptHod();
							if (ehfEnrollment.getDeptDesignation() != null
									&& !"".equals(ehfEnrollment
											.getDeptDesignation())) {
								Long desgnId = Long.parseLong(ehfEnrollment
										.getDeptDesignation());

								List<LabelBean> designationList = new ArrayList<LabelBean>();
								StringBuffer query = null;
								try {
									query = new StringBuffer();
									query.append("select distinct a.deptDesignation as VALUE from EhfDesignationMst a where a.id.hod='"
											+ hod
											+ "' and a.id.service='"
											+ service
											+ "' and a.id.categoryName='"
											+ categoryName
											+ "' and a.id.dsgnId=" + desgnId);
									designationList = genericDao
											.executeHQLQueryList(
													LabelBean.class,
													query.toString());
									if (designationList != null
											&& designationList.size() > 0) {
										annualCheckUpVo1
												.setDesignation(designationList
														.get(0).getVALUE());
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
						Map<String, Object> photoMap = new HashMap<String, Object>();
						photoMap.put("employeeParntId", enrollParntId);
						photoMap.put("attachType",
								config.getString("FamilyHeadPhoto.AttachType"));
						photoMap.put("attachactiveYN", "Y");
						EhfEmployeeDocAttach ehfEmployeeDocAttach = genericDao
								.findFirstByPropertyMatch(
										EhfEmployeeDocAttach.class, photoMap);
						if (ehfEmployeeDocAttach != null) {
							annualCheckUpVo1.setPhoto(ehfEmployeeDocAttach
									.getSavedName());
						}
					} else {
						annualCheckUpVo1.setPhoto(ehfEnrollmentFamily
								.getEnrollPhoto());
					}
				}
			}
				
			}else {
				annualCheckUpVo1 = new AnnualCheckUpVo();
				annualCheckUpVo1.setMsg(config
						.getString("inactivate_card_death_remarks"));
			}
		}
		else if(ehfEnrollmentFamily!=null && "N".equalsIgnoreCase(ehfEnrollmentFamily.getAnnualCheckup()))
		{
			annualCheckUpVo1=new AnnualCheckUpVo();
			annualCheckUpVo1.setMsg(config.getString("inactivate_ahc_remarks"));
		}
		else if(ehfEnrollmentFamily!=null && "A".equalsIgnoreCase(ehfEnrollmentFamily.getAnnualCheckup()))
		{
			annualCheckUpVo1=new AnnualCheckUpVo();
			annualCheckUpVo1.setMsg(config.getString("inactivate_ahc_year_remarks"));
		}
		return annualCheckUpVo1;
	}

	/**
	 * ;
	 * 
	 * @param String
	 *            userId
	 * @param String
	 *            roleId
	 * @return List<LabelBean> hospitalList
	 * @function This Method is Used For getting Hospital List for given user
	 *           and role
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<LabelBean> getHospital(String userId, String roleId)
			throws Exception {
		List<LabelBean> hospitalList = new ArrayList<LabelBean>();
		SessionFactory factory = null;
		Session session = null;
		try {
			factory = hibernateTemplate.getSessionFactory();
			session = factory.openSession();
			StringBuffer query = new StringBuffer();
			StringBuffer query1 = new StringBuffer();
			Query hQuery;
			List hospList = null;
			ArrayList<String> hospIdList = null;
			Iterator hospItr = null;
			List resultList = null;
			Iterator resultItr = null;
			if (roleId.equals(config.getString("Group.Mithra"))) {
				query.append("from EhfmHospMithraDtls amu where amu.id.mithraId='"
						+ userId + "' and amu.endDt=" + null);
				hQuery = session.createQuery(query.toString());
				hospList = hQuery.list();
				if (hospList.size() > 0) {
					hospIdList = new ArrayList<String>();
					hospItr = hospList.iterator();
					while (hospItr.hasNext()) {
						EhfmHospMithraDtls ehfmHospMithraDtls = (EhfmHospMithraDtls) hospItr
								.next();
						EhfmHospMithraDtlsId ehfmHospMithraDtlsId = ehfmHospMithraDtls
								.getId();
						GLOGGER.info("hospIds "
								+ ehfmHospMithraDtlsId.getHospId());
						hospIdList.add(ehfmHospMithraDtlsId.getHospId());
					}
				} else {
					GLOGGER.info("No associated hosp for this user");
					System.out.println("No associated hosp for this user");
				}
			} else if (roleId.equals(config.getString("Group.Medco"))) {
				query.append("from EhfmMedcoDtls anu where anu.id.medcoId='"
						+ userId + "' and anu.endDate=" + null);

				hQuery = session.createQuery(query.toString());
				hospList = hQuery.list();
				if (hospList.size() > 0) {
					hospIdList = new ArrayList();
					hospItr = hospList.iterator();
					while (hospItr.hasNext()) {
						EhfmMedcoDtls ehfmMedcoDetails = (EhfmMedcoDtls) hospItr
								.next();
						EhfmMedcoDtlsId ehfmMedcoDetailsId = ehfmMedcoDetails
								.getId();
						GLOGGER.info("hospIds "
								+ ehfmMedcoDetailsId.getHospId());
						hospIdList.add(ehfmMedcoDetailsId.getHospId());
					}
				} else {
					System.out.println("No associated hosp for this user");
				}
			}
			String activeYn = config.getString("ActiveYn");
			if (hospIdList != null) {
				// query1.append("from EhfmHospitals ah where ah.hospId in (:param) and ah.hospActiveYN='"+activeYn+"'");
				query1.append("from EhfmHospitals ah where ah.hospId in (:param) ");
				Query hQuery1 = session.createQuery(query1.toString());
				hQuery1.setParameterList("param", hospIdList);
				resultList = hQuery1.list();
				if (resultList.size() > 0)
					GLOGGER.info("Hosp details retrieved");
				resultItr = resultList.iterator();
				while (resultItr.hasNext()) {
					EhfmHospitals ehfmHospitals = (EhfmHospitals) resultItr
							.next();
					LabelBean labelBean = new LabelBean();
					labelBean.setID(ehfmHospitals.getHospId());
					labelBean.setVALUE(ehfmHospitals.getHospName() + ","
							+ ehfmHospitals.getHouseNumber() + ","
							+ ehfmHospitals.getHospCity());
					labelBean.setNATURE(ehfmHospitals.getHospType() + "");
					hospitalList.add(labelBean);
				}
			}
		} catch (Exception e) {
			GLOGGER.error("Exception Occurred in getHospital() in AnnualCheckUpDAOImpl class."
					+ e.getMessage());
		} finally {
			session.close();
			factory.close();
		}
		return hospitalList;
	}

	public String getHospActiveStatus(String userId, String roleId) {
		String actStatus = "";
		StringBuffer query = new StringBuffer();
		try {
			if (roleId.equals(config.getString("Group.Mithra"))) {
				query.append(" select h.hospActiveYN as ID from EhfmHospitals h, EhfmHospMithraDtls d ");
				query.append(" where d.id.mithraId=? and d.endDt is null and h.hospId= d.id.hospId ");
				String args[] = new String[1];
				args[0] = userId;

				List<LabelBean> resultLst = genericDao.executeHQLQueryList(
						LabelBean.class, query.toString(), args);
				if (resultLst != null && resultLst.size() > 0) {
					actStatus = resultLst.get(0).getID();
				}
			}
		} catch (Exception e) {
			GLOGGER.error("Error in method getHospActiveStatus() of AnnualCheckUpDAOImpl "
					+ e.getMessage());
		}
		return actStatus;
	}

	@Override
	public String getSequence(String pStrSeqName) throws Exception {
		String lStrSeqRetVal = "";

		try {

			StringBuffer query = new StringBuffer();
			query.append(" SELECT " + pStrSeqName
					+ ".NEXTVAL NEXTVAL  FROM DUAL ");
			List<LabelBean> seqList = genericDao.executeSQLQueryList(
					LabelBean.class, query.toString());

			if (seqList != null) {

				lStrSeqRetVal = seqList.get(0).getNEXTVAL().toString();
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return lStrSeqRetVal;
	}

	@Override
	/**
	 * ;
	 * @param AnnualCheckUpVo annualCheckUpVo
	 * @return int noOfRecords
	 * @function This Method is Used For Registering Direct Patient
	 */
	public int registerPatient(AnnualCheckUpVo annualCheckUpVo)
			throws Exception {
		SimpleDateFormat sdfw = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date crtDt;
		Date dateOfBirth;
		Date cardIssueDt;
		try {
			EhfAnnualPatientDtls ehfAnnualPatientDtls = null;
			ehfAnnualPatientDtls = new EhfAnnualPatientDtls();
			crtDt = sdfw.parse(annualCheckUpVo.getCrtDt());

			if (annualCheckUpVo.getCardIssueDt() != null
					&& !annualCheckUpVo.getCardIssueDt().equalsIgnoreCase("")) {
				cardIssueDt = sdf.parse(annualCheckUpVo.getCardIssueDt());
				ehfAnnualPatientDtls.setCardIssueDt(cardIssueDt);
			}
			if (annualCheckUpVo.getDateOfBirth() != null
					&& !annualCheckUpVo.getDateOfBirth().equalsIgnoreCase("")) {
				dateOfBirth = sdf.parse(annualCheckUpVo.getDateOfBirth());
				ehfAnnualPatientDtls.setEnrollDob(dateOfBirth);
			}

			BigDecimal contactNo = null;
			BigDecimal age = null;
			BigDecimal ageMonths = null;
			BigDecimal ageDays = null;

			if (annualCheckUpVo.getContactno() != null
					&& !"".equalsIgnoreCase(annualCheckUpVo.getContactno()))
				contactNo = new BigDecimal(annualCheckUpVo.getContactno());
			if (annualCheckUpVo.getAge() != null
					&& !"".equalsIgnoreCase(annualCheckUpVo.getAge()))
				age = new BigDecimal(annualCheckUpVo.getAge());
			if (annualCheckUpVo.getAgeMonths() != null
					&& !"".equalsIgnoreCase(annualCheckUpVo.getAgeMonths()))
				ageMonths = new BigDecimal(annualCheckUpVo.getAgeMonths());
			if (annualCheckUpVo.getAgeDays() != null
					&& !"".equalsIgnoreCase(annualCheckUpVo.getAgeDays()))
				ageDays = new BigDecimal(annualCheckUpVo.getAgeDays());

			ehfAnnualPatientDtls.setAhcId(annualCheckUpVo.getPatientId());
			ehfAnnualPatientDtls.setCardType(annualCheckUpVo.getCardType());
			ehfAnnualPatientDtls.setEmployeeNo(annualCheckUpVo.getEmpCode());
			ehfAnnualPatientDtls.setCardNo(annualCheckUpVo.getRationCard());
			ehfAnnualPatientDtls.setFamilyHead(annualCheckUpVo.getFamilyHead());
			ehfAnnualPatientDtls.setChildYn(annualCheckUpVo.getChild_yn());
			ehfAnnualPatientDtls.setCrtDt(crtDt);
			ehfAnnualPatientDtls.setCrtUsr(annualCheckUpVo.getCrtUsr());
			ehfAnnualPatientDtls.setLstUpdDt(crtDt);
			ehfAnnualPatientDtls.setLstUpdUsr(annualCheckUpVo.getCrtUsr());
			ehfAnnualPatientDtls.setName(annualCheckUpVo.getName());
			ehfAnnualPatientDtls.setGender(annualCheckUpVo.getGender());
			ehfAnnualPatientDtls.setOccupationCd(annualCheckUpVo
					.getOccupationCd());
			ehfAnnualPatientDtls.setContactNo(contactNo);
			ehfAnnualPatientDtls.setRelation(annualCheckUpVo.getRelation());
			ehfAnnualPatientDtls.setAge(age);
			ehfAnnualPatientDtls.setAgeMonths(ageMonths);
			ehfAnnualPatientDtls.setAgeDays(ageDays);
			ehfAnnualPatientDtls.setCaste(annualCheckUpVo.getCaste());
			ehfAnnualPatientDtls.setMaritalStatus(annualCheckUpVo
					.getMaritalStatus());
			ehfAnnualPatientDtls.setSlab(annualCheckUpVo.getSlab());
			ehfAnnualPatientDtls.setSchemeId(annualCheckUpVo.getSchemeId());
			if (annualCheckUpVo.geteMailId() != null
					&& !annualCheckUpVo.geteMailId().equals("")) {
				ehfAnnualPatientDtls.setEmailid(annualCheckUpVo.geteMailId());
			}
			ehfAnnualPatientDtls.setHouseNo(annualCheckUpVo.getAddr1());
			ehfAnnualPatientDtls.setStreet(annualCheckUpVo.getAddr2());
			ehfAnnualPatientDtls.setState(annualCheckUpVo.getState());
			ehfAnnualPatientDtls.setDistrictCode(annualCheckUpVo.getDistrict());
			ehfAnnualPatientDtls.setMdlMpl(annualCheckUpVo.getMdl_mpl());
			ehfAnnualPatientDtls.setMandalCode(annualCheckUpVo.getMandal());
			ehfAnnualPatientDtls.setVillageCode(annualCheckUpVo.getVillage());
			ehfAnnualPatientDtls.setPinCode(annualCheckUpVo.getPin());
			// ehfAnnualPatientDtls.setSrcRegistration(annualCheckUpVo.getSrcRegistration());
			// ehfAnnualPatientDtls.setPatientIpop(annualCheckUpVo.getPatient_ipop());
			// ehfAnnualPatientDtls.setPhaseId(Long.parseLong(annualCheckUpVo.getPhaseId()));
			// ehfAnnualPatientDtls.setRenewal(Long.parseLong(annualCheckUpVo.getRenewal()));
			// ehfAnnualPatientDtls.setSchemeId(annualCheckUpVo.getSchemeId());
			// ehfAnnualPatientDtls.setSourceId(Long.parseLong(annualCheckUpVo.getCid()));

			ehfAnnualPatientDtls.setCHouseNo(annualCheckUpVo.getPermAddr1());
			ehfAnnualPatientDtls.setCStreet(annualCheckUpVo.getPermAddr2());
			ehfAnnualPatientDtls.setCState(annualCheckUpVo.getC_state());
			ehfAnnualPatientDtls.setCDistrictCode(annualCheckUpVo
					.getC_district_code());
			ehfAnnualPatientDtls.setCMdlMpl(annualCheckUpVo.getC_mdl_mpl());
			ehfAnnualPatientDtls.setCMandalCode(annualCheckUpVo
					.getC_mandal_code());
			ehfAnnualPatientDtls.setCVillageCode(annualCheckUpVo
					.getC_village_code());
			ehfAnnualPatientDtls.setCPinCode(annualCheckUpVo.getC_pin_code());

			ehfAnnualPatientDtls.setRegHospId(annualCheckUpVo.getRegHospId());
			ehfAnnualPatientDtls.setRegHospDate(crtDt);

			ehfAnnualPatientDtls.setPostDist(annualCheckUpVo.getPost_Dist());
			// ehfAnnualPatientDtls.setStoCode(annualCheckUpVo.getDdoOffUnit());
			ehfAnnualPatientDtls.setDdoCode(annualCheckUpVo.getPost_Ddo_Code());
			ehfAnnualPatientDtls.setDeptHod(annualCheckUpVo.getDept_Hod());

			EhfAnnualCaseDtls ehfAnnualCaseDtls = null;

			ehfAnnualCaseDtls = new EhfAnnualCaseDtls();
			ehfAnnualCaseDtls.setAhcId(annualCheckUpVo.getPatientId());
			ehfAnnualCaseDtls.setAhcHospCode(annualCheckUpVo.getRegHospId());
			ehfAnnualCaseDtls.setAhcStatus(config
					.getString("AHC.CaseRegisteredStatus"));
			ehfAnnualCaseDtls.setSchemeId(annualCheckUpVo.getSchemeId());
			ehfAnnualCaseDtls.setAhcRegnDate(crtDt);
			ehfAnnualCaseDtls.setCrtUsr(annualCheckUpVo.getCrtUsr());
			ehfAnnualCaseDtls.setCrtDt(crtDt);
			ehfAnnualCaseDtls.setLstUpdDt(crtDt);
			ehfAnnualCaseDtls.setLstUpdUsr(annualCheckUpVo.getCrtUsr());

			/*
			 * if("Telephonic Registration".equalsIgnoreCase(annualCheckUpVo.
			 * getRegState())) {
			 * ehfAnnualPatientDtls.setIntimationId(annualCheckUpVo
			 * .getTelephonicId()); EhfTelephonicRegn
			 * ehfmTelephonicRegn=genericDao.findById(EhfTelephonicRegn.class,
			 * String.class, annualCheckUpVo.getTelephonicId());
			 * ehfmTelephonicRegn.setPatientId(annualCheckUpVo.getPatientId());
			 * ehfmTelephonicRegn.setEmployeeNo(annualCheckUpVo.getEmpCode());
			 * ehfmTelephonicRegn.setLstUpdDt(crtDt);
			 * ehfmTelephonicRegn.setLstUpdUsr(annualCheckUpVo.getCrtUsr());
			 * genericDao.save(ehfmTelephonicRegn); }
			 */
				List<EhfEnrollmentFamily> ehfEnrollmentFamilyLst=new ArrayList<EhfEnrollmentFamily>();
				EhfEnrollmentFamily ehfEnrollmentFamily=new EhfEnrollmentFamily();
				List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("ehfCardNo", annualCheckUpVo.getRationCard(),
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				ehfEnrollmentFamilyLst=genericDao.findAllByCriteria(EhfEnrollmentFamily.class, criteriaList);
				if(ehfEnrollmentFamilyLst.size()>0)
					{
						ehfEnrollmentFamily=ehfEnrollmentFamilyLst.get(0);
						ehfEnrollmentFamily.setAnnualCheckup("A");
						genericDao.save(ehfEnrollmentFamily);
					}	
	
			
			try {
				ehfAnnualPatientDtls = genericDao.save(ehfAnnualPatientDtls);
				if (ehfAnnualPatientDtls != null) {
					ehfAnnualCaseDtls = genericDao.save(ehfAnnualCaseDtls);
					
					if (ehfAnnualCaseDtls != null){
						
						StringBuffer lQueryBuffer = new StringBuffer();
						String args[] = new String[1];
						Long lActOrder = 0L;
						lQueryBuffer.append(" select max(au.id.actOrder) as COUNT from EhfAhcAudit au where au.id.ahcId=? ");
						args[0] = annualCheckUpVo.getPatientId();
						List<ClaimsFlowVO> actOrderList = genericDao.executeHQLQueryList(
								ClaimsFlowVO.class, lQueryBuffer.toString(), args);
						if (actOrderList != null && !actOrderList.isEmpty()
								&& actOrderList.get(0).getCOUNT() != null) 
						{
						if (actOrderList.get(0).getCOUNT().longValue() >= 0)
							lActOrder = actOrderList.get(0).getCOUNT().longValue() + 1;
						EhfAhcAuditId id= new EhfAhcAuditId(lActOrder,annualCheckUpVo.getPatientId());
						EhfAhcAudit ahcAudit = new EhfAhcAudit();
						ahcAudit.setActBy(annualCheckUpVo.getCrtUsr());
						ahcAudit.setActId(config.getString("AHC_CMB_REGISTER"));
						ahcAudit.setId(id);
						ahcAudit.setCrtDt(crtDt);
						ahcAudit.setRemarks("Registered");
						ahcAudit=genericDao.save(ahcAudit);
						if(ahcAudit!=null)
							return 1;
				}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// EhfPatJbpmProcess ehfPatJbpmProcess= new EhfPatJbpmProcess();
			// ehfPatJbpmProcess.setPatientId(annualCheckUpVo.getPatientId());
			// ehfPatJbpmProcess.setProcessInstanceId(annualCheckUpVo.getProcessInstanceId());
			// genericDao.save(ehfPatJbpmProcess);
		} catch (ParseException e) {
			e.printStackTrace();
			GLOGGER.error("Error occured in registerPatient() in AnnualCheckUpDAOImpl class."
					+ e.getMessage());
			return 0;
		}
		return 1;
	}

	/**
	 * ;
	 * 
	 * @param String
	 *            hospId
	 * @return String hospName
	 * @function This Method is Used For getting Hospital Name
	 */
	@Override
	public String getHospName(String hospId) throws Exception {
		String hospitalName = null;
		try {
			EhfmHospitals asrimHospitals = genericDao.findById(
					EhfmHospitals.class, String.class, hospId);
			hospitalName = asrimHospitals.getHospName();
		} catch (Exception e) {
			GLOGGER.error("Exception Occurred in getHospName() in AnnualCheckUpDAOImpl class."
					+ e.getMessage());
		}
		return hospitalName;
	}

	/*	*//**
	 * ;
	 * 
	 * @param annualCheckUpVo
	 * @return int noOfRecords
	 * @throws Exception
	 * @function This Method is Used For Registering Direct Patient Case
	 */
	/*
	 * @Override public int registerPatientCase(AnnualCheckUpVo annualCheckUpVo)
	 * throws Exception { String lStrCaseId=null; EhfAnnualCaseDtls
	 * ehfAnnualCaseDtls= null; SimpleDateFormat sdfw=new
	 * SimpleDateFormat("dd/MM/yyyy HH:mm:ss a"); Date
	 * lCurrentDate=sdfw.parse(annualCheckUpVo.getCrtDt()); int i=0; try {
	 * String liNextVal = null; if(annualCheckUpVo.getSchemeId()!=null &&
	 * config.
	 * getString("Scheme.ap").equalsIgnoreCase(annualCheckUpVo.getSchemeId())) {
	 * liNextVal = "AP"+getSequence("CASE_ID_AP"); } else
	 * if(annualCheckUpVo.getSchemeId()!=null &&
	 * config.getString("Scheme.tg").equalsIgnoreCase
	 * (annualCheckUpVo.getSchemeId())) { liNextVal =
	 * "TG"+getSequence("CASE_ID_TG"); } else
	 * if(annualCheckUpVo.getSchemeId()!=null &&
	 * "1".equalsIgnoreCase(annualCheckUpVo.getSchemeId())) { liNextVal =
	 * getSequence("CASE_ID"); } annualCheckUpVo.setCaseId(liNextVal);
	 * lStrCaseId=annualCheckUpVo.getCaseId();
	 * 
	 * ehfAnnualCaseDtls=new EhfAnnualCaseDtls();
	 * ehfAnnualCaseDtls.setCaseId(liNextVal);
	 * ehfAnnualCaseDtls.setCaseHospCode(annualCheckUpVo.getHospitalId());
	 * ehfAnnualCaseDtls.setCasePatientNo(annualCheckUpVo.getPatientId());
	 * ehfAnnualCaseDtls.setCaseRegnDate(caseRegnDate);
	 * ehfAnnualCaseDtls.setCrtUsr(annualCheckUpVo.getCrtUsr());
	 * ehfAnnualCaseDtls.setCrtDt(crtDt);
	 * ehfAnnualCaseDtls.setPhaseId(annualCheckUpVo.getPhaseId());
	 * ehfAnnualCaseDtls.setSchemeId(annualCheckUpVo.getSchemeId()); }
	 * 
	 * catch (Exception e) { e.printStackTrace(); GLOGGER.error(
	 * "Exception Occurred in registerPatientCase() in AnnualCheckUpDAOImpl class."
	 * +e.getMessage()); } return i;
	 * 
	 * }
	 */
	/*@Override
	public int registerPatientCase(AnnualCheckUpVo annualCheckUpVo) throws Exception 
	{
		String lStrCaseId=null;
		EhfAnnualCaseDtls ehfAnnualCaseDtls= null; 
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		Date lCurrentDate=sdfw.parse(annualCheckUpVo.getCrtDt());
		int i=0;
		try 
		{
			String liNextVal = null;
			if(annualCheckUpVo.getSchemeId()!=null && config.getString("Scheme.ap").equalsIgnoreCase(annualCheckUpVo.getSchemeId()))
			{
				liNextVal = "AP"+getSequence("CASE_ID_AP");
			}
			else if(annualCheckUpVo.getSchemeId()!=null && config.getString("Scheme.tg").equalsIgnoreCase(annualCheckUpVo.getSchemeId()))
			{
				liNextVal = "TG"+getSequence("CASE_ID_TG");
			}
			else if(annualCheckUpVo.getSchemeId()!=null && "1".equalsIgnoreCase(annualCheckUpVo.getSchemeId()))
			{
				liNextVal = getSequence("CASE_ID");
			}
			annualCheckUpVo.setCaseId(liNextVal);
			lStrCaseId=annualCheckUpVo.getCaseId();
			
			ehfAnnualCaseDtls=new EhfAnnualCaseDtls();
			ehfAnnualCaseDtls.setCaseId(liNextVal);
			ehfAnnualCaseDtls.setCaseHospCode(annualCheckUpVo.getHospitalId());
			ehfAnnualCaseDtls.setCasePatientNo(annualCheckUpVo.getPatientId());
			ehfAnnualCaseDtls.setCaseRegnDate(caseRegnDate);
			ehfAnnualCaseDtls.setCrtUsr(annualCheckUpVo.getCrtUsr());
			ehfAnnualCaseDtls.setCrtDt(crtDt);
			ehfAnnualCaseDtls.setPhaseId(annualCheckUpVo.getPhaseId());
			ehfAnnualCaseDtls.setSchemeId(annualCheckUpVo.getSchemeId());
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			GLOGGER.error("Exception Occurred in registerPatientCase() in AnnualCheckUpDAOImpl class."+e.getMessage());
		}
		return i;
		
	}*/
	@Override
	public List<AnnualCheckUpVo> getAnnualPatientDtls(String hospId,String  roleId,String userState) {
		
		
		List<GenericDAOQueryCriteria> crList = new ArrayList<GenericDAOQueryCriteria>();
		crList.add(new GenericDAOQueryCriteria("regHospId", hospId,
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		crList.add(new GenericDAOQueryCriteria("regHospDate", "",
				GenericDAOQueryCriteria.CriteriaOperator.ASC));
        if((userState!=null && !"".equalsIgnoreCase(userState)) && userState.equalsIgnoreCase("CD203")){
			
			List<String> li=new ArrayList<String>();
			li.add("CD201");
			li.add("CD202");
			crList.add(new GenericDAOQueryCriteria("schemeId",li,GenericDAOQueryCriteria.CriteriaOperator.IN));	
			
		}else if(userState!=null && !"".equalsIgnoreCase(userState)){
			crList.add(new GenericDAOQueryCriteria("schemeId",userState,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));	
			
		}
		List<EhfAnnualPatientDtls> annualList = genericDao.findAllByCriteria(
				EhfAnnualPatientDtls.class, crList);

		int st_index = 0;
		List<AnnualCheckUpVo> annList = new ArrayList<AnnualCheckUpVo>();

		if (st_index == 1) {
			st_index = 0;

		}
		if (annualList != null && annualList.size() > 0) {

			if (config.getString("Group.Medco").equalsIgnoreCase(roleId)) {
				for (int i = 0; i < annualList.size(); i++) {
					EhfAnnualCaseDtls eacd = null;
					eacd = genericDao.findById(EhfAnnualCaseDtls.class,
							String.class, annualList.get(i).getAhcId());
					if (((eacd != null && !"".equalsIgnoreCase(eacd.getAhcId())) && eacd
							.getAhcId().equalsIgnoreCase(
									annualList.get(i).getAhcId()))
							&& ((eacd.getAhcStatus() != null
									&& !"".equalsIgnoreCase(eacd.getAhcStatus()) && (eacd
									.getAhcStatus().equalsIgnoreCase(config
									.getString("AHC-CASE-REG")))) || ((eacd
									.getAhcStatus() != null
									&& !"".equalsIgnoreCase(eacd.getAhcStatus()) && (eacd
									.getAhcStatus().equalsIgnoreCase(config
									.getString("AHC-DTL-SAVED"))))))) {

						AnnualCheckUpVo vo = new AnnualCheckUpVo();

						vo.setPatientNo(annualList.get(i).getAhcId());
						vo.setPatientName(annualList.get(i).getName());
						vo.setPatientNo(annualList.get(i).getAhcId());
						vo.setName(annualList.get(i).getName());
						vo.setEmployeeNo(annualList.get(i).getEmployeeNo());

						if (annualList.get(i).getDistrictCode() != null
								&& !"".equalsIgnoreCase(annualList.get(i)
										.getDistrictCode())) {
							List<GenericDAOQueryCriteria> critList = new ArrayList<GenericDAOQueryCriteria>();
							critList.add(new GenericDAOQueryCriteria(
									"id.locId",
									annualList.get(i).getDistrictCode(),
									GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
							List<EhfmLocations> dtls = genericDao
									.findAllByCriteria(EhfmLocations.class,
											critList);

							if (dtls != null && dtls.size() > 0) {
								vo.setDistrict(dtls.get(0).getLocName());

							}

						}

						vo.setGender(annualList.get(i).getGender());
						vo.setAge((annualList.get(i).getAge()).toString());
						vo.setRegistrationDate(annualList.get(i)
								.getRegHospDate().toString());
						annList.add(vo);
					}
				}

			}

			if (config.getString("Group.Mithra").equalsIgnoreCase(roleId)) {
				for (int i = 0; i < annualList.size(); i++) {
					EhfAnnualCaseDtls eacd = null;
					eacd = genericDao.findById(EhfAnnualCaseDtls.class,
							String.class, annualList.get(i).getAhcId());
					if (((eacd != null && !"".equalsIgnoreCase(eacd.getAhcId())) && eacd
							.getAhcId().equalsIgnoreCase(
									annualList.get(i).getAhcId()))
							&& ((eacd.getAhcStatus() != null && !""
									.equalsIgnoreCase(eacd.getAhcStatus())) && eacd
									.getAhcStatus().equalsIgnoreCase(
											config.getString("AHC-CASE-REG")))) {

						AnnualCheckUpVo vo = new AnnualCheckUpVo();

						vo.setPatientNo(annualList.get(i).getAhcId());
						vo.setPatientName(annualList.get(i).getName());
						vo.setPatientNo(annualList.get(i).getAhcId());
						vo.setName(annualList.get(i).getName());
						vo.setEmployeeNo(annualList.get(i).getEmployeeNo());

						if (annualList.get(i).getDistrictCode() != null
								&& !"".equalsIgnoreCase(annualList.get(i)
										.getDistrictCode())) {
							List<GenericDAOQueryCriteria> critList = new ArrayList<GenericDAOQueryCriteria>();
							critList.add(new GenericDAOQueryCriteria(
									"id.locId",
									annualList.get(i).getDistrictCode(),
									GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
							List<EhfmLocations> dtls = genericDao
									.findAllByCriteria(EhfmLocations.class,
											critList);

							if (dtls != null && dtls.size() > 0) {
								vo.setDistrict(dtls.get(0).getLocName());

							}

						}

						vo.setGender(annualList.get(i).getGender());
						vo.setAge((annualList.get(i).getAge()).toString());
						vo.setRegistrationDate(annualList.get(i)
								.getRegHospDate().toString());
						annList.add(vo);
					}
				}
			}
		}

		return annList;
	}

	@Override
	public EhfAnnualPatientDtls getPatientDtls(String patienId) {
		// TODO Auto-generated method stub
		EhfAnnualPatientDtls epd = genericDao.findById(
				EhfAnnualPatientDtls.class, String.class, patienId);

		return epd;
	}

	/**
	 * 
	 * ;
	 * 
	 * @param String
	 *            ahcId
	 * @return EhfAnnualPatientDtls ehfAnnualPatientDtls
	 * @function This Method is Used For retrieving PatientDetails for given
	 *           ahcId
	 */
	@Override
	public EhfAnnualPatientDtls getPatientDetails(String ahcId)
			throws Exception {
		EhfAnnualPatientDtls ehfAnnualPatientDtls = new EhfAnnualPatientDtls();
		try {
			ehfAnnualPatientDtls = genericDao.findById(
					EhfAnnualPatientDtls.class, String.class, ahcId);
		} catch (Exception e) {
			GLOGGER.error("Exception Occurred in getPatientDetails() in AnnualCheckUpDAOImpl class."
					+ e.getMessage());
		}
		return ehfAnnualPatientDtls;
	}

	/**
	 * ;
	 * 
	 * @param String
	 *            relationId
	 * @return String relationName
	 * @function This Method is Used For getting relationName for given
	 *           relationId from RelationMst table
	 */
	@Override
	public String getRelationName(String relationId) throws Exception {
		String relationName = null;
		// Long relationIdVal=new Long(relationId);
		Integer relationIdVal = Integer.valueOf(relationId);
		EhfmRelationMst relationMst = genericDao.findById(
				EhfmRelationMst.class, Integer.class, relationIdVal);
		relationName = relationMst.getRelationName();
		return relationName;
	}

	@Override
	public String saveAhcDetails(AnnualCheckUpVo annualCheckUpVo) {
		// TODO Auto-generated method stub

		SessionFactory factory = hibernateTemplate.getSessionFactory();
		Session session = factory.openSession();
		SimpleDateFormat sdfw = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		
		// String lStrChronicCaseId=null;
		
		//Saving AHC General Investigation Attachments
		if(annualCheckUpVo.getAhcId()!=null && !"".equalsIgnoreCase(annualCheckUpVo.getAhcId()))
			{
				EhfAnnualPatientDtls ehfAnnualPatientDtls =getPatientDtls(annualCheckUpVo.getAhcId());
				
				if(ehfAnnualPatientDtls!=null 
						&& ehfAnnualPatientDtls.getRegHospId()!=null 
						&& !"".equalsIgnoreCase(ehfAnnualPatientDtls.getRegHospId())
						/*&& config.getString("HOSP_NIMS").equalsIgnoreCase(ehfAnnualPatientDtls.getRegHospId())*/)
					{
						try
							{
								if(annualCheckUpVo.getTestsList()!=null && annualCheckUpVo.getTestsList().size()>0)
									{
										StringBuffer query =new StringBuffer();
										query.append(" select testId as ID from EhfAhcPatientTest where patientId ='"+annualCheckUpVo.getAhcId()+"' ");
										List<LabelBean> labelBeanLst=genericDao.executeHQLQueryList(LabelBean.class, query.toString());
												 
										for(AnnualCheckUpVo  test : annualCheckUpVo.getTestsList())
											{
												if(test.getICDcatName()!=null)
													{
														if(labelBeanLst==null || (labelBeanLst!=null && labelBeanLst.size()==0))
															{
																EhfAhcPatientTest ehfAhcPatientTest=new EhfAhcPatientTest();
																
																ehfAhcPatientTest.setSno(Long.parseLong(getSequence("EHF_AHC_INVEST_SEQ")));
																ehfAhcPatientTest.setPatientId(annualCheckUpVo.getAhcId());
																ehfAhcPatientTest.setAttachPath(test.getFilePath());
																ehfAhcPatientTest.setTestId(test.getICDcatName());
																ehfAhcPatientTest.setTestName(test.getICDsubCatName());
																ehfAhcPatientTest.setInvestPrice(test.getCatName());
																ehfAhcPatientTest.setCrtUsr(annualCheckUpVo.getCrtUsr());
																ehfAhcPatientTest.setCrtDt(sdfw.parse(annualCheckUpVo.getCrtDt()));
																
																genericDao.save(ehfAhcPatientTest);
															}
														else if(labelBeanLst!=null && labelBeanLst.size()>0)
															{
																int count=0;
																for(int j=0;j<labelBeanLst.size();j++)
																	{
																		if(labelBeanLst.get(0)!=null && labelBeanLst.get(0).getID()!=null)
																			{
																				if(labelBeanLst.get(0).getID().equalsIgnoreCase(annualCheckUpVo.getICDcatName()))
																					count++;
																			}
																	}
																if(count==0)
																	{
																		EhfAhcPatientTest ehfAhcPatientTest=new EhfAhcPatientTest();
																		
																		ehfAhcPatientTest.setSno(Long.parseLong(getSequence("EHF_AHC_INVEST_SEQ")));
																		ehfAhcPatientTest.setPatientId(annualCheckUpVo.getAhcId());
																		ehfAhcPatientTest.setAttachPath(test.getFilePath());
																		ehfAhcPatientTest.setTestId(test.getICDcatName());
																		ehfAhcPatientTest.setTestName(test.getICDsubCatName());
																		ehfAhcPatientTest.setInvestPrice(test.getCatName());
																		ehfAhcPatientTest.setCrtUsr(annualCheckUpVo.getCrtUsr());
																		ehfAhcPatientTest.setCrtDt(sdfw.parse(annualCheckUpVo.getCrtDt()));
																		
																		genericDao.save(ehfAhcPatientTest);
																	}
															}
														
													}
											}
									}
								
								if(annualCheckUpVo.getConsultList()!=null && annualCheckUpVo.getConsultList().size()>0)
								{
									StringBuffer query =new StringBuffer();
									query.append(" select consultFilePath as ID from EhfAhcConsultationDtls where ahcId ='"+annualCheckUpVo.getAhcId()+"' ");
									List<LabelBean> labelBeanLst=genericDao.executeHQLQueryList(LabelBean.class, query.toString());
											 
									for(AnnualCheckUpVo  test : annualCheckUpVo.getConsultList())
										{
											if(test.getFilePath()!=null)
												{
													if(labelBeanLst==null || (labelBeanLst!=null && labelBeanLst.size()==0))
														{
															
														EhfAhcConsultationDtls ehfAhcConsultationDtls= new EhfAhcConsultationDtls();
														ehfAhcConsultationDtls.setSno(Long.parseLong(getSequence("EHF_AHC_CONSULT_SEQ")));
														ehfAhcConsultationDtls.setAhcId(annualCheckUpVo.getAhcId());
														ehfAhcConsultationDtls.setConsultationSpec(test.getICDsubCatName());
														ehfAhcConsultationDtls.setConsultDoctor(test.getICDcatName());
														ehfAhcConsultationDtls.setConsultFilePath(test.getFilePath());
														ehfAhcConsultationDtls.setRemarks(test.getCatName());
														ehfAhcConsultationDtls.setCrtUsr(annualCheckUpVo.getCrtUsr());
														ehfAhcConsultationDtls.setCrtDt(sdfw.parse(annualCheckUpVo.getCrtDt()));
														
															genericDao.save(ehfAhcConsultationDtls);
														}
													else if(labelBeanLst!=null && labelBeanLst.size()>0)
														{
															int count=0;
															for(int j=0;j<labelBeanLst.size();j++)
																{
																	if(labelBeanLst.get(0)!=null && labelBeanLst.get(0).getID()!=null)
																		{
																			if(labelBeanLst.get(0).getID().equalsIgnoreCase(annualCheckUpVo.getFilePath()))
																				count++;
																		}
																}
															if(count==0)
																{
																EhfAhcConsultationDtls ehfAhcConsultationDtls= new EhfAhcConsultationDtls();
																ehfAhcConsultationDtls.setAhcId(annualCheckUpVo.getAhcId());
																ehfAhcConsultationDtls.setConsultationSpec(test.getICDcatName());
																ehfAhcConsultationDtls.setConsultDoctor(test.getICDsubCatName());
																ehfAhcConsultationDtls.setConsultFilePath(test.getFilePath());
																ehfAhcConsultationDtls.setRemarks(test.getCatName());
																ehfAhcConsultationDtls.setCrtUsr(annualCheckUpVo.getCrtUsr());
																ehfAhcConsultationDtls.setCrtDt(sdfw.parse(annualCheckUpVo.getCrtDt()));
																	
																	genericDao.save(ehfAhcConsultationDtls);
																}
														}
													
												}
										}
								}
								if(annualCheckUpVo.getConsultRemLst()!=null && annualCheckUpVo.getConsultRemLst().length>0){
									for(String id : annualCheckUpVo.getConsultRemLst()){
										EhfAhcConsultationDtls ehfAhcConsultationDtls=null;
										if(id!=null && !"".equalsIgnoreCase(id))
											 ehfAhcConsultationDtls = genericDao.findById(EhfAhcConsultationDtls.class, Long.class, Long.parseLong(id));
									if(ehfAhcConsultationDtls!=null)
										genericDao.delete(ehfAhcConsultationDtls);
									}
								}
								if(annualCheckUpVo.getInvestRemLst()!=null && annualCheckUpVo.getInvestRemLst().length>0){
									for(String id : annualCheckUpVo.getInvestRemLst()){
										List<EhfAhcPatientTest> ehfAhcPatientTest=null;
										if(id!=null && !"".equalsIgnoreCase(id)){
											List<GenericDAOQueryCriteria> critList= new ArrayList<GenericDAOQueryCriteria>();
											critList.add(new GenericDAOQueryCriteria("patientId", annualCheckUpVo.getAhcId(), GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
											critList.add(new GenericDAOQueryCriteria("testId", id, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
											ehfAhcPatientTest = genericDao.findAllByCriteria(EhfAhcPatientTest.class, critList);
										}
										
									if(ehfAhcPatientTest!=null && ehfAhcPatientTest.size()>0){
										genericDao.delete(ehfAhcPatientTest.get(0));
									}
										
									}
								}
								
								/*if(annualCheckUpVo.getTestCheck()!=null && annualCheckUpVo.getTestCheck().length>0){
									EhfAhcPatientInvest ehfAhcPatientInvest =new EhfAhcPatientInvest();
									ehfAhcPatientInvest.setAhcId(annualCheckUpVo.getAhcId());
									Field[] fields = ehfAhcPatientInvest.getClass().getDeclaredFields();
									List<String> testIds=new ArrayList<String>();
									for(int i=0;i<annualCheckUpVo.getTestCheck().length;i++){
										String id=annualCheckUpVo.getTestCheck()[i].replaceAll("_", "");
										id=id.toLowerCase();
										testIds.add(id);
									}
									 
								}*/
								
								/*if(annualCheckUpVo.getSaveFlag()!=null && "Submit".equalsIgnoreCase(annualCheckUpVo.getSaveFlag()))
									{
										EhfAhcPatientInvest ehfAhcPatientInvest =new EhfAhcPatientInvest();
										ehfAhcPatientInvest.setAhcId(annualCheckUpVo.getAhcId());
										ehfAhcPatientInvest.setT1Com("Y");
										ehfAhcPatientInvest.setT2Com("Y");
										ehfAhcPatientInvest.setT3Com("Y");
										ehfAhcPatientInvest.setT4Com("Y");
										ehfAhcPatientInvest.setT5Com("Y");
										ehfAhcPatientInvest.setT6Com("Y");
										ehfAhcPatientInvest.setT7Com("Y");
										ehfAhcPatientInvest.setT8Com("Y");
										ehfAhcPatientInvest.setT9Com("Y");
										ehfAhcPatientInvest.setT10Com("Y");
										ehfAhcPatientInvest.setT11Com("Y");
										ehfAhcPatientInvest.setT12Com("Y");
										ehfAhcPatientInvest.setT13Com("Y");
										ehfAhcPatientInvest.setT14Com("Y");
										ehfAhcPatientInvest.setT15Com("Y");
										ehfAhcPatientInvest.setT16Com("Y");
										ehfAhcPatientInvest.setT17Com("Y");
										ehfAhcPatientInvest.setT18Com("Y");
										ehfAhcPatientInvest.setT19Com("Y");
										ehfAhcPatientInvest.setT20Com("Y");
										ehfAhcPatientInvest.setT21Com("Y");
										ehfAhcPatientInvest.setT22Com("Y");
										ehfAhcPatientInvest.setT23Com("Y");
										ehfAhcPatientInvest.setT24Com("Y");
										ehfAhcPatientInvest.setT25Com("Y");
										
										if(ehfAnnualPatientDtls.getGender()!=null &&
												!"".equalsIgnoreCase(ehfAnnualPatientDtls.getGender()))
											{
												if("F".equalsIgnoreCase(ehfAnnualPatientDtls.getGender()))
													{
														ehfAhcPatientInvest.setT26Fem("Y");
														ehfAhcPatientInvest.setT27Fem("Y");
														ehfAhcPatientInvest.setT28Fem("Y");
														ehfAhcPatientInvest.setGender(config.getString("FemaleStat"));
													}
												else if("M".equalsIgnoreCase(ehfAnnualPatientDtls.getGender()))
													{
														ehfAhcPatientInvest.setGender(config.getString("MaleStat"));
														ehfAhcPatientInvest.setT26Fem("N");
														ehfAhcPatientInvest.setT27Fem("N");
														ehfAhcPatientInvest.setT28Fem("N");
													}
											}
										if(ehfAnnualPatientDtls.getSchemeId()!=null && 
												!"".equalsIgnoreCase(ehfAnnualPatientDtls.getSchemeId()))
											{
												ehfAhcPatientInvest.setSchemeId(ehfAnnualPatientDtls.getSchemeId());
											}
										ehfAhcPatientInvest.setCrtUsr(annualCheckUpVo.getCrtUsr());
										ehfAhcPatientInvest.setCrtDt(sdfw.parse(annualCheckUpVo.getCrtDt()));
										
										genericDao.save(ehfAhcPatientInvest);
									}*/
								
								
							}
						catch(Exception e)
							{
								e.printStackTrace();
								GLOGGER.error("Exception occured in Saving the AHC Patient Investigation Attachments "+e.getMessage());
							}
					}

			}
		
		
		try {
			Date lCurrentDate = sdfw.parse(annualCheckUpVo.getCrtDt());

			EhfAhcHospDiagnosis ehfmAhcHospDiagnosis = genericDao.findById(
					EhfAhcHospDiagnosis.class, String.class,
					annualCheckUpVo.getAhcId());
			if (ehfmAhcHospDiagnosis == null) {
				ehfmAhcHospDiagnosis = new EhfAhcHospDiagnosis();
				ehfmAhcHospDiagnosis.setCrtUsr(annualCheckUpVo.getCrtUsr());
				ehfmAhcHospDiagnosis.setCrtDt(lCurrentDate);
			} else {
				ehfmAhcHospDiagnosis.setLstUpdDt(lCurrentDate);
				ehfmAhcHospDiagnosis.setLstUpdUsr(annualCheckUpVo.getCrtUsr());
			}

			if (annualCheckUpVo.getAhcId() != null) {
				String lStrPastHistory = "";
				if (annualCheckUpVo.getPastHistory() != null) {
					for (int i = 0; i < annualCheckUpVo.getPastHistory().length; i++) {
						lStrPastHistory = lStrPastHistory
								+ annualCheckUpVo.getPastHistory()[i];
						if (i != annualCheckUpVo.getPastHistory().length - 1) {
							lStrPastHistory = lStrPastHistory + "~";
						}
					}
				}

				EhfAhcPersonalHistory ehfmAhcPersonalHistory = genericDao
						.findById(EhfAhcPersonalHistory.class, String.class,
								annualCheckUpVo.getAhcId());
				if (ehfmAhcPersonalHistory != null) {
					ehfmAhcPersonalHistory.setLstUpdDt(lCurrentDate);
					ehfmAhcPersonalHistory.setLstUpdUsr(annualCheckUpVo
							.getCrtUsr());
				} else {
					ehfmAhcPersonalHistory = new EhfAhcPersonalHistory();
					ehfmAhcPersonalHistory.setCrtDt(lCurrentDate);
					ehfmAhcPersonalHistory.setCrtUsr(annualCheckUpVo
							.getCrtUsr());
				}
				ehfmAhcPersonalHistory.setAhcId(annualCheckUpVo.getAhcId());

				if(annualCheckUpVo.getPersonalHis()!=null && !"".equalsIgnoreCase(annualCheckUpVo.getPersonalHis())){
				String[] result = annualCheckUpVo.getPersonalHis().split(
						"#");
				for (int x = 0; x < result.length; x++) {
					String[] result1 = result[x].split("~");
					if (result1[0].equalsIgnoreCase("PR1"))
						ehfmAhcPersonalHistory.setAppetite(result1[1]);
					else if (result1[0].equalsIgnoreCase("PR2"))
						ehfmAhcPersonalHistory.setDiet(result1[1]);
					else if (result1[0].equalsIgnoreCase("PR3"))
						ehfmAhcPersonalHistory.setBowels(result1[1]);
					else if (result1[0].equalsIgnoreCase("PR4"))
						ehfmAhcPersonalHistory.setNutrition(result1[1]);
					else if (result1[0].equalsIgnoreCase("PR5"))
						ehfmAhcPersonalHistory.setKnownAllergies(result1[1]);
					else if (result1[0].equalsIgnoreCase("PR6"))
						ehfmAhcPersonalHistory.setAddictions(result1[1]);
					else if (result1[0].equalsIgnoreCase("PR7"))
						ehfmAhcPersonalHistory.setMaritalStatus(result1[1]);

				}
				}
				genericDao.save(ehfmAhcPersonalHistory);

				EhfAhcExamFind ehfmAhcExamFind = genericDao.findById(
						EhfAhcExamFind.class, String.class,
						annualCheckUpVo.getAhcId());
				if (ehfmAhcExamFind == null) {
					ehfmAhcExamFind = new EhfAhcExamFind();
					ehfmAhcExamFind.setCrtDt(lCurrentDate);
					ehfmAhcExamFind.setCrtUsr(annualCheckUpVo.getCrtUsr());
				} else {
					ehfmAhcExamFind.setLstUpdDt(lCurrentDate);
					ehfmAhcExamFind.setLstUpdUsr(annualCheckUpVo.getCrtUsr());
				}
				ehfmAhcExamFind.setAhcId(annualCheckUpVo.getAhcId());
				if(annualCheckUpVo.getExamFndsVal()!=null && !"".equalsIgnoreCase(annualCheckUpVo.getExamFndsVal())){
				String[] result2 = annualCheckUpVo.getExamFndsVal().split("#");
				for (int x = 0; x < result2.length; x++) {
					String[] result1 = result2[x].split("~");
					if (result1.length > 1) {
						if (result1[0].equalsIgnoreCase("GE3"))
							ehfmAhcExamFind.setBmi(result1[1]);
						else if (result1[0].equalsIgnoreCase("GE4"))
							ehfmAhcExamFind.setPallor(result1[1]);
						else if (result1[0].equalsIgnoreCase("GE5"))
							ehfmAhcExamFind.setCyanosis(result1[1]);
						else if (result1[0].equalsIgnoreCase("GE6"))
							ehfmAhcExamFind.setClubOfFingrToes(result1[1]);
						else if (result1[0].equalsIgnoreCase("GE7"))
							ehfmAhcExamFind.setLymphadenopathy(result1[1]);
						else if (result1[0].equalsIgnoreCase("GE8"))
							ehfmAhcExamFind.setOedemaInFeet(result1[1]);
						else if (result1[0].equalsIgnoreCase("GE9"))
							ehfmAhcExamFind.setMalnutrition(result1[1]);
						else if (result1[0].equalsIgnoreCase("GE10"))
							ehfmAhcExamFind.setDehydration(result1[1]);
						else if (result1[0].equalsIgnoreCase("GE11"))
							ehfmAhcExamFind.setTemperature(result1[1]);
						else if (result1[0].equalsIgnoreCase("GE12"))
							ehfmAhcExamFind.setPluseRate(result1[1]);
						else if (result1[0].equalsIgnoreCase("GE13"))
							ehfmAhcExamFind.setRespirationRate(result1[1]);
						else if (result1[0].equalsIgnoreCase("GE14"))
							ehfmAhcExamFind.setBpLt(result1[1]);
						else if (result1[0].equalsIgnoreCase("GE1"))
							ehfmAhcExamFind.setHeight(result1[1]);
						else if (result1[0].equalsIgnoreCase("GE2"))
							ehfmAhcExamFind.setWeight(result1[1]);
						else if (result1[0].equalsIgnoreCase("GE15"))
							ehfmAhcExamFind.setBpRt(result1[1]);
					}
				}
				genericDao.save(ehfmAhcExamFind);
				}
				String lStrExamFind = "";
				if (annualCheckUpVo.getExaminationFnd() != null) {
					for (int i = 0; i < annualCheckUpVo.getExaminationFnd().length; i++) {
						lStrExamFind = lStrExamFind
								+ annualCheckUpVo.getExaminationFnd()[i];
						if (i != annualCheckUpVo.getExaminationFnd().length - 1) {
							lStrExamFind = lStrExamFind + "~";
						}
					}
				}
				ehfmAhcHospDiagnosis.setAhcId(annualCheckUpVo.getAhcId());

				ehfmAhcHospDiagnosis.setPastHistory(lStrPastHistory);
				ehfmAhcHospDiagnosis.setHistoryIllness(annualCheckUpVo
						.getPresentHistory());
				ehfmAhcHospDiagnosis.setExamntnFindings(lStrExamFind);
				ehfmAhcHospDiagnosis.setDiagnosedBy(annualCheckUpVo.getDiagnosedBy());

				String lStrValue = "";
				if (annualCheckUpVo.getPersonalHistory() != null
						&& annualCheckUpVo.getPersonalHistory().length > 0) {
					for (int i = 0; i < annualCheckUpVo.getPersonalHistory().length; i++) {
						lStrValue = lStrValue
								+ annualCheckUpVo.getPersonalHistory()[i];
						if (i != annualCheckUpVo.getPersonalHistory().length - 1) {
							lStrValue = lStrValue + "~";
						}
					}
				}
				ehfmAhcHospDiagnosis.setPersonalHistory(lStrValue);
				lStrValue = "";
				if (annualCheckUpVo.getFamilyHistory() != null) {
					for (int i = 0; i < annualCheckUpVo.getFamilyHistory().length; i++) {
						lStrValue = lStrValue
								+ annualCheckUpVo.getFamilyHistory()[i];
						if (i != annualCheckUpVo.getFamilyHistory().length - 1) {
							lStrValue = lStrValue + "~";
						}
					}
				}
				ehfmAhcHospDiagnosis.setFamilyHistory(lStrValue);
				ehfmAhcHospDiagnosis.setPastHistoryOthr(annualCheckUpVo
						.getPastHistryOthr());
				ehfmAhcHospDiagnosis.setPastHistoryCancers(annualCheckUpVo
						.getPastHistryCancers());
				ehfmAhcHospDiagnosis.setPastHistoryEndDis(annualCheckUpVo
						.getPastHistryEndDis());
				ehfmAhcHospDiagnosis.setPastHistorySurg(annualCheckUpVo
						.getPastHistrySurg());
				ehfmAhcHospDiagnosis.setFamilyHistoryOthr(annualCheckUpVo
						.getFamilyHistoryOthr());

			}
			genericDao.save(ehfmAhcHospDiagnosis);
			
			

			
			EhfAnnualCaseDtls caseDtls = genericDao.findById(
					EhfAnnualCaseDtls.class, String.class,
					annualCheckUpVo.getAhcId());

			if (caseDtls != null) {
				caseDtls.setAhcStatus(annualCheckUpVo.getStatus());
				caseDtls.setLstUpdDt(new Date());
				caseDtls.setCrtUsr(annualCheckUpVo.getCrtUsr());
				caseDtls.setTotPckgAmt(calculateAhcPckAmt(annualCheckUpVo.getAhcId()));
			}
			genericDao.save(caseDtls);

		} catch (Exception e) {
			System.out
					.println("Exception Occurred in saveCaseDetails() in PatientDaoImpl class."
							+ e.getMessage());
			e.printStackTrace();

		} finally {
			session.close();
			factory.close();
		}
		return annualCheckUpVo.getAhcId();

	}

	public String calculateAhcPckAmt(String ahcId){
		StringBuffer query= new StringBuffer();
		int amt=0;
		try{
		query.append(" select investPrice as ID from EhfAhcPatientTest where patientId ='"+ahcId+"' ");
		List<LabelBean> labelBeanLst=genericDao.executeHQLQueryList(LabelBean.class, query.toString());
		for(LabelBean lb : labelBeanLst){
			if(lb.getID()!=null && !"".equalsIgnoreCase(lb.getID())){
				amt=amt+Integer.parseInt(lb.getID());
			}
			
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(amt);
		
	}
	
	
	/**
	 * ;
	 * 
	 * @param String
	 *            locId
	 * @return String locName
	 * @function This Method is Used For getting Location Name for given
	 *           Location Id
	 */
	@Override
	public String getLocationName(String locId) throws Exception {
		String locName = null;
		
		
		try {
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			criteriaList.add(new GenericDAOQueryCriteria("id.locId", locId,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfmLocations> ehfmLocations = genericDao.findAllByCriteria(
					EhfmLocations.class, criteriaList);
			for (EhfmLocations el : ehfmLocations) {
				locName = el.getLocName();
			}
		} catch (Exception e) {
			GLOGGER.error("Exception Occurred in getLocationName() in AnnualCheckUpDAOImpl class."
					+ e.getMessage());
		}
		return locName;
	}

	@Override
	public List<AnnualCheckUpVo> searchAnnualPatientDtls(String patID,
			String PatName, String healthCardNo, String state, String district,
			String fromDate, String toDate, String userState, String hospId,
			String roleId) {
		List<AnnualCheckUpVo> annList = new ArrayList<AnnualCheckUpVo>();

		try {
			List<GenericDAOQueryCriteria> critList = new ArrayList<GenericDAOQueryCriteria>();
			if (patID != null && !"".equalsIgnoreCase(patID)) {

				critList.add(new GenericDAOQueryCriteria(
						"ahcId",
						patID.toUpperCase(),
						GenericDAOQueryCriteria.CriteriaOperator.LIKE_IN_BETWEEN));
			}

			if (PatName != null && !"".equalsIgnoreCase(PatName)) {
				critList.add(new GenericDAOQueryCriteria(
						"name",
						PatName.toUpperCase(),
						GenericDAOQueryCriteria.CriteriaOperator.LIKE_IN_BETWEEN));

			}
			if((userState!=null && !"".equalsIgnoreCase(userState)) && userState.equalsIgnoreCase("CD203")){
				
				List<String> li=new ArrayList<String>();
				li.add("CD201");
				li.add("CD202");
				critList.add(new GenericDAOQueryCriteria("schemeId",li,GenericDAOQueryCriteria.CriteriaOperator.IN));	
				
			}else if(userState!=null && !"".equalsIgnoreCase(userState)){
				critList.add(new GenericDAOQueryCriteria("schemeId",userState,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));	
				
			}
			if (healthCardNo != null && !"".equalsIgnoreCase(healthCardNo)) {

				critList.add(new GenericDAOQueryCriteria(
						"cardNo",
						healthCardNo.toUpperCase(),
						GenericDAOQueryCriteria.CriteriaOperator.LIKE_IN_BETWEEN));
			}

			if (state != null && !"-1".equalsIgnoreCase(state)) {

				critList.add(new GenericDAOQueryCriteria("state", state,
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));

			}

			if (district != null && !"-1".equalsIgnoreCase(district)) {
				critList.add(new GenericDAOQueryCriteria("districtCode",
						district,
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));

			}
			if ((fromDate != null && !"".equalsIgnoreCase(fromDate))
					&& (toDate != null && !"".equalsIgnoreCase(toDate))) {

				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				Date frmDt = formatter.parse(fromDate);
				Date toDt = formatter.parse(toDate);

				critList.add(new GenericDAOQueryCriteria("regHospDate", frmDt,
						GenericDAOQueryCriteria.CriteriaOperator.GE));

				critList.add(new GenericDAOQueryCriteria("regHospDate", toDt,
						GenericDAOQueryCriteria.CriteriaOperator.LE));
				
				critList.add(new GenericDAOQueryCriteria("regHospDate", "",
						GenericDAOQueryCriteria.CriteriaOperator.ASC));

			}

			if (hospId != null && !"".equalsIgnoreCase(hospId)) {

				critList.add(new GenericDAOQueryCriteria("regHospId", hospId,
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));

			}

			List<EhfAnnualPatientDtls> dtls = genericDao.findAllByCriteria(
					EhfAnnualPatientDtls.class, critList);

			critList.removeAll(critList);

			if (dtls != null && dtls.size() > 0) {

				if (roleId.equalsIgnoreCase(config.getString("Group.Medco"))) {
					for (int i = 0; i < dtls.size(); i++) {

						AnnualCheckUpVo vo = new AnnualCheckUpVo();
						EhfAnnualCaseDtls eacd = genericDao.findById(
								EhfAnnualCaseDtls.class, String.class, dtls
										.get(i).getAhcId());

						if (((eacd != null && !"".equalsIgnoreCase(eacd
								.getAhcId())) && eacd.getAhcId()
								.equalsIgnoreCase(dtls.get(i).getAhcId()))
								&& (eacd.getAhcStatus().equalsIgnoreCase(
										config.getString("AHC-CASE-REG")) || eacd
										.getAhcStatus()
										.equalsIgnoreCase(
												config.getString("AHC-DTL-SAVED")))) {

							vo.setPatientNo(dtls.get(i).getAhcId());
							vo.setName(dtls.get(i).getName());
							vo.setEmployeeNo(dtls.get(i).getEmployeeNo());
							vo.setGender(dtls.get(i).getGender());
							vo.setAge(dtls.get(i).getAge().toString());
							vo.setDistrict(dtls.get(i).getDistrictCode());
							if (dtls.get(i).getDistrictCode() != null
									&& !"".equalsIgnoreCase(dtls.get(i)
											.getDistrictCode())) {
								List<GenericDAOQueryCriteria> critList1 = new ArrayList<GenericDAOQueryCriteria>();
								critList.add(new GenericDAOQueryCriteria(
										"id.locId",
										dtls.get(i).getDistrictCode(),
										GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								List<EhfmLocations> dtls1 = genericDao
										.findAllByCriteria(EhfmLocations.class,
												critList);

								if (dtls1 != null && dtls1.size() > 0) {
									vo.setDistrict(dtls1.get(0).getLocName());

								}

							}
							vo.setRegistrationDate(dtls.get(i).getRegHospDate()
									.toString());

							annList.add(vo);

						}

					}
				}

				if (roleId.equalsIgnoreCase(config.getString("Group.Mithra"))) {
					for (int i = 0; i < dtls.size(); i++) {

						AnnualCheckUpVo vo = new AnnualCheckUpVo();
						EhfAnnualCaseDtls eacd = genericDao.findById(
								EhfAnnualCaseDtls.class, String.class, dtls
										.get(i).getAhcId());

						if (((eacd != null && !"".equalsIgnoreCase(eacd
								.getAhcId())) && eacd.getAhcId()
								.equalsIgnoreCase(dtls.get(i).getAhcId()))
								&& (eacd.getAhcStatus().equalsIgnoreCase(config
										.getString("AHC-CASE-REG")))) {
							vo.setPatientNo(dtls.get(i).getAhcId());
							vo.setName(dtls.get(i).getName());
							vo.setEmployeeNo(dtls.get(i).getEmployeeNo());
							vo.setGender(dtls.get(i).getGender());
							vo.setAge(dtls.get(i).getAge().toString());
							vo.setDistrict(dtls.get(i).getDistrictCode());
							if (dtls.get(i).getDistrictCode() != null
									&& !"".equalsIgnoreCase(dtls.get(i)
											.getDistrictCode())) {
								List<GenericDAOQueryCriteria> critList1 = new ArrayList<GenericDAOQueryCriteria>();
								critList.add(new GenericDAOQueryCriteria(
										"id.locId",
										dtls.get(i).getDistrictCode(),
										GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								List<EhfmLocations> dtls1 = genericDao
										.findAllByCriteria(EhfmLocations.class,
												critList);

								if (dtls1 != null && dtls1.size() > 0) {
									vo.setDistrict(dtls1.get(0).getLocName());

								}

							}
							vo.setRegistrationDate(dtls.get(i).getRegHospDate()
									.toString());
							annList.add(vo);

						}
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return annList;
	}

	@Override
	public List<AnnualCheckUpVo> getAHCClaimCases(String hospId, String roleId,String userState,String caseSearchFlag) {
		List<AnnualCheckUpVo> annClaimList = new ArrayList<AnnualCheckUpVo>();
		List<GenericDAOQueryCriteria> critList = new ArrayList<GenericDAOQueryCriteria>();
		

		
		
		if(roleId.equalsIgnoreCase(config.getString("Group.Mithra"))||roleId.equalsIgnoreCase(config.getString("Group.Medco"))){
			critList.add(new GenericDAOQueryCriteria("ahcHospCode", hospId,
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			
		}
       if((userState!=null && !"".equalsIgnoreCase(userState)) && userState.equalsIgnoreCase("CD203")){
			
			List<String> li=new ArrayList<String>();
			li.add("CD201");
			li.add("CD202");
			critList.add(new GenericDAOQueryCriteria("schemeId",li,GenericDAOQueryCriteria.CriteriaOperator.IN));	
			
		}else if(userState!=null && !"".equalsIgnoreCase(userState)){
			critList.add(new GenericDAOQueryCriteria("schemeId",userState,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));	
			
		}
       
       if(caseSearchFlag==null || (caseSearchFlag!=null && !caseSearchFlag.equalsIgnoreCase("Y")))
    		 {
       
				if ((roleId != null && !"".equalsIgnoreCase(roleId))
						&& config.getString("Group.Mithra").equalsIgnoreCase(roleId)) 
					{
						critList.add(new GenericDAOQueryCriteria("ahcStatus", config
								.getString("AHC-CLAIM_INTIATED"),
								GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					}
				if ((roleId != null && !"".equalsIgnoreCase(roleId))
						&& config.getString("Group.Medco").equalsIgnoreCase(roleId)) 
					{
						List<String> li=new ArrayList<String>();
						li.add(config.getString("AHC-MEDCO-SCR_CMPL"));
						//li.add(config.getString("AHC-TD-REC-REJ"));
						li.add(config.getString("AHC-TD-REC-PEN"));
						//li.add(config.getString("AHC-CH-REJ"));
						li.add( config.getString("AHC-CH-PEN"));
						critList.add(new GenericDAOQueryCriteria("ahcStatus", li,GenericDAOQueryCriteria.CriteriaOperator.IN));
					}
				if ((roleId != null && !"".equalsIgnoreCase(roleId))
						&& config.getString("Group.AHC-EX").equalsIgnoreCase(roleId)) 
					{
						critList.add(new GenericDAOQueryCriteria("ahcStatus", config.getString("AHC_MITHRA_FRWD"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					}
				if ((roleId != null && !"".equalsIgnoreCase(roleId))
						&& config.getString("Group.AHC-TD").equalsIgnoreCase(roleId)) 
					{	
						List<String> li=new ArrayList<String>();
						li.add(config.getString("AHC-EX-REC-APPRV"));
						li.add(config.getString("AHC-EX-REC-REJ"));
						li.add(config.getString("AHC-EX-REC-PEN"));
						li.add(config.getString("AHC-MED-TD-PEN-UPD"));
						critList.add(new GenericDAOQueryCriteria("ahcStatus", li,GenericDAOQueryCriteria.CriteriaOperator.IN));
					}
				if ((roleId != null && !"".equalsIgnoreCase(roleId))
						&& config.getString("Group.AHC-CH").equalsIgnoreCase(roleId)) 
					{
						List<String> li=new ArrayList<String>();
						li.add(config.getString("AHC-TD-REC-APPRV"));
						li.add(config.getString("AHC-MED-CH-PEN-UPD"));
						li.add(config.getString("AHC-TD-REC-REJ"));
						critList.add(new GenericDAOQueryCriteria("ahcStatus", li,GenericDAOQueryCriteria.CriteriaOperator.IN));
					}
				if ((roleId != null && !"".equalsIgnoreCase(roleId))
						&& config.getString("Group.AHC-ACO").equalsIgnoreCase(roleId)) 
					{
						critList.add(new GenericDAOQueryCriteria("ahcStatus", config.getString("AHC-CH-APPRV"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					}
				if ((roleId != null && !"".equalsIgnoreCase(roleId))
						&& config.getString("Group.AHC-EO").equalsIgnoreCase(roleId)) 
					{
			
						critList.add(new GenericDAOQueryCriteria("ahcStatus", config.getString("AHC-ACO-APPRV"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					}
    		 }
		critList.add(new GenericDAOQueryCriteria("ahcRegnDate","",
				GenericDAOQueryCriteria.CriteriaOperator.ASC));
		
		List<EhfAnnualCaseDtls> dtls = genericDao.findAllByCriteria(
				EhfAnnualCaseDtls.class, critList);

		if (dtls != null && dtls.size() > 0) {

			for (int i = 0; i < dtls.size(); i++) {

				EhfmCmbDtls cmbDtls = genericDao.findById(
						EhfmCmbDtls.class, String.class, dtls.get(i)
								.getAhcStatus());
				EhfAnnualPatientDtls patDtl = genericDao.findById(
						EhfAnnualPatientDtls.class, String.class, dtls.get(i)
								.getAhcId());

				if (patDtl != null) {

					AnnualCheckUpVo vo = new AnnualCheckUpVo();
					vo.setPatientNo(patDtl.getAhcId());
					vo.setName(patDtl.getName());
					//vo.setEmployeeNo(patDtl.getEmployeeNo());
					vo.setEmployeeNo(patDtl.getCardNo());

					if (patDtl.getDistrictCode() != null
							&& !"".equalsIgnoreCase(patDtl.getDistrictCode())) {

						List<GenericDAOQueryCriteria> crList = new ArrayList<GenericDAOQueryCriteria>();

						crList.add(new GenericDAOQueryCriteria("id.locId",
								patDtl.getDistrictCode(),
								GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
						List<EhfmLocations> loc = genericDao.findAllByCriteria(
								EhfmLocations.class, crList);
						if (loc != null && loc.size() > 0) {

							vo.setDistrict(loc.get(0).getLocName());

						}

					}
					vo.setGender(patDtl.getGender());
					vo.setAge(patDtl.getAge().toString());
					vo.setRegistrationDate(patDtl.getRegHospDate().toString());
					

					if(cmbDtls!=null && cmbDtls.getCmbDtlName()!=null)
						vo.setCaseProcCodes(cmbDtls.getCmbDtlName());
					
					
					
					
					annClaimList.add(vo);

				}
				
				

			}

		}
		return annClaimList;
	}

	@Override
	public AnnualCheckUpVo getOtherTestDetails(String ahcId) {
		// TODO Auto-generated method stub
		List<AnnualCheckUpVo> testDtls = null;
		StringBuffer query = new StringBuffer();
		// part1
		query.append("select p1.r1 as haemoglobin, p1.r2 as tlc, p1.r3 as Polymorphs,p1.r4 as lymphocytes, ");
		query.append("p1.r5 as eosinophils, p1.r6 as basophils, p1.r7 as monocytes,p1.r8 as rbc, ");
		query.append("p1.r9 as wbc, p1.r10 as platelets, p1.r11 as bloodGroup,p1.r12 as esr, ");
		query.append("p1.r13 as fastingSugar, p1.r14 as postPrandialSugar, p1.r15 as glycosylatedHaemoglobin,p1.r16 as hbsAg, ");
		query.append("p1.r17 as totalCholesterol, p1.r18 as hdlCholesterol, p1.r19 as ldlCholesterol,p1.r20 as vldlCholesterol, ");
		query.append("p1.r21 as triglycerides, p1.r22 as bloodUrea, p1.r23 as sCreatinine,p1.r24 as sUricAcid, ");
		query.append("p1.r25 as serumElectrolytes, p1.r26 as serumCreatinine,");

		// part2
		query.append(" p2.r27 as sgot, p2.r28 as sgpt, p2.r29 as sTotalBilirubin,p2.r30 as sDirectBilirubin, ");
		query.append("p2.r31 as liverSgot, p2.r32 as liverSgpt, p2.r33 as urineColor,p2.r34 as urineAlbumin, ");
		query.append("p2.r35 as urineSugar, p2.r36 as urineMicroscopicExam, p2.r37 as abdomenUltrasound,p2.r38 as chestXrayPAView, ");
		query.append("p2.r39 as pulmonaryFunction, p2.r40 as ecg, p2.r41 as twodEcho,p2.r42 as treadmillTest, ");
		query.append("p2.r43 as retinopathy, p2.r44 as fundoscopy, p2.r45 as audiometry,p2.r46 as pelvicLocalExam, ");
		query.append("p2.r47 as perVaginum, p2.r48 as perSpeculum, p2.r49 as surgicalExam,p2.r50 as breastExam, ");
		query.append("p2.r51 as papSmear ");
		query.append("from EhfAhcPart1Results p1,EhfAhcPart2Results p2 where p1.ahcId= '"
				+ ahcId + "' and p2.ahcId='" + ahcId+"'");

		testDtls = genericDao.executeHQLQueryList(AnnualCheckUpVo.class,
				query.toString());
		if (!testDtls.isEmpty() && testDtls.size() > 0)
			return testDtls.get(0);
		else
			return null;
	}

	@Override
	public EhfAnnualCaseDtls getAhcCaseDtls(String ahcId) {
		EhfAnnualCaseDtls epd = genericDao.findById(EhfAnnualCaseDtls.class,
				String.class, ahcId);

		return epd;
	}

	/**
	 * ;
	 * 
	 * @param String
	 *            cardNo
	 * @return String photoUrl
	 * @function This Method is Used For getting photoUrl for given cardNo
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getPatientPhoto(String cardNo) throws Exception {
		String photoUrl = null;
		String enrollPrntId = null;
		List<String> l = null;
		StringBuffer query = new StringBuffer();
		// String enrollDdoReject=config.getString("ENROLLMENT_DDO_REJECTION");
		// String cardValidStatus=config.getString("Card.ValidStatus");
		// query.append("select ef.enrollPrntId,ef.enrollPhoto from EhfEnrollmentFamily ef where ef.ehfCardNo='"+cardNo+"' and ef.enrollStatus not in ("+cardValidStatus.replace("~",
		// ",")+") and ef.displayFlag||ef.rejected <>'NN'");
		query.append("select ef.enrollPrntId,ef.enrollPhoto from EhfEnrollmentFamily ef where ef.ehfCardNo='"
				+ cardNo + "'");
		try {
			if (!hibernateTemplate.getSessionFactory().getCurrentSession()
					.isOpen()) {
				throw new RuntimeException(
						"No Session open for executing the query " + query);
			} else
				l = hibernateTemplate.getSessionFactory().getCurrentSession()
						.createQuery(query.toString()).list();
		} catch (Exception e) {
			GLOGGER.error("Exception occurred in getPatientPhoto() while creating session factory instance in AnnualCheckUpDAOImpl class."
					+ e.getMessage());
		}
		@SuppressWarnings("rawtypes")
		Iterator ite = l.iterator();
		if (ite.hasNext()) {
			Object[] obj = (Object[]) ite.next();
			if (obj[0] != null) {
				enrollPrntId = obj[0].toString();
			}
			if (obj[1] != null) {
				photoUrl = obj[1].toString();
			}
		}
		System.out.println(photoUrl);
		if (cardNo.endsWith("01")) {
			Map<String, Object> photoMap = new HashMap<String, Object>();
			photoMap.put("employeeParntId", enrollPrntId);
			photoMap.put("attachType",
					config.getString("FamilyHeadPhoto.AttachType"));
			photoMap.put("attachactiveYN", "Y");
			EhfEmployeeDocAttach ehfEmployeeDocAttach = genericDao
					.findFirstByPropertyMatch(EhfEmployeeDocAttach.class,
							photoMap);
			photoUrl = ehfEmployeeDocAttach.getSavedName();
		}

		return photoUrl;
	}


	@Override
	public AnnualCheckUpVo getFindings(String ahcId) {
		// TODO Auto-generated method stub
		AnnualCheckUpVo findings = new AnnualCheckUpVo();
		StringTokenizer str1 = null;
		StringBuffer query1 = new StringBuffer();
		try
		{
	  
			EhfAhcHospDiagnosis ehfAhcHospDiagnosis = genericDao.findById(EhfAhcHospDiagnosis.class, String.class, ahcId);
			if(ehfAhcHospDiagnosis != null)
			{
				findings.setPresentIllness(ehfAhcHospDiagnosis.getHistoryIllness());	
	
				/**
				 * get past illness
				 */	
				findings.setPastIllness(ehfAhcHospDiagnosis.getPastHistory());
				findings.setPastIllenesCancers(ehfAhcHospDiagnosis.getPastHistoryCancers());
				findings.setPastIllenesEndDis(ehfAhcHospDiagnosis.getPastHistoryEndDis());
				findings.setPastIllenesSurg(ehfAhcHospDiagnosis.getPastHistorySurg());
				if(ehfAhcHospDiagnosis.getPastHistoryOthr()!=null && !ehfAhcHospDiagnosis.getPastHistoryOthr().equalsIgnoreCase(""))
					findings.setPastIllenesOthr(ehfAhcHospDiagnosis.getPastHistoryOthr());
				else
					findings.setPastIllenesOthr("Past Illness- Others not found");
	
				/**
				 * get past history values
				 */
				String pastHist=ehfAhcHospDiagnosis.getPastHistory();
				if(pastHist != null && !pastHist.equals(""))
				{
					str1 = new StringTokenizer(pastHist,"~");
					while(str1.hasMoreTokens())
					{
						query1 = new StringBuffer();
						String disCode=str1.nextToken();
						if(disCode!=null && !disCode.equalsIgnoreCase("PH11"))
						{
							query1.append(" select hm.diseaseName as ID from EhfmPastHistoryMst hm where hm.diseaseCode='"+disCode+"'");
							List<LabelBean> ehfComplList = genericDao.executeHQLQueryList(LabelBean.class, query1.toString());
							//ehfmSymptomsMst = genericDao.findById(EhfmSymptomsMst.class, String.class, str.nextToken());	
							if(ehfComplList != null && ehfComplList.size()>0)
							{	

				if(findings.getPastIllnessValue() == null || findings.getPastIllnessValue().equalsIgnoreCase(""))
				{	
					findings.setPastIllnessValue(ehfComplList.get(0).getID());
				}
				else
				{
					findings.setPastIllnessValue( findings.getPastIllnessValue() +" , "+ehfComplList.get(0).getID())	;
				}
				String concatRemarks="";
				if(disCode.equalsIgnoreCase("PH8"))
				{
					concatRemarks=ehfAhcHospDiagnosis.getPastHistoryCancers();
					findings.setPastIllnessValue( findings.getPastIllnessValue() +" (" + concatRemarks + ") ");
				}
				if(disCode.equalsIgnoreCase("PH10"))
				{
					concatRemarks=ehfAhcHospDiagnosis.getPastHistorySurg();
					findings.setPastIllnessValue( findings.getPastIllnessValue() +" (" + concatRemarks + ") ");
				}
				if(disCode.equalsIgnoreCase("PH12"))
				{
					concatRemarks=ehfAhcHospDiagnosis.getPastHistoryEndDis();
					findings.setPastIllnessValue( findings.getPastIllnessValue() +" (" + concatRemarks + ") ");
				}
				
			}
		}
	}
	}
	else
		findings.setPastIllnessValue("Past Illness not found");
	
	findings.setPersonalHis( ehfAhcHospDiagnosis.getPersonalHistory());
	String lDesc=null;
	List<LabelBean> personalHisList=new ArrayList<LabelBean>();
	List<LabelBean> personalHis=new ArrayList<LabelBean>();
	List<GenericDAOQueryCriteria> critList = new ArrayList<GenericDAOQueryCriteria>();
	critList.add(new GenericDAOQueryCriteria("parntCode","PR",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	critList.add(new GenericDAOQueryCriteria("activeYN","Y",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	critList.add(new GenericDAOQueryCriteria("code","PR4",GenericDAOQueryCriteria.CriteriaOperator.NOT_EQUALS));
	List<EhfmPersonalHistoryMst> parentList=genericDao.findAllByCriteria(EhfmPersonalHistoryMst.class, critList);
	//List<EhfmPersonalHistoryMst> parentList=genericDao.findAllByPropertyMatch(EhfmPersonalHistoryMst.class,"parntCode","PR");
	if(parentList!=null && !parentList.isEmpty())
	{
		
		for(EhfmPersonalHistoryMst ehfmPersonalHistoryMst1:parentList)
		{
			LabelBean lstPersonal = new LabelBean();
			lstPersonal.setID(ehfmPersonalHistoryMst1.getCode());
			lstPersonal.setVALUE(ehfmPersonalHistoryMst1.getDescription());
			/*Map<String,Object> crit = new HashMap<String,Object>();
			crit.put("parntCode", ehfmPersonalHistoryMst1.getCode());
			crit.put("activeYN", "Y");
			List<EhfmPersonalHistoryMst> childList=genericDao.findAllByPropertiesLike(EhfmPersonalHistoryMst.class, crit);*/
			List<EhfmPersonalHistoryMst> childList=genericDao.findAllByPropertyMatch(EhfmPersonalHistoryMst.class,"parntCode",ehfmPersonalHistoryMst1.getCode());
			List<LabelBean> personalsub=new ArrayList<LabelBean>();
			if(childList!=null && !childList.isEmpty())
			{
				
				for(EhfmPersonalHistoryMst ehfmPersonalHistoryMs2: childList)
				{
					LabelBean personalHisSub=new LabelBean();
					if(ehfmPersonalHistoryMs2!=null)
					{						
						if( lDesc== null || lDesc.equalsIgnoreCase(""))
						{
							lDesc=ehfmPersonalHistoryMs2.getDescription() ;	
						}
						else
						{
							lDesc=lDesc + "~" + ehfmPersonalHistoryMs2.getDescription() 	;	
						}
						personalHisSub.setID(ehfmPersonalHistoryMs2.getCode());
						personalHisSub.setVALUE(ehfmPersonalHistoryMs2.getDescription());
						}
					personalsub.add(personalHisSub);
					}
				
				}
			lstPersonal.setLstSub(personalsub);
			personalHisList.add(new LabelBean(ehfmPersonalHistoryMst1.getCode(),ehfmPersonalHistoryMst1.getDescription()+"^"+lDesc));
			lDesc="";
			personalHis.add(lstPersonal);
		}
	}
	findings.setLstPersonalHistory(personalHis);
	
	EhfAhcPersonalHistory ehfAhcPersonalHistory= genericDao.findById(EhfAhcPersonalHistory.class, String.class, ahcId);
	List<String> lstPerHis = new ArrayList<String>();
	if(ehfAhcPersonalHistory != null)
	{
		if(ehfAhcPersonalHistory.getAppetite() != null)
		{
			lstPerHis.add(ehfAhcPersonalHistory.getAppetite());	
		}
		if(ehfAhcPersonalHistory.getDiet() != null)
		{
			lstPerHis.add(ehfAhcPersonalHistory.getDiet());	
		}
		if(ehfAhcPersonalHistory.getBowels() !=null)
		{
			lstPerHis.add(ehfAhcPersonalHistory.getBowels());		
		}
		/*if(ehfAhcPersonalHistory.getNutrition() != null)
		{
			lstPerHis.add(ehfAhcPersonalHistory.getNutrition());
		}*/
		if(ehfAhcPersonalHistory.getKnownAllergies() != null)
		{
			lstPerHis.add(ehfAhcPersonalHistory.getKnownAllergies());	
			if(ehfAhcPersonalHistory.getKnownAllergies().contains("PR5.1"))
				findings.setAllergy(ehfAhcPersonalHistory.getKnownAllergies());
		}
		if(ehfAhcPersonalHistory.getAddictions() != null)
		{
			lstPerHis.add(ehfAhcPersonalHistory.getAddictions());	
			if(ehfAhcPersonalHistory.getAddictions().contains("PR6.1"))
				findings.setAddiction(ehfAhcPersonalHistory.getAddictions());
			
		}
		if(ehfAhcPersonalHistory.getMaritalStatus() != null)
		{
			lstPerHis.add(ehfAhcPersonalHistory.getMaritalStatus());	
		}
	}
	findings.setLstPerHis(lstPerHis);
	/**
	 * get family history
	 */		
	if(ehfAhcHospDiagnosis.getFamilyHistory()!=null && !ehfAhcHospDiagnosis.getFamilyHistory().equalsIgnoreCase("")){
		findings.setFamilyHis(ehfAhcHospDiagnosis.getFamilyHistory());
	}//end of ehfPatientHospDiagnosis!=null
	
	if(ehfAhcHospDiagnosis.getFamilyHistoryOthr()!=null && !ehfAhcHospDiagnosis.getFamilyHistoryOthr().equalsIgnoreCase(""))
		findings.setFamilyHistoryOthr(ehfAhcHospDiagnosis.getFamilyHistoryOthr());
	else
		findings.setFamilyHistoryOthr("Family History -Others not found");
	
	
	
	/**
	 * get family history values
	 */
	String famhist=ehfAhcHospDiagnosis.getFamilyHistory();
	if(famhist != null && !famhist.equals(""))
	{
	str1 = new StringTokenizer(famhist,"~");
	while(str1.hasMoreTokens())
	{
		 query1 = new StringBuffer();
		String code=str1.nextToken();
		if(code!=null && !code.equalsIgnoreCase("FH11"))
		{
			query1.append(" select hm.description as ID from EhfmFamilyHistoryMst hm where hm.code='"+code+"'");
			List<LabelBean> ehfComplList = genericDao.executeHQLQueryList(LabelBean.class, query1.toString());
			if(ehfComplList != null && ehfComplList.size()>0)
			{
				if(findings.getFamilyHistoryOthr() == null || findings.getFamilyHistoryOthr().equalsIgnoreCase(""))
				{	
					findings.setFamilyHistoryOthr(ehfComplList.get(0).getID());
				}
				else
				{
					findings.setFamilyHistoryOthr( findings.getFamilyHistoryOthr() +" , "+ehfComplList.get(0).getID())	;
				}
				
			}
		}
	}
	}
	else
		findings.setFamilyHistoryOthr("Family History not found");
	}//end of ehfPatientHospDiagnosis!=null
	/**
	 * get Examination findings
	 */
	EhfAhcExamFind ehfAhcExamFind = genericDao.findById(EhfAhcExamFind.class, String.class, ahcId);
	if(ehfAhcExamFind != null)
	{
		if(ehfAhcExamFind.getBmi()!=null && !ehfAhcExamFind.getBmi().equalsIgnoreCase(""))
			findings.setBmi(ehfAhcExamFind.getBmi());
		else
			findings.setBmi("NA");
		if(ehfAhcExamFind.getHeight()!=null && !ehfAhcExamFind.getHeight().equalsIgnoreCase(""))
			findings.setHeight(ehfAhcExamFind.getHeight());
		else
			findings.setHeight("NA");
		if(ehfAhcExamFind.getWeight()!=null && !ehfAhcExamFind.getWeight().equalsIgnoreCase(""))
			findings.setWeight(ehfAhcExamFind.getWeight());
		else
			findings.setWeight("NA");
		findings.setPallor(ehfAhcExamFind.getPallor());
		findings.setCyanosis(ehfAhcExamFind.getCyanosis());
		findings.setClubbingOfFingers(ehfAhcExamFind.getClubOfFingrToes());
		findings.setLymphadenopathy(ehfAhcExamFind.getLymphadenopathy());
		findings.setOedema(ehfAhcExamFind.getOedemaInFeet());
		findings.setMalNutrition(ehfAhcExamFind.getMalnutrition());
		if(ehfAhcExamFind.getDehydration() != null && ehfAhcExamFind.getDehydration().equalsIgnoreCase("N"))
			findings.setDehydration(ehfAhcExamFind.getDehydration());
		else if(ehfAhcExamFind.getDehydration() != null &&  !ehfAhcExamFind.getDehydration().equals("") && ehfAhcExamFind.getDehydration().contains("Y"))
		{
			findings.setDehydration(ehfAhcExamFind.getDehydration().charAt(0)+"");
			findings.setDehydrationType(ehfAhcExamFind.getDehydration().substring(1));
		}
		if(ehfAhcExamFind.getTemperature()!=null && !ehfAhcExamFind.getTemperature().equalsIgnoreCase(""))
			findings.setTemperature(ehfAhcExamFind.getTemperature());
		else
			findings.setTemperature("NA");	
		if(ehfAhcExamFind.getPluseRate()!=null && !ehfAhcExamFind.getPluseRate().equalsIgnoreCase(""))
			findings.setPulseRate(ehfAhcExamFind.getPluseRate());
		else
			findings.setPulseRate("NA");
		if(ehfAhcExamFind.getRespirationRate()!=null && !ehfAhcExamFind.getRespirationRate().equalsIgnoreCase(""))
			findings.setRespirationRate(ehfAhcExamFind.getRespirationRate());
		else
			findings.setRespirationRate("NA");	
		if(ehfAhcExamFind.getBpLt()!=null && !ehfAhcExamFind.getBpLt().equalsIgnoreCase(""))
			findings.setBpLmt(ehfAhcExamFind.getBpLt());
		else
			findings.setBpLmt("NA");
		if(ehfAhcExamFind.getBpRt()!=null && !ehfAhcExamFind.getBpRt().equalsIgnoreCase(""))
			findings.setBpRmt(ehfAhcExamFind.getBpRt());
		else
			findings.setBpRmt("NA");
	}
	
	/**
	 * get system Examination findings
	 */
	
	//genericDao.findById(ehfm_systematic_exam_fnd, idClass, id);
	


		
	
		}catch(Exception e)
		{
			e.printStackTrace();	
		}		
		
	
		return findings;
	}

	@Override
	public List<AnnualCheckUpVo> searchAnnualClaimDtls(String patID,
			String PatName, String healthCardNo, String state, String district,
			String fromDate, String toDate, String hospId, String roleId,String userState,String caseSearchFlag,String status) {

		List<AnnualCheckUpVo>	annClaimList=new ArrayList<AnnualCheckUpVo>();

		List<GenericDAOQueryCriteria> critList = new ArrayList<GenericDAOQueryCriteria>();
		
		try {
			
			if(roleId.equalsIgnoreCase(config.getString("Group.Mithra"))||roleId.equalsIgnoreCase(config.getString("Group.Medco"))){
				critList.add(new GenericDAOQueryCriteria("ahcHospCode", hospId,
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				
			}
			if(status!=null && !"".equalsIgnoreCase(status) && !"-1".equalsIgnoreCase(status)){
				
				List<String> li=new ArrayList<String>();
				li.add(status);
				critList.add(new GenericDAOQueryCriteria("ahcStatus",li,GenericDAOQueryCriteria.CriteriaOperator.IN));	
				
			}
			
			if((userState!=null && !"".equalsIgnoreCase(userState)) && userState.equalsIgnoreCase("CD203")){
				
				List<String> li=new ArrayList<String>();
				li.add("CD201");
				li.add("CD202");
				critList.add(new GenericDAOQueryCriteria("schemeId",li,GenericDAOQueryCriteria.CriteriaOperator.IN));	
				
			}else if(userState!=null && !"-1".equalsIgnoreCase(userState)){
				critList.add(new GenericDAOQueryCriteria("schemeId",userState,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			}
				else 
					critList.add(new GenericDAOQueryCriteria("schemeId",config.getString("Scheme.TG.State"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));	
			

		if(caseSearchFlag==null || (caseSearchFlag!=null && !caseSearchFlag.equalsIgnoreCase("Y")))
		 	{
				if ((roleId != null && !"".equalsIgnoreCase(roleId))
						&& config.getString("Group.Mithra").equalsIgnoreCase(roleId)) {
	
					critList.add(new GenericDAOQueryCriteria("ahcStatus", config
							.getString("AHC-CLAIM_INTIATED"),
							GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	
				}
				if ((roleId != null && !"".equalsIgnoreCase(roleId))
						&& config.getString("Group.Mithra").equalsIgnoreCase(roleId)) 
					{
						critList.add(new GenericDAOQueryCriteria("ahcStatus", config
								.getString("AHC-CLAIM_INTIATED"),
								GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					}
				if ((roleId != null && !"".equalsIgnoreCase(roleId))
						&& config.getString("Group.Medco").equalsIgnoreCase(roleId)) 
					{
						List<String> li=new ArrayList<String>();
						li.add(config.getString("AHC-MEDCO-SCR_CMPL"));
						//li.add(config.getString("AHC-TD-REC-REJ"));
						li.add(config.getString("AHC-TD-REC-PEN"));
						//li.add(config.getString("AHC-CH-REJ"));
						li.add( config.getString("AHC-CH-PEN"));
						critList.add(new GenericDAOQueryCriteria("ahcStatus", li,GenericDAOQueryCriteria.CriteriaOperator.IN));
					}
				if ((roleId != null && !"".equalsIgnoreCase(roleId))
						&& config.getString("Group.AHC-EX").equalsIgnoreCase(roleId)) 
					{
						critList.add(new GenericDAOQueryCriteria("ahcStatus", config.getString("AHC_MITHRA_FRWD"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					}
				if ((roleId != null && !"".equalsIgnoreCase(roleId))
						&& config.getString("Group.AHC-TD").equalsIgnoreCase(roleId)) 
					{	
						List<String> li=new ArrayList<String>();
						li.add(config.getString("AHC-EX-REC-APPRV"));
						li.add(config.getString("AHC-EX-REC-REJ"));
						li.add(config.getString("AHC-EX-REC-PEN"));
						li.add(config.getString("AHC-MED-TD-PEN-UPD"));
						critList.add(new GenericDAOQueryCriteria("ahcStatus", li,GenericDAOQueryCriteria.CriteriaOperator.IN));
					}
				if ((roleId != null && !"".equalsIgnoreCase(roleId))
						&& config.getString("Group.AHC-CH").equalsIgnoreCase(roleId)) 
					{
						List<String> li=new ArrayList<String>();
						li.add(config.getString("AHC-TD-REC-APPRV"));
						li.add(config.getString("AHC-MED-CH-PEN-UPD"));
						li.add(config.getString("AHC-TD-REC-REJ"));
						critList.add(new GenericDAOQueryCriteria("ahcStatus", li,GenericDAOQueryCriteria.CriteriaOperator.IN));
					}
				if ((roleId != null && !"".equalsIgnoreCase(roleId))
						&& config.getString("Group.AHC-ACO").equalsIgnoreCase(roleId)) 
					{
						critList.add(new GenericDAOQueryCriteria("ahcStatus", config.getString("AHC-CH-APPRV"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					}
				if ((roleId != null && !"".equalsIgnoreCase(roleId))
						&& config.getString("Group.AHC-EO").equalsIgnoreCase(roleId)) 
					{
			
						critList.add(new GenericDAOQueryCriteria("ahcStatus", config.getString("AHC-ACO-APPRV"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					}
		 	}	
		List<EhfAnnualCaseDtls> dtls = genericDao.findAllByCriteria(
				EhfAnnualCaseDtls.class, critList);
		
		
		
		
		for(int i=0;i<dtls.size();i++){
			List<GenericDAOQueryCriteria> crList = new ArrayList<GenericDAOQueryCriteria>();
			crList.add(new GenericDAOQueryCriteria("ahcId", dtls.get(i).getAhcId(),
					GenericDAOQueryCriteria.CriteriaOperator.EQUALS));	
			
				
	
			
			if (patID != null && !"".equalsIgnoreCase(patID)) {

				crList.add(new GenericDAOQueryCriteria(
						"ahcId",
						patID.toUpperCase(),
						GenericDAOQueryCriteria.CriteriaOperator.LIKE_IN_BETWEEN));
			}

			if (PatName != null && !"".equalsIgnoreCase(PatName)) {
				crList.add(new GenericDAOQueryCriteria(
						"name",
						PatName.toUpperCase(),
						GenericDAOQueryCriteria.CriteriaOperator.LIKE_IN_BETWEEN));

			}

			if (healthCardNo != null && !"".equalsIgnoreCase(healthCardNo)) {

				crList.add(new GenericDAOQueryCriteria(
						"cardNo",
						healthCardNo.toUpperCase(),
						GenericDAOQueryCriteria.CriteriaOperator.LIKE_IN_BETWEEN));
			}

			if (state != null && !"-1".equalsIgnoreCase(state)) {

				crList.add(new GenericDAOQueryCriteria("state", state,
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));

			}

			if (district != null && !"-1".equalsIgnoreCase(district)) {
				crList.add(new GenericDAOQueryCriteria("districtCode",
						district,
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));

			}
			if ((fromDate != null && !"".equalsIgnoreCase(fromDate))
					&& (toDate != null && !"".equalsIgnoreCase(toDate))) {

				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				Date frmDt = formatter.parse(fromDate);
				Date toDt = formatter.parse(toDate);
				
				Calendar cal=Calendar.getInstance();
				cal.setTime(toDt);
				cal.add(Calendar.DATE,1);
				
				toDt=cal.getTime();

				crList.add(new GenericDAOQueryCriteria("regHospDate", frmDt,
						GenericDAOQueryCriteria.CriteriaOperator.GE));

				crList.add(new GenericDAOQueryCriteria("regHospDate", toDt,
						GenericDAOQueryCriteria.CriteriaOperator.LE));

				
				crList.add(new GenericDAOQueryCriteria("regHospDate", "",
						GenericDAOQueryCriteria.CriteriaOperator.ASC));
			}

			if (roleId.equalsIgnoreCase(config.getString("Group.Mithra"))||roleId.equalsIgnoreCase(config.getString("Group.Medco"))) {

				crList.add(new GenericDAOQueryCriteria("regHospId", hospId,
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));

			}

			List<EhfAnnualPatientDtls> dtls1 = genericDao.findAllByCriteria(
					EhfAnnualPatientDtls.class, crList);

			
			for(int j=0;j<dtls1.size();j++){
				
				EhfmCmbDtls cmbDtls = genericDao.findById(
						EhfmCmbDtls.class, String.class, dtls.get(i)
								.getAhcStatus());
				
				AnnualCheckUpVo vo = new AnnualCheckUpVo();
				vo.setPatientNo(dtls1.get(j).getAhcId());
				vo.setName(dtls1.get(j).getName());
				//vo.setEmployeeNo(dtls1.get(j).getEmployeeNo());
				vo.setEmployeeNo(dtls1.get(j).getCardNo());

				if (dtls1.get(j).getDistrictCode() != null
						&& !"".equalsIgnoreCase(dtls1.get(j).getDistrictCode())) {

					List<GenericDAOQueryCriteria> locList = new ArrayList<GenericDAOQueryCriteria>();

					locList.add(new GenericDAOQueryCriteria("id.locId",
							dtls1.get(j).getDistrictCode(),
							GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					List<EhfmLocations> loc = genericDao.findAllByCriteria(
							EhfmLocations.class, locList);
					if (loc != null && loc.size() > 0) {

						vo.setDistrict(loc.get(0).getLocName());

					}

				}
				
				if(cmbDtls!=null && cmbDtls.getCmbDtlName()!=null)
					vo.setCaseProcCodes(cmbDtls.getCmbDtlName());
				
				
				vo.setGender(dtls1.get(j).getGender());
				vo.setAge(dtls1.get(j).getAge().toString());
				vo.setRegistrationDate(dtls1.get(j).getRegHospDate().toString());
				annClaimList.add(vo);

				
				
				
			}
			
		}
			
		} catch (Exception e) {

			e.printStackTrace();
		}

		return annClaimList;
	}

	@Override
	public EhfAhcMedicalReport getOverallReport(String ahcId) {
		EhfAhcMedicalReport rep= genericDao.findById(EhfAhcMedicalReport.class, String.class, ahcId);
		return rep;
	}

	@Override
	public AnnualCheckUpVo getPatientFullDtls(String ahcId) {
		AnnualCheckUpVo details= new AnnualCheckUpVo();
		// TODO Auto-generated method stub
		EhfAhcHospDiagnosis ehfPatientHospDiagnosis = genericDao.findById(EhfAhcHospDiagnosis.class, String.class,ahcId);
		if(ehfPatientHospDiagnosis != null)
		{
			details.setPresentIllness(ehfPatientHospDiagnosis.getHistoryIllness());	
			
			
			details.setPastIllness(ehfPatientHospDiagnosis.getPastHistory());
			details.setExamFindg(ehfPatientHospDiagnosis.getExamntnFindings());
			
			if(ehfPatientHospDiagnosis.getPastHistoryOthr()!=null && !ehfPatientHospDiagnosis.getPastHistoryOthr().equalsIgnoreCase(""))
				details.setPastIllenesOthr(ehfPatientHospDiagnosis.getPastHistoryOthr());
			if(ehfPatientHospDiagnosis.getPastHistoryCancers()!=null && !ehfPatientHospDiagnosis.getPastHistoryCancers().equalsIgnoreCase(""))
				details.setPastIllenesCancers(ehfPatientHospDiagnosis.getPastHistoryCancers());
			if(ehfPatientHospDiagnosis.getPastHistoryEndDis()!=null && !ehfPatientHospDiagnosis.getPastHistoryEndDis().equalsIgnoreCase(""))
				details.setPastIllenesEndDis(ehfPatientHospDiagnosis.getPastHistoryEndDis());
			if(ehfPatientHospDiagnosis.getPastHistorySurg()!=null && !ehfPatientHospDiagnosis.getPastHistorySurg().equalsIgnoreCase(""))
				details.setPastIllenesSurg(ehfPatientHospDiagnosis.getPastHistorySurg());
			
			if(ehfPatientHospDiagnosis.getDiagnosedBy()!=null && !ehfPatientHospDiagnosis.getDiagnosedBy().equalsIgnoreCase(""))
				details.setDiagnosedBy(ehfPatientHospDiagnosis.getDiagnosedBy());
			
			details.setPersonalHis( ehfPatientHospDiagnosis.getPersonalHistory());
			
			EhfAhcPersonalHistory ehfPatientPersonalHistory= genericDao.findById(EhfAhcPersonalHistory.class, String.class, ahcId);
			List<String> lstPerHis = new ArrayList<String>();
			if(ehfPatientPersonalHistory != null)
			{
				if(ehfPatientPersonalHistory.getAppetite() != null)
				{
					lstPerHis.add(ehfPatientPersonalHistory.getAppetite());	
				}
				if(ehfPatientPersonalHistory.getDiet() != null)
				{
					lstPerHis.add(ehfPatientPersonalHistory.getDiet());	
				}
				if(ehfPatientPersonalHistory.getBowels() !=null)
				{
					lstPerHis.add(ehfPatientPersonalHistory.getBowels());		
				}
				if(ehfPatientPersonalHistory.getNutrition() != null)
				{
					lstPerHis.add(ehfPatientPersonalHistory.getNutrition());
				}
				if(ehfPatientPersonalHistory.getKnownAllergies() != null)
				{
					lstPerHis.add(ehfPatientPersonalHistory.getKnownAllergies());	
					if(ehfPatientPersonalHistory.getKnownAllergies().contains("PR5.1"))
						details.setAllergy(ehfPatientPersonalHistory.getKnownAllergies());
				}
				if(ehfPatientPersonalHistory.getAddictions() != null)
				{
					lstPerHis.add(ehfPatientPersonalHistory.getAddictions());	
					if(ehfPatientPersonalHistory.getAddictions().contains("PR6.1"))
					details.setAddiction(ehfPatientPersonalHistory.getAddictions());
				}
				if(ehfPatientPersonalHistory.getMaritalStatus() != null)
				{
					lstPerHis.add(ehfPatientPersonalHistory.getMaritalStatus());	
				}
			}
			details.setLstPerHis(lstPerHis);
			
			/**
			 * get Examination findings
			 */
			EhfAhcExamFind ehfPatientExamFind = genericDao.findById(EhfAhcExamFind.class, String.class, ahcId);
			if(ehfPatientExamFind != null)
			{
				if(ehfPatientExamFind.getBmi()!=null && !ehfPatientExamFind.getBmi().equalsIgnoreCase(""))
				details.setBmi(ehfPatientExamFind.getBmi());
				else
				details.setBmi("NA");
				if(ehfPatientExamFind.getHeight()!=null && !ehfPatientExamFind.getHeight().equalsIgnoreCase(""))
				details.setHeight(ehfPatientExamFind.getHeight());
				else
				details.setHeight("NA");
				if(ehfPatientExamFind.getWeight()!=null && !ehfPatientExamFind.getWeight().equalsIgnoreCase(""))
				details.setWeight(ehfPatientExamFind.getWeight());
				else
				details.setWeight("NA");
				details.setPallor(ehfPatientExamFind.getPallor());
				details.setCyanosis(ehfPatientExamFind.getCyanosis());
				details.setClubbingOfFingers(ehfPatientExamFind.getClubOfFingrToes());
				details.setLymphadenopathy(ehfPatientExamFind.getLymphadenopathy());
				details.setOedema(ehfPatientExamFind.getOedemaInFeet());
				details.setMalNutrition(ehfPatientExamFind.getMalnutrition());
				if(ehfPatientExamFind.getDehydration() != null && ehfPatientExamFind.getDehydration().equalsIgnoreCase("N"))
				details.setDehydration(ehfPatientExamFind.getDehydration());
				else if(ehfPatientExamFind.getDehydration() != null &&  !ehfPatientExamFind.getDehydration().equals("") && ehfPatientExamFind.getDehydration().contains("Y"))
				{
						details.setDehydration(ehfPatientExamFind.getDehydration().charAt(0)+"");
						details.setDehydrationType(ehfPatientExamFind.getDehydration().substring(1));
				}
				if(ehfPatientExamFind.getTemperature()!=null && !ehfPatientExamFind.getTemperature().equalsIgnoreCase(""))
				details.setTemperature(ehfPatientExamFind.getTemperature());
				else
				details.setTemperature("NA");	
				if(ehfPatientExamFind.getPluseRate()!=null && !ehfPatientExamFind.getPluseRate().equalsIgnoreCase(""))
				details.setPulseRate(ehfPatientExamFind.getPluseRate());
				else
				details.setPulseRate("NA");
				if(ehfPatientExamFind.getRespirationRate()!=null && !ehfPatientExamFind.getRespirationRate().equalsIgnoreCase(""))
				details.setRespirationRate(ehfPatientExamFind.getRespirationRate());
				else
					details.setRespirationRate("NA");	
				if(ehfPatientExamFind.getBpLt()!=null && !ehfPatientExamFind.getBpLt().equalsIgnoreCase(""))
				details.setBpLmt(ehfPatientExamFind.getBpLt());
				else
					details.setBpLmt("NA");
				if(ehfPatientExamFind.getBpRt()!=null && !ehfPatientExamFind.getBpRt().equalsIgnoreCase(""))
				details.setBpRmt(ehfPatientExamFind.getBpRt());
				else
					details.setBpRmt("NA");
			}
			
			details.setFamilyHis(ehfPatientHospDiagnosis.getFamilyHistory());	
			if(ehfPatientHospDiagnosis.getFamilyHistoryOthr()!=null && !ehfPatientHospDiagnosis.getFamilyHistoryOthr().equalsIgnoreCase(""))
				details.setFamilyHistoryOthr(ehfPatientHospDiagnosis.getFamilyHistoryOthr());
			
			
			
		}
		
		return details;
	}
		/**
	    * ;
	    * @param String AHC ID of the Patient
	    * @return String EhfAnnualPatientDtls Object
	    * @author Kalyan
	    * @function This Method is Used For getting patient object of AHC
	    */
		public EhfAnnualPatientDtls getAHCPatRec(String patientId)
		   	{
				EhfAnnualPatientDtls retObj=new EhfAnnualPatientDtls();
			   	if(patientId!=null)
			   		{
			   			try
			   				{
			   					retObj=genericDao.findById(EhfAnnualPatientDtls.class, String.class, patientId);
			   				}
			   			catch(Exception e)
			   				{
			   					e.printStackTrace();
			   					GLOGGER.error("Exception Occurred in getAHCPatRec method of AnnualCheckUpDAOImpl Class"+e.getMessage());
			   					return retObj;
			   				}
			   		}
			   	return retObj;
		   	}
		
		/**
	    * ;
	    * @param String query to Execute
	    * @return List of LabelBean
	    * @author Kalyan
	    * @function This Method is Used For Executing a HQL Query   
	    */
	   public List<LabelBean> executeHQLQuery(String query)
	   	{	
		   	List<LabelBean> returnLst=new ArrayList<LabelBean>();
		   	try
		   		{
		   			returnLst=genericDao.executeHQLQueryList(LabelBean.class, query);
		   		}
		   	catch(Exception e)
		   		{
		   			e.printStackTrace();
		   			GLOGGER.error("Exception Occurred in executeHQLQuery method of AnnualCheckUpDAOImpl Class"+e.getMessage());
   					return returnLst;
		   		}
		   	return returnLst;
	   	}
	   
	   /**
	    * ;
	    * @param String query to Execute
	    * @return List of EhfAhcPatientTest
	    * @author Kalyan
	    * @function This Method is Used For Executing a HQL 
	    * Query and return List of EhfAhcPatientTest     
	    */
	   @SuppressWarnings("unchecked")
	public List<EhfAhcPatientTest> executeAttachQuery(String query)
	   	{
		   List<EhfAhcPatientTest> returnList=new ArrayList<EhfAhcPatientTest>();
		   	if(query!=null && !"".equalsIgnoreCase(query))
		   		{
		   			try
		   				{
		   					SessionFactory factory=hibernateTemplate.getSessionFactory();
		   					Session session=factory.openSession();
		   					returnList=session.createQuery(query).list();
		   				}
		   			catch(Exception e)
		   				{
		   					e.printStackTrace();
		   					GLOGGER.error("Exception Occurred in executeAttachQuery method of AnnualCheckUpDAOImpl Class"+e.getMessage());
		   					return returnList;
		   				}
		   		}
		   	return returnList;
	   	}

	@Override
	public List<LabelBean> getConsultationDtls(String patientId) {
		// TODO Auto-generated method stub
		List<LabelBean> attachList=new ArrayList<LabelBean>();
		if(patientId !=null && !"".equalsIgnoreCase(patientId))
			{
				try
					{
						StringBuffer query=new StringBuffer();
						query.append("select ecd.ahcId as ID,ecd.sno as COUNT, ecd.consultationSpec as UNITTYPE, ecd.consultDoctor as UNITNAME, ecd.remarks as REMARKS, ecd.consultFilePath as PATH from EhfAhcConsultationDtls ecd where ecd.ahcId = '"+patientId+"' order by ecd.crtDt " );
						
						 attachList = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
					}
				catch(Exception e)
					{		
						e.printStackTrace();
						GLOGGER.error("Exception occured in getConsultationDtls method of AnnualCheckUpDAOImpl class"+e.getMessage());
						return attachList;
					}	
			}
		return attachList;
	}
	public boolean uploadAttachments(List<AnnualCheckUpVo> ahcTestLst,String ahcId) {
		
		boolean res=true;
		try{
		for(int i=0;i<ahcTestLst.size();i++){
			StringBuffer query=new StringBuffer();
			List<EhfAhcPatientTest> attachList=new ArrayList<EhfAhcPatientTest>();
			query.append("  from EhfAhcPatientTest where patientId = '"+ahcId+"' and testId='"+ahcTestLst.get(i).getICDcatName() +"' order by attachPath,crtDt " );
			 attachList = executeAttachQuery(query.toString());
			 if(attachList!=null && attachList.size()>0){
				 attachList.get(0).setAttachPath(ahcTestLst.get(i).getFilePath());
				 genericDao.save(attachList.get(0));
			 }
		}
		
		//query.append("  from EhfAhcPatientTest where patientId = '"+ahcId+"' and order by attachPath,crtDt " );
		}
		catch(Exception e ){
			e.printStackTrace();
			return false;
		}
		return res;
	}
}