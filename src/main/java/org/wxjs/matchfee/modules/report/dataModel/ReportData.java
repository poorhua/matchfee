package org.wxjs.matchfee.modules.report.dataModel;

public class ReportData {
	
	private String title;
	private ChartData chartData;
	private String tableLabel;
	private TableData tableData;
	
	private String chartType;
	
	public ReportData(){
		
	}
	
	public ReportData(String title, String chartType, ChartData chartData, String tableLabel, TableData tableData){
		this.title = title;
		this.chartType = chartType;
		this.chartData = chartData;
		this.tableLabel = tableLabel;
		this.tableData = tableData;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ChartData getChartData() {
		return chartData;
	}
	public void setChartData(ChartData chartData) {
		this.chartData = chartData;
	}	
	
	public String getTableLabel() {
		return tableLabel;
	}

	public void setTableLabel(String tableLabel) {
		this.tableLabel = tableLabel;
	}

	public TableData getTableData() {
		return tableData;
	}
	public void setTableData(TableData tableData) {
		this.tableData = tableData;
	}

	public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

}
