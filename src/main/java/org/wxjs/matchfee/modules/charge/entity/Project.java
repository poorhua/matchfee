package org.wxjs.matchfee.modules.charge.entity;

import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wxjs.matchfee.common.persistence.DataEntity;

import com.google.common.collect.Lists;

public class Project extends DataEntity<Project> {
	
	private static final long serialVersionUID = 1L;
	
	private String prjNum;

	private String prjName;

	private String buildCorpName;

	private String buildCorpCode;

	private String prjAddress;
	
	private String prjPassword;
	
	private String contact;
	
	private String mobile;
	
	//for query param
	private String projectLicense;
	
	//charge history
	private String chargeHistory;
	
	private List<Charge> charges = Lists.newArrayList();
	
	public Project(){
		
	}

	public String getPrjNum() {
		return prjNum;
	}

	public void setPrjNum(String prjNum) {
		this.prjNum = prjNum;
	}

	public String getPrjPassword() {
		return prjPassword;
	}

	public void setPrjPassword(String prjPassword) {
		this.prjPassword = prjPassword;
	}

	public String getPrjName() {
		return StringEscapeUtils.unescapeHtml4(prjName);
	}

	public void setPrjName(String prjName) {
		this.prjName = prjName;
	}

	public String getBuildCorpName() {
		return StringEscapeUtils.unescapeHtml4(buildCorpName);
	}

	public void setBuildCorpName(String buildCorpName) {
		this.buildCorpName = buildCorpName;
	}

	public String getBuildCorpCode() {
		return buildCorpCode;
	}

	public void setBuildCorpCode(String buildCorpCode) {
		this.buildCorpCode = buildCorpCode;
	}

	public String getPrjAddress() {
		return StringEscapeUtils.unescapeHtml4(prjAddress);
	}

	public void setPrjAddress(String prjAddress) {
		this.prjAddress = prjAddress;
	}
	
    public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProjectLicense() {
		return projectLicense;
	}

	public void setProjectLicense(String projectLicense) {
		this.projectLicense = projectLicense;
	}

	public String getChargeHistory() {
		return chargeHistory;
	}

	public void setChargeHistory(String chargeHistory) {
		this.chargeHistory = chargeHistory;
	}
	
	public boolean getIsMultiplePrjNum(){
		return !StringUtils.isBlank(this.prjNum) && this.prjNum.contains(",");
	}
	
	public List<String> getPrjNums(){
		List<String> list = Lists.newArrayList();
		if(!StringUtils.isBlank(this.prjNum)){
			String[] strs = this.prjNum.split(",");
			for(String str : strs){
				list.add(str);
			}
		}
		return list;
	}

	public List<Charge> getCharges() {
		return charges;
	}

	public void setCharges(List<Charge> charges) {
		this.charges = charges;
	}
	
	public void addCharge(Charge charge){
		this.charges.add(charge);
	}

	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

	
}
