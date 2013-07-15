package com.suntf.pkmserver.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ShDairy {
	private int id;
	private String name;
	private String title;
	private String content;
	private String time;
	private long etime;
	private int check;
	
	public ShDairy(int id,String name,String title,String content,String time,
			long etime,int check)
	{
		this.id = id;
		this.name = name;
		this.title = title;
		this.content = content;
		this.time = time;
		this.etime = etime;
		this.check = check;
	}
	
	public ShDairy(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public long getEtime() {
		return etime;
	}
	public void setEtime(long etime) {
		this.etime = etime;
	}
	public int getCheck() {
		return check;
	}
	public void setCheck(int check) {
		this.check = check;
	}
	
	public JSONObject toJSONObject() throws JSONException{
		JSONObject dairyj = new JSONObject();
		dairyj.put("id", this.id);
		dairyj.put("name", this.name);
		dairyj.put("title", this.title);
		dairyj.put("content", this.content);
		dairyj.put("time", this.time);
		dairyj.put("etime", this.etime);
		return dairyj;
	}
	public String toString()
	{
		return this.id+this.name+this.title+this.content+this.time+this.etime;
	}
}
