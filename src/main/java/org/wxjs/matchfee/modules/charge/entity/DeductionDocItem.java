/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.entity;


import org.wxjs.matchfee.common.persistence.DataEntity;
import org.wxjs.matchfee.modules.base.entity.DeductionItem;

/**
 * 抵扣项文件Entity
 * @author GLQ
 * @version 2017-12-05
 */
public class DeductionDocItem extends DataEntity<DeductionDocItem> {
	
	private static final long serialVersionUID = 1L;
	private DeductionDoc doc;		// 文档代码 父类
	private DeductionItem item;		// 抵扣项代码
	private String area;		// 面积（平米）
	private String money;		// 金额（元）
	
	public DeductionDocItem() {
		super();
	}

	public DeductionDocItem(String id){
		super(id);
	}

	public DeductionDocItem(DeductionDoc doc){
		this.doc = doc;
	}
	
	public DeductionDocItem(DeductionItem item){
		this.item = item;
	}

	public DeductionDoc getDoc() {
		return doc;
	}

	public void setDoc(DeductionDoc doc) {
		this.doc = doc;
	}
	
	
	public DeductionItem getItem() {
		return item;
	}

	public void setItem(DeductionItem item) {
		this.item = item;
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