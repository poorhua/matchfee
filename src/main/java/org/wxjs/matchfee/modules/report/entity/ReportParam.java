package org.wxjs.matchfee.modules.report.entity;

import java.util.Date;

import org.wxjs.matchfee.common.persistence.BaseEntity;



public class ReportParam extends BaseEntity<ReportParam>{
	

	private String reportType;

	private Date dateFrom;
	
	private Date dateTo;

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}	

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	@Override
	public void preInsert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preUpdate() {
		// TODO Auto-generated method stub
		
	}

}
