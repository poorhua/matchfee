package org.wxjs.matchfee.modules.report.dao;

import java.util.Collection;
import java.util.HashMap;



import org.wxjs.matchfee.common.persistence.BaseDao;
import org.wxjs.matchfee.common.persistence.annotation.MyBatisDao;
import org.wxjs.matchfee.modules.report.entity.ReportParam;



@MyBatisDao
public interface ReportDao extends BaseDao{
	
	public Collection<HashMap<String, Object>> report(ReportParam reportParam);

}
