package com.ahct.annualCheckUp.action;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.commons.configuration.Configuration;

import com.ahct.annualCheckUp.form.AnnualCheckUpForm;
import com.tcs.framework.common.util.HtmlEncode;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.emailConfiguration.EmailConstants;
import com.tcs.framework.emailConfiguration.EmailVO;
import com.ahct.annualCheckUp.service.AnnualCheckUpService;
import com.ahct.chronicOp.service.ChronicOPService;
import com.ahct.common.util.ServiceFinder;
import com.ahct.common.vo.LabelBean;
import com.ahct.common.vo.PatientSmsVO;
import com.ahct.model.EhfAhcMedicalReport;
import com.ahct.model.EhfAhcPatientTest;
import com.ahct.model.EhfAnnualCaseDtls;
import com.ahct.model.EhfAnnualPatientDtls;
import com.ahct.model.EhfmSeq;
import com.ahct.patient.action.PatientAction;
import com.ahct.patient.service.PatientService;
import com.ahct.patient.vo.PatientVO;
import com.ahct.annualCheckUp.vo.AnnualCheckUpVo;
import com.ahct.common.service.CommonService;
import com.ahct.annualCheckUp.form.AnnualCheckUpForm;
import com.ahct.attachments.constants.ASRIConstants;

public class AnnualCheckUpAction extends Action

