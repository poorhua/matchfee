package org.wxjs.matchfee.common.utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.wxjs.matchfee.modules.charge.entity.DeductionDocItem;
import org.wxjs.matchfee.modules.charge.entity.PayTicket;
import org.wxjs.matchfee.modules.charge.entity.ProjectDeduction;
import org.wxjs.matchfee.modules.charge.entity.ProjectLicense;
import org.wxjs.matchfee.modules.charge.entity.SettlementList;


public class ExportExcelFile {
	public static void write(String fileName, SettlementList settementList, HttpServletResponse response) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("table"); // 创建table工作薄
		HSSFRow row;
		HSSFCell cell;
		int column = 0;
		// 生成标题
		Object[] title = { "无锡市城市基础设施配套费结算清单", "", "", "" };
		row = sheet.createRow(column);
		for (int j = 0; j < 4; j++) {
			cell = row.createCell(j);// 根据表格行创建单元格
			cell.setCellValue(String.valueOf(title[j]));
			if (j % 2 == 0) {
				fontGenerate(wb, cell, row, sheet, column, 30, 15, true, false, true);
			} else {
				fontGenerate(wb, cell, row, sheet, column, 30, 15, false, false, true);
			}
		}
		column += 3;

		// 项目主体信息
		Object[][] titleContent = {
				{ "申报代码：", settementList.getCharge().getId(), "申报日期：",
						settementList.getCharge().getReportDateYYYYMMDD() },
				{ "项目编号：", settementList.getCharge().getProject().getPrjNum(), "项目名称：",
						settementList.getCharge().getProject().getPrjName() },
				{ "建设单位代码：", settementList.getCharge().getProject().getBuildCorpCode(), "建设单位名称：",
						settementList.getCharge().getProject().getBuildCorpName() },
				{ "项目地址：", settementList.getCharge().getProject().getPrjAddress(), "状态：",
						settementList.getCharge().getStatusLabel() } };
		for (int i = 0; i < titleContent.length; i++) {
			row = sheet.createRow(column);
			for (int j = 0; j < 4; j++) {
				cell = row.createCell(j);// 根据表格行创建单元格
				cell.setCellValue(String.valueOf(titleContent[i][j]));
				if (j % 2 == 0) {
					fontGenerate(wb, cell, row, sheet, column, 15, 8, true, false, true);
				} else {
					fontGenerate(wb, cell, row, sheet, column, 15, 8, false, false, true);
				}
			}
			column++;
		}
		column += 2;

		// 主题标题
		Object[] mainProTitle = { "条目", "建筑面积（平米）", "金额（元）", "备注" };
		row = sheet.createRow(column);
		for (int j = 0; j < 4; j++) {
			cell = row.createCell(j);// 根据表格行创建单元格
			cell.setCellValue(String.valueOf(mainProTitle[j]));
			fontGenerate(wb, cell, row, sheet, column, 15, 8, true, true, true);
		}
		column++;

		// 结算项目
		Object[] payProTitle = { "*结算项目", "", "", "" };
		row = sheet.createRow(column);
		for (int j = 0; j < 4; j++) {
			cell = row.createCell(j);// 根据表格行创建单元格
			cell.setCellValue(String.valueOf(payProTitle[j]));
			fontGenerate(wb, cell, row, sheet, column,15, 8, true, true, false);
		}
		column++;

		List<String[]> items = new ArrayList<String[]>();
		items.clear();
		for(ProjectLicense item : settementList.getProjectLicenses()){
			items.add(new String[]{item.getName(), item.getTotalArea() +"", item.getTotalMoney() +"", item.getRemarks()});
		}
		for (int i = 0; i < items.size(); i++) {
			row = sheet.createRow(column);
			for (int j = 0; j < 4; j++) {
				cell = row.createCell(j);// 根据表格行创建单元格
				cell.setCellValue(String.valueOf(items.get(i)[j]));
				fontGenerate(wb, cell, row, sheet, column, 15, 8, false, true, true);
			}
			column++;
		}

		// 抵扣项（设计院证明）
		Object[] deductionTitle = { "*抵扣项（设计院证明）", "", "", "" };
		row = sheet.createRow(column);
		for (int j = 0; j < 4; j++) {
			cell = row.createCell(j);// 根据表格行创建单元格
			cell.setCellValue(String.valueOf(deductionTitle[j]));
			fontGenerate(wb, cell, row, sheet, column, 15, 8, true, true, false);
		}
		column++;
		
		items.clear();
		for(DeductionDocItem item : settementList.getDeductionDocItems()){
			items.add(new String[]{item.getItem().getName(), item.getArea() +"", item.getMoney() +"", item.getRemarks()});
		}
		for (int i = 0; i < items.size(); i++) {
			row = sheet.createRow(column);
			for (int j = 0; j < 4; j++) {
				cell = row.createCell(j);// 根据表格行创建单元格
				cell.setCellValue(String.valueOf(items.get(i)[j]));
				fontGenerate(wb, cell, row, sheet, column, 15, 8, false, true, true);
			}
			column++;
		}

		int deductionLine = column;

