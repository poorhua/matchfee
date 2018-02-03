package org.wxjs.matchfee.modules.charge.utils;

import org.apache.commons.lang3.StringUtils;

public class EntityUtils {
	
	public static String duplicateTag(String item){
		String rst = item;
		if(!StringUtils.isBlank(item) && item.endsWith("_duplicate")){
			rst = item.substring(0, item.indexOf("_duplicate"))+" <font color='red'>(重复)</font>";
		}
		
		return rst;
	}

}
