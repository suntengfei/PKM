package com.suntf.pkm.model;

public class TagDairy
{
	private int id;
	private int tid;
	private String dairy_target;
	public TagDairy(){}
	public TagDairy(int tid,String dairy_target)
	{
		this.tid = tid;
		this.dairy_target = dairy_target;
	}
	
	public TagDairy(int id,int tid,String dairy_target)
	{
		this.id = id;
		this.tid = tid;
		this.dairy_target = dairy_target;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getTid()
	{
		return tid;
	}
	public void setTid(int tid)
	{
		this.tid = tid;
	}
	public String getDairy_target()
	{
		return dairy_target;
	}
	public void setDid(String dairy_target)
	{
		this.dairy_target = dairy_target;
	}
	
}
