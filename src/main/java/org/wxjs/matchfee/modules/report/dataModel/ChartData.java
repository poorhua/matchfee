package org.wxjs.matchfee.modules.report.dataModel;

import java.util.Collection;
import java.util.HashMap;

public abstract class ChartData {
	
	private String title = "";
	
	protected float total = 0;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
	
	public int getTotalInt(){
		return (int)this.total;
	}
	
	public abstract void loadData(Collection<HashMap<String, Object>> col, String seresName);

}
