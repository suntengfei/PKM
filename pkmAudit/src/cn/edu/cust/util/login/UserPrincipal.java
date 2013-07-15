package cn.edu.cust.util.login;

import java.security.Principal;

public class UserPrincipal implements Principal {
	private String name;
	
	public UserPrincipal(String name){
		this.name = name;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
