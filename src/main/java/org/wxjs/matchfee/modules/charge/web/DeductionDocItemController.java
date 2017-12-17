/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.wxjs.matchfee.common.config.Global;
import org.wxjs.matchfee.common.persistence.Page;
import org.wxjs.matchfee.common.web.BaseController;
import org.wxjs.matchfee.common.utils.StringUtils;
import org.wxjs.matchfee.common.utils.Util;
import org.wxjs.matchfee.modules.charge.entity.DeductionDocItem;
import org.wxjs.matchfee.modules.charge.service.DeductionDocItemService;
import org.wxjs.matchfee.modules.charge.service.DeductionDocService;
import org.wxjs.matchfee.modules.charge.service.OpinionBookItemService;

import com.google.common.collect.Maps;

/**
 * 抵扣项目Controller
 * @author GLQ
 * @version 2017-11-25
 */
@Controller
@RequestMapping(value = "${adminPath}/charge/deductionDocItem")
public class DeductionDocItemController extends BaseController {
	
	@Autowired
	private OpinionBookItemService opinionBookItemService;

	@Autowired
	private DeductionDocItemService deductionDocItemService;
	
	@Autowired
	private DeductionDocService  deductionDocService;
	
	
	@ModelAttribute
	public DeductionDocItem get(@RequestParam(required=false) String id) {
		DeductionDocItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = deductionDocItemService.get(id);
		}
		if (entity == null){
			entity = new DeductionDocItem();
		}
		return entity;
	}
	
	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = {"list", ""})
	public String list(DeductionDocItem deductionDocItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DeductionDocItem> page = deductionDocItemService.findPage(new Page<DeductionDocItem>(request, response), deductionDocItem); 
		model.addAttribute("page", page);
		return "modules/charge/deductionDocItemList";
	}

	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = "form")
	public String form(DeductionDocItem deductionDocItem, Model model) {
		if(deductionDocItem.getDoc()!=null && !StringUtils.isBlank(deductionDocItem.getDoc().getId())){
			deductionDocItem.setDoc(deductionDocService.get(deductionDocItem.getDoc().getId()));
		
		}
		
		model.addAttribute("deductionDocItem", deductionDocItem);
		
		//get deduction item hint
		
		if(deductionDocItem != null && deductionDocItem.getItem()!=null){
			String itemId = deductionDocItem.getItem().getId();
			
			String prjNum = deductionDocItem.getDoc().getPrjNum();
			
			String areaInOpinionBook = opinionBookItemService.getAreaInOpinionBook(itemId, prjNum);
			
			String areaDeducted = deductionDocItemService.getAreaDeducted(itemId, prjNum);
			
			StringBuffer buffer = new StringBuffer();
			
			buffer.append("意见书面积：");
			
			if(!StringUtils.isBlank(areaInOpinionBook)){
				buffer.append(areaInOpinionBook).append("平米");
			}else{
				buffer.append("平米");
			}
			
			buffer.append("，已抵扣面积：");
			
			if(!StringUtils.isBlank(areaDeducted)){
				buffer.append(areaDeducted).append("平米");
			}else{
				buffer.append("0平米");
			}
			
			float areaRemained = Util.getFloat(areaInOpinionBook) - Util.getFloat(areaDeducted);
			
			buffer.append(", 剩余面积： ");
			buffer.append(Util.formatDecimal(areaRemained, Global.DecimalFormat)+"平米");
			
			model.addAttribute("deductionItemHint", buffer.toString());			
		}
		
		return "modules/charge/deductionDocItemForm";
	}

	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "save")
	public String save(DeductionDocItem deductionDocItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, deductionDocItem)){
			return form(deductionDocItem, model);
		}
		
		//check whether total deductions exceed the limit in opinion book
		String itemId = deductionDocItem.getItem().getId();
		String prjNum = deductionDocItem.getDoc().getPrjNum();
		String areaInOpinionBookStr = opinionBookItemService.getAreaInOpinionBook(itemId, prjNum);
		
		float areaInOpinionBook = Util.getFloat(areaInOpinionBookStr);
		
		logger.debug("areaInOpinionBook: "+areaInOpinionBook);

		if(areaInOpinionBook > 0){
			String areaDeductedStr = deductionDocItemService.getAreaDeducted(itemId, prjNum);
			float areaDeducted = Util.getFloat(areaDeductedStr);
			
			logger.debug("1 areaDeducted: "+areaDeducted);
			//exclude the original item when do update operation
			if(!StringUtils.isBlank(deductionDocItem.getId())){
				DeductionDocItem originalEntity = deductionDocItemService.get(deductionDocItem.getId());
				if(originalEntity != null){
					areaDeducted -= Util.getFloat(originalEntity.getArea());
				}
			}
			logger.debug("2 areaDeducted: "+areaDeducted);
			//add area in this operation
			areaDeducted += Util.getFloat(deductionDocItem.getArea());
			
			logger.debug("3 areaDeducted: "+areaDeducted);
			
			if(areaDeducted > areaInOpinionBook){
				addMessage(model, "验证失败。累计抵扣面积超过条件意见书规定。");
				return form(deductionDocItem, model);
			}
		}
		
		try{
			deductionDocItemService.save(deductionDocItem);
			addMessage(redirectAttributes, "保存设计院证明项目成功");
		}catch(DuplicateKeyException e1){
			addMessage(redirectAttributes, "保存设计院证明项目失败。重复！");
			logger.error("save error", e1);
		}catch(Exception e2){
			addMessage(redirectAttributes, "保存设计院证明项目失败。");
			logger.error("save error", e2);
		}
		
		return "redirect:"+Global.getAdminPath()+"/charge/charge/deductionDocTab/?repage";
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "delete")
	public String delete(DeductionDocItem deductionDocItem, RedirectAttributes redirectAttributes) {
		deductionDocItemService.delete(deductionDocItem);
		addMessage(redirectAttributes, "删除设计院证明项目成功");
		return "redirect:"+Global.getAdminPath()+"/charge/charge/deductionDocTab/?repage";
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "deductionSummary")
	public Map<String, Object> deductionSummary(@RequestParam(required=true) String itemId, @RequestParam(required=true) String prjNum, HttpServletResponse response) {
		Map<String, Object> map = Maps.newHashMap();
		
		String areaInOpinionBook = opinionBookItemService.getAreaInOpinionBook(itemId, prjNum);
		
		String areaDeducted = deductionDocItemService.getAreaDeducted(itemId, prjNum);
		
		map.put("areaInOpinionBook", areaInOpinionBook);
		
		map.put("areaDeducted", areaDeducted);
		
		float areaRemained = Util.getFloat(areaInOpinionBook) - Util.getFloat(areaDeducted);
		
		map.put("areaRemained", Util.formatDecimal(areaRemained, Global.DecimalFormat));

		return map;
	}

}