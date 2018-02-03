/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.wxjs.matchfee.modules.base.service.OperationLogService;
import org.wxjs.matchfee.modules.charge.entity.Charge;
import org.wxjs.matchfee.modules.charge.entity.ProjectDeduction;
import org.wxjs.matchfee.modules.charge.service.ChargeService;
import org.wxjs.matchfee.modules.charge.service.ProjectDeductionService;

/**
 * 项目抵扣项Controller
 * @author GLQ
 * @version 2017-12-03
 */
@Controller
@RequestMapping(value = "${adminPath}/charge/projectDeduction")
public class ProjectDeductionController extends BaseController {

	@Autowired
	private ProjectDeductionService projectDeductionService;
	
	@Autowired
	private ChargeService chargeService;
	
	
	@ModelAttribute
	public ProjectDeduction get(@RequestParam(required=false) String id) {
		ProjectDeduction entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = projectDeductionService.get(id);
		}
		if (entity == null){
			entity = new ProjectDeduction();
		}
		return entity;
	}
	
	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProjectDeduction projectDeduction, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProjectDeduction> page = projectDeductionService.findPage(new Page<ProjectDeduction>(request, response), projectDeduction); 
		model.addAttribute("page", page);
		return "modules/charge/projectDeductionList";
	}

	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = "form")
	public String form(ProjectDeduction projectDeduction, HttpSession httpSession, Model model) {
		model.addAttribute("projectDeduction", projectDeduction);
		
		String chargeId = (String)httpSession.getAttribute("chargeId");
		
		Charge charge = chargeService.get(chargeId);
		
		model.addAttribute("charge", charge);
		
		return "modules/charge/projectDeductionForm";
	}

	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "save")
	public String save(ProjectDeduction projectDeduction, HttpSession httpSession, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, projectDeduction)){
			return form(projectDeduction, httpSession, model);
		}
		try{
			projectDeductionService.save(projectDeduction);
			
			//operationLogService.log(projectDeduction.getCharge().getId(), "保存规划许可证", "成功");
			
			addMessage(redirectAttributes, "保存成功");
		}catch(DuplicateKeyException e1){
			addMessage(redirectAttributes, "保存失败。重复！");
			logger.error("save error", e1);
		}catch(Exception e2){
			addMessage(redirectAttributes, "保存失败。");
			logger.error("save error", e2);
		}
		
		
		return "redirect:"+Global.getAdminPath()+"/charge/charge/projectDeductionTab/?repage";
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "delete")
	public String delete(ProjectDeduction projectDeduction, RedirectAttributes redirectAttributes) {
		projectDeductionService.delete(projectDeduction);
		
		//operationLogService.log(projectDeduction.getCharge().getId(), "删除规划许可证", "成功");
		
		addMessage(redirectAttributes, "删除项目抵扣项成功");
		return "redirect:"+Global.getAdminPath()+"/charge/charge/projectDeductionTab/?repage";
	}
	
	@ResponseBody
	@RequestMapping(value = "documentNoExists")
	public boolean documentNoExists(String documentNo) {
		
		ProjectDeduction projectDeduction = new ProjectDeduction();
		
		projectDeduction.setDocumentNo(documentNo);
		
		List<ProjectDeduction> list = projectDeductionService.findList(projectDeduction);
		
		return (list != null && list.size() > 0);
	}

}