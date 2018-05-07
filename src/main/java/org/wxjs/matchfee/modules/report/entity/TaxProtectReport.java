package org.wxjs.matchfee.modules.report.entity;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.httpclient.util.DateUtil;
import org.wxjs.matchfee.common.config.Global;
import org.wxjs.matchfee.common.utils.Util;
import org.wxjs.matchfee.common.utils.excel.annotation.ExcelField;
import org.wxjs.matchfee.modules.charge.entity.Charge;

public class TaxProtectReport extends Charge{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1722300717334924467L;

	private String seq = "";

	private String areaLevel = "市本级";
	
	private String constructLicenseNo = "暂无";
	
	private String constructArea;
	
	private String projectLicense;
	
	private String matchArea;//相关配套面积
	
	private String landPay;//国土代收费
	
	private String projectDeduction;//其他减项

	@ExcelField(title="序号", type=1, align=2, sort=10)
	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	@ExcelField(title="项目名称", type=1, align=2, sort=20)
	public String getProjectName(){
		return this.getProject().getPrjName();
	}
	
	@ExcelField(title="项目位置", type=1, align=2, sort=30)
	public String getProjectAddress(){
		return this.getProject().getPrjAddress();
	}
	
	@ExcelField(title="建设单位", type=1, align=2, sort=40)
	public String getBuildCorpName(){
		return this.getProject().getBuildCorpName();
	}

	@ExcelField(title="所属级次", type=1, align=2, sort=50)
	public String getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(String areaLevel) {
		this.areaLevel = areaLevel;
	}

	@ExcelField(title="施工许可证编号", type=1, align=2, sort=80)
	public String getConstructLicenseNo() {
		return constructLicenseNo;
	}

	public void setConstructLicenseNo(String constructLicenseNo) {
		this.constructLicenseNo = constructLicenseNo;
	}

	@ExcelField(title="建筑面积", type=1, align=3, sort=60)
	public String getConstructArea() {
		return Util.formatMoneyArea(constructArea);
	}

	public void setConstructArea(String constructArea) {
		this.constructArea = constructArea;
	}

	@ExcelField(title="规划许可证", type=1, align=2, sort=70)
	public String getProjectLicense() {
		return projectLicense;
	}

	public void setProjectLicense(String projectLicense) {
		this.projectLicense = projectLicense;
	}

	@ExcelField(title="征收金额的计算依据", type=1, align=2, sort=110)
	public String getChargeGist() {
		
		String gist = Global.getConfig("CHARGE_GIST");

		MessageFormat mf = new MessageFormat(gist);
		
		String[] params = {
				Util.getString(this.constructArea, "0.00"),
				Util.getString(this.matchArea, "0.00"),
				Util.getString(this.landPay, "0.00"),
				Util.getString(this.projectDeduction, "0.00")
				};		
		return mf.format(params);
	}
	
	@ExcelField(title="征收金额", type=1, align=3, sort=90)
	public String getChargeMoney() {
		return Util.formatMoneyArea(super.getPayMoney());
	}
	
	@ExcelField(title="征收时间", type=1, align=2, sort=120)
	public String getChargeDate() {
		return DateUtil.formatDate(super.getMaxPayDate(),"yyyy-MM-dd");
	}

	public String getMatchArea() {
		return matchArea;
	}

	public void setMatchArea(String matchArea) {
		this.matchArea = matchArea;
	}

	public String getLandPay() {
		return landPay;
	}

	public void setLandPay(String landPay) {
		this.landPay = landPay;
	}

	public String getProjectDeduction() {
		return projectDeduction;
	}

	public void setProjectDeduction(String projectDeduction) {
		this.projectDeduction = projectDeduction;
	}
	
}
