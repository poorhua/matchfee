package org.wxjs.matchfee.modules.report.dataModel;

public class PieNameY{
	private String name;
	private float y;
	
	public PieNameY(){
		
	}
	
	public PieNameY(String name, float y){
		this.name = name;
		this.y = y;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}

}