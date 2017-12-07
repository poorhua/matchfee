package org.wxjs.matchfee.modules.report.dataModel;

import java.util.List;

public class ColumnSeries {
	
	private String name;
	private List<Float> data;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Float> getData() {
		return data;
	}
	public void setData(List<Float> data) {
		this.data = data;
	}

}
