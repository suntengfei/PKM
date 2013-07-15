package com.suntf.pkm.model;

public class Dairy
{
	private int id;
	private String title;
	private String content;
	private int epoch;
	private String target;
	private String time;
	private int category;
	private int synchro;
	
	public Dairy(){}
	public Dairy(int id, String title, String content, int epoch,
			String target, String time, int category, int synchro)
	{
		this.id = id;
		this.title = title;
		this.content = content;
		this.epoch = epoch;
		this.target = target;
		this.time = time;
		this.category = category;
		this.synchro = synchro;
	}
	public Dairy(String title, String content, int epoch,
			String target, String time, int category, int synchro)
	{
		this.id = id;
		this.title = title;
		this.content = content;
		this.epoch = epoch;
		this.target = target;
		this.time = time;
		this.category = category;
		this.synchro = synchro;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
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
	public int getEpoch()
	{
		return epoch;
	}
	public void setEpoch(int epoch)
	{
		this.epoch = epoch;
	}
	public String getTarget()
	{
		return target;
	}
	public void setTarget(String target)
	{
		this.target = target;
	}
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	public int getCategory()
	{
		return category;
	}
	public void setCategory(int category)
	{
		this.category = category;
	}
	public int getSynchro()
	{
		return synchro;
	}
	public void setSynchro(int synchro)
	{
		this.synchro = synchro;
	}
	
	@Override
	public String toString()
	{
		return "标题:"+this.title+"\n 内容："+this.content+"\n时间："+this.time;
	}
	
}
