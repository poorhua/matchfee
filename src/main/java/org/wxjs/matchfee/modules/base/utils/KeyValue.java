package org.wxjs.matchfee.modules.base.utils;

public class KeyValue {
	
	private String key = "";
	private String value = "";
	private String memo = "";
	
	public KeyValue(){
		
	}
	
	public KeyValue(String key, String value){
		this.key = key;
		this.value = value;
	}
	
	public KeyValue(String key, String value, String memo){
		this.key = key;
		this.value = value;
		this.memo = memo;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
