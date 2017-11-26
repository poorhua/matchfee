/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.test.dao;

import org.wxjs.matchfee.common.persistence.TreeDao;
import org.wxjs.matchfee.common.persistence.annotation.MyBatisDao;
import org.wxjs.matchfee.test.entity.TestTree;

/**
 * 树结构生成DAO接口
 * @author ThinkGem
 * @version 2015-04-06
 */
@MyBatisDao
public interface TestTreeDao extends TreeDao<TestTree> {
	
}