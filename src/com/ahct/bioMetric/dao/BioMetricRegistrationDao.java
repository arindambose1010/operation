package com.ahct.bioMetric.dao;

import java.util.List;

import com.ahct.bioMetric.vo.BioMetricVo;
import com.ahct.common.vo.LabelBean;

public interface BioMetricRegistrationDao {

	
	public String uploadEmpPhoto(BioMetricVo bioMetricVo );
	
	public boolean biometricEnroll(BioMetricVo bioMetricVo);
	
	public String biometricAttendance(BioMetricVo bioMetricVo);
	
	public String validateAttendance(BioMetricVo bioMetricVo);
	
	public List<LabelBean> getOthersList();
	
	public String getUserIdByRole(BioMetricVo biometricVO);
	
	public BioMetricVo getBiometricReport(BioMetricVo bioMetricVo);
}
