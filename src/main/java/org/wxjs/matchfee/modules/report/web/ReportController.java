/**
 * Copyright &copy; 2016-2018 千里目.
 */
package org.wxjs.matchfee.modules.report.web;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wxjs.matchfee.common.web.BaseController;
import org.wxjs.matchfee.modules.report.dataModel.ReportData;
import org.wxjs.matchfee.modules.report.entity.ReportParam;
import org.wxjs.matchfee.modules.report.service.ReportService;


/**
 * FaultReportController
 * @author sw
 * @version 2016-05-01
 */
@Controller
@RequestMapping(value = "${adminPath}/report/charge")
public class ReportController extends BaseController {	
	
	@Autowired
	ReportService reportService;
	
	@RequiresPermissions("report:charge:view")
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

}