{
	private final static Logger GLOGGER = Logger.getLogger ( AnnualCheckUpAction.class ) ;
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response) throws Exception 
			{
				
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("pragma","no-cache");
		response.setDateHeader("Expires", 0); 
		String lStrActionVal="";
		String lStrPageName="";
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		String serverDt = sdfw.format(new Date());
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		FileInputStream fis=null;
		DataInputStream dis=null;
		byte bytes[]=null;
		String userName=null;
		String lStrLangID=null;
		String lStrUserId =null,schemeId=null;
		List<LabelBean> grpList=null;
		List<String> lStrgrpList=new ArrayList<String>();
		String roleId=null;
		String smsNextVal="";
				HttpSession session = request.getSession ( false ) ;
				
				String lStrResultPage = HtmlEncode.verifySession(request, response);
		        if (lStrResultPage.length() > 0)
		        {
		            return mapping.findForward("sessionExpire");
		        }
				
				AnnualCheckUpForm annualCheckUpForm=(AnnualCheckUpForm)form;
				ConfigurationService configurationService=(ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
				CommonService commonService =(CommonService)ServiceFinder.getContext(request).getBean("commonService");
				AnnualCheckUpService annualCheckUpService =(AnnualCheckUpService)ServiceFinder.getContext(request).getBean("annualCheckUpService");
				PatientService patientService =(PatientService)ServiceFinder.getContext(request).getBean("patientService");
				Configuration config=configurationService.getConfiguration();
				ChronicOPService chronicOPService=(ChronicOPService)ServiceFinder.getContext(request).getBean("chronicOPService");
				String stateHdrId =config.getString("IPOP.StateHDRID");
				String maritalStatusHdrId=config.getString("IPOP.MaritalStatusCMBHDRID");
				String statePrntId =config.getString("IPOP.StatePrntId");
				
				String lStrcastesHdrId = config.getString("IPOP.CasteCMBHDRID");
				String lStrMandalHdrId = config.getString("IPOP.MandalHDRID");
				String lStrMunicipalHdrId = config.getString("IPOP.MunicipalityHDRID");
				String lStrVillageHdrId = config.getString("IPOP.VillageHDRID");
				String lStrMplVillageHdrId = config.getString("IPOP.MunicipalVillageHDRID");
				String distHdrId =config.getString("IPOP.DistrictHDRID");
				String userState=null;
				if ( ( session.getAttribute ( "userState" ) != null ) && !( session.getAttribute ( "userState" ).equals ( "" ) ) )
			    	userState = ( String ) session.getAttribute ( "userState" ) ;
			    request.setAttribute("userState", userState);
				
				List<LabelBean> stateList = commonService.getAsrimLocations(stateHdrId, statePrntId);
				session.setAttribute("stateList", stateList);
				if(session.getAttribute("EmpID")!=null)
				{
					lStrUserId = session.getAttribute("EmpID").toString();
				}
				if(session.getAttribute("LangID")!=null)
				{
					lStrLangID = session.getAttribute("LangID").toString();
				}
				if(session.getAttribute("userName")!=null)
				{
					userName=session.getAttribute("userName").toString();
				}
				if(session.getAttribute("userState").toString()!=null)
				{
					schemeId=session.getAttribute("userState").toString();
				}
				if(session.getAttribute("groupList")!=null)
				{
					grpList=(List<LabelBean>)session.getAttribute("groupList");
				}
				for(LabelBean labelBean:grpList)
				{
					lStrgrpList.add(labelBean.getID());
				}
				if(lStrgrpList.contains(config.getString("Group.Mithra")))
				{
					roleId=config.getString("Group.Mithra");
				}
				else if(lStrgrpList.contains(config.getString("Group.Medco")))
				{
					roleId=config.getString("Group.Medco");
				}
				else if(lStrgrpList.contains(config.getString("Group.Pex")))
				{
					roleId=config.getString("Group.Pex");
				}else if(lStrgrpList.contains(config.getString("Group.Pex")))
				{
					roleId=config.getString("Group.Pex");
				}else if(lStrgrpList.contains(config.getString("Group.AHC-EX")))
				{
					roleId=config.getString("Group.AHC-EX");
				}else if(lStrgrpList.contains(config.getString("Group.AHC-TD")))
				{
					roleId=config.getString("Group.AHC-TD");
				}else if(lStrgrpList.contains(config.getString("Group.AHC-CH")))
				{
					roleId=config.getString("Group.AHC-CH");
				}else if(lStrgrpList.contains(config.getString("Group.AHC-ACO")))
				{
					roleId=config.getString("Group.AHC-ACO");
				}else if(lStrgrpList.contains(config.getString("Group.AHC-EO")))
				{
					roleId=config.getString("Group.AHC-EO");
				}
				else
				{
					roleId=lStrgrpList.get(0);
				}
				List<LabelBean> investigationsList = null;
				investigationsList=commonService.getInvestBlockName();
				request.setAttribute("investigationsList", investigationsList);
				String hospId=null;
		 hospId=chronicOPService.getHospitalID(session.getAttribute("UserID").toString(),roleId);
				
				
				if(request.getParameter("actionFlag")!=null && !request.getParameter("actionFlag").equalsIgnoreCase(""))
				{
					lStrActionVal=request.getParameter("actionFlag");
				}
				
				if("openAnnualPatRegForm".equalsIgnoreCase(lStrActionVal))
					{
						List<LabelBean> maritalStatusList=commonService.getComboDetails(maritalStatusHdrId);
						session.setAttribute("maritalStatusList", maritalStatusList);
						
						List<LabelBean> relationList=annualCheckUpService.getRelations();
						session.setAttribute("relationList",relationList);
						
					
						
						List<LabelBean> hospitalList=annualCheckUpService.getHospital(lStrUserId,roleId);
						session.setAttribute("hospitalList",hospitalList);
						
						annualCheckUpForm.setCardType("E");
						annualCheckUpForm.setHead("Y");
						annualCheckUpForm.setDtTime(serverDt);
						annualCheckUpForm.setDisableFlag(config.getString("YFlag"));
						
						String hospStatus= annualCheckUpService.getHospActiveStatus(lStrUserId,roleId);
						if(config.getString("inactive_status")!=null && hospStatus!=null 
								&& config.getString("inactive_status").contains("~"+hospStatus+"~"))
						{
							request.setAttribute("inActiveStatusMsg", config.getString("msg_"+hospStatus));
							request.setAttribute("inActiveStatusFlag", "Y");
						}
						
						lStrPageName="annualCheckUpReg";
					}
				
				if ("getLocations".equalsIgnoreCase(lStrActionVal))
				{
					String locHdrId=request.getParameter("lStrHdrId");
					String stateId=request.getParameter("stateId");
					String distId=request.getParameter("distId");
					String mandalId=request.getParameter("mandalId");
					String villageId=request.getParameter("villageId");
					String stateType=request.getParameter("stateType");
					try {
						List<LabelBean> cmbHdrList=null;
						List<String> locationsList = new ArrayList<String>();
						if(stateId!=null)
						{
							cmbHdrList=commonService.getLocationsNew(locHdrId, stateId,stateType);
							//cmbHdrList=commonService.getAsrimLocations(locHdrId, stateId);
							request.setAttribute("districtList",cmbHdrList);
						}
						else if(distId!=null)
						{
							cmbHdrList=commonService.getAsrimLocations(locHdrId, distId);
							request.setAttribute("mandalList",cmbHdrList);
						}
						else if(mandalId!=null)
						{
							cmbHdrList=commonService.getAsrimLocations(locHdrId, mandalId);
							request.setAttribute("villageList",cmbHdrList);
						}
						else if(villageId!=null)
						{
							cmbHdrList=commonService.getAsrimLocations(locHdrId, villageId);
							request.setAttribute("hamletList",cmbHdrList);
						}
						for (LabelBean labelBean: cmbHdrList) {
							if (labelBean.getID() != null && 
									labelBean.getVALUE() != null)
								if (locationsList != null && locationsList.size() > 0) {
									locationsList.add(labelBean.getID() + "~" + 
											labelBean.getVALUE());
								} else
									locationsList.add(labelBean.getID() + "~" + 
											labelBean.getVALUE());
						}
						if (locationsList != null && locationsList.size() > 0)

							request.setAttribute("AjaxMessage", locationsList);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					lStrPageName="ajaxResult";
				}
				if("retrieveCardDetails".equalsIgnoreCase(lStrActionVal))
				{
					AnnualCheckUpVo annualCheckUpVo=new AnnualCheckUpVo();
					String cardNo=request.getParameter("cardNo");
					annualCheckUpVo.setCardNo(cardNo);
					annualCheckUpVo.setCardType(annualCheckUpForm.getCardType());
					annualCheckUpVo.setSchemeId(schemeId);
					annualCheckUpVo=annualCheckUpService.retrieveCardDetails(annualCheckUpVo);
					if(annualCheckUpVo!=null)
					{
						if(annualCheckUpVo.getVillageCode()!=null )
						{
							LabelBean labelBeanVillage=new LabelBean();
							labelBeanVillage.setID(annualCheckUpVo.getVillageCode());
							labelBeanVillage=commonService.getNewLocations(labelBeanVillage);
							if(labelBeanVillage!=null)
							{
								annualCheckUpVo.setDistrictCode(labelBeanVillage.getNEW_DIST());					
								annualCheckUpVo.setMandalCode(labelBeanVillage.getNEW_MAND());
								annualCheckUpVo.setVillageCode(labelBeanVillage.getNEW_VILLAGE());
								
							}
						}
					}
					/*patientForm.setTelephonicId(patientForm.getTelephonicId());
					patientForm.setTelephonicReg(patientForm.getTelephonicReg());*/
					
					if(annualCheckUpVo!=null && !(config.getString("inactivate_card_death_remarks").equalsIgnoreCase(annualCheckUpVo.getMsg()))
												&& !(config.getString("inactivate_ahc_remarks").equalsIgnoreCase(annualCheckUpVo.getMsg()))
												&& !(config.getString("inactivate_ahc_year_remarks").equalsIgnoreCase(annualCheckUpVo.getMsg()))
												&& !(config.getString("invalid_state_remarks").equalsIgnoreCase(annualCheckUpVo.getMsg())))
					{
						annualCheckUpForm.setCardIssueDt(annualCheckUpVo.getCardIssueDt());
						annualCheckUpForm.setEmpCode(annualCheckUpVo.getEmpCode());
						annualCheckUpForm.setCardNo(cardNo);
						annualCheckUpForm.setCardType(annualCheckUpForm.getCardType());
						annualCheckUpForm.setHead("Y");
						annualCheckUpForm.setDisableFlag(config.getString("NFlag"));
						annualCheckUpForm.setAddrEnable(config.getString("YFlag"));
						String dob[]=annualCheckUpVo.getDateOfBirth().substring(0,10).split("-");
						String finalDob=dob[2]+"-"+dob[1]+"-"+dob[0];

						annualCheckUpForm.setErrMsg("");
						annualCheckUpForm.setDateOfBirth(finalDob);
						annualCheckUpForm.setGender(annualCheckUpVo.getGender());
						annualCheckUpForm.setName(annualCheckUpVo.getName());
						annualCheckUpForm.setRelation(annualCheckUpVo.getRelation());
						annualCheckUpForm.setCaste(annualCheckUpVo.getCaste());
						annualCheckUpForm.setContactno(annualCheckUpVo.getContactno());
						annualCheckUpForm.setMaritalStatus(annualCheckUpVo.getMaritalStatus());
						annualCheckUpForm.setDesignation(annualCheckUpVo.getDesignation());
						annualCheckUpForm.setSlab(annualCheckUpVo.getSlab());
						annualCheckUpForm.setScheme(annualCheckUpVo.getScheme());
						annualCheckUpForm.setPostDist(annualCheckUpVo.getPost_Dist());
						annualCheckUpForm.setDept_Hod(annualCheckUpVo.getDept_Hod());
						annualCheckUpForm.setDdoCode(annualCheckUpVo.getPost_Ddo_Code());
						//annualCheckUpForm.setStoCode(annualCheckUpVo.getDdoOffUnit());
						request.setAttribute("scheme", annualCheckUpVo.getScheme());
						if(annualCheckUpVo.geteMailId()!=null && !annualCheckUpVo.geteMailId().equals(""))
						{
							annualCheckUpForm.seteMailId(annualCheckUpVo.geteMailId());
						}
						if(annualCheckUpVo.getPhoto()!=null)
						{
							try
							{
								File photo = new File(annualCheckUpVo.getPhoto());
								fis = new FileInputStream(photo);
								dis= new DataInputStream(fis);
								bytes = new byte[dis.available()];
								fis.read(bytes);
								String lStrContentType=null;
								lStrContentType="image/jpg";
								request.setAttribute("ContentType", lStrContentType);
								request.setAttribute("File", bytes);
								fis.close();
								dis.close();
								annualCheckUpForm.setPhotoUrl(annualCheckUpVo.getPhoto());
							}
							catch(Exception e)
							{
								/*GLOGGER.error ( "Exception occured while photo is not available in the path specified in PatientAction." +e.getMessage()) ;*/
								e.printStackTrace();
							}
						}
						annualCheckUpForm.setHno(annualCheckUpVo.getAddr1());
						annualCheckUpForm.setStreet(annualCheckUpVo.getAddr2());
						List<LabelBean> districtList=null;
						if(annualCheckUpVo.getState()!=null)
						{
							annualCheckUpForm.setState(annualCheckUpVo.getState());
							districtList=commonService.getAsrimLocations(distHdrId, annualCheckUpVo.getState());
						}
						request.setAttribute("districtList",districtList);
						annualCheckUpForm.setDistrict(annualCheckUpVo.getDistrict());
						annualCheckUpForm.setMdl_mcl(annualCheckUpVo.getMdl_mcl());
						if(annualCheckUpVo.getMdl_mcl()!=null && annualCheckUpVo.getMdl_mcl().equalsIgnoreCase("Mdl"))
						{
							List<LabelBean> mdlList=commonService.getAsrimLocations(lStrMandalHdrId, annualCheckUpVo.getDistrict());
							request.setAttribute("mdlList", mdlList);
							annualCheckUpForm.setMandal(annualCheckUpVo.getMandal());

							List<LabelBean> villList=commonService.getAsrimLocations(lStrVillageHdrId, annualCheckUpVo.getMandal());
							request.setAttribute("villList", villList);
						}
						else if(annualCheckUpVo.getMdl_mcl()!=null && annualCheckUpVo.getMdl_mcl().equalsIgnoreCase("Mpl"))
						{
							List<LabelBean> mplList=commonService.getAsrimLocations(lStrMunicipalHdrId, annualCheckUpVo.getDistrict());
							request.setAttribute("mplList", mplList);
							annualCheckUpForm.setMunicipality(annualCheckUpVo.getMandal());

							List<LabelBean> villList=commonService.getAsrimLocations(lStrMplVillageHdrId, annualCheckUpVo.getMandal());
							request.setAttribute("villList", villList);
						}
						annualCheckUpForm.setVillage(annualCheckUpVo.getVillage());
						annualCheckUpForm.setDtTime(serverDt);
						//GLOGGER.info ( "Setting  Card Details to PatientForm in PatientAction." +"  Mandal Code is "+patientVO.getMandalCode()+"  and village Code is"+patientVO.getVillageCode()) ;
						
						
					}
					else if(annualCheckUpVo!=null)
					{
						if(annualCheckUpVo.getMsg()!=null)
						{
							if((config.getString("inactivate_card_death_remarks").equalsIgnoreCase(annualCheckUpVo.getMsg()))||
										config.getString("inactivate_ahc_remarks").equalsIgnoreCase(annualCheckUpVo.getMsg()) ||
										config.getString("inactivate_ahc_year_remarks").equalsIgnoreCase(annualCheckUpVo.getMsg())
										||(config.getString("invalid_state_remarks").equalsIgnoreCase(annualCheckUpVo.getMsg())))
								{
									annualCheckUpForm.setDisableFlag(config.getString("YFlag"));
									annualCheckUpForm.setCardNo("");
									annualCheckUpForm.setCardType("E");
									annualCheckUpForm.setHead("Y");
									annualCheckUpForm.setDtTime(serverDt);
									annualCheckUpForm.setErrMsg(config.getString("inactivate_card_death_remarks"));
									if(config.getString("inactivate_ahc_remarks").equalsIgnoreCase(annualCheckUpVo.getMsg()))
										annualCheckUpForm.setErrMsg(config.getString("inactivate_ahc_remarks"));
									if(config.getString("inactivate_ahc_year_remarks").equalsIgnoreCase(annualCheckUpVo.getMsg()))
										annualCheckUpForm.setErrMsg(config.getString("inactivate_ahc_year_remarks"));
									if(config.getString("invalid_state_remarks").equalsIgnoreCase(annualCheckUpVo.getMsg()))
										annualCheckUpForm.setErrMsg(config.getString("invalid_state_remarks"));
								}
						}
					}
					else
					{
						annualCheckUpForm.setDisableFlag(config.getString("YFlag"));
						annualCheckUpForm.setCardNo("");
						annualCheckUpForm.setCardType("E");
						annualCheckUpForm.setHead("Y");
						annualCheckUpForm.setDtTime(serverDt);
						annualCheckUpForm.setErrMsg(config.getString("err.InvalidCardNo"));
					}
					

					/*if(request.getParameter("SelOper")!=null && request.getParameter("SelOper").equalsIgnoreCase("TELE"))
						lStrPageName = "telephonicPatientEntry";
					else*/
						lStrPageName = "annualCheckUpReg";
				}
				
				if ("registerPatientDetails".equalsIgnoreCase(lStrActionVal))
				{
					AnnualCheckUpVo annualCheckUpVo;
					System.out.println(annualCheckUpForm.getEmpCode());
					String wapNo=request.getParameter("wapNo");
					String familyNo=request.getParameter("familyNo");
					annualCheckUpVo=new AnnualCheckUpVo();
					//EhfmSeq ehfmSeqPatient = null;
					String liNextVal="";
					String userId=null;
					int phaseRenewal=0;
					Date date = new Date();
					String crtDate=sdfw.format(date);
					Date crtDt = sdfw.parse((sdfw.format(date)));
					userId=session.getAttribute("EmpID").toString(); 
					//GLOGGER.info("Card No"+wapNo+"/"+familyNo) ;
					
					String conHospId=annualCheckUpForm.getHospitalId().substring(3);
					
					if(annualCheckUpForm.getScheme()!=null && config.getString("Scheme.ap").equalsIgnoreCase(annualCheckUpForm.getScheme()))
					{
						liNextVal = "AP"+annualCheckUpService.getSequence("EHF_AP_AHC_ID");
					}
					else if(annualCheckUpForm.getScheme()!=null && config.getString("Scheme.tg").equalsIgnoreCase(annualCheckUpForm.getScheme()))
					{
						liNextVal = "TG"+annualCheckUpService.getSequence("EHF_TG_AHC_ID");
					}
					else if(annualCheckUpForm.getScheme()!=null && "1".equalsIgnoreCase(annualCheckUpForm.getScheme()))
					{
						liNextVal = annualCheckUpService.getSequence("EHF_AHC_ID");
					}
					liNextVal=config.getString("CASE.AHC")+config.getString("SLASH_VALUE")+conHospId+
							   config.getString("SLASH_VALUE")+liNextVal;
					//annualCheckUpVo.setDdoOffUnit(annualCheckUpForm.getStoCode());
					//liNextVal = annualCheckUpService.getSequence("PATIENT_ID");

					annualCheckUpVo.setPatientId(liNextVal);
					annualCheckUpVo.setProcessInstanceId("1");
					annualCheckUpVo.setCrtDt(crtDate);
					annualCheckUpVo.setCrtUsr(userId);
					annualCheckUpVo.setRationCard((wapNo+"/"+familyNo).toUpperCase());
					annualCheckUpVo.setCardType(annualCheckUpForm.getCardType());
					annualCheckUpVo.setCardIssueDt(annualCheckUpForm.getCardIssueDt());
					annualCheckUpVo.setDateOfBirth(annualCheckUpForm.getDateOfBirth());
					annualCheckUpVo.setName(annualCheckUpForm.getName());
					annualCheckUpVo.setGender(annualCheckUpForm.getGender());
					if(!familyNo.equalsIgnoreCase("01") && annualCheckUpForm.getHead()==null)
					{
						annualCheckUpVo.setFamilyHead(config.getString("NFlag"));
					}
					else
					{
						annualCheckUpVo.setFamilyHead(config.getString("YFlag"));
						annualCheckUpVo.setChild_yn(config.getString("NFlag"));
					}
					String[] age=annualCheckUpForm.getAgeString().split("~");
					annualCheckUpVo.setEmpCode(annualCheckUpForm.getEmpCode());
					annualCheckUpVo.setAge(age[0]);
					annualCheckUpVo.setAgeMonths(age[1]);
					annualCheckUpVo.setAgeDays(age[2]);
					annualCheckUpVo.setRelation(annualCheckUpForm.getRelation());
					annualCheckUpVo.setCaste(annualCheckUpForm.getCaste());
					annualCheckUpVo.setMaritalStatus(annualCheckUpForm.getMaritalStatus());
					annualCheckUpVo.setOccupationCd(annualCheckUpForm.getDesignation());
					annualCheckUpVo.setContactno(annualCheckUpForm.getContactno());
					annualCheckUpVo.setSlab(annualCheckUpForm.getSlab());
					annualCheckUpVo.seteMailId(annualCheckUpForm.geteMailId());
					annualCheckUpVo.setAddr1(annualCheckUpForm.getHno());
					annualCheckUpVo.setAddr2(annualCheckUpForm.getStreet());
					annualCheckUpVo.setVillage(annualCheckUpForm.getVillage());
					annualCheckUpVo.setState(annualCheckUpForm.getState());
					annualCheckUpVo.setDistrict(annualCheckUpForm.getDistrict());
					
					annualCheckUpVo.setDept_Hod(annualCheckUpForm.getDept_Hod());
					annualCheckUpVo.setPost_Dist(annualCheckUpForm.getPostDist());
					//annualCheckUpVo.setDdoOffUnit(annualCheckUpForm.getStoCode());
					annualCheckUpVo.setPost_Ddo_Code(annualCheckUpForm.getDdoCode());
					
					
					annualCheckUpVo.setMdl_mpl(annualCheckUpForm.getMdl_mcl());
					if(annualCheckUpForm.getMdl_mcl().equalsIgnoreCase("Mdl"))
					{
						annualCheckUpVo.setMandal(annualCheckUpForm.getMandal());
					}
					else if(annualCheckUpForm.getMdl_mcl().equalsIgnoreCase("Mpl"))
					{
						annualCheckUpVo.setMandal(annualCheckUpForm.getMunicipality());
					}
					annualCheckUpVo.setPin(annualCheckUpForm.getPin());
					//annualCheckUpVo.setSrcRegistration(config.getString("PatientIPOP.Source"));
					//start sameaddress check
					if(annualCheckUpForm.getCommCheck()==null)
					{
						annualCheckUpVo.setPermAddr1(annualCheckUpForm.getComm_hno());
						annualCheckUpVo.setPermAddr2(annualCheckUpForm.getComm_street());
						annualCheckUpVo.setC_pin_code(annualCheckUpForm.getComm_pin());
						annualCheckUpVo.setC_state(annualCheckUpForm.getComm_state());
						annualCheckUpVo.setC_district_code(annualCheckUpForm.getComm_dist());
						annualCheckUpVo.setC_mdl_mpl(annualCheckUpForm.getComm_mdl_mcl());
						if(annualCheckUpForm.getComm_mdl_mcl().equalsIgnoreCase("Mdl"))
						{
							annualCheckUpVo.setC_mandal_code(annualCheckUpForm.getComm_mandal());
						}
						else if(annualCheckUpForm.getComm_mdl_mcl().equalsIgnoreCase("Mpl"))
						{
							annualCheckUpVo.setC_mandal_code(annualCheckUpForm.getComm_municipality());
						}

						annualCheckUpVo.setC_village_code(annualCheckUpForm.getComm_village());	
					}
					else
					{
						annualCheckUpVo.setPermAddr1(annualCheckUpForm.getHno());
						annualCheckUpVo.setPermAddr2(annualCheckUpForm.getStreet());
						annualCheckUpVo.setC_pin_code(annualCheckUpForm.getPin());
						annualCheckUpVo.setC_state(annualCheckUpForm.getState());
						annualCheckUpVo.setC_district_code(annualCheckUpForm.getDistrict());
						annualCheckUpVo.setC_mdl_mpl(annualCheckUpForm.getMdl_mcl());
						if(annualCheckUpForm.getMdl_mcl().equalsIgnoreCase("Mdl"))
						{
							annualCheckUpVo.setC_mandal_code(annualCheckUpForm.getMandal());
						}
						else if(annualCheckUpForm.getMdl_mcl().equalsIgnoreCase("Mpl"))
						{
							annualCheckUpVo.setC_mandal_code(annualCheckUpForm.getMunicipality());
						}
						annualCheckUpVo.setC_village_code(annualCheckUpForm.getVillage());	
					}
					//end sameaddress check
					annualCheckUpVo.setPatient_ipop(config.getString("PatientIPOP.RegisterStatus"));

					//phaseRenewal=commonService.getPhaseId(patientForm.getDistrict());
					phaseRenewal=0;
					annualCheckUpVo.setPhaseId(phaseRenewal+"");
					annualCheckUpVo.setRenewal(config.getString("PatientIPOP.Renewal"));
					//patientVO.setSchemeId(config.getString("PatientIPOP.SchemeId"));
					annualCheckUpVo.setSchemeId(annualCheckUpForm.getScheme());
					annualCheckUpVo.setCid(config.getString("PatientIPOP.Cid"));
					if(annualCheckUpForm.getCardType().equalsIgnoreCase(config.getString("PatientIPOP.Emp")))
						annualCheckUpVo.setEmployeeRenewal(config.getString("PatientIPOP.Renewal"));
					/*else if(annualCheckUpForm.getCardType().equalsIgnoreCase(config.getString("PatientIPOP.Pen")))
						annualCheckUpVo.setPensionerRenewal(config.getString("PatientIPOP.Renewal"));*/
					annualCheckUpVo.setRegHospId(annualCheckUpForm.getHospitalId());
					annualCheckUpVo.setRegHospDt(crtDate);
				/*	if(!annualCheckUpForm.getTelephonicId().equals(""))
					{
						annualCheckUpVo.setTelephonicId(annualCheckUpForm.getTelephonicId());
						annualCheckUpVo.setRegState("Telephonic Registration");
					}*/
					try{
						int rowsInserted=annualCheckUpService.registerPatient(annualCheckUpVo);
						if(rowsInserted==0)
						{
							GLOGGER.info("Patient cannot be registered");
							lStrPageName="failure";
						}
						else
						{
							annualCheckUpForm.setPatientNo(liNextVal);             
							if("true".equalsIgnoreCase(config.getString("SmsRequired")))
							{
								if(annualCheckUpVo.getContactNo()!=null && !annualCheckUpVo.getContactNo().equals(""))
								{
									smsNextVal = annualCheckUpService.getSequence("PATIENT_SMS_SNO");
									String lStrResultMsg=null;
									PatientSmsVO patientSmsVO=new PatientSmsVO();
									patientSmsVO.setSerialNo(Long.parseLong(smsNextVal));
									patientSmsVO.setPhoneNo(annualCheckUpVo.getContactNo());
									patientSmsVO.setSmsText("Dear "+annualCheckUpVo.getFirstName().trim()+" , You have been registered for Annual Health Check Up  in "+annualCheckUpService.getHospName(annualCheckUpForm.getHospitalId())+" under Employee Health Scheme ");
									patientSmsVO.setEmpCode(annualCheckUpVo.getEmpCode());
									patientSmsVO.setEmpName(annualCheckUpVo.getFirstName().trim());
									patientSmsVO.setCrtUsr(annualCheckUpVo.getCrtUsr());
									patientSmsVO.setCrtDt(crtDt);
									patientSmsVO.setSmsAction("Patient Registered for Annual Health CheckUp");
									patientSmsVO.setPatientId(annualCheckUpVo.getPatientId());
									lStrResultMsg=commonService.sendPatientSms(patientSmsVO);
								}
							}
							if (config.getBoolean("EmailRequired")) 
							{
								String mailId=null;
								if(annualCheckUpForm.geteMailId()!=null && !annualCheckUpForm.geteMailId().equals(""))
								{
									mailId=annualCheckUpForm.geteMailId();
									String[] emailToArray = {mailId};
									EmailVO emailVO = new EmailVO();
									emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
									emailVO.setTemplateName(config.getString("EHFPatientTemplateName"));
									emailVO.setFromEmail(config.getString("emailFrom"));
									emailVO.setToEmail(emailToArray);
									emailVO.setSubject(config.getString("patientEmailSubject"));
									Map<String, String> model = new HashMap<String, String>();
									model.put("patientName", annualCheckUpVo.getName().trim());
									model.put("registeredHosp", annualCheckUpService.getHospName(annualCheckUpForm.getHospitalId()));
									model.put("status", "Patient Registered for Annual Health CheckUp");
									model.put("statusDate",crtDate);
									commonService.generateMail(emailVO, model);
								}
							}
							lStrPageName="annualPatient";
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
						GLOGGER.error ( "Exception occured while registering patient in PatientAction." +e.getMessage()) ;
					}       				
				}
				if ("printPatientDetails".equalsIgnoreCase(lStrActionVal))
				{
					String ahcId=null;
					String lStrCaseId = request.getParameter("caseId");
					EhfAnnualPatientDtls ehfAnnualPatientDtls=null;
					ahcId=request.getParameter("patientId");
					annualCheckUpForm.setPatientNo(ahcId);
					// request.setAttribute("patientId",patientId);
					ehfAnnualPatientDtls=(EhfAnnualPatientDtls)annualCheckUpService.getPatientDetails(ahcId);
					//storing patcrtdt
					session.setAttribute("patCrtdt",ehfAnnualPatientDtls.getCrtDt());
					annualCheckUpForm.setCardNo(ehfAnnualPatientDtls.getCardNo());
					annualCheckUpForm.setName(ehfAnnualPatientDtls.getName());
					if(ehfAnnualPatientDtls.getCardIssueDt()!=null)
					{
						annualCheckUpForm.setCardIssueDt(sdf.format(ehfAnnualPatientDtls.getCardIssueDt()));
					}
					else
						annualCheckUpForm.setCardIssueDt("NA");
					if(ehfAnnualPatientDtls.getGender()!=null && "M".equalsIgnoreCase(ehfAnnualPatientDtls.getGender()))
					{
						annualCheckUpForm.setGender("Male");
					}
					else if(ehfAnnualPatientDtls.getGender()!=null && "F".equalsIgnoreCase(ehfAnnualPatientDtls.getGender()))
					{
						annualCheckUpForm.setGender("Female");
					}
					if(ehfAnnualPatientDtls.getEnrollDob()!=null)
					{
						annualCheckUpForm.setDateOfBirth(sdf.format(ehfAnnualPatientDtls.getEnrollDob()));
					}
					if(ehfAnnualPatientDtls.getAge()!=null)
					{
						annualCheckUpForm.setYears(ehfAnnualPatientDtls.getAge().toString());
					}
					if(ehfAnnualPatientDtls.getAgeMonths()!=null)
					{
						annualCheckUpForm.setMonths(ehfAnnualPatientDtls.getAgeMonths().toString());
					}
					if(ehfAnnualPatientDtls.getAgeDays()!=null)
					{
						annualCheckUpForm.setDays(ehfAnnualPatientDtls.getAgeDays().toString());
					}
					String relationId=ehfAnnualPatientDtls.getRelation();
					String relationName=annualCheckUpService.getRelationName(relationId);
					annualCheckUpForm.setRelation(relationName);
					String casteId=ehfAnnualPatientDtls.getCaste();
					if(casteId!=null && !casteId.equals(""))
					{
						String casteName=commonService.getCmbHdrname(lStrcastesHdrId, casteId);
						annualCheckUpForm.setCaste(casteName);
					}
					if(ehfAnnualPatientDtls.getContactNo()!=null && !ehfAnnualPatientDtls.getContactNo().equals(""))
					{
						annualCheckUpForm.setContactno(ehfAnnualPatientDtls.getContactNo().toString());
					}
					/*String occName = patientService.getOccupationName(ehfPatient.getOccupationCd());
		        	if(occName!=null && !occName.equalsIgnoreCase(""))*/
					annualCheckUpForm.setDesignation(ehfAnnualPatientDtls.getOccupationCd());
					annualCheckUpForm.setScheme(ehfAnnualPatientDtls.getSchemeId());
					/*else
		        	patientForm.setOccupation("NA");*/
					//Setting slab
					String slabType=null;
					String slab=null;
					if(ehfAnnualPatientDtls.getSlab()!=null)
					{
						slabType=ehfAnnualPatientDtls.getSlab();
					}
					if(config.getString("Slab.SemiPrivateWard").equalsIgnoreCase(slabType))
						slab=config.getString("Slab.Name.SemiPrivateWard");
					else if(config.getString("Slab.PrivateWard").equalsIgnoreCase(slabType))
						slab=config.getString("Slab.Name.PrivateWard");
					annualCheckUpForm.setSlab(slab);
					//End Of Slab
					annualCheckUpForm.setHno(ehfAnnualPatientDtls.getHouseNo());
					annualCheckUpForm.setStreet(ehfAnnualPatientDtls.getStreet());
					annualCheckUpForm.setState(annualCheckUpService.getLocationName(ehfAnnualPatientDtls.getState()));
					String distCode=ehfAnnualPatientDtls.getDistrictCode();
					String distName=annualCheckUpService.getLocationName(distCode);
					annualCheckUpForm.setDistrict(distName);
					String mandalCode=ehfAnnualPatientDtls.getMandalCode();
					String mandalName=annualCheckUpService.getLocationName(mandalCode);
					annualCheckUpForm.setMandal(mandalName);
					String villageCode=ehfAnnualPatientDtls.getVillageCode();
					String villageName=annualCheckUpService.getLocationName(villageCode);
					annualCheckUpForm.setVillage(villageName);
					if(ehfAnnualPatientDtls.getPinCode()!=null && !ehfAnnualPatientDtls.getPinCode().equalsIgnoreCase(""))
						annualCheckUpForm.setPin(ehfAnnualPatientDtls.getPinCode());
					else
						annualCheckUpForm.setPin("NA");
					//Setting Communication Address
					annualCheckUpForm.setComm_hno(ehfAnnualPatientDtls.getCHouseNo());
					annualCheckUpForm.setComm_street(ehfAnnualPatientDtls.getCStreet());
					annualCheckUpForm.setComm_state(annualCheckUpService.getLocationName(ehfAnnualPatientDtls.getCState()));
					annualCheckUpForm.setComm_dist(annualCheckUpService.getLocationName(ehfAnnualPatientDtls.getCDistrictCode()));
					annualCheckUpForm.setComm_mandal(annualCheckUpService.getLocationName(ehfAnnualPatientDtls.getCMandalCode()));
					annualCheckUpForm.setComm_village(annualCheckUpService.getLocationName(ehfAnnualPatientDtls.getCVillageCode()));
					if(ehfAnnualPatientDtls.getCPinCode()!=null && !ehfAnnualPatientDtls.getCPinCode().equalsIgnoreCase(""))
						annualCheckUpForm.setComm_pin(ehfAnnualPatientDtls.getCPinCode());
					else
						annualCheckUpForm.setComm_pin("NA");
					annualCheckUpForm.setDtTime(sdfw.format(ehfAnnualPatientDtls.getRegHospDate()));
					String photoUrl=annualCheckUpService.getPatientPhoto(ehfAnnualPatientDtls.getCardNo());
					if(photoUrl!=null)
					{
						try
						{
							File photo = new File(photoUrl);
							fis = new FileInputStream(photo);
							dis= new DataInputStream(fis);
							bytes = new byte[dis.available()];
							fis.read(bytes);
							String lStrContentType=null;
							lStrContentType="image/jpg";
							session.setAttribute("ContentType", lStrContentType);
							session.setAttribute("File", bytes);
							fis.close();
							dis.close();
							annualCheckUpForm.setPhotoUrl(photoUrl);
						}
						catch(Exception e)
						{
							GLOGGER.error ( "Exception occured when photo is not available in the path specified to display for Medco in AnnualCheckUpAction." +e.getMessage()) ;
						}
					}
					String hospCode=ehfAnnualPatientDtls.getRegHospId();
					String hospName=annualCheckUpService.getHospName(hospCode);
					annualCheckUpForm.setHospitalId(hospCode);
					request.setAttribute("hospitalId",hospCode);
					annualCheckUpForm.setHospitalName(hospName);

					/*if(ehfPatient.getIntimationId()!=null)
					{
						annualCheckUpForm.setTelephonicId(ehfPatient.getIntimationId());
						annualCheckUpForm.setTelephonicReg("Yes");
						PatientVO patientVo = new PatientVO();
						patientVo.setTelephonicId(ehfPatient.getIntimationId());
						PatientVO patientVO1 =  patientService.getTelephonicIntimationDtls(patientVo);

						patientForm.setTherapyType(patientVO1.getAsriCatCode());
						patientForm.setTherapyCategory(patientVO1.getICDcatName());
						patientForm.setTherapy(patientVO1.getTherapyCatId());
					}
					*/
					String pageType="";
					if(request.getParameter("pageType")!=null && !request.getParameter("pageType").equals(""))
					{
						pageType=request.getParameter("pageType");
						if(pageType.equalsIgnoreCase("print"))
							lStrPageName="annualPrintDetails";
					}	
				}

				if ("getPersonalHistory".equalsIgnoreCase(lStrActionVal))
				{
					String parntCode=null;
					List<String> personalHistoryList = new ArrayList<String>(); 
					if(request.getParameter("parntCode")!=null && !request.getParameter("parntCode").equals(""))
					{
						parntCode=request.getParameter("parntCode");
					}
					List<LabelBean> pHistoryList=null;
					pHistoryList=commonService.getPersonalHistory(parntCode);
					try
					{
						for (LabelBean labelBean: pHistoryList) {
							if (labelBean.getID() != null && 
									labelBean.getVALUE() != null)
							{
								personalHistoryList.add(labelBean.getID() + "~" + labelBean.getVALUE());
							}
						}
						if (personalHistoryList != null && personalHistoryList.size() > 0)

							request.setAttribute("AjaxMessage", personalHistoryList);
					}
					catch(Exception e)
					{
						GLOGGER.error ( "Exception occurred in Ajax getLocations actionFlag in PatientAction." +e.getMessage()) ;
					}
					lStrPageName="ajaxResult";
				}
				
				if("getAnnualPatientsView".equalsIgnoreCase(lStrActionVal)){
					
					List<AnnualCheckUpVo> annualList1=null;
					List<AnnualCheckUpVo> annualList=null;
					int  totalRecords=1;
					int pageNo=1;
				 int  showPage=1;;
					int rowsPerPage=10;
					int pages=1;
					int startIndex=1;
					int endIndex=5;
					int startPage=1;
					int endPage=10;
					
					
					if((request.getParameter("search")!=null && !"".equalsIgnoreCase(request.getParameter("search")))&& request.getParameter("search").equalsIgnoreCase("search")){
					 annualList1=annualCheckUpService.searchAnnualPatientDtls(request.getParameter("patID"), request.getParameter("PatName"), request.getParameter("healthCardNo"), request.getParameter("state"), request.getParameter("district"), request.getParameter("fromDate"), request.getParameter("toDate"),request.getParameter("userState"),hospId,roleId);
					 annualCheckUpForm.setAnnualList(annualList1);
						
					}else{
					 annualList=annualCheckUpService.getAnnualPatientDtls(hospId,roleId,userState);
						
					 annualCheckUpForm.setAnnualList(annualList);
						
					}
					
					if(annualCheckUpForm.getAnnualList()!=null && annualCheckUpForm.getAnnualList().size()>0){
						
						totalRecords=annualCheckUpForm.getAnnualList().size();
						
					}
		         if(request.getParameter("rowsPerPage")!=null && !"".equalsIgnoreCase(request.getParameter("rowsPerPage"))){
						
						rowsPerPage=Integer.parseInt(request.getParameter("rowsPerPage"));
					}
		         
		         if(rowsPerPage==0){
		        	 
		        	 rowsPerPage=10;
		         }
					if(totalRecords>rowsPerPage){
						
						
						if((totalRecords%rowsPerPage)==0){
							pages=totalRecords/rowsPerPage;
							
						}else{
							
							pages=(totalRecords/rowsPerPage)+1;
						}
						
					}else{
						
						pages=1;
					}
					
					if(request.getParameter("pageNo")==null ||"".equalsIgnoreCase(request.getParameter("pageNo"))){
						
						
						 if(pages>10 && showPage<=10){
								
								request.setAttribute("next", "next");
								annualCheckUpForm.setNext("next");
								
							}
				              if(pages>10 && (showPage-10)>1){
								
								request.setAttribute("prev", "prev");
								annualCheckUpForm.setPrev("prev");
								
				              }
						
						
						
					}
					
		      
					if((request.getParameter("pageNo")!=null && !"".equalsIgnoreCase(request.getParameter("pageNo").toString())&& !"prev".equalsIgnoreCase(request.getParameter("pageNo").toString()))&&!"next".equalsIgnoreCase(request.getParameter("pageNo").toString()) ){
						
							String str=request.getParameter("pageNo");				
						showPage=Integer.parseInt(str);
						
						startIndex=((showPage-1)*rowsPerPage)+1;
						
						if((showPage*rowsPerPage)<totalRecords){
							
							
							endIndex=showPage*rowsPerPage;
							
						}else{
							
							endIndex=totalRecords;
							
						}
						
						
			           int x=annualCheckUpForm.getStartPage();
						
						int y=annualCheckUpForm.getEndPage();
						
						if(y>pages){
						y=pages;
						}
						
						if(pages>10 && pages>y){
							
							
							request.setAttribute("next", "next");
							annualCheckUpForm.setNext("next");
							
						}
						
						if(pages>10 && x>10){
							request.setAttribute("prev", "prev");
							annualCheckUpForm.setPrev("prev");
						}
						
						
						startPage=x;
						endPage=y;
						
						
					}
					
					if(request.getParameter("pageNo")!=null && "prev".equalsIgnoreCase(request.getParameter("pageNo").toString())){
						
						int x=annualCheckUpForm.getStartPage();
						
						int y=annualCheckUpForm.getEndPage();
						if(x>=10 && pages>10){
							
							request.setAttribute("next", "next");
							annualCheckUpForm.setNext("next");
							
						}
						
						if(x-10>10 && pages>10){
							
							request.setAttribute("prev", "prev");
							annualCheckUpForm.setPrev("prev");
						}
						
						if(showPage==1){
							
							if(totalRecords>rowsPerPage){
								
								startIndex=1;
								endIndex=rowsPerPage;
							}else{
							
								startIndex=(showPage-1)*rowsPerPage+1;
								if((startIndex+rowsPerPage)<totalRecords){
									
									endIndex=startIndex+rowsPerPage;
								}else{
									
									endIndex=totalRecords;
								}
								
								
							}
						}
						startPage=x-10;
						endPage=x-1;
						
						
						annualCheckUpForm.setStartPage(startPage);
						annualCheckUpForm.setEndPage(endPage);
						
			startIndex=((showPage-1)*rowsPerPage)+1;
						
						if((showPage*rowsPerPage)<totalRecords){
							
							
							endIndex=showPage*rowsPerPage;
							
						}else{
							
							endIndex=totalRecords;
							
						}
					}
					
					
					if(request.getParameter("pageNo")!=null && "next".equalsIgnoreCase(request.getParameter("pageNo").toString())){
						
		                int x=annualCheckUpForm.getStartPage();
						
						int y=annualCheckUpForm.getEndPage();
					
						
						startPage=y+1;
						endPage=y+10;
						if(endPage>pages){
							
							endPage=pages;
						}
						
						if(pages>startPage+10){
							request.setAttribute("next", "next");
							annualCheckUpForm.setNext("next");
							
						}
						
						if(startPage-10>=1){
							
							request.setAttribute("prev", "prev");
							annualCheckUpForm.setPrev("prev");
						}
						annualCheckUpForm.setStartPage(startPage);
						annualCheckUpForm.setEndPage(endPage);
						showPage=startPage;
		              startIndex=((showPage-1)*rowsPerPage)+1;
						
						if((showPage*rowsPerPage)<totalRecords){
							
							
							endIndex=showPage*rowsPerPage;
							
						}else{
							
							endIndex=totalRecords;
							
						}
					
						
						
						
					}
					
					if(showPage==1){
						
						if(totalRecords>rowsPerPage){
							
							startIndex=1;
							endIndex=rowsPerPage;
						}else{
						
							startIndex=(showPage-1)*rowsPerPage+1;
							if((startIndex+rowsPerPage)<totalRecords){
								
								endIndex=startIndex+rowsPerPage;
							}else{
								
								endIndex=totalRecords;
							}
							
							
						}
					}
					startIndex=startIndex-1;
					List<AnnualCheckUpVo> annList3=new ArrayList<AnnualCheckUpVo>();
					if( (request.getParameter("search")!=null && !"".equalsIgnoreCase(request.getParameter("search"))) && request.getParameter("search").equalsIgnoreCase("search")){
						
						if(annualList1!=null && annualList1.size()>0){
						for(int i=startIndex;i<endIndex;i++){
							
							AnnualCheckUpVo vo=new AnnualCheckUpVo();
							vo.setPatientNo(annualList1.get(i).getPatientNo());
							vo.setName(annualList1.get(i).getName());
							vo.setEmployeeNo(annualList1.get(i).getEmployeeNo());
							vo.setAge(annualList1.get(i).getAge());
							vo.setDistrict(annualList1.get(i).getDistrict());
							vo.setGender(annualList1.get(i).getGender());
							vo.setRegistrationDate(annualList1.get(i).getRegistrationDate());
							annList3.add(vo);
							
						}
						}
						
					}else{
						
						if(annualList!=null && annualList.size()>0){
                            for(int j=startIndex;j<endIndex;j++){
							
							AnnualCheckUpVo vo=new AnnualCheckUpVo();
							vo.setPatientNo(annualList.get(j).getPatientNo());
							vo.setName(annualList.get(j).getName());
							vo.setEmployeeNo(annualList.get(j).getEmployeeNo());
							vo.setAge(annualList.get(j).getAge());
							vo.setDistrict(annualList.get(j).getDistrict());
							vo.setGender(annualList.get(j).getGender());
							vo.setRegistrationDate(annualList.get(j).getRegistrationDate());
							annList3.add(vo);
							
						}
						}
						
					}
							
					annualCheckUpForm.setAnnualList(annList3);
					request.setAttribute("liPageNo",showPage);
					if(annList3.size()==0){
						request.setAttribute("startIndex", 0);
					}else{
						
						request.setAttribute("startIndex", startIndex+1);
					}
					if(annList3.size()==0){
						request.setAttribute("endIndex", 0);
					}else{
						request.setAttribute("endIndex", endIndex);
						
					}
					
					request.setAttribute("rowsPerPage", rowsPerPage);
					request.setAttribute("pages", pages);
					request.setAttribute("startPage", startPage);
					request.setAttribute("endPage", endPage);
					request.setAttribute("endPage", endPage);
					if(annList3.size()==0){
						
						request.setAttribute("totalRecords", 0);
					}else{
						request.setAttribute("totalRecords", totalRecords);
					}
					
				
					
					lStrPageName="annualPatientsView";
					
					
				}
				if(lStrActionVal!=null && "saveAhcDetails".equalsIgnoreCase(lStrActionVal)){
					
					String saveFlg = request.getParameter("saveFlag");
					Date date = new Date();
					String crtDate=sdfw.format(date);
					AnnualCheckUpVo annualCheckupVo=new AnnualCheckUpVo();
					//String lDir=null,lFileName=null,lFileExt=null,lStrTotFileName=null;
					String userId=null,lStrCaseId=null,lStrPatientId=null;
					String checkType=null;
					userId=session.getAttribute("EmpID").toString(); 
					if(request.getParameter("patientId")!=null && !request.getParameter("patientId").equals("")){
						lStrPatientId=request.getParameter("patientId");
					}
					
					if(request.getParameter("checkType")!=null && !request.getParameter("checkType").equals("")){
						checkType=request.getParameter("checkType");
						if(checkType.equalsIgnoreCase("submit")){
							annualCheckupVo.setStatus("CD373");
						}
						else if(checkType.equalsIgnoreCase("Save")){
							annualCheckupVo.setStatus("CD372");
						}
					}		
				
					
					annualCheckupVo.setPersonalHis(annualCheckUpForm.getPersonalHistVal());
					annualCheckupVo.setExamFndsVal(annualCheckUpForm.getExamFndsVal());
					annualCheckupVo.setCrtDt(crtDate);
					annualCheckupVo.setAhcId(lStrPatientId);
					annualCheckupVo.setCrtUsr(userId);
					//annualCheckupVo.setDiagnosedBy(annualCheckUpForm.getDiagnosedBy());
					annualCheckupVo.setTestCheck(annualCheckUpForm.getTestCheck());
					if(annualCheckUpForm.getPresentHistory()!=null && annualCheckUpForm.getPresentHistory().length()>3000)
					{
						annualCheckupVo.setPresentHistory(annualCheckUpForm.getPresentHistory().substring(0, 3000));
					}
					else if(annualCheckUpForm.getPresentHistory()!=null)
					{
						annualCheckupVo.setPresentHistory(annualCheckUpForm.getPresentHistory());
					}
					else 
						annualCheckupVo.setPresentHistory("");
					annualCheckupVo.setPersonalHistory(annualCheckUpForm.getPersonalHistory());
					annualCheckupVo.setFamilyHistory(annualCheckUpForm.getFamilyHistory());
					annualCheckupVo.setPastHistory(annualCheckUpForm.getPastHistory());
					annualCheckupVo.setExaminationFnd(annualCheckUpForm.getExaminationFnd());
					annualCheckupVo.setFamilyHistoryOthr(annualCheckUpForm.getFamilyHistoryOthr());
					annualCheckupVo.setPastHistryOthr(annualCheckUpForm.getPastHistryOthr());
					annualCheckupVo.setPastHistryCancers(annualCheckUpForm.getPastHistryCancers()); 
					annualCheckupVo.setPastHistryEndDis(annualCheckUpForm.getPastHistryEndDis());
					
					if(annualCheckUpForm.getPastHistrySurg()!=null && annualCheckUpForm.getPastHistrySurg().length()>3000)
					{
						annualCheckupVo.setPastHistrySurg(annualCheckUpForm.getPastHistrySurg().substring(0, 3000));
					}
					else
						annualCheckupVo.setPastHistrySurg(annualCheckUpForm.getPastHistrySurg());
				
					String ahcInvestigationList=null;
					List<LabelBean> ahcInvList=new ArrayList<LabelBean>();
					if(request.getParameter("addTests")!=null && !request.getParameter("addTests").equals(""))
					{
						ahcInvestigationList=request.getParameter("addTests");
						
						StringTokenizer st=new StringTokenizer(ahcInvestigationList,"~");
						
						
						while(st.hasMoreTokens()){
							LabelBean labelBean=new LabelBean();
							String lL=st.nextToken();
							// GLOGGER.info("investigation :"+lL);
							 System.out.println("investigation :"+lL);
							String[] st1=new String[3];
							st1=lL.split("\\$");
							//StringTokenizer st1=new StringTokenizer(lL,"$");
						
								labelBean.setVALUE(st1[0]);
								labelBean.setID(st1[1]);   
									 if(st1[2]!=null)
								labelBean.setPrice(st1[2]); 
								 else
									 labelBean.setPrice(""); 
								
									 ahcInvList.add(labelBean)  ;
							
						
						}
					}
					String consultList	=null;
					List<LabelBean> ahcConsultList=new ArrayList<LabelBean>();
					if(request.getParameter("addConsultData")!=null && !request.getParameter("addConsultData").equals(""))
					{
						consultList=request.getParameter("addConsultData");
						StringTokenizer st=new StringTokenizer(consultList,"~");
						
						
						while(st.hasMoreTokens()){
							LabelBean labelBean=new LabelBean();
							String lL=st.nextToken();
							 //GLOGGER.info("consultation :"+lL);
							 System.out.println("consultation :"+lL);
							String[] st1=new String[3];
							st1=lL.split("\\$");
							//StringTokenizer st1=new StringTokenizer(lL,"$");
						
								labelBean.setVALUE(st1[0]);
								labelBean.setID(st1[1]);   
								labelBean.setPrice(st1[2]); 
									 ahcConsultList.add(labelBean)  ;
							
						
						}
						
					}
					
					FormFile lFormFile=null;String errmsg="";int i=0;
				/*	for(LabelBean labelBean: ahcInvList){
						//used to avoid deleted attachments-conflict
						while(annualCheckUpForm.getInvestAttach(i)==null )
						{
							i++;
						}
						if(annualCheckUpForm.getInvestAttach(i)!=null ){
							lFormFile=annualCheckUpForm.getInvestAttach(i);
							if (lFormFile.getFileSize() > 204800) 
							{
								errmsg=errmsg+"\\'"+labelBean.getVALUE()+"\\' \\n";
							}
						}
						i++;
					}*/
					FormFile lFormFile1=null;
					for(LabelBean labelBean: ahcConsultList){
						//used to avoid deleted attachments-conflict
						while(annualCheckUpForm.getConsultAttach(i)==null )
						{
							i++;
						}
						if(annualCheckUpForm.getConsultAttach(i)!=null ){
							lFormFile1=annualCheckUpForm.getConsultAttach(i);
							if (lFormFile1.getFileSize() > 204800) 
							{
								errmsg=errmsg+"\\'"+labelBean.getVALUE()+"\\' \\n";
							}
						}
						i++;
					}
					String consultRemLst=request.getParameter("consultDtlsRemove");
					String consultRemList[] =null;
					if(consultRemLst!=null && !"".equals(consultRemLst))
					{
						
						StringTokenizer st=new StringTokenizer(consultRemLst,"@");
						consultRemList=new String[st.countTokens()];
						int index=0;
						while(st.hasMoreTokens()){
							String lL=st.nextToken();
							 //GLOGGER.info("consultation :"+lL);
							 System.out.println("consultation :"+lL);
							 consultRemList[index]=lL;
							 index++;
						}
					}
					annualCheckupVo.setConsultRemLst(consultRemList);
					String investRemLst=request.getParameter("removeInvest");
					String investRemList[] =null;
					if(investRemLst!=null && !"".equals(investRemLst))
					{
						
						StringTokenizer st=new StringTokenizer(investRemLst,"@");
						investRemList=new String[st.countTokens()];
						int index=0;
						while(st.hasMoreTokens()){
							String lL=st.nextToken();
							 //GLOGGER.info("consultation :"+lL);
							 System.out.println("consultation :"+lL);
							 investRemList[index]=lL;
							 index++;
						}
					}
					annualCheckupVo.setInvestRemLst(investRemList);
					List<AnnualCheckUpVo> ahcTestLst= new ArrayList<AnnualCheckUpVo>();
					List<AnnualCheckUpVo> ahcConsultLst= new ArrayList<AnnualCheckUpVo>();
					EhfAnnualPatientDtls eapd=  annualCheckUpService.getPatientDtls(lStrPatientId);
					if(eapd!=null)
						{
							if(eapd.getRegHospId()!=null && !"".equalsIgnoreCase(eapd.getRegHospId())
									/*&& config.getString("HOSP_NIMS").equalsIgnoreCase(eapd.getRegHospId())*/)
								{
								//Added by Srikalyan for Saving investigation Data
								String investAttachLength=request.getParameter("investAttachLength");
								
								if("".equals(errmsg))
								{
									i=0; 
									//For General Investigations
									for(LabelBean labelBean: ahcInvList){
										//int attachLength=Integer.parseInt(investAttachLength);
										//annualCheckupVo.setInvestLength(attachLength);
										/*for(int i=0; i<attachLength;i++)
											{
										while(annualCheckUpForm.getInvestAttach(i)==null )
										{
											i++;
										}
												if(annualCheckUpForm.getInvestAttach(i)!=null)
													{
														if(annualCheckUpForm.getInvestAttach(i).getFileSize()<204800)
															{*/
																try
																	{
																	AnnualCheckUpVo test= new AnnualCheckUpVo();
																		/*StringBuffer filePath=new StringBuffer();
																		filePath.append(config.getString("mapPath"));
																		filePath.append(config.getString("ahcAttchpath"));
																		filePath.append(lStrPatientId);
																		String fileName=filePath.toString()+"/"+annualCheckUpForm.getInvestAttach(i).getFileName();
																		//Creating Folder
																		File folderPath =new File(filePath.toString()+"/");
																		if(!folderPath.exists())
																			folderPath.mkdirs();
																		
																		File newFile=new File(fileName);
																		if(newFile.exists())
																			newFile.delete();
																		
																		//Creating File															
																		newFile.createNewFile();
																		byte[] fileData=annualCheckUpForm.getInvestAttach(i).getFileData();
																		
																		FileOutputStream fos=new FileOutputStream(newFile);
																		fos.write(fileData);
																		fos.close();
																		test.setFilePath(fileName);*/
																		test.setICDcatName(labelBean.getID());
																		test.setICDsubCatName(labelBean.getVALUE());
																		test.setCatName(labelBean.getPrice());
																		ahcTestLst.add(test);
																		i++;
																	}
																catch(Exception e)
																	{
																		e.printStackTrace();
																		//GLOGGER.error("Exception Occured in Creating Investigation Attachments in AHC Patient Registration "+e.getMessage());
																	}
																
															/*}
														else
															{
																break;
															}
													}*/
											//}
									//}
								}
									
									i=0; 
									//For General Investigations
									for(LabelBean labelBean: ahcConsultList ){
										//int attachLength=Integer.parseInt(investAttachLength);
										//annualCheckupVo.setInvestLength(attachLength);
										/*for(int i=0; i<attachLength;i++)
											{*/
										while(annualCheckUpForm.getConsultAttach(i)==null )
										{
											i++;
										}
												if(annualCheckUpForm.getConsultAttach(i)!=null)
													{
														if(annualCheckUpForm.getConsultAttach(i).getFileSize()<204800)
															{
																try
																	{
																	AnnualCheckUpVo test= new AnnualCheckUpVo();
																		StringBuffer filePath=new StringBuffer();
																		filePath.append(config.getString("mapPath"));
																		filePath.append(config.getString("ahcAttchpath"));
																		filePath.append(lStrPatientId);
																		String fileName=filePath.toString()+"/"+annualCheckUpForm.getConsultAttach(i).getFileName();
																		//Creating Folder
																		File folderPath =new File(filePath.toString()+"/");
																		if(!folderPath.exists())
																			folderPath.mkdirs();
																		
																		File newFile=new File(fileName);
																		if(newFile.exists())
																			newFile.delete();
																		
																		//Creating File															
																		newFile.createNewFile();
																		byte[] fileData=annualCheckUpForm.getConsultAttach(i).getFileData();
																		
																		FileOutputStream fos=new FileOutputStream(newFile);
																		fos.write(fileData);
																		fos.close();
																		test.setFilePath(fileName);
																		test.setICDcatName(labelBean.getID());
																		test.setICDsubCatName(labelBean.getVALUE());
																		test.setCatName(labelBean.getPrice());
																		ahcConsultLst.add(test);
																		
																		i++;
																	}
																catch(Exception e)
																	{
																		e.printStackTrace();
																		//GLOGGER.error("Exception Occured in Creating Investigation Attachments in AHC Patient Registration "+e.getMessage());
																	}
																
															}
														else
															{
																break;
															}
													}
											//}
									//}
								}	
									
						}
								}
						}
					annualCheckupVo.setTestsList(ahcTestLst);
					annualCheckupVo.setConsultList(ahcConsultLst);
					//if(annualCheckUpForm.getInvestAttach())
					
					 errmsg="";
					String medcoFlag="N";
					if(roleId.equalsIgnoreCase(config.getString("Group.Medco")))
						medcoFlag="Y";
					annualCheckUpForm.setMedcoFlag(medcoFlag);
					
					if("".equals(errmsg))
					{
		                annualCheckupVo.setSaveFlag(saveFlg);
						lStrCaseId=annualCheckUpService.saveAhcDetails(annualCheckupVo);
						annualCheckUpForm.setCaseId(lStrCaseId);
						if(annualCheckupVo.getSaveFlag()!=null && annualCheckupVo.getSaveFlag().equalsIgnoreCase("Submit")){
							annualCheckUpForm.setMsg("AHC Case Registered Successfully with Case ID :"+lStrCaseId);
							request.setAttribute("save", "N");
							
							if(eapd!=null)
								{
									if(eapd.getRegHospId()!=null && !"".equalsIgnoreCase(eapd.getRegHospId())
											&& config.getString("HOSP_NIMS").equalsIgnoreCase(eapd.getRegHospId()))
										{
											lStrPageName="viewAhcTGNims";		
										}
									else if(eapd.getSchemeId()!=null && !"".equalsIgnoreCase(eapd.getSchemeId())
											&& config.getString("Scheme.TG.State").equalsIgnoreCase(eapd.getSchemeId())) 
										{
											lStrPageName="viewAhcTGNims";
										}
								}
							else 
								lStrPageName="viewAhcTGNims";
						}else{
							annualCheckUpForm.setMsg("AHC Case Saved Successfully with Case ID :"+lStrCaseId);
							request.setAttribute("save", "Y");
							lStrActionVal="viewAhcForm";
							if(checkType!=null)
							{
								annualCheckUpForm.setDisableFlag(checkType);
							}
						}
						if(lStrCaseId!=null && !lStrCaseId.equalsIgnoreCase(""))
						{
							if(lStrCaseId.equalsIgnoreCase("Already Registered"))
							{
								annualCheckUpForm.setErrMsg("Patient Already Registered");
								annualCheckUpForm.setCaseId("");
								lStrPageName="viewAhcTGNims";
							}
						}
						
					}
					
				}
				if(lStrActionVal!=null && "viewAhcForm".equalsIgnoreCase(lStrActionVal))
				{
					
					String medcoFlag="N";
					String nimsFlag="N";
					String fromPatientsView=request.getParameter("fromPatientsView");
					request.setAttribute("fromPatientsView",fromPatientsView);
					AnnualCheckUpVo annualCheckUpVo = new AnnualCheckUpVo();
					String patienId = request.getParameter("patientId");
					EhfAnnualPatientDtls eapd=  annualCheckUpService.getPatientDtls(patienId);
					if(eapd!=null){
						if(fromPatientsView !=null && !"".equalsIgnoreCase(fromPatientsView)
							&& "Y".equalsIgnoreCase(fromPatientsView))
							{
								if(eapd.getRegHospId()!=null && !"".equalsIgnoreCase(eapd.getRegHospId())
										&& config.getString("HOSP_NIMS").equalsIgnoreCase(eapd.getRegHospId()))
									nimsFlag="Y";
							}
						
						annualCheckUpForm.setDtTime(serverDt);
						//GLOGGER.info ( "Setting  Card Details to PatientForm in PatientAction." +"  Mandal Code is "+patientVO.getMandalCode()+"  and village Code is"+patientVO.getVillageCode()) ;
						annualCheckUpForm.setCardNo(eapd.getCardNo());
						annualCheckUpForm.setPatientNo(eapd.getAhcId());
						annualCheckUpForm.setName(eapd.getName());
						for(String loc:lStrgrpList)
							{
								if(loc!=null && loc.equalsIgnoreCase(config.getString("Group.Medco")))
									{
										medcoFlag="Y";
										/*String checkDaysCond = annualCheckUpService.checkRegDaysCond(eapd.getAhcId());
										if(checkDaysCond!=null && !"".equalsIgnoreCase(checkDaysCond))
											request.setAttribute("checkDaysCond", checkDaysCond);
										else
											request.setAttribute("checkDaysCond", "N");*/
								
									}
							}
						if(eapd.getGender()!=null && "M".equalsIgnoreCase(eapd.getGender()))
							annualCheckUpForm.setGender("Male");
						if(eapd.getGender()!=null && "F".equalsIgnoreCase(eapd.getGender()))
							annualCheckUpForm.setGender("Female");
						if(eapd.getEnrollDob()!=null)
						{
							annualCheckUpForm.setDateOfBirth(sdf.format(eapd.getEnrollDob()));
						}
						if(eapd.getAge()!=null)
						{
							annualCheckUpForm.setYears(eapd.getAge().toString());
						}
						if(eapd.getAgeMonths()!=null)
						{
							annualCheckUpForm.setMonths(eapd.getAgeMonths().toString());
						}
						if(eapd.getAgeDays()!=null)
						{
							annualCheckUpForm.setDays(eapd.getAgeDays().toString());
						}
						String relationId=eapd.getRelation();
						String relationName=patientService.getRelationName(relationId);
						annualCheckUpForm.setRelation(relationName);
						
						String slabType=null;
						String slab=null;
						if(eapd.getSlab()!=null)
						{
							slabType=eapd.getSlab();
						}
						if(config.getString("Slab.SemiPrivateWard").equalsIgnoreCase(slabType))
							slab=config.getString("Slab.Name.SemiPrivateWard");
						else if(config.getString("Slab.PrivateWard").equalsIgnoreCase(slabType))
							slab=config.getString("Slab.Name.PrivateWard");
						annualCheckUpForm.setSlab(slab);
						annualCheckUpForm.setDesignation(eapd.getOccupationCd());
						annualCheckUpForm.setContactNo(eapd.getContactNo().toString());
						annualCheckUpForm.setMedcoFlag(medcoFlag);
						//card details
						annualCheckUpForm.setHouseNo(eapd.getHouseNo());
						annualCheckUpForm.setStreet(eapd.getStreet());
						annualCheckUpForm.setState(patientService.getLocationName(eapd.getState()));
						String distCode=eapd.getDistrictCode();
						String distName=patientService.getLocationName(distCode);
						annualCheckUpForm.setDistrict(distName);
						String mandalCode=eapd.getMandalCode();
						String mandalName=patientService.getLocationName(mandalCode);
						annualCheckUpForm.setMandal(mandalName);
						String villageCode=eapd.getVillageCode();
						String villageName=patientService.getLocationName(villageCode);
						annualCheckUpForm.setVillage(villageName);
						if(eapd.getPinCode()!=null && !eapd.getPinCode().equalsIgnoreCase(""))
							annualCheckUpForm.setPin(eapd.getPinCode());
						else
							annualCheckUpForm.setPin("NA");
						
						
					
						annualCheckUpForm.setCommHouseNo(eapd.getCHouseNo());
						annualCheckUpForm.setCommStreet(eapd.getCStreet());
						annualCheckUpForm.setCommState(patientService.getLocationName(eapd.getCState()));
						annualCheckUpForm.setCommDistrict(patientService.getLocationName(eapd.getCDistrictCode()));
						annualCheckUpForm.setCommMandal(patientService.getLocationName(eapd.getCMandalCode()));
						annualCheckUpForm.setCommVillage(patientService.getLocationName(eapd.getCVillageCode()));
						if(eapd.getCPinCode()!=null && !eapd.getCPinCode().equalsIgnoreCase(""))
							annualCheckUpForm.setCommPin(eapd.getCPinCode());
						else
							annualCheckUpForm.setCommPin("NA");
						annualCheckUpForm.setDtTime(sdfw.format(eapd.getRegHospDate()));
						String photoUrl=patientService.getPatientPhoto(eapd.getCardNo());
						if(photoUrl!=null)
						{
							try
							{
								File photo = new File(photoUrl);
								fis = new FileInputStream(photo);
								dis= new DataInputStream(fis);
								bytes = new byte[dis.available()];
								fis.read(bytes);
								String lStrContentType=null;
								lStrContentType="image/jpg";
								session.setAttribute("ContentType", lStrContentType);
								session.setAttribute("File", bytes);
								fis.close();
								dis.close();
								annualCheckUpForm.setPhotoUrl(photoUrl);
							}
							catch(Exception e)
							{
								GLOGGER.error ( "Exception occured when photo is not available in the path specified to display for Ramco in PatientAction." +e.getMessage()) ;
							}
						}
						String hospCode=eapd.getRegHospId();
						String hospName=patientService.getHospName(hospCode);
						annualCheckUpForm.setHospitalId(hospCode);
						request.setAttribute("hospitalId",hospCode);
						annualCheckUpForm.setHospitalName(hospName);

						
					}
		
					//annualCheckUpVo=annualCheckUpService.getOtherTestDetails(patienId);
				
					AnnualCheckUpVo fullDtls = annualCheckUpService.getPatientFullDtls(patienId);			    
					
					 request.setAttribute("otherFindings",fullDtls);
					 annualCheckUpForm.setPresentHistory(fullDtls.getPresentIllness());
					 if(fullDtls.getDiagnosedBy()!=null)
					 annualCheckUpForm.setDiagnosedBy(fullDtls.getDiagnosedBy());
					 else
						 annualCheckUpForm.setDiagnosedBy(null);
						
					 if(fullDtls.getPersonalHis()!=null && !fullDtls.getPersonalHis().equalsIgnoreCase(""))
					    {
						String[]  persHist = fullDtls.getPersonalHis().split("~");
						annualCheckUpForm.setPersonalHist(persHist);
						}
					
					 if(fullDtls.getExamFindg()!=null && !fullDtls.getExamFindg().equalsIgnoreCase(""))
					    {
						String[] examfndList = fullDtls.getExamFindg().split("~");
						annualCheckUpForm.setExaminationFnd(examfndList);
						}
					 
					List<LabelBean> examinFndgsList=null;
					examinFndgsList=commonService.getGenExaminFndgs(eapd.getSchemeId());
					session.setAttribute("examinFndgsList", examinFndgsList);
					
					List<LabelBean> personalHistoryList=null;
					personalHistoryList=commonService.getPersonalHistory("PR");
					session.setAttribute("personalHistoryList", personalHistoryList);

					List<LabelBean> familyHistoryList=null;
					familyHistoryList=commonService.getFamilyHistory();
					session.setAttribute("familyHistoryList", familyHistoryList);
					
					List<LabelBean> pastHistoryList=null;
					pastHistoryList=commonService.getPastIllnessHitory();
					session.setAttribute("pastHistoryList", pastHistoryList);

					
					
					if(eapd.getSchemeId()!=null && (config.getString("TG")).equalsIgnoreCase(eapd.getSchemeId()))
						{
							System.out.println("scheme TG  : "+eapd.getSchemeId());
							/*if(nimsFlag!=null && !"".equalsIgnoreCase(nimsFlag) &&
									"Y".equalsIgnoreCase(nimsFlag))
								{*/
									System.out.println("nimsFlag  : "+nimsFlag);
									lStrPageName="viewAhcTGNims";
									/*
									 * Added by Srikalyan for TG Nims General Investigations
									 */
									List<LabelBean> investList=new ArrayList<LabelBean>();
									investList=annualCheckUpService.getGeneralInvest(patienId);
									if(investList!=null && investList.size()>0)
										request.setAttribute("investList", investList);
									String oldConsultLst="";
									List<EhfAhcPatientTest> attachList =new ArrayList<EhfAhcPatientTest>();
									attachList=annualCheckUpService.getAttachDtls(patienId);
									if(attachList!=null && attachList.size()>0)
										{
											List<LabelBean> attachLbList=new ArrayList<LabelBean>(); 
											for(EhfAhcPatientTest localBean:attachList)
												{
													LabelBean lbean=new LabelBean();
													if(localBean!=null && localBean.getTestId()!=null
															&& localBean.getPatientId()!=null
															&& !"".equalsIgnoreCase(localBean.getPatientId())
														)
														{
														//	int location=localBean.getAttachPath().lastIndexOf("/");
															lbean.setCALLID(localBean.getTestId());
															lbean.setATTACH(localBean.getTestName());
														//	lbean.setVALUE(localBean.getAttachPath());
														//	lbean.setID(localBean.getAttachPath().substring(location+1, localBean.getAttachPath().length()));
															lbean.setCaseId(localBean.getPatientId());
														}
													attachLbList.add(lbean);
												}
											request.setAttribute("attachList", attachLbList);
											System.out.println(attachLbList.size());
										}
									List<LabelBean> oldConsultList =new ArrayList<LabelBean>();
									List<LabelBean> oldConsultListFin =new ArrayList<LabelBean>();
									oldConsultList= annualCheckUpService.getConsultationDtls(patienId);
									for(LabelBean lb : oldConsultList){
										oldConsultLst=oldConsultLst+lb.getID()+"~"+lb.getCOUNT()+"~"+lb.getUNITTYPE()+"~"+lb.getUNITNAME()+"~"+lb.getREMARKS()+"@";
										
										int location=lb.getPATH().lastIndexOf("/");
										lb.setVALUE(lb.getPATH().substring(location+1, lb.getPATH().length()));
										oldConsultListFin.add(lb);
									}
									request.setAttribute("oldConsultList", oldConsultList);
									request.setAttribute("oldConsultLst", oldConsultLst);
								/*}
							else
								lStrPageName="viewAhcTGNims";*/
						}
					else
						{
							lStrPageName="viewAhcTGNims";
						}
						
					}
				
				else if("viewAhcClaimPage".equalsIgnoreCase(lStrActionVal)){
					
					String casesSearchFlag =request.getParameter("casesSearchFlag");
  					request.setAttribute("casesSearchFlag", casesSearchFlag);
					request.setAttribute("ahcId",request.getParameter("ahcId"));
					lStrPageName="ahcClaimsFramePage";
					
				}
				else if("openAttachmentInvest".equalsIgnoreCase(lStrActionVal)){
					String caseId=request.getParameter("caseId");
					String attachPath=request.getParameter("attachPath");
					
					if(caseId!=null &&!"".equalsIgnoreCase(caseId)
							&& attachPath!=null && !"".equalsIgnoreCase(attachPath))
						{
							try
								{
								  String lStrType = null;
							        ServletOutputStream out = response.getOutputStream();
						          /**
						           * 
						           */
						          String fileExt = attachPath.substring((attachPath.lastIndexOf(".")+1));
						          String lStrFileName=attachPath.substring((attachPath.lastIndexOf("/")+1));
						          String attachMode="attachment";
						          if(fileExt.equalsIgnoreCase(ASRIConstants.FILE_EXT_JPG) || fileExt.equalsIgnoreCase(ASRIConstants.FILE_EXT_JPEG) || fileExt.equalsIgnoreCase(ASRIConstants.FILE_EXT_GIF)){
						              attachMode="inline";
						          }
						          String lStrContentType="image/jpg";
								  File file = new File ( attachPath ) ; 
								  fis = new FileInputStream ( file ) ;
								  dis = new DataInputStream ( fis ) ;
					              byte[] lbBytes = new byte[ dis.available () ] ;
					              fis.read ( lbBytes ) ;
					              request.setAttribute ( "File", lbBytes ) ;
					              request.setAttribute ( "ContentType", lStrContentType ) ;
					              request.setAttribute ( "FileName", lStrFileName ) ;
					              request.setAttribute ( "Extn", lStrType ) ;
					              response.setContentType ( lStrContentType ) ;
					              response.setHeader("Content-Disposition",""+attachMode+"; filename="+lStrFileName);//006
					              out.write(lbBytes);
								}
							catch(Exception e)
								{
									e.printStackTrace();
									GLOGGER.error("Error occured while opening attachment in AHC investigations"+e.getMessage());
								}
							return mapping.findForward(null) ;
						}
					
				}
				else if("printAhcForm".equalsIgnoreCase(lStrActionVal)){
					
					AnnualCheckUpVo annualCheckUpVo = new AnnualCheckUpVo();
					String ahcId = request.getParameter("ahcId");
					EhfAnnualPatientDtls eapd=  annualCheckUpService.getPatientDtls(ahcId);
					EhfAnnualCaseDtls caseDtls= annualCheckUpService.getCaseDetails(ahcId);
					if(caseDtls!=null){
						if(caseDtls.getAhcStatus()!=null && !"".equalsIgnoreCase(caseDtls.getAhcStatus())){
							if(caseDtls.getAhcStatus().equalsIgnoreCase("CD371") || caseDtls.getAhcStatus().equalsIgnoreCase("CD372")){
								annualCheckUpForm.setStatus("AHC Case Registered");
							}
							else if(caseDtls.getAhcStatus().equalsIgnoreCase("CD373")){
								annualCheckUpForm.setStatus("AHC Screening Completed");
							}
							
						}
						
					}
					if(eapd!=null){
						annualCheckUpForm.setName(eapd.getName());
						annualCheckUpForm.setGender(eapd.getGender());
						annualCheckUpForm.setDtTime(serverDt);
						//GLOGGER.info ( "Setting  Card Details to PatientForm in PatientAction." +"  Mandal Code is "+patientVO.getMandalCode()+"  and village Code is"+patientVO.getVillageCode()) ;
						annualCheckUpForm.setCardNo(eapd.getCardNo());
						annualCheckUpForm.setPatientNo(eapd.getAhcId());
						
						if(eapd.getEnrollDob()!=null)
						{
							annualCheckUpForm.setDateOfBirth(sdf.format(eapd.getEnrollDob()));
						}
						if(eapd.getAge()!=null)
						{
							annualCheckUpForm.setYears(eapd.getAge().toString());
						}
						if(eapd.getAgeMonths()!=null)
						{
							annualCheckUpForm.setMonths(eapd.getAgeMonths().toString());
						}
						if(eapd.getAgeDays()!=null)
						{
							annualCheckUpForm.setDays(eapd.getAgeDays().toString());
						}
												
						annualCheckUpForm.setContactNo(eapd.getContactNo().toString());
						
						//card details
						annualCheckUpForm.setHouseNo(eapd.getHouseNo());
						annualCheckUpForm.setStreet(eapd.getStreet());
						annualCheckUpForm.setState(patientService.getLocationName(eapd.getState()));
						String distCode=eapd.getDistrictCode();
						String distName=patientService.getLocationName(distCode);
						annualCheckUpForm.setDistrict(distName);
						String mandalCode=eapd.getMandalCode();
						String mandalName=patientService.getLocationName(mandalCode);
						annualCheckUpForm.setMandal(mandalName);
						String villageCode=eapd.getVillageCode();
						String villageName=patientService.getLocationName(villageCode);
						annualCheckUpForm.setVillage(villageName);
						if(eapd.getPinCode()!=null && !eapd.getPinCode().equalsIgnoreCase(""))
							annualCheckUpForm.setPin(eapd.getPinCode());
						else
							annualCheckUpForm.setPin("NA");
						
						annualCheckUpForm.setCommHouseNo(eapd.getCHouseNo());
						annualCheckUpForm.setCommStreet(eapd.getCStreet());
						annualCheckUpForm.setCommState(patientService.getLocationName(eapd.getCState()));
						annualCheckUpForm.setCommDistrict(patientService.getLocationName(eapd.getCDistrictCode()));
						annualCheckUpForm.setCommMandal(patientService.getLocationName(eapd.getCMandalCode()));
						annualCheckUpForm.setCommVillage(patientService.getLocationName(eapd.getCVillageCode()));
						if(eapd.getCPinCode()!=null && !eapd.getCPinCode().equalsIgnoreCase(""))
							annualCheckUpForm.setCommPin(eapd.getCPinCode());
						else
							annualCheckUpForm.setCommPin("NA");
						annualCheckUpForm.setDtTime(sdfw.format(eapd.getRegHospDate()));
						String photoUrl=patientService.getPatientPhoto(eapd.getCardNo());
						if(photoUrl!=null)
						{
							try
							{
								File photo = new File(photoUrl);
								fis = new FileInputStream(photo);
								dis= new DataInputStream(fis);
								bytes = new byte[dis.available()];
								fis.read(bytes);
								String lStrContentType=null;
								lStrContentType="image/jpg";
								session.setAttribute("ContentType", lStrContentType);
								session.setAttribute("File", bytes);
								fis.close();
								dis.close();
								annualCheckUpForm.setPhotoUrl(photoUrl);
							}
							catch(Exception e)
							{
								GLOGGER.error ( "Exception occured when photo is not available in the path specified to display for Ramco in PatientAction." +e.getMessage()) ;
							}
						}
						String hospCode=eapd.getRegHospId();
						String hospName=patientService.getHospName(hospCode);
						annualCheckUpForm.setHospitalId(hospCode);
						request.setAttribute("hospitalId",hospCode);
						annualCheckUpForm.setHospitalName(hospName);
						//annualCheckUpVo=annualCheckUpService.getOtherTestDetails(ahcId);
				
						
						AnnualCheckUpVo findings= null;
						findings=annualCheckUpService.getFindings(ahcId);
						 request.setAttribute("otherFindings",findings);
						/*List<LabelBean> examinFndgsList=null;
						examinFndgsList=commonService.getGenExaminFndgs(eapd.getSchemeId());
						session.setAttribute("examinFndgsList", examinFndgsList);
						
						List<LabelBean> personalHistoryList=null;
						personalHistoryList=commonService.getPersonalHistory("PR");
						session.setAttribute("personalHistoryList", personalHistoryList);

						List<LabelBean> familyHistoryList=null;
						familyHistoryList=commonService.getFamilyHistory();
						session.setAttribute("familyHistoryList", familyHistoryList);
						
						List<LabelBean> pastHistoryList=null;
						pastHistoryList=commonService.getPastIllnessHitory();
						session.setAttribute("pastHistoryList", pastHistoryList);*/
						
						
					}
					lStrPageName="ahcPrintPage";
				}

				if(lStrActionVal!=null && "AHCClaimCases".equalsIgnoreCase(lStrActionVal))
				{
					String casesSearchFlag =request.getParameter("casesSearchFlag");
					request.setAttribute("casesSearchFlag", casesSearchFlag);
					request.setAttribute("statusLst",annualCheckUpService.getAhcStatus());
					List<AnnualCheckUpVo> annualList1=null;
					List<AnnualCheckUpVo> annualList=null;
					int  totalRecords=1;
					int pageNo=1;
					int  showPage=1;;
					int rowsPerPage=10;
					int pages=1;
					int startIndex=1;
					int endIndex=5;
					int startPage=1;
					int endPage=10;
					//annualCheckUpForm
					if((request.getParameter("search")!=null && !"".equalsIgnoreCase(request.getParameter("search")))&& request.getParameter("search").equalsIgnoreCase("search")){
						annualList1=annualCheckUpService.searchAnnualClaimDtls(request.getParameter("patID"), request.getParameter("PatName"), request.getParameter("healthCardNo"), request.getParameter("state"), request.getParameter("district"), request.getParameter("fromDate"), request.getParameter("toDate"), hospId, roleId,request.getParameter("userState"),casesSearchFlag,annualCheckUpForm.getStatus());
						
						
						 annualCheckUpForm.setAnnualList(annualList1);	
						
						
						
					}else{
						annualList=annualCheckUpService.getAHCClaimCases(hospId,roleId,userState,casesSearchFlag,annualCheckUpForm.getStatus());
						
						annualCheckUpForm.setAnnualList(annualList);
						
					}

					if(request.getParameter("rowsPerPage")!=null && !"".equalsIgnoreCase(request.getParameter("rowsPerPage")))
						rowsPerPage=Integer.parseInt(request.getParameter("rowsPerPage"));
						
						if(annualCheckUpForm.getAnnualList().size()>0){
						totalRecords=annualCheckUpForm.getAnnualList().size();
						}
						
						if(request.getParameter("rowsPerPage")!=null && !"".equalsIgnoreCase(request.getParameter("rowsPerPage"))){
							
							rowsPerPage=Integer.parseInt(request.getParameter("rowsPerPage"));
						}
			         
			         if(rowsPerPage==0){
			        	 
			        	 rowsPerPage=10;
			         }
						if(totalRecords>rowsPerPage){
							
							
							if((totalRecords%rowsPerPage)==0){
								pages=totalRecords/rowsPerPage;
								
							}else{
								
								pages=(totalRecords/rowsPerPage)+1;
							}
							
						}else{
							
							pages=1;
						}
						
						if(request.getParameter("pageNo")==null ||"".equalsIgnoreCase(request.getParameter("pageNo"))){
							
							
							 if(pages>10 && showPage<=10){
									
									request.setAttribute("next", "next");
									annualCheckUpForm.setNext("next");
									
								}
					              if(pages>10 && (showPage-10)>1){
									
									request.setAttribute("prev", "prev");
									annualCheckUpForm.setPrev("prev");
									
					              }
							
							
							
						}
						
			      
						if((request.getParameter("pageNo")!=null && !"".equalsIgnoreCase(request.getParameter("pageNo").toString())&& !"prev".equalsIgnoreCase(request.getParameter("pageNo").toString()))&&!"next".equalsIgnoreCase(request.getParameter("pageNo").toString()) ){
							
								String str=request.getParameter("pageNo");				
							showPage=Integer.parseInt(str);
							
							startIndex=((showPage-1)*rowsPerPage)+1;
							
							if((showPage*rowsPerPage)<totalRecords){
								
								
								endIndex=showPage*rowsPerPage;
								
							}else{
								
								endIndex=totalRecords;
								
							}
							
							
				           int x=annualCheckUpForm.getStartPage();
							
							int y=annualCheckUpForm.getEndPage();
							
							startPage=x;
							endPage=y;
							
							if(y>pages){
							y=pages;
							}
							
							if(pages>10 && pages>y){
								
								
								request.setAttribute("next", "next");
								annualCheckUpForm.setNext("next");
								
							}
							
							if(pages>10 && x>10){
								request.setAttribute("prev", "prev");
								annualCheckUpForm.setPrev("prev");
							}
							
							
							
							
							
						}
						
						if(request.getParameter("pageNo")!=null && "prev".equalsIgnoreCase(request.getParameter("pageNo").toString())){
							
							int x=annualCheckUpForm.getStartPage();
							
							int y=annualCheckUpForm.getEndPage();
							if(x>=10 && pages>10){
								
								request.setAttribute("next", "next");
								annualCheckUpForm.setNext("next");
								
							}
							
							if(x-10>10 && pages>10){
								
								request.setAttribute("prev", "prev");
								annualCheckUpForm.setPrev("prev");
							}
							
							if(showPage==1){
								
								if(totalRecords>rowsPerPage){
									
									startIndex=1;
									endIndex=rowsPerPage;
								}else{
								
									startIndex=(showPage-1)*rowsPerPage+1;
									if((startIndex+rowsPerPage)<totalRecords){
										
										endIndex=startIndex+rowsPerPage;
									}else{
										
										endIndex=totalRecords;
									}
									
									
								}
							}
							startPage=x-10;
							endPage=x-1;
							
							
							annualCheckUpForm.setStartPage(startPage);
							annualCheckUpForm.setEndPage(endPage);
							
				startIndex=((showPage-1)*rowsPerPage)+1;
							
							if((showPage*rowsPerPage)<totalRecords){
								
								
								endIndex=showPage*rowsPerPage;
								
							}else{
								
								endIndex=totalRecords;
								
							}
						}
						
						
						if(request.getParameter("pageNo")!=null && "next".equalsIgnoreCase(request.getParameter("pageNo").toString())){
							
			                int x=annualCheckUpForm.getStartPage();
							
							int y=annualCheckUpForm.getEndPage();
						
							
							startPage=y+1;
							endPage=y+10;
							if(endPage>pages){
								
								endPage=pages;
							}
							
							if(pages>startPage+10){
								request.setAttribute("next", "next");
								annualCheckUpForm.setNext("next");
								
							}
							
							if(startPage-10>=1){
								
								request.setAttribute("prev", "prev");
								annualCheckUpForm.setPrev("prev");
							}
							annualCheckUpForm.setStartPage(startPage);
							annualCheckUpForm.setEndPage(endPage);
							showPage=startPage;
			              startIndex=((showPage-1)*rowsPerPage)+1;
							
							if((showPage*rowsPerPage)<totalRecords){
								
								
								endIndex=showPage*rowsPerPage;
								
							}else{
								
								endIndex=totalRecords;
								
							}
						
							
							
							
						}
						
						if(showPage==1){
							
							if(totalRecords>rowsPerPage){
								
								startIndex=1;
								endIndex=rowsPerPage;
							}else{
							
								startIndex=(showPage-1)*rowsPerPage+1;
								if((startIndex+rowsPerPage)<totalRecords){
									
									endIndex=startIndex+rowsPerPage;
								}else{
									
									endIndex=totalRecords;
								}
								
								
							}
						}
						startIndex=startIndex-1;
						List<AnnualCheckUpVo> annList3=new ArrayList<AnnualCheckUpVo>();
						if( (request.getParameter("search")!=null && !"".equalsIgnoreCase(request.getParameter("search"))) && request.getParameter("search").equalsIgnoreCase("search")){
							if(annualList1!=null && annualList1.size()>0){
							for(int i=startIndex;i<endIndex;i++){
								
								AnnualCheckUpVo vo=new AnnualCheckUpVo();
								vo.setPatientNo(annualList1.get(i).getPatientNo());
								vo.setName(annualList1.get(i).getName());
								vo.setEmployeeNo(annualList1.get(i).getEmployeeNo());
								vo.setAge(annualList1.get(i).getAge());
								vo.setDistrict(annualList1.get(i).getDistrict());
								vo.setGender(annualList1.get(i).getGender());
								vo.setRegistrationDate(annualList1.get(i).getRegistrationDate());
								if(annualList1.get(i).getCaseProcCodes()!=null)
								vo.setCaseProcCodes(annualList1.get(i).getCaseProcCodes());
								annList3.add(vo);
								
							}
							
							}
						}else{
							
							if(annualList!=null && annualList.size()>0){
	                            for(int j=startIndex;j<endIndex;j++){
								
								AnnualCheckUpVo vo=new AnnualCheckUpVo();
								vo.setPatientNo(annualList.get(j).getPatientNo());
								vo.setName(annualList.get(j).getName());
								vo.setEmployeeNo(annualList.get(j).getEmployeeNo());
								vo.setAge(annualList.get(j).getAge());
								vo.setDistrict(annualList.get(j).getDistrict());
								vo.setGender(annualList.get(j).getGender());
								vo.setRegistrationDate(annualList.get(j).getRegistrationDate());
								if(annualList.get(j).getCaseProcCodes()!=null)
									vo.setCaseProcCodes(annualList.get(j).getCaseProcCodes());
								annList3.add(vo);
								
							}
							}
							
						}
								
						annualCheckUpForm.setAnnualList(annList3);
						request.setAttribute("liPageNo",showPage);
						if(annList3.size()==0){
							request.setAttribute("startIndex", 0);
						}else{
							
							request.setAttribute("startIndex", startIndex+1);
						}
						if(annList3.size()==0){
							request.setAttribute("endIndex", 0);
						}else{
							request.setAttribute("endIndex", endIndex);
							
						}
						
						request.setAttribute("rowsPerPage", rowsPerPage);
						request.setAttribute("pages", pages);
						request.setAttribute("startPage", startPage);
						request.setAttribute("endPage", endPage);
						request.setAttribute("endPage", endPage);
						if(annList3.size()==0){
							
							request.setAttribute("totalRecords", 0);
						}else{
							request.setAttribute("totalRecords", totalRecords);
						}
						
					
					lStrPageName="ahcClaimCases";
					
				}
				
				
				
				if("annualPatientsCSV".equalsIgnoreCase(lStrActionVal)){
					
					List<AnnualCheckUpVo> annualList1=null;
					List<AnnualCheckUpVo> annualList=null;
					
					 String lStrDirPath=config.getString("mapPath");
					 System.out.println(lStrDirPath);
			         String lStrFileName=config.getString("AnnualPatientTempFile")+config.getString("DOT_VALUE")+config.getString("OPR.CsvExtn");
			         System.out.println(lStrFileName);
			         String lStrFileName1=lStrDirPath+"/"+lStrFileName; 
			         System.out.println(lStrFileName1);
					     //Creates file in EHFTemp folder of client machine 
				         File lcsvfile = createFile(lStrDirPath,lStrFileName1);	         
				         char separator = '^';
				         StringBuilder line = new StringBuilder();   
					
				         line.append("PatientNo");
		                  line.append(separator);
		                  line.append("Employee No");
		                  line.append(separator);
		                  line.append("Patient Name");
		                  line.append(separator);
		                  line.append("District");
		                  line.append(separator);
		                  line.append("Gender");
		                  line.append(separator);
		                  line.append("Age");
		                  line.append(separator);
		                  line.append("Registration Date");
		                  line.append("\n");
		                  line.append("\n");
					

							if((request.getParameter("search")!=null && !"".equalsIgnoreCase(request.getParameter("search")))&& request.getParameter("search").equalsIgnoreCase("search")){
							 annualList1=annualCheckUpService.searchAnnualPatientDtls(request.getParameter("patID"), request.getParameter("PatName"), request.getParameter("healthCardNo"), request.getParameter("state"), request.getParameter("district"), request.getParameter("fromDate"), request.getParameter("toDate"),request.getParameter("userState"),hospId,roleId);
							 annualCheckUpForm.setAnnualCSVList(annualList1);
								
							}else{
							 annualList=annualCheckUpService.getAnnualPatientDtls(hospId,roleId,userState);
								
							 annualCheckUpForm.setAnnualCSVList(annualList);
								
							}
					
					
					if(annualCheckUpForm.getAnnualCSVList()!=null && annualCheckUpForm.getAnnualCSVList().size()>0){
						
						
						for(int i=0;i<annualCheckUpForm.getAnnualCSVList().size();i++){
							  
							
							   
							  line.append(annualCheckUpForm.getAnnualCSVList().get(i).getPatientNo());
		                      line.append(separator);
		                      line.append(annualCheckUpForm.getAnnualCSVList().get(i).getEmployeeNo());
		                      line.append(separator);
		                      line.append(annualCheckUpForm.getAnnualCSVList().get(i).getName());
		                      line.append(separator);
		                      line.append(annualCheckUpForm.getAnnualCSVList().get(i).getDistrict());
		                      line.append(separator);
		                      line.append(annualCheckUpForm.getAnnualCSVList().get(i).getGender());
		                      line.append(separator);
		                      line.append(annualCheckUpForm.getAnnualCSVList().get(i).getAge());
		                      line.append(separator);
		                      line.append(annualCheckUpForm.getAnnualCSVList().get(i).getRegistrationDate());
		                      line.append("\n");
							
							
							
							
						}
						
						request.setAttribute("File", line.toString().getBytes());    
					    response.setContentType(config.getString("REPORT.CaseSearchCvsContentType"));
					    response.setHeader("Content-Disposition","Attachment; filename="+lStrFileName);
					    response.setCharacterEncoding("UTF-8");
					    return mapping.findForward("openFile");
						
						
					}else{
						request.setAttribute("NODATA", 1); 
						lStrPageName="annualPatientsView";
						
						
					}
					
					
					
					
					
					
					
					
					
				}
				
                  if("AHCClaimCasesCSV".equalsIgnoreCase(lStrActionVal)){
                	  
                	String casesSearchFlag =request.getParameter("casesSearchFlag");
  					request.setAttribute("casesSearchFlag", casesSearchFlag);
					
					List<AnnualCheckUpVo> annualList1=null;
					List<AnnualCheckUpVo> annualList=null;
					if((request.getParameter("search")!=null && !"".equalsIgnoreCase(request.getParameter("search")))&& request.getParameter("search").equalsIgnoreCase("search")){
						annualList1=annualCheckUpService.searchAnnualClaimDtls(request.getParameter("patID"), request.getParameter("PatName"), request.getParameter("healthCardNo"), request.getParameter("state"), request.getParameter("district"), request.getParameter("fromDate"), request.getParameter("toDate"), hospId, roleId,request.getParameter("userState"),casesSearchFlag,annualCheckUpForm.getStatus());
						
						
						 annualCheckUpForm.setAnnualCSVList(annualList1);	
						
						
						
					}else{
						annualList=annualCheckUpService.getAHCClaimCases(hospId,roleId,userState,casesSearchFlag,annualCheckUpForm.getStatus());
						
						annualCheckUpForm.setAnnualCSVList(annualList);
						
					}
					 String lStrDirPath=config.getString("mapPath");
					 System.out.println(lStrDirPath);
			         String lStrFileName=config.getString("AnnualClaimCasesTempFile")+config.getString("DOT_VALUE")+config.getString("OPR.CsvExtn");
			         System.out.println(lStrFileName);
			         String lStrFileName1=lStrDirPath+"/"+lStrFileName; 
			         System.out.println(lStrFileName1);
					     //Creates file in EHFTemp folder of client machine 
				         File lcsvfile = createFile(lStrDirPath,lStrFileName1);	         
				         char separator = '^';
				         StringBuilder line = new StringBuilder();   
					
				         line.append("PatientNo");
		                  line.append(separator);
		                  line.append("Employee No");
		                  line.append(separator);
		                  line.append("Patient Name");
		                  line.append(separator);
		                  line.append("District");
		                  line.append(separator);
		                  line.append("Gender");
		                  line.append(separator);
		                  line.append("Age");
		                  line.append(separator);
		                  line.append("Registration Date");
		                  line.append("\n");
		                  line.append("\n");
					

							
					
					
					if(annualCheckUpForm.getAnnualCSVList()!=null && annualCheckUpForm.getAnnualCSVList().size()>0){
						
						
						for(int i=0;i<annualCheckUpForm.getAnnualCSVList().size();i++){
							  
							
							   
							  line.append(annualCheckUpForm.getAnnualCSVList().get(i).getPatientNo());
		                      line.append(separator);
		                      line.append(annualCheckUpForm.getAnnualCSVList().get(i).getEmployeeNo());
		                      line.append(separator);
		                      line.append(annualCheckUpForm.getAnnualCSVList().get(i).getName());
		                      line.append(separator);
		                      line.append(annualCheckUpForm.getAnnualCSVList().get(i).getDistrict());
		                      line.append(separator);
		                      line.append(annualCheckUpForm.getAnnualCSVList().get(i).getGender());
		                      line.append(separator);
		                      line.append(annualCheckUpForm.getAnnualCSVList().get(i).getAge());
		                      line.append(separator);
		                      line.append(annualCheckUpForm.getAnnualCSVList().get(i).getRegistrationDate());
		                      line.append("\n");
							
							
							
							
						}
						
						request.setAttribute("File", line.toString().getBytes());    
					    response.setContentType(config.getString("REPORT.CaseSearchCvsContentType"));
					    response.setHeader("Content-Disposition","Attachment; filename="+lStrFileName);
					    response.setCharacterEncoding("UTF-8");
					    return mapping.findForward("openFile");
						
						
					}else{
						request.setAttribute("NODATA", 1); 
						lStrPageName="annualPatientsView";
						
						
					}
					
				
                  }
				
				
				
					
				
						
				return mapping.findForward(lStrPageName);
			}

			
	private File createFile(String lStrDirPath,String lStrFileName) throws IOException 
	{
		//Making Directory		  
		File file = new File(lStrDirPath);
		if (!file.exists()) 
		{
			file.mkdir();
		}
		File lfile = new File(lStrFileName);
		if(!lfile.exists())
		{
			lfile.createNewFile();
		}
		else
		{
			lfile.delete();
			lfile.createNewFile();
		}
		return lfile;
		
	}

	
}
