/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.web;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.wxjs.matchfee.common.config.Global;
import org.wxjs.matchfee.common.persistence.Page;
import org.wxjs.matchfee.common.web.BaseController;
import org.wxjs.matchfee.common.utils.StringUtils;
import org.wxjs.matchfee.modules.charge.entity.DeductionDocItem;
import org.wxjs.matchfee.modules.charge.service.DeductionDocItemService;
import org.wxjs.matchfee.modules.charge.service.DeductionDocService;

/**
 * 抵扣项目Controller
 * @author GLQ
 * @version 2017-11-25
 */
@Controller
@RequestMapping(value = "${adminPath}/charge/deductionDocItem")
public class DeductionDocItemController extends BaseController {

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
		return "modules/charge/deductionDocItemForm";
	}

	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "save")
	public String save(DeductionDocItem deductionDocItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, deductionDocItem)){
			return form(deductionDocItem, model);
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

}