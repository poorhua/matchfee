/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.base.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.wxjs.matchfee.common.persistence.Page;
import org.wxjs.matchfee.common.service.CrudService;
import org.wxjs.matchfee.modules.base.entity.DeductionItem;
import org.wxjs.matchfee.modules.base.dao.DeductionItemDao;

/**
 * deductionitemService
 * @author GLQ
 * @version 2017-11-24
 */
@Service
@Transactional(readOnly = true)
public class DeductionItemService extends CrudService<DeductionItemDao, DeductionItem> {

	public DeductionItem get(String id) {
		return super.get(id);
	}
	
	public List<DeductionItem> findList(DeductionItem deductionItem) {
		return super.findList(deductionItem);
	}
	
	public Page<DeductionItem> findPage(Page<DeductionItem> page, DeductionItem deductionItem) {
		return super.findPage(page, deductionItem);
	}
	
	@Transactional(readOnly = false)
	public void save(DeductionItem deductionItem) {
		super.save(deductionItem);
	}
	
	@Transactional(readOnly = false)
	public void delete(DeductionItem deductionItem) {
		super.delete(deductionItem);
	}
	
}