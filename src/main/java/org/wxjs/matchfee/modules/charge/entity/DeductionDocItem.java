/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.entity;

import org.hibernate.validator.constraints.Length;

import org.wxjs.matchfee.common.persistence.DataEntity;

/**
 * 抵扣项目Entity
 * @author GLQ
 * @version 2017-11-25
 */
public class DeductionDocItem extends DataEntity<DeductionDocItem> {
	
	private static final long serialVersionUID = 1L;
	private String docId;		// 文档代码
	private String itemId;		// 抵扣项代码
	private String area;		// 面积（平米）
	private String money;		// 金额（元）
	
	public DeductionDocItem() {
		super();
	}

	public DeductionDocItem(String id){
		super(id);
	}

	@Length(min=1, max=64, message="文档代码长度必须介于 1 和 64 之间")
	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}
	
	@Length(min=1, max=64, message="抵扣项代码长度必须介于 1 和 64 之间")
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
}