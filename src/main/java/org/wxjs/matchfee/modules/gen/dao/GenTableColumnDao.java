/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.gen.dao;

import org.wxjs.matchfee.common.persistence.CrudDao;
import org.wxjs.matchfee.common.persistence.annotation.MyBatisDao;
import org.wxjs.matchfee.modules.gen.entity.GenTableColumn;

/**
 * 业务表字段DAO接口
 * @author ThinkGem
 * @version 2013-10-15
 */
@MyBatisDao
public interface GenTableColumnDao extends CrudDao<GenTableColumn> {
	
	public void deleteByGenTableId(String genTableId);
}
