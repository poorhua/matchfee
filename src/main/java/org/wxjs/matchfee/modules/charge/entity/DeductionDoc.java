/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;

import org.wxjs.matchfee.common.persistence.DataEntity;

/**
 * 抵扣项文件Entity
 * @author GLQ
 * @version 2017-11-25
 */
public class DeductionDoc extends DataEntity<DeductionDoc> {
	
	private static final long serialVersionUID = 1L;
	private String chargeId;		// 征收代码
	private String name;		// 名称
	private String prjNum;		// 项目代码
	private String documentNo;		// 文件编号
	private String documentType;		// 文件类型
	private String path;		// 保存路径
	private Date documentDate;		// 文档日期
	
	private List<DeductionDocItem> itemList;
	
	public DeductionDoc() {
		super();
	}

	public DeductionDoc(String id){
		super(id);
	}

	@Length(min=1, max=11, message="征收代码长度必须介于 1 和 11 之间")
	public String getChargeId() {
		return chargeId;
	}

	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}
	
	@Length(min=1, max=64, message="名称长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=64, message="项目代码长度必须介于 1 和 64 之间")
	public String getPrjNum() {
		return prjNum;
	}

	public void setPrjNum(String prjNum) {
		this.prjNum = prjNum;
	}
	
	@Length(min=1, max=8, message="文件编号长度必须介于 1 和 8 之间")
	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	
	@Length(min=1, max=8, message="文件类型长度必须介于 1 和 8 之间")
	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	
	@Length(min=1, max=256, message="保存路径长度必须介于 1 和 256 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="文档日期不能为空")
	public Date getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}

	public List<DeductionDocItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<DeductionDocItem> itemList) {
		this.itemList = itemList;
	}
	
}