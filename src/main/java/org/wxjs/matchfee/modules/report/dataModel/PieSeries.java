package org.wxjs.matchfee.modules.report.dataModel;

import java.util.List;

public class PieSeries{
	private String name;
	private boolean colorByPoint = true;
	private List<PieNameY> data;

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public boolean getColorByPoint() {
		return colorByPoint;
	}


	public void setColorByPoint(boolean colorByPoint) {
		this.colorByPoint = colorByPoint;
	}


	public List<PieNameY> getData() {
		return data;
	}


	public void setData(List<PieNameY> data) {
		this.data = data;
	}
	
}
