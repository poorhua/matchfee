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
import org.wxjs.matchfee.common.utils.StringUtils;
import org.wxjs.matchfee.modules.charge.entity.OpinionBook;
import org.wxjs.matchfee.modules.charge.dao.OpinionBookDao;
import org.wxjs.matchfee.modules.charge.entity.OpinionBookItem;
import org.wxjs.matchfee.modules.charge.dao.OpinionBookItemDao;

/**
 * 条件意见书Service
 * @author GLQ
 * @version 2017-11-27
 */
@Service
@Transactional(readOnly = true)
public class OpinionBookService extends CrudService<OpinionBookDao, OpinionBook> {

	@Autowired
	private OpinionBookItemDao opinionBookItemDao;
	
	public OpinionBook get(String id) {
		OpinionBook opinionBook = super.get(id);
		opinionBook.setOpinionBookItemList(opinionBookItemDao.findList(new OpinionBookItem(opinionBook)));
		return opinionBook;
	}
	
	public List<OpinionBook> findList(OpinionBook opinionBook) {
		return super.findList(opinionBook);
	}
	
	public Page<OpinionBook> findPage(Page<OpinionBook> page, OpinionBook opinionBook) {
		return super.findPage(page, opinionBook);
	}
	
	@Transactional(readOnly = false)
	public void save(OpinionBook opinionBook) {
		
		boolean isNew = opinionBook.getIsNewRecord();
		
		super.save(opinionBook);
		
		logger.debug("opinionBook.getId(): "+opinionBook.getId() +", opinionBook.getIsNewRecord(): "+opinionBook.getIsNewRecord());
		
		//initial item list
		if(isNew){
			OpinionBookItem opinionBookItem = new OpinionBookItem();
			opinionBookItem.setDoc(opinionBook);
			opinionBookItemDao.initialItemList(opinionBookItem);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(OpinionBook opinionBook) {
		super.delete(opinionBook);
		opinionBookItemDao.delete(new OpinionBookItem(opinionBook));
	}
	
}