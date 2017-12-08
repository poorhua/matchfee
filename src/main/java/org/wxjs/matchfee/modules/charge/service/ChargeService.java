/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wxjs.matchfee.common.persistence.Page;
import org.wxjs.matchfee.common.service.CrudService;
import org.wxjs.matchfee.common.utils.Util;
import org.wxjs.matchfee.modules.charge.entity.Charge;
import org.wxjs.matchfee.modules.charge.entity.DeductionDoc;
import org.wxjs.matchfee.modules.charge.entity.DeductionDocItem;
import org.wxjs.matchfee.modules.charge.entity.OpinionBook;
import org.wxjs.matchfee.modules.charge.entity.OpinionBookItem;
import org.wxjs.matchfee.modules.charge.entity.PayTicket;
import org.wxjs.matchfee.modules.charge.entity.ProjectDeduction;
import org.wxjs.matchfee.modules.charge.entity.ProjectLicense;
import org.wxjs.matchfee.modules.charge.entity.SettlementList;
import org.wxjs.matchfee.modules.charge.dao.ChargeDao;

/**
 * 征收Service
 * @author GLQ
 * @version 2017-11-25
 */
@Service
@Transactional(readOnly = true)
public class ChargeService extends CrudService<ChargeDao, Charge> {
	
	@Autowired
	private DeductionDocService deductionDocService;
	
	@Autowired
	private DeductionDocItemService  deductionDocItemService;
	
	@Autowired
	private OpinionBookService opinionBookService;
	
	@Autowired
	private OpinionBookItemService opinionBookItemService;
	
	@Autowired
	private ProjectDeductionService projectDeductionService;
	
	@Autowired
	private ProjectLicenseService projectLicenseService;	
	
	@Autowired
	private PayTicketService payTicketService;	

	public Charge get(String id) {
		return super.get(id);
	}
	
	public List<Charge> findList(Charge charge) {
		return super.findList(charge);
	}
	
	public Page<Charge> findPage(Page<Charge> page, Charge charge) {
		return super.findPage(page, charge);
	}
	
	@Transactional(readOnly = false)
	public void save(Charge charge) {
		super.save(charge);
	}
	
	@Transactional(readOnly = false)
	public void updateReport(Charge charge) {
		dao.updateReport(charge);
	}
	
	@Transactional(readOnly = false)
	public void updateCalculate(Charge charge) {
		dao.updateCalculate(charge);
	}
	
	@Transactional(readOnly = false)
	public void updateApprove(Charge charge) {
		dao.updateApprove(charge);
	}
	
	@Transactional(readOnly = false)
	public void updateConfirm(Charge charge) {
		dao.updateConfirm(charge);
	}
	
	@Transactional(readOnly = false)
	public void updateStatus(Charge charge) {
		dao.updateStatus(charge);
	}
	
	@Transactional(readOnly = false)
	public void delete(Charge charge) {
		super.delete(charge);
	}
	
	@Transactional(readOnly = true)
	public SettlementList settle(String chargeId) {
		SettlementList settlementList = new SettlementList();
		
		float calMoney = 0; //结算金额
		float payMoney = 0; //缴费金额
		
		Charge charge = this.get(chargeId);
		
		settlementList.setCharge(charge);
		
		//OpinionBookItem
		OpinionBookItem opinionBookItem = new OpinionBookItem();
		OpinionBook opinionBook = new OpinionBook();
		opinionBook.setPrjNum(charge.getProject().getPrjNum());
		opinionBookItem.setDoc(opinionBook);
		
		settlementList.setOpinionBookItems(this.opinionBookItemService.findList(opinionBookItem));
		
		Map<String, String> opinionBookItemMap = new HashMap<String, String>();
		for(OpinionBookItem item : settlementList.getOpinionBookItems()){
			opinionBookItemMap.put(item.getItem().getId(), item.getArea());
		}
		
		//ProjectLicense
		ProjectLicense projectLicense = new ProjectLicense();
		projectLicense.setCharge(charge);
		settlementList.setProjectLicenses(this.projectLicenseService.findList(projectLicense));
		
		for(ProjectLicense item : settlementList.getProjectLicenses()){
			calMoney += item.getTotalMoney();
			item.setRemarks("规划许可证号: "+item.getDocumentNo());
		}
		
		//DeductionDocItem
		DeductionDocItem deductionDocItem = new DeductionDocItem();
		
		DeductionDoc deductionDoc = new DeductionDoc();
		deductionDoc.setCharge(charge);
		deductionDoc.setPrjNum(charge.getProject().getPrjNum());
		deductionDocItem.setDoc(deductionDoc);
		
		settlementList.setDeductionDocItems(this.deductionDocItemService.findList(deductionDocItem));
		
		//get settled item till this charge
		List<DeductionDocItem> settledItemList = this.deductionDocItemService.sumDeductions(deductionDocItem);
		Map<String, String> settledItemMap = new HashMap<String, String>();
		for(DeductionDocItem item : settledItemList){
			settledItemMap.put(item.getItem().getId(), item.getArea());
		}
		
		for(DeductionDocItem item : settlementList.getDeductionDocItems()){
			
			calMoney -= Util.getFloat(item.getMoney());
			
			String opinionBookValue = opinionBookItemMap.get(item.getItem().getId());
			if(opinionBookValue == null){
				opinionBookValue = "无";
			}else{
				opinionBookValue = opinionBookValue + "平米";
			}
			String settledValue = settledItemMap.get(item.getItem().getId());
			if(settledValue == null){
				settledValue = "0平米";
			}else{
				settledValue = settledValue + "平米";
			}			
			StringBuilder sb = new StringBuilder();
			sb.append("意见书总面积: ").append(opinionBookValue);
			sb.append("，至本期共抵扣: ").append(settledValue);
			
			item.setRemarks(sb.toString());
		}
		
		//ProjectDeduction
		ProjectDeduction projectDeduction = new ProjectDeduction();
		projectDeduction.setCharge(charge);
		
		settlementList.setProjectDeductions(this.projectDeductionService.findList(projectDeduction));
		
		for(ProjectDeduction item : settlementList.getProjectDeductions()){
			calMoney -= Util.getFloat(item.getMoney());
		}
		
		
		//PayTicket
		PayTicket payTicket = new PayTicket();
		payTicket.setCharge(charge);
		
		settlementList.setPayTickets(this.payTicketService.findList(payTicket));
		
		for(PayTicket item : settlementList.getPayTickets()){
			payMoney += Util.getFloat(item.getMoney());
			item.setRemarks("票据号: "+item.getTicketNo()+", 缴费日期: "+DateUtil.formatDate(item.getPayDate(), "yyyy-MM-dd"));
		}
		
		settlementList.getCharge().setCalMoney(calMoney + "");
		settlementList.getCharge().setPayMoney(payMoney + "");
		
		return settlementList;
	}
	
	
	
}