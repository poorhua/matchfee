/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.dao;

import org.wxjs.matchfee.common.persistence.CrudDao;
import org.wxjs.matchfee.common.persistence.annotation.MyBatisDao;
import org.wxjs.matchfee.modules.charge.entity.Project;

/**
 * 项目信息DAO接口
 * @author GLQ
 * @version 2017-11-27
 */
@MyBatisDao
public interface ProjectDao extends CrudDao<Project> {
	
	public Project getByPrjNum(String prjNum);
	
	public void updateHint(Project project);
	
}