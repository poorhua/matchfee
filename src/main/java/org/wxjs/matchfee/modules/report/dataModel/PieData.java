package org.wxjs.matchfee.modules.report.dataModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.wxjs.matchfee.common.config.Global;
import org.wxjs.matchfee.common.mapper.JsonMapper;
import org.wxjs.matchfee.common.utils.Util;

public class PieData extends ChartData{

	private PieSeries series;

	public PieSeries getSeries() {
		return series;
	}
	public void setSeries(PieSeries series) {
		this.series = series;
	}
	
	public String getTitle(){
		return super.getTitle() +" " +Global.getConfig("TOTAL")+ ":" +this.getTotalInt();
	}

	public void loadData(Collection<HashMap<String, Object>> col, String seresName){
		this.series = new PieSeries();
		
		this.series.setName(seresName);
		
		List<PieNameY> data = new ArrayList<PieNameY>();
		
		for(HashMap<String, Object> hm: col){
			String key = Util.getString(hm.get("ITEMNAME"));
			float value = Util.getFloat(hm.get("ITEMVALUE"));
			
			PieNameY ny = new PieNameY(key, value);
			data.add(ny);
			
			this.total += value;
		}
		
		this.series.setData(data);
		
	}
	
	public String getJsonSeries(){
		return JsonMapper.toJsonString(this.series);
	}
	
	public static void main(String[] args){
		PieData pd = new PieData();
		pd.setTitle("my pie chart");
		
		PieSeries ps = new PieSeries();
		//ps.setColorByPoint(true);
		ps.setName("fault analysis");
		
		List<PieNameY> data = new ArrayList<PieNameY>();
		
		PieNameY ny = new PieNameY("AA", 41.0f);
		data.add(ny);
		ny = new PieNameY("BB", 21.0f);
		data.add(ny);
		ny = new PieNameY("CC", 11.0f);
		data.add(ny);
		ny = new PieNameY("DD", 27.0f);
		data.add(ny);
		
		ps.setData(data);
		
		pd.setSeries(ps);
		
		System.out.println(pd.getJsonSeries());
		
		
	}
	
		
}



