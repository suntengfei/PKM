package com.suntf.pkmserver.model;

public class User {
	private int id;
	private String name;
	private String password;
	private int dairy_count;
	private int sh_count;
	
	public User(int id,String name,String password,int dairy_count,int sh_count)
	{
		this.id = id;
		this.name = name;
		this.password = password;
		this.dairy_count = dairy_count;
		this.sh_count = sh_count;
	}
	
	public User(){}
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getDairy_count() {
		return dairy_count;
	}
	public void setDairy_count(int dairyCount) {
		dairy_count = dairyCount;
	}
	public int getSh_count() {
		return sh_count;
	}
	public void setSh_count(int shCount) {
		sh_count = shCount;
	}
}
