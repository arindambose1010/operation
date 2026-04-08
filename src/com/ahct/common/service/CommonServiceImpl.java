package com.ahct.common.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ahct.common.util.SendSMS;
import com.ahct.common.vo.LabelBean;
import com.ahct.common.vo.PatientSmsVO;
import com.ahct.model.AsritSms;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfCaseFollowupClaim;
import com.ahct.model.EhfCaseLockStatus;
import com.ahct.model.EhfCaseTherapy;
import com.ahct.model.EhfCochlearFollowup;
import com.ahct.model.EhfPatient;
import com.ahct.model.EhfPatientSmsData;
import com.ahct.model.EhfSmsBuffer;
import com.ahct.model.EhfmCmbDtls;
import com.ahct.model.EhfmDepts;
import com.ahct.model.EhfmHospitals;
import com.ahct.model.EhfmLocations;
import com.ahct.model.EhfmSeq;
import com.ahct.model.EhfmTherapyCatMst;
import com.ahct.model.EhfmPastHistoryMst;
import com.ahct.model.EhfmFamilyHistoryMst;
import com.ahct.model.EhfmTherapyProcMst;
import com.ahct.model.EhfmUsrGrpsMpgId;
import com.ahct.model.EhfmUsrHospitalMpg;
import com.ahct.model.EhfmUsrSpecialityMpg;
import com.ahct.patient.vo.PatientVO;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.emailConfiguration.EmailConstants;
import com.tcs.framework.emailConfiguration.EmailNotifier;
import com.tcs.framework.emailConfiguration.EmailVO;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria.CriteriaOperator;
import com.ahct.model.EhfmGrps;



