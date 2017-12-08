package org.wxjs.matchfee.modules.message;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.wxjs.matchfee.modules.base.jdbc.SmsDAOHelper;
import org.wxjs.matchfee.modules.base.utils.KeyValue;


public class DbMessageSender implements MessageSender {

	@Override
	public KeyValue send(String mobiles, String message) throws SQLException {
		List<String> sqls = new ArrayList<String>();
		String[] items = mobiles.split(",");
		int index = 1;
		for(String item: items){
			if(!item.equals("")){
				StringBuffer buffer = new StringBuffer();
				//String id = "AFC-"+System.currentTimeMillis()+"-"+index;
				
				buffer.append(" insert into sms_outbox ( extcode, destaddr, messagecontent,"); 
				buffer.append(" reqdeliveryreport,msgfmt,sendmethod,requesttime,applicationid)");
				buffer.append(" VALUES ('55', '"+item+"',"); 
				buffer.append(" '"+message+"', 1, 15, 0, now(), 'matchfee')");
				
				sqls.add(buffer.toString());
				index++;
			}
			
		}
		
		int effects = SmsDAOHelper.executeSQL(sqls);
		
		KeyValue kv = new KeyValue();
		kv.setKey("0");
		kv.setValue("Post "+effects+" items to SMS database");
		
		return kv;
		
	}

}
