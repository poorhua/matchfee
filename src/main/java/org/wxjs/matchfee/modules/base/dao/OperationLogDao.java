/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.base.dao;

import org.wxjs.matchfee.common.persistence.CrudDao;
import org.wxjs.matchfee.common.persistence.annotation.MyBatisDao;
import org.wxjs.matchfee.modules.base.entity.OperationLog;

/**
 * operationlogDAO接口
 * @author GLQ
 * @version 2017-12-13
 */
@MyBatisDao
public interface OperationLogDao extends CrudDao<OperationLog> {
	
}