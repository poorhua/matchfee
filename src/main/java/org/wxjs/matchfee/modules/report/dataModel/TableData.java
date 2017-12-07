package org.wxjs.matchfee.modules.report.dataModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.wxjs.matchfee.common.mapper.JsonMapper;
import org.wxjs.matchfee.common.utils.Util;

public class TableData {
	
	private List<Object> data;
	
	private List<TableColModel> colModels;

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}
	
	public List<TableColModel> getColModels() {
		return colModels;
	}

	public void setColModels(List<TableColModel> colModels) {
		this.colModels = colModels;
	}

	public void loadData(Collection<HashMap<String, Object>> col){
		this.data = new ArrayList<Object>();
		
		float total = 0;
		//get total
		for(HashMap<String, Object> hm: col){
			total += Util.getFloat(hm.get("ITEMVALUE"));
		}
		//calculate percent
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("0.0");
		for(HashMap<String, Object> hm: col){
			
			float value = Util.getFloat(hm.get("ITEMVALUE"));
			
			String percent = "0.0%";
			if(total>0){
				percent = df.format(value/total*100)+"%";
			}
			hm.put("PERCENT", percent);
			this.data.add(hm);
		}
	}
	
	public String getJsonData(){
		return JsonMapper.toJsonString(this.data);
	}
	
	public String getJsonColModel(){
		return JsonMapper.toJsonString(this.colModels);
	}

}
