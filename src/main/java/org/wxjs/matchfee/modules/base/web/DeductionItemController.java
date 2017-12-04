/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.base.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.wxjs.matchfee.modules.base.entity.DeductionItem;
import org.wxjs.matchfee.modules.base.service.DeductionItemService;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * deductionitemController
 * @author GLQ
 * @version 2017-11-24
 */
@Controller
@RequestMapping(value = "${adminPath}/base/deductionItem")
public class DeductionItemController extends BaseController {

	@Autowired
	private DeductionItemService deductionItemService;
	
	@ModelAttribute
	public DeductionItem get(@RequestParam(required=false) String id) {
		DeductionItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = deductionItemService.get(id);
		}
		if (entity == null){
			entity = new DeductionItem();
		}
		return entity;
	}
	
	@RequiresPermissions("base:deductionItem:view")
	@RequestMapping(value = {"list", ""})
	public String list(DeductionItem deductionItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DeductionItem> page = deductionItemService.findPage(new Page<DeductionItem>(request, response), deductionItem); 
		model.addAttribute("page", page);
		return "modules/base/deductionItemList";
	}

	@RequiresPermissions("base:deductionItem:view")
	@RequestMapping(value = "form")
	public String form(DeductionItem deductionItem, Model model) {
		model.addAttribute("deductionItem", deductionItem);
		return "modules/base/deductionItemForm";
	}

	@RequiresPermissions("base:deductionItem:edit")
	@RequestMapping(value = "save")
	public String save(DeductionItem deductionItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, deductionItem)){
			return form(deductionItem, model);
		}
		deductionItemService.save(deductionItem);
		addMessage(redirectAttributes, "保存抵扣项成功");
		return "redirect:"+Global.getAdminPath()+"/base/deductionItem/?repage";
	}
	
	@RequiresPermissions("base:deductionItem:edit")
	@RequestMapping(value = "delete")
	public String delete(DeductionItem deductionItem, RedirectAttributes redirectAttributes) {
		deductionItemService.delete(deductionItem);
		addMessage(redirectAttributes, "删除抵扣项成功");
		return "redirect:"+Global.getAdminPath()+"/base/deductionItem/?repage";
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(HttpServletResponse response) {

		List<DeductionItem> list = deductionItemService.findList(new DeductionItem());

		return this.getMapList(list);
	}
	
	private List<Map<String, Object>> getMapList(List<DeductionItem> list){
		
		List<Map<String, Object>> mapList = Lists.newArrayList();

		for(DeductionItem e : list){
			
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", "");
			map.put("pIds", "");
			map.put("name", e.getName());
			mapList.add(map);

		}		
		
		return mapList;
	}

}