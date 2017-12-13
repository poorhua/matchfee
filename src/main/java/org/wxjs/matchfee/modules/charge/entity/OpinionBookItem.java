/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.entity;

import org.wxjs.matchfee.common.config.Global;
import org.wxjs.matchfee.common.persistence.DataEntity;
import org.wxjs.matchfee.common.utils.Util;
import org.wxjs.matchfee.modules.base.entity.DeductionItem;

/**
 * 条件意见书Entity
 * @author GLQ
 * @version 2017-11-27
 */
public class OpinionBookItem extends DataEntity<OpinionBookItem> {
	
	private static final long serialVersionUID = 1L;
	private OpinionBook doc;		// 文档代码 父类
	private DeductionItem item;		// 抵扣项
	private String area;		// 面积（平米）
	private String money;		// 金额（元）
	
	public OpinionBookItem() {
		super();
	}

	public OpinionBookItem(String id){
		super(id);
	}

	public OpinionBookItem(OpinionBook doc){
		this.doc = doc;
	}
	
	public OpinionBook getDoc() {
		return doc;
	}

	public void setDoc(OpinionBook doc) {
		this.doc = doc;
	}
	
	public DeductionItem getItem() {
		return item;
	}

	public void setItem(DeductionItem item) {
		this.item = item;
	}

	public String getArea() {
		return Util.formatDecimal(area, Global.DecimalFormat);
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public String getMoney() {
		return Util.formatDecimal(money, Global.DecimalFormat);
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
}