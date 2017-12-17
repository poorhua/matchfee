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
import org.wxjs.matchfee.modules.charge.dao.ChargeDao;
import org.wxjs.matchfee.modules.charge.dao.DeductionDocItemDao;

/**
 * 抵扣项目Service
 * @author GLQ
 * @version 2017-11-25
 */
@Service
@Transactional(readOnly = true)
public class DeductionDocItemService extends CrudService<DeductionDocItemDao, DeductionDocItem> {
	
	@Autowired
	private ChargeDao chargeDao;	
	
	@Autowired
	private OpinionBookItemService opinionBookItemService;

	public DeductionDocItem get(String id) {
		return super.get(id);
	}
	
	public List<DeductionDocItem> findList(DeductionDocItem deductionDocItem) {
		return super.findList(deductionDocItem);
	}
	
	public Page<DeductionDocItem> findPage(Page<DeductionDocItem> page, DeductionDocItem deductionDocItem) {
		return super.findPage(page, deductionDocItem);
	}
	
	public List<DeductionDocItem> sumDeductions(DeductionDocItem deductionDocItem){
		return dao.sumDeductions(deductionDocItem);
	}
	
	@Transactional(readOnly = false)
	public void save(DeductionDocItem deductionDocItem) {
		super.save(deductionDocItem);
		
		//refresh calMoney in charge
		chargeDao.refreshCalMoney(deductionDocItem.getDoc().getCharge());
	}
	
	@Transactional(readOnly = false)
	public void delete(DeductionDocItem deductionDocItem) {
		super.delete(deductionDocItem);
		//refresh calMoney in charge
		chargeDao.refreshCalMoney(deductionDocItem.getDoc().getCharge());
	}
	
	@Transactional(readOnly = true)
	public String getAreaDeducted(String itemId, String prjNum) {

		List<DeductionDocItem> list = this.getAreaDeductions(itemId, prjNum);
		
		String rst = "";
		
		if(list != null){
			for(DeductionDocItem entity : list){
				if(entity.getItem().getId().equals(itemId)){
					rst = entity.getArea();
					break;
				}
			}
		}
		
		return rst;
	}
	
	@Transactional(readOnly = true)
	public List<DeductionDocItem> getAreaDeductions(String itemId, String prjNum) {
		DeductionDocItem deductionDocItem = new DeductionDocItem();
		DeductionDoc doc = new DeductionDoc();
		doc.setPrjNum(prjNum);
		deductionDocItem.setDoc(doc);

		return this.sumDeductions(deductionDocItem);
	}
	
}