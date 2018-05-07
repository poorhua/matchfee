package org.wxjs.matchfee.modules.report.dao;

import java.util.Collection;
import java.util.HashMap;



import java.util.List;

import org.wxjs.matchfee.common.persistence.BaseDao;
import org.wxjs.matchfee.common.persistence.annotation.MyBatisDao;
import org.wxjs.matchfee.modules.report.entity.ReportParam;
import org.wxjs.matchfee.modules.report.entity.TaxProtectReport;



@MyBatisDao
public interface ReportDao extends BaseDao{
	
	public Collection<HashMap<String, Object>> report(ReportParam reportParam);
	
	public  Collection<HashMap<String, Object>> dashboardDeclare();
	
	public  Collection<HashMap<String, Object>> dashboardChargeMoney();
	
	public  Collection<HashMap<String, Object>> dashboardChargeStatus();
	
	public List<TaxProtectReport> taxProtect(ReportParam reportParam);

}
