/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.cms.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.wxjs.matchfee.common.service.CrudService;
import org.wxjs.matchfee.modules.cms.dao.ArticleDataDao;
import org.wxjs.matchfee.modules.cms.entity.ArticleData;

/**
 * 站点Service
 * @author ThinkGem
 * @version 2013-01-15
 */
@Service
@Transactional(readOnly = true)
public class ArticleDataService extends CrudService<ArticleDataDao, ArticleData> {

}
