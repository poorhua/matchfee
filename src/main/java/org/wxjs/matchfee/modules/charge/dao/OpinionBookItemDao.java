/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.dao;

import java.util.List;

import org.wxjs.matchfee.common.persistence.CrudDao;
import org.wxjs.matchfee.common.persistence.annotation.MyBatisDao;
import org.wxjs.matchfee.modules.charge.entity.OpinionBookItem;

/**
 * 条件意见书DAO接口
 * @author GLQ
 * @version 2017-11-27
 */
@MyBatisDao
public interface OpinionBookItemDao extends CrudDao<OpinionBookItem> {
	
}