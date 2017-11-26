/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.wxjs.matchfee.common.persistence.Page;
import org.wxjs.matchfee.common.service.CrudService;
import org.wxjs.matchfee.modules.charge.entity.DeductionDocItem;
import org.wxjs.matchfee.modules.charge.dao.DeductionDocItemDao;

/**
 * 抵扣项目Service
 * @author GLQ
 * @version 2017-11-25
 */
@Service
@Transactional(readOnly = true)
public class DeductionDocItemService extends CrudService<DeductionDocItemDao, DeductionDocItem> {

	public DeductionDocItem get(String id) {
		return super.get(id);
	}
	
	public List<DeductionDocItem> findList(DeductionDocItem deductionDocItem) {
		return super.findList(deductionDocItem);
	}
	
	public Page<DeductionDocItem> findPage(Page<DeductionDocItem> page, DeductionDocItem deductionDocItem) {
		return super.findPage(page, deductionDocItem);
	}
	
	@Transactional(readOnly = false)
	public void save(DeductionDocItem deductionDocItem) {
		super.save(deductionDocItem);
	}
	
	@Transactional(readOnly = false)
	public void delete(DeductionDocItem deductionDocItem) {
		super.delete(deductionDocItem);
	}
	
}