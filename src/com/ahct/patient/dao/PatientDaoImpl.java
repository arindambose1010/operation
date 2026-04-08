package com.ahct.patient.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.net.URL;
import java.util.StringTokenizer;
import java.net.HttpURLConnection;

import com.ahct.model.EhfmPayGradeMst;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;
import org.dom4j.Text;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.chronicOp.vo.ChronicOPVO;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.util.SMSServices;
import com.ahct.common.vo.LabelBean;
import com.ahct.followup.vo.FollowUpVO;
import com.ahct.model.EhfAudit;
import com.ahct.model.EhfAuditId;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfCasePatient;
import com.ahct.model.EhfCaseTherapy;
import com.ahct.model.EhfCaseTherapyInvest;
import com.ahct.model.EhfChronicAudit;
import com.ahct.model.EhfChronicAuditPK;
import com.ahct.model.EhfChronicCaseDtl;
import com.ahct.model.EhfChronicCaseDtlPK;
import com.ahct.model.EhfChronicPatientDrug;
import com.ahct.model.EhfChronicPatientDrugPK;
import com.ahct.model.EhfChronicPatientDtl;
import com.ahct.model.EhfChronicPatientExamFind;
import com.ahct.model.EhfChronicPatientHosDgnsi;
import com.ahct.model.EhfChronicPatientPerHstry;
import com.ahct.model.EhfChronicPatientTest;
import com.ahct.model.EhfChronicTherapyInvest;
import com.ahct.model.EhfChronicTherapyInvestPK;
import com.ahct.model.EhfDentalOralExaminations;
import com.ahct.model.EhfDentalOtherExaminations;
import com.ahct.model.EhfDentalPatientDtls;
import com.ahct.model.EhfDentalTissueExaminations;
import com.ahct.model.EhfEmpCaseDtls;
import com.ahct.model.EhfEmployeeDocAttach;
import com.ahct.model.EhfEnrollment;
import com.ahct.model.EhfEnrollmentFamily;
import com.ahct.model.EhfJrnlstDAttachment;
import com.ahct.model.EhfJrnlstDClaimAuditPK;
import com.ahct.model.EhfJrnlstDClaimDtls;
import com.ahct.model.EhfJrnlstdClaimAudit;
import com.ahct.model.EhfJrnlstPatientTherapy;
import com.ahct.model.EhfJrnlstdAdmEstDtls;
import com.ahct.model.EhfOncologyCmteApprvl;
import com.ahct.model.EhfOncologyCmteAudit;
import com.ahct.model.EhfOncologyCmteAuditPK;
import com.ahct.model.EhfOncologyDrugs;
import com.ahct.model.EhfOncologyDrugsPK;
import com.ahct.model.EhfOncologyMedical;
import com.ahct.model.EhfOncologyOtpAudit;
import com.ahct.model.EhfOncologyTreatment;
import com.ahct.model.EhfOpConsultData;
import com.ahct.model.EhfOpdBillAttachAudit;
import com.ahct.model.EhfOpdBillDtlsAttachment;
import com.ahct.model.EhfOpdClaimAudit;
import com.ahct.model.EhfOpdClaimAuditPK;
import com.ahct.model.EhfOpdClaimDtls;
import com.ahct.model.EhfOpdPatient;
import com.ahct.model.EhfPatient;
import com.ahct.model.EhfPatientBioReg;
import com.ahct.model.EhfPatientDrugs;
import com.ahct.model.EhfPatientExamFind;
import com.ahct.model.EhfPatientHospDiagnosis;
import com.ahct.model.EhfPatientPersonalHistory;
import com.ahct.model.EhfPatientTests;
import com.ahct.model.EhfSmsData;
import com.ahct.model.EhfSsrMedinpData;
import com.ahct.model.EhfSymtematicExamDtls;
import com.ahct.model.EhfSymtematicExamDtlsId;
import com.ahct.model.EhfTelephonicRegn;
import com.ahct.model.EhfmComplaintMst;
import com.ahct.model.EhfmDiagCategoryMst;
import com.ahct.model.EhfmDiagCategoryMstId;
import com.ahct.model.EhfmDiagDisAnatomicalMst;
import com.ahct.model.EhfmDiagDiseaseMst;
import com.ahct.model.EhfmDiagMainCatMst;
import com.ahct.model.EhfmDiagMainCatMstId;
import com.ahct.model.EhfmDiagSubCatMst;
import com.ahct.model.EhfmDiagSubCatMstId;
import com.ahct.model.EhfmDiagnosisMst;
import com.ahct.model.EhfmDrugsMst;
import com.ahct.model.EhfmDutyDctrs;
import com.ahct.model.EhfmHospMithraDtls;
import com.ahct.model.EhfmHospMithraDtlsId;
import com.ahct.model.EhfmHospitals;
import com.ahct.model.EhfmHubSpokeMainTherapy;
import com.ahct.model.EhfmHubSpokeMainTherapyId;
import com.ahct.model.EhfmLocations;
import com.ahct.model.EhfmMedcoDtls;
import com.ahct.model.EhfmMedcoDtlsId;
import com.ahct.model.EhfmMedcoMaster;
import com.ahct.model.EhfmOccupationMst;
import com.ahct.model.EhfmOpDrugMst;
import com.ahct.model.EhfmPersonalHistoryMst;
import com.ahct.model.EhfmQualMst;
import com.ahct.model.EhfmRelationMst;
import com.ahct.model.EhfmSeq;
import com.ahct.model.EhfmSpecialities;
import com.ahct.model.EhfmSplstDctrs;
import com.ahct.model.EhfmSystematicExamFnd;
import com.ahct.model.EhfmTherapyCatMst;
import com.ahct.model.EhfmTherapyCatMstId;
import com.ahct.model.EhfmTherapyProcMst;
import com.ahct.model.EhfmTherapyProcMstId;
import com.ahct.model.EhfmUsers;
import com.ahct.model.EhfonBedStatus;
import com.ahct.model.EhfmEDCHospActionDtls;
import com.ahct.model.EhfmEDCHospActionDtlsId;
import com.ahct.patient.form.PatientForm;
import com.ahct.patient.vo.AttachmentVO;
import com.ahct.patient.vo.CaseTherapyVO;
import com.ahct.patient.vo.CommonDtlsVO;
import com.ahct.patient.vo.DrugsVO;
import com.ahct.patient.vo.PatientVO;
import com.ahct.patient.vo.PreauthVO;
import com.ahct.model.EhfJrnlstFamily;
import com.ahct.model.EhsJournalistEnrollment;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;
import com.ahct.common.service.AsriBiomServiceImplServiceStub;
import com.ahct.common.service.AsriBiomServiceImplServiceStub.CompareBiometricValues;
import com.ahct.common.service.AsriBiomServiceImplServiceStub.CompareBiometricValuesResponse;
@Transactional
public class PatientDaoImpl implements PatientDao {
	private final static Logger GLOGGER = Logger.getLogger ( PatientDaoImpl.class ) ;
	GenericDAO genericDao;
	HibernateTemplate hibernateTemplate;
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	public GenericDAO getGenericDao()
	{
		return genericDao;
	}
	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}
	/**
	 * ;
	 * @param PatientVO patientVo
	 * @return PatientVO
	 * @function This Method is Used For Retrieving Enrollment Details of Employee/Pensioner card no
	 */	
	@Override
	public PatientVO retrieveCardDetails(PatientVO patientVo) throws Exception {
		PatientVO patientVO=null;
		SessionFactory factory=null;
		Session session=null;
		Query hQuery=null;
		String cardValidStatus=config.getString("Card.ValidStatus");
		String schemeId=patientVo.getSchemeId();
		//Chandana - 8442 - Added the below code for abha numbers, using abha number getting the main card number either journalist or emp or pensioner
		//pravalika - added OR condition for delhi journalist abha no
		if (patientVo.getCardType().equalsIgnoreCase("AB") || patientVo.getCardType().equalsIgnoreCase("DAB")) {
		    String cardDetails = getCardDtlsForAbhaSearch(patientVo.getCardNo());
		    if (cardDetails != null && cardDetails.contains("~")) {
		        String[] parts = cardDetails.split("~");
		        String cardNum = parts[0];
		        String type = parts[1];
		        patientVo.setCardNo(cardNum);
		        patientVo.setCardType(type);
		        //patientVO.setAbhaId(patientVo.getCardNo());
		    }
		}
		if(patientVo.getCardType()!=null && !"".equalsIgnoreCase(patientVo.getCardType()))
			{
				if(patientVo.getCardType().equalsIgnoreCase("E") || patientVo.getCardType().equalsIgnoreCase("P") || 
						patientVo.getCardType().equalsIgnoreCase("NB"))
					{
						EhfEnrollmentFamily ehfEnrollmentFamily=null;
							try
								{
									factory= hibernateTemplate.getSessionFactory();
									session=factory.openSession();
									StringBuffer query = new StringBuffer();
									query.append("from EhfEnrollmentFamily ef where ef.ehfCardNo='"+patientVo.getCardNo()+"' and ef.enrollStatus  in ("+cardValidStatus.replace("~", ",")+")");
									hQuery=session.createQuery(query.toString());
									if(hQuery.list().size()>0)
									{
										ehfEnrollmentFamily=(EhfEnrollmentFamily) hQuery.list().get(0);
									}
								}
							catch(Exception e)
								{
									GLOGGER.error("Exception Occurred in retrieveCardDetails() in PatientDaoImpl class."+e.getMessage());
								}
							finally
								{
									session.close();
									factory.close();
								}
						if(ehfEnrollmentFamily!=null)
							{
								//To check if card is deactivated due to patient's death
								if(ehfEnrollmentFamily.getEnrollStatus()!=null && !"".equalsIgnoreCase(ehfEnrollmentFamily.getEnrollStatus())
										&& !(config.getString("inactivate_card_death_status").equalsIgnoreCase(ehfEnrollmentFamily.getEnrollStatus())))
									{
										String enrollParntId=ehfEnrollmentFamily.getEnrollPrntId();
										StringBuffer query1=new StringBuffer(); 
										List<String> l=null;
										query1.append("from EhfEnrollment where enrollPrntId='"+enrollParntId+"'");
										if(patientVo.getCardType()!=null && !"".equalsIgnoreCase(patientVo.getCardType()))
											{
												if(patientVo.getCardType().equalsIgnoreCase("E"))
												{
									
													query1.append(" and empType='"+config.getString("Role.Employee")+"'");
												}
												else if(patientVo.getCardType().equalsIgnoreCase("P"))
												{
													query1.append(" and empType in('"+config.getString("Role.Pensioner")+"','"+config.getString("Role.ServicePensioner")+"')");
												}
												else if(patientVo.getCardType().equalsIgnoreCase("NB"))
												{
													query1.append(" and empType in('"+config.getString("Role.Pensioner")+"','"+config.getString("Role.ServicePensioner")+"','"+config.getString("Role.Employee")+"')");
												}
												/*Bifurcation Changes*/
												if(patientVo.getSchemeId()!=null && !("").equalsIgnoreCase(patientVo.getSchemeId()))
												{
													query1.append(" and Scheme='"+schemeId+"' ");
												}
											}
							
										try{
												if ( !hibernateTemplate.getSessionFactory ().getCurrentSession ().isOpen () )
													{
														throw new RuntimeException ( "No Session open for executing the query " + query1 ) ;
													}
												else
													l = hibernateTemplate.getSessionFactory ().getCurrentSession().createQuery ( query1.toString() ).list();
											}
										catch(Exception e)
											{
												GLOGGER.error("Exception occurred in retrieveCardDetails() while creating session factory instance in PatientDaoImpl class."+e.getMessage());
											}
										Iterator ite=l.iterator();
										EhfEnrollment ehfEnrollment=null;
										if(ite.hasNext())
											{
												ehfEnrollment= (EhfEnrollment)ite.next();
											}
										if(ehfEnrollment!=null)
										{
											if(patientVo.getCardType()!=null && !"".equalsIgnoreCase(patientVo.getCardType()) 
													&& (patientVo.getCardType().equalsIgnoreCase("NB")))
												{
													if(ehfEnrollment.getEmpType()!=null)
														{
															if(ehfEnrollment.getEmpType().equalsIgnoreCase(config.getString("Role.Pensioner"))||
																	ehfEnrollment.getEmpType().equalsIgnoreCase(config.getString("Role.ServicePensioner")))
																{
																	patientVO=new PatientVO();
																	patientVO.setMsg(config.getString("invalid_born_baby"));
																	return patientVO;
																}
														/*	if(ehfEnrollment.getScheme()!=null)
																{
																	if(!ehfEnrollment.getScheme().equalsIgnoreCase(config.getString("AP")))
																		{
																			patientVO=new PatientVO();
																			patientVO.setMsg(config.getString("invalid_born_baby_Scheme"));
																			return patientVO;
																			
																		}
																}*/
														}
													
												}	
											
											patientVO=new PatientVO();
											
											if(ehfEnrollment.getScheme()!=null && !"".equalsIgnoreCase(ehfEnrollment.getScheme()) && !(config.getString("Group.Pex").equalsIgnoreCase(patientVo.getRoleId())))
												{
													/*if(patientVo.getSchemeId()!=null && !"".equalsIgnoreCase(patientVo.getSchemeId())
															&& !config.getString("COMMON").equalsIgnoreCase(patientVo.getSchemeId()))
														{
															if(!ehfEnrollment.getScheme().equalsIgnoreCase(patientVo.getSchemeId()))
																{
																	patientVO=new PatientVO();
																	patientVO.setMsg(config.getString("invalid_state_remarks"));
																	return patientVO;
																}
														}*/
													if(patientVo.getHospitalCode()!=null && !"".equalsIgnoreCase(patientVo.getHospitalCode()))
														{
															EhfmEDCHospActionDtls ehfmEDCHospActionDtls=genericDao.findById(EhfmEDCHospActionDtls.class,EhfmEDCHospActionDtlsId.class,new EhfmEDCHospActionDtlsId(patientVo.getHospitalCode(),ehfEnrollment.getScheme()));
															if(ehfmEDCHospActionDtls!=null)
																{
																	if(ehfmEDCHospActionDtls.getHospStatus()!=null)
																		{
																			patientVO=new PatientVO();
																			if("S".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
																				{
																					patientVO.setMsg(config.getString("scheme_hosp_susp_remarks"));
																					return patientVO;
																				}
																			else if("C".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
																				{
																					patientVO.setMsg(config.getString("scheme_cont_hosp_susp_remarks"));
																					return patientVO;
																				}
																			else if("D".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
																				{
																					patientVO.setMsg(config.getString("scheme_hosp_delist_remarks"));
																					return patientVO;
																				}
																			else if("E".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
																				{
																					patientVO.setMsg(config.getString("scheme_hosp_deempan_remarks"));
																					return patientVO;
																				}
																			else if("SP".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
																				{
																					patientVO.setMsg(config.getString("scheme_hosp_susppay_remarks"));
																					return patientVO;
																				}
																			else if("CP".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
																				{
																					patientVO.setMsg(config.getString("scheme_hosp_cont_susppay_remarks"));
																					return patientVO;
																				}
																			else if("N".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
																				{
																					patientVO.setMsg(config.getString("invalid_hosp_scheme"));
																					return patientVO;
																				}
																		}
																}
															else if(ehfmEDCHospActionDtls==null)
																{
																	patientVO.setMsg(config.getString("invalid_hosp_scheme"));
																	return patientVO;
																}
														}
												}
											
											if(ehfEnrollment.getScheme()!=null)
											{
												patientVO.setSchemeId(ehfEnrollment.getScheme());
											}
											else
											{
												patientVO.setSchemeId(config.getString("Scheme.ap"));
											}
											
											patientVO.setEmpCode(ehfEnrollment.getEmpCode());
											patientVO.setDateOfBirth(ehfEnrollmentFamily.getEnrollDob().toString());
											patientVO.setGender(Character.toString(ehfEnrollmentFamily.getEnrollGender()));
											patientVO.setFirstName(ehfEnrollmentFamily.getEnrollName());
											patientVO.setRelation(ehfEnrollmentFamily.getEnrollRelation());
											patientVO.setCaste(ehfEnrollment.getEmpCommu());
											patientVO.setContactNo(ehfEnrollment.getEmpHphone());
											patientVO.setAddr1(ehfEnrollment.getEmpHno());
											patientVO.setAddr2(ehfEnrollment.getEmpHstreetno());
											patientVO.setState(ehfEnrollment.getEmpHstate());
											patientVO.setDistrictCode(ehfEnrollment.getEmpHdist());
											patientVO.setMdl_mpl(ehfEnrollment.getEmpHmandMunciSel());
											patientVO.setMandalCode(ehfEnrollment.getEmpHmandMunci());
											patientVO.setVillageCode(ehfEnrollment.getEmpHvillTwn());
											patientVO.setPrc(ehfEnrollment.getPrc());
											patientVO.setPayScale(ehfEnrollment.getPayScale());
											patientVO.setDept(ehfEnrollment.getDept());
											patientVO.setDeptHod(ehfEnrollment.getDeptHod());
											patientVO.setPostDist(ehfEnrollment.getPostDist());
											patientVO.setPostDDOcode(ehfEnrollment.getPostDdoCode());
											patientVO.setServDsgn(ehfEnrollment.getServDsgn());
											patientVO.setDdoOffUnit(ehfEnrollment.getDdoOffUnit());
											patientVO.setCurrPay(ehfEnrollment.getCurrPay());
											patientVO.setDesignation(ehfEnrollment.getDesignation());
											patientVO.setAadharID(ehfEnrollmentFamily.getAadharId());
											patientVO.setAadharEID(ehfEnrollmentFamily.getAadharEid());
											patientVO.setEkycFlag(ehfEnrollmentFamily.getEkycDoneYn());//Chandana - 8326 - for abha
											patientVO.setAbhaId(ehfEnrollmentFamily.getAbhaId());//Chandana - 8326 - getting and setting to patinevo
										
											
											String slab=getSlabType(ehfEnrollment.getPayScale());
											if(slab!=null)
											{
												patientVO.setSlab(slab);
											}
											else
											{
												patientVO.setSlab(config.getString("Slab.SemiPrivateWard"));
											}
						
											if(ehfEnrollment.getEmpHemail()!=null)
											{
												patientVO.seteMailId(ehfEnrollment.getEmpHemail());
											}
							
											if(patientVo.getCardNo().endsWith("01"))
											{
												patientVO.setMaritalStatus(ehfEnrollment.getEmpMaritalStat());
												if(patientVo.getCardType()!=null && !"".equalsIgnoreCase(patientVo.getCardType()) 
														&& (patientVo.getCardType().equalsIgnoreCase("E") || (patientVo.getCardType().equalsIgnoreCase("NB") && (ehfEnrollment!=null && ehfEnrollment.getEmpType()!=null && ehfEnrollment.getEmpType().equalsIgnoreCase(config.getString("Role.Employee"))))) )
												{
													String service=ehfEnrollment.getService();
													String categoryName=ehfEnrollment.getServDsgn();
													String hod=ehfEnrollment.getDeptHod();
													if(ehfEnrollment.getDeptDesignation()!=null && !"".equals(ehfEnrollment.getDeptDesignation()))
													{
														Long desgnId=Long.parseLong(ehfEnrollment.getDeptDesignation());
							
														List<LabelBean> designationList= new ArrayList<LabelBean>();
														StringBuffer query =null;
														try
														{
															query = new StringBuffer();
															query.append("select distinct a.deptDesignation as VALUE from EhfDesignationMst a where a.id.hod='"+hod+"' and a.id.service='"+service+"' and a.id.categoryName='"+categoryName+"' and a.id.dsgnId="+desgnId);     
															designationList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
															if(designationList!=null && designationList.size()>0)
															{
																patientVO.setOccupationCd(designationList.get(0).getVALUE());
															}
														}
														catch(Exception e)
														{
															GLOGGER.error("Exception Occurred in retrieveCardDetails() in PatientDaoImpl class."+e.getMessage());
														}
													}
												}
												if(patientVo.getCardType()!=null && !"".equalsIgnoreCase(patientVo.getCardType()) 
														&& (patientVo.getCardType().equalsIgnoreCase("P") ||patientVo.getCardType().equalsIgnoreCase("E")))
														{
															Map<String, Object> photoMap=new HashMap<String, Object>();
															photoMap.put("employeeParntId", enrollParntId);
															photoMap.put("attachType", config.getString("FamilyHeadPhoto.AttachType"));
															photoMap.put("attachactiveYN","Y");
															EhfEmployeeDocAttach ehfEmployeeDocAttach=genericDao.findFirstByPropertyMatch(EhfEmployeeDocAttach.class,photoMap);
															if(ehfEmployeeDocAttach!=null)
															{
																patientVO.setPhoto(ehfEmployeeDocAttach.getSavedName());
															}
														}
												
											}
											else
											{
												patientVO.setPhoto(ehfEnrollmentFamily.getEnrollPhoto());
												if(patientVo.getCardType()!=null && !"".equalsIgnoreCase(patientVo.getCardType()) 
														&& patientVo.getCardType().equalsIgnoreCase("NB"))
													patientVO.setPhoto("");
											}
										}
										
										else
										{
										
															patientVO=new PatientVO();
															patientVO.setMsg(config.getString("invalid_state_remarks"));
															//return patientVO;
														
												
										}
									}
								else
								{
									patientVO=new PatientVO();
									patientVO.setMsg(config.getString("inactivate_card_death_remarks"));
								}
							}
						return patientVO;
					}
				/*pavan*/
				//pravalika added or condition for delhi journalist retrieve
			else if(patientVo.getCardType().equalsIgnoreCase("J") || patientVo.getCardType().equalsIgnoreCase("DJ"))
				{
					EhfJrnlstFamily ehfJrnlstFamily=null;
				
				List<PatientVO> jrnlstRecrds=new ArrayList<PatientVO>();
					try
						{
							factory= hibernateTemplate.getSessionFactory();
							session=factory.openSession();
							StringBuffer query = new StringBuffer();
							query.append("select ejf.journalEnrollId as journalEnrollId ");/*,ejf.journalEnrollParntId as journalEnrollParntId , ejf.journalEnrollSno as journalEnrollSno , ejf.enrollStatus as enrollStatus ,");*/
							
							query.append("  from EhfJrnlstFamily ejf ,  EhfmJournalist ej , EhsJournalistEnrollment eje where ejf.journalCradNo='"+patientVo.getCardNo()+"' and ejf.cardValid='Y' and  eje.journalEnrollParntId=ejf.journalEnrollParntId and eje.journalCode=ej.userNo and ej.dsgnId in ('DG91','DG92') ");
							if ("DJ".equalsIgnoreCase(patientVo.getCardType())) {
							    query.append(" AND ej.djhsFlag = 'Y'");
							}
							jrnlstRecrds=session.createQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(PatientVO.class)).list();
							if(jrnlstRecrds!=null && jrnlstRecrds.size()>0)
								ehfJrnlstFamily=genericDao.findById(EhfJrnlstFamily.class,String.class,jrnlstRecrds.get(0).getJournalEnrollId());
							
						}
					catch(Exception e)
						{
							e.printStackTrace();
							GLOGGER.error("Exception Occurred in retrieveCardDetails() in PatientDaoImpl for journalists class."+e.getMessage());
						}
					finally
						{
							session.close();
							factory.close();
						}
					
					if(ehfJrnlstFamily!=null)
						{
						EhsJournalistEnrollment ehsJournalistEnrollment =null;
							if(ehfJrnlstFamily.getEnrollStatus()!=null && !"".equalsIgnoreCase(ehfJrnlstFamily.getEnrollStatus())
									&& !(config.getString("inactivate_card_death_status").equalsIgnoreCase(ehfJrnlstFamily.getEnrollStatus())))
								{
									String journalEnrollParntId=ehfJrnlstFamily.getJournalEnrollParntId();
									StringBuffer query1=new StringBuffer(); 
									patientVO=new PatientVO();
									try{ehsJournalistEnrollment =genericDao.findById(EhsJournalistEnrollment.class,String.class,journalEnrollParntId);}
									catch(Exception e){
										e.printStackTrace();
//										GLOGGER.error("Exception occurred in retrieveCardDetails() while creating session factory instance in PatientDaoImpl class."+e.getMessage());
										}
									if(ehsJournalistEnrollment!=null && !(config.getString("Group.Pex").equalsIgnoreCase(patientVo.getRoleId())))
										{
											
											if(ehsJournalistEnrollment.getScheme()!=null && !"".equalsIgnoreCase(ehsJournalistEnrollment.getScheme())
											&& (ehsJournalistEnrollment.getScheme()).equalsIgnoreCase(schemeId)		
											)
												{
													if(patientVo.getHospitalCode()!=null && !"".equalsIgnoreCase(patientVo.getHospitalCode()))
														{
															EhfmEDCHospActionDtls ehfmEDCHospActionDtls=genericDao.findById(EhfmEDCHospActionDtls.class,EhfmEDCHospActionDtlsId.class,new EhfmEDCHospActionDtlsId(patientVo.getHospitalCode(),ehsJournalistEnrollment.getScheme()));
															if(ehfmEDCHospActionDtls!=null)
																{
																	if(ehfmEDCHospActionDtls.getHospStatus()!=null)
																		{
																			patientVO=new PatientVO();
																			if("S".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
																				{
																					patientVO.setMsg(config.getString("scheme_hosp_susp_remarks"));
																					return patientVO;
																				}
																			else if("C".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
																				{
																					patientVO.setMsg(config.getString("scheme_cont_hosp_susp_remarks"));
																					return patientVO;
																				}
																			else if("D".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
																				{
																					patientVO.setMsg(config.getString("scheme_hosp_delist_remarks"));
																					return patientVO;
																				}
																			else if("E".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
																				{
																					patientVO.setMsg(config.getString("scheme_hosp_deempan_remarks"));
																					return patientVO;
																				}
																			else if("SP".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
																				{
																					patientVO.setMsg(config.getString("scheme_hosp_susppay_remarks"));
																					return patientVO;
																				}
																			else if("CP".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
																				{
																					patientVO.setMsg(config.getString("scheme_hosp_cont_susppay_remarks"));
																					return patientVO;
																				}
																			else if("N".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
																				{
																					patientVO.setMsg(config.getString("invalid_hosp_scheme"));
																					return patientVO;
																				}
																		}
																}
															else if(ehfmEDCHospActionDtls==null)
																{
																	patientVO.setMsg(config.getString("invalid_hosp_scheme"));
																	return patientVO;
																}
														}
												}
											else
											{
																patientVO=new PatientVO();
																patientVO.setMsg(config.getString("invalid_state_remarks"));
																return patientVO;		
											}
										}
									
									if(ehsJournalistEnrollment.getScheme()!=null)
									{
										patientVO.setSchemeId(ehsJournalistEnrollment.getScheme());
									}
									else
									{
										patientVO.setSchemeId(config.getString("Scheme.ap"));
									}
									
									patientVO.setEmpCode(ehsJournalistEnrollment.getJournalCode());
									patientVO.setDateOfBirth(ehfJrnlstFamily.getEnrollDob().toString());
									patientVO.setGender(ehfJrnlstFamily.getEnrollGender());
									patientVO.setFirstName(ehfJrnlstFamily.getEnrollName());
									patientVO.setRelation(ehfJrnlstFamily.getEnrollRelation());
									patientVO.setCaste(ehsJournalistEnrollment.getCommunity());
									patientVO.setContactNo(ehsJournalistEnrollment.getHomeMobileNo());
									patientVO.setAddr1(ehsJournalistEnrollment.getHomeHouseNo());
									patientVO.setAddr2(ehsJournalistEnrollment.getHomeStreetname());
									patientVO.setState(ehsJournalistEnrollment.getHomeState());
									patientVO.setDistrictCode(ehsJournalistEnrollment.getHomeDistrict());
									patientVO.setMdl_mpl(ehsJournalistEnrollment.getHomeMandMunciSel());
									patientVO.setMandalCode(ehsJournalistEnrollment.getHomeMunc());
									patientVO.setVillageCode(ehsJournalistEnrollment.getHomeVillage());
									patientVO.setEkycFlag(ehfJrnlstFamily.getEkycDoneYN());//Chandana - 8326 - For abha
									patientVO.setAbhaId(ehfJrnlstFamily.getAbhaId());//Chandana - 8326 - for abha id
									patientVO.setSlab(config.getString("Slab.SemiPrivateWard"));
				
									if(ehsJournalistEnrollment.getHomeEmail()!=null)
										patientVO.seteMailId(ehsJournalistEnrollment.getHomeEmail());
					
									if(patientVo.getCardNo().endsWith("01"))
										{
											patientVO.setMaritalStatus(ehsJournalistEnrollment.getJournalMaritalStatus());
											if(patientVo.getCardType()!=null && !"".equalsIgnoreCase(patientVo.getCardType()) && patientVo.getCardType().equalsIgnoreCase("J"))
												{
													if(ehsJournalistEnrollment.getDesignation()!=null && !"".equals(ehsJournalistEnrollment.getDesignation()))
														{
															List<LabelBean> designationList= new ArrayList<LabelBean>();
															StringBuffer query =null;
															try
																{
																	query = new StringBuffer();
																	query.append("select distinct a.dsgnName as VALUE from EhfmDesignation a where a.id.dsgnId='"+ehsJournalistEnrollment.getDesignation()+"' ");     
																	designationList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
																	if(designationList!=null && designationList.size()>0)
																		{
																			patientVO.setOccupationCd(designationList.get(0).getVALUE());
																		}
																}
															catch(Exception e)
																{
																	e.printStackTrace();
//																	GLOGGER.error("Exception Occurred in retrieveCardDetails() in PatientDaoImpl class."+e.getMessage());
																}
														}
												}
										}
									patientVO.setPhoto(ehfJrnlstFamily.getEnrollPhoto());
									
								}
							else
								{
									patientVO=new PatientVO();
									patientVO.setMsg(config.getString("inactivate_card_death_remarks"));
								}
						}
					return patientVO;
				}
				/*pavan*/
			else if(patientVo.getCardType().equalsIgnoreCase("R"))
			{
				EhfJrnlstFamily ehfJrnlstFamily=null;
				
			List<PatientVO> jrnlstRecrds=new ArrayList<PatientVO>();
				try
					{
						factory= hibernateTemplate.getSessionFactory();
						session=factory.openSession();
						StringBuffer query = new StringBuffer();
						query.append("select ejf.journalEnrollId as journalEnrollId ");/*,ejf.journalEnrollParntId as journalEnrollParntId , ejf.journalEnrollSno as journalEnrollSno , ejf.enrollStatus as enrollStatus ,");*/
						
						query.append("  from EhfJrnlstFamily ejf ,  EhfmJournalist ej , EhsJournalistEnrollment eje where ejf.journalCradNo='"+patientVo.getCardNo()+"' and ejf.cardValid='Y' and  eje.journalEnrollParntId=ejf.journalEnrollParntId and eje.journalCode=ej.userNo and ej.dsgnId='DG92'");
					
						jrnlstRecrds=session.createQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(PatientVO.class)).list();
						ehfJrnlstFamily=genericDao.findById(EhfJrnlstFamily.class,String.class,jrnlstRecrds.get(0).getJournalEnrollId());
						
					}
				catch(Exception e)
					{
						e.printStackTrace();
//						GLOGGER.error("Exception Occurred in retrieveCardDetails() in PatientDaoImpl for journalists class."+e.getMessage());
					}
				finally
					{
						session.close();
						factory.close();
					}
				
				if(ehfJrnlstFamily!=null)
					{
					EhsJournalistEnrollment ehsJournalistEnrollment =null;
						if(ehfJrnlstFamily.getEnrollStatus()!=null && !"".equalsIgnoreCase(ehfJrnlstFamily.getEnrollStatus())
								&& !(config.getString("inactivate_card_death_status").equalsIgnoreCase(ehfJrnlstFamily.getEnrollStatus())))
							{
								String journalEnrollParntId=ehfJrnlstFamily.getJournalEnrollParntId();
								StringBuffer query1=new StringBuffer(); 
								patientVO=new PatientVO();
								try{ehsJournalistEnrollment =genericDao.findById(EhsJournalistEnrollment.class,String.class,journalEnrollParntId);}
								catch(Exception e){
									e.printStackTrace();
//									GLOGGER.error("Exception occurred in retrieveCardDetails() while creating session factory instance in PatientDaoImpl class."+e.getMessage());
									}
								if(ehsJournalistEnrollment!=null && !(config.getString("Group.Pex").equalsIgnoreCase(patientVo.getRoleId())))
									{
										if(ehsJournalistEnrollment.getScheme()!=null && !"".equalsIgnoreCase(ehsJournalistEnrollment.getScheme())
										&& (ehsJournalistEnrollment.getScheme()).equalsIgnoreCase(schemeId)		
										)
											{
												if(patientVo.getHospitalCode()!=null && !"".equalsIgnoreCase(patientVo.getHospitalCode()))
													{
														EhfmEDCHospActionDtls ehfmEDCHospActionDtls=genericDao.findById(EhfmEDCHospActionDtls.class,EhfmEDCHospActionDtlsId.class,new EhfmEDCHospActionDtlsId(patientVo.getHospitalCode(),ehsJournalistEnrollment.getScheme()));
														if(ehfmEDCHospActionDtls!=null)
															{
																if(ehfmEDCHospActionDtls.getHospStatus()!=null)
																	{
																		patientVO=new PatientVO();
																		if("S".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
																			{
																				patientVO.setMsg(config.getString("scheme_hosp_susp_remarks"));
																				return patientVO;
																			}
																		else if("C".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
																			{
																				patientVO.setMsg(config.getString("scheme_cont_hosp_susp_remarks"));
																				return patientVO;
																			}
																		else if("D".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
																			{
																				patientVO.setMsg(config.getString("scheme_hosp_delist_remarks"));
																				return patientVO;
																			}
																		else if("E".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
																			{
																				patientVO.setMsg(config.getString("scheme_hosp_deempan_remarks"));
																				return patientVO;
																			}
																		else if("SP".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
																			{
																				patientVO.setMsg(config.getString("scheme_hosp_susppay_remarks"));
																				return patientVO;
																			}
																		else if("CP".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
																			{
																				patientVO.setMsg(config.getString("scheme_hosp_cont_susppay_remarks"));
																				return patientVO;
																			}
																		else if("N".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
																			{
																				patientVO.setMsg(config.getString("invalid_hosp_scheme"));
																				return patientVO;
																			}
																	}
															}
														else if(ehfmEDCHospActionDtls==null)
															{
																patientVO.setMsg(config.getString("invalid_hosp_scheme"));
																return patientVO;
															}
													}
											}
										else
										{
															patientVO=new PatientVO();
															patientVO.setMsg(config.getString("invalid_state_remarks"));
															return patientVO;		
										}
									}
								
								if(ehsJournalistEnrollment.getScheme()!=null)
								{
									patientVO.setSchemeId(ehsJournalistEnrollment.getScheme());
								}
								else
								{
									patientVO.setSchemeId(config.getString("Scheme.ap"));
								}
								
								patientVO.setEmpCode(ehsJournalistEnrollment.getJournalCode());
								patientVO.setDateOfBirth(ehfJrnlstFamily.getEnrollDob().toString());
								patientVO.setGender(ehfJrnlstFamily.getEnrollGender());
								patientVO.setFirstName(ehfJrnlstFamily.getEnrollName());
								patientVO.setRelation(ehfJrnlstFamily.getEnrollRelation());
								patientVO.setCaste(ehsJournalistEnrollment.getCommunity());
								patientVO.setContactNo(ehsJournalistEnrollment.getHomeMobileNo());
								patientVO.setAddr1(ehsJournalistEnrollment.getHomeHouseNo());
								patientVO.setAddr2(ehsJournalistEnrollment.getHomeStreetname());
								patientVO.setState(ehsJournalistEnrollment.getHomeState());
								patientVO.setDistrictCode(ehsJournalistEnrollment.getHomeDistrict());
								patientVO.setMdl_mpl(ehsJournalistEnrollment.getHomeMandMunciSel());
								patientVO.setMandalCode(ehsJournalistEnrollment.getHomeMunc());
								patientVO.setVillageCode(ehsJournalistEnrollment.getHomeVillage());
								patientVO.setEkycFlag(ehfJrnlstFamily.getEkycDoneYN());//Chandana - 8326 - for abha
								patientVO.setSlab(config.getString("Slab.SemiPrivateWard"));
			
								if(ehsJournalistEnrollment.getHomeEmail()!=null)
									patientVO.seteMailId(ehsJournalistEnrollment.getHomeEmail());
				
								if(patientVo.getCardNo().endsWith("01"))
									{
										patientVO.setMaritalStatus(ehsJournalistEnrollment.getJournalMaritalStatus());
										if(patientVo.getCardType()!=null && !"".equalsIgnoreCase(patientVo.getCardType()) && patientVo.getCardType().equalsIgnoreCase("R"))
											{
												if(ehsJournalistEnrollment.getDesignation()!=null && !"".equals(ehsJournalistEnrollment.getDesignation()))
													{
														List<LabelBean> designationList= new ArrayList<LabelBean>();
														StringBuffer query =null;
														try
															{
																query = new StringBuffer();
																query.append("select distinct a.dsgnName as VALUE from EhfmDesignation a where a.id.dsgnId='"+ehsJournalistEnrollment.getDesignation()+"' ");     
																designationList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
																if(designationList!=null && designationList.size()>0)
																	{
																		patientVO.setOccupationCd(designationList.get(0).getVALUE());
																	}
															}
														catch(Exception e)
															{
																e.printStackTrace();
//																GLOGGER.error("Exception Occurred in retrieveCardDetails() in PatientDaoImpl class."+e.getMessage());
															}
													}
											}
									}
								patientVO.setPhoto(ehfJrnlstFamily.getEnrollPhoto());
								
							}
						else
							{
								patientVO=new PatientVO();
								patientVO.setMsg(config.getString("inactivate_card_death_remarks"));
							}
					}
				return patientVO;
			}
			}		
		patientVO=null;
		return patientVO;
	}
	/**
	 * ;
	 * @param String payGrade
	 * @return String slab
	 * @function This Method is Used For Retrieving Slab type for given payGrade
	 */	
	@Override
	public String getSlabType(String payGrade) throws Exception {
		List<LabelBean> payGradeList= new ArrayList<LabelBean>();
		String slabType=null;
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct a.slab as VALUE from EhfmPayGradeMst a where a.id.payGrade='"+payGrade+"'");     
			payGradeList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
			if(payGradeList!=null && payGradeList.size()>0)
			{
				slabType=payGradeList.get(0).getVALUE();
			}
			/*List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
			criteriaList.add(new GenericDAOQueryCriteria("id.payGrade",payGrade,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("id.srcName","CD1335",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("prcYear","",GenericDAOQueryCriteria.CriteriaOperator.DESC));
		    List<EhfmPayGradeMst> ehfCaseLst = genericDao.findAllByCriteria(EhfmPayGradeMst.class, criteriaList);
		    if(ehfCaseLst.size()>0)
			{
				slabType=ehfCaseLst.get(0).getSlab();
			}*/
		}
		catch(Exception e)
		{
//			GLOGGER.error("Exception Occurred in getSlabType() in PatientDaoImpl class."+e.getMessage());
		}
		return slabType;
	}  
	@Override
	/**
	 * ;
	 * @param PatientVO patientVO
	 * @return int noOfRecords
	 * @function This Method is Used For Registering Direct Patient
	 */
	public  int registerPatient(PatientVO patientVO) throws Exception {
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date crtDt;
		Date dateOfBirth;
		Date cardIssueDt;
		try {
			EhfPatient ehfPatient = null;
			ehfPatient= new EhfPatient();
			crtDt=sdfw.parse(patientVO.getCrtDt());
			//Chandana - 8442 - Added this condition for storing the abha number in ehf_patient table if abha number is not null 
			if(patientVO.getAbhaId() != null || !"".equalsIgnoreCase(patientVO.getAbhaId()))
				ehfPatient.setAbhaNo(patientVO.getAbhaId());
			if(patientVO.getCardIssueDt()!=null && !patientVO.getCardIssueDt().equalsIgnoreCase(""))
			{
				cardIssueDt = sdf.parse(patientVO.getCardIssueDt());
				ehfPatient.setCardIssueDt(cardIssueDt);
			}
			if(patientVO.getDateOfBirth()!=null && !patientVO.getDateOfBirth().equalsIgnoreCase(""))
			{
				dateOfBirth = sdf.parse(patientVO.getDateOfBirth());
				ehfPatient.setEnrollDob(dateOfBirth);
			}
			ehfPatient.setPatientId(patientVO.getPatientId());
			if(patientVO.getCardType()!=null)
				if(patientVO.getCardType().equalsIgnoreCase("NB"))
					{
						ehfPatient.setCardType(config.getString("NewBornBaby"));
						ehfPatient.setNewBornBaby("Y");
					}
				else
					{
						ehfPatient.setCardType(patientVO.getCardType());
						ehfPatient.setNewBornBaby("N");
					}
			else
				{
					ehfPatient.setNewBornBaby("N");
					ehfPatient.setCardType(patientVO.getCardType());
				}
			ehfPatient.setEmployeeNo(patientVO.getEmpCode());
			ehfPatient.setCardNo(patientVO.getRationCard());
			ehfPatient.setFamilyHead(patientVO.getFamilyHead());
			ehfPatient.setChildYn(patientVO.getChild_yn());
			ehfPatient.setCrtDt(crtDt);
			ehfPatient.setCrtUsr(patientVO.getCrtUsr());
			ehfPatient.setName(patientVO.getFirstName());
			ehfPatient.setGender(patientVO.getGender());
			ehfPatient.setOccupationCd(patientVO.getOccupationCd());
			ehfPatient.setContactNo(Long.parseLong(patientVO.getContactNo()));
			ehfPatient.setRelation(patientVO.getRelation());
			ehfPatient.setAge(Long.parseLong(patientVO.getAge()));
			ehfPatient.setAgeMonths(Long.parseLong(patientVO.getAgeMonths()));
			ehfPatient.setAgeDays(Long.parseLong(patientVO.getAgeDays()));
			ehfPatient.setCaste(patientVO.getCaste());
			ehfPatient.setMaritalStatus(patientVO.getMaritalStatus());
			ehfPatient.setSlab(patientVO.getSlab());
			if(patientVO.geteMailId()!=null && !patientVO.geteMailId().equals(""))
			{
				ehfPatient.setEmailId(patientVO.geteMailId());
			}
			ehfPatient.setHouseNo(patientVO.getAddr1());
			ehfPatient.setStreet(patientVO.getAddr2());
			ehfPatient.setState(patientVO.getState());
			ehfPatient.setDistrictCode(patientVO.getDistrictCode());
			ehfPatient.setMdl_mpl(patientVO.getMdl_mpl());
			ehfPatient.setMandalCode(patientVO.getMandalCode());
			ehfPatient.setVillageCode(patientVO.getVillageCode());
			ehfPatient.setPinCode(patientVO.getPincode());
			ehfPatient.setSrcRegistration(patientVO.getSrcRegistration());
			ehfPatient.setPatientIpop(patientVO.getPatient_ipop());
			ehfPatient.setPhaseId(Long.parseLong(patientVO.getPhaseId()));

			ehfPatient.setPatientScheme(patientVO.getPatientScheme());

			//ehfPatient.setRenewal(Long.parseLong(patientVO.getRenewal()));

			ehfPatient.setSchemeId(patientVO.getSchemeId());
			ehfPatient.setSourceId(Long.parseLong(patientVO.getCid()));

			ehfPatient.setcHouseNo(patientVO.getPermAddr1());
			ehfPatient.setcStreet(patientVO.getPermAddr2());
			ehfPatient.setcState(patientVO.getC_state());
			ehfPatient.setcDistrictCode(patientVO.getC_district_code());
			ehfPatient.setC_mdl_mpl(patientVO.getC_mdl_mpl());
			ehfPatient.setcMandalCode(patientVO.getC_mandal_code());
			ehfPatient.setcVillageCode(patientVO.getC_village_code());
			ehfPatient.setcPinCode(patientVO.getC_pin_code());

			ehfPatient.setPrc(patientVO.getPrc());
			ehfPatient.setPayScale(patientVO.getPayScale());
			ehfPatient.setDept(patientVO.getDept());
			ehfPatient.setDeptHod(patientVO.getDeptHod());
			ehfPatient.setPostDist(patientVO.getPostDist());
			ehfPatient.setPostDDOcode(patientVO.getPostDDOcode());
			ehfPatient.setServDsgn(patientVO.getServDsgn());
			ehfPatient.setDdoOffUnit(patientVO.getDdoOffUnit());
			ehfPatient.setCurrPay(patientVO.getCurrPay());
			ehfPatient.setDesignation(patientVO.getDesignation());
			ehfPatient.setAadharID(patientVO.getAadharID());
			ehfPatient.setAadharEID(patientVO.getAadharEID());
			
			ehfPatient.setRegHospId(patientVO.getRegHospId());
			ehfPatient.setRegHospDate(crtDt);
			if("Telephonic Registration".equalsIgnoreCase(patientVO.getRegState()))
			{
				ehfPatient.setIntimationId(patientVO.getTelephonicId());
				EhfTelephonicRegn ehfmTelephonicRegn=genericDao.findById(EhfTelephonicRegn.class, String.class, patientVO.getTelephonicId());
				ehfmTelephonicRegn.setPatientId(patientVO.getPatientId());
				ehfmTelephonicRegn.setEmployeeNo(patientVO.getEmpCode());
				ehfmTelephonicRegn.setLstUpdDt(crtDt);
				ehfmTelephonicRegn.setLstUpdUsr(patientVO.getCrtUsr());
				genericDao.save(ehfmTelephonicRegn);
			}
			
			/*
			 * Added by Srikalyan for Capturing Biometrics of TG Patients in Government Hospitals.
			 */
			if(patientVO.getTgGovPatCond()!=null && !"".equalsIgnoreCase(patientVO.getTgGovPatCond())
					&& "Y".equalsIgnoreCase(patientVO.getTgGovPatCond())
					&& patientVO.getFingerPrint()!=null && patientVO.getFingerCaptured()!=null 
					&& patientVO.getBioRegFlag()!=null && !"".equalsIgnoreCase(patientVO.getBioRegFlag())
					&& "Y".equalsIgnoreCase(patientVO.getBioRegFlag()))
				
				{
					EhfPatientBioReg ehfPatientBioReg=new EhfPatientBioReg();
					
					ehfPatientBioReg.setPatientId(patientVO.getPatientId());
					ehfPatientBioReg.setFingerPrint(patientVO.getFingerPrint());
					ehfPatientBioReg.setFingerCaptured(patientVO.getFingerCaptured());
					ehfPatientBioReg.setHandCaptured(patientVO.getBioHand());
					ehfPatientBioReg.setCardNo(ehfPatient.getCardNo());
					ehfPatientBioReg.setEmployeeNo(ehfPatient.getEmployeeNo());
					ehfPatientBioReg.setName(ehfPatient.getName());
					ehfPatientBioReg.setCardType(ehfPatient.getCardType());
					ehfPatientBioReg.setPatientScheme(ehfPatient.getPatientScheme());
					ehfPatientBioReg.setSchemeId(ehfPatient.getSchemeId());
					ehfPatientBioReg.setRegHospId(ehfPatient.getRegHospId());
					ehfPatientBioReg.setCrtUsr(ehfPatient.getCrtUsr());
					ehfPatientBioReg.setCrtDt(crtDt);
					
					ehfPatientBioReg = genericDao.save(ehfPatientBioReg);
				}
			ehfPatient = genericDao.save(ehfPatient);
		//EhfPatJbpmProcess ehfPatJbpmProcess= new EhfPatJbpmProcess();
		//ehfPatJbpmProcess.setPatientId(patientVO.getPatientId());
		//ehfPatJbpmProcess.setProcessInstanceId(patientVO.getProcessInstanceId());
		//genericDao.save(ehfPatJbpmProcess);

		if(ehfPatient!=null){
			return 1;
		}
		} 
		catch (ParseException e) {
			GLOGGER.error("Error occured in registerPatient() in patientDaoImpl class."+e.getMessage());
			return 0;
		}
		return 1;
	}
	 /*
		 * Added by Srikalyan to get the Biometric Details 
		 * for corresponding Registered Patient
		 */
		@Override
		public PatientVO getBiomDtls(CommonDtlsVO commonDtlsVO)
			{
				PatientVO patientVO=new PatientVO();
				try
				{
					EhfPatientBioReg ehfPatientBioReg = genericDao.findById(EhfPatientBioReg.class, String.class,commonDtlsVO.getPATID());
					if(ehfPatientBioReg!=null)
						{
							patientVO.setFingerPrint(ehfPatientBioReg.getFingerPrint());
							patientVO.setFingerCaptured(ehfPatientBioReg.getFingerCaptured());
							patientVO.setBioHand(ehfPatientBioReg.getHandCaptured());
						}
				}
				catch (Exception e) {
					e.printStackTrace();	
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<LabelBean> getHospital(String userId,String roleId)throws Exception {
		List<LabelBean> hospitalList = new ArrayList<LabelBean>();
		SessionFactory factory=null;
		Session session=null;
		try
		{
			factory= hibernateTemplate.getSessionFactory();
			session=factory.openSession();
			StringBuffer query = new StringBuffer();
			StringBuffer query1=new StringBuffer();
			Query hQuery;
			List hospList=null;
			ArrayList<String> hospIdList=null;
			Iterator hospItr=null;
			List resultList=null;
			Iterator resultItr=null;
			if(roleId.equals(config.getString("Group.Mithra")))
			{
				query.append("from EhfmHospMithraDtls amu where amu.id.mithraId='"+userId+"' and amu.endDt="+null);
				hQuery=session.createQuery(query.toString());
				hospList=hQuery.list();
				if(hospList.size()>0)
				{
					hospIdList=new ArrayList<String>();
					hospItr=hospList.iterator();
					while(hospItr.hasNext())
					{
						EhfmHospMithraDtls ehfmHospMithraDtls=(EhfmHospMithraDtls)hospItr.next();
						EhfmHospMithraDtlsId ehfmHospMithraDtlsId=ehfmHospMithraDtls.getId();
						GLOGGER.info("hospIds "+ehfmHospMithraDtlsId.getHospId());
						hospIdList.add(ehfmHospMithraDtlsId.getHospId());
					}
				}
				else
				{
					GLOGGER.info("No associated hosp for this user");
				}
			}
			else if(roleId.equals(config.getString("Group.Medco")))
			{
				query.append("from EhfmMedcoDtls anu where anu.id.medcoId='"+userId+"' and anu.endDate="+null);

				hQuery=session.createQuery(query.toString());
				hospList=hQuery.list();
				if(hospList.size()>0)
				{
					hospIdList=new ArrayList();
					hospItr=hospList.iterator();
					while(hospItr.hasNext())
					{
						EhfmMedcoDtls ehfmMedcoDetails=(EhfmMedcoDtls)hospItr.next();
						EhfmMedcoDtlsId ehfmMedcoDetailsId=ehfmMedcoDetails.getId();
						GLOGGER.info("hospIds "+ehfmMedcoDetailsId.getHospId());
						hospIdList.add(ehfmMedcoDetailsId.getHospId());
					}
				}
				else
				{
					GLOGGER.info("No associated hosp for this user");
				}
			}
			String activeYn=config.getString("ActiveYn");
			if(hospIdList!=null)
			{
	    		//query1.append("from EhfmHospitals ah where ah.hospId in (:param) and ah.hospActiveYN='"+activeYn+"'");
	    		query1.append("from EhfmHospitals ah where ah.hospId in (:param) ");
				Query hQuery1=session.createQuery(query1.toString());
				hQuery1.setParameterList("param", hospIdList);
				resultList=hQuery1.list();
				if(resultList.size()>0)
					GLOGGER.info("Hosp details retrieved");
				resultItr=resultList.iterator();
				while(resultItr.hasNext())
				{
					EhfmHospitals ehfmHospitals=(EhfmHospitals)resultItr.next();
					LabelBean labelBean=new LabelBean();
					labelBean.setID(ehfmHospitals.getHospId());
					labelBean.setVALUE(ehfmHospitals.getHospName()+","+ehfmHospitals.getHospCity());  //"+ehfmHospitals.getHouseNumber()+",
					labelBean.setNATURE(ehfmHospitals.getHospType()+"");
					labelBean.setHospActiveYn(ehfmHospitals.getHospActiveYN());
					hospitalList.add(labelBean);
				}
			}   		
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getHospital() in PatientDaoImpl class."+e.getMessage());
		}
		finally
		{
			session.close();
			factory.close();
		}
		return hospitalList;
	}
	/**
     * ;
     * @param HashMap<String,String> searchMap
     * @return PatientVO registeredPatientsList
     * @function This Method is Used For retrieving list of registered patients for given search data
     */
	@Override
	public PatientVO getRegisteredPatients(HashMap<String, String> searchMap) {
		SessionFactory factory=null;
		Session session=null;
		String database=config.getString("Database");
		PatientVO patVO = new PatientVO();
		ArrayList<PatientVO> registeredPatientsList =new ArrayList<PatientVO>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		SimpleDateFormat sqlf = new SimpleDateFormat("yyyy-MM-dd");
		String schemeId=searchMap.get("schemeId").toString();
		String hospId=null;
		try
		{
		if(searchMap.get("userHospId")!=null)
		{
			hospId=searchMap.get("userHospId").toString();
		}
		else
		{
			patVO.setMsg("No hospital is mapped for this user");
		}
		String patientName="";
		String patientNo="";
		String cardNo="";
		String gender="";
		String stateId="";
		String districtId="";
		String fromDate;
		String sqlFromDate;
		String toDate;
		String sqlToDate;
		Query hQuery=null;
		Iterator searchDataItr=null;
		int startIndex=Integer.parseInt(searchMap.get("lStrStartIndex"));
		int rowsPerPage=Integer.parseInt(searchMap.get("lStrRowsperpage"));
		
		factory= hibernateTemplate.getSessionFactory();
		session=factory.openSession();
		StringBuffer searchQuery = new StringBuffer();
		StringBuffer countQuery = new StringBuffer();
		String patientIpop=config.getString("PatientIPOP.RegisterStatus");
		countQuery.append("select count(ap.patientId) from EhfPatient ap where ap.regHospId='"+hospId+"' and ap.registerYN is null and ap.nabhHosp is null "); // and ap.patientIpop='"+patientIpop+"'");
		searchQuery.append("from EhfPatient ap where ap.regHospId='"+hospId+"' and ap.registerYN is null and ap.nabhHosp is null "); //and ap.patientIpop='"+patientIpop+"'");
		if(schemeId!=null && !("").equalsIgnoreCase(schemeId))
		{
			searchQuery.append(" and ap.schemeId='"+schemeId+"' ");
			countQuery.append(" and ap.schemeId='"+schemeId+"' ");
		}
		if(searchMap.get("patientName")!=null)
		{
			patientName=searchMap.get("patientName").toString();
			countQuery.append("  and upper(ap.name) like  upper('%"+patientName.trim()+"%') ");
			searchQuery.append("  and upper(ap.name) like  upper('%"+patientName.trim()+"%') ");
		}
		if(searchMap.get("patientNo")!=null)
		{
			patientNo=searchMap.get("patientNo").toString();
			countQuery.append("and ap.patientId='"+patientNo+"'");
			searchQuery.append("and ap.patientId='"+patientNo+"'");
		}
		if(searchMap.get("cardNo")!=null)
		{
			cardNo=searchMap.get("cardNo").toString().toUpperCase();
			countQuery.append("  and upper(ap.cardNo) like  upper('%"+cardNo.trim()+"%') ");
			searchQuery.append("  and upper(ap.cardNo) like  upper('%"+cardNo.trim()+"%') ");
		}
		if(searchMap.get("gender")!=null)
		{
			gender=searchMap.get("gender").toString();
			countQuery.append("and ap.gender='"+gender+"'");
			searchQuery.append("and ap.gender='"+gender+"'");
		}
		if(searchMap.get("state")!=null)
		{
			stateId=searchMap.get("state").toString();
			countQuery.append("and ap.state='"+stateId+"'");
			searchQuery.append("and ap.state='"+stateId+"'");
		}
		if(searchMap.get("district")!=null)
		{
			districtId=searchMap.get("district").toString();
			countQuery.append("and ap.districtCode='"+districtId+"'");
			searchQuery.append("and ap.districtCode='"+districtId+"'");
		}
		if(searchMap.get("fromDate")!=null && searchMap.get("toDate")!=null)
		{
			fromDate=searchMap.get("fromDate").toString();
			sqlFromDate=sqlf.format(sdf.parse(fromDate).getTime());
			toDate=searchMap.get("toDate").toString();
			
			//To include todate in search criteria--adding date+1 
			Calendar cal = Calendar.getInstance();  
        	cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(toDate)); 
        	cal.add(Calendar.DAY_OF_YEAR, 1); // <--  
        	Date tomorrow = cal.getTime();  
        	 String lStrToDate = new SimpleDateFormat("dd-MM-yyyy").format(tomorrow);
        	 //End of date+1 
			 
			sqlToDate=sqlf.format(sdf.parse(toDate).getTime());
			
			if(database.equalsIgnoreCase("ORACLE"))
			{
				countQuery.append("and ap.regHospDate between  TO_DATE('"+fromDate+"','DD-MM-YYYY') and TO_DATE('"+lStrToDate+"','DD-MM-YYYY')");
				searchQuery.append("and ap.regHospDate between  TO_DATE('"+fromDate+"','DD-MM-YYYY') and TO_DATE('"+lStrToDate+"','DD-MM-YYYY')");
			}
			else if(database.equalsIgnoreCase("MYSQL"))
			{
				countQuery.append("and ap.regHospDate between '"+sqlFromDate+"' and '"+sqlToDate+"'");
				searchQuery.append("and ap.regHospDate between '"+sqlFromDate+"' and '"+sqlToDate+"'");
			}
		}
		Long count = (Long) session.createQuery(countQuery.toString()).uniqueResult();
		if(hospId!=null && (config.getString("HOSP_NIMS").equalsIgnoreCase(hospId)))
			searchQuery.append("order by ap.regHospDate desc");
		else
		searchQuery.append("order by ap.regHospDate asc");
		hQuery=session.createQuery(searchQuery.toString());
		

		patVO.setTotRowCount(count.intValue());
		hQuery.setFirstResult(startIndex);
		hQuery.setMaxResults(rowsPerPage);

		searchDataItr=hQuery.iterate();
		while(searchDataItr.hasNext())
		{
			EhfPatient ehfPatient=(EhfPatient)searchDataItr.next();
			PatientVO patientVO=new PatientVO();
			
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
			criteriaList.add(new GenericDAOQueryCriteria("casePatientNo",ehfPatient.getPatientId(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		    List<EhfCase> ehfCaseLst = genericDao.findAllByCriteria(EhfCase.class, criteriaList);
		    if(ehfCaseLst!=null && ehfCaseLst.size()>0){
		    	patientVO.setCaseId(ehfCaseLst.get(0).getCaseId());
		    }	
		    else{
		    	patientVO.setCaseId("NA");
		    }
			patientVO.setFirstName(ehfPatient.getName());
			patientVO.setPatientId(ehfPatient.getPatientId());
			patientVO.setRationCard(ehfPatient.getCardNo());
			String district=getLocationName(ehfPatient.getDistrictCode());
			patientVO.setDistrictCode(district);
			if(ehfPatient.getGender()!=null && "M".equalsIgnoreCase(ehfPatient.getGender()))
			{
				patientVO.setGender("Male");
			}
			else if(ehfPatient.getGender()!=null && "F".equalsIgnoreCase(ehfPatient.getGender()))
			{
				patientVO.setGender("Female");
			}
			//patientVO.setGender(ehfPatient.getGender());
			patientVO.setAge(ehfPatient.getAge().toString());
			patientVO.setRegHospDt(sdfw.format(ehfPatient.getRegHospDate()));
			if(ehfPatient.getIntimationId()!=null)
			{
				patientVO.setTelephonicId(ehfPatient.getIntimationId());
			}
			registeredPatientsList.add(patientVO);
		}
		patVO.setRegisteredPatList(registeredPatientsList);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getRegisteredPatients() in PatientDaoImpl class."+e.getMessage());
		}
		finally
		{
			session.close();
			factory.close();
		}
		return patVO;
	}
	/**
	 * ;
	 * @param String patientId
	 * @return AsritPatient asritPatient
	 * @function This Method is Used For retrieving PatientDetails for given PatientId
	 */
	@Override
	public EhfPatient getPatientDetails(String patientId) throws Exception {
		EhfPatient ehfPatient=new EhfPatient();
		try{
			ehfPatient=genericDao.findById(EhfPatient.class,String.class,patientId);
		}
		catch(Exception e){
			GLOGGER.error("Exception Occurred in getPatientDetails() in PatientDaoImpl class."+e.getMessage());
		}
		return ehfPatient;
	}
	/**
	 * ;
	 * @return List<String> complaintList
	 * @function This Method is Used For getting Complaints List
	 */
	@Override
	public List<String> getComplaints(String mainCompId) throws Exception {
		
		List<String> complaintList = new ArrayList<String>();	
    	Iterator ehfmComplaintMstItr=null;
    	EhfmComplaintMst ehfmComplMst=null;
		String activeYn=config.getString("ActiveYn");
		String compArray[]=mainCompId.split("~");
		List<Object> compList=new ArrayList<Object>();
		for(int i=0;i<compArray.length;i++)
		{
		compList.add(compArray[i]);
		}
    	try
    	{
    		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
    		criteriaList.add(new GenericDAOQueryCriteria("activeYn",activeYn,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("id.mainComplCode",compList,GenericDAOQueryCriteria.CriteriaOperator.IN));
			criteriaList.add(new GenericDAOQueryCriteria("complName",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
		    List<EhfmComplaintMst> EhfmComplaintMstList = genericDao.findAllByCriteria(EhfmComplaintMst.class, criteriaList);
		    ehfmComplaintMstItr=EhfmComplaintMstList.iterator();
    		while(ehfmComplaintMstItr.hasNext())
    		{
    			ehfmComplMst=(EhfmComplaintMst)ehfmComplaintMstItr.next();    	
    			complaintList.add(ehfmComplMst.getId().getComplCode()+"~"+ehfmComplMst.getComplName()+"@");
    		}
    	}
    	catch(Exception e)
    	{
    		GLOGGER.error("Exception Occurred in getComplaints() in PatientDaoImpl class."+e.getMessage());
    	}
		return complaintList;
	}
	/**
	 * ;
	 * @param String doctorType
	 * @param String hospId
	 * @return List<LabelBean> doctorsList
	 * @function This Method is Used For retrieving Doctors List associated for the specified hospId
	 */
	@Override
public List<LabelBean> getDoctorsList(String doctorType, String hospId,String schemeId)
			throws Exception {
		List<LabelBean> doctorsList = new ArrayList<LabelBean>();
		String ramcoRole=config.getString("Group.Medco");
		String activeYn=config.getString("ActiveYn");
		String apprvStatus=null;
		String scheme=schemeId;
		List<String> schemeLst=new ArrayList<String>();
		schemeLst.add(scheme);
		schemeLst.add(config.getString("COMMON"));
    	try
    	{
    		if((config.getString("doctorType.Ramco")).equalsIgnoreCase(doctorType))
    		{
    		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
    		criteriaList.add(new GenericDAOQueryCriteria("activeYN",activeYn,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 criteriaList.add(new GenericDAOQueryCriteria("id.hospId",hospId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 criteriaList.add(new GenericDAOQueryCriteria("id.hospId",hospId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 criteriaList.add(new GenericDAOQueryCriteria("scheme",schemeLst,GenericDAOQueryCriteria.CriteriaOperator.IN));
			
			//criteriaList.add(new GenericDAOQueryCriteria("regNo",null,GenericDAOQueryCriteria.CriteriaOperator.NOT_EQUALS));
			// criteriaList.add(new GenericDAOQueryCriteria("cordType",ramcoRole,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			// criteriaList.add(new GenericDAOQueryCriteria("cordName",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
		     List<EhfmMedcoDtls> ehfmMedcoMasterLst = genericDao.findAllByCriteria(EhfmMedcoDtls.class, criteriaList);
    		for(EhfmMedcoDtls ehfmMedcoMaster:ehfmMedcoMasterLst)
    		{
    			LabelBean labelBean =new LabelBean();
    			labelBean.setID(ehfmMedcoMaster.getId().getMedcoId());
    			EhfmUsers EhfmUserDtls = genericDao.findById(EhfmUsers.class, String.class, ehfmMedcoMaster.getId().getMedcoId());
    			if(EhfmUserDtls != null)
    			{
    			labelBean.setVALUE(EhfmUserDtls.getFirstName());
    			if(EhfmUserDtls.getLastName() !=null)
    			labelBean.setVALUE(EhfmUserDtls.getFirstName() + " " + EhfmUserDtls.getLastName());
    			}
    			doctorsList.add(labelBean);
    		}
    		}
    		else if((config.getString("doctorType.DutyDoctor")).equalsIgnoreCase(doctorType))
    	      { 
    			apprvStatus=config.getString("Doctor.ApprvStatus");
    			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
        		criteriaList.add(new GenericDAOQueryCriteria("isActiveYn",activeYn,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
    			 criteriaList.add(new GenericDAOQueryCriteria("id.hospId",hospId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
    			//criteriaList.add(new GenericDAOQueryCriteria("regNum",null,GenericDAOQueryCriteria.CriteriaOperator.NOT_EQUALS));
    			 criteriaList.add(new GenericDAOQueryCriteria("apprvStatus",apprvStatus,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
    			 criteriaList.add(new GenericDAOQueryCriteria("apprvStatus",apprvStatus,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
    			 criteriaList.add(new GenericDAOQueryCriteria("id.scheme",schemeLst,GenericDAOQueryCriteria.CriteriaOperator.IN));
    		     List<EhfmDutyDctrs> asrimDutyDctrsLst = genericDao.findAllByCriteria(EhfmDutyDctrs.class, criteriaList);
    	         for(EhfmDutyDctrs asrimDutyDctrs:asrimDutyDctrsLst)
    	    		{
    	    			LabelBean labelBean =new LabelBean();
    	    			labelBean.setID(asrimDutyDctrs.getId().getRegNum());
    	    			labelBean.setVALUE(asrimDutyDctrs.getDoctorName());
    	    			doctorsList.add(labelBean);
    	    		}
    	      }
    		else if((config.getString("doctorType.InHouseDoctor").equalsIgnoreCase(doctorType)) || (config.getString("doctorType.Consultant").equalsIgnoreCase(doctorType)))
    	      {
    			apprvStatus=config.getString("Doctor.ApprvStatus");
    			String consultant_Yn=null;
    			if((config.getString("doctorType.InHouseDoctor")).equalsIgnoreCase(doctorType))
    				consultant_Yn="N";
    			else if(config.getString("doctorType.Consultant").equalsIgnoreCase(doctorType))
    				consultant_Yn="Y";
    			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
    			criteriaList.add(new GenericDAOQueryCriteria("isConsultant",consultant_Yn,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
        		criteriaList.add(new GenericDAOQueryCriteria("isActiveYn",activeYn,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
    			criteriaList.add(new GenericDAOQueryCriteria("id.hospId",hospId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
    			 criteriaList.add(new GenericDAOQueryCriteria("id.scheme",schemeLst,GenericDAOQueryCriteria.CriteriaOperator.IN));
    			//criteriaList.add(new GenericDAOQueryCriteria("regNum",null,GenericDAOQueryCriteria.CriteriaOperator.NOT_EQUALS));
    			 criteriaList.add(new GenericDAOQueryCriteria("apprvStatus",apprvStatus,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
    			 criteriaList.add(new GenericDAOQueryCriteria("splstName",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
    			 List<EhfmSplstDctrs> asrimSplstDctrsLst = genericDao.findAllByCriteria(EhfmSplstDctrs.class, criteriaList);
    			 for(EhfmSplstDctrs asrimSplstDctrs:asrimSplstDctrsLst)
 	    		{
 	    			LabelBean labelBean =new LabelBean();
 	    			labelBean.setID(asrimSplstDctrs.getId().getRegNum());
 	    			labelBean.setVALUE(asrimSplstDctrs.getSplstName());
 	    			doctorsList.add(labelBean);
 	    		}
    	       }
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		GLOGGER.error("Exception Occurred in getDoctorsList() in PatientDaoImpl class."+e.getMessage());
    	}
		return doctorsList;
	}
	@Override
	/**
	 * ;
	 * @param PatientVO patientVO
	 * @return String caseId
	 * @function This Method is Used For Registering Patient Case as IP/OP/Chronic OP
	 */
	@Transactional
	public String saveCaseDetails(PatientVO patientVO) throws Exception {
		String lStrCaseId=null;
		//String lStrChronicCaseId=null;
		String caseNo=null;
		String hospGovu=patientVO.getHospGov();
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		Date lCurrentDate=sdfw.parse(patientVO.getCrtDt());
		String temp=null;
		//Start CR#4511-SriHari-10/10/24
		String highEndProformaId = "";
		 if(patientVO.getCategory()!=null && "S12MMD".equalsIgnoreCase(patientVO.getCategory()) && patientVO.getHighEndProformaId() != null)
			 highEndProformaId = patientVO.getHighEndProformaId();
		//end CR#4511-SriHari
		SessionFactory factory= hibernateTemplate.getSessionFactory();
		Session session=factory.openSession();
		EhfCase ehfCase= null; 
		EhfDentalPatientDtls ehfDentalPatientDtls=null;
		EhfCase ehfCaseCheck=genericDao.findFirstByPropertyMatch(EhfCase.class, "casePatientNo",patientVO.getPatientId());
		if(ehfCaseCheck==null)
		{
			//EhfmSeq ehfmSeqCase = getSeqNextVal("CASE_ID");
			String liNextVal = null;
			if(patientVO.getPatientScheme()!=null && config.getString("Scheme.JHS").equalsIgnoreCase(patientVO.getPatientScheme()))
				{
					if(patientVO.getSchemeId()!=null && config.getString("Scheme.ap").equalsIgnoreCase(patientVO.getSchemeId())){
							liNextVal = "JAP"+getSequence("EHFM_Journalist_Case_ID_AP");
					}
					else if(patientVO.getSchemeId()!=null && config.getString("Scheme.tg").equalsIgnoreCase(patientVO.getSchemeId())){
							liNextVal = "JHS"+getSequence("EHFM_Journalist_Case_ID_TG");
					}
					else if(patientVO.getSchemeId()!=null && "1".equalsIgnoreCase(patientVO.getSchemeId())){
							liNextVal = getSequence("EHFM_Journalist_Case_ID");
					}
				}
			else
				{
					if(patientVO.getSchemeId()!=null && config.getString("Scheme.ap").equalsIgnoreCase(patientVO.getSchemeId()))
						{
							liNextVal = "AP"+getSequence("CASE_ID_AP");
						}
					else if(patientVO.getSchemeId()!=null && config.getString("Scheme.tg").equalsIgnoreCase(patientVO.getSchemeId()))
						{
							liNextVal = "EHS"+getSequence("CASE_ID_TG");
						}
					else if(patientVO.getSchemeId()!=null && "1".equalsIgnoreCase(patientVO.getSchemeId()))
						{
							liNextVal = getSequence("CASE_ID");
						}
				}	
			//int seqNextVal=Integer.parseInt(liNextVal);
			//ehfmSeqCase.setSeqNextVal(Long.valueOf(seqNextVal + 1));
			//updateSequenceVal(ehfmSeqCase);
			patientVO.setCaseId(liNextVal);
			lStrCaseId=patientVO.getCaseId();
			ehfCase = new EhfCase();
			ehfCase.setCrtUsr(patientVO.getCrtUsr());
			ehfCase.setCrtDt(lCurrentDate);
			ehfCase.setLstUpdDt(lCurrentDate);
			ehfCase.setLstUpdUsr(patientVO.getCrtUsr());
			ehfCase.setCochlearYN(patientVO.getCochlearYN());
			ehfCase.setOrganTransYN(patientVO.getOrganTransYN());
		}
		else{
			lStrCaseId=ehfCaseCheck.getCaseId();
			ehfCase=ehfCaseCheck;
			ehfCase.setLstUpdDt(lCurrentDate);
			ehfCase.setLstUpdUsr(patientVO.getCrtUsr());
		}
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			
			EhfPatient ehfPatient=new EhfPatient();			
			EhfPatientHospDiagnosis ehfmPatientHospDiagnosis = genericDao.findById(EhfPatientHospDiagnosis.class, String.class, patientVO.getPatientId());
				if (ehfmPatientHospDiagnosis==null){
					ehfmPatientHospDiagnosis = new EhfPatientHospDiagnosis();
					ehfmPatientHospDiagnosis.setCrtUsr(patientVO.getCrtUsr());
					ehfmPatientHospDiagnosis.setCrtDt(lCurrentDate);
				}
				else{
					ehfmPatientHospDiagnosis.setLstUpdDt(lCurrentDate);
					ehfmPatientHospDiagnosis.setLstUpdUsr(patientVO.getCrtUsr());
				}
			try{

				if(patientVO.getPatientId()!=null){
					ehfPatient=genericDao.findById(EhfPatient.class,String.class,patientVO.getPatientId());
					
					String lStrTestDesc=null;
					List<AttachmentVO> lAttachList=patientVO.getGenAttachmentsList();
					if(ehfPatient.getRegHospId()!=null && config.getString("HOSP_NIMS").equalsIgnoreCase(ehfPatient.getRegHospId()))
					{
						SessionFactory factory2=hibernateTemplate.getSessionFactory();
						Session session2 =factory2.openSession();
						try
						{
						StringBuilder hql=new StringBuilder();
						hql.append( " delete from EhfPatientTests a where a.patientId='"+patientVO.getPatientId()+"' ");
						Query query;
						query=session.createQuery(hql.toString());
						query.executeUpdate();
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
						finally
						{
							session2.close();
							factory2.close();
							
						}
						
						
					}
					for(AttachmentVO attachmentVO:lAttachList){

						EhfPatientTests patientTests=new EhfPatientTests();
						patientTests.setSno(Long.parseLong(attachmentVO.getAttachId()));
						patientTests.setTestId(attachmentVO.getTestId());
						if(attachmentVO.getTestId()!=null && attachmentVO.getTestId().contains("OI"))
						{
							patientTests.setTestName(attachmentVO.getTestName());
						}
						if(lStrTestDesc!=null){
							lStrTestDesc=lStrTestDesc+","+attachmentVO.getTestName();
						}
						else{
							lStrTestDesc=attachmentVO.getTestName();  
						}
						patientTests.setPatientId(patientVO.getPatientId());
						patientTests.setAttachTotalPath(attachmentVO.getFileReportPath());                	            
						if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Submit"))
						{
						if((config.getString("PatientIPOP.IP")).equalsIgnoreCase(patientVO.getPatientType()))	
						    {
							SessionFactory Sessfactory=hibernateTemplate.getSessionFactory();
							Session sess =factory.openSession();
							try
							{
							StringBuffer query=new StringBuffer();
							query.append("update EhfPatientTests a set invest_price='' ");
							query.append( " where a.patientId='"+patientVO.getPatientId()+"'");
							sess.createQuery(query.toString()).executeUpdate();
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
							finally
							{
								Sessfactory.close();
								sess.close();
							}
							
							
							
							}
						}
						if(attachmentVO.getPrice()!=null)
						patientTests.setInvestPrice(attachmentVO.getPrice());
						patientTests.setCrtUsr(patientVO.getCrtUsr());
						patientTests.setCrtDt(lCurrentDate);
						genericDao.save(patientTests);
					}
					//Updating existing test file path
					List<AttachmentVO> lUpdGenAttachList=patientVO.getUpdGenAttachmentsList();
					EhfPatientTests patientTests=null;
					for(AttachmentVO attachmentVO:lUpdGenAttachList)
					{
						Map<String,Object> testMap=new HashMap<String,Object>();
						testMap.put("patientId",patientVO.getPatientId() );
						testMap.put("testId",attachmentVO.getTestId());
						List<EhfPatientTests> patientTestsList=genericDao.findAllByPropertyMatch(EhfPatientTests.class, testMap);
						if(patientTestsList!=null && patientTestsList.size()>0)
						{
							patientTests=patientTestsList.get(0);
							patientTests.setAttachTotalPath(attachmentVO.getFileReportPath());
							patientTests.setLstUpdUsr(patientVO.getCrtUsr());
							patientTests.setLstUpdDt(lCurrentDate);
							genericDao.save(patientTests);
						}
					}
					
					String lStrPastHistory="";
					if(patientVO.getPastHistory()!=null)
					{
						for(int i=0;i<patientVO.getPastHistory().length;i++){
							lStrPastHistory = lStrPastHistory+patientVO.getPastHistory()[i];
							if(i!=patientVO.getPastHistory().length-1){
								lStrPastHistory = lStrPastHistory+"~";
							}
						}
					}

					String lStrExamFind="";	   
					if(patientVO.getExaminationFnd()!=null){
					for(int i=0;i<patientVO.getExaminationFnd().length;i++){
						lStrExamFind = lStrExamFind+patientVO.getExaminationFnd()[i];
						if(i!=patientVO.getExaminationFnd().length-1){
							lStrExamFind = lStrExamFind+"~";
						}
					}
					}					
					//saving in ehfcase for ip/op/chronic op
						String lStrHospitalCodeInCaseNo = ehfPatient.getRegHospId().substring(3);
						String nflag=config.getString("NFlag");
						if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Yes"))
						{
							ehfPatient.setRegisterYN(null);
							caseNo=config.getString("CASE")+config.getString("SLASH_VALUE")+lStrHospitalCodeInCaseNo+config.getString("SLASH_VALUE")+lStrCaseId+config.getString("SLASH_VALUE")+"D";
						}
						else if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Submit"))
						{
							caseNo=config.getString("CASE")+config.getString("SLASH_VALUE")+lStrHospitalCodeInCaseNo+config.getString("SLASH_VALUE")+lStrCaseId;
							ehfPatient.setRegisterYN("Y");
							ehfCase.setCaseRegnDate(lCurrentDate);
							
						}
						else
						{
							ehfPatient.setRegisterYN(null);
							caseNo=config.getString("CASE")+config.getString("SLASH_VALUE")+lStrHospitalCodeInCaseNo+config.getString("SLASH_VALUE")+lStrCaseId+config.getString("SLASH_VALUE")+"D";
						}
						
						ehfCase.setCaseId(lStrCaseId);
						ehfCase.setCaseNo(caseNo);
						ehfCase.setCaseHospCode(ehfPatient.getRegHospId());
						ehfCase.setSourceId(1L);
						ehfCase.setCasePatientNo(patientVO.getPatientId());						
						ehfCase.setPhaseId(ehfPatient.getPhaseId());
						if(ehfPatient.getNewBornBaby()!=null && !ehfPatient.getNewBornBaby().equalsIgnoreCase(""))
							ehfCase.setNewBornBaby(ehfPatient.getNewBornBaby());
						
						if(ehfPatient.getPatientScheme()!=null){
								ehfCase.setPatientScheme(ehfPatient.getPatientScheme());
							}
						//ehfCase.setRenewal(ehfPatient.getRenewal());

						//ehfCase.setRenewal(ehfPatient.getRenewal());

						ehfCase.setSchemeId(ehfPatient.getSchemeId());
						ehfCase.setSurgCount(0L);
						ehfCase.setPckAppvAmt(0L);
						if(patientVO.getCochlearYN()!=null && patientVO.getCochlearYN()!="")
						{
						ehfCase.setCochlearYN(patientVO.getCochlearYN());
						}
						
						if(patientVO.getOrganTransYN()!=null && patientVO.getOrganTransYN()!="")
						{
							ehfCase.setOrganTransYN(patientVO.getOrganTransYN());
						}
						EhfEnrollment ehfEnrollment=null;
						List<EhfEnrollment> ehfEnrollmentList=genericDao.findAllByPropertyMatch(EhfEnrollment.class,"empCode",ehfPatient.getEmployeeNo());
						if(ehfEnrollmentList!=null && ehfEnrollmentList.size()>0)
						{
							ehfEnrollment=ehfEnrollmentList.get(0);
						}
						
						//saving in ehfEmpCaseDtls for ip/op/chronic op
						EhfEmpCaseDtls ehfEmpCaseDtls = genericDao.findById(EhfEmpCaseDtls.class, String.class, lStrCaseId);
						if(ehfEmpCaseDtls==null){
							ehfEmpCaseDtls = new EhfEmpCaseDtls();
							ehfEmpCaseDtls.setCrtDt(lCurrentDate);
							ehfEmpCaseDtls.setCrtUsr(patientVO.getCrtUsr());
						}
						else{
							ehfEmpCaseDtls.setLstUpdDt(lCurrentDate);
							ehfEmpCaseDtls.setLstUpdUsr(patientVO.getCrtUsr());
						}
						ehfEmpCaseDtls.setCaseId(lStrCaseId);
						ehfEmpCaseDtls.setCaseNo(caseNo);
						ehfEmpCaseDtls.setCaseHospCode(ehfPatient.getRegHospId());
						ehfEmpCaseDtls.setCasePatientNo(patientVO.getPatientId());
						ehfEmpCaseDtls.setCardType(ehfPatient.getCardType());
						ehfEmpCaseDtls.setCardNo(ehfPatient.getCardNo());
						ehfEmpCaseDtls.setCaseRegnDate(lCurrentDate);
						ehfEmpCaseDtls.setEmployeeNo(ehfPatient.getEmployeeNo());
						ehfEmpCaseDtls.setRelation(ehfPatient.getRelation());
						ehfEmpCaseDtls.setFamilyHead(ehfPatient.getFamilyHead());
						ehfEmpCaseDtls.setChildYn(ehfPatient.getChildYn());
						if(ehfEnrollment!=null)
						{
							if(ehfEnrollment.getDeptHod()!=null)
							{
								ehfEmpCaseDtls.setDeptHod(ehfEnrollment.getDeptHod());
							}
							if(ehfEnrollment.getPostDist()!=null)
							{
								ehfEmpCaseDtls.setPostDist(ehfEnrollment.getPostDist());
							}
							if(ehfEnrollment.getPostDdoCode()!=null)
							{
								ehfEmpCaseDtls.setDdoCode(ehfEnrollment.getPostDdoCode());
							}
							if(ehfEnrollment.getDdoOffUnit()!=null)
							{
								ehfEmpCaseDtls.setStoCode(ehfEnrollment.getDdoOffUnit());
							}
						}
						//saving in audit for ip/op/chronic op 
						EhfAudit ehfAudit = new EhfAudit();Long lActOrder = 1L;
						StringBuffer lQueryBuffer = new StringBuffer();
						String args[] = new String[1];
						lQueryBuffer.append(" select max(au.id.actOrder) as COUNT from EhfAudit au where au.id.caseId=? ");
						args[0] = lStrCaseId;
						List<PatientVO> actOrderList = genericDao.executeHQLQueryList(
								PatientVO.class, lQueryBuffer.toString(), args);
						if (actOrderList != null && !actOrderList.isEmpty() && actOrderList.get(0).getCOUNT() != null) {
							if (actOrderList.get(0).getCOUNT().longValue() >= 0)
								lActOrder = actOrderList.get(0).getCOUNT().longValue() + 1;
						}
						ehfAudit.setId(new EhfAuditId(lActOrder,lStrCaseId));
						ehfAudit.setActBy(patientVO.getCrtUsr());
/*						if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Yes")){
							ehfAudit.setRemarks("Case Drafted");
						}else{
						ehfAudit.setRemarks(patientVO.getIpCaseRemarks());
						}*/
						ehfAudit.setCrtUsr(patientVO.getCrtUsr());
						ehfAudit.setCrtDt(lCurrentDate);
						ehfAudit.setLangId(config.getString("en_US"));
						
						if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Yes")){
							ehfCase.setCaseStatus(config.getString("CASE.CaseDrafted"));							
							ehfEmpCaseDtls.setCaseStatus(config.getString("CASE.CaseDrafted"));
							ehfAudit.setActId(config.getString("CASE.CaseDrafted"));
						}
						
						EhfPatientPersonalHistory ehfmPatientPersonalHistory = genericDao.findById(EhfPatientPersonalHistory.class, String.class, patientVO.getPatientId());
						if(ehfmPatientPersonalHistory!=null){
							ehfmPatientPersonalHistory.setLstUpdDt(lCurrentDate);
							ehfmPatientPersonalHistory.setLstUpdUsr(patientVO.getCrtUsr());
						}else{
                        ehfmPatientPersonalHistory = new EhfPatientPersonalHistory();
                        ehfmPatientPersonalHistory.setCrtDt(lCurrentDate);
						ehfmPatientPersonalHistory.setCrtUsr(patientVO.getCrtUsr());
						}
						ehfmPatientPersonalHistory.setPatientId(patientVO.getPatientId());
						ehfmPatientPersonalHistory.setCaseId(lStrCaseId);
						ehfmPatientPersonalHistory.setDrugHst(patientVO.getDrughistoryid());
						if(patientVO.getDrughistory()!=null ||patientVO.getDrughistory()!="")
						ehfmPatientPersonalHistory.setDrugHstVal(patientVO.getDrughistory());
						if(patientVO.getPersonalHistVal()!=null ||patientVO.getPersonalHistVal()!="")
						{
						String[] result = patientVO.getPersonalHistVal().split("#");
						for (int x=0; x<result.length; x++)
						{
							String[] result1 = result[x].split("~");
							if(result1[0].equalsIgnoreCase("PR1"))
								ehfmPatientPersonalHistory.setAppetite(result1[1]);
							else if(result1[0].equalsIgnoreCase("PR2"))
								ehfmPatientPersonalHistory.setDiet(result1[1]);
							else if(result1[0].equalsIgnoreCase("PR3"))
								ehfmPatientPersonalHistory.setBowels(result1[1]);
							else if(result1[0].equalsIgnoreCase("PR4"))
								ehfmPatientPersonalHistory.setNutrition(result1[1]);
							else if(result1[0].equalsIgnoreCase("PR5"))
								ehfmPatientPersonalHistory.setKnownAllergies(result1[1]);
							else if(result1[0].equalsIgnoreCase("PR6"))
								ehfmPatientPersonalHistory.setAddictions(result1[1]);
							else if(result1[0].equalsIgnoreCase("PR7"))
								ehfmPatientPersonalHistory.setMaritalStatus(result1[1]);
							else if(result1[0].equalsIgnoreCase("PR8"))
								ehfmPatientPersonalHistory.setOccupation(result1[1]);
						}
						}
                        genericDao.save(ehfmPatientPersonalHistory);
						//savig medical history details for dental case sheet 
						EhfDentalOtherExaminations ehfDentalOtherExaminations1 = genericDao.findById(EhfDentalOtherExaminations.class, String.class, patientVO.getPatientId());
						if(ehfDentalOtherExaminations1==null)
						{
							ehfDentalOtherExaminations1=new EhfDentalOtherExaminations();
							ehfDentalOtherExaminations1.setCrtDt(lCurrentDate);
							ehfDentalOtherExaminations1.setCrtUsr(patientVO.getCrtUsr());
						}
						else
						{
							ehfDentalOtherExaminations1.setCrtDt(lCurrentDate);
							ehfDentalOtherExaminations1.setCrtUsr(patientVO.getCrtUsr());
						}
						ehfDentalOtherExaminations1.setCaseId(lStrCaseId);
						ehfDentalOtherExaminations1.setPatientId(patientVO.getPatientId());
						String lStrValueDental="";
						if(patientVO.getMedicalDtlsid()!=null)
						{
							for(int i=0;i<patientVO.getMedicalDtlsid().length;i++)
							{
								lStrValueDental = lStrValueDental+patientVO.getMedicalDtlsid()[i];
							if(i!=patientVO.getMedicalDtlsid().length-1)
							   {
								lStrValueDental = lStrValueDental+"~"; 
							    }
							}
						}
						ehfDentalOtherExaminations1.setMedicalHistory(lStrValueDental);
						ehfDentalOtherExaminations1.setMedicalHistoryOthr(patientVO.getShowMedicalTextval());
						if(patientVO.getProbingdepth()!=null)
						ehfDentalOtherExaminations1.setClinicalProbingDepth(patientVO.getProbingdepth());
						if(patientVO.getPreviousDentalTreatment()!=null)
							ehfDentalOtherExaminations1.setPreviousDentalTreatment(patientVO.getPreviousDentalTreatment());
						if(patientVO.getOcclusion()!=null)
							ehfDentalOtherExaminations1.setOcclusion(patientVO.getOcclusion());
						if(patientVO.getOcclusionType()!=null)
							ehfDentalOtherExaminations1.setOcclusionDivii(patientVO.getOcclusionType());
						if(patientVO.getOcclusionOther()!=null)
							ehfDentalOtherExaminations1.setOcclusionOther(patientVO.getOcclusionOther());
						
						
						genericDao.save(ehfDentalOtherExaminations1);
						
						
						EhfDentalOralExaminations ehfDentalOralExaminations1 = genericDao.findById(EhfDentalOralExaminations.class, String.class, patientVO.getPatientId());
						if(ehfDentalOralExaminations1==null)
						{
							ehfDentalOralExaminations1=new EhfDentalOralExaminations();
							ehfDentalOralExaminations1.setCrtDt(lCurrentDate);
							ehfDentalOralExaminations1.setCrtUsr(patientVO.getCrtUsr());
						}
						else
						{
							ehfDentalOralExaminations1.setCrtDt(lCurrentDate);
							ehfDentalOralExaminations1.setCrtUsr(patientVO.getCrtUsr());
						}
						ehfDentalOralExaminations1.setCaseId(lStrCaseId);
						ehfDentalOralExaminations1.setPatientId(patientVO.getPatientId());
						String lStrValueoral="";
						if(patientVO.getExtraoral()!=null)
						{
							for(int i=0;i<patientVO.getExtraoral().length;i++)
							{
								lStrValueoral = lStrValueoral+patientVO.getExtraoral()[i];
							if(i!=patientVO.getExtraoral().length-1)
							   {
								lStrValueoral = lStrValueoral+"~"; 
							    }
							}
						}
						
						ehfDentalOralExaminations1.setExtraOralCheck(lStrValueoral);
						
						String lStrValueintra="";
						if(patientVO.getIntraoral()!=null)
						{
							for(int i=0;i<patientVO.getIntraoral().length;i++)
							{
								lStrValueintra = lStrValueintra+patientVO.getIntraoral()[i];
							if(i!=patientVO.getIntraoral().length-1)
							   {
								lStrValueintra = lStrValueintra+"~"; 
							    }
							}
						}
						ehfDentalOralExaminations1.setIntraOralCheck(lStrValueintra);
						
						ehfDentalOralExaminations1.setLymphadenopathy(patientVO.getSubdental0());
						ehfDentalOralExaminations1.setJaws(patientVO.getSubdental1());
						ehfDentalOralExaminations1.setTmj(patientVO.getSubdental2());
						ehfDentalOralExaminations1.setFace(patientVO.getSubdental3());
						ehfDentalOralExaminations1.setHardPalate(patientVO.getDntsublistoral0());
						ehfDentalOralExaminations1.setSoftPalate(patientVO.getDntsublistoral1());
						ehfDentalOralExaminations1.setFloorMouth(patientVO.getDntsublistoral2());
						ehfDentalOralExaminations1.setTongue(patientVO.getDntsublistoral3());
						ehfDentalOralExaminations1.setFrenalAttachment(patientVO.getDntsublistoral4());
						ehfDentalOralExaminations1.setBuccalMucosa(patientVO.getDntsublistoral5());
						ehfDentalOralExaminations1.setGingiva(patientVO.getDntsublistoral6());
						if(patientVO.getSubdentalrl0()!=null )
						{
							String lStrValuerlsub="";
						if(patientVO.getSubdentalrl0()!=null)
						{
							for(int i=0;i<patientVO.getSubdentalrl0().length;i++)
							{
								lStrValuerlsub = lStrValuerlsub+patientVO.getSubdentalrl0()[i];
							if(i!=patientVO.getSubdentalrl0().length-1)
							   {
								lStrValuerlsub = lStrValuerlsub+"~"; 
							    }
							}
						}
						ehfDentalOralExaminations1.setLymphadenopathySub(lStrValuerlsub);
						}
						
						if(patientVO.getSubdentaljaws1()!=null && !"".equalsIgnoreCase(patientVO.getSubdentaljaws1()))
						ehfDentalOralExaminations1.setJawsSub(patientVO.getSubdentaljaws1());
						if(patientVO.getSubdentalrltxt()!=null && !("".equalsIgnoreCase(patientVO.getSubdentalrltxt())))
						ehfDentalOralExaminations1.setLymphadenopathySub(patientVO.getSubdentalrltxt());
						if(patientVO.getSubdentaljawstxt()!=null &&!("".equalsIgnoreCase(patientVO.getSubdentaljawstxt())))
						ehfDentalOralExaminations1.setJawsSub(patientVO.getSubdentaljawstxt());
						ehfDentalOralExaminations1.setSwSite(patientVO.getSwSite());
						ehfDentalOralExaminations1.setSwSize(patientVO.getSwSize());
						ehfDentalOralExaminations1.setSwExtension(patientVO.getSwExtension());
						ehfDentalOralExaminations1.setSwColour(patientVO.getSwColour());
						ehfDentalOralExaminations1.setSwConsistency(patientVO.getSwConsistency());
						ehfDentalOralExaminations1.setSwTenderness(patientVO.getSwTenderness());
						ehfDentalOralExaminations1.setSwBorders(patientVO.getSwBorders());
						ehfDentalOralExaminations1.setPsSite(patientVO.getPsSite());
						ehfDentalOralExaminations1.setPsDischarge(patientVO.getPsDischarge());
						genericDao.save(ehfDentalOralExaminations1);
						
						EhfDentalTissueExaminations ehfDentalTissueExaminations1= genericDao.findById(EhfDentalTissueExaminations.class, String.class, patientVO.getPatientId());
						if(ehfDentalTissueExaminations1==null)
						{
							ehfDentalTissueExaminations1=new EhfDentalTissueExaminations();
							ehfDentalTissueExaminations1.setCrtDt(lCurrentDate);
							ehfDentalTissueExaminations1.setCrtUsr(patientVO.getCrtUsr());
						}
						else
						{
							ehfDentalTissueExaminations1.setCrtDt(lCurrentDate);
							ehfDentalTissueExaminations1.setCrtUsr(patientVO.getCrtUsr());
						}
						ehfDentalTissueExaminations1.setCaseId(lStrCaseId);
						ehfDentalTissueExaminations1.setPatientId(patientVO.getPatientId());
						
						
						if(patientVO.getCarriesdecidous()!=null)
						ehfDentalTissueExaminations1.setDeciduousCaries(patientVO.getCarriesdecidous());
						if(patientVO.getMissingdecidous()!=null)
							ehfDentalTissueExaminations1.setDeciduousMissing(patientVO.getMissingdecidous());
						if(patientVO.getMobiledecidous()!=null)
							ehfDentalTissueExaminations1.setMobile(patientVO.getMobiledecidous());
						if(patientVO.getGrosslydecadedecidous()!=null)
							ehfDentalTissueExaminations1.setGrosslyDecayed(patientVO.getGrosslydecadedecidous());
						if(patientVO.getCarriespermanent()!=null)
							ehfDentalTissueExaminations1.setPermanentCaries(patientVO.getCarriespermanent());
						if(patientVO.getRootstumppermannet()!=null)
							ehfDentalTissueExaminations1.setRootGrosslyDecayed(patientVO.getRootstumppermannet());
						if(patientVO.getMobilitypermanent()!=null)
							ehfDentalTissueExaminations1.setMobility(patientVO.getMobilitypermanent());
						if(patientVO.getAttritionpermanent()!=null)
							ehfDentalTissueExaminations1.setAttritionAbrasion(patientVO.getAttritionpermanent());
						if(patientVO.getMissingpermanent()!=null)
							ehfDentalTissueExaminations1.setPermanentMissing(patientVO.getMissingpermanent());
						if(patientVO.getOtherpermanent()!=null)
							ehfDentalTissueExaminations1.setPermanentothers(patientVO.getOtherpermanent());
						
						genericDao.save(ehfDentalTissueExaminations1);
						
						EhfPatientExamFind ehfmPatientExamFind = genericDao.findById(EhfPatientExamFind.class, String.class, patientVO.getPatientId());
						if(ehfmPatientExamFind==null){
						ehfmPatientExamFind = new EhfPatientExamFind();
						ehfmPatientExamFind.setCrtDt(lCurrentDate);
						ehfmPatientExamFind.setCrtUsr(patientVO.getCrtUsr());
						}else{
							ehfmPatientExamFind.setLstUpdDt(lCurrentDate);
							ehfmPatientExamFind.setLstUpdUsr(patientVO.getCrtUsr());
						}
						ehfmPatientExamFind.setPatientId(patientVO.getPatientId());
						ehfmPatientExamFind.setCaseId(lStrCaseId);
						if(patientVO.getTreatmentDntl()!=null || "".equalsIgnoreCase(patientVO.getTreatmentDntl()))
						ehfmPatientExamFind.setTreatmentDntl(patientVO.getTreatmentDntl());
						String[] result2 = patientVO.getExamFndsVal().split("#");
						for (int x=0; x<result2.length; x++)
						{
							String[] result1 = result2[x].split("~");
							if(result1.length>1){
							if(result1[0].equalsIgnoreCase("GE3"))
								ehfmPatientExamFind.setBmi(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE4"))
								ehfmPatientExamFind.setPallor(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE5"))
								ehfmPatientExamFind.setCyanosis(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE6"))
								ehfmPatientExamFind.setClubOfFingrToes(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE7"))
								ehfmPatientExamFind.setLymphadenopathy(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE8"))
								ehfmPatientExamFind.setOedemaInFeet(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE9"))
								ehfmPatientExamFind.setMalnutrition(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE10"))
								ehfmPatientExamFind.setDehydration(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE11"))
								ehfmPatientExamFind.setTemperature(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE12"))
								ehfmPatientExamFind.setPluseRate(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE13"))
								ehfmPatientExamFind.setRespirationRate(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE14"))
								ehfmPatientExamFind.setBpLt(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE1"))
								ehfmPatientExamFind.setHeight(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE2"))
								ehfmPatientExamFind.setWeight(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE15"))
								ehfmPatientExamFind.setBpRt(result1[1]);
							}
						}
						genericDao.save(ehfmPatientExamFind);
						
						ehfmPatientHospDiagnosis.setPatientId(patientVO.getPatientId());
						ehfmPatientHospDiagnosis.setCaseId(lStrCaseId);
						ehfmPatientHospDiagnosis.setPastHistory(lStrPastHistory);
						ehfmPatientHospDiagnosis.setHistoryIllness(patientVO.getPresentHistory());
						ehfmPatientHospDiagnosis.setExamntnFindings(lStrExamFind);
						
						String lStrValue="";	
						if(patientVO.getPatientComplaint()!=null && patientVO.getPatientComplaint().length>0){
						for(int i=0;i<patientVO.getPatientComplaint().length;i++){
							lStrValue = lStrValue+patientVO.getPatientComplaint()[i];
							if(i!=patientVO.getPatientComplaint().length-1){
								lStrValue = lStrValue+"~";
							}
						}
						}
						ehfmPatientHospDiagnosis.setPatientComplaint(lStrValue);
						
						ehfmPatientHospDiagnosis.setComplaintType(patientVO.getComplaints());
						
						if(patientVO.getOtherComplaint()!=null && !("").equalsIgnoreCase(patientVO.getOtherComplaint()))
						{
							ehfmPatientHospDiagnosis.setOtherComplaintName(patientVO.getOtherComplaint());
						}
						lStrValue="";
						if(patientVO.getPersonalHistory()!=null && patientVO.getPersonalHistory().length>0){
						for(int i=0;i<patientVO.getPersonalHistory().length;i++){
							lStrValue = lStrValue+patientVO.getPersonalHistory()[i];
							if(i!=patientVO.getPersonalHistory().length-1){
								lStrValue = lStrValue+"~";
							}
						}}
						ehfmPatientHospDiagnosis.setPersonalHistory(lStrValue);
						lStrValue="";	  
						if(patientVO.getFamilyHistory()!=null)
						{
							for(int i=0;i<patientVO.getFamilyHistory().length;i++){
								lStrValue = lStrValue+patientVO.getFamilyHistory()[i];
								if(i!=patientVO.getFamilyHistory().length-1){
									lStrValue = lStrValue+"~";
								}
							}
						}
						ehfmPatientHospDiagnosis.setFamilyHistory(lStrValue);
						ehfmPatientHospDiagnosis.setPastHistoryOthr(patientVO.getPastHistryOthr());
						ehfmPatientHospDiagnosis.setPastHistoryCancers(patientVO.getPastHistryCancers());
						ehfmPatientHospDiagnosis.setPastHistoryEndDis(patientVO.getPastHistryEndDis());
						ehfmPatientHospDiagnosis.setPastHistorySurg(patientVO.getPastHistrySurg());
						ehfmPatientHospDiagnosis.setFamilyHistoryOthr(patientVO.getFamilyHistoryOthr());
						
						String delQuery = "DELETE FROM EhfSymtematicExamDtls where id.caseId='"+lStrCaseId+"'";
						session.createQuery( delQuery ).executeUpdate();
						session.flush();
						session.clear();

						EhfSymtematicExamDtls examDtls=null;
						if(patientVO.getSymptoms()!=null){
						for(LabelBean symptom : patientVO.getSymptoms())
						{
							examDtls = new EhfSymtematicExamDtls(); 
							examDtls.setMainSymptomCode(symptom.getID());
							examDtls.setSubSymptomCode(symptom.getSUBCODE());
							examDtls.setId(new EhfSymtematicExamDtlsId(symptom.getVALUE(),lStrCaseId));
							
							if(symptom.getOtherSymptomName()!=null && !("").equalsIgnoreCase(symptom.getOtherSymptomName()))
							examDtls.setOtherSymptomName(symptom.getOtherSymptomName());
							
							examDtls.setCrtDt(lCurrentDate);
							examDtls.setCrtUsr(patientVO.getCrtUsr());
							genericDao.save(examDtls);
						}
						}
						
						/*ehfPatient.setPatientIpop(patientVO.getPatientType());	
						if(patientVO.getPatientType()==null)
							ehfPatient.setPatientIpop(config.getString("PatientIPOP.RegisterStatus"));	*/
						
						if(patientVO.getPatientType()!=null)
							ehfPatient.setPatientIpop(patientVO.getPatientType());
						else
							{
								if(config.getString("PatientIPOP.RegisterStatus")!=null)
									ehfPatient.setPatientIpop(config.getString("PatientIPOP.RegisterStatus"));
							}
						
									ehfDentalPatientDtls=genericDao.findById(EhfDentalPatientDtls.class, String.class, ehfPatient.getPatientId());
									if(ehfDentalPatientDtls==null){
										ehfDentalPatientDtls = new EhfDentalPatientDtls();
										ehfDentalPatientDtls.setCrtDt(lCurrentDate);
										ehfDentalPatientDtls.setCrtUsr(patientVO.getCrtUsr());
									}
									
										ehfDentalPatientDtls.setLstUpdDt(lCurrentDate);
										ehfDentalPatientDtls.setLstUpdUsr(patientVO.getCrtUsr());
									ehfDentalPatientDtls.setPatientId(ehfPatient.getPatientId());
									ehfDentalPatientDtls.setCaseId(ehfCase.getCaseNo());
									ehfDentalPatientDtls.setDiagnosis(patientVO.getDiagnosis());
									ehfDentalPatientDtls.setIpOp(patientVO.getPatientType());
									ehfDentalPatientDtls.setAdmissionDtls(patientVO.getAdmissionDetails());
									ehfDentalPatientDtls.setAdvancedInvestigations(patientVO.getAdvancedInvestigations());
									ehfDentalPatientDtls.setFlwupAdvise(patientVO.getFollowupAdv());
									ehfDentalPatientDtls.setMedicationGiven(patientVO.getMedicationGiven());
							
									genericDao.save(ehfDentalPatientDtls);
									if("IPM".equalsIgnoreCase(patientVO.getPatientType()))
									{
										ehfPatient.setPatientIpopNo(patientVO.getOpNo());
										EhfCaseTherapy ehfCaseTherapy = null;
										List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
							    		criteriaList.add(new GenericDAOQueryCriteria("caseId",lStrCaseId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
										  List<EhfCaseTherapy> ehfCaseThrpyLstLst = genericDao.findAllByCriteria(EhfCaseTherapy.class, criteriaList);
										  Long therapyId=0L;
										if(ehfCaseThrpyLstLst.size() == 0)
										{
											ehfCaseTherapy = new EhfCaseTherapy();
											ehfCaseTherapy.setCaseTherapyId(patientVO.getCaseThrpySeq());
										}
										else{
											if(ehfCaseThrpyLstLst.size() > 0)	
											{	
												 therapyId = Long.parseLong(ehfCaseThrpyLstLst.get(0).getCaseTherapyId().toString());
												 ehfCaseTherapy= genericDao.findById(EhfCaseTherapy.class, Long.class,therapyId);
											}
										}
										ehfCaseTherapy.setAsriCatCode(patientVO.getMedicalSplty());
										ehfCaseTherapy.setIcdCatCode(patientVO.getCategory());
										ehfCaseTherapy.setIcdProcCode(patientVO.getCategory());
										ehfCaseTherapy.setCaseId(lStrCaseId);
										ehfCaseTherapy.setDocName(patientVO.getDoctorName());
										ehfCaseTherapy.setCrtDt(lCurrentDate);
										ehfCaseTherapy.setCrtUsr(patientVO.getCrtUsr());
										ehfCaseTherapy.setActiveyn("Y");
										genericDao.save(ehfCaseTherapy);
									}
						if(patientVO.getPatientType()!=null)
						{
							ehfCase.setPharmaFlag("NA");
						if((config.getString("PatientIPOP.IP")).equalsIgnoreCase(patientVO.getPatientType()) || ("IPM").equalsIgnoreCase(patientVO.getPatientType()) || (config.getString("PatientIPOP.IPD")).equalsIgnoreCase(patientVO.getPatientType()) )
						{		            	            	  
							
							ehfPatient.setPatientIpopNo(patientVO.getIpNo());
							if(("IPM").equalsIgnoreCase(patientVO.getPatientType()))
								{
									ehfPatient.setPatientIpopNo(patientVO.getOpNo());
									ehfmPatientHospDiagnosis.setOpRemarks(patientVO.getOpRemarks());
									if(patientVO.getDiagnosedBy()!=null)
									{
										String selDocType=patientVO.getDiagnosedBy();
										ehfmPatientHospDiagnosis.setDoctType(selDocType);
									}
								}
							//ishank
							ehfCase.setIpNo(patientVO.getIpNo());
							//Start CR#4511-SriHari-10/10/24
							if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Submit") && "".equalsIgnoreCase(highEndProformaId)){
								ehfCase.setCaseStatus(config.getString("CASE.IPCaseRegistered"));							
								ehfEmpCaseDtls.setCaseStatus(config.getString("CASE.IPCaseRegistered"));
								ehfAudit.setActId(config.getString("CASE.IPCaseRegistered"));
								ehfAudit.setRemarks(patientVO.getIpCaseRemarks());																					
							}
							else if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Submit") && !"".equalsIgnoreCase(highEndProformaId)){
								ehfCase.setCaseStatus(config.getString("CASE.HighEndOncologyCaseSubmittedforCmteApproval"));							
								ehfEmpCaseDtls.setCaseStatus(config.getString("CASE.HighEndOncologyCaseSubmittedforCmteApproval"));
								ehfAudit.setActId(config.getString("CASE.HighEndOncologyCaseSubmittedforCmteApproval"));
								ehfAudit.setRemarks(patientVO.getIpCaseRemarks());
								EhfOncologyMedical ehfOncologyMedical = genericDao.findById(EhfOncologyMedical.class, String.class, highEndProformaId);
								if(ehfOncologyMedical != null){
									if(ehfOncologyMedical.getPreauthInitDt() == null){
										ehfOncologyMedical.setStatus("CD7307");
										ehfOncologyMedical.setLstUpdDt(new Timestamp(System.currentTimeMillis()));
								        ehfOncologyMedical.setLstUpdUsr(patientVO.getCrtUsr());
									}									
							        
							        EhfOncologyCmteApprvl ehfOncologyCmteApprvl = new EhfOncologyCmteApprvl();
							        ehfOncologyCmteApprvl.setPatientId(ehfPatient.getPatientId());
							        ehfOncologyCmteApprvl.setProformaId(highEndProformaId);
							        ehfOncologyCmteApprvl.setCardNo(ehfPatient.getCardNo());
							        ehfOncologyCmteApprvl.setStatus("CD7307");
							        ehfOncologyCmteApprvl.setNimsApprvl("N");
							        ehfOncologyCmteApprvl.setMnjioApprvl("N");
							        ehfOncologyCmteApprvl.setCrtUsr(patientVO.getCrtUsr());
							        ehfOncologyCmteApprvl.setCrtDt(new Timestamp(System.currentTimeMillis()));
							        
							        EhfOncologyTreatment ehfOncologyTreatment = genericDao.findById(EhfOncologyTreatment.class, String.class, patientVO.getPatientId());
							        if(ehfOncologyTreatment != null){
							        	ehfOncologyTreatment.setSubmit_Flag("Y");
							        	ehfOncologyTreatment.setLstUpdDt(new Timestamp(System.currentTimeMillis()));
							        	ehfOncologyTreatment.setLstUpdUsr(patientVO.getCrtUsr());
							        	genericDao.save(ehfOncologyTreatment);
							        }
							        String[] mobileNos = config.getString("CASE.HighEndOncologyCmteMembersMobileNo").split("~");
							        EhfSmsData ehfSmsData = new EhfSmsData();
							        for(String mobileNo : mobileNos){
							        	ehfSmsData = new EhfSmsData();
							        	ehfSmsData.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
										ehfSmsData.setCrtUsr(patientVO.getCrtUsr());
										ehfSmsData.setUserId(patientVO.getCrtUsr());
										ehfSmsData.setPhoneNo(mobileNo);
										//ehfSmsData.setEhfPasswod();
										ehfSmsData.setTemplateId("1407173493462337081");
										String dateFormat = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
										String lStrMsg = "Dear Member, \n A HEOD request has been raised on "+dateFormat+" and currently pending for your approval. Kindly review the request at your earliest action to approve or reject the request at the earliest.\n Rajiv Aarogyasri Health Care Trust";
										
										SMSServices SMSServicesobj = new SMSServices();
										SMSServicesobj.sendSingleSMS(lStrMsg,mobileNo,"1407173493462337081");
										ehfSmsData.setSmsText(lStrMsg);
								        
										genericDao.save(ehfSmsData);
							        }

							        genericDao.save(ehfOncologyMedical);
							        genericDao.save(ehfOncologyCmteApprvl);
								}			
								
							}
							//End CR#4511-SriHari
							String hospitalName=null;
							hospitalName=getHospName(ehfPatient.getRegHospId());

							//saving in ehfOnbedStatus for ip
							EhfonBedStatus ehfOnBedStatus=new EhfonBedStatus();
							ehfOnBedStatus.setCaseId(lStrCaseId);
							ehfOnBedStatus.setCaseNo(caseNo);
							ehfOnBedStatus.setPatientName(ehfPatient.getName());
							ehfOnBedStatus.setHospital(hospitalName);
							ehfOnBedStatus.setCardNo(ehfPatient.getCardNo());
							ehfOnBedStatus.setAge(ehfPatient.getAge());
							ehfOnBedStatus.setGender(ehfPatient.getGender());
							ehfOnBedStatus.setDistrict(getLocationName(ehfPatient.getDistrictCode()));
							if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Yes"))
								ehfOnBedStatus.setCaseStatus(config.getString("CASE.CaseDraftedStatus"));
							else if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Submit") && !"".equalsIgnoreCase(highEndProformaId)){
								ehfOnBedStatus.setCaseStatus(config.getString("CASE.HighEndOncologyCaseSubmittedforCmteApproval"));
							}
							else
								ehfOnBedStatus.setCaseStatus(config.getString("CASE.CaseRegisteredStatus"));
							
							 if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Submit") && "".equalsIgnoreCase(highEndProformaId)){
								 ehfOnBedStatus.setBedStatusId(config.getString("CASE.HighEndOncologyCaseSubmittedforCmteApproval"));
								 ehfOnBedStatus.setBedStatus(config.getString("CASE.HighEndOncologyCaseSubmittedforCmteApprovalStatus"));
							}
							else{
								ehfOnBedStatus.setBedStatusId(config.getString("CASE.OnBed"));
								ehfOnBedStatus.setBedStatus(config.getString("CASE.OnBedStatus"));
							}
							
							ehfOnBedStatus.setBedStatusUpdDate(lCurrentDate);
							ehfOnBedStatus.setRegDate(temp);
							ehfOnBedStatus.setHospDiagnosis(patientVO.getProvisionalDiagnosis());	                    
							ehfOnBedStatus.setSchemeId(ehfPatient.getSchemeId());
							ehfOnBedStatus.setPhaseId(ehfPatient.getPhaseId());
							ehfOnBedStatus.setDistrictCode(ehfPatient.getDistrictCode());
							ehfOnBedStatus.setCaseHospCode(ehfPatient.getRegHospId());
							ehfOnBedStatus.setSourceId(1L);
							ehfOnBedStatus.setCrtDt(lCurrentDate);
							ehfOnBedStatus.setCrtUsr(patientVO.getCrtUsr());
							genericDao.save(ehfOnBedStatus);

							//saving in ehfCaseTherapy for ip
							EhfCaseTherapy ehfCaseTherapy=null;
							if(patientVO.getCaseTherapy()!=null && patientVO.getCaseTherapy().size()>0)
							{
//								GLOGGER.error("Info Message for Ehf Case Therapies for case Id "+lStrCaseId+" exist");

								for(CaseTherapyVO caseTherapyVO : patientVO.getCaseTherapy())
								{
//									GLOGGER.error("Info Message for Ehf Case Therapy for case Id "+lStrCaseId+" is "+caseTherapyVO.getICDProcCode());
									ehfCaseTherapy = new EhfCaseTherapy();
									String flag = checkCaseTherapy(lStrCaseId,caseTherapyVO.getAsriCatCode(),caseTherapyVO.getICDCatCode(),caseTherapyVO.getICDProcCode());
									if(flag!=null && flag.equalsIgnoreCase("true")){
										ehfCaseTherapy.setCaseTherapyId(caseTherapyVO.getCaseTherapyId());
										ehfCaseTherapy.setCaseId(lStrCaseId);
										ehfCaseTherapy.setAsriCatCode(caseTherapyVO.getAsriCatCode());
										ehfCaseTherapy.setIcdCatCode(caseTherapyVO.getICDCatCode());
										ehfCaseTherapy.setIcdProcCode(caseTherapyVO.getICDProcCode());
										ehfCaseTherapy.setDocRegNum(caseTherapyVO.getDocRegNum());
										ehfCaseTherapy.setDocName(caseTherapyVO.getDocName());
										ehfCaseTherapy.setDocQual(caseTherapyVO.getDocQual());
										ehfCaseTherapy.setDocMobileNo(caseTherapyVO.getDocMobileNo());
										//ehfCaseTherapy.setAsriProcCode(caseTherapyVO.getAsriProcCode());
										ehfCaseTherapy.setProcUnits(caseTherapyVO.getProcUnits());
										ehfCaseTherapy.setSurgProcUnits(caseTherapyVO.getProcUnits());
										ehfCaseTherapy.setCrtDt(lCurrentDate);
										ehfCaseTherapy.setCrtUsr(patientVO.getCrtUsr());
										ehfCaseTherapy.setActiveyn("Y");
										/*@author Sravan
										 * Added to main audit for regular changes in package prices
										 */
										/**
										 * Added by ramalakshmi for hubspoke CR
										 */
										if((patientVO.getGroupId() !=null && (config.getString("preauth_spoke_role").equalsIgnoreCase(patientVO.getGroupId()) 
												|| "HUB".equalsIgnoreCase(patientVO.getGroupId())))
												&& (caseTherapyVO.getICDProcCode()!=null && ("N18.6.A").equalsIgnoreCase(caseTherapyVO.getICDProcCode())))
											{
											String process= getPatientTypeNew(caseTherapyVO.getAsriCatCode(),caseTherapyVO.getICDProcCode(), "CD202");
												EhfmHubSpokeMainTherapyId ehfmHubSpokeId = new EhfmHubSpokeMainTherapyId(patientVO.getHubHospId(),caseTherapyVO.getAsriCatCode(),caseTherapyVO.getICDProcCode(), "CD202",process);
												EhfmHubSpokeMainTherapy ehfmHubSpoc = genericDao.findById(EhfmHubSpokeMainTherapy.class, EhfmHubSpokeMainTherapyId.class, ehfmHubSpokeId);
												if(ehfmHubSpoc != null)
												{
													if(ehfmHubSpoc.getHospstayAmt() != null)
														ehfCaseTherapy.setHospstayAmt(ehfmHubSpoc.getHospstayAmt());
													if(ehfmHubSpoc.getCommonCatAmt() != null)
														ehfCaseTherapy.setCommonCatAmt(ehfmHubSpoc.getCommonCatAmt());
													if(ehfmHubSpoc.getIcdAmt() != null)
														ehfCaseTherapy.setIcdAmt(ehfmHubSpoc.getIcdAmt());
												}
											}
										else
										{
											String process= getPatientType(caseTherapyVO.getAsriCatCode(),caseTherapyVO.getICDProcCode(), "CD202");
											EhfmTherapyProcMstId ehfmTherapyProcMstId=new EhfmTherapyProcMstId(caseTherapyVO.getAsriCatCode(),caseTherapyVO.getICDProcCode(), "CD202",process);
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
										}
										genericDao.save(ehfCaseTherapy);
//										GLOGGER.error("Info Message for Ehf Case Therapy for case Id "+lStrCaseId+" is "+caseTherapyVO.getICDProcCode()+" inserted");
									}
								}
							}
							else
							{
//								GLOGGER.error("Info Message for Ehf Case Therapies for case Id "+lStrCaseId+" doesnt exist");
							}

							//saving mutiple special investigation for ip
							List<AttachmentVO> lSplAttachList=patientVO.getAttachmentsList();
							//Long caseTherInvestSeqId=patientVO.getCaseTherInvestSeqId();
							/*String caseProcCodes=patientVO.getCaseProcCodes();
							for(AttachmentVO attachmentVO:lSplAttachList)
							{
								StringBuffer query=new StringBuffer();
								String investId=attachmentVO.getTestId();
								query.append("select e.id.ICDProcCode as ID from EhfmTherapyInvest e where e.id.investigationId='"+investId+"' and e.id.ICDProcCode in ("+caseProcCodes+")");
								List<LabelBean> procInvestList=genericDao.executeHQLQueryList(LabelBean.class, query.toString());
								for(LabelBean labelBean:procInvestList)
								{
									EhfCaseTherapyInvest ehfCaseTherapyInvest=new EhfCaseTherapyInvest();
									ehfCaseTherapyInvest.setSno(caseTherInvestSeqId);
									ehfCaseTherapyInvest.setCaseId(lStrCaseId);
									ehfCaseTherapyInvest.setActiveYN("Y");
									ehfCaseTherapyInvest.setCrtDt(lCurrentDate);
									ehfCaseTherapyInvest.setCrtUsr(patientVO.getCrtUsr());
									ehfCaseTherapyInvest.setInvestigationId(attachmentVO.getTestId());
									ehfCaseTherapyInvest.setFilename(attachmentVO.getFileName());
									ehfCaseTherapyInvest.setInvestigationStage("CaseReg");
									ehfCaseTherapyInvest.setAttachTotalPath(attachmentVO.getFileReportPath());
									ehfCaseTherapyInvest.setIcdProcCode(labelBean.getID());
									genericDao.save(ehfCaseTherapyInvest);
									caseTherInvestSeqId++;
								}
							}*/
							for(AttachmentVO attachmentVO:lSplAttachList)
							{
								EhfCaseTherapyInvest ehfCaseTherapyInvest=new EhfCaseTherapyInvest();
								ehfCaseTherapyInvest.setSno(Long.parseLong(attachmentVO.getAttachId()));
								ehfCaseTherapyInvest.setCaseId(lStrCaseId);
								ehfCaseTherapyInvest.setActiveYN("Y");
								ehfCaseTherapyInvest.setCrtDt(lCurrentDate);
								ehfCaseTherapyInvest.setCrtUsr(patientVO.getCrtUsr());
								ehfCaseTherapyInvest.setInvestigationId(attachmentVO.getTestId());
								ehfCaseTherapyInvest.setFilename(attachmentVO.getFileName());
								ehfCaseTherapyInvest.setInvestigationStage("CaseReg");
								ehfCaseTherapyInvest.setAttachTotalPath(attachmentVO.getFileReportPath());
								ehfCaseTherapyInvest.setIcdProcCode(attachmentVO.getIcdProcCode());
								genericDao.save(ehfCaseTherapyInvest);
							}
							if(patientVO.getTeleDocremarks()!=null)
							{
							ehfmPatientHospDiagnosis.setTreatingDocRmks(patientVO.getTeleDocremarks());
							}
							ehfCase.setPharmaFlag("");
						}
						if((config.getString("PatientIPOP.DOP")).equalsIgnoreCase(patientVO.getPatientType())){
      	            	  
						 ehfPatient.setPatientIpopNo(patientVO.getIpNo());
					     ehfCase.setIpNo(patientVO.getIpNo());
						if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Submit")){
							ehfCase.setCaseStatus(config.getString("CASE.IPCaseRegistered"));							
							ehfEmpCaseDtls.setCaseStatus(config.getString("CASE.IPCaseRegistered"));
							ehfAudit.setActId(config.getString("CASE.IPCaseRegistered"));
							ehfAudit.setRemarks(patientVO.getIpCaseRemarks());
						}
						String hospitalName=null;
						hospitalName=getHospName(ehfPatient.getRegHospId());
		
						//saving in ehfOnbedStatus for ip
						EhfonBedStatus ehfOnBedStatus=new EhfonBedStatus();
						ehfOnBedStatus.setCaseId(lStrCaseId);
						ehfOnBedStatus.setCaseNo(caseNo);
						ehfOnBedStatus.setPatientName(ehfPatient.getName());
						ehfOnBedStatus.setHospital(hospitalName);
						ehfOnBedStatus.setCardNo(ehfPatient.getCardNo());
						ehfOnBedStatus.setAge(ehfPatient.getAge());
						ehfOnBedStatus.setGender(ehfPatient.getGender());
						ehfOnBedStatus.setDistrict(getLocationName(ehfPatient.getDistrictCode()));
						if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Yes"))
						ehfOnBedStatus.setCaseStatus(config.getString("CASE.CaseDraftedStatus"));
						else
						ehfOnBedStatus.setCaseStatus(config.getString("CASE.CaseRegisteredStatus"));
						ehfOnBedStatus.setBedStatusId(config.getString("CASE.OnBed"));
						ehfOnBedStatus.setBedStatus(config.getString("CASE.OnBedStatus"));
						ehfOnBedStatus.setBedStatusUpdDate(lCurrentDate);
						ehfOnBedStatus.setRegDate(temp);
						ehfOnBedStatus.setHospDiagnosis(patientVO.getProvisionalDiagnosis());	                    
						ehfOnBedStatus.setSchemeId(ehfPatient.getSchemeId());
						ehfOnBedStatus.setPhaseId(ehfPatient.getPhaseId());
						ehfOnBedStatus.setDistrictCode(ehfPatient.getDistrictCode());
						ehfOnBedStatus.setCaseHospCode(ehfPatient.getRegHospId());
						ehfOnBedStatus.setSourceId(1L);
						ehfOnBedStatus.setCrtDt(lCurrentDate);
						ehfOnBedStatus.setCrtUsr(patientVO.getCrtUsr());
						genericDao.save(ehfOnBedStatus);

				//saving in ehfCaseTherapy for DOp
							EhfCaseTherapy ehfCaseTherapy=null;
							if(patientVO.getCaseTherapy()!=null && patientVO.getCaseTherapy().size()>0)
							{
//								GLOGGER.error("Info Message for Ehf Case Therapies for case Id "+lStrCaseId+" exist");
			
								for(CaseTherapyVO caseTherapyVO : patientVO.getCaseTherapy())
								{
//									GLOGGER.error("Info Message for Ehf Case Therapy for case Id "+lStrCaseId+" is "+caseTherapyVO.getICDProcCode());
									ehfCaseTherapy = new EhfCaseTherapy();
									String flag = checkCaseTherapy(lStrCaseId,caseTherapyVO.getAsriCatCode(),caseTherapyVO.getICDCatCode(),caseTherapyVO.getICDProcCode());
									if(flag!=null && flag.equalsIgnoreCase("true")){
										ehfCaseTherapy.setCaseTherapyId(caseTherapyVO.getCaseTherapyId());
										ehfCaseTherapy.setCaseId(lStrCaseId);
										ehfCaseTherapy.setAsriCatCode(caseTherapyVO.getAsriCatCode());
										ehfCaseTherapy.setIcdCatCode(caseTherapyVO.getICDCatCode());
										ehfCaseTherapy.setIcdProcCode(caseTherapyVO.getICDProcCode());
										ehfCaseTherapy.setDocRegNum(caseTherapyVO.getDocRegNum());
										ehfCaseTherapy.setDocName(caseTherapyVO.getDocName());
										ehfCaseTherapy.setDocQual(caseTherapyVO.getDocQual());
										ehfCaseTherapy.setDocMobileNo(caseTherapyVO.getDocMobileNo());
										
										//ehfCaseTherapy.setAsriProcCode(caseTherapyVO.getAsriProcCode());
										ehfCaseTherapy.setProcUnits(caseTherapyVO.getProcUnits());
										ehfCaseTherapy.setSurgProcUnits(caseTherapyVO.getProcUnits());
										ehfCaseTherapy.setCrtDt(lCurrentDate);
										ehfCaseTherapy.setCrtUsr(patientVO.getCrtUsr());
										ehfCaseTherapy.setActiveyn("Y");
										/*@author Sravan
										 * Added to main audit for regular changes in package prices
										 */
										String process= getPatientType(caseTherapyVO.getAsriCatCode(),caseTherapyVO.getICDProcCode(), "CD202");
										EhfmTherapyProcMstId ehfmTherapyProcMstId=new EhfmTherapyProcMstId(caseTherapyVO.getAsriCatCode(),caseTherapyVO.getICDProcCode(), "CD202",process);
										EhfmTherapyProcMst ehfmTherapyProcMst=genericDao.findById(EhfmTherapyProcMst.class,EhfmTherapyProcMstId.class,ehfmTherapyProcMstId);
										if(ehfmTherapyProcMst != null)
										{
											if(ehfmTherapyProcMst.getHospstayAmt() != null)
											ehfCaseTherapy.setHospstayAmt(ehfmTherapyProcMst.getHospstayAmt());
											if(ehfmTherapyProcMst.getCommonCatAmt() != null )
											ehfCaseTherapy.setCommonCatAmt(ehfmTherapyProcMst.getCommonCatAmt());
											if(ehfmTherapyProcMst.getIcdAmt() != null )
											ehfCaseTherapy.setIcdAmt(ehfmTherapyProcMst.getIcdAmt());
										}
										genericDao.save(ehfCaseTherapy);
//										GLOGGER.error("Info Message for Ehf Case Therapy for case Id "+lStrCaseId+" is "+caseTherapyVO.getICDProcCode()+" inserted");
									}
								}
							}
							else
							{
//								GLOGGER.error("Info Message for Ehf Case Therapies for case Id "+lStrCaseId+" doesnt exist");
							}
			               List<AttachmentVO> lSplAttachList=patientVO.getAttachmentsList();
							
							for(AttachmentVO attachmentVO:lSplAttachList)
							{
								EhfCaseTherapyInvest ehfCaseTherapyInvest=new EhfCaseTherapyInvest();
								ehfCaseTherapyInvest.setSno(Long.parseLong(attachmentVO.getAttachId()));
								ehfCaseTherapyInvest.setCaseId(lStrCaseId);
								ehfCaseTherapyInvest.setActiveYN("Y");
								ehfCaseTherapyInvest.setCrtDt(lCurrentDate);
								ehfCaseTherapyInvest.setCrtUsr(patientVO.getCrtUsr());
								ehfCaseTherapyInvest.setInvestigationId(attachmentVO.getTestId());
								ehfCaseTherapyInvest.setFilename(attachmentVO.getFileName());
								ehfCaseTherapyInvest.setInvestigationStage("CaseReg");
								ehfCaseTherapyInvest.setAttachTotalPath(attachmentVO.getFileReportPath());
								ehfCaseTherapyInvest.setIcdProcCode(attachmentVO.getIcdProcCode());
								genericDao.save(ehfCaseTherapyInvest);
							}
							if(patientVO.getTeleDocremarks()!=null)
							{
							ehfmPatientHospDiagnosis.setTreatingDocRmks(patientVO.getTeleDocremarks());
							}
							ehfCase.setPharmaFlag("");
						}
						
						if((config.getString("PatientIPOP.OP")).equalsIgnoreCase(patientVO.getPatientType()) 
								|| (!"".equalsIgnoreCase(patientVO.getPatientType()) && "IPM".equalsIgnoreCase(patientVO.getPatientType())))
						{
							ehfPatient.setPatientIpopNo(patientVO.getOpNo());
							
							/* Added by venkatesh for saving op package details*/
							
							ehfCase.setPreauthTotPckgAmt(patientVO.getOpTotPkgAmt()); 
							ehfCase.setOpConsultAmt(patientVO.getConsultFee());
							ehfCase.setOpInvestAmt(patientVO.getTotInvestAmt());
							
							if(patientVO.getConsultList()!=null && patientVO.getConsultList().size()>0 )
							{
								for(int i=0;i<patientVO.getConsultList().size();i++)
								{
									LabelBean consultData=patientVO.getConsultList().get(i);
									EhfOpConsultData ehfOpConsultData=new EhfOpConsultData();
									String Sno=getSequenceVal("op_consult_sequence");
									Date date=new Date();
									if(Sno!=null && !("").equalsIgnoreCase(Sno))
									{
									ehfOpConsultData.setSNo(Sno);
									ehfOpConsultData.setPatientId(ehfPatient.getPatientId());
									ehfOpConsultData.setDiagnoisedBy(consultData.getDiagnoisedBy());
									ehfOpConsultData.setUnitName(consultData.getUnitName());
									ehfOpConsultData.setUnitHodName(consultData.getHodName());
									ehfOpConsultData.setConsultationTime(date);
									ehfOpConsultData.setCrtUser(patientVO.getCrtUsr());
									ehfOpConsultData.setCrtDt(date);
									genericDao.save(ehfOpConsultData);
									}									
								}
							}
							
							
							ehfmPatientHospDiagnosis.setOpRemarks(patientVO.getOpRemarks());
							delQuery="";
							delQuery = "DELETE FROM EhfPatientDrugs where caseId='"+lStrCaseId+"'";
							session.createQuery( delQuery ).executeUpdate();
							session.flush();
							session.clear();
							//Setting Prescription Details
							EhfPatientDrugs ehfDrugs=null;
							if(patientVO.getDrugs()!=null){
								ehfCase.setPharmaFlag("N");
							for(DrugsVO drugsVO : patientVO.getDrugs())
							{
								ehfDrugs = new EhfPatientDrugs(); 
								ehfDrugs.setDrugId(drugsVO.getDrugId());
								ehfDrugs.setCaseId(lStrCaseId);
								ehfDrugs.setPatientId(ehfPatient.getPatientId());
								ehfDrugs.setDrugTypeCode(drugsVO.getDrugTypeName());
								ehfDrugs.setDrugSubTypeCode(drugsVO.getDrugSubTypeName());
								ehfDrugs.setPharSubGrpCode(drugsVO.getpSubGrpCode());
								ehfDrugs.setCheSubGrpCode(drugsVO.getcSubGrpCode());
								ehfDrugs.setDrugCode(drugsVO.getDrugName());
								ehfDrugs.setRouteType(drugsVO.getRouteType());
								ehfDrugs.setRoute(drugsVO.getRoute());
								ehfDrugs.setStrengthType(drugsVO.getStrengthType());
								ehfDrugs.setStrength(drugsVO.getStrength());
								ehfDrugs.setDosage(drugsVO.getDosage());
								ehfDrugs.setMedicationPeriod(drugsVO.getMedicationPeriod());
								if(drugsVO.getOtherDrugName()!=null && !("").equalsIgnoreCase(drugsVO.getOtherDrugName()))
								ehfDrugs.setOtherDrugName(drugsVO.getOtherDrugName());
								ehfDrugs.setCrtDt(lCurrentDate);
								ehfDrugs.setCrtUsr(patientVO.getCrtUsr());
								genericDao.save(ehfDrugs);
							}
							}
							if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Submit") && !"IPM".equalsIgnoreCase(patientVO.getPatientType())){
								ehfCase.setCaseStatus(config.getString("CASE.OPCaseRegistered"));
								ehfEmpCaseDtls.setCaseStatus(config.getString("CASE.OPCaseRegistered"));
								ehfAudit.setActId(config.getString("CASE.OPCaseRegistered"));
								ehfAudit.setRemarks(patientVO.getOpRemarks());
							}
						}
						if((config.getString("PatientIPOP.ChronicOP")).equalsIgnoreCase(patientVO.getPatientType()))
						{
							//Setting Prescription Details
							/*EhfPatientDrugs ehfDrugs=null;
							for(DrugsVO drugsVO : patientVO.getDrugs())
							{
								ehfDrugs = new EhfPatientDrugs(); 
								
								String chronicOP=config.getString("PatientIPOP.ChronicOP");
								String caseStatus=config.getString("CASE.ChronicCaseRegistered");
								StringBuffer query =null;
								EhfPatientDrugs existPatientDrugs = null;
								try
								{
									query = new StringBuffer();
						    	    query.append("select epd.drugId ");
						    	    query.append(" from EhfPatient ep,EhfCase ec,EhfPatientDrugs epd");
						    	    query.append(" where ep.cardNo='"+ehfPatient.getCardNo()+"' and ep.patientIpop='"+chronicOP+"' and  ep.patientId=ec.casePatientNo");
						    	    query.append(" and ec.caseStatus='"+caseStatus+"' and ep.patientId=epd.patientId and ec.caseId=epd.caseId and epd.drugCode='"+drugsVO.getDrugName()+"'");
						    	    
						    	    Long drugId = (Long) session.createQuery(query.toString()).uniqueResult();
						    	    if(drugId!=null && drugId>0)
						    	    {
						    	    	existPatientDrugs = genericDao.findById(EhfPatientDrugs.class, Long.class, drugId);
						    	    }
						    	}
								catch(Exception e)
						    	{
						    		GLOGGER.error("Exception Occurred in saveCaseDetails() while registering patient drugs in PatientDaoImpl class."+e.getMessage());
						    	}
								
								ehfDrugs.setDrugId(drugsVO.getDrugId());
								ehfDrugs.setCaseId(lStrCaseId);
								ehfDrugs.setPatientId(ehfPatient.getPatientId());
								ehfDrugs.setDrugTypeCode(drugsVO.getDrugTypeName());
								ehfDrugs.setDrugSubTypeCode(drugsVO.getDrugSubTypeName());
								ehfDrugs.setDrugCode(drugsVO.getDrugName());
								ehfDrugs.setRoute(drugsVO.getRoute());
								ehfDrugs.setStrength(drugsVO.getStrength());
								ehfDrugs.setDosage(drugsVO.getDosage());
								ehfDrugs.setMedicationPeriod(drugsVO.getMedicationPeriod());
								if(existPatientDrugs!=null)
								{
									ehfDrugs.setIssueFromDt(existPatientDrugs.getIssueFromDt());
									ehfDrugs.setIssueToDt(existPatientDrugs.getIssueToDt());
								}
								ehfDrugs.setCrtDt(lCurrentDate);
								ehfDrugs.setCrtUsr(patientVO.getCrtUsr());
								genericDao.save(ehfDrugs);
							}

							//Setting Therapy Details
							ehfmPatientHospDiagnosis.setOpCategoryCode(patientVO.getOpCatCode());
							ehfmPatientHospDiagnosis.setOpPackageCode(patientVO.getOpPkgCode());
							ehfmPatientHospDiagnosis.setOpIcdCode(patientVO.getOpIcdCode());*/

							ehfCase.setCaseStatus(config.getString("CASE.ChronicCaseRegistered"));
							ehfEmpCaseDtls.setCaseStatus(config.getString("CASE.ChronicCaseRegistered"));
							ehfAudit.setActId(config.getString("CASE.ChronicCaseRegistered"));
							
							//updateOldChronicCase(ehfPatient.getCardNo(),patientVO.getCrtUsr());
						}
						//For DentalOP Cases
						//saving in ehfCasePatient for ip/op/chronic op
						EhfCasePatient ehfCasePatient=genericDao.findById(EhfCasePatient.class, String.class, ehfPatient.getPatientId());
						if(ehfCasePatient==null)
						{
//							GLOGGER.ERROR("Info Message while setting details for Ehf Case Patient for case Id: "+lStrCaseId +" and patient Id : "+ehfPatient.getPatientId());
							ehfCasePatient = new EhfCasePatient();
							ehfCasePatient.setPatientId(ehfPatient.getPatientId());
							ehfCasePatient.setCardNo(ehfPatient.getCardNo());
							ehfCasePatient.setName(ehfPatient.getName());
							ehfCasePatient.setEmployeeNo(ehfPatient.getEmployeeNo());
							ehfCasePatient.setCardType(ehfPatient.getCardType());
							ehfCasePatient.setOccupationCd(ehfPatient.getOccupationCd());	  	              
							ehfCasePatient.setHouseNo(ehfPatient.getHouseNo());	             
							ehfCasePatient.setStreet(ehfPatient.getStreet());
							ehfCasePatient.setAge(ehfPatient.getAge());
							ehfCasePatient.setAgeDays(ehfPatient.getAgeDays());
							ehfCasePatient.setAgeMonths(ehfPatient.getAgeMonths());
							ehfCasePatient.setGender(ehfPatient.getGender());
							ehfCasePatient.setRelation(ehfPatient.getRelation());
							ehfCasePatient.setFamilyHead(ehfPatient.getFamilyHead());
							ehfCasePatient.setChildYn(ehfPatient.getChildYn());
							ehfCasePatient.setContactNo(ehfPatient.getContactNo());
							ehfCasePatient.setDistrictCode(ehfPatient.getDistrictCode());
							ehfCasePatient.setMandalCode(ehfPatient.getMandalCode());
							ehfCasePatient.setVillageCode(ehfPatient.getVillageCode());
							ehfCasePatient.setPinCode(ehfPatient.getPinCode());
							ehfCasePatient.setState(ehfPatient.getState());
							ehfCasePatient.setSrcRegistration(ehfPatient.getSrcRegistration());
							ehfCasePatient.setPhaseId(ehfPatient.getPhaseId());

							ehfCasePatient.setPatientScheme(ehfPatient.getPatientScheme());
							//ehfCasePatient.setRenewal(ehfPatient.getRenewal());

							//ehfCasePatient.setRenewal(ehfPatient.getRenewal());

							ehfCasePatient.setSchemeId(ehfPatient.getSchemeId());
							ehfCasePatient.setSourceId(1L);
							ehfCasePatient.setRegHospId(ehfPatient.getRegHospId());
							ehfCasePatient.setIntimationId(ehfPatient.getIntimationId());
							ehfCasePatient.setPatientIpopNo(patientVO.getIpNo());
							ehfCasePatient.setRegHospDate(lCurrentDate);
							ehfCasePatient.setCrtDt(lCurrentDate);
							ehfCasePatient.setCrtUsr(patientVO.getCrtUsr());
						}
						else
						{
							ehfCasePatient.setPatientIpopNo(patientVO.getIpNo());
							ehfCasePatient.setRegHospDate(lCurrentDate);
							ehfCasePatient.setLstUpdDt(lCurrentDate);
							ehfCasePatient.setLstUpdUsr(patientVO.getCrtUsr());
						}
						genericDao.save(ehfCasePatient);
//						GLOGGER.error("Info Message after saving  details in Ehf Case Patient for case Id: "+lStrCaseId +" and patient Id : "+ehfPatient.getPatientId());

						ehfmPatientHospDiagnosis.setDiagnosisType(patientVO.getDiagnosisType());
						ehfmPatientHospDiagnosis.setMainCatCode(patientVO.getMainCatName());
						ehfmPatientHospDiagnosis.setCategoryCode(patientVO.getCatName());
						ehfmPatientHospDiagnosis.setSubCatCode(patientVO.getSubCatName());
						ehfmPatientHospDiagnosis.setDiseaseCode(patientVO.getDiseaseName());
						ehfmPatientHospDiagnosis.setDiseaseAnatCode(patientVO.getDisAnatomicalName());
						if(patientVO.getOtherDiagName()!=null)
						ehfmPatientHospDiagnosis.setOtherDiagnosisName(patientVO.getOtherDiagName());
//						GLOGGER.error("Info Message after setting Diagnosis details in Ehf Patient Hosp Diagnosis for case Id: "+lStrCaseId +" and patient Id : "+ehfPatient.getPatientId());
						
						if(patientVO.getDiagnosedBy()!=null){
						String selDocType=patientVO.getDiagnosedBy();
						ehfmPatientHospDiagnosis.setDoctType(selDocType);
						}
						List<LabelBean> docLst = null;
						if(patientVO.getDoctorName()!=null){
						if(!"0".equalsIgnoreCase(patientVO.getDoctorName()))
						{
							docLst=getSelDocDetails(patientVO.getDiagnosedBy(),ehfPatient.getRegHospId(),patientVO.getDoctorName(),ehfPatient.getSchemeId());                

						}
						if(!"0".equalsIgnoreCase(patientVO.getDoctorName()))
						{
							ehfmPatientHospDiagnosis.setDoctId(patientVO.getDoctorName());
							//if(ehfPatient.getRegHospId()!=null && config.getString("HOSP_NIMS").equalsIgnoreCase(ehfPatient.getRegHospId()))
							if(hospGovu!=null && "G".equalsIgnoreCase(hospGovu))
							{
							ehfmPatientHospDiagnosis.setDoctName(patientVO.getDoctorName());
							}
							if(docLst!=null && docLst.size()>0){
							ehfmPatientHospDiagnosis.setDoctName(docLst.get(0).getLVL());
							ehfmPatientHospDiagnosis.setDoctRegNo(docLst.get(0).getID());
							ehfmPatientHospDiagnosis.setDoctQuali(docLst.get(0).getDSGNNAME());
							ehfmPatientHospDiagnosis.setDoctMobNo(docLst.get(0).getVALUE());
							}
						}
						else if(patientVO.getUnitName()!=null && patientVO.getUnitHodName()!=null )
						{
							ehfmPatientHospDiagnosis.setUnitName(patientVO.getUnitName());
							ehfmPatientHospDiagnosis.setUnitHodName(patientVO.getUnitHodName());
						}
						else
						{
							ehfmPatientHospDiagnosis.setDoctId(patientVO.getDoctorName());
							ehfmPatientHospDiagnosis.setDoctName(patientVO.getOtherDocName());
							
							
							ehfmPatientHospDiagnosis.setDoctRegNo(patientVO.getDocRegNo());
							ehfmPatientHospDiagnosis.setDoctQuali(patientVO.getDocQual());
							ehfmPatientHospDiagnosis.setDoctMobNo(patientVO.getDocMobileNo());
						}
						}
						

						ehfmPatientHospDiagnosis.setCaseAdmType(patientVO.getAdmissionType());
						
						ehfmPatientHospDiagnosis.setLegalCaseCheck(patientVO.getLegalCase());
						ehfmPatientHospDiagnosis.setLegalCaseNo(patientVO.getLegalCaseNo());
						ehfmPatientHospDiagnosis.setPoliceStatName(patientVO.getPoliceStatName());
						
						ehfmPatientHospDiagnosis.setIpCaseRemarks(patientVO.getIpCaseRemarks());
						if(patientVO.getIpDate()!=null && !patientVO.getIpDate().equalsIgnoreCase(""))
							ehfmPatientHospDiagnosis.setCasePropSurgDate(sdf.parse(patientVO.getIpDate()));
						ehfPatient.setLstUpdUsr(patientVO.getCrtUsr());            
						ehfPatient.setLstUpdDt(lCurrentDate);						
					}
						if("IPM".equalsIgnoreCase(patientVO.getPatientType())){
							ehfCase.setPendingFlag("Y");
						}
						genericDao.save(ehfmPatientHospDiagnosis);
						genericDao.save(ehfCase); 
						genericDao.save(ehfEmpCaseDtls);
						genericDao.save(ehfPatient);
						if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Submit"))
						genericDao.save(ehfAudit);	
						
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error("Exception Occurred in saveCaseDetails() in PatientDaoImpl class."+e.getMessage());
				caseNo=null;
			}
			finally
			{
				session.close();
				factory.close();
			}
	//}
		//else
		//{
		//	caseNo="Already Registered";
		//}
		return caseNo;
	}
	/**
	 * ;
	 * @param String seqIdentifier
	 * @return String seqValue
	 * @function This method is used to get next sequence value for specified sequence
	 */
	@Override
	public String getSequenceVal(String pStrSeqIdentifier)throws Exception{
		StringBuffer lQuery=new StringBuffer();
		String lStrSeqValue=null;
		lQuery.append(" select ");
		lQuery.append( pStrSeqIdentifier);
		lQuery.append(".nextval||'' VALUE from dual ");
		List<LabelBean> seqList=genericDao.executeSQLQueryList(LabelBean.class,lQuery.toString());
		if(!seqList.isEmpty()){
			lStrSeqValue=seqList.get(0).getVALUE();
		}
		return lStrSeqValue;    
	}
	/**
	 * ;
	 * @param String hospId
	 * @return String hospName
	 * @function This Method is Used For getting Hospital Name
	 */
	@Override
	public String getHospName(String hospId) throws Exception {
		String hospitalName=null;
		try
		{
			EhfmHospitals asrimHospitals=genericDao.findById(EhfmHospitals.class,String.class,hospId);
			if(asrimHospitals!=null)
			hospitalName=asrimHospitals.getHospName();
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getHospName() in PatientDaoImpl class."+e.getMessage());
		}
		return hospitalName;
	}
	/**
	 * ;
	 * @param String locId
	 * @return String locName
	 * @function This Method is Used For getting Location Name for given Location Id
	 */
	@Override
	public String getLocationName(String locId) throws Exception {
		String locName=null;
		try
		{
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
			criteriaList.add(new GenericDAOQueryCriteria("id.locId",locId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfmLocations> ehfmLocations = genericDao.findAllByCriteria(EhfmLocations.class, criteriaList);
			for(EhfmLocations el:ehfmLocations)
			{
				locName=el.getLocName();
			}
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getLocationName() in PatientDaoImpl class."+e.getMessage());
		}
		return locName;
	}
	/**
	 * ;
	 * @param String doctorType
	 * @param String hospId
	 * @param String doctorId
	 * @return List<LabelBean> doctorsList
	 * @function This Method is Used For getting Selected Doctor Details
	 */
	@Override
	public List<LabelBean> getSelDocDetails(String doctorType,String hospId,String docId,String schemeId)throws Exception
	{
		String activeYn=config.getString("ActiveYn");
		String apprvStatus=config.getString("Doctor.ApprvStatus");
		SessionFactory factory=null;
		Session session=null;
		Query hQuery=null;
		List<LabelBean> doctorsList = new ArrayList<LabelBean>();
		try
		{
			String ramcoRole=config.getString("Group.Medco");
			if((config.getString("doctorType.Ramco")).equalsIgnoreCase(doctorType))
			{
				List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
				criteriaList.add(new GenericDAOQueryCriteria("hospId",hospId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add(new GenericDAOQueryCriteria("regNo",docId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add(new GenericDAOQueryCriteria("cordType",ramcoRole,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add(new GenericDAOQueryCriteria("scheme",schemeId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				
				List<EhfmMedcoMaster> ehfmMedcoMasterLst = genericDao.findAllByCriteria(EhfmMedcoMaster.class, criteriaList);
				for(EhfmMedcoMaster ehfmMedcoMaster:ehfmMedcoMasterLst)
				{
					LabelBean labelBean =new LabelBean();
					labelBean.setID(ehfmMedcoMaster.getRegNo());
					labelBean.setDSGNNAME(ehfmMedcoMaster.getQualification());
					labelBean.setVALUE(ehfmMedcoMaster.getMobileNo());
					labelBean.setLVL(ehfmMedcoMaster.getCordName());
					doctorsList.add(labelBean);
				}
			}
    		else if((config.getString("doctorType.DutyDoctor")).equalsIgnoreCase(doctorType))
    		{
    			try{
    			factory= hibernateTemplate.getSessionFactory();
    			session=factory.openSession();
    			StringBuffer query = new StringBuffer();
    			query.append("from EhfmDutyDctrs asd,EhfmDoctorQlfctns adq,EhfmQualMst aqm where asd.isActiveYn='"+activeYn+"' and asd.id.hospId='"+hospId+"'and asd.apprvStatus='"+apprvStatus+"'and asd.id.regNum = adq.id.regNum and adq.id.qualId = aqm.qualId and  adq.isActiveYn = '"+activeYn+"' and asd.id.regNum ='"+docId+"' and asd.id.scheme in ('"+schemeId+"','"+config.getString("COMMON")+"') order by asd.doctorName");
    			hQuery=session.createQuery(query.toString());
    			Iterator ite = hQuery.list().iterator();
    			while(ite.hasNext())
    			{
    			Object[] pair = (Object[]) ite.next();
    			EhfmDutyDctrs asrimDutyDctrs=(EhfmDutyDctrs)pair[0];
    			EhfmQualMst asrimQualMst= (EhfmQualMst)pair[2];
    			LabelBean labelBean =new LabelBean();
    			labelBean.setID(asrimDutyDctrs.getId().getRegNum());
    			labelBean.setDSGNNAME(asrimQualMst.getQual());
    			labelBean.setVALUE(asrimDutyDctrs.getMobile());
    			labelBean.setLVL(asrimDutyDctrs.getDoctorName());
    			doctorsList.add(labelBean);
    			}
    		}
    			catch(Exception e){
    				e.printStackTrace();
    			}
    			}
    		
			else if((config.getString("doctorType.InHouseDoctor")).equalsIgnoreCase(doctorType) || (config.getString("doctorType.Consultant")).equalsIgnoreCase(doctorType))
			{
				String consultant_Yn=null;
				if((config.getString("doctorType.InHouseDoctor")).equalsIgnoreCase(doctorType))
					consultant_Yn="N";
				else
					consultant_Yn="Y";  
				try{
				factory= hibernateTemplate.getSessionFactory();
				session=factory.openSession();
				StringBuffer query = new StringBuffer();
				query.append("from EhfmSplstDctrs asd,EhfmDoctorQlfctns adq,EhfmQualMst aqm where asd.isConsultant='"+consultant_Yn+"'and asd.isActiveYn='"+activeYn+"' and asd.id.hospId='"+hospId+"'and asd.apprvStatus='"+apprvStatus+"'and asd.id.regNum = adq.id.regNum and adq.id.qualId = aqm.qualId and  adq.isActiveYn = '"+activeYn+"' and asd.id.scheme in ('"+schemeId+"','"+config.getString("COMMON")+"') and asd.id.regNum ='"+docId+"' order by asd.splstName");
				hQuery=session.createQuery(query.toString());
				@SuppressWarnings("unchecked")
				Iterator<Object[]> ite = hQuery.list().iterator();
				while(ite.hasNext())
				{
					Object[] pair = (Object[]) ite.next();
					EhfmSplstDctrs ehfmSplstDctrs=(EhfmSplstDctrs)pair[0];
					EhfmQualMst ehfmQualMst= (EhfmQualMst)pair[2];
					LabelBean labelBean =new LabelBean();
					labelBean.setID(ehfmSplstDctrs.getId().getRegNum());
					labelBean.setDSGNNAME(ehfmQualMst.getQual());
					labelBean.setVALUE(ehfmSplstDctrs.getMobile());
					labelBean.setLVL(ehfmSplstDctrs.getSplstName());
					doctorsList.add(labelBean);
				}
				}
    			catch(Exception e){
    				e.printStackTrace();
    			}
				}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getSelDocDetails() in PatientDaoImpl class."+e.getMessage());
		}
		finally
		{
			if(factory!=null && session!=null)
			{
				session.close();
				factory.close();
			}
		}
		return doctorsList;

	}
	/**
	 * ;
	 * @param String hospId
	 * @return List<LabelBean> ICDCategorylist 
	 * @function This Method is Used For getting ICD Category List for specific hospital
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LabelBean> getTherapyCategory(String hospId) throws Exception{

		List<LabelBean> ICDcategoryList= new ArrayList<LabelBean>();
		SessionFactory factory=null;
		Session session=null;
		factory= hibernateTemplate.getSessionFactory();
		session=factory.openSession();
		try{
		StringBuffer query = new StringBuffer();
		query.append("select distinct a.id.icdCatCode as ID , a.icdCatName  as VALUE from EhfmTherapyCatMst a,EhfmHospCatMst b "); 
		query.append(" where a.id.asriCatCode = b.id.catId and b.id.hospId ='"+hospId+"' order by a.icdCatName");     
		ICDcategoryList=session.createQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(LabelBean.class)).list();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	finally {
		session.close();
		factory.close();
	}
		return ICDcategoryList;
	}
	/**
	 * ;
	 * @return List<LabelBean> relationList
	 * @function This Method is Used For getting Relations List from RelationMst table
	 */
	@Override
	public List<LabelBean> getRelations() throws Exception {
		List<LabelBean> relationList= new ArrayList<LabelBean>();
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
		criteriaList.add(new GenericDAOQueryCriteria("relationName",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
		List<EhfmRelationMst> relationMstList=genericDao.findAllByCriteria(EhfmRelationMst.class,criteriaList);
		for(EhfmRelationMst relationMst:relationMstList)
		{
			LabelBean labelBean=new LabelBean();
			labelBean.setID(relationMst.getRelationId().toString());
			labelBean.setVALUE(relationMst.getRelationName());
			relationList.add(labelBean);
		}

		return relationList;
	}
	/**
	 * ;
	 * @param String relationId
	 * @return String relationName
	 * @function This Method is Used For getting relationName for given relationId from RelationMst table
	 */
	@Override
	public String getRelationName(String relationId) throws Exception {
		String relationName=null;
		//Long relationIdVal=new Long(relationId);
		Integer relationIdVal=Integer.valueOf(relationId);
		EhfmRelationMst relationMst=genericDao.findById(EhfmRelationMst.class, Integer.class,relationIdVal);
		relationName=relationMst.getRelationName();
		return relationName;
	}
	/**
	 * ;
	 * @param String cardNo
	 * @return String photoUrl
	 * @function This Method is Used For getting photoUrl for given cardNo
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getPatientPhoto(String cardNo) throws Exception {
		String photoUrl=null;
		String enrollPrntId=null;
		
		StringBuffer query = new StringBuffer();
		List<EhfEnrollmentFamily> ehfEnrollmentFamily=new ArrayList<EhfEnrollmentFamily>();
		
		try
		{
			query.append("select ef.enrollPrntId as enrollPrntId,ef.enrollPhoto as enrollPhoto from EhfEnrollmentFamily ef where ef.ehfCardNo='"+cardNo+"'");
			ehfEnrollmentFamily=genericDao.executeHQLQueryList(EhfEnrollmentFamily.class, query.toString());  
		    if(cardNo.endsWith("01"))
			{
		    	enrollPrntId=ehfEnrollmentFamily.get(0).getEnrollPrntId();
				Map<String, Object> photoMap=new HashMap<String, Object>();
				photoMap.put("employeeParntId", enrollPrntId);
				photoMap.put("attachType", config.getString("FamilyHeadPhoto.AttachType"));
				photoMap.put("attachactiveYN","Y");
				EhfEmployeeDocAttach ehfEmployeeDocAttach=genericDao.findFirstByPropertyMatch(EhfEmployeeDocAttach.class,photoMap);
				photoUrl=ehfEmployeeDocAttach.getSavedName();
			}
		    else
		    {
		    	photoUrl=ehfEnrollmentFamily.get(0).getEnrollPhoto();
		    }
		    
		    
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception occurred in getPatientPhoto() while creating session factory instance in PatientDaoImpl class."+e.getMessage());
		}
		System.out.println(photoUrl);
		
		return photoUrl;
		
		//String enrollDdoReject=config.getString("ENROLLMENT_DDO_REJECTION");
		//String cardValidStatus=config.getString("Card.ValidStatus");
		//query.append("select ef.enrollPrntId,ef.enrollPhoto from EhfEnrollmentFamily ef where ef.ehfCardNo='"+cardNo+"' and ef.enrollStatus not in ("+cardValidStatus.replace("~", ",")+") and ef.displayFlag||ef.rejected <>'NN'");
		
	/*
	 * List<String> l=null;
	 try{
	 	query.append("select ef.enrollPrntId,ef.enrollPhoto from EhfEnrollmentFamily ef where ef.ehfCardNo='"+cardNo+"'");
		    if ( !hibernateTemplate.getSessionFactory ().getCurrentSession ().isOpen () )
		    {
		      throw new RuntimeException ( "No Session open for executing the query " + query ) ;
		    }
		    else
		    	l = hibernateTemplate.getSessionFactory ().getCurrentSession().createQuery ( query.toString() ).list();
		}	
		@SuppressWarnings("rawtypes")
		Iterator ite=l.iterator();
		if(ite.hasNext())
		{
			Object[] obj= (Object[])ite.next();
			if(obj[0]!=null)
			{
				enrollPrntId=obj[0].toString();
			}
			if(obj[1]!=null)
			{
				photoUrl=obj[1].toString();
			}
		}
		
		if(cardNo.endsWith("01"))
		{
			Map<String, Object> photoMap=new HashMap<String, Object>();
			photoMap.put("employeeParntId", enrollPrntId);
			photoMap.put("attachType", config.getString("FamilyHeadPhoto.AttachType"));
			photoMap.put("attachactiveYN","Y");
			EhfEmployeeDocAttach ehfEmployeeDocAttach=genericDao.findFirstByPropertyMatch(EhfEmployeeDocAttach.class,photoMap);
			photoUrl=ehfEmployeeDocAttach.getSavedName();
		}*/

	
	}
	/**
	 * ;
	 * @param String cardNo
	 * @return String photoUrl
	 * @function This Method is Used For getting photoUrl for given cardNo
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getJournalistPhoto(String cardNo) {
		String photoUrl=null;
		String enrollPrntId=null;
		
		StringBuffer query = new StringBuffer();
		List<EhfJrnlstFamily> ehfJrnlstFamily=null;
		
		try
		{
			query.append(" select ejf.journalEnrollParntId as journalEnrollParntId, ");
			query.append(" ejf.enrollPhoto as enrollPhoto from EhfJrnlstFamily ejf where ejf.cardValid='Y' and  ejf.journalCradNo='"+cardNo+"'");
			ehfJrnlstFamily=genericDao.executeHQLQueryList(EhfJrnlstFamily.class, query.toString());  
	    	if(ehfJrnlstFamily!=null)
	    		{
		    		if(ehfJrnlstFamily.size()>0)
			    		{
			    			if(ehfJrnlstFamily.get(0)!=null)
				    			{
				    				if(ehfJrnlstFamily.get(0).getJournalEnrollParntId()!=null)
				    					{
				    						enrollPrntId=ehfJrnlstFamily.get(0).getJournalEnrollParntId();
				    						photoUrl=ehfJrnlstFamily.get(0).getEnrollPhoto();
				    					}
				    			}
			    		}	
	    		}
		}
		catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error("Exception occurred in getPatientPhoto() while creating session factory instance in PatientDaoImpl class."+e.getMessage());
			}
		System.out.println(photoUrl);
		
		return photoUrl;
	}	
	/**
	 * ;
	 * @param PatientVO patientVO
	 * @return int records inserted
	 * @function This Method is Used For Saving telephonic information
	 */
	@Override    
	public int captureTelephonicPatientDtls(PatientVO patientVO) throws Exception{

		int i=0;
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		Date crtDt;
		Date dateOfBirth;
		Date cardIssueDt;
		try {
			EhfTelephonicRegn ehfTelephonicRegn = new EhfTelephonicRegn();
			crtDt=sdfw.parse(patientVO.getCrtDt());
			if(patientVO.getCardIssueDt()!=null && !patientVO.getCardIssueDt().equalsIgnoreCase(""))
			{
				cardIssueDt = sdf.parse(patientVO.getCardIssueDt());
				ehfTelephonicRegn.setCardIssueDt(cardIssueDt);
			}
			if(patientVO.getDateOfBirth()!=null && !patientVO.getDateOfBirth().equalsIgnoreCase("")){
				dateOfBirth = sdf.parse(patientVO.getDateOfBirth());
				ehfTelephonicRegn.setEnrollDob(dateOfBirth);
			}

			ehfTelephonicRegn.setTelephonicId(patientVO.getTelephonicId());
			ehfTelephonicRegn.setEmployeeNo(patientVO.getEmpCode());
			ehfTelephonicRegn.setCardNo(patientVO.getRationCard());
			ehfTelephonicRegn.setFamilyHead(patientVO.getFamilyHead());
			ehfTelephonicRegn.setCrtDt(crtDt);
			ehfTelephonicRegn.setCrtUsr(patientVO.getCrtUsr());

			ehfTelephonicRegn.setName(patientVO.getFirstName());
			ehfTelephonicRegn.setGender(patientVO.getGender());
			ehfTelephonicRegn.setCaste(patientVO.getCaste());
			ehfTelephonicRegn.setOccupationCd(patientVO.getOccupationCd());
			if(patientVO.getContactNo()!=null && !patientVO.getContactNo().equalsIgnoreCase(""))
				ehfTelephonicRegn.setContactNo(Long.parseLong(patientVO.getContactNo()));
			ehfTelephonicRegn.setRelation(patientVO.getRelation());
			if(patientVO.getAge()!=null && !patientVO.getAge().equalsIgnoreCase(""))
				ehfTelephonicRegn.setAge(Long.parseLong(patientVO.getAge()));
			if(patientVO.getAgeMonths()!=null && !patientVO.getAgeMonths().equalsIgnoreCase(""))
				ehfTelephonicRegn.setAgeMonths(Long.parseLong(patientVO.getAgeMonths()));
			if(patientVO.getAgeDays()!=null && !patientVO.getAgeDays().equalsIgnoreCase(""))
				ehfTelephonicRegn.setAgeDays(Long.parseLong(patientVO.getAgeDays()));
			ehfTelephonicRegn.setSlab(patientVO.getSlab());

			ehfTelephonicRegn.sethNo(patientVO.getAddr1());
			ehfTelephonicRegn.setStreet(patientVO.getAddr2());
			ehfTelephonicRegn.setState(patientVO.getState());
			ehfTelephonicRegn.setDistrictCode(patientVO.getDistrictCode());
			ehfTelephonicRegn.setMdl_mpl(patientVO.getMdl_mpl());
			ehfTelephonicRegn.setMandalCode(patientVO.getMandalCode());
			ehfTelephonicRegn.setVillageCode(patientVO.getVillageCode());
			ehfTelephonicRegn.setPinCode(patientVO.getPincode());

			ehfTelephonicRegn.setcHouseNo(patientVO.getPermAddr1());
			ehfTelephonicRegn.setcStreet(patientVO.getPermAddr2());
			ehfTelephonicRegn.setcState(patientVO.getC_state());
			ehfTelephonicRegn.setcDistrictCode(patientVO.getC_district_code());
			ehfTelephonicRegn.setC_mdl_mpl(patientVO.getC_mdl_mpl());
			ehfTelephonicRegn.setcMandalCode(patientVO.getC_mandal_code());
			ehfTelephonicRegn.setcVillageCode(patientVO.getC_village_code());
			ehfTelephonicRegn.setcPinCode(patientVO.getC_pin_code());

			ehfTelephonicRegn.setHospId(patientVO.getRegHospId());
			ehfTelephonicRegn.setCallerDesig(patientVO.getTeleCallerDesgn());
			ehfTelephonicRegn.setCallerMobileNo(patientVO.getTelePhoneNo());
			ehfTelephonicRegn.setCallerName(patientVO.getTeleCallerName());

			ehfTelephonicRegn.setDiaType(patientVO.getDiagnosisType());
			ehfTelephonicRegn.setDiaMainCatCode(patientVO.getMainCatName());
			ehfTelephonicRegn.setDiaCatCode(patientVO.getCatName());
			ehfTelephonicRegn.setDiaSubCatCode(patientVO.getSubCatName());
			ehfTelephonicRegn.setDiaDiseaseCode(patientVO.getDiseaseName());
			ehfTelephonicRegn.setDiaDisAnatomicalCode(patientVO.getDisAnatomicalName());

			ehfTelephonicRegn.setAsriCatCode(patientVO.getAsriCatCode());
			ehfTelephonicRegn.setICDCatCode(patientVO.getICDcatName());
			ehfTelephonicRegn.setICDSubCatCode(patientVO.getICDsubCatName());
			ehfTelephonicRegn.setICDProcCode(patientVO.getICDprocName());
			ehfTelephonicRegn.setTeleIntimRemarks(patientVO.getIndication());

			ehfTelephonicRegn.setDoctorName(patientVO.getTeleDocName());
			ehfTelephonicRegn.setDoctorDesig(patientVO.getTeleDocDesgn());
			ehfTelephonicRegn.setDocMobileNo(patientVO.getTeleDocPhoneNo());
			String[] remarks=patientVO.getTeleDocremarks().split("~");
			ehfTelephonicRegn.setRemarksProcedure(remarks[0]);
			ehfTelephonicRegn.setRemarksDiagnosis(remarks[1]);
			ehfTelephonicRegn.setSchemeId(patientVO.getSchemeId());

			ehfTelephonicRegn.setCardType(patientVO.getCardType());

			if(patientVO.getCardType()!=null)
				{
					if(patientVO.getCardType().equalsIgnoreCase("E")||
						patientVO.getCardType().equalsIgnoreCase("P")||
						patientVO.getCardType().equalsIgnoreCase("NB"))
							{
								ehfTelephonicRegn.setPatientScheme(config.getString("Scheme.EHS"));
							}
					else if(patientVO.getCardType().equalsIgnoreCase("J"))
							{
								ehfTelephonicRegn.setPatientScheme(config.getString("Scheme.JHS"));
							}
				}	

			genericDao.save(ehfTelephonicRegn);
			if(ehfTelephonicRegn!=null)
				i = 1;
		} 
		catch (ParseException e) {
			e.printStackTrace();
//			GLOGGER.error("Error occured in captureTelephonicPatientDtls() in patientDaoImpl class."+e.getMessage());
			return 0;
		}   
		return i;
	}
	/**
	 * ;
	 * @param PatientVO patientVO
	 * @return PatientVO patientVO
	 * @function This Method is Used For Retrieving telephonic registered details for specified telephonicId
	 */
	@Override
	public PatientVO getTelephonicIntimationDtls(PatientVO patientVo)throws Exception { 

		PatientVO patientVO = new PatientVO();
		DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat dateformat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		SessionFactory factory=null;
		Session session=null;
		try {
			EhfmLocations ehfmLocation=new EhfmLocations();
			factory= hibernateTemplate.getSessionFactory();
			session=factory.openSession();
			Query hQuery;
			StringBuffer query = new StringBuffer();
			query.append(" from EhfTelephonicRegn trr,EhfmHospitals EH ,EhfmLocations EL");
			query.append(" where trr.hospId=EH.hospId and EL.id.locId=trr.districtCode " );
			query.append(" and trr.telephonicId='"+patientVo.getTelephonicId()+"'");
			hQuery=session.createQuery(query.toString());
			@SuppressWarnings("unchecked")
			Iterator<Object[]> ite = hQuery.list().iterator();
			while(ite.hasNext())
			{
				Object[] pair = (Object[]) ite.next();
				EhfTelephonicRegn ehfmTelephonicRegn=(EhfTelephonicRegn)pair[0];
				EhfmHospitals asrimHosp=(EhfmHospitals)pair[1];
				EhfmLocations ehfmLoc=(EhfmLocations)pair[2];
				patientVO.setTelephonicId(ehfmTelephonicRegn.getTelephonicId());
				patientVO.setCardNo(ehfmTelephonicRegn.getCardNo());
				patientVO.setCardType(ehfmTelephonicRegn.getCardType());
				patientVO.setEmpCode(ehfmTelephonicRegn.getEmployeeNo());
				if(ehfmTelephonicRegn.getName()!=null && !ehfmTelephonicRegn.getName().equalsIgnoreCase(""))
					patientVO.setFirstName(ehfmTelephonicRegn.getName());
				else
					patientVO.setFirstName("NA");
				patientVO.setGender(ehfmTelephonicRegn.getGender());
				if(ehfmTelephonicRegn.getAge()!=null)
					patientVO.setAge(ehfmTelephonicRegn.getAge().toString());
				else
					patientVO.setAge("NA");
				if(ehfmTelephonicRegn.getAgeDays()!=null)
					patientVO.setAgeDays(ehfmTelephonicRegn.getAgeDays().toString());
				else
					patientVO.setAgeDays("NA");
				if(ehfmTelephonicRegn.getAgeMonths()!=null)
					patientVO.setAgeMonths(ehfmTelephonicRegn.getAgeMonths().toString());
				else
					patientVO.setAgeMonths("NA");
				patientVO.setRelation(ehfmTelephonicRegn.getRelation());
				if(ehfmTelephonicRegn.getRelation()!=null && !ehfmTelephonicRegn.getRelation().equalsIgnoreCase("-1")){
					EhfmRelationMst relationmst = genericDao.findById(EhfmRelationMst.class, Integer.class,Integer.parseInt(ehfmTelephonicRegn.getRelation()));
					if(relationmst!=null)
						patientVO.setRelOthers(relationmst.getRelationName());					
				}
				else
					patientVO.setRelOthers("NA");
				
				patientVO.setOccupationCd(ehfmTelephonicRegn.getOccupationCd());
				patientVO.setOccOthers(ehfmTelephonicRegn.getOccupationCd());
				/*if(ehfmTelephonicRegn.getOccupationCd()!=null && !ehfmTelephonicRegn.getOccupationCd().equalsIgnoreCase("-1"))
				{
					EhfmOccupationMst occupationmst = genericDao.findById(EhfmOccupationMst.class, String.class,ehfmTelephonicRegn.getOccupationCd());
					if(occupationmst!=null){
					patientVO.setOccupationCd(occupationmst.getOccupationId().toString());
					patientVO.setOccOthers(occupationmst.getOccName());
					}
				}
				else
					{patientVO.setOccupationCd("-1");
					patientVO.setOccOthers("NA");
					}*/

				patientVO.setSlab(ehfmTelephonicRegn.getSlab());
				//setting card address
				if(ehfmTelephonicRegn.gethNo()!=null)
					patientVO.setAddr1(ehfmTelephonicRegn.gethNo());
				else
					patientVO.setAddr1("NA");
				if(ehfmTelephonicRegn.getStreet()!=null)
					patientVO.setAddr2(ehfmTelephonicRegn.getStreet());
				else
					patientVO.setAddr2("NA");
				if(ehfmTelephonicRegn.getState()!=null && !ehfmTelephonicRegn.getState().equalsIgnoreCase("-1")){

					patientVO.setState(ehfmTelephonicRegn.getState());
				}
				if(ehfmTelephonicRegn.getDistrictCode()!=null && !ehfmTelephonicRegn.getDistrictCode().equalsIgnoreCase("-1")){

					patientVO.setDistrictCode(ehfmTelephonicRegn.getDistrictCode());
				}
				if(ehfmTelephonicRegn.getMdl_mpl()!=null)
					patientVO.setMdl_mpl(ehfmTelephonicRegn.getMdl_mpl());

				if(ehfmTelephonicRegn.getMandalCode()!=null && !ehfmTelephonicRegn.getMandalCode().equalsIgnoreCase("-1"))
				{
					patientVO.setMandalCode(ehfmTelephonicRegn.getMandalCode());

					if(ehfmTelephonicRegn.getVillageCode()!=null && !ehfmTelephonicRegn.getVillageCode().equalsIgnoreCase("-1"))
					{
						patientVO.setVillageCode(ehfmTelephonicRegn.getVillageCode());
					}
					else
						patientVO.setVillageCode("NA");
				}
				else
				{
					patientVO.setMandalCode("NA");
					patientVO.setVillageCode("NA");
				}
				if(ehfmTelephonicRegn.getPinCode()!=null)
					patientVO.setPincode(ehfmTelephonicRegn.getPinCode());
				else
					patientVO.setPincode("NA");

				//Setting Communication address

				if(ehfmTelephonicRegn.getcHouseNo()!=null)
					patientVO.setPermAddr1(ehfmTelephonicRegn.getcHouseNo());
				else
					patientVO.setPermAddr1("NA");
				if(ehfmTelephonicRegn.getcStreet()!=null)
					patientVO.setPermAddr2(ehfmTelephonicRegn.getcStreet());
				else
					patientVO.setPermAddr2("NA");
				if(ehfmTelephonicRegn.getcState()!=null && !ehfmTelephonicRegn.getcState().equalsIgnoreCase("-1"))
				{
					patientVO.setC_state(ehfmTelephonicRegn.getcState());
				}
				if(ehfmTelephonicRegn.getcDistrictCode()!=null && !ehfmTelephonicRegn.getcDistrictCode().equalsIgnoreCase("-1"))
				{
					patientVO.setC_district_code(ehfmTelephonicRegn.getcDistrictCode());
				}
				if(ehfmTelephonicRegn.getC_mdl_mpl()!=null)
					patientVO.setC_mdl_mpl(ehfmTelephonicRegn.getC_mdl_mpl());

				if(ehfmTelephonicRegn.getcMandalCode()!=null && !ehfmTelephonicRegn.getcMandalCode().equalsIgnoreCase("-1"))
				{
					patientVO.setC_mandal_code(ehfmTelephonicRegn.getcMandalCode());
					if(ehfmTelephonicRegn.getcVillageCode()!=null && !ehfmTelephonicRegn.getcVillageCode().equalsIgnoreCase("-1"))
					{
						patientVO.setC_village_code(ehfmTelephonicRegn.getcVillageCode());
					}
					else
						patientVO.setC_village_code("NA");
				}
				else
				{
					patientVO.setC_mandal_code("NA");
					patientVO.setC_village_code("NA");
				}
				if(ehfmTelephonicRegn.getcPinCode()!=null)
					patientVO.setC_pin_code(ehfmTelephonicRegn.getcPinCode());
				else
					patientVO.setC_pin_code("NA");
				if(ehfmTelephonicRegn.getContactNo()!=null)
					patientVO.setContactNo(ehfmTelephonicRegn.getContactNo().toString());	
				else
					patientVO.setContactNo("NA");
				patientVO.setRegHospId(ehfmTelephonicRegn.getHospId());
				patientVO.setFamilyHead(ehfmTelephonicRegn.getFamilyHead());
				if(ehfmTelephonicRegn.getEnrollDob()!=null)
					patientVO.setDateOfBirth(dateformat.format(ehfmTelephonicRegn.getEnrollDob()));
				if(ehfmTelephonicRegn.getCardIssueDt()!=null && !ehfmTelephonicRegn.getCardIssueDt().equals(""))
				{
					patientVO.setCardIssueDt(dateformat.format(ehfmTelephonicRegn.getCardIssueDt()));
				}
				patientVO.setCaste(ehfmTelephonicRegn.getCaste());
				patientVO.setTeleCallerDesgn(ehfmTelephonicRegn.getCallerDesig());
				patientVO.setTeleCallerName(ehfmTelephonicRegn.getCallerName());
				patientVO.setTeleDocDesgn(ehfmTelephonicRegn.getDoctorDesig());
				patientVO.setTeleDocName(ehfmTelephonicRegn.getDoctorName());
				patientVO.setTeleDocPhoneNo(ehfmTelephonicRegn.getDocMobileNo());
				patientVO.setTeleDocremarks(ehfmTelephonicRegn.getDocRemarks());
				patientVO.setTelePhoneNo(ehfmTelephonicRegn.getCallerMobileNo());
				patientVO.setCrtDt(dateformat1.format(ehfmTelephonicRegn.getCrtDt()));
				patientVO.setIndication(ehfmTelephonicRegn.getTeleIntimRemarks());
				patientVO.setRegHospDt(asrimHosp.getHospName());
				//Setting Therapy details
                if(ehfmTelephonicRegn.getAsriCatCode()!=null)
                {
                	EhfmSpecialities ehfmSpecialities=genericDao.findById(EhfmSpecialities.class,String.class,ehfmTelephonicRegn.getAsriCatCode());
                	if(ehfmSpecialities!=null)
                	{
                		patientVO.setAsriCatCode(ehfmSpecialities.getDisMainName());
                	}
                	else
                		patientVO.setAsriCatCode("NA");
                }
				if(ehfmTelephonicRegn.getICDCatCode()!=null && !ehfmTelephonicRegn.getICDCatCode().equalsIgnoreCase("-1"))
				{
					EhfmTherapyCatMstId ehfmTherapyCatMstId=new EhfmTherapyCatMstId(ehfmTelephonicRegn.getAsriCatCode(),ehfmTelephonicRegn.getICDCatCode());
					EhfmTherapyCatMst ehfmTherapyCatMst=genericDao.findById(EhfmTherapyCatMst.class,EhfmTherapyCatMstId.class,ehfmTherapyCatMstId);
					if(ehfmTherapyCatMst!=null)
						patientVO.setICDcatName(ehfmTherapyCatMst.getIcdCatName());
					else
						patientVO.setICDcatName("NA");
				}
				if(ehfmTelephonicRegn.getICDProcCode()!=null && !ehfmTelephonicRegn.getICDProcCode().equalsIgnoreCase("-1"))
				{
					patientVO.setICDprocName(ehfmTelephonicRegn.getICDProcCode());
					String process= getPatientType(ehfmTelephonicRegn.getAsriCatCode(),ehfmTelephonicRegn.getICDProcCode(), "CD202");
                    EhfmTherapyProcMstId ehfmTherapyProcMstId=new EhfmTherapyProcMstId(ehfmTelephonicRegn.getAsriCatCode(),ehfmTelephonicRegn.getICDProcCode(), ehfmTelephonicRegn.getSchemeId(),process);
					EhfmTherapyProcMst ehfmTherapyProcMst=genericDao.findById(EhfmTherapyProcMst.class,EhfmTherapyProcMstId.class,ehfmTherapyProcMstId);
					if(ehfmTherapyProcMst!=null)
					{
						patientVO.setICDprocName(ehfmTherapyProcMst.getProcName());
						patientVO.setTherapyCatId(ehfmTherapyProcMst.getId().getIcdProcCode());
					}
					else
						patientVO.setICDprocName("NA");
				}
				//Setting Diagnosis Details
				if(ehfmTelephonicRegn.getDiaType()!=null && !ehfmTelephonicRegn.getDiaType().equalsIgnoreCase("-1"))
				{
					patientVO.setDiagnosisType(ehfmTelephonicRegn.getDiaType());
					List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
					criteriaList.add(new GenericDAOQueryCriteria("id.diagnosisCode",ehfmTelephonicRegn.getDiaType(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					List<EhfmDiagnosisMst> ehfmDiagnosisMstList = genericDao.findAllByCriteria(EhfmDiagnosisMst.class, criteriaList);
					for(EhfmDiagnosisMst ehfmDiagnosisMst:ehfmDiagnosisMstList)
					{
						patientVO.setDiagnosisType(ehfmDiagnosisMst.getDiagnosisName());
					}
				}
				if(ehfmTelephonicRegn.getDiaMainCatCode()!=null && !ehfmTelephonicRegn.getDiaMainCatCode().equalsIgnoreCase("-1"))
				{
					patientVO.setMainCatName(ehfmTelephonicRegn.getDiaMainCatCode());
					EhfmDiagMainCatMst ehfmDiagMainCatMst=genericDao.findById(EhfmDiagMainCatMst.class,EhfmDiagMainCatMstId.class,new EhfmDiagMainCatMstId(ehfmTelephonicRegn.getDiaMainCatCode(),ehfmTelephonicRegn.getDiaType()));
					if(ehfmDiagMainCatMst!=null)
						patientVO.setMainCatName(ehfmDiagMainCatMst.getMainCatName());
					else
						patientVO.setMainCatName("NA");
				}
				if(ehfmTelephonicRegn.getDiaCatCode()!=null && !ehfmTelephonicRegn.getDiaCatCode().equalsIgnoreCase("-1"))
				{
					patientVO.setCatName(ehfmTelephonicRegn.getDiaCatCode());
					EhfmDiagCategoryMst ehfmDiagCategoryMst=genericDao.findById(EhfmDiagCategoryMst.class,EhfmDiagCategoryMstId.class,new EhfmDiagCategoryMstId(ehfmTelephonicRegn.getDiaCatCode(),ehfmTelephonicRegn.getDiaMainCatCode()));
					if(ehfmDiagCategoryMst!=null)
						patientVO.setCatName(ehfmDiagCategoryMst.getCatName());
					else
						patientVO.setCatName("NA");
				}
				if(ehfmTelephonicRegn.getDiaSubCatCode()!=null && !ehfmTelephonicRegn.getDiaSubCatCode().equalsIgnoreCase("-1"))
				{
					patientVO.setSubCatName(ehfmTelephonicRegn.getDiaSubCatCode());
					EhfmDiagSubCatMst ehfmDiagSubCatMst=genericDao.findById(EhfmDiagSubCatMst.class,EhfmDiagSubCatMstId.class,new EhfmDiagSubCatMstId(ehfmTelephonicRegn.getDiaSubCatCode(),ehfmTelephonicRegn.getDiaCatCode()));
					if(ehfmDiagSubCatMst!=null)
						patientVO.setSubCatName(ehfmDiagSubCatMst.getSubCatName());
					else
						patientVO.setSubCatName("NA");
				}
				if(ehfmTelephonicRegn.getDiaDiseaseCode()!=null && !ehfmTelephonicRegn.getDiaDiseaseCode().equalsIgnoreCase("-1"))
				{
					patientVO.setDiseaseName(ehfmTelephonicRegn.getDiaDiseaseCode());
					List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
					criteriaList.add(new GenericDAOQueryCriteria("id.subCatCode",ehfmTelephonicRegn.getDiaSubCatCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					criteriaList.add(new GenericDAOQueryCriteria("id.diseaseCode",ehfmTelephonicRegn.getDiaDiseaseCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					List<EhfmDiagDiseaseMst> ehfmDiagDiseaseMstList = genericDao.findAllByCriteria(EhfmDiagDiseaseMst.class, criteriaList);
					for(EhfmDiagDiseaseMst ehfmDiagDiseaseMst:ehfmDiagDiseaseMstList)
					{
						patientVO.setDiseaseName(ehfmDiagDiseaseMst.getDiseaseName());
					}
				}
				if(ehfmTelephonicRegn.getDiaDisAnatomicalCode()!=null && !ehfmTelephonicRegn.getDiaDisAnatomicalCode().equalsIgnoreCase("-1"))
				{
					patientVO.setDisAnatomicalName(ehfmTelephonicRegn.getDiaDisAnatomicalCode());
					List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
					criteriaList.add(new GenericDAOQueryCriteria("id.disAnatomicalCode",ehfmTelephonicRegn.getDiaDisAnatomicalCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					criteriaList.add(new GenericDAOQueryCriteria("id.diseaseCode",ehfmTelephonicRegn.getDiaDiseaseCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					List<EhfmDiagDisAnatomicalMst> ehfmDiagDisAnatomicalMstList = genericDao.findAllByCriteria(EhfmDiagDisAnatomicalMst.class, criteriaList);
					for(EhfmDiagDisAnatomicalMst ehfmDiagDisAnatomicalMst:ehfmDiagDisAnatomicalMstList)
					{
						patientVO.setDisAnatomicalName(ehfmDiagDisAnatomicalMst.getDisAnatomicalName());
					}
				}
				patientVO.setTeleDocremarks(ehfmTelephonicRegn.getRemarksProcedure()+"~"+ehfmTelephonicRegn.getRemarksDiagnosis());
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
//			GLOGGER.error("Error occured in captureTelephonicPatientDtls() in patientDaoImpl class."+e.getMessage());
			return patientVO;
		} 
		finally
		{
			if(factory!=null && session!=null)
    		{
				session.close();
				factory.close();
    		}
		}		
		return patientVO;

	}
	/**
	 * ;
	 * @param String categoryId
	 * @return List<LabelBean> ICDSubCategorylist 
	 * @function This Method is Used For getting ICD Sub Category List for specific category
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LabelBean> getTherapySubCategory(String categoryId)throws Exception {

		List<LabelBean> subCatList= new ArrayList<LabelBean>();
		SessionFactory factory=null;
		Session session=null;
		factory= hibernateTemplate.getSessionFactory();
		session=factory.openSession();
		try{
		StringBuffer query = new StringBuffer();
		query.append("select distinct a.ICDSubCatCode as ID , a.ICDSubCatName as VALUE from EhfmTherapySubCatMst a where a.ICDCatCode='"+categoryId+"' order by a.ICDSubCatName asc");     
		subCatList=session.createQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(LabelBean.class)).list();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
		return subCatList;
	}
	/**
	 * ;
	 * @param String occupationId
	 * @return String occupationName
	 * @function This Method is Used For getting occupationName for given occupationId from EhfmOccupationMst table
	 */
	@Override
	public String getOccupationName(String occupationId)throws Exception {
		String occupationName=null;
		EhfmOccupationMst occupationMst=genericDao.findById(EhfmOccupationMst.class, String.class,occupationId);
		occupationName=occupationMst.getOccName();
		return occupationName;
	}
	/**
	 * ;
	 * @param String categoryId
	 * @return List<LabelBean> procList 
	 * @function This Method is Used For getting ICD Procedure List for specific category
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LabelBean> getTherapyProcedure(String subCategoryId)throws Exception {
		List<LabelBean> procList= new ArrayList<LabelBean>();
		SessionFactory factory=null;
		Session session=null;
		factory= hibernateTemplate.getSessionFactory();
		session=factory.openSession();
		try{
		StringBuffer query = new StringBuffer();
		query.append("select distinct a.id.icdProcCode as ID , a.procName as VALUE from EhfmTherapyProcMst a where a.icdCatCode='"+subCategoryId+"' order by a.procName asc");     
		procList=session.createQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(LabelBean.class)).list();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
		return procList;
	}
	/**
	 * ;
	 * @return List<LabelBean> diagnosisTypesList
	 * @function This Method is Used For getting Diagnosis Types List(EHFM_DIAGNOSIS_MST--EhfmDiagnosisMst)
	 */
	@Override
	public List<LabelBean> getDiagnosisTypes()throws Exception {
		List<LabelBean> diagnosisList=null;
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.id.diagnosisCode as  ID , edm.diagnosisName as VALUE from EhfmDiagnosisMst edm where edm.activeYn='Y' and edm.medOrSurg='M' order by edm.diagnosisName asc");
			diagnosisList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDiagnosisTypes() in PatientDaoImpl class."+e.getMessage());
		}
		return diagnosisList;
	}
	/**
	 * ;
	 * @param String diagnosisId
	 * @return List<LabelBean> diagnosisMainCategoryList
	 * @function This Method is Used For getting Diagnosis Main Category List(EHFM_DIA_MAINCAT_MST--EhfmDiagMainCatMst)
	 */
	@Override
	public List<LabelBean> getDiagnMainCategory(String diagnosisId)throws Exception {
		List<LabelBean> mainCatList= new ArrayList<LabelBean>();
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct a.id.mainCatCode as ID , a.mainCatName as VALUE from EhfmDiagMainCatMst a where a.id.diagnosisCode='"+diagnosisId+"' order by a.mainCatName asc");     
			mainCatList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getDiagnMainCategory() in PatientDaoImpl class."+e.getMessage());
		}
		return mainCatList;
	}
	/**
	 * ;
	 * @param String mainCategoryId
	 * @return List<LabelBean> diagnosisCategoryList
	 * @function This Method is Used For getting Diagnosis Category List(EHFM_DIA_CATEGORY_MST--EhfmDiagCategoryMst)
	 */
	@Override
	public List<LabelBean> getDiagnCategory(String mainCategoryId) throws Exception{
		List<LabelBean> categoryList= new ArrayList<LabelBean>();
		StringBuffer query = null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct a.id.catCode as ID , a.catName as VALUE from EhfmDiagCategoryMst a where a.id.mainCatCode='"+mainCategoryId+"' order by a.catName asc");     
			categoryList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getDiagnCategory() in PatientDaoImpl class."+e.getMessage());
		}
		return categoryList;
	}
	/**
	 * ;
	 * @param String categoryId
	 * @return List<LabelBean> diagnosisSubCategoryList
	 * @function This Method is Used For getting Diagnosis Sub Category List(EHFM_DIA_SUBCAT_MST--EhfmDiagSubCatMst)
	 */
	@Override
	public List<LabelBean> getDiagnSubCategory(String categoryId)throws Exception {
		List<LabelBean> subCategoryList= new ArrayList<LabelBean>();
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct a.id.subCatCode as ID, a.subCatName as VALUE from EhfmDiagSubCatMst a where a.id.catCode='"+categoryId+"' order by  a.subCatName asc");     
			subCategoryList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getDiagnSubCategory() in PatientDaoImpl class."+e.getMessage());
		}
		return subCategoryList;
	}
	/**
	 * ;
	 * @param String code
	 * @param String paramType
	 * @return List<LabelBean> diagnosisDiseaseList
	 * @function This Method is Used For getting Diagnosis Disease List(EHFM_DIA_DISEASE_MST--EhfmDiagDiseaseMst)
	 */
	@Override
	public List<LabelBean> getDiagnDisease(String code, String param)throws Exception {
		List<LabelBean> diseaseList= new ArrayList<LabelBean>();
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct a.id.diseaseCode as ID, a.diseaseName as VALUE from EhfmDiagDiseaseMst a ");
			if("categoryId".equalsIgnoreCase(param))
			{
				query.append("where a.id.catCode='"+code+"' order by  a.diseaseName asc");
			}
			else if("subCategoryId".equalsIgnoreCase(param))
			{
				query.append("where a.id.subCatCode='"+code+"' order by a.diseaseName asc");
			}
			diseaseList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getDiagnDisease() in PatientDaoImpl class."+e.getMessage());
		}
		return diseaseList;
	}
	/**
	 * ;
	 * @param String code
	 * @param String paramType
	 * @return List<LabelBean> diagnosisDisAnatomicalList
	 * @function This Method is Used For getting Diagnosis Disease Anatomical List(EHFM_DIA_DISANATOMICAL_MST--EhfmDiagDisAnatomicalMst)
	 */
	@Override
	public List<LabelBean> getDiagnDisAnatomical(String code, String param)throws Exception {
		List<LabelBean> disAnatomicalList= new ArrayList<LabelBean>();
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct a.id.disAnatomicalCode as ID, a.disAnatomicalName as VALUE from EhfmDiagDisAnatomicalMst a ");
			if("categoryId".equalsIgnoreCase(param))
			{
				query.append("where a.id.catCode='"+code+"' order by a.disAnatomicalName asc");
			}
			else if("subCategoryId".equalsIgnoreCase(param))
			{
				query.append("where a.id.subCatCode='"+code+"' order by a.disAnatomicalName asc");
			}
			else if("diseaseId".equalsIgnoreCase(param))
			{
				query.append("where a.id.diseaseCode='"+code+"' order by a.disAnatomicalName asc");
			}
			disAnatomicalList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getDiagnDisAnatomical() in PatientDaoImpl class."+e.getMessage());
		}
		return disAnatomicalList;
	}
	/**
	 * ;
	 * @return List<LabelBean> mainComplaintList
	 * @function This Method is Used For getting Main Complaint List(EHFM_COMPLAINT_MST--EhfmComplaintMst)
	 */
	@Override
	public List<LabelBean> getMainComplaintLst() throws Exception{
		List<LabelBean> mainCompList = new ArrayList<LabelBean>();
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select DISTINCT a.id.mainComplCode as ID , a.mainComplName as VALUE from EhfmComplaintMst a where a.activeYn='Y'  order by a.mainComplName asc");     
			mainCompList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getMainComplaintLst() in PatientDaoImpl class."+e.getMessage());
		}
		return mainCompList;
	}
	/**
	 * ;
	 * @return List<LabelBean> getDentalMainComplaintLst
	 * @function This Method is Used For getting Main Complaint List(EHFM_COMPLAINT_MST--EhfmComplaintMst)
	 */
	@Override
	public List<LabelBean> getDentalMainComplaintLst(){
		List<LabelBean> mainCompList = new ArrayList<LabelBean>();
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select DISTINCT a.id.mainComplCode as ID , a.mainComplName as VALUE from EhfmComplaintMst a where a.activeYn='Y' and a.id.mainComplCode like 'S18%' order by a.mainComplName asc");     
			mainCompList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getMainComplaintLst() in PatientDaoImpl class."+e.getMessage());
		}
		return mainCompList;
	}
	/**
	 * ;
	 * @param String mainSymptomCode
	 * @return List<String> symptomList
	 * @function This Method is Used For retrieving Symptom List(EHFM_SYSTEMATIC_EXAM_FND--EhfmSystematicExamFnd)
	 */
	@Override
	public List<String> getSymptomList(String mainSymptomCode, String subSymptomCode) throws Exception{
		List<String> symptomList = new ArrayList<String>();	
		Iterator<EhfmSystematicExamFnd> ehfmSymptomItr=null;
		EhfmSystematicExamFnd ehfmSymptomMst=null;
		try
		{
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
			criteriaList.add(new GenericDAOQueryCriteria("activeYn",config.getString("ActiveYn"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("id.mainSymptomCode",mainSymptomCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("id.subSymptomCode",subSymptomCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("symptomName",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
			List<EhfmSystematicExamFnd> asrimSymptomsList = genericDao.findAllByCriteria(EhfmSystematicExamFnd.class, criteriaList);
			ehfmSymptomItr=asrimSymptomsList.iterator();
			while(ehfmSymptomItr.hasNext())
			{
				ehfmSymptomMst=(EhfmSystematicExamFnd)ehfmSymptomItr.next();    	
				symptomList.add(ehfmSymptomMst.getId().getSymptomCode()+"~"+ehfmSymptomMst.getSymptomName()+"@");
			}
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getSymptomList() in PatientDaoImpl class."+e.getMessage());
		}
		return symptomList;
	}
	@Override
	public List<String> getSubSymptomList(String mainSymptomCode) {
		List<LabelBean> systematicExamList = new ArrayList<LabelBean>();
		List<String> symptomList = new ArrayList<String>();	
		try{
			StringBuffer query = new StringBuffer();
			query.append(" select distinct ESD.subSymptomName as VALUE, ESD.id.subSymptomCode as ID ");  
			query.append(" from EhfmSystematicExamFnd ESD where ESD.activeYn='Y' and ESD.id.mainSymptomCode='"+mainSymptomCode+"'");
			query.append(" and ESD.subSymptomName is not null order by ESD.subSymptomName asc");
			systematicExamList = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
			for(LabelBean ehfmSymptomMst : systematicExamList){
				symptomList.add(ehfmSymptomMst.getID()+"~"+ehfmSymptomMst.getVALUE()+"@");
			}
		}catch(Exception ex){
			GLOGGER.error("Exception Occurred in getSubSymptomList() in PatientDaoImpl class."+ex.getMessage());
		}
		return symptomList;
}
	/**
	 * ;
	 * @param String icdCatCode
	 * @param String asriCatCode
	 * @param String hospId
	 * @return List<String> procedureList
	 * @function This Method is Used For retrieving Icd procedure List(EHFM_MAIN_THERAPY--EhfmTherapyProcMst)
	 */
	@Override
	public List<String> getIcdProcList(String icdCatCode,String asriCatCode,String hospId,String state,String hosType) {
		List<String> procedureList = new ArrayList<String>();	
		Iterator<EhfmTherapyProcMst> therapyProcItr=null;
		EhfmTherapyProcMst ehfmTherapyProcMst=null;
    	EhfmHospitals ehfmHospitals=genericDao.findById(EhfmHospitals.class,String.class,hospId);
    	String hospType = ehfmHospitals.getHospType()+"";
    	List<String> procTypeLst= new ArrayList<String>();
    	List<LabelBean> icdCatList1 = new ArrayList<LabelBean>();
    	GLOGGER.info("hosptype VAlue"+hosType);
    	if(hosType==null)
    		hosType="HOSPITAL";

    	if(hosType.equals("DC"))
    	{
	    	procTypeLst.add("DOP");
    	}
    	else
    	{
    		procTypeLst.add("IP");
    		procTypeLst.add("DOP");
    	}
		try
		{
			/*List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
			criteriaList.add(new GenericDAOQueryCriteria("activeYN",config.getString("ActiveYn"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("icdCatCode",icdCatCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("id.asriCode",asriCatCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("id.process",procTypeLst,GenericDAOQueryCriteria.CriteriaOperator.IN));
			criteriaList.add(new GenericDAOQueryCriteria("id.state",state,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("procName",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
			List<EhfmTherapyProcMst> therapyProcList = genericDao.findAllByCriteria(EhfmTherapyProcMst.class, criteriaList);
			therapyProcItr=therapyProcList.iterator();
			while(therapyProcItr.hasNext())
			{
				ehfmTherapyProcMst=(EhfmTherapyProcMst)therapyProcItr.next();    	
    			if(ehfmTherapyProcMst.getGovOrCorp()!=null && !ehfmTherapyProcMst.getGovOrCorp().equalsIgnoreCase(""))
    			{
    				if(ehfmTherapyProcMst.getGovOrCorp().equalsIgnoreCase("G")){
    					if(hospType.equalsIgnoreCase(ehfmTherapyProcMst.getGovOrCorp()))
    						procedureList.add(ehfmTherapyProcMst.getId().getIcdProcCode()+"~"+ehfmTherapyProcMst.getProcName()+"@");
    				}else
    					procedureList.add(ehfmTherapyProcMst.getId().getIcdProcCode()+"~"+ehfmTherapyProcMst.getProcName()+"@");
    			}
    			else
    				procedureList.add(ehfmTherapyProcMst.getId().getIcdProcCode()+"~"+ehfmTherapyProcMst.getProcName()+"@");
    		}*/
			StringBuffer query = new StringBuffer();
			query.append("select distinct cm.id.icdProcCode as ID ,cm.procName  as VALUE from EhfmTherapyProcMst cm  where cm.id.asriCode='"+asriCatCode+"' and cm.activeYN='Y' ");
			query.append(" and cm.icdCatCode='"+icdCatCode+"'  and cm.id.state='"+state+"' ");
			icdCatList1=genericDao.executeHQLQueryList (LabelBean.class,query.toString());
			
			for(int i=0; i<icdCatList1.size();i++)
			{
				procedureList.add(icdCatList1.get(i).getID()+"~"+icdCatList1.get(i).getVALUE()+"("+icdCatList1.get(i).getID()+")@");
				
    		}
    	}
		catch(Exception e)
		{
    		e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getIcdProcList() in PatientDaoImpl class."+e.getMessage());
		}
		return procedureList;
	}
	/**
	 * ;
	 * @param String drugTypeCode
	 * @return List<String> drugSubTypeList
	 * @function This Method is Used For getting Drug Sub Type List From EhfmDrugsMst(EHFM_DRUGS_MST)
	 */
	@Override
	public List<LabelBean> getDrugSubList(String drugTypeCode)throws Exception {
		List<LabelBean> drugSubList = null;	
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.id.drugSubTypeCode as  ID , edm.drugSubTypeName as VALUE from EhfmDrugsMst edm where edm.id.drugTypeCode='"+drugTypeCode+"' and edm.activeYN='Y' order by edm.drugSubTypeName asc");
			drugSubList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDrugSubList() in PatientDaoImpl class."+e.getMessage());
		}

		return drugSubList;
	}
	/**
	 * ;
	 * @param String drugCode
	 * @return List<String> drugDetailsList
	 * @function This Method is Used For getting Drug Details From EhfmDrugsMst(EHFM_DRUGS_MST)
	 */
	@Override
	public String getDrugDetails(String drugCode)throws Exception {
		String drugDetails="";
		Iterator<EhfmDrugsMst> drugItr=null;
		EhfmDrugsMst ehfmDrugsMst=null;
		try
		{
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
			criteriaList.add(new GenericDAOQueryCriteria("activeYN",config.getString("ActiveYn"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("id.drugCode",drugCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfmDrugsMst> drugsList = genericDao.findAllByCriteria(EhfmDrugsMst.class, criteriaList);
			drugItr=drugsList.iterator();
			if(drugItr.hasNext())
			{
				ehfmDrugsMst=(EhfmDrugsMst)drugItr.next();   
				drugDetails=ehfmDrugsMst.getRouteCode()+"("+ehfmDrugsMst.getRouteName()+")~"+ehfmDrugsMst.getStrength();
    			criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
    			criteriaList.add(new GenericDAOQueryCriteria("id.inpCode",drugCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
    			List<EhfSsrMedinpData> lstEhfSsrMedinpData = genericDao.findAllByCriteria(EhfSsrMedinpData.class, criteriaList);
    			if(lstEhfSsrMedinpData != null && lstEhfSsrMedinpData.size() > 0)
    			drugDetails= drugDetails+"~"+lstEhfSsrMedinpData.get(0).getUnitPrice();
			}
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getDrugDetails() in PatientDaoImpl class."+e.getMessage());
		}
		return drugDetails;
	}
	/**
	 * ;
	 * @param String icdProcCode
	 * @return String procCode
	 * @function This Method is Used For getting Procedure Codes From EhfmTherapyProcMst(EHFM_THERAPY_PROC_MST )
	 */
	@Override
	public String getTherapyProcCodes(String icdProcCode)throws Exception {
		String procCode=null;
		EhfmTherapyProcMst ehfmTherapyProcMst=genericDao.findById(EhfmTherapyProcMst.class, String.class, icdProcCode);
		//procCode=ehfmTherapyProcMst.getAsriProcCode();
		procCode=ehfmTherapyProcMst.getId().getIcdProcCode();
		return procCode;
	}
	/**
	 * ;
	 * @return List<LabelBean> opCategoryList
	 * @function This Method is Used For getting Therapy OP Category Codes From EhfmOpTherapyPkgMst(EHFM_OP_THERAPY_PKG_MST )
	 */
	@Override
	public List<LabelBean> getTherapyOPCategory() throws Exception {
		List<LabelBean> opCategoryList= new ArrayList<LabelBean>();
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct a.asriMainCode as ID , am.disMainName as VALUE from EhfmOpTherapyPkgMst a,EhfmSpecialities am where am.disMainId=a.asriMainCode and a.activeYN='"+config.getString("ActiveYn")+"' order by am.disMainName asc");     
			opCategoryList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getTherapyOPCategory() in PatientDaoImpl class."+e.getMessage());
		}
		return opCategoryList;
	}
	/**
	 * ;
	 * @param opMainCode
	 * @return List<LabelBean> opPkgList
	 * @function This Method is Used For getting Therapy OP Package Codes From EhfmOpTherapyPkgMst(EHFM_OP_THERAPY_PKG_MST )
	 */
	@Override
	public List<LabelBean> getOpPkgList(String opCatCode) throws Exception {
		List<LabelBean> opPkgList= new ArrayList<LabelBean>();
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct a.id.icdOpPkgCode as ID , a.icdOpPkgName as VALUE from EhfmOpTherapyPkgMst a where a.activeYN='"+config.getString("ActiveYn")+"' and a.asriMainCode='"+opCatCode+"' order by a.icdOpPkgName asc");     
			opPkgList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getOpPkgList() in PatientDaoImpl class."+e.getMessage());
		}
		return opPkgList;
	}
	/**
	 * ;
	 * @param opPkgCode
	 * @return List<LabelBean> opIcdList
	 * @function This Method is Used For getting Therapy OP ICD Codes From EhfmOpTherapyPkgMst(EHFM_OP_THERAPY_PKG_MST )
	 */
	@Override
	public List<LabelBean> getOpIcdList(String opCode) throws Exception {
		List<LabelBean> opIcdList= new ArrayList<LabelBean>();
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct a.id.icdCatCode as ID , a.icdCatName as VALUE from EhfmOpTherapyPkgMst a where a.activeYN='"+config.getString("ActiveYn")+"' and a.id.icdOpPkgCode='"+opCode+"' order by a.icdCatName asc");     
			opIcdList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getOpIcdList() in PatientDaoImpl class."+e.getMessage());
		}
		return opIcdList;
	}
	/**
	 * ;
	 * @param cardNo
	 * @param opCatCode
	 * @return int count
	 * @function This Method is Used For getting count of cases with given cardNo and therapyOPCategory whose medication period is not completed
	 */
	@Override
	public int validateTherapyOPCat(String cardNo, String opCatCode)
			throws Exception {
		String caseType=config.getString("PatientIPOP.ChronicOP");
		int count=0;
		StringBuffer query = new StringBuffer();
		SessionFactory factory= hibernateTemplate.getSessionFactory();
		Session session=factory.openSession();
		try
		{
			query.append("select count(*) from EhfPatient e,EhfPatientHospDiagnosis ehd,EhfPatientDrugs epd");
			query.append(" where e.cardNo='"+cardNo+"' and e.patientIpop='"+caseType+"' and e.patientId=ehd.patientId");
			query.append(" and ehd.opCategoryCode='"+opCatCode+"' and ehd.caseId=epd.caseId and (epd.crtDt+epd.medicationPeriod)>sysdate");
			count = ((Long) session.createQuery(query.toString()).uniqueResult()).intValue();
			System.out.println(count);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in validateTherapyOPCat() in PatientDaoImpl class."+e.getMessage());
		}
		finally
		{
			session.close();
			factory.close();
		}
		return count;
	}
	
	
	/**
     * ;
     * @param cardNo
     * @return List<DrugsVO> 
     * @function This Method is Used For getting list of drugs already provided for given card no
     */
	@Override
	public List<DrugsVO> getChronicDetails(String cardNo) throws Exception {
		String chronicOP=config.getString("PatientIPOP.ChronicOP");
		String caseStatus=config.getString("CASE.ChronicCaseRegistered");
		List<DrugsVO> drugsList = new ArrayList<DrugsVO>();
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
    	    query.append("select edm.drugTypeName||'('||epd.drugTypeCode||')' as drugTypeName,edm.drugSubTypeName||'('||epd.drugSubTypeCode||')' as drugSubTypeName,edm.drugName||'('||epd.drugCode||')' as drugName ,");
    	    query.append(" epd.route as route, epd.strength as strength, epd.dosage as dosage,epd.medicationPeriod as medicationPeriod");
    	    query.append(" from EhfPatient ep,EhfCase ec,EhfPatientDrugs epd,EhfmDrugsMst edm ");
    	    query.append(" where ep.cardNo='"+cardNo+"' and ep.patientIpop='"+chronicOP+"' and  ep.patientId=ec.casePatientNo");
    	    query.append(" and ec.caseStatus='"+caseStatus+"' and ep.patientId=epd.patientId and ec.caseId=epd.caseId");
    	    query.append(" and edm.id.drugTypeCode = epd.drugTypeCode ");
    	    query.append(" and edm.id.drugSubTypeCode = epd.drugSubTypeCode ");
            query.append(" and edm.id.drugCode = epd.drugCode ");
    	    drugsList=genericDao.executeHQLQueryList ( DrugsVO.class,query.toString());
    	}
		catch(Exception e)
    	{
    		e.printStackTrace();
//    		GLOGGER.error("Exception Occurred in getChronicDetails() in PatientDaoImpl class."+e.getMessage());
    	}
		return drugsList;
	}
	public void updateOldChronicCase(String cardNo,String userId) throws Exception
	{
		String chronicOP=config.getString("PatientIPOP.ChronicOP");
		String caseStatus=config.getString("CASE.ChronicCaseRegistered");
		List<PatientVO> caseList = new ArrayList<PatientVO>();
		String existCaseId=null;
		StringBuffer query =null;
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		Date crtDt = sdfw.parse(sdfw.format(new Date()));
		try
		{
			query = new StringBuffer();
    	    query.append("select ec.caseId as caseId");
    	    query.append(" from EhfPatient ep,EhfCase ec");
    	    query.append(" where ep.cardNo='"+cardNo+"' and ep.patientIpop='"+chronicOP+"' and  ep.patientId=ec.casePatientNo");
    	    query.append(" and ec.caseStatus='"+caseStatus+"'");
    	    caseList=genericDao.executeHQLQueryList ( PatientVO.class,query.toString());
    	    if(caseList.size()>0)
    	    {
    	    	existCaseId=caseList.get(0).getCaseId();
    	    }
    	    
    	    if(existCaseId!=null)
    	    {
    	    EhfCase ehfCase=genericDao.findById(EhfCase.class, String.class, existCaseId);
    	    ehfCase.setCaseStatus(config.getString("CASE.ChronicCaseClosed"));
    	    ehfCase.setLstUpdDt(crtDt);
    	    ehfCase.setLstUpdUsr(userId);
    	    ehfCase.setChronicEndDt(crtDt);
    	    genericDao.save(ehfCase);
    	    }
    	}
		catch(Exception e)
    	{
    		e.printStackTrace();
//    		GLOGGER.error("Exception Occurred in getChronicDetails() in PatientDaoImpl class."+e.getMessage());
    	}
	
	}
	/**
     * ;
     * @param String seqIdentifier
     * @return EhfmSeq ehfmSeq
     * @function This Method is Used For getting EhfmSeq data based on seqIdentifier
     */
	private EhfmSeq getSeqNextVal(String seqIdentifier) {
		EhfmSeq ehfmSeq = 
	            genericDao.findById(EhfmSeq.class, String.class, seqIdentifier);
	        return ehfmSeq;
	}
	private void updateSequenceVal(EhfmSeq ehfmSeq) {
		try {
            genericDao.save(ehfmSeq);
        } catch (Exception e) {
        	GLOGGER.error("Exception occurred in updateSequence() in CommonServiceImpl class."+e.getMessage());
        }
		
	}
	@Override
	public PreauthVO getPatientFullDtls(String lStrCaseId, String patientId) {
		PreauthVO preauthVO = new PreauthVO();
		String dentalFlg="N";
		EhfPatient ehfPatient=genericDao.findById(EhfPatient.class,String.class,patientId);
		EhfPatientHospDiagnosis ehfPatientHospDiagnosis = genericDao.findById(EhfPatientHospDiagnosis.class, String.class,patientId);
		if(ehfPatientHospDiagnosis != null)
		{
			preauthVO.setPresentIllness(ehfPatientHospDiagnosis.getHistoryIllness());	
			preauthVO.setDocName(ehfPatientHospDiagnosis.getDoctName());
			preauthVO.setDoctorDtls(ehfPatientHospDiagnosis.getDoctId());
			preauthVO.setUnitName(ehfPatientHospDiagnosis.getUnitName());
			preauthVO.setUnitHodName(ehfPatientHospDiagnosis.getUnitHodName());
			preauthVO.setDocQual(ehfPatientHospDiagnosis.getDoctQuali());
			preauthVO.setDocMobNo(ehfPatientHospDiagnosis.getDoctMobNo());
			preauthVO.setDocReg(ehfPatientHospDiagnosis.getDoctRegNo());;
			preauthVO.setDocType(ehfPatientHospDiagnosis.getDoctType());
			preauthVO.setAdmType(ehfPatientHospDiagnosis.getCaseAdmType());
			preauthVO.setLegalCaseCheck(ehfPatientHospDiagnosis.getLegalCaseCheck());
			preauthVO.setLegalCaseNo(ehfPatientHospDiagnosis.getLegalCaseNo());
			preauthVO.setPoliceStatName(ehfPatientHospDiagnosis.getPoliceStatName());
			preauthVO.setDate(ehfPatientHospDiagnosis.getCasePropSurgDate());
			preauthVO.setOtherDiagName(ehfPatientHospDiagnosis.getOtherDiagnosisName());
			if(ehfPatient.getPatientIpop()!=null && ehfPatient.getPatientIpop().equals("IP"))
			{
				preauthVO.setRemarks(ehfPatientHospDiagnosis.getIpCaseRemarks());
			}
			else if(ehfPatient.getPatientIpop()!=null && ehfPatient.getPatientIpop().equals("OP"))
			{
				preauthVO.setRemarks(ehfPatientHospDiagnosis.getOpRemarks());
			}
			else if(ehfPatient.getPatientIpop()!=null && ehfPatient.getPatientIpop().equals("DOP"))
			{
				preauthVO.setRemarks(ehfPatientHospDiagnosis.getIpCaseRemarks());
			}
			else if(ehfPatient.getPatientIpop()!=null && ehfPatient.getPatientIpop().equals("IPM"))
			{
				preauthVO.setRemarks(ehfPatientHospDiagnosis.getOpRemarks());
				preauthVO.setPatientIPNo(ehfPatient.getPatientIpopNo());
				/*if(!"".equalsIgnoreCase(ehfPatientHospDiagnosis.getDiseaseAnatCode()) && ehfPatientHospDiagnosis.getDiseaseAnatCode() != null)
					preauthVO.setDrugName(ehfPatientHospDiagnosis.getDiseaseAnatCode());*/
			}
			preauthVO.setComplaintType(ehfPatientHospDiagnosis.getComplaintType());
			preauthVO.setPatComplaint(ehfPatientHospDiagnosis.getPatientComplaint());
			preauthVO.setPastIllness(ehfPatientHospDiagnosis.getPastHistory());
			preauthVO.setExamFindg(ehfPatientHospDiagnosis.getExamntnFindings());
			
			if(ehfPatientHospDiagnosis.getPastHistoryOthr()!=null && !ehfPatientHospDiagnosis.getPastHistoryOthr().equalsIgnoreCase(""))
				preauthVO.setPastIllenesOthr(ehfPatientHospDiagnosis.getPastHistoryOthr());
			if(ehfPatientHospDiagnosis.getPastHistoryCancers()!=null && !ehfPatientHospDiagnosis.getPastHistoryCancers().equalsIgnoreCase(""))
				preauthVO.setPastIllenesCancers(ehfPatientHospDiagnosis.getPastHistoryCancers());
			if(ehfPatientHospDiagnosis.getPastHistoryEndDis()!=null && !ehfPatientHospDiagnosis.getPastHistoryEndDis().equalsIgnoreCase(""))
				preauthVO.setPastIllenesEndDis(ehfPatientHospDiagnosis.getPastHistoryEndDis());
			if(ehfPatientHospDiagnosis.getPastHistorySurg()!=null && !ehfPatientHospDiagnosis.getPastHistorySurg().equalsIgnoreCase(""))
				preauthVO.setPastIllenesSurg(ehfPatientHospDiagnosis.getPastHistorySurg());
			
			preauthVO.setPersonalHis( ehfPatientHospDiagnosis.getPersonalHistory());
			
			
			if(ehfPatientHospDiagnosis.getOtherComplaintName()!=null && !("").equalsIgnoreCase(ehfPatientHospDiagnosis.getOtherComplaintName()))
			{
				preauthVO.setOtherComplaintName(ehfPatientHospDiagnosis.getOtherComplaintName());
			}
			EhfPatientPersonalHistory ehfPatientPersonalHistory= genericDao.findById(EhfPatientPersonalHistory.class, String.class, patientId);
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
						preauthVO.setTestKnown(ehfPatientPersonalHistory.getKnownAllergies());
				}
				if(ehfPatientPersonalHistory.getAddictions() != null)
				{
					lstPerHis.add(ehfPatientPersonalHistory.getAddictions());	
					if(ehfPatientPersonalHistory.getAddictions().contains("PR6.1"))
					preauthVO.setTest(ehfPatientPersonalHistory.getAddictions());
				}
				if(ehfPatientPersonalHistory.getMaritalStatus() != null)
				{
					lstPerHis.add(ehfPatientPersonalHistory.getMaritalStatus());	
				}
				if(ehfPatientPersonalHistory.getDrugHst() != null)
				{
					preauthVO.setDrugHst(ehfPatientPersonalHistory.getDrugHst());
				}
				if(ehfPatientPersonalHistory.getDrugHstVal() != null)
				{
					preauthVO.setDrugHstVal(ehfPatientPersonalHistory.getDrugHstVal());
				}
			}
			preauthVO.setLstPerHis(lstPerHis);
			
			//pavan
			//retreving the dental case sheet medical history
			
			EhfDentalOtherExaminations ehfDentalOtherExaminations1 = genericDao.findById(EhfDentalOtherExaminations.class, String.class, patientId);
			if(ehfDentalOtherExaminations1!=null)
			{
					if(ehfDentalOtherExaminations1.getMedicalHistory()!=null)
					{
						String medicaldtls=ehfDentalOtherExaminations1.getMedicalHistory();
						preauthVO.setMedicalDtlsidStr(medicaldtls);
						
					}
					if(ehfDentalOtherExaminations1.getMedicalHistoryOthr()!=null)
					preauthVO.setShowMedicalTextval(ehfDentalOtherExaminations1.getMedicalHistoryOthr());
					if(ehfDentalOtherExaminations1.getClinicalProbingDepth()!=null)
					preauthVO.setProbingdepth(ehfDentalOtherExaminations1.getClinicalProbingDepth());
					if(ehfDentalOtherExaminations1.getPreviousDentalTreatment()!=null)
						preauthVO.setPreviousDentalTreatment(ehfDentalOtherExaminations1.getPreviousDentalTreatment());
					if(ehfDentalOtherExaminations1.getOcclusion()!=null)
						preauthVO.setOcclusion(ehfDentalOtherExaminations1.getOcclusion());
					if(ehfDentalOtherExaminations1.getOcclusionDivii()!=null)
						preauthVO.setOcclusionType(ehfDentalOtherExaminations1.getOcclusionDivii());
					if(ehfDentalOtherExaminations1.getOcclusionOther()!=null)
						preauthVO.setOcclusionOther(ehfDentalOtherExaminations1.getOcclusionOther());
			}
			
			
			EhfDentalOralExaminations ehfDentalOralExaminations = genericDao.findById(EhfDentalOralExaminations.class, String.class, patientId);
			if(ehfDentalOralExaminations!=null)
			{
					if(ehfDentalOralExaminations.getExtraOralCheck()!=null)
					{
						String medicaldtls=ehfDentalOralExaminations.getExtraOralCheck();
						preauthVO.setExtraoralStr(medicaldtls);
						String[] requires =medicaldtls.split("~");
						if(requires.length>0)
							{
								String[] arr=new String[requires.length];
								for(int i=0 ; i<requires.length; i++)
									{
										arr[i]=requires[i];
									}
								preauthVO.setExtraoral(arr);
							}
					}
					
					if(ehfDentalOralExaminations.getIntraOralCheck()!=null)
					{
						String medicaldtls=ehfDentalOralExaminations.getIntraOralCheck();
						preauthVO.setIntraoralStr(medicaldtls);
						String[] requires =medicaldtls.split("~");
						if(requires.length>0)
							{
								String[] arr=new String[requires.length];
								for(int i=0 ; i<requires.length; i++)
									{
										arr[i]=requires[i];
									}
								preauthVO.setIntraoral(arr);
							}	
					}
					
					if(ehfDentalOralExaminations.getLymphadenopathySub()!=null)
					{
						String medicaldtls=ehfDentalOralExaminations.getLymphadenopathySub();
						String[] requires =medicaldtls.split("~");
						if(requires.length>0)
							{
								String[] arr=new String[requires.length];
								for(int i=0 ; i<requires.length; i++)
									{
										arr[i]=requires[i];
									}
								String rt=medicaldtls.substring(0,2);
								if("CH".equalsIgnoreCase(rt))
								{
									char rt1=(medicaldtls.charAt(3));
									boolean retval =Character.isDigit(rt1);
									if(!retval)
										preauthVO.setSubdentalrltxt(medicaldtls);
									   else
									   {
									    preauthVO.setSubdentalrl0Str(medicaldtls);
									    preauthVO.setSubdentalrl0(arr);
									   }
								}
								else
								{
									char rt1=(medicaldtls.charAt(2));
									boolean retval =Character.isDigit(rt1);
									if(!retval)
										preauthVO.setSubdentalrltxt(medicaldtls);
									   else
									   {
									    preauthVO.setSubdentalrl0Str(medicaldtls);
									    preauthVO.setSubdentalrl0(arr);
									   }
								}
							}	
					}
					if(ehfDentalOralExaminations.getJawsSub()!=null)
					{
						String medicaldtls=ehfDentalOralExaminations.getJawsSub();
						String rt=medicaldtls.substring(0,2);
						if("CH".equalsIgnoreCase(rt))
						{
							char rt2=(medicaldtls.charAt(2));
						   boolean retval =Character.isDigit(rt2);
						    if(!retval)
							preauthVO.setSubdentaljawstxt(medicaldtls);
						    else
						    {
							preauthVO.setSubdentaljaws1(medicaldtls);
						    }
						}
						else
						{
							  char rt2=(medicaldtls.charAt(2));
							   boolean retval =Character.isDigit(rt2);
							    if(!retval)
								preauthVO.setSubdentaljawstxt(medicaldtls);
							    else
							    {
								preauthVO.setSubdentaljaws1(medicaldtls);
							    }
						}
					}
					
					if(ehfDentalOralExaminations.getLymphadenopathy()!=null)
						preauthVO.setSubdental0(ehfDentalOralExaminations.getLymphadenopathy());
					if(ehfDentalOralExaminations.getJaws()!=null)
						preauthVO.setSubdental1(ehfDentalOralExaminations.getJaws());
					if(ehfDentalOralExaminations.getTmj()!=null)
						preauthVO.setSubdental2(ehfDentalOralExaminations.getTmj());
					if(ehfDentalOralExaminations.getFace()!=null)
						preauthVO.setSubdental3(ehfDentalOralExaminations.getFace());
					if(ehfDentalOralExaminations.getHardPalate()!=null)
						preauthVO.setDntsublistoral0(ehfDentalOralExaminations.getHardPalate());
					if(ehfDentalOralExaminations.getSoftPalate()!=null)
						preauthVO.setDntsublistoral1(ehfDentalOralExaminations.getSoftPalate());
					if(ehfDentalOralExaminations.getFloorMouth()!=null)
						preauthVO.setDntsublistoral2(ehfDentalOralExaminations.getFloorMouth());
					if(ehfDentalOralExaminations.getTongue()!=null)
						preauthVO.setDntsublistoral3(ehfDentalOralExaminations.getTongue());
					if(ehfDentalOralExaminations.getFrenalAttachment()!=null)
						preauthVO.setDntsublistoral4(ehfDentalOralExaminations.getFrenalAttachment());
					if(ehfDentalOralExaminations.getBuccalMucosa()!=null)
						preauthVO.setDntsublistoral5(ehfDentalOralExaminations.getBuccalMucosa());
					if(ehfDentalOralExaminations.getGingiva()!=null)
						preauthVO.setDntsublistoral6(ehfDentalOralExaminations.getGingiva());
					if(ehfDentalOralExaminations.getSwSite()!=null)
						preauthVO.setSwSite(ehfDentalOralExaminations.getSwSite());
					if(ehfDentalOralExaminations.getSwSize()!=null)
						preauthVO.setSwSize(ehfDentalOralExaminations.getSwSize());
					if(ehfDentalOralExaminations.getSwExtension()!=null)
						preauthVO.setSwExtension(ehfDentalOralExaminations.getSwExtension());
					if(ehfDentalOralExaminations.getSwColour()!=null)
						preauthVO.setSwColour(ehfDentalOralExaminations.getSwColour());
					if(ehfDentalOralExaminations.getSwConsistency()!=null)
						preauthVO.setSwConsistency(ehfDentalOralExaminations.getSwConsistency());
					if(ehfDentalOralExaminations.getSwTenderness()!=null)
						preauthVO.setSwTenderness(ehfDentalOralExaminations.getSwTenderness());
					if(ehfDentalOralExaminations.getSwBorders()!=null)
						preauthVO.setSwBorders(ehfDentalOralExaminations.getSwBorders());
					if(ehfDentalOralExaminations.getPsSite()!=null)
						preauthVO.setPsSite(ehfDentalOralExaminations.getPsSite());
					if(ehfDentalOralExaminations.getPsDischarge()!=null)
						preauthVO.setPsDischarge(ehfDentalOralExaminations.getPsDischarge());
			}
			EhfDentalTissueExaminations ehfDentalTissueExaminations = genericDao.findById(EhfDentalTissueExaminations.class, String.class, patientId);
			{
				if(ehfDentalTissueExaminations!=null)
				{
					if(ehfDentalTissueExaminations.getDeciduousCaries()!=null)
					  preauthVO.setCarriesdecidous(ehfDentalTissueExaminations.getDeciduousCaries());
					if(ehfDentalTissueExaminations.getDeciduousMissing()!=null)
						  preauthVO.setMissingdecidous(ehfDentalTissueExaminations.getDeciduousMissing());
					if(ehfDentalTissueExaminations.getMobile()!=null)
						  preauthVO.setMobiledecidous(ehfDentalTissueExaminations.getMobile());
					if(ehfDentalTissueExaminations.getGrosslyDecayed()!=null)
						  preauthVO.setGrosslydecadedecidous(ehfDentalTissueExaminations.getGrosslyDecayed());
					if(ehfDentalTissueExaminations.getPermanentCaries()!=null)
						  preauthVO.setCarriespermanent(ehfDentalTissueExaminations.getPermanentCaries());
					if(ehfDentalTissueExaminations.getRootGrosslyDecayed()!=null)
						  preauthVO.setRootstumppermannet(ehfDentalTissueExaminations.getRootGrosslyDecayed());
					if(ehfDentalTissueExaminations.getMobility()!=null)
						  preauthVO.setMobilitypermanent(ehfDentalTissueExaminations.getMobility());
					if(ehfDentalTissueExaminations.getAttritionAbrasion()!=null)
						  preauthVO.setAttritionpermanent(ehfDentalTissueExaminations.getAttritionAbrasion());
					if(ehfDentalTissueExaminations.getPermanentMissing()!=null)
						  preauthVO.setMissingpermanent(ehfDentalTissueExaminations.getPermanentMissing());
					if(ehfDentalTissueExaminations.getPermanentothers()!=null)
						  preauthVO.setOtherpermanent(ehfDentalTissueExaminations.getPermanentothers());
					
					
					
				}
				
				
			}
			/**
			 * get Examination findings
			 */
			EhfPatientExamFind ehfPatientExamFind = genericDao.findById(EhfPatientExamFind.class, String.class, patientId);
			if(ehfPatientExamFind != null)
			{
				if(ehfPatientExamFind.getBmi()!=null && !ehfPatientExamFind.getBmi().equalsIgnoreCase(""))
				preauthVO.setBmi(ehfPatientExamFind.getBmi());
				else
				preauthVO.setBmi("NA");
				if(ehfPatientExamFind.getHeight()!=null && !ehfPatientExamFind.getHeight().equalsIgnoreCase(""))
				preauthVO.setHeight(ehfPatientExamFind.getHeight());
				else
				preauthVO.setHeight("NA");
				if(ehfPatientExamFind.getWeight()!=null && !ehfPatientExamFind.getWeight().equalsIgnoreCase(""))
				preauthVO.setWeight(ehfPatientExamFind.getWeight());
				else
				preauthVO.setWeight("NA");
				preauthVO.setPallor(ehfPatientExamFind.getPallor());
				preauthVO.setCyanosis(ehfPatientExamFind.getCyanosis());
				preauthVO.setClubbingOfFingers(ehfPatientExamFind.getClubOfFingrToes());
				preauthVO.setLymphadenopathy(ehfPatientExamFind.getLymphadenopathy());
				preauthVO.setEdema(ehfPatientExamFind.getOedemaInFeet());
				preauthVO.setMalNutrition(ehfPatientExamFind.getMalnutrition());
				if(ehfPatientExamFind.getDehydration() != null && ehfPatientExamFind.getDehydration().equalsIgnoreCase("N"))
				preauthVO.setDehydration(ehfPatientExamFind.getDehydration());
				else if(ehfPatientExamFind.getDehydration() != null &&  !ehfPatientExamFind.getDehydration().equals("") && ehfPatientExamFind.getDehydration().contains("Y"))
				{
						preauthVO.setDehydration(ehfPatientExamFind.getDehydration().charAt(0)+"");
						preauthVO.setDehydrationType(ehfPatientExamFind.getDehydration().substring(1));
				}
				if(ehfPatientExamFind.getTemperature()!=null && !ehfPatientExamFind.getTemperature().equalsIgnoreCase(""))
				preauthVO.setTemperature(ehfPatientExamFind.getTemperature());
				else
				preauthVO.setTemperature("NA");	
				if(ehfPatientExamFind.getPluseRate()!=null && !ehfPatientExamFind.getPluseRate().equalsIgnoreCase(""))
				preauthVO.setPulseRate(ehfPatientExamFind.getPluseRate());
				else
				preauthVO.setPulseRate("NA");
				if(ehfPatientExamFind.getRespirationRate()!=null && !ehfPatientExamFind.getRespirationRate().equalsIgnoreCase(""))
				preauthVO.setRespirationRate(ehfPatientExamFind.getRespirationRate());
				else
					preauthVO.setRespirationRate("NA");	
				if(ehfPatientExamFind.getBpLt()!=null && !ehfPatientExamFind.getBpLt().equalsIgnoreCase(""))
				preauthVO.setBpLmt(ehfPatientExamFind.getBpLt());
				else
					preauthVO.setBpLmt("NA");
				if(ehfPatientExamFind.getBpRt()!=null && !ehfPatientExamFind.getBpRt().equalsIgnoreCase(""))
				preauthVO.setBpRmt(ehfPatientExamFind.getBpRt());
				else
					preauthVO.setBpRmt("NA");
				if(ehfPatientExamFind.getTreatmentDntl()!=null && !ehfPatientExamFind.getTreatmentDntl().equalsIgnoreCase(""))
					preauthVO.setTreatmentDntl(ehfPatientExamFind.getTreatmentDntl());
					else
						preauthVO.setTreatmentDntl("NA");
			}
			
			preauthVO.setFamilyHis(ehfPatientHospDiagnosis.getFamilyHistory());	
			if(ehfPatientHospDiagnosis.getFamilyHistoryOthr()!=null && !ehfPatientHospDiagnosis.getFamilyHistoryOthr().equalsIgnoreCase(""))
				preauthVO.setFamilyHistoryOthr(ehfPatientHospDiagnosis.getFamilyHistoryOthr());
			
			/**
			 * Investigation Details--wm_concate(gim.invName );
			 */
			StringBuffer query=new StringBuffer();
			query.append("select gim.inv_Main_Code as TESTKNOWN, ");
			query.append(" case when pt.test_Id like '%OI%' then 'Others' else gim.inv_Main_Name end as  TEST, ");
			query.append(" case when pt.test_Id like '%OI%' then pt.test_Name else gim.inv_Name end as NAME, ");
			query.append(" pt.test_Id as SPLINVSTID ");
			query.append(" ,pt.ATTACH_PATH as ROUTE,gim.invest_Price as PRICE from Ehfm_Gen_Investigations_Mst gim,Ehf_Patient_Tests pt"); 
			query.append(" where pt.test_Id=gim.inv_Code(+) and pt.patient_Id='"+patientId+"' ");
			List<PreauthVO> list1=genericDao.executeSQLQueryList(PreauthVO.class, query.toString());
			if(list1!=null && !list1.isEmpty())
			{
				preauthVO.setInvList(list1);
			}	
			if(ehfPatient.getPatientIpop()!=null && !ehfPatient.getPatientIpop().equalsIgnoreCase("")){
				preauthVO.setIpOpFlag(ehfPatient.getPatientIpop());
				preauthVO.setDiagnosisType(ehfPatientHospDiagnosis.getDiagnosisType());
				preauthVO.setMainCatName(ehfPatientHospDiagnosis.getMainCatCode());
				preauthVO.setCatId(ehfPatientHospDiagnosis.getCategoryCode());
				preauthVO.setSubCatName(ehfPatientHospDiagnosis.getSubCatCode());
				preauthVO.setDisMainId(ehfPatientHospDiagnosis.getDiseaseCode());
				preauthVO.setDisAnatomicalSitename(ehfPatientHospDiagnosis.getDiseaseAnatCode());
				preauthVO.setPatientIPNo(ehfPatient.getPatientIpopNo());
			}
			query=new StringBuffer();
			
		
			
			
		
			
			query.append(" select nvl(EDM.MAIN_GRP_CODE,'Others') as DRUGTYPECODE,nvl(EDM.ther_main_group_code,'Others') as DRUGSUBTYPECODE,nvl(EDM.PHAR_SUB_GRP_CODE,'Others') as PSUBGRPCODE,nvl(EDM.CHE_SUB_GRP_CODE,'Others') as CSUBGRPCODE,EPD.ASRI_DRUG_CODE as DRUGCODE, ");
			query.append(" nvl(EDM.main_Grp_Name,'Others') as DRUGTYPENAME,nvl(EDM.ther_Main_Grp_Name,'Others') as DRUGSUBTYPENAME,nvl(EDM.phar_Sub_Grp_Name,'Others') as PSUBGRPNAME,nvl(EDM.che_Sub_Grp_Name,'Others') as CSUBGRPNAME," );
			
			query.append( "  case when EPD.ASRI_DRUG_CODE like '%OD%' then");
			query.append( " EPD.other_drug_name else EDM.CHEMICAL_NAME end as  DRUGNAME,EPD.OTHER_DRUG_NAME as OTHERDRUGNAME, ");
					
			query.append(" ER.route_Type_Code as ROUTETYPE,ER.route_Type_Name as ROUTETYPENAME,ER.route_Code as ROUTE,ER.route_Name as ROUTENAME,ES.strength_Unit_Code as STRENGTHTYPE,ES.strength_Unit_Name as STRENGTHTYPENAME ,");
			query.append(" ES.strength_Code as STRENGTH,ES.strength_Name as STRENGTHNAME,EPD.dosage as DOSAGE,EPD.medication_Period as MEDICATIONPERIOD from Ehf_Patient_Drugs EPD,Ehfm_Op_Drug_Mst EDM,Ehfm_Op_Strength_Mst ES, ");
			query.append(" Ehfm_Op_Route_Mst ER where EPD.ASRI_drug_Code=EDM.chemical_Code(+) and ER.route_Code(+)=EPD.route and ES.strength_Code(+)=EPD.strength ");
			query.append("  and EPD.patient_Id='"+patientId+"' ");
			
			
			List<DrugsVO> drugList=genericDao.executeSQLQueryList(DrugsVO.class, query.toString());
			if(drugList!=null && drugList.size()>0){
				preauthVO.setDrugList(drugList);
			}
		}
		
		
		return preauthVO;
	}
	private String String(char rt1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String checkDentalHospital(String hospId){
		String dentalFlg="N";
		StringBuffer query = new StringBuffer();
		query.append("select ehs.icd_cat_code as TEST from ehfm_hosp_speciality ehs where ehs.hosp_id='"+hospId+"'");
		List<PreauthVO> specialityList = genericDao.executeSQLQueryList(PreauthVO.class, query.toString());
		if(specialityList!=null){
			for(PreauthVO vo : specialityList){
				if(vo.getTEST() !=null && "S18".equalsIgnoreCase(vo.getTEST())){
					dentalFlg="Y";
					break;
				}
			}
			
		}
		return dentalFlg;
	}
	/*
	 * Added by Srikalyan to get the Submit
	 * Drug Details of Pharmacy Case
	 */
	@Override
	public String submitPharmacyCase(String caseId,String patId,String drugDtls)
		{
			String returnMsg=null;
			int count=0,length=0;
			try
				{
					if(drugDtls!=null && !"null".equalsIgnoreCase(drugDtls))
						{	
							String[] drugParam=drugDtls.split("@");
								if(drugParam!=null && drugParam.length>0)
									{
										length=drugParam.length;
										for(String eachParam:drugParam)
											{
												String[] finalParam=eachParam.split("~");
												if(finalParam!=null && finalParam.length>1)
													{
														if(finalParam[0]!=null && finalParam[1]!=null)
															{
																EhfPatientDrugs ehfPatientDrugs=genericDao.findById(EhfPatientDrugs.class,Long.class,Long.parseLong(finalParam[1]));
																	if(caseId !=null && patId!=null &&
																			caseId.equalsIgnoreCase(ehfPatientDrugs.getCaseId()) && 
																			patId.equalsIgnoreCase(ehfPatientDrugs.getPatientId()) &&
																			finalParam[0].equalsIgnoreCase(ehfPatientDrugs.getDrugCode()))
																		{
																			SimpleDateFormat newDateFmt=new SimpleDateFormat("dd-MM-yyyy");
																			ehfPatientDrugs.setIssueYn("Y");
																			
																			if(finalParam[2]!=null && !"".equalsIgnoreCase(finalParam[2]))
																				{
																					Date date=newDateFmt.parse(finalParam[2]);
																					ehfPatientDrugs.setExpiryDate(date);
																				}
																			if(finalParam[3]!=null && !"".equalsIgnoreCase(finalParam[3]))
																				ehfPatientDrugs.setQuantity(finalParam[3]);
																		}
																	ehfPatientDrugs=genericDao.save(ehfPatientDrugs);
																	if(ehfPatientDrugs!=null)
																		{
																			EhfCase ehfCase=genericDao.findById(EhfCase.class, String.class, caseId);
																			if(ehfCase!=null)
																				{
																					ehfCase.setPharmaFlag("Y");
																					ehfCase=genericDao.save(ehfCase);
																				}
																			count++;
																		}
															}
													}
											}
									}
								if(length==count)
									returnMsg=config.getString("Pharmacy.SaveSuccess.Msg");
								else
									returnMsg=config.getString("Pharmacy.SaveFailure.Msg");
						}
				}
			catch(Exception e)
				{
					e.printStackTrace();
//					GLOGGER.error("Exception occurred in submitPharmacyCase() in PatientDaoImpl class."+e.getMessage());
					return returnMsg;
				}
			return returnMsg;
		}
	@Override
	public String deleteInvestigations(String lStrCaseId, String procCode,String investId,String asriCode) {
		String result="false";
		List<GenericDAOQueryCriteria> investCriteria=new ArrayList<GenericDAOQueryCriteria>();
		if(investId!=null && !investId.equalsIgnoreCase("") && !investId.equalsIgnoreCase("NA")){
			
			investCriteria.add(new GenericDAOQueryCriteria("caseId",lStrCaseId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			investCriteria.add(new GenericDAOQueryCriteria("icdProcCode",procCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			investCriteria.add(new GenericDAOQueryCriteria("investigationId",investId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfCaseTherapyInvest> investList=genericDao.findAllByCriteria(EhfCaseTherapyInvest.class, investCriteria);
			for(EhfCaseTherapyInvest row: investList){
				System.out.println("deleting");
				genericDao.delete(row);
				result="true";
			}
		}
				
		List<GenericDAOQueryCriteria> procCriteria=new ArrayList<GenericDAOQueryCriteria>();
		procCriteria.add(new GenericDAOQueryCriteria("caseId",lStrCaseId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		procCriteria.add(new GenericDAOQueryCriteria("icdProcCode",procCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		procCriteria.add(new GenericDAOQueryCriteria("asriCatCode",asriCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));		
		List<EhfCaseTherapy> list=genericDao.findAllByCriteria(EhfCaseTherapy.class, procCriteria);
		for(EhfCaseTherapy row: list){
			investCriteria=new ArrayList<GenericDAOQueryCriteria>();
			investCriteria.add(new GenericDAOQueryCriteria("caseId",lStrCaseId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			investCriteria.add(new GenericDAOQueryCriteria("icdProcCode",procCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfCaseTherapyInvest> investList1=genericDao.findAllByCriteria(EhfCaseTherapyInvest.class, investCriteria);
			System.out.println(investList1.size());
			if(investList1.size()==0){
			System.out.println("deleting");
			genericDao.delete(row);
			}
			result="true";
		}
		
		return result;
	}
	@Override
	public String deleteGenInvest(String patientId, String investId) {
		String result="false";
		List<GenericDAOQueryCriteria> criteria=new ArrayList<GenericDAOQueryCriteria>();
		criteria.add(new GenericDAOQueryCriteria("patientId",patientId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteria.add(new GenericDAOQueryCriteria("testId",investId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));		
		List<EhfPatientTests> list=genericDao.findAllByCriteria(EhfPatientTests.class, criteria);
		for(EhfPatientTests row: list){
			System.out.println("deleting");
			genericDao.delete(row);
			result="true";
		}
		return result;
	}
	@Override
	public List<String> getInvestigations(String lStrBlockId) {
		List<LabelBean> investigationsList=new ArrayList<LabelBean>(); 
		List<String> investList=new ArrayList<String>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select eim.invCode as ID,eim.invName as VALUE from EhfmGenInvestigationsMst eim where eim.activeYN='Y' ");
			query.append(" and eim.invMainCode='"+lStrBlockId+"' order by eim.invName asc");
			investigationsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
			for(LabelBean invest:investigationsList)
    		{
    			investList.add(invest.getID()+"~"+invest.getVALUE()+"@");
    		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception occurred in getInvestigations() in CommonServiceImpl class."+e.getMessage());
		}
		return investList;
	}
	
	public String getInvestPrice(String blockId,String investId)
	{
		String price =null;
		StringBuffer hql=new StringBuffer();
		List<LabelBean> priceList=new ArrayList<LabelBean>();
		SessionFactory factory=hibernateTemplate.getSessionFactory();
		Session session=factory.openSession();
		try
		{
		hql.append(" select a.investPrice as price from EhfmGenInvestigationsMst a");
		hql.append( " where a.invMainCode='"+blockId+"' and a.invCode='"+investId+"' ");
		Query query=session.createQuery(hql.toString());
		priceList=genericDao.executeHQLQueryList(LabelBean.class,hql.toString());
		if(priceList!=null &&priceList.size()>0)
		{
			price=priceList.get(0).getPrice();	
		}
		}
		catch(Exception e)
		{
			GLOGGER.error("error occured in getInvestPrice() method in patientDaoImpl.class"+ e.getMessage());
		}
		finally
		{
			session.close();
			factory.close();
		}
		
		return price;
	}
	
	@Override
	public List<LabelBean> getOpDrugSubList(String drugTypeCode,String pStrIpOpType) {
		List<LabelBean> drugSubList = null;	
    	StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append(" select distinct edm.therMainGrpCode as  ID , edm.therMainGrpName as VALUE from ");
			if(pStrIpOpType != null && pStrIpOpType.equalsIgnoreCase("IP"))
				query.append(" EhfmIpDrugMst edm ");
			else
				query.append(" EhfmOpDrugMst edm ");
			query.append("  where edm.mainGrpCode='"+drugTypeCode+"' and edm.activeYN='Y' order by edm.therMainGrpName asc");
			drugSubList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
    	{
    		e.printStackTrace();
//    		GLOGGER.error("Exception Occurred in getDrugSubList() in PatientDaoImpl class."+e.getMessage());
    	}
		
		return drugSubList;
	}
	@Override
	public List<LabelBean> getOpPharSubList(String drugTypeCode,String pStrIpOpType) {
		List<LabelBean> drugSubList = null;	
    	StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.pharSubGrpCode as  ID , edm.pharSubGrpName as VALUE from ");
			if(pStrIpOpType != null && pStrIpOpType.equalsIgnoreCase("IP"))
				query.append(" EhfmIpDrugMst edm ");
			else
				query.append(" EhfmOpDrugMst edm ");
			query.append(" where edm.therMainGrpCode='"+drugTypeCode+"' and edm.activeYN='Y' order by edm.pharSubGrpName asc");
			drugSubList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
    	{
    		e.printStackTrace();
//    		GLOGGER.error("Exception Occurred in getDrugSubList() in PatientDaoImpl class."+e.getMessage());
    	}
		
		return drugSubList;
	}
	@Override
	public List<LabelBean> getOpChemSubList(String pharSubCode,String pStrIpOpType) {
		List<LabelBean> drugSubList = null;	
    	StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.cheSubGrpCode as  ID , edm.cheSubGrpName as VALUE from ");
			if(pStrIpOpType != null && pStrIpOpType.equalsIgnoreCase("IP"))
				query.append(" EhfmIpDrugMst edm ");
			else
				query.append(" EhfmOpDrugMst edm ");
			query.append(" where edm.pharSubGrpCode='"+pharSubCode+"' and edm.activeYN='Y' order by edm.cheSubGrpName asc");
			drugSubList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
    	{
    		e.printStackTrace();
//    		GLOGGER.error("Exception Occurred in getDrugSubList() in PatientDaoImpl class."+e.getMessage());
    	}
		
		return drugSubList;
	}
	@Override
	public List<LabelBean> getChemSubList(String cSubGrpCode,String pStrIpOpType) {
		List<LabelBean> drugSubList = null;	
    	StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.chemicalCode as  ID , edm.chemicalName as VALUE from ");
			if(pStrIpOpType != null && pStrIpOpType.equalsIgnoreCase("IP"))
				query.append(" EhfmIpDrugMst edm ");
			else
				query.append(" EhfmOpDrugMst edm ");
			query.append("  where edm.cheSubGrpCode='"+cSubGrpCode+"' and edm.activeYN='Y' order by edm.chemicalName asc");
			drugSubList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
    	{
    		e.printStackTrace();
//    		GLOGGER.error("Exception Occurred in getDrugSubList() in PatientDaoImpl class."+e.getMessage());
    	}
		
		return drugSubList;
	}
	@Override
	public List<LabelBean> getRouteList(String routeTypeCode,String pStrIpOpType) {
		List<LabelBean> routeList = null;	
    	StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.routeCode as  ID , edm.routeName as VALUE from ");
			if(pStrIpOpType != null && pStrIpOpType.equalsIgnoreCase("IP"))
				query.append(" EhfmIpRouteMst edm ");
			else
				query.append(" EhfmOpRouteMst edm ");
			query.append("  where edm.routeTypeCode='"+routeTypeCode+"' and edm.activeYN='Y' order by edm.routeName asc");
			routeList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
    	{
    		e.printStackTrace();
//    		GLOGGER.error("Exception Occurred in getRouteList() in PatientDaoImpl class."+e.getMessage());
    	}
		
		return routeList;
	}
	@Override
	public List<LabelBean> getStrengthList(String strengthTypeCode,String pStrIpOpType) {
		List<LabelBean> routeList = null;	
    	StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.strengthCode as  ID , edm.strengthName as VALUE from ");
			if(pStrIpOpType != null && pStrIpOpType.equalsIgnoreCase("IP"))
				query.append(" EhfmIpStrengthMst edm ");
			else
				query.append(" EhfmOpStrengthMst edm ");
			query.append(" where edm.strengthUnitCode='"+strengthTypeCode+"' and edm.activeYN='Y' order by edm.strengthName asc");
			routeList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
    	{
    		e.printStackTrace();
//    		GLOGGER.error("Exception Occurred in getRouteList() in PatientDaoImpl class."+e.getMessage());
    	}
		
		return routeList;
	}
	@Override
	public String getSequence(String pStrSeqName)throws Exception
	  {
	    String lStrSeqRetVal = "";   
	    
	    try{
	     
	    	StringBuffer query = new StringBuffer();
	        query.append(" SELECT "+pStrSeqName+".NEXTVAL NEXTVAL  FROM DUAL ");
	        List<LabelBean> seqList = genericDao.executeSQLQueryList(LabelBean.class,query.toString());

	        if(seqList != null){
	        	
	          lStrSeqRetVal = seqList.get(0).getNEXTVAL().toString();
	        }
	    }catch(Exception e){
	    
	    	e.printStackTrace();
	    	
	    }
	    
	    return lStrSeqRetVal;
	  }
	
	public String checkCaseTherapy(String caseId, String asriCode,String icdCatCode,String icdProcCode) {
    String flag="true";
	List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
	criteriaList.add(new GenericDAOQueryCriteria("caseId",caseId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	criteriaList.add(new GenericDAOQueryCriteria("asriCatCode",asriCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	criteriaList.add(new GenericDAOQueryCriteria("icdCatCode",icdCatCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	criteriaList.add(new GenericDAOQueryCriteria("icdProcCode",icdProcCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	List<EhfCaseTherapy> ehfTherapyLst = genericDao.findAllByCriteria(EhfCaseTherapy.class, criteriaList);
			if(ehfTherapyLst.size()>0)
				flag="false";
	return flag;
	}
	@Override
	public List<CommonDtlsVO> getDtrsFormDtls(String caseId) throws Exception {
		StringBuffer queryBuff = new StringBuffer();
		List<CommonDtlsVO> resLst = null;
		try
		{
			
			
			queryBuff.append(" select distinct p.patientId as PATID, p.name as PATIENTNAME , p.cardNo as CARDNO , l1.locName as DISTRICT , l2.locName  as MANDAL , l3.locName as VILLAGE , p.crtDt as date ");
			queryBuff.append(" , p.age||' years '||p.ageMonths||' months '||p.ageDays||' days ' as AGE , cast(p.contactNo as string) as CONTACT , case when p.slab ='P' then 'Private Ward' else 'Semi Private Ward' end as slabType ");
			queryBuff.append(" ,ec.caseId  as CASENO , ec.claimNo as CLAIMNO  , ec.caseId as CASEID , p.patientId as PATID ,ec.crtDt as date , p.cardType as cardType ");
			queryBuff.append("  ,p.regHospId as INTIID, hm.hospName||''||hm.hospDispCode as HOSPNAME , case when p.gender='M' then 'Male' else case when p.gender='F' then 'Female' else p.gender end end as GENDER , a.cmbDtlName as STATUS ,ec.caseStatus as CASESTAT   ");
			queryBuff.append(" , p.patientIpopNo as IPNO , p.houseNo || ' ' || p.street as PATADDR, p.crtDt||'' as PATDT , ");
			queryBuff.append("  hm.houseNumber || ' ' || hm.street || ' ' || hm.hospCity as HOSPADDR,ec.schemeId as  scheme,p.patientScheme as patientScheme");
			queryBuff.append(" from EhfPatient p ,EhfmLocations l1, EhfmLocations l2 , EhfmLocations l3 ,EhfCase ec , EhfmHospitals hm ");
			queryBuff.append(" , EhfmCmbDtls a , EhfPatientHospDiagnosis ph  ");
			
			
			queryBuff.append(" where p.patientId = ec.casePatientNo and  p.districtCode = l1.id.locId and  ");
			queryBuff.append(" p.mandalCode = l2.id.locId and p.villageCode = l3.id.locId  and ec.caseId = '"+caseId+"' ");
			queryBuff.append(" and p.regHospId = hm.hospId and a.cmbDtlId = ec.caseStatus ");
			queryBuff.append(" and p.patientId =ph.patientId ");
	
			System.out.println(queryBuff.toString());
			
			resLst = genericDao.executeHQLQueryList(CommonDtlsVO.class, queryBuff.toString());
			
			
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getDtrsFormDtls() in PatientDaoImpl class."+e.getMessage());
		}
		return resLst;	
	}
	@Override
	public PatientVO getOPCases(HashMap<String, String> searchMap) {
		SessionFactory factory=null;
		Session session=null;
		String database=config.getString("Database");
		PatientVO patVO = new PatientVO();
		List<PatientVO> registeredPatientsList= new ArrayList<PatientVO>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sqlf = new SimpleDateFormat("yyyy-MM-dd");
		String hospId=null;
		String grpId=null, pharmaScheme=null;
		String patientScheme=searchMap.get("patientScheme");
		try
		{
			grpId=searchMap.get("grpId").toString();
			
		if(searchMap.get("userHospId")!=null)
		{
			hospId=searchMap.get("userHospId").toString();
		}
		else if(searchMap.get("userHospId")==null && ("GP1".equalsIgnoreCase(grpId) || "GP2".equalsIgnoreCase(grpId)))
		{
			patVO.setMsg("No hospital is mapped for this user");
		}
		String caseNo="";
		String patientName="";
		String patientNo="";
		String cardNo="";
		String stateId="";
		String districtId="";
		String fromDate;
		String sqlFromDate;
		String toDate;
		String sqlToDate;

		int startIndex=Integer.parseInt(searchMap.get("lStrStartIndex"));
		int rowsPerPage=Integer.parseInt(searchMap.get("lStrRowsperpage"));
		
		factory= hibernateTemplate.getSessionFactory();
		session=factory.openSession();
		StringBuffer searchQuery = new StringBuffer();
		StringBuffer countQuery = new StringBuffer();
		String patientIpop=config.getString("PatientIPOP.OP");
		countQuery.append("select count(ec.caseId) from EhfPatient ep,EhfCase ec where  ec.caseStatus='CD5' and ep.patientId=ec.casePatientNo and ep.registerYN='Y' and ep.patientIpop='"+patientIpop+"'"); // and ap.patientIpop='"+patientIpop+"'");
		searchQuery.append("select ep.name as firstName,ep.patientId as patientId,ec.caseNo as caseId,ep.cardNo as rationCard,el.locName as districtCode " +
				",case when coalesce(ep.gender,'NA')='M' then 'MALE' else case when coalesce(ep.gender,'NA')='F' then 'FEMALE' else coalesce(ep.gender,'NA') end end as gender" +
				",to_char(ec.caseRegnDate ,'DD/MM/YYYY') as caseRegDate from EhfPatient ep, EhfCase ec,EhfmLocations el where  ec.caseStatus='CD5' " +
						"and ep.patientId=ec.casePatientNo and ep.registerYN='Y' and ep.patientIpop='"+patientIpop+"' and el.id.locId=ep.districtCode "); //and ap.patientIpop='"+patientIpop+"'");
		
		if(hospId!=null)
		{
			countQuery.append(" and ec.caseHospCode='"+hospId+"' ");
			searchQuery.append(" and ec.caseHospCode='"+hospId+"' ");
		}
		if(searchMap.get("caseNo")!=null)
		{
			caseNo=searchMap.get("caseNo").toString();
			countQuery.append("  and ec.caseId='"+caseNo+"'");
			searchQuery.append("  and ec.caseId='"+caseNo+"'");
		}
		if(searchMap.get("patientName")!=null)
		{
			patientName=searchMap.get("patientName").toString();
			countQuery.append("  and upper(ep.name) like  upper('%"+patientName.trim()+"%') ");
			searchQuery.append("  and upper(ep.name) like  upper('%"+patientName.trim()+"%') ");
		}
		if(searchMap.get("patientNo")!=null)
		{
			patientNo=searchMap.get("patientNo").toString();
			countQuery.append("and ep.patientId='"+patientNo+"'");
			searchQuery.append("and ep.patientId='"+patientNo+"'");
		}
		if(searchMap.get("cardNo")!=null)
		{
			cardNo=searchMap.get("cardNo").toString().toUpperCase();
			countQuery.append("  and upper(ep.cardNo) like  upper('%"+cardNo.trim()+"%') ");
			searchQuery.append("  and upper(ep.cardNo) like  upper('%"+cardNo.trim()+"%') ");
		}
		if(searchMap.get("state")!=null)
		{
			stateId=searchMap.get("state").toString();
			countQuery.append("and ep.state='"+stateId+"'");
			searchQuery.append("and ep.state='"+stateId+"'");
		}
		if(searchMap.get("district")!=null)
		{
			districtId=searchMap.get("district").toString();
			countQuery.append("and ep.districtCode='"+districtId+"'");
			searchQuery.append("and ep.districtCode='"+districtId+"'");
		}
		if(searchMap.get("pharmaScheme")!=null)
		{
			pharmaScheme=searchMap.get("pharmaScheme");
			countQuery.append(" and ep.schemeId in ('"+pharmaScheme+"','1') ");
			searchQuery.append(" and ep.schemeId in ('"+pharmaScheme+"','1')");
		}
		if(searchMap.get("fromDate")!=null && searchMap.get("toDate")!=null)
		{
			fromDate=searchMap.get("fromDate").toString();
			sqlFromDate=sqlf.format(sdf.parse(fromDate).getTime());
			toDate=searchMap.get("toDate").toString();
			
			//To include todate in search criteria--adding date+1 
			Calendar cal = Calendar.getInstance();  
        	cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(toDate)); 
        	cal.add(Calendar.DAY_OF_YEAR, 1); // <--  
        	Date tomorrow = cal.getTime();  
        	 String lStrToDate = new SimpleDateFormat("dd-MM-yyyy").format(tomorrow);
        	 //End of date+1 
			 
			sqlToDate=sqlf.format(sdf.parse(toDate).getTime());
			
			if(database.equalsIgnoreCase("ORACLE"))
			{
				countQuery.append("and ec.caseRegnDate between  TO_DATE('"+fromDate+"','DD-MM-YYYY') and TO_DATE('"+lStrToDate+"','DD-MM-YYYY')");
				searchQuery.append("and ec.caseRegnDate between  TO_DATE('"+fromDate+"','DD-MM-YYYY') and TO_DATE('"+lStrToDate+"','DD-MM-YYYY')");
			}
			else if(database.equalsIgnoreCase("MYSQL"))
			{
				countQuery.append("and ec.caseRegnDate between '"+sqlFromDate+"' and '"+sqlToDate+"'");
				searchQuery.append("and ec.caseRegnDate between '"+sqlFromDate+"' and '"+sqlToDate+"'");
			}
		}
		
		if(searchMap.get("pharmaCases")!=null && searchMap.get("apprPharma")!=null)
			{
				if(searchMap.get("pharmaCases").equalsIgnoreCase("Y") && searchMap.get("apprPharma").equalsIgnoreCase("N"))
					{
						countQuery.append("and ec.pharmaFlag in ( 'N' , 'Y' ,'NA' ) ");
						searchQuery.append("and ec.pharmaFlag in ( 'N' , 'Y' ,'NA' ) ");
					}
				else if(searchMap.get("pharmaCases").equalsIgnoreCase("Y") && searchMap.get("apprPharma").equalsIgnoreCase("Y"))
					{
						countQuery.append("and ec.pharmaFlag in ( 'N' ) ");
						searchQuery.append("and ec.pharmaFlag in ( 'N' ) ");
					}
			}
		/*Added by Venkatesh to separate EHS and JHS */
		if(patientScheme!=null && patientScheme.equalsIgnoreCase(config.getString("Scheme.EHS")) || (patientScheme.equalsIgnoreCase(config.getString("Scheme.JHS"))) )
		{
			countQuery.append(" and ec.patientScheme='"+patientScheme+"' ");
			searchQuery.append(" and ec.patientScheme='"+patientScheme+"' ");
		}
		countQuery.append(" and ep.schemeId in ('CD202','1') ");
		searchQuery.append(" and ep.schemeId in ('CD202','1')");
		/*end*/
		
		Long count = (Long) session.createQuery(countQuery.toString()).uniqueResult();
		searchQuery.append(" order by ec.caseRegnDate asc");
		registeredPatientsList=session.createQuery(searchQuery.toString()).setFirstResult(startIndex).setMaxResults(rowsPerPage).setResultTransformer(Transformers.aliasToBean(PatientVO.class)).list();
		
		patVO.setTotRowCount(count.intValue());
		patVO.setRegisteredPatList(registeredPatientsList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getOPCases() in PatientDaoImpl class."+e.getMessage());
		}
		finally
		{
			session.close();
			factory.close();
		}
		return patVO;
	}
	@Override
	public String getDutyDoctorById(String regNo) {
		String q="select distinct title||' '||doctorName as ID from EhfmDutyDctrs where id.regNum='"+regNo+"'";
		String user="";
		try
		{
			List<LabelBean> list=genericDao.executeHQLQueryList(LabelBean.class, q);
			if(list!=null && !list.isEmpty())
			{
				user=list.get(0).getID();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public String getProcedureType(String procCode,String asriCode) {
		String q="select id.process as ID from EhfmTherapyProcMst where id.icdProcCode='"+procCode+"' and id.asriCode='"+asriCode+"'";
		String procedureType="";
		try
		{
			List<LabelBean> list=genericDao.executeHQLQueryList(LabelBean.class, q);
			if(list!=null && !list.isEmpty())
			{
				procedureType=list.get(0).getID();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return procedureType;
	}
	
	
	
	
	/**
	 * ;
	 * @param caseId
	 * @return List<CommonDtlsVO> caseDetailsList
	 * @function This Method is Used For common details for the caseId specified
	 */
	@Override
	public List<CommonDtlsVO> getPatientCommonDtls(String caseId){
		StringBuffer queryBuff = new StringBuffer();
		HashMap<Object,String> lQueryVal=new HashMap<Object,String>();
		int i=0;
		List<CommonDtlsVO> caseDetailsList = null;
		try
		{
			queryBuff.append(" select distinct p.name as PATIENTNAME , p.employeeNo as EMPLOYEENO, p.cardNo as CARDNO , l1.locName as DISTRICT , l2.locName  as MANDAL , l3.locName as VILLAGE , ec.caseRegnDate as date ");
			queryBuff.append(" , p.age||' years '||p.ageMonths||' months '||p.ageDays||' days ' as AGE , cast(p.contactNo as string) as CONTACT , cast(p.age as string) as AGEYEARS , case when p.slab ='P' then 'Private Ward' else 'Semi Private Ward' end as slabType ");
			queryBuff.append(" ,ec.caseId  as CASENO , ec.claimNo as CLAIMNO  , ec.caseId as CASEID , p.patientId as PATID , p.cardType as cardType ");
			queryBuff.append("  ,p.regHospId as INTIID,hm.hospType as hospType, hm.hospName||''||hm.hospDispCode as HOSPNAME , case when p.gender='M' then 'Male' else case when p.gender='F' then 'Female' else p.gender end end as GENDER , a.cmbDtlName as STATUS ,ec.caseStatus as CASESTAT   ");
			queryBuff.append(" , p.patientIpopNo as IPNO ,  p.intimationId as telephonicId , p.patientIpop as PATTYPE, p.houseNo || ' ' || p.street as PATADDR, p.crtDt||'' as PATDT , ");
			queryBuff.append(" case when ph.caseAdmType='EMG' then 'Emergency' else case when ph.caseAdmType='PLN' then 'Planned' else ph.caseAdmType end end as ADMTYPE , hm.houseNumber || ' ' || hm.street || ' ' || hm.hospCity as HOSPADDR , ");
			queryBuff.append(" ph.doctType as doctType,ph.doctId as DOCID,ph.doctName as DOCNAME,ph.doctQuali as DOCQUAL,ph.doctMobNo as DOCCONTACT,ph.doctRegNo as DOCREGNO,p.crtUsr as mithra,ec.schemeId as  scheme,p.patientScheme as patientScheme,ph.unitName as unitName,ph.unitHodName as unitHodName ");
			queryBuff.append(" from EhfPatient p ,EhfmLocations l1, EhfmLocations l2 , EhfmLocations l3 ,EhfCase ec , EhfmHospitals hm ");
			queryBuff.append(" , EhfmCmbDtls a , EhfPatientHospDiagnosis ph ");
			queryBuff.append(" where p.patientId = ec.casePatientNo and  p.districtCode = l1.id.locId and  ");
			queryBuff.append(" p.mandalCode = l2.id.locId and p.villageCode = l3.id.locId  and ec.caseId = '"+caseId+"' ");
			queryBuff.append(" and p.regHospId = hm.hospId and a.cmbDtlId = ec.caseStatus ");
			queryBuff.append(" and p.patientId =ph.patientId ");

			caseDetailsList = genericDao.executeHQLQueryList(CommonDtlsVO.class, queryBuff.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return caseDetailsList;		
	}
	/**
	 * ;
	 * @param PatientVO patientVO
	 * @return List<LabelBean> docAavilList
	 * @function This Method is Used For getting available doctor list for given hospital and specialty
	 */
	@Override
	public List<LabelBean> getDocAvailLst(PatientVO patientVO) throws Exception{
		List<LabelBean> docAvailLst = new ArrayList<LabelBean>();	
		try
		{
			StringBuffer query = new StringBuffer();
			query.append(" select z.id.regNum  as ID,z.splstName as  VALUE  ");
			query.append(" from EhfmSplstDctrs z , EhfmDotorSplty y where z.id.isActiveYn ='Y' and z.isConsultant='"+patientVO.getDoctorType()+"'  ");
			query.append(" and y.id.spctlyCode='"+patientVO.getTherapyCatId()+"' and z.id.regNum = y.id.regNum  and z.apprvStatus = 'CD896' and z.id.regNum is not null ");
			query.append(" and z.id.hospId = '"+patientVO.getHospitalCode()+"' ");
			docAvailLst = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getDocAvailLst() in PatientDaoImpl class."+e.getMessage());
		}

		return docAvailLst;
	}
	/**
	 * ;
	 * @param PatientVO patientVO
	 * @return List<String> specInvestList
	 * @function This Method is Used For getting special investigation list for given therapy
	 */
	@Override
	public List<String> getSpecialInvestigationLst(PatientVO patientVO)
	{
		List<LabelBean> lstSpecialInvst = new ArrayList<LabelBean>();
		List<String> SpecialInvstDtls = new ArrayList<String>();
		String surgeryId = null;
		try
		{
			StringBuffer query = new StringBuffer();
			query.append(" select distinct eti.id.investigationId||'~'||eti.investDesc as ID ");
			StringTokenizer str = new StringTokenizer(patientVO.getTherapyCatId(),"~");
			while(str.hasMoreTokens())
			{
				if(surgeryId!= null && !surgeryId.equalsIgnoreCase("") )
					surgeryId = surgeryId +"','"+str.nextToken();
				else
					surgeryId = str.nextToken();
			}
			query.append(" from EhfmTherapyInvest eti where eti.id.ICDProcCode in('"+surgeryId+"') ");
			if(patientVO.getAsriCatCode()!=null && !patientVO.getAsriCatCode().equalsIgnoreCase(""))
				query.append("  and eti.id.asriSpec in('"+patientVO.getAsriCatCode()+"') ");
			query.append(" and eti.activeYn ='Y' ");  //and eti.preOpPostOp ='PRE'
			query.append(" and eti.id.schemeId ='"+patientVO.getSchemeId()+"'");
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
	public List<PreauthVO> getcaseSurgeryDtls(String caseId)  
	{
		List<PreauthVO> lstSurgerydlts = new ArrayList<PreauthVO>();
		EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, caseId);
		String hospId = "";
		if(ehfCase!=null)
			hospId=ehfCase.getCaseHospCode();
		try{
			StringBuffer query = new StringBuffer();
			query.append(" select DISTINCT am.disMainName as asriCatName  ,  am.disMainId  as catId ,cm.icdCatName as catName , cm.id.icdCatCode as  icdCatCode ,");
			query.append(" pm.procName as  procName , pm.id.icdProcCode as icdProcCode, cast(ec.caseTherapyId as string) as seqNo," );
			query.append("  ec.docName as docName,ec.docRegNum as docReg,ec.docQual as docQual,ec.docMobileNo as docMobNo,ec.procUnits as opProcUnits");
			//query.append(" z.title||' '||z.splstName as docName ");
			query.append( " ,pm.hospstayAmt as hospStayAmt , pm.commonCatAmt as commonCatAmt , pm.icdAmt as icdAmt " );
			query.append("  ,ec.splInvRemarks as  investRemarks , cast(pm.noOfDays as string) as days ");
			query.append(" from EhfCaseTherapy ec , EhfmTherapyCatMst cm  , EhfmTherapyProcMst pm , EhfmSpecialities am ,EhfCase efc ");
			//query.append(" EhfmSplstDctrs z  ");
			query.append(" where ec.caseId ='"+caseId+"' and ec.asriCatCode = cm.id.asriCatCode and ec.icdCatCode = cm.id.icdCatCode ");
			//query.append(" and z.id.regNum=ec.docRegNum ");
			query.append(" and ec.asriCatCode =pm.id.asriCode and ec.icdProcCode = pm.id.icdProcCode and ec.activeyn = 'Y' ");
			query.append(" and ec.id.asriCatCode = am.disMainId ");
			query.append(" and ec.caseId = efc.caseId ");
			query.append(" and pm.id.state= efc.schemeId and pm.id.process in ('IP','DOP')  ");
			/*if(hospId!=null && !hospId.equalsIgnoreCase(""))
				query.append(" and z.id.hospId='"+hospId+"' ");*/
			lstSurgerydlts = genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
			for(PreauthVO preauthVO:lstSurgerydlts)
			{
				query = new StringBuffer();
				query.append(" select ei.investigationId as therapyId, ti.investDesc as filename, cast(ei.sno as string) as filePath,cast(ei.attachTotalPath as string) as ipOpFlag ");
				query.append(" from EhfCaseTherapyInvest ei , EhfmTherapyInvest ti , EhfCaseTherapy ec , EhfCase a   where ei.caseId  ='"+caseId+"' and ei.icdProcCode = '"+preauthVO.getIcdProcCode()+"'   ");
				query.append(" and ei.investigationId =ti.id.investigationId  and ei.activeYN = 'Y' and ti.id.ICDProcCode = '"+preauthVO.getIcdProcCode()+"'  ");
				query.append(" and ei.caseId=ec.caseId and ec.asriCatCode=ti.id.asriSpec and ti.id.ICDProcCode=ec.icdProcCode ");
				query.append(" and a.caseId = ec.caseId and a.schemeId = ti.id.schemeId ");
				List<PreauthVO> lstsplInvest = genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
				preauthVO.setLstSplInvet(lstsplInvest);
			}
			
		}catch(Exception e)
		{
		e.printStackTrace();	
		}
		return lstSurgerydlts;
	}
	@Override
	public List<LabelBean> getSymptomsDtls(String lStrCaseId) {
		List<LabelBean> symptomList = new ArrayList<LabelBean>();
		try{
			StringBuffer query = new StringBuffer();
			/*query.append("select ESF.mainSymptomName as ID , ESF.subSymptomName  as SUBID , ESF.symptomName as VALUE , " );
			query.append(" ESD.mainSymptomCode as UNITID, ESD.subSymptomCode as DSGNID, ESD.id.symptomCode as LEVELID ");
			query.append("	from EhfSymtematicExamDtls ESD,EhfmSystematicExamFnd ESF where ESF.id.symptomCode = ESD.id.symptomCode ");
			query.append(" and ESD.id.caseId='"+lStrCaseId+"'");*/
			
	
		    /*Query changed for TG OP Changes by venkatesh*/
			
			query.append("select case when ESD.main_symptom_code  like '%OS%'  then ESD.other_symptom_name else  ESF.main_Symptom_Name end  as ID , nvl(ESF.sub_Symptom_Name,'Others')  as SUBID , nvl(ESF.symptom_Name,'Others') as VALUE , " );
			query.append(" nvl(ESF.main_Symptom_Code,'Others') as UNITID, ESD.main_Symptom_Code as WFTYPE,nvl(ESD.sub_Symptom_Code,'Others') as DSGNID, nvl(ESD.symptom_Code,'Others') as LEVELID ");
			query.append("	from Ehf_Symtematic_Exam_Dtls ESD,Ehfm_Systematic_Exam_Fnd ESF where ESF.symptom_Code(+) = ESD.symptom_Code ");
			query.append(" and ESD.case_Id='"+lStrCaseId+"'");
			
			symptomList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return symptomList;
	}
	@Override
	public PreauthVO getPatientOpDtls(String pStrCaseId,String pStrPatientId)
	{
		PreauthVO preauthVO = new PreauthVO(); 	
		StringBuffer query = new StringBuffer();
		try
		{
			/**
			 * query to get registration details
			 */
			query.append(" select distinct ep.patientIpop as patientType, ep.name as patientName , cast(ep.age as string) as age ,ep.gender as gender , ep.cardNo as rationCard , ep.patientIpopNo as patientIPNo ,ep.newBornBaby as newBornBaby ,ep.schemeId as scheme, ");
			query.append(" case when ep.srcRegistration='D' then 'Direct' else case when ep.srcRegistration='MC'  then 'Medical' else case when ep.srcRegistration='P' then 'PHC' end end end  as srcRegistration, ");
			query.append(" ep.pinCode as contactNo,ep.cardIssueDt as cardIssueDt,ep.cardNo as refCardNo , ep.occupationCd as occName , rm.relationName as relationName  , cast(ep.contactNo as string) as contactNo ,  ");	
			//query.append(" ep.houseNo as houseNo , ep.street as street  ,lm.locName as district ,lm1.locName as mandal , lm2.locName as village ");	
			// card address
			query.append(" ep.houseNo as cardHNo , ep.street as cardStreet  ,lm.locName as cardDistrict ,lm1.locName as cardMandal , lm2.locName as cardVillage, ep.crtUsr as cruUsr ");
			query.append("   , s1.id.symptomCode as symCode ,s1.symptomName as symName ,s2.id.mainSymptomCode as mainSymCode ,s2.mainSymptomName as mainSymName  ");	
			
			// get communication details
			
			query.append(" ,l4.locName as district , l5.locName  as mandal , l6.locName as village ");
			query.append(" , ep.cHouseNo as houseNo , ep.cStreet as street , ep.cPinCode as pincode ");
			query.append(" , eh.hospName as hospitalName, l7.locName as hospDistrict ");
			
			query.append(" from EhfPatient ep , EhfmRelationMst rm , " );//AsrimCombo ac ,, EhfmOccupationMst om 
			query.append(" EhfmLocations lm ,EhfmLocations lm1 , EhfmLocations lm2 ,EhfmSystematicExamFnd s1 ,EhfmSystematicExamFnd s2 ,EhfPatientHospDiagnosis hd  "); //
			query.append("  , EhfmLocations l4, EhfmLocations l5 , EhfmLocations l6  "); //,EhfEnrollment ee , EhfEnrollmentFamily ef 
			query.append("  , EhfmLocations    l7, EhfmHospitals    eh  "); //,EhfEnrollment ee , EhfEnrollmentFamily ef 
			
			query.append("	where ep.patientId = '"+pStrPatientId+"'   ");	
			query.append(" and ep.districtCode = lm.id.locId and ep.mandalCode =lm1.id.locId and ep.villageCode = lm2.id.locId ");
			query.append(" and ep.relation = rm.relationId  "); //and ac.id.cmbDtlId =ep.caste
			query.append(" and s1.id.symptomCode =hd.symptomCode and s2.id.mainSymptomCode =hd.mainSymptomCode  and hd.patientId =ep.patientId ");
			query.append(" and ep.cDistrictCode = l4.id.locId  "); //ef.ehfCardNo = ep.cardNo and ef.enrollPrntId = ee.enrollPrntId and 
			query.append(" and ep.cMandalCode = l5.id.locId and ep.cVillageCode = l6.id.locId  ");
			query.append(" and eh.hospId= ep.regHospId and (l7.id.locId= eh.hospDist or l7.id.locId= eh.oldHospDistCode)  ");
			
			List<PreauthVO> lstPreauthVO = genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
	if(lstPreauthVO != null && lstPreauthVO.size() > 0)
	{
		preauthVO  = lstPreauthVO.get(0); 	// assigning registration details to vo
	}
	if(preauthVO.getCruUsr()!=null && !"".equalsIgnoreCase(preauthVO.getCruUsr()))
		preauthVO.setCruUsr(getEmpNameById(preauthVO.getCruUsr()));
	/**
	 * query to get Admission Details 
	 */
	  
	  EhfPatientHospDiagnosis ehfPatientHospDiagnosis = genericDao.findById(EhfPatientHospDiagnosis.class, String.class, pStrPatientId);
	if(ehfPatientHospDiagnosis != null)
	{
		preauthVO.setPresentIllness(ehfPatientHospDiagnosis.getHistoryIllness());	
		if(ehfPatientHospDiagnosis.getDoctId()!=null && !"".equalsIgnoreCase(ehfPatientHospDiagnosis.getDoctId()))
		{
			if(preauthVO.getPatientType()!=null && !"".equalsIgnoreCase(preauthVO.getPatientType()) && "IP".equalsIgnoreCase(preauthVO.getPatientType()))
				preauthVO.setDocName(getEmpNameById(ehfPatientHospDiagnosis.getDoctId()));
			else if(preauthVO.getPatientType()!=null && !"".equalsIgnoreCase(preauthVO.getPatientType()) && "OP".equalsIgnoreCase(preauthVO.getPatientType()))
				preauthVO.setDocName(getDoctorById(ehfPatientHospDiagnosis.getDoctId()));
		}
		else
			preauthVO.setDocName("-NA-");
		preauthVO.setDocQual(ehfPatientHospDiagnosis.getDoctQuali());
		preauthVO.setDocMobNo(ehfPatientHospDiagnosis.getDoctMobNo());
		preauthVO.setDocReg(ehfPatientHospDiagnosis.getDoctRegNo());;
		preauthVO.setDocType(ehfPatientHospDiagnosis.getDoctType());
		preauthVO.setPresentIllness(ehfPatientHospDiagnosis.getHistoryIllness());
		preauthVO.setRemarks(ehfPatientHospDiagnosis.getIpCaseRemarks());
		String complaintCode = ehfPatientHospDiagnosis.getComplaintType();
		String patComplaint = ehfPatientHospDiagnosis.getPatientComplaint();
			
		StringTokenizer str = null;
		StringTokenizer str1 = null;
		if(patComplaint!=null && !"".equalsIgnoreCase(patComplaint))
			str = new StringTokenizer(patComplaint,"~");
		if(complaintCode!=null && !"".equalsIgnoreCase(complaintCode))
			str1 = new StringTokenizer(complaintCode,"~");
	//EhfmSymptomsMst ehfmSymptomsMst = genericDao.findById(EhfmSymptomsMst.class, String.class, complaintCode);
		StringBuffer query1=null;
		if(str1!=null)
		{
			while(str1.hasMoreTokens())
			{
				String code=str1.nextToken();
				query1 = new StringBuffer();
				query1.append(" select distinct ecm.id.mainComplCode as ID,ecm.mainComplName as VALUE from EhfmComplaintMst ecm where ecm.id.mainComplCode='"+code+"'");
				List<LabelBean> ehfComplaintList = genericDao.executeHQLQueryList(LabelBean.class, query1.toString());
				if(ehfComplaintList!=null && !ehfComplaintList.isEmpty())
				{
					if(preauthVO.getComplaintType() == null || preauthVO.getComplaintType().equalsIgnoreCase(""))
					{
						//preauthVO.setPatComplaint(ehfmSymptomsMst.getSymptomName() + "( "+ehfmSymptomsMst.getSymptomId()+" )")	;	
						preauthVO.setComplaintType(ehfComplaintList.get(0).getVALUE() + "( "+ehfComplaintList.get(0).getID()+" )")	;
					}
					else
					{
						//preauthVO.setPatComplaint( preauthVO.getPatComplaint() +" , "+ ehfmSymptomsMst.getSymptomName() + "( "+ehfmSymptomsMst.getSymptomId()+" )")	;
						preauthVO.setComplaintType( preauthVO.getComplaintType() +" , "+ ehfComplaintList.get(0).getVALUE() + "( "+ehfComplaintList.get(0).getID()+" )")	;
					}
					
				}
			}
		}
	preauthVO.setComplaintCode(complaintCode);
	if(str!=null)
	{
		while(str.hasMoreTokens())
		{
			query1 = new StringBuffer();
			query1.append(" select ecm.id.complCode as ID,ecm.complName as VALUE from EhfmComplaintMst ecm where ecm.id.complCode='"+str.nextToken()+"'");
			List<LabelBean> ehfComplList = genericDao.executeHQLQueryList(LabelBean.class, query1.toString());
			//ehfmSymptomsMst = genericDao.findById(EhfmSymptomsMst.class, String.class, str.nextToken());	
			if(ehfComplList != null && ehfComplList.size()>0)
			{
				if(preauthVO.getPatComplaint() == null || preauthVO.getPatComplaint().equalsIgnoreCase(""))
				{
					//preauthVO.setPatComplaint(ehfmSymptomsMst.getSymptomName() + "( "+ehfmSymptomsMst.getSymptomId()+" )")	;	
					preauthVO.setPatComplaint(ehfComplList.get(0).getVALUE() + "( "+ehfComplList.get(0).getID()+" )")	;
				}
				else
				{
					//preauthVO.setPatComplaint( preauthVO.getPatComplaint() +" , "+ ehfmSymptomsMst.getSymptomName() + "( "+ehfmSymptomsMst.getSymptomId()+" )")	;
					preauthVO.setPatComplaint( preauthVO.getPatComplaint() +" , "+ ehfComplList.get(0).getVALUE() + "( "+ehfComplList.get(0).getID()+" )")	;
				}
				
			}
		} // setting list of patient complaints
	}
	
	/**
	 * get past illness
	 */	
	
	if(ehfPatientHospDiagnosis.getOtherComplaintName()!=null && !("").equalsIgnoreCase(ehfPatientHospDiagnosis.getOtherComplaintName()))
	{
		preauthVO.setPatComplaint( preauthVO.getPatComplaint() +" , "+ehfPatientHospDiagnosis.getOtherComplaintName());	
	}
	preauthVO.setPastIllness(ehfPatientHospDiagnosis.getPastHistory());
	preauthVO.setPastIllenesCancers(ehfPatientHospDiagnosis.getPastHistoryCancers());
	preauthVO.setPastIllenesEndDis(ehfPatientHospDiagnosis.getPastHistoryEndDis());
	preauthVO.setPastIllenesSurg(ehfPatientHospDiagnosis.getPastHistorySurg());
	if(ehfPatientHospDiagnosis.getPastHistoryOthr()!=null && !ehfPatientHospDiagnosis.getPastHistoryOthr().equalsIgnoreCase(""))
		preauthVO.setPastIllenesOthr(ehfPatientHospDiagnosis.getPastHistoryOthr());
	else
		preauthVO.setPastIllenesOthr("Past Illness- Others not found");
	/**
	 * get medco legal case details
	 */
	preauthVO.setLegalCaseCheck(ehfPatientHospDiagnosis.getLegalCaseCheck());
	preauthVO.setLegalCaseNo(ehfPatientHospDiagnosis.getLegalCaseNo());
	preauthVO.setPoliceStatName(ehfPatientHospDiagnosis.getPoliceStatName());
	
	/**
	 * get past history values
	 */
	String pastHist=ehfPatientHospDiagnosis.getPastHistory();
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

				if(preauthVO.getPastIllnessValue() == null || preauthVO.getPastIllnessValue().equalsIgnoreCase(""))
				{	
					preauthVO.setPastIllnessValue(ehfComplList.get(0).getID());
				}
				else
				{
					preauthVO.setPastIllnessValue( preauthVO.getPastIllnessValue() +" , "+ehfComplList.get(0).getID())	;
				}
				String concatRemarks="";
				if(disCode.equalsIgnoreCase("PH8"))
				{
					concatRemarks=ehfPatientHospDiagnosis.getPastHistoryCancers();
					preauthVO.setPastIllnessValue( preauthVO.getPastIllnessValue() +" (" + concatRemarks + ") ");
				}
				if(disCode.equalsIgnoreCase("PH10"))
				{
					concatRemarks=ehfPatientHospDiagnosis.getPastHistorySurg();
					preauthVO.setPastIllnessValue( preauthVO.getPastIllnessValue() +" (" + concatRemarks + ") ");
				}
				if(disCode.equalsIgnoreCase("PH12"))
				{
					concatRemarks=ehfPatientHospDiagnosis.getPastHistoryEndDis();
					preauthVO.setPastIllnessValue( preauthVO.getPastIllnessValue() +" (" + concatRemarks + ") ");
				}
				
			}
		}
	}
	}
	else
		preauthVO.setPastIllnessValue("Past Illness not found");
	/**
	 * get personal History
	 */
	
	preauthVO.setPersonalHis( ehfPatientHospDiagnosis.getPersonalHistory());
	String lDesc=null;
	List<LabelBean> personalHisList=new ArrayList();
	List<LabelBean> personalHis=new ArrayList();
	
	List<EhfmPersonalHistoryMst> parentList=genericDao.findAllByPropertyMatch(EhfmPersonalHistoryMst.class,"parntCode","PR");
	if(parentList!=null && !parentList.isEmpty())
	{
		
		for(EhfmPersonalHistoryMst ehfmPersonalHistoryMst1:parentList)
		{
			LabelBean lstPersonal = new LabelBean();
			lstPersonal.setID(ehfmPersonalHistoryMst1.getCode());
			lstPersonal.setVALUE(ehfmPersonalHistoryMst1.getDescription());
			List<EhfmPersonalHistoryMst> childList=genericDao.findAllByPropertyMatch(EhfmPersonalHistoryMst.class,"parntCode",ehfmPersonalHistoryMst1.getCode());
			List<LabelBean> personalsub=new ArrayList();
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
	preauthVO.setLstPersonalHistory(personalHis);
	
	EhfPatientPersonalHistory ehfPatientPersonalHistory= genericDao.findById(EhfPatientPersonalHistory.class, String.class, pStrPatientId);
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
				preauthVO.setTestKnown(ehfPatientPersonalHistory.getKnownAllergies());
		}
		if(ehfPatientPersonalHistory.getAddictions() != null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getAddictions());	
			if(ehfPatientPersonalHistory.getAddictions().contains("PR6.1"))
			preauthVO.setTest(ehfPatientPersonalHistory.getAddictions());
		}
		if(ehfPatientPersonalHistory.getMaritalStatus() != null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getMaritalStatus());	
		}
		if(ehfPatientPersonalHistory.getDrugHst() != null)
		{
			preauthVO.setDrugHst(ehfPatientPersonalHistory.getDrugHst());	
		}
		if(ehfPatientPersonalHistory.getDrugHstVal() != null)
		{
			preauthVO.setDrugHstVal(ehfPatientPersonalHistory.getDrugHstVal());	
		}
		else
			preauthVO.setDrugHstVal("Drug history not avialable");
	}
	preauthVO.setLstPerHis(lstPerHis);
	/**
	 * get family history
	 */		
	if(ehfPatientHospDiagnosis.getFamilyHistory()!=null && !ehfPatientHospDiagnosis.getFamilyHistory().equalsIgnoreCase("")){
		preauthVO.setFamilyHis(ehfPatientHospDiagnosis.getFamilyHistory());
	}//end of ehfPatientHospDiagnosis!=null
	
	if(ehfPatientHospDiagnosis.getFamilyHistoryOthr()!=null && !ehfPatientHospDiagnosis.getFamilyHistoryOthr().equalsIgnoreCase(""))
		preauthVO.setFamilyHistoryOthr(ehfPatientHospDiagnosis.getFamilyHistoryOthr());
	else
		preauthVO.setFamilyHistoryOthr("Family History -Others not found");
	
	
	
	/**
	 * get family history values
	 */
	String famhist=ehfPatientHospDiagnosis.getFamilyHistory();
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
				if(preauthVO.getFamilyHistValue() == null || preauthVO.getFamilyHistValue().equalsIgnoreCase(""))
				{	
					preauthVO.setFamilyHistValue(ehfComplList.get(0).getID());
				}
				else
				{
					preauthVO.setFamilyHistValue( preauthVO.getFamilyHistValue() +" , "+ehfComplList.get(0).getID())	;
				}
				
			}
		}
	}
	}
	else
		preauthVO.setFamilyHistValue("Family History not found");
	}//end of ehfPatientHospDiagnosis!=null
	/**
	 * get Examination findings
	 */
	EhfPatientExamFind ehfPatientExamFind = genericDao.findById(EhfPatientExamFind.class, String.class, pStrPatientId);
	if(ehfPatientExamFind != null)
	{
		String schemeId=preauthVO.getScheme();
		if(ehfPatientExamFind.getBmi()!=null && !ehfPatientExamFind.getBmi().equalsIgnoreCase(""))
		preauthVO.setBmi(ehfPatientExamFind.getBmi());
		else
		preauthVO.setBmi("NA");
		if(ehfPatientExamFind.getHeight()!=null && !ehfPatientExamFind.getHeight().equalsIgnoreCase(""))
		preauthVO.setHeight(ehfPatientExamFind.getHeight());
		else
		preauthVO.setHeight("NA");
		if(ehfPatientExamFind.getWeight()!=null && !ehfPatientExamFind.getWeight().equalsIgnoreCase(""))
		preauthVO.setWeight(ehfPatientExamFind.getWeight());
		else
		preauthVO.setWeight("NA");
		preauthVO.setPallor(ehfPatientExamFind.getPallor());
		preauthVO.setCyanosis(ehfPatientExamFind.getCyanosis());
		preauthVO.setClubbingOfFingers(ehfPatientExamFind.getClubOfFingrToes());
		preauthVO.setLymphadenopathy(ehfPatientExamFind.getLymphadenopathy());
		preauthVO.setEdema(ehfPatientExamFind.getOedemaInFeet());
		preauthVO.setMalNutrition(ehfPatientExamFind.getMalnutrition());
		if(ehfPatientExamFind.getDehydration() != null && ehfPatientExamFind.getDehydration().equalsIgnoreCase("N"))
		preauthVO.setDehydration(ehfPatientExamFind.getDehydration());
		else if(ehfPatientExamFind.getDehydration() != null &&  !ehfPatientExamFind.getDehydration().equals("") && ehfPatientExamFind.getDehydration().contains("Y"))
		{
				preauthVO.setDehydration(ehfPatientExamFind.getDehydration().charAt(0)+"");
				preauthVO.setDehydrationType(ehfPatientExamFind.getDehydration().substring(1));
		}
		if(ehfPatientExamFind.getTemperature()!=null && !ehfPatientExamFind.getTemperature().equalsIgnoreCase(""))
		preauthVO.setTemperature(ehfPatientExamFind.getTemperature());
		else
		preauthVO.setTemperature("NA");	
		if(ehfPatientExamFind.getPluseRate()!=null && !ehfPatientExamFind.getPluseRate().equalsIgnoreCase(""))
		preauthVO.setPulseRate(ehfPatientExamFind.getPluseRate());
		else
		preauthVO.setPulseRate("NA");
		if(ehfPatientExamFind.getRespirationRate()!=null && !ehfPatientExamFind.getRespirationRate().equalsIgnoreCase(""))
		preauthVO.setRespirationRate(ehfPatientExamFind.getRespirationRate());
		else
			preauthVO.setRespirationRate("NA");	
		if(ehfPatientExamFind.getBpLt()!=null && !ehfPatientExamFind.getBpLt().equalsIgnoreCase(""))
		preauthVO.setBpLmt(ehfPatientExamFind.getBpLt());
		else
			preauthVO.setBpLmt("NA");
		if(ehfPatientExamFind.getBpRt()!=null && !ehfPatientExamFind.getBpRt().equalsIgnoreCase(""))
		preauthVO.setBpRmt(ehfPatientExamFind.getBpRt());
		else if(schemeId!=null && (config.getString("TG")).equalsIgnoreCase(schemeId) && ehfPatientExamFind.getBpRt()!=null && ehfPatientExamFind.getBpRt().equalsIgnoreCase("/") )
			preauthVO.setBpRmt("--");
		else
			preauthVO.setBpRmt("NA");
	}
	
	/**
	 * get system Examination findings
	 */
	
	//genericDao.findById(ehfm_systematic_exam_fnd, idClass, id);
	

	/**
	 * Investigation Details--wm_concate(gim.invName );
	 */
	query=new StringBuffer();
	query.append("select gim.invName as investRemarks from EhfmGenInvestigationsMst gim,EhfPatientTests pt"); 
			query.append("      where pt.testId=gim.invCode and pt.patientId='"+pStrPatientId+"' ");
	List<PreauthVO> list1=genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
	if(list1!=null && !list1.isEmpty())
	{
		for(PreauthVO preauthVO1:list1)
		{
			if(preauthVO.getInvestRemarks() == null || preauthVO.getInvestRemarks().equalsIgnoreCase(""))
			{
				preauthVO.setInvestRemarks(preauthVO1.getInvestRemarks());	
			}
			else
			{
				preauthVO.setInvestRemarks(preauthVO.getInvestRemarks()+" , "+preauthVO1.getInvestRemarks());	
			}			
		}
	}
	
	/**
	 * Main Signs and Symptoms
	 */
	query=new StringBuffer();
	query.append( " select case when other_symptom_name is not null then other_symptom_name else e.symptom_name end as SYMPTOMS ");
	query.append( " from EHFM_SYSTEMATIC_EXAM_FND e,Ehf_Symtematic_Exam_Dtls d where  e.symptom_code(+) = d.symptom_code and d.case_id='"+pStrCaseId+"' ");
	
	List<PreauthVO> list2=genericDao.executeSQLQueryList(PreauthVO.class, query.toString());
	if(list2!=null && !list2.isEmpty())
	{
		for(PreauthVO preauthVO1:list2)
		{
			if(preauthVO.getSymName() == null || preauthVO.getSymName().equalsIgnoreCase(""))
			{
				preauthVO.setSymName(preauthVO1.getSYMPTOMS());	
			}
			else
			{
				preauthVO.setSymName(preauthVO.getSymName()+" , "+preauthVO1.getSYMPTOMS());	
			}
		}
	}
	else
	{
		preauthVO.setSymName("NA");
	}
	/**
	 * Medical History and others
	 **/
	StringBuffer query2 = new StringBuffer();
	StringBuffer query3 = new StringBuffer();
	EhfDentalOtherExaminations ehfDentalOtherExaminations = genericDao.findById(EhfDentalOtherExaminations.class, String.class, pStrPatientId);
		if(ehfDentalOtherExaminations!=null){
			if(ehfDentalOtherExaminations.getMedicalHistoryOthr()!=null && ehfDentalOtherExaminations.getMedicalHistoryOthr()!=""){
				preauthVO.setMedicalHistoryOthr(ehfDentalOtherExaminations.getMedicalHistoryOthr());
				preauthVO.setPreviousDentalTreatment(ehfDentalOtherExaminations.getPreviousDentalTreatment());
				String OcclussionTypeId=ehfDentalOtherExaminations.getOcclusionDivii();
				String OcclusionId=ehfDentalOtherExaminations.getOcclusion();
				if(ehfDentalOtherExaminations.getOcclusion().equalsIgnoreCase("CH83")){
				preauthVO.setOcclusionTypeTxt(ehfDentalOtherExaminations.getOcclusionOther());
				}
				if(OcclussionTypeId!=null && !OcclussionTypeId.equalsIgnoreCase("") && !ehfDentalOtherExaminations.getOcclusion().equalsIgnoreCase("CH83")){
					query2.append(" select labelNAME as ID from EhfmDentalCmbDtls where id.labelID='"+OcclussionTypeId+"'");
					List<LabelBean> ehfOccType = genericDao.executeHQLQueryList(LabelBean.class, query2.toString());
					if(ehfOccType != null && ehfOccType.size()>0)
					{
						preauthVO.setOcclusionTypeTxt(ehfOccType.get(0).getID());
					}
				}
				if(OcclusionId!=null && !OcclusionId.equalsIgnoreCase("")){
					query3.append(" select labelNAME as ID from EhfmDentalCmbDtls where id.labelID='"+OcclusionId+"'");
					List<LabelBean> ehfOcc = genericDao.executeHQLQueryList(LabelBean.class, query3.toString());
					if(ehfOcc != null && ehfOcc.size()>0)
					{
						preauthVO.setOcclusionTxt(ehfOcc.get(0).getID());
					}
				}
				
			}
			
			String medDet=ehfDentalOtherExaminations.getMedicalHistory();
			if(medDet!=null && !medDet.equalsIgnoreCase("")){
				StringTokenizer str1 = new StringTokenizer(medDet,"~");
				
				while(str1.hasMoreTokens())
				{
					query = new StringBuffer();
					String code=str1.nextToken();
					if(code!=null && !code.equalsIgnoreCase("CH117"))
					{
					query.append(" select labelNAME as ID from EhfmDentalCmbDtls where id.labelID='"+code+"'");
					List<LabelBean> ehfMedHist = genericDao.executeHQLQueryList(LabelBean.class, query.toString());					
					
					if(ehfMedHist != null && ehfMedHist.size()>0)
					{
						if(preauthVO.getMedHistVal() == null || preauthVO.getMedHistVal().equalsIgnoreCase(""))
						{	
							preauthVO.setMedHistVal(ehfMedHist.get(0).getID());
						}
						else
						{
							preauthVO.setMedHistVal( preauthVO.getMedHistVal() +" , "+ehfMedHist.get(0).getID())	;
						}
						
					}
					
					}
				}
			}
			
		}
		/** 
		 * Extra oral Examin
		 **/
		
		EhfDentalOralExaminations ehfDentalOralExaminations = genericDao.findById(EhfDentalOralExaminations.class, String.class, pStrPatientId);
		if(ehfDentalOralExaminations!=null){
			
			String LymphId=ehfDentalOralExaminations.getLymphadenopathy();
			String jawsId=ehfDentalOralExaminations.getJaws();
			String tmjId=ehfDentalOralExaminations.getTmj();
			String faceId=ehfDentalOralExaminations.getFace();
			String LymphSub=ehfDentalOralExaminations.getLymphadenopathySub();
			String jawsSub=ehfDentalOralExaminations.getJawsSub();
			String Lymph1="";
			String Jaws1="";
			String Tmj1="";
			String Face1="";
			String SubLymphSub="";
			String SubJawsSub="";
			query = new StringBuffer();
			query.append(" select labelNAME as ID,labelPrntID as VALUE from EhfmDentalCmbDtls where id.labelID in ('"+LymphId+"','"+jawsId+"','"+tmjId+"','"+faceId+"')");
			List<LabelBean> ehfLymph = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
			if(ehfLymph != null && ehfLymph.size()>0)
			{
			for (int i=0;i<ehfLymph.size();i++){
			if(ehfLymph.get(i).getVALUE().equalsIgnoreCase("CH4")){
				Lymph1=ehfLymph.get(i).getID();
			}
			if(ehfLymph.get(i).getVALUE().equalsIgnoreCase("CH3")){
				Jaws1=ehfLymph.get(i).getID();
			}
			if(ehfLymph.get(i).getVALUE().equalsIgnoreCase("CH2")){
				Tmj1=ehfLymph.get(i).getID();
			}
			if(ehfLymph.get(i).getVALUE().equalsIgnoreCase("CH1")){
				Face1=ehfLymph.get(i).getID();
			}
			}
			preauthVO.setRegionalLymphadenopathyDtrsSub(Lymph1);
			preauthVO.setJawsDtrsSub(Jaws1);
			preauthVO.setTmjDtrsSub(Tmj1);
			preauthVO.setFaceDtrsSub(Face1);
			
		if((Lymph1 != null && !"".equalsIgnoreCase(Lymph1)))
		{
			if( (!Lymph1.equalsIgnoreCase("Other Lymph nodes") && !Lymph1.equalsIgnoreCase("NAD ( No Abnormality detected )")))
			{
				StringTokenizer str1 = new StringTokenizer(LymphSub,"~");
				while(str1.hasMoreTokens())
				{
					query = new StringBuffer();
					String code=str1.nextToken();
					query.append(" select labelNAME as ID from EhfmDentalCmbDtls where id.labelID='"+code+"'");
					List<LabelBean> ehfSubLymphSub = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
					
					
					if(ehfSubLymphSub != null && ehfSubLymphSub.size()>0)
					{
						if(SubLymphSub == null || SubLymphSub.equalsIgnoreCase(""))
						{	
							SubLymphSub=ehfSubLymphSub.get(0).getID();
						}
						else
						{
							SubLymphSub=SubLymphSub+","+ehfSubLymphSub.get(0).getID();
						}
						
					}
					
				}
				
				}
				else if(Lymph1.equalsIgnoreCase("Other Lymph nodes"))
				{
					SubLymphSub=LymphSub;
				}
			}
			preauthVO.setRegionalLymphadenopathyDtrsTxt(SubLymphSub);
			if((Jaws1 != null && !"".equalsIgnoreCase(Jaws1)))
			{
				if(!Jaws1.equalsIgnoreCase("NAD ( No Abnormality detected )") && !Jaws1.equalsIgnoreCase("Growth /swelling in mandible") && !Jaws1.equalsIgnoreCase("Growth /swelling in maxilla") && !Jaws1.equalsIgnoreCase("Others"))
				{
					StringTokenizer str1 = new StringTokenizer(jawsSub,"~");
					while(str1.hasMoreTokens())
					{
						query = new StringBuffer();
						String code=str1.nextToken();
						query.append(" select labelNAME as ID from EhfmDentalCmbDtls where id.labelID='"+code+"'");
						List<LabelBean> ehfSubJawsSub = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
						
						
						if(ehfSubJawsSub != null && ehfSubJawsSub.size()>0)
						{
							if(SubJawsSub == null || SubJawsSub.equalsIgnoreCase(""))
							{	
								SubJawsSub=ehfSubJawsSub.get(0).getID();
							}
							else
							{
								SubJawsSub=SubJawsSub+","+ehfSubJawsSub.get(0).getID();
							}
							
						}
						
					}
				
					
				}
			
				else if(Jaws1.equalsIgnoreCase("Growth /swelling in mandible") || Jaws1.equalsIgnoreCase("Growth /swelling in maxilla") || Jaws1.equalsIgnoreCase("Others"))
				{
								SubJawsSub=jawsSub;
				}
			}
			preauthVO.setJawsDtrsTxt(SubJawsSub);
//			preauthVO.setRegionalLymphadenopathyDtrsTxt(SubLymphSub);
			}
			
			
			/**
			 * Intra Oral Examins
			 */
			preauthVO.setDntsublistoral0(ehfDentalOralExaminations.getHardPalate());
			preauthVO.setDntsublistoral1(ehfDentalOralExaminations.getSoftPalate());
			preauthVO.setDntsublistoral2(ehfDentalOralExaminations.getFloorMouth());
			preauthVO.setDntsublistoral3(ehfDentalOralExaminations.getTongue());
			preauthVO.setDntsublistoral4(ehfDentalOralExaminations.getFrenalAttachment());
			preauthVO.setDntsublistoral5(ehfDentalOralExaminations.getBuccalMucosa());
			preauthVO.setDntsublistoral6(ehfDentalOralExaminations.getGingiva());
			preauthVO.setSwSite(ehfDentalOralExaminations.getSwSite());
			preauthVO.setSwSize(ehfDentalOralExaminations.getSwSize());
			preauthVO.setSwExtension(ehfDentalOralExaminations.getSwExtension());
			preauthVO.setSwColour(ehfDentalOralExaminations.getSwColour());
			preauthVO.setSwConsistency(ehfDentalOralExaminations.getSwConsistency());
			preauthVO.setSwTenderness(ehfDentalOralExaminations.getSwTenderness());
			preauthVO.setSwBorders(ehfDentalOralExaminations.getSwBorders());
			preauthVO.setPsSite(ehfDentalOralExaminations.getPsSite());
			preauthVO.setPsDischarge(ehfDentalOralExaminations.getPsDischarge());
			
			
			
		}
		}catch(Exception e)
		{
		e.printStackTrace();	
		}		
		return preauthVO;
	}
	@Override
	public CommonDtlsVO getOtherDtls(String pStrCaseId,String pStrPatId) {
		String query="";
		CommonDtlsVO userData=new CommonDtlsVO();;
		try
		{
			/**
			 * Fetching habbits and allergies
			 */
			EhfPatientPersonalHistory ehfPatientPersonalHistory=genericDao.findById(EhfPatientPersonalHistory.class, String.class, pStrPatId);
			if(ehfPatientPersonalHistory!=null)
			{
				userData.setAllergy(ehfPatientPersonalHistory.getKnownAllergies());
				userData.setHabbits(ehfPatientPersonalHistory.getAddictions());
			}
			
			/**
			 * Fetching investigations 
			 */
			query="select  nvl(gim.inv_Main_Name ,'Others')  as TEST,case  when pt.test_id like '%OI%' then pt.test_name else   gim.inv_Name end as NAME, pt.attach_Path as ROUTE from Ehfm_Gen_Investigations_Mst gim,Ehf_Patient_Tests pt "; 
			query+=" where pt.test_Id=gim.inv_Code(+) and pt.patient_Id='"+pStrPatId+"' ";
			List<PreauthVO> list1=genericDao.executeSQLQueryList(PreauthVO.class, query.toString());
			userData.setInvestigations(list1);
			/**
			 * Fetching Diagnosis
			 */
			query="select dm.diagnosisName as diagnosisType,d.diagnosisType as refCardNo, mm.mainCatName as mainCatName, d.mainCatCode as occName, cm.catName as catName, d.categoryCode as relationName "+
					", sm.subCatName as subCatName,d.subCatCode as houseNo, ddm.diseaseName as disName, d.diseaseCode as street, ddam.disAnatomicalName as disAnatomicalSitename, d.diseaseAnatCode as hamlet,d.otherDiagnosisName as otherDiagName "+ 
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
				userData.setOtherDiagName(dg.getOtherDiagName());
				userData.setADMTYPE(dg.getHamlet());
			}
			
			EhfPatientHospDiagnosis ehfPatientHospDiagnosis=new EhfPatientHospDiagnosis();
			ehfPatientHospDiagnosis=genericDao.findById(EhfPatientHospDiagnosis.class,String.class,pStrPatId);
			if(ehfPatientHospDiagnosis!=null)
				userData.setOtherDiagName(ehfPatientHospDiagnosis.getOtherDiagnosisName());
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return userData;
	}
	@Override
	public String getEmpNameById(String id)
	{
		String q="select firstName||' '||lastName as ID from EhfmUsers where userId='"+id+"'";
		String user="";
		try
		{
			List<LabelBean> list=genericDao.executeHQLQueryList(LabelBean.class, q);
			if(list!=null && !list.isEmpty())
			{
				user=list.get(0).getID();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public String getDoctorById(String id) {
		String q="select distinct title||' '||splstName as ID from EhfmSplstDctrs where id.regNum='"+id+"'";
		String user="";
		try
		{
			List<LabelBean> list=genericDao.executeHQLQueryList(LabelBean.class, q);
			if(list!=null && !list.isEmpty())
			{
				user=list.get(0).getID();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public List<DrugsVO> getDrugs(String pStrPatId,String flag)
	{
		StringBuffer query = new StringBuffer();
		List<DrugsVO> drugs=null;
		try
		{
		
			
			query.append(" select EPD.drug_id as DRUGID, to_char(EPD.expiry_Date,'DD-MM-YYYY') as EXPIRYDATE,EPD.quantity as QUANTITY, nvl(EDM.MAIN_GRP_CODE,'Others') as DRUGTYPECODE,nvl(EDM.ther_main_group_code,'Others') as DRUGSUBTYPECODE,nvl(EDM.PHAR_SUB_GRP_CODE,'Others') as PSUBGRPCODE,nvl(EDM.CHE_SUB_GRP_CODE,'Others') as CSUBGRPCODE,EPD.ASRI_DRUG_CODE as DRUGCODE, ");
			query.append(" nvl(EDM.main_Grp_Name,'Others') as DRUGTYPENAME,nvl(EDM.ther_Main_Grp_Name,'Others') as DRUGSUBTYPENAME,nvl(EDM.phar_Sub_Grp_Name,'Others') as PSUBGRPNAME,nvl(EDM.che_Sub_Grp_Name,'Others') as CSUBGRPNAME," );
			
			query.append( "  case when EPD.ASRI_DRUG_CODE like '%OD%' then");
			query.append( " EPD.other_drug_name else EDM.CHEMICAL_NAME end as  DRUGNAME, ");
					
			query.append(" ER.route_Type_Code as ROUTETYPE,ER.route_Type_Name as ROUTETYPENAME,ER.route_Code as ROUTE,ER.route_Name as ROUTENAME,ES.strength_Unit_Code as STRENGTHTYPE,ES.strength_Unit_Name as STRENGTHTYPENAME ,");
			query.append(" ES.strength_Code as STRENGTH,ES.strength_Name as STRENGTHNAME,EPD.dosage as DOSAGE,EPD.medication_Period as MEDICATIONPERIOD from Ehf_Patient_Drugs EPD,Ehfm_Op_Drug_Mst EDM,Ehfm_Op_Strength_Mst ES, ");
			query.append(" Ehfm_Op_Route_Mst ER where EPD.ASRI_drug_Code=EDM.chemical_Code(+) and ER.route_Code(+)=EPD.route and ES.strength_Code(+)=EPD.strength ");
			query.append("  and EPD.patient_Id='"+pStrPatId+"' ");
			
			drugs=genericDao.executeSQLQueryList(DrugsVO.class, query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return drugs;
	}
	
	public String getHospActiveStatus(String userId, String roleId)
	{
		String actStatus="";
		StringBuffer query= new StringBuffer();
		try
		{
			if(roleId.equals(config.getString("Group.Mithra")))
			{
				query.append(" select h.hospActiveYN as ID from EhfmHospitals h, EhfmHospMithraDtls d ");
				query.append(" where d.id.mithraId=? and d.endDt is null and h.hospId= d.id.hospId ");
				String args[]= new String[1];
				args[0]= userId;
				
				List<LabelBean> resultLst= genericDao.executeHQLQueryList(LabelBean.class, query.toString(), args);
				if(resultLst!=null && resultLst.size()>0)
				{
					actStatus= resultLst.get(0).getID();
				}
			}
		}
		catch(Exception e)
		{
			GLOGGER.error("Error in method getHospActiveStatus() of PatientDaoImpl "+e.getMessage());
		}
		return actStatus;
	}
	
	//pravalika
	@Override
	public List<LabelBean> getcategoryList()
	{
		//String status = null;
		StringBuffer query = new StringBuffer();
		List<LabelBean> resList = null;  
		try
		{
				query.append(" select DIS_MAIN_ID as ID,DIS_MAIN_NAME as VALUE from EHFM_SPECIALITIES");
				
				resList= genericDao.executeSQLQueryList(LabelBean.class, query.toString());
				
		}
		catch(Exception e)
		{
			GLOGGER.error("Error in method getcategoryList() of PatientDaoImpl "+e.getMessage());
		}
		return resList;
	}
	
	@Override
	public String getProvisionDiagnosis(PatientForm patientForm,String lStrUserId){
	
	    EhfJrnlstPatientTherapy therapy = new EhfJrnlstPatientTherapy();
	    
	    EhfJrnlstdAdmEstDtls admDtls = new EhfJrnlstdAdmEstDtls();
	    
	    therapy.setPatientId(patientForm.getPatientNo());
	    therapy.setIcdCatCode(patientForm.getDiagnosisCategory());
	    therapy.setProvisionDiagnosis(patientForm.getDialysis());
	    //CR 9043 saving admission details 
	    admDtls.setPatientId(patientForm.getPatientNo());
	    if (patientForm.getAdmissionDate() != null &&
	    	    !patientForm.getAdmissionDate().trim().isEmpty()) {

	    	    try {
	    	    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    	    	Date admissionDate = sdf.parse(patientForm.getAdmissionDate() + " 00:00:00");
	    	    	admDtls.setAdmissionDate(admissionDate);

	    	    } catch (ParseException e) {
	    	        e.printStackTrace();   
	    	    }
	    	}
	    //therapy.setAdmissionDate(patientForm.getAdmissionDate());
	    admDtls.setAdmissionNote(patientForm.getAdmissionNote());
	    admDtls.setEstAmount(patientForm.getEstAmount());
	    FormFile file = patientForm.getAttachment();

	    if (file != null && file.getFileSize() > 0) {
	    	 SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
	    	 String currentDtAndTime = dateFormat.format(new Date());

	        String originalFileName = file.getFileName();
	        String uniqueFileName = currentDtAndTime + "_" +originalFileName;

	        String basePath = "/storageNAS-TS-Production/DJHS_ADM/";
	        String savedPath = basePath + uniqueFileName;

	        File folder = new File(basePath);
	        if (!folder.exists()) {
	            folder.mkdirs();
	        }
	        try {
	            FileOutputStream fos =
	                new FileOutputStream(savedPath);

	            fos.write(file.getFileData());
	            fos.close();

	            admDtls.setDocPath(savedPath);
	            admDtls.setFileName(uniqueFileName);
	            patientForm.setFileName(uniqueFileName);
	            patientForm.setSavedPath(savedPath);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    therapy.setActiveYN("Y");

	    therapy.setCrtUsr(lStrUserId);
	    therapy.setCrtDt(new Date());
	    therapy.setLstUpdUsr(lStrUserId);
	    therapy.setLstUpdDt(new Date());
	    //saving admission details
	    admDtls.setCrtUsr(lStrUserId);
	    admDtls.setCrtDt(new Date());
	    admDtls.setLstUpdUsr(lStrUserId);
	    admDtls.setLstUpdDt(new Date());
	    genericDao.save(therapy);
	    genericDao.save(admDtls);
	    
	    return "sucess";

	   // patientDao.savePatientTherapy(therapy);
	}
	
	@Override
	public List<LabelBean> getPrintDiagnosis(String patientId){
		StringBuffer query = new StringBuffer();
		List<LabelBean> resList = null;  
		try
		{
				//CR 9043 displaying admission details in print form 
				query.append("select a.ICD_CAT_CODE as ID,b.DIS_MAIN_NAME as CONST,a.PROVISIONAL_DIAGNOSIS as VALUE,c.ADM_DATE as DOB,c.ADM_NOTE as DSGNNAME, ");
				query.append("c.EST_AMT as COUNT1,c.DOC_PATH as SUBID,c.FILE_NAME as NEW_VILLAGE from EHF_JRNLSTD_PATIENT_THERAPY a, EHFM_SPECIALITIES b, ");
				query.append("EHF_JRNLSTD_ADM_EST_DTLS c WHERE a.ICD_CAT_CODE = b.DIS_MAIN_ID AND a.PATIENT_ID = c.PATIENT_ID AND a.PATIENT_ID = '"+patientId+"' ");
				resList= genericDao.executeSQLQueryList(LabelBean.class, query.toString());
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
			GLOGGER.error("Error in method getcategoryList() of PatientDaoImpl "+e.getMessage());
		}
		return resList;
	}
	
	/**
     * @param String icdProcCode
     * @return List<LabelBean> dentalUnitsList
     * @function This Method is Implementation For getting Dental Units List for given Procedure
     */
	public int getDenUnitsList(String icdProcCode)
	{
		List<LabelBean> denUnitsList=new ArrayList<LabelBean>();
		StringBuffer query= new StringBuffer();
		int dopUnits=0;
		if(icdProcCode!=null)
			{
				query.append(" select dopUnits as dopUnits from EhfmDentalOpProcedures ");
				query.append(" where id.icdProcCode='"+icdProcCode+"'");
				denUnitsList=genericDao.executeHQLQueryList(LabelBean.class, query.toString());
			}
		if(denUnitsList.size()>0)
			{
				if(denUnitsList.get(0).getDopUnits()!=0)
					{
						dopUnits=denUnitsList.get(0).getDopUnits();
					}
			}
		return dopUnits;
	}


	
	
	
	
	
	
	/*
	 * Added by Srikalyan to Verify New Born
	 * Baby Condition for Mithra based on 
	 * the scheme
	 */
	public String checkNewBornCond(String lStrUserId)
		{
			String cond="N";
			StringBuffer query=new StringBuffer();
			query.append(" select edc.hospStatus as test from EhfmHospMithraDtls ehm,EhfmEDCHospActionDtls edc ");
			query.append(" where ehm.id.mithraId='"+lStrUserId+"' and ehm.activeYN='Y' and ehm.endDt is null");
			query.append(" and ehm.id.hospId=edc.id.hospId and edc.id.scheme in ('CD202') ");
			List<PreauthVO> lst=genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
			if(lst!=null)
				{
					if(lst.size()>0)
						{
							if(lst.get(0)!=null)
								{
									if(lst.get(0).getTest()!=null)
										{
											if(!lst.get(0).getTest().equalsIgnoreCase("S")     &&
													!lst.get(0).getTest().equalsIgnoreCase("C")&&
													!lst.get(0).getTest().equalsIgnoreCase("D")&&
													!lst.get(0).getTest().equalsIgnoreCase("E")&&
													!lst.get(0).getTest().equalsIgnoreCase("SP")&&
													!lst.get(0).getTest().equalsIgnoreCase("CP")&&
													!lst.get(0).getTest().equalsIgnoreCase("N"))
												cond="Y";
										}
								}
						}
				}
			return cond; 
		}
	
	/*
	 * Added by Srikalyan to Execute ID Based Clases in the DB
	 */
	@Override
	public Map<String,Object> executeIDClass(Map<String,Object> map)
		{
			Map<String,Object> mapNew=	new HashMap<String,Object>();
			try
				{
					if(map.get("classPath")!=null)
						{
							String classPath=(String)(map.get("classPath"));
							EhfPatient newClass=null;
								newClass=(EhfPatient)genericDao.findById(Class.forName(classPath),
									String.class,(String)map.get("ID"));
							if(newClass!=null)
								mapNew.put(classPath, newClass);
						}
				}
			catch(Exception e)
				{
					e.printStackTrace();
//					GLOGGER.error("Error occured in executeIDClass() method in patientDaoImpl.class"+e.getMessage());
					
				}
			return mapNew;
		}
	/*
	 * Added by Srikalyan to Execute HQL Query  
	 */
	@Override
	 public Map<String,Object> executeNormalQuery(String classPath,String query)
	 	{
		 	Map<String,Object> returnMap=new HashMap<String,Object>();
		 	try
		 		{
		 			List<?> retLst=genericDao.executeHQLQueryList(Class.forName(classPath), query);
		 			if(retLst!=null)
		 				returnMap.put(classPath, retLst);
		 		}
		 	catch(Exception e)
		 		{
		 			e.printStackTrace();
//		 			GLOGGER.error("Exception Occured in executeNormalQuery method of patientDaoImpl"+e.getMessage());
		 		}
		 	return returnMap;
	 	}
	
	
	public String getDaysActiveOP(String caseId)
	{
		String msg="";
		List<PatientVO> activeLst=new ArrayList<PatientVO>();
		String deploymentdate=config.getString("TG_OP_deployment_date");
		Date date=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	Date date1 = null;
		try {
			date1 = sdf.parse(deploymentdate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
		float daysActive=0;
		try
		{
		StringBuffer query=new StringBuffer();
		query.append( " select  sysdate-a.crtDt as daysDiff from EhfCase a " );
		query.append( "where a.caseId='"+caseId+"' and a.crtDt>=to_date('"+deploymentdate+"','dd/MM/yyyy') ");
		activeLst=genericDao.executeHQLQueryList(PatientVO.class,query.toString());
		
		if(activeLst!=null &&  activeLst.size()>0)
		{
			daysActive=(activeLst.get(0).getDaysDiff().floatValue());	
		}
		
		if(daysActive>14)
		{
			msg="OP Case cannot be registered as the following Case is Active since "+Math.round(daysActive)+" days (Prescribed limit : 14 Days)";
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return msg;
	
	
		
	}
	
	public List<LabelBean> getConsultDtls(String patientId)
	{
		List<LabelBean> consultDtls=new ArrayList<LabelBean>();
		StringBuffer query=new StringBuffer();
		
		SessionFactory factory=hibernateTemplate.getSessionFactory();
		Session session=factory.openSession();
		
		
		try
		{
		query.append( " from EhfOpConsultData a where a.patientId='"+patientId+"' order by a.crtDt");
		
		Query q;
		q=session.createQuery(query.toString());
		List<EhfOpConsultData> opConsultDtls=q.list();
		
		if(opConsultDtls!=null && opConsultDtls.size()>0)
		{
		for(EhfOpConsultData consultData:opConsultDtls)
		{
			LabelBean consultation = new LabelBean();
			
			//consultation.setPatientId(consultData.getPatientId());
			consultation.setUnitName(consultData.getUnitName());
			consultation.setDiagnoisedBy(consultData.getDiagnoisedBy());
			consultation.setHodName(consultData.getUnitHodName());
			consultation.setConsultationTime(consultData.getConsultationTime());
			consultDtls.add(consultation);
			
		}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			
			session.flush();
			session.close();
			factory.close();
			
			
			
		}
		return consultDtls;
	}
	
  	public List<CasesSearchVO> getOpPastHistoryDetails(CasesSearchVO vo) {
  		List<CasesSearchVO> list=null;
  		String caseId=vo.getCaseId();
  		StringBuffer query = new StringBuffer();
  		query.append(" select distinct(c.case_Id) as CATID,p.patient_ipop as PATIPOP, ecb.cmb_Dtl_Name as ISSUESTATUS, c.case_No as CASENO,p.patient_Id as PATIENTID,p.name as PATNAME,h.hosp_Name as HOSPNAME,asri.cmb_Dtl_Name as caseStatus, " );
  		query.append(" c.case_Status as CASESTATUSID,p.reg_Hosp_Date  as SUBMITTEDDATE, nvl(ec.cs_Cl_Amount,0) as CLAIMAMT, (nvl(ec.pck_Appv_Amt,0)+ nvl(ec.comorbid_Appv_Amt,0)  + nvl(ec.enh_Appv_Amt,0)) as PREAUTHAPPAMT ");  		
  		query.append(" from Ehf_Emp_Case_Dtls c,Ehf_Patient p ,Ehfm_Hospitals h,Ehfm_Cmb_Dtls asri, Ehf_Case ec, Ehfm_Cmb_Dtls ecb  ");
  		if(vo.getCaseForDissFlg()==null || "".equals(vo.getCaseForDissFlg()) || !"Y".equalsIgnoreCase(vo.getCaseForDissFlg()))
  		{
  			query.append(" ,Ehf_Audit ea ");
  		}
  		query.append(" where asri.cmb_Dtl_Id=c.case_Status and c.case_Hosp_Code=h.hosp_Id and  ec.case_Patient_No=p.patient_Id and c.employee_No='"+vo.getLoginName()+"' ");
  		query.append(" and ec.case_Id=c.case_Id and ecb.cmb_Dtl_Id= ec.case_Status  ");
  		
  		if(vo.getCaseForDissFlg()==null || "".equals(vo.getCaseForDissFlg()) || !"Y".equalsIgnoreCase(vo.getCaseForDissFlg()))
  		{
  			query.append(" and ea.case_Id= c.case_Id and c.case_Status = '"+config.getString("CASE.OPCaseRegistered")+"' ");
  		}
  		query.append(" and ec.case_Id not in ('"+caseId+"') ");
  		query.append(" group by c.case_Id,p.patient_ipop,ecb.cmb_Dtl_Name,c.case_No,p.patient_Id,p.name,h.hosp_Name,asri.cmb_Dtl_Name,c.case_Status,p.reg_Hosp_Date,ec.cs_Cl_Amount,ec.pck_Appv_Amt,ec.comorbid_Appv_Amt,ec.enh_Appv_Amt");
  		query.append(" order by  p.reg_Hosp_Date ");
  		try{
  			list=genericDao.executeSQLQueryList(CasesSearchVO.class, query.toString());
  		}
  		catch(Exception e)
  		{
  			e.printStackTrace();
  		}
  		return list;
  	}
  	
  	public String getEmpCode(String PatientId)
  	{
  		String empCode=null;
  		SessionFactory factory=hibernateTemplate.getSessionFactory();
  		Session session=factory.openSession();
  		
  		StringBuffer query=new StringBuffer();
  		Query q = null;
  		try
  		{
  		query.append( " from EhfEmpCaseDtls a where a.casePatientNo='"+PatientId+"' ");
  		q=session.createQuery(query.toString());
  		List<EhfEmpCaseDtls> lst=q.list();
  		
  		if(lst!=null && lst.size()>0)
  		{
  			empCode=lst.get(0).getEmployeeNo();
  		}
  		}
  		catch(Exception e)
  		{
  			e.printStackTrace();
  		}
  		finally
  		{
  			session.flush();
  			session.close();
  			factory.close();
  			
  		}
  		
  		return empCode;
  		
  	}
  	
  	/*added by venkatesh*/
	@Transactional	
	public String saveChronicCaseDetails(PatientVO patientVO) throws Exception {
		String lStrChronicId=null;
		String chronicNo=null;
		//String lStrChronicCaseId=null;
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		Date lCurrentDate=sdfw.parse(patientVO.getCrtDt());
		Date date=new Date();
		String temp=null;
		SessionFactory factory= hibernateTemplate.getSessionFactory();
		Session session=factory.openSession();
		//EhfChronicCaseDtl ehfChronicCaseDtl=new EhfChronicCaseDtl();
		List<AttachmentVO> lSplAttachList=patientVO.getAttachmentsList();
		EhfChronicAudit ehfChronicAudit = new EhfChronicAudit();
		//EhfEmpCaseDtls ehfEmpCaseDtls = new EhfEmpCaseDtls();
		
		
		
		
		chronicNo=patientVO.getChronicId()+config.getString("SLASH_VALUE")+"1";
		EhfChronicCaseDtlPK ehfChronicCaseDtlPK=new EhfChronicCaseDtlPK(patientVO.getChronicId(),chronicNo);
		
		EhfChronicCaseDtl ehfChronicCaseDtl=genericDao.findFirstByPropertyMatch(EhfChronicCaseDtl.class, "id",ehfChronicCaseDtlPK);
		
		
		
		/*if(ehfChronicCaseCheck==null )
		{
			
			//int seqNextVal=Integer.parseInt(liNextVal);
			//ehfmSeqCase.setSeqNextVal(Long.valueOf(seqNextVal + 1));
			//updateSequenceVal(ehfmSeqCase);
		
			lStrChronicId=patientVO.getChronicID();
			patientVO.setChronicID(lStrChronicId);
			ehfChronicCaseDtl = new EhfChronicCaseDtl();
			ehfChronicCaseDtl.setCrtUsr(patientVO.getCrtUsr());
			ehfChronicCaseDtl.setCrtDt(lCurrentDate);
			ehfChronicCaseDtl.setLstUpdDt(lCurrentDate);
			ehfChronicCaseDtl.setLstUpdUsr(patientVO.getCrtUsr());
			
		}*/
		if(ehfChronicCaseDtl!=null)
		{
			
			//ehfChronicCaseDtl=ehfChronicCaseCheck;
			lStrChronicId=patientVO.getChronicId();
			//ehfChronicCaseDtl=ehfChronicCaseCheck;
			ehfChronicCaseDtl.setLstUpdDt(lCurrentDate);
			ehfChronicCaseDtl.setLstUpdUsr(patientVO.getCrtUsr());
		}
		
		
		
		EhfChronicPatientHosDgnsi ehfChronicPatientHosDgnsi = genericDao.findById(EhfChronicPatientHosDgnsi.class, String.class, patientVO.getChronicId());
		if (ehfChronicPatientHosDgnsi==null){
			ehfChronicPatientHosDgnsi = new EhfChronicPatientHosDgnsi();
			ehfChronicPatientHosDgnsi.setCrtUsr(patientVO.getCrtUsr());
			ehfChronicPatientHosDgnsi.setCrtDt(lCurrentDate);
		}
		else{
			ehfChronicPatientHosDgnsi.setLstUpdDt(lCurrentDate);
			ehfChronicPatientHosDgnsi.setLstUpdUsr(patientVO.getCrtUsr());
		}
		
			SimpleDateFormat sdf2=new SimpleDateFormat("dd-MM-yyyy");
			
			EhfChronicPatientDtl ehfChronicPatientDtl2=new EhfChronicPatientDtl();			
			
			try{

				if(patientVO.getChronicId()!=null){
					ehfChronicPatientDtl2=genericDao.findById(EhfChronicPatientDtl.class,String.class,patientVO.getChronicId());
					
					String lStrTestDesc=null;
					List<AttachmentVO> lAttachList=patientVO.getGenAttachmentsList();
					for(AttachmentVO attachmentVO:lAttachList){

						EhfChronicPatientTest ehfChronicPatientTest=new EhfChronicPatientTest();
						ehfChronicPatientTest.setSno(Long.parseLong(attachmentVO.getAttachId()));
						ehfChronicPatientTest.setTestId(attachmentVO.getTestId());
						if(lStrTestDesc!=null){
							lStrTestDesc=lStrTestDesc+","+attachmentVO.getTestName();
						}
						else{
							lStrTestDesc=attachmentVO.getTestName();  
						}
						ehfChronicPatientTest.setChronicId(lStrChronicId);
						ehfChronicPatientTest.setChronicNo(chronicNo);
						ehfChronicPatientTest.setAttachPath(attachmentVO.getFileReportPath());                	            
						ehfChronicPatientTest.setCrtUsr(patientVO.getCrtUsr());
						ehfChronicPatientTest.setCrtDt(lCurrentDate);
						genericDao.save(ehfChronicPatientTest);
					}
					//Updating existing test file path
					List<AttachmentVO> lUpdGenAttachList=patientVO.getUpdGenAttachmentsList();
					EhfChronicPatientTest ehfChronicPatientTest=null;
					for(AttachmentVO attachmentVO:lUpdGenAttachList)
					{
						Map<String,Object> testMap=new HashMap<String,Object>();
						testMap.put("chronicId",lStrChronicId );
						testMap.put("chronicNo",chronicNo );
						testMap.put("testId",attachmentVO.getTestId());
						List<EhfChronicPatientTest> ehfChronicPatientTestList=genericDao.findAllByPropertyMatch(EhfChronicPatientTest.class, testMap);
						if(ehfChronicPatientTestList!=null && ehfChronicPatientTestList.size()>0)
						{
							ehfChronicPatientTest=ehfChronicPatientTestList.get(0);
							ehfChronicPatientTest.setAttachPath(attachmentVO.getFileReportPath());
							ehfChronicPatientTest.setLstUpdUsr(patientVO.getCrtUsr());
							ehfChronicPatientTest.setLstUpdDt(lCurrentDate);
							genericDao.save(ehfChronicPatientTest);
						}
					}
					
					String lStrPastHistory="";
					if(patientVO.getPastHistory()!=null)
					{
						for(int i=0;i<patientVO.getPastHistory().length;i++){
							lStrPastHistory = lStrPastHistory+patientVO.getPastHistory()[i];
							if(i!=patientVO.getPastHistory().length-1){
								lStrPastHistory = lStrPastHistory+"~";
							}
						}
					}

					String lStrExamFind="";	   
					if(patientVO.getExaminationFnd()!=null){
					for(int i=0;i<patientVO.getExaminationFnd().length;i++){
						lStrExamFind = lStrExamFind+patientVO.getExaminationFnd()[i];
						if(i!=patientVO.getExaminationFnd().length-1){
							lStrExamFind = lStrExamFind+"~";
						}
					}
					}					
					//saving in ehfChroniccase for chronic op
						String lStrHospitalCodeInCaseNo = ehfChronicPatientDtl2.getRegHospId().substring(3);
						String nflag=config.getString("NFlag");
						if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Yes"))
						{
							ehfChronicPatientDtl2.setRegisterYN(null);
							chronicNo=lStrChronicId+config.getString("SLASH_VALUE")+"1";
						}
						else if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Submit"))
						{
							chronicNo=lStrChronicId+config.getString("SLASH_VALUE")+"1";
								//config.getString("CASE")+config.getString("SLASH_VALUE")+lStrHospitalCodeInCaseNo+config.getString("SLASH_VALUE")+lStrChronicId;
							ehfChronicPatientDtl2.setRegisterYN("Y");
							ehfChronicCaseDtl.setChronicRegnDate(lCurrentDate);
							
							/*added to save total Drug amount after submitting*/
							String totPckgAmt=null;
							String pkgCode=patientVO.getOpPkgCode();
							String actOrder="1";
							String schemeId=ehfChronicCaseDtl.getSchemeId();
							totPckgAmt=calculatePackageAmt( pkgCode, actOrder, schemeId);
							ehfChronicCaseDtl.setTotPckgAmt(totPckgAmt);
							
						}
						else
						{
							ehfChronicPatientDtl2.setRegisterYN(null);
							//caseNo=config.getString("CASE")+config.getString("SLASH_VALUE")+lStrHospitalCodeInCaseNo+config.getString("SLASH_VALUE")+lStrChronicId+config.getString("SLASH_VALUE")+"D";
						}
					
						ehfChronicCaseDtl.setId(new EhfChronicCaseDtlPK(lStrChronicId,chronicNo));
						//ehfChronicCaseDtl.setCaseNo(caseNo);
						ehfChronicCaseDtl.setHospCode(ehfChronicPatientDtl2.getRegHospId());
							
						ehfChronicCaseDtl.setSchemeId(ehfChronicPatientDtl2.getSchemeId());
						ehfChronicCaseDtl.setChronicRegnDate(ehfChronicPatientDtl2.getCrtDt());
						
						if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Yes"))
						{
							ehfChronicCaseDtl.setChronicStatus(config.getString("CO-MEDCO-SAVE"));
						}
						else if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Submit"))
						{
							ehfChronicCaseDtl.setChronicStatus(config.getString("CO-MEDCO-SCREENING"));	
						}
						
						EhfEnrollment ehfEnrollment=null;
						List<EhfEnrollment> ehfEnrollmentList=genericDao.findAllByPropertyMatch(EhfEnrollment.class,"empCode",ehfChronicPatientDtl2.getEmployeeNo());
						if(ehfEnrollmentList!=null && ehfEnrollmentList.size()>0)
						{
							ehfEnrollment=ehfEnrollmentList.get(0);
						}
						

						//saving in audit for ip/op/chronic op 
						
						Long lActOrder = 1L;
						StringBuffer lQueryBuffer = new StringBuffer();
						String args[] = new String[1];
						args[0] = chronicNo;
						lQueryBuffer
								.append(" select max(au.id.actOrder) as COUNT from EhfChronicAudit au where au.id.chronicNo=? ");
					
						List<ChronicOPVO> actOrderList = genericDao.executeHQLQueryList(
								ChronicOPVO.class, lQueryBuffer.toString(), args);
						if (actOrderList != null && !actOrderList.isEmpty()
								&& actOrderList.get(0).getCOUNT() != null) {
							if (actOrderList.get(0).getCOUNT().longValue() >= 0)
								lActOrder = actOrderList.get(0).getCOUNT().longValue() + 1;
						}
						ehfChronicAudit.setId(new EhfChronicAuditPK(lStrChronicId,chronicNo,lActOrder));
						ehfChronicAudit.setActBy(patientVO.getCrtUsr());
/*						
 * 
 * if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Yes")){
							ehfAudit.setRemarks("Case Drafted");
						}else{
						ehfAudit.setRemarks(patientVO.getIpCaseRemarks());
						}*/
						ehfChronicAudit.setCrtUsr(patientVO.getCrtUsr());
						ehfChronicAudit.setCrtDt(date);
						ehfChronicAudit.setLangId(config.getString("en_US"));
						
						if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Yes")){
						
						//ehfEmpCaseDtls.setCaseStatus(config.getString("CASE.CaseDrafted"));
							
							ehfChronicAudit.setActId(config.getString("CO-MEDCO-SAVE"));
						}
						if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Submit")){
							
							ehfChronicAudit.setActId(config.getString("CO-MEDCO-SCREENING"));
							
							}
						
						
						EhfChronicPatientPerHstry ehfChronicPatientPerHstry = genericDao.findById(EhfChronicPatientPerHstry.class, String.class, patientVO.getChronicId());
						if(ehfChronicPatientPerHstry!=null){
							ehfChronicPatientPerHstry.setLstUpdDt(lCurrentDate);
							ehfChronicPatientPerHstry.setLstUpdUsr(patientVO.getCrtUsr());
						}else{
							ehfChronicPatientPerHstry = new EhfChronicPatientPerHstry();
							ehfChronicPatientPerHstry.setCrtDt(lCurrentDate);
							ehfChronicPatientPerHstry.setCrtUsr(patientVO.getCrtUsr());
						}
						
						ehfChronicPatientPerHstry.setChronicId(lStrChronicId);
						String[] result = patientVO.getPersonalHistVal().split("#");
						for (int x=0; x<result.length; x++)
						{
							String[] result1 = result[x].split("~");
							if(result1[0].equalsIgnoreCase("PR1"))
								ehfChronicPatientPerHstry.setAppetite(result1[1]);
							else if(result1[0].equalsIgnoreCase("PR2"))
								ehfChronicPatientPerHstry.setDiet(result1[1]);
							else if(result1[0].equalsIgnoreCase("PR3"))
								ehfChronicPatientPerHstry.setBowels(result1[1]);
							else if(result1[0].equalsIgnoreCase("PR4"))
								ehfChronicPatientPerHstry.setNutrition(result1[1]);
							else if(result1[0].equalsIgnoreCase("PR5"))
								ehfChronicPatientPerHstry.setKnownAllergies(result1[1]);
							else if(result1[0].equalsIgnoreCase("PR6"))
								ehfChronicPatientPerHstry.setAddictions(result1[1]);
							else if(result1[0].equalsIgnoreCase("PR7"))
								ehfChronicPatientPerHstry.setMaritalStatus(result1[1]);
							else if(result1[0].equalsIgnoreCase("PR8"))
								ehfChronicPatientPerHstry.setOccupation(result1[1]);
						}
                        genericDao.save(ehfChronicPatientPerHstry);

                        
                        
                                       
                        
                        EhfChronicPatientExamFind ehfChronicPatientExamFind = genericDao.findById(EhfChronicPatientExamFind.class, String.class, patientVO.getChronicId());
						if(ehfChronicPatientExamFind==null){
							ehfChronicPatientExamFind = new EhfChronicPatientExamFind();
							ehfChronicPatientExamFind.setCrtDt(lCurrentDate);
							ehfChronicPatientExamFind.setCrtUsr(patientVO.getCrtUsr());
						}else{
							ehfChronicPatientExamFind.setLstUpdDt(lCurrentDate);
							ehfChronicPatientExamFind.setLstUpdUsr(patientVO.getCrtUsr());
						}
						
						ehfChronicPatientExamFind.setChronicId(lStrChronicId);
						
						String[] result2 = patientVO.getExamFndsVal().split("#");
						for (int x=0; x<result2.length; x++)
						{
							String[] result1 = result2[x].split("~");
							if(result1.length>1){
							if(result1[0].equalsIgnoreCase("GE3"))
								ehfChronicPatientExamFind.setBmi(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE4"))
								ehfChronicPatientExamFind.setPallor(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE5"))
								ehfChronicPatientExamFind.setCyanosis(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE6"))
								ehfChronicPatientExamFind.setClubOfFingrtoes(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE7"))
								ehfChronicPatientExamFind.setLymphadenopathy(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE8"))
								ehfChronicPatientExamFind.setOedemaInFeet(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE9"))
								ehfChronicPatientExamFind.setMalnutrition(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE10"))
								ehfChronicPatientExamFind.setDehydration(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE11"))
								ehfChronicPatientExamFind.setTemperature(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE12"))
								ehfChronicPatientExamFind.setPluseRate(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE13"))
								ehfChronicPatientExamFind.setRespirationRate(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE14"))
								ehfChronicPatientExamFind.setBpLt(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE1"))
								ehfChronicPatientExamFind.setHeight(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE2"))
								ehfChronicPatientExamFind.setWeight(result1[1]);
							else if(result1[0].equalsIgnoreCase("GE15"))
								ehfChronicPatientExamFind.setBpRt(result1[1]);
							}
						}
						genericDao.save(ehfChronicPatientExamFind);
						
					
						ehfChronicPatientHosDgnsi.setChronicId(lStrChronicId);
						ehfChronicPatientHosDgnsi.setPastHistory(lStrPastHistory);
						ehfChronicPatientHosDgnsi.setHistoryIllness(patientVO.getPresentHistory());
						ehfChronicPatientHosDgnsi.setExamntnFindings(lStrExamFind);
						
						String lStrValue="";	
						if(patientVO.getPatientComplaint()!=null && patientVO.getPatientComplaint().length>0){
						for(int i=0;i<patientVO.getPatientComplaint().length;i++){
							lStrValue = lStrValue+patientVO.getPatientComplaint()[i];
							if(i!=patientVO.getPatientComplaint().length-1){
								lStrValue = lStrValue+"~";
							}
						}
						}
						ehfChronicPatientHosDgnsi.setPatientComplaint(lStrValue);
						
						ehfChronicPatientHosDgnsi.setComplaintType(patientVO.getComplaints());
						lStrValue="";
						if(patientVO.getPersonalHistory()!=null && patientVO.getPersonalHistory().length>0){
						for(int i=0;i<patientVO.getPersonalHistory().length;i++){
							lStrValue = lStrValue+patientVO.getPersonalHistory()[i];
							if(i!=patientVO.getPersonalHistory().length-1){
								lStrValue = lStrValue+"~";
							}
						}}
						ehfChronicPatientHosDgnsi.setPersonalHistory(lStrValue);
						lStrValue="";	  
						if(patientVO.getFamilyHistory()!=null)
						{
							for(int i=0;i<patientVO.getFamilyHistory().length;i++){
								lStrValue = lStrValue+patientVO.getFamilyHistory()[i];
								if(i!=patientVO.getFamilyHistory().length-1){
									lStrValue = lStrValue+"~";
								}
							}
						}
						ehfChronicPatientHosDgnsi.setFamilyHistory(lStrValue);
						ehfChronicPatientHosDgnsi.setPastHistoryOthr(patientVO.getPastHistryOthr());
						ehfChronicPatientHosDgnsi.setPastHistoryCancers(patientVO.getPastHistryCancers());
						ehfChronicPatientHosDgnsi.setPastHistoryEnddis(patientVO.getPastHistryEndDis());
						ehfChronicPatientHosDgnsi.setPastHistorySurg(patientVO.getPastHistrySurg());
						ehfChronicPatientHosDgnsi.setFamilyHistoryOthr(patientVO.getFamilyHistoryOthr());
						
						

						
						
						

						for(AttachmentVO attachmentVO:lSplAttachList)
							{
							EhfChronicTherapyInvest ehfChronicTherapyInvest=new EhfChronicTherapyInvest();
							EhfChronicTherapyInvestPK ehfChronicTherapyInvestPK=new EhfChronicTherapyInvestPK(lStrChronicId,attachmentVO.getTestId());
							ehfChronicTherapyInvest.setId(ehfChronicTherapyInvestPK);
							//ehfChronicTherapyInvest.setChronicCatCode(attachmentVO.getIcdProcCode());
							ehfChronicTherapyInvest.setActiveyn("Y");
							ehfChronicTherapyInvest.setCrtDt(lCurrentDate);
							ehfChronicTherapyInvest.setCrtUsr(patientVO.getCrtUsr());
							
							ehfChronicTherapyInvest.setFilename(attachmentVO.getFileName());
							ehfChronicTherapyInvest.setInvestigationStage("CaseReg");
							ehfChronicTherapyInvest.setAttachTotalPath(attachmentVO.getFileReportPath());

								genericDao.save(ehfChronicTherapyInvest);
							}
							if(patientVO.getTeleDocremarks()!=null)
							{
								ehfChronicPatientHosDgnsi.setTreatingDocRmks(patientVO.getTeleDocremarks());
							}
						}
						
				
				/*removing the existing prescription details*/
				
							String delQuery="";
							delQuery = "DELETE FROM EhfChronicPatientDrug epd where epd.id.chronicId='"+lStrChronicId+"'";
							session.createQuery( delQuery ).executeUpdate();
							session.flush();
							session.clear();
							//Setting Prescription Details
							float totDrugAmount=0;
							EhfChronicPatientDrug ehfDrugs=null;
							
							for(DrugsVO drugsVO : patientVO.getDrugs())
							{
								ehfDrugs = new EhfChronicPatientDrug(); 
								
								
								/*String caseStatus=config.getString("CO-MEDCO-SAVE");
								StringBuffer query =null;
								EhfChronicPatientDrug existPatientDrugs = null;
								
								try
								{
									query = new StringBuffer();
						    	    query.append("select epd.id.drugId ");
						    	    query.append(" from EhfChronicPatientDtl ep,EhfChronicCaseDtl ec,EhfChronicPatientDrug epd");
						    	    query.append(" where ep.cardNo='"+ehfChronicPatientDtl.getCardNo()+"'  ");
						    	    query.append(" and ec.chronicStatus='"+caseStatus+"' and ep.chronicId=epd.id.chronicId and epd.asriDrugCode='"+drugsVO.getDrugName()+"'");
						    	    
						    	    Long drugId = (Long) session.createQuery(query.toString()).uniqueResult();
						    	    if(drugId!=null && drugId>0)
						    	    {
						    	    	existPatientDrugs = genericDao.findById(EhfChronicPatientDrug.class, Long.class, drugId);
						    	    }
						    	   
									}
									
						    	
								catch(Exception e)
						    	{
						    		e.printStackTrace();
									GLOGGER.error("Exception Occurred in saveCaseDetails() while registering patient drugs in PatientDaoImpl class."+e.getMessage());
						    	}
							*/
						
								/*EhfChronicPatientDrugPK ehfChronicPatientDrugPK=new EhfChronicPatientDrugPK();
								ehfChronicPatientDrugPK.setChronicId(lStrChronicId);
								ehfChronicPatientDrugPK.setDrugId(drugsVO.getDrugId());
								*/
								
								/*added for calculating drug amount*/
								float drugAmount=0;
								if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Submit"))
								{
								String pkgCode=patientVO.getOpPkgCode();
								String drugCode=drugsVO.getDrugName();
								StringBuffer query =null;
								//float drugAmount=0;
								
								String dosage=drugsVO.getDosage();
								int dos=0;
								String medicationPeriod=drugsVO.getMedicationPeriod();
								try
								{
									query = new StringBuffer();
						    	    query.append("select b.unitPrice from EhfmChronicDrugMst a,EhfmChronicDrugPricMst b");
						    	    query.append(" WHERE a.id.chemicalCode=b.id.drugCode AND a.id.chemicalCode='"+drugCode+"' AND a.id.chronicPkgCode='"+pkgCode+"'");
						    	    query.append(" AND b.id.active='Y' AND a.activeYn='Y' ");
						    	    String unitPrice = (String) session.createQuery(query.toString()).uniqueResult();
						    	    
						    	    if(("OD").equalsIgnoreCase(dosage))
						    	    	dos=1;
						    	    else if(("BD").equalsIgnoreCase(dosage))
						    	    	dos=2;
						    	    else if(("TID").equalsIgnoreCase(dosage))
						    	    	dos=3;
						    	    else if(("QID").equalsIgnoreCase(dosage))
						    	    	dos=4;
						    	    if(medicationPeriod!=null && unitPrice!=null)
						    	    drugAmount=dos*Float.parseFloat(medicationPeriod)*Float.parseFloat(unitPrice);
								}
								catch(Exception e)
								{
									e.printStackTrace();
//									GLOGGER.info("chronic Id is :"+lStrChronicId);
//									GLOGGER.error("Exception Occurred in getting chronic drug unit price while registering patient drugs in chronicDaoImpl class."+e.getMessage());
								}
								
								if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Submit"))
								{
									totDrugAmount=Math.round(totDrugAmount+drugAmount);
								}
								}
								/*saving in drugs table*/			
								if(drugsVO!=null  )
								{
								ehfDrugs.setId( new EhfChronicPatientDrugPK(lStrChronicId,chronicNo,drugsVO.getDrugId()));								
								ehfDrugs.setDrugTypeCode(drugsVO.getDrugTypeName());
								ehfDrugs.setDrugSubTypeCode(drugsVO.getDrugSubTypeName());
								ehfDrugs.setAsriDrugCode(drugsVO.getDrugName());
								ehfDrugs.setRoute(drugsVO.getRoute());
								ehfDrugs.setStrength(drugsVO.getStrength());
								ehfDrugs.setDosage(drugsVO.getDosage());
								ehfDrugs.setMedicationPeriod(drugsVO.getMedicationPeriod());
								
								/*Date expDt=new Date();
								SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
								String dateInString = drugsVO.getExpiryDt();
								try{
								expDt=sd.parse(dateInString);
						
								}
								catch(Exception e)
								{
									e.printStackTrace();
								}
								
								ehfDrugs.setBatchNo(drugsVO.getBatchNo());
								ehfDrugs.setExpiryDt(expDt);
								*/
								ehfDrugs.setDrugAmount(String.valueOf(drugAmount));
								}
								/*if(existPatientDrugs!=null)
								{
									ehfDrugs.setIssueFromDt(existPatientDrugs.getIssueFromDt());
									ehfDrugs.setIssueToDt(existPatientDrugs.getIssueToDt());
								}*/
								ehfDrugs.setCrtDt(lCurrentDate);
								ehfDrugs.setCrtUsr(patientVO.getCrtUsr());
								genericDao.save(ehfDrugs);
							}
if(totDrugAmount!=0)
	
{
	
	ehfChronicCaseDtl.setTotDrugAmount(String.valueOf(Math.round(totDrugAmount)));
}
							//Setting Therapy Details
							ehfChronicPatientHosDgnsi.setOpCategoryCode(patientVO.getOpCatCode());
							ehfChronicPatientHosDgnsi.setOpPackageCode(patientVO.getOpPkgCode());
							ehfChronicPatientHosDgnsi.setOpIcdCode(patientVO.getOpIcdCode());

							//ehfChronicCaseDtl.setChronicStatus(config.getString("CO-MEDCO-SAVE"));
							//ehfEmpCaseDtls.setCaseStatus(config.getString("CO-MEDCO-SAVE"));
							
							
							
						ehfChronicPatientHosDgnsi.setDiagnosisType(patientVO.getDiagnosisType());
						ehfChronicPatientHosDgnsi.setMainCategoryCode(patientVO.getMainCatName());
						ehfChronicPatientHosDgnsi.setCategoryCode(patientVO.getCatName());
						ehfChronicPatientHosDgnsi.setSubCategoryCode(patientVO.getSubCatName());
						ehfChronicPatientHosDgnsi.setDiseaseCode(patientVO.getDiseaseName());
						ehfChronicPatientHosDgnsi.setDiseaseAnatCode(patientVO.getDisAnatomicalName());
//						GLOGGER.error("Info Message after setting Diagnosis details in Ehf Patient Hosp Diagnosis for case Id: "+lStrChronicId +" and patient Id : "+ehfChronicPatientDtl2.getChronicId());
						
						if(patientVO.getDiagnosedBy()!=null){
						String selDocType=patientVO.getDiagnosedBy();
						ehfChronicPatientHosDgnsi.setDoctType(selDocType);
						}
						List<LabelBean> docLst = null;
						/*if(patientVO.getDoctorName()!=null){
						if(!"0".equalsIgnoreCase(patientVO.getDoctorName()))
						{
							docLst=getSelDocDetails(patientVO.getDiagnosedBy(),ehfChronicPatientDtl2.getRegHospId(),patientVO.getDoctorName());                

						}
						if(!"0".equalsIgnoreCase(patientVO.getDoctorName()))
						{
							ehfChronicPatientHosDgnsi.setDoctId(patientVO.getDoctorName());
							if(docLst!=null && docLst.size()>0){
								ehfChronicPatientHosDgnsi.setDoctName(docLst.get(0).getLVL());
								ehfChronicPatientHosDgnsi.setDoctRegNo(docLst.get(0).getID());
								ehfChronicPatientHosDgnsi.setDoctQuali(docLst.get(0).getDSGNNAME());
								ehfChronicPatientHosDgnsi.setDoctMobNo(docLst.get(0).getVALUE());
							}
						}
						else
						{
							ehfChronicPatientHosDgnsi.setDoctId(patientVO.getDoctorName());
							ehfChronicPatientHosDgnsi.setDoctName(patientVO.getOtherDocName());
							ehfChronicPatientHosDgnsi.setDoctRegNo(patientVO.getDocRegNo());
							ehfChronicPatientHosDgnsi.setDoctQuali(patientVO.getDocQual());
							ehfChronicPatientHosDgnsi.setDoctMobNo(patientVO.getDocMobileNo());
						}
						}
						*/

						ehfChronicPatientHosDgnsi.setCaseAdmType(patientVO.getAdmissionType());
						
						
						ehfChronicPatientHosDgnsi.setPoliceStatName(patientVO.getPoliceStatName());
						
						
						
						ehfChronicPatientDtl2.setLstUpdUsr(patientVO.getCrtUsr());            
						ehfChronicPatientDtl2.setLstUpdDt(lCurrentDate);						
					
						genericDao.save(ehfChronicPatientHosDgnsi);
						genericDao.save(ehfChronicCaseDtl); 
						//genericDao.save(ehfEmpCaseDtls);
						genericDao.save(ehfChronicPatientDtl2);
						if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Submit"))
						genericDao.save(ehfChronicAudit);
						chronicNo=chronicNo;
						
				}
				
			
			catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error("Exception Occurred in saveCaseDetails() in PatientDaoImpl class."+e.getMessage());
				chronicNo=null;
			}
			finally
			{
				session.close();
				factory.close();
			}
	
		return chronicNo;
	}
	
	public String calculatePackageAmt(String pkgCode,String actOrder,String scheme)
	{
		String pkgAmount=null;
		long amt=0;
		
		try
		{
		StringBuffer  query=new StringBuffer();
		query.append( " select a.consultAmt as consultAmt,a.investAmt as investAmt from EhfmChronicFollowupPackage a  " );
		query.append( " where a.id.chronicDisCode='"+pkgCode+"' and a.id.scheme='"+scheme+"' and a.id.actOrder=1 and a.activeYn='Y'  ");
		
		List<ChronicOPVO> pkgLst=new ArrayList<ChronicOPVO>();
		pkgLst=genericDao.executeHQLQueryList(ChronicOPVO.class,query.toString());
		if(pkgLst!=null && pkgLst.size()>0)
		{
			if(pkgLst.get(0).getConsultAmt()!=null && pkgLst.get(0).getInvestAmt()!=null)
			amt=Long.valueOf(pkgLst.get(0).getConsultAmt())+Long.valueOf(pkgLst.get(0).getInvestAmt());
		}
		pkgAmount=amt+"";
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Error occured in Method calculatePackageAmt() in ChronicopDaoImpl class");
			
		}
		return pkgAmount;
	}
  public EhfmOpDrugMst getopdrugDataAuto(String chemicalCode)
	{
		EhfmOpDrugMst ehfmOpDrugMst=new EhfmOpDrugMst();
		List<EhfmOpDrugMst> drugLst=new ArrayList<EhfmOpDrugMst>();
		SessionFactory factory=hibernateTemplate.getSessionFactory();
		Session session=factory.openSession();
		StringBuilder hql=new StringBuilder();
		try
		{
		hql.append(" from  EhfmOpDrugMst a where a.chemicalCode='"+chemicalCode+"'  ");
		Query query;
		query=session.createQuery(hql.toString());
		
		drugLst=query.list();
		if(drugLst!=null && drugLst.size()>0)
		{
			ehfmOpDrugMst=drugLst.get(0);
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
		return ehfmOpDrugMst;
		
		
	}
  	
  	public EhfOpConsultData getOpDocDtls(String patientId)
	{
  		EhfOpConsultData ehfOpConsultData=new EhfOpConsultData();
		List<EhfOpConsultData> docLst=new ArrayList<EhfOpConsultData>();
		SessionFactory factory=hibernateTemplate.getSessionFactory();
		Session session=factory.openSession();
		StringBuilder hql=new StringBuilder();
		try
		{
		hql.append(" from  EhfOpConsultData a where a.patientId='"+patientId+"'  ");
		Query query;
		query=session.createQuery(hql.toString());
		
		docLst=query.list();
		if(docLst!=null && docLst.size()>0)
		{
			ehfOpConsultData=docLst.get(0);
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
		return ehfOpConsultData;
		
		
	}	
  	
  	public LabelBean getDiagnosisDtlsAuto(String disCode) throws Exception
  	{
  		LabelBean diagDtls=new LabelBean();
  		List<LabelBean> diagDtlsLst=new ArrayList<LabelBean>();
  		StringBuilder query=new StringBuilder();
  		try
  		{
  		query.append(" select f.dis_anatomical_name as ANATOMICALNAME,f.dis_anatomical_code as ANATOMICALCODE,e.disease_code as DISEASECODE,e.disease_name as DISEASENAME, ");
  		query.append(" d.sub_cat_code as SUBCATCODE,d.sub_cat_name as SUBCATNAME,c.cat_code as CATCODE,c.cat_name as CATNAME, ");
  		query.append(" b.main_cat_code as MAINCATCODE,b.main_cat_name as MAINCATNAME,a.diagnosis_code as DIAGNOSISCODE,a.diagnosis_name as DIAGNOSISNAME from");
  		query.append(" EHFM_DIAGNOSIS_MST a ,EHFM_DIA_MAINCAT_MST b,ehfm_dia_category_mst c, ");
  		query.append(" EHFM_DIA_SUBCAT_MST d,EHFM_DIA_DISEASE_MST  e,EHFM_DIA_DISANATOMICAL_MST  f ");
  		query.append(" where  a.diagnosis_code=b.diagnosis_code and b.main_cat_code=c.main_cat_code and c.cat_code=d.cat_code ");
  		query.append(" and d.sub_cat_code=e.sub_cat_code and e.disease_code=f.disease_code and f.dis_anatomical_code='"+disCode+"' ");
  		
  		diagDtlsLst=genericDao.executeSQLQueryList(LabelBean.class,query.toString());
  		
  		if(diagDtlsLst!=null && diagDtlsLst.size()>0)
  		{
  			diagDtls=diagDtlsLst.get(0);
  		}
  		}
  		catch(Exception e)
  		{
  			e.printStackTrace();
  		}
  		return diagDtls;
  	}


	@SuppressWarnings("unchecked")
	public List<LabelBean> getDiagList()
	{
		List<LabelBean> diagList=new ArrayList<LabelBean>();
		StringBuilder hql=new StringBuilder();
	
		try
		{
		hql.append("select a.id.disAnatomicalCode as ID,a.disAnatomicalName as VALUE from EhfmDiagDisAnatomicalMst a ");
		
		diagList=genericDao.executeHQLQueryList(LabelBean.class,hql.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return diagList;
	}

	
	public List<LabelBean> getDiagListSearch(String data,String type) throws Exception
	{
		List<LabelBean> diagList=new ArrayList<LabelBean>();
		StringBuilder hql=new StringBuilder();
		data=data.toUpperCase();
	
		try
		{
		hql.append("select a.id.disAnatomicalCode as ID,a.disAnatomicalName as VALUE from EhfmDiagDisAnatomicalMst a where upper(a.disAnatomicalName) ");
		
		if(type!=null && ("s").equalsIgnoreCase(type))
			hql.append(" like '"+data+"%'  ");
		else
			hql.append(" like '%"+data+"%'  ");
		
		diagList=genericDao.executeHQLQueryList(LabelBean.class,hql.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return diagList;
	}
	
	public String checkDentalClinic(String userid, String patientId)
	{
		List<LabelBean> hospids;
		String query=" select b.hosp_id as HOSPID from ehfm_medco_dtls a ,ehfm_edc_hosp_action_dtls b ,ehf_patient c where a.medco_id='"+userid+ "' and  c.patient_id='"+patientId+"'  and a.hosp_id=b.hosp_id and b.hosp_id=c.reg_hosp_id and b.hospital_type = 'CD10001'  and  b.scheme = 'CD202' ";
		hospids=genericDao.executeSQLQueryList(LabelBean.class, query);
		String hospID = "hospital";
		if(hospids!=null && hospids.size()>0)
	    {		
			hospID=hospids.get(0).getHOSPID(); 
	    }
		return hospID;
	}
	public String checkGovernmentHosp(String userid, String patientId)
	{
		List<LabelBean> hospids;
		String query=" select cast(d.hosp_type as varchar2(20) ) as HOSPTYPE from ehfm_medco_dtls a ,ehfm_edc_hosp_action_dtls b ,ehf_patient c ,  ehfm_hospitals  d where a.medco_id='"+userid+ "' and  c.patient_id='"+patientId+"'  and a.hosp_id=b.hosp_id and b.hosp_id=c.reg_hosp_id  and  b.scheme = 'CD202'  and b.hosp_id = d.hosp_id ";
		hospids=genericDao.executeSQLQueryList(LabelBean.class, query);
		String hospType = "";
		if(hospids!=null && hospids.size()>0)
	    {		
			hospType=hospids.get(0).getHOSPTYPE(); 
	    }
		return hospType;
	}
	
	public String checkGovernmentHosp(String userid)
	{
		List<LabelBean> hospids;
		String query=" select distinct cast(d.hosp_type as varchar2(20) ) as HOSPTYPE from ehfm_medco_dtls a ,ehfm_edc_hosp_action_dtls b ,ehf_patient c ,  ehfm_hospitals  d where a.medco_id='"+userid+ "'  and a.hosp_id=b.hosp_id and b.hosp_id=c.reg_hosp_id  and  b.scheme = 'CD202'  and b.hosp_id = d.hosp_id ";
		hospids=genericDao.executeSQLQueryList(LabelBean.class, query);
		String hospType = "";
		if(hospids!=null && hospids.size()>0)
	    {		
			hospType=hospids.get(0).getHOSPTYPE(); 
	    }
		return hospType;
	}
	@Override
	public PatientVO getPatientDentalDtls(String patientId) {
		// TODO Auto-generated method stub
		PatientVO dentalDtls= new PatientVO();
		EhfDentalPatientDtls ehfDentalPatientDtls = genericDao.findById(EhfDentalPatientDtls.class, String.class, patientId);
		if(ehfDentalPatientDtls!=null){
			dentalDtls.setDiagnosis(ehfDentalPatientDtls.getDiagnosis());
			dentalDtls.setPatientType(ehfDentalPatientDtls.getIpOp());
			dentalDtls.setAdmissionDetails(ehfDentalPatientDtls.getAdmissionDtls());
			dentalDtls.setAdvancedInvestigations(ehfDentalPatientDtls.getAdvancedInvestigations());
			dentalDtls.setFollowupAdv(ehfDentalPatientDtls.getFlwupAdvise());
			dentalDtls.setMedicationGiven(ehfDentalPatientDtls.getMedicationGiven());
			
		}
		return dentalDtls;
	}
	@Override
	public List<LabelBean> getdentalexaminationDtls(String prntId) {
		// TODO Auto-generated method stub

		List<LabelBean> dentaldetails=new ArrayList<LabelBean>();
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.id.labelID as ID , edm.labelNAME as VALUE from EhfmDentalCmbDtls edm  where edm.labelPrntID='"+prntId+"' and edm.activeYn='Y' and edm.id.scheme='CD202' ");
			dentaldetails=genericDao.executeHQLQueryList (LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDiagnosisTypes() in PatientDaoImpl class."+e.getMessage());
		}
		return dentaldetails;
	}
	public java.lang.String getTreatmentDntlvalue(java.lang.String caseId) {
		// TODO Auto-generated method stub
		List<LabelBean> dentaldetails=new ArrayList<LabelBean>();
		String dentalDbVal="";
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.treatmentDntl as VALUE from EhfPatientExamFind edm  where edm.caseId='"+caseId+"' ");
			dentaldetails=genericDao.executeHQLQueryList (LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.ERROR("Exception Occurred in getDiagnosisTypes() in PatientDaoImpl class."+e.getMessage());
		}
		if(dentaldetails.size() > 0)
		{
			dentalDbVal = dentaldetails.get(0).getVALUE();
		}
		return dentalDbVal;
	}

	@Override
	public List<LabelBean> getCatName(java.lang.String lStrCaseId) {
		// TODO Auto-generated method stub
		StringBuffer query=new StringBuffer();
		String[] args= new String[1];
		args[0] = lStrCaseId;
		query.append(" select cth.icd_cat_code ID,mt.proc_name VALUE,cth.asri_cat_code ICDCODE ,es.dis_main_name ICDNAME  from ehf_case_therapy cth,EHFM_MAIN_THERAPY_NABH mt, ehfm_specialities es  where case_id= ? ");
		query.append(" and cth.icd_cat_code = mt.icd_cat_code and es.dis_main_id=cth.asri_cat_code ");
		
		List<LabelBean> catList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(), args);
				
		return catList;
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
	/**
	 * Added by ramalakshmi for nubspoc CR
	 */
	public String getPatientTypeNew(String asriCode,String icdProcCode,String scheme)
	{
		String patientType="";
		StringBuffer query=new StringBuffer();
		query.append( " select et.process as ID from EHFM_HUBSPOKE_MAIN_THERAPY et  WHERE et.asri_code='"+asriCode+"' and ICD_PROC_CODE='"+icdProcCode+"' AND STATE='"+scheme+"' ");
		List<LabelBean> newBeanLst=genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		if(newBeanLst.size() >0)
		{
			if(newBeanLst != null)
				patientType=newBeanLst.get(0).getID();
		}
		return patientType;
	}
	/**
	 * End of hubSpoc CR
	 */
	@Override
	public LabelBean getHospStatus(String cardNo,String hospId) 
	{
		String cardValidStatus=config.getString("Card.ValidStatus");
		StringBuffer query=new StringBuffer();
		String scheme="";
		LabelBean vo = new LabelBean();
		query.append(" select EF.EHF_CARD_NO as ID,EE.SCHEME as VALUE from EHF_ENROLLMENT EE, EHF_ENROLLMENT_FAMILY EF ");
		query.append("  WHERE EE.ENROLL_PRNT_ID = EF.ENROLL_PRNT_ID  AND EF.EHF_CARD_NO='"+cardNo+"'  AND EF.ENROLL_STATUS IN  ("+cardValidStatus.replace("~", ",")+")");
		List<LabelBean> list = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		if(list.size()>0)
		{
			if(list != null && list.get(0).getVALUE() != null )
				scheme=list.get(0).getVALUE();
		}
		if(!"".equalsIgnoreCase(scheme) && scheme != null)
		{
			EhfmEDCHospActionDtls ehfmEDCHospActionDtls=genericDao.findById(EhfmEDCHospActionDtls.class,EhfmEDCHospActionDtlsId.class,new EhfmEDCHospActionDtlsId(hospId,scheme));
			if(ehfmEDCHospActionDtls!=null)
				{
					if(ehfmEDCHospActionDtls.getHospStatus()!=null)
						{
							if("S".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
								{
									vo.setMsg(config.getString("scheme_hosp_susp_remarks"));
									return vo;
								}
							else if("C".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
								{
									vo.setMsg(config.getString("scheme_cont_hosp_susp_remarks"));
									return vo;
								}
							else if("D".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
								{
									vo.setMsg(config.getString("scheme_hosp_delist_remarks"));
									return vo;
								}
							else if("E".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
								{
									vo.setMsg(config.getString("scheme_hosp_deempan_remarks"));
									return vo;
								}
							else if("SP".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
								{
									vo.setMsg(config.getString("scheme_hosp_susppay_remarks"));
									return vo;
								}
							else if("CP".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()) || "CB".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
								{
									vo.setMsg(config.getString("scheme_hosp_cont_susppay_remarks"));
									return vo;
								}
							else if("N".equalsIgnoreCase(ehfmEDCHospActionDtls.getHospStatus()))
								{
									vo.setMsg(config.getString("invalid_hosp_scheme"));
									return vo;
								}
						}
				}
				else if(ehfmEDCHospActionDtls==null)
				{
					vo.setMsg(config.getString("invalid_hosp_scheme"));
					return vo;
				}
		}
		return vo;
	}
	@Override
	public String checkHubSpoke(String userIDu) 
	{
		List<LabelBean> hospids;
		StringBuffer query = new StringBuffer();
		
		 query.append(" select hub_spoke as HOSPTYPE from ehfm_hospitals h, ehfm_medco_dtls m WHERE h.hosp_id = m.hosp_id  and m.active_yn='Y' and m.medco_id = '"+userIDu+"'  ");
		
		hospids=genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		String hospType = "";
		if(hospids!=null && hospids.size()>0)
	    {		
			hospType=hospids.get(0).getHOSPTYPE(); 
	    }
		return hospType;
	}
	@Override
	public String getHubSpokeHospId(java.lang.String userIDu,String groupId) {
		// TODO Auto-generated method stub
		String hospId = "";
		List<LabelBean> hospids =null;
		StringBuffer query = new StringBuffer();
		try{
			if(groupId!=null && groupId.equalsIgnoreCase(config.getString("preauth_spoke_role")))
			{
				query.append("select spoke_hosp_id as ID from ehfm_hosp_hub_spoke_mpg where spoke_user_id='"+userIDu+"' ");
			}
			else
			{
				query.append("select h.hosp_id as ID from ehfm_hospitals h,ehfm_medco_dtls m where h.hosp_id=m.hosp_id ");
				query.append("and h.hub_spoke='HUB' and m.medco_id='"+userIDu+"'");
			}
			
			hospids = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
			if(hospids!=null && hospids.size()>0)
			{
				hospId=hospids.get(0).getID();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return hospId;
	}
	
	// Oncology changes Start CR#4511-SriHari-11/10/24
	@Transactional
	public Map<String,Object> saveProformaFormHighEnd(PatientForm form) throws Exception{
		Map<String,Object> lResMap = new HashMap<String,Object>();
		List<EhfOncologyDrugs> ehfOncologyDrugsList = new ArrayList<EhfOncologyDrugs>();
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		StringBuffer query = new StringBuffer();
		EhfOncologyDrugs ehfOncologyDrugs = null;
		EhfOncologyMedical ehfOncologyMedical = null;
		String proformaId = form.getProformaId();
		String[] fileDtls = new String[2];
		String uploadType = "proformaType";
		String molecularMarkers = "";
		
		if(proformaId != null && !"".equalsIgnoreCase(proformaId) && !proformaId.isEmpty()){
			ehfOncologyMedical = genericDao.findById(EhfOncologyMedical.class, String.class, proformaId);
		}
		if(ehfOncologyMedical != null){
			criteriaList.add(new GenericDAOQueryCriteria("id.proformaId", proformaId, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfOncologyDrugs> existingDrugs = genericDao.findAllByCriteria(EhfOncologyDrugs.class, criteriaList);
			for(EhfOncologyDrugs row : existingDrugs)
				genericDao.delete(row);
			
			genericDao.flush();
			
			ehfOncologyMedical.setLstUpdDt(new Date());
	        ehfOncologyMedical.setLstUpdUsr(form.getCrtUsr());
	        ehfOncologyMedical.setRemarks(null);
		}else{
			
			query.append(" SELECT EHF_ONCOLOGY_MEDICAL_SEQ.nextval AS COUNT1 FROM DUAL ");
			List<LabelBean> seqId = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
			proformaId = "OPF"+seqId.get(0).getCOUNT1().toString();
			
			ehfOncologyMedical = new EhfOncologyMedical();
			ehfOncologyMedical.setProformaId(proformaId);
			ehfOncologyMedical.setCrtDt(new Timestamp(System.currentTimeMillis()));
	        ehfOncologyMedical.setCrtUsr(form.getCrtUsr());
	        ehfOncologyMedical.setStatus("CD7311");
			ehfOncologyMedical.setPatientName(form.getPatientName());
			ehfOncologyMedical.setCardNo(form.getCardNo());
			ehfOncologyMedical.setRegNo(form.getHospitalRegNo());	        
		}
		
		for(String marker : form.getMolecularMarkers()){
			molecularMarkers = molecularMarkers+","+marker;
		}
		molecularMarkers = molecularMarkers.replaceFirst(",","");
		String drugList = "";
		for(DrugsVO drug : form.getDrugLt()){
			drugList = drugList+","+drug.getDrugCode();
			ehfOncologyDrugs = new EhfOncologyDrugs();
			ehfOncologyDrugs.setId(new EhfOncologyDrugsPK(proformaId,drug.getDrugCode()));
			ehfOncologyDrugs.setDosage(drug.getDosage());
			ehfOncologyDrugs.setFrequency(drug.getRoute());
			ehfOncologyDrugs.setCrtDt(new Timestamp(System.currentTimeMillis()));
			ehfOncologyDrugs.setCrtUsr(form.getCrtUsr());
			ehfOncologyDrugsList.add(ehfOncologyDrugs);
		}
		if(!drugList.isEmpty()) {
	        drugList = drugList.replaceFirst(",", "");
	    }
		
		ehfOncologyMedical.setDiagnosis(form.getDiagnosis());
		ehfOncologyMedical.setStage(form.getStage());
		ehfOncologyMedical.setRemarks(form.getRemarks());
		ehfOncologyMedical.setMolecularMarkers(molecularMarkers);
		if(form.getAttachment()!= null && form.getAttachment().getFileSize()>0){
			deleteOldFile(ehfOncologyMedical.getAttachmentPath());
			fileDtls = fileUploadUtils(form.getAttachment(), proformaId, uploadType);
			ehfOncologyMedical.setAttachment(fileDtls[0]);
			ehfOncologyMedical.setAttachmentPath(fileDtls[1]);
		}		
		ehfOncologyMedical.setAvailability(form.getAvailability());
		ehfOncologyMedical.setDrugList(drugList);
		ehfOncologyMedical.setApprovalAuthority(form.getApprovalAuthority());
		ehfOncologyMedical.setAvailabilityIndia(form.getAvailabilityIndia());
		ehfOncologyMedical.setPalliativeSetting(form.getPalliativeSetting());
		ehfOncologyMedical.setMcbsGrade(form.getMcbsGrade());
		ehfOncologyMedical.setSection(form.getSection());
		ehfOncologyMedical.setDoctorName(form.getDoctorName());
		ehfOncologyMedical.setSuperIndentName(form.getSuperintendentName());
		ehfOncologyMedical.setOrganId(form.getOrganId());
		ehfOncologyMedical.setOrganName(form.getOrganName());
		
		genericDao.save(ehfOncologyMedical);
		if(!ehfOncologyDrugsList.isEmpty())
			genericDao.saveAll(ehfOncologyDrugsList);
		lResMap.put("Msg","SUCCESS");
		lResMap.put("proformaId",proformaId);
		return lResMap;
	}
	
	@Override
	public Map<String, Object> getProformaFormHighEnd(Map<String, Object> lResMap)  throws Exception{
		String cardNo = (String)lResMap.get("cardNo");
		String checkFormId = null;
		String proformaId = null;
		StringBuffer query = new StringBuffer();        
        EhfOncologyMedical ehfOncologyMedical = null;
		List<LabelBean> resList = new ArrayList<LabelBean>();
		if(lResMap.get("checkFormId") != null)
			checkFormId  = (String) lResMap.get("checkFormId");
		if(lResMap.get("proformaId") != null)
			proformaId = (String) lResMap.get("proformaId");
		
		if(checkFormId != null && "Y".equalsIgnoreCase(checkFormId)){
			query.append(" SELECT PROFORMA_ID AS ID FROM EHF_ONCOLOGY_MEDICAL eom WHERE eom.CARD_NO = ? AND ((SYSDATE <= eom.PREAUTH_INIT_DT + 90) OR eom.PREAUTH_INIT_DT IS NULL) ");
			resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(), new String[]{cardNo});
			if(!resList.isEmpty()){
				ehfOncologyMedical = genericDao.findById(EhfOncologyMedical.class, String.class, resList.get(0).getID());
			}
		}else{
			ehfOncologyMedical = genericDao.findById(EhfOncologyMedical.class, String.class, proformaId);
		}
		
		if(ehfOncologyMedical !=null){
			List<LabelBean> medcialList = new ArrayList<LabelBean>();
			query = new StringBuffer();
		    query.append(" select proc_name as VALUE,ICD_CAT_CODE AS ID,b.dosage as TYPE,b.frequency as QUANTITY from  EHFM_ONCOLOGY_DRUGS A,Ehf_Oncology_Drugs B WHERE A.ICD_CAT_CODE = B.DRUG_ID AND B.PROFORMA_ID=? order by proc_name ");
		    medcialList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(), new String[]{ehfOncologyMedical.getProformaId()});
		    lResMap.put("ehfOncologyMedical", ehfOncologyMedical);
			lResMap.put("ehfOncologyDrugsList", medcialList);
			lResMap.put("formSubmit", ehfOncologyMedical.getStatus().equalsIgnoreCase("CD7311") ? "P" : "Y");
			lResMap.put("proformaId", ehfOncologyMedical.getProformaId());
		}else{
			lResMap.put("formSubmit","N");
		}
		query = new StringBuffer();
		query.append(" SELECT eo.ORGAN_ID AS ID, eo.ORGAN_NAME AS NAME FROM EHFM_ORGANS eo WHERE eo.ACTIVE_YN = 'Y' ");
		List<LabelBean> organsList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		lResMap.put("organsList", organsList);
		return lResMap;
	}
	
	@Override
	public List<LabelBean> getOpClaimCasesList(HashMap<java.lang.String, java.lang.String> hashMap) {
		String claimDt  = hashMap.get("claimDt");
		String patientId  = hashMap.get("patientId");
		String crNo = hashMap.get("crNo"); 
		String letterNo = hashMap.get("letterNo");;
		String claimStatus = hashMap.get("claimStatus");
		String claimSubmittedNo = hashMap.get("claimSubmittedNo");
		String cardNo = hashMap.get("cardNo");
		StringBuffer query = new StringBuffer();
		List<LabelBean> resList = null;
		String[] args = null;
		Map lTempQryVal = new HashMap();
        int cnt=0; 
        boolean hasFilter = false;
        
        try{
        	query.append(" SELECT CLAIM_NO as PATIENTID,  CR_NO AS CARDNO, PATIENT_NAME AS PNAME, LETTER_NO as LETTERNO, CLAIM_ORG as CLAIMORG,eop.OP_CLAIM_SEQ AS CLAIMNO, ");
        	query.append(" TO_CHAR(CLAIM_DT, 'DD/MM/YYYY HH24:MI:SS') AS CLAIMDT, TOTAL_BILL AS AMOUNT, TO_CHAR(EOP.MEDCO_CLM_DT, 'DD/MM/YYYY HH24:MI:SS') AS CRTDT, ep.CARD_NO AS EHFCARDNO,ecd.CMB_DTL_NAME AS VALUE ");
        	query.append(" FROM EHF_OPD_PATIENTS eop, EHF_PATIENT ep,EHFM_CMB_DTLS ecd WHERE EP.PATIENT_ID = EOP.CLAIM_NO AND ecd.CMB_DTL_ID = eop.CLAIM_STATUS AND ep.REG_HOSP_ID = 'EHS34' ");//Chandana -7845- Jonied patient table and this condition, and added nims hosp id condition
        	
        	if(claimStatus != null && !"-1".equalsIgnoreCase(claimStatus) && !"all".equalsIgnoreCase(claimStatus)){//Chandana - 8618 - Added this
        		query.append(" AND EOP.CLAIM_STATUS = ? ");
        		lTempQryVal.put(new Integer(++cnt), claimStatus);
        		hasFilter = true;
        	}
        	if(claimStatus != null && "all".equalsIgnoreCase(claimStatus)){//Chandana - 8618 - Added this if condition for shwoing the cases in all status's
        		hasFilter = true;
        	}
        	if(claimDt != null && !"".equalsIgnoreCase(claimDt)){
        		query.append(" AND TO_CHAR(EOP.CLAIM_DT, 'DD/MM/YYYY') = ? ");
        		lTempQryVal.put(new Integer(++cnt), claimDt);
        		hasFilter = true;
        	}
        	
        	if(patientId != null && !"".equalsIgnoreCase(patientId)){
        		query.append(" AND EOP.CLAIM_NO  = ?  ");
        		lTempQryVal.put(new Integer(++cnt), patientId);
        		hasFilter = true;
        	}
        	
        	if(crNo != null && !"".equalsIgnoreCase(crNo)){
        		query.append(" AND EOP.CR_NO = ? ");
        		lTempQryVal.put(new Integer(++cnt), crNo);
        		hasFilter = true;
        	}
        	
        	if(claimSubmittedNo != null && !"".equalsIgnoreCase(claimSubmittedNo)){
        		query.append(" AND EOP.OP_CLAIM_SEQ = ? ");
        		lTempQryVal.put(new Integer(++cnt), claimSubmittedNo);
        		hasFilter = true;
        	}
        	
        	if(letterNo != null && !"".equalsIgnoreCase(letterNo)){
        		query.append(" AND EOP.LETTER_NO = ? ");
        		lTempQryVal.put(new Integer(++cnt), letterNo);
        		hasFilter = true;
        	}
        	if(cardNo != null && !"".equals(cardNo)){
        		query.append(" AND ep.CARD_NO = ? ");
        		lTempQryVal.put(new Integer(++cnt), cardNo);
        		hasFilter = true;
        	}
        	if (!hasFilter) {
        		query.append(" AND EOP.CLAIM_STATUS IN  ('CD7303','CD7329','CD7315') ");//Chandana - 8036 - added in condition
        	}
        	query.append(" ORDER BY EOP.CRT_DT ASC ");
        	
        	args = new String[cnt];
		    for (int i = 1; i <= cnt; i++) {
		        args[i-1] = lTempQryVal.get(new Integer(i)).toString();
		    }
		    
		    resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(), args);
		    
        }catch(Exception e){
        	e.printStackTrace();
        }
		return resList;
	}
	
	@Override
	public List<LabelBean> getOpClaimBillDtls(HashMap hashMap) {
		String patientId = null;
		String claimAssignFlag = null;
		if(hashMap.get("claimAssignFlag") != null)
			claimAssignFlag = (String) hashMap.get("claimAssignFlag");
		if(hashMap.get("patientId") != null)
			patientId  = hashMap.get("patientId").toString();
		StringBuffer query = new StringBuffer();
		StringBuffer query1 = new StringBuffer();
		List<LabelBean> resList = null;
		List<LabelBean> resList1 = null;
		String[] args = null;
		Map lTempQryVal = new HashMap();
        int cnt=0; 
        
        try{
        	query1.append(" SELECT OP_CLAIM_SEQ AS ID FROM EHF_OPD_PATIENTS WHERE CLAIM_NO ='"+patientId+"' ");
        	resList1 = genericDao.executeSQLQueryList(LabelBean.class, query1.toString());
        	if(!resList1.isEmpty() || "".equals(resList1))
        		claimAssignFlag="Y";
        	//Chandana - 8599 - Added seq_id and cr_no in the existing record
        	query.append(" SELECT DISTINCT eobd.BILL_NO AS billNo,eoba.seq_id AS DIFF, eoba.CR_NO as ID, eobd.SAMPLE_NO AS sampleNo, TO_CHAR(eobd.BILL_DT, 'DD/MM/YYYY HH24:MI:SS') AS billDt, eobd.TYPE, eobd.AMOUNT, eobd.QUANTITY as COUNT, EOP.TOTAL_BILL AS TOTALAMT ");
        	//Chandana - 8602 - Added CRT_DT to get the bill received date from the api
        	query.append(" ,eoba.DOC_PATH AS ATTACHPATH, eop.CLAIM_STATUS AS CASESTATUS, TO_CHAR(eobd.CRT_DT, 'DD/MM/YYYY HH24:MI:SS') AS CRTDT FROM EHF_OPD_BILL_DTLS eobd, EHF_OPD_PATIENTS eop, EHF_OPD_BILL_ATTACHMENT eoba WHERE eobd.CLAIM_NO = EOP.CLAIM_NO ");
        	query.append(" AND eoba.CLAIM_NO = eobd.CLAIM_NO AND eoba.CR_NO = eobd.CR_NO AND eobd.BILL_NO = eoba.BILL_NO ");//Chandana -7845- Added to get the attachments path //AND eoba.BILL_NO = eobd.BILL_NO
        	if(claimAssignFlag == null || "".equalsIgnoreCase(claimAssignFlag)){//Chandana - 8036 - added bill number join in the above line as multiple bills same bill number is coming
        		query.append(" AND EOP.OP_CLAIM_SEQ IS NULL ");
        	}
        	query.append(" AND eobd.STATUS = 'C' AND eobd.CLAIM_NO = ? ORDER BY eoba.SEQ_ID ");
        	lTempQryVal.put(new Integer(++cnt), patientId);
        	
        	args = new String[cnt];
		    for (int i = 1; i <= cnt; i++) {
		        args[i-1] = lTempQryVal.get(new Integer(i)).toString();
		    }
		    
		    resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(), args);
		    
		    if(resList != null &&  !resList.isEmpty() && resList.size() > 0){
		    	for(LabelBean opdDtls : resList){
		    		String billNo = opdDtls.getBILLNO();
		    		opdDtls.setOPCLAIMATTACHLIST(getOpBillAttachDtls(billNo));
		    	}
		    }
        	
        }catch(Exception le){
        	le.printStackTrace();
        }
        
		return resList;
	}
	//Chandana -7845- new method to get the claim status
	@Override
	public String getClaimStatus(String patientId) {
		String status = null;
		StringBuffer query = new StringBuffer();
		List<LabelBean> resList = null;        
        try{
        	query.append(" SELECT DISTINCT CLAIM_STATUS ID FROM EHF_OPD_PATIENTS WHERE CLAIM_NO ='"+patientId+"' ");
		    resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		    if(resList != null &&  !resList.isEmpty() && resList.size() > 0){
		    	for(LabelBean opdDtls : resList){
		    		status = resList.get(0).getID();
		    	}
		    }
        }catch(Exception le){
        	le.printStackTrace();
        }
		return status;
	}
	//Chandana - 8036 - Added below new method
	public String getClaimNum(String patientId) {
		String claimNo = null;
		StringBuffer query = new StringBuffer();
		List<LabelBean> resList = null;        
        try{
        	query.append(" SELECT DISTINCT OP_CLAIM_SEQ AS ID FROM EHF_OPD_PATIENTS WHERE CLAIM_NO ='"+patientId+"' ");
		    resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		    if(resList != null &&  !resList.isEmpty() && resList.size() > 0){
		    	for(LabelBean opdDtls : resList){
		    		claimNo = resList.get(0).getID();
		    	}
		    }
        }catch(Exception le){
        	le.printStackTrace();
        }
		return claimNo;
	}
	//Chandana -7845- new method to get the next status
		@Override
		public String getNextStatus(String currentStatus, String actiondone) {
			String status = null;
			String userRole = null;//Chandana - 8602 - Added new variable for userrole
			StringBuffer query = new StringBuffer();
			List<LabelBean> resList = null;        
	        try{
	        	//Chandana - 8602 - Added Current_role in select statement to get the role id 
	        	query.append(" SELECT NEXT_STATUS ID, CURRENT_ROLE VALUE FROM EHFM_WORKFLOW_STATUS WHERE CURRENT_STATUS ='"+currentStatus+"' AND ACTION_DONE = '"+actiondone+"' ");
			    resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
			    if(resList != null &&  !resList.isEmpty() && resList.size() > 0){
			    		status = resList.get(0).getID();
			    		userRole = resList.get(0).getVALUE();//Chandana - 8602 - Added this userRole and sending it to action
			    }
	        }catch(Exception le){
	        	le.printStackTrace();
	        }
			return status + "~" + userRole;//Chandana - 8602 - Added userRole
	}
	private List<LabelBean> getOpBillAttachDtls(String billNo) {
		StringBuffer query = new StringBuffer();
		List<LabelBean> resList = null;
		String[] args = null;
		Map<Integer, String> lTempQryVal = new HashMap();
        int cnt=0; 
        
        try{
        	query.append(" SELECT RECORD_NAME AS attachName, DOC_PATH AS attachPath FROM EHF_OPD_BILL_ATTACHMENT  WHERE STATUS = 'C' AND bill_no = ? ");
        	lTempQryVal.put(++cnt, billNo);
        	
        	args = new String[cnt];
		    for (int i = 1; i <= cnt; i++) {
		        args[i-1] = lTempQryVal.get(i).toString();
		    }
		    
		    resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(), args);
		    
        }catch(Exception le){
        	le.printStackTrace();
        }		
		return resList;
	}

	@Transactional
	public String updateMedcoOpClaim(HashMap hashMap) {
		String opdClaimApprovedSeq = null;
		String patientId = null;
		String userId = null;
		String remarks = null;
		String nextStatus = null;
		StringBuffer lsb = new StringBuffer();
		double billAmt = 0;
		String status = null, userRole = null;//Chandana - 8602 - Added new variable for user role
		
		if(hashMap.get("patientId") != null && hashMap.get("userId") != null){
			patientId = hashMap.get("patientId").toString();
			userId = hashMap.get("userId").toString();
		}
		if(hashMap.get("nextStatus") != null)//Chandana - 7845
			nextStatus = hashMap.get("nextStatus").toString();
		if(hashMap.get("patientId") != null)
			remarks = hashMap.get("remarks").toString();
		if(!"Submit".equalsIgnoreCase(status))//Chandana - 8036 - Added this based on the the status condition seq id i am getting 
			opdClaimApprovedSeq = getClaimNum(patientId);
		if(hashMap.get("status") != null)//Chandana - 7845
			status = hashMap.get("status").toString();
		if(hashMap.get("userRole") != null)//Chandana - 8602 - Added this to get the userrole
			userRole = hashMap.get("userRole").toString();
		try{
			//if("".equals(opdClaimApprovedSeq) || opdClaimApprovedSeq==null){//Chandana - 8036 - Added this condition to not generate the op claim number if medco resubmitted the 
			if("Submit".equalsIgnoreCase(status))	{
				lsb.append(" SELECT 'OPC'||lpad(EHF_OP_CLAIM_SEQ.NEXTVAL,5,'0') ID from DUAL ");
				List<LabelBean> opdSeq =  genericDao.executeSQLQueryList(LabelBean.class, lsb.toString());
				opdClaimApprovedSeq = opdSeq.get(0).getID();
			}
			List<EhfOpdPatient> ehfOpdPatientLst = new ArrayList<EhfOpdPatient>();
			if(patientId != null && !"".equalsIgnoreCase(patientId)){
				List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("claimNo", patientId, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				ehfOpdPatientLst = genericDao.findAllByCriteria(EhfOpdPatient.class, criteriaList);
				
				if(!ehfOpdPatientLst.isEmpty() && ehfOpdPatientLst.size() > 0){
					billAmt = ehfOpdPatientLst.get(0).getTotalBill();
					for(EhfOpdPatient ehfopdDtls : ehfOpdPatientLst){
						ehfopdDtls.setClaimStatus(nextStatus);//Chandana - 7845 - added the nextStatus, previously status has hardcoded
						if("Submit".equalsIgnoreCase(status)){
							ehfopdDtls.setClaimDt(new Date());//Chandana - 7845 - Added this to save the claimdate once the medco acted on the record
							ehfopdDtls.setOpClaimSeq(opdClaimApprovedSeq);
							ehfopdDtls.setMedcoClmDt(new Date());
						}
						else if("Resubmit".equalsIgnoreCase(status)){
							ehfopdDtls.setLstUpdDt(new Date());
							ehfopdDtls.setLstUpdUsr(userId);
						}
						genericDao.save(ehfopdDtls);
					}
				}
			}			
			
			EhfOpdClaimDtls ehfOpdClaimDtls = new EhfOpdClaimDtls();
			ehfOpdClaimDtls.setOpClaimId(opdClaimApprovedSeq);
			ehfOpdClaimDtls.setClaimStatus(nextStatus);//Chandana - 7845
			//ehfOpdClaimDtls.setMedcoRemarks(remarks);
			ehfOpdClaimDtls.setBillAmount(billAmt);
			ehfOpdClaimDtls.setClaimAmount(billAmt);
			ehfOpdClaimDtls.setCrtUsr(userId);
			ehfOpdClaimDtls.setCrtDt(new Date());
			genericDao.save(ehfOpdClaimDtls);
			
			boolean insertAuditRecord = insertAuditRecord(opdClaimApprovedSeq, nextStatus, remarks, userId,patientId, billAmt, userRole);//Chandana -7845- nextStatus and patientIdthis from status, 8602 - Added userRole id to the existing method
			
			genericDao.flush();
			
		}catch(Exception le){
			le.printStackTrace();
		}
		genericDao.flush();
		return opdClaimApprovedSeq;
	}
	
	@Override
	public String checkOpClaimNo(String patientId) {
		String opClaimNo = null;
		StringBuffer query = new StringBuffer();
		String[] args = null;
		Map<Integer, String> lTempQryVal = new HashMap();
        int cnt=0; 
        List<LabelBean> resLst = null;
        
        try{
        	query.append(" SELECT EOP.OP_CLAIM_SEQ AS CLAIMNO FROM EHF_OPD_PATIENTS eop WHERE EOP.CLAIM_NO = ? ");
        	lTempQryVal.put(++cnt, patientId);
        	
        	args = new String[cnt];
		    for (int i = 1; i <= cnt; i++) {
		        args[i-1] = lTempQryVal.get(i).toString();
		    }
		    
		    resLst = genericDao.executeSQLQueryList(LabelBean.class, query.toString(), args);
		    if(resLst != null){
		    	opClaimNo = resLst.get(0).getCLAIMNO();
		    }
		}catch(Exception le){
			le.printStackTrace();
		}
		return opClaimNo;
	}
	@Override
	public List<java.lang.String> getNimsOpClaimCases(String status, String roleId, String userId) {//Chandana - 8602 - Added userid in the existing method
		List<String> resultLst = new ArrayList<String>();
		StringBuffer query = new StringBuffer();
		List<LabelBean> labelLst = null;
		List<LabelBean> labelLst1 = null;
		String[] args = null;
		Map<Integer, String> lTempQryVal = new HashMap();
        int cnt=0; 
		
		try{
			//Chandana - 8606 - Added CR_NO column to get the cr number
			query.append(" SELECT DISTINCT eop.OP_CLAIM_SEQ AS CLAIMNO, EOP.CLAIM_NO AS PatientId, eop.CR_NO AS VALUE, eop.CLAIM_STATUS AS ID FROM EHF_OPD_PATIENTS eop, EHF_PATIENT ep WHERE eop.CLAIM_NO = ep.PATIENT_ID ");
			if("GP3".equalsIgnoreCase(roleId))
				query.append(" AND EOP.CLAIM_STATUS IN ('CD7328','CD7334') ");//Chandana - 8036 - Added in condition to
			else if("GP9".equalsIgnoreCase(roleId) || "GP29".equalsIgnoreCase(roleId))//Chandana - added for GP29, as some EO having CH role
				query.append(" AND EOP.CLAIM_STATUS IN ('CD7330','CD7316','CD7343') "); //Chandana - 8602 - Added this CD7343 status for getting back from ACO to CH
			
			query.append(" ORDER BY EOP.OP_CLAIM_SEQ ASC ");
			
			args = new String[cnt];
		    for (int i = 1; i <= cnt; i++) {
		        args[i-1] = lTempQryVal.get(i).toString();
		    }
			labelLst = genericDao.executeSQLQueryList(LabelBean.class, query.toString(), args);
			if(!labelLst.isEmpty() && labelLst.size() > 0){
				for(LabelBean ls : labelLst){
						resultLst.add(ls.getCLAIMNO().toString() + "~" + ls.getPATIENTID() + "~" + ls.getVALUE() + "~" + ls.getID());//Chandana - 22-10-25 - Added getVALUE() to sent the cr number back to action, 8602 - Added getID to get the claim status and sending it back to action class
				}
			}
			
		}catch(Exception le){
			le.printStackTrace();
		}
		return resultLst;
	}
	@Transactional
	public java.lang.String updateCHOpClaim(HashMap hashMap) {
		String result = null;
		String claimStatus = hashMap.get("nextStatus").toString();
		String opdClaimNo = hashMap.get("opdClaimNo").toString();
		String status = hashMap.get("status").toString();
		String remarks = hashMap.get("remarks").toString();
		String deductAmt = hashMap.get("deductAmt").toString();
		String finalAmt = hashMap.get("finalAmt").toString();
		String userId = hashMap.get("userId").toString();
		String patientId = hashMap.get("patientId").toString();
		String userRole = hashMap.get("userRole").toString();
		double deductAmount = 0;
		double finalAmount = 0;
		if(!"".equalsIgnoreCase(deductAmt))
			deductAmount = Double.parseDouble(deductAmt);
		if(finalAmt != null)
			finalAmount = Double.parseDouble(finalAmt);
		try{
			List<EhfOpdPatient> ehfOpdPatientLst = new ArrayList<EhfOpdPatient>();
			if(opdClaimNo != null && !"".equalsIgnoreCase(opdClaimNo)){
				List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("opClaimSeq", opdClaimNo, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				ehfOpdPatientLst = genericDao.findAllByCriteria(EhfOpdPatient.class, criteriaList);
				
				if(ehfOpdPatientLst != null && !ehfOpdPatientLst.isEmpty()){
					for(EhfOpdPatient ehfopdDtls : ehfOpdPatientLst){
						ehfopdDtls.setClaimStatus(claimStatus);												
						genericDao.save(ehfopdDtls);
						result = "Success";
					}
				}
				/*boolean insertAuditRecord = insertAuditRecord(opdClaimNo, claimStatus, remarks, userId, patientId);*/
				List<EhfOpdClaimDtls> ehfOpdClaimDtlLst = new ArrayList<EhfOpdClaimDtls>();
				criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("opClaimId", opdClaimNo, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				ehfOpdClaimDtlLst = genericDao.findAllByCriteria(EhfOpdClaimDtls.class, criteriaList);
				
				if(!ehfOpdClaimDtlLst.isEmpty()){
					for(EhfOpdClaimDtls claimDtls : ehfOpdClaimDtlLst){
						claimDtls.setClaimStatus(claimStatus);
						claimDtls.setClaimAmount(finalAmount);
						if(!"Return".equalsIgnoreCase(status)){//Chandana - 8602 - Added this if condition for not updating the 3 columnd if the action is Return
							claimDtls.setChDeductAmount(deductAmount);
							claimDtls.setChApprvUsr(userId);
							claimDtls.setChApprvDt(new Date());
						}
						genericDao.save(claimDtls);
						result = "Success";
					}
				}
				boolean insertAuditRecord = insertAuditRecord(opdClaimNo, claimStatus, remarks, userId, patientId, finalAmount, userRole);//Chandana - 8602 - Added userRole param in the existing method call
			}
			
		}catch(Exception le){
			le.printStackTrace();
			result = "Fail";
		}
		return result;
	}
	//Chandana - 8036 - below method for updating the nims op cliams by PEX
	@Transactional
	public java.lang.String updatePEXOpClaim(HashMap hashMap) {
		String result = null;
		String opdClaimNo = hashMap.get("opdClaimNo").toString();
		String status = hashMap.get("status").toString();
		String remarks = hashMap.get("remarks").toString();
		String userId = hashMap.get("userId").toString();
		String claimStatus = hashMap.get("nextStatus").toString();
		//String patientId = null;
		String patientId = hashMap.get("patientId").toString();
		String finalAmount = hashMap.get("finalAmt").toString();
		String userRole = hashMap.get("userRole").toString();//Chandana - 8602 - Getting userRole 
		
		double finalAmt = Double.parseDouble(finalAmount);
		try{
			List<EhfOpdPatient> ehfOpdPatientLst = new ArrayList<EhfOpdPatient>();
			if(opdClaimNo != null && !"".equalsIgnoreCase(opdClaimNo)){
				List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("opClaimSeq", opdClaimNo, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				ehfOpdPatientLst = genericDao.findAllByCriteria(EhfOpdPatient.class, criteriaList);
				if(ehfOpdPatientLst != null && !ehfOpdPatientLst.isEmpty()){
					for(EhfOpdPatient ehfopdDtls : ehfOpdPatientLst){
						ehfopdDtls.setClaimStatus(claimStatus);												
						genericDao.save(ehfopdDtls);
						result = "Success";
					}
				}
				/*boolean insertAuditRecord = insertAuditRecord(opdClaimNo, claimStatus, remarks, userId,patientId);*/
				List<EhfOpdClaimDtls> ehfOpdClaimDtlLst = new ArrayList<EhfOpdClaimDtls>();
				criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("opClaimId", opdClaimNo, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				ehfOpdClaimDtlLst = genericDao.findAllByCriteria(EhfOpdClaimDtls.class, criteriaList);
				if(!ehfOpdClaimDtlLst.isEmpty()){
					for(EhfOpdClaimDtls claimDtls : ehfOpdClaimDtlLst){
						claimDtls.setClaimStatus(claimStatus);
						//claimDtls.setChRemarks(remarks);//Chandana - 8602 - Commented three settings 
						/*claimDtls.setChApprvUsr(userId);
						claimDtls.setChApprvDt(new Date());*/
						genericDao.save(claimDtls);
						result = "Success";
					}
				}
				boolean insertAuditRecord = insertAuditRecord(opdClaimNo, claimStatus, remarks, userId,patientId,finalAmt, userRole);//Chandana - 8602 - Added userRole parameter to existing method
			}
		}catch(Exception le){
			le.printStackTrace();
			result = "Fail";
		}
		return result;
	}
	//Chandana - 8602 - Added userRole parameter in the below method
	private boolean insertAuditRecord(String opdClaimSeq, String status, String remarks, String userId, String patientId, Double claimAmnt, String userRole) {
		boolean response = false;
		try{
			List<EhfOpdClaimAudit> ehfOpdClaimAuditLst = new ArrayList<EhfOpdClaimAudit>();
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			criteriaList.add(new GenericDAOQueryCriteria("id.opClaimSeq", opdClaimSeq, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			ehfOpdClaimAuditLst = genericDao.findAllByCriteria(EhfOpdClaimAudit.class, criteriaList);
			double maxActOrder = 0;
			if(!ehfOpdClaimAuditLst.isEmpty()){
				for(EhfOpdClaimAudit audit : ehfOpdClaimAuditLst){
					double currentActOrder = audit.getId().getActOrder();
					if(currentActOrder > maxActOrder){
						maxActOrder = currentActOrder;
					}
				}
			}
			double newActOrder = maxActOrder +1;
			EhfOpdClaimAuditPK ehfOpdClaimAuditPK = new EhfOpdClaimAuditPK();
			ehfOpdClaimAuditPK.setOpClaimSeq(opdClaimSeq);
			ehfOpdClaimAuditPK.setActOrder(newActOrder);
			EhfOpdClaimAudit ehfOpdClaimAudit = new EhfOpdClaimAudit();
			ehfOpdClaimAudit.setId(ehfOpdClaimAuditPK);
			ehfOpdClaimAudit.setClaimStatus(status);
			ehfOpdClaimAudit.setPatientId(patientId);
			ehfOpdClaimAudit.setRemarks(remarks);
			ehfOpdClaimAudit.setActBy(userId);
			ehfOpdClaimAudit.setCrtUsr(userId);
			ehfOpdClaimAudit.setCrtDt(new Date());	
			ehfOpdClaimAudit.setClaimAmount(claimAmnt);
			ehfOpdClaimAudit.setUserRole(userRole);
			genericDao.save(ehfOpdClaimAudit);
			response = true;
		}catch(Exception le){
			le.printStackTrace();
			response = false;
		}
		return response;
	}
	//Chandana - 8618 - New method for getting the userhospid
	@Override
	public String getUserHospId(String lStrUserId, String roleId, String schemeId) {
		String hospId = "";
		List<LabelBean> userHospList = new ArrayList<LabelBean>();
		StringBuffer query = new StringBuffer();
		String[] args = null;
		Map<Integer, String> lTempQryVal = new HashMap();
        int cnt=0;
		try
		{
			if("GP2".equalsIgnoreCase(roleId)){
				query.append(" select hosp_id as ID from ehfm_medco_dtls WHERE medco_id=? and end_dt is null and active_yn='Y' and scheme=? ");
			}else{
				query.append(" SELECT HOSP_ID AS ID FROM EHFM_HOSP_MITHRA_DTLS ehmd WHERE MITHRA_ID = ? AND END_DT IS NULL AND ACTIVE_YN ='Y' AND scheme=? ");
			}
			lTempQryVal.put(++cnt, lStrUserId);
			lTempQryVal.put(++cnt, schemeId);
			
		    args = new String[cnt];
		    for (int i = 1; i <= cnt; i++) {
		        args[i-1] = lTempQryVal.get(i).toString();
		    }
		    
		    userHospList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(), args);
		    
			if(userHospList.size()>0){
				if(userHospList != null)
					hospId=userHospList.get(0).getID();
			}
		}catch(Exception le){
			le.printStackTrace();
		}
		return hospId;
	}
	//Chandana - 8618 - New method for checking the registered hosp and 30 days condition
	@Override
	public String checkPatientIsRegisteredInNims(String cardNo) {
		String result = "";
		List<LabelBean> resultList = null;
		StringBuffer query = new StringBuffer();
		String[] args = null;
		Map<Integer, String> lTempQryVal = new HashMap();
        int cnt=0;
		try
		{
			query.append(" SELECT PATIENT_ID || '~' || TO_CHAR(CRT_DT +30, 'dd-MM-yyyy') AS ID FROM EHF_PATIENT WHERE CRT_DT >=SYSDATE-30 AND PATIENT_IPOP IN ('OP','RG') AND REG_HOSP_ID = 'EHS34' AND CARD_NO = ? ORDER BY CRT_DT DESC ");
			lTempQryVal.put(++cnt, cardNo);
		    args = new String[cnt];
		    for (int i = 1; i <= cnt; i++) {
		        args[i-1] = lTempQryVal.get(i).toString();
		    }
		    
		    resultList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(), args);
		    
			if(resultList.size()>0){
				if(resultList != null)
					result=resultList.get(0).getID();
			}
		}catch(Exception le){
			le.printStackTrace();
		}
		return result;
	}

	@Override
	public List<LabelBean> getMoleculesForOrgan(String organId) {
		List<LabelBean> moleculesList = new ArrayList<LabelBean>();
		StringBuffer query = new StringBuffer();
		try{
			query.append(" SELECT mol_name AS NAME FROM TABLE(FN_GET_ONC_MOL_ORG_DTLS(?)) ");
			moleculesList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(), new String[]{organId});
		}catch(Exception le){
			le.printStackTrace();
		}
		return moleculesList;
	}
	
	@Override
	public List<LabelBean> getProformaHighEndApproval(Map<String, Object> lResMap) throws Exception{
		List<LabelBean> patientList = new ArrayList<LabelBean>();
		StringBuffer query = new StringBuffer();
		String[] args = null;
        Map lTempQryVal = new HashMap();
        int cnt = 0; 
		try{
			String patientId = (String)lResMap.get("patientId");
			String userName = (String)lResMap.get("userName");
			String cardNo = (String) lResMap.get("cardNo");
			String roleId = (String) lResMap.get("roleId");
			String caseType = (String) lResMap.get("caseType");
			String loggedUserHospId = null;
			if(lResMap.get("loggedUserHospId") != null)
				loggedUserHospId  = (String) lResMap.get("loggedUserHospId");
			
		    query.append(" select eoca.PATIENT_ID AS PATIENTID, eom.PROFORMA_ID AS SEQID, ep.NAME AS PNAME, ep.card_no AS CARDNO, eh.hosp_name AS HOSPNAME, ");
		    query.append(" to_char(EOCA.crt_dt, 'dd/mm/yyyy hh24:mi:ss') AS CRTDT, EOCA.status AS CASESTATUS, ep.AGE AS COUNT, ep.GENDER AS EGENDER,  ");
		    query.append(" CASE WHEN EXISTS (SELECT 1 FROM EHF_ONCOLOGY_TREATMENT eot WHERE eot.PATIENT_ID = ep.PATIENT_ID) THEN 'YES' ELSE 'NO' END AS ONCOLOGYFLAG ");
		    if("GP2".equalsIgnoreCase(roleId) && "CmteRejected".equalsIgnoreCase(caseType))
		    	query.append(" ,eoca2.REMARKS AS REMARKS ");
		    query.append(" FROM EHF_ONCOLOGY_MEDICAL eom, EHF_ONCOLOGY_CMTE_APPRVL eoca, EHF_PATIENT ep, EHFM_HOSPITALS eh, EHF_CASE ec "); 
		    if("GP2".equalsIgnoreCase(roleId) && "CmteRejected".equalsIgnoreCase(caseType))
		    	query.append(" ,EHF_ONCOLOGY_CMTE_ADUIT eoca2 ");
		    query.append(" WHERE eom.REG_NO = eh.HOSP_ID AND eom.PROFORMA_ID = EOCA.PROFORMA_ID AND EOCA.PATIENT_ID = ep.PATIENT_ID AND ep.PATIENT_ID = ec.CASE_PATIENT_NO ");
		    
		    if("GP2".equalsIgnoreCase(roleId)){
		    	if("InProgress".equalsIgnoreCase(caseType)){
		    		query.append(" AND EOCA.STATUS IN ('CD7307','CD7308') and EOM.REG_NO = ? ");
		    	}else if("CmteRejected".equalsIgnoreCase(caseType)){
		    		query.append(" AND EOCA.PATIENT_ID  = EOCA2.PATIENT_ID AND EOCA2.STATUS='CD7310' and EOM.REG_NO = ? ");
		    	}else if("PreauthInit".equalsIgnoreCase(caseType)){
		    		query.append(" AND EOCA.STATUS='CD2' and EOM.REG_NO = ? ");
		    	}else{
		    		query.append(" AND EOCA.STATUS = 'CD7309' and EOM.REG_NO = ? ");
		    	}
		    	lTempQryVal.put(new Integer(++cnt),loggedUserHospId);
		    }else{		    	
		    	if("N".equalsIgnoreCase(caseType))
		    		query.append(" AND EOCA.STATUS ='CD7310' ");
		    	else if(caseType == null || !"Y".equalsIgnoreCase(caseType))
		    		query.append(" AND EOCA.STATUS IN ('CD7307','CD7308') ");
		    	
		    	if(userName.toUpperCase().contains("NIMS")){
			    	query.append(" AND EOCA.NIMS_APPRVL=? ");
			    	if("Y".equalsIgnoreCase(caseType))
			    		lTempQryVal.put(new Integer(++cnt),"Y");
			    	else
			    		lTempQryVal.put(new Integer(++cnt),"N");
			    }else if(userName.toUpperCase().contains("MNJIO")){
			    	query.append(" AND EOCA.MNJIO_APPRVL=? ");
			    	if("Y".equalsIgnoreCase(caseType))
			    		lTempQryVal.put(new Integer(++cnt),"Y");
			    	else
			    		lTempQryVal.put(new Integer(++cnt),"N");
			    }
		    }
		    
		    if(patientId != null && !"".equalsIgnoreCase(patientId) && !"null".equalsIgnoreCase(patientId)){
		    	query.append(" and EOCA.patient_id = ? ");
		    	lTempQryVal.put(new Integer(++cnt), patientId);
		    }
		    
		    if(cardNo != null && !"".equalsIgnoreCase(cardNo) && !"null".equalsIgnoreCase(cardNo)){
		    	query.append(" and EOCA.card_no = ? ");
		    	lTempQryVal.put(new Integer(++cnt), cardNo);
		    }
		    query.append(" order by EOCA.crt_dt ASC ");
		    
		    args = new String[cnt];
		    for (int i = 1; i <= cnt; i++) {
		        args[i-1] = lTempQryVal.get(new Integer(i)).toString();
		    }
		    
		    patientList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(), args);
		    
		}catch(Exception le){
			le.printStackTrace();
		}
		return patientList;
	}
	
	@Override
	public String saveOncologyCMTEResp(Map<String, Object> hashMap) throws Exception {
		String patientId = hashMap.get("patientId").toString();
		String remarks = hashMap.get("remarks").toString();
		String status = hashMap.get("status").toString();
		String userId = hashMap.get("userId").toString();
		String userName = hashMap.get("userName").toString();
		String statusCode = null, response = null, medcoMobileNum = null, templateId=null, lStrMsg=null;
		EhfOncologyMedical ehfOncologyMedical = new EhfOncologyMedical();
		EhfOncologyCmteAudit ehfOncologyCmteAudit = new EhfOncologyCmteAudit();
		EhfOncologyCmteAuditPK ehfOncologyCmteAuditPK = new EhfOncologyCmteAuditPK();
		List<EhfOncologyCmteAudit> ehfOncologyCmteAuditLst = new ArrayList<EhfOncologyCmteAudit>();
		EhfOncologyCmteApprvl ehfOncologyCmteApprvl = new EhfOncologyCmteApprvl();
		StringBuffer query = new StringBuffer();
		EhfSmsData ehfSmsData = new EhfSmsData();
		SMSServices SMSServicesobj = new SMSServices();
		boolean sendSMS = false;
		try{
			ehfOncologyCmteApprvl = genericDao.findById(EhfOncologyCmteApprvl.class, String.class, patientId);
			if(ehfOncologyCmteApprvl != null){
				query.append(" SELECT EU.MOBILE_NO AS ID FROM EHF_CASE ec, EHFM_USERS eu WHERE EC.CRT_USR = EU.USER_ID AND EU.SERVICE_FLG ='Y' AND EC.CASE_PATIENT_NO = ? ");
				List<LabelBean> resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(), new String[]{patientId});
				if(resList !=null){
					medcoMobileNum = resList.get(0).getID();
				}
				if("Approve".equalsIgnoreCase(status)){
					if(userName.toUpperCase().contains("NIMS")){
						if("Y".equalsIgnoreCase(ehfOncologyCmteApprvl.getMnjioApprvl())){
							statusCode = "CD7309";
							sendSMS = true;
						}else{
							statusCode = "CD7308";
						}
						ehfOncologyCmteApprvl.setNimsApprvl("Y");						
				    }else if(userName.toUpperCase().contains("MNJIO")){
				    	if("Y".equalsIgnoreCase(ehfOncologyCmteApprvl.getNimsApprvl())){
							statusCode = "CD7309";
							sendSMS = true;
				    	}else{
							statusCode = "CD7308";
				    	}
				    	ehfOncologyCmteApprvl.setMnjioApprvl("Y");						
				    }
					if(sendSMS && medcoMobileNum != null && medcoMobileNum.length() == 10){
						lStrMsg = "Dear Medco, \n Your HEOD request with patient id "+patientId+" has been successfully approved by HEOD committee. You are further required to process preauthorization \n Rajiv Aarogyasri Healthcare Trust";
						templateId = "1407173709658512086";
						SMSServicesobj.sendSingleSMS(lStrMsg,medcoMobileNum,templateId);
					}
					response = "approved";
				}else if("Reject".equalsIgnoreCase(status)){
					statusCode = "CD7310";
					response = "rejected";
					ehfOncologyMedical = genericDao.findById(EhfOncologyMedical.class, String.class, ehfOncologyCmteApprvl.getProformaId());
					if(ehfOncologyMedical != null){
						ehfOncologyMedical.setStatus(statusCode);
						ehfOncologyMedical.setLstUpdUsr(userId);
						ehfOncologyMedical.setLstUpdDt(new Timestamp(System.currentTimeMillis()));
						genericDao.save(ehfOncologyMedical);
					}
					sendSMS = true;
					if(sendSMS && medcoMobileNum != null && medcoMobileNum.length() == 10){
						lStrMsg = "Dear Medco, \n Your HEOD request with patient id "+patientId+" has been rejected by the HEOD committee. The remarks can be viewed in the application \n Rajiv Aarogyasri Healthcare Trust";
						templateId = "1407173709703420833";
						SMSServicesobj.sendSingleSMS(lStrMsg,medcoMobileNum,templateId);
					}
				}
				ehfOncologyCmteApprvl.setStatus(statusCode);
				ehfOncologyCmteApprvl.setLstUpdUsr(userId);
				ehfOncologyCmteApprvl.setLstUpdDt(new Timestamp(System.currentTimeMillis()));
				genericDao.save(ehfOncologyCmteApprvl);
				
				List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("id.patientId", patientId, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				ehfOncologyCmteAuditLst = genericDao.findAllByCriteria(EhfOncologyCmteAudit.class, criteriaList);
				
				long maxActOrder = 0;
				if(!ehfOncologyCmteAuditLst.isEmpty()){
					for(EhfOncologyCmteAudit audit : ehfOncologyCmteAuditLst){
						long currentActOrder = audit.getId().getActOrder();
						if(currentActOrder > maxActOrder){
							maxActOrder = currentActOrder;
						}
					}
				}
				
				ehfOncologyCmteAuditPK.setPatientId(patientId);
				ehfOncologyCmteAuditPK.setActOrder(maxActOrder +1);
				
				ehfOncologyCmteAudit.setId(ehfOncologyCmteAuditPK);
				ehfOncologyCmteAudit.setStatus(statusCode);
				ehfOncologyCmteAudit.setRemarks(remarks);
				ehfOncologyCmteAudit.setActBy(userId);
				ehfOncologyCmteAudit.setCrtUsr(userId);
				ehfOncologyCmteAudit.setCrtDt(new Timestamp(System.currentTimeMillis()));				
				genericDao.save(ehfOncologyCmteAudit);
				
				if(sendSMS && medcoMobileNum !=null && medcoMobileNum.length() == 10){
					ehfSmsData.setCrtDt(new Timestamp(new Date().getTime()));
					ehfSmsData.setCrtUsr(userId);
					ehfSmsData.setUserId(userId);
					ehfSmsData.setPhoneNo(medcoMobileNum);
					ehfSmsData.setTemplateId(templateId);
					ehfSmsData.setSmsText(lStrMsg);
					genericDao.save(ehfSmsData);
				}				
			}
			
		}catch(Exception le){
			le.printStackTrace();
		}
		return response;
	}
	
	@Override
	public Map<String, Object> getOncologyEvaluationResponse(Map<String, Object> lResMap) {
		String patientId = lResMap.get("patientNo").toString();
		Map<String, Object> resMap = new HashMap<String, Object>();
		String startDate = null;
		try{
			EhfOncologyTreatment ehfOncologyTreatment = genericDao.findById(EhfOncologyTreatment.class, String.class, patientId);
			if(ehfOncologyTreatment != null){
				Date strtDate = ehfOncologyTreatment.getStartDate();
				if (strtDate != null) {
					startDate = new SimpleDateFormat("dd-MM-yyyy").format(strtDate);
				}
				resMap.put("startDate", startDate);
				resMap.put("isDataAvailable", ehfOncologyTreatment.getSubmit_Flag());
				resMap.put("ehfOncologyTreatment", ehfOncologyTreatment);
			}else{
				resMap.put("isDataAvailable", "N");
			}
		}catch(Exception le){
			le.printStackTrace();
		}
		return resMap;
	}
	@Transactional
	public Map<String, Object> saveOncologyEvaluationForm(PatientForm patientForm) {
		Map<String,Object> lResMap = new HashMap<String,Object>();
		EhfOncologyTreatment ehfOncologyTreatment = null;
		String activeFlag = "Y", inActiveFlag="N";
		String[] fileDtls = null;
		String uploadType = "evalutionType";
		Date startDate = null;
		try{
			String patientId = patientForm.getPatientNo();
			ehfOncologyTreatment = genericDao.findById(EhfOncologyTreatment.class, String.class, patientId);
			
			if(ehfOncologyTreatment != null){
				ehfOncologyTreatment.setLstUpdUsr(patientForm.getCrtUsr());
				ehfOncologyTreatment.setLstUpdDt(new Timestamp(System.currentTimeMillis()));
			}else{
				ehfOncologyTreatment =  new EhfOncologyTreatment();
				ehfOncologyTreatment.setCrtUsr(patientForm.getCrtUsr());
				ehfOncologyTreatment.setCrtDt(new Timestamp(System.currentTimeMillis()));
			}
			
			String strtDate = patientForm.getStartDate();
			if (strtDate != null && !strtDate.isEmpty()) {
				 startDate =  new SimpleDateFormat("dd-MM-yyyy").parse(strtDate);
			}
			ehfOncologyTreatment.setPatientId(patientId);
			ehfOncologyTreatment.setRecommendedTreatment(patientForm.getRecommendedTreatment());
			ehfOncologyTreatment.setDosageBsa(patientForm.getDosageBSA());
			ehfOncologyTreatment.setWeight(patientForm.getWeight());
			ehfOncologyTreatment.setHeight(patientForm.getHeight());
			ehfOncologyTreatment.setBsa(patientForm.getBodySurfaceArea());
			ehfOncologyTreatment.setStartDate(startDate);
			ehfOncologyTreatment.setStage(patientForm.getEcog());
			ehfOncologyTreatment.setTemperature(String.valueOf(patientForm.getTemperature()));
			ehfOncologyTreatment.setPulseRate(String.valueOf(patientForm.getPulseRate()));
			ehfOncologyTreatment.setRespiratoryRate(String.valueOf(patientForm.getRespiratoryRate()));
			ehfOncologyTreatment.setSpo2(String.valueOf(patientForm.getSpo2()));
			ehfOncologyTreatment.setBloodPressure(patientForm.getBloodPressure());
			ehfOncologyTreatment.setGeneralExamination(patientForm.getGeneralExamination());
			ehfOncologyTreatment.setCardiovascularSystem(patientForm.getCardioVascular());
			ehfOncologyTreatment.setRespiratorySystem(patientForm.getRespiratorySystem());
			ehfOncologyTreatment.setNervousSystem(patientForm.getNervousSystem());
			ehfOncologyTreatment.setAbdominalExamination(patientForm.getAbdominalExam());
			ehfOncologyTreatment.setMusculoskeletalSystem(patientForm.getMusculoSkeletal());
			ehfOncologyTreatment.setCbp(patientForm.getCbp());
			ehfOncologyTreatment.setCoagulationProfile(patientForm.getCoagulationProfile());
			ehfOncologyTreatment.setPt(patientForm.getPt());
			ehfOncologyTreatment.setAptt(patientForm.getAptt());
			ehfOncologyTreatment.setInr(patientForm.getInr());
			ehfOncologyTreatment.setSubmit_Flag("P");		
			
			if(activeFlag.equalsIgnoreCase(patientForm.getBoneMarrow())){
				if(patientForm.getBoneMarrowFile() != null && !patientForm.getBoneMarrowFile().getFileName().isEmpty()){
					deleteOldFile(ehfOncologyTreatment.getBoneMarrowAttachmentPath());
					
					fileDtls = fileUploadUtils(patientForm.getBoneMarrowFile(), patientId, uploadType);
					ehfOncologyTreatment.setBoneMarrowExam(patientForm.getBoneMarrow());
					ehfOncologyTreatment.setBoneMarrowAttachment(fileDtls[0]);
					ehfOncologyTreatment.setBoneMarrowAttachmentPath(fileDtls[1]);
				}				
			}else{
				ehfOncologyTreatment.setBoneMarrowExam(inActiveFlag);
				ehfOncologyTreatment.setBoneMarrowAttachment(null);
				ehfOncologyTreatment.setBoneMarrowAttachmentPath(null);
			}
			
			if(activeFlag.equalsIgnoreCase(patientForm.getRenal())){
				if(patientForm.getRenalFile() != null && !patientForm.getRenalFile().getFileName().isEmpty()){
					deleteOldFile(ehfOncologyTreatment.getRenalAttachmentPath());
					
					fileDtls = fileUploadUtils(patientForm.getRenalFile(), patientId, uploadType);
					ehfOncologyTreatment.setRenalFunctionalTest(patientForm.getRenal());
					ehfOncologyTreatment.setRenalAttachment(fileDtls[0]);
					ehfOncologyTreatment.setRenalAttachmentPath(fileDtls[1]);
				}
			}else{
				ehfOncologyTreatment.setRenalFunctionalTest(inActiveFlag);
				ehfOncologyTreatment.setRenalAttachment(null);
				ehfOncologyTreatment.setRenalAttachmentPath(null);
			}
			
			if(activeFlag.equalsIgnoreCase(patientForm.getLiver())){
				if(patientForm.getLiverFile() != null && !patientForm.getLiverFile().getFileName().isEmpty()){
					deleteOldFile(ehfOncologyTreatment.getLiverAttachmentPath());
					
					fileDtls = fileUploadUtils(patientForm.getLiverFile(), patientId, uploadType);
					ehfOncologyTreatment.setLiverFunctionalTest(patientForm.getLiver());
					ehfOncologyTreatment.setLiverAttachment(fileDtls[0]);
					ehfOncologyTreatment.setLiverAttachmentPath(fileDtls[1]);
				}
			}else{
				ehfOncologyTreatment.setLiverFunctionalTest(inActiveFlag);
				ehfOncologyTreatment.setLiverAttachment(null);
				ehfOncologyTreatment.setLiverAttachmentPath(null);
			}
			
			if(activeFlag.equalsIgnoreCase(patientForm.getThyroid())){
				if(patientForm.getThyroidFile() != null && !patientForm.getThyroidFile().getFileName().isEmpty()){
					deleteOldFile(ehfOncologyTreatment.getThyroidAttachmentPath());
				
					fileDtls = fileUploadUtils(patientForm.getThyroidFile(), patientId, uploadType);
					ehfOncologyTreatment.setThyroidFunctionTest(patientForm.getThyroid());
					ehfOncologyTreatment.setThyroidAttachment(fileDtls[0]);
					ehfOncologyTreatment.setThyroidAttachmentPath(fileDtls[1]);
				}
			}else{
				ehfOncologyTreatment.setThyroidFunctionTest(inActiveFlag);
				ehfOncologyTreatment.setThyroidAttachment(null);
				ehfOncologyTreatment.setThyroidAttachmentPath(null);
			}
			
			if(activeFlag.equalsIgnoreCase(patientForm.getUrine())){
				if(patientForm.getUrineFile() != null && !patientForm.getUrineFile().getFileName().isEmpty()){
					deleteOldFile(ehfOncologyTreatment.getUrineAttachmentPath());
				
					fileDtls = fileUploadUtils(patientForm.getUrineFile(), patientId, uploadType);
					ehfOncologyTreatment.setCompleteUrineExam(patientForm.getUrine());
					ehfOncologyTreatment.setUrineAttachment(fileDtls[0]);
					ehfOncologyTreatment.setUrineAttachmentPath(fileDtls[1]);
				}
			}else{
				ehfOncologyTreatment.setCompleteUrineExam(inActiveFlag);
				ehfOncologyTreatment.setUrineAttachment(null);
				ehfOncologyTreatment.setUrineAttachmentPath(null);
			}
			
			if(activeFlag.equalsIgnoreCase(patientForm.getLipid())){
				if(patientForm.getLipidFile() != null && !patientForm.getLipidFile().getFileName().isEmpty()){
					deleteOldFile(ehfOncologyTreatment.getLipidAttachmentPath());
				
					fileDtls = fileUploadUtils(patientForm.getLipidFile(), patientId, uploadType);
					ehfOncologyTreatment.setLipidProfile(patientForm.getLipid());
					ehfOncologyTreatment.setLipidAttachment(fileDtls[0]);
					ehfOncologyTreatment.setLipidAttachmentPath(fileDtls[1]);
				}
			}else{
				ehfOncologyTreatment.setLipidProfile(inActiveFlag);
				ehfOncologyTreatment.setLipidAttachment(null);
				ehfOncologyTreatment.setLipidAttachmentPath(null);
			}
			
			if(activeFlag.equalsIgnoreCase(patientForm.getEcho())){
				if(patientForm.getEchoFile() != null && !patientForm.getEchoFile().getFileName().isEmpty()){
					deleteOldFile(ehfOncologyTreatment.getdEchoAttachmentPath());
				
					fileDtls = fileUploadUtils(patientForm.getEchoFile(), patientId, uploadType);
					ehfOncologyTreatment.setdEcho(patientForm.getEcho());
					ehfOncologyTreatment.setDEchoAttachment(fileDtls[0]);
					ehfOncologyTreatment.setdEchoAttachmentPath(fileDtls[1]);
				}
			}else{
				ehfOncologyTreatment.setdEcho(inActiveFlag);
				ehfOncologyTreatment.setDEchoAttachment(null);
				ehfOncologyTreatment.setdEchoAttachmentPath(null);
			}
			
			if(activeFlag.equalsIgnoreCase(patientForm.getPulmonary())){
				if(patientForm.getPulmonaryFile() != null && !patientForm.getPulmonaryFile().getFileName().isEmpty()){
					deleteOldFile(ehfOncologyTreatment.getPulmonaryAttachmentPath());
				
					fileDtls = fileUploadUtils(patientForm.getPulmonaryFile(), patientId, uploadType);
					ehfOncologyTreatment.setPulmonaryFunctionTest(patientForm.getPulmonary());
					ehfOncologyTreatment.setPulmonaryAttachment(fileDtls[0]);
					ehfOncologyTreatment.setPulmonaryAttachmentPath(fileDtls[1]);
				}
			}else{
				ehfOncologyTreatment.setPulmonaryFunctionTest(inActiveFlag);
				ehfOncologyTreatment.setPulmonaryAttachment(null);
				ehfOncologyTreatment.setPulmonaryAttachmentPath(null);
			}
			
			if(activeFlag.equalsIgnoreCase(patientForm.getEcg())){
				if(patientForm.getEcgFile() != null && !patientForm.getEcgFile().getFileName().isEmpty()){
					deleteOldFile(ehfOncologyTreatment.getEcgAttachmentPath());
				
					fileDtls = fileUploadUtils(patientForm.getEcgFile(), patientId, uploadType);
					ehfOncologyTreatment.setEcg(patientForm.getEcg());
					ehfOncologyTreatment.setEcgAttachment(fileDtls[0]);
					ehfOncologyTreatment.setEcgAttachmentPath(fileDtls[1]);
				}
			}else{
				ehfOncologyTreatment.setEcg(inActiveFlag);
				ehfOncologyTreatment.setEcgAttachment(null);
				ehfOncologyTreatment.setEcgAttachmentPath(null);
			}
			
			if(activeFlag.equalsIgnoreCase(patientForm.getCea())){
				if(patientForm.getCeaFile() != null && !patientForm.getCeaFile().getFileName().isEmpty()){
					deleteOldFile(ehfOncologyTreatment.getCeaAttachmentPath());
				
					fileDtls = fileUploadUtils(patientForm.getCeaFile(), patientId, uploadType);
					ehfOncologyTreatment.setCea(patientForm.getCea());
					ehfOncologyTreatment.setCeaAttachment(fileDtls[0]);
					ehfOncologyTreatment.setCeaAttachmentPath(fileDtls[1]);
				}
			}else{
				ehfOncologyTreatment.setCea(inActiveFlag);
				ehfOncologyTreatment.setCeaAttachment(null);
				ehfOncologyTreatment.setCeaAttachmentPath(null);
			}
			
			if(activeFlag.equalsIgnoreCase(patientForm.getCa125())){
				if(patientForm.getCa125File() != null && !patientForm.getCa125File().getFileName().isEmpty()){
					deleteOldFile(ehfOncologyTreatment.getCa125AttachmentPath());
				
					fileDtls = fileUploadUtils(patientForm.getCa125File(), patientId, uploadType);
					ehfOncologyTreatment.setCa125(patientForm.getCa125());
					ehfOncologyTreatment.setCa125Attachment(fileDtls[0]);
					ehfOncologyTreatment.setCa125AttachmentPath(fileDtls[1]);
				}
			}else{
				ehfOncologyTreatment.setCa125(inActiveFlag);
				ehfOncologyTreatment.setCa125Attachment(null);
				ehfOncologyTreatment.setCa125AttachmentPath(null);
			}
			
			if(activeFlag.equalsIgnoreCase(patientForm.getCa153())){
				if(patientForm.getCa153File() != null && !patientForm.getCa153File().getFileName().isEmpty()){
					deleteOldFile(ehfOncologyTreatment.getCa153AttachmentPath());
				
					fileDtls = fileUploadUtils(patientForm.getCa153File(), patientId, uploadType);
					ehfOncologyTreatment.setCa153(patientForm.getCa153());
					ehfOncologyTreatment.setCa153Attachment(fileDtls[0]);
					ehfOncologyTreatment.setCa153AttachmentPath(fileDtls[1]);
				}
			}else{
				ehfOncologyTreatment.setCa153(inActiveFlag);
				ehfOncologyTreatment.setCa153Attachment(null);
				ehfOncologyTreatment.setCa153AttachmentPath(null);
			}
			
			if(activeFlag.equalsIgnoreCase(patientForm.getAfp())){
				if(patientForm.getAfpFile() != null && !patientForm.getAfpFile().getFileName().isEmpty()){
					deleteOldFile(ehfOncologyTreatment.getAfpAttachmentPath());
				
					fileDtls = fileUploadUtils(patientForm.getAfpFile(), patientId, uploadType);
					ehfOncologyTreatment.setAfp(patientForm.getAfp());
					ehfOncologyTreatment.setAfpAttachment(fileDtls[0]);
					ehfOncologyTreatment.setAfpAttachmentPath(fileDtls[1]);
				}
			}else{
				ehfOncologyTreatment.setAfp(inActiveFlag);
				ehfOncologyTreatment.setAfpAttachment(null);
				ehfOncologyTreatment.setAfpAttachmentPath(null);
			}
			
			if(activeFlag.equalsIgnoreCase(patientForm.getCa199())){
				if(patientForm.getCa199File() != null && !patientForm.getCa199File().getFileName().isEmpty()){
					deleteOldFile(ehfOncologyTreatment.getCa199AttachmentPath());
				
					fileDtls = fileUploadUtils(patientForm.getCa199File(), patientId, uploadType);
					ehfOncologyTreatment.setCa199(patientForm.getCa199());
					ehfOncologyTreatment.setCa199Attachment(fileDtls[0]);
					ehfOncologyTreatment.setCa199AttachmentPath(fileDtls[1]);
				}
			}else{
				ehfOncologyTreatment.setCa199(inActiveFlag);
				ehfOncologyTreatment.setCa199Attachment(null);
				ehfOncologyTreatment.setCa199AttachmentPath(null);
			}
			
			if(activeFlag.equalsIgnoreCase(patientForm.getPsa())){
				if(patientForm.getPsaFile() != null && !patientForm.getPsaFile().getFileName().isEmpty()){
					deleteOldFile(ehfOncologyTreatment.getPsaAttachmentPath());
				
					fileDtls = fileUploadUtils(patientForm.getPsaFile(), patientId, uploadType);
					ehfOncologyTreatment.setPsa(patientForm.getPsa());
					ehfOncologyTreatment.setPsaAttachment(fileDtls[0]);
					ehfOncologyTreatment.setPsaAttachmentPath(fileDtls[1]);
				}
			}else{
				ehfOncologyTreatment.setPsa(inActiveFlag);
				ehfOncologyTreatment.setPsaAttachment(null);
				ehfOncologyTreatment.setPsaAttachmentPath(null);
			}
			
			if(activeFlag.equalsIgnoreCase(patientForm.getBetahcg())){
				if(patientForm.getBetahcgFile() != null && !patientForm.getBetahcgFile().getFileName().isEmpty()){
					deleteOldFile(ehfOncologyTreatment.getBetaHcgAttachmentPath());
				
					fileDtls = fileUploadUtils(patientForm.getBetahcgFile(), patientId, uploadType);
					ehfOncologyTreatment.setBetaHcg(patientForm.getBetahcg());
					ehfOncologyTreatment.setBetaHcgAttachment(fileDtls[0]);
					ehfOncologyTreatment.setBetaHcgAttachmentPath(fileDtls[1]);
				}
			}else{
				ehfOncologyTreatment.setBetaHcg(inActiveFlag);
				ehfOncologyTreatment.setBetaHcgAttachment(null);
				ehfOncologyTreatment.setBetaHcgAttachmentPath(null);
			}
			
			if(activeFlag.equalsIgnoreCase(patientForm.getChromogranin())){
				if(patientForm.getChromograninFile() != null && !patientForm.getChromograninFile().getFileName().isEmpty()){
					deleteOldFile(ehfOncologyTreatment.getChromograninAAttachmentPath());
				
					fileDtls = fileUploadUtils(patientForm.getChromograninFile(), patientId, uploadType);
					ehfOncologyTreatment.setChromograninA(patientForm.getChromogranin());
					ehfOncologyTreatment.setChromograninAAttachment(fileDtls[0]);
					ehfOncologyTreatment.setChromograninAAttachmentPath(fileDtls[1]);
				}
			}else{
				ehfOncologyTreatment.setChromograninA(inActiveFlag);
				ehfOncologyTreatment.setChromograninAAttachment(null);
				ehfOncologyTreatment.setChromograninAAttachmentPath(null);
			}
			
			if(activeFlag.equalsIgnoreCase(patientForm.getCect())){
				if(patientForm.getCectFile() != null && !patientForm.getCectFile().getFileName().isEmpty()){
					deleteOldFile(ehfOncologyTreatment.getCectAttachmentPath());
				
					fileDtls = fileUploadUtils(patientForm.getCectFile(), patientId, uploadType);
					ehfOncologyTreatment.setCectScan(patientForm.getCect());
					ehfOncologyTreatment.setCectAttachment(fileDtls[0]);
					ehfOncologyTreatment.setCectAttachmentPath(fileDtls[1]);
				}
			}else{
				ehfOncologyTreatment.setCectScan(inActiveFlag);
				ehfOncologyTreatment.setCectAttachment(null);
				ehfOncologyTreatment.setCectAttachmentPath(null);
			}
			
			if(activeFlag.equalsIgnoreCase(patientForm.getMri())){
				if(patientForm.getMriFile() != null && !patientForm.getMriFile().getFileName().isEmpty()){
					deleteOldFile(ehfOncologyTreatment.getMriAttachmentPath());
				
					fileDtls = fileUploadUtils(patientForm.getMriFile(), patientId, uploadType);
					ehfOncologyTreatment.setMriScan(patientForm.getMri());
					ehfOncologyTreatment.setMriAttachment(fileDtls[0]);
					ehfOncologyTreatment.setMriAttachmentPath(fileDtls[1]);
				}
			}else{
				ehfOncologyTreatment.setMriScan(inActiveFlag);
				ehfOncologyTreatment.setMriAttachment(null);
				ehfOncologyTreatment.setMriAttachmentPath(null);
			}
			
			if(activeFlag.equalsIgnoreCase(patientForm.getPetct())){
				if(patientForm.getPetctFile() != null && !patientForm.getPetctFile().getFileName().isEmpty()){
					deleteOldFile(ehfOncologyTreatment.getPetCtAttachmentPath());
				
					fileDtls = fileUploadUtils(patientForm.getPetctFile(), patientId, uploadType);
					ehfOncologyTreatment.setPetCtScan(patientForm.getPetct());
					ehfOncologyTreatment.setPetCtAttachment(fileDtls[0]);
					ehfOncologyTreatment.setPetCtAttachmentPath(fileDtls[1]);
				}
			}else{
				ehfOncologyTreatment.setPetCtScan(inActiveFlag);
				ehfOncologyTreatment.setPetCtAttachment(null);
				ehfOncologyTreatment.setPetCtAttachmentPath(null);
			}
			
			genericDao.save(ehfOncologyTreatment);
			
			lResMap.put("Msg","SUCCESS");
		}catch(Exception le){
			le.printStackTrace();
		}
		return lResMap;
	}

	@Override
	public String[] getHighEndFormsSubmitFlag(String id, String idType, String patientId) throws Exception {
		String[] response = new String[3];
		StringBuffer query = new StringBuffer();
		List<LabelBean> resLst = new ArrayList<LabelBean>();
		EhfOncologyTreatment ehfOncologyTreatment = new EhfOncologyTreatment();
		
		try{
			if("cardNo".equalsIgnoreCase(idType))
				query.append(" SELECT PROfORMA_ID AS ID, STATUS as CASESTATUS FROM EHF_ONCOLOGY_MEDICAL eom WHERE eom.CARD_NO = ? AND ((SYSDATE <= eom.PREAUTH_INIT_DT + 90) OR eom.PREAUTH_INIT_DT IS NULL) ");
			else if("patientId".equalsIgnoreCase(idType))
				query.append(" SELECT PROFORMA_ID AS ID FROM EHF_ONCOLOGY_CMTE_APPRVL eoca WHERE eoca.PATIENT_ID =? ");
			resLst = genericDao.executeSQLQueryList(LabelBean.class, query.toString(), new String[]{id});
			if(!resLst.isEmpty()){
				response[0] = "Y";
				response[1] = resLst.get(0).getID();					
				
				if("cardNo".equalsIgnoreCase(idType) && ("CD2".equalsIgnoreCase(resLst.get(0).getCASESTATUS()) || "CD7310".equalsIgnoreCase(resLst.get(0).getCASESTATUS())))
					ehfOncologyTreatment = genericDao.findById(EhfOncologyTreatment.class, String.class, patientId);
				else
					ehfOncologyTreatment = genericDao.findById(EhfOncologyTreatment.class, String.class, patientId);
				if(ehfOncologyTreatment != null){
					response[2] = "Y";
				}
				else{
					if("cardNo".equalsIgnoreCase(idType) && ("CD2".equalsIgnoreCase(resLst.get(0).getCASESTATUS()) || "CD7310".equalsIgnoreCase(resLst.get(0).getCASESTATUS())))
						response[2] ="P";
					else if("cardNo".equalsIgnoreCase(idType) && ("CD7308".equalsIgnoreCase(resLst.get(0).getCASESTATUS()) || "CD7309".equalsIgnoreCase(resLst.get(0).getCASESTATUS()) || "CD7307".equalsIgnoreCase(resLst.get(0).getCASESTATUS())))
						response[2] = "M";
					else
						response[2] = "N";
				}
					
			}else{
				response[0] = "N";
			}
			
			
		}catch(Exception le){
			le.printStackTrace();
		}
		return response;
	}
	
	@Override
	public String generateOTP(String userMobileNo, String userId, String patientId) throws Exception {
		String templateId="1407161769810700722";
		String OTP = "";
		EhfSmsData ehfSmsData = new EhfSmsData();
		String response = null;
		EhfOncologyOtpAudit ehfOncologyOtpAudit = new EhfOncologyOtpAudit();
		List<EhfOncologyOtpAudit> ehfOncologyOtpAuditLst = new ArrayList<EhfOncologyOtpAudit>();
		try{
			OTP = RandomStringUtils.randomNumeric(6);
			if (userMobileNo != null && !"".equalsIgnoreCase(userMobileNo.trim())) {
				try {					
						ehfSmsData.setCrtDt(new java.sql.Timestamp(new Date().getTime()));
						ehfSmsData.setCrtUsr(userId);
						ehfSmsData.setUserId(userId);
						ehfSmsData.setPhoneNo(userMobileNo);
						ehfSmsData.setEhfPasswod(OTP);
						ehfSmsData.setTemplateId(templateId);
						String lStrMsg = "Your One-Time Password(OTP) for logging into \"Employee Health Scheme\" is "+OTP+"\n\nAHCT, Govt. of Telangana";
						
						SMSServices SMSServicesobj = new SMSServices();
						SMSServicesobj.sendSingleSMS(lStrMsg,userMobileNo,templateId);
						ehfSmsData.setSmsText(lStrMsg);
						
						List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
						criteriaList.add(new GenericDAOQueryCriteria("mobileNo", userMobileNo, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
						ehfOncologyOtpAuditLst = genericDao.findAllByCriteria(EhfOncologyOtpAudit.class, criteriaList);
						if(!ehfOncologyOtpAuditLst.isEmpty()){
							ehfOncologyOtpAudit = ehfOncologyOtpAuditLst.get(0);
							ehfOncologyOtpAudit.setOtp(OTP);
							ehfOncologyOtpAudit.setOtpCount(ehfOncologyOtpAudit.getOtpCount() + 1);
							ehfOncologyOtpAudit.setGenerateFlag("Y");
							ehfOncologyOtpAudit.setVerifyFlag(null);
							ehfOncologyOtpAudit.setLstUpdDt(new Timestamp(System.currentTimeMillis()));
							ehfOncologyOtpAudit.setOtpValidDt(new Timestamp(System.currentTimeMillis()+5*60*1000));
						}else{
							ehfOncologyOtpAudit.setMobileNo(userMobileNo);
							ehfOncologyOtpAudit.setOtp(OTP);
							ehfOncologyOtpAudit.setGenerateFlag("Y");
							ehfOncologyOtpAudit.setOtpCount(Long.valueOf(1));
							ehfOncologyOtpAudit.setCrtDt(new Timestamp(System.currentTimeMillis()));
							ehfOncologyOtpAudit.setOtpValidDt(new Timestamp(System.currentTimeMillis()+5*60*1000));
						}
						response = "success";
				}catch (Exception e) {
					e.printStackTrace();
					ehfSmsData.setSmsText("Msg Delivery Failed");	
					response = "failed";
				}finally {
					genericDao.save(ehfSmsData);
					genericDao.save(ehfOncologyOtpAudit);
				}
			}
		}catch(Exception le){
			le.printStackTrace();
		}
		return response;
	}
	@Override
	public String verifyOTP(String userMobileNo, String lStrUserId, String OTP, String patientId) throws Exception {
		EhfOncologyOtpAudit ehfOncologyOtpAudit = new EhfOncologyOtpAudit();
		List<EhfOncologyOtpAudit> ehfOncologyOtpAuditLst = new ArrayList<EhfOncologyOtpAudit>();
		String response = null;
		try{
			
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			criteriaList.add(new GenericDAOQueryCriteria("mobileNo", userMobileNo, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			ehfOncologyOtpAuditLst = genericDao.findAllByCriteria(EhfOncologyOtpAudit.class, criteriaList);
			if(!ehfOncologyOtpAuditLst.isEmpty()){
				ehfOncologyOtpAudit = ehfOncologyOtpAuditLst.get(0);
				
				if(ehfOncologyOtpAudit.getOtp().equalsIgnoreCase(OTP) && (System.currentTimeMillis() - ehfOncologyOtpAudit.getOtpValidDt().getTime() < 5*60*1000)){
					ehfOncologyOtpAudit.setVerifyFlag("Y");
					ehfOncologyOtpAudit.setLstUpdDt(new Timestamp(System.currentTimeMillis()));
					genericDao.save(ehfOncologyOtpAudit);
					response="success";
				}else if(!ehfOncologyOtpAudit.getOtp().equalsIgnoreCase(OTP))
					response = "The provided OTP is incorrect. Please try again";
				
				if(System.currentTimeMillis() - ehfOncologyOtpAudit.getOtpValidDt().getTime() > 5*60*1000)
					response = "The OTP has expired. Please request a new OTP.";
				
			}else{
				response = "No user found with the provided mobile number";
			}
		}catch(Exception le){
			le.printStackTrace();
		}
		return response;
	}
	@Transactional
	public String savePreauthInitforOncologyCases(Map<String, Object> hashMap) throws Exception {
		String caseId = null;
		String caseNo = null;
		long maxActOrder = 0;
		EhfOncologyMedical ehfOncologyMedical = new EhfOncologyMedical();
		EhfOncologyCmteApprvl ehfOncologyCmteApprvl = new EhfOncologyCmteApprvl();
		EhfCase ehfCase = new EhfCase();
		List<EhfCase> ehfCaseLst = new ArrayList<EhfCase>();
		EhfEmpCaseDtls ehfEmpCaseDtls = new EhfEmpCaseDtls();
		EhfAudit ehfAudit = new EhfAudit();
		EhfAuditId ehfAuditId = new EhfAuditId();
		List<EhfAudit> ehfAuditLst = new ArrayList<EhfAudit>();
		EhfonBedStatus ehfOnBedStatus = new EhfonBedStatus();
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		try{
			String patientId = hashMap.get("patientId").toString();
			String remarks = hashMap.get("remarks").toString();
			String userId = hashMap.get("userId").toString();
			String proformaId = hashMap.get("proformaId").toString();
			
			ehfOncologyMedical = genericDao.findById(EhfOncologyMedical.class, String.class, proformaId);
			ehfOncologyCmteApprvl = genericDao.findById(EhfOncologyCmteApprvl.class, String.class, patientId);
			if(ehfOncologyMedical != null && ehfOncologyCmteApprvl != null){
				
				criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("casePatientNo", patientId, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add(new GenericDAOQueryCriteria("caseStatus", config.getString("CASE.HighEndOncologyCaseSubmittedforCmteApproval"), GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				ehfCaseLst = genericDao.findAllByCriteria(EhfCase.class, criteriaList);
				
				if(!ehfCaseLst.isEmpty()){
					ehfCase = ehfCaseLst.get(0);
					if(ehfCase != null){
						caseId = ehfCase.getCaseId();
						caseNo = ehfCase.getCaseNo();
						ehfCase.setCaseStatus(config.getString("CASE.IPCaseRegistered"));
						ehfCase.setLstUpdUsr(userId);
						ehfCase.setLstUpdDt(new Timestamp(System.currentTimeMillis()));
					}
				}
				
				ehfEmpCaseDtls = genericDao.findById(EhfEmpCaseDtls.class, String.class, caseId);
				if(ehfEmpCaseDtls != null){
					ehfEmpCaseDtls.setCaseStatus(config.getString("CASE.IPCaseRegistered"));
					ehfEmpCaseDtls.setLstUpdUsr(userId);
					ehfEmpCaseDtls.setLstUpdDt(new Timestamp(System.currentTimeMillis()));
				}
				
				ehfOnBedStatus = genericDao.findById(EhfonBedStatus.class, String.class, caseId);
				if(ehfOnBedStatus != null){
					ehfOnBedStatus.setCaseStatus(config.getString("CASE.CaseRegisteredStatus"));
					ehfOnBedStatus.setBedStatusId(config.getString("CASE.OnBed"));
					ehfOnBedStatus.setBedStatus(config.getString("CASE.OnBedStatus"));
					ehfOnBedStatus.setBedStatusUpdDate(new Timestamp(System.currentTimeMillis()));
					ehfOnBedStatus.setLstUpdUsr(userId);
					ehfOnBedStatus.setLstUpdDt(new Timestamp(System.currentTimeMillis()));
				}
				
				criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("id.caseId", caseId, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				ehfAuditLst = genericDao.findAllByCriteria(EhfAudit.class, criteriaList);
				if(!ehfAuditLst.isEmpty()){
					for(EhfAudit audit : ehfAuditLst){
						long currentActOrder = audit.getId().getActOrder();
						if(currentActOrder > maxActOrder){
							maxActOrder = currentActOrder;
						}
					}
				}
				ehfAuditId.setCaseId(caseId);
				ehfAuditId.setActOrder(maxActOrder+1);
				ehfAudit.setId(ehfAuditId);
				ehfAudit.setActId(config.getString("CASE.IPCaseRegistered"));
				ehfAudit.setActBy(userId);
				ehfAudit.setCrtUsr(userId);
				ehfAudit.setRemarks(remarks);
				ehfAudit.setLangId(config.getString("en_US"));
				ehfAudit.setCrtDt(new Timestamp(System.currentTimeMillis()));
				
				if(ehfOncologyMedical.getPreauthInitDt() == null){
					ehfOncologyMedical.setStatus(config.getString("CASE.IPCaseRegistered"));
					ehfOncologyMedical.setLstUpdUsr(userId);
					ehfOncologyMedical.setLstUpdDt(new Timestamp(System.currentTimeMillis()));
					ehfOncologyMedical.setPreauthInitDt(new Timestamp(System.currentTimeMillis()));
					genericDao.save(ehfOncologyMedical);
				}
				ehfOncologyCmteApprvl.setStatus(config.getString("CASE.IPCaseRegistered"));
				ehfOncologyCmteApprvl.setLstUpdUsr(userId);
				ehfOncologyCmteApprvl.setLstUpdDt(new Timestamp(System.currentTimeMillis()));
								
				genericDao.save(ehfOncologyCmteApprvl);
				genericDao.save(ehfCase);
				genericDao.save(ehfOnBedStatus);
				genericDao.save(ehfEmpCaseDtls);
				genericDao.save(ehfAudit);
			}else{
				caseNo = "Patient not found";
			}
			
		}catch(Exception le){
			le.printStackTrace();
		}
		return caseNo;
	}
	public static String[] fileUploadUtils(FormFile file, String id, String uploadType){
		try{

			String [] fileDtls = new String[2];
			String fileName = file.getFileName();
			String timeStamp = new SimpleDateFormat("dd_MM_yy_HH_mm_ss").format(new Date());
			String fileExt = "";
    	    int fileExtIndex = fileName.lastIndexOf('.');
    	    if (fileExtIndex != -1) {
    	        fileExt = fileName.substring(fileExtIndex); 
    	        fileName = fileName.substring(0, fileExtIndex); 
    	    }
    	    fileName = fileName + "_" + timeStamp + fileExt;
			String filePath ="";
			if("proformaType".equalsIgnoreCase(uploadType)){
				filePath = config.getString("STORAGE_BOX")+"HighEnd_Drugs"+config.getString("SLASH_VALUE")+id;
			}else if("evalutionType".equalsIgnoreCase(uploadType)){
				filePath = config.getString("STORAGE_BOX")+"OncologyEvolution_Response"+config.getString("SLASH_VALUE")+id;
			}
			if(!(new File(filePath).exists())){
				new File(filePath).mkdirs();
			}
			String path = filePath+config.getString("SLASH_VALUE")+fileName;;
			if (!fileName.equals("")) {

				File fileToCreate = new File(path);
				if (!fileToCreate.exists()) {
					FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
					fileOutStream.write(file.getFileData());
					fileOutStream.flush();
					fileOutStream.close();
				}
			}
			fileDtls[0] = fileName;
			fileDtls[1] = path;
			return fileDtls;
		}catch(Exception le){
			le.printStackTrace();
			return null;
		}
		
	}
	
	private boolean deleteOldFile(String filePath) {
		try{
			if(filePath != null && !filePath.isEmpty()){
				File oldFile = new File(filePath);
                if (oldFile.exists() && oldFile.delete()) {
                    return true;
                }
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;		
	}
	
	//End CR#4511-SriHari
	//Chandana - 7845 - for getting the claims workflow details
	@Override
	public List<LabelBean> getWorkFlowDtls(HashMap hashMap) {
		String patientId = null;
		String rolId = null;
		if(hashMap.get("patientId") != null)
			patientId  = hashMap.get("patientId").toString();
		if(hashMap.get("roleId") != null)
			rolId = hashMap.get("roleId").toString();
		StringBuffer query = new StringBuffer();
		List<LabelBean> resList = null;
        try{
        	if("GP2".equalsIgnoreCase(rolId)){//Chandana - 8036 - This condition for Medco should not show the names of next levels in workflow
        		query.append(" SELECT CASE WHEN e.GRP_ID = 'GP2' THEN TRIM(TRIM(C.FIRST_NAME)||' '||TRIM(C.LAST_NAME))||' & '|| e.GRP_SHORT_NAME ");//Chandana - 8602 - Added GRP_SHORT_NAME instead of DSGN_NAME
        		query.append(" ELSE e.GRP_SHORT_NAME END AS EMPNAME, ");
        	}
        	else
        		query.append(" SELECT c.FIRST_NAME || ' ' || c.LAST_NAME || ' ' || '& ' || e.GRP_SHORT_NAME as EMPNAME, ");//Chandana - 8602 - Added GRP_SHORT_NAME instead of DSGN_NAME
        	//Chandana - 8602 - Changed the query, joined this ehfm_grps table and shwoing the dsgn Short name instead of entire names
        	query.append(" b.CMB_DTL_NAME as CASESTATUS,a.REMARKS as REMARKS, TO_CHAR(a.CRT_DT, 'DD/MM/YYYY HH24:MI:SS') AS VALUE,  a.CLAIM_AMOUNT AS AMOUNT ");
        	query.append(" FROM EHF_OPD_CLAIM_AUDIT a,EHFM_CMB_DTLS b,EHFM_USERS c,EHF_OPD_PATIENTS d, Ehfm_Grps e WHERE a.ACT_BY = c.USER_ID ");
        	query.append(" AND a.CLAIM_STATUS = b.CMB_DTL_ID AND a.PATIENT_ID = '"+patientId+"' AND d.OP_CLAIM_SEQ = a.OP_CLAIM_SEQ AND a.user_role = e.GRP_ID ");
        	query.append(" ORDER BY a.CRT_DT ");
		    resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
        }catch(Exception le){
        	le.printStackTrace();
        }
		return resList;
	}
	//Chandana - 7845 - to get the status list related o nims opd claims
	@Override
	public List<LabelBean> getNimsOPDClaimStatusLst(String acoFlag) {//Chandana - 8602 - Added acoFlag to the existing method
		StringBuffer query = new StringBuffer();
		List<LabelBean> resList = null;
        try{
        	query.append(" SELECT CMB_DTL_ID AS ID, CMB_DTL_NAME AS VALUE FROM EHFM_CMB_DTLS WHERE CMB_PARNT_DTL_ID ='OPD' ");
			if("Y".equalsIgnoreCase(acoFlag))//Chandana - 8602 - Added if condition for aco we need to append the below query also
        		query.append(" AND CMB_DTL_ID IN ('CD7305','CD7344') ");
		    resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
        }catch(Exception e){
        	e.printStackTrace();
        }
		return resList;
	}
	//Chandana - 8442 - Implemented below method for abha numbers, using abha number getting the main card number either journalist or emp or pensioner
	@Override
	public String getCardDtlsForAbhaSearch(String cardNo){
		List<LabelBean> submitList =null;
		String cardType = null;
		String cardNum = null;
		try
		{						  
			StringBuffer query = new StringBuffer();
			query.append(" SELECT EF.EHF_CARD_NO ID, decode(user_role,'CD3022','E','P') VALUE FROM ASRIM_USERS AU,EHF_ENROLLMENT EE,EHF_ENROLLMENT_FAMILY EF WHERE AU.LOGIN_NAME=EE.EMP_CODE");
			query.append(" AND EE.ENROLL_PRNT_ID=EF.ENROLL_PRNT_ID AND AU.ACTIVE_YN='Y' AND AU.SCHEME='CD202' AND EF.ABHA_ID ='"+cardNo+"' ");
			submitList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
			if(submitList != null && !submitList.isEmpty()){
				cardNum = submitList.get(0).getID();
	            cardType = submitList.get(0).getVALUE();
			}
			else {

			    query = new StringBuffer();
			    query.append(" SELECT a.JOURNAL_CARD_NO ID ");
			    query.append(" FROM EHF_JRNLST_FAMILY a, EHFM_JRNLST b, EHF_JRNLST_ENROLLMENT c ");
			    query.append(" WHERE b.DJHS_FLAG='Y' ");
			    query.append(" AND c.JOURNAL_CODE=b.USER_NO ");
			    query.append(" AND c.JOURNAL_ENROLL_PRNT_ID=a.JOURNAL_ENROLL_PRNT_ID ");
			    query.append(" AND a.ABHA_ID ='"+cardNo+"' ");

			    submitList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());

			    if(submitList != null && !submitList.isEmpty()){
			        cardNum = submitList.get(0).getID();
			        cardType = "DJ";
			    }

			    else {

			        query = new StringBuffer();
			        query.append(" SELECT JOURNAL_CARD_NO ID FROM EHF_JRNLST_FAMILY WHERE ABHA_ID ='"+cardNo+"' ");
			        submitList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());

			        if(submitList != null && !submitList.isEmpty()){
			            cardNum = submitList.get(0).getID();
			            cardType = "J";
			        }
			    }
			}
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getCardDtlsForAbhaSearch() in PatientDaoImpl class."+e.getMessage());
			e.printStackTrace();
		}
		if(cardNum != null || cardType != null)
			return cardNum + "~" +cardType;
		else
			return null;
	}
	//Chandana - 8441 - New method for getting the additional attchments
	@Override
	public List<LabelBean> getAddtnalAttach(HashMap hashMap) {
		String patientId = null;
		if(hashMap.get("patientId") != null)
			patientId  = hashMap.get("patientId").toString();
		StringBuffer query = new StringBuffer();
		List<LabelBean> resList = null;
        try{
        	query.append(" SELECT a.SEQ_ID as COUNT, a.RECORD_NAME AS VALUE, a.COMMENTS AS REMARKS, a.DOC_PATH AS ATTACHPATH FROM EHF_OPD_BILL_ATTACHMENT a WHERE ");
        	query.append(" a.STATUS = 'O' AND a.CLAIM_NO = '"+patientId+"' AND a.ACTIVE_YN = 'Y' ORDER BY CRT_DT DESC ");
		    resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
        }catch(Exception le){
        	le.printStackTrace();
        }
		return resList;
	}
	//Chandana - 8441 - New method for saving or updating the active_yn flag for deleting addtional attachments
	@Transactional
	public String updateAdditionalAttach(HashMap hashMap) {
		String opdClaimApprovedSeq = null;
		String claimNo  = hashMap.get("claimNo").toString();
		String res = null;
		Long seqId = null;
		FormFile fPath = null; 
		String fileName = null, remarks = null, description = null;
		if (hashMap.get("seqId") != null)
		    seqId = Long.parseLong(hashMap.get("seqId").toString());
		if (hashMap.get("file") != null)
			fPath = (FormFile) hashMap.get("file");
		if (hashMap.get("fileName") != null)
			fileName = (String) hashMap.get("fileName");
		if (hashMap.get("description") != null)
			description = (String) hashMap.get("description");
		if (hashMap.get("remarks") != null)
			remarks = (String) hashMap.get("remarks");
		String userId  = hashMap.get("lStrUserId").toString();
		String action = hashMap.get("action").toString();
		String status = "O";
		String activeYn = "Y";
		StringBuffer lsb = new StringBuffer();
		Long generatedSeqId = null;
		StringBuffer query = new StringBuffer();
		try{
			
			if("delete".equalsIgnoreCase(action)){
				List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("status", status, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add(new GenericDAOQueryCriteria("activeYn", "Y", GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add(new GenericDAOQueryCriteria("claimNo", claimNo, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add(new GenericDAOQueryCriteria("seqId", seqId, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				List<EhfOpdBillDtlsAttachment> additionalAttach = genericDao.findAllByCriteria(EhfOpdBillDtlsAttachment.class, criteriaList);
				if(!additionalAttach.isEmpty() && additionalAttach.size() > 0){
					EhfOpdBillDtlsAttachment ehfOpdBillAttach = additionalAttach.get(0);
					ehfOpdBillAttach.setLstUpdDt(new Date());
					ehfOpdBillAttach.setLstUpdUsr(userId);
					ehfOpdBillAttach.setActiveYn("N");
						genericDao.save(ehfOpdBillAttach);
						//genericDao.flush();
						boolean insertAuditRecord = insertAttachAuditRecord(seqId,claimNo,userId,action,description,remarks);
						opdClaimApprovedSeq = "success";
					}
			} else{
				List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("claimNo", claimNo, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add(new GenericDAOQueryCriteria("activeYn", "Y", GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add(new GenericDAOQueryCriteria("status", status , GenericDAOQueryCriteria.CriteriaOperator.NOT_EQUALS));
				List<EhfOpdBillDtlsAttachment> additionalAttach = genericDao.findAllByCriteria(EhfOpdBillDtlsAttachment.class, criteriaList);
				EhfOpdBillDtlsAttachment ehfOpdBillAttach = new EhfOpdBillDtlsAttachment();
				ehfOpdBillAttach.setCrNo(additionalAttach.get(0).getCrNo());
				ehfOpdBillAttach.setHospCode(additionalAttach.get(0).getHospCode());
				String filePath = uploadFile(additionalAttach.get(0).getCrNo(),fPath,fileName);//To insert EHF_DISP_DRUG_EXCEL_UPLOAD this table and file storage code
				ehfOpdBillAttach.setClaimNo(claimNo);
				ehfOpdBillAttach.setRecordName(description);
				ehfOpdBillAttach.setDocPath(filePath);
				ehfOpdBillAttach.setStatus(status);
				ehfOpdBillAttach.setComments(remarks);
				ehfOpdBillAttach.setCrtDt(new Date());
				ehfOpdBillAttach.setCrtUsr(userId);
				ehfOpdBillAttach.setActiveYn("Y");
				genericDao.save(ehfOpdBillAttach);
				
				generatedSeqId = ehfOpdBillAttach.getSeqId(); 
				res = String.valueOf(generatedSeqId);
				seqId = Long.valueOf(res);
				boolean insertAuditRecord = insertAttachAuditRecord(seqId,claimNo,userId,action,description,remarks);
				opdClaimApprovedSeq = "success";
			}
		}catch(Exception le){
			le.printStackTrace();
			opdClaimApprovedSeq = "fail";
		}
		return opdClaimApprovedSeq;
	}
	//Chandana - 8441 - New method for inserting into audit table
	public boolean insertAttachAuditRecord(Long seqId,String claimNo,String userId,String action, String description, String remarks){
		boolean response = false; 
		try{
			EhfOpdBillAttachAudit ehfOpdBillAttachAudit = new EhfOpdBillAttachAudit();
			ehfOpdBillAttachAudit.setSeqId(seqId);
			ehfOpdBillAttachAudit.setClaimNo(claimNo);
			ehfOpdBillAttachAudit.setRemarks(remarks);
			if("delete".equalsIgnoreCase(action))
				ehfOpdBillAttachAudit.setAction("Delete");
			else
				ehfOpdBillAttachAudit.setAction("Save");
			ehfOpdBillAttachAudit.setDescription(description);
			ehfOpdBillAttachAudit.setCrtDt(new Date());
			ehfOpdBillAttachAudit.setCrtUsr(userId);
			genericDao.save(ehfOpdBillAttachAudit);
			response = true;
		}catch(Exception le){
			le.printStackTrace();
			response = false;
		}
		return response;
	}
	//Chandana - 8599 - New method for saving or updating the active_yn flag for deleting addtional attachments
	@Transactional
	public String updateMissingAttach(HashMap hashMap) {
	    String result = "fail";
	    try {
	        String claimNo = hashMap.get("claimNo").toString();
	        String billNo = hashMap.get("billNo").toString();
	        String userId = hashMap.get("lStrUserId").toString();
	        Long seqId = Long.valueOf(hashMap.get("seqId").toString());
	        FormFile fPath = (FormFile) hashMap.get("file");
	        String fileName = (String) hashMap.get("fileName");
	        String crNo = (String) hashMap.get("crNo");

	        List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
	        criteriaList.add(new GenericDAOQueryCriteria("crNo", crNo, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	        criteriaList.add(new GenericDAOQueryCriteria("seqId", seqId, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	        criteriaList.add(new GenericDAOQueryCriteria("billNo", billNo, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	        criteriaList.add(new GenericDAOQueryCriteria("claimNo", claimNo, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	        List<EhfOpdBillDtlsAttachment> existingRecord = genericDao.findAllByCriteria(EhfOpdBillDtlsAttachment.class, criteriaList);

	        if (existingRecord != null && !existingRecord.isEmpty()) {
	            EhfOpdBillDtlsAttachment ehfOpdBillAttach = existingRecord.get(0);
	            String filePath = uploadFile(ehfOpdBillAttach.getCrNo(), fPath, fileName);
	            ehfOpdBillAttach.setDocPath(filePath);
	            ehfOpdBillAttach.setLstUpdDt(new Date());
	            ehfOpdBillAttach.setLstUpdUsr(userId);
	            genericDao.save(ehfOpdBillAttach);
	            result = "success";
	        }
	        else
	        	result = "fail";
	    } catch (Exception e) {
	        e.printStackTrace();
	        result = "fail";
	    }
	    return result;
	}
		
	//Chandana - 8441 - Implemented new method for file uploading
	public String uploadFile(String crNo, FormFile file, String fileName) {
	    String lStrAbsolutePath = null;
	    FileOutputStream lFileOutputStream = null;
	    SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
	    String currentDtAndTime = dateFormat.format(new Date());
	    try {
	        String basePath = "/NAS4-TS-Production/Phase7/NIMS-SCHEDULER/AdditionalAttach";
	        String crNoFolderPath = basePath + "/" + crNo;
	        File crNoDirectory = new File(crNoFolderPath);
	        if (!crNoDirectory.exists())
	            crNoDirectory.mkdirs();
	        if (fileName != null && !fileName.trim().isEmpty()) {
	            String finalFileName = currentDtAndTime + "_" + fileName;
	            lStrAbsolutePath = crNoFolderPath + "/" + finalFileName;
	            File lFile = new File(lStrAbsolutePath);
	            if (lFile.exists()) {
	                lFile.delete();
	            }
	            lFileOutputStream = new FileOutputStream(lFile);
	            lFileOutputStream.write(file.getFileData());
	            lFileOutputStream.flush();
	            lFileOutputStream.close();
	            lFileOutputStream = null;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return lStrAbsolutePath;
	}
	//Chandana - 8602
	public String getApprovedAmount(String patId) throws Exception{
		List<LabelBean> submitList =null;
		String opClaimNo = null;
		String chApprovedAmnt = null;
		String chDeductAmnt = null;
		try
		{						  
			StringBuffer query = new StringBuffer();
			query.append(" SELECT a.OP_CLAIM_SEQ as ID FROM EHF_OPD_PATIENTS a, EHF_PATIENT b WHERE a.CLAIM_NO ='"+patId+"' AND a.CLAIM_NO = b.PATIENT_ID ");
			submitList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
			if(submitList != null && !submitList.isEmpty()){
				opClaimNo = submitList.get(0).getID();
				query = new StringBuffer();
				query.append(" SELECT a.CLAIM_AMOUNT AS NEXTVAL, a.CH_DEDUCT_AMOUNT AS AMOUNT FROM EHF_OPD_CLAIM_DTLS a,EHF_OPD_PATIENTS b WHERE a.OP_CLAIM_ID ='"+opClaimNo+"' AND a.OP_CLAIM_ID = b.OP_CLAIM_SEQ ");
				submitList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
				chApprovedAmnt = String.valueOf(submitList.get(0).getNEXTVAL());
				chDeductAmnt = String.valueOf(submitList.get(0).getAMOUNT());
			}
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getApprovedAmount() in PatientDaoImpl class."+e.getMessage());
			e.printStackTrace();
		}
		return chApprovedAmnt +"~"+ chDeductAmnt;
	}
	
	//Chandana - 8602
		public String getPatNoFromClaimNo(String opClaimNo, String seqId) throws Exception{
			List<LabelBean> submitList =null;
			String patientId = null;
			try
			{						  
				StringBuffer query = new StringBuffer();
				query.append(" SELECT CLAIM_NO as ID FROM EHF_OPD_PATIENTS WHERE OP_CLAIM_SEQ = '"+opClaimNo+"' and SEQ_ID = '"+seqId+"' ");
				submitList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
				if(submitList != null && !submitList.isEmpty())
					patientId = submitList.get(0).getID();
			}
			catch(Exception e)
			{
				GLOGGER.error("Exception Occurred in getPatNoFromClaimNo() in PatientDaoImpl class."+e.getMessage());
				e.printStackTrace();
			}
			return patientId;
		}
		//Chandana - 8602
		public String getFlagForACOReturnedClaim(String userId, String opClaimNo) throws Exception{
			List<LabelBean> submitList =null;
			String flag = "N";
			try 
			{
				StringBuffer query = new StringBuffer();
				query.append(" SELECT OP_CLAIM_ID ID FROM EHF_OPD_CLAIM_DTLS WHERE OP_CLAIM_ID ='"+opClaimNo+"' AND CLAIM_STATUS ='CD7343' AND CH_APPRV_USR ='"+userId+"' ");
				submitList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
				if(submitList != null && !submitList.isEmpty())
					flag = "Y";
			}catch(Exception e)
			{
				GLOGGER.error("Exception Occurred in getPatNoFromClaimNo() in PatientDaoImpl class."+e.getMessage());
				e.printStackTrace();
				flag = "N";
			}
		return flag;
	}
	@Override
	public List<LabelBean> getDlhJrnlstClaimCasesLst(HashMap<java.lang.String, java.lang.String> hashMap) {
		String claimDt = hashMap.get("claimDt");
		String claimStatus = hashMap.get("claimStatus");
		String claimNo = hashMap.get("claimSubmittedNo");
		String patientId  = hashMap.get("patientId");
		String cardNo = hashMap.get("cardNo");
		String searchFlg = hashMap.get("searchFlg");
		String loggedUserHospId = hashMap.get("loggedUserHospId");//Chandana - 9033 - getting the logged user hosp id
		StringBuffer query = new StringBuffer();
		List<LabelBean> resList = null;
		String[] args = null;
		Map lTempQryVal = new HashMap();
	    int cnt=0; 
	    boolean hasFilter = false;
	        
	    try{
	    	if((("".equalsIgnoreCase(claimDt) && "".equalsIgnoreCase(claimNo)) || claimDt == null) && ("-1".equalsIgnoreCase(claimStatus) || "CD7351".equalsIgnoreCase(claimStatus) || claimStatus == null)){
		    	query.append(" SELECT ep.PATIENT_ID AS PATIENTID,ep.CARD_NO AS CARDNO,ep.Name AS PNAME,decode(PATIENT_IPOP, 'RG', 'Delhi Journalist Claim Case to be initiated by Medco', 'P') VALUE, ep.CRT_DT AS DOB, ");
		    	query.append(" '' AS CLAIMNO, 0 AS AMOUNT, '' AS CLAIMDT FROM EHF_PATIENT ep WHERE CARD_TYPE = 'DJ' AND PATIENT_IPOP ='RG' AND ep.REG_HOSP_ID ='"+loggedUserHospId+"' ");//Chandana - Checking the reg hosp id and kogged user hospid
		    	if(patientId != null && !"".equalsIgnoreCase(patientId)){
		    		query.append(" AND EP.PATIENT_ID= ? ");
		    		lTempQryVal.put(new Integer(++cnt), patientId);
		        	hasFilter = true;
		    	}
		    	if(cardNo != null && !"".equalsIgnoreCase(cardNo)){
		    		query.append(" AND ep.CARD_NO = ? ");
		    		lTempQryVal.put(new Integer(++cnt), cardNo);
		        	hasFilter = true;
		    	}
		    	if(!"CD7351".equalsIgnoreCase(claimStatus))
		    		query.append(" UNION ALL ");
	    	}
	    	if(!"CD7351".equalsIgnoreCase(claimStatus)){
		    	query.append(" SELECT ep.PATIENT_ID AS PATIENTID,ep.CARD_NO AS CARDNO,ep.Name AS PNAME,CMB_DTL_NAME VALUE, ep.CRT_DT AS DOB,edc.CLAIM_ID AS CLAIMNO, ");
		    	query.append(" edc.CLAIM_AMOUNT AS AMOUNT, TO_CHAR(edc.CRT_DT, 'DD/MM/YYYY HH24:MI:SS') AS CLAIMDT FROM EHF_PATIENT ep, ");
		    	query.append(" EHF_JRNLSTD_CLAIM_DTLS EDC,EHFM_CMB_DTLS CMB WHERE ep.PATIENT_ID=EDC.PATIENT_ID AND EDC.CLAIM_STATUS=CMB.CMB_DTL_ID AND ep.REG_HOSP_ID ='"+loggedUserHospId+"'");//Chandana - 9033 - Checking the reg hosp id and kogged user hospid
	    	}
	    	if(claimNo != null && !"".equalsIgnoreCase(claimNo)){
	    		query.append(" AND EDC.CLAIM_ID= ? ");
	    		lTempQryVal.put(new Integer(++cnt), claimNo);
	        	hasFilter = true;
	    	}
	    	if(patientId != null && !"".equalsIgnoreCase(patientId)){
	    		query.append(" AND EDC.PATIENT_ID= ? ");
	    		lTempQryVal.put(new Integer(++cnt), patientId);
	        	hasFilter = true;
	    	}
	    	if(claimDt != null && !"".equalsIgnoreCase(claimDt)){
	    		query.append(" AND TO_CHAR(EDC.CRT_DT, 'DD/MM/YYYY') = ? ");
	    		lTempQryVal.put(new Integer(++cnt), claimDt);
	        	hasFilter = true;
	    	}
	    	if(cardNo != null && !"".equalsIgnoreCase(cardNo)){
	    		query.append(" AND ep.CARD_NO = ? ");
	    		lTempQryVal.put(new Integer(++cnt), cardNo);
	        	hasFilter = true;
	    	}
	    	if(claimStatus != null && !"-1".equalsIgnoreCase(claimStatus) && !"CD7351".equalsIgnoreCase(claimStatus)){
	    		query.append(" AND EDC.CLAIM_STATUS= ? ");
	    		lTempQryVal.put(new Integer(++cnt), claimStatus);
	        	hasFilter = true;
	    	}
	    	if("N".equalsIgnoreCase(searchFlg))
	    		query.append(" AND CLAIM_STATUS in ('CD7354','CD7363') ");
	        
	        /*if (!hasFilter) {
	        	query.append(" AND b.CLAIM_STATUS IN  ('CD7303','CD7329','CD7315') ");//Chandana - 8036 - added in condition
	        }*/
	        query.append(" ORDER BY DOB ");	
	        args = new String[cnt];
			for (int i = 1; i <= cnt; i++) {
				args[i-1] = lTempQryVal.get(new Integer(i)).toString();
			}
			resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(), args);
	       }catch(Exception e){
	        	e.printStackTrace();
	       }
		return resList;
	}
	//Chandana - 8755 - New method for getting the catogry and provisional details
	@Override
	public List<LabelBean> getCatProvisionalDtls(HashMap hashMap) {
		String patientId = null;
		StringBuffer query = new StringBuffer();
		List<LabelBean> resList = null;
		String[] args = null;
		Map lTempQryVal = new HashMap();
		patientId = (String) hashMap.get("patientId");
        try{
        	query.append(" SELECT a.ICD_CAT_CODE AS ID, a.PROVISIONAL_DIAGNOSIS AS VALUE, c.DIS_MAIN_NAME AS LVL FROM EHF_JRNLSTD_PATIENT_THERAPY a, EHF_PATIENT b, EHFM_SPECIALITIES c WHERE a.patient_id = b.PATIENT_ID AND b.PATIENT_ID ='"+patientId+"' AND c.DIS_MAIN_ID = a.ICD_CAT_CODE ");
        	resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
        }catch(Exception le){
        	le.printStackTrace();
        }
		return resList;
	}
	//Chandana - 8755 - New method for getting the manadtory attachment types
	@Override
	public List<LabelBean> getAttachmentsLst(HashMap hashMap) {
		String patientId = null;
		String mandateAttachmntsLst = (String) hashMap.get("mandateAttachmntsLst");
		String inClause = "'" + mandateAttachmntsLst.replace("~", "','") + "'";
		StringBuffer query = new StringBuffer();
		List<LabelBean> resList = null;
		String[] args = null;
		Map lTempQryVal = new HashMap();
        try{
        	query.append(" SELECT CMB_DTL_ID ID, CMB_DTL_NAME VALUE FROM EHFM_CMB_DTLS WHERE CMB_DTL_ID IN ("+inClause+") ORDER BY CMB_DTL_NAME ");
        	resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
        }catch(Exception le){
        	le.printStackTrace();
        }
		return resList;
	}
	//Chandana - 8755 - New method for getting the aditional attachment s for delhi journalist
	@Override
	public List<LabelBean> getDlhJrnlstAddtnalAttach(HashMap hashMap) {
		String patientId = null;
		if(hashMap.get("patientId") != null)
			patientId  = hashMap.get("patientId").toString();
		StringBuffer query = new StringBuffer();
		List<LabelBean> resList = null;
        try{
        	query.append(" SELECT a.SEQ_ID AS COUNT, a.ATTACH_TYPE AS ID, a.REMARKS AS CONST, b.CMB_DTL_NAME AS VALUE, a.DOC_PATH AS ATTACHPATH FROM ");
        	query.append(" EHF_JRNLSTD_ATTACHMENT a, EHFM_CMB_DTLS b WHERE PATIENT_ID ='"+patientId+"' AND a.ATTACH_TYPE = b.CMB_DTL_ID AND a.ACTIVE_YN ='Y' and a.ATTACH_TYPE = 'CD7362' ");
		    resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
        }catch(Exception le){
        	le.printStackTrace();
        }
		return resList;
	}
	//Chandana - 8755 - New method for saving or updating the active_yn flag for deleting addtional attachments
		@Transactional
		public String updDlhJrnlstAddtnalAttach(HashMap hashMap) {
			String result = null;
			String patientId  = hashMap.get("patientId").toString();
			String res = null, claimNo = null;
			Long seqId = null;
			FormFile fPath = null; 
			String fileName = null, remarks = null, description = null;
			if (hashMap.get("seqId") != null)
			    seqId = Long.parseLong(hashMap.get("seqId").toString());
			if (hashMap.get("file") != null)
				fPath = (FormFile) hashMap.get("file");
			if (hashMap.get("fileName") != null)
				fileName = (String) hashMap.get("fileName");
			if (hashMap.get("remarks") != null)
				remarks = (String) hashMap.get("remarks");
			String type = hashMap.get("type").toString();
			String userId  = hashMap.get("lStrUserId").toString();
			String action = hashMap.get("action").toString();
			StringBuffer lsb = new StringBuffer();
			Long generatedSeqId = null;
			StringBuffer query = new StringBuffer();
			try{
				
				if("delete".equalsIgnoreCase(action)){
					List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
					criteriaList.add(new GenericDAOQueryCriteria("activeYn", "Y", GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					criteriaList.add(new GenericDAOQueryCriteria("patientId", patientId, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					criteriaList.add(new GenericDAOQueryCriteria("seqId", seqId, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					List<EhfJrnlstDAttachment> additionalAttach = genericDao.findAllByCriteria(EhfJrnlstDAttachment.class, criteriaList);
					if(!additionalAttach.isEmpty() && additionalAttach.size() > 0){
						EhfJrnlstDAttachment ehfJrnlstDAttachment = additionalAttach.get(0);
						ehfJrnlstDAttachment.setLstUpdDt(new Date());
						ehfJrnlstDAttachment.setLstUpdUsr(userId);
						ehfJrnlstDAttachment.setActiveYn("N");
							genericDao.save(ehfJrnlstDAttachment);
							result = "success";
						}
				} else{
					String filePath = dJhsuploadFile(patientId,fPath,fileName,type);//To insert EHF_DISP_DRUG_EXCEL_UPLOAD this table and file storage code
					EhfJrnlstDAttachment ehfJrnlstDAttachment = new EhfJrnlstDAttachment();
					ehfJrnlstDAttachment.setPatientId(patientId);
					ehfJrnlstDAttachment.setClaimNo(claimNo);
					ehfJrnlstDAttachment.setFileName(fileName);
					ehfJrnlstDAttachment.setAttcahType("CD7362");
					ehfJrnlstDAttachment.setDocPath(filePath);
					ehfJrnlstDAttachment.setRemarks(remarks);
					ehfJrnlstDAttachment.setCrtDt(new Date());
					ehfJrnlstDAttachment.setCrtUsr(userId);
					ehfJrnlstDAttachment.setActiveYn("Y");
					genericDao.save(ehfJrnlstDAttachment);
					result = "success";
				}
			}catch(Exception le){
				le.printStackTrace();
				result = "fail";
			}
			return result;
		}
	//Chandana - 8755 - New method
	@Transactional
	public String updateMedcoDlhJrnlstClaim(HashMap hashMap) {
		String claimApprvedSeq = null;
		String patientId = null, userId = null, remarks = null, nextStatus = null, status = null, userRole = null, billAmount = "";
		StringBuffer lsb = new StringBuffer();
		double billAmt = 0, finalAmount = 0, estimatedAmnt = 0;
		if(hashMap.get("patientId") != null && hashMap.get("userId") != null){
			patientId = hashMap.get("patientId").toString();
			userId = hashMap.get("userId").toString();
		}
		if(hashMap.get("nextStatus") != null)
			nextStatus = hashMap.get("nextStatus").toString();
		if(hashMap.get("patientId") != null)
			remarks = hashMap.get("remarks").toString();
		if(hashMap.get("status") != null)
			status = hashMap.get("status").toString();
		if(!"Submit".equalsIgnoreCase(status)) 
			claimApprvedSeq = hashMap.get("claimNo").toString(); //getJDClaimNum(patientId);
		if(hashMap.get("userRole") != null)
			userRole = hashMap.get("userRole").toString();
		String deductAmt = hashMap.get("initAmnt").toString();
		if(!"".equalsIgnoreCase(deductAmt))
			billAmt = Double.parseDouble(deductAmt);
		String estmtdAmnt = hashMap.get("estmtdAmnt").toString();
		estimatedAmnt = Double.parseDouble(estmtdAmnt);
		
		try{
			if("Submit".equalsIgnoreCase(status))	{
				lsb.append(" SELECT 'JD'||lpad(EHF_JRNLSTD_CLAIM_SEQ.NEXTVAL,5,'0') ID from DUAL ");
				List<LabelBean> opdSeq =  genericDao.executeSQLQueryList(LabelBean.class, lsb.toString());
				claimApprvedSeq = opdSeq.get(0).getID();
			}
			List<EhfPatient> ehfPatientLst = new ArrayList<EhfPatient>();
			if(patientId != null && !"".equalsIgnoreCase(patientId)){
				List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("patientId", patientId, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				ehfPatientLst = genericDao.findAllByCriteria(EhfPatient.class, criteriaList);
				
				if(!ehfPatientLst.isEmpty() && ehfPatientLst.size() > 0){
					for(EhfPatient ehfPatDtls : ehfPatientLst){
						if("Submit".equalsIgnoreCase(status)){
							ehfPatDtls.setPatientIpop("IP");
							ehfPatDtls.setLstUpdDt(new Date());
							ehfPatDtls.setLstUpdUsr(userId);
							genericDao.save(ehfPatDtls);
						}
					}
				}
				List<EhfJrnlstDAttachment> ehfJrnlstDAttachLst = new ArrayList<EhfJrnlstDAttachment>();
				List<GenericDAOQueryCriteria> criteriaList1 = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList1.add(new GenericDAOQueryCriteria("patientId", patientId, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList1.add(new GenericDAOQueryCriteria("attcahType", "CD7362", GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				ehfJrnlstDAttachLst = genericDao.findAllByCriteria(EhfJrnlstDAttachment.class, criteriaList1);
				if(!ehfJrnlstDAttachLst.isEmpty() && ehfJrnlstDAttachLst.size() > 0){
					for(EhfJrnlstDAttachment ehfJrnlstDAttachment : ehfJrnlstDAttachLst){
						if("Submit".equalsIgnoreCase(status)){
							ehfJrnlstDAttachment.setClaimNo(claimApprvedSeq);;
							genericDao.save(ehfJrnlstDAttachment);
						}
					}
				}
			}
			EhfJrnlstDClaimDtls ehfJrnlstDClaimDtls;
			if("Submit".equalsIgnoreCase(status)){
				ehfJrnlstDClaimDtls = new EhfJrnlstDClaimDtls();
				ehfJrnlstDClaimDtls.setClaimId(claimApprvedSeq);
				ehfJrnlstDClaimDtls.setPatientId(patientId);
				ehfJrnlstDClaimDtls.setClaimStatus(nextStatus);
				ehfJrnlstDClaimDtls.setBillAmount(billAmt);
				ehfJrnlstDClaimDtls.setClaimAmount(billAmt);
				ehfJrnlstDClaimDtls.setEstimatedAmount(estimatedAmnt);
				ehfJrnlstDClaimDtls.setCrtUsr(userId);
				ehfJrnlstDClaimDtls.setCrtDt(new Date());
				genericDao.save(ehfJrnlstDClaimDtls);
						
				FormFile[] disFile = (FormFile[]) hashMap.get("disFile");
				FormFile[] benFile = (FormFile[]) hashMap.get("benFile");
				FormFile[] caseSheetFile = (FormFile[]) hashMap.get("caseSheetFile");
				FormFile[] selfCertFile = (FormFile[]) hashMap.get("selfCertFile");
				FormFile[] invFile = (FormFile[]) hashMap.get("invFile");
				String[] attachTypeIds = (String[]) hashMap.get("attachTypeIds");
				
				Map<String, FormFile[]> fileMap = new HashMap<java.lang.String, FormFile[]>();
				fileMap.put("CD012", disFile);
				fileMap.put("CD7347", benFile);
				fileMap.put("CD7348", caseSheetFile);
				fileMap.put("CD7349", selfCertFile);
				fileMap.put("CD7350", invFile);
				for (String typeId : attachTypeIds) {
					FormFile[] fileArr = fileMap.get(typeId);
					if (fileArr == null) continue;
						for (FormFile file : fileArr) {
				            if (file == null || file.getFileSize() == 0) continue;
				            HashMap hMap = new HashMap();
				            hMap.put("patientId", patientId);
				            hMap.put("claimSeq", claimApprvedSeq);
				            hMap.put("userId", userId);
				            hMap.put("typeId", typeId);
				            String type = "Mandatory"+"~"+typeId;
				            String filePath = dJhsuploadFile(patientId,file,file.getFileName(),type);//To insert EHF_DISP_DRUG_EXCEL_UPLOAD this table and file storage code
				            EhfJrnlstDAttachment ehfJrnlstDAttachment = new EhfJrnlstDAttachment();
				            ehfJrnlstDAttachment.setClaimNo(claimApprvedSeq);
				            ehfJrnlstDAttachment.setPatientId(patientId);
				            ehfJrnlstDAttachment.setAttcahType(typeId);
				            ehfJrnlstDAttachment.setFileName(file.getFileName());
				            ehfJrnlstDAttachment.setDocPath(filePath);
				            ehfJrnlstDAttachment.setActiveYn("Y");
				            ehfJrnlstDAttachment.setCrtDt(new Date());
				            ehfJrnlstDAttachment.setCrtUsr(userId);
				            genericDao.save(ehfJrnlstDAttachment);
				        }
				    }
				}
				else {
					List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
					List<EhfJrnlstDClaimDtls> ehfJrnlstDClaimDtlsLst = new ArrayList<EhfJrnlstDClaimDtls>();
					criteriaList = new ArrayList<GenericDAOQueryCriteria>();
					criteriaList.add(new GenericDAOQueryCriteria("claimId", claimApprvedSeq, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
					ehfJrnlstDClaimDtlsLst = genericDao.findAllByCriteria(EhfJrnlstDClaimDtls.class, criteriaList);
					if(!ehfJrnlstDClaimDtlsLst.isEmpty()){
						for(EhfJrnlstDClaimDtls claimDtls : ehfJrnlstDClaimDtlsLst){
							claimDtls.setClaimStatus(nextStatus);
							//claimDtls.setBillAmount(billAmt);
							claimDtls.setLstUpdUsr(userId);
							claimDtls.setLstUpdDt(new Date());
							genericDao.save(claimDtls);
						}
					}
				}
			boolean insertAuditRecord = insertJrnlstDlhAuditRecord(claimApprvedSeq, nextStatus, remarks, userId,patientId, billAmt, userRole);
			genericDao.flush();
		}catch(Exception le){
			le.printStackTrace();
		}
		genericDao.flush();
		return claimApprvedSeq;
	}
	//Chandana - 8755 - Added new file for djhs file upload
	public String dJhsuploadFile(String patientNo, FormFile file, String fileName, String type) {
	    String lStrAbsolutePath = null;
	    String crNoFolderPath = null;
	    FileOutputStream lFileOutputStream = null;
	    SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
	    String currentDtAndTime = dateFormat.format(new Date());
	    String category = type;
        String typeId = "";

        if (type != null && type.contains("~")) {
            String[] arr = type.split("~");
            category = arr[0];
            typeId = arr[1];
        }
	    try {
	        String basePath = "/NAS4-TS-Production/DJHS_Attach";
	        if("Additional".equalsIgnoreCase(category))
	        	crNoFolderPath = basePath + "/" +  "AdditionalAttach" + "/" + patientNo;
	        else
	        	crNoFolderPath = basePath + "/" + "Mandatory" + "/" + patientNo + "/" + typeId;
	        File crNoDirectory = new File(crNoFolderPath);
	        if (!crNoDirectory.exists())
	            crNoDirectory.mkdirs();
	        if (fileName != null && !fileName.trim().isEmpty()) {
	            String finalFileName = currentDtAndTime + "_" + fileName;
	            lStrAbsolutePath = crNoFolderPath + "/" + finalFileName;
	            File lFile = new File(lStrAbsolutePath);
	            if (lFile.exists()) {
	                lFile.delete();
	            }
	            lFileOutputStream = new FileOutputStream(lFile);
	            lFileOutputStream.write(file.getFileData());
	            lFileOutputStream.flush();
	            lFileOutputStream.close();
	            lFileOutputStream = null;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return lStrAbsolutePath;
	}
	public String dJhsMandatoryuploadFile(FormFile file, String fileName, HashMap hMap) {
		String patId = hMap.get("patientId").toString();
		String typeId = hMap.get("typeId").toString();
		String claimSeq = hMap.get("claimSeq").toString();
		String userId = hMap.get("userId").toString();
		//String file = hMap.get("file").toString();
	    String lStrAbsolutePath = null;
	    String crNoFolderPath = null;
	    FileOutputStream lFileOutputStream = null;
	    SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
	    String currentDtAndTime = dateFormat.format(new Date());
	    try {
	        String basePath = "/NAS4-TS-Production/DJHS_Attach";
	        crNoFolderPath = basePath + "/" + "Mandatory" + "/" + patId + "/" + typeId;
	        File crNoDirectory = new File(crNoFolderPath);
	        if (!crNoDirectory.exists())
	            crNoDirectory.mkdirs();
	        if (fileName != null && !fileName.trim().isEmpty()) {
	            String finalFileName = currentDtAndTime + "_" + fileName;
	            lStrAbsolutePath = crNoFolderPath + "/" + finalFileName;
	            File lFile = new File(lStrAbsolutePath);
	            if (lFile.exists()) {
	                lFile.delete();
	            }
	            lFileOutputStream = new FileOutputStream(lFile);
	            lFileOutputStream.write(file.getFileData());
	            lFileOutputStream.flush();
	            lFileOutputStream.close();
	            lFileOutputStream = null;
	        }
	        EhfJrnlstDAttachment ehfJrnlstDAttachment = new EhfJrnlstDAttachment();
            ehfJrnlstDAttachment.setClaimNo(claimSeq);
            ehfJrnlstDAttachment.setPatientId(patId);
            ehfJrnlstDAttachment.setAttcahType(typeId);
            ehfJrnlstDAttachment.setFileName(file.getFileName());
            ehfJrnlstDAttachment.setDocPath(lStrAbsolutePath);
            ehfJrnlstDAttachment.setCrtDt(new Date());
            ehfJrnlstDAttachment.setCrtUsr(userId);

            genericDao.save(ehfJrnlstDAttachment);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return lStrAbsolutePath;
	}
	//Chandana - 8755 - New method for getting the claim number
	public String getJDClaimNum(String patientId) {
		String claimNo = null;
		StringBuffer query = new StringBuffer();
		List<LabelBean> resList = null;        
        try{
        	query.append(" SELECT DISTINCT CLAIM_ID AS ID FROM EHF_JRNLSTD_CLAIM_DTLS WHERE PATIENT_ID ='"+patientId+"' ");
		    resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		    if(resList != null &&  !resList.isEmpty() && resList.size() > 0){
		    	for(LabelBean opdDtls : resList){
		    		claimNo = resList.get(0).getID();
		    	}
		    }
        }catch(Exception le){
        	le.printStackTrace();
        }
		return claimNo;
	}
	//Chandana - 8755 - New method for inserting the audit record for Delhi journalist
		private boolean insertJrnlstDlhAuditRecord(String jrnlstDClaimSeq, String status, String remarks, String userId, String patientId, Double claimAmnt, String userRole) {
			boolean response = false;
			try{
				List<EhfJrnlstdClaimAudit> ehfJrnlstdClaimAuditLst = new ArrayList<EhfJrnlstdClaimAudit>();
				List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("id.claimSeq", jrnlstDClaimSeq, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				ehfJrnlstdClaimAuditLst = genericDao.findAllByCriteria(EhfJrnlstdClaimAudit.class, criteriaList);
				double maxActOrder = 0;
				if(!ehfJrnlstdClaimAuditLst.isEmpty()){
					for(EhfJrnlstdClaimAudit audit : ehfJrnlstdClaimAuditLst){
						double currentActOrder = audit.getId().getActOrder();
						if(currentActOrder > maxActOrder){
							maxActOrder = currentActOrder;
						}
					}
				}
				double newActOrder = maxActOrder +1;
				EhfJrnlstDClaimAuditPK ehfJrnlstDClaimAuditPK = new EhfJrnlstDClaimAuditPK();
				ehfJrnlstDClaimAuditPK.setClaimSeq(jrnlstDClaimSeq);
				ehfJrnlstDClaimAuditPK.setActOrder(newActOrder);
				EhfJrnlstdClaimAudit ehfJrnlstdClaimAudit = new EhfJrnlstdClaimAudit();
				ehfJrnlstdClaimAudit.setId(ehfJrnlstDClaimAuditPK);
				ehfJrnlstdClaimAudit.setClaimStatus(status);
				ehfJrnlstdClaimAudit.setPatientId(patientId);
				ehfJrnlstdClaimAudit.setRemarks(remarks);
				ehfJrnlstdClaimAudit.setActBy(userId);
				ehfJrnlstdClaimAudit.setCrtUsr(userId);
				ehfJrnlstdClaimAudit.setCrtDt(new Date());	
				ehfJrnlstdClaimAudit.setClaimAmount(claimAmnt);
				ehfJrnlstdClaimAudit.setUserRole(userRole);
				genericDao.save(ehfJrnlstdClaimAudit);
				response = true;
			}catch(Exception le){
				le.printStackTrace();
				response = false;
			}
			return response;
		}
		@Override
		public List<LabelBean> getJrnlstDlhClaimStatusLst(String acoFlag) {//Chandana - 8602 - Added acoFlag to the existing method
			StringBuffer query = new StringBuffer();
			List<LabelBean> resList = null;
	        try{
	        	query.append(" SELECT CMB_DTL_ID AS ID, CMB_DTL_NAME AS VALUE FROM EHFM_CMB_DTLS WHERE CMB_PARNT_DTL_ID ='JD' ");
			    resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
			return resList;
		}
		//Chandana - 8755 - New method for getting the claim number and claim status based on the patient id
		@Override
		public String checkjrnlstDClaimNo(String patientId) {
			String jrnlstDClaimNo = null;
			StringBuffer query = new StringBuffer();
	        List<LabelBean> resLst = null;
	        try{
	        	query.append(" SELECT ejcd.CLAIM_ID AS CLAIMNO FROM EHF_JRNLSTD_CLAIM_DTLS ejcd WHERE ejcd.PATIENT_ID = '"+patientId+"' ");
			    resLst = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
			    if(!resLst.isEmpty()){
			    	jrnlstDClaimNo = resLst.get(0).getCLAIMNO();
			    }
			}catch(Exception le){
				le.printStackTrace();
			}
			return jrnlstDClaimNo;
		}
		//Chandana - 8755 - New method for getting the claim number and claim status based on the patient id
		@Override
		public String checkjrnlstDStatus(String patientId) {
			String claimsStatus = null;
			StringBuffer query = new StringBuffer();
			List<LabelBean> resLst = null;
			String claimNo = null;
			try{
				query.append(" SELECT 'CD7351' AS CASESTATUS FROM ehf_patient ea WHERE ea.PATIENT_ID = '"+patientId+"' AND ea.CARD_TYPE = 'DJ' AND ea.PATIENT_IPOP = 'RG' ");
			    query.append(" UNION ALL SELECT ej.CLAIM_STATUS AS CASESTATUS FROM EHF_JRNLSTD_CLAIM_DTLS ej WHERE ej.PATIENT_ID = '"+patientId+"' ");
				resLst = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
				if(resLst != null){
					claimsStatus = resLst.get(0).getCASESTATUS();
					claimNo = resLst.get(0).getCLAIMNO();
				}
			}catch(Exception le){
				le.printStackTrace();
			}
			return claimsStatus;
		}
		//Chandana - 8755 - New method for getting the Mandatory attachment s for delhi journalist
		@Override
		public List<LabelBean> getDlhJrnlstMandateAttach(HashMap hashMap) {
			String patientId = null;
			if(hashMap.get("patientId") != null)
				patientId  = hashMap.get("patientId").toString();
			StringBuffer query = new StringBuffer();
			List<LabelBean> resList = null;
	        try{
	        	query.append(" SELECT a.SEQ_ID AS COUNT, a.ATTACH_TYPE AS ID, b.CMB_DTL_NAME AS VALUE, a.FILE_NAME AS ATTACH, a.DOC_PATH AS ATTACHPATH FROM EHF_JRNLSTD_ATTACHMENT a, ");
	        	query.append(" EHFM_CMB_DTLS b WHERE a.PATIENT_ID = '"+patientId+"' AND a.ATTACH_TYPE = b.CMB_DTL_ID AND a.ATTACH_TYPE NOT IN ('CD7362') AND ACTIVE_YN = 'Y' ORDER BY b.CMB_DTL_NAME  ");
			    resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
	        }catch(Exception le){
	        	le.printStackTrace();
	        }
			return resList;
		}
		//Chandana - 8755
		public String getInitiatedAmount(String patId) throws Exception{
			List<LabelBean> submitList =null;
			String opClaimNo = null;
			String chApprovedAmnt = null;
			String chDeductAmnt = null;
			String intiatedAmount = null;
			try
			{						  
				StringBuffer query = new StringBuffer();
					query.append(" SELECT CLAIM_AMOUNT AS NEXTVAL, BILL_AMOUNT DIFF,CH_DEDUCT_AMOUNT AS AMOUNT FROM EHF_JRNLSTD_CLAIM_DTLS WHERE PATIENT_ID = '"+patId+"' ");
					submitList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
					if(submitList != null || !submitList.isEmpty())
						chApprovedAmnt = String.valueOf(submitList.get(0).getNEXTVAL());
						chDeductAmnt = String.valueOf(submitList.get(0).getAMOUNT());
						intiatedAmount = String.valueOf(submitList.get(0).getDIFF());
			}
			catch(Exception e)
			{
				GLOGGER.error("Exception Occurred in getInitiatedAmount() in PatientDaoImpl class."+e.getMessage());
				e.printStackTrace();
			}
			return chApprovedAmnt + "~" + chDeductAmnt + "~" + intiatedAmount;
		}
		//Chandana - 8755 - for getting the claims workflow details
		@Override
		public List<LabelBean> getJrnlstDWorkFlowDtls(HashMap hashMap) {
			String patientId = null;
			String rolId = null;
			if(hashMap.get("patientId") != null)
				patientId  = hashMap.get("patientId").toString();
			if(hashMap.get("roleId") != null)
				rolId = hashMap.get("roleId").toString();
			StringBuffer query = new StringBuffer();
			List<LabelBean> resList = null;
	        try{
	        	if("GP2".equalsIgnoreCase(rolId)){
	        		query.append(" SELECT CASE WHEN e.GRP_ID = 'GP2' THEN TRIM(TRIM(C.FIRST_NAME)||' '||TRIM(C.LAST_NAME))||' & '|| e.GRP_SHORT_NAME ");
	        		query.append(" ELSE e.GRP_SHORT_NAME END AS EMPNAME, ");
	        	}
	        	else
	        		query.append(" SELECT c.FIRST_NAME || ' ' || c.LAST_NAME || ' ' || '& ' || e.GRP_SHORT_NAME as EMPNAME, ");
	        	query.append(" b.CMB_DTL_NAME as CASESTATUS,a.REMARKS as REMARKS, TO_CHAR(a.CRT_DT, 'DD/MM/YYYY HH24:MI:SS') AS VALUE,  a.CLAIM_AMOUNT AS AMOUNT ");
	        	query.append(" FROM EHF_JRNLSTD_CLAIM_AUDIT a,EHFM_CMB_DTLS b,EHFM_USERS c,EHF_JRNLSTD_CLAIM_DTLS d, Ehfm_Grps e WHERE a.ACT_BY = c.USER_ID ");
	        	query.append(" AND a.CLAIM_STATUS = b.CMB_DTL_ID AND a.PATIENT_ID = '"+patientId+"' AND a.CLAIM_SEQ = d.CLAIM_ID AND a.user_role = e.GRP_ID ");
	        	query.append(" ORDER BY a.CRT_DT ");
			    resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
	        }catch(Exception le){
	        	le.printStackTrace();
	        }
			return resList;
		}
	//Chandana - 8874 - New method for getting the consolidated attachment
		@Override
		public List<LabelBean> getOPConsolidateBill(HashMap hashMap) {
			String patientId = null;
			if(hashMap.get("patientId") != null)
				patientId  = hashMap.get("patientId").toString();
			StringBuffer query = new StringBuffer();
			StringBuffer query1 = new StringBuffer();
			List<LabelBean> resList = null;
			List<LabelBean> resList1 = null;
			String[] args = null;
			Map lTempQryVal = new HashMap();
	        int cnt=0;
	        try{
	        	query.append(" SELECT a.DOC_PATH AS ATTACHPATH,TO_CHAR(a.CRT_DT, 'DD/MM/YYYY HH24:MI:SS') AS CRTDT  FROM EHF_OPD_BILL_ATTACHMENT a WHERE a.CLAIM_NO ='"+patientId+"' AND a.ACTIVE_YN ='Y' AND a.IS_DOC_ATTCH ='Y' ");
			    resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
	        }catch(Exception le){
	        	le.printStackTrace();
	        }
			return resList;
		}
		//Chandana - 9033 - For Delhi journalist
	@Override
	public List<java.lang.String> getDlhJrnlstClaimCases(String status, String roleId, String userId) {
		List<String> resultLst = new ArrayList<String>();
		StringBuffer query = new StringBuffer();
		List<LabelBean> labelLst = null;
		try{
			query.append(" SELECT DISTINCT ejcd.CLAIM_ID AS CLAIMNO,ejcd.PATIENT_ID AS PatientId,ejcd.CLAIM_STATUS AS ID FROM EHF_JRNLSTD_CLAIM_DTLS ejcd,EHF_PATIENT ep WHERE ejcd.PATIENT_ID = ep.PATIENT_ID ");
			if("GP3".equalsIgnoreCase(roleId))
				query.append(" AND ejcd.CLAIM_STATUS IN ('CD7352', 'CD7364') ");
			else if("GP9".equalsIgnoreCase(roleId) || "GP29".equalsIgnoreCase(roleId))
				query.append(" AND ejcd.CLAIM_STATUS IN ('CD7353', 'CD7355', 'CD7357') ");
			query.append(" ORDER BY ejcd.CLAIM_ID ASC ");
			labelLst = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
			if(!labelLst.isEmpty() && labelLst.size() > 0){
				for(LabelBean ls : labelLst){
					resultLst.add(ls.getCLAIMNO().toString() + "~" + ls.getPATIENTID() + "~" + ls.getID());
				}
			}
		}catch(Exception le){
			le.printStackTrace();
		}
		return resultLst;
	}
	//Chandana - 9033 - below method for updating the nims op cliams by CEX
	@Transactional
	public java.lang.String updCEXDlhJrnlstClaim(HashMap hashMap) {
		String result = null;
		String claimNo = hashMap.get("claimNo").toString();
		String status = hashMap.get("status").toString();
		String remarks = hashMap.get("remarks").toString();
		String userId = hashMap.get("userId").toString();
		String claimStatus = hashMap.get("nextStatus").toString();
		//String patientId = null;
		String patientId = hashMap.get("patientId").toString();
		String finalAmount = hashMap.get("finalAmt").toString();
		String userRole = hashMap.get("userRole").toString();
		double finalAmt = Double.parseDouble(finalAmount);
		try{
			//List<EhfJrnlstDClaimDtls> ehfJrnlstDClaimDtlsLst = new ArrayList<EhfJrnlstDClaimDtls>();
			if(claimNo != null && !"".equalsIgnoreCase(claimNo)){
				List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				List<EhfJrnlstDClaimDtls> ehfJrnlstDClaimDtlsLst = new ArrayList<EhfJrnlstDClaimDtls>();
				criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("claimId", claimNo, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				ehfJrnlstDClaimDtlsLst = genericDao.findAllByCriteria(EhfJrnlstDClaimDtls.class, criteriaList);
				if(!ehfJrnlstDClaimDtlsLst.isEmpty()){
					for(EhfJrnlstDClaimDtls claimDtls : ehfJrnlstDClaimDtlsLst){
						claimDtls.setClaimStatus(claimStatus);
						claimDtls.setLstUpdUsr(userId);
						claimDtls.setLstUpdDt(new Date());
						genericDao.save(claimDtls);
						result = "Success";
					}
				}
				boolean insertAuditRecord = insertJrnlstDlhAuditRecord(claimNo, claimStatus, remarks, userId,patientId,finalAmt, userRole);//Chandana - 8602 - Added userRole parameter to existing method
			}
		}catch(Exception le){
			le.printStackTrace();
			result = "Fail";
		}
		return result;
	}
	@Transactional
	public java.lang.String updDlhJrnlstCHClaim(HashMap hashMap) {
		String result = null;
		String claimStatus = hashMap.get("nextStatus").toString();
		String claimNo = hashMap.get("claimNo").toString();
		String status = hashMap.get("status").toString();
		String remarks = hashMap.get("remarks").toString();
		String deductAmt = hashMap.get("deductAmt").toString();
		String finalAmt = hashMap.get("finalAmt").toString();
		String userId = hashMap.get("userId").toString();
		String patientId = hashMap.get("patientId").toString();
		String userRole = hashMap.get("userRole").toString();
		double deductAmount = 0;
		double finalAmount = 0;
		if(!"".equalsIgnoreCase(deductAmt))
			deductAmount = Double.parseDouble(deductAmt);
		if(finalAmt != null)
			finalAmount = Double.parseDouble(finalAmt);
		try{
			if(claimNo != null && !"".equalsIgnoreCase(claimNo)){
				List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				List<EhfJrnlstDClaimDtls> ehfJrnlstDClaimDtlsLst = new ArrayList<EhfJrnlstDClaimDtls>();
				criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("claimId", claimNo, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				ehfJrnlstDClaimDtlsLst = genericDao.findAllByCriteria(EhfJrnlstDClaimDtls.class, criteriaList);
				
				if(!ehfJrnlstDClaimDtlsLst.isEmpty()){
					for(EhfJrnlstDClaimDtls claimDtls : ehfJrnlstDClaimDtlsLst){
						claimDtls.setClaimStatus(claimStatus);
						claimDtls.setClaimAmount(finalAmount);
						claimDtls.setChDeductAmount(deductAmount);
						claimDtls.setChApprvUsr(userId);
						claimDtls.setChApprvDt(new Date());
						claimDtls.setLstUpdDt(new Date());
						claimDtls.setLstUpdUsr(userId);
						genericDao.save(claimDtls);
						result = "Success";
					}
				}
				boolean insertAuditRecord = insertJrnlstDlhAuditRecord(claimNo, claimStatus, remarks, userId, patientId, finalAmount, userRole);//Chandana - 8602 - Added userRole param in the existing method call
			}
			
		}catch(Exception le){
			le.printStackTrace();
			result = "Fail";
		}
		return result;
	}
	
	@Transactional
	public String dlhJrnlstClaimPenByACO(HashMap hashMap) throws Exception {
		String result = null;
		String claimStatus = hashMap.get("nextStatus").toString();
		String claimNo = hashMap.get("claimNo").toString();
		String remarks = hashMap.get("remarks").toString();
		String finalAmt = hashMap.get("finalAmt").toString();
		String userId = hashMap.get("userId").toString();
		String patientId = hashMap.get("patientId").toString();
		String userRole = hashMap.get("userRole").toString();
		double finalAmount = 0;
		if(finalAmt != null)
			finalAmount = Double.parseDouble(finalAmt);
		try{
			if(claimNo != null && !"".equalsIgnoreCase(claimNo)){
				List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				List<EhfJrnlstDClaimDtls> ehfJrnlstDClaimDtlsLst = new ArrayList<EhfJrnlstDClaimDtls>();
				criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("claimId", claimNo, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				ehfJrnlstDClaimDtlsLst = genericDao.findAllByCriteria(EhfJrnlstDClaimDtls.class, criteriaList);
				if(!ehfJrnlstDClaimDtlsLst.isEmpty()){
					for(EhfJrnlstDClaimDtls claimDtls : ehfJrnlstDClaimDtlsLst){
						claimDtls.setClaimStatus(claimStatus);
						claimDtls.setAcoApprvAmount(finalAmount);
						claimDtls.setAcoApprvUsr(userId);
						claimDtls.setAcoApprvDt(new Date());
						claimDtls.setLstUpdDt(new Date());
						claimDtls.setLstUpdUsr(userId);
						genericDao.save(claimDtls);
						result = "Success";
					}
				}
				boolean insertAuditRecord = insertJrnlstDlhAuditRecord(claimNo, claimStatus, remarks, userId, patientId, finalAmount, userRole);
			}
			
		}catch(Exception le){
			le.printStackTrace();
			result = "Fail";
		}
		return result;
	}
	@Override
	public String getDlhJurnlstClaimStatus(String patientId) {
		String status = null;
		StringBuffer query = new StringBuffer();
		List<LabelBean> resList = null;
		try{
			query.append(" SELECT DISTINCT CLAIM_STATUS ID FROM EHF_JRNLSTD_CLAIM_DTLS WHERE  PATIENT_ID = '"+patientId+"' ");
			resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
			if(resList != null &&  !resList.isEmpty() && resList.size() > 0){
				for(LabelBean opdDtls : resList){
					status = resList.get(0).getID();
				}
			}
		}catch(Exception le){
			le.printStackTrace();
		}
		return status;
	}
	//Chandana - 9033 - New moethod for getting the list for ACO approvals
	@Override
	public List<LabelBean> getDlhJrnlstClaimsLstForApprvl(HashMap<java.lang.String, java.lang.String> hashMap) {
		String claimDt = hashMap.get("claimDt");
		String claimStatus = hashMap.get("claimStatus");
		String claimNo = hashMap.get("claimSubmittedNo");
		String patientId  = hashMap.get("patientId");
		String cardNo = hashMap.get("cardNo");
		String searchFlg = hashMap.get("searchFlg");
		StringBuffer query = new StringBuffer();
		List<LabelBean> resList = null;
		String[] args = null;
		Map lTempQryVal = new HashMap();
	    int cnt=0; 
	    boolean hasFilter = false;
	    try{
		    query.append(" SELECT EJC.CLAIM_ID AS CLAIMNO,EJC.PATIENT_ID AS PATIENTID, ecd.CMB_DTL_ID AS ID, ep.CARD_NO AS EHFCARDNO,ep.Name AS PNAME,ecd.CMB_DTL_NAME VALUE,ep.CRT_DT AS DOB,ejc.CLAIM_AMOUNT AS AMOUNT, ");
		    query.append(" TO_CHAR(ejc.CRT_DT, 'dd-MM-yyyy hh24:mi:ss') AS CLAIMDT, eh.HOSP_NAME HOSPNAME FROM EHF_PATIENT ep,EHF_JRNLSTD_CLAIM_DTLS ejc,EHFM_CMB_DTLS ecd,EHFM_HOSPITALS eh WHERE CARD_TYPE = 'DJ' AND ep.PATIENT_ID = ejc.PATIENT_ID ");
		    query.append(" AND ejc.CLAIM_STATUS = ecd.CMB_DTL_ID AND eh.HOSP_ID = ep.REG_HOSP_ID ");
		    if(patientId != null && !"".equalsIgnoreCase(patientId)){
		    	query.append(" AND EJC.PATIENT_ID= ? ");
		    	lTempQryVal.put(new Integer(++cnt), patientId);
		        hasFilter = true;
		    }
		   	if(cardNo != null && !"".equalsIgnoreCase(cardNo)){
		    	query.append(" AND ep.CARD_NO = ? ");
		    	lTempQryVal.put(new Integer(++cnt), cardNo);
		        hasFilter = true;
		    }
		   	if(claimStatus != null && !"-1".equalsIgnoreCase(claimStatus)){
		   		query.append(" AND ejc.CLAIM_STATUS = ? ");
		   		lTempQryVal.put(new Integer(++cnt), claimStatus);
	        	hasFilter = true;
		   	}
		   	if(claimNo != null && !"".equalsIgnoreCase(claimNo)){
		   		query.append(" AND ejc.CLAIM_ID = ? ");
		   		lTempQryVal.put(new Integer(++cnt), claimNo);
	        	hasFilter = true;
		   	}
	    	if(claimDt != null && !"".equalsIgnoreCase(claimDt)){
	    		query.append(" AND TO_CHAR(EJC.CRT_DT, 'DD/MM/YYYY') = ? ");
	    		lTempQryVal.put(new Integer(++cnt), claimDt);
	        	hasFilter = true;
	    	}
	        if (!hasFilter)
	        	query.append(" AND EJC.CLAIM_STATUS IN ('CD7356','CD7358') ");
	        query.append(" ORDER BY DOB ");	
	        args = new String[cnt];
			for (int i = 1; i <= cnt; i++) {
				args[i-1] = lTempQryVal.get(new Integer(i)).toString();
			}
			resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(), args);
	       }catch(Exception e){
	        	e.printStackTrace();
	       }
		return resList;
	}
	@Override
	public List<LabelBean> getAdmissionDtls(HashMap hashMap) {//Chandana - 9045 - New method for getting the admission details
		String patientId = null;
		StringBuffer query = new StringBuffer();
		List<LabelBean> resList = null;
		String[] args = null;
		Map lTempQryVal = new HashMap();
		patientId = (String) hashMap.get("patientId");
        try{
        	query.append(" SELECT a.PATIENT_ID AS PATIENTID,TO_CHAR(a.ADM_DATE, 'dd-MM-yyyy') AS ID,a.ADM_NOTE AS VALUE,a.EST_AMT AS NEXTVAL,a.DOC_PATH AS ATTACHPATH ");
        	query.append(" FROM EHF_JRNLSTD_ADM_EST_DTLS a,EHF_PATIENT b WHERE a.patient_id = b.PATIENT_ID AND b.PATIENT_ID = '"+patientId+"' ");
        	resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
        }catch(Exception le){
        	le.printStackTrace();
        }
		return resList;
	}
	@Override
	public String getPatientId(String caseId) {
		String patId = null;
		StringBuffer query = new StringBuffer();
		List<LabelBean> resList = null;
		try{
			query.append(" SELECT PATIENT_ID AS ID FROM EHF_JRNLSTD_CLAIM_DTLS ejcd WHERE CLAIM_ID = '"+caseId+"' ");
			resList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
			if(resList != null &&  !resList.isEmpty() && resList.size() > 0){
				for(LabelBean opdDtls : resList){
					patId = resList.get(0).getID();
				}
			}
		}catch(Exception le){
			le.printStackTrace();
		}
		return patId;
	}
	@Override
	@Transactional
	public ClaimsFlowVO saveDlhJrnlstClaim(ClaimsFlowVO claimFlowVO) throws Exception {
		String result = null;
		double deductAmount = 0;
		double finalAmount = 0;
		String claimNo = claimFlowVO.getCaseId();
		String claimStatus = claimFlowVO.getCaseStat();
		String userId = claimFlowVO.getUserId();
		String patientId = claimFlowVO.getPatientId();
		try{
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			List<EhfJrnlstDClaimDtls> ehfJrnlstDClaimDtlsLst = new ArrayList<EhfJrnlstDClaimDtls>();
			criteriaList = new ArrayList<GenericDAOQueryCriteria>();
			criteriaList.add(new GenericDAOQueryCriteria("claimId", claimNo, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			ehfJrnlstDClaimDtlsLst = genericDao.findAllByCriteria(EhfJrnlstDClaimDtls.class, criteriaList);	
			if(!ehfJrnlstDClaimDtlsLst.isEmpty()){
				for(EhfJrnlstDClaimDtls claimDtls : ehfJrnlstDClaimDtlsLst){
					claimDtls.setClaimStatus(claimStatus);
					claimDtls.setAcoApprvAmount(claimDtls.getClaimAmount());
					claimDtls.setAcoApprvDt(new Date());
					claimDtls.setAcoApprvUsr(userId);
					claimDtls.setLstUpdDt(new Date());
					claimDtls.setLstUpdUsr(userId);
					genericDao.save(claimDtls);
					result = "Success";
				}
			}
			boolean insertAuditRecord = insertJrnlstDlhAuditRecord(claimNo, claimStatus, claimFlowVO.getAcoRemark(), userId, patientId, finalAmount, claimFlowVO.getRoleId());
		}catch(Exception le){
			le.printStackTrace();
			result = "Fail";
		}
		return claimFlowVO;
	}
}
