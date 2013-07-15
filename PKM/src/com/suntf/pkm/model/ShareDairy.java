package com.suntf.pkm.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ShareDairy
{
	private int id;
	private String name;
	private String title;
	private String content;
	private String time;
	private long etime;
	
	public ShareDairy(JSONObject dairy) throws JSONException
	{
		this.id = dairy.getInt("id");
		this.name = dairy.getString("name");
		this.title = dairy.getString("title");
		this.content = dairy.getString("content");
		this.time = dairy.getString("time");
		this.etime = dairy.getLong("etime");
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	public long getEtime()
	{
		return etime;
	}
	public void setEtime(long etime)
	{
		this.etime = etime;
	}
}
