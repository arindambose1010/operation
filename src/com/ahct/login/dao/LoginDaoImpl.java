package com.ahct.login.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ahct.common.vo.LabelBean;
import com.ahct.login.vo.EmployeeDetailsVO;
import com.ahct.login.vo.MenuVo;
import com.ahct.model.EhfChronicCaseDtl;
import com.ahct.model.EhfGroupMessage;
import com.ahct.model.EhfHospitalMessage;
import com.ahct.model.EhfUserMessage;
import com.ahct.model.EhfmHospMithraDtls;
import com.ahct.model.EhfmMedcoDtls;
import com.ahct.model.EhfmMod;
import com.ahct.model.EhfmUsers;
import com.ahct.model.EhfmUsrGrpsMpg;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;


public class LoginDaoImpl implements LoginDao{
	GenericDAO genericDao;
	GenericDAOQueryCriteria genericDaoqueryCrit;
	
	
	public GenericDAOQueryCriteria getGenericDaoqueryCrit() {
		return genericDaoqueryCrit;
	}
	public void setGenericDaoqueryCrit(GenericDAOQueryCriteria genericDaoqueryCrit) {
		this.genericDaoqueryCrit = genericDaoqueryCrit;
	}
	public GenericDAO getGenericDao() {
		return genericDao;
	}
	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}
	HibernateTemplate hibernateTemplate ;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@SuppressWarnings("unchecked")
	public List<MenuVo> getActionBarModulesForEMP(String pStrParentModId,String pStrUsrID,String pStrLangId)
	{
		List<MenuVo> menuLst  = new ArrayList<MenuVo>();
		List<MenuVo> menuLst1  = new ArrayList<MenuVo>();
		String lstrUserState=null;
		
		try
		{
		StringBuffer sql_query = new StringBuffer();
		String[] args = new String[6];
		args[0] = pStrUsrID;
		args[1] = pStrParentModId;
		args[2]=pStrLangId;
		args[3]="Y";
		args[4]="Operations";
		args[5]="OperationsTS";
		sql_query.append(" select distinct AM.modId as MODID,AM.modName as MODNAME,AM.modDesc as MODDESC, AM.subUrl as MODURL, AM.modOrder as MODORDER1, AM.modParntId  as PARNTMODID "); 
		sql_query.append(" from EhfmMod AM,EhfmModGrpMpg AMR,EhfmUsrGrpsMpg SUG where SUG.id.grpId=AMR.id.grpId");
		sql_query.append(" and SUG.id.usergrpId=? and AM.modId=AMR.id.modId");
		sql_query.append(" and AM.modParntId=? and AM.langId=? ");
		sql_query.append(" and AM.activeYn=?  and AMR.moduleId in (?,?)   order by AM.modOrder ");
		
		
		EhfmUsers ehfmUsers=new EhfmUsers();
		if(pStrUsrID!=null)
		{
			ehfmUsers=genericDao.findById(EhfmUsers.class,String.class,pStrUsrID);
			if(ehfmUsers!=null)
			lstrUserState=ehfmUsers.getUserType();
		}
		
		EhfmUsrGrpsMpg EhfmUsrGrpsMpg=new EhfmUsrGrpsMpg();
		
		
		
         List<GenericDAOQueryCriteria> crList=new ArrayList<GenericDAOQueryCriteria>();
		
		if(pStrUsrID!=null && !"".equalsIgnoreCase(pStrUsrID)){
			crList.add(new GenericDAOQueryCriteria("id.usergrpId",pStrUsrID,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			
		}
		
		
		
		 List<EhfmUsrGrpsMpg>  grpList=genericDao.findAllByCriteria(EhfmUsrGrpsMpg.class,crList );
		 int count=0;
		if(grpList!=null)
		{
			for(EhfmUsrGrpsMpg grps:grpList)
			{
				if(grps.getId().getGrpId()!=null && grps.getId().getGrpId().equalsIgnoreCase("GP2"))
				{
					count++;
					break;
				}
			}
		}
		
		if(count>0)
		{
			lstrUserState=getMedcoScheme(pStrUsrID);
		}
	
		menuLst = genericDao.executeHQLQueryList(MenuVo.class,sql_query.toString(),args);
		if(menuLst.size()>0){
			for(MenuVo menuVo1 : menuLst){
				MenuVo menuVo=new MenuVo();
				if(lstrUserState!=null)
				{
				menuVo.setMODID(menuVo1.getMODID());
				menuVo.setMODNAME(menuVo1.getMODNAME());
				menuVo.setMODDESC(menuVo1.getMODDESC());
				menuVo.setMODURL(menuVo1.getMODURL());
				if(menuVo1.getMODORDER1()!=null)
					menuVo.setMODORDER1(menuVo1.getMODORDER1());
				menuVo.setPARNTMODID(menuVo1.getPARNTMODID());
				menuLst1.add(menuVo);
				}
			}
		}
		
	}
	catch(Exception e)
	{
	e.printStackTrace();	
	}
		return menuLst1;
}
	public List<EmployeeDetailsVO> checkCasesForClinicalNotes(String userId)
    {
  	  List<EmployeeDetailsVO> list=new ArrayList<EmployeeDetailsVO>();
  	  StringBuffer query= new StringBuffer();
  	  try
  	  {
  		String args[]= new String[20];
    	args[0]= userId;
    	args[1]= "CD202";
    	args[2]= "DOP";
    	args[3]= "CD34";
    	args[4]= "CD601";
    	args[5]= "CD216";
    	args[6]= "CD9";
    	args[7]= "CD209";
    	args[8]= "CD5";
    	args[9]= "CD612";
    	args[10]= userId;
    	args[11]= "CD202";
    	args[12]= "DOP";
    	args[13]= "CD34";
    	args[14]= "CD601";
    	args[15]= "CD216";
    	args[16]= "CD9";
    	args[17]= "CD209";
    	args[18]= "CD5";
    	args[19]= "CD612";
    	query.append(" (select cc.case_id ID from ehf_case cc,  ehfm_medco_dtls emd ");
    	query.append(" ,Ehfm_main_therapy emt,Ehf_Case_Therapy ect ");
  		query.append(" where cc.case_hosp_code = emd.hosp_id and emd.medco_id=? ");
  		query.append(" and cc.scheme_id = ? and cc.case_Id=ect.case_Id and ect.icd_Proc_Code=emt.icd_Proc_Code");
  		query.append(" and cc.scheme_Id=emt.state and emt.process not in (?) ");
  		query.append(" and cc.case_status not in (?,?,?,?,?,?,?) and ((cc.cs_dis_dt is null and cc.cs_death_dt is  null) or (cc.cs_dis_dt is not null and cc.cs_dis_dt >= trunc(sysdate)) or (cc.cs_death_dt is not null and cc.cs_death_dt >= trunc(sysdate))))  ");
  		query.append(" minus ");
  		query.append(" (select ec.case_id ID from ehf_case cc, ehf_case_clinical_notes ec, ehfm_medco_dtls emd ");
  		query.append(" ,Ehfm_main_therapy emt,Ehf_Case_Therapy ect ");
  		query.append(" where cc.case_id = ec.case_id and cc.case_hosp_code = emd.hosp_id and emd.medco_id=? ");
  		query.append(" and cc.scheme_id = ? and cc.case_Id=ect.case_Id and ect.icd_Proc_Code=emt.icd_Proc_Code");
  		query.append(" and cc.scheme_Id=emt.state and emt.process not in (?) ");
  		query.append(" and case_status not in (?,?,?,?,?,?,?) and ((cc.cs_dis_dt is null and cc.cs_death_dt is  null) or (cc.cs_dis_dt is not null and cc.cs_dis_dt >= trunc(sysdate)) or (cc.cs_death_dt is not null and cc.cs_death_dt >= trunc(sysdate))) and ec.clinic_invest_done_dt = trunc(sysdate))  ");
	        
  		list= genericDao.executeSQLQueryList(EmployeeDetailsVO.class, query.toString(), args);
  	  }
  	  catch(Exception e)
  	  {
  		  e.printStackTrace();
  	  }
	         return list;
    }
	@Override
	public List<LabelBean> getUserMessage(String userId,String moduleId) {

		
		List<LabelBean> list=new ArrayList<LabelBean>();
		List<GenericDAOQueryCriteria> crList=new ArrayList<GenericDAOQueryCriteria>();
		
		if(userId!=null && !"".equalsIgnoreCase(userId)){
			crList.add(new GenericDAOQueryCriteria("userId",userId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			
		}
		
		crList.add(new GenericDAOQueryCriteria("active","Y",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		
		 List<EhfUserMessage>  dtls=genericDao.findAllByCriteria(EhfUserMessage.class,crList );
		if(dtls!=null && dtls.size()>0){
			
			
			for(int i=0;i<dtls.size();i++){
				
				LabelBean vo=new LabelBean();
				vo.setNATURE(dtls.get(i).getShortMsg().toUpperCase());
				vo.setID(dtls.get(i).getmId());
				vo.setVALUE(dtls.get(i).getMessage());
				vo.setNATUREID(dtls.get(i).getPath());
				list.add(vo);
			}
			
			
		}
		
		return list;
		
		
	}
	@Override
	public List<LabelBean> getGroupMessage(List grpId,String moduleId) {
		List<LabelBean> list=new ArrayList<LabelBean>();
		List<GenericDAOQueryCriteria> crList=new ArrayList<GenericDAOQueryCriteria>();
		
		if(grpId!=null ){
			crList.add(new GenericDAOQueryCriteria("grpId",grpId,GenericDAOQueryCriteria.CriteriaOperator.IN));
			
		}
		
		crList.add(new GenericDAOQueryCriteria("active","Y",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 List<EhfGroupMessage>  dtls=genericDao.findAllByCriteria(EhfGroupMessage.class,crList );
		if(dtls!=null && dtls.size()>0){
			
			
			for(int i=0;i<dtls.size();i++){
				
				LabelBean vo=new LabelBean();
				vo.setNATURE(dtls.get(i).getShortMsg().toUpperCase());
				vo.setID(dtls.get(i).getmId());
				vo.setVALUE(dtls.get(i).getMessage());
				vo.setNATUREID(dtls.get(i).getPath());
				list.add(vo);
			}
			
			
		}
		
		return list;
		
	}
	@Override
	public List<LabelBean> getHospitalMessage(String hospId,String grpId,String moduleId) {
		
		List<LabelBean> list=new ArrayList<LabelBean>();
		List<GenericDAOQueryCriteria> crList=new ArrayList<GenericDAOQueryCriteria>();
		
		if(hospId!=null && !"".equalsIgnoreCase(hospId)){
			crList.add(new GenericDAOQueryCriteria("hospId",hospId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			
				
		}
		crList.add(new GenericDAOQueryCriteria("active","Y",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		List<String> lst=new ArrayList<String>();
		lst.add("GP1");
		lst.add("GP2");
		crList.add(new GenericDAOQueryCriteria("grpId",lst,GenericDAOQueryCriteria.CriteriaOperator.IN));
		
		
		
		 List<EhfHospitalMessage>  dtls=genericDao.findAllByCriteria(EhfHospitalMessage.class,crList );
		if(dtls!=null && dtls.size()>0){
			
			
			for(int i=0;i<dtls.size();i++){
				
				LabelBean vo=new LabelBean();
				vo.setNATURE(dtls.get(i).getShortMessage().toUpperCase());
				vo.setID(dtls.get(i).getmId());
				vo.setVALUE(dtls.get(i).getMessage());
				vo.setNATUREID(dtls.get(i).getPath());
				list.add(vo);
			}
			
			
		}
		
		return list;
		
		
		
		
	}
	@Override
	public String getHospitslId(String userId, String grpId) {
		  String hospId=null;
			List<GenericDAOQueryCriteria> critList=new ArrayList<GenericDAOQueryCriteria>();
			if("GP2".equalsIgnoreCase(grpId)){
			critList.add(new GenericDAOQueryCriteria("id.medcoId",userId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			critList.add(new GenericDAOQueryCriteria("activeYN","Y",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfmMedcoDtls> medcoDtl=genericDao.findAllByCriteria(EhfmMedcoDtls.class,critList );
			
			if(medcoDtl!=null && medcoDtl.size()>0){
				
				hospId=medcoDtl.get(0).getId().getHospId();
			}
			}
			
			if("GP1".equalsIgnoreCase(grpId)){
				critList.add(new GenericDAOQueryCriteria("id.mithraId",userId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				critList.add(new GenericDAOQueryCriteria("activeYN","Y",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				List<EhfmHospMithraDtls> mithraDtl=genericDao.findAllByCriteria(EhfmHospMithraDtls.class,critList );
				if(mithraDtl!=null && mithraDtl.size()>0){
					
					hospId=	mithraDtl.get(0).getId().getHospId();
				}
				
			}
			return hospId;
	}
	
	/*added by venkatesh for getting scheme of medco as per mapped hospital*/ 
	
	public String getMedcoScheme(String userId)
	{
		String scheme=null;
		
		try
		{
		StringBuffer query=new  StringBuffer();
		List<LabelBean> SchemeList=new ArrayList<LabelBean>();
		String[] args1 = new String[1];
	 	args1[0] = userId;
		query.append(" select b.id.scheme as schemeId from EhfmMedcoDtls a,EhfmEDCHospActionDtls b");
		query.append( " where a.id.hospId=b.id.hospId and a.id.medcoId=? ");
		SchemeList=genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args1);
		
		if(SchemeList!=null)
		{
			for(LabelBean schemeLst:SchemeList)
			{
				if(schemeLst.getSchemeId()!=null && ("CD201").equalsIgnoreCase(schemeLst.getSchemeId()))
				{
					scheme=schemeLst.getSchemeId();
					break;
				}
				else
				{
					scheme="CD202";
				}
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return scheme;
		
	}
	
}