		// 其他减项
		Object[] otherDeductionTitle = { "*其他减项", "", "", "" };
		row = sheet.createRow(column);
		for (int j = 0; j < 4; j++) {
			cell = row.createCell(j);// 根据表格行创建单元格
			cell.setCellValue(String.valueOf(otherDeductionTitle[j]));
			fontGenerate(wb, cell, row, sheet, column, 15, 8, true, true, false);
		}
		column++;
		
		
		items.clear();
		for(ProjectDeduction item : settementList.getProjectDeductions()){
			items.add(new String[]{item.getName(), item.getArea() +"", item.getMoney() +"", item.getRemarks()});
		}
		for (int i = 0; i < items.size(); i++) {
			row = sheet.createRow(column);
			for (int j = 0; j < 4; j++) {
				cell = row.createCell(j);// 根据表格行创建单元格
				cell.setCellValue(String.valueOf(items.get(i)[j]));
				fontGenerate(wb, cell, row, sheet, column, 15, 8, false, true, true);
			}
			column++;
		}

		int otherDeductionLine = column;

		// 缴费情况
		Object[] moneyTitle = { "*缴费情况", "", "", "" };
		row = sheet.createRow(column);
		for (int j = 0; j < 4; j++) {
			cell = row.createCell(j);// 根据表格行创建单元格
			cell.setCellValue(String.valueOf(moneyTitle[j]));
			fontGenerate(wb, cell, row, sheet, column, 15, 8, true, true, false);
		}

		int moneyLine = column;

		// 结算金额
		Object[] needPayPro = { "*结算金额", "", String.valueOf(settementList.getCharge().getCalMoney()), "" };
		row = sheet.createRow(column);
		for (int j = 0; j < 4; j++) {
			cell = row.createCell(j);// 根据表格行创建单元格
			cell.setCellValue(String.valueOf(needPayPro[j]));
			fontGenerate(wb, cell, row, sheet, column, 15,8, false, true, false);
		}
		column++;

		// 具体缴费情况
		items.clear();
		for(PayTicket item : settementList.getPayTickets()){
			items.add(new String[]{"缴费", "", item.getMoney() +"", item.getRemarks()});
		}
		for (int i = 0; i < items.size(); i++) {
			row = sheet.createRow(column);
			for (int j = 0; j < 4; j++) {
				cell = row.createCell(j);// 根据表格行创建单元格
				cell.setCellValue(String.valueOf(items.get(i)[j]));
				fontGenerate(wb, cell, row, sheet, column, 15, 8, false, true, true);
			}
			column++;
		}

		// 多少缴费
		String gapHint = "多缴费";
		if(settementList.getCharge().getMoneyGap()<0){
			gapHint = "少缴费";
		}
		Object[][] littlePro = { {gapHint, "", String.valueOf(settementList.getCharge().getMoneyGap()), "" } };
		for (int i = 0; i < littlePro.length; i++) {
			row = sheet.createRow(column);
			for (int j = 0; j < 4; j++) {
				cell = row.createCell(j);// 根据表格行创建单元格
				cell.setCellValue(String.valueOf(littlePro[i][j]));
				fontGenerate(wb, cell, row, sheet, column, 15, 8, false, true, true);
			}
			column++;
		}

		CellRangeAddress region1 = new CellRangeAddress(0, // first row
				0, // last row
				0, // first column
				3 // last column
		);

		CellRangeAddress region2 = new CellRangeAddress(10, // first row
				10, // last row
				0, // first column
				3 // last column
		);

		CellRangeAddress region3 = new CellRangeAddress(deductionLine, // first
																		// row
				deductionLine, // last row
				0, // first column
				3 // last column
		);

		CellRangeAddress region4 = new CellRangeAddress(otherDeductionLine, // first
																			// row
				otherDeductionLine, // last row
				0, // first column
				3 // last column
		);

		CellRangeAddress region5 = new CellRangeAddress(moneyLine, // first row
				moneyLine, // last row
				0, // first column
				3 // last column
		);

		sheet.addMergedRegion(region1);
		sheet.addMergedRegion(region2);
		sheet.addMergedRegion(region3);
		sheet.addMergedRegion(region4);
		sheet.addMergedRegion(region5);
		try {
			response.reset();
			response.setContentType("application/octet-stream; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + Encodes.urlEncode(fileName));
			wb.write(response.getOutputStream());
		} catch (Exception e) {
		}
	}

	public static void fontGenerate(HSSFWorkbook wb, HSSFCell cell, HSSFRow row, HSSFSheet sheet, int column,
			int height, int fontSize, Boolean boldFlat, Boolean borderFlat, Boolean centerFlat) {
		HSSFCellStyle cellStyle = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		if (boldFlat) {
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}
		font.setFontHeightInPoints((short) fontSize);
		cellStyle.setFont(font);
		if (borderFlat) {
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		}
		// bdIndex 边框颜色下标值
		short colorIndex = 10;
		HSSFPalette palette = wb.getCustomPalette();// 自定义颜色
		Color rgb = Color.BLACK;
		short bdIndex = colorIndex++; // 边框颜色下标值
		palette.setColorAtIndex(bdIndex, (byte) rgb.getRed(), (byte) rgb.getGreen(), (byte) rgb.getBlue());
		cellStyle.setBottomBorderColor(bdIndex);
		cellStyle.setLeftBorderColor(bdIndex);
		cellStyle.setRightBorderColor(bdIndex);
		cellStyle.setTopBorderColor(bdIndex);
		if (centerFlat) {
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		}

		cell.setCellStyle(cellStyle);

		row = sheet.getRow(column);
		row.setHeightInPoints(height);// 设置行高

		sheet.setColumnWidth(0, MSExcelUtil.pixel2WidthUnits(120));
		sheet.setColumnWidth(1, MSExcelUtil.pixel2WidthUnits(115));
		sheet.setColumnWidth(2, MSExcelUtil.pixel2WidthUnits(75));
		sheet.setColumnWidth(3, MSExcelUtil.pixel2WidthUnits(310));
	}
}
