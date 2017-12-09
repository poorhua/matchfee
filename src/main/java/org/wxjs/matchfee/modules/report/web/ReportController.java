/**
 * Copyright &copy; 2016-2018 千里目.
 */
package org.wxjs.matchfee.modules.report.web;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wxjs.matchfee.common.web.BaseController;
import org.wxjs.matchfee.modules.report.dataModel.ReportData;
import org.wxjs.matchfee.modules.report.entity.ReportParam;
import org.wxjs.matchfee.modules.report.service.ReportService;
import org.wxjs.matchfee.modules.sys.entity.User;
import org.wxjs.matchfee.modules.sys.utils.UserUtils;

import com.google.gson.Gson;


/**
 * ReportController
 * @author GLQ
 * @version 2017-12-07
 */
@Controller
@RequestMapping(value = "${adminPath}/report/report")
public class ReportController extends BaseController {	
	
	@Autowired
	ReportService reportService;
	
	@RequiresPermissions("report:report:view")
	@RequestMapping(value = {"query"})
	public String query(ReportParam param, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		if(param==null){
			param = new ReportParam();
		}

		if(param.getDateFrom()==null){
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.MONTH, -12);
			param.setDateFrom(cal.getTime());
		}
		
		if(param.getDateTo()==null){
			Calendar cal=Calendar.getInstance();
			param.setDateTo(cal.getTime());
		}
		
		ReportData data = reportService.getReport(param);
		
		model.addAttribute("reportData", data);
		
		model.addAttribute("reportParam", param);
		
		return "modules/report/report";
	}
	
	@RequiresPermissions("report:report:view")
	@RequestMapping(value = {"dashboard"})
	public String dashboard(ReportParam param, HttpServletRequest request, HttpServletResponse response, Model model) {
		//第一个用户框数据框
		User currentUser=UserUtils.getUser();
		model.addAttribute("user", currentUser);
		//第二个征收汇总数据框
		Collection<HashMap<String, Object>> map1=reportService.dashboardChargeStatus();
		String chargeStatusData=new Gson().toJson(map1);
		model.addAttribute("chargeStatusData", chargeStatusData);
		//第三个申报数按月统计数据框
		Collection<HashMap<String, Object>> map2=reportService.dashboardDeclare();
		String declareData=new Gson().toJson(map2);
		model.addAttribute("declareData", declareData);
		//第四个征收金额按月统计框
		Collection<HashMap<String, Object>> map3=reportService.dashboardChargeMoney();
		String chargeMoneyData=new Gson().toJson(map3);
		model.addAttribute("chargeMoneyData", chargeMoneyData);
		
		return "modules/report/dashboard";
	}

}