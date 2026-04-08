package com.ahct.login.service;
import java.util.Date;

import com.ahct.common.service.CommonService;
import com.ahct.login.vo.UpdateProfileVo;
import com.ahct.model.EhfmUsers;
import com.tcs.framework.persistanceConfiguration.GenericDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdateProfileServiceImpl.
 */
public class UpdateProfileServiceImpl implements UpdateProfileService {
	
	/** The generic dao. */
	GenericDAO genericDao;
	
	/** The common service. */
	CommonService commonService;
	
	/**
	 * Sets the common service.
	 *
	 * @param commonService the new common service
	 */
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	
	/**
	 * Gets the generic dao.
	 *
	 * @return the generic dao
	 */
	public GenericDAO getGenericDao() {
		return genericDao;
	}

	/**
	 * Sets the generic dao.
	 *
	 * @param genericDao the new generic dao
	 */
	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}

	/* (non-Javadoc)
	 * @see com.ahct.login.service.UpdateProfileService#saveProfileDtls(com.ahct.login.vo.UpdateProfileVo)
	 */
	@Override
	// to save the updated profile details
	public UpdateProfileVo saveProfileDtls(UpdateProfileVo updateProfileVo) {
		String message="Success";
		EhfmUsers ehfmUsers=genericDao.findById(EhfmUsers.class, String.class, updateProfileVo.getUserId());
		ehfmUsers.setFirstName(updateProfileVo.getFirstName());
		ehfmUsers.setMiddleName(updateProfileVo.getMiddleName());
		ehfmUsers.setLastName(updateProfileVo.getLastName());
		ehfmUsers.setMobileNo(updateProfileVo.getMobileNumber());
		ehfmUsers.setEmailId(updateProfileVo.getEmail());
		ehfmUsers.setLstUpdDt(new java.sql.Timestamp(new Date()
		.getTime()));
		ehfmUsers.setLstUpdUser(updateProfileVo.getUserId());

		String lStrPswd=commonService.getDecryptedPswd(ehfmUsers.getLoginName());
		if(lStrPswd!=null){
			ehfmUsers.setPasswd(lStrPswd);
			try{
		ehfmUsers=genericDao.save(ehfmUsers);
			}
			catch(Exception e){
				 message="Failure";
				e.printStackTrace();
			}
		}
		if(message=="Success"){
			updateProfileVo.setMsg("yes");
			updateProfileVo.setCommMsg("All communications will be made through the mobile number and e-mail");
		}else{
			updateProfileVo.setMsg("no");
		}

		return updateProfileVo;
		
		
		
	}

}
