/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.wxjs.matchfee.common.persistence.Page;
import org.wxjs.matchfee.common.service.CrudService;
import org.wxjs.matchfee.modules.charge.entity.DeductionDoc;
import org.wxjs.matchfee.modules.charge.entity.DeductionDocItem;
import org.wxjs.matchfee.modules.charge.dao.DeductionDocDao;
import org.wxjs.matchfee.modules.charge.dao.DeductionDocItemDao;

/**
 * 抵扣项文件Service
 * @author GLQ
 * @version 2017-11-25
 */
@Service
@Transactional(readOnly = true)
public class DeductionDocService extends CrudService<DeductionDocDao, DeductionDoc> {

	@Autowired
	private DeductionDocItemDao deductionDocItemDao;
	
	public DeductionDoc get(String id) {
		DeductionDoc deductionDoc = super.get(id);
		deductionDoc.setDeductionDocItemList(deductionDocItemDao.findList(new DeductionDocItem(id)));
		return deductionDoc;
	}
	
	public List<DeductionDoc> findList(DeductionDoc deductionDoc) {
		return super.findList(deductionDoc);
	}
	
	public Page<DeductionDoc> findPage(Page<DeductionDoc> page, DeductionDoc deductionDoc) {
		return super.findPage(page, deductionDoc);
	}
	
	@Transactional(readOnly = false)
	public void save(DeductionDoc deductionDoc) {
		super.save(deductionDoc);
	}
	
	@Transactional(readOnly = false)
	public void delete(DeductionDoc deductionDoc) {
		super.delete(deductionDoc);
	}
	
}