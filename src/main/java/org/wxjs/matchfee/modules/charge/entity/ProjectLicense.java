/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;

import org.wxjs.matchfee.common.config.Global;
import org.wxjs.matchfee.common.persistence.DataEntity;
import org.wxjs.matchfee.common.utils.Util;

/**
 * 征收Entity
 * @author GLQ
 * @version 2017-12-06
 */
public class ProjectLicense extends DataEntity<ProjectLicense> {
	
	private static final long serialVersionUID = 1L;
	private Charge charge;		// 征收代码 父类
	private String name;		// 名称
	private Project project;		// 项目代码
	private String documentNo;		// 文件编号
	private String path;		// 保存路径
	private Date documentDate;		// 文档日期
	private String upArea;		// 地上面积（平米）
	private String downArea;		// 地下面积（平米）
	
	public ProjectLicense() {
		super();
	}

	public ProjectLicense(String id){
		super(id);
	}

	public ProjectLicense(Charge charge){
		this.charge = charge;
	}

	public Charge getCharge() {
		return charge;
	}

	public void setCharge(Charge charge) {
		this.charge = charge;
	}
	
	@Length(min=1, max=64, message="名称长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	@Length(min=1, max=64, message="文件编号长度必须介于 1 和 64 之间")
	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	
	@Length(min=1, max=256, message="保存路径长度必须介于 1 和 256 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="文档日期不能为空")
	public Date getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}
	
	public String getUpArea() {
		return upArea;
	}

	public void setUpArea(String upArea) {
		this.upArea = upArea;
	}
	
	public String getDownArea() {
		return downArea;
	}

	public void setDownArea(String downArea) {
		this.downArea = downArea;
	}
	
	public float getTotalMoney(){
		float totalArea = Util.getFloat(this.upArea) + Util.getFloat(this.downArea);
		return this.getTotalArea() * Util.getFloat(Global.getConfig("matchfee.basis"));
	}
	
	public String getTotalMoneyDisplay(){
		return Util.formatDecimal(this.getTotalMoney(), Global.DecimalFormat);
	}
	
	public float getTotalArea(){
		return Util.getFloat(this.upArea) + Util.getFloat(this.downArea);
	}
	
	public String getTotalAreaDisplay(){
		return Util.formatDecimal(this.getTotalArea(), Global.DecimalFormat);
	}
}