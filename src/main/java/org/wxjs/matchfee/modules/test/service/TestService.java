/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.test.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.wxjs.matchfee.common.service.CrudService;
import org.wxjs.matchfee.modules.test.entity.Test;
import org.wxjs.matchfee.modules.test.dao.TestDao;

/**
 * 测试Service
 * @author ThinkGem
 * @version 2013-10-17
 */
@Service
@Transactional(readOnly = true)
public class TestService extends CrudService<TestDao, Test> {

}
