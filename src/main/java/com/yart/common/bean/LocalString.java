package com.yart.common.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class LocalString {
	
	public static final String DEFAULT_LOCALE = "en_US";
	
	Map<String, String> localeValueMap;
	
	public LocalString(String value) {
		initMap();
		localeValueMap.put(DEFAULT_LOCALE, value);
	}
	
	public LocalString(){
		initMap();
	}
	
	private void initMap(){
		localeValueMap = new HashMap<String, String>();
	}
	
	public String getDefaultValue(){
		return localeValueMap.get(DEFAULT_LOCALE);
	}
	
	public void addValueForLocale(String locale, String value){
		localeValueMap.put(locale, value);
	}
	
	public String getValueForLocale(String locale){
		if(locale == null || locale.isEmpty()){
			return getDefaultValue();
		} 
		return localeValueMap.get(locale);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("[");
		for(Entry<String, String> e : localeValueMap.entrySet()){
			sb.append(e.getKey()).append(":").append('\"').append(e.getValue()).append('\"').append(",");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("]");
		return sb.toString();
	}

}
