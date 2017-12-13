/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.base.web;

import java.util.List;

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
import org.wxjs.matchfee.modules.base.entity.OperationLog;
import org.wxjs.matchfee.modules.base.service.OperationLogService;


/**
 * 操作日志Controller
 * @author GLQ
 * @version 2017-12-13
 */
@Controller
@RequestMapping(value = "${adminPath}/base/operationLog")
public class OperationLogController extends BaseController {

	@Autowired
	private OperationLogService operationLogService;
	
	@ModelAttribute
	public OperationLog get(@RequestParam(required=false) String id) {
		OperationLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = operationLogService.get(id);
		}
		if (entity == null){
			entity = new OperationLog();
		}
		return entity;
	}
	

	@RequestMapping(value = {"list", ""})
	public String list(OperationLog operationLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OperationLog> page = operationLogService.findPage(new Page<OperationLog>(request, response), operationLog); 
		model.addAttribute("page", page);
		return "modules/base/operationLogList";
	}


	@RequestMapping(value = "form")
	public String form(OperationLog operationLog, Model model) {
		model.addAttribute("operationLog", operationLog);
		return "modules/base/operationLogForm";
	}


	@RequestMapping(value = "save")
	public String save(OperationLog operationLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, operationLog)){
			return form(operationLog, model);
		}
		operationLogService.save(operationLog);
		addMessage(redirectAttributes, "保存操作日志成功");
		return "redirect:"+Global.getAdminPath()+"/base/operationLog/?repage";
	}
	

	@RequestMapping(value = "delete")
	public String delete(OperationLog operationLog, RedirectAttributes redirectAttributes) {
		operationLogService.delete(operationLog);
		addMessage(redirectAttributes, "删除操作日志成功");
		return "redirect:"+Global.getAdminPath()+"/base/operationLog/?repage";
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "queryApprovelogs")
	public List<OperationLog> queryApprovelogs(@RequestParam(required=true) String chargeId, HttpServletResponse response) {
		
		OperationLog operationLog = new OperationLog();
		operationLog.setChargeId(chargeId);
		operationLog.setRemarks("1");
		
		List<OperationLog> list = operationLogService.findList(operationLog);

		return list;
	}

}