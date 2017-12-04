/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.dao;

import org.wxjs.matchfee.common.persistence.CrudDao;
import org.wxjs.matchfee.common.persistence.annotation.MyBatisDao;
import org.wxjs.matchfee.modules.charge.entity.ProjectDeduction;

/**
 * 项目抵扣项DAO接口
 * @author GLQ
 * @version 2017-12-03
 */
@MyBatisDao
public interface ProjectDeductionDao extends CrudDao<ProjectDeduction> {
	
}