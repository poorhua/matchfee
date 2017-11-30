/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.wxjs.matchfee.common.persistence.Page;
import org.wxjs.matchfee.common.service.CrudService;
import org.wxjs.matchfee.modules.charge.entity.OpinionBookItem;
import org.wxjs.matchfee.modules.charge.dao.OpinionBookItemDao;

/**
 * 条件意见书项目Service
 * @author GLQ
 * @version 2017-11-27
 */
@Service
@Transactional(readOnly = true)
public class OpinionBookItemService extends CrudService<OpinionBookItemDao, OpinionBookItem> {

	public OpinionBookItem get(String id) {
		return super.get(id);
	}
	
	public List<OpinionBookItem> findList(OpinionBookItem opinionBookItem) {
		return super.findList(opinionBookItem);
	}
	
	public Page<OpinionBookItem> findPage(Page<OpinionBookItem> page, OpinionBookItem opinionBookItem) {
		return super.findPage(page, opinionBookItem);
	}
	
	@Transactional(readOnly = false)
	public void save(OpinionBookItem opinionBookItem) {
		super.save(opinionBookItem);
	}
	
	@Transactional(readOnly = false)
	public void delete(OpinionBookItem opinionBookItem) {
		super.delete(opinionBookItem);
	}
	
}