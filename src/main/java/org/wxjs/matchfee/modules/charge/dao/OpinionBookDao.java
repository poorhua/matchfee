/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.dao;

import org.wxjs.matchfee.common.persistence.CrudDao;
import org.wxjs.matchfee.common.persistence.annotation.MyBatisDao;
import org.wxjs.matchfee.modules.charge.entity.OpinionBook;

/**
 * 条件意见书DAO接口
 * @author GLQ
 * @version 2017-11-27
 */
@MyBatisDao
public interface OpinionBookDao extends CrudDao<OpinionBook> {
	
}