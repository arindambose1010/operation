package com.ahct.annualCheckUp.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.CompositeConfiguration;

import com.ahct.annualCheckUp.dao.AhcClaimsDao;
import com.ahct.claims.util.ClaimsConstants;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfAhcCaseClaim;
import com.ahct.model.EhfAhcTdChklst;
import com.ahct.model.EhfAhcexChklst;
import com.ahct.model.EhfAnnualCaseDtls;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;

public class AhcClaimsServiceImpl implements AhcClaimsService {
	private GenericDAO genericDao;
	private AhcClaimsDao ahcClaimsDao;
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
	public GenericDAO getGenericDao() {
		return genericDao;
	}


	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}


	public AhcClaimsDao getAhcClaimsDao() {
		return ahcClaimsDao;
	}


	public void setAhcClaimsDao(AhcClaimsDao ahcClaimsDao) {
		this.ahcClaimsDao = ahcClaimsDao;
	}


	public static ConfigurationService getConfigurationService() {
		return configurationService;
	}


	public static void setConfigurationService(
			ConfigurationService configurationService) {
		AhcClaimsServiceImpl.configurationService = configurationService;
	}


	@Override
	public EhfAnnualCaseDtls getAhcStatusDtls(String ahcId) {
		EhfAnnualCaseDtls caseDtls = genericDao.findById(EhfAnnualCaseDtls.class, String.class, ahcId);
		return caseDtls;
	}


	@Override
	public List<ClaimsFlowVO> getAuditTrail(String ahcId) {
		List<ClaimsFlowVO> lstWorkList = new ArrayList<ClaimsFlowVO>();
		String args[] = new String[1];
		args[0] = ahcId;
		try {
			StringBuffer query = new StringBuffer();
			query.append(" select au.firstName ||''|| au.lastName as auditName ,a.remarks as cexRemark ,");
			query.append(" to_char(a.crtDt) as auditDate,ac.grpShortName as auditRole,ac1.cmbDtlName as auditAction,a.apprvAmt as COUNT ");
			query.append(" from EhfAhcAudit a , EhfmGrps ac , EhfmUsers au,EhfmCmbDtls ac1   ");
			query.append(" where a.actId=ac1.cmbDtlId and a.actBy = au.userId and ac.grpId=a.userRole ");
			query.append(" and a.actId in ( ");
			String[] claimStatus = {"CD371","CD372","CD373","CD374","CD375","CD378","CD379","CD380","CD382","CD383","CD384","CD385","CD387","CD388","CD390","CD590"};
			for (int i = 0; i < claimStatus.length; i++) {
				query.append(" '" + claimStatus[i] + "' ");
				if (i != claimStatus.length - 1)
					query.append(",");
			}

			query.append(" ) and a.id.ahcId = ? ");
			query.append(" order by a.id.actOrder ");

			lstWorkList = genericDao.executeHQLQueryList(ClaimsFlowVO.class,
					query.toString(), args);

		} catch (Exception e) {
			System.out.println("Error occured in getAuditTrail() in AhcClaimsServiceImpl class."
					+ e.getMessage());
			e.printStackTrace();
		}
		return lstWorkList;
	}
	@Override
	public String checkMandatoryAttch(String ahcId , String pStrattachType)
	{
		String msg = null;
		StringBuffer query = new StringBuffer();
		Map<String,String> attachNames = new HashMap<String,String>();
		
		try
		{
			int i=0;
	
			query.append("select testId as ID, testName as VALUE,patientId as CONST from EhfAhcPatientTest where ");
			query.append("patientId = '"+ahcId+"' and attachPath is null");
			List<LabelBean> lstdocType = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
			
			
			
	/*	query.append(" select distinct au.id.docType as ID , cm.cmbDtlName as VALUE ");
		query.append(" from EhfAttachType au ,EhfmCmbDtls cm where au.activeYN ='Y' and ");
		query.append(" au.docName='M' ");
		query.append(" and cm.cmbDtlId =au.id.docType and au.id.updType ='"+pStrattachType+"' ");
		List<LabelBean> lstdocType = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
		query = new StringBuffer();
		query.append(" select distinct attachType as ID");
		query.append(" from EhfAhcDocAttch ac where ac.ahcId = '"+ahcId+"' and ac.activeYN='Y'  ");
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
		}*/
		
		/*if( i < lstdocType.size())
		{*/
			
			
			if(lstdocType !=null && lstdocType.size()>0){
				msg ="Please add mandatory attachments";		
			}
			else
			{
				msg ="success";	
			}
			/*	for(LabelBean labelBean:lstdocType)
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
		}*/
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		return msg;
	}


	@Override
	public String saveAhcClaim(ClaimsFlowVO claimFlowVO) {
		String msg= ahcClaimsDao.saveClaimDtls(claimFlowVO);
		return msg;
	}


	@Override
	public EhfAhcexChklst getExeRemarks(String ahcId) {
		EhfAhcexChklst caseDtls = genericDao.findById(EhfAhcexChklst.class, String.class, ahcId);
		return caseDtls;
	}


	@Override
	public List<LabelBean> getCasesForPayments(ClaimsFlowVO claimsFlowVO) {
		List<LabelBean> lstCasesForPayment = new ArrayList<LabelBean>();
		String fromDate;
		String sqlFromDate;
		String toDate;
		String sqlToDate;
		String database = config.getString("Database");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sqlf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			StringBuffer query = new StringBuffer();
			query.append(" select EC.ahcId as ID,EH.hospName as LVL,EC.pckAppvAmt||'' as WFTYPE,EP.name as VALUE,EP.cardNo as UNITID, EL.locName as EMPNAME,EHB.hospAccName as SUBNAME ");
			query.append(" from EhfAnnualCaseDtls EC,EhfmHospitals EH,EhfAnnualPatientDtls EP,EhfmLocations EL,EhfmHospBankDtls EHB ");
			query.append(" where EC.ahcHospCode=EH.hospId and EP.ahcId=EC.ahcId ");
			query.append(" and EL.id.locId=EP.districtCode and EHB.id.hospId=EH.hospId and EH.hospActiveYN in ('Y','S','C','D','E','CB') ");

			if (claimsFlowVO.getCaseStat() != null
					&& !claimsFlowVO.getCaseStat().equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				query.append(" and EC.ahcStatus='"
						+ claimsFlowVO.getCaseStat() + "'");
			if (claimsFlowVO.getPatName() != null
					&& !claimsFlowVO.getPatName().equalsIgnoreCase(
							ClaimsConstants.EMPTY))
				query.append(" and upper(EP.name) like  upper('%"
						+ claimsFlowVO.getPatName().trim() + "%') ");
			if (claimsFlowVO.getWapNo() != null
					&& !claimsFlowVO.getWapNo().equals(ClaimsConstants.EMPTY))
				query.append("  and upper(EP.cardNo) like  upper('%"
						+ claimsFlowVO.getWapNo().trim() + "%') ");
			if (claimsFlowVO.getCaseId() != null
					&& !claimsFlowVO.getCaseId().equals(ClaimsConstants.EMPTY))
				query.append("  and upper(EC.ahcId) like  upper('%"
						+ claimsFlowVO.getCaseId().trim() + "%') ");
			if (claimsFlowVO.getFromDate() != null
					&& !claimsFlowVO.getFromDate()
							.equals(ClaimsConstants.EMPTY)
					&& claimsFlowVO.getToDate() != null
					&& !claimsFlowVO.getToDate().equals(ClaimsConstants.EMPTY)) {
				fromDate = claimsFlowVO.getFromDate();
				sqlFromDate = sqlf.format(sdf.parse(fromDate).getTime());
				toDate = claimsFlowVO.getToDate().toString();

				// To include todate in search criteria--adding date+1
				Calendar cal = Calendar.getInstance();
				cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(toDate));
				cal.add(Calendar.DAY_OF_YEAR, 1); // <--
				Date tomorrow = cal.getTime();
				String lStrToDate = new SimpleDateFormat("dd-MM-yyyy")
						.format(tomorrow);
				// End of date+1

				sqlToDate = sqlf.format(sdf.parse(toDate).getTime());

				if (database.equalsIgnoreCase("ORACLE"))
					query.append("and EC.crtDt between  TO_DATE('" + fromDate
							+ "','DD-MM-YYYY') and TO_DATE('" + lStrToDate
							+ "','DD-MM-YYYY')");
				else if (database.equalsIgnoreCase("MYSQL"))
					query.append("and EC.crtDt between '" + sqlFromDate
							+ "' and '" + sqlToDate + "'");
			}
			 if(claimsFlowVO.getSchemeType()!=null && !claimsFlowVO.getSchemeType().equalsIgnoreCase("")){
				  query.append(" and EC.schemeId in ('"+claimsFlowVO.getSchemeType()+"') ");
				  query.append(" and EHB.id.scheme in ('"+claimsFlowVO.getSchemeType()+"') ");
			  }
			query.append(" order by EC.ahcId DESC ");
			lstCasesForPayment = genericDao.executeHQLQueryList(
					LabelBean.class, query.toString());
		} catch (Exception e) {
			System.out.println("Error occured in getCasesForPayments() in ClaimsFlowServiceImpl class."
					+ e.getMessage());
			e.printStackTrace();
		}
		return lstCasesForPayment;
	}


	@Override
	public String updateClaimDtlsforPayment(ClaimsFlowVO claimFlowVO) {
		// TODO Auto-generated method stub
		String msg = ahcClaimsDao.updateClaimDtlsforPayment(claimFlowVO);
		return msg;
		
	}


	@Override
	public EhfAhcTdChklst getTdRemarks(String ahcId) {
		EhfAhcTdChklst caseDtls = genericDao.findById(EhfAhcTdChklst.class, String.class, ahcId);
		return caseDtls;
	}
	@Override
	public EhfAhcCaseClaim getAhcCaseClaimDtls(String ahcId){
	EhfAhcCaseClaim caseDtls = genericDao.findById(EhfAhcCaseClaim.class, String.class, ahcId);
		return caseDtls;
	}
	
}
