package com.ahct.flagging.action;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ahct.flagging.vo.FlaggingVO;
import com.ahct.attachments.constants.ASRIConstants;
import com.ahct.common.service.CommonService;
import com.ahct.common.util.ServiceFinder;
import com.ahct.common.vo.LabelBean;
import com.ahct.flagging.service.FlaggingService;
import com.ahct.flagging.form.FlaggingForm;
import com.tcs.framework.configuration.ConfigurationService;

import org.apache.struts.upload.FormFile;

public class FlaggingAction extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response) throws Exception 
			{
				String lStrActionVal="";
				String lStrPageName="";
				String caseId;
				String flagId;
				String lStrEmpId=null;
				String lStrUserGroup = null;
				String loggedUsrGrp=null;
				String roleId = null;
				String lStrContentType=null;
				String fileDocArr[]=new String[5];
				FlaggingForm flaggingForm=(FlaggingForm)form;
				List<LabelBean> rolesList = null;
				FlaggingService flaggingService=(FlaggingService) ServiceFinder.getContext(request).getBean("flaggingService");
				ConfigurationService configurationService = (ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
	            Configuration config = configurationService.getConfiguration();
	            CommonService commonService =(CommonService)ServiceFinder.getContext(request).getBean("commonService");  
				List<LabelBean> flagList=new ArrayList<LabelBean>();
				List<LabelBean> grpList = null;
				List<String> lStrgrpList = new ArrayList<String>();
				
				HttpSession session = request.getSession ( false ) ;
				if ( ( session.getAttribute ( "EmpID" ) != null ) && !( session.getAttribute ( "EmpID" ).equals ( "" ) ) )
				    lStrEmpId = ( String ) session.getAttribute ( "EmpID" ) ;
				if ( ( session.getAttribute ( "groupList" ) != null ) && !( session.getAttribute ( "groupList" ).equals ( "" ) ) )
			    	rolesList = (List<LabelBean>) session.getAttribute ( "groupList" ) ;
				 for(LabelBean labelBean:rolesList)
		     	    {
		     	   if(labelBean.getID() != null && (labelBean.getID().equalsIgnoreCase(config.getString("preauth_medco_role")) || labelBean.getID().equalsIgnoreCase(config.getString("preauth_mithra_role")) ))
		     	     {
		     		    request.setAttribute("is_medco_mithra", "Y");
		     			lStrUserGroup = labelBean.getID() ;
		     			break;
		     	     }
		     	     else{
		     		lStrUserGroup = null;
		     		roleId = labelBean.getID();
		     	     }
		     	     }
				 try
		           {
					if (session.getAttribute("groupList").toString() != null)
					    {
						grpList = (List<LabelBean>) session.getAttribute("groupList");
				        }
			       }
				catch(NullPointerException e)
				{
					e.printStackTrace();
				}
				for (LabelBean labelBean : grpList)
				{
					lStrgrpList.add(labelBean.getID());
				}
				if (lStrgrpList.contains(config.getString("Medco_Grp")))
				{
					roleId = config.getString("Medco_Grp");
				} 
				else if (lStrgrpList.contains(config.getString("Mithra_Grp")))
				{
					roleId = config.getString("Mithra_Grp");
				}
				else if (lStrgrpList.contains(config.getString("DC_Grp")))
				{
					roleId = config.getString("DC_Grp");
				}
				else if (lStrgrpList.contains(config.getString("DM_Grp")))
				{
					roleId = config.getString("DM_Grp");
				}
				else if (lStrgrpList.contains(config.getString("NTL_Grp")))
				{
					roleId = config.getString("NTL_Grp");
				}
				else if (lStrgrpList.contains(config.getString("GMOP_Grp")))
				{
					roleId = config.getString("GMOP_Grp");
				}
				else if (lStrgrpList.contains(config.getString("JEO_Empanel_Grp")))
				{
					roleId = config.getString("JEO_Empanel_Grp");
				}
				
				if(request.getParameter("actionFlag")!=null && !request.getParameter("actionFlag").equalsIgnoreCase(""))
					{
						lStrActionVal=request.getParameter("actionFlag");
					}
				if("getFlagPage".equals(lStrActionVal))
					{
						caseId=request.getParameter("caseId");
						String caseIdS=request.getParameter("caseIdS");
						if(caseIdS!=null)
							request.setAttribute("back", "true");
						FlaggingVO schemeVO=null;
						
						if(caseId!=null)
							schemeVO= flaggingService.getCaseDtls(caseId);
						else if (caseIdS!=null)	
							schemeVO= flaggingService.getCaseDtls(caseIdS);
						if(schemeVO!=null)
							{
								if(schemeVO.getSchemeId()!=null && !"".equalsIgnoreCase(schemeVO.getSchemeId()))
									request.setAttribute("flaggingSchemeId",schemeVO.getSchemeId());		
							}
						
						flagId="";
						FlaggingVO fVO=new FlaggingVO();
						fVO=flaggingService.checkAuthority(rolesList);
						for(LabelBean lb:rolesList)
							{
								FlaggingVO authVO = new FlaggingVO();
								if(lb.getID()!=null && !"".equalsIgnoreCase(lb.getID()))
									{
										if(lb.getID().equalsIgnoreCase(config.getString("DC_Grp"))||
												lb.getID().equalsIgnoreCase(config.getString("DM_Grp"))||
												lb.getID().equalsIgnoreCase(config.getString("NTL_Grp")))
											{
												FlaggingVO flaggingVOAu =new FlaggingVO();
												if(lb.getID().equalsIgnoreCase(config.getString("DC_Grp")))
													{
														flaggingVOAu.setDcId(lStrEmpId);
														authVO=flaggingService.checkDCTLDMAuthority(flaggingVOAu);
													}
												else if(lb.getID().equalsIgnoreCase(config.getString("DM_Grp")))
													{
														flaggingVOAu.setDmId(lStrEmpId);
														authVO=flaggingService.checkDCTLDMAuthority(flaggingVOAu);
													}
												else if(lb.getID().equalsIgnoreCase(config.getString("NTL_Grp")))
													{
														flaggingVOAu.setTlId(lStrEmpId);
														authVO=flaggingService.checkDCTLDMAuthority(flaggingVOAu);
													}
												if(authVO!=null)
													{
														int count=0;
														if(authVO.getFlaggingVOLst()!=null)
															{
																if(authVO.getFlaggingVOLst().size()>0)
																	{
																		//FlaggingVO hospVO=flaggingService.getCaseDtls(caseId);
																		if(schemeVO!=null)
																			{	
																				if(schemeVO.getHospId()!=null)
																					{
																						for(FlaggingVO flagVO :authVO.getFlaggingVOLst())
																							{
																								if(flagVO.getHospId()!=null)
																									{
																										if(flagVO.getHospId().equalsIgnoreCase(schemeVO.getHospId()))
																											break;
																										else
																											count++;
																									}
																							}		
																					}
																			}
																		if(authVO.getFlaggingVOLst().size()==count)
																			{
																				flaggingForm.setAuthority("noDcDmTlAuth");
																				return mapping.findForward("flagging");
																			}
																	}
															}
														
													}
											}
										
									}
							}
						String authority=fVO.getAuthority();
						loggedUsrGrp=fVO.getLoggedUsrGrp();
						if("N".equalsIgnoreCase(authority))
							{
								if(loggedUsrGrp!=null && !"".equalsIgnoreCase(loggedUsrGrp) && ("GP2".equalsIgnoreCase(loggedUsrGrp)||"GP63".equalsIgnoreCase(loggedUsrGrp)))
									{
										String flaggedCasesForApproval=request.getParameter("flaggedCasesForApproval");
										if(flaggedCasesForApproval==null)
											{
												flaggingForm.setAuthority("false");
												return mapping.findForward("flagging");
											}
										flaggingForm.setAuthority("true");
									}
								else
									{
										flaggingForm.setAuthority("false");
										return mapping.findForward("flagging");
									}
							}
						else if(authority=="S")
							flaggingForm.setAuthority("exp");
						else
							flaggingForm.setAuthority("true");
						
						FlaggingVO flaggingVO=new FlaggingVO();
						flaggingVO.setLoggedUsrGrp(loggedUsrGrp);
						flaggingVO.setCaseId(caseId);
						if(caseIdS!=null)
							flaggingVO.setCaseId(caseIdS);
						flaggingVO.setFlagId(flagId);
						
						List<FlaggingVO> flaggingVOLst=flaggingService.getFlaggedCases(flaggingVO);
						if(flaggingVOLst!=null&&flaggingVOLst.size()>0)
							{
								request.setAttribute("previousFlagsList",flaggingVOLst);
								request.setAttribute("previousFlags","Y");
								String flaggedCasesForApproval=request.getParameter("flaggedCasesForApproval");
									if(flaggedCasesForApproval!=null && !"".equalsIgnoreCase(flaggedCasesForApproval))
										request.setAttribute("flaggedCasesForApproval", flaggedCasesForApproval);
									else
										request.setAttribute("flaggedCasesForApproval", "Y");
							}
						else if(flaggingVOLst.size()==0)
							flaggingForm.setFlagged("false");
						
						flagList=flaggingService.getFlagList();
						String newFlag=request.getParameter("newFlag");
						
						if(newFlag!=null && !"".equalsIgnoreCase(newFlag) && !"GP63".equalsIgnoreCase(loggedUsrGrp))
							request.setAttribute("newFlag",newFlag);
						else if("GP63".equalsIgnoreCase(loggedUsrGrp))
							request.setAttribute("newFlag","N");
						else
							request.setAttribute("newFlag","Y");
						
						request.setAttribute("flagList",flagList);
						request.setAttribute("caseId", caseId);
						if(caseIdS!=null)
							request.setAttribute("caseId", caseIdS);
						request.setAttribute("userGroup", loggedUsrGrp);
						request.setAttribute("lStrEmpId",lStrEmpId);

						request.setAttribute("caseId1",flaggingForm.getCaseId1());
						request.setAttribute("cardNo",flaggingForm.getCardNo());
						request.setAttribute("patState",flaggingForm.getPatState());
						request.setAttribute("patDist",flaggingForm.getPatDist());
						request.setAttribute("fromDt",flaggingForm.getFromDt());
						request.setAttribute("hospState",flaggingForm.getHospState());
						request.setAttribute("hospDist",flaggingForm.getHospDist());
						request.setAttribute("hospType",flaggingForm.getHospType());
						request.setAttribute("hospName",flaggingForm.getHospName());

						
						lStrPageName="flagging";
					}
				
				if("getDistricts".equalsIgnoreCase(lStrActionVal))
				{
					String stateId=request.getParameter("stateId");
					if((stateId!=null)&&(!"".equalsIgnoreCase(stateId)))
						{
						List<FlaggingVO> districtlist=flaggingService.getDistricts(stateId);
						List districtAjax=new ArrayList();
						for(FlaggingVO lb:districtlist)
							{
								if(lb.getID()!=null && districtAjax.size()>0)
									{
										districtAjax.add("@"+lb.getID()+"~"+lb.getDist());
									}
								else
									{
										districtAjax.add(lb.getID()+"~"+lb.getDist());
									}
							}
						request.setAttribute("AjaxMessage", districtAjax);
						return mapping.findForward("ajaxResult");
						}
				}
				if("getDistrictsNew".equalsIgnoreCase(lStrActionVal))
				{
					String stateId=request.getParameter("stateId");
					String patStateType = request.getParameter("patStateType");
					if((stateId!=null)&&(!"".equalsIgnoreCase(stateId)))
						{
						List<FlaggingVO> districtlist=flaggingService.getDistrictsNew(stateId,patStateType);
						List districtAjax=new ArrayList();
						for(FlaggingVO lb:districtlist)
							{
								if(lb.getID()!=null && districtAjax.size()>0)
									{
										districtAjax.add("@"+lb.getID()+"~"+lb.getDist());
									}
								else
									{
										districtAjax.add(lb.getID()+"~"+lb.getDist());
									}
							}
						request.setAttribute("AjaxMessage", districtAjax);
						return mapping.findForward("ajaxResult");
						}
				}
				if("getHospitals".equalsIgnoreCase(lStrActionVal))
					{
						String stateId=request.getParameter("stateId");
						String distId=request.getParameter("distId");
						String hospType=request.getParameter("hospType");
						if((stateId!=null)&&(!"".equalsIgnoreCase(stateId))&&
							 (distId!=null)&&(!"".equalsIgnoreCase(distId))&&
							  (hospType!=null)&&(!"".equalsIgnoreCase(hospType)))
							{
							List<FlaggingVO> hosplist=flaggingService.getHospList(stateId,distId,hospType);
							List hospAjax=new ArrayList();
							for(FlaggingVO lb:hosplist)
								{
									if(lb.getID()!=null && hospAjax.size()>0)
										{
											hospAjax.add("@"+lb.getID()+"~"+lb.getHospName());
										}
									else
										{
											hospAjax.add(lb.getID()+"~"+lb.getHospName());
										}
								}
							request.setAttribute("AjaxMessage", hospAjax);
							return mapping.findForward("ajaxResult");
							}
					}
				if("saveFlagDtls".equals(lStrActionVal))
					{
						String caseIdS=request.getParameter("caseIdS");
						if(caseIdS==null||"".equalsIgnoreCase(caseIdS))
							caseIdS=flaggingForm.getCaseIdS();
						request.setAttribute("caseIdS", caseIdS);
						
						if(caseIdS!=null)
							request.setAttribute("back", "true");
						
						FlaggingVO fVO=new FlaggingVO();
						fVO=flaggingService.checkAuthority(rolesList);
						String loggedUsrGrp1=fVO.getLoggedUsrGrp();
					
						String lStrFolderPath;
						FlaggingVO flaggingVO=new FlaggingVO(); 
						
						String finalFileNames[]=new String[5]; 
						
						Date date=new Date();
						DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
						dateFormat.format(date);
						
						caseId=request.getParameter("caseId");
						String newFlag=request.getParameter("newFlag");
						
						FlaggingVO schemeVO=null;
						if(caseId!=null)
							schemeVO= flaggingService.getCaseDtls(caseId);
						else if (caseIdS!=null)	
							schemeVO= flaggingService.getCaseDtls(caseIdS);
						if(schemeVO!=null)
							{
								if(schemeVO.getSchemeId()!=null && !"".equalsIgnoreCase(schemeVO.getSchemeId()))
									request.setAttribute("flaggingSchemeId",schemeVO.getSchemeId());		
							}
						
						flaggingVO.setCaseId(caseId);
						flaggingVO.setDeFlagId(flaggingForm.getDeFlagId());
						flaggingVO.setFlagNature(flaggingForm.getFlagNature());
						flaggingVO.setFlagStatus(flaggingForm.getFlagStatus());
						flaggingVO.setFlagRemarks(flaggingForm.getFlagRemarks());
						if(flaggingForm.getAmount()!=null&&flaggingForm.getAmount()!="")
							flaggingVO.setAmount(Long.parseLong(flaggingForm.getAmount()));
						if(flaggingForm.getAmountRefund()!=null && !"".equalsIgnoreCase(flaggingForm.getAmountRefund()))
							flaggingVO.setAmountRef(Long.parseLong(flaggingForm.getAmountRefund()));
						else
							flaggingVO.setAmountRef(0);
						flaggingVO.setCrtUsr(lStrEmpId);
						flaggingVO.setCrtDt(date);
						flaggingVO.setFdf(flaggingForm.getFdf());
						flaggingVO.setLoggedUsrGrp(loggedUsrGrp1);
						
						flaggingVO.setFlagRemarks(flaggingForm.getFlagRemarks());
						
						int count=0;
						String msg=null;
						for(int i=0;i<5;i++)
							{
								if(flaggingForm.getDoc(i)!=null)
									{
										if(flaggingForm.getDoc(i).getFileSize()>204800)
											{
												flaggingForm.setDoc(i,null);
												count++;
											}
									}	
							}
						if(count>0)
							{
								msg="Cannot upload files of size greater than 200KB";
								request.setAttribute("msg",msg);

								FlaggingVO flaggingVO1=new FlaggingVO();
								flaggingVO1.setLoggedUsrGrp(loggedUsrGrp1);
								flaggingVO1.setCaseId(caseId);
								List<FlaggingVO> flaggingVOLst=flaggingService.getFlaggedCases(flaggingVO1);
								if(flaggingVOLst!=null&&flaggingVOLst.size()>0)
									{
										request.setAttribute("previousFlagsList",flaggingVOLst);
										request.setAttribute("previousFlags","Y");
										String flaggedCasesForApproval=request.getParameter("flaggedCasesForApproval");
											if(flaggedCasesForApproval!=null && !"".equalsIgnoreCase(flaggedCasesForApproval))
												request.setAttribute("flaggedCasesForApproval", flaggedCasesForApproval);
											else
												request.setAttribute("flaggedCasesForApproval", "Y");
									}
								else if(flaggingVOLst.size()==0)
									flaggingForm.setFlagged("false");
								
								flagList=flaggingService.getFlagList();
								newFlag=request.getParameter("newFlag");
								
								if(newFlag!=null && !"".equalsIgnoreCase(newFlag) && !"GP63".equalsIgnoreCase(loggedUsrGrp1))
									request.setAttribute("newFlag",newFlag);
								else if("GP63".equalsIgnoreCase(loggedUsrGrp1))
									request.setAttribute("newFlag","N");
								else
									request.setAttribute("newFlag","Y");
								
								request.setAttribute("flagList",flagList);
								request.setAttribute("caseId", caseId);
								request.setAttribute("userGroup", loggedUsrGrp1);
								request.setAttribute("lStrEmpId",lStrEmpId);
								lStrPageName="flagging";
								flaggingForm.setAuthority("true");
								if(flaggingForm.getFdf()!=null)
									{
										flaggingForm.setFdf(null);
										flaggingForm.setAmount(null);
										flaggingForm.setFlagRemarks(null);
										flaggingForm.setAmountRefund(null);
									}
								return mapping.findForward(lStrPageName);
							}
						for(int i=0;i<5;i++)
							{	
						
								if(flaggingForm.getDoc(i)!=null)
									{
									
									fileDocArr[i]=flaggingService.getFlagDocId();
									StringBuffer sb=new StringBuffer();
									sb.append("/storageNAS-TS-Production/");
									sb.append("flagging/attachments/");
									sb.append(caseId);
									sb.append("/");
									sb.append(fileDocArr[i]);
									lStrFolderPath=sb.toString();
									
									boolean flag;
									flag=new File(lStrFolderPath).mkdirs();
									System.out.println(flag);
									
										String fileNameData=lStrFolderPath+"/"+flaggingForm.getDoc(i).getFileName();
										finalFileNames[i]=fileNameData;
										File file =new File(lStrFolderPath+"/"+flaggingForm.getDoc(i));
										byte[] fileData=flaggingForm.getDoc(i).getFileData();
											try
												{
													FileOutputStream fos=new FileOutputStream(file);
													fos.write(fileData);
													fos.close();
												}
											catch(FileNotFoundException e)
												{
													e.printStackTrace();
												}
									}
							}
						flaggingVO.setFileDocArr(fileDocArr);
						flaggingVO.setDoc(finalFileNames);								
						String flagId1=flaggingService.saveFlagDtls(flaggingVO);
							
						List<FlaggingVO> flaggingVOLst=new ArrayList<FlaggingVO>();
							if(flagId1!=null&&flagId1!="")
								{
									FlaggingVO fVO1=flaggingService.checkAuthority(rolesList);
									if(fVO1.getAuthority()=="S")
									{
										flaggingForm.setAuthority("false");
										return mapping.findForward("flagging");
									}
									flaggingVO.setLoggedUsrGrp(fVO1.getLoggedUsrGrp());
									flaggingVO.setCaseId(caseId);
									flaggingVO.setFlagId(flagId1);
									
									flaggingVOLst=flaggingService.getFlaggedCases(flaggingVO);
									request.setAttribute("previousFlagsList",flaggingVOLst);
									request.setAttribute("previousFlags","Y");
									request.setAttribute("flagId", flagId1);
									request.setAttribute("flaggedCasesForApproval", "Y");
								}
						
						flaggingForm.setCaseId1(flaggingForm.getCaseId1());
						flaggingForm.setCardNo(flaggingForm.getCardNo());
						flaggingForm.setPatState(flaggingForm.getPatState());
						flaggingForm.setPatDist(flaggingForm.getPatDist());
						flaggingForm.setFromDt(flaggingForm.getFromDt());
						flaggingForm.setHospState(flaggingForm.getHospState());
						flaggingForm.setHospDist(flaggingForm.getHospDist());
						flaggingForm.setHospType(flaggingForm.getHospType());
						flaggingForm.setHospName(flaggingForm.getHospName());
							
						flaggingForm.setFlagNature(null);
						flaggingForm.setFlagRemarks(null);
						flaggingForm.setFlagStatus(null);
						flaggingForm.setAmount(null);
						flaggingForm.setFdf(null);
						
						FlaggingVO fVO1=flaggingService.checkAuthority(rolesList);
						if("Y".equalsIgnoreCase(fVO1.getAuthority()))
							flaggingForm.setAuthority("true");
						
						flagList=flaggingService.getFlagList();
						request.setAttribute("flagList",flagList);
						request.setAttribute("userGroup", fVO1.getLoggedUsrGrp());
						request.setAttribute("newFlag",newFlag);
						request.setAttribute("caseId", caseId);
						request.setAttribute("lStrEmpId",lStrEmpId);
						
						if(fVO1.getLoggedUsrGrp()!=null && fVO1.getLoggedUsrGrp().equalsIgnoreCase(config.getString("JEO_Empanel_Grp")))
							lStrActionVal="viewFlaggedCases";
						else
							lStrPageName="flagging";						
					}
				
				if("getAttachments".equalsIgnoreCase(lStrActionVal))
					{
						caseId=request.getParameter("caseId");
						flagId=request.getParameter("flagId");
						String flagDocId=request.getParameter("flagDocId");
						
						if(flagDocId==null||flagDocId=="")
						{
						List<FlaggingVO> flaggingVOLst=flaggingService.getflagAttach(caseId,flagId,flagDocId);
						List<FlaggingVO> flaggingVOLst1=new ArrayList<FlaggingVO>();
						if(flaggingVOLst.size()>0)
							{
								for(int i=0;i<flaggingVOLst.size();i++)
									{
										FlaggingVO flaggingVO=new FlaggingVO();
										String attachPath=flaggingVOLst.get(i).getAttachPath();
										int slashPos=attachPath.lastIndexOf('/');
										attachPath=attachPath.substring(slashPos+1,attachPath.length());
										
										if(attachPath==null||attachPath.equalsIgnoreCase(""))
											continue;
										flaggingVO.setFlagId(flaggingVOLst.get(i).getFlagId());
										flaggingVO.setCaseId(flaggingVOLst.get(i).getCaseId());
										flaggingVO.setAttachPath(attachPath);
										flaggingVO.setFlagDocId(flaggingVOLst.get(i).getFlagDocId());
										flaggingVOLst1.add(flaggingVO);
									}
								request.setAttribute("flagAttachments", flaggingVOLst1);
							}	
						else
						flaggingForm.setAttachVal("Y");
						lStrPageName="flaggingAttach";
						}
						
						if(flagDocId!=null&&flagDocId!="")
						{
						List<FlaggingVO> flaggingVOLst=flaggingService.getflagAttach(caseId,flagId,flagDocId);
						String attachPath=flaggingVOLst.get(0).getAttachPath();
						int slashPos=attachPath.lastIndexOf('/');
						String fileName=attachPath.substring(slashPos+1,attachPath.length());
						String fileType=fileName.substring((fileName.lastIndexOf('.')+1),fileName.length());
						ServletOutputStream out = response.getOutputStream();
						String attachMode="attachment";
						 if(fileType.equalsIgnoreCase(ASRIConstants.FILE_EXT_JPG) || fileType.equalsIgnoreCase(ASRIConstants.FILE_EXT_JPEG) || fileType.equalsIgnoreCase(ASRIConstants.FILE_EXT_GIF)){
				              attachMode="inline";
				          }
						 lStrContentType=ASRIConstants.FILE_EXT.get(fileType);
								try
									{
										File f=new File(attachPath);
										FileInputStream fis=new FileInputStream(f);
										DataInputStream dis=new DataInputStream(fis);
										byte[] data=new byte[dis.available()];
										fis.read(data);
										
										 request.setAttribute ( "File", data ) ;
					      	             request.setAttribute ( "ContentType",lStrContentType);
					      	             request.setAttribute ( "FileName", fileName ) ;
					      	             request.setAttribute ( "Extn", fileType ) ;
					      	             response.setContentType ( lStrContentType ) ;
					      	             response.setHeader("Content-Disposition",""+attachMode+"; filename="+fileName);
					      	             out.write(data);
									}
								catch(Exception e)
									{
										e.printStackTrace();
									}
								finally
									{
										out.close();
									}
								return mapping.findForward(null) ;  
						}		
					}

				if("viewFlaggedCases".equalsIgnoreCase(lStrActionVal))
					{
						String schemeId=(String) session.getAttribute("userState");
						
						int start_index=0;
						int end_index=0;
						int pageId;	
						int noOfRecords;
						int noOfPages=0;
						char hospType='\0';
						String flaggedCasesForApproval=request.getParameter("flaggedCasesForApproval");
						String newFlag=request.getParameter("newFlag");
						request.setAttribute("flaggedCasesForApproval", flaggedCasesForApproval);
						
						FlaggingVO fVO1=flaggingService.checkAuthority(rolesList);
						
						FlaggingVO flaggingVO=new FlaggingVO();
						
						if((flaggingForm.getHospType()!=null)&&(!"".equalsIgnoreCase(flaggingForm.getHospType()))&&(flaggingForm.getHospType().length()==1))
							hospType=flaggingForm.getHospType().charAt(0);
						
						flaggingVO.setSchemeId(schemeId);
						flaggingVO.setCaseId(flaggingForm.getCaseId1());
						flaggingVO.setCardNo(flaggingForm.getCardNo());
						flaggingVO.setPatState(flaggingForm.getPatState());
						flaggingVO.setPatDist(flaggingForm.getPatDist());
						flaggingVO.setFromDt(flaggingForm.getFromDt());
						flaggingVO.setHospState(flaggingForm.getHospState());
						flaggingVO.setHospDist(flaggingForm.getHospDist());
						flaggingVO.setHospType(hospType);
						flaggingVO.setHospName(flaggingForm.getHospName());
						flaggingVO.setLoggedUsrGrp(roleId);
						flaggingVO.setFlaggedCasesForApproval(flaggedCasesForApproval);
						flaggingVO.setLoggedUsrId(lStrEmpId);
						flaggingVO.setMainCatName(flaggingForm.getMainCatName());
						flaggingVO.setProcName(flaggingForm.getProcName());
						flaggingVO.setCatName(flaggingForm.getCatName());
						request.setAttribute("catList", commonService.getCategoryList(lStrEmpId,roleId));
		               request.setAttribute("icdCatList", new ArrayList<LabelBean>());
		                request.setAttribute("procList", new ArrayList<LabelBean>());
						List<FlaggingVO> flaggingVOLst=flaggingService.getAllFlaggedCases(flaggingVO);
						
						if(flaggingVOLst.size()==0)
							{
								flaggingForm.setFlagged("false");
								flagList=flaggingService.getFlagList();
								request.setAttribute("flagList",flagList);
								
								if(flaggingForm.getPatDist()!=null && !"-1".equalsIgnoreCase(flaggingForm.getPatDist()) 
										|| flaggingForm.getPatState()!=null && !"-1".equalsIgnoreCase(flaggingForm.getPatState()))
									{
										List<FlaggingVO> districtlist=flaggingService.getDistricts(flaggingForm.getPatState());
										request.setAttribute("patDistList",districtlist);
									}
								else
									{
										List<FlaggingVO> districtlist=new ArrayList<FlaggingVO>();
										request.setAttribute("patDistList",districtlist);
									}	
								if(flaggingForm.getHospDist()!=null && !"-1".equalsIgnoreCase(flaggingForm.getHospDist())
										|| flaggingForm.getHospState()!=null && !"-1".equalsIgnoreCase(flaggingForm.getHospState()))
									{
										List<FlaggingVO> hospDistList=flaggingService.getDistricts(flaggingForm.getHospState());
										request.setAttribute("hospDistList",hospDistList);
									}
								else
									{
										List<FlaggingVO> hospDistList=new ArrayList<FlaggingVO>();
										request.setAttribute("hospDistList",hospDistList);
									}
								if(flaggingForm.getHospType()!=null && !"-1".equalsIgnoreCase(flaggingForm.getHospType())
										|| flaggingForm.getHospDist()!=null && !"-1".equalsIgnoreCase(flaggingForm.getHospDist()))
									{
										List<FlaggingVO> hospTypeList=new ArrayList<FlaggingVO>();
										FlaggingVO fvo=new FlaggingVO();
										fvo.setID("C");
										fvo.setID1("Corporate");
										hospTypeList.add(fvo);
										FlaggingVO fvo1=new FlaggingVO();
										fvo1.setID("G");
										fvo1.setID1("Government");
										hospTypeList.add(fvo1);
										request.setAttribute("hospTypeList",hospTypeList);
									}
								else
									{
										List<FlaggingVO> hospTypeList=new ArrayList<FlaggingVO>();
										request.setAttribute("hospTypeList",hospTypeList);
									}
								if(flaggingForm.getHospName()!=null && !"-1".equalsIgnoreCase(flaggingForm.getHospName())
										|| flaggingForm.getHospType()!=null && !"-1".equalsIgnoreCase(flaggingForm.getHospType()))
									{
										List<FlaggingVO> hospNameList=flaggingService.getHospList(flaggingForm.getHospState(),flaggingForm.getHospDist(),flaggingForm.getHospType());
										request.setAttribute("hospNameList",hospNameList);
									}
								else
									{
										List<FlaggingVO> hospNameList=new ArrayList<FlaggingVO>();
										request.setAttribute("hospNameList",hospNameList);
									}
								
								
								return mapping.findForward("viewFlaggedCases");
							}
						else
							{	
								if((request.getParameter("pageId")!=null&&(!"".equalsIgnoreCase(request.getParameter("pageId")))))
									{
										pageId=Integer.parseInt(request.getParameter("pageId"));
											
										if((request.getParameter("end_index")!=null&&(!"".equalsIgnoreCase(request.getParameter("end_index")))))
												end_index=Integer.parseInt(request.getParameter("end_index"));
											else
												end_index=10;
												
										start_index=end_index*(pageId-1);
									}
								else
									{
										pageId=1;
										end_index=10;
										start_index=0;
									}
								noOfRecords=flaggingVOLst.size();
								int div=noOfRecords%end_index;
								if(div==0)
									noOfPages=noOfRecords/end_index;
								if(div!=0)
									noOfPages=(noOfRecords/end_index)+1;
								
								flaggingVO.setStart_index(start_index);
								flaggingVO.setEnd_index(end_index);

								flaggingVOLst=flaggingService.getAllFlaggedCases(flaggingVO);
								String csvFlag=request.getParameter("csvFlag");
								List<FlaggingVO> flaggingVOLst1=null;
								if(csvFlag!=null && "Y".equalsIgnoreCase(csvFlag))
									{
									flaggingVO.setStart_index(0);
									flaggingVO.setEnd_index(0);
									flaggingVOLst1=flaggingService.getAllFlaggedCases(flaggingVO);
									}
								System.out.println("size"+flaggingVOLst.size());
								flaggingForm.setLstFlaggedCases(flaggingVOLst);
								flaggingForm.setFlagged("true");
								
								FlaggingVO fVO=flaggingService.checkAuthority(rolesList);
								flagList=flaggingService.getFlagList();
								
								
								if(csvFlag!=null && "Y".equalsIgnoreCase(csvFlag))
									{
										String lStrDirPath = config.getString("CASESSEARCH.MapPath");
										String lStrFileName = config.getString("CASESSEARCH.Record")+config.getString("DOT_VALUE")+ "CSV";
										File lcsvfile = createFile(lStrDirPath,lStrFileName);
										char separator = ',';
								        StringBuilder line = new StringBuilder();  
										if(flaggingVOLst.size()>0)
											{
									            line.append("Case No");
											    line.append(separator);
												line.append("Flag ID");
												line.append(separator);
												line.append("Flag Status");
												line.append(separator);
												line.append("Card No");
												line.append(separator);
												line.append("Flagged Date and Time");
												line.append(separator);
												line.append("Patient District");
												line.append(separator);
												line.append("Hospital District");
												line.append(separator);
												line.append("Hospital Type");
												line.append(separator);
												line.append("Hospital Name");
												line.append(separator);
												
												line.append("\n");
								                line.append("\n");
												
												for(FlaggingVO flaggingVOCsv : flaggingVOLst1)
													{
													    line.append(flaggingVOCsv.getCaseId());
													    line.append(separator);
														line.append(flaggingVOCsv.getFlagId());
														line.append(separator);
														line.append(flaggingVOCsv.getCurrentStatus());
														line.append(separator);
														line.append(flaggingVOCsv.getCardNo());
														line.append(separator);
														line.append(flaggingVOCsv.getCrtDt());
														line.append(separator);
														line.append(flaggingVOCsv.getPatDist());
														line.append(separator);
														line.append(flaggingVOCsv.getHospDist());
														line.append(separator);
														if("C".equalsIgnoreCase(Character.toString(flaggingVOCsv.getHospType())))
															line.append("Corporate");
														else if("G".equalsIgnoreCase(Character.toString(flaggingVOCsv.getHospType())))
															line.append("Government");
														else
															line.append(flaggingVOCsv.getHospType());
														line.append(separator);
														line.append(flaggingVOCsv.getHospName());
														line.append(separator);
														line.append("\n");
													}
											}
										
											request.setAttribute("File", line.toString().getBytes());    
										    response.setContentType(config.getString("REPORT.CaseSearchCvsContentType"));
										    response.setHeader("Content-Disposition","Attachment; filename="+lStrFileName);
										    response.setCharacterEncoding("UTF-8");
									        return mapping.findForward("openFile");
									}
								
								
								
								request.setAttribute("flagList",flagList);
								request.setAttribute("newFlag",newFlag);
								request.setAttribute("userGroup", fVO.getLoggedUsrGrp());
								request.setAttribute("lStrEmpId",lStrEmpId);
								request.setAttribute("pageId",pageId);
								request.setAttribute("noOfRecords",noOfRecords);
								request.setAttribute("lastPage",noOfPages);
								request.setAttribute("start_index",start_index);
								request.setAttribute("end_index",end_index);
								request.setAttribute("endresults",start_index+flaggingVOLst.size());
								
								flaggingForm.setCaseId1(flaggingForm.getCaseId1());
								flaggingForm.setCardNo(flaggingForm.getCardNo());
								flaggingForm.setPatState(flaggingForm.getPatState());
								flaggingForm.setPatDist(flaggingForm.getPatDist());
								flaggingForm.setFromDt(flaggingForm.getFromDt());
								flaggingForm.setHospState(flaggingForm.getHospState());
								flaggingForm.setHospDist(flaggingForm.getHospDist());
								flaggingForm.setHospType(flaggingForm.getHospType());
								flaggingForm.setHospName(flaggingForm.getHospName());
								
								if(flaggingForm.getPatDist()!=null && !"-1".equalsIgnoreCase(flaggingForm.getPatDist()) 
										|| flaggingForm.getPatState()!=null && !"-1".equalsIgnoreCase(flaggingForm.getPatState()))
									{
										List<FlaggingVO> districtlist=flaggingService.getDistricts(flaggingForm.getPatState());
										request.setAttribute("patDistList",districtlist);
									}
								else
									{
										List<FlaggingVO> districtlist=new ArrayList<FlaggingVO>();
										request.setAttribute("patDistList",districtlist);
									}	
								if(flaggingForm.getHospDist()!=null && !"-1".equalsIgnoreCase(flaggingForm.getHospDist())
										|| flaggingForm.getHospState()!=null && !"-1".equalsIgnoreCase(flaggingForm.getHospState()))
									{
										List<FlaggingVO> hospDistList=flaggingService.getDistricts(flaggingForm.getHospState());
										request.setAttribute("hospDistList",hospDistList);
									}
								else
									{
										List<FlaggingVO> hospDistList=new ArrayList<FlaggingVO>();
										request.setAttribute("hospDistList",hospDistList);
									}
								if(flaggingForm.getHospType()!=null && !"-1".equalsIgnoreCase(flaggingForm.getHospType())
										|| flaggingForm.getHospDist()!=null && !"-1".equalsIgnoreCase(flaggingForm.getHospDist()))
									{
										List<FlaggingVO> hospTypeList=new ArrayList<FlaggingVO>();
										FlaggingVO fvo=new FlaggingVO();
										fvo.setID("C");
										fvo.setID1("Corporate");
										hospTypeList.add(fvo);
										FlaggingVO fvo1=new FlaggingVO();
										fvo1.setID("G");
										fvo1.setID1("Government");
										hospTypeList.add(fvo1);
										request.setAttribute("hospTypeList",hospTypeList);
									}
								else
									{
										List<FlaggingVO> hospTypeList=new ArrayList<FlaggingVO>();
										request.setAttribute("hospTypeList",hospTypeList);
									}
								if(flaggingForm.getHospName()!=null && !"-1".equalsIgnoreCase(flaggingForm.getHospName())
										|| flaggingForm.getHospType()!=null && !"-1".equalsIgnoreCase(flaggingForm.getHospType()))
									{
										List<FlaggingVO> hospNameList=flaggingService.getHospList(flaggingForm.getHospState(),flaggingForm.getHospDist(),flaggingForm.getHospType());
										request.setAttribute("hospNameList",hospNameList);
									}
								else
									{
										List<FlaggingVO> hospNameList=new ArrayList<FlaggingVO>();
										request.setAttribute("hospNameList",hospNameList);
									}
								
							lStrPageName="viewFlaggedCases";	
						}
					}
				if("attachSize".equalsIgnoreCase(lStrActionVal))
					{
					 String id=request.getParameter("id");
					 char i=id.charAt(id.length()-1);
					 FormFile formFileObj = null;
					 formFileObj = flaggingForm.getDoc(Character.getNumericValue(i));
                     if (formFileObj != null && !formFileObj.getFileName().trim().equals(""))
                     { 
                         if(formFileObj.getFileSize() > 204800)
                         	{
                        	 	String msg="Cannot upload a file of size more than 200KB";
                        	 	request.setAttribute("AjaxMessage", msg);
 							   return mapping.findForward("ajaxResult");
                         	}
                         else
                         	{
                        	 	String msg=null;
                        	 	request.setAttribute("AjaxMessage", msg);
 							   return mapping.findForward("ajaxResult");
                         	}
                     }
					}
				return mapping.findForward(lStrPageName);
			}
	 /**
		 * Creates the file.
		 *
		 * @param lStrDirPath the l str dir path
		 * @param lStrFileName the l str file name
		 * @return the file
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		private File createFile(String lStrDirPath, String lStrFileName)
				throws IOException {
			// Making Directory
			File file = new File(lStrDirPath);
			if (!file.exists()) {
				file.mkdir();
			}
			File lfile = new File(lStrFileName);
			if (!lfile.exists()) {
				lfile.createNewFile();
			} else {
				lfile.delete();
				lfile.createNewFile();
			}
			return lfile;
		}
}
