package org.wxjs.matchfee.modules.report.dataModel;

public class TableColModel {
	private String label;
	private String name;
	private int width;
	
	private boolean key = false;
	
	private boolean sortable = false;
	
	public TableColModel(String label, String name, int width){
		this.label = label;
		this.name = name;
		this.width = width;
	}
	
	public TableColModel(String label, String name, int width, boolean key){
		this(label, name, width);
		this.key = key;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public boolean isKey() {
		return key;
	}
	public void setKey(boolean key) {
		this.key = key;
	}

	public boolean isSortable() {
		return sortable;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

}
