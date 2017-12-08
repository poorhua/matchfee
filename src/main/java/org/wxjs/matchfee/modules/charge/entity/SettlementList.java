package org.wxjs.matchfee.modules.charge.entity;

import java.util.List;

public class SettlementList {
	
	private Charge charge;
	private List<ProjectLicense> projectLicenses;
	private List<OpinionBookItem> opinionBookItems;
	private List<DeductionDocItem> deductionDocItems;
	private List<ProjectDeduction> projectDeductions;
	private List<PayTicket> payTickets;
	
	public Charge getCharge() {
		return charge;
	}
	public void setCharge(Charge charge) {
		this.charge = charge;
	}
	public List<ProjectLicense> getProjectLicenses() {
		return projectLicenses;
	}
	public void setProjectLicenses(List<ProjectLicense> projectLicenses) {
		this.projectLicenses = projectLicenses;
	}
	public List<OpinionBookItem> getOpinionBookItems() {
		return opinionBookItems;
	}
	public void setOpinionBookItems(List<OpinionBookItem> opinionBookItems) {
		this.opinionBookItems = opinionBookItems;
	}
	public List<DeductionDocItem> getDeductionDocItems() {
		return deductionDocItems;
	}
	public void setDeductionDocItems(List<DeductionDocItem> deductionDocItems) {
		this.deductionDocItems = deductionDocItems;
	}
	public List<ProjectDeduction> getProjectDeductions() {
		return projectDeductions;
	}
	public void setProjectDeductions(List<ProjectDeduction> projectDeductions) {
		this.projectDeductions = projectDeductions;
	}
	public List<PayTicket> getPayTickets() {
		return payTickets;
	}
	public void setPayTickets(List<PayTicket> payTickets) {
		this.payTickets = payTickets;
	}

}
