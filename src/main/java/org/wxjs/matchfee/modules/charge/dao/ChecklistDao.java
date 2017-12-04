/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.dao;

import org.wxjs.matchfee.common.persistence.CrudDao;
import org.wxjs.matchfee.common.persistence.annotation.MyBatisDao;
import org.wxjs.matchfee.modules.charge.entity.Checklist;

/**
 * 结算清单DAO接口
 * @author XYM
 * @version 2017-12-04
 */
@MyBatisDao
public interface ChecklistDao extends CrudDao<Checklist> {
	
}