package org.wxjs.matchfee.modules.charge.utils;

import org.apache.commons.lang3.StringUtils;

public class EntityUtils {
	
	public static String duplicateTag(String item, String duplicateFlag){
		String rst = item;
		if(!StringUtils.isBlank(item) && "1".equals(duplicateFlag)){
			rst = item + " <font color='red'>(重复)</font>";
		}
		
		return rst;
	}

}
