package org.wxjs.matchfee.modules.message;

import org.wxjs.matchfee.modules.base.utils.KeyValue;


public interface MessageSender {
	
	public KeyValue send(String mobiles, String message) throws Exception;

}
