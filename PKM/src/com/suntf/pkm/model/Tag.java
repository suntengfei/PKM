package com.suntf.pkm.model;

public class Tag
{
	private int id;
	private String name;
	public Tag(int id,String name)
	{
		this.id = id;
		this.name = name;
	}
	public Tag(){}
	
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
	
	public String toString()
	{
		return "id: "+String.valueOf(this.id)+"  name: "+this.name;
	}
}