public class CommonServiceImpl implements CommonService {
	private final static Logger GLOGGER = Logger.getLogger ( CommonServiceImpl.class ) ;
	GenericDAO genericDao;
	HibernateTemplate hibernateTemplate;
	EmailNotifier emailNotifier;
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;
	String illnessHistory;
	String familyHis;

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
	public GenericDAO getGenericDao() {
				return genericDao;
	}

	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}
	public EmailNotifier getEmailNotifier() {
		return emailNotifier;
	}
	public void setEmailNotifier(EmailNotifier emailNotifier) {
		this.emailNotifier = emailNotifier;
	}
	/**
     * ;
     * @param String seqIdentifier
     * @return EhfmSeq ehfmSeq
     * @function This Method is Used For getting EhfmSeq data based on seqIdentifier
     */
	
	public EhfmSeq getSeqNextVal(String seqIdentifier) {
		EhfmSeq ehfmSeq = 
	            genericDao.findById(EhfmSeq.class, String.class, seqIdentifier);
	        return ehfmSeq;
	}
	/**
     * ;
     * @param EhfmSeq ehfmSeq
     * @function This Method is Used For saving seqVal in EhfmSeq Table
     */
	
	public void updateSequenceVal(EhfmSeq ehfmSeq) {
		try {
            genericDao.save(ehfmSeq);
        } catch (Exception e) {
        	GLOGGER.error("Exception occurred in updateSequence() in CommonServiceImpl class."+e.getMessage());
        }
		
	}
	
	/*public SgvcSeqMst getNextVal(String seqIdentifier) {
		SgvcSeqMst sgvcSeqMst = 
	            genericDao.findById(SgvcSeqMst.class, String.class, seqIdentifier);
	        return sgvcSeqMst;
	}
	
	
	public void updateSequence(SgvcSeqMst sgvcSeqMst) {
		try {
            genericDao.save(sgvcSeqMst);
        } catch (Exception e) {
        	GLOGGER.error("Exception occurred in updateSequence() in CommonServiceImpl class."+e.getMessage());
        }
		
	}
	*/
	public List<LabelBean> getDistrictList() throws Exception
    {
    	List<LabelBean> districtList = new ArrayList<LabelBean>();
    	Iterator asrimListItr=null;
    	EhfmLocations asrimLocations=null;
    	try
    	{
//    		HashMap<String,Object> map=new HashMap<String,Object>();
//    		map.put("locHdrId", "LH6");
//    		map.put("activeYn", "Y");
//    		List<EhfmLocations> asrimLocationsList=(List<EhfmLocations>)genericDao.findAllByPropertyMatch(EhfmLocations.class,map);
    		
    		
    		
    		List<GenericDAOQueryCriteria> criteriaList = 
                    new ArrayList<GenericDAOQueryCriteria>();

                criteriaList.add(new GenericDAOQueryCriteria("locName", null, 
                        GenericDAOQueryCriteria.CriteriaOperator.ASC));
                criteriaList.add(new GenericDAOQueryCriteria("locHdrId", "LH6", 
                        GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
                criteriaList.add(new GenericDAOQueryCriteria("activeYn", "Y", 
                        GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
                List<EhfmLocations> asrimLocationsList = 
                        genericDao.findAllByCriteria(EhfmLocations.class, criteriaList);
    		
    		
    		
    		asrimListItr=asrimLocationsList.iterator();
    		while(asrimListItr.hasNext())
    		{
    			asrimLocations=(EhfmLocations)asrimListItr.next();
    			LabelBean labelBean =new LabelBean();
    			labelBean.setID(asrimLocations.getId().getLocId());
    			labelBean.setVALUE(asrimLocations.getLocName());
    			districtList.add(labelBean);
    		}
    		
        String args[] = new String[2];
        args[0]="LH6";
        args[1]="Y";
        StringBuffer query = new StringBuffer();
        query.append("select loc.loc_id ID,loc.loc_name VALUE from ehfm_locations loc where loc.loc_hdr_id=? and loc.active_yn=? order by loc.loc_name asc");
       //districtList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),args);
    	}
    	catch(Exception e)
    	{
    		GLOGGER.error("Exception occurred in getDistrictList() in CommonServiceImpl class."+e.getMessage());
    	}
        return districtList;
    }

    /**
     * ;
     * @param String district ID
     * @return List of type LabelBean  Mandal List
     * @function This Method is Used For Getting List of Mandals for the passed District
     */
	
    public List<LabelBean> getMandalList(String distId) throws Exception
    {
		SessionFactory factory=null;
		Session session=null;
        List<LabelBean> mandalList = new ArrayList<LabelBean>();
        Iterator asrimListItr=null;
    	EhfmLocations asrimLocations=null;
    	try
    	{
    		String mdllocHdrId="LH7";
    		factory= hibernateTemplate.getSessionFactory();
    		session=factory.openSession();
    		StringBuffer mandalquery = new StringBuffer();
    		mandalquery.append("from EhfmLocations al where al.locHdrId='"+mdllocHdrId+"' and al.id.locParntId='"+distId+"' and al.activeYn='Y' order by al.locName asc");
            Query mandallst=session.createQuery(mandalquery.toString());
            Iterator mandalItr=mandallst.iterate();
            while(mandalItr.hasNext())
            {
            	asrimLocations=(EhfmLocations)mandalItr.next();
            	LabelBean labelBean =new LabelBean();
    			labelBean.setID(asrimLocations.getId().getLocId());
    			labelBean.setVALUE(asrimLocations.getLocName());
    			mandalList.add(labelBean);
            }
    	    	   		
        StringBuffer query = new StringBuffer();
        String args[] = new String[3];
        query.append("select loc.loc_id ID,loc.loc_name VALUE from ehfm_locations loc where loc.loc_parnt_id=? and loc.loc_hdr_id=? and loc.active_yn=? order by loc.loc_name asc");
        args[0] = distId;
        args[1]= "LH7";
        args[2] = "Y";
       // mandalList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),args);
        }
        catch(Exception e)
        {
        	GLOGGER.error("Exception occurred in getMandalList() in CommonServiceImpl class."+e.getMessage());
        }
    	finally
    	{
    		session.close();
    		factory.close();
    	}
        
        return mandalList;
    }
    
    /**
     * ;
     * @param String mandal ID
     * @return List of type LabelBean  Village List
     * @function This Method is Used For Getting List of Villages for the passed Mandal
     */
	
	public List<LabelBean> getVillageList(String mandalId) throws Exception {
		List<LabelBean> villageList = new ArrayList<LabelBean>();
        try
        {
        StringBuffer query = new StringBuffer();
        String args[] = new String[3];
        query.append("select loc.loc_id ID,loc.loc_name VALUE from ehfm_locations loc where loc.loc_parnt_id=? and loc.loc_hdr_id=? and loc.active_yn=? order by loc.loc_name asc");
        args[0] = mandalId;
        args[1]= "LH8";
        args[2] = "Y";
        villageList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),args);
        }
        catch(Exception e)
        {
        	GLOGGER.error("Exception occurred in getVillageList() in CommonServiceImpl class."+e.getMessage());
        }
        
        return villageList;
	}

	/**
     * ;
     * @param String village ID
     * @return List of type LabelBean  Hamlet List
     * @function This Method is Used For Getting List of Hamlets for the passed Village
     */
	
	public List<LabelBean> getHamletList(String villageId) throws Exception {
		List<LabelBean> hamletList = new ArrayList<LabelBean>();
        try
        {
        StringBuffer query = new StringBuffer();
        String args[] = new String[3];
        query.append("select loc.loc_id ID,loc.loc_name VALUE from ehfm_locations loc where loc.loc_parnt_id=? and loc.loc_hdr_id=? and loc.active_yn=? order by loc.loc_name asc");
        args[0] = villageId;
        args[1]= "LH9";
        args[2] = "Y";
        hamletList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),args);
        }
        catch(Exception e)
        {
        	GLOGGER.error("Exception occurred in getHamletList() in CommonServiceImpl class."+e.getMessage());
        }
        
        return hamletList;
	}

	
	public List<LabelBean> getCastes() throws Exception {
		
		List<LabelBean> casteList = new ArrayList<LabelBean>();
        try
        {
        StringBuffer query = new StringBuffer();
        String args[] = new String[1];
        query.append("select castes.cmb_dtl_val ID,castes.cmb_dtl_name VALUE from asrim_combo castes where castes.cmb_hdr_id=? order by castes.cmb_dtl_name asc");
        args[0] = "CH6";
        casteList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),args);
        }
        catch(Exception e)
        {
        	GLOGGER.error("Exception occurred in getCastes() in CommonServiceImpl class."+e.getMessage());
        }
        
        return casteList;
	}

	
	public List<LabelBean> getRelations() throws Exception {
		List<LabelBean> relationList = new ArrayList<LabelBean>();
        try
        {
        StringBuffer query = new StringBuffer();
        String args[] = new String[1];
        query.append("select relation.cmb_dtl_val ID,relation.cmb_dtl_name VALUE from asrim_combo relation where relation.cmb_hdr_id=? order by relation.cmb_dtl_name asc");
        args[0] = "CH4";
        relationList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),args);
        }
        catch(Exception e)
        {
        	GLOGGER.error("Exception occurred in getRelations() in CommonServiceImpl class."+e.getMessage());
        }
        
        return relationList;
	}
	
	/**
     * ;
     * @param String locHdrId
     * @param String locParntId
     * @return List of type LabelBean locationList
     * @function This Method is Used For Getting List of Districts/Mandals/Villages/Hamlets
     */
	
	public List<LabelBean> getAsrimLocations(String locHdrId,String locParntId) throws Exception 
	{
		SessionFactory factory=null;
		Session session=null;
		List<LabelBean> locationsList = new ArrayList<LabelBean>();
        Iterator locationsItr=null;
        StringBuffer locationsQuery=null;
    	EhfmLocations ehfmLocations=null;
    	try
    	{
    		factory= hibernateTemplate.getSessionFactory();
    		session=factory.openSession();
    		locationsQuery= new StringBuffer();
    		locationsQuery.append("from EhfmLocations al where al.locHdrId='"+locHdrId+"' and al.id.locParntId='"+locParntId+"' and al.activeYn='Y' order by al.locName asc");
            Query locationslst=session.createQuery(locationsQuery.toString());
            locationsItr=locationslst.iterate();
            while(locationsItr.hasNext())
            {
            	ehfmLocations=(EhfmLocations)locationsItr.next();
            	LabelBean labelBean =new LabelBean();
    			labelBean.setID(ehfmLocations.getId().getLocId());
    			labelBean.setVALUE(ehfmLocations.getLocName());
    			locationsList.add(labelBean);
            }
    	}
    	catch(Exception e)
    	{
    		GLOGGER.error("Exception occurred in getEhfmLocations() in CommonServiceImpl class."+e.getMessage());
    	}
    	finally
    	{
    		session.close();
    		factory.close();
    	}
    	return locationsList;
	}
	/**
     * ;
     * @param String locId
     * @return String locName
     * @function This Method is Used For getting Location Name for given Location Id
     */
	
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
     * @return List of type LabelBean occupationList
     * @function This Method is Used For Getting List of Occupations
     */
	
	public List<LabelBean> getOccupations() throws Exception {
		List<LabelBean> occupationList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select cast(om.occupationId as string) as ID,om.occName as VALUE from EhfmOccupationMst om where om.activeYN='Y' order by om.occName asc");
			occupationList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception occurred in getOccupations() in CommonServiceImpl class."+e.getMessage());
		}
		return occupationList;
	}
	
	/**
     * ;
     * @param String cmbHdrId
     * @return List of type LabelBean comboList
     * @function This Method is Used For Getting List of Castes/Relations
     */
	@SuppressWarnings("unchecked")
	
	public List<LabelBean> getComboDetails(String cmbHdrId)throws Exception
	{
		SessionFactory factory=null;
		Session session=null;
		List<LabelBean> comboList = new ArrayList<LabelBean>();
        Iterator<EhfmCmbDtls> cmbItr=null;
        StringBuffer cmbHdrQuery=null;
        EhfmCmbDtls ehfmCmbDtls=null;
		try
    	{
    		factory= hibernateTemplate.getSessionFactory();
    		session=factory.openSession();
    		cmbHdrQuery= new StringBuffer();
    		cmbHdrQuery.append("from EhfmCmbDtls ac where ac.cmbHdrId='"+cmbHdrId+"' order by ac.cmbDtlName asc");
            Query locationslst=session.createQuery(cmbHdrQuery.toString());
            cmbItr=locationslst.iterate();
            while(cmbItr.hasNext())
            {
            	ehfmCmbDtls=(EhfmCmbDtls)cmbItr.next();
            	LabelBean labelBean =new LabelBean();
    			labelBean.setID(ehfmCmbDtls.getCmbDtlId());
    			labelBean.setVALUE(ehfmCmbDtls.getCmbDtlName());
    			comboList.add(labelBean);
            }
    	}
    	catch(Exception e)
    	{
    		GLOGGER.error("Exception occurred in getComboDetails() in CommonServiceImpl class."+e.getMessage());
    	}
		finally
    	{
    		session.close();
    		factory.close();
    	}
		return comboList;
	}
	/**
     * ;
     * @param String cmbHdrId
     * @param String cmbVal
     * @return String cmbName
     * @function This Method is Used For getting Combo Name
     */
	
	public String getCmbHdrname(String cmbHdrId, String cmbVal)
			throws Exception {
		String cmbName=null;
		try
		{
		 List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
			 criteriaList.add(new GenericDAOQueryCriteria("id.cmbDtlId",cmbVal,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 criteriaList.add(new GenericDAOQueryCriteria("cmbHdrId",cmbHdrId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		     List<EhfmCmbDtls> ehfmCmbDtls = genericDao.findAllByCriteria(EhfmCmbDtls.class, criteriaList);
		for(EhfmCmbDtls ac:ehfmCmbDtls)
		{
			cmbName=ac.getCmbDtlName();
		}
		}
    	catch(Exception e)
    	{
    		GLOGGER.error("Exception occurred in getCmbHdrname() in CommonServiceImpl class."+e.getMessage());
    	}
		return cmbName;
	}
	/**
     * ;
     * @param String distId
     * @return phase renewal
     * @function This Method is Used For Getting current renewal phase for district provided
     */
	
	public int getPhaseId(String distId) throws Exception {
		SessionFactory factory=null;
		Session session=null;
		int renewal=0;
		Iterator phaseItr=null;
        StringBuffer phaseQuery=null;
        try
    	{
    		factory= hibernateTemplate.getSessionFactory();
    		session=factory.openSession();
    		phaseQuery= new StringBuffer();
    		phaseQuery.append("select max(apd.id.renewal) as renewal from AsrimPhaseDuration apd where apd.id.phaseId= (select ads.phaseId from AsrimDistSeq ads where ads.distId='"+distId+"')");
            Query phaselst=session.createQuery(phaseQuery.toString());
            phaseItr=phaselst.iterate();
            if(phaseItr.hasNext())
            {
            renewal=((Long) phaseItr.next()).intValue();
            }
    	}
    	catch(Exception e)
    	{
    		GLOGGER.error("Exception occurred in getPhaseId() in CommonServiceImpl class."+e.getMessage());
    	}
        finally
    	{
    		session.close();
    		factory.close();
    	}
		return renewal;
	}
	

	/**
     * ;
     * @return List of type LabelBean phaseList
     * @function This Method is Used For Getting List of Phases
     */
	
	public List<LabelBean> getPhases() throws Exception {
		SessionFactory factory=null;
		Session session=null;
		List<LabelBean> phaseList = new ArrayList<LabelBean>();
        Iterator phaseItr=null;
        StringBuffer phaseQuery=null;
		try
    	{
    		factory= hibernateTemplate.getSessionFactory();
    		session=factory.openSession();
    		phaseQuery= new StringBuffer();
    		phaseQuery.append("select distinct p.phaseId,p.phaseName from PhaseMaster p");
            Query locationslst=session.createQuery(phaseQuery.toString());
            phaseItr=locationslst.iterate();
            while(phaseItr.hasNext())
            {
            	Object[] phase=(Object[])phaseItr.next();
            	LabelBean labelBean =new LabelBean();
    			labelBean.setID(phase[0].toString());
    			labelBean.setVALUE(phase[1].toString());
    			phaseList.add(labelBean);
            }
    	}
		catch(Exception e)
    	{
			GLOGGER.error("Exception occurred in getPhases() in CommonServiceImpl class."+e.getMessage());
    	}
		finally
    	{
    		session.close();
    		factory.close();
    	}
		return phaseList;
	}
	
	
	/*public List<LabelBean> getGoDetails(String goType) {
		List<LabelBean> lGoData=new ArrayList<LabelBean>();
		List<EhfmGoMst> lGoList=null;
		try
		{List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>() ;
		criteriaList.add(new GenericDAOQueryCriteria("id.goType",goType,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("id.goNum",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
			lGoList=genericDao.findAllByCriteria(EhfmGoMst.class, criteriaList);
			for(EhfmGoMst efmGoMst:lGoList)
			{
				LabelBean labelBean=new LabelBean();
				labelBean.setID(efmGoMst.getId().getGoNum());
				labelBean.setVALUE(efmGoMst.getGoTitle());
				lGoData.add(labelBean);
				
			}
			
	}
	catch(Exception e)
	{
		GLOGGER.error("Exception occurred in getGoDetails() in CommonServiceImpl class."+e.getMessage());
	}
		return lGoData;
	}
	*/
	
	public List<LabelBean> getHospitals() throws Exception {
		List<LabelBean> hospitalList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct AH.hospId as ID,(AH.hospName||'--'||AH.hospId) as VALUE,AH.hospName as HOSPNAME from EhfmHospitals AH where AH.taashaHosp is null order by AH.hospName asc");
			//where AH.taashaHosp is null 
			hospitalList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception occurred in getHospitals() in CommonServiceImpl class."+e.getMessage());
		}
		return hospitalList;
	}

	/**
     * ;
     * @param String parntCode
     * @return List<LabelBean> personalHistoryList
     * @function This Method is Used For getting Personal History List
     */
	
	public List<LabelBean> getPersonalHistory(String parntCode) throws Exception {
		List<LabelBean> historyList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select eph.code as ID,eph.description as VALUE from EhfmPersonalHistoryMst eph where eph.parntCode='"+parntCode+"' and eph.activeYN='Y'");
			historyList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//GLOGGER.error("Exception occurred in getPersonalHistory() in CommonServiceImpl class."+e.getMessage());
		}
		return historyList;
	}
	/**
     * ;
     * @param String parntCode
     * @return List<LabelBean> personalHistoryList
     * @function This Method is Used For getting Personal History List
     */
	@Override
	public List<LabelBean> getDentalPersonalHistory(String parntCode) {
		List<LabelBean> historyList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select eph.code as ID,eph.description as VALUE from EhfmPersonalHistoryMst eph where eph.parntCode='"+parntCode+"' and eph.activeYN='Y' and eph.code not in ('PR1','PR3') ");
			historyList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//GLOGGER.error("Exception occurred in getPersonalHistory() in CommonServiceImpl class."+e.getMessage());
		}
		return historyList;
	}
	
	/**
     * ;
     * @return List<LabelBean> familyHistoryList
     * @function This Method is Used For getting Family History List
     */
	
	public List<LabelBean> getFamilyHistory() throws Exception {
		List<LabelBean> historyList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select efh.code as ID,efh.description as VALUE from EhfmFamilyHistoryMst efh where efh.activeYN='Y'");
			historyList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//GLOGGER.error("Exception occurred in getFamilyHistory() in CommonServiceImpl class."+e.getMessage());
		}
		return historyList;
	}
	/**
     * ;
     * @return List<LabelBean> investigationsList
     * @function This Method is Used For getting Investigations List(Book Of Trust)
     */
	
	public List<LabelBean> getInvestigations() throws Exception {
		List<LabelBean> investigationsList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select eim.invCode as ID,eim.invName as VALUE from EhfmGenInvestigationsMst eim where eim.activeYN='Y' order by eim.invName asc");
			investigationsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//GLOGGER.error("Exception occurred in getInvestigations() in CommonServiceImpl class."+e.getMessage());
		}
		return investigationsList;
	}
	/**
     * ;
     * @return List<LabelBean> drugsList
     * @function This Method is Used For getting Drugs List(Book Of Trust)
     */
	
	public List<LabelBean> getDrugs() throws Exception {
		List<LabelBean> drugsList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.id.drugTypeCode as ID,edm.drugTypeName as VALUE from EhfmDrugsMst edm where edm.activeYN='Y' order by edm.drugTypeName asc");
			drugsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//GLOGGER.error("Exception occurred in getDrugs() in CommonServiceImpl class."+e.getMessage());
		}
		return drugsList;
	}
	
	
	public List<LabelBean> getOpDrugsAuto() throws Exception {
		List<LabelBean> drugsList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.chemicalCode as ID,edm.chemicalName as VALUE from EhfmOpDrugMst edm where edm.activeYN='Y' order by edm.chemicalName asc");
			drugsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//GLOGGER.error("Exception occurred in getDrugs() in CommonServiceImpl class."+e.getMessage());
		}
		return drugsList;
	}
	
	
	public List<LabelBean> getAsriDrugs(String drugSubTypeId) throws Exception {
		List<LabelBean> drugsList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.id.drugCode as  ID , edm.drugName ||' ( '|| edm.id.drugCode || ' )'  as VALUE from EhfmDrugsMst edm where edm.id.drugSubTypeCode='"+drugSubTypeId+"' and edm.activeYN='Y' order by edm.drugName ||' ( '|| edm.id.drugCode || ' )' asc");
			drugsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			GLOGGER.error("Exception occurred in getDrugs() in CommonServiceImpl class."+e.getMessage());
		}
		return drugsList;
	}
	/**
     * ;
     * @return List<LabelBean> examinFndsList
     * @function This Method is Used For getting General Examination Findings
     */
	
	public List<LabelBean> getGenExaminFndgs(String schemeId) throws Exception {
		List<LabelBean> examinFndsList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select efm.id.code as ID,efm.description as VALUE,efm.subFieldtype as LVL from EhfmGeneralExaminFndsMst efm where efm.activeYn='Y' and efm.id.schemeId='"+schemeId+"'  order by to_number(substr(efm.id.code,'3'))");
			examinFndsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//GLOGGER.error("Exception occurred in getGenExaminFndgs() in CommonServiceImpl class."+e.getMessage());
		}
		return examinFndsList;
	}
	@Override
	public List<LabelBean> getDentalGenExaminFndgs(String schemeId) {
		List<LabelBean> examinFndsList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select efm.id.code as ID,efm.description as VALUE,efm.subFieldtype as LVL from EhfmGeneralExaminFndsMst efm where efm.activeYn='Y'" );
					query.append("and efm.id.code in ('GE11','GE12','GE13','GE14') and efm.id.schemeId='"+schemeId+"'  order by to_number(substr(efm.id.code,'3'))");
			examinFndsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//GLOGGER.error("Exception occurred in getGenExaminFndgs() in CommonServiceImpl class."+e.getMessage());
		}
		return examinFndsList;
	}
	
	public List<LabelBean> getPastIllnessHitory() throws Exception {
		List<LabelBean> pastIllnessList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select ephm.diseaseCode as ID,ephm.diseaseName as VALUE from EhfmPastHistoryMst ephm where ephm.activeYN='Y'");
			pastIllnessList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//GLOGGER.error("Exception occurred in getPastIllnessHitory() in CommonServiceImpl class."+e.getMessage());
		}
		return pastIllnessList;
	}
	
	public String getPastIllnessHitoryNames(String illnessHistory) 
	{
		this.illnessHistory=illnessHistory;
		String illnessHistoryLocal;
		String illnessHistoryFinal="";
		EhfmPastHistoryMst ehfmPastHistoryMst=new EhfmPastHistoryMst();
		String arr[]=illnessHistory.split("~");
		try
		{
				for(int i=0;i<arr.length;i++)
					{
						ehfmPastHistoryMst=genericDao.findById(EhfmPastHistoryMst.class,String.class,arr[i]);
		    			illnessHistoryLocal=ehfmPastHistoryMst.getDiseaseName();
		    			if(i==0)
		    				{	illnessHistoryFinal=illnessHistoryLocal;	}
		    			else
		    				{	illnessHistoryFinal=illnessHistoryFinal+","+illnessHistoryLocal;  }
					}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//GLOGGER.error("Exception occurred in getPastIllnessHitoryNames() in CommonServiceImpl class."+e.getMessage());
		}
		
		return illnessHistoryFinal;
	}
	
	public String getFamilyHisNames(String familyHis) 
	{
		this.familyHis=familyHis;
		String familyHisLocal;
		String familyHisFinal="";
		EhfmFamilyHistoryMst ehfmFamilyHistoryMst=new EhfmFamilyHistoryMst();
		String arr[]=familyHis.split("~");
		try
		{
				for(int i=0;i<arr.length;i++)
					{
					ehfmFamilyHistoryMst=genericDao.findById(EhfmFamilyHistoryMst.class,String.class,arr[i]);
						familyHisLocal=ehfmFamilyHistoryMst.getDescription();
		    			if(i==0)
		    				{	familyHisFinal=familyHisLocal;	}
		    			else
		    				{	familyHisFinal=familyHisFinal+","+familyHisLocal;  }
					}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//GLOGGER.error("Exception occurred in getFamilyHisNames() in CommonServiceImpl class."+e.getMessage());
		}
		
		return familyHisFinal;
	}
	
	
	public List<LabelBean> getAllDiagnosisCat() throws Exception {
		List<LabelBean> diaCatList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct AH.diaCatId as ID,AH.diaCatName as VALUE from EhfmDiagnosisCatMst AH where AH.activeYN='Y' order by AH.diaCatName");
			diaCatList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception occurred in getAllDiagnosisCat() in CommonServiceImpl class."+e.getMessage());
		}
		return diaCatList;
	}
	
	public List<LabelBean> getDiagnosisSubCat(String lStrDiagCat) {
		List<LabelBean> diaSubCatList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct AH.diaSubCatId as ID,AH.diaSubCatName as VALUE from EhfmDiagnosisSubCatMst AH where AH.activeYN='Y' and AH.diaCatId='"+lStrDiagCat+"' order by AH.diaSubCatName");
			diaSubCatList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception occurred in getDiagnosisSubCat() in CommonServiceImpl class."+e.getMessage());
		}
		return diaSubCatList;
	}
	@Override
	public List<LabelBean> getMainSymptonLst() {
		List<LabelBean> mainSymptomList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct AH.id.mainSymptomCode as ID,AH.mainSymptomName as VALUE from EhfmSystematicExamFnd AH where AH.activeYn='Y'  order by AH.mainSymptomName asc");
			mainSymptomList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception occurred in getMainSymptonLst() in CommonServiceImpl class."+e.getMessage());
		}
		return mainSymptomList;
	}
	@Override
	public List<LabelBean> getDentalMainSymptonLst() {
		List<LabelBean> mainSymptomList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct AH.id.mainSymptomCode as ID,AH.mainSymptomName as VALUE from EhfmSystematicExamFnd AH where AH.activeYn='Y'");
			query.append(" and AH.id.mainSymptomCode in ('K00-K13')");
			query.append(" order by AH.mainSymptomName asc");
			mainSymptomList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception occurred in getMainSymptonLst() in CommonServiceImpl class."+e.getMessage());
		}
		return mainSymptomList;
	}
	
	public List<LabelBean> getAsriCategoryList(String hospId,String schemeId) {
		List<LabelBean> asriCatList=new ArrayList<LabelBean>(); 
		String scheme=schemeId;
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			//query.append("select distinct cm.id.asriCatCode as ID , am.disMainName||' ( '|| cm.id.asriCatCode || ' )'  as VALUE from EhfmTherapyCatMst cm,EhfmSpecialities am where am.disMainId=cm.id.asriCatCode and cm.activeYN='Y'  ");
			query.append("select distinct adm.disMainId as ID,adm.disMainName||' ( '|| adm.disMainId || ' )' as VALUE from EhfmHospCatMst ehc,EhfmSpecialities adm where ehc.id.hospId='"+hospId+"' and ehc.isActvFlg='Y' and ehc.id.catId=adm.disMainId and ehc.id.schemeId='"+schemeId+"' order by adm.disMainName||' ( '|| adm.disMainId || ' )' asc");
			asriCatList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception occurred in getAsriCategoryList() in CommonServiceImpl class."+e.getMessage());
		}
		return asriCatList;
	}
	
	public List<String> getICDCategoryList(String asriCatCode) {
		List<LabelBean> icdCatList1 = new ArrayList<LabelBean>();
		List<String> icdCatList=new ArrayList<String>(); 
		Iterator catItr=null;
		EhfmTherapyCatMst ehfmTherapyCatMst=null;
		EhfmTherapyProcMst ehfmTherapyProcMst=null;
		try
		{
			/*List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
    		criteriaList.add(new GenericDAOQueryCriteria("activeYN","Y",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
         	criteriaList.add(new GenericDAOQueryCriteria("id.asriCatCode",asriCatCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("icdCatName",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
		    List<EhfmTherapyCatMst> ehfmTherapyCatList = genericDao.findAllByCriteria(EhfmTherapyCatMst.class, criteriaList);
		    catItr=ehfmTherapyCatList.iterator();
		    while(catItr.hasNext())
    		{
		    	ehfmTherapyCatMst=(EhfmTherapyCatMst)catItr.next();    	
		    	icdCatList.add(ehfmTherapyCatMst.getId().getIcdCatCode()+"~"+ehfmTherapyCatMst.getIcdCatName()+"("+ehfmTherapyCatMst.getId().getIcdCatCode()+")@");
    		}*/
    		StringBuffer query = new StringBuffer();
			query.append("select distinct cm.id.icdCatCode as ID ,cm.icdCatName  as VALUE from EhfmTherapyCatMst cm , EhfmTherapyProcMst etp where cm.id.asriCatCode='"+asriCatCode+"' and cm.activeYN='Y' ");
			query.append(" and cm.id.asriCatCode=etp.id.asriCode and  cm.id.icdCatCode=etp.icdCatCode and etp.id.state='CD202' ");
			icdCatList1=genericDao.executeHQLQueryList (LabelBean.class,query.toString());
			
			for(int i=0; i<icdCatList1.size();i++)
			{
			   icdCatList.add(icdCatList1.get(i).getID()+"~"+icdCatList1.get(i).getVALUE()+"("+icdCatList1.get(i).getID()+")@");
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			GLOGGER.error("Exception occurred in getICDCategoryList() in CommonServiceImpl class."+e.getMessage());
		}
		return icdCatList;
	}
	
	public List<LabelBean> getDynamicWrkFlowDtls(String pCurrStatus,
			String pCurrRole,String mainModule,String subModule) {
		List<LabelBean> buttonList = new ArrayList<LabelBean>();
        StringBuffer query=null;
    	try
    	{
    		query= new StringBuffer();
    		query.append("select ew.buttonName as ID ,ac.cmbDtlName as VALUE from EhfmWorkflow ew,EhfmCmbDtls ac where ew.buttonName=ac.cmbDtlId and ew.currStatus='"+ pCurrStatus +"' and ew.currRole='"+ pCurrRole +"'" );
    		query.append(" and ew.mainModule='"+ mainModule +"' and ew.subModule='"+ subModule +"' and ew.activeYn ='Y' order by ac.cmbDtlName");
    		buttonList = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
    	}
    	catch(Exception e)
    	{
    		GLOGGER.error("Exception occurred in getDynamicWrkFlowDtls() in CommonServiceImpl class."+e.getMessage());
    	}
    	finally
    	{
    	}
    	return buttonList;
	}
	/**
	 * get list of asri categories mapped with userRoles
	 */
	
	public List<LabelBean> getCategoryList(String userId, String userRole)
	{
		List<LabelBean> lstCat = new ArrayList<LabelBean>();
		try
		{
		StringBuffer query = new StringBuffer();
		if(userId!=null && !"".equalsIgnoreCase(userRole) && userRole != null && !"".equalsIgnoreCase(userRole) && config.getString("preauth_medco_role").equalsIgnoreCase(userRole))
		{
			query.append(" select distinct cm.id.asriCatCode as ID  , am.disMainName||' ( '|| cm.id.asriCatCode || ' )'  as VALUE ,am.disMainName as UNITID ");
			query.append(" from EhfmTherapyCatMst cm , EhfmSpecialities am, EhfmMedcoDtls md , EhfmHospCatMst hc " );
			query.append(" where md.id.medcoId ='"+userId+"' and md.id.hospId = hc.id.hospId    and  cm.activeYN ='Y'  ");
			query.append(" and hc.id.catId = am.disMainId  and hc.isActvFlg ='Y'  ");
			query.append(" and cm.id.asriCatCode = am.disMainId order by am.disMainName asc ");
		}
		if(userId!=null && !"".equalsIgnoreCase(userRole) && userRole != null && !"".equalsIgnoreCase(userRole) && config.getString("preauth_mithra_role").equalsIgnoreCase(userRole))
		{
			query.append(" select distinct cm.id.asriCatCode as ID  , am.disMainName||' ( '|| cm.id.asriCatCode || ' )'  as VALUE ,am.disMainName as UNITID ");
			query.append(" from EhfmTherapyCatMst cm , EhfmSpecialities am, EhfmHospMithraDtls md , EhfmHospCatMst hc " );
			query.append(" where md.id.mithraId ='"+userId+"' and md.id.hospId = hc.id.hospId    and  cm.activeYN ='Y'  ");
			query.append(" and hc.id.catId = am.disMainId  and hc.isActvFlg ='Y'  ");
			query.append(" and cm.id.asriCatCode = am.disMainId order by am.disMainName asc ");
		}
		else
		{
			query.append(" select distinct cm.id.asriCatCode as ID  , am.disMainName||' ( '|| cm.id.asriCatCode || ' )'  as VALUE ,am.disMainName as UNITID ");
			query.append(" from EhfmTherapyCatMst cm , EhfmSpecialities am " );
			query.append(" where cm.activeYN ='Y'  ");
			query.append(" and cm.id.asriCatCode = am.disMainId order by am.disMainName asc ");
		}	
		lstCat = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		return lstCat;
	}
	
	public List<LabelBean> getCategorys(String pStrCatId,String userId)
	{
		List<LabelBean> lstCat = new ArrayList<LabelBean>();
		try
		{
		StringBuffer query = new StringBuffer();
		/**
		 * get list of asri categories mapped with asrim diseases and medco with hospital mapping
		 */
		
		if(pStrCatId == null || pStrCatId.equalsIgnoreCase(""))
		{
			query.append(" select distinct cm.id.asriCatCode as ID  , am.disMainName||' ( '|| cm.id.asriCatCode || ' )'  as VALUE ,am.disMainName as UNITID ");
			query.append(" from EhfmTherapyCatMst cm , EhfmSpecialities am, EhfmMedcoDtls md , EhfmHospCatMst hc " );
			query.append(" where md.id.medcoId ='"+userId+"' and md.id.hospId = hc.id.hospId    and  cm.activeYN ='Y'  ");
			query.append(" and hc.id.catId = am.disMainId  and hc.isActvFlg ='Y'  ");
			query.append(" and cm.id.asriCatCode = am.disMainId order by am.disMainName asc ");
		}
		
		if(pStrCatId != null && !pStrCatId.equalsIgnoreCase(""))
		{
			query = new StringBuffer();
			query.append(" select  distinct  cm.id.icdCatCode as ICDCODE , cm.icdCatName||' ( '||cm.id.icdCatCode ||' )'  as ICDNAME , cm.icdCatName as UNITID ");
			query.append(" from EhfmTherapyCatMst cm where cm.activeYN ='Y'");	
			query.append(" and  cm.id.asriCatCode = '"+pStrCatId+"' order by cm.icdCatName asc	");
		}
		lstCat = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		return lstCat;
	}
	
	public List<LabelBean> getICDCategoryList(String pStrCatId,String userId,String hosType)
	{
		List<LabelBean> lstCat = new ArrayList<LabelBean>();
		try
		{
		StringBuffer query = new StringBuffer();
		/**
		 * get list of asri categories mapped with asrim diseases and medco with hospital mapping
		 */
		
		if(pStrCatId == null || pStrCatId.equalsIgnoreCase(""))
		{
			query.append(" select distinct cm.id.asriCatCode as ID  , am.disMainName||' ( '|| cm.id.asriCatCode || ' )'  as VALUE ,am.disMainName as UNITID ");
			query.append(" from EhfmTherapyCatMst cm , EhfmSpecialities am, EhfmMedcoDtls md , EhfmHospCatMst hc " );
			query.append(" where md.id.medcoId ='"+userId+"' and md.id.hospId = hc.id.hospId    and  cm.activeYN ='Y'  ");
			query.append(" and hc.id.catId = am.disMainId  and hc.isActvFlg ='Y'  ");
			query.append(" and cm.id.asriCatCode = am.disMainId order by am.disMainName asc ");
		}
		
		if(pStrCatId != null && !pStrCatId.equalsIgnoreCase("")  && hosType.equals("DC"))
		{
			query = new StringBuffer();
			query.append("select  distinct  cm.id.icdCatCode as ICDCODE , cm.icdCatName||' ( '||cm.id.icdCatCode ||' )'  as ICDNAME , cm.icdCatName as UNITID ");
			query.append("FROM  EhfmTherapyCatMst cm, EhfmTherapyProcMst m where cm.id.icdCatCode=m.icdCatCode and  cm.id.asriCatCode = '"+pStrCatId+"' and cm.activeYN ='Y' and m.id.state='CD202' and m.icdCatCode not in ");
            query.append("(SELECT distinct mm.icdCatCode FROM EhfmTherapyProcMst mm  where mm.id.asriCode='S18' and mm.id.process='IP' and mm.id.state='CD202' and mm.activeYN='Y' and mm.icdCatCode not in ( SELECT distinct mt.icdCatCode FROM EhfmTherapyProcMst mt where mt.id.asriCode='S18' and mt.id.process='DOP' and mt.id.state='CD202' and mt.activeYN='Y' group by mt.icdCatCode))");
            query.append("order by cm.id.icdCatCode asc");
 
		}
		if(pStrCatId != null && !pStrCatId.equalsIgnoreCase("")  && hosType.equals("HOSPITAL"))
		{
			query = new StringBuffer();
			query.append(" select  distinct  cm.id.icdCatCode as ICDCODE , cm.icdCatName||' ( '||cm.id.icdCatCode ||' )'  as ICDNAME , cm.icdCatName as UNITID ");
			query.append(" from EhfmTherapyCatMst cm where cm.activeYN ='Y'");	
			query.append(" and  cm.id.asriCatCode = '"+pStrCatId+"' order by cm.icdCatName asc	");
		}
		
		
		lstCat = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		return lstCat;
	}
	public List<LabelBean> getProcedures(String pStrIcdCatCode , String pStrAsriCode ,String pStrHospId,String Scheme,String dentalEnhFlag)
	{
		List<LabelBean> lstCat = new ArrayList<LabelBean>();
		try
		{
	    String hospType=null;
	    EhfmHospitals ehfmHospitals=null;
	    if(pStrHospId!=null && !"".equalsIgnoreCase(pStrHospId))
	    	ehfmHospitals = genericDao.findById(EhfmHospitals.class, String.class, pStrHospId);
		if(ehfmHospitals!= null )
		{
			hospType = 	ehfmHospitals.getHospType()+"";
		}
		
	    StringBuffer query = new StringBuffer();
		query.append(" select distinct pm.id.icdProcCode as ICDCODE , pm.procName||' ( '||pm.id.icdProcCode ||' )'  as ICDNAME , pm.procName as UNITID ");
		query.append(" from EhfmTherapyProcMst pm ");
		
			query.append(" where pm.activeYN ='Y' and pm.id.process in ('IP','DOP') ");	
		
		if(pStrIcdCatCode != null && !pStrIcdCatCode.equalsIgnoreCase(""))
		{
			query.append(" and  pm.icdCatCode = '"+pStrIcdCatCode+"'	");
		}
		if(pStrAsriCode != null && !pStrAsriCode.equalsIgnoreCase(""))
		{
			query.append(" and  pm.id.asriCode = '"+pStrAsriCode+"'	");
		}
		if(hospType != null && !"".equalsIgnoreCase(hospType) && hospType.equalsIgnoreCase("C"))
			query.append(" and (	pm.govOrCorp  not in ('G') OR   pm.govOrCorp IS NULL)");
		query.append(" and pm.id.state='"+Scheme+"' ");
		if(dentalEnhFlag!=null && !("").equalsIgnoreCase(dentalEnhFlag) && ("Y").equalsIgnoreCase(dentalEnhFlag))
			query.append(" and pm.dentalEnhFlag='"+dentalEnhFlag+"' ");
		query.append(" order by pm.procName asc ");
		
		lstCat = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		return lstCat;
	}
	
	public List<LabelBean> getICDProcedures(String pStrIcdCatCode , String pStrAsriCode ,String pStrHospId,String Scheme,String dentalEnhFlag)
	{
		List<LabelBean> lstCat = new ArrayList<LabelBean>();
		try
		{
	    String hospType=null;
	    EhfmHospitals ehfmHospitals=null;
	    if(pStrHospId!=null && !"".equalsIgnoreCase(pStrHospId))
	    	ehfmHospitals = genericDao.findById(EhfmHospitals.class, String.class, pStrHospId);
		if(ehfmHospitals!= null )
		{
			hospType = 	ehfmHospitals.getHospType()+"";
		}
		
	    StringBuffer query = new StringBuffer();
		query.append(" select distinct pm.id.icdProcCode as ICDCODE , pm.procName||' ( '||pm.id.icdProcCode ||' )'  as ICDNAME , pm.procName as UNITID ");
		query.append(" from EhfmTherapyProcMst pm ");
		
			query.append(" where pm.activeYN ='Y' and pm.id.process ='DOP' ");	
		
		if(pStrIcdCatCode != null && !pStrIcdCatCode.equalsIgnoreCase(""))
		{
			query.append(" and  pm.icdCatCode = '"+pStrIcdCatCode+"'	");
		}
		if(pStrAsriCode != null && !pStrAsriCode.equalsIgnoreCase(""))
		{
			query.append(" and  pm.id.asriCode = '"+pStrAsriCode+"'	");
		}
		if(hospType != null && !"".equalsIgnoreCase(hospType) && hospType.equalsIgnoreCase("C"))
			query.append(" and (	pm.govOrCorp  not in ('G') OR   pm.govOrCorp IS NULL)");
		query.append(" and pm.id.state='"+Scheme+"' ");
		if(dentalEnhFlag!=null && !("").equalsIgnoreCase(dentalEnhFlag) && ("Y").equalsIgnoreCase(dentalEnhFlag))
			query.append(" and pm.dentalEnhFlag='"+dentalEnhFlag+"' ");
		query.append(" order by pm.procName asc ");
		
		lstCat = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		return lstCat;
	}
	
	/**
     * ;
     * @param PatientSmsVO patientSmsVO
     * @return String Msg
     * @function This method is used for sending sms to the specified patient
     */
	
	public String sendPatientSms(PatientSmsVO patientSmsVO) {
		String lStrResultMsg=null;
		String templateId="";
		try
		{
			//SendSMS sendSms =new SendSMS();
			//lStrResultMsg=sendSms.sendSMS(patientSmsVO.getSmsText(),patientSmsVO.getPhoneNo());
			//GLOGGER.info("Sms Result Msg :"+lStrResultMsg);
			if("Y".equalsIgnoreCase(config.getString("AsriSms"))){
				Map<String,Object> lMap = new HashMap<String,Object>();
				lMap.put("msg", patientSmsVO.getSmsText());
				lMap.put("mobile",patientSmsVO.getPhoneNo());
				lMap.put("templateId",templateId);
				lStrResultMsg = this.saveToAsritSms(lMap);
		  }
			EhfPatientSmsData ehfPatientSmsData=new EhfPatientSmsData();
			//ehfPatientSmsData.setSerialNo(patientSmsVO.getSerialNo());
			ehfPatientSmsData.setPhoneNo(patientSmsVO.getPhoneNo());
			ehfPatientSmsData.setSmsText(patientSmsVO.getSmsText());
			ehfPatientSmsData.setEmpCode(patientSmsVO.getEmpCode());
			ehfPatientSmsData.setEmpName(patientSmsVO.getEmpName());
			ehfPatientSmsData.setCrtUsr(patientSmsVO.getCrtUsr());
			ehfPatientSmsData.setCrtDt(patientSmsVO.getCrtDt());
			ehfPatientSmsData.setSmsAction(patientSmsVO.getSmsAction());
			ehfPatientSmsData.setPatientId(patientSmsVO.getPatientId());
			genericDao.save(ehfPatientSmsData);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//GLOGGER.error("Exception occurred in sendPatientSms() in CommonServiceImpl class."+e.getMessage());
		}
		return lStrResultMsg;
	}
	/**
     * ;
     * @param EmailVO emailVO
     * @param Map model
     * @function This method is used for sending Email to the specified patient
     */
	
	public void generateMail(EmailVO emailVO, Map model) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(emailVO.getFromEmail());
			mailMessage.setBcc(emailVO.getCcBcc());
			mailMessage.setCc(emailVO.getCcEmail());
			mailMessage.setSubject(emailVO.getSubject());
			mailMessage.setTo(emailVO.getToEmail());
			mailMessage
					.setSentDate(new java.sql.Timestamp(new Date().getTime()));

			emailNotifier.sendImageMessage(mailMessage, emailVO.getTemplateName(),
					model, emailVO.getTemplateSource());
		} catch (Exception e) {
			//GLOGGER.error("Exception occurred in generateMail() in CommonServiceImpl class."+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * @function This method is used for sending Email to the specified receipent without any images
	 * @param emailVO
	 * @param model
	 */
	public void generateNonImageMail(EmailVO emailVO,Map model)
		{
			try{
					SimpleMailMessage mailMessage=new SimpleMailMessage();
					mailMessage.setFrom(emailVO.getFromEmail());
					mailMessage.setBcc(emailVO.getCcBcc());
					mailMessage.setCc(emailVO.getCcEmail());
					mailMessage.setSubject(emailVO.getSubject());
					mailMessage.setTo(emailVO.getToEmail());
					mailMessage
							.setSentDate(new java.sql.Timestamp(new Date().getTime()));
					
					emailNotifier.sendEHSMessage(mailMessage, emailVO.getTemplateName(),
					model, emailVO.getTemplateSource());
				}
			catch (Exception e) {
				//GLOGGER.error("Exception occurred in generateNonImageMail() in CommonServiceImpl class."+e.getMessage());
				e.printStackTrace();
			}
		}
	/**
     * ;
     * @return String sno
     * @function This method is used for getting primary key(sno) max count value for sequence id
     */
	
	public Long getCaseTherapyInvestId() {
		Long sno=0L;
		SessionFactory factory=null;
		Session session=null;
		Iterator caseInvestItr=null;
        StringBuffer caseInvestQuery=null;
        try
    	{
    		factory= hibernateTemplate.getSessionFactory();
    		session=factory.openSession();
    		caseInvestQuery= new StringBuffer();
    		caseInvestQuery.append("select max(cti.sno) as sno from EhfCaseTherapyInvest cti");
            Query caseInvestlst=session.createQuery(caseInvestQuery.toString());
            caseInvestItr=caseInvestlst.iterate();
            if(caseInvestItr.hasNext())
            {
            sno=((Long) caseInvestItr.next()).longValue();
            }
    	}
    	catch(Exception e)
    	{
    		GLOGGER.error("Exception occurred in getPhaseId() in CommonServiceImpl class."+e.getMessage());
    	}
        finally
    	{
    		session.close();
    		factory.close();
    	}
		return sno;
	}
	
	public void sendEmailMessage(){
		System.out.println("in Common service for sending Email");
		EmailVO emailVO = new EmailVO();
		Map<String, String> model = new HashMap<String, String>();
		
        StringBuffer patientQuery=null;
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(config.getString("emailFrom"));
			mailMessage.setSubject("Wish You A Happy Independence Day");			
			
    		patientQuery= new StringBuffer();
    		patientQuery.append(" select distinct a.firstName as EMPNAME,a.lastName as SUBNAME,a.email as ID from AsrimUsers a where a.activeYn='Y' and a.email is not null ");
    		List<LabelBean> asrimusrList = genericDao.executeHQLQueryList(LabelBean.class, patientQuery.toString());
    		for(LabelBean labelBean : asrimusrList){
    			String[] emailToArray = {labelBean.getID()};
    			if(labelBean.getSUBNAME()!=null && !labelBean.getSUBNAME().equalsIgnoreCase(""))
    			model.put("patientName", labelBean.getEMPNAME()+" "+labelBean.getSUBNAME());
    			else
    			model.put("patientName", labelBean.getEMPNAME());
    			    			
    			mailMessage.setTo(emailToArray);
    			mailMessage.setSentDate(new java.sql.Timestamp(new Date().getTime()));

    			//emailNotifier.sendImageIndMessage(mailMessage, config.getString("EhfMessageTemplateName"),
    			//		model, EmailConstants.TEMPLATE_FROM_FA);
    		}
    		patientQuery= new StringBuffer();
    		patientQuery.append(" select distinct a.firstName as EMPNAME,a.lastName as SUBNAME,a.middleName as CONST ,a.emailId as ID from EhfmUsers a where a.serviceFlag='Y' and a.emailId is not null ");
    		List<LabelBean> ehfmusrList = genericDao.executeHQLQueryList(LabelBean.class, patientQuery.toString());
    		for(LabelBean labelBean : ehfmusrList){
    			String[] emailToArray = {labelBean.getID()};
    			if(labelBean.getSUBNAME()!=null && !labelBean.getSUBNAME().equalsIgnoreCase("") && labelBean.getCONST()!=null && !labelBean.getCONST().equalsIgnoreCase(""))
    			    model.put("patientName", labelBean.getEMPNAME()+" "+labelBean.getCONST()+" "+labelBean.getSUBNAME());
    			else if(labelBean.getSUBNAME()==null && labelBean.getCONST()!=null && !labelBean.getCONST().equalsIgnoreCase(""))
    				model.put("patientName", labelBean.getEMPNAME()+" "+labelBean.getCONST());
    			else if(labelBean.getCONST()==null && labelBean.getSUBNAME()!=null && !labelBean.getSUBNAME().equalsIgnoreCase(""))
    				model.put("patientName", labelBean.getEMPNAME()+" "+labelBean.getSUBNAME());
    			else
    				model.put("patientName", labelBean.getEMPNAME());
    			    			
    			mailMessage.setTo(emailToArray);
    			mailMessage.setSentDate(new java.sql.Timestamp(new Date().getTime()));

    			//emailNotifier.sendImageIndMessage(mailMessage, config.getString("EhfMessageTemplateName"),
    			//		model, EmailConstants.TEMPLATE_FROM_FA);
    		}
    		 	
    		String msgEmailTo= config.getString("ehfIndMsgTo");
    		StringTokenizer token = new StringTokenizer(msgEmailTo,"$");
    		while (token.hasMoreElements()) {
    			String mailId=(String) token.nextElement();
    			
        		model.put("patientName", "Team");
    			
    			mailMessage.setTo(mailId);
    			mailMessage.setSentDate(new java.sql.Timestamp(new Date().getTime()));

    			//emailNotifier.sendImageIndMessage(mailMessage, config.getString("EhfMessageTemplateName"),
    			//		model, EmailConstants.TEMPLATE_FROM_FA);
    		}
    		
			
		} catch (Exception e) {
			GLOGGER.error("Exception occurred in generateMail() in CommonServiceImpl class."+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void sendNewYearEmailMessage(){
		System.out.println("in Common service for sending Email");
		boolean test=config.getString("EmailTesting").equalsIgnoreCase("Y");
		Map<String, String> model = new HashMap<String, String>();
        StringBuffer patientQuery=null;
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(config.getString("emailFrom"));
			mailMessage.setSubject(config.getString("NewYrSubject"));			
			String othersEmails=config.getString("OtherEmails");
			String[] emailIds=othersEmails.split("~");
    		for(String emailId : emailIds){
    			String[] emailToArray = {emailId};
    			model.put("patientName", emailId);
    			    			
    			mailMessage.setTo(emailToArray);
    			mailMessage.setSentDate(new java.sql.Timestamp(new Date().getTime()));

    			//emailNotifier.sendNewYearMessage(mailMessage, config.getString("EhfMessageTemplateName"),
    			//		model, EmailConstants.TEMPLATE_FROM_FA);
    		}
    		if(test)
    			return;
    		patientQuery= new StringBuffer();
    		patientQuery.append(" select distinct a.firstName as EMPNAME,a.lastName as SUBNAME,a.email as ID from AsrimUsers a where a.activeYn='Y' and a.email is not null ");
    		List<LabelBean> asrimusrList = genericDao.executeHQLQueryList(LabelBean.class, patientQuery.toString());
    		for(LabelBean labelBean : asrimusrList){
    			String[] emailToArray = {labelBean.getID()};
    			if(labelBean.getSUBNAME()!=null && !labelBean.getSUBNAME().equalsIgnoreCase(""))
    			model.put("patientName", labelBean.getEMPNAME()+" "+labelBean.getSUBNAME());
    			else
    			model.put("patientName", labelBean.getEMPNAME());
    			    			
    			mailMessage.setTo(emailToArray);
    			mailMessage.setSentDate(new java.sql.Timestamp(new Date().getTime()));

    			//emailNotifier.sendNewYearMessage(mailMessage, config.getString("EhfMessageTemplateName"),
    			//		model, EmailConstants.TEMPLATE_FROM_FA);
    			return;
    		}
    		/*patientQuery= new StringBuffer();
    		patientQuery.append(" select distinct a.firstName as EMPNAME,a.lastName as SUBNAME,a.middleName as CONST ,a.emailId as ID from EhfmUsers a where a.serviceFlag='Y' and a.emailId is not null ");
    		List<LabelBean> ehfmusrList = genericDao.executeHQLQueryList(LabelBean.class, patientQuery.toString());
    		for(LabelBean labelBean : ehfmusrList){
    			//String[] emailToArray = {labelBean.getID()};
    			if(labelBean.getSUBNAME()!=null && !labelBean.getSUBNAME().equalsIgnoreCase("") && labelBean.getCONST()!=null && !labelBean.getCONST().equalsIgnoreCase(""))
    			    model.put("patientName", labelBean.getEMPNAME()+" "+labelBean.getCONST()+" "+labelBean.getSUBNAME());
    			else if(labelBean.getSUBNAME()==null && labelBean.getCONST()!=null && !labelBean.getCONST().equalsIgnoreCase(""))
    				model.put("patientName", labelBean.getEMPNAME()+" "+labelBean.getCONST());
    			else if(labelBean.getCONST()==null && labelBean.getSUBNAME()!=null && !labelBean.getSUBNAME().equalsIgnoreCase(""))
    				model.put("patientName", labelBean.getEMPNAME()+" "+labelBean.getSUBNAME());
    			else
    				model.put("patientName", labelBean.getEMPNAME());
    			    			
    			mailMessage.setTo(emailToArray);
    			mailMessage.setSentDate(new java.sql.Timestamp(new Date().getTime()));

    			emailNotifier.sendNewYearMessage(mailMessage, config.getString("EHFNewYearMsg"),
    					model, EmailConstants.TEMPLATE_FROM_FA);
    		}
    		 	
    		String msgEmailTo= config.getString("ehfIndMsgTo");
    		StringTokenizer token = new StringTokenizer(msgEmailTo,"$");
    		while (token.hasMoreElements()) {
    			String mailId=(String) token.nextElement();
    			
        		model.put("patientName", "Team");
    			
    			mailMessage.setTo(mailId);
    			mailMessage.setSentDate(new java.sql.Timestamp(new Date().getTime()));

    			emailNotifier.sendNewYearMessage(mailMessage, config.getString("EHFNewYearMsg"),
    					model, EmailConstants.TEMPLATE_FROM_FA);
    		}*/
    		
			
		} catch (Exception e) {
			GLOGGER.error("Exception occurred in generateMail() in CommonServiceImpl class."+e.getMessage());
			e.printStackTrace();
		}
	}
	/**
     * ;
     * @param String userId
     * @param String caseId
     * @param String lockStatus
     * @return int recordsInserted
     * @function This method is used for locking a specific case against a specified user
     */
	
	public int lockCase(String userId, String caseId, String lockStatus) {
		
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		Date crtDt=null;
		EhfCaseLockStatus ehfCaseLockStatus=null;
		try {
			crtDt=sdfw.parse(sdfw.format(new Date()));
		} catch (ParseException e) {
			GLOGGER.error("Exception occurred in lockCase() in CommonServiceImpl class."+e.getMessage());
		}
		Map<String,Object> keyMap=new HashMap<String,Object>();
		keyMap.put("caseId",caseId);
		keyMap.put("userId", userId);
		ehfCaseLockStatus=genericDao.findFirstByPropertyMatch(EhfCaseLockStatus.class, keyMap);
		if(ehfCaseLockStatus!=null)
		{
			ehfCaseLockStatus.setLockStatus(lockStatus);
			ehfCaseLockStatus.setLstUpdDt(crtDt);
			ehfCaseLockStatus.setLstUpdUsr(userId);
			ehfCaseLockStatus = genericDao.save(ehfCaseLockStatus);
		}
		else
		{
			if("Y".equals(lockStatus))
			{
			ehfCaseLockStatus=new EhfCaseLockStatus();
			EhfmSeq ehfmLockSeq=getSeqNextVal("LOCK_SNO");
			int seqId=ehfmLockSeq.getSeqNextVal().intValue();
			ehfmLockSeq.setSeqNextVal(ehfmLockSeq.getSeqNextVal()+1);
			updateSequenceVal(ehfmLockSeq);
			ehfCaseLockStatus.setSno(seqId);
			ehfCaseLockStatus.setUserId(userId);
			ehfCaseLockStatus.setCaseId(caseId);
			ehfCaseLockStatus.setLockStatus(lockStatus);
			ehfCaseLockStatus.setCrtDt(crtDt);
			ehfCaseLockStatus.setCrtUsr(userId);
			ehfCaseLockStatus = genericDao.save(ehfCaseLockStatus);
			}
		}
		if(ehfCaseLockStatus!=null)
		{
			return 1;
		}
		return 0;
	}
	/**
     * ;
     * @param String userId
     * @function This method is used for unlocking cases locked by a specified user
     */
	
	public void unlockCases(String userId) {
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		Date crtDt=null;
		try {
			crtDt=sdfw.parse(sdfw.format(new Date()));
		} catch (ParseException e) {
			GLOGGER.error("Exception occurred in lockCase() in CommonServiceImpl class."+e.getMessage());
		}
		List<EhfCaseLockStatus> lockedCasesList=null;
		Map<String,Object> keyMap=new HashMap<String,Object>();
		keyMap.put("userId", userId);
		keyMap.put("lockStatus","Y");
		lockedCasesList=genericDao.findAllByPropertyMatch(EhfCaseLockStatus.class, keyMap);
		if(lockedCasesList!=null && lockedCasesList.size()>0)
		{
			for(EhfCaseLockStatus ehfCaseLockStatus:lockedCasesList)
			{
				ehfCaseLockStatus.setLockStatus("N");
				ehfCaseLockStatus.setLstUpdDt(crtDt);
				ehfCaseLockStatus.setLstUpdUsr(userId);
				genericDao.save(ehfCaseLockStatus);
			}
		}
	}
	/**
     * ;
     * @param String caseId
     * @function This method is used for getting lock status for a specific case
     */
	
	public String getLockStatus(String caseId) {
		String lockStatus=null;
		List<EhfCaseLockStatus> lockedList=null;
		Map<String,Object> keyMap=new HashMap<String,Object>();
		keyMap.put("caseId", caseId);
		keyMap.put("lockStatus", "Y");
		lockedList=genericDao.findAllByPropertyMatch(EhfCaseLockStatus.class, keyMap);
		if(lockedList!=null && lockedList.size()>0)
		{
			lockStatus="Y";
		}
		else
		{
			lockStatus="N";
		}
		return lockStatus;
	}
	
	
	public List<LabelBean> getInvestBlockName(){
		List<LabelBean> investigationsList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct eim.invMainCode as ID,eim.invMainName as VALUE from EhfmGenInvestigationsMst eim where eim.activeYN='Y' order by eim.invMainName asc");
			investigationsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			GLOGGER.error("Exception occurred in getInvestBlockName() in CommonServiceImpl class."+e.getMessage());
		}
		return investigationsList;
	}
	@Override
	public List<LabelBean> getInvestBlockNameNew(PatientVO patientVO){
		List<LabelBean> investigationsList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		String schemeId=patientVO.getSchemeId();
		String patientScheme=patientVO.getPatientScheme();
		String hospType=patientVO.getHospType();
		String hospId=patientVO.getHospitalCode();
		try
		{
			query = new StringBuffer();
			query.append("select distinct eim.invMainCode as ID,eim.invMainName as VALUE from EhfmGenInvestigationsMst eim where eim.activeYN='Y' ");
			if(hospId!=null && (config.getString("HOSP_NIMS").equalsIgnoreCase(hospId)) )
				query.append(" and eim.invMainCode  like  ('%"+config.getString("GeneralOPInvestId")+"%') ");
			/*if(schemeId!=null && hospType!=null &&     (   (config.getString("AP").equalsIgnoreCase(schemeId)) || !(config.getString("HOSP_NIMS").equalsIgnoreCase(hospId) )       ))*/
			else
				query.append(" and eim.invMainCode not like  ('%"+config.getString("GeneralOPInvestId")+"%') ");
			query.append( " order by eim.invMainName asc");
			investigationsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			GLOGGER.error("Exception occurred in getInvestBlockName() in CommonServiceImpl class."+e.getMessage());
		}
		return investigationsList;
	}
	@Override
	public List<LabelBean> getDentalInvestBlockNameNew(PatientVO patientVO){
		List<LabelBean> investigationsList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		String schemeId=patientVO.getSchemeId();
		String patientScheme=patientVO.getPatientScheme();
		String hospType=patientVO.getHospType();
		String hospId=patientVO.getHospitalCode();
		try
		{
			query = new StringBuffer();
			query.append("select distinct eim.invMainCode as ID,eim.invMainName as VALUE from EhfmGenInvestigationsMst eim where eim.activeYN='Y' ");
//			query.append(" and eim.invMainCode in ('I51','123','I01','I24','I68')");
			//if(hospId!=null && (config.getString("HOSP_NIMS").equalsIgnoreCase(hospId)) )
			//	query.append(" and eim.invMainCode  like  ('%"+config.getString("GeneralOPInvestId")+"%') ");
			/*if(schemeId!=null && hospType!=null &&     (   (config.getString("AP").equalsIgnoreCase(schemeId)) || !(config.getString("HOSP_NIMS").equalsIgnoreCase(hospId) )       ))*/
			//else
				query.append(" and eim.invMainCode not like  ('%"+config.getString("GeneralOPInvestId")+"%') ");
			query.append( " order by eim.invMainName asc");
			investigationsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			GLOGGER.error("Exception occurred in getInvestBlockName() in CommonServiceImpl class."+e.getMessage());
		}
		return investigationsList;
	}
	
	public List<LabelBean> getOpDrugs(String pStrIpOpType) throws Exception {
		List<LabelBean> drugsList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.id.mainGrpCode as ID,edm.mainGrpName as VALUE from ");
			if(pStrIpOpType != null && pStrIpOpType.equalsIgnoreCase("IP"))
				query.append(" EhfmIpDrugMst edm ");
			else
				query.append(" EhfmOpDrugMst edm ");
			query.append(" where edm.activeYN='Y' order by edm.mainGrpName asc");
			drugsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			GLOGGER.error("Exception occurred in getDrugs() in CommonServiceImpl class."+e.getMessage());
		}
		return drugsList;
	}
	
	public List<LabelBean> getRouteType(String pStrIpOpType) throws Exception {
		List<LabelBean> routeList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.routeTypeCode as ID,edm.routeTypeName as VALUE from ");
			if(pStrIpOpType != null && pStrIpOpType.equalsIgnoreCase("IP"))
				query.append(" EhfmIpRouteMst edm ");
			else
				query.append(" EhfmOpRouteMst edm ");
			query.append("   where edm.activeYN='Y' order by edm.routeTypeName asc");
			routeList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//GLOGGER.error("Exception occurred in getDrugs() in CommonServiceImpl class."+e.getMessage());
		}
		return routeList;
	}
	
	public List<LabelBean> getStrengthType(String pStrIpOpType) throws Exception {
		List<LabelBean> strengthList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.strengthUnitCode as ID,edm.strengthUnitName as VALUE from ");
			if(pStrIpOpType != null && pStrIpOpType.equalsIgnoreCase("IP"))
				query.append(" EhfmIpStrengthMst edm ");
			else
				query.append(" EhfmOpStrengthMst edm ");
			query.append("  where edm.activeYN='Y' order by edm.strengthUnitName asc");
			strengthList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//GLOGGER.error("Exception occurred in strengthList() in CommonServiceImpl class."+e.getMessage());
		}
		return strengthList;
	}
	
	public String getSequence(String pStrSeqName) throws Exception {
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
	
	public List<String> getDocListBySpec(String asriCatCode,String hospCode,String scheme) {
		List<LabelBean> docAvailLst = new ArrayList<LabelBean>();	
		List<String> docSpecList=new ArrayList<String>(); 
		try
		{
			StringBuffer query = new StringBuffer();
			query.append(" select z.id.regNum  as ID,z.splstName as  VALUE,z.title as CONST  ");
			query.append(" from EhfmSplstDctrs z , EhfmDotorSplty y where z.isActiveYn ='Y' "); //and z.isConsultant='"+preauthVO.getConsultant()+"'  
			query.append(" and y.id.spctlyCode='"+asriCatCode+"' and z.id.regNum = y.id.regNum and z.id.regNum is not null "); //and z.apprvStatus = 'CD896'
			query.append(" and y.id.hospId = '"+hospCode+"' and z.id.hospId='"+hospCode+"' and z.id.scheme  in ('"+scheme+"','"+config.getString("COMMON")+"') and  z.id.scheme=y.scheme ");
			docAvailLst = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
			for(LabelBean labelBean:docAvailLst)
			{
				docSpecList.add(labelBean.getID()+"~"+labelBean.getCONST()+" "+labelBean.getVALUE()+"@");	
			}
			if("G".equalsIgnoreCase(getHospType(hospCode)))
			{
				docSpecList.add("0~Others@");	
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}		
		return docSpecList;
	}
	
	public String getHospType(String hospId) {
		StringBuffer hqlQuery = new StringBuffer();
		String hospType=null;
  	  	List<LabelBean>  hospList=null;
  	  	hqlQuery.append("select cast(ah.hospType as string) as VALUE from EhfmHospitals ah where  ah.hospId = '"+hospId+"'");
  		hqlQuery.append("  and ah.hospActiveYN='Y'");
  		hospList= genericDao.executeHQLQueryList(LabelBean.class, hqlQuery.toString());
  		if(hospList != null && hospList.size()>0 )
  			{
  				hospType=hospList.get(0).getVALUE();
  			}
  	  	return hospType;
	}
	public String getDutyDoctorById(String regNo) {
		String query="select distinct title||' '||doctorName as ID from EhfmDutyDctrs where id.regNum='"+regNo+"'";
		String user="";
		try
		{
			List<LabelBean> list=genericDao.executeHQLQueryList(LabelBean.class, query);
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
	synchronized public void saveToBuffer(String message,String phoneList)
	{
		
		try
		{
			long val=Long.parseLong(String.valueOf(getSequence("SMS_BUFFER_SEQ")));
			EhfSmsBuffer ehfSmsBuffer=new EhfSmsBuffer();
			ehfSmsBuffer.setSerialNo(val+"");
			ehfSmsBuffer.setPhoneNo(phoneList);
			ehfSmsBuffer.setSmsText(message);
			ehfSmsBuffer.setResentYN("N");
			ehfSmsBuffer.setCrtDt(new Date());
			genericDao.save(ehfSmsBuffer);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
    public String getDecryptedPswd(String pStrLoginName){

		StringBuffer query = new StringBuffer();
		String[] args=new String[1];
		args[0]=pStrLoginName;
		query.append("  select DECRYPT_USER_PSWD(?) as ID from dual  ");
		List<LabelBean> lStPswd=genericDao.executeSQLQueryList(LabelBean.class,query.toString(),args);
		if(lStPswd!=null)	
		return lStPswd.get(0).getID();
		return null;
	}
    
    public EhfmHospitals getHospInfo(String hospId) throws Exception
    {
    	EhfmHospitals ehfmHospitals=genericDao.findById(EhfmHospitals.class,String.class,hospId);
    	return ehfmHospitals;
    }
    public String ckDentalClinic(String userid, String caseId)
	{
		List<LabelBean> hospids;
		String query=" select b.hosp_id as HOSPID from ehfm_medco_dtls a , ehfm_hospitals b ,ehf_empnl_hospinfo c , ehfm_cmb_dtls d ,ehf_case e where a.medco_id='"+userid+ "' and  e.case_id='"+caseId+"'  and a.hosp_id=b.hosp_id and b.hosp_empnl_ref_num=c.hospinfo_id and c.applictn_type=d.cmb_dtl_id and c.applictn_type='CD10001' and e.scheme_id='CD202' ";
		hospids=genericDao.executeSQLQueryList(LabelBean.class, query);
		String hospID = "hospital";
		if(hospids!=null && hospids.size()>0)
	    {		
			hospID=hospids.get(0).getHOSPID(); 
	    }
		return hospID;
	}
    
    public String saveToAsritSms(Map<String,Object> lmap)
    {
    	String msg = "";
    	AsritSms asritsms = new AsritSms();
    	try{
    		asritsms.setSno(99999L);
	    	asritsms.setMessage((String)lmap.get("msg"));
	    	asritsms.setMobile((String)lmap.get("mobile"));
	    	asritsms.setSmsSent("N");
	    	asritsms.setCrtDt(new Timestamp(new Date().getTime()));
	    	asritsms.setCrtUsr("EHFUSERTS");
	    	asritsms.setStateCode("7");
	    	asritsms.setTemplateId((String)lmap.get("templateId"));
	    	asritsms = genericDao.save(asritsms);
	    	msg="success";
    	}
    	catch(Exception e)
    	{
    		msg="failure";
    		//GLOGGER.error("Exception in saveToAsritSms method of CommonServiceImpl "+e.getMessage());
    		e.printStackTrace();
    		
    	}
    	
  return msg;  	
    	    	
    }

	@Override
	public List<LabelBean> getMedicalCatgryList(String splcty) {
		List<LabelBean> medcialList = new ArrayList<LabelBean>();
		StringBuffer query = new StringBuffer();
		try
		{
		    query.append(" select ee.proc_name as VALUE,EE.ICD_CAT_CODE AS ID from  EHFM_MAIN_THERAPY_NABH ee WHERE EE.MEDICAL_SURG='M' AND EE.ACTIVE_YN='Y' and ee.asri_code='"+splcty+"' order by ee.proc_name  ");
		    medcialList = genericDao.executeSQLQueryList(LabelBean.class,query.toString());
		}
	   	catch(Exception e)
	   	{
	   		e.printStackTrace();
	   	}
		return medcialList;
	}
	@Override
	public List<LabelBean> getMedicalDrugList(String cat) {
		List<LabelBean> medcialList = new ArrayList<LabelBean>();
		StringBuffer query = new StringBuffer();
		try
		{
		    query.append(" select proc_name as VALUE,ICD_CAT_CODE AS ID from  EHFM_ONCOLOGY_DRUGS order by proc_name  ");
		    medcialList = genericDao.executeSQLQueryList(LabelBean.class,query.toString());
		}
	   	catch(Exception e)
	   	{
	   		e.printStackTrace();
	   	}
		return medcialList;
	}
	@Override
	public List<LabelBean> getMedicalSpltyList(String hospId, String schemeId)
	{
		List<LabelBean> asriCatList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			//query.append(" select distinct adm.disMainId as ID,adm.disMainName||' ( '|| adm.disMainId || ' )' as VALUE from EhfmSpecialities adm where adm.id.disMainId not in ('DM95','S1','S10','S11','S12','S13','S14','S15','S16','S17','S2','S3','S4','S5','S6','S7','S9','S8','S18','S19')   order by adm.disMainName||' ( '|| adm.disMainId || ' )' asc");
			query.append(" select distinct adm.disMainId as ID,adm.disMainName||' ( '|| adm.disMainId || ' )' as VALUE from EhfmHospCatMst ehc,EhfmSpecialities adm,EhfmMainTherapyNabh em where em.id.asriCode=adm.id.disMainId and em.id.process in ('IPM')  and em.id.state ='"+schemeId+"' and ehc.id.hospId='"+hospId+"' and ehc.isActvFlg='Y' and ehc.id.catId=adm.disMainId and ehc.id.schemeId='"+schemeId+"' order by adm.disMainName||' ( '|| adm.disMainId || ' )' asc ");
			asriCatList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception occurred in getAsriCategoryList() in CommonServiceImpl class."+e.getMessage());
		}
		return asriCatList;
	}
	@Override
	public List<LabelBean> getMedicalCatgryListDflt() {
		List<LabelBean> medcialList = new ArrayList<LabelBean>();
		StringBuffer query = new StringBuffer();
		try
		{
		    query.append(" select ee.proc_name as VALUE,EE.ICD_CAT_CODE AS ID from  EHFM_MAIN_THERAPY_NABH ee WHERE EE.MEDICAL_SURG='M' AND EE.ACTIVE_YN='Y' order by ee.proc_name  ");
		    medcialList = genericDao.executeSQLQueryList(LabelBean.class,query.toString());
		}
	   	catch(Exception e)
	   	{
	   		e.printStackTrace();
	   	}
		return medcialList;
	}
	@Override
	public String getloggedUserHospId(String lStrUserId,String schemeId) {
		String hospId="";
		List<LabelBean> medcialList = new ArrayList<LabelBean>();
		StringBuffer query = new StringBuffer();
		try
		{
		    query.append(" select hosp_id as ID from ehfm_medco_dtls WHERE medco_id='"+lStrUserId+"' and end_dt is null and active_yn='Y' and scheme='"+schemeId+"'  ");
		    medcialList = genericDao.executeSQLQueryList(LabelBean.class,query.toString());
		
				if(medcialList.size()>0)
				{
					if(medcialList != null)
						hospId=medcialList.get(0).getID();
				}
		}
	   	catch(Exception e)
	   	{
	   		e.printStackTrace();
	   	}
		return hospId;
	}
	
	@Override
	public List<LabelBean> getCardDetails(String cardNo) {
		List<LabelBean> cardDetails=new ArrayList<LabelBean>(); 
		StringBuffer query =null,query1=null;
		try
		{
			query = new StringBuffer();
			
			query.append(" select ef.enroll_Name as NAME, decode(ef.enroll_Gender,'M','Male','F','Female','N/A') as EGENDER, to_char(ef.enroll_Dob, 'dd/mm/yyyy') as BENDOB, er.relation_Name as RELATION,ee.emp_Hno || ', ' || ");
			query.append(" ee.emp_Hstreetno || ', ' || el2.loc_Name || ', ' || el3.loc_Name || ', ' || el1.loc_Name || ' District' as ADDRESS, ");
			query.append(" (select a.enroll_Name from Ehf_Enrollment_Family a where a.enroll_Prnt_Id = ef.enroll_Prnt_Id and ");
			query.append(" a.enroll_Relation = '0') as EMPNAME, ee.emp_Code as EMPID, ef.aadhar_Id as AADHARID, el4.loc_name as DDO, ac.cmb_dtl_name as CARDTYPE ");
			query.append(" from Ehf_Enrollment ee, Ehf_Enrollment_Family ef, Ehfm_Relation_Mst er, Ehfm_Locations el1, Ehfm_Locations el2, ");
			query.append(" Ehfm_Locations el3, Ehfm_Locations el4, asrim_users au, asrim_combo ac where ee.enroll_Prnt_Id = ef.enroll_Prnt_Id and ef.enroll_Relation = er.Relation_id ");
			query.append(" and el1.loc_Id = ee.emp_Hdist and el2.loc_Id = ee.emp_Hvill_twn and el3.loc_Id = ee.Emp_Hmand_Munci ");
			query.append(" and el4.loc_id = ee.post_dist and au.login_name=ee.emp_code and ac.cmb_dtl_id=au.user_role and ef.ehf_Card_no = '"+cardNo+"' ");
			cardDetails=genericDao.executeSQLQueryList ( LabelBean.class,query.toString());

			if(cardDetails.size()<=0){
				cardDetails=new ArrayList<LabelBean>();
				query1 = new StringBuffer();
				query1.append(" select jf.name as NAME, decode(jf.Gender,'M','Male','F','Female','N/A') as EGENDER,to_char(jf.Dob, 'dd/mm/yyyy') as BENDOB, ");
				query1.append(" er.relation_name as RELATION, je.home_Houseno || ', ' || je.home_streetname || ', ' || el2.loc_Name || ', ' || el3.loc_Name || ', ' || el1.loc_Name || ' District' as ADDRESS, ");
				query1.append(" (select a.Name from ehf_jrnlst_family a where a.journal_enroll_prnt_id = jf.journal_enroll_prnt_id and a.Relation = '0') as EMPNAME, je.journal_Code as EMPID, ");
				query1.append(" jf.aadhar_Id as AADHARID, el4.loc_name as DDO, cast('Journalist' as varchar2(20)) as CARDTYPE, je.acc_card_no as ACCRIDNO, je.home_email as EMAILID, je.home_mobile_no as MOBILENO, ac.cmb_dtl_name as IDTYPE ");
				query1.append(" from  ehf_jrnlst_enrollment je, ehf_jrnlst_family jf, Ehfm_Relation_Mst er, Ehfm_Locations el1, Ehfm_Locations el2, Ehfm_Locations el3, Ehfm_Locations el4, asrim_combo ac ");
				query1.append(" where je.journal_enroll_prnt_id=jf.journal_enroll_prnt_id and jf.relation=er.relation_id and el1.loc_Id = je.home_district and el2.loc_Id = je.home_village ");
				query1.append(" and el3.loc_Id = je.home_muncipality and el4.loc_id = je.home_district and ac.cmb_dtl_id=jf.aadhar_exists and jf.journal_card_no='"+cardNo+"' ");
				cardDetails=genericDao.executeSQLQueryList ( LabelBean.class,query1.toString());
			}
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception occurred in getCardDetails() in CommonServiceImpl class."+e.getMessage());
		}
		return cardDetails;
	}
	@Override
	public List<LabelBean> getLocationsNew(String locHdrId, String distId,String stateType) 
	{
		SessionFactory factory=null;
		Session session=null;
		List<LabelBean> locationsList = new ArrayList<LabelBean>();
        Iterator locationsItr=null;
        StringBuffer locationsQuery=null;
    	EhfmLocations ehfmLocations=null;
    	try
    	{
    		factory= hibernateTemplate.getSessionFactory();
    		session=factory.openSession();
    		locationsQuery= new StringBuffer();
    		
    		if(stateType!=null && "O".equalsIgnoreCase(stateType))
    		{
    			locationsQuery.append("from EhfmLocations al where al.locHdrId='"+locHdrId+"' and al.id.locParntId='"+distId+"' and al.activeYn='N' order by al.locName asc");
    		}
    		else
    		locationsQuery.append("from EhfmLocations al where al.locHdrId='"+locHdrId+"' and al.id.locParntId='"+distId+"' and al.activeYn='Y' order by al.locName asc");
            Query locationslst=session.createQuery(locationsQuery.toString());
            locationsItr=locationslst.iterate();
            while(locationsItr.hasNext())
            {
            	ehfmLocations=(EhfmLocations)locationsItr.next();
            	LabelBean labelBean =new LabelBean();
    			labelBean.setID(ehfmLocations.getId().getLocId());
    			labelBean.setVALUE(ehfmLocations.getLocName());
    			locationsList.add(labelBean);
            }
    	}
    	catch(Exception e)
    	{
    		GLOGGER.error("Exception occurred in getEhfmLocations() in CommonServiceImpl class."+e.getMessage());
    	}
    	finally
    	{
    		session.close();
    		factory.close();
    	}
    	return locationsList;
	}
	@Override
	public List<LabelBean> getDistrictListNew()
	{
			List<LabelBean> districtList = new ArrayList<LabelBean>();
			StringBuffer query = new StringBuffer();
		 try
	   	{
			 	query.append(" select loc_id as ID,loc_name as VALUE from ehfm_locations WHERE loc_parnt_id in ('S35') order by loc_name ");
		 		districtList = genericDao.executeSQLQueryList(LabelBean.class,query.toString());
	   	}
	   	catch(Exception e)
	   	{
	   		GLOGGER.error("Exception occurred in getDistrictListNew() in CommonServiceImpl class."+e.getMessage());
	   	}
	       return districtList;
	 }

	/**
	 * Added for fetching new locations
	 */
	@Override
	public LabelBean getNewLocations(LabelBean labelBeanVillage) {
		List<LabelBean> villageList =new  ArrayList<LabelBean>();
		LabelBean lb=null;
        try
        {
        StringBuffer query = new StringBuffer();
        String args[] = new String[1];
        query.append("select new_district_id as NEW_DIST,new_mandal_id NEW_MAND,new_village_id NEW_VILLAGE from ehf_district_mapping where old_village_id=?");
        args[0] = labelBeanVillage.getID();
       
        villageList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),args);
        }
        catch(Exception e)
        {
        	GLOGGER.error("Exception occurred in getVillageList() in CommonServiceImpl class."+e.getMessage());
        }
        
       if(villageList!=null && villageList.size()>0)
       {
    	   lb=villageList.get(0);
       }
    	   return lb;
	}
	@Override
	public List<LabelBean> getActiveHospitals()
	{

		List<LabelBean> hospitalList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append(" select distinct AH.hospId as ID,(AH.hospName||'--'||AH.hospId) as VALUE,AH.hospName as CONST  from EhfmHospitals AH,EhfmEDCHospActionDtls ED ");
			query.append(" where AH.hospId=ED.id.hospId and ED.hospStatus not in ('N') and ED.id.scheme in ('"+config.getString("Scheme.tg")+"') and AH.taashaHosp is null order by AH.hospName asc ");
			hospitalList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception occurred in getActiveHospitals() in CommonServiceImpl class."+e.getMessage());
		}
		return hospitalList;
	
	}
	@Override
	public String getActionDoneName(String caseId,String module)
	{
		String cmbName="";
		try{
			if(module != null && (!"".equalsIgnoreCase(module) && "ehfCase".equalsIgnoreCase(module)))
			{
				EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, caseId);
				if(ehfCase != null && !"".equalsIgnoreCase(ehfCase.getCaseStatus()))
					return getCaseStatusName(ehfCase.getCaseStatus());
			}
			else if(module != null && (!"".equalsIgnoreCase(module) && "ehfFollowup".equalsIgnoreCase(module)))
			{
				EhfCaseFollowupClaim caseFollowUpClaim = genericDao.findById(EhfCaseFollowupClaim.class, String.class,caseId);
				if(caseFollowUpClaim != null && !"".equalsIgnoreCase(caseFollowUpClaim.getFollowUpStatus()))
					return getCaseStatusName(caseFollowUpClaim.getFollowUpStatus());
			}
			else if(module != null && (!"".equalsIgnoreCase(module) && "ehfCochlrFlp".equalsIgnoreCase(module)))
			{
				EhfCochlearFollowup cochlearFollowup = genericDao.findById(EhfCochlearFollowup.class, String.class,caseId);
				if(cochlearFollowup != null && !"".equalsIgnoreCase(cochlearFollowup.getFollowupType()))
					return getCaseStatusName(cochlearFollowup.getFollowupType());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			GLOGGER.error("Exception Occured in getActionDoneName of CommonServiceImpl"+ e.getMessage());
		}
		return cmbName;
	}
	public String getCaseStatusName(String caseStatus)
	{
		String cmbName="";
		try{
			EhfmCmbDtls cmbDtls = genericDao.findById(EhfmCmbDtls.class, String.class, caseStatus);
			if(cmbDtls != null && !"".equalsIgnoreCase(cmbDtls.getCmbDtlName()))
				cmbName=cmbDtls.getCmbDtlName().replaceAll("\\s+","");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			GLOGGER.error("Exception Occured in getCaseStatusName of CommonServiceImpl"+ e.getMessage());
		}
		return cmbName;
	}
	public List<LabelBean> getAllUsersFromDepts1(String caseId) {
		List<GenericDAOQueryCriteria> criteriaList = 
                new ArrayList<GenericDAOQueryCriteria>();
           EhfCase ehfCase=genericDao.findById(EhfCase.class,String.class , caseId);
           criteriaList.add(new GenericDAOQueryCriteria("id.hospitalId", ehfCase.getCaseHospCode(), GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
           criteriaList.add(new GenericDAOQueryCriteria("activeYN","Y", GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
           List<EhfmUsrHospitalMpg> husrHosp = genericDao.findAllByCriteria(EhfmUsrHospitalMpg.class, criteriaList);
       	List<GenericDAOQueryCriteria> criteriaList1 = 
                new ArrayList<GenericDAOQueryCriteria>();  
       	criteriaList1.add(new GenericDAOQueryCriteria("caseId", caseId, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
            List<EhfCaseTherapy> therapyList = genericDao.findAllByCriteria(EhfCaseTherapy.class, criteriaList1);
		StringBuffer sb=new StringBuffer();
		sb.append("select eu.userId as ID,upper(eu.firstName) ||' '|| upper(eu.lastName)   || ' - ' || upper(eu.loginName) as EMPNAME,");
		sb.append(" eu.loginName as LOGINNAME");
		sb.append(" from EhfmUsers eu,EhfmUsrGrpsMpg ug,EhfmGrps eg,EhfmUsrSpecialityMpg eug");
		sb.append(" where  ug.id.usergrpId=eu.id.userId");
		sb.append(" and eg.id.grpId=ug.id.grpId");
		sb.append(" and eg.id.grpId=eug.id.grpId");
		sb.append(" and eu.id.userId=eug.id.userId");
		sb.append(" and eu.serviceFlag='Y' and eu.serviceExpiryDt is null ");
		sb.append(" and ug.id.grpId in ('GP7') and eu.userType ='CD202' ");
		sb.append(" and eug.id.specialityId='"+therapyList.get(0).getAsriCatCode()+"' ");
		sb.append(" and eug.activeYN='Y' ");
		if(husrHosp!=null&&husrHosp.size()>0){
		sb.append(" and eu.userId not in (");
		for (int k = 0; k < husrHosp.size(); k++) {
		if (k != 0 && k != husrHosp.size()) {
			sb.append(" , ");
			}
			sb.append(" '" + husrHosp.get(k).getId().getUserId() + "' ");
		}
		sb.append(" ) ");
		}
		List<LabelBean> usrLst=genericDao.executeHQLQueryList(LabelBean.class, sb.toString());
		
		return usrLst;
	}
		/** 
	 * Added by ramalakshmi for hubspoke CR
	 */
	@Override
	public List<LabelBean> getHubSpoke(String lStrUserId, String schemeId) 
	{
		List<LabelBean> hubList = null;
		StringBuffer query = new StringBuffer();
		try{
			query.append("select h.hosp_id as ID,h.hub_spoke as VALUE from ehfm_hospitals h,");
			query.append("ehfm_medco_dtls m where m.hosp_id = h.hosp_id and m.medco_id='"+lStrUserId+"' ");
			query.append(" and h.scheme='"+schemeId+"' and m.active_yn='Y'");
			hubList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return hubList;
	}
	@Override
	public List<LabelBean> getcaseList(String caseId, String schemeId) 
	{
		List<LabelBean> caseList = null;
		StringBuffer query = new StringBuffer();
		try{
			query.append("select c.case_hosp_code as ID,a.act_id as VALUE from ehf_case c,ehf_case_therapy t,ehf_audit a, ");
			query.append("ehfm_hospitals h WHERE c.case_id='"+caseId+"' and t.case_id = c.case_id and t.icd_proc_code = 'N18.6.A' ");
			query.append("and h.hosp_id=c.case_hosp_code and c.scheme_id='"+schemeId+"' and c.case_id = a.case_id and t.CASE_ID = a.case_id ");
			query.append("and h.hub_spoke in ('SPOKE')  order by act_order");
			caseList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return caseList;
	}
	/**
	 * end of hubspoke CR
	 */
	@Override
	public LabelBean getUserDtls(String userId)
		{
			List<LabelBean> usrDtlsLst=new ArrayList<LabelBean>();
			LabelBean lb=new LabelBean();
			StringBuffer query=new StringBuffer();
			query.append(" select eud.id.unitId as UNITID,ewa.id.userDeptId as deptId ");
			query.append(" from EhfmUsersUnitDtls eud,EhfmUsers eu,EhfmWorkAllocation ewa ");
			query.append(" where eud.id.userId=eu.userId ");
			query.append(" and ewa.id.userRole=eud.id.unitId ");
			query.append(" and eu.userId='"+userId+"' ");
			
			usrDtlsLst=genericDao.executeHQLQueryList(LabelBean.class, query.toString());
			
			if(usrDtlsLst!=null)
				if(usrDtlsLst.size()>0)
					if(usrDtlsLst.get(0)!=null)
						lb=usrDtlsLst.get(0);
			
			return lb;
		}
	
	@Override
	public String getNxtValCEOSent()
		{
			StringBuffer sb=new StringBuffer();
			sb.append(" select EHS_CEO_SENDBACK_SEQ.nextVal NEXTVAL from dual");
			String returnVal=null;
			List<LabelBean> lst=genericDao.executeSQLQueryList(LabelBean.class, sb.toString());
			if(lst!=null)
				{
					if(lst.size()>0)
						{
							if(lst.get(0)!=null)
								{
									returnVal=lst.get(0).getNEXTVAL().toString();
								}
						}
				}
			return returnVal;
		}
	
	@Override
	public List<LabelBean> getAllUsersFromDepts(String deptId)
		{
			StringBuffer sb=new StringBuffer();
			sb.append("select eu.userId as ID,upper(eu.firstName) ||' '|| upper(eu.lastName)  || '(' || upper(ed.dsgnName) || ')' || ' - ' || upper(eu.loginName) as EMPNAME,");
			sb.append(" eu.loginName as loginName,ed.dsgnName as desingName,ewa.id.userDeptId as deptName");
			sb.append(" from EhfmUsers eu,EhfmWorkAllocation ewa,EhfmDesignation ed,EhfmUnits euu");
			sb.append(" where eu.loginName=ewa.userCode");
			sb.append(" and ewa.id.userDeptId='"+deptId+"'");
			sb.append(" and eu.userType in ('"+config.getString("TG")+"','"+config.getString("COMMON")+"')");
			sb.append(" and ed.id.dsgnId=ewa.userDsgnId and ewa.userDsgnId not in ('DG9999')");
			sb.append(" and eu.serviceFlag='Y' and eu.serviceExpiryDt is null ");
			sb.append(" and ewa.id.userRole=euu.unitId ");
			sb.append(" order by to_number(euu.levelId) desc");
			//sb.append(" and eu.serviceFlag='Y'  ");
			
			List<LabelBean> usrLst=genericDao.executeHQLQueryList(LabelBean.class, sb.toString());
			
			return usrLst;
		 }
	
	@Override
	public List<EhfmDepts> getAllDepartments()
		{
			List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>();
			List<String> notLst=new ArrayList<String>();
			notLst.add(config.getString("TCS_DEPT"));
			
			criteriaList.add(new GenericDAOQueryCriteria("deptType", config.getString("AP"),
				GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("deptId", notLst,
					GenericDAOQueryCriteria.CriteriaOperator.NOT_IN));
			criteriaList.add(new GenericDAOQueryCriteria("deptName", "",
					GenericDAOQueryCriteria.CriteriaOperator.ASC));
			
			List<EhfmDepts> ehfmDepts = genericDao.findAllByCriteria(EhfmDepts.class, criteriaList);
			
			return ehfmDepts;
		}

}	
