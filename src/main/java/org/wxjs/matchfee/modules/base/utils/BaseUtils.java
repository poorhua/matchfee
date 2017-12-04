package org.wxjs.matchfee.modules.base.utils;

import java.util.List;

import org.wxjs.matchfee.common.utils.SpringContextHolder;
import org.wxjs.matchfee.modules.base.dao.DeductionItemDao;
import org.wxjs.matchfee.modules.base.entity.DeductionItem;


public class BaseUtils {
	
	private static DeductionItemDao deductionItemDao = SpringContextHolder.getBean(DeductionItemDao.class);
	
	public static List<DeductionItem> getDeductionItems(){
		
		return deductionItemDao.findAllList(new DeductionItem());
		
	}

}
