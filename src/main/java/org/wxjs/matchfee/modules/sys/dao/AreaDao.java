/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.sys.dao;

import org.wxjs.matchfee.common.persistence.TreeDao;
import org.wxjs.matchfee.common.persistence.annotation.MyBatisDao;
import org.wxjs.matchfee.modules.sys.entity.Area;

/**
 * 区域DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	
}
