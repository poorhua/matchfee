/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.cms.dao;

import org.wxjs.matchfee.common.persistence.CrudDao;
import org.wxjs.matchfee.common.persistence.annotation.MyBatisDao;
import org.wxjs.matchfee.modules.cms.entity.Guestbook;

/**
 * 留言DAO接口
 * @author ThinkGem
 * @version 2013-8-23
 */
@MyBatisDao
public interface GuestbookDao extends CrudDao<Guestbook> {

}